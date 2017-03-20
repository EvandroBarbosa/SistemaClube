/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Entidade.EAssociado;
import Persistencia.PAssociado;
import java.sql.SQLException;

/**
 *
 * @author aluno
 */
public class NAssociado {
    
    PAssociado pAssociado;
    
    public NAssociado(){
        pAssociado = new PAssociado();
    }
    
    public void salvar(EAssociado associado) throws SQLException, Exception{
        if (validar(associado)) {
            
            if (associado.getCodigo() == 0) {
                pAssociado.incluir(associado);
            } else {
                pAssociado.alterar(associado);
            }
        }
    }

    private boolean validar(EAssociado associado) throws Exception {
       if(associado.getNome() == null){
           throw new Exception("É necessario preencher o mome");
       }
       if(associado.getEndereco()== null){
           throw new Exception("É necessario preencher o Endereço");
       }
       if(associado.getTipoAssociado()== null){
           throw new Exception("É necessario preencher o Tipo Asociado");
       }
        return false;
       
    }
}
