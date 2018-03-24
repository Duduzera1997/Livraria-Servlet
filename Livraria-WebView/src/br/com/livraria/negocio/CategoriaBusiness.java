package br.com.livraria.negocio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.livraria.bean.Categoria;
import br.com.livraria.persistencia.CategoriaDAO;

public class CategoriaBusiness {
	
	CategoriaDAO dao;
	
	public CategoriaBusiness() throws ClassNotFoundException {
		this.dao = new CategoriaDAO();
	}
	
	public ArrayList<Categoria> listar() throws SQLException {
		ArrayList<Categoria> lstCategoria = new ArrayList<Categoria>();
		ResultSet rset = this.dao.listar();
	
		
		while(rset.next()) {
			Categoria categoria = new Categoria();
			categoria.setIdCategoria(rset.getInt("idCategoria"));
			categoria.setNome(rset.getString("categoria"));
			categoria.setDescricao(rset.getString("descricao"));
			
			lstCategoria.add(categoria);
		}
		
		return lstCategoria;
	}

}
