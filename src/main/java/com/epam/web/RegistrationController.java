package com.epam.web;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class RegistrationController extends HttpServlet {

    private static final String page = "WEB-INF/view/register.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(page).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
