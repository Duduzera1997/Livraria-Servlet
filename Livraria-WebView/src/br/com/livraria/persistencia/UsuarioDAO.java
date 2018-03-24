package br.com.livraria.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.livraria.bean.Usuario;

	public class UsuarioDAO {
		Connection conexao;
		
		public UsuarioDAO() {
			try {
				conexao = new ConnectionFactory().getConnection();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}



	public ResultSet getUsuario (String usuario, String senha) throws SQLException {
		
 		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT * from usuario user where user.login = ? AND user.senha = ?");
		
		
		PreparedStatement st = this.conexao.prepareStatement(sql.toString());
		st.setString(1, usuario);
		st.setString(2, senha);
		System.out.println(st.toString());
		ResultSet rset  = st.executeQuery();
		
		
		
		return rset;
	}
}