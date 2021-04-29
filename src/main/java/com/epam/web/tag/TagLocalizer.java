package com.epam.web.tag;

import javax.servlet.http.HttpSession;
import java.util.ResourceBundle;

class TagLocalizer {

    private static final String DEFAULT_LOCAL = "local";
    private static final String LOCAL = "local_";

    private final ResourceBundle resourceBundle;

    public TagLocalizer(HttpSession session) {
        String loc = (String) session.getAttribute("local");
        String bundleName = loc == null ? DEFAULT_LOCAL : LOCAL + loc;
        this.resourceBundle = ResourceBundle.getBundle(bundleName);
    }


    public String localize(String propertyName) {
        return resourceBundle.getString(propertyName);
    }

}
