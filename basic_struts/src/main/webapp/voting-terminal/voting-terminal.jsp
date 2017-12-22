<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Terminal Voto</title>
    <style>
        .lista2 {
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

        .column {
            background-color: white;
        }

        .column:first-child {
            background-color: #fffdfd;
        }

        .column:nth-child(even) {
            background-color: #f2f2f2;
        }

        .link1 {
            color: black;
            text-decoration: none;
        }

        .link1:hover, .link1:active {
            color: deepskyblue;
            text-decoration: none;
        }

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
<body bgcolor="#fffdfd">
<%
    session.setAttribute("index", 7);
%>
<s:if test="%{user.admin}">
    <%@ include file="../admin-console/index.jsp" %>
</s:if>
<s:else>
<h1 align="center">TERMINAL VOTO</h1>
</s:else>
<br>
<br>
<table class="lista2">
    <tr>
        <td class="rowleft"><h2>Bem Vindo <s:property value="user.nome"/></h2></td>
        <td class="rowright">
            <s:if test="%{!user.admin}"><a class="link2" href="<s:url action='logout'/>">Logout</a></s:if>
        </td>
    </tr>
    <tr>
        <td class="rowleft">
            <s:if test="hasActionErrors()">
                <h3><s:actionerror/></h3>
            </s:if>
        </td>
    </tr>
</table>
<br>
<h3>Eleições em que pode Votar</h3>
<table class="lista2">
    <tr class="column">
        <td>ID</td>
        <td>Tipo</td>
        <td>Título</td>
        <td>Data Início</td>
        <td>Data Fim</td>
        <td>ID Departamento</td>
    </tr>
    <s:iterator value="eleicoes">
        <tr class="column">
            <td class="rowleft">
                <s:property value="id"/>
            </td>
            <td class="rowleft">
                <s:property value="tipo"/>
            </td>
            <td class="rowleft">
                <s:property value="titulo"/>
            </td>
            <td class="rowleft">
                <s:date name="%{data_inicio}" format="HH:mm:ss dd/MM/yyyy"/>
            </td>
            <td class="rowleft">
                <s:date name="%{data_fim}" format="HH:mm:ss dd/MM/yyyy"/>
            </td>
            <td class="rowleft">
                <s:if test="tipo == 'Nucleo Estudantes'">
                    <s:property value="departamento_id"/>
                </s:if>
            </td>
            <td class="rowright">
                <a class="link1" href="
                            <s:url action="votar">
                                <s:param name="eleicao_id"><s:property value="id"/></s:param>
                                <s:param name="pessoa_id"><s:property value="user.id"/></s:param>
                                <s:param name="pessoa_tipo"><s:property value="user.tipo"/></s:param>
                            </s:url>" target="_parent">
                    Votar
                </a>
            </td>
        </tr>
    </s:iterator>
</table>
<br><br>
<h3>Eleições em que já votou</h3>
<table class="lista2">
    <tr class="column">
        <td>ID</td>
        <td>Tipo</td>
        <td>Título</td>
        <td>Data Início</td>
        <td>Data Fim</td>
        <td>ID Departamento</td>
    </tr>
    <s:iterator value="eleicoes_passadas">
        <tr class="column">
            <td class="rowleft">
                <s:property value="id"/>
            </td>
            <td class="rowleft">
                <s:property value="tipo"/>
            </td>
            <td class="rowleft">
                <s:property value="titulo"/>
            </td>
            <td class="rowleft">
                <s:date name="%{data_inicio}" format="HH:mm:ss dd/MM/yyyy"/>
            </td>
            <td class="rowleft">
                <s:date name="%{data_fim}" format="HH:mm:ss dd/MM/yyyy"/>
            </td>
            <td class="rowleft">
                <s:if test="tipo == 'Nucleo Estudantes'">
                    <s:property value="departamento_id"/>
                </s:if>
            </td>
        </tr>
    </s:iterator>
</table>
</body>
</html>
