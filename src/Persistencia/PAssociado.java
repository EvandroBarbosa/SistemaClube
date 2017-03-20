package Persistencia;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Entidade.EAssociado;
import Entidade.ETipoAssociado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Evandro
 */
public class PAssociado {
    
    public void incluir(EAssociado parametro) throws SQLException{
        String sql = "INSERT INTO associado (nome,endereco,codigo_tipoassociado)"+"values(?,?,?)";
        Connection cnn = util.Conexao.getConexao();
        PreparedStatement ps = cnn.prepareStatement(sql);
        
        ps.setString(1, parametro.getNome());
        ps.setString(2, parametro.getEndereco());
        ps.setInt(3, parametro.getTipoAssociado().getCodigo());
        
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
        String sql = "UPDATE associado"+"SET nome= ? "+"endereco = ? "+" codigo_tipoassociado = ?"+" WHERE codigo = ? ";
        Connection cnn = util.Conexao.getConexao();
        PreparedStatement ps = cnn.prepareStatement(sql);
                
        ps.setString(1, parametro.getNome());
        ps.setString(2, parametro.getEndereco());
        ps.setInt(4, parametro.getCodigo());  
        ps.setInt(3, parametro.getTipoAssociado().getCodigo());
        
        ps.executeUpdate();
        cnn.close();
    }
    public void excluir(int parametro) throws SQLException{
        String sql = "DELETE FROM associado WHERE codigo = ?";
        Connection cnn = util.Conexao.getConexao();
        PreparedStatement ps = cnn.prepareStatement(sql);
        
        ps.setInt(1, parametro);
        ps.execute();
        cnn.close();
    }
    public EAssociado consultar(int parametro) throws SQLException{
        String sql = "SELECT codigo, nome, endereco, "                
                + " FROM  associado "                
                + "  WHERE codigo = ? ";
        
        Connection cnn = util.Conexao.getConexao();
        PreparedStatement ps = cnn.prepareStatement(sql);
        
        ps.setInt(1, parametro);
        ResultSet rs = ps.executeQuery();
        
        EAssociado socio = new EAssociado();  
//        ETipoAssociado tipo = new ETipoAssociado();
        
        if (rs.next()) {
            socio.setCodigo(rs.getInt("codigo"));
            socio.setNome(rs.getString("nome"));
            socio.setEndereco(rs.getString("endereco"));
            
            socio.setTipoAssociado(new PTipoAssociado().consultar(rs.getInt("codigo_tipoassociado")));
//            // pega as informações do tipo associado
//            tipo.setCodigo(rs.getInt("tipoassociado.codigo"));
//            tipo.setDescricao(rs.getString("tipoassociado.descricao"));
//            tipo.setValorMensalidade(rs.getDouble("tipoassociado.valormensalidade"));
//            
//            socio.setTipoAssociado(tipo);
        }
        
        rs.close();
        cnn.close();
        return socio;
        
    }
    public List<EAssociado> listar() throws SQLException{
       
        String sql = "SELECT * "+ " FROM  associado ";     
        
        Connection cnn = util.Conexao.getConexao();
        Statement stm = cnn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        
       List<EAssociado> lista = new ArrayList<>();
        while(rs.next()){
            EAssociado socio = new EAssociado();
            ETipoAssociado tipo = new ETipoAssociado();
            socio.setCodigo(rs.getInt("codigo"));
            socio.setNome(rs.getString("nome"));
            socio.setEndereco(rs.getString("endereco"));
            socio.setTipoAssociado(new ETipoAssociado(rs.getInt("codigo_tipoassociado")));
            
            lista.add(socio);
        }
        rs.close();
        cnn.close();
        return lista; 
    }
}

