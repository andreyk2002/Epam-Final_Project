package com.epam.web.security;

import java.util.regex.Pattern;

public class XssProtect {

    public String replaceMalformed(String string) {
        String s3 = string.replaceAll("<", "&lt;");
        return s3.replaceAll(">", "&gt;");
    }
}
