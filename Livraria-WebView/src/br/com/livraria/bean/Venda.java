package br.com.livraria.bean;

import java.io.Serializable;
import java.util.Date;

public class Venda implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int idVenda;
    private Date dataVenda;
    private String observacao;

	public int getIdVenda() {
		return idVenda;
	}
	public void setIdVenda(int idVenda) {
		this.idVenda = idVenda;
	}
	public Date getDataVenda() {
		return dataVenda;
	}
	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
