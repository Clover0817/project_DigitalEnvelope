<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="../css/default.css">
    <meta charset="UTF-8">
    <title>Envelope Verification</title>
</head>
<body>
<div>
    <form action="../verifyEnvelope" method="post">
        <h1>✉전자봉투 검증하기✉</h1>
        <br/>
        <table>
		  <tr>
		    <td><label for="envelopeFName">전자봉투 파일 이름:</label></td>
		    <td><input type="text" id="envelopeFName" name="envelopeFName"></td>
		  </tr>
		  <tr>
		    <td><label for="recPrivateFName">나의 개인키 파일:</label></td>
		    <td><input type="text" id="privateFName" name="privateFName"></td>
		  </tr>
		</table>
		<br/>     
        <input type="submit" value="검증하기">
    </form>
</div>
</body>
</html>
