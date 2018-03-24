package br.com.livraria.bean;

public class Estoque implements Comparable<Estoque> {
		private int idEntrada;
    private int codigo;
    private Produto produto;
    private double valorTotal;
    private int quantidade;
    
	public int getIdEntrada() {
		return idEntrada;
	}
	public void setIdEntrada(int idEntrada) {
		this.idEntrada = idEntrada;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	@Override
	public int compareTo(Estoque estoque) {
		return this.getProduto().getNome().compareTo(estoque.getProduto().getNome());
	}
    

}
