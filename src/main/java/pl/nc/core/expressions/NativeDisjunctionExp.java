package pl.nc.core.expressions;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import pl.nc.core.expressions.NativeExp;
import pl.nc.core.expressions.NativeJunctionExp;

/**
 * Native OR expression.
 */
public class NativeDisjunctionExp implements NativeJunctionExp
{
	private List<NativeExp> exps;
	
	public NativeDisjunctionExp()
	{
		this.exps = new ArrayList<NativeExp>();
	}
	
	public NativeDisjunctionExp(List<NativeExp> exps)
	{
		super();
		if (exps != null)
			this.exps = exps;
	}
	
	/**
	 * Add sql expression.
	 *
	 * @param exp the exp
	 * @return the native junction exp
	 */
	@Override
	public NativeJunctionExp add(NativeExp exp)
	{
		if (exp == null)
			throw new IllegalStateException("exp is null!");
		
		exps.add(exp);
		return this;
	}

	/**
	 * Add sql expressions.
	 *
	 * @param exps the exps
	 * @return the native junction exp
	 */
	@Override
	public NativeJunctionExp add(List<NativeExp> exps)
	{
		if (exps == null)
			throw new IllegalStateException("exp is null!");
		
		this.exps.addAll(exps);
		return null;
	}

	@Override
	public void setValues(SQLQuery query)
	{
		for (NativeExp exp : exps)
			exp.setValues(query);
	}

	/**
	 * Return sql query
	 *
	 * @return the string
	 */
	@Override
	public String toSQL()
	{
		if (exps.isEmpty()) return "";
		
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("(");
		final String SPACE = " ";
		boolean first = true;
		for (NativeExp exp : exps)
		{
			if (first)
			{
				sqlBuilder.append(exp.toSQL());
				first = false;
			}
			else
			{
				sqlBuilder.append(SPACE).append("OR").append(SPACE)
					.append(exp.toSQL());
			}
		}
		sqlBuilder.append(")");
		return sqlBuilder.toString();
	}
}
