package br.com.livraria.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Embalagem {

	private List<Entrada> itens = new ArrayList<Entrada>();

    public void adicionar(Entrada item) {

        if (item.getQuantidade() == 0) {
            item.setQuantidade(1);
            item.setValorEntrada(item.getValorEntrada());
            item.setValorTotal(item.getValorTotal());
        }

        for (Entrada itm : itens) {
            if (itm.getProduto().getIdProduto() == item.getProduto().getIdProduto()) {
                itm.setQuantidade(itm.getQuantidade() + 1);
                itm.setValorEntrada(itm.getValorEntrada() + itm.getValorEntrada());
                itm.setValorTotal(item.getValorTotal());
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
        for (Entrada item : itens) {
            result += item.getValorTotal();
        }
        return result;
    }

    public void altera(Entrada item) {
        for (Entrada itm : itens) {
            if (itm.getProduto().getIdProduto() == item.getProduto().getIdProduto()) {
                itm.setQuantidade(item.getQuantidade());
                itm.setValorEntrada(item.getValorEntrada());
                itm.setValorTotal(item.getValorTotal());
                return;
            }
        }
    }

    public void remover(Entrada item) {
        for (Entrada itm : itens) {
            if (itm.getProduto().getIdProduto() == item.getProduto().getIdProduto()) {
                itens.remove(itm);
                return;
            }
        }
    }

    public List<Entrada> getItens() {
        Collections.reverse(itens);
        return itens;
    }

    public void setItens(List<Entrada> itens) {
        this.itens = itens;
    }
}