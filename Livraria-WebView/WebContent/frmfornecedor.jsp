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
	<title>Cadastro de Fornecedores</title>
</head>
<body>
	<div>
		<div>
			<center><h1>~~> Cadastro de Fornecedores <~~</h1></center>
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
							<form action="servletfornecedor" method="post" name="frmfornecedor" id="frmfornecedor">
								<input type="hidden" name="action" value="salvar"/>
								<input type="hidden" name="idFornecedor" value="${objFornecedor.idFornecedor}"/>
								<table align="center" width="550">
									<tr>
										<td>
											<table border="0">
												<tr>
													<td><strong>Nome:</strong></td><td>&nbsp;&nbsp;</td>
													<td>
														<label for="nome"></label>
														<input type="text" id="nome" name="nome" size="40" maxlength="30" value="${objFornecedor.nome}"/>
													</td>
												</tr>
												<tr><td>&nbsp;</td></tr>
												<tr>
													<td><strong>CPF/CNPJ:</strong></td><td>&nbsp;&nbsp;</td>
													<td>
														<label for="cpfoucnpj"></label>
														<input type="text" name="cpfoucnpj" size="40" maxlength="100" id="cpfoucnpj" value="${objFornecedor.cnpj}"/>
													</td>
												</tr>
												<tr><td>&nbsp;</td></tr>
												<tr>
                                                   <td><strong>Endereço:</strong></td><td>&nbsp;&nbsp;</td>
                                                   <td>
                                                       <label for="endereco"></label>
                                                       <input type="text" name="endereco" size="40" maxlength="30" id="endereco" value="${objFornecedor.endereco}"/>
                                                   </td>
                                               </tr>
                                                <tr><td>&nbsp;</td></tr>
                                                <tr>
                                                   <td><strong>Cidade:</strong></td><td>&nbsp;&nbsp;</td>
                                                   <td>
                                                       <label for="cidade"></label>
                                                       <input type="text" name="cidade" size="40" maxlength="30" id="cidade" value="${objFornecedor.cidade}"/>
                                                   </td>
                                               </tr>
                                               <tr><td>&nbsp;</td></tr>
                                               <tr>
                                                    <td colspan="2"><strong>UF:</strong></td>
                                                    <td>
                                                        <select name="uf" value="${objFornecedor.uf}">
                                                        <option name="uf" value="${objFornecedor.uf}">AC</option>
														<option name="uf" value="${objFornecedor.uf}">AL</option>
														<option name="uf" value="${objFornecedor.uf}">AM</option>
														<option name="uf" value="${objFornecedor.uf}">AP</option>
														<option name="uf" value="${objFornecedor.uf}">BA</option>
														<option name="uf" value="${objFornecedor.uf}">CE</option>
														<option name="uf" value="${objFornecedor.uf}">DF</option>
														<option name="uf" value="${objFornecedor.uf}">ES</option>
														<option name="uf" value="${objFornecedor.uf}">GO</option>
														<option name="uf" value="${objFornecedor.uf}">MA</option>
														<option name="uf" value="${objFornecedor.uf}">MG</option>
														<option name="uf" value="${objFornecedor.uf}">MS</option>
														<option name="uf" value="${objFornecedor.uf}">MT</option>
														<option name="uf" value="${objFornecedor.uf}">PA</option>
														<option name="uf" value="${objFornecedor.uf}">PB</option>
														<option name="uf" value="${objFornecedor.uf}">PE</option>
														<option name="uf" value="${objFornecedor.uf}">PI</option>
														<option name="uf" value="${objFornecedor.uf}">PR</option>
														<option name="uf" value="${objFornecedor.uf}">RJ</option>
														<option name="uf" value="${objFornecedor.uf}">RN</option>
														<option name="uf" value="${objFornecedor.uf}">RO</option>
														<option name="uf" value="${objFornecedor.uf}">RR</option>
														<option name="uf" value="${objFornecedor.uf}">RS</option>
														<option name="uf" value="${objFornecedor.uf}">SC</option>
														<option name="uf" value="${objFornecedor.uf}">SE</option>
														<option name="uf" value="${objFornecedor.uf}">SP</option>
														<option name="uf" value="${objFornecedor.uf}">TO</option>
                                                        </select>
                                                    </td>
                                                </tr>
                                                <tr><td>&nbsp;</td></tr>
                                                <tr>
                                                   <td><strong>Telefone:</strong></td><td>&nbsp;&nbsp;</td>
                                                   <td>
                                                       <label for="telefone"></label>
                                                       <input type="text" name="telefone" size="40" maxlength="30" id="telefone" value="${objFornecedor.telefone}"/>
                                                   </td>
                                               </tr>
                                               <tr><td>&nbsp;</td></tr>
                                               <tr>
                                                   <td><strong>E-mail:</strong></td><td>&nbsp;&nbsp;</td>
													<td>
	                                                   <label for="email"></label>
	                                                   <input type="text" name="email" size="40" maxlength="30" id="minimo" value="${objFornecedor.email}"/>
													</td>
                                                   </td>
                                               </tr>
                                               <tr><td>&nbsp;</td></tr>
                                                <tr>
                                                   <td><strong>Descrição:</strong></td><td>&nbsp;&nbsp;</td>
													<td>
	                                                   <textarea name="descricao" cols=37 rows=6>${objFornecedor.descricao}</textarea>
													</td>
                                                   </td>
                                               </tr>
                                               <tr><td>&nbsp;</td></tr>
                                               <td align="center" colspan="4"><br/>
                                                  <input type="submit" name="salvar" value=" Salvar "/> &nbsp;&nbsp;
                                                  <input type="button" name="home" value=" Cancelar " onclick="window.location.href='servletfornecedor?action=home'"/>
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