package br.com.livraria.negocio;

import br.com.livraria.bean.Pedido;
import br.com.livraria.persistencia.VendaDAO;

public class VendaBusiness {
	
	VendaDAO dao;
	
	public VendaBusiness() {
		this.dao = new VendaDAO();
	}

	public void incluir(Pedido pedido) throws Exception {
		this.dao.incluirPedido(pedido);
	}

}
