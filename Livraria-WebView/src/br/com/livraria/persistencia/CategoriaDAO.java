package br.com.livraria.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoriaDAO {
	
	Connection conexao;
	
	public CategoriaDAO() throws ClassNotFoundException {
		conexao = new ConnectionFactory().getConnection();
	}
	
	public ResultSet listar() throws SQLException {
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT * FROM categoria");
		
		PreparedStatement st = this.conexao.prepareStatement(sql.toString());
		
		ResultSet rset  = st.executeQuery();
		
		return rset;
	}

}
