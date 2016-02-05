package com.github.fhtw.swp.tutorium.common;

import java.net.URL;

public class SwpTestContext {

    private static URL url;

    public static void setJarUrl(URL url) {
        SwpTestContext.url = url;
    }

    public static URL getUrl() {
        return url;
    }
}
