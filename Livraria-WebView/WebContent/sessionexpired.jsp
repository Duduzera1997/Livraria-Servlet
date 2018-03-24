<%
	if (request.getSession().getAttribute("userSessionLogged") == null) {
		response.sendRedirect("expiredpage.jsp"); // redireciona para a expiredpage.jsp que emite a mensagem de erro caso a sessão não estiver setada;
	}
%>