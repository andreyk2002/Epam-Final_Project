package com.epam.web.security;

public class XssProtector {

    public String replaceMalformed(String string) {
        String beginTagReplaced = string.replaceAll("<", "&lt;");
        return beginTagReplaced.replaceAll(">", "&gt;");
    }
}
