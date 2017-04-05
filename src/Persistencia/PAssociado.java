package Persistencia;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Entidade.EAssociado;
import Entidade.ETipoAssociado;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Evandro
 */
public class PAssociado {

    public PAssociado() {
    }
            
    public void incluir(EAssociado parametro) throws SQLException{
        //Criar uma String com instruções SQL para ser executada
        String sql = "INSERT INTO associado "
                + "(nome,endereco,codigo_tipoassociado)"
                +"values(?, ?, ?)";
        
        //Cria um objeto de conexão com o banco
        Connection cnn = util.Conexao.getConexao();
        
        //Cria objeto para excutar os comando "contra"o banco
        PreparedStatement ps = cnn.prepareStatement(sql);
        
        //Aqui seta os valor recebidos como parametro para a string SQL
        ps.setString(1, parametro.getNome());
        ps.setString(2, parametro.getEndereco());
        ps.setInt(3, parametro.getTipoAssociado().getCodigo());
        
        //Aqui executa o SQL no banco de dado
        ps.execute();
        
        //Cria a instrução SQL para recuperar o valor da sequence
        String sql2 = "SELECT currval('associado_codigo_seq')as codigo";
        
        //Cria o objeto para recuperação de informação do banco
        Statement stm = cnn.createStatement();
        
        //Executa o comando SQL no banco de dado
        //retornando o resultado para o ResultSet
        ResultSet rst = stm.executeQuery(sql2);
        
        //Verifica se existe algo dentro do ResultSet
        if (rst.next()) {
            parametro.setCodigo(rst.getInt("codigo"));
        }
        //fecha o ResultSet
        rst.close();
        
        //Fecha a conexão
        cnn.close();
    }
    public void alterar(EAssociado parametro) throws SQLException{
        
        //Criar uma String com instruções SQL para ser executada
        String sql = "UPDATE associado"
                        +"SET nome= ? "
                        +"endereco = ? "
                        +" codigo_tipoassociado = ?"
                        +" WHERE codigo = ? ";
                
        //Cria um objeto de conexão com o banco
        Connection cnn = util.Conexao.getConexao();
        
       //Cria objeto para excutar os comando "contra"o banco
        PreparedStatement ps = cnn.prepareStatement(sql);
        
        //Aqui seta os valor recebidos como parametro para a string SQL
        ps.setString(1, parametro.getNome());
        ps.setString(2, parametro.getEndereco());
        ps.setInt(3, parametro.getTipoAssociado().getCodigo());
        ps.setInt(4, parametro.getCodigo());  
        
        //Aqui executa o SQL no banco de dado
        ps.executeUpdate();
        
        //Fecha conexão com o banco
        cnn.close();
    }
    public void excluir(int parametro) throws SQLException{
        
        //Criar uma String com instruções SQL para ser executada
        String sql = "DELETE FROM associado WHERE codigo = ?";
                
        //Cria um objeto de conexão com o banco
        Connection cnn = util.Conexao.getConexao();
        
        //Cria objeto para excutar os comando "contra"o banco
        PreparedStatement ps = cnn.prepareStatement(sql);
        
        //Aqui seta o valor recebido como parametro para a string SQL
        ps.setInt(1, parametro);
        
        //Aqui executa o SQL no banco de dado
        ps.execute();
        
        //Fecha conexão com o banco
        cnn.close();
    }
    public EAssociado consultar(int parametro) throws SQLException{
        
        Connection cnn = util.Conexao.getConexao();
        String sql = "SELECT codigo, nome, endereco,"
                + " codigo_tipoassociado"                
                + " FROM  associado "                
                + "  WHERE codigo = ? ";
        
        PreparedStatement ps = cnn.prepareStatement(sql);
        
        ps.setInt(1, parametro);
        ResultSet rs = ps.executeQuery();
        
        EAssociado socio = new EAssociado();  
        if (rs.next()) {
            socio.setCodigo(rs.getInt("codigo"));
            socio.setNome(rs.getString("nome"));
            socio.setEndereco(rs.getString("endereco"));
            
            socio.setTipoAssociado(new PTipoAssociado().consultar(rs.getInt("codigo_tipoassociado")));

        }
        
        rs.close();
        cnn.close();
        return socio;
        
    }
    public List<EAssociado> listar(EAssociado associado) throws SQLException{
       List<EAssociado> lista = new ArrayList<>();
       
        String sql = "SELECT * "
                + " FROM  associado "
                + "WHERE 1=1";     
        
        Connection cnn = util.Conexao.getConexao();
        
        //Aqui procura pelo nome
        if (associado.getNome() != null) {
            if (!associado.getNome().isEmpty()) {
                sql += "AND nome LIKE ? ";
            }
        }
        
        //Aqui procura pelo endereço
        if (associado.getEndereco() != null) {
            if (!associado.getEndereco().isEmpty()) {
                sql += "AND endereço LIKE ? ";
            }
        }
        sql += " ORDER BY nome";
               
        PreparedStatement ps = cnn.prepareStatement(sql);
        
        if (associado.getNome() != null) {
            if (!associado.getNome().isEmpty()) {
                ps.setString(1, "%" +associado.getNome()+ "%");
            }
        }
        
        if (associado.getEndereco() != null) {
            if (!associado.getEndereco().isEmpty()) {
                ps.setString(1, "%" +associado.getEndereco()+ "%");
            }
        }
        
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            EAssociado socio = new EAssociado();           
            socio.setCodigo(rs.getInt("codigo"));
            socio.setNome(rs.getString("nome"));
            socio.setEndereco(rs.getString("endereco"));                        
            lista.add(socio);
        }
        rs.close();
        cnn.close();
        return lista; 
    }
}

