package lerrain.tool.script.warlock.statement;

import lerrain.tool.formula.Factors;
import lerrain.tool.script.Stack;
import lerrain.tool.script.warlock.Code;
import lerrain.tool.script.warlock.Interrupt;
import lerrain.tool.script.warlock.analyse.Expression;
import lerrain.tool.script.warlock.analyse.Words;

import java.util.Arrays;
import java.util.List;

public class StatementReturn extends Code
{
	Code r;
	
	public StatementReturn(Words ws)
	{
		super(ws);

		Words exp = ws.cut(1);
		if (exp.size() != 0)
			r = Expression.expressionOf(exp);
	}

	public Object run(Factors factors)
	{
		super.debug(factors);

		Object val = r == null ? null : r.run(factors);

		if (factors instanceof Stack)
		{
			Stack stack = (Stack) factors;

			if (stack.getDebug() != Stack.RUNNING && stack.getBreakListener() != null)
				stack.getBreakListener().onReturn(this, stack, val);
		}

		throw new Interrupt.Return(val);
	}

	@Override
	public boolean isFixed()
	{
		return r == null || r.isFixed();
	}

	public String toText(String space, boolean line)
	{
		return space + "RETURN " + (r == null ? "" : r.toText(space + "  ", line));
	}

	@Override
	public List<Code> getChildren()
	{
		return Arrays.asList(r);
	}
}
