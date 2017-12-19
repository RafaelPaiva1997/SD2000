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
    <title>Administrator Console Departamentos</title>
</head>
<body>
<%
    session.setAttribute("index", 2);
%>
<%@ include file="../index.jsp" %>
<div align="right">
    <br>
    <a class="link2" href="<s:url action="departamentos-new"/>" target="_parent">Nova</a>
    <br>
</div>
<table class="lista2">
    <tr class="column">
        <td>ID</td><td>Nome</td><td>ID Faculdade</td>
    </tr>
    <s:iterator value="departamentos">
        <tr class="column">
            <td class="rowleft">
                <s:property value="id"/>
            </td>
            <td class="rowleft">
                <s:property value="nome"/>
            </td>
            <td class="rowleft">
                <s:property value="faculdade_id"/>
            </td>
            <td class="rowright">
                <a class="link1" href="
                            <s:url action="departamentos-edit">
                                <s:param name="id"><s:property value="id"/></s:param>
                                <s:param name="faculdade_id"><s:property value="faculdade_id"/></s:param>
                                <s:param name="nome"><s:property value="nome"/></s:param>
                            </s:url>" target="_parent">
                    Editar
                </a>
                &nbsp;
                <a class="link1" href="
                            <s:url action="departamentos-remove">
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
