package com.github.fhtw.swp.tutorium.common;

import java.net.URL;

/**
 * @author Thomas Eizinger, Senacor Technologies AG.
 */
public class JarFileUrlHolder {

    private static URL url;

    public static void setUrl(URL url) {
        JarFileUrlHolder.url = url;
    }

    public static URL getUrl() {
        return url;
    }
}
