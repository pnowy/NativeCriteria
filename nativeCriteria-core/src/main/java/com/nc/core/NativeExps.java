package com.nc.core;

import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;

import java.util.Collection;
import java.util.List;

import com.nc.core.expressions.*;
import com.nc.core.expressions.NativeJoin.JoinType;

/**
 * Class to build native sql expressions. The best way to build expressions - static import methods from this class.
 */
public class NativeExps
{
	
	/**
	 * Equal expression
	 *
	 * @param columnName the column name
	 * @param value the value
	 * @return the native exp
	 */
	public static NativeExp eq(String columnName, Object value)
	{
		return new NativeEqExp(columnName, value);
	}
	
	/**
	 * Not equal expression.
	 *
	 * @param columnName the column name
	 * @param value the value
	 * @return the native exp
	 */
	public static NativeExp notEq(String columnName, Object value)
	{
		return new NativeNotEqExp(columnName, value);
	}
	
	/**
	 * In expression
	 *
	 * @param columnName the column name
	 * @param values the values
	 * @return the native exp
	 */
	public static NativeExp in(String columnName, 
			@SuppressWarnings("rawtypes") Collection values)
	{
		return new NativeInExp(columnName, values);
	}

    /**
     * In expression for collection.
     * This method also service null values in collection, collections with one element (throught equal) and empty collections
     * (empty result by adding sql 1=0)
     *
     * @param columnName the column name
     * @param values the values
     * @return the native exp
     */
    public static NativeExp inSmart(String columnName, Collection values) {
        switch(values.size()){
            case 0: {
                return sql("0=1");
            }
            case 1: {
                Object value = values.iterator().next();
                if(value == null) {
                    return isNull(columnName);
                } else {
                    return eq(columnName, value);
                }
            }
            default: {
                if(values.contains(null)){
                    return disjunction()
                            .add(inSmart(columnName, Collections2.filter(values, Predicates.notNull())))
                            .add(isNull(columnName));
                } else {
                    return in(columnName, values);
                }
            }
        }
    }
	
	/**
	 * In expression for array.
	 *
	 * @param columnName the column name
	 * @param values the values
	 * @return the native exp
	 */
	public static NativeExp in(String columnName, Object[] values)
	{
		return new NativeInExp(columnName, values);
	}
	
	/**
	 * Not in expression for collection.
	 *
	 * @param columnName the column name
	 * @param values the values
	 * @return the native exp
	 */
	public static NativeExp notIn(String columnName, 
			@SuppressWarnings("rawtypes") List values)
	{
		return new NativeNotInExp(columnName, values);
	}
	
	/**
	 * Not in expression for array.
	 *
	 * @param columnName the column name
	 * @param values the values
	 * @return the native exp
	 */
	public static NativeExp notIn(String columnName, Object[] values)
	{
		return new NativeNotInExp(columnName, values);
	}
	
	/**
	 * Is not null expression.
	 *
	 * @param columnName the column name
	 * @return the native exp
	 */
	public static NativeExp isNotNull(String columnName)
	{
		return new NativeIsNotNullExp(columnName);
	}
	
	/**
	 * Is null expression.
	 *
	 * @param columnName the column name
	 * @return the native exp
	 */
	public static NativeExp isNull(String columnName)
	{
		return new NativeIsNullExp(columnName);
	}
	
	/**
	 * Like expression.
	 *
	 * @param columnName the column name
	 * @param value the value
	 * @return the native exp
	 */
	public static NativeExp like(String columnName, String value)
	{
		return new NativeLikeExp(columnName, value);
	}
	
	/**
	 * Not like expression.
	 *
	 * @param columnName the column name
	 * @param value the value
	 * @return the native exp
	 */
	public static NativeExp notLike(String columnName, String value)
	{
		return new NativeNotLikeExp(columnName, value);
	}
	
	/**
	 * Between expression.
	 *
	 * @param columnName the column name
	 * @param lowValue the low value
	 * @param highValue the high value
	 * @return the native exp
	 */
	public static NativeExp between(String columnName, Object lowValue, Object highValue)
	{
		return new NativeBetweenExp(columnName, lowValue, highValue);
	}
	
	/**
	 * Not between expression.
	 *
	 * @param columnName the column name
	 * @param lowValue the low value
	 * @param highValue the high value
	 * @return the native exp
	 */
	public static NativeExp notBetween(String columnName, Object lowValue, Object highValue)
	{
		return new NativeNotBetweenExp(columnName, lowValue, highValue);
	}
	
	/**
	 * Greater expression.
	 *
	 * @param columnName the column name
	 * @param value the value
	 * @return the native exp
	 */
	public static NativeExp gt(String columnName, Object value)
	{
		return new NativeGtExp(columnName, value);
	}
	
	/**
	 * Greater equal expression.
	 *
	 * @param columnName the column name
	 * @param value the value
	 * @return the native exp
	 */
	public static NativeExp gte(String columnName, Object value)
	{
		return new NativeGteExp(columnName, value);
	}
	
