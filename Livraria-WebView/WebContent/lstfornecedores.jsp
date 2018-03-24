<%@include file="sessionexpired.jsp" %>
<%@include file="cabecalho.jsp" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>.:: Livraria ::.</title>
    </head>
    <body>
        <div>
            <div>
                <center><h1> ~~> Catálogo de Fornecedores <~~ </h1></center>
            </div>

            <table border="0" width="100%" cellpadding="0" cellspacing="0">
                <tr>
                    <th rowspan="3"></th>
                    <th class="topleft"></th>
                    <td>&nbsp;</td>
                    <th></th>
                    <th rowspan="3"></th>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <div>
                            <div>
                                <form action="servletfornecedor" method="post" name="lstfornecedores">
                                <center>
                                    <table>
                                        <tr><td ><strong> Pesquisar Por: &nbsp;&nbsp;&nbsp; </strong>
                                                <input type="radio" name="parametro" value="N" checked/>Nome &nbsp;&nbsp;&nbsp;&nbsp;
                                                <input type="radio" name="parametro" value="CPF" <c:if test="${parametro == 'CPF'}"> checked</c:if>/> CPF &nbsp;&nbsp;&nbsp;&nbsp;
                                                <input type="radio" name="parametro" value="CNPJ" <c:if test="${parametro == 'CNPJ'}"> checked</c:if>/> CNPJ &nbsp;&nbsp;&nbsp;&nbsp;
                                        </tr>
                                        <tr>
                                            <td>&nbsp;&nbsp;&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <input type="hidden" name="action" value="pesquisar"/>
                                                Parâmetro: <input type="text" name="pesquisa" size="35" maxlength="60" id="pesquisa" value="${pesquisa}" /><br/>
                                            </td>
                                            <td>
                                                <input type="submit" name="btn" value="Pesquisar" /><br/>
                                            </td><td>&nbsp;&nbsp;</td>
                                            <td>
                                                <input type="button" name="btn" value="Novo Fornecedor" onclick="window.location.href='frmfornecedor.jsp'"/>
                                            </td>
                                        </td><td>&nbsp;&nbsp;</td>
                                </tr>
                            </table>
                            </center>
                        </form>
                        <br/>
                        <c:if test="${not empty lstFornecedores}">
                            <table border="0" width="100%" cellpadding="0" cellspacing="0">
                                <tr style="font-family: arial; font-weight: bold; font-size: small; background-color: silver;">
                                    <td>Nome</td>
                                    <td>CPF</td>
                                    <td>CNPJ</td>
                                    <td>Endereço</td>
                                    <td>Cidade</td>
                                    <td>Estado</td>
                                    <td>Telefone</td>
                                    <td>E-mail</td>
                                    <td>Descrição</td>
                                </tr>
                                <c:forEach items="${lstFornecedores}" var="prm">
                                    <tr>
                                        <td>${prm.nome}</td>
                                        <td>${prm.cpf}</td>
                                    	<td>${prm.cnpj}</td>     
                                        <td>${prm.endereco}</td>
                                        <td>${prm.cidade}</td>
                                        <td>${prm.uf}</td>
                                        <td>${prm.telefone}</td>
                                        <td>${prm.email}</td>
                                        <td>${prm.descricao}</td>
                                        <td><a href="servletfornecedor?action=editar&codigo=${prm.idFornecedor}"><font color="#4A708B"><img src="images/atualizar.png" alt="" />  Editar </font></a></td>
                                    </tr>
                                </c:forEach>
                                <tr>
                                    <td colspan="9" align="right">
                                        Quantidade de Produtos Cadastrados: ${fn:length(lstFornecedores)}
                                    </td>
                                </tr>
                            </table>
                        </c:if>
                    </div>
                    <div></div>
                </div>
            </td>
            <td></td>
        </tr>
        <tr>
            <th></th>
            <td>&nbsp;</td>
            <th></th>
        </tr>
         <center>
            <FONT FACE="Verdana" COLOR="#8B0000"><b> ${msg} </b></FONT>
         </center>
    </table>
    <div>&nbsp;</div>
</div>
</body>
</html>

