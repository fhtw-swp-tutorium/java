package com.github.fhtw.swp.tutorium.common;

import java.net.URL;

public class JarFileUrlHolder {

    private static URL url;

    public static void setUrl(URL url) {
        JarFileUrlHolder.url = url;
    }

    public static URL getUrl() {
        return url;
    }
}
