package lerrain.tool;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class Network
{
	public static String request(String urlstr) throws Exception
	{
		return request(urlstr, null, 500, "GET");
	}

	public static String request(String urlstr, String req) throws Exception
	{
		return request(urlstr, req, 500, req == null ? "GET" : "POST");
	}

	public static String request(String urlstr, String req, int timeout) throws Exception
	{
		return request(urlstr, req, timeout, "POST");
	}

	public static String request(String urlstr, String req, int timeout, String method) throws Exception
	{
		String res;
		
		HttpURLConnection conn = null;
		try
		{
			URL url = new URL(urlstr);
			byte[] info = req == null ? null : req.getBytes("UTF-8");
			
			conn = (HttpURLConnection)url.openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestMethod(method);
			conn.setReadTimeout(timeout);
			conn.setConnectTimeout(timeout);
			conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");

			if (conn instanceof HttpsURLConnection)
			{
				SSLContext sslContext = SSLContext.getInstance("SSL");
				TrustManager[] tm = {new MyX509TrustManager()};
				sslContext.init(null, tm, new java.security.SecureRandom());;
				SSLSocketFactory ssf = sslContext.getSocketFactory();
				((HttpsURLConnection) conn).setSSLSocketFactory(ssf);
			}

			if (info != null)
			{
				conn.setRequestProperty("Content-Length", String.valueOf(info.length));

				try (OutputStream out = conn.getOutputStream())
				{
					out.write(info);
					out.flush();
				}
			}

			//System.out.println(conn.getContentType());

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			try (InputStream in = conn.getInputStream())
			{
				byte[] b = new byte[1024];
				int c = 0;
				while ((c = in.read(b)) >= 0)
				{
					baos.write(b, 0, c);
				}
			}
			baos.close();
			
			res = baos.toString("utf-8");
		}
		catch (Exception e)
		{
			System.out.println("request: " + urlstr + " - " + req + " -> " + e.getMessage());
			throw e;
		}
		finally
		{
			if (conn != null)
				conn.disconnect();
		}

		return res;
	}

	public static String request(String urlstr, byte[] info, int timeout) throws Exception
	{
		String res = null;

		HttpURLConnection conn = null;
		try
		{
			URL url = new URL(urlstr);

			conn = (HttpURLConnection)url.openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setReadTimeout(timeout);
			conn.setConnectTimeout(timeout);
			conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");

			if (conn instanceof HttpsURLConnection)
			{
				SSLContext sslContext = SSLContext.getInstance("SSL");
				TrustManager[] tm = {new MyX509TrustManager()};
				sslContext.init(null, tm, new java.security.SecureRandom());;
				SSLSocketFactory ssf = sslContext.getSocketFactory();
				((HttpsURLConnection) conn).setSSLSocketFactory(ssf);
			}

			if (info != null)
			{
				conn.setRequestProperty("Content-Length", String.valueOf(info.length));

				try (OutputStream out = conn.getOutputStream())
				{
					out.write(info);
					out.flush();
				}
			}

			try (InputStream in = conn.getInputStream())
			{
				res = Common.stringOf(in, "utf-8");
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			if (conn != null)
				conn.disconnect();
		}

		return res;
	}

	public static String request(String urlstr, int timeout) throws Exception
	{
		return request(urlstr, (byte[])null, timeout);
	}
	
	public static boolean download(String urlstr, File dstFile) throws Exception
	{
		File dir = dstFile.getParentFile();
		if (!dir.exists())
		{
			if (!dir.mkdirs())
				return false;
		}
		
		if (!dir.isDirectory())
			return false;
		
		HttpURLConnection conn = null;
		
		try 
		{
			URL url = new URL(urlstr);
			conn = (HttpURLConnection)url.openConnection();
			
			try (InputStream in = conn.getInputStream(); OutputStream out = new FileOutputStream(dstFile))
			{
				byte[] b = new byte[2048];
				int c;
				while ((c = in.read(b)) >= 0)
					out.write(b, 0, c);
			}

		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			if (conn != null)
				conn.disconnect();
		}
		
		return true;
	}

	private static class MyX509TrustManager implements X509TrustManager
	{
		@Override
		public void checkClientTrusted(X509Certificate[] chain, String authType)  throws CertificateException
		{
		}

		@Override
		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException
		{
		}

		@Override
		public X509Certificate[] getAcceptedIssuers()
		{
			return null;
		}
	}
}
