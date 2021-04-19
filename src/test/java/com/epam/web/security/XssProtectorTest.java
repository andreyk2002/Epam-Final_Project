package com.epam.web.security;

import org.junit.Assert;
import org.testng.annotations.Test;

public class XssProtectorTest {

    private static final String SAFE_STRING = "abc";
    private static final String HACK_SCRIPT = "<script>you have been hacked!</script>";
    private static final String SCRIPT_TEXT = "&lt;script&gt;you have been hacked!&lt;/script&gt;";


    private final XssProtector protect = new XssProtector();

    @Test
    public void testReplaceMalformedShouldNotReplaceSafeString() {
        String actual = protect.replaceMalformed(SAFE_STRING);
        Assert.assertEquals(actual, SAFE_STRING);
    }

    @Test
    public void testReplaceShouldReplaceSimpleScript(){
        String actual = protect.replaceMalformed(HACK_SCRIPT);
        Assert.assertEquals(actual, SCRIPT_TEXT);
    }
}
