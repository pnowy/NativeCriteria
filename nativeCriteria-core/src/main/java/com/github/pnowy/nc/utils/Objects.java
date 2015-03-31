package com.github.pnowy.nc.utils;

/**
 * Objects
 */
public class Objects {

    /**
     * <p>Gets the {@code toString} of an {@code Object} returning
     * an empty string ("") if {@code null} input.</p>
     * <p/>
     * <pre>
     * Objects.toString(null)         = ""
     * Objects.toString("")           = ""
     * Objects.toString("bat")        = "bat"
     * Objects.toString(Boolean.TRUE) = "true"
     * </pre>
     *
     * @param obj the Object to {@code toString}, may be null
     * @return the passed in Object's toString, or nullStr if {@code null} input
     */
    public static String toString(Object obj) {
        return obj == null ? "" : obj.toString();
    }

}
