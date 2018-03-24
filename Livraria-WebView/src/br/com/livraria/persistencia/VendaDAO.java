package br.com.livraria.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.livraria.bean.Pedido;
import br.com.livraria.bean.Saida;

public class VendaDAO {
	
	Connection conexao;
	
	public VendaDAO() {
		try {
			this.conexao = new ConnectionFactory().getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void incluirPedido(Pedido pedido) throws SQLException{
		StringBuffer sql = new StringBuffer();
		
		 sql.append("INSERT INTO PEDIDO (dataVenda, valorTotal, observacao, desconto) VALUES (?, ?, ?, ?)");

		 PreparedStatement ps = this.conexao.prepareStatement(sql.toString());
	           
		 ps.setDate(1, new java.sql.Date(pedido.getDtNota().getTime()));
		 ps.setDouble(2, pedido.getValorTotal());
		 ps.setString(3, pedido.getObservacao());
		 ps.setDouble(4, pedido.getDesconto());

	     ps.executeUpdate();
	     this.incluirItemPedido(pedido);
	     
	}
	
	private void incluirItemPedido(Pedido pedido) throws SQLException{
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO saida(idProduto, idPedido, quantidade, desconto, valor) VALUES (?, ?, ?, ?, ?)");

	    PreparedStatement pstmtItem = this.conexao.prepareStatement(sql.toString());
        for (Saida item : pedido.getItens()) {
            pstmtItem.setInt(1, item.getProduto().getIdProduto());
            pstmtItem.setInt(2, maxCodPedido());
            pstmtItem.setInt(3, item.getQuantidade());
            pstmtItem.setDouble(4, item.getDesconto());
            pstmtItem.setDouble(5, item.getProduto().getValorVenda());

            pstmtItem.executeUpdate();
        }
	}
		
    private int maxCodPedido() throws SQLException {
        int iRet = 0;
        PreparedStatement ps = this.conexao.prepareStatement("select max(idPedido) from pedido");
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
}
