package com.nc.utils;

import org.hibernate.annotations.common.util.StringHelper;
import java.util.Random;

/**
 * Variable names generator.
 */
public class VarGenerator
{
	
	/** Random number generator. */
	private static Random random = new Random();
	
	/**
	 * Method generates the variable name of a specified length.
	 *
	 * @param description the description
	 * @return the string
	 */
	public static String gen(String description)
	{
		return StringHelper.generateAlias(description.replaceAll("\\(|\\)", ""), random.nextInt(1000));
	}
}
