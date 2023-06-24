<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="../css/default.css">
    <title>Create Key Pair</title>
</head>
<body>
<div>
    
    <form action="../keyPair" method="post">
     <h1>🔑공개키와 개인키 생성하기🔑</h1>
     <br/>
        <label for="publicFileName">당신의 공개키를 저장할 파일이름을 입력하세요:</label>
        <input type="text" name="publicFileName" id="publicFileName">
        <br/>
        <label for="privateFileName">당신의 개인키를 저장할 파일이름을 입력하세요:</label>
        <input type="text" name="privateFileName" id="privateFileName">
        <br/>
        <br/>
        <input type="submit" value="생성 및 저장">
    </form>
</div>
</body>
</html>
