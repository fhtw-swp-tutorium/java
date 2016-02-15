package com.github.fhtw.swp.tutorium.common.matcher;

import org.junit.Assert;
import org.junit.Test;

public class AlwaysFalseMatcherTest {

    @Test
    public void testShouldNeverMatch() throws Exception {
        Assert.fail();
    }
}