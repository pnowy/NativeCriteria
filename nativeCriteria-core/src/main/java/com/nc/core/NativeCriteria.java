package com.nc.core;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.nc.core.expressions.NativeExp;
import com.nc.core.expressions.NativeJoin;
import com.nc.core.expressions.NativeOrderExp;
import com.nc.core.expressions.NativeProjection;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Class to build native sql queries.
 */
public class NativeCriteria implements NativeExp
{
	private static final Logger LOG = LoggerFactory.getLogger(NativeCriteria.class);

	/**
	 * Hibernate session.
	 */
	private NativeQueryProvider nativeProvider;

	/**
	 * Tables with aliases to FROM clause.
	 */
	private Map<String, String> tables;

	/**
	 * Joins.
	 */
	private List<NativeJoin> joins;

	/**
	 * WHERE expressions.
	 */
	private Map<NativeExp, Operator> whereExps;

	/**
	 * Having expressions.
	 */
	private Map<NativeExp, Operator> havingExps;

	/**
	 * Order expression.
	 */
	private NativeOrderExp orderExp;

	/**
	 * Limit (max result).
	 */
	private Integer limit;

	/**
	 * Offset (first result).
	 */
	private Integer offset;

	/**
	 * Distintc.
	 */
	private boolean distinct;

	/**
	 * Projections.
	 */
	private NativeProjection projection;

	/**
	 * SQL operators.
	 */
	private enum Operator
	{

		/**
		 * The AND.
		 */
		AND("AND"),

		/**
		 * The OR.
		 */
		OR("OR");

		/**
		 * The value.
		 */
		private String value;

		/**
		 * Instantiates a new operator.
		 *
		 * @param value the value
		 */
		private Operator(String value)
		{
			this.value = value;
		}

		/**
		 * Gets the value.
		 *
		 * @return the value
		 */
		public String getValue()
		{
			return value;
		}
	}

	/**
	 * Construcotr.
	 *
	 * @param nativeProvider native select provider
	 * @param mainTable      the main table
	 * @param mainAlias      the main alias
	 */
	public NativeCriteria(NativeQueryProvider nativeProvider, String mainTable, String mainAlias)
	{
		if (nativeProvider == null)
		{
			throw new IllegalStateException("NativeProvider is null!");
		}
		if (StringUtils.isBlank(mainTable))
		{
			throw new IllegalStateException("mainTable is null");
		}
		if (StringUtils.isBlank(mainAlias))
		{
			throw new IllegalStateException("mainAlias is null");
		}

		this.nativeProvider = nativeProvider;
		this.distinct = false;

		// main table
		this.tables = new LinkedHashMap<String, String>();
		this.tables.put(mainTable, mainAlias);

		this.whereExps = new LinkedHashMap<NativeExp, Operator>();
		this.havingExps = new LinkedHashMap<NativeExp, Operator>();
		this.joins = new ArrayList<NativeJoin>();
	}

	/**
	 * Set native provider.
	 *
	 * @param nativeProvider
	 */
	public void setNativeProvider(NativeQueryProvider nativeProvider)
	{
		this.nativeProvider = nativeProvider;
	}

	/**
	 * Add table to FROM clause.
	 *
	 * @param tableName  the table name
	 * @param tableAlias the table alias
	 * @return the native criteria
	 */
	public NativeCriteria addTable(String tableName, String tableAlias)
	{
		this.tables.put(tableName, tableAlias);
		return this;
	}

	/**
	 * Set projection.
	 *
	 * @param projection the projection
	 * @return the native criteria
	 */
	public NativeCriteria setProjection(NativeProjection projection)
	{
		this.projection = projection;
		return this;
	}

	/**
	 * Gets the projection.
	 *
	 * @return the projection
	 */
	public NativeProjection getProjection()
	{
		return projection;
	}

	/**
	 * Add new condition to query.
	 *
	 * @param exp the exp
	 * @return the native criteria
	 */
	public NativeCriteria add(NativeExp exp)
	{
		if (exp == null)
		{
			throw new IllegalStateException("Object exp is null!");
		}

		whereExps.put(exp, Operator.AND);
		return this;
	}

	/**
	 * Add "or" condition to query.
	 *
	 * @param exp the exp
	 * @return the native criteria
	 */
	public NativeCriteria addOr(NativeExp exp)
	{
		if (exp == null)
		{
			throw new IllegalStateException("Object exp is null!");
		}

		whereExps.put(exp, Operator.OR);
		return this;
	}

	/**
	 * Add having AND.
	 *
	 * @param exp the exp
	 * @return the native criteria
	 */
	public NativeCriteria addHaving(NativeExp exp)
	{
		if (exp == null)
		{
			throw new IllegalStateException("Object exp is null!");
		}

		havingExps.put(exp, Operator.AND);
		return this;
	}

