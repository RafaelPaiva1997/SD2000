<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Mesas Voto</title>
</head>
<body>
<%
    session.setAttribute("index", 6);
%>
<%@ include file="../index.jsp" %>
<br>
<br>
<table class="lista2">
    <tr class="column">
        <td>ID</td>
        <td>ID Departamento</td>
        <td>Ligada</td>
    </tr>
    <s:iterator value="mesas_voto">
        <tr class="column">
            <td class="rowleft">
                <s:property value="id"/>
            </td>
            <td class="rowleft">
                <s:property value="departamento_id"/>
            </td>
            <td class="rowleft">
                <s:if test="%{working}">
                    Sim
                </s:if>
                <s:else>
                    Não
                </s:else>
            </td>
            <td class="rowright">
                <a class="link1" href="
                            <s:url action="mesas-voto-eleicoes">
                                <s:param name="id"><s:property value="id"/></s:param>
                                <s:param name="departamento_id"><s:property value="departamento_id"/></s:param>
                            </s:url>" target="_parent">
                    Eleições
                </a>
            </td>
        </tr>
    </s:iterator>
</table>
</body>
</html>
