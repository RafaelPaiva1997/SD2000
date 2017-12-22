<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Votar</title>
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

        .back {
            background-color: midnightblue;
            color: white;
            padding: 14px 25px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
        }

        .back:hover, .back:active {
            background-color: deepskyblue;
        }
    </style>
</head>
<body>
<h1>Listas Disponíveis</h1>
<br>
<div align="right">
    <a class="back" href="<s:url action='voting-terminal'/>">Voltar</a>
</div>
<br>
<br>
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
                            <s:url action="votar-submit">
                                <s:param name="lista_id" value="id"/>
                                <s:param name="pessoa_id" value="%{pessoa_id}"/>
                                <s:param name="eleicao_id" value="%{eleicao_id}"/>
                                <s:param name="tipo">Normal</s:param>
                                <s:param name="data">
                                    <%SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");%>
                                    <%=f.format(new Date())%>
                                </s:param>
                            </s:url>" target="_parent">
                    Votar
                </a>
            </td>
        </tr>
    </s:iterator>
    <tr class="column">
        <td class="rowleft">
            Voto em Branco
        </td>
        <td class="rowleft">
        </td>
        <td class="rowleft">
        </td>
        <td class="rowleft">
        </td>
        <td class="rowright">
            <a class="link1" href="
                            <s:url action="votar-submit">
                                <s:param name="pessoa_id" value="%{pessoa_id}"/>
                                <s:param name="eleicao_id" value="%{eleicao_id}"/>
                                <s:param name="tipo">Branco</s:param>
                                <s:param name="data">
                                    <%SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");%>
                                    <%=f.format(new Date())%>
                                </s:param>
                            </s:url>" target="_parent">
                Votar
            </a>
        </td>
    </tr>
    <tr class="column">
        <td class="rowleft">
            Voto Nulo
        </td>
        <td class="rowleft">
        </td>
        <td class="rowleft">
        </td>
        <td class="rowleft">
        </td>
        <td class="rowright">
            <a class="link1" href="
                            <s:url action="votar-submit">
                                <s:param name="pessoa_id" value="%{pessoa_id}"/>
                                <s:param name="eleicao_id" value="%{eleicao_id}"/>
                                <s:param name="tipo">Nulo</s:param>
                                <s:param name="data">
                                    <%SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");%>
                                    <%=f.format(new Date())%>
                                </s:param>
                            </s:url>" target="_parent">
                Votar
            </a>
        </td>
    </tr>
</table>
</body>
</html>
