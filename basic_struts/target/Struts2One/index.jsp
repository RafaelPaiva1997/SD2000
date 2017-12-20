<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<%
    session.setAttribute("index", 0);
%>
<s:form action="login">
    <table>
        <tr>
            <td><s:label value="Username:"/></td>
            <td><s:textfield name="username" value="rafaelpaiva"/></td>
            <td><s:fielderror fieldName="username"><s:property value="usernameError"/></s:fielderror></td>
        </tr>
        <tr>
            <td><s:label>Password:</s:label></td>
            <td><s:password name="password"/></td>
            <td><s:fielderror fieldName="password"><s:property value="passwordError"/></s:fielderror></td>
        </tr>
        <tr>
            <td><s:submit/></td>
        </tr>
    </table>
</s:form>
</body>
</html>
