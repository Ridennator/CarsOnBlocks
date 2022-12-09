//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: 
//::                                                                         ::
//::     Antonio Manuel Rodrigues Manso                                      ::
//::                                                                         ::
//::     I N S T I T U T O    P O L I T E C N I C O   D E   T O M A R        ::
//::     Escola Superior de Tecnologia de Tomar                              ::
//::     e-mail: manso@ipt.pt                                                ::
//::     url   : http://orion.ipt.pt/~manso                                  ::
//::                                                                         ::
//::     This software was build with the purpose of investigate and         ::
//::     learning.                                                           ::
//::                                                                         ::
//::                                                               (c)2021   ::
//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
//////////////////////////////////////////////////////////////////////////////

package blockChain.p2p.gui;

import blockChain.miner.Miner;

import java.awt.Color;
import myUtils.GuiUtils;
import myUtils.RMI;
import blockChain.p2p.miner.IminerRemoteP2P;

/**
 *
 * @author IPT
 */
public class RemoteClientP2P extends javax.swing.JFrame {

    IminerRemoteP2P miner;

    /**
     * Creates new form Test03_GUI_miner
     */
    public RemoteClientP2P() {
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        txtLog = new javax.swing.JTextPane();
        tpMain = new javax.swing.JTabbedPane();
        pnServer = new javax.swing.JPanel();
        pnStartServer = new javax.swing.JPanel();
        txtAddress = new javax.swing.JTextField();
        btStartServer = new javax.swing.JButton();
        pnMiner = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btMining = new javax.swing.JButton();
        spZeros = new javax.swing.JSpinner();
        txtNonce = new javax.swing.JTextField();
        txtHash = new javax.swing.JTextField();
        icon = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMessage = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Miner (c) M@nso0 2022");

        jScrollPane2.setPreferredSize(new java.awt.Dimension(64, 400));

        txtLog.setBackground(new java.awt.Color(0, 0, 0));
        txtLog.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Log", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        txtLog.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        jScrollPane2.setViewportView(txtLog);

        getContentPane().add(jScrollPane2, java.awt.BorderLayout.CENTER);

        pnStartServer.setLayout(new java.awt.GridLayout(2, 1, 5, 5));

        txtAddress.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        txtAddress.setText("//192.168.161.15:10010/miner");
        txtAddress.setBorder(javax.swing.BorderFactory.createTitledBorder("Server Address"));

        btStartServer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/blockChain/gui/images/start.png"))); // NOI18N
        btStartServer.setText("Connect To Server ");
        btStartServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btStartServerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnServerLayout = new javax.swing.GroupLayout(pnServer);
        pnServer.setLayout(pnServerLayout);
        pnServerLayout.setHorizontalGroup(
            pnServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnServerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btStartServer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtAddress, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnStartServer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(159, 159, 159))
        );
        pnServerLayout.setVerticalGroup(
            pnServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnStartServer, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(pnServerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btStartServer, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        tpMain.addTab("Server", pnServer);

        pnMiner.setLayout(new java.awt.BorderLayout());

        btMining.setIcon(new javax.swing.ImageIcon(getClass().getResource("/blockChain/gui/images/getNonce48.png"))); // NOI18N
        btMining.setText("mine");
        btMining.setPreferredSize(new java.awt.Dimension(200, 100));
        btMining.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btMiningActionPerformed(evt);
            }
        });

        spZeros.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        spZeros.setModel(new javax.swing.SpinnerNumberModel(3, 2, null, 1));
        spZeros.setBorder(javax.swing.BorderFactory.createTitledBorder("Zeros"));

        txtNonce.setEditable(false);
        txtNonce.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        txtNonce.setText("0");
        txtNonce.setBorder(javax.swing.BorderFactory.createTitledBorder("Nonce"));

        txtHash.setEditable(false);
        txtHash.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        txtHash.setText("0");
        txtHash.setBorder(javax.swing.BorderFactory.createTitledBorder("Hash"));

        icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/blockChain/gui/images/working.gif"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtHash)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btMining, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spZeros, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNonce, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(icon)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(spZeros, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btMining, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(txtNonce)
                    .addComponent(icon, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtHash, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnMiner.add(jPanel7, java.awt.BorderLayout.WEST);

        txtMessage.setColumns(20);
        txtMessage.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        txtMessage.setRows(5);
        txtMessage.setText("Hello Mining world!\nRemote World!");
        txtMessage.setBorder(javax.swing.BorderFactory.createTitledBorder("Message"));
        jScrollPane1.setViewportView(txtMessage);

        pnMiner.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        tpMain.addTab("Miner", pnMiner);

        getContentPane().add(tpMain, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btMiningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btMiningActionPerformed

        try {
            if (miner.isMining()) {
                miner.stopMining(9999);
                GuiUtils.insertText(txtLog, "Stop Mining", miner.getAdress());
            } else {
                new Thread(() -> {
                    try {
                        GuiUtils.insertText(txtLog, "Start Mining", miner.getAdress(), Color.GREEN, Color.WHITE);
                        txtNonce.setText("");
                        txtHash.setText("");
                        btMining.setText("Stop");
                        int nonce = miner.mine(txtMessage.getText(), (int) spZeros.getValue());
                        txtNonce.setText(nonce + "");
                        txtHash.setText(Miner.getHash(txtMessage.getText(), nonce));
                        btMining.setText("Start");
                    } catch (Exception ex) {
                        onException("Mining", ex);
                    }
                }).start();
            }
        } catch (Exception ex) {
            onException("Mining", ex);
        }


    }//GEN-LAST:event_btMiningActionPerformed

    private void btStartServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btStartServerActionPerformed
        try {
            miner = (IminerRemoteP2P) RMI.getRemote(txtAddress.getText());
            tpMain.setSelectedComponent(pnMiner);
            GuiUtils.insertText(txtLog, "Connected ", miner.getAdress(), Color.GREEN, Color.MAGENTA);
        } catch (Exception ex) {
            onException("Start Remote", ex);
        }
    }//GEN-LAST:event_btStartServerActionPerformed

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
            java.util.logging.Logger.getLogger(RemoteClientP2P.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RemoteClientP2P.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RemoteClientP2P.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RemoteClientP2P.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RemoteClientP2P().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btMining;
    private javax.swing.JButton btStartServer;
    private javax.swing.JLabel icon;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pnMiner;
    private javax.swing.JPanel pnServer;
    private javax.swing.JPanel pnStartServer;
    private javax.swing.JSpinner spZeros;
    private javax.swing.JTabbedPane tpMain;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtHash;
    private javax.swing.JTextPane txtLog;
    private javax.swing.JTextArea txtMessage;
    private javax.swing.JTextField txtNonce;
    // End of variables declaration//GEN-END:variables

    public void onException(String title, Exception ex) {
        GuiUtils.insertText(txtLog, title, ex.getMessage(), Color.RED, Color.MAGENTA);
    }

}
