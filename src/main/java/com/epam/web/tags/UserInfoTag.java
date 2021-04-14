package com.epam.web.tags;

import com.epam.web.entity.Role;
import com.epam.web.entity.User;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.ResourceBundle;

public class UserInfoTag extends TagSupport {

    private User user;


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
        content.append(makeTitle(loginLabel, login));
        content.append(makeInfo(ratingLabel, Double.toString(rating)));
        content.append(makeInfo(roleLabel, role.toString()));
        try {
            JspWriter out = pageContext.getOut();
            out.write(content.toString());
        } catch (IOException e) {
            throw new JspException(e.getMessage(), e);
        }
        return SKIP_BODY;
    }

    private StringBuilder makeInfo(String label, String content) {
        StringBuilder info = new StringBuilder();
        info.append("<h3 class=\"personal-info\">");
        info.append(label);
        info.append(" : ");
        info.append(content);
        info.append("</h3>");
        return info;
    }

    private StringBuilder makeTitle(String loginLabel, String login) {
        StringBuilder title = new StringBuilder();
        title.append("<h2 class=\"personal-title\">");
        title.append(loginLabel);
        title.append(" : ");
        title.append(login);
        title.append(login);
        title.append("</h2>");
        return title;
    }

    private String getLocale() {
        HttpSession session = pageContext.getSession();
        Object local = session.getAttribute("local");
        return (String) local;
    }

    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }
}
