package com.github.pnowy.nc.domain;

/**
 * Base class for all entities.
 *
 * @author Przemek Nowak [przemek dot nowak dot pl at gmail.com]
 */
public abstract class AbstractEntity {
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
