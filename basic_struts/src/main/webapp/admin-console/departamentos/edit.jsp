<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Editar Departamento</title>
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
<h1>Editar Departamento</h1>
<s:form action="departamentos-update">
    <s:hidden name="id" value="%{id}"/>
    <table>
        <tr>
            <td><s:label value="Nome:"/></td>
            <td><s:textfield name="nome" value="%{nome}"/></td>
            <td><s:fielderror fieldName="nome"><s:property value="nomeError"/></s:fielderror></td>
        </tr>
        <tr>
            <td><s:label value="Faculdade:"/></td>
            <td>
                <s:select headerKey="-1" headerValue="-- Seleccionar --"
                          list="faculdades"
                          name="faculdade"
                          value="faculdadeDefault"/>
            </td>
            <td><s:fielderror fieldName="faculdade"><s:property value="faculdadeError"/></s:fielderror></td>
        </tr>
        <tr>
            <td><s:submit method="voltar" value="Voltar" cssClass="link2"/></td>
            <td><s:submit method="update" value="Actualizar" cssClass="link2"/></td>
        </tr>
    </table>
</s:form>
</body>
</html>
