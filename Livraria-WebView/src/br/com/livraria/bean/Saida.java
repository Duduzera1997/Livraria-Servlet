package br.com.livraria.bean;

public class Saida implements Comparable<Saida> {

    private int idSaida;
    private int quantidade;
    private double valorVenda;
    private double desconto;
    private Produto produto;
    
	public int getIdSaida() {
		return idSaida;
	}

	public void setIdSaida(int idSaida) {
		this.idSaida = idSaida;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(double valorVenda) {
		this.valorVenda = valorVenda;
	}

	public double getDesconto() {
		return desconto;
	}

	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	@Override
	public int compareTo(Saida saida) {
		return this.getProduto().getNome().compareTo(saida.getProduto().getNome());
	}

}
