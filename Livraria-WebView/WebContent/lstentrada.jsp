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
                <center><h1>.:: Catálogo de Entrada ::.</h1></center>
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
                                <form action="servletentrada" method="post" name="lstNotaFiscal">
                                <center>
                                    <table>
                                        <tr><td ><strong> Pesquisar Por: &nbsp;&nbsp;&nbsp; </strong>
                                                <input type="radio" name="parametro" value="C" checked/>Código &nbsp;&nbsp;&nbsp;&nbsp;
                                                
                                        </tr>
                                        <tr>
                                            <td>&nbsp;&nbsp;&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <input type="hidden" name="action" value="pesquisar"/>
                                                Código da Nota Fiscal: <input type="text" name="nome" size="35" maxlength="60" id="nome" value="${codigo}" /><br/>
                                            </td>
                                            <td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
                                            <td>
                                                <input type="submit" name="btn" value="Pesquisar" /><br/>
                                            </td><td>&nbsp;&nbsp;</td>
                                            <td>
                                                <input type="button" name="btn" value="Nova Entrada" onclick="window.location.href='frmentrada.jsp'"/>
                                            </td>
                                        </td><td>&nbsp;&nbsp;</td>
                                </tr>
                            </table>
                            </center>
                        </form>
                        <br/>
                        <c:if test="${not empty lstNotaFiscal}">
                            <table border="0" width="100%" cellpadding="0" cellspacing="0">
                                <tr style="font-family: arial; font-weight: bold; font-size: small; background-color: silver;">
                                    <td>Código</td>
                                    <td>Data</td>
                                    <td>Valor do Frete</td>
                                    <td>Outras Dispesas</td>
                                    <td>Valor Total</td>
                                    <!-- <td>Editar</td> -->
                                </tr>
                                <c:forEach items="${lstNotaFiscal}" var="item">
                                    <tr>
                                        <td>${item.codNf}</td>
                                        <td>${item.dtNota}</td>
                                        <td>${item.valorFrete}</td>
                                        <td>${item.outrasDispesas}</td>
                                        <td>${item.valorTotal}</td>
                                        <!-- <td><a href="servletentrada?action=editar&codigo=${item.idNotaFiscal}"><font color="#4A708B"><img src="images/atualizar.png" alt="" />  Editar </font></a></td>
                                    	-->
                                    </tr>
                                </c:forEach>
                                <tr>
                                    <td colspan="9" align="right">
                                        Quantidade de Notas Fiscais Cadastradas: ${fn:length(lstNotaFiscal)}
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

