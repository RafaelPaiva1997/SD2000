<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Administrator Console Pessoas</title>
</head>
<body>
<%
    session.setAttribute("index", 3);
%>
<%@ include file="../index.jsp" %>
<div align="right">
    <br>
    <a class="link2" href="<s:url action="pessoas-new">
                            <s:param name="tipo">Aluno</s:param>
                            </s:url>" target="_parent">Novo Aluno</a>
    &nbsp;
    <a class="link2" href="<s:url action="pessoas-new">
                            <s:param name="tipo">Docente</s:param>
                            </s:url>" target="_parent">Novo Docente</a>
    &nbsp;
    <a class="link2" href="<s:url action="pessoas-new">
                            <s:param name="tipo">Funcionario</s:param>
                            </s:url>" target="_parent">Novo Funcionário</a>
    <br>
    <s:if test="hasActionErrors()">
        <h3><s:actionerror/></h3>
    </s:if>
    <br>
</div>
<table class="lista2">
    <tr class="column">
        <td>ID</td>
        <td>Tipo</td>
        <td>Nome</td>
        <td>Username</td>
        <td>ID Departamento</td>
        <td>Telemóvel</td>
    </tr>
    <s:iterator value="pessoas">
        <tr class="column">
            <td class="rowleft">
                <s:property value="id"/>
            </td>
            <td class="rowleft">
                <s:property value="tipo"/>
            </td>
            <td class="rowleft">
                <s:property value="nome"/>
            </td>
            <td class="rowleft">
                <s:property value="username"/>
            </td>
            <td class="rowleft">
                <s:property value="departamento_id"/>
            </td>
            <td class="rowleft">
                <s:property value="telemovel"/>
            </td>
            <td class="rowright">
                <a class="link1" href="
                            <s:url action="pessoas-details">
                                <s:param name="id"><s:property value="id"/></s:param>
                                <s:param name="tipo"><s:property value="tipo"/></s:param>
                            </s:url>" target="_parent">
                    Detalhes
                </a>
                &nbsp;
                <a class="link1" href="
                            <s:url action="pessoas-edit">
                                <s:param name="id"><s:property value="id"/></s:param>
                                <s:param name="tipo"><s:property value="tipo"/></s:param>
                            </s:url>" target="_parent">
                    Editar
                </a>
                &nbsp;
                <a class="link1" href="
                            <s:url action="pessoas-remove">
                                <s:param name="id"><s:property value="id"/></s:param>
                            </s:url>" target="_parent">
                    Remover
                </a>
            </td>
        </tr>
    </s:iterator>
</table>
</body>
</html>