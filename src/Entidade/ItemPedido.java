/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

/**
 *
 * @author aluno
 */
public class ItemPedido {
    private int codigo;
    private double precoEpoca;
    private int quantidade;
    private Produto codigo_produto;
    private Pedido codigo_pedido;
    

    public ItemPedido() {
    }

    public Produto getCodigo_produto() {
        return codigo_produto;
    }

    public void setCodigo_produto(Produto codigo_produto) {
        this.codigo_produto = codigo_produto;
    }

    public Pedido getCodigo_pedido() {
        return codigo_pedido;
    }

    public void setCodigo_pedido(Pedido codigo_pedido) {
        this.codigo_pedido = codigo_pedido;
    }

    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public double getPrecoEpoca() {
        return precoEpoca;
    }

    public void setPrecoEpoca(double precoEpoca) {
        this.precoEpoca = precoEpoca;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    
}
