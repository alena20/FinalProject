package com.epam.finalproject.util;

public class XssProtector {
    private static final String EMPTY = "";
    private static final String SCRIPT_REGEX = "</?script>";

    public static String protect(String line) {
        String securedLine = line;
        if (line != null) {
            securedLine = line.replaceAll(SCRIPT_REGEX, EMPTY);
        }
        return securedLine;
    }
}
