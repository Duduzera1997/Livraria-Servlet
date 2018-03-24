package br.com.livraria.negocio;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.livraria.bean.Produto;
import br.com.livraria.bean.Usuario;
import br.com.livraria.persistencia.UsuarioDAO;

public class UsuarioBusiness {
	
	UsuarioDAO dao;
	
	public UsuarioBusiness() {
		this.dao = new UsuarioDAO();
	}
	
	private Usuario mapearUsuario(ResultSet rset) throws SQLException {
		Usuario usuario = new Usuario();
		if (rset!=null) {
			if (rset.next()) {
				usuario.setIdUsuario(rset.getInt("idUsuario"));
				usuario.setLogin(rset.getString("login"));
				usuario.setSenha(rset.getString("senha"));
				usuario.setNome(rset.getString("nome"));
				usuario.setNivel(rset.getString("nivel"));
				usuario.setStatus(rset.getInt("status"));
			}
		}
		return usuario;
		
	}
	
	private static String criptografarSenha(String senha) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
        String s = hash.toString(16);
        if (s.length() % 2 != 0) {
            s = "0" + s;
        }
        return s;
    }
	
	public Usuario getUsuario(String usuario, String senha) throws SQLException {
		ResultSet rset = null;
		try {
			rset = this.dao.getUsuario(usuario, criptografarSenha(senha));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mapearUsuario(rset);
		
	}

}
