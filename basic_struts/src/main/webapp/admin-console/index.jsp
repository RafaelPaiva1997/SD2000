<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>

    <title>Administrator Console Index</title>
    <style>
        .lista1 {
            border-collapse: collapse;
            width: 100%;
        }

        .row1 {
            text-align: center;
            padding: 8px;
            background-color: darkseagreen;
        }

        .row1:nth-child(even) {
            background-color: cadetblue;
        }

        .row1:nth-child(<%=session.getAttribute("index")%>) {
            background-color: deepskyblue;
        }

        .header_link:link, .header_link:visited {
            font-size: large;
            font-family: sans-serif;
            font-weight: bold;
            color: white;
            text-decoration: none;
        }

        .header_link:hover, .header_link:active {
            color: black;
        }

        .lista2 {
            border-collapse: collapse;
            width: 100%;
        }

        .rowleft {
            text-align: left;
            padding: 8px;
        }

        .rowright {
            text-align: right;
            padding: 8px;
        }

        .column {
            background-color: white;
        }

        .column:first-child {
            background-color: #fffdfd;
        }

        .column:nth-child(even) {
            background-color: #f2f2f2;
        }

        .link1 {
            color: black;
            text-decoration: none;
        }

        .link1:hover, .link1:active {
            color: deepskyblue;
            text-decoration: none;
        }

        .link2 {
            background-color: midnightblue;
            color: white;
            padding: 14px 25px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
        }

        .link2:hover, .link2:active {
            background-color: deepskyblue;
        }
    </style>
</head>
<body bgcolor="#fffdfd">
<h1 align="center">ADMINISTRATOR CONSOLE</h1>
<table class="lista1">
    <tr>
        <th class="row1"><a class="header_link" href="<s:url action="admin-console-faculdades"/>" target="_parent">FACULDADES</a></th>
        <th class="row1"><a class="header_link" href="<s:url action="admin-console-departamentos"/>" target="_parent">DEPARTAMENTOS</a></th>
        <th class="row1"><a class="header_link" href="<s:url action="admin-console-pessoas"/>" target="_parent">PESSOA</a></th>
        <th class="row1"><a class="header_link" href="<s:url action="admin-console-eleicoes"/>" target="_parent">ELEIÇÕES</a></th>
        <th class="row1"><a class="header_link" href="<s:url action="admin-console-listas"/>" target="_parent">LISTAS</a></th>
        <th class="row1"><a class="header_link" href="<s:url action="admin-console-mesas-voto"/>" target="_parent">MESAS DE VOTO</a></th>
        <th class="row1"><a class="header_link" href="<s:url action="logout"/>" target="_parent">LOGOUT</a></th>
    </tr>
</table>
</body>
</html>
