package br.com.livraria.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.livraria.bean.Entrada;
import br.com.livraria.bean.NotaFiscal;
import br.com.livraria.bean.Pedido;
import br.com.livraria.bean.Saida;

public class EntradaDAO {
	
	Connection conexao;
	
	public EntradaDAO() {
		try {
			this.conexao = new ConnectionFactory().getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void incluirNotaFiscal(NotaFiscal notafiscal) throws SQLException{
		StringBuffer sql = new StringBuffer();
		
		 sql.append("INSERT INTO notafiscal (data, codigo, valorFrete, outrasDispesas, valorTotal, quantidade, fkFornecedor, observacao) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

		 PreparedStatement ps = this.conexao.prepareStatement(sql.toString());
	     
		 int i = 1;
		 ps.setDate(i++, new java.sql.Date(notafiscal.getDtNota().getTime()));
		 ps.setString(i++, notafiscal.getCodNf());
		 ps.setString(i++, notafiscal.getValorFrete());
		 ps.setString(i++, notafiscal.getOutrasDispesas());
		 ps.setString(i++, notafiscal.getValorTotal());
		 ps.setInt(i++, notafiscal.getQuantidade());
		 ps.setInt(i++, notafiscal.getFornecedor());
		 ps.setString(i++, notafiscal.getObservacao());
		 System.out.println(ps);
	     ps.executeUpdate();
	     this.incluirItemNotaFiscal(notafiscal);
	     
	}
	
	private void incluirItemNotaFiscal(NotaFiscal notafiscal) throws SQLException{
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO entrada(fkProduto, fkNotaFiscal, quantidade, fkFornecedor , valorEntrada) VALUES (?, ?, ?, ?, ?)");

	    PreparedStatement pstmtItem = this.conexao.prepareStatement(sql.toString());
        for (Entrada item : notafiscal.getItens()) {
        	int i = 1;
            pstmtItem.setInt(i++, item.getProduto().getIdProduto());
            pstmtItem.setInt(i++, maxCodNotaFiscal());
            pstmtItem.setInt(i++, item.getQuantidade());
            pstmtItem.setInt(i++, notafiscal.getFornecedor());
            pstmtItem.setDouble(i++, item.getValorEntrada());

            pstmtItem.executeUpdate();
        }
	}
		
    private int maxCodNotaFiscal() throws SQLException {
        int iRet = 0;
        PreparedStatement ps = this.conexao.prepareStatement("select max(idNotaFiscal) from notafiscal");
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            iRet = rs.getInt(1);
        }
        return iRet;
    }
    public ResultSet getProduto(String cod) throws SQLException{
		StringBuffer sql = new StringBuffer();
		
		sql.append("select p.*, c.categoria, c.descricao descCategoria from produto p ")
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

        ps.executeUpdate();
	}
    
	public ResultSet pesquisarPorCodigo(String busca) throws SQLException {
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT notafiscal.* FROM notafiscal left join entrada on fkNotafiscal = notafiscal.idNotaFiscal ")
		.append("where notafiscal.codigo = ?");
		
		
		PreparedStatement st = this.conexao.prepareStatement(sql.toString());
		st.setString(1, busca);
		System.out.println(st.toString());
		ResultSet rset  = st.executeQuery();
		
		return rset;
	}

	public ResultSet listar() throws SQLException {
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT notafiscal.* FROM notafiscal left join entrada on fkNotafiscal = notafiscal.idNotaFiscal");
		
		
		PreparedStatement st = this.conexao.prepareStatement(sql.toString());
		System.out.print(st);
		ResultSet rset  = st.executeQuery();
		
		return rset;
	}

	public ResultSet getNotaFiscalById(int id) throws SQLException {
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT * from notafiscal ")
		.append("where p.idNotaFiscal = ?");
		
		PreparedStatement st = this.conexao.prepareStatement(sql.toString());
		st.setInt(1, id);
		System.out.println(st.toString());
		ResultSet rset = st.executeQuery();
		
		return rset;

	}
}
