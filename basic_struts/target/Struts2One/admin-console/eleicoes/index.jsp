<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Administrator Console Eleicoes</title>
</head>
<body>
<%
    session.setAttribute("index", 4);
%>
<%@ include file="../index.jsp" %>
<div align="right">
    <br>
    <a class="link2" href="<s:url action="eleicoes-new">
                            <s:param name="tipo">Conselho Geral</s:param>
                            </s:url>" target="_parent">Nova Conselho Geral</a>
    &nbsp;
    <a class="link2" href="<s:url action="eleicoes-new">
                            <s:param name="tipo">Nucleo Estudantes</s:param>
                            </s:url>" target="_parent">Nova Núcleo Estudantes</a>
    <br>
    <s:if test="hasActionErrors()">
        <h3><s:actionerror/></h3>
    </s:if>
    <br>
</div>
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
                            <s:url action="eleicoes-details">
                                <s:param name="id" value="id"/>
                                <s:param name="tipo" value="tipo"/>
                            </s:url>" target="_parent">
                    Detalhes
                </a>
                &nbsp;
                <a class="link1" href="
                            <s:url action="eleicoes-edit">
                                <s:param name="id"><s:property value="id"/></s:param>
                                <s:param name="tipo"><s:property value="tipo"/></s:param>
                            </s:url>" target="_parent">
                    Editar
                </a>
                &nbsp;
                <a class="link1" href="
                            <s:url action="eleicoes-remove">
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
