<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Gerir Pessoas da Lista <s:property value="nome"/></title>
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
<body>
<s:set var="idlista" value="%{id}"/>
<s:set var="tipolista" value="%{tipo}"/>
<h1>Gerir Pessoas da Lista <s:property value="nome"/></h1>
<br>
<div align="right">
    <a class="link2" href="<s:url action='admin-console-listas'/>">Voltar</a>
</div>
<br>
<h3>Pessoas Inscritas</h3>
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
                            <s:url action="listas-pessoas-manage" method="removePessoa">
                                <s:param name="id" value="%{idlista}"/>
                                <s:param name="id_pessoa" value="id"/>
                                <s:param name="tipo" value="%{tipolista}"/>
                                <s:param name="nome" value="%{nome}"/>
                                <s:param name="eleicao_id" value="%{eleicao_id}"/> 3
                            </s:url>" target="_parent">
                    Desinscrever
                </a>
            </td>
        </tr>
    </s:iterator>
</table>
<br><br>
<h3>Pessoas que pode Inscrever</h3>
<table class="lista2">
    <tr class="column">
        <td>ID</td>
        <td>Tipo</td>
        <td>Nome</td>
        <td>Username</td>
        <td>ID Departamento</td>
        <td>Telemóvel</td>
    </tr>
    <s:iterator value="nao_pessoas">
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
                            <s:url action="listas-pessoas-manage" method="addPessoa">
                                <s:param name="id" value="%{idlista}"/>
                                <s:param name="id_pessoa" value="id"/>
                                <s:param name="tipo" value="%{tipolista}"/>
                                <s:param name="nome" value="%{nome}"/>
                                <s:param name="eleicao_id" value="%{eleicao_id}"/>
                            </s:url>" target="_parent">
                    Inscrever
                </a>
            </td>
        </tr>
    </s:iterator>
</table>
</body>
</html>
