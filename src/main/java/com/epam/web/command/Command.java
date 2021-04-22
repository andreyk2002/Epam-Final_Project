package com.epam.web.command;



import com.epam.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Executes an operation which is dynamically chosen dy CommandFactory class
 *
 *
 */

public interface Command {

    /**
     * Executes a specific command
     * @param request servlet request from controller
     * @param response servlet response from controller
     * @return CommandResult class which contains the type of user redirect to next
     * resource (redirect or forward) and the name of next resource
     * @throws ServiceException
     */
    CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException;

}
