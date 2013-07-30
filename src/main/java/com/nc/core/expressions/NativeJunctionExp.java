package com.nc.core.expressions;

import java.util.List;

/**
 * Interface for joining classes.
 */
public interface NativeJunctionExp extends NativeExp
{
	
	/**
	 * Adds the.
	 *
	 * @param exp the exp
	 * @return the native junction exp
	 */
	public NativeJunctionExp add(NativeExp exp);
	
	/**
	 * Adds the.
	 *
	 * @param exps the exps
	 * @return the native junction exp
	 */
	public NativeJunctionExp add(List<NativeExp> exps);
}
