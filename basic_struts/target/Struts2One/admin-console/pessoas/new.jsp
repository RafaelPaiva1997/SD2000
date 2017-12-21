<html>
<head>
    <title>Nova Pessoa</title>
</head>
<body>
<h1>Nova Pessoa</h1>
<s:form action="pessoas-add">
    <table>
        <tr>
            <td><s:label value="Nome:"/></td>
            <td><s:textfield name="nome"/></td>
            <td><s:fielderror fieldName="nome"><s:property value="nomeError"/></s:fielderror></td>

            <td><s:label value="Username:"/></td>
            <td><s:textfield name="username"/></td>
            <td><s:fielderror fieldName="username"><s:property value="usernameERROR"/></s:fielderror></td>

            <td><s:label value="Password:"/></td>
            <td><s:textfield name="password"/></td>
            <td><s:fielderror fieldName="password"><s:property value="passwordERROR"/></s:fielderror></td>

            <td><s:label value="Departamento:"/></td>
            <td>
                <s:select label="Departamento"
                          headerKey="-1" headerValue="-- Select --"
                          list="departamentos"
                          name="departamento"
                          value="departamentoDefault"/>
            </td>
            <td><s:fielderror fieldName="departamento"><s:property value="departamentoError"/></s:fielderror></td>

            <td><s:label value="Tipo:"/></td>
            <td><s:textfield name="tipo"/></td>
            <td><s:fielderror fieldName="tipo"><s:property value="tipoERROR"/></s:fielderror></td>
        </tr>
        <tr>
            <td><s:label value="Telemovel:"/></td>
            <td><s:textfield name="telemovel"/></td>
            <td><s:fielderror fieldName="telemovel"><s:property value="telemovelError"/></s:fielderror></td>

            <td><s:label value="Localidade:"/></td>
            <td><s:textfield name="localidade"/></td>
            <td><s:fielderror fieldName="localidade"><s:property value="localidadeERROR"/></s:fielderror></td>

            <td><s:label value="Código postal:"/></td>
            <td><s:textfield name="codigo_postal"/></td>
            <td><s:fielderror fieldName="codigo_postal"><s:property value="codigo_postalERROR"/></s:fielderror></td>

            <td><s:label value="Morada:"/></td>
            <td><s:textfield name="morada"/></td>
            <td><s:fielderror fieldName="morada"><s:property value="moradaError"/></s:fielderror></td>

            <td><s:label value="Numero de CC:"/></td>
            <td><s:textfield name="numero_cc"/></td>
            <td><s:fielderror fieldName="numero_cc"><s:property value="numero_ccError"/></s:fielderror></td>
        </tr>
        <tr>
            <td><s:label value="Validade de CC:"/></td>
            <td><s:textfield name="validade_cc"/></td>
            <td><s:fielderror fieldName="validade_cc"><s:property value="validade_ccError"/></s:fielderror></td>

            <td><s:label value="Género:"/></td>
            <td><s:textfield name="genero"/></td>
            <td><s:fielderror fieldName="genero"><s:property value="generoERROR"/></s:fielderror></td>

            <td><s:label value="Data de nascimento:"/></td>
            <td><s:textfield name="data_nascimento"/></td>
            <td><s:fielderror fieldName="data_nascimento"><s:property value="data_nascimentoERROR"/></s:fielderror></td>

            <td><s:label value="Admin?:"/></td>
            <td><s:textfield name="admin"/></td>
            <td><s:fielderror fieldName="admin"><s:property value="adminError"/></s:fielderror></td>


        </tr>
        <tr>
            <td><s:submit method="voltar" value="Voltar"/></td>
            <td><s:submit method="add"/></td>
        </tr>
    </table>
</s:form>
</body>
</html>