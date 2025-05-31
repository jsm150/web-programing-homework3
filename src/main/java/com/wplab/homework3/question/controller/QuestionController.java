package com.wplab.homework3.question.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import com.wplab.homework3.core.ServiceFactory;
import com.wplab.homework3.question.DO.QuestionResponse;
import com.wplab.homework3.question.service.IQuestionService;
import com.wplab.homework3.question.service.QuestionService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.UnavailableException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class Test1Control
 */
@WebServlet("/question")
public class QuestionController extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// step #1. get request parameters
		request.setCharacterEncoding("UTF-8");
		int page = request.getAttribute("isFirst") != null
				? 1 : Integer.parseInt(request.getParameter("page"));

		var qnames = request.getParameterValues("parameterName");

        try {
			var session = request.getSession(false);
			if (session == null) throw new UnavailableException("");
			var parameterValues = (ArrayList<String>)session.getAttribute("parameterValues");

			if (qnames != null) {
				for (var qname : qnames) {
					parameterValues.add(request.getParameter(qname));
				}
			}

			if (page <= 5) {
				var service = ServiceFactory.createProxy(IQuestionService.class, QuestionService.class);
				var questionResponse = service.getContent(page, (String)session.getAttribute("userName"));
				request.setAttribute("questionResponse", questionResponse);
				request.getRequestDispatcher("/question.jsp").forward(request, response);
			}
			else {
				request.getRequestDispatcher("/result").forward(request, response);
			}
        }
		catch (UnavailableException e) {
			response.sendRedirect(request.getContextPath());
		}
		catch (SQLException e) {
            throw new RuntimeException(e);
        }
	}

}
