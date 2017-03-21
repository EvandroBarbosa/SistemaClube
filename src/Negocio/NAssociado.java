/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Entidade.EAssociado;
import Persistencia.PAssociado;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author aluno
 */
public class NAssociado {
    
    PAssociado pAssociado;
    
    public NAssociado(){
        pAssociado = new PAssociado();
    }
    
    //onde validar os metodos incluir e alterar
    public void salvar(EAssociado associado) throws SQLException, Exception{
        if (validar(associado)) {
            
            if (associado.getCodigo() == 0) {
                pAssociado.incluir(associado);
            } else {
                pAssociado.alterar(associado);
            }
        }
    }
    public void excluir(int codigo) throws SQLException, Exception{
       pAssociado.excluir(codigo);
    }
    public EAssociado consultar(int codigo) throws SQLException, Exception{
     return pAssociado.consultar(codigo);
    }
    public List<EAssociado> list() throws SQLException{        
        return pAssociado.listar();
    }

    private boolean validar(EAssociado associado) throws Exception {
       if(associado.getNome() == null){
           throw new Exception("É necessario preencher o mome");
       }
       if(associado.getEndereco()== null){
           throw new Exception("É necessario preencher o Endereço");
       }
       if(associado.getTipoAssociado().getCodigo() == 0){
           throw new Exception("É necessario preencher o Tipo Asociado");
       }
        return false;
       
    }

    
}
