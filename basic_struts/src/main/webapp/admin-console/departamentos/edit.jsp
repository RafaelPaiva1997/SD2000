<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Editar Departamento</title>
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
                <s:select headerKey="-1" headerValue="-- Selecionar --"
                          list="faculdades"
                          name="faculdade"
                          value="faculdadeDefault"/>
            </td>
            <td><s:fielderror fieldName="faculdade"><s:property value="faculdadeError"/></s:fielderror></td>
        </tr>
        <tr>
            <td><s:submit method="voltar" value="Voltar"/></td>
            <td><s:submit method="update"/></td>
        </tr>
    </table>
</s:form>
</body>
</html>
