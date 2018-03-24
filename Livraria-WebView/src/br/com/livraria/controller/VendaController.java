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

import br.com.livraria.bean.Carrinho;
import br.com.livraria.bean.Pedido;
import br.com.livraria.bean.Saida;
import br.com.livraria.negocio.ProdutoBusiness;
import br.com.livraria.negocio.VendaBusiness;

public class VendaController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
    	String acao = request.getParameter("action");
        request.getSession().removeAttribute("msg");
        
        if (acao.equals("montar")) {
            request.getSession().setAttribute("objPedido", montarPedido(request));
            request.getRequestDispatcher("frmfinalizarvenda.jsp").forward(request, response);
        } else if (acao.equals("cancelar")) {
            request.getSession().setAttribute("carrinho", null);
            request.getSession().removeAttribute("valorVenda");
            request.getRequestDispatcher("frmvenda.jsp").forward(request, response);
        } else if ("finalizar".equals(acao)) {
                if (Double.parseDouble(request.getParameter("valorVenda")) != 0) {
                    if (!finalizarPedido(request)) {
                        request.setAttribute("msg", "Ocorreu um erro ao tentar finalizar a Venda, Por Favor Tente Novamente ou Procure o Administrador do Sistema!");
                    } else {
                        request.getSession().removeAttribute("pedido");
                        request.getSession().removeAttribute("carrinho");
                        request.getSession().removeAttribute("valorNota");
                        request.setAttribute("msg", "A Venda foi concluída com sucesso!");
                    }
                } else {
                    request.setAttribute("msg", "A Lista de Itens está Vazia, Por Favor Adicione Itens à  Venda!");
                    request.setAttribute("carrinho", null);
                }
                request.getRequestDispatcher("frmvenda.jsp").forward(request, response);
            } else {

        	 Carrinho carrinho = (Carrinho) request.getSession().getAttribute("carrinho");
             if (carrinho == null) {
                 carrinho = new Carrinho();
             }

            String codigo = request.getParameter("codigo") == null ? "0" : request.getParameter("codigo");
            Saida item = new Saida();

            try {
				item.setProduto(new ProdutoBusiness().getProdutoByCod(codigo));
			} catch (SQLException e) {
				request.setAttribute("msg", "Erro ao tentar localizar o Produto informado, Por Favor Tente Novamente!");
				if (carrinho.getItens().isEmpty()) {
                    carrinho = null;
                    request.getSession().setAttribute("carrinho", carrinho);
                }
                request.getRequestDispatcher("frmvenda.jsp").forward(request, response);
                return;
			}

            if (acao.equals("adicionar")) {
                if (item.getProduto() != null) {
                    carrinho.adicionar(item);
                } else {
                    request.setAttribute("msg", "O Sistema não Localizou o Produto informado, Por Favor Tente Novamente!");
                    if (carrinho.getItens().isEmpty()) {
                        carrinho = null;
                        request.getSession().setAttribute("carrinho", carrinho);
                    }
                    request.getRequestDispatcher("frmvenda.jsp").forward(request, response);
                    return;
                }
            }
            if ("remover".equals(acao)) {
                carrinho.remover(item);
                if (!carrinho.isPossuiAlgumItem()) {
                    carrinho = null;
                    request.getSession().setAttribute("carrinho", carrinho);
                    request.getRequestDispatcher("frmvenda.jsp").forward(request, response);
                    return;
                }

            }
            if ("alterar".equals(acao)) {
                int quantidade = request.getParameter("qtd" + codigo) == null ? 0 : Integer.parseInt((String) request.getParameter("qtd" + codigo));
                item.setValorVenda(quantidade * item.getProduto().getValorVenda());
                item.setQuantidade(quantidade);
                carrinho.altera(item);
                List<Saida> aux = new ArrayList<Saida>(carrinho.getItens());
                Collections.sort(aux);
            }
            request.getSession().setAttribute("carrinho", carrinho);
            request.getSession().setAttribute("valorVenda", carrinho.getValor());
            request.getRequestDispatcher("frmvenda.jsp").forward(request, response);
        }
	}
    
    private Pedido montarPedido(HttpServletRequest request) {
        Pedido pedido = new Pedido();

        Carrinho carrinho = (Carrinho) request.getSession().getAttribute("carrinho");
        pedido.setDtNota(new java.util.Date(System.currentTimeMillis()));
        pedido.setItens(carrinho.getItens());
        pedido.setValorTotal(Double.parseDouble(request.getParameter("valorVenda")));

        return pedido;
    }
    
    private boolean finalizarPedido(HttpServletRequest request) {      
        Pedido pedido = montarPedido(request);
        pedido.setObservacao(request.getParameter("observacao"));
        String desconto = request.getParameter("desconto");
        desconto = desconto.replace(",", ".");
        pedido.setDesconto(Double.parseDouble(desconto));

        try {
        	new VendaBusiness().incluir(pedido);
        	for (Saida item : pedido.getItens()) {
                new ProdutoBusiness().atualizaEstoque(item.getProduto().getIdProduto(), item.getQuantidade() * (-1));
            }
        	return true;
        }catch (Exception e) {
			return false;
        }
    }
}
