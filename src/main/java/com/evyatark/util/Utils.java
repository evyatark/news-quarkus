package com.evyatark.util;

import java.util.UUID;

public class Utils {

    public static String createUUID() {
        return UUID.randomUUID().toString();
    }

    public static boolean isEmpty(final String id) {
        return ((id == null) || (id.length() == 0));
    }

    // from /gallery/music/.premium-1.8952956   return  1.8952956
    public static String findLastDigits(final String url) {
        String str = url;
        String siteId = "";
        if (str.indexOf('?') > 0) {     // sometimes url contains a query parameter
            str = str.substring(0, str.indexOf('?'));
        }

        int index = str.indexOf("1.");
        if (index > 0) {
            siteId = str.substring(index);
        }
        return siteId;
    }
}
