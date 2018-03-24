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
    <script language="javascript">
    	
    </script>
        <div id="content">

            <div id="page-heading">
                <center><h1>.:: Finalizar Venda ::.</h1></center>
            </div>
			<center>
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
                                <form action="servletvenda" method="post" name="frmfinalizarvenda" id="frmfinalizarvenda" runat="server">
                                    <input type="hidden" name="action" value="finalizar"/>
                                    <input type="hidden" name="carrinho" value="${objPedido.itens}"/>
                                    <input type="hidden" name="valorVenda" value="${objPedido.valorTotal}"/>
                                    <table align="center" width="550">
                                        <tr>
                                            <td>
                                                <table border="0">
                                                    <tr>
                                                        <td><strong>Data da Venda:</strong></td><td>&nbsp;&nbsp;</td>
                                                        <td>
                                                            <input type="text" id="data_1" name="dtNota" size="10" maxlength="10" id="dtNota" disabled="disabled" value="<fmt:formatDate value='${objPedido.dtNota}' pattern='dd/MM/yyyy' />" />
                                                        </td>
                                                    </tr>
                                                    <tr><td>&nbsp;</td></tr>
                                                    <tr>
                                                        <td><strong>Total da Nota:</strong></td><td>&nbsp;&nbsp;</td>
                                                        <td>
                                                            <fmt:formatNumber value='${objPedido.valorTotal}' pattern='R$ #,##0.00'/>
                                                        </td>
                                                    </tr>
                                                    <tr><td>&nbsp;</td></tr>
                                                    <!-- <tr>
                                                         <td><strong>Tipo de Pagamento:</strong></td><td>&nbsp;&nbsp;</td>
                                                        <td colspan="10">
                                                            <select name="idTipoPagamento">
                                                                <c:forEach items="${lstTipoPagamento}" var="item">
                                                                    <option value="${item.idTipoPagamento}" <c:if test="${item.idTipoPagamento == 1}"> selected </c:if> >${item.descricao}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </td><td>&nbsp;&nbsp;</td><td>&nbsp;&nbsp;</td>
                                                    </tr>
                                                    <tr><td>&nbsp;</td></tr>-->
                                                    <tr>
                                                        <td><strong>Desconto:</strong></td><td>&nbsp;&nbsp;</td>
                                                        <td>
                                                            <label for="desconto"></label>
                                                            <input type="text" name="desconto" size="40" maxlength="30" id="desconto" value="<fmt:formatNumber value='0' pattern='#,##0.00' />" />
                                                        </td>
                                                    </tr>
                                                    <tr><td>&nbsp;</td></tr>
                                                     <tr>
                                                        <td><strong>Total da Nota com Desconto:</strong></td><td>&nbsp;&nbsp;</td>
                                                        <td>
                                                            <fmt:formatNumber value='${objPedido.valorTotal}' pattern='R$ #,##0.00'/>
                                                        </td>
                                                    </tr>
                                                    <tr><td>&nbsp;</td></tr>
                                                    <tr>
                                                        <td><strong>Observações</strong></td><td>&nbsp;&nbsp;</td>
                                                        <td>
                                                            <textarea id="observacao" name="observacao" cols=40 rows=6> ${objPedido.observacao} </textarea>
                                                        </td>
                                                    </tr>
                                                    <tr><td>&nbsp;</td></tr>
                                                    <td align="center" colspan="4"><br/>
                                                        <input type="submit" value="Finalizar"/> &nbsp;&nbsp;             
                                                        <input type="submit" value="Voltar" onclick="action.value='frmvenda.jsp'"/> &nbsp;&nbsp;             
                                                    </td>
                                                    <tr><td>&nbsp;</td></tr>
                                                    <tr><td>&nbsp;</td></tr>
                                                </table>
                                                </form>
                                            </td>
                                        </tr>
                                    </table>
                                    <center>
                                        <FONT FACE="Verdana" COLOR="#8B0000"><b> ${msg} </b></FONT>
                                    </center>
                            </div>
                            <div class="clear"></div>
                        </div>
                    </td>
                    <td id="tbl-border-right"></td>
                </tr>
                <tr>
                    <th class="sized bottomleft"></th>
                    <td id="tbl-border-bottom">&nbsp;</td>
                    <th class="sized bottomright"></th>
                </tr>
            </table>
            </center>
            <div>&nbsp;</div>
        </div>
    </body>
</html>
