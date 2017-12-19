<%--
  Created by IntelliJ IDEA.
  User: rafaelpaiva
  Date: 18/12/2017
  Time: 16:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Administrator Console Index</title>
</head>
<body>
<h1>Admin Console</h1>
<table style="width: 100%">
    <tr>
        <th><s:a action="admin-console-faculdades">Faculdades</s:a></th>
        <th><s:a action="admin-console-departamentos">Departamentos</s:a></th>
        <th><s:a action="logout">Logout</s:a></th>
    </tr>
</table>
</body>
</html>
