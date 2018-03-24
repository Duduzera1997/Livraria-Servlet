package br.com.livraria.bean;

import java.util.Date;
import java.util.List;

public class NotaFiscal {
		
		private int idNotaFiscal;
	 	private List<Entrada> itens;
	    private String observacao;
	    private Fornecedor fornecedor;
	    private Date dtNota;
	    private String codNf;
	    private String valorFrete;
	    private String outrasDispesas;
	    private String valorTotal;
	    private int quantidade;
	    
	    
	    
		public int getIdNotaFiscal() {
			return idNotaFiscal;
		}
		public void setIdNotaFiscal(int idNotaFiscal) {
			this.idNotaFiscal = idNotaFiscal;
		}
		public List<Entrada> getItens() {
			return itens;
		}
		public void setItens(List<Entrada> itens) {
			this.itens = itens;
		}
		public String getObservacao() {
			return observacao;
		}
		public void setObservacao(String observacao) {
			this.observacao = observacao;
		}
		public int getFornecedor() {
			return fornecedor.getIdFornecedor();
		}
		public void setFornecedor(Fornecedor fornecedor) {
			this.fornecedor = fornecedor;
		}
		public Date getDtNota() {
			return dtNota;
		}
		public void setDtNota(Date dtNota) {
			this.dtNota = dtNota;
		}
		public String getCodNf() {
			return codNf;
		}
		public void setCodNf(String codNf) {
			this.codNf = codNf;
		}
		public String getValorFrete() {
			return valorFrete;
		}
		public void setValorFrete(String valorFrete) {
			this.valorFrete = valorFrete;
		}
		public String getOutrasDispesas() {
			return outrasDispesas;
		}
		public void setOutrasDispesas(String outrasDispesas) {
			this.outrasDispesas = outrasDispesas;
		}
		public String getValorTotal() {
			return valorTotal;
		}
		public void setValorTotal(String valorTotal) {
			this.valorTotal = valorTotal;
		}
		
		public int getQuantidade() {
			return quantidade;
		}
		public void setQuantidade(int i) {
			this.quantidade = i;
		}
	    
	    
}