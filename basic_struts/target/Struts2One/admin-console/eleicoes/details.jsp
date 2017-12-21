<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Eleição <s:property value="titulo"/></title>
    <style>
        .lista2 {
            border-collapse: collapse;
            width: 50%;
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

        .link2 {
            background-color: midnightblue;
            color: white;
            padding: 14px 25px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
        }

        .link2:hover, .back:active {
            background-color: deepskyblue;
        }
    </style>
</head>
<body>
<h1 align="center">Eleição <s:property value="titulo"/></h1>
<table align="center" class="lista2">
    <tr class="column">
        <td class="rowleft">ID</td>
        <td class="rowright"><s:property value="id"/></td>
    </tr>
    <tr class="column">
        <td class="rowleft">Tipo</td>
        <td class="rowright"><s:property value="tipo"/></td>
    </tr>
    <tr class="column">
        <td class="rowleft">Título</td>
        <td class="rowright"><s:property value="titulo"/></td>
    </tr>
    <tr class="column">
        <td class="rowleft">Descrição</td>
        <td class="rowright"><s:property value="descricao"/></td>
    </tr>
    <tr class="column">
        <td class="rowleft">Data Início</td>
        <td class="rowright"><s:date name="%{data_inicio_print}" format="HH:mm:ss dd/MM/yyyy"/></td>
    </tr>
    <tr class="column">
        <td class="rowleft">Data Fim</td>
        <td class="rowright"><s:date name="%{data_fim_print}" format="HH:mm:ss dd/MM/yyyy"/></td>
    </tr>
    <s:if test="tipo == 'Nucleo Estudantes'">
        <tr class="column">
            <td class="rowleft">ID Departamento</td>
            <td class="rowright"><s:property value="departamento_id"/></td>
        </tr>
    </s:if>
    <tr class="column">
        <td class="rowleft">Terminou</td>
        <td class="rowright">
            <s:if test="%{finished} == 'true'">
                Sim
            </s:if>
            <s:else>
                Não
            </s:else>
        </td>
    </tr>
</table>
<br>
<div align="center">
    <a class="link2" href="<s:url action='admin-console-eleicoes'/>">Voltar</a>
</div>
</body>
</html>