	/**
	 * Add having OR.
	 *
	 * @param exp the exp
	 * @return the native criteria
	 */
	public NativeCriteria addOrHaving(NativeExp exp)
	{
		if (exp == null)
		{
			throw new IllegalStateException("Object exp is null!");
		}

		havingExps.put(exp, Operator.OR);
		return this;
	}

	/**
	 * Add join.
	 *
	 * @param join the join
	 * @return the native criteria
	 */
	public NativeCriteria addJoin(NativeJoin join)
	{
		if (join == null)
		{
			throw new IllegalStateException("Object exp is null!");
		}

		joins.add(join);
		return this;
	}

	/**
	 * Add order by.
	 *
	 * @param orderExp the order exp
	 * @return the native criteria
	 */
	public NativeCriteria setOrder(NativeOrderExp orderExp)
	{
		this.orderExp = orderExp;
		return this;
	}

	/**
	 * Set distinct to query.
	 *
	 * @param distinct the distinct
	 * @return the native criteria
	 */
	public NativeCriteria setDistinct(boolean distinct)
	{
		this.distinct = distinct;
		return this;
	}

	/**
	 * Set limitu.
	 *
	 * @param limit the limit
	 * @return the native criteria
	 */
	public NativeCriteria setLimit(Integer limit)
	{
		this.limit = limit;
		return this;
	}

	/**
	 * Set offsetu.
	 *
	 * @param offset the offset
	 * @return the native criteria
	 */
	public NativeCriteria setOffset(Integer offset)
	{
		this.offset = offset;
		return this;
	}

	/**
	 * Return result as {@link CriteriaResult}
	 *
	 * @return the criteria result
	 */
	public CriteriaResult criteriaResult()
	{
		return new CriteriaResultImpl(list(), projection);
	}

	/**
	 * Return result as objects arrays lists.
	 *
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> list()
	{
		NativeQuery query = buildCriteriaQuery();
		return query.list();
	}

//	@SuppressWarnings("unchecked")
//	public List<Object[]> list()
//	{
//		SQLQuery query = buildCriteriaQuery();
//		if (logPerformance)
//		{
//			long startTime = System.currentTimeMillis();
//			List<Object[]> res = query.list();
//			checkEndExecutionTime(startTime);
//
//			return res;
//		}
//		else
//			return query.list();
//	}

//	public Object uniqueResult()
//	{
//		SQLQuery query = buildCriteriaQuery();
//		if (logPerformance)
//		{
//			long startTime = System.currentTimeMillis();
//			Object res = query.uniqueResult();
//			checkEndExecutionTime(startTime);
//
//			return res;
//		}
//		else
//			return query.uniqueResult();
//	}

	/**
	 * Unique result.
	 *
	 * @return the object
	 */
	public Object uniqueResult()
	{
		NativeQuery query = buildCriteriaQuery();
		return query.uniqueResult();
	}

	//TODO implement measure performance
//	private void checkEndExecutionTime(long startTime)
//	{
//		long finishTime = System.currentTimeMillis();
//		long executionTime = finishTime - startTime;
//
//		if (executionTime > longExecutionTime)
//		{
//			PERFORMANCE_LOG.error("Dlugi czas wykonania zapytania ({}ms, - {}",
//					executionTime, getSQL());
//		}
//	}

	/**
	 * Return query SQL.
	 *
	 * @return the sQL
	 */
	public String getSQL()
	{
		return buildSQL();
	}

	/**
	 * Build object SQLQuery based on created criteria query.
	 *
	 * @return the sQL query
	 */
	private NativeQuery buildCriteriaQuery()
	{
		NativeQuery nativeQuery = nativeProvider.getNativeQuery(buildSQL());
		setValues(nativeQuery);

		return nativeQuery;
	}

	/**
	 * Build SQL query.
	 *
	 * @return the string
	 */
	private String buildSQL()
	{
		final String SPACE = " ";

		StringBuilder sqlBuilder = new StringBuilder();

		// clause select i distinct
		sqlBuilder.append("SELECT").append(SPACE);
		if (distinct)
		{
			sqlBuilder.append("DISTINCT").append(SPACE);
		}

		// columns
		appendProjectionSQL(sqlBuilder);

		// FROM
		appendFromSQL(sqlBuilder);

		// JOIN
		appendJoinSQL(sqlBuilder);

		// WHERE
		appendWhereSQL(sqlBuilder);

		// GROUP BY (PROJECTIONS)
		appendGroupBySQL(sqlBuilder);

		// HAVING
		appendHavingSQL(sqlBuilder);

		// ORDER BY
		appendOrderBySQL(sqlBuilder);

		String sql = sqlBuilder.toString();
		LOG.debug("NativeCriteria SQL: " + sql);
		return sql;
	}

