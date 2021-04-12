package com.epam.web.tags;

import com.epam.web.entity.Role;
import com.epam.web.entity.User;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class UserInfoTag extends TagSupport {

    private User user;
    private String locale;

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int doStartTag() throws JspException {
        String loc = getLocale();
        String bundleName = loc == null ? "local" : "local" + "_" + loc;
        ResourceBundle resource = ResourceBundle.getBundle(bundleName);
        String loginLabel = resource.getString("local.username");
        String roleLabel = resource.getString("local.role");
        String ratingLabel = resource.getString("local.rating");
        String login = user.getLogin();
        Role role = user.getRole();
        double rating = user.getRating();
        StringBuilder content = new StringBuilder();
        content.append("<h2 class=\"personal-title\">");
        content.append(loginLabel).append(" : ");
        content.append(login);
        content.append("</h2>");
        content.append("<div>");
        content.append("<h2>").append(ratingLabel).append(" : ");
        content.append(rating).append("</h2>");
        content.append("<h2>").append(roleLabel).append(" : ");
        content.append(role).append("</h2>");
        content.append("</div>");
        try {
            JspWriter out = pageContext.getOut();
            out.write(content.toString());
        } catch (IOException e) {
            throw new JspException(e.getMessage(), e);
        }
        return SKIP_BODY;
    }

    private String getLocale() {
        HttpSession session = pageContext.getSession();
        Object local = session.getAttribute("local");
        return (String)local;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
