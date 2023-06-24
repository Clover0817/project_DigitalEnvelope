<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="../css/default.css">
    <title>Create Secret Key</title>
</head>
<body>
<div>
    <form action="../secretKey" method="post">
        <h1>🔑비밀키 생성하기🔑</h1>
        <br/>
        <label for="secretFName">당신의 비밀키를 저장할 파일 이름을 입력하세요:</label>
        <input type="text" name="secretFName" id="secretFName">
        <br/>
        <br/>
        <input type="submit" value="생성 및 저장">
    </form>
</div>
</body>
</html>
