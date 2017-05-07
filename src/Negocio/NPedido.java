package Negocio;

import entidade.Pedido;
import java.sql.SQLException;
import java.util.List;
import persistencia.PPedido;

/**
 *
 * @author Evandro
 */
public class NPedido {
    PPedido pPedido;

    public NPedido() {
        pPedido = new PPedido();
    }
    //validar as entrada de dados 
    public void salvar(Pedido pd) throws SQLException, Exception{
        if (validar(pd)){ 
            if (pd.getCodigo() == 0) {
                pPedido.incluir(pd);
            }else{
                pPedido.alterar(pd);
            }
        }
    }
    
    public void excluir(int codigo) throws SQLException{
        pPedido.excluir(codigo);
    }
    
    public Pedido consultar(int codigo) throws SQLException{
        return pPedido.consultar(codigo);
    }
    
    public List<Pedido> lista(Pedido pd) throws SQLException{
        return pPedido.listar(pd);
    }

    private boolean validar(Pedido pd) throws Exception {
        if (pd.getValorTotal() == 0) {
            throw new Exception("É necessário informar o valortotal");
        }
        if (pd.getDataPedido() == null) {
            throw  new Exception("Informe a data do pedido");
        }
       return true;
    }
    
}
