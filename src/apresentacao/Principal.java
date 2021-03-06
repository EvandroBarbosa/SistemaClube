/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apresentacao;
import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JOptionPane;

/**
 *
 * @author Evandro
 */
public class Principal extends javax.swing.JFrame {

    Associado_cadastro janela;   

    /**
     * Creates new form Principal
     */
    public Principal() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ImageIcon image = new ImageIcon(getClass().getResource("/imagens/background.jpg"));

        Image img = image.getImage();
        prdPrincipal = new javax.swing.JDesktopPane(){

            public void paintComponent(Graphics g){
                g.drawImage(img,0,0,getWidth(),getHeight(),this);
            }

        };
        jMBarra = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mmCadAssociado = new javax.swing.JMenuItem();
        mmTipoAssociado = new javax.swing.JMenuItem();
        msProduto = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        mmSair = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("AppClube");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        prdPrincipal.setBackground(new java.awt.Color(0, 0, 51));

        javax.swing.GroupLayout prdPrincipalLayout = new javax.swing.GroupLayout(prdPrincipal);
        prdPrincipal.setLayout(prdPrincipalLayout);
        prdPrincipalLayout.setHorizontalGroup(
            prdPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 674, Short.MAX_VALUE)
        );
        prdPrincipalLayout.setVerticalGroup(
            prdPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 464, Short.MAX_VALUE)
        );

        jMBarra.setBackground(new java.awt.Color(102, 204, 255));

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/cads.jpg"))); // NOI18N
        jMenu1.setText("Cadastros");
        jMenu1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jMenu1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMenu1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jMenu1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        mmCadAssociado.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        mmCadAssociado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/soc.jpg"))); // NOI18N
        mmCadAssociado.setText("Associado");
        mmCadAssociado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mmCadAssociadoActionPerformed(evt);
            }
        });
        jMenu1.add(mmCadAssociado);

        mmTipoAssociado.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        mmTipoAssociado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/tip.jpg"))); // NOI18N
        mmTipoAssociado.setText("Tipo Associado");
        jMenu1.add(mmTipoAssociado);

        msProduto.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        msProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/pd.jpg"))); // NOI18N
        msProduto.setText("Produto");
        jMenu1.add(msProduto);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/ped.png"))); // NOI18N
        jMenuItem1.setText("Pedido");
        jMenu1.add(jMenuItem1);

        jMBarra.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/mov.jpg"))); // NOI18N
        jMenu2.setText("Movimentações");
        jMenu2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMenu2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jMBarra.add(jMenu2);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/rel.jpg"))); // NOI18N
        jMenu3.setText("Relatorios");
        jMenu3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMenu3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jMBarra.add(jMenu3);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/sa.jpg"))); // NOI18N
        jMenu4.setText("Sair");
        jMenu4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMenu4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        mmSair.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.CTRL_MASK));
        mmSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/sai.jpg"))); // NOI18N
        mmSair.setText("Sair");
        mmSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mmSairActionPerformed(evt);
            }
        });
        jMenu4.add(mmSair);

        jMBarra.add(jMenu4);

        setJMenuBar(jMBarra);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(prdPrincipal)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(prdPrincipal)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mmCadAssociadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mmCadAssociadoActionPerformed
        // chamando um outra janela
        try {

            if (janela == null) {
                janela = new Associado_cadastro(prdPrincipal);
            }
            prdPrincipal.add(janela);
            janela.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_mmCadAssociadoActionPerformed

    private void mmSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mmSairActionPerformed
        fecharAplicacao();
    }//GEN-LAST:event_mmSairActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        fecharAplicacao();
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Principal().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMBarra;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem mmCadAssociado;
    private javax.swing.JMenuItem mmSair;
    private javax.swing.JMenuItem mmTipoAssociado;
    private javax.swing.JMenuItem msProduto;
    private javax.swing.JDesktopPane prdPrincipal;
    // End of variables declaration//GEN-END:variables

    private void fecharAplicacao() {
        try {

            int resp = JOptionPane.showConfirmDialog(null, "Confirmar a saida do programa?", "AppClube",
                    JOptionPane.YES_NO_OPTION);
            if (resp == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

}
