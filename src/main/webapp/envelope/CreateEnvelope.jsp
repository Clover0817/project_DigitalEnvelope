<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="digitalEnvelope.Create" %>
<%@ page import="digitalEnvelope.DigitEnvelope" %>
<%@ page import="java.io.*" %>
<%@ page import="java.security.*" %>
<%@ page import="java.util.Scanner" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../css/default.css">
    <title>Create Digital Envelope</title>
</head>
<style>
	form {
	 margin-top: -100px;
	}
</style>
<body>
<div>
    <form action="../createEnvelope" method="post">
    <h1>✉전자봉투 만들기✉</h1>
    <br/>
	  <table>
	  <tr>
	    <td><label for="letterFName">편지를 저장할 파일 이름:</label></td>
	    <td><input type="text" name="letterFName" id="letterFName" required></td>
	  </tr>
	  <tr>
	    <td><label for="content">편지 내용:</label></td>
	    <td><input type="text" name="content" id="content" required></td>
	  </tr>
	  <tr>
	    <td><label for="myPrivateFName">나의 개인키 파일 이름:</label></td>
	    <td><input type="text" name="myPrivateFName" id="myPrivateFName" required></td>
	  </tr>
	  <tr>
	    <td><label for="myPublicFName">나의 공개키 파일 이름:</label></td>
	    <td><input type="text" name="myPublicFName" id="myPublicFName" required></td>
	  </tr>
	  <tr>
	    <td><label for="mySecretFName">나의 비밀키 파일 이름:</label></td>
	    <td><input type="text" name="mySecretFName" id="mySecretFName" required></td>
	  </tr>
	  <tr>
	    <td><label for="yourPublicFName">친구의 공개키 파일 이름:</label></td>
	    <td><input type="text" name="yourPublicFName" id="yourPublicFName" required></td>
	  </tr>
	  <tr>
	    <td><label for="sigFName">전자서명을 저장할 파일 이름:</label></td>
	    <td><input type="text" name="sigFName" id="sigFName" required></td>
	  </tr>
	  <tr>
	    <td><label for="encryptedFName">암호화된 데이터를 저장할 파일 이름:</label></td>
	    <td><input type="text" name="encryptedFName" id="encryptedFName" required></td>
	  </tr>
	</table>
	<br/>
	<input type="submit" value="전자봉투 생성하기">
    </form>
</div>
</body>
</html>
