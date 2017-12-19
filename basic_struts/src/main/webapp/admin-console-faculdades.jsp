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
    <title>Administrator Console Faculdades</title>
    <style>
        .lista {
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

        .column:nth-child(even) {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
<jsp:include page="admin-console-index.jsp"/>
<h2>Faculdades</h2>
<a href="<s:url action="faculdades-new"/>" target="_parent"><h3 align="end">Nova</h3></a>
<table class="lista">
    <tr class="column">
        <td class="row">ID</td><td class="row">Nome</td>
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
                <a href="
                            <s:url action="faculdades-edit">
                                <s:param name="id"><s:property value="id"/></s:param>
                                <s:param name="nome"><s:property value="nome"/></s:param>
                            </s:url>" target="_parent">
                    Editar
                </a>
                &nbsp;
                <a href="
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
