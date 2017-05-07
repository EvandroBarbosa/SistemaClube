
package persistencia;

import entidade.Pedido;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class PPedido {
    
    
    public void incluir(Pedido pedido) throws SQLException{
        //criar instruções sql para execultar no banco
        String sql = "INSERT INTO pedido (valortotal, datapedido)"
                + " VALUES (?, ?) ";
        
        //Cria a conexão com o banco de dado
        Connection cnn = util.Conexao.getConexao();
        //executa a conexão
        PreparedStatement ps = cnn.prepareStatement(sql);
        
        //seta os valores no objeto ps
        ps.setDouble(1, pedido.getValorTotal());
        ps.setDate(2, (Date) pedido.getDataPedido());
        
        //execulta o objeto 
        ps.execute();
        
        //cria instrução sql para recupera o valor da sequencia
        String sql1 = "SELECT curval('pedido_cod_seq')as, codigo";
        
        //cria um objeto para recupera as informações
        Statement stm = cnn.createStatement();
        
        //busca sa informações no banco e preenche o objeto
        ResultSet rs  = stm.executeQuery(sql);
        if (rs.next()) {
            pedido.setCodigo(rs.getInt("codigo"));
        }
        //fecha o resultset
        rs.close();
        //fecha a conexão
        cnn.close();
    }
    
    public void alterar(Pedido pedido) throws SQLException{
        //cria instrução sql 
        String sql = "UPADET pedido SET valortotal = ?, datapedido = ? "
                + " WHERE codigo = ? ";
        
        //cria conexão como o banco
        Connection cnn = util.Conexao.getConexao();
        //execulta a conexão
        PreparedStatement ps = cnn.prepareStatement(sql);
     
        ps.setDouble(1, pedido.getValorTotal());
        ps.setDate(2, (Date) pedido.getDataPedido());
        ps.setInt(3, pedido.getCodigo());
        
        ps.execute();
        cnn.close();
        //execulta o objeto
        
        
    }
    
    public void excluir(int codigo) throws SQLException{
        //cria instruções sql 
        String sql = "DELETE FROM pedido"
                + " WHERE codigo = ? ";
        
        //cria conexão com o banco
        Connection cnn = util.Conexao.getConexao();
        //execulta a conexão 
        PreparedStatement ps = cnn.prepareStatement(sql);
        
        ps.setInt(1, codigo);
        
        //execulta o objeto 
        ps.execute();
        //fecha a conexão
        cnn.close();
        
    }
    
    public Pedido consultar(int codigo) throws SQLException{
        //cria instrução sql para execulta no banco
        String sql = "SELECT codigo, valortotal, datapedido"
                + "FROM pedido "
                + " WHERE codigo = ? ";
        
        //cria a conexão com o banco
        Connection cnn = util.Conexao.getConexao();
        //execulta a conexão
        PreparedStatement ps = cnn.prepareStatement(sql);
        
        ps.setInt(1, codigo);
        
        //busca as informações no banco e preenche o objeto resultset
        ResultSet rs = ps.executeQuery();
        
        Pedido pedido = new Pedido();
        if (rs.next()) {
            pedido.setCodigo(rs.getInt("codigo"));
            pedido.setValorTotal(rs.getDouble("valortotal"));
            pedido.setDataPedido(rs.getDate("datapedido"));
        }
        //fecha o resultset
        rs.close();
        //fecha a conexão
        cnn.close();
        return pedido;
    }
    
    public List<Pedido> listar(Pedido pedido) throws SQLException{
        //cria instruções sql para execultar no banco
        String sql = "SELECT * FROM pedido"
                + "WHERE codigo = ? ";
        
        //cria uma conexão com o banco
        Connection cnn = util.Conexao.getConexao();
        //execulta a conexão
        PreparedStatement ps = cnn.prepareStatement(sql);
        
        ps.setInt(1, pedido.getCodigo());
        
        //busca as informações no banco e preenche o objeto resultset
        ResultSet rs = ps.executeQuery();
                        
        List<Pedido> lista = new ArrayList<>();
        while(rs.next()){
            Pedido pd = new Pedido();
            pd.setCodigo(rs.getInt("codigo"));
            pd.setValorTotal(rs.getDouble("valortotal"));
            pd.setDataPedido(rs.getDate("datapedido"));
            lista.add(pd);
        }
        //fecha resultset
        rs.close();
        //fecha conexão
        cnn.close();
        return lista;
    }
}
