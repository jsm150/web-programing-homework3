<%@ page import="com.wplab.homework3.question.DO.QuestionResponse" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" session="false"%>
<%
	QuestionResponse resp = (QuestionResponse)request.getAttribute("questionResponse");
	if (resp == null) response.sendRedirect(request.getContextPath());
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>재미있는 2분 성격 테스트</title>
	<style>
		body {
			width: 600px;
			margin-top: 50px;
			margin-left: 50px;
		}
		header {
			height: 100px;
		}
		.question {
			padding-top: 25px;
		}
		.buttons {
			padding-top: 50px; 
			text-align: right;
		}
	</style>
</head>
<body>
	<header>
		<h1>재미있는 2분 성격 테스트</h1>
		<div style="text-align: right;">
			<p>반갑습니다, <%= resp.getName() %> 님!...</p>
		</div>
		<hr>
	</header>
	<article>
		<form action="question" method="POST">
		<%
		for (var question : resp.getQuestions()) {
		%>
			<div class="question">
			<p><%=question.Number%>. <%=question.Title%></p>
			<div>
			<%
			for (var content : question.Contents) {
			%>
				<input type="radio" name="q<%=question.Number%>" value="<%=content.Value%>" required> <%=(char)(64 + content.ItemNumber)%>. <%=content.Content%> <br>
			<%
			}
			%>
			</div>
			<input type="hidden" name="parameterName" value="q<%=question.Number%>">
		<%
		}
		%>
			<div class="buttons">
				<input type="button" value="<< 이전" onclick="window.history.go(-1);" />
				<input type="submit" value="다음 >>" />
			</div>

			<input type="hidden" name="page" value="<%=resp.getNextPage()%>">
		</form>
	</article>
</body>
</html>