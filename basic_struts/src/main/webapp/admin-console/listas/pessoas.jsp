<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: rafaelpaiva
  Date: 21/12/2017
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Gerir Pessoas da Lista <s:property value="nome"/></title>
</head>
<body>
<h1>Gerir Pessoas da Lista <s:property value="nome"/></h1>
<div>
    <br>
    <a class="link2" href="<s:url action="listas-new">
                            <s:param name="tipo">Funcionarios</s:param>
                            </s:url>" target="_parent">Adicionar Pessoas</a>
    <br>
</div>
</body>
</html>
