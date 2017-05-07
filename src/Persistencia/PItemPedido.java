
package persistencia;

import entidade.ItemPedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PItemPedido {
    
    public void incluir(ItemPedido pedido) throws SQLException{
        //cria instruçõe sql para execultar no banco
        String sql = "INSERT INTO itempedido SET"
                + " precoepoca = ?, quantidade = ?, "
                + " codigo_produto = ?, codigo_pedido = ? "
                + " VALUES(?,?,?,?)";
        //cria conexão com o banco
        Connection cnn = util.Conexao.getConexao();
        //execulta a conexão 
        PreparedStatement ps = cnn.prepareStatement(sql);
        
        ps.setDouble(1, pedido.getPrecoEpoca());
        ps.setInt(2, pedido.getQuantidade());
        ps.setInt(3, pedido.getCodigo_produto().getCodigo());
        ps.setInt(4, pedido.getCodigo_pedido().getCodigo());
        
        //execulta o objeto
        ps.execute();
        
        //cria instruções sql para pegara a sequencia perdida
        String sql1 = "SELECT curval('itempedido_cod_seq')as, codigo";
        
        //cria um objeto para recuperar as informações 
        Statement stm = cnn.createStatement();
        
        //execulta o comando sql no banco e preenche o objeto resultset
        ResultSet rs = stm.executeQuery(sql);
        if (rs.next()) {
            pedido.setCodigo(rs.getInt("codigo"));
        }
        //fecha o resultset
        rs.close();
        //fecha a conexão
        cnn.close();
    }
    
    public void alterar(ItemPedido pedido) throws SQLException{
        //cria instruções sql para execultar no banco
        String sql = "UPADET itempedido SET "
                + " precoepoca = ?, quantidade = ?, "
                + " codigo_produto = ?, codigo_pedido = ?"
                + " WHERE codigo = ?";
        
        //cria uma conexão com o banco 
        Connection cnn = util.Conexao.getConexao();
        //execulta a conexão 
        PreparedStatement ps = cnn.prepareStatement(sql);
        
        ps.setDouble(1, pedido.getPrecoEpoca());
        ps.setInt(2, pedido.getQuantidade());
        ps.setInt(3, pedido.getCodigo_produto().getCodigo());
        ps.setInt(4, pedido.getCodigo_pedido().getCodigo());
        ps.setInt(5, pedido.getCodigo());
        
        //execulta o bojeto
        ps.execute();
        
        //fecha a conexão
        cnn.close();
        
    }
    
    public void excluir(int codigo) throws SQLException{
        //cria instruções sql
        String sql = "DELETE FROM itempedido"
                + " WHERE codigo = ? ";
        
        //cria a conexão
        Connection cnn = util.Conexao.getConexao();
        
        //execulta a conexão
        PreparedStatement ps = cnn.prepareStatement(sql);
        
        ps.setInt(1, codigo);
        
        //execulta o objeto
        ps.execute();
        //fecha a conexão
        cnn.close();
    }
    
    public ItemPedido consultar(int codigo) throws SQLException{
        //cria instruçõe sql 
        String sql = "SELECT codigo, precoepoca, quantidade, codigo_produto, codigo_pedido"
                + "FROM itempedido"
                + "WHERE codigo = ?";
        
        //cria a conexão com o banco
        Connection cnn = util.Conexao.getConexao();
        //execulta a conexão
        PreparedStatement ps = cnn.prepareStatement(sql);
        
        ps.setInt(1, codigo);
        //exeulta o objeto
        ps.execute();
        
        //busca as informações no banco e preenche o objeto resultset
        ResultSet rs = ps.executeQuery();
        
        ItemPedido pedido = new ItemPedido();
        if (rs.next()) {
            pedido.setCodigo(rs.getInt("codigo"));
            pedido.setPrecoEpoca(rs.getDouble("precoepoca"));
            pedido.setQuantidade(rs.getInt("quantidade"));
            pedido.setCodigo_produto(new PProduto().consultar(rs.getInt("codigo")));
            pedido.setCodigo_pedido(new PPedido().consultar(rs.getInt("codigo")));
        }
        //fecha o resultset
        rs.close();
        //fecha a conexão
        cnn.close();
        return pedido;
    }
    
    public List<ItemPedido> listar(ItemPedido pedido) throws SQLException{
        //criar instruções sql para execultar no banco
        String sql = "SELECT * FROM itempedido"
                + " WHERE codigo = ?";
        
        //cria a conexão com o banco
        Connection cnn = util.Conexao.getConexao();
        //execulta a conexão
        PreparedStatement ps = cnn.prepareStatement(sql);
        
        ps.setInt(1, pedido.getCodigo());
        
        //busca informações no banco e preenche o objeto
        ResultSet rs = ps.executeQuery();
        
        List<ItemPedido> lista = new ArrayList<>();
        while(rs.next()){
            ItemPedido ip = new ItemPedido();
            ip.setCodigo(rs.getInt("codigo"));
            ip.setPrecoEpoca(rs.getDouble("precoepoca"));
            ip.setQuantidade(rs.getInt("quantidade"));
            lista.add(ip);
        }
        //fecha o resultset
        rs.close();
        //fecha a conexão
        cnn.close();
        return lista;
    }
}
