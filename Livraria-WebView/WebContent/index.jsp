<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/style.css" />
<title>Livraria - Login</title>
</head>
<body>
	<div id="topo">
		<center><h1 font-face="Tahoma">Acesso ao Sistema</h1><center>
	</div>
	<div id="corpo">
		<center>
			<form action="servletuser" method="post" name="login" id="login">
				<input type="hidden" name="action" value="logar"/>
				<table>
					<tr>
						<th>Usuário: </th>
						<td><input type="text" name="usuario" id="usuario" required/></td>
					</tr>
					<tr>
						<th>Senha: </th>
						<td><input type="password" name="senha" id="senha" required/></td>
					</tr>
					<tr>
						<td></td>
							<td>
							<input type="submit" value="Logar" name="logar" required/>
						</td>
					</tr>
				</table>
			</form>
		</center>
		<center>
        	<FONT FACE="Tahoma" COLOR="#8B0000"><b> ${msg}</b></FONT>
        </center>
	</div>
</body>
</html>