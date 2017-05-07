package Negocio;

import Entidade.EAssociado;
import Persistencia.PAssociado;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Evandro
 */
public class NAssociado {

    PAssociado pAssociado;

    public NAssociado() {
        pAssociado = new PAssociado();
    }

    //onde validar os metodos incluir e alterar
    public void salvar(EAssociado associado) throws SQLException, Exception {

        if (validar(associado)) {
            if (associado.getCodigo() == 0) {
                pAssociado.incluir(associado);
            } else {
                pAssociado.alterar(associado);
            }
        }
    }

    //valida o metodo excluir
    public void excluir(int codigo) throws SQLException, Exception {
        pAssociado.excluir(codigo);
    }

    //valida o método consulta
    public EAssociado consultar(int codigo) throws SQLException, Exception {
        return pAssociado.consultar(codigo);
    }

    //valida o método listar
    public List<EAssociado> list(EAssociado socio) throws SQLException {
        return pAssociado.listar(socio);
    }

    //Esse método é chamado no metodo salvar validando as informações
    private boolean validar(EAssociado associado) throws Exception {
        if (associado.getNome() == null) {
            throw new Exception("É necessario preencher o mome");
        }

        if (associado.getNome().isEmpty()) {
            throw new Exception("É necessario preencher o mome");
        }

        if (associado.getEndereco() == null) {
            throw new Exception("É necessario preencher o Endereço");
        }

        if (associado.getEndereco().isEmpty()) {
            throw new Exception("É necessario preencher o Endereço");
        }

        if (associado.getTipoAssociado().getCodigo() == 0) {
            throw new Exception("É necessario preencher o Tipo Asociado");
        }

        return true;

    }

}
