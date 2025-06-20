<%@ page import="com.wplab.homework3.question.DO.QuestionResponse" %>
<%@ page import="com.wplab.homework3.question.DO.IndexResponse" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" session="false" %>
<%
	IndexResponse resp = (IndexResponse)request.getAttribute("indexResponse");

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
	</style>
</head>
<body>
	<header>
		<h1>재미있는 2분 성격 테스트</h1>
	</header>
	<article>
		<div style="height: 150px;">
			<p>"2분 성격 테스트"는 10개의 문항을 살펴본 뒤 점수를 매기고 결과에 따른 자신의 성격을 점검하는 테스트입니다.</p>
			<p>하루 중 기분이 좋을 때, 걸을 때 보폭, 앉을 때 자세, 좋아하는 색 등 일상 속 자신의 모습을 생각해보고 
				자신이 현명한 사람인지, 깐깐한지, 또는 소심한지 알아볼 수 있습니다.</p>		
		</div>
		<div style="text-align: center;">
			<form action="question_start" method="POST">
				<p>이메일 : <input type='email' name='email' required value="<%
					if (resp != null) { %><%=resp.getEmail()%><% } %>" /></p>
				<p>이름 : <input type='text' name='name' required value="<%
					if (resp != null) { %><%=resp.getName()%><% } %>" /></p>
				<p>비밀번호 : <input type='password' name='password' required value="<%
					if (resp != null) { %><%=resp.getPassword()%><% } %>" /></p>
				<input type="submit" value="시작하기 >>" />
				<%
					if (resp != null) {
				%>
						<p><%=resp.getNotice()%></p>
				<%
					}
				%>
			</form>
		</div>
	</article>
</body>
</html>