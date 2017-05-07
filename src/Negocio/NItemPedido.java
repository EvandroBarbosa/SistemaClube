package Negocio;

import entidade.ItemPedido;
import java.sql.SQLException;
import java.util.List;
import persistencia.PItemPedido;

/**
 *
 * @author Evandro
 */
public class NItemPedido {
    PItemPedido pPItemPedido;

    public NItemPedido() {
        pPItemPedido = new PItemPedido();    
    }
    
    //validar os dados dos itens
    public void salvar(ItemPedido ip) throws SQLException, Exception{
        if (validar(ip)) {
            if (ip.getCodigo() == 0) {
                pPItemPedido.incluir(ip);
            }else{
                pPItemPedido.alterar(ip);
            }
        }
    }
    
    public void excluir(int codigo) throws SQLException{
        pPItemPedido.excluir(codigo);
    }

    public ItemPedido consultar(int codigo) throws SQLException{
        return pPItemPedido.consultar(codigo);
    }
    
    public List<ItemPedido> lista(ItemPedido ip) throws SQLException{
        return pPItemPedido.listar(ip);
    }
    private boolean validar(ItemPedido ip) throws Exception {
        if (ip.getPrecoEpoca() == 0) {
            throw new Exception("É necessário informa preço");
        }
        
        if (ip.getQuantidade() == 0) {
            throw new Exception("Informe a quantidade de itens ");
        }
        return true;
    }
}
