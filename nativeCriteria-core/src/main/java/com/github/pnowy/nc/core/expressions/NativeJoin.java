package com.github.pnowy.nc.core.expressions;

import static com.google.common.base.Preconditions.checkArgument;

import com.github.pnowy.nc.core.NativeQuery;
import com.github.pnowy.nc.utils.Strings;

/**
 * Native Join.
 */
public class NativeJoin
{
	
	/** Table name. */
	private String tableName;
	
	/** Table alias. */
	private String tableAlias;
	
	/** Join type. */
	private JoinType joinType;
	
	/** left join column */
	private String leftColumn;
	
	/** right join column. */
	private String rightColumn;
    
    private NativeExp complexJoinExp;
	
	/**
	 * The Enum JoinType.
	 */
	public enum JoinType
	{
		
		/** The INNER. */
		INNER ("INNER JOIN"),
		
		/** The NATURAL. */
		NATURAL ("NATURAL JOIN"),
		
		/** The CROSS. */
		CROSS ("CROSS JOIN"),
		
		/** The LEF t_ outer. */
		LEFT_OUTER ("LEFT OUTER JOIN"),
		
		/** The RIGH t_ outer. */
		RIGHT_OUTER ("RIGHT OUTER JOIN"),
		
		/** The FUL l_ outer. */
		FULL_OUTER ("FULL OUTER JOIN");
		
		/** The type. */
		private String type;
		
		/**
		 * Instantiates a new join type.
		 *
		 * @param type the type
		 */
		private JoinType(String type)
		{
			this.type = type;
		}
		
		/**
		 * Gets the type.
		 *
		 * @return the type
		 */
		public String getType()
		{
			return type;
		}
	}
    
    public NativeJoin(String tableName, String tableAlias, JoinType joinType, NativeExp complexJoin){
        if (Strings.isBlank(tableName))
            throw new IllegalStateException("tableName is null!");
        if (Strings.isBlank(tableAlias))
            throw new IllegalStateException("tableAlias is null!");
        checkArgument(joinType != JoinType.CROSS && joinType != JoinType.NATURAL);
        this.tableName = tableName;
        this.tableAlias = tableAlias;
        this.joinType = joinType;
        this.complexJoinExp = complexJoin;
    }
	
	/**
	 * Constructor.
	 *
	 * @param tableName the table name
	 * @param tableAlias the table alias
	 * @param joinType the join type
	 * @param leftColumn the left column
	 * @param rightColumn the right column
	 */
	public NativeJoin(String tableName, String tableAlias, JoinType joinType, String leftColumn, String rightColumn)
	{
		if (Strings.isBlank(tableName))
			throw new IllegalStateException("tableName is null!");
		if (Strings.isBlank(tableAlias))
			throw new IllegalStateException("tableAlias is null!");
		if (joinType == null)
			throw new IllegalStateException("joinTYpe is null!");
		else if (joinType.equals(JoinType.NATURAL) || joinType.equals(JoinType.CROSS))
			throw new IllegalArgumentException("This constructor doesn't support natural and cross join!");
		if (Strings.isBlank(rightColumn) || Strings.isBlank(leftColumn))
			throw new IllegalStateException("Incorrect join columns!");
		
		this.tableName = tableName;
		this.tableAlias = tableAlias;
		this.joinType = joinType;
		this.leftColumn = leftColumn;
		this.rightColumn = rightColumn;
	}
    
    public String column(String columnName){
        return tableAlias + "." + columnName;
    }

	/**
	 * Konstruktor.
	 *
	 * @param tableName the table name
	 * @param tableAlias the table alias
	 * @param joinType the join type
	 */
	public NativeJoin(String tableName, String tableAlias, JoinType joinType)
	{
		if (Strings.isBlank(tableName))
			throw new IllegalStateException("tableName is null!");
		if (Strings.isBlank(tableAlias))
			throw new IllegalStateException("tableAlias is null!");
		if (joinType == null)
			throw new IllegalStateException("joinTYpe is null!");
		else if (!joinType.equals(JoinType.NATURAL) && !joinType.equals(JoinType.CROSS))
			throw new IllegalArgumentException("This constructor doesn't support natural and cross join");
		
		this.tableName = tableName;
		this.tableAlias = tableAlias;
		this.joinType = joinType;
	}
	
	/**
	 * Gets the table name.
	 *
	 * @return the table name
	 */
	public String getTableName()
	{
		return tableName;
	}
	
	/**
	 * Gets the table alias.
	 *
	 * @return the table alias
	 */
	public String getTableAlias()
	{
		return tableAlias;
	}
	
	/**
	 * To sql.
	 *
	 * @return the string
	 */
	public String toSQL()
	{
		String joinSQL = joinType.getType() + " " + tableName + " " + tableAlias;
		
        if (joinType.equals(JoinType.NATURAL) || joinType.equals(JoinType.CROSS)){
			return joinSQL;
        } else if (complexJoinExp != null) {
            return joinSQL + " ON " + complexJoinExp.toSQL();
        } else {
		    return joinSQL + " ON " + leftColumn + " = " + rightColumn;
        }
	}

    public void setValues(NativeQuery query) {
        if(complexJoinExp != null){
            complexJoinExp.setValues(query);
        }
    }
}
