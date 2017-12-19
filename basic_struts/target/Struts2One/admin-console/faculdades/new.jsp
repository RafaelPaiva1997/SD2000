<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: rafaelpaiva
  Date: 18/12/2017
  Time: 19:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Nova Faculdade</title>
</head>
<body>
<h1>Nova Faculdade</h1>
<s:form action="faculdades-add">
    <s:textfield name="nome" label="Nome"/> <p><s:property value="nomeError"/></p>
    <s:submit/>
</s:form>
</body>
</html>