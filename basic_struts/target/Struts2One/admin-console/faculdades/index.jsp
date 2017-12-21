<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Administrator Console Faculdades</title>
</head>
<body>
<%
    session.setAttribute("index", 1);
%>
<%@ include file="../index.jsp" %>
<div align="right">
    <br>
    <a class="link2" href="<s:url action="faculdades-new"/>" target="_parent">Nova</a>
    <br>
    <s:if test="hasActionErrors()">
        <h3><s:actionerror/></h3>
    </s:if>
    <br>
</div>
<table class="lista2">
    <tr class="column">
        <td>ID</td><td>Nome</td>
    </tr>
    <s:iterator value="faculdades">
        <tr class="column">
            <td class="rowleft">
                <s:property value="id"/>
            </td>
            <td class="rowleft">
                <s:property value="nome"/>
            </td>
            <td class="rowright">
                <a class="link1" href="
                            <s:url action="faculdades-edit">
                                <s:param name="id"><s:property value="id"/></s:param>
                                <s:param name="nome"><s:property value="nome"/></s:param>
                            </s:url>" target="_parent">
                    Editar
                </a>
                &nbsp;
                <a class="link1" href="
                            <s:url action="faculdades-remove">
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
