<%--
  Created by IntelliJ IDEA.
  User: Johny
  Date: 20/12/2017
  Time: 23:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Administrator Console Listas</title>
</head>
<body>
<%
    session.setAttribute("index", 5);
%>
<%@ include file="../index.jsp" %>
<div align="right">
    <br>
    <a class="link2" href="<s:url action="listas-new">
                            <s:param name="tipo">Alunos</s:param>
                            </s:url>" target="_parent">Nova Alunos</a>
    &nbsp;
    <a class="link2" href="<s:url action="listas-new">
                            <s:param name="tipo">Docentes</s:param>
                            </s:url>" target="_parent">Nova Docentes</a>
    &nbsp;
    <a class="link2" href="<s:url action="listas-new">
                            <s:param name="tipo">Funcionarios</s:param>
                            </s:url>" target="_parent">Nova Funcionários</a>
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
        <td>ID Eleição</td>
    </tr>
    <s:iterator value="listas">
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
                <s:property value="eleicao_id"/>
            </td>
            <td class="rowright">
                <a class="link1" href="
                            <s:url action="listas-edit">
                                <s:param name="id"><s:property value="id"/></s:param>
                                <s:param name="tipo"><s:property value="tipo"/></s:param>
                                <s:param name="nome"><s:property value="nome"/></s:param>
                                <s:param name="eleicao_id"><s:property value="eleicao_id"/></s:param>
                            </s:url>" target="_parent">
                    Editar
                </a>
                &nbsp;
                <a class="link1" href="
                            <s:url action="listas-remove">
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
