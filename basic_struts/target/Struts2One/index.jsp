<%--
  Created by IntelliJ IDEA.
  User: rafaelpaiva
  Date: 18/12/2017
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<s:form action="login">
    <s:textfield name="username" label="Username"/> <p><s:property value="usernameError"/></p>
    <s:textfield name="password" label="Password"/> <p><s:property value="passwordError"/></p>
    <s:submit/>
</s:form>
</body>
</html>
