<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>회원 가입</title>
<link rel="stylesheet" href="resources/css/joinform.css" />
</head>

<body>
<h1>joinform</h1> <br />
<form action="join">
<fieldset>
<legend>MEMBER JOIN</legend>
<ol>
  <li>
    <label for="userid">아이디</label>
    <input id="userid" name="id" type="text" equired autofocus>
  </li>
  <li>
    <label for="pwd1">비밀번호</label>
    <input id="pwd1" name="pw" required>
  </li>
  <li>
    <label pwd="pwd2">비밀번호 확인</label>
    <input id="pwd2" name="pw2" type="password" required>
  </li>  
  <li>
    <label pwd="nickname">닉네임</label>
    <input id="nickname" name="nickname">
  </li>  
  
  <li>
    <label pwd="level">이메일</label>
    <input id="level" name="em" type="text" >
  </li>
</ol>
</fieldset>
<fieldset>
  <button type="submit"> <b>Member join </b></button> 
</fieldset>
</form>
</body>
</html>
