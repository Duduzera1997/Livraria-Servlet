<%@include file="sessionexpired.jsp" %>
<%@include file="cabecalho.jsp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Produto</title>
</head>
<body>
	<div>
		<div>
			<h1>.:: Catálago de Produtos ::.</h1>
		</div>
		
		<table border="0" width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<th rowspan="3"></th>
				<th></th>
				<td></td>
				<th></th>
				<th></th>
			</tr>
			<tr>
				<td>
					<div>
						<div>
							<form action="servletproduto" method="post" name="frmproduto" id="frmproduto">
								<input type="hidden" name="action" value="salvar"/>
								<input type="hidden" name="idProduto" value="${objProduto.idProduto}"/>
								<table align="center" width="550">
									<tr>
										<td>
											<table border="0">
												<tr>
													<td><strong>Código:</strong></td><td>&nbsp;&nbsp;</td>
													<td>
														<label for="codigo"></label>
														<input type="text" id="codigo" name="codigo" size="40" maxlength="30" value="${objProduto.codigo}"/>
													</td>
												</tr>
												<tr><td>&nbsp;</td></tr>
												<tr>
													<td><strong>Nome:</strong></td><td>&nbsp;&nbsp;</td>
													<td>
														<label for="nome"></label>
														<input type="text" name="nome" size="40" maxlength="100" id="nome" value="${objProduto.nome}"/>
													</td>
												</tr>
												<tr><td>&nbsp;</td></tr>
												<tr>
                                                    <td colspan="2"><strong>Categoria:</strong></td>
                                                    <td>
                                                        <select name="idCategoria">
                                                        <c:forEach items="${lstCategorias}" var="item">
                                                        <option value="${item.idCategoria}" <c:if test="${item.idCategoria} == ${objProduto.categoria.idCategoria}"> selected </c:if> >${item.nome}</option>
                                                        </c:forEach>
                                                        </select>
                                                    </td>
                                                </tr>
                                                <tr><td>&nbsp;</td></tr>
                                                <tr>
                                                   <td><strong>Valor Venda:</strong></td><td>&nbsp;&nbsp;</td>
                                                   <td>
                                                       <label for="valorVenda"></label>
                                                       <input type="text" name="valorVenda" size="40" maxlength="30" id="valorVenda" value="${objProduto.valorVenda}"/>
                                                   </td>
                                               </tr>
                                               <tr><td>&nbsp;</td></tr>
                                               <tr>
                                                   <td><strong>Qtde Min. Estoque:</strong></td><td>&nbsp;&nbsp;</td>
													<td>
	                                                   <label for="minimo"></label>
	                                                   <input type="text" name="minimo" size="40" maxlength="30" id="minimo" value="${objProduto.minimo}"/>
													</td>
                                                   </td>
                                               </tr>
                                               <tr><td>&nbsp;</td></tr>
                                               <tr>
                                                  <td><strong>Descrição:</strong></td><td>&nbsp;&nbsp;</td>
                                                  <td>
                                                      <textarea name="descricao" cols=40 rows=6>${objProduto.descricao}</textarea>
                                                  </td>
                                               </tr>
                                               <tr><td>&nbsp;</td></tr>
                                               <td align="center" colspan="4"><br/>
                                                  <input type="submit" name="salvar" value=" Salvar "/> &nbsp;&nbsp;
                                                  <input type="button" name="home" value=" Cancelar " onclick="window.location.href='servletproduto?action=home'"/>
                                               </td>
											</table>
										</td>
									</tr>
								</table>
							</form>
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
		</table>
		<div>&nbsp;</div>
	</div>
</body>
</html>