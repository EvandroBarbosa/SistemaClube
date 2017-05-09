
package persistencia;

import entidade.EProduto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PProduto {
    
    public void incluir(EProduto produto) throws SQLException{
        //Instruções sql para execução no banco
        String sql = "INSERT INTO produto (nome, valorvenda)"
                + " VALUES (?, ?)";
        
        //Comando para conexão com o banco de dados
        Connection cnn = util.Conexao.getConexao();
        //executa a conexão
        PreparedStatement ps = cnn.prepareStatement(sql);
        
        //seta os valores recebidos como parametros
        ps.setString(1, produto.getNome());
        ps.setDouble(2, produto.getPrecoVenda());
        
        //executa o objeto 
        ps.execute();
        
        //Recupera a valor da sequencia
        String sql1 = "SELECT curval ('produto_codigo_seq')as codigo";
        //cria um objeto para recuperar as informações
        Statement stm = cnn.createStatement();
        
        //busca as informações no banco de dado
        //e preenche o objeto rst
        ResultSet rst = stm.executeQuery(sql);
        //verifica se existe algo no objeto resultSet
        if (rst.next()) {
            produto.setCodigo(rst.getInt("codigo"));
        }
        //fecha o resultSet
        rst.close();
        //fecha a conexão
        cnn.close();
    }
    
    public void alterar(EProduto produto) throws SQLException{
        String sql = "UPDATE produto SET nome = ?, valorvenda = ?"
                + " WHERE codigo = ? ";
        
        Connection cnn = util.Conexao.getConexao();
        PreparedStatement ps = cnn.prepareStatement(sql);
        
        ps.setString(1, produto.getNome());
        ps.setDouble(2, produto.getPrecoVenda());
        ps.setInt(3, produto.getCodigo());
        
        ps.executeUpdate();
        cnn.close();
    }
    
    public void excluir(int codigo) throws SQLException{
        //cria instruções sql para executar contro o banco
        String sql = "DELETE FROM produto"
                + " WHERE codigo = ?";
        
        //cria conexão com o banco 
        Connection cnn = util.Conexao.getConexao();
        
        //execulta a conexão
        PreparedStatement ps = cnn.prepareStatement(sql);
        
        //seta os valores recebidos como parametro
        ps.setInt(1, codigo);
        //execulta o objeto ps
        ps.execute();
        //fecha a conexão
        cnn.close();
        
    }
    
    public EProduto consultar(int codigo) throws SQLException{
        //cria inestruções sql para execultar no banco
        String sql = "SELECT codigo, nome, valorvenda"
                + "FROM produto"
                + " WHERE codigo = ?";
        
        //cria uma conexão com o banco de dado
        Connection cnn = util.Conexao.getConexao();
        //execulta a conexão
        PreparedStatement ps = cnn.prepareStatement(sql);
        //seta os valores no objeto ps 
        ps.setInt(1, codigo);
        
        //busca as informações no banco e preenche o objeto
        ResultSet rs = ps.executeQuery();
        
        EProduto pedido = new EProduto();
        if (rs.next()) {
            pedido.setCodigo(rs.getInt("codigo"));
            pedido.setNome(rs.getString("nome"));
            pedido.setPrecoVenda(rs.getDouble("valorvenda"));
        }
        //fecha o resultSet
        rs.close();
        //fecha conexão com o banco
        cnn.close();
        return pedido;
    }
    
    public List<EProduto> listar(EProduto produto) throws SQLException{
        //cria instruções sql para execultar contra o banco
        String sql = "SELECT * FROM produto"
                + " WHERE nome LIKE ? ";
        
        //cria uma conexão com o banco
        Connection cnn = util.Conexao.getConexao();
        //execulta a conexão
        PreparedStatement ps = cnn.prepareStatement(sql);
        //seta as informações no objeto
        ps.setString(1, "%" +produto.getNome()+ "%");
        
        //busca sa informações no banco e preenche o objeto
        ResultSet rs = ps.executeQuery();
        
        //cria uma lista de produto
        List<EProduto> lista = new ArrayList<>();
        while(rs.next()){
            EProduto p = new EProduto();
            p.setCodigo(rs.getInt("codigo"));
            p.setNome(rs.getString("nome"));
            p.setPrecoVenda(rs.getDouble("valorvenda"));
            lista.add(p);
        }
        rs.close();
        cnn.close();
        return lista;
    }
}
