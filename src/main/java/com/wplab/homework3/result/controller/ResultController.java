package com.wplab.homework3.result.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.wplab.homework3.core.ServiceFactory;
import com.wplab.homework3.result.service.IResultService;
import com.wplab.homework3.result.service.ResultService;
import jakarta.servlet.ServletException;
import jakarta.servlet.UnavailableException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Test1Control
 */
@WebServlet("/result")
public class ResultController extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// step #1. get request parameters
		request.setCharacterEncoding("UTF-8");

		try {
			var session = request.getSession(false);
			if (session == null) throw new UnavailableException("");

			var service = ServiceFactory.createProxy(IResultService.class, ResultService.class);
			var resultResponse = service.evaluate((String)session.getAttribute("userEmail"),
					(String)session.getAttribute("userName"),
					(ArrayList<String>)session.getAttribute("parameterValues"));
			request.setAttribute("resultResponse", resultResponse);
			request.getRequestDispatcher("/result.jsp").forward(request, response);
		}
		catch (UnavailableException e) {
			response.sendRedirect(request.getContextPath());
		}
	}

}