	/**
	 * Lower expression.
	 *
	 * @param columnName the column name
	 * @param value the value
	 * @return the native exp
	 */
	public static NativeExp lt(String columnName, Object value)
	{
		return new NativeLtExp(columnName, value);
	}
	
	/**
	 * Lower equal expression.
	 *
	 * @param columnName the column name
	 * @param value the value
	 * @return the native exp
	 */
	public static NativeExp lte(String columnName, Object value)
	{
		return new NativeLteExp(columnName, value);
	}
	
	/**
	 * Any sql expression.
	 *
	 * @param sql the sql
	 * @return the native exp
	 */
	public static NativeExp sql(String sql)
	{
		return new NativeSqlExp(sql);
	}
	
	/**
	 * Create order by object for native SQL.
	 *
	 * @return the native order exp
	 */
	public static NativeOrderExp order()
	{
		return new NativeOrderExp();
	}
	
	/**
	 * Projection.
	 *
	 * @return the native projection
	 */
	public static NativeProjection projection()
	{
		return new NativeProjection();
	}

	/**
	 * Exist expression
	 * @param subquery native subquery
	 *
	 * @return the native expression
	 */
    public static NativeExp exists(final NativeExp subquery) {
        return new NativeExp(){
            @Override
            public String toSQL() {
                return " EXISTS (" + subquery.toSQL() + ")";
            }

            @Override
            public void setValues(NativeQuery query) {
                subquery.setValues(query);
            }
        };
    }
	
	/**
	 * Inner join.
	 *
	 * @param tableName the table name
	 * @param tableAlias the table alias
	 * @param leftColumn the left column
	 * @param rightColumn the right column
	 * @return the native join
	 */
	public static NativeJoin innerJoin(String tableName, String tableAlias, String leftColumn, String rightColumn)
	{
		return new NativeJoin(tableName, tableAlias, JoinType.INNER, leftColumn, rightColumn);
	}
    
    public static NativeJoin innerJoin(String tableName, String tableAlias, NativeExp complexJoinExp ){
        return new NativeJoin(tableName,tableAlias, JoinType.INNER, complexJoinExp);
    }
	
	/**
	 * Right outer join.
	 *
	 * @param tableName the table name
	 * @param tableAlias the table alias
	 * @param leftColumn the left column
	 * @param rightColumn the right column
	 * @return the native join
	 */
	public static NativeJoin rightJoin(String tableName, String tableAlias, String leftColumn, String rightColumn)
	{
		return new NativeJoin(tableName, tableAlias, JoinType.RIGHT_OUTER, leftColumn, rightColumn);
	}

	/**
	 * Left outer join.
	 *
	 * @param tableName the table name
	 * @param tableAlias the table alias
	 * @param leftColumn the left column
	 * @param rightColumn the right column
	 * @return the native join
	 */
	public static NativeJoin leftJoin(String tableName, String tableAlias, String leftColumn, String rightColumn)
	{
		return new NativeJoin(tableName, tableAlias, JoinType.LEFT_OUTER, leftColumn, rightColumn);
	}
	
	/**
	 * Full outer join.
	 *
	 * @param tableName the table name
	 * @param tableAlias the table alias
	 * @param leftColumn the left column
	 * @param rightColumn the right column
	 * @return the native join
	 */
	public static NativeJoin fullJoin(String tableName, String tableAlias, String leftColumn, String rightColumn)
	{
		return new NativeJoin(tableName, tableAlias, JoinType.FULL_OUTER, leftColumn, rightColumn);
	}
	
	/**
	 * Inner join.
	 *
	 * @param tableName the table name
	 * @param tableAlias the table alias
	 * @return the native join
	 */
	public static NativeJoin naturalJoin(String tableName, String tableAlias)
	{
		return new NativeJoin(tableName, tableAlias, JoinType.NATURAL);
	}
	
	/**
	 * Cross join.
	 *
	 * @param tableName the table name
	 * @param tableAlias the table alias
	 * @return the native join
	 */
	public static NativeJoin crossJoin(String tableName, String tableAlias)
	{
		return new NativeJoin(tableName, tableAlias, JoinType.CROSS);
	}
	
	/**
	 * Expressions crossing with AND operator.
	 *
	 * @return the native junction exp
	 */
	public static NativeJunctionExp conjunction()
	{
		return new NativeConjunctionExp();
	}
	
	/**
	 * Expressions crossing with AND operator.
	 *
	 * @param exps the exps
	 * @return the native junction exp
	 */
	public static NativeJunctionExp conjunction(List<NativeExp> exps)
	{
		return new NativeConjunctionExp(exps);
	}
	
	/**
	 * Expressions crossing with OR operator.
	 *
	 * @return the native junction exp
	 */
	public static NativeJunctionExp disjunction()
	{
		return new NativeDisjunctionExp();
	}
	
	/**
	 * Expressions crossing with OR operator..
	 *
	 * @param exps the exps
	 * @return the native junction exp
	 */
	public static NativeJunctionExp disjunction(List<NativeExp> exps)
	{
		return new NativeDisjunctionExp(exps);
	}
}
