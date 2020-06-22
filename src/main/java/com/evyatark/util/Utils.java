package com.evyatark.util;

import java.util.UUID;

public class Utils {

    public static String createUUID() {
        return UUID.randomUUID().toString();
    }

    public static boolean isEmpty(String id) {
        return ((id == null) || (id.length() == 0));
    }
}
