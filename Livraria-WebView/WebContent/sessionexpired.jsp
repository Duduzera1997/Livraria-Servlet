<%
	if (request.getSession().getAttribute("userSessionLogged") == null) {
		response.sendRedirect("expiredpage.jsp"); // redireciona para a expiredpage.jsp que emite a mensagem de erro caso a sess�o n�o estiver setada;
	}
%>