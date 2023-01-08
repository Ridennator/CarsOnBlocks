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
//::                                                               (c)2022   ::
//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
//////////////////////////////////////////////////////////////////////////////
package templarCoin.gui;

import blockChain.chain.Block;
import blockChain.p2p.miner.InterfaceRemoteMiner;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import templarCoin.core.TemplarCoin;
import templarCoin.core.Transaction;
import templarCoin.core.User;

/**
 *
 * @author manso
 */
public class TemplarCoinGUI extends javax.swing.JFrame {

    public static String fileTemplarCoin = "templarCoin.obj";
    TemplarCoin coin;
    User myUser;

    /**
     * Creates new form TemplarCoinGUI
     */
    public TemplarCoinGUI(User user) {
        initComponents();
        this.myUser = user;
        try {
            coin = new TemplarCoin();
            try {

                InterfaceRemoteMiner miner = user.getMiner();

                if (miner.getChainSize() <= 1) {
                    //if( b.getChain().size() < 2){
                    //by default Master have 1000 coins
                    // ledger.add(new Transaction("System", "Master", 1000));
                    User system = User.load("System", "");
                    User master = User.load("Master", "");
                    Transaction genesis = new Transaction(system.getBase64PublicKey(),
                            master.getBase64PublicKey(), 1000);
                    genesis.sign(system.getPrivKey());
                    miner.mine(genesis.toBase64(), 3);
                }

                coin.setBlockChain(user.getMiner().getBlockChain());
                new Thread(
                        () -> {
                            //codigo run da thread
                            while (true) {
                                try {
                                    if (miner.getChainSize() > coin.getBlockChain().getChain().size()) {
                                        coin.setBlockChain(miner.getBlockChain());
                                        coin.save(fileTemplarCoin);
                                        SwingUtilities.invokeLater(
                                                () -> {

                                            try {
                                                tpBlockchainStateChanged(null);
                                                pnTransactionsUserStateChanged(null);
                                                txtLeger.setText(coin.toString());
                                                coin.save(fileTemplarCoin);
                                            } catch (IOException ex) {
                                                Logger.getLogger(TemplarCoinGUI.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                                }
                                        );

                                    }
                                    Thread.sleep(1000);
                                } catch (Exception ex) {
                                }

                            }
                        }
                ).start();

            } catch (Exception e) {
            }
            displayUser();
            txtLeger.setText(coin.toString());
            setSize(800, 800);
            setLocationRelativeTo(null);
        } catch (Exception e) {
        }
    }

    public void displayUser() {
        List<User> lst = User.getUserList();
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addAll(lst);
        cbTo.setModel(model);

        String pubB64 = Base64.getEncoder().encodeToString(
                myUser.getPubKey().getEncoded());
        txtFrom.setText(pubB64);
        txtInfo.setText(myUser.getInfo());
        txtPrivateKey.setText(Base64.getEncoder().encodeToString(
                myUser.getPrivKey().getEncoded()));
        txtPublicKey.setText(pubB64);
        txtSecretKey.setText(Base64.getEncoder().encodeToString(
                myUser.getKey().getEncoded()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnUserTransactions = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        lstUserTransactions = new javax.swing.JList<>();
        jScrollPane10 = new javax.swing.JScrollPane();
        txtUserTransactions = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        txtBalance = new javax.swing.JLabel();
        tpBlockchain = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtLeger = new javax.swing.JTextArea();
        pnBlockChain = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        lstBlockchain = new javax.swing.JList<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtBlock = new javax.swing.JTextArea();
        pnTransactionsUser = new javax.swing.JTabbedPane();
        pnTrans = new javax.swing.JPanel();
        pnTransaction = new javax.swing.JPanel();
        txtFrom = new javax.swing.JTextField();
        cbTo = new javax.swing.JComboBox<>();
        txtValue = new javax.swing.JTextField();
        txtSignature = new javax.swing.JTextField();
        btRegister = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        pnUsersBalance = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstUsers = new javax.swing.JList<>();
        pnUser = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtInfo = new javax.swing.JTextArea();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtPublicKey = new javax.swing.JTextArea();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtPrivateKey = new javax.swing.JTextArea();
        jScrollPane8 = new javax.swing.JScrollPane();
        txtSecretKey = new javax.swing.JTextArea();

        pnUserTransactions.setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.GridLayout(2, 1, 10, 10));

        jScrollPane9.setBorder(javax.swing.BorderFactory.createTitledBorder("Transactions"));

        lstUserTransactions.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane9.setViewportView(lstUserTransactions);

        jPanel1.add(jScrollPane9);

        jScrollPane10.setBorder(javax.swing.BorderFactory.createTitledBorder("Transaction Detail"));

        txtUserTransactions.setColumns(20);
        txtUserTransactions.setRows(5);
        jScrollPane10.setViewportView(txtUserTransactions);

        jPanel1.add(jScrollPane10);

        pnUserTransactions.add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setLayout(new java.awt.BorderLayout());

        txtBalance.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        txtBalance.setText("0");
        txtBalance.setBorder(javax.swing.BorderFactory.createTitledBorder("Balance"));
        jPanel2.add(txtBalance, java.awt.BorderLayout.CENTER);

        pnUserTransactions.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Templar Coin");

        tpBlockchain.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tpBlockchainStateChanged(evt);
            }
        });

        txtLeger.setColumns(20);
        txtLeger.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        txtLeger.setRows(5);
        jScrollPane1.setViewportView(txtLeger);

        tpBlockchain.addTab("Ledger", new javax.swing.ImageIcon(getClass().getResource("/templarCoin/multimedia/blockChain.png")), jScrollPane1); // NOI18N

        pnBlockChain.setLayout(new java.awt.BorderLayout());

        jScrollPane3.setPreferredSize(new java.awt.Dimension(200, 146));

        lstBlockchain.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        lstBlockchain.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        lstBlockchain.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstBlockchainValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(lstBlockchain);

        pnBlockChain.add(jScrollPane3, java.awt.BorderLayout.WEST);

        txtBlock.setEditable(false);
        txtBlock.setColumns(20);
        txtBlock.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        txtBlock.setLineWrap(true);
        txtBlock.setRows(5);
        txtBlock.setWrapStyleWord(true);
        jScrollPane4.setViewportView(txtBlock);

        pnBlockChain.add(jScrollPane4, java.awt.BorderLayout.CENTER);

        tpBlockchain.addTab("BlockChainExplorer", new javax.swing.ImageIcon(getClass().getResource("/templarCoin/multimedia/Block.png")), pnBlockChain); // NOI18N

        getContentPane().add(tpBlockchain, java.awt.BorderLayout.CENTER);

        pnTransactionsUser.setMinimumSize(new java.awt.Dimension(160, 500));
        pnTransactionsUser.setPreferredSize(new java.awt.Dimension(360, 500));
        pnTransactionsUser.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                pnTransactionsUserStateChanged(evt);
            }
        });

        pnTrans.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnTrans.setLayout(new java.awt.BorderLayout());

        pnTransaction.setLayout(new java.awt.GridLayout(5, 1, 5, 5));

        txtFrom.setEditable(false);
        txtFrom.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        txtFrom.setBorder(javax.swing.BorderFactory.createTitledBorder("From"));
        pnTransaction.add(txtFrom);

        cbTo.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        cbTo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        pnTransaction.add(cbTo);

        txtValue.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        txtValue.setText("0.0");
        txtValue.setBorder(javax.swing.BorderFactory.createTitledBorder("Value"));
        pnTransaction.add(txtValue);

        txtSignature.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        txtSignature.setBorder(javax.swing.BorderFactory.createTitledBorder("Signature"));
        pnTransaction.add(txtSignature);

        btRegister.setIcon(new javax.swing.ImageIcon(getClass().getResource("/templarCoin/multimedia/cash-icon.png"))); // NOI18N
        btRegister.setText("Register");
        btRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRegisterActionPerformed(evt);
            }
        });
        pnTransaction.add(btRegister);

        pnTrans.add(pnTransaction, java.awt.BorderLayout.NORTH);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/templarCoin/multimedia/templarCoin.png"))); // NOI18N
        jLabel1.setText("logout");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        pnTrans.add(jLabel1, java.awt.BorderLayout.CENTER);

        pnTransactionsUser.addTab("Transaction", new javax.swing.ImageIcon(getClass().getResource("/templarCoin/multimedia/templar.png")), pnTrans); // NOI18N

        pnUsersBalance.setLayout(new java.awt.BorderLayout());

        lstUsers.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        lstUsers.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(lstUsers);

        pnUsersBalance.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        pnTransactionsUser.addTab("Users Balance", new javax.swing.ImageIcon(getClass().getResource("/templarCoin/multimedia/users_ledger_24.png")), pnUsersBalance); // NOI18N

        pnUser.setLayout(new java.awt.BorderLayout());

        txtInfo.setColumns(20);
        txtInfo.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        txtInfo.setLineWrap(true);
        txtInfo.setRows(5);
        txtInfo.setWrapStyleWord(true);
        jScrollPane5.setViewportView(txtInfo);

        jTabbedPane2.addTab("Info", jScrollPane5);

        txtPublicKey.setColumns(20);
        txtPublicKey.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        txtPublicKey.setLineWrap(true);
        txtPublicKey.setRows(5);
        txtPublicKey.setWrapStyleWord(true);
        jScrollPane6.setViewportView(txtPublicKey);

        jTabbedPane2.addTab("Public Key", jScrollPane6);

        txtPrivateKey.setColumns(20);
        txtPrivateKey.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        txtPrivateKey.setLineWrap(true);
        txtPrivateKey.setRows(5);
        txtPrivateKey.setWrapStyleWord(true);
        jScrollPane7.setViewportView(txtPrivateKey);

        jTabbedPane2.addTab("Private Key", jScrollPane7);

        txtSecretKey.setColumns(20);
        txtSecretKey.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        txtSecretKey.setLineWrap(true);
        txtSecretKey.setRows(5);
        txtSecretKey.setWrapStyleWord(true);
        jScrollPane8.setViewportView(txtSecretKey);

        jTabbedPane2.addTab("Secret Key", jScrollPane8);

        pnUser.add(jTabbedPane2, java.awt.BorderLayout.CENTER);

        pnTransactionsUser.addTab("User", new javax.swing.ImageIcon(getClass().getResource("/templarCoin/multimedia/user.png")), pnUser); // NOI18N

        getContentPane().add(pnTransactionsUser, java.awt.BorderLayout.WEST);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRegisterActionPerformed
        try {
            User to = (User) cbTo.getSelectedObjects()[0];
            String txtPub = Base64.getEncoder().encodeToString(to.getPubKey().getEncoded());
            Transaction t = new Transaction(
                    txtFrom.getText(),
                    txtPub,
                    Double.valueOf(txtValue.getText())
            );
            t.sign(myUser.getPrivKey());
            txtSignature.setText(t.getSignature());

            myUser.getMiner().startMining(t.toBase64(), 3);
            txtLeger.setText(coin.toString());
            coin.save(fileTemplarCoin);
            JOptionPane.showMessageDialog(this, "transaction registered");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
            Logger.getLogger(TemplarCoinGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btRegisterActionPerformed

    private void pnTransactionsUserStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_pnTransactionsUserStateChanged
        if (pnTransactionsUser.getSelectedComponent() == pnUsersBalance) {
            DefaultListModel model = new DefaultListModel();
            model.addAll(coin.getUsers());
            lstUsers.setModel(model);
        }
    }//GEN-LAST:event_pnTransactionsUserStateChanged

    private void tpBlockchainStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tpBlockchainStateChanged
        if (tpBlockchain.getSelectedComponent() == pnBlockChain) {
            DefaultListModel model = new DefaultListModel();
            model.addAll(coin.getBlockChain().getChain());
            lstBlockchain.setModel(model);
        }
    }//GEN-LAST:event_tpBlockchainStateChanged

    private void lstBlockchainValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstBlockchainValueChanged
        if (lstBlockchain.getSelectedIndex() >= 0) {
            Block b = (Block) lstBlockchain.getSelectedValues()[0];
            txtBlock.setText(b.getFullInfo());
        }
    }//GEN-LAST:event_lstBlockchainValueChanged

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        this.dispose();
        new Autentication().setVisible(true);
    }//GEN-LAST:event_jLabel1MouseClicked

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(TemplarCoinGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(TemplarCoinGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(TemplarCoinGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(TemplarCoinGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new TemplarCoinGUI().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btRegister;
    private javax.swing.JComboBox<String> cbTo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JList<String> lstBlockchain;
    private javax.swing.JList<String> lstUserTransactions;
    private javax.swing.JList<String> lstUsers;
    private javax.swing.JPanel pnBlockChain;
    private javax.swing.JPanel pnTrans;
    private javax.swing.JPanel pnTransaction;
    private javax.swing.JTabbedPane pnTransactionsUser;
    private javax.swing.JPanel pnUser;
    private javax.swing.JPanel pnUserTransactions;
    private javax.swing.JPanel pnUsersBalance;
    private javax.swing.JTabbedPane tpBlockchain;
    private javax.swing.JLabel txtBalance;
    private javax.swing.JTextArea txtBlock;
    private javax.swing.JTextField txtFrom;
    private javax.swing.JTextArea txtInfo;
    private javax.swing.JTextArea txtLeger;
    private javax.swing.JTextArea txtPrivateKey;
    private javax.swing.JTextArea txtPublicKey;
    private javax.swing.JTextArea txtSecretKey;
    private javax.swing.JTextField txtSignature;
    private javax.swing.JTextArea txtUserTransactions;
    private javax.swing.JTextField txtValue;
    // End of variables declaration//GEN-END:variables
}
