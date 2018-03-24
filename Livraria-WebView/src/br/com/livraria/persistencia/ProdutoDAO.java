package br.com.livraria.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.livraria.bean.Categoria;
import br.com.livraria.bean.Produto;

public class ProdutoDAO {
	
	Connection conexao;
	
	public ProdutoDAO() {
		try {
			conexao = new ConnectionFactory().getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void incluir (Produto produto) throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO produto(nome, codigo, estoque, ")
		.append("minimo, valorVenda, descricao, idCategoria) ")
		.append("VALUES (?,?,?,?,?,?,?)");
		
		PreparedStatement ps = this.conexao.prepareStatement(sql.toString());
		
		int i=1;
		
		ps.setString(i++, produto.getNome());
		ps.setString(i++, produto.getCodigo());
		ps.setInt(i++, produto.getEstoque());
		ps.setInt(i++, produto.getMinimo());
		ps.setDouble(i++, produto.getValorVenda());
		ps.setString(i++, produto.getDescricao());
		ps.setInt(i++, produto.getCategoria().getIdCategoria());
		System.out.println(ps.toString());
		ps.executeUpdate();
		
	}
	
	public ResultSet listar() throws SQLException {
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT p.*, c.categoria, c.descricao as dsc FROM produto as p ")
		.append("INNER JOIN categoria as c ")
		.append("ON p.idCategoria = c.idCategoria");
		
		
		PreparedStatement st = this.conexao.prepareStatement(sql.toString());
		
		ResultSet rset  = st.executeQuery();
		
		return rset;
	}
	
	public void alterar(Produto produto, Categoria categoria) throws SQLException {
		
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO produto(nome, codigo, estoque, ")
		.append("minimo, valorVenda, descricao, idCategoria) ")
		.append("VALUES (?,?,?,?,?,?,?)");
		
		PreparedStatement ps = this.conexao.prepareStatement(sql.toString());
		
		int i=1;
		
		ps.setString(i++, produto.getNome());
		ps.setString(i++, produto.getCodigo());
		ps.setInt(i++, produto.getEstoque());
		ps.setInt(i++, produto.getMinimo());
		ps.setDouble(i++, produto.getValorVenda());
		ps.setString(i++, produto.getDescricao());
		ps.setInt(i++, produto.getCategoria().getIdCategoria());
		ps.setString(i++, categoria.getNome());
		ps.setString(i++, categoria.getDescricao());
		System.out.println(ps.toString());
		ps.executeUpdate();
		
	}
	
	// Pode-se passar parametro dentro destes métodos abaixo e realizar condições IF dentro dele, porém foi feito assim para praticar;
	public ResultSet pesquisarPorCodigo(String busca) throws SQLException {
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT p.*, c.categoria, c.descricao as dsc FROM produto as p ")
		.append("INNER JOIN categoria as c ")
		.append("ON p.idCategoria = c.idCategoria ")
		.append("where p.codigo = ?");
		
		
		PreparedStatement st = this.conexao.prepareStatement(sql.toString());
		st.setString(1, busca);
		System.out.println(st.toString());
		ResultSet rset  = st.executeQuery();
		
		return rset;
	}
	
	public ResultSet pesquisarPorNome(String busca) throws SQLException {
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT p.*, c.categoria, c.descricao as dsc FROM produto as p ")
		.append("INNER JOIN categoria as c ")
		.append("ON p.idCategoria = c.idCategoria ")
		.append("where p.nome = ?");
		
		
		PreparedStatement st = this.conexao.prepareStatement(sql.toString());
		st.setString(1, busca);
		System.out.println(st.toString());
		ResultSet rset  = st.executeQuery();
		
		return rset;
	}
	
	public ResultSet pesquisarPorPreco(String busca) throws SQLException {
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT p.*, c.categoria, c.descricao as dsc FROM produto as p ")
		.append("INNER JOIN categoria as c ")
		.append("ON p.idCategoria = c.idCategoria ")
		.append("where p.valorVenda = ?");
		
		
		PreparedStatement st = this.conexao.prepareStatement(sql.toString());
		st.setString(1, busca);
		System.out.println(st.toString());
		ResultSet rset  = st.executeQuery();
		
		return rset;
	}
	
	public ResultSet getProdutoById(int id) throws SQLException {
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT p.*, c.categoria, c.descricao as dsc FROM produto as p ")
		.append("INNER JOIN categoria as c ")
		.append("ON p.idCategoria = c.idCategoria ")
		.append("where p.idProduto = ?");
		
		PreparedStatement st = this.conexao.prepareStatement(sql.toString());
		st.setInt(1, id);
		System.out.println(st.toString());
		ResultSet rset = st.executeQuery();
		
		return rset;
		
	}
	
	public void editar(Produto produto) throws SQLException {
		StringBuffer sql = new StringBuffer();
		
		sql.append("UPDATE produto SET nome = ?, ")
		.append("codigo = ?, estoque = ?, minimo = ?, valorVenda = ?, descricao = ?, idCategoria = ? where idProduto = ?");
		
		PreparedStatement ps = this.conexao.prepareStatement(sql.toString());
		int i=1;
		
		
		ps.setString(i++, produto.getNome());
		ps.setString(i++, produto.getCodigo());
		ps.setInt(i++, produto.getEstoque());
		ps.setInt(i++, produto.getMinimo());
		ps.setDouble(i++, produto.getValorVenda());
		ps.setString(i++, produto.getDescricao());
		ps.setInt(i++, produto.getCategoria().getIdCategoria());
		ps.setInt(i++, produto.getIdProduto());
		System.out.println(ps.toString());
		ps.executeUpdate();
	}
	
	public ResultSet getProdutoByCod(String cod) throws SQLException{
		StringBuffer sql = new StringBuffer();
		
		sql.append("select p.*, c.categoria, c.descricao dsc from produto p ")
		   .append("inner join categoria c ")
		   .append("on p.idCategoria = c.idCategoria ")
		   .append("where p.codigo = ?");
		
		PreparedStatement st = this.conexao.prepareStatement(sql.toString());
		st.setString(1, cod);
		System.out.println(st.toString());
		ResultSet rset = st.executeQuery();
		
		return rset;
	}

public void atualizaEstoque(int idProduto, int quantidade) throws SQLException{
		StringBuffer sql = new StringBuffer();
		
		sql.append("UPDATE produto SET estoque = estoque + ? ")
        .append("WHERE idProduto = ?");

        PreparedStatement ps = this.conexao.prepareStatement(sql.toString());
        ps.setInt(1, quantidade);
        ps.setInt(2, idProduto);
        System.out.println(ps);
        ps.executeUpdate();
	}
	
}
