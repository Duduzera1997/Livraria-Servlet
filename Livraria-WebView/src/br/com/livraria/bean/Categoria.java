package br.com.livraria.bean;

public class Categoria {
	
	private int idCategoria;
	private String nome;
	private String descricao;

	public int getIdCategoria() {
		if (idCategoria == 0) {
			return 1;
		}
		return idCategoria;
	}


	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}



}
