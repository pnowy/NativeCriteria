package com.github.pnowy.nc.core.exceptions;

/**
 * //todo improve exception stack
 *
 * <p>Basic class for all NativeCriteria exceptions.</p>
 */
public abstract class NativeCriteriaException extends RuntimeException {

    /**
     * Construct a {@code NestedRuntimeException} with the specified detail message.
     * @param msg the detail message
     */
    public NativeCriteriaException(String msg) {
        super(msg);
    }

    /**
     * Construct a {@code NestedRuntimeException} with the specified detail message
     * and nested exception.
     * @param msg the detail message
     * @param cause the nested exception
     */
    public NativeCriteriaException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