	/**
	 * Add having clause.
	 *
	 * @param sqlBuilder the sql builder
	 */
	private void appendHavingSQL(StringBuilder sqlBuilder)
	{
		final String SPACE = " ";
		boolean first = true;
		if (havingExps.size() > 0)
		{
			sqlBuilder.append("HAVING").append(SPACE);

			for (Entry<NativeExp, Operator> exp : havingExps.entrySet())
			{
				if (first)
				{
					sqlBuilder.append(exp.getKey().toSQL()).append(SPACE);
					first = false;
				}
				else
				{
					sqlBuilder.append(exp.getValue().getValue())
							.append(SPACE)
							.append(exp.getKey().toSQL())
							.append(SPACE);
				}
			}
		}
	}

	/**
	 * Add group by clause (projections).
	 *
	 * @param sqlBuilder the sql builder
	 */
	private void appendGroupBySQL(StringBuilder sqlBuilder)
	{
		if (projection == null)
		{
			return;
		}

		sqlBuilder.append(projection.groupByToSQL());
	}

	/**
	 * Add order by clause.
	 *
	 * @param sqlBuilder the sql builder
	 */
	private void appendOrderBySQL(StringBuilder sqlBuilder)
	{
		if (orderExp == null)
		{
			return;
		}

		sqlBuilder.append(orderExp.toSQL());
	}

	/**
	 * Add where clause
	 *
	 * @param sqlBuilder the sql builder
	 */
	private void appendWhereSQL(StringBuilder sqlBuilder)
	{
		final String SPACE = " ";
		boolean first = true;
		if (whereExps.size() > 0)
		{
			sqlBuilder.append("WHERE").append(SPACE);

			for (Entry<NativeExp, Operator> exp : whereExps.entrySet())
			{
				if (first)
				{
					sqlBuilder.append(exp.getKey().toSQL()).append(SPACE);
					first = false;
				}
				else
				{
					sqlBuilder.append(exp.getValue().getValue())
							.append(SPACE)
							.append(exp.getKey().toSQL())
							.append(SPACE);
				}
			}
		}
	}

	/**
	 * Add join.
	 *
	 * @param sqlBuilder the sql builder
	 */
	private void appendJoinSQL(StringBuilder sqlBuilder)
	{
		final String SPACE = " ";
		for (NativeJoin join : joins)
		{
			sqlBuilder.append(join.toSQL()).append(SPACE);
		}
	}

	/**
	 * Add FROM clause.
	 *
	 * @param sqlBuilder the sql builder
	 */
	private void appendFromSQL(StringBuilder sqlBuilder)
	{
		final String SPACE = " ";
		sqlBuilder.append(SPACE).append("FROM").append(SPACE);

		boolean first = true;
		for (Entry<String, String> from : tables.entrySet())
		{
			if (first)
			{
				sqlBuilder.append(from.getKey()).append(SPACE)
						.append(from.getValue());
				first = false;
			}
			else
			{
				sqlBuilder.append(", ").append(from.getKey()).append(SPACE)
						.append(from.getValue());
			}
		}

		sqlBuilder.append(SPACE);
	}

	/**
	 * Add column to sql builder.
	 *
	 * @param sqlBuilder the sql builder
	 */
	private void appendProjectionSQL(StringBuilder sqlBuilder)
	{
		// if column was defined add to sql
		if (projection != null && projection.hasProjections())
		{
			sqlBuilder.append(projection.projectionToSQL());
		}

		if (projection == null || projection.countProjections() == 0)
		{
			boolean first = true;
			for (String alias : tables.values())
			{
				if (first)
				{
					sqlBuilder.append(alias).append(".*");
					first = false;
				}
				else
				{
					sqlBuilder.append(", ").append(alias).append(".*");
				}
			}
			if (joins.size() > 0)
			{
				for (NativeJoin join : joins)
				{
					sqlBuilder.append(", ")
							.append(join.getTableAlias())
							.append(".*");
				}
			}
		}
	}

	@Override
	public String toSQL()
	{
		return buildSQL();
	}

	@Override
	public void setValues(NativeQuery sqlQuery)
	{
		// subqueries
		if (projection != null)
		{
			for (NativeCriteria cr : projection.getSubqueries())
			{
				cr.setValues(sqlQuery);
			}
		}

		// parameters where clause
		for (NativeExp exp : whereExps.keySet())
		{
			exp.setValues(sqlQuery);
		}
		for (NativeExp exp : havingExps.keySet())
		{
			exp.setValues(sqlQuery);
		}

		for (NativeJoin join : joins)
		{
			join.setValues(sqlQuery);
		}

		if (limit != null)
		{
			sqlQuery.setMaxResults(limit);
		}
		if (offset != null)
		{
			sqlQuery.setFirstResult(offset);
		}
	}
}
