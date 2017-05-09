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
public class EItemPedido {
    private int codigo;
    private double precoEpoca;
    private int quantidade;
    
    private EProduto produto;
    private EPedido pedido;
    

    public EItemPedido() {
        produto = new EProduto();
        pedido = new EPedido();
    }

    public EProduto getProduto() {
        return produto;
    }

    public void setProduto(EProduto produto) {
        this.produto = produto;
    }

    public EPedido getPedido() {
        return pedido;
    }

    public void setPedido(EPedido pedido) {
        this.pedido = pedido;
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
