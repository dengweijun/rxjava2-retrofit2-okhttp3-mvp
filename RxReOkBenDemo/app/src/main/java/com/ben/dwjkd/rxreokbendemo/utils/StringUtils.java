package com.ben.dwjkd.rxreokbendemo.utils;

public class StringUtils {

    public static boolean isEmptyString(String str, boolean isAutoTrim) {
        if (isAutoTrim && str != null) {
            str = str.trim();
        }
        if (str == null || str.length() == 0) {
            return true;
        }
        return false;
    }

    public static String getAvailableString(String nullStr) {
        if (nullStr == null) {
            return "";
        }
        return nullStr;
    }

    public static boolean isEmptyString(String str) {
        return isEmptyString(str, false);
    }

    public static boolean equals(String str1, String str2) {
        if (str1 == null && str2 == null) {
            return true;
        }
        if (str1 == null && str2 != null) {
            return false;
        }
        return str1.equals(str2);
    }

    public static boolean equalsIgnoreCase(String str1, String str2) {
        if (str1 == null && str2 == null) {
            return true;
        }
        if (str1 == null && str2 != null) {
            return false;
        }
        return str1.equalsIgnoreCase(str2);
    }

}
