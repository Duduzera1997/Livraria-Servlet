package br.com.livraria.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.livraria.bean.Fornecedor;
import br.com.livraria.bean.Produto;
import br.com.livraria.negocio.FornecedorBusiness;

public class FornecedorController extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		FornecedorBusiness fornecedorBO = new FornecedorBusiness();

		if (action.equals("salvar")) {
			boolean isCpf = false;
			Fornecedor fornecedor = new Fornecedor();
			String cpfOrCnpj = request.getParameter("cpfoucnpj");

			if (cpfOrCnpj.length() == 11) {
				fornecedor.setNome(request.getParameter("nome"));
				fornecedor.setCpf(cpfOrCnpj);
				fornecedor.setEndereco(request.getParameter("endereco"));
				fornecedor.setCidade(request.getParameter("cidade"));
				fornecedor.setUf(request.getParameter("uf"));
				fornecedor.setTelefone(request.getParameter("telefone"));
				fornecedor.setEmail(request.getParameter("email"));
				fornecedor.setDescricao(request.getParameter("descricao"));
				isCpf = true;
			} else if (cpfOrCnpj.length() == 14) {
				fornecedor.setNome(request.getParameter("nome"));
				fornecedor.setCnpj(cpfOrCnpj);
				fornecedor.setEndereco(request.getParameter("endereco"));
				fornecedor.setCidade(request.getParameter("cidade"));
				fornecedor.setUf(request.getParameter("uf"));
				fornecedor.setTelefone(request.getParameter("telefone"));
				fornecedor.setEmail(request.getParameter("email"));
				fornecedor.setDescricao(request.getParameter("descricao"));
				isCpf = false;
			}
			String idd;
			
			idd = request.getParameter("idFornecedor");
			int id = 0;
			
			if (!idd.equals("")) {
				id = Integer.parseInt(idd);
				try {
					fornecedor.setIdFornecedor(id);
					fornecedorBO.editar(fornecedor, isCpf);
					request.setAttribute("msg", "Fornecedor Alterado com Sucesso!");
					request.setAttribute("lstFornecedores", fornecedorBO.pesquisarFornecedor(fornecedor.getNome(), "N"));
					request.getRequestDispatcher("lstfornecedores.jsp").forward(request, response);
					return;
				} catch (SQLException e){
					request.setAttribute("msg", "Erro ao Alterar o Fornecedor, Tente Novamente!");
					request.getRequestDispatcher("lstfornecedores.jsp").forward(request, response);
					return;
				}
			} else {
				try {
					fornecedorBO.incluir(fornecedor, isCpf);
					request.setAttribute("msg", "Fornecedor Cadastrado com Sucesso!");
					request.getRequestDispatcher("lstfornecedores.jsp").forward(request, response);
					return;
				} catch (SQLException e) {
					request.setAttribute("msg", "O Fornecedor Não foi Cadastrado, Tente Novamente!");
					request.getRequestDispatcher("lstprodutos.jsp").forward(request, response);
					return;
				}

			}

		} else if (action.equals("pesquisar")) {
			String parametro = request.getParameter("parametro");
			String busca = request.getParameter("pesquisa");
			ArrayList<Fornecedor> lstFornecedores = null;
			if (busca.equals("")) {
				try {
					lstFornecedores = fornecedorBO.listar();

				} catch (SQLException e) {
					request.setAttribute("msg", "Não foi Possível Listar os Fornecedores!");
				}

			} else {
				try {
					lstFornecedores = fornecedorBO.pesquisarFornecedor(busca, parametro);
				} catch (SQLException e) {
					request.setAttribute("msg", "Não foi Possível Listar os Fornecedores!");
				}
			}
			if (!lstFornecedores.isEmpty()) {
				request.setAttribute("lstFornecedores", lstFornecedores);
	
				// RequestDispatcher destino = request.getRequestDispatcher("lstprodutos.jsp");
				// destino.forward(request, response);
			} else {
				request.setAttribute("msg", "Nenhum Fornecedor Encontrado com o Parâmetro informado!");
			}
			request.getRequestDispatcher("lstfornecedores.jsp").forward(request, response);
			return;
		} else if (action.equals("editar")) {
			int id = Integer.parseInt(request.getParameter("codigo"));
			try {
				request.setAttribute("objFornecedor", new FornecedorBusiness().getFornecedorById(id));
				request.getRequestDispatcher("frmfornecedor.jsp").forward(request, response);
			} catch (SQLException e) {
				request.setAttribute("msg", "Erro ao carregar o Fornecedor!");
				request.getRequestDispatcher("lstfornecedores.jsp").forward(request, response);
				
			}

		}  else if (action.equals("home")) {
			request.getRequestDispatcher("lstfornecedores.jsp").forward(request, response);
			return;
			
		}
	}

}
