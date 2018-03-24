package br.com.livraria.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.livraria.bean.Fornecedor;

public class FornecedorDAO {

	Connection conexao;
	
	public FornecedorDAO() {
		try {
			conexao = new ConnectionFactory().getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void incluir(Fornecedor fornecedor, boolean isCpf) throws SQLException {
		
		StringBuffer sql = new StringBuffer();
		if (isCpf == true) {
			sql.append("INSERT INTO fornecedor(nome, cpf, endereco, ")
			.append("cidade, uf, telefone, email, descricao) ")
			.append("VALUES (?,?,?,?,?,?,?,?)");
			
			PreparedStatement ps = this.conexao.prepareStatement(sql.toString());
			
			int i=1;
			ps.setString(i++, fornecedor.getNome());
			ps.setString(i++, fornecedor.getCpf());
			ps.setString(i++, fornecedor.getEndereco());
			ps.setString(i++, fornecedor.getCidade());
			ps.setString(i++, fornecedor.getUf());
			ps.setString(i++, fornecedor.getTelefone());
			ps.setString(i++, fornecedor.getEmail());
			ps.setString(i++, fornecedor.getDescricao());
			System.out.println(ps);
			ps.executeUpdate();
			
		} else {
			sql.append("INSERT INTO fornecedor(nome, cnpj, endereco, ")
			.append("cidade, uf, telefone, email, descricao) ")
			.append("VALUES (?,?,?,?,?,?,?,?)");
			
			PreparedStatement ps = this.conexao.prepareStatement(sql.toString());
			
			int i=1;
			ps.setString(i++, fornecedor.getNome());
			ps.setString(i++, fornecedor.getCnpj());
			ps.setString(i++, fornecedor.getEndereco());
			ps.setString(i++, fornecedor.getCidade());
			ps.setString(i++, fornecedor.getUf());
			ps.setString(i++, fornecedor.getTelefone());
			ps.setString(i++, fornecedor.getEmail());
			ps.setString(i++,  fornecedor.getDescricao());
			System.out.println(ps);
			ps.executeUpdate();
		}
	}

	public ResultSet pesquisarPorNome(String busca) throws SQLException {
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT * FROM fornecedor ")
		.append("where fornecedor.nome = ?");
		
		
		PreparedStatement st = this.conexao.prepareStatement(sql.toString());
		st.setString(1, busca);
		System.out.println(st.toString());
		ResultSet rset  = st.executeQuery();
		return rset;
	}

	public ResultSet pesquisarPorCPF(String busca) throws SQLException{
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT * FROM fornecedor ")
		.append("where fornecedor.cpf = ?");
		
		
		PreparedStatement st = this.conexao.prepareStatement(sql.toString());
		st.setString(1, busca);
		System.out.println(st.toString());
		ResultSet rset  = st.executeQuery();
		return rset;
	}

	public ResultSet pesquisarPorCNPJ(String busca) throws SQLException {
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT * FROM fornecedor ")
		.append("where fornecedor.cnpj = ?");
		
		
		PreparedStatement st = this.conexao.prepareStatement(sql.toString());
		st.setString(1, busca);
		System.out.println(st.toString());
		ResultSet rset  = st.executeQuery();
		return rset;
		
	}

	public ResultSet listar() throws SQLException {
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT * FROM fornecedor ");
		
		PreparedStatement st = this.conexao.prepareStatement(sql.toString());
		
		ResultSet rset  = st.executeQuery();
		
		return rset;
	}
	
	public ResultSet getFornecedorById(int id) throws SQLException {
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT * FROM fornecedor f ")
		.append("where f.idFornecedor = ?");
		
		PreparedStatement st = this.conexao.prepareStatement(sql.toString());
		st.setInt(1, id);
		System.out.println(st.toString());
		ResultSet rset = st.executeQuery();
		
		return rset;
	}

	public void editar(Fornecedor fornecedor, boolean isCpf) throws SQLException {
		StringBuffer sql = new StringBuffer();
		if (isCpf == true) {
			sql.append("UPDATE fornecedor SET nome = ?, ")
			.append("cpf = ?, endereco = ?, cidade = ?, uf = ?, telefone = ?, email = ?, descricao = ? where idFornecedor = ?");
			
			PreparedStatement ps = this.conexao.prepareStatement(sql.toString());
			int i=1;
			
			ps.setString(i++, fornecedor.getNome());
			ps.setString(i++, fornecedor.getCpf());
			ps.setString(i++, fornecedor.getEndereco());
			ps.setString(i++, fornecedor.getCidade());
			ps.setString(i++, fornecedor.getUf());
			ps.setString(i++, fornecedor.getTelefone());
			ps.setString(i++, fornecedor.getEmail());
			ps.setString(i++, fornecedor.getDescricao());
			ps.setInt(i++, fornecedor.getIdFornecedor());
			ps.executeUpdate();
		} else {
			sql.append("UPDATE fornecedor SET nome = ?, ")
			.append("cnpj = ?, endereco = ?, cidade = ?, uf = ?, telefone = ?, email = ?, descricao = ? where idFornecedor = ?");
			
			PreparedStatement ps = this.conexao.prepareStatement(sql.toString());
			int i=1;
			
			ps.setString(i++, fornecedor.getNome());
			ps.setString(i++, fornecedor.getCnpj());
			ps.setString(i++, fornecedor.getEndereco());
			ps.setString(i++, fornecedor.getCidade());
			ps.setString(i++, fornecedor.getUf());
			ps.setString(i++, fornecedor.getTelefone());
			ps.setString(i++, fornecedor.getEmail());
			ps.setString(i++, fornecedor.getDescricao());
			ps.setInt(i++, fornecedor.getIdFornecedor());
			ps.executeUpdate();

		}
		
	}
}
