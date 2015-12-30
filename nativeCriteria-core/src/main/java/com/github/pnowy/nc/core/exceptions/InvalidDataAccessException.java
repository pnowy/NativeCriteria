package com.github.pnowy.nc.core.exceptions;

/**
 *
 * <p>Exception which is used for describe the situation when for some reasons we cannot get the data for/from
 * {@linkplain com.github.pnowy.nc.core.CriteriaResult}</p>
 *
 * @author Przemek Nowak [przemek dot nowak dot pl at gmail.com]
 */
public class InvalidDataAccessException extends NativeCriteriaException {

    public InvalidDataAccessException(String msg) {
        super(msg);
    }
}
