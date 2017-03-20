/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Entidade.ETipoAssociado;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.Conexao;

/**
 *
 * @author Evandro
 */
public class PTipoAssociado {
    
    public void incluir(ETipoAssociado parametro) throws SQLException{
        //cria a construção sql que será usada para inserir as informações no banco de dados
        String sql = "INSERT INTO tipoassociado (descricao, valormensalidade)"
                    +" values(?,?)";        
        //cria o objeto para a conexão com o banco de dados
        Connection cnn = util.Conexao.getConexao();
        //cria o objeto para execução no banco de dado
        PreparedStatement ps = cnn.prepareStatement(sql);
        
        //substituo as variaveis craidas na instrução SQL
        //pelos valores recebidos do parametro
        ps.setString(1, parametro.getDescricao());
        ps.setDouble(2, parametro.getValorMensalidade());
        
        //Executa o comando no banco de dado
        ps.execute();
        
        //Cria a instrução SQL para recuperar o valor da sequence
        String sql2 = "SELECT currval('tipoassociado_codigo_seq')as codigo";
        
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
    public void alterar(ETipoAssociado parametro) throws SQLException{
        //cria a construção sql que será usada para inserir as informações no banco de dados
        String sql = "UPDATE tipoassociado SET descricao = ?, "
                +"valormensalidade = ?"
                +"where codigo = ?";
                         
        //cria o objeto para a conexão com o banco de dados
        Connection cnn = util.Conexao.getConexao();
        
        //cria o objeto para execução no banco de dado
        PreparedStatement ps = cnn.prepareStatement(sql);
        
        //substituo as variaveis craidas na instrução SQL
        //pelos valores recebidos do parametro
        ps.setString(1, parametro.getDescricao());
        ps.setDouble(2, parametro.getValorMensalidade());
        ps.setInt(3, parametro.getCodigo());
        
        //Executa o comando no banco de dado
        ps.executeUpdate();
        //Fecha a conexão
        cnn.close();
        
    
    }
    
    public void excluir(int parametro) throws SQLException{
        //cria a construção sql que será usada para inserir as informações no banco de dados
        String sql = "DELETE FROM tipoassociado WHERE codigo = ?";
        //cria o objeto para a conexão com o banco de dados
        Connection cnn = util.Conexao.getConexao();
        
         //cria o objeto para execução no banco de dado
        PreparedStatement ps = cnn.prepareStatement(sql);
        
        //substituo as variaveis craidas na instrução SQL
        //pelos valores recebidos do parametro
        ps.setInt(1, parametro);
        
        //Executa o comando no banco de dado
        ps.execute();
        //Fecha a conexão
        cnn.close();
    }
    public ETipoAssociado consultar(int parametro) throws SQLException{
         //cria a construção sql que será usada para inserir as informações no banco de dados
        String sql = "SELECT * FROM tipoassociado where codigo = ?";
                         
        //cria o objeto para a conexão com o banco de dados
        Connection cnn = util.Conexao.getConexao();
        
        //cria o objeto para execução no banco de dado
        PreparedStatement ps = cnn.prepareStatement(sql);
        
        //substituo as variaveis craidas na instrução SQL
        //pelos valores recebidos do parametro              
        ps.setInt(1,parametro);
        
        ResultSet rst = ps.executeQuery();
        
        ETipoAssociado objeto = new ETipoAssociado();
        if (rst.next()) {
            objeto.setCodigo(rst.getInt("codigo"));
            objeto.setDescricao(rst.getString("descricao"));
            objeto.setValorMensalidade(rst.getDouble("valormensalidade"));
            
        }       
        
        //Fecha a conexão
        cnn.close();
        rst.close();
        
        return objeto;
    }
    public List<ETipoAssociado> listar() throws SQLException{
        String sql = "SELECT * FROM tipoassociado";
        
        Connection cnn = Conexao.getConexao();
        Statement stm = cnn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        
        List<ETipoAssociado> lista = new ArrayList<ETipoAssociado>();
        while(rs.next()){
            ETipoAssociado obj = new ETipoAssociado();
            obj.setCodigo(rs.getInt("codigo"));
            obj.setDescricao(rs.getString("descricao"));
            obj.setValorMensalidade(rs.getDouble("valormensalidade"));
            
            lista.add(obj);
        }
        rs.close();
        cnn.close();
      return lista;
    }
}
