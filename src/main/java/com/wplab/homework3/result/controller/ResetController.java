package com.wplab.homework3.result.controller;

import com.wplab.homework3.core.ServiceFactory;
import com.wplab.homework3.result.service.IResetService;
import com.wplab.homework3.result.service.ResetService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/reset")
public class ResetController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // step #1. get request parameters
        request.setCharacterEncoding("UTF-8");

        var session = request.getSession(false);
        if (session != null) {
            if (request.getParameter("delete") != null) {
                var service = ServiceFactory.createProxy(IResetService.class, ResetService.class);
                service.remove((String)session.getAttribute("userEmail"));
            }

            session.invalidate();
        }

        response.sendRedirect(request.getContextPath());
    }
}
