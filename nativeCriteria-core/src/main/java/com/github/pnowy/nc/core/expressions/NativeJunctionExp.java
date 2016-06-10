package com.github.pnowy.nc.core.expressions;

import java.util.List;

/**
 * Interface for junction expression.
 */
public interface NativeJunctionExp extends NativeExp {

    /**
     * Adds the.
     *
     * @param exp the exp
     * @return the native junction exp
     */
    NativeJunctionExp add(NativeExp exp);

    /**
     * Adds the.
     *
     * @param exps the exps
     * @return the native junction exp
     */
    NativeJunctionExp add(List<NativeExp> exps);
}
