<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><s:property value="tipo"/> <s:property value="nome"/></title>
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

        .link2:hover, .link2:active {
            background-color: deepskyblue;
        }
    </style>
</head>
<body>
<h1 align="center"><s:property value="tipo"/> <s:property value="nome"/></h1>
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
        <td class="rowleft">Nome</td>
        <td class="rowright"><s:property value="nome"/></td>
    </tr>
    <tr class="column">
        <td class="rowleft">Username</td>
        <td class="rowright"><s:property value="username"/></td>
    </tr>
    <tr class="column">
        <td class="rowleft">Password</td>
        <td class="rowright"><s:property value="password"/></td>
    </tr>
    <tr class="column">
        <td class="rowleft">Telemovel</td>
        <td class="rowright"><s:property value="telemovel"/></td>
    </tr>
    <tr class="column">
        <td class="rowleft">Morada</td>
        <td class="rowright"><s:property value="morada"/></td>
    </tr>
    <tr class="column">
        <td class="rowleft">Codigo Postal</td>
        <td class="rowright"><s:property value="codigo_postal"/></td>
    </tr>
    <tr class="column">
        <td class="rowleft">Localidade</td>
        <td class="rowright"><s:property value="localidade"/></td>
    </tr>
    <tr class="column">
        <td class="rowleft">ID Departamento</td>
        <td class="rowright"><s:property value="departamento_id"/></td>
    </tr>
    <tr class="column">
        <td class="rowleft">Genero</td>
        <td class="rowright"><s:property value="genero"/></td>
    </tr>
    <tr class="column">
        <td class="rowleft">Numero CC</td>
        <td class="rowright"><s:property value="numero_cc"/></td>
    </tr>
    <tr class="column">
        <td class="rowleft">Validade CC</td>
        <td class="rowright"><s:date name="%{validade_cc_print}" format="dd/MM/yyyy"/></td>
    </tr>
    <tr class="column">
        <td class="rowleft">Data Nascimento</td>
        <td class="rowright"><s:date name="%{data_nascimento_print}" format="dd/MM/yyyy"/></td>
    </tr>
    <s:if test="tipo == 'Aluno'">
        <tr class="column">
            <td class="rowleft">Curso</td>
            <td class="rowright"><s:property value="curso"/></td>
        </tr>
        <tr class="column">
            <td class="rowleft">Numero Aluno</td>
            <td class="rowright"><s:property value="numero_aluno"/></td>
        </tr>
    </s:if>
    <s:if test="tipo == 'Docente'">
        <tr class="column">
            <td class="rowleft">Cargo</td>
            <td class="rowright"><s:property value="cargo"/></td>
        </tr>
    </s:if>
    <s:if test="tipo == 'Funcionario'">
        <tr class="column">
            <td class="rowleft">Funcao</td>
            <td class="rowright"><s:property value="funcao"/></td>
        </tr>
    </s:if>
    <tr class="column">
        <td class="rowleft">Admin</td>
        <td class="rowright">
            <s:if test="admin_print == 'true'">
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
    <a class="link2" href="<s:url action='admin-console-pessoas'/>">Voltar</a>
</div>
</body>
</html>
