<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Gerir Eleições</title>
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
<s:set var="idmesavoto" value="%{id}"/>
<s:set var="departamentoidvoto" value="%{departamento_id}"/>
<h1>Gerir Eleições da Mesa de Voto do Departamento <s:property value="departamento_id"/></h1>
<br>
<div align="right">
    <a class="link2" href="<s:url action='admin-console-mesas-voto'/>">Voltar</a>
</div>
<br>
<h3>Eleições Permitidas</h3>
<br>
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
                            <s:url action="mesas-voto-eleicoes-manage" method="remove">
                                <s:param name="eleicao_id"><s:property value="id"/></s:param>
                                <s:param name="id" value="%{idmesavoto}"/>
                                <s:param name="departamento_id" value="%{departamentoidvoto}"/>
                            </s:url>" target="_parent">
                    Remover
                </a>
            </td>
        </tr>
    </s:iterator>
</table>
<br><br>
<h3>Eleições por Permitir</h3>
<br>
<table class="lista2">
    <tr class="column">
        <td>ID</td>
        <td>Tipo</td>
        <td>Título</td>
        <td>Data Início</td>
        <td>Data Fim</td>
        <td>ID Departamento</td>
    </tr>
    <s:iterator value="nao_eleicoes">
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
                            <s:url action="mesas-voto-eleicoes-manage" method="add">
                                <s:param name="eleicao_id"><s:property value="id"/></s:param>
                                <s:param name="id" value="%{idmesavoto}"/>
                                <s:param name="departamento_id" value="%{departamentoidvoto}"/>
                            </s:url>" target="_parent">
                    Adicionar
                </a>
            </td>
        </tr>
    </s:iterator>
</table>
</body>
</html>
