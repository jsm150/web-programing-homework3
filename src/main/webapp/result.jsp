<%@ page import="com.wplab.homework3.result.DO.ResultResponse" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" session="false" %>
<%
	ResultResponse testResult = (ResultResponse)request.getAttribute("resultResponse");
	if (testResult == null) response.sendRedirect(request.getContextPath());
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
			<p>반갑습니다, <%= testResult.getName() %> 님!...</p>
		</div>
		<hr>
	</header>
	<article>
		<form action='reset' method="POST">
			<div>
				<h3>성격 테스트 분석 결과</h3>
			</div>
			<div>
				<p><b>1. 평가 점수 : </b><%= testResult.getScore() %></p>
				<p><b>2. 분석 결과 : </b></p>
				<div style="padding-left: 25px;">
					<%= testResult.getResult() %>
				</div>
			</div>
			<div class="buttons">
				<input type="submit" value="첫 화면으로 가기" />
				<input type="submit" name="delete" value="계정 삭제" />
			</div>
		</form>
	</article>
</body>
</html>
