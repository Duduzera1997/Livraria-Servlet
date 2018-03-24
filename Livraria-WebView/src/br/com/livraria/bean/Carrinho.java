package br.com.livraria.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Carrinho {

	private List<Saida> itens = new ArrayList<Saida>();

    public void adicionar(Saida item) {

        if (item.getQuantidade() == 0) {
            item.setQuantidade(1);
            item.setValorVenda(item.getProduto().getValorVenda());
        }

        for (Saida itm : itens) {
            if (itm.getProduto().getIdProduto() == item.getProduto().getIdProduto()) {
                itm.setQuantidade(itm.getQuantidade() + 1);
                itm.setValorVenda(itm.getValorVenda() + itm.getProduto().getValorVenda());
                return;
            }
        }
        getItens().add(item);
    }

    public boolean isPossuiAlgumItem() {
        if (getItens().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public double getValor() {
        double result = 0;
        for (Saida item : itens) {
            result += item.getValorVenda();
        }
        return result;
    }

    public void altera(Saida item) {
        for (Saida itm : itens) {
            if (itm.getProduto().getIdProduto() == item.getProduto().getIdProduto()) {
                itm.setQuantidade(item.getQuantidade());
                itm.setValorVenda(item.getValorVenda());
                return;
            }
        }
    }

    public void remover(Saida item) {
        for (Saida itm : itens) {
            if (itm.getProduto().getIdProduto() == item.getProduto().getIdProduto()) {
                itens.remove(itm);
                return;
            }
        }
    }

    public List<Saida> getItens() {
        Collections.reverse(itens);
        return itens;
    }

    public void setItens(List<Saida> itens) {
        this.itens = itens;
    }
}