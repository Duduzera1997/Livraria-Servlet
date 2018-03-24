<%@include file="sessionexpired.jsp" %>
<%@include file="cabecalho.jsp" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>.:: Livraria dos Anjos ::.</title>
    </head>
    <body>
        <div>
            <div>
                <center><h1>~~> Entrada no Estoque <~~</h1></center>
            </div>

            <table border="0" width="100%" cellpadding="0" cellspacing="0">
                <tr>
                    <th rowspan="3"></th>
                    <th></th>
                    <td>&nbsp;</td>
                    <th></th>
                    <th rowspan="3"></th>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <div>
                            <div>
                                <form action="servletentrada" method="post" name="frmestoque" id="frmestoque">
                                    <input type="hidden" name="action" value=""/>
                                    <table align="center" width="100%">
                                        <tr>
                                            <td><center>
                                                <table border="0" align="">
                                                    <tr>
                                                        <td><strong>Insira o C�digo do Produto:</strong></td><td>&nbsp;&nbsp;</td>
                                                        <td>
                                                            <label for="codigo"></label>
                                                            <input type="text" id="codigo" name="codigo" size="40" maxlength="100" id="codigo" value="" />
                                                        </td>
                                                    </tr>
                                                    <tr><td>&nbsp;</td></tr>
                                                    <tr>
                                                        <td align="center" colspan="4"><br/>
                                                            <input type="submit" value="Adicionar" onclick="action.value='adicionar'"/> &nbsp;&nbsp;             
                                                        </td>
                                                    </tr>
                                                </table>
                                                </center>
                                                <center>
                                                    <br><br>
                                                    <FONT FACE="Verdana" COLOR="#8B0000"><b> ${msg} </b></FONT>
                                                </center><br><br>
                                            </td>
                                        </tr>
                                    </table>
                                </form>
                                <c:if test="${not empty embalagem}">
                                    <table border="0" width="100%" cellpadding="0" cellspacing="0" id="product-table">
                                        <tr style="font-family: arial; font-weight: bold; font-size: small; background-color: silver;">
                                            <td>Produto</td>
                                            <td>Valor Unit.</td>
                                            <td>Quantidade</td>
                                            <td>Sub-Total</td>
                                            <td>Alterar</td>
                                            <td>Excluir</td>
                                        </tr>

                                        <c:forEach items="${embalagem.itens}" var="item">
                                            <form action="servletentrada" method="post" >
                                                <input type="hidden" name="codigo" value="${item.produto.codigo}"/>
                                                <input type="hidden" name="action" value=""/>
                                                <tr>
                                                    <td>${item.produto.nome}</td>
                                                    <td><input type="text" name="valorEntrada" size="7px" value="<fmt:formatNumber value='${item.valorEntrada}' pattern='##,##0.00'/>"/></td>
                                                    <td>
                                                        <input type="text" name="qtd${item.produto.codigo}" value="${item.quantidade}" id="qtd${item.produto.codigo}" size="3" onKeyPress="return Tecla(event)" />
                                                    </td>
                                                    <td><fmt:formatNumber value="${item.valorTotal}" pattern="##,##0.00" /></td>                                                  
                                                    <td><input type="submit" value="Alterar" onclick="action.value='alterar'"/></td>
                                                    <td><input type="submit" value="Remover" onclick="action.value='remover'"/></td>
                                                </tr>
                                            </form>
                                        </c:forEach>
                                        <tr>
                                        <tr>
                                            <td colspan="9" align="right">
                                                Total da Nota: <fmt:formatNumber value='${valorTotal}' pattern='R$ #,##0.00' />
                                                <input type="hidden" name='quantidade' value='${quantidade}'/>                                           </td>
                                        </tr>
                                    </table>
                                    <input type="button" value="Finalizar Entrada" onclick="location.href='servletentrada?action=montar&valorTotal=${valorTotal}'">
                                    <input type="button" value="Cancelar Entrada" onclick="location.href='servletentrada?action=cancelar'">
                                </c:if>
                            </div></div>
                    </td>
                </tr>
            </table>
        </div>
        <div></div>
    </div>
</td>
<td id="tbl-border-right"></td>
</tr>
<tr>
    <th></th>
    <td>&nbsp;</td>
    <th></th>
</tr>
</table>
<div>&nbsp;</div>
</div>
</body>
</html>
