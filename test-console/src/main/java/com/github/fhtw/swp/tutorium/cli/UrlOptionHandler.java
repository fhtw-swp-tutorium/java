package com.github.fhtw.swp.tutorium.cli;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.OptionDef;
import org.kohsuke.args4j.spi.OptionHandler;
import org.kohsuke.args4j.spi.Parameters;
import org.kohsuke.args4j.spi.Setter;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;

public class UrlOptionHandler extends OptionHandler<URL> {

    public UrlOptionHandler(CmdLineParser parser, OptionDef option, Setter<? super URL> setter) {
        super(parser, option, setter);
    }

    @Override
    public int parseArguments(Parameters parameters) throws CmdLineException {

        final String urlString = parameters.getParameter(0);

        try {
            final URL url = Paths.get(urlString).toUri().toURL();

            setter.addValue(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        return 1;
    }

    @Override
    public String getDefaultMetaVariable() {
        return null;
    }
}
