<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/default.css">
	<title>메인</title>
</head>
<style>
	div {
	 margin-top: 150px;
	}
</style>
<body>
	<div>
		Welcome, ${name}<br/>
		<img src = "img/lock.jpg"/>
		<br/><br/>
	    <a href="key/CreateKeyPair.jsp"><button class="bttn">나의 공개키, 개인키 만들기</button></a><br/>
		<a href="key/CreateSecretKey.jsp"><button class="bttn">나의 비밀키 만들기</button></a><br/>
		<a href="envelope/CreateEnvelope.jsp"><button class="bttn">전자봉투 만들기</button></a><br/>
		<a href="envelope/VerifyEnvelope.jsp"><button class="bttn">전자봉투 검증하기</button></a>
	</div>
</body>
</html>