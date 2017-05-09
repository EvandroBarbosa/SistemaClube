package persistencia;

import entidade.EItemPedido;
import entidade.EPedido;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PPedido {

    public void incluir(EPedido pedido) throws SQLException {
        //criar instruções sql para execultar no banco
        Connection cnn = util.Conexao.getConexao();
        cnn.setAutoCommit(false);
        try {

            String sql = "INSERT INTO pedido (valortotal, datapedido, codigo_associado)"
                    + " VALUES (?, NOW(), ?) ";

            //Cria a conexão com o banco de dado
            //executa a conexão
            PreparedStatement ps = cnn.prepareStatement(sql);

            //seta os valores no objeto ps
            ps.setDouble(1, pedido.getValorTotal());
            ps.setInt(2, pedido.getAssociado().getCodigo());

            //execulta o objeto 
            ps.execute();

            //cria instrução sql para recupera o valor da sequencia
            String sql1 = "SELECT curval('pedido_cod_seq')as codigo";

            //cria um objeto para recupera as informações
            Statement stm = cnn.createStatement();

            //busca sa informações no banco e preenche o objeto
            ResultSet rs = stm.executeQuery(sql);
            if (rs.next()) {
                pedido.setCodigo(rs.getInt("codigo"));
            }
            //fecha o resultset
            rs.close();
            PItemPedido pitem = new PItemPedido();
            for (EItemPedido item : pedido.getListaItem()) {
                item.getPedido().setCodigo(pedido.getCodigo());
                pitem.incluir(item, cnn);
            }
            //fecha a conexão
            cnn.commit();
        } catch (Exception e) {
            cnn.rollback();
        }
        cnn.close();
    }

    public void alterar(EPedido pedido) throws SQLException {
        //cria conexão como o banco
        Connection cnn = util.Conexao.getConexao();
        cnn.setAutoCommit(false);
        try {
            //cria instrução sql 
            String sql = "UPADET pedido SET valortotal = ?, datapedido = NOW() "
                    + " WHERE codigo = ? ";

            //execulta a conexão
            PreparedStatement ps = cnn.prepareStatement(sql);

            ps.setDouble(1, pedido.getValorTotal());
            ps.setInt(2, pedido.getCodigo());
            //execulta o objeto
            ps.execute();

            PItemPedido pitem = new PItemPedido();
            pitem.excluirPorPedido(pedido.getCodigo(),cnn);
            for (EItemPedido item : pedido.getListaItem()) {
                item.getPedido().setCodigo(pedido.getCodigo());
                pitem.incluir(item, cnn);
            }
            
            cnn.commit();
        } catch (Exception e) {
            cnn.rollback();
        }
        cnn.close();

    }

    public void excluir(int codigo) throws SQLException {
        Connection cnn = util.Conexao.getConexao();
        cnn.setAutoCommit(false);
        try {
            //cria instrução sql 
            String sql = "DELETE FROM pedido "
                    + " WHERE codigo = ? ";

            //execulta a conexão
            PreparedStatement ps = cnn.prepareStatement(sql);

            ps.setDouble(1, codigo);            
            //execulta o objeto
            ps.execute();

//            PItemPedido pitem = new PItemPedido();
//            pitem.excluirPorPedido(codigo,cnn);
            
            cnn.commit();
        } catch (Exception e) {
            cnn.rollback();
        }
        cnn.close();

    }

    public EPedido consultar(int codigo) throws SQLException {
        //cria a conexão com o banco
        Connection cnn = util.Conexao.getConexao();

        //cria instrução sql para execulta no banco
        String sql = "SELECT codigo, valortotal, datapedido"
                + "FROM pedido "
                + " WHERE codigo = ? ";

        //execulta a conexão
        PreparedStatement ps = cnn.prepareStatement(sql);

        ps.setInt(1, codigo);

        //busca as informações no banco e preenche o objeto resultset
        ResultSet rs = ps.executeQuery();

        EPedido pedido = new EPedido();
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

    public List<EPedido> listar(EPedido pedido) throws SQLException {
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

        List<EPedido> lista = new ArrayList<>();
        while (rs.next()) {
            EPedido pd = new EPedido();
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
