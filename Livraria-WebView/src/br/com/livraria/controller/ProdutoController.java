package br.com.livraria.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.livraria.bean.Categoria;
import br.com.livraria.bean.Produto;
import br.com.livraria.negocio.CategoriaBusiness;
import br.com.livraria.negocio.ProdutoBusiness;
import br.com.livraria.persistencia.ProdutoDAO;

public class ProdutoController extends HttpServlet {
	
	protected void service (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String action = request.getParameter("action");
		ProdutoBusiness produtoBO = new ProdutoBusiness();
		
		if (action.equals("salvar")) {
			Produto produto = new Produto();
			Categoria categoria = new Categoria();
			produto.setNome(request.getParameter("nome"));
			produto.setCodigo(request.getParameter("codigo"));
			produto.setEstoque(0);
			produto.setMinimo(Integer.parseInt(request.getParameter("minimo")));
			produto.setValorVenda(Double.parseDouble(request.getParameter("valorVenda")));
			produto.setDescricao(request.getParameter("descricao"));
			produto.setCategoria(categoria);
			
			if (!request.getParameter("idProduto").equals("")) {
				int id = Integer.parseInt(request.getParameter("idProduto"));
				try {
					produto.setIdProduto(id);
					produtoBO.editar(produto);
					request.setAttribute("msg", "Produto Alterado com Sucesso!");
					request.setAttribute("lstProdutos", produtoBO.pesquisarProduto(produto.getCodigo(), "C"));
					request.getRequestDispatcher("lstprodutos.jsp").forward(request, response);
					return;
				} catch (SQLException e){
					request.setAttribute("msg", "Erro ao Alterar o Produto, Tente Novamente!");
					request.getRequestDispatcher("lstprodutos.jsp").forward(request, response);
					return;
				}
				
			} else {
				try {
					produtoBO.incluir(produto);
					request.setAttribute("msg", "Produto Cadastrado com Sucesso!");
					request.getRequestDispatcher("lstprodutos.jsp").forward(request, response);
					return;
				} catch (SQLException e) {
					request.setAttribute("msg", "O Produto Não foi Cadastrado, verifique se não há um Produto cadastrado com o Código Inserido!");
					request.getRequestDispatcher("lstprodutos.jsp").forward(request, response);
					return;
				}
			}
			
		}
		
		if (action.equals("pesquisar")) {
			String parametro = request.getParameter("parametro");
			String busca =  request.getParameter("nome");
			ArrayList<Produto> lstProdutos = null;
				if (busca.equals("")) {
						try {
							lstProdutos = produtoBO.listar();
							
						} catch (SQLException e) {
							request.setAttribute("msg", "Não foi Possível Listar Produtos!");
						}	
					
				} else {
					try {
						lstProdutos = produtoBO.pesquisarProduto(busca, parametro);
					} catch (SQLException e) {
						request.setAttribute("msg", "Não foi Possível Listar Produtos!");
					}
				}
				if (!lstProdutos.isEmpty()) {
					request.setAttribute("lstProdutos", lstProdutos);
					//RequestDispatcher destino = request.getRequestDispatcher("lstprodutos.jsp");
					//destino.forward(request, response);
			} else {
				request.setAttribute("msg", "Nenhum Produto Encontrado com o Parâmetro informado!");
			}
				request.getRequestDispatcher("lstprodutos.jsp").forward(request, response);
				return;		
		} else if (action.equals("editar")) {
			int id = Integer.parseInt(request.getParameter("codigo"));
			try {
				request.setAttribute("objProduto", new ProdutoBusiness().getProdutoById(id));
				request.setAttribute("lstCategorias", new CategoriaBusiness().listar());
				request.getRequestDispatcher("frmproduto.jsp").forward(request, response);
			} catch (ClassNotFoundException | SQLException e) {
				request.setAttribute("msg", "Erro ao carregar o Produto!");
				request.getRequestDispatcher("lstprodutos.jsp").forward(request, response);
				
			}

		} else if (action.equals("novo")) {
			try {
				request.setAttribute("lstCategorias", new CategoriaBusiness().listar());
				request.getRequestDispatcher("frmproduto.jsp").forward(request, response);
				return;
			} catch (ClassNotFoundException | SQLException e) {
				request.setAttribute("msg", "Erro ao carregar a Página!");
				request.getRequestDispatcher("lstprodutos.jsp").forward(request, response);
				return;
			}
		} else if (action.equals("home")) {
			request.getRequestDispatcher("lstprodutos.jsp").forward(request, response);
			return;
			
		}
		/*PrintWriter out = response.getWriter();
		out.println("<html><title>Teste</title><h1>Ação Executada: " + action + "</h1></html>");*/
		
	}
}
