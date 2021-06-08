package lerrain.tool.script.warlock.statement;

import lerrain.tool.formula.Factors;
import lerrain.tool.formula.Value;
import lerrain.tool.script.warlock.Code;
import lerrain.tool.script.warlock.analyse.Words;

public class ArithmeticBitOr extends Arithmetic2Elements
{
	public ArithmeticBitOr(Words ws, int i)
	{
		super(ws, i);
	}

	public Object run(Factors factors)
	{
		Object l1 = v(l, factors);
		Object l2 = v(r, factors);

		if (l1 instanceof Long || l2 instanceof Long)
			return ((Number)l1).longValue() | ((Number)l2).longValue();
		else
			return ((Number)l1).intValue() | ((Number)l2).intValue();
	}
	
	private boolean v(Code c, Factors factors)
	{
		return Value.booleanOf(c.run(factors));
	}

	public String toText(String space, boolean line)
	{
		return l.toText("", line) + " AND " + r.toText("", line);
	}
}
