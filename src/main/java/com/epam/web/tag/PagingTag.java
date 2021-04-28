package com.epam.web.tag;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class PagingTag extends TagSupport {
    public static final String PATH = "/controller?commandName=showFilmsPage&pageNumber=";
    private static final String START = "<div class=\"pages\">";
    private static final String END = "</div>";
    private static final int MAX_PAGES = 5;
    public static final String LINK_END = "</a>";
    private static final String CURRENT_PAGE_START = "<a class=\"film-link\" style=\"color:green\" href=";
    public static final String NEXT = "Next";
    public static final String PREVIOUS = "Previous";

    private static final String LINK_START = "<a class=\"film-link\" href=";

    private int pagesCount;
    private int currentPage;

    public void setPagesCount(int pagesCount) {
        this.pagesCount = pagesCount;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    @Override
    public int doStartTag() throws JspException {
        ServletContext context = pageContext.getServletContext();
        String contextPath = context.getContextPath();
        try {
            JspWriter out = pageContext.getOut();
            out.write(START);
            int remainsLinks = MAX_PAGES;
            for (int i = currentPage - 2; i < currentPage; i++) {
                if (i > 0) {
                    String page = Integer.toString(i);
                    writePageLink(contextPath, out, i, page);
                    remainsLinks--;
                }
            }
            for (int i = currentPage; i <= currentPage + remainsLinks && i <= pagesCount; i++) {
                String page = Integer.toString(i);
                writePageLink(contextPath, out, i, page);
            }
            if (currentPage > 0) {
                writePageLink(contextPath, out, currentPage - 1, PREVIOUS);
            }
            if (currentPage < pagesCount) {
                writePageLink(contextPath, out, currentPage + 1, NEXT);
            }
            out.write(LINK_START);

            out.write(LINK_END);
            out.write(END);
        } catch (IOException e) {
            throw new JspException(e.getMessage(), e);
        }
        return SKIP_BODY;
    }


    private void writePageLink(String contextPath, JspWriter out, Integer pageNumber, String message) throws IOException {
        String page = pageNumber.toString();
        if (pageNumber == currentPage) {
            out.write(CURRENT_PAGE_START);
        } else {
            out.write(LINK_START);
        }
        out.write(contextPath);
        out.write(PATH);
        out.write(page);
        out.write(">");
        out.write(message);
        out.write(LINK_END);
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

}
