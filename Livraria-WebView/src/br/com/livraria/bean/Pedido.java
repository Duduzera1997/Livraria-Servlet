package br.com.livraria.bean;

import java.util.Date;
import java.util.List;

public class Pedido {

	 private List<Saida> itens;
	    private String observacao;
	    private double desconto;
	    private Date dtNota;
	    private double valorTotal;

	    public List<Saida> getItens() {
	        return itens;
	    }

	    public void setItens(List<Saida> itens) {
	        this.itens = itens;
	    }

	    public String getObservacao() {
	        return observacao;
	    }

	    public void setObservacao(String observacao) {
	        this.observacao = observacao;
	    }

	    public double getDesconto() {
	        return desconto;
	    }

	    public void setDesconto(double desconto) {
	        this.desconto = desconto;
	    }

		public Date getDtNota() {
			return dtNota;
		}

		public void setDtNota(Date dtNota) {
			this.dtNota = dtNota;
		}

		public double getValorTotal() {
			return valorTotal;
		}

		public void setValorTotal(double valorTotal) {
			this.valorTotal = valorTotal;
		}
}
