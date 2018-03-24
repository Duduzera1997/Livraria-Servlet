package br.com.livraria.negocio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



import br.com.livraria.bean.Entrada;
import br.com.livraria.bean.Fornecedor;
import br.com.livraria.bean.NotaFiscal;
import br.com.livraria.bean.Produto;
import br.com.livraria.persistencia.EntradaDAO;

public class EntradaBusiness {
	EntradaDAO dao;
	
	public EntradaBusiness() {
		this.dao = new EntradaDAO();
	}

	public void incluir(NotaFiscal notafiscal) throws Exception {
		this.dao.incluirNotaFiscal(notafiscal);
	}

	public ArrayList<NotaFiscal> pesquisarNotaFiscal(String busca, String parametro) throws SQLException {
		ResultSet rset = null;
		if (parametro.equals("C" )) {
			rset = this.dao.pesquisarPorCodigo(busca);
			
		} else {
			rset = this.dao.listar();
		}
		
		return mapearNotaFiscal(rset);
	
	
	}

	private ArrayList<NotaFiscal> mapearNotaFiscal(ResultSet rset) throws SQLException {
		ArrayList<NotaFiscal> lstNotaFiscal = new ArrayList<NotaFiscal>();
		if (rset!=null) {
			while (rset.next()) {
				//Entrada entrada= new Entrada();
				NotaFiscal nf = new NotaFiscal();
				//entrada.setIdEntrada(rset.getInt("idEntrada"));
				//entrada.setQuantidade(rset.getInt("quantidade"));
				//entrada.setProduto(entrada.getProduto().getIdProduto());
				nf.setCodNf(rset.getString("codigo"));
				nf.setDtNota(rset.getDate("data"));
				nf.setIdNotaFiscal(rset.getInt("idNotaFiscal"));
				nf.setValorFrete(rset.getString("valorFrete"));
				nf.setValorTotal(rset.getString("valorTotal"));
				nf.setOutrasDispesas(rset.getString("outrasDispesas"));
				//nf.setFornecedor(fornecedor);
				
				lstNotaFiscal.add(nf);
				
			}
		}
		return lstNotaFiscal;
	}
	
	private NotaFiscal mapearNf(ResultSet rset) throws SQLException  {
		NotaFiscal nf = new NotaFiscal();
		if (rset!=null) {
			while (rset.next()) {
				Fornecedor fornecedor = new Fornecedor();
				Entrada entrada= new Entrada();
				
				fornecedor.setIdFornecedor(rset.getInt("fkFornecedor"));
				entrada.setIdEntrada(rset.getInt("idEntrada"));
				entrada.setQuantidade(rset.getInt("quantidade"));
				//entrada.setProduto(entrada.getProduto().getIdProduto());
				nf.setCodNf(rset.getString("codigo"));
				nf.setDtNota(rset.getDate("data"));
				nf.setIdNotaFiscal(rset.getInt("idNotaFiscal"));
				nf.setValorFrete(rset.getString("valorFrete"));
				nf.setValorTotal(rset.getString("valorTotal"));
				nf.setOutrasDispesas(rset.getString("outrasDispesas"));
				//nf.setFornecedor(fornecedor);
				
				}
		}	
		return nf;
	}
	
	public ArrayList<NotaFiscal> listar() throws SQLException {
		ResultSet rset = this.dao.listar();
		return mapearNotaFiscal(rset);
		}

	public NotaFiscal getNotaFiscalById(int id) throws SQLException {
		ResultSet rset = this.dao.getNotaFiscalById(id);
		return mapearNf(rset);
	}
}
