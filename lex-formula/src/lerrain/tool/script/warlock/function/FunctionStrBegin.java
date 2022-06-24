package lerrain.tool.script.warlock.function;

import lerrain.tool.formula.Factors;
import lerrain.tool.formula.Function;
import lerrain.tool.script.ScriptRuntimeException;

public class FunctionStrBegin implements Function
{
	public Object run(Object[] v, Factors factors)
	{
		if (v.length == 2)
		{
			String r = (String)v[0];
			String r1 = (String)v[1];
			
			return new Boolean(r.startsWith(r1));
		}
		
		throw new ScriptRuntimeException("错误的str_begin运算");
	}
}
