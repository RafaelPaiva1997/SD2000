<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Editar Listas <s:property value="tipo"/></title>
</head>
<body>
<h1>Editar Lista <s:property value="tipo"/></h1>
<s:form action="listas-update">
    <s:hidden name="id" value="%{id}"/>
    <s:hidden name="tipo" value="%{tipo}"/>
    <table>
        <tr>
            <td><s:label value="Nome:"/></td>
            <td><s:textfield name="nome" value="%{nome}"/></td>
            <td><s:fielderror fieldName="nome"><s:property value="nomeError"/></s:fielderror></td>
        </tr>
        <tr>
            <td><s:label value="Eleicao:"/></td>
            <td>
                <s:select headerKey="-1" headerValue="-- Seleccionar --"
                          list="eleicoes"
                          name="eleicao"
                          value="eleicaoDefault"/>
            </td>
            <td><s:fielderror fieldName="eleicao"><s:property value="eleicaoError"/></s:fielderror></td>
        </tr>
        <tr>
            <td><s:submit method="voltar" value="Voltar"/></td>
            <td><s:submit method="update"/></td>
        </tr>
    </table>
</s:form>
</body>
</html>