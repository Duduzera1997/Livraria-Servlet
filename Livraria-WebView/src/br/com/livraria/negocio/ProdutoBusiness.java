package br.com.livraria.negocio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import br.com.livraria.bean.Categoria;
import br.com.livraria.bean.Produto;
import br.com.livraria.persistencia.ProdutoDAO;

public class ProdutoBusiness {
	
	ProdutoDAO dao;
	
	public ProdutoBusiness() {
		this.dao = new ProdutoDAO();
	}
	
	
	private ArrayList<Produto> mapearProdutos(ResultSet rset) throws SQLException {
		ArrayList<Produto> lstProduto = new ArrayList<Produto>();
		if (rset!=null) {
			while (rset.next()) {
				Produto produto = new Produto();
				Categoria categoria = new Categoria();
				
				produto.setIdProduto(rset.getInt("idProduto"));
				produto.setNome(rset.getString("nome"));
				produto.setCodigo(rset.getString("codigo"));
				produto.setEstoque(rset.getInt("estoque"));
				produto.setMinimo(rset.getInt("minimo"));
				produto.setValorVenda(rset.getDouble("valorVenda"));
				produto.setDescricao(rset.getString("descricao"));
				
				categoria.setIdCategoria(rset.getInt("idCategoria"));
				categoria.setNome(rset.getString("categoria"));
				categoria.setDescricao(rset.getString("dsc"));
				
				produto.setCategoria(categoria);
				
				lstProduto.add(produto);
				
			}
		}
		
		return lstProduto;
	}
	
	private Produto mapearProduto(ResultSet rset) throws SQLException {
		Produto produto = new Produto();
		if (rset!=null) {
			if (rset.next()) {
				
				Categoria categoria = new Categoria();
				
				produto.setIdProduto(rset.getInt("idProduto"));
				produto.setNome(rset.getString("nome"));
				produto.setCodigo(rset.getString("codigo"));
				produto.setEstoque(rset.getInt("minimo"));
				produto.setValorVenda(rset.getDouble("valorVenda"));
				produto.setDescricao(rset.getString("descricao"));
				
				categoria.setIdCategoria(rset.getInt("idCategoria"));
				categoria.setNome(rset.getString("categoria"));
				categoria.setDescricao(rset.getString("dsc"));
				
				produto.setCategoria(categoria);
				
			}
		}
		
		return produto;
	}
	
	public ArrayList<Produto> listar() throws SQLException {
		ResultSet rset = this.dao.listar();
		return mapearProdutos(rset);
		
	}
	
	public ArrayList<Produto> pesquisarProduto(String busca, String parametro) throws SQLException {
			ResultSet rset;
			if (parametro.equals("C" )) {
				rset = this.dao.pesquisarPorCodigo(busca);
				
			} else if (parametro.equals("N") ) {
				rset = this.dao.pesquisarPorNome(busca);
				
			} else if (parametro.equals("P")) {
				rset = this.dao.pesquisarPorPreco(busca);
				
			} else {
				rset = this.dao.listar();
			}
			return mapearProdutos(rset);
		
		
	}

	public void incluir(Produto produto) throws SQLException {
		this.dao.incluir(produto);
		
	}
	
	public void alterar (Produto produto, Categoria categoria) throws SQLException {
		this.dao.alterar(produto, categoria);
	}
	
	public Produto getProdutoById(int id) throws SQLException {
		ResultSet rset = this.dao.getProdutoById(id);
		return mapearProduto(rset);
	}
	
	public void editar(Produto produto) throws SQLException {
		this.dao.editar(produto);
	}
	
	public Produto getProdutoByCod(String cod) throws SQLException{
		ResultSet rs = this.dao.getProdutoByCod(cod);
		return mapearProduto(rs);
	}
	
	public void atualizaEstoque(int idProduto, int quantidade) throws Exception{
		this.dao.atualizaEstoque(idProduto, quantidade);
	}
}
