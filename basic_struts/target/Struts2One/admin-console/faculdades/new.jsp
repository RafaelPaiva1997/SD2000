<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Nova Faculdade</title>
    <style>
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
<body>
<h1>Nova Faculdade</h1>
<s:form action="faculdades-add">
    <table>
        <tr>
            <td><s:label value="Nome:"/></td>
            <td><s:textfield name="nome"/></td>
            <td><s:fielderror fieldName="nome"><s:property value="nomeError"/></s:fielderror></td>
        </tr>
        <tr>
            <td><s:submit method="voltar" value="Voltar" cssClass="link2"/></td>
            <td><s:submit method="add" value="Adicionar" cssClass="link2"/></td>
        </tr>
    </table>
</s:form>
</body>
</html>