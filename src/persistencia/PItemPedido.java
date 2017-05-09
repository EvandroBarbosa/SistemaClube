
package persistencia;

import entidade.EItemPedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PItemPedido {
    
    public void incluir(EItemPedido item, Connection cnn) throws SQLException{
               
        try {
            
            //cria instruçõe sql para execultar no banco
            String sql = "INSERT INTO itempedido SET"
                    + " precoepoca = ?, quantidade = ?, "
                    + " codigo_produto = ?, codigo_pedido = ? "
                    + " VALUES(?,?,?,?)";

            //execulta a conexão 
            PreparedStatement ps = cnn.prepareStatement(sql);

            ps.setDouble(1, item.getPrecoEpoca());
            ps.setInt(2, item.getQuantidade());
            ps.setInt(3, item.getProduto().getCodigo());
            ps.setInt(4, item.getPedido().getCodigo());

            //execulta o objeto
            ps.execute();

            //cria instruções sql para pegara a sequencia perdida
            String sql1 = "SELECT curval('itempedido_cod_seq')as, codigo";

            //cria um objeto para recuperar as informações 
            Statement stm = cnn.createStatement();

            //execulta o comando sql no banco e preenche o objeto resultset
            ResultSet rs = stm.executeQuery(sql);
            if (rs.next()) {
                item.setCodigo(rs.getInt("codigo"));
            }
            //fecha o resultset
            rs.close();
            cnn.commit();
        } catch (Exception e) {
            cnn.rollback();
        }
            //fecha a conexão
            cnn.close();
    }
    
    public void alterar(EItemPedido item, Connection cnn) throws SQLException{
        //cria uma conexão com o banco 
        
        try {
            
            //cria instruções sql para execultar no banco
            String sql = "UPADET itempedido SET "
                    + " precoepoca = ?, quantidade = ?, "
                    + " codigo_produto = ?, codigo_pedido = ?"
                    + " WHERE codigo = ?";

            //execulta a conexão 
            PreparedStatement ps = cnn.prepareStatement(sql);

            ps.setDouble(1, item.getPrecoEpoca());
            ps.setInt(2, item.getQuantidade());
            ps.setInt(3, item.getProduto().getCodigo());
            ps.setInt(4, item.getPedido().getCodigo());
            ps.setInt(5, item.getCodigo());

            //execulta o bojeto
            ps.execute();
            
            //se der tudo certo grava no banco
            cnn.commit();

        } catch (Exception e) {
            //caso houver algum erro isso será desfeito
            cnn.rollback();
        }
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
    
    public EItemPedido consultar(int codigo) throws SQLException{
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
        
        EItemPedido pedido = new EItemPedido();
        if (rs.next()) {
            pedido.setCodigo(rs.getInt("codigo"));
            pedido.setPrecoEpoca(rs.getDouble("precoepoca"));
            pedido.setQuantidade(rs.getInt("quantidade"));
            pedido.setProduto(new PProduto().consultar(rs.getInt("codigo")));
            pedido.setPedido(new PPedido().consultar(rs.getInt("codigo")));
        }
        //fecha o resultset
        rs.close();
        //fecha a conexão
        cnn.close();
        return pedido;
    }
    
    public List<EItemPedido> listar(EItemPedido pedido) throws SQLException{
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
        
        List<EItemPedido> lista = new ArrayList<>();
        while(rs.next()){
            EItemPedido ip = new EItemPedido();
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

    void excluirPorPedido(int codigo, Connection cnn) throws SQLException {
               
        try {
            //cria instrução sql 
            String sql = "DELETE FROM itempedido "
                    + " WHERE codigo = ? ";

            //execulta a conexão
            PreparedStatement ps = cnn.prepareStatement(sql);

            ps.setDouble(1, codigo);            
            //execulta o objeto
            ps.execute();

            PItemPedido pitem = new PItemPedido();
            pitem.excluirPorPedido(codigo,cnn);
            
            cnn.commit();
        } catch (Exception e) {
            cnn.rollback();
        }
        cnn.close();
    }

    
}
