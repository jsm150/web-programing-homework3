package com.wplab.homework3.question.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.wplab.homework3.core.ServiceFactory;
import com.wplab.homework3.question.DO.IndexResponse;
import com.wplab.homework3.question.DO.QuestionResponse;
import com.wplab.homework3.question.service.IQuestionStartService;
import com.wplab.homework3.question.service.QuestionStartService;
import com.wplab.homework3.result.DO.ResultResponse;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.security.auth.login.LoginException;

/**
 * Servlet implementation class Test1Control
 */
@WebServlet("/question_start")
public class QuestionStartController extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// step #1. get request parameters
		request.setCharacterEncoding("UTF-8");

		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String password = request.getParameter("password");

		try {
			var service = ServiceFactory.createProxy(IQuestionStartService.class, QuestionStartService.class);
			var user = service.loginOrRegister(email, name, password);

			var session = request.getSession();
			session.setAttribute("userEmail", email);
			session.setAttribute("userName", name);
			session.setAttribute("parameterValues", new ArrayList<String>());
			
			// 결과가 있는지 확인해서 분기
			if (user.Result == null) {
				request.setAttribute("isFirst", true);
				request.setAttribute("name", name);
				request.getRequestDispatcher("/question").forward(request, response);
			}
			else {
				var resultResponse = new ResultResponse(name, user.Result.Value, user.Result.Content.Content);
				request.setAttribute("resultResponse", resultResponse);
				request.getRequestDispatcher("/result.jsp").forward(request, response);
			}


		} catch (LoginException e) {
			IndexResponse indexResponse = new IndexResponse(email, name, password, "비밀번호가 일치하지 않습니다!");
			request.setAttribute("indexResponse", indexResponse);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
	}

}
