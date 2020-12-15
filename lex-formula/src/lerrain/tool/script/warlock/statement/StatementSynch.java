package lerrain.tool.script.warlock.statement;

import lerrain.tool.formula.Factors;
import lerrain.tool.script.Script;
import lerrain.tool.script.Stack;
import lerrain.tool.script.warlock.Code;
import lerrain.tool.script.warlock.analyse.Syntax;
import lerrain.tool.script.warlock.analyse.Words;

public class StatementSynch extends Code
{
	Code pre, code;

	public StatementSynch(Words ws)
	{
		super(ws);

		int left = 1;
		if (ws.getType(left) == Words.PRT)
		{
			int right = Syntax.findRightBrace(ws, left + 1);
			Words preWords = ws.cut(left + 1, right);
			pre = preWords.size() > 0 ? new Script(preWords) : null;
			left = right + 1;
		}

		if (ws.getType(left) != Words.BRACE)
			throw new RuntimeException("synch 代码体需要以大括号包裹");

		int right = Syntax.findRightBrace(ws, left + 1);
		code = new Script(ws.cut(left + 1, right));
	}

	public Object run(final Factors factors)
	{
		super.debug(factors);

		final Stack stack = new Stack(factors);
		Object r = pre == null ? null : pre.run(stack);

		synchronized (r == null ? code : r)
		{
			return code.run(stack);
		}
	}

	public Code markBreakPoint(int pos)
	{
		if (code.isPointOn(pos))
			return code.markBreakPoint(pos);
		else if (pre != null && pre.isPointOn(pos))
			return pre.markBreakPoint(pos);

		return super.markBreakPoint(pos);
	}

	public void clearBreakPoints()
	{
		code.clearBreakPoints();

		if (pre != null)
			pre.clearBreakPoints();

		super.clearBreakPoints();
	}

	@Override
	public boolean isFixed()
	{
		return (pre == null || pre.isFixed()) && code.isFixed();
	}

	public String toText(String space, boolean line)
	{
		StringBuffer buf = new StringBuffer("SYNCH (");
		buf.append(pre == null ? "" : pre.toText("", line));
		buf.append(")\n");
		buf.append(space + "{\n");
		buf.append(code.toText(space + "    ", line) + "\n");
		buf.append(space + "}");

		return buf.toString();
	}
}
