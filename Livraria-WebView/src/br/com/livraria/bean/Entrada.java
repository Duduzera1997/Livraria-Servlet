package br.com.livraria.bean;

public class Entrada implements Comparable<Entrada> {

    private int idEntrada;
    private int quantidade;
    private double valorEntrada;
    private double valorTotal;
    private Produto produto;
    
	public int getIdEntrada() {
		return idEntrada;
	}

	public void setIdEntrada(int idEntrada) {
		this.idEntrada = idEntrada;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getValorEntrada() {
		return valorEntrada;
	}

	public void setValorEntrada(double valorEntrada) {
		this.valorEntrada = valorEntrada;
	}
	
	
	
	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	@Override
	public int compareTo(Entrada Entrada) {
		return this.getProduto().getNome().compareTo(Entrada.getProduto().getNome());
	}

}
