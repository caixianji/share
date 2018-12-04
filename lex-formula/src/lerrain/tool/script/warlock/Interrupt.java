package lerrain.tool.script.warlock;

public class Interrupt
{
	public static final int CONTINUE	= 1;
	public static final int BREAK		= 2;
	public static final int RETURN		= 3;
//	public static final int THROW		= 4;
	
	int type;
	
	Object value;

	Code code;
	
	public Interrupt(Code code, int type)
	{
		this(code, type, null);
	}
	
	public Interrupt(Code code, int type, Object value)
	{
		this.code = code;
		this.type = type;
		this.value = value;
	}

	public Object getValue()
	{
		return value;
	}

	public void setValue(Object value)
	{
		this.value = value;
	}

	public int getType()
	{
		return type;
	}
	
	public static Object getValue(Object v)
	{
		return ((Interrupt)v).value;
	}
	
	public static Interrupt interruptOf(Code code, int type, Object v)
	{
		return new Interrupt(code, type, v);
	}
	
	public static Interrupt interruptOf(Code code, int type)
	{
		return interruptOf(code, type, null);
	}
	
	public static boolean isMatch(Object v, int type)
	{
		return v instanceof Interrupt && ((Interrupt)v).getType() == type;
	}

//	public static Interrupt returnOf(Object v)
//	{
//		return new Interrupt(3, v);
//	}
//	
//	public static boolean isReturn(Object v)
//	{
//		return v instanceof Interrupt && ((Interrupt)v).type == 3;
//	}
//	
//	public static Interrupt throwOf(Object v)
//	{
//		return new Interrupt(4, v);
//	}
//	
//	public static boolean isThrow(Object v)
//	{
//		return v instanceof Interrupt && ((Interrupt)v).type == 4;
//	}
//	
//	public static Interrupt CONTINUE()
//	{
//		if (CONTINUE == null)
//			
//		return 
//	}
}
