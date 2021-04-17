package com.epam.web.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XssProtect {

    private static final String xssString = "<>";
    public static final Pattern xssPattern = Pattern.compile(xssString);

    public String  replaceMalformed(String string){
        String s3 = string.replaceAll("<", "&lt;");
        return s3.replaceAll(">", "&gt;");
    }
}
