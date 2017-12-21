<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Nova Eleição <s:property value="tipo"/></title>
</head>
<body>
<h1>Nova Eleição <s:property value="tipo"/></h1>
<s:form action="eleicoes-add">
    <s:hidden name="tipo" value="%{tipo}"/>
    <table>
        <tr>
            <td><s:label value="Título:"/></td>
            <td><s:textfield name="titulo" size="30"/></td>
            <td><s:fielderror fieldName="titulo"><s:property value="tituloError"/></s:fielderror></td>
        </tr>
        <tr>
            <td><s:label value="Descrição:"/></td>
            <td><s:textarea name="descricao" cols="28" rows="3"/></td>
            <td><s:fielderror fieldName="descricao"><s:property value="descricaoError"/></s:fielderror></td>
        </tr>
        <td><s:label value="Data Início:"/></td>
        <td>
            <s:textfield name="data_inicio_hora" size="2" /> :
            <s:textfield name="data_inicio_minuto" size="2" /> :
            <s:textfield name="data_inicio_segundo" size="2" /> -
            <s:textfield name="data_inicio_dia" size="2" /> /
            <s:textfield name="data_inicio_mes" size="2" /> /
            <s:textfield name="data_inicio_ano" size="4" />
        </td>
        <td><s:property value="data_inicioError"/></td>
        <tr>
            <td><s:label value="Data Fim:"/></td>
            <td>
                <s:textfield name="data_fim_hora" size="2" /> :
                <s:textfield name="data_fim_minuto" size="2" /> :
                <s:textfield name="data_fim_segundo" size="2" /> -
                <s:textfield name="data_fim_dia" size="2" /> /
                <s:textfield name="data_fim_mes" size="2" /> /
                <s:textfield name="data_fim_ano" size="4" />
            </td>
            <td><s:property value="data_fimError"/></td>
        </tr>
        <s:if test="tipo == 'Nucleo Estudantes'">
            <tr>
                <td><s:label value="Departamento:"/></td>
                <td>
                    <s:select headerKey="-1" headerValue="-- Selecionar --"
                              list="departamentos"
                              name="departamento"
                              value="departamentoDefault"/>
                </td>
                <td><s:fielderror fieldName="departamento"><s:property value="departamentoError"/></s:fielderror></td>
            </tr>
        </s:if>
        <tr>
            <td><s:submit method="voltar" value="Voltar"/></td>
            <td><s:submit method="add"/></td>
        </tr>
    </table>
</s:form>
</body>
</html>
