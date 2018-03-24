package br.com.livraria.negocio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import br.com.livraria.bean.Fornecedor;
import br.com.livraria.persistencia.FornecedorDAO;

public class FornecedorBusiness {
	
	FornecedorDAO dao;
	
	public FornecedorBusiness() {
		this.dao = new FornecedorDAO();
	}
	
	
	public void incluir(Fornecedor fornecedor, boolean isCpf) throws SQLException{
		this.dao.incluir(fornecedor, isCpf);
	}
	
	private ArrayList<Fornecedor> mapearFornecedores(ResultSet rset) throws SQLException {
		ArrayList<Fornecedor> lstFornecedores = new ArrayList<Fornecedor>();
		if (rset!=null) {
			while (rset.next()) {
				
				Fornecedor fornecedor = new Fornecedor();
				
				fornecedor.setIdFornecedor(rset.getInt("idFornecedor"));
				fornecedor.setNome(rset.getString("nome"));
				if (rset.getString("cpf") != null) {
					fornecedor.setCpf(rset.getString("cpf"));
					fornecedor.setCnpj("0");
				} else {
					fornecedor.setCnpj(rset.getString("cnpj"));
					fornecedor.setCpf("0");
				}
				fornecedor.setEndereco(rset.getString("endereco"));
				fornecedor.setCidade(rset.getString("cidade"));
				fornecedor.setUf(rset.getString("uf"));
				fornecedor.setTelefone(rset.getString("telefone"));
				fornecedor.setEmail(rset.getString("email"));
				fornecedor.setDescricao(rset.getString("descricao"));
				
				lstFornecedores.add(fornecedor);
				
			}
		}
		
		return lstFornecedores;
	}
	
	public ArrayList<Fornecedor> pesquisarFornecedor(String busca, String parametro) throws SQLException
	{
		ResultSet rset;
		if (parametro.equals("N")) {
			rset = this.dao.pesquisarPorNome(busca);
			
		} else if (parametro.equals("CPF")) {
			rset = this.dao.pesquisarPorCPF(busca);
			
		} else if (parametro.equals("CNPJ")) {
			rset = this.dao.pesquisarPorCNPJ(busca);
			
		} else {
			rset = this.dao.listar();
		}
		return mapearFornecedores(rset);
	
}


	public ArrayList<Fornecedor> listar() throws SQLException {
		ResultSet rset = this.dao.listar();
		return mapearFornecedores(rset);
	}

	private Fornecedor mapearFornecedor(ResultSet rset) throws SQLException {
		Fornecedor fornecedor = new Fornecedor();
		if (rset!=null) {
			if (rset.next()) {
				
				fornecedor.setIdFornecedor(rset.getInt("idFornecedor"));
				fornecedor.setNome(rset.getString("nome"));
				if (rset.getString("cpf") != null) {
					fornecedor.setCpf(rset.getString("cpf"));
					fornecedor.setCnpj("0");
				} else {
					fornecedor.setCnpj(rset.getString("cnpj"));
					fornecedor.setCpf("0");
				}
				fornecedor.setEndereco(rset.getString("endereco"));
				fornecedor.setCidade(rset.getString("cidade"));
				fornecedor.setUf(rset.getString("uf"));
				fornecedor.setTelefone(rset.getString("telefone"));
				fornecedor.setEmail(rset.getString("email"));
				fornecedor.setDescricao(rset.getString("descricao"));
				
			}
		}
		
		return fornecedor;
	}
	
	public Fornecedor getFornecedorById(int id) throws SQLException {
		ResultSet rset = this.dao.getFornecedorById(id);
		return mapearFornecedor(rset);
		
	}


	public void editar(Fornecedor fornecedor, boolean isCpf) throws SQLException {
		this.dao.editar(fornecedor, isCpf);
		
	}


}
