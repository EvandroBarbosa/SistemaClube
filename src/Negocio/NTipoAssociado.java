/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Entidade.ETipoAssociado;
import Persistencia.PTipoAssociado;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author aluno
 */
public class NTipoAssociado {
    
    PTipoAssociado ptipoassociado;
    
    public NTipoAssociado() {
        ptipoassociado = new PTipoAssociado();
    }
    //validar os metodos do etipoAssocado
    public void salvar(ETipoAssociado tipoAssociado) throws SQLException, Exception{
        if (validar(tipoAssociado)) {
            if (tipoAssociado.getCodigo() == 0) {
               ptipoassociado.incluir(tipoAssociado);
            }else{
                ptipoassociado.alterar(tipoAssociado);
            }
        }
    }
    public void excluir(int codigo) throws SQLException{
        ptipoassociado.excluir(codigo);
    }
    public void consultar(int codigo) throws SQLException{
        ptipoassociado.consultar(codigo);
    }
    public List<ETipoAssociado> list() throws SQLException{
       return ptipoassociado.listar();
       
       
    }

    private boolean validar(ETipoAssociado tipoAssociado) throws Exception, Exception {
       if(tipoAssociado.getDescricao() == null){
           throw new Exception("É necessario fornecer a descrição");
       }
       if(tipoAssociado.getValorMensalidade()== 0){
           throw new Exception("É necessario forneça o valor da mensalidade");
       }
       
        return false;
    }
    
}
