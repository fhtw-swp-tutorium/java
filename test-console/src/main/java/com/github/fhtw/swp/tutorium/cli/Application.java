package com.github.fhtw.swp.tutorium.cli;

import com.github.fhtw.swp.tutorium.common.ConfigurationFactory;
import info.cukes.feather.FeatherObjectFactory;
import org.codejargon.feather.Feather;

public class Application {

    public static void main(String[] args) {

        final Feather feather = Feather.with(
                new ConfigurationFactory()
        );

        FeatherObjectFactory.setInstance(feather);

        final SwpTestTool tool = feather.instance(SwpTestTool.class);
        tool.run(args);
    }
}
