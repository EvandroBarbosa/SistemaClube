/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import entidade.ETipoAssociado;
import persistencia.PTipoAssociado;
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

    //validar os metodos incluir e alterar do etipoAssocado
    public void salvar(ETipoAssociado codigo) throws SQLException, Exception {

        if (codigo.getCodigo() == 0) {
            ptipoassociado.incluir(codigo);
        } else {
            ptipoassociado.alterar(codigo);
        }

    }

    //valida o método excluir
    public void excluir(int codigo) throws SQLException {
        ptipoassociado.excluir(codigo);
    }

    //valida o método consultar
    public void consultar(int codigo) throws SQLException {
        ptipoassociado.consultar(codigo);
    }

    //valida o método listar
    public List<ETipoAssociado> list() throws SQLException {
        return ptipoassociado.listar();

    }
}
