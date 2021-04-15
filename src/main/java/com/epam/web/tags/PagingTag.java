package com.epam.web.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class PagingTag extends TagSupport {
    public static final String PATH = "/controller?commandName=showFilmsPage&pageNumber=";
    private int pagesCount;
    private static final String LINK_START  = "<a class=\"film-link\" href=";

    public void setPagesCount(int pagesCount) {
        this.pagesCount = pagesCount;
    }

    @Override
    public int doStartTag() throws JspException {
        String contextPath = pageContext.getServletContext().getContextPath();
        try {
            JspWriter out = pageContext.getOut();
            for (int i = 0; i <= pagesCount; i++) {
                String page = Integer.toString(i);
                out.write(LINK_START);
                out.write(contextPath);
                out.write(PATH);
                out.write(page);
                out.write(">");
                out.write(page);
                out.write("</a>");
            }
        } catch (IOException e) {
            throw new JspException(e.getMessage(), e);
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

}
