package com.acazia.testjavaspring.common;

public class Utils {

    public static String getProperty(Object object) {

        return object != null ? object.toString() : null;
    }
}
