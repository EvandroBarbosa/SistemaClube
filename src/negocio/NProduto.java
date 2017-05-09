package negocio;

import entidade.EProduto;
import java.sql.SQLException;
import java.util.List;
import persistencia.PProduto;

/**
 *
 * @author Evandro
 */
public class NProduto {
    PProduto pProduto;

    public NProduto() {
        pProduto = new PProduto();
    }
    
    //valida os dados do produto
    public void salvar(EProduto pdt) throws SQLException, Exception{
        if (validar(pdt)) {
            if (pdt.getCodigo() == 0) {
                pProduto.incluir(pdt);
            }else{
                pProduto.alterar(pdt);
            }
        }
    }
    
    public void excluir(int codigo) throws SQLException{
        pProduto.excluir(codigo);
    }
    
    public EProduto consultar(int codigo) throws SQLException{
       return pProduto.consultar(codigo);
    }
    
    public List<EProduto> lista(EProduto pdt) throws SQLException{
        return pProduto.listar(pdt);
    }

    private boolean validar(EProduto pdt) throws Exception {
        if (pdt.getNome() == null) {
            throw new Exception("É necessário informar o nome do produto");
        }
        if (pdt.getNome().isEmpty()) {
            throw new Exception("É necessário informar o nome do produto");
        }
        if (pdt.getPrecoVenda() == 0) {
            throw new Exception("É necessário informarr o valor da venda do produto");
        }
        return true;
    }
}
