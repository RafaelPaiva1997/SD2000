<%--
  Created by IntelliJ IDEA.
  User: rafaelpaiva
  Date: 19/12/2017
  Time: 08:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Voltar</title>
    <style>
        .back {
            background-color: midnightblue;
            color: white;
            padding: 14px 25px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
        }

        .back:hover, .back:active {
            background-color: deepskyblue;
        }
    </style>
</head>
<body>
<% session.setAttribute("index", 0); %>
<div align="center">
    <a class="back" href="<s:url action='back'/>">Voltar</a>
</div>
</body>
</html>
