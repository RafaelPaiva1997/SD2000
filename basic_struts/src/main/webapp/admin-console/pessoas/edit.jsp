<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Novo <s:property value="tipo"/></title>
    <style>
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
<h1>Novo <s:property value="tipo"/></h1>
<s:form action="pessoas-update">
    <s:hidden name="id" value="%{id}"/>
    <s:hidden name="tipo" value="%{tipo}"/>
    <table>
        <tr>
            <td><s:label value="Nome:"/></td>
            <td><s:textfield name="nome"  value="%{nome}" size="30"/></td>
            <td><s:fielderror fieldName="nome"><s:property value="nomeError"/></s:fielderror></td>
        </tr>
        <tr>
            <td><s:label value="Username:"/></td>
            <td><s:textfield name="username" value="%{username}" size="30"/></td>
            <td><s:fielderror fieldName="username"><s:property value="usernameError"/></s:fielderror></td>
        </tr>
        <tr>
            <td><s:label value="Password:"/></td>
            <td><s:textfield name="password" value="%{password}" size="30"/></td>
            <td><s:fielderror fieldName="password"><s:property value="passwordError"/></s:fielderror></td>
        </tr>
        <tr>
            <td><s:label value="Telemovel:"/></td>
            <td><s:textfield name="telemovel" value="%{telemovel}" size="30"/></td>
            <td><s:fielderror fieldName="telemovel"><s:property value="telemovelError"/></s:fielderror></td>
        </tr>
        <tr>
            <td><s:label value="Localidade:"/></td>
            <td><s:textfield name="localidade" value="%{localidade}" size="30"/></td>
            <td><s:fielderror fieldName="localidade"><s:property value="localidadeError"/></s:fielderror></td>
        </tr>
        <tr>
            <td><s:label value="Codigo postal:"/></td>
            <td><s:textfield name="codigo_postal" value="%{codigo_postal}" size="30"/></td>
            <td><s:fielderror fieldName="codigo_postal"><s:property value="codigo_postalError"/></s:fielderror></td>
        </tr>
        <tr>
            <td><s:label value="Morada:"/></td>
            <td><s:textfield name="morada" value="%{morada}" size="30"/></td>
            <td><s:fielderror fieldName="morada"><s:property value="moradaError"/></s:fielderror></td>
        </tr>
        <tr>
            <td><s:label value="Numero de CC:"/></td>
            <td><s:textfield name="numero_cc" value="%{numero_cc}" size="30"/></td>
            <td><s:fielderror fieldName="numero_cc"><s:property value="numero_ccError"/></s:fielderror></td>
        </tr>
        <s:if test="tipo == 'Aluno'">
            <tr>
                <td><s:label value="Numero Aluno:"/></td>
                <td><s:textfield name="numero_aluno" value="%{numero_aluno}" size="30"/></td>
                <td><s:fielderror fieldName="numero_aluno"><s:property value="numero_alunoError"/></s:fielderror></td>
            </tr>
            <tr>
                <td><s:label value="Curso:"/></td>
                <td><s:textfield name="curso"  value="%{curso}" size="30"/></td>
                <td><s:fielderror fieldName="curso"><s:property value="cursoError"/></s:fielderror></td>
            </tr>
        </s:if>
        <s:if test="tipo == 'Docente'">
            <tr>
                <td><s:label value="Cargo:"/></td>
                <td><s:textfield name="cargo" value="%{cargo}"  size="30"/></td>
                <td><s:fielderror fieldName="cargo"><s:property value="cargoError"/></s:fielderror></td>
            </tr>
        </s:if>
        <s:if test="tipo == 'Funcionario'">
            <tr>
                <td><s:label value="Funcao:"/></td>
                <td><s:textfield name="funcao" value="%{funcao}"  size="30"/></td>
                <td><s:fielderror fieldName="funcao"><s:property value="funcaoError"/></s:fielderror></td>
            </tr>
        </s:if>
        <tr>
            <td><s:label value="Validade de CC:"/></td>
            <td>
                <s:textfield name="validade_cc_dia" value="%{validade_cc_dia}" size="2"/> /
                <s:textfield name="validade_cc_mes" value="%{validade_cc_mes}" size="2"/> /
                <s:textfield name="validade_cc_ano" value="%{validade_cc_ano}" size="4"/>
            </td>
            <td><s:fielderror fieldName="validade_cc"><s:property value="validade_ccError"/></s:fielderror></td>
        </tr>
        <tr>
            <td><s:label value="Data de nascimento:"/></td>
            <td>
                <s:textfield name="data_nascimento_dia" value="%{data_nascimento_dia}" size="2"/> /
                <s:textfield name="data_nascimento_mes" value="%{data_nascimento_mes}" size="2"/> /
                <s:textfield name="data_nascimento_ano" value="%{data_nascimento_ano}" size="4"/>
            </td>
            <td><s:fielderror fieldName="data_nascimento"><s:property value="data_nascimentoError"/></s:fielderror></td>
        </tr>
        </tr>
        <tr>
            <td><s:label value="Departamento:"/></td>
            <td>
                <s:select headerKey="-1" headerValue="-- Seleccionar --"
                          list="departamentos"
                          name="departamento"
                          value="departamentoDefault"/>
            </td>
            <td><s:fielderror fieldName="departamento"><s:property value="departamentoError"/></s:fielderror></td>
        </tr>
        <tr>
            <td><s:label value="Genero:"/></td>
            <td>
                <s:select headerKey="-1" headerValue="-- Seleccionar --"
                          list="{'Masculino','Feminino','Outro'}"
                          name="genero"
                          value="genero"/>
            </td>
            <td><s:fielderror fieldName="genero"><s:property value="generoError"/></s:fielderror></td>
        </tr>
        <tr>
            <td><s:label value="Admin:"/></td>
            <td><s:checkbox name="admin" value="%{admin}"/></td>
            <td><s:fielderror fieldName="admin"><s:property value="adminError"/></s:fielderror></td>
        </tr>
        <tr>
            <td><s:submit method="voltar" value="Voltar" cssClass="link2"/></td>
            <td><s:submit method="update" value="Actualizar" cssClass="link2"/></td>
        </tr>
    </table>
</s:form>
</body>
</html>