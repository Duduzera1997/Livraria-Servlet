package br.com.livraria.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.livraria.bean.Embalagem;
import br.com.livraria.bean.Entrada;
import br.com.livraria.bean.Fornecedor;
import br.com.livraria.bean.NotaFiscal;
import br.com.livraria.bean.Produto;
import br.com.livraria.negocio.CategoriaBusiness;
import br.com.livraria.negocio.EntradaBusiness;
import br.com.livraria.negocio.FornecedorBusiness;
import br.com.livraria.negocio.ProdutoBusiness;

public class EntradaController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	String quantidade;

	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String acao = request.getParameter("action");
		request.getSession().removeAttribute("msg");

		if ("pesquisar".equals(acao)) {
			EntradaBusiness entradaBO = new EntradaBusiness();
			String parametro = request.getParameter("parametro");
			String busca = request.getParameter("nome");
			ArrayList<NotaFiscal> lstNotaFiscal = null;
			if (busca.equals("")) {
				try {
					lstNotaFiscal = entradaBO.listar();

				} catch (SQLException e) {
					request.setAttribute("msg", "Não foi Possível Listar Produtos!");
				}

			} else {
				try {
					lstNotaFiscal = entradaBO.pesquisarNotaFiscal(busca, parametro);
				} catch (SQLException e) {
					request.setAttribute("msg", "Não foi Possível Listar a Nota Fiscal!");
				}
			}
			if (!lstNotaFiscal.isEmpty()) {
				request.setAttribute("lstNotaFiscal", lstNotaFiscal);
				// RequestDispatcher destino = request.getRequestDispatcher("lstprodutos.jsp");
				// destino.forward(request, response);
			} else {
				request.setAttribute("msg", "Nenhuma Nota Fiscal foi Encontrada com o Parâmetro informado!");
			}
			request.getRequestDispatcher("lstentrada.jsp").forward(request, response);
			return;
		} else if (acao.equals("editar")) {
			int id = Integer.parseInt(request.getParameter("codigo"));
			try {
				request.setAttribute("objNotaFiscal", new EntradaBusiness().getNotaFiscalById(id));
				request.getRequestDispatcher("frmnotafiscal.jsp").forward(request, response);
			} catch (SQLException e) {
				request.setAttribute("msg", "Erro ao carregar a Nota Fiscal!");
				request.getRequestDispatcher("lstentrada.jsp").forward(request, response);

			}

		} else if (acao.equals("montar")) {
			try {
				request.getSession().setAttribute("objEntrada", montarEntrada(request));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getRequestDispatcher("frmfinalizarentrada.jsp").forward(request, response);
		} else if (acao.equals("cancelar")) {
			request.getSession().setAttribute("embalagem", null);
			request.getSession().removeAttribute("ValorTotal");
			request.getRequestDispatcher("frmentrada.jsp").forward(request, response);
		} else if ("finalizar".equals(acao)) {
			String vT = request.getParameter("valorTotal");
			double valorTotal = Double.parseDouble(vT.replace(',', '.'));

			if (valorTotal != 0) {
				try {
					if (!finalizarNotaFiscal(request)) {
						request.setAttribute("msg",
								"Ocorreu um erro ao tentar finalizar a Venda, Por Favor Tente Novamente ou Procure o Administrador do Sistema!");
					} else {
						request.getSession().removeAttribute("notaFiscal");
						request.getSession().removeAttribute("embalagem");
						// request.getSession().removeAttribute("valorNota");
						request.setAttribute("msg", "A Entrada foi concluída com sucesso!");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				request.setAttribute("msg", "A Lista de Itens está Vazia, Por Favor Adicione Itens à  Venda!");
				request.setAttribute("embalagem", null);
			}
			request.getRequestDispatcher("frmentrada.jsp").forward(request, response);
		} else {

			Embalagem embalagem = (Embalagem) request.getSession().getAttribute("embalagem");
			if (embalagem == null) {
				embalagem = new Embalagem();
			}

			String codigo = request.getParameter("codigo") == null ? "0" : request.getParameter("codigo");
			Entrada item = new Entrada();

			try {
				item.setProduto(new ProdutoBusiness().getProdutoByCod(codigo));
			} catch (SQLException e) {
				request.setAttribute("msg", "Erro ao tentar localizar o Produto informado, Por Favor Tente Novamente!");
				if (embalagem.getItens().isEmpty()) {
					embalagem = null;
					request.getSession().setAttribute("embalagem", embalagem);
				}
				request.getRequestDispatcher("frmentrada.jsp").forward(request, response);
				return;
			}

			if (acao.equals("adicionar")) {
				if (item.getProduto() != null) {
					embalagem.adicionar(item);
				} else {
					request.setAttribute("msg",
							"O Sistema não Localizou o Produto informado, Por Favor Tente Novamente!");
					if (embalagem.getItens().isEmpty()) {
						embalagem = null;
						request.getSession().setAttribute("embalagem", embalagem);
					}
					request.getRequestDispatcher("frmentrada.jsp").forward(request, response);
					return;
				}
			}
			if ("remover".equals(acao)) {
				embalagem.remover(item);
				if (!embalagem.isPossuiAlgumItem()) {
					embalagem = null;
					request.getSession().setAttribute("embalagem", embalagem);
					request.getRequestDispatcher("frmentrada.jsp").forward(request, response);
					return;
				}

			}
			if ("alterar".equals(acao)) {
				int quantidade = request.getParameter("qtd" + codigo) == null ? 0
						: Integer.parseInt((String) request.getParameter("qtd" + codigo));
				String vF = request.getParameter("valorFrete");
				String oD = request.getParameter("outrasDispesas");

				/*
				 * double valorFrete = Double.parseDouble(vF.replace(',', '.')); double
				 * outrasDispesas = Double.parseDouble(oD.replace(',', '.'));
				 */
				String vletd;
				vletd = request.getParameter("valorEntrada");
				double valorEntrada = Double.parseDouble(vletd.replace(',', '.'));
				item.setValorTotal(valorEntrada * quantidade);
				item.setValorEntrada(item.getValorTotal() / quantidade);
				item.setQuantidade(quantidade);
				embalagem.altera(item);
				List<Entrada> aux = new ArrayList<Entrada>(embalagem.getItens());
				Collections.sort(aux);
			}
			request.getSession().setAttribute("embalagem", embalagem);
			request.getSession().setAttribute("valorTotal", embalagem.getValor());
			request.getSession().setAttribute("quantidade", item.getQuantidade());
			request.getRequestDispatcher("frmentrada.jsp").forward(request, response);

		}

	}

	private NotaFiscal montarEntrada(HttpServletRequest request) throws SQLException {
		FornecedorBusiness fornecedorBO = new FornecedorBusiness();
		NotaFiscal notafiscal = new NotaFiscal();
		Entrada entrada = new Entrada();

		Embalagem embalagem = (Embalagem) request.getSession().getAttribute("embalagem");

		notafiscal.setDtNota(new java.util.Date(System.currentTimeMillis()));
		notafiscal.setItens(embalagem.getItens());
		request.setAttribute("lstFornecedores", fornecedorBO.listar());

		String vT = request.getParameter("valorTotal");
		double vtnf = Double.parseDouble(vT.replace(",","."));
		notafiscal.setValorTotal(request.getParameter("valorTotal"));
		String qtde = request.getParameter("quantidade");
		
		// quantidade bugada, sempre apaga o valor antes de entrar na condição abaixo.
		if (request.getParameter("codNf") != null) {
			notafiscal.setQuantidade(notafiscal.getItens().size());
			Fornecedor fornecedor = new Fornecedor();

			// int idFornecedor = Integer.parseInt(request.getParameter("idFornecedor"));
			// fornecedorBO.getFornecedorById(idFornecedor)
			fornecedor.setIdFornecedor(Integer.parseInt(request.getParameter("idFornecedor")));
			notafiscal.setFornecedor(fornecedor);
			notafiscal.setCodNf(request.getParameter("codNf"));

			notafiscal.setValorFrete(request.getParameter("valorFrete"));
			
			
			// notafiscal.setValorTotal(Double.parseDouble(request.getParameter("valorTotalNota")));
			notafiscal.setOutrasDispesas(request.getParameter("outrasDispesas"));
			notafiscal.setObservacao(request.getParameter("observacao"));
		}
		return notafiscal;
	}

	private NotaFiscal finalizarEntrada(HttpServletRequest request) throws SQLException {
		FornecedorBusiness fornecedorBO = new FornecedorBusiness();
		NotaFiscal notafiscal = new NotaFiscal();
		Entrada entrada = new Entrada();

		Embalagem embalagem = (Embalagem) request.getSession().getAttribute("embalagem");
		/*
		notafiscal.setDtNota(new java.util.Date(System.currentTimeMillis()));
		notafiscal.setItens(embalagem.getItens());
		request.setAttribute("lstFornecedores", fornecedorBO.listar());

		notafiscal.setValorTotal(request.getParameter("valorTotalNota"));
		String qtde = request.getParameter("quantidade");*/

		notafiscal.setQuantidade(notafiscal.getItens().size());
		Fornecedor fornecedor = new Fornecedor();

		// int idFornecedor = Integer.parseInt(request.getParameter("idFornecedor"));
		// fornecedorBO.getFornecedorById(idFornecedor)
		fornecedor.setIdFornecedor(Integer.parseInt(request.getParameter("idFornecedor")));
		notafiscal.setFornecedor(fornecedor);
		notafiscal.setCodNf(request.getParameter("codNf"));

		notafiscal.setValorFrete(request.getParameter("valorFrete"));
		String vtn = request.getParameter("valorTotalNota");
		double vlnf = Double.parseDouble(vtn.replace(',', '.'));
		notafiscal.setValorTotal(request.getParameter("valorTotalNota"));
		// notafiscal.setValorTotal(Double.parseDouble(request.getParameter("valorTotalNota")));
		notafiscal.setOutrasDispesas(request.getParameter("outrasDispesas"));
		notafiscal.setObservacao(request.getParameter("observacao"));
		return notafiscal;
	}

	private boolean finalizarNotaFiscal(HttpServletRequest request) throws SQLException {
		NotaFiscal notafiscal = montarEntrada(request);
		// notafiscal.setDesconto(Double.parseDouble(desconto));

		try {
			new EntradaBusiness().incluir(notafiscal);
			for (Entrada item : notafiscal.getItens()) {
				new ProdutoBusiness().atualizaEstoque(item.getProduto().getIdProduto(), item.getQuantidade() * (+1));
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
