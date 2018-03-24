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
        <link rel="stylesheet" type="text/css" href="css/style.css" />
        <title>.:: Livraria ::.</title>
    </head>
    <body>
        <div>
            <div id='topo'>
                <center><h1> ~~> Catálogo de Produtos <~~ </h1></center>
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
                                <form action="servletproduto" method="post" name="lstprodutos">
                                <center>
                                    <table>
                                        <tr><td ><strong> Pesquisar Por: &nbsp;&nbsp;&nbsp; </strong>
                                                <input type="radio" name="parametro" value="N" checked/>Nome &nbsp;&nbsp;&nbsp;&nbsp;
                                                <input type="radio" name="parametro" value="C" <c:if test="${parametro == 'C'}"> checked</c:if>/> Código &nbsp;&nbsp;&nbsp;&nbsp;
                                                <input type="radio" name="parametro" value="P" <c:if test="${parametro == 'P'}"> checked</c:if>/> Preço &nbsp;&nbsp;&nbsp;&nbsp;
                                        </tr>
                                        <tr>
                                            <td>&nbsp;&nbsp;&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <input type="hidden" name="action" value="pesquisar"/>
                                                Produto: <input type="text" name="nome" size="35" maxlength="60" id="nome" value="${nome}" /><br/>
                                            </td>
                                            <td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
                                            <td>
                                                <input type="submit" name="btn" value="Pesquisar" /><br/>
                                            </td><td>&nbsp;&nbsp;</td>
                                            <td>
                                                <input type="button" name="btn" value="Novo Produto" onclick="window.location.href='servletproduto?action=novo'"/>
                                            </td>
                                        </td><td>&nbsp;&nbsp;</td>
                                </tr>
                            </table>
                            </center>
                        </form>
                        <br/>
                        <c:if test="${not empty lstProdutos}">
                            <table border="0" width="100%" cellpadding="0" cellspacing="0">
                                <tr style="font-family: arial; font-weight: bold; font-size: small; background-color: silver;">
                                    <td>Código</td>
                                    <td>Produto</td>
                                    <td>Estoque</td>
                                    <td>Mínimo</td>
                                    <td>Valor Venda</td>
                                    <td>Editar</td>
                                </tr>
                                <c:forEach items="${lstProdutos}" var="item">
                                    <tr>
                                        <td>${item.codigo}</td>
                                        <td>${item.nome}</td>
                                        <td>${item.estoque}</td>
                                        <td>${item.minimo}</td>
                                        <td><fmt:formatNumber value='${item.valorVenda}' pattern='R$ #,##0.00' /></td>
                                        <td><a href="servletproduto?action=editar&codigo=${item.idProduto}"><font color="#4A708B"><img src="images/atualizar.png" alt="" />  Editar </font></a></td>
                                    </tr>
                                </c:forEach>
                                <tr>
                                    <td colspan="9" align="right">
                                        Quantidade de Produtos Cadastrados: ${fn:length(lstProdutos)}
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

