package com.github.pnowy.nc.utils;

/**
 * Class for additional Strings operations. Is embedded in Core
 * in order to avoid additional (non necessary) dependencies.
 */
public class Strings {

    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

}
