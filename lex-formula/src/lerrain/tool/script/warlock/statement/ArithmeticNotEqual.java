package lerrain.tool.script.warlock.statement;

import lerrain.tool.formula.Factors;
import lerrain.tool.formula.Value;
import lerrain.tool.script.warlock.Code;
import lerrain.tool.script.warlock.analyse.Expression;
import lerrain.tool.script.warlock.analyse.Words;

public class ArithmeticNotEqual extends Arithmetic2Elements
{
	public ArithmeticNotEqual(Words ws, int i)
	{
		super(ws, i);
	}

	public Object run(Factors factors)
	{
		Value v1 = Value.valueOf(l, factors);
		Value v2 = Value.valueOf(r, factors);
		
		if (v1.isNull() && v2.isNull())
			return new Boolean(false);
		else if (!v1.isNull() && !v2.isNull())
		{
			if (v1.isDecimal() && v2.isDecimal())
				return new Boolean(v1.doubleValue() != v2.doubleValue());
			else
				return new Boolean(!v1.getValue().equals(v2.getValue()));
		}
		else
			return new Boolean(true);
	}

	public String toText(String space, boolean line)
	{
		return l.toText("", line) + " <> " + r.toText("", line);
	}
}
