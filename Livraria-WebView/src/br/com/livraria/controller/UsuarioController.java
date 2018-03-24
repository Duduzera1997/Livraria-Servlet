package br.com.livraria.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.livraria.bean.Usuario;
import br.com.livraria.negocio.UsuarioBusiness;

public class UsuarioController extends HttpServlet {
	
	protected void service (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		UsuarioBusiness usuarioBO = new UsuarioBusiness();
		
		if (action.equals("logar")) {
			String usuario = request.getParameter("usuario");
			String senha = request.getParameter("senha");
			try {
				Usuario user = usuarioBO.getUsuario(usuario, senha);
				if (user.getIdUsuario() != 0) {
					if (user.getLogin() != null) {
						request.getSession().setAttribute("userSessionLogged", usuario);
						request.getSession().setAttribute("nivel", user.getNivel());
						request.getRequestDispatcher("lstprodutos.jsp").forward(request, response);
						return;
					} else {
						request.setAttribute("msg", "Oops!!! Usuário ou Senha Incorretos!");
						request.getRequestDispatcher("index.jsp").forward(request, response);
						return;
					}
				} else {
					request.setAttribute("msg", "Oops!!! Usuário ou Senha Incorretos!");
					request.getRequestDispatcher("index.jsp").forward(request, response);
					return;
				}
			} catch (SQLException e) {
				request.setAttribute("msg", "Oops!!! Usuário ou Senha Incorretos!");
				request.getRequestDispatcher("index.jsp").forward(request, response);
				return;
			}
			
		}
		
		if (action.equals("logout")) {
			request.getSession().removeAttribute("userSessionLogged");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
			
		}
 	
	}

}
