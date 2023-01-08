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
package templarCoin.core;

import blockChain.chain.Block;
import blockChain.chain.BlockChain;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manso
 */
public class TemplarCoin implements Serializable {
    //data structure to suport  ledger
    //private final ArrayList<Transaction> ledger;

    private BlockChain ledger;

    public BlockChain getBlockChain() {
        return ledger;
    }

    public void setBlockChain(BlockChain b) {

        ledger = b;
    }

    public TemplarCoin() throws Exception {
        ledger = new BlockChain();
        //by default Master have 1000 coins
        // ledger.add(new Transaction("System", "Master", 1000));
        User system = User.load("System", "");
        User master = User.load("Master", "");
        Transaction genesis = new Transaction(system.getBase64PublicKey(),
                master.getBase64PublicKey(), 1000);
        genesis.sign(system.getPrivKey());
        ledger.add(genesis.toBase64(), DIFICULTY);

    }

    /**
     * gets the leger
     *
     * @return
     */
    public List<Transaction> getLedger() throws Exception {
        List<Transaction> lst = new ArrayList<>();
        for (Block b : ledger.getChain()) {
            try {
                Transaction t = Transaction.fromBase64(b.getData());
                lst.add(t);
            } catch (Exception e) {
            }

        }
        return lst;
        //return ledger;
    }

    public String toString() {

        StringBuilder txt = new StringBuilder();
        try {
            List<Transaction> ledger = getLedger();
            for (Transaction transaction : ledger) {
                txt.append(transaction.toString() + "\n");
            }

        } catch (Exception ex) {
        }
        return txt.toString();
    }

    /**
     * saves templarCoin to file
     *
     * @param fileName name of the file
     * @throws IOException
     */
    public void save(String fileName) throws IOException {
        try ( ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(fileName))) {
            out.writeObject(this);
        }
    }

    /**
     * load ledger from the file
     *
     * @param fileName name of the file
     * @return TemplarCoin
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static TemplarCoin load(String fileName) throws IOException, ClassNotFoundException {
        try ( ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(fileName))) {
            return (TemplarCoin) in.readObject();
        }
    }

    /**
     * gets the amount of a user
     *
     * @param user user name
     * @return amount
     */
    public double getAmount(String user) {
        double total = 0;
        try {

            List<Transaction> ledger = getLedger();
            for (Transaction transaction : ledger) {
                try {
                    //F R O M
                    if (transaction.getFrom().equals(user)) {
                        total -= transaction.getValue();
                    }// T O
                    else if (transaction.getTo().equals(user)) {
                        total += transaction.getValue();
                    }
                } catch (Exception e) {
                }

            }

        } catch (Exception ex) {
            Logger.getLogger(TemplarCoin.class.getName()).log(Level.SEVERE, null, ex);
        }

        return total;
    }

    public boolean isValid(Transaction t) throws Exception {
        if (t.getFrom().equals(t.getTo())) {
            throw new Exception("The users are equal ");
        }
        if (t.getValue() <= 0) {
            throw new Exception("Value " + t.getValue() + " is invalid");
        }
        if (t.getFrom().trim().isEmpty()) {
            throw new Exception("From user is empty");
        }
        if (t.getTo().trim().isEmpty()) {
            throw new Exception("To user is empty");
        }
        double value = getAmount(t.getFrom());
        if (t.getValue() > value) {
            throw new Exception(t.getFrom() + " not have founds \n"
                    + t.getValue() + " is larger than " + value);
        }
        return t.getValue() <= value;
    }

    /**
     * add a transaction to the ledger
     *
     * @param t Transactiob
     * @throws Exception
     */
    public void add(Transaction t) throws Exception {
        if (isValid(t)) {
            ledger.add(t.toBase64(), DIFICULTY);
        } else {
            throw new Exception("Transaction not valid");
        }
    }

    /**
     * get list of users with amount
     *
     * @return list of users with amount
     */
    public List<String> getUsers() {

        ArrayList<String> balance = new ArrayList<>();
        try {
            ArrayList<String> users = new ArrayList<>();
            List<Transaction> ledger = getLedger();
            //get Users
            for (Transaction transaction : ledger) {
                if (!users.contains(transaction.getFrom())) {
                    users.add(transaction.getFrom());
                }
                if (!users.contains(transaction.getTo())) {
                    users.add(transaction.getTo());
                }
            }
            //get amount of users

            for (String user : users) {
                balance.add(String.format("%8.2f - %s", getAmount(user), user));
            }

        } catch (Exception ex) {
            Logger.getLogger(TemplarCoin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return balance;
    }

    public static int DIFICULTY = 3;

    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    private static final long serialVersionUID = 202211011644L;
    //:::::::::::::::::::::::::::  Copyright(c) M@nso  2022  :::::::::::::::::::
    ///////////////////////////////////////////////////////////////////////////

}
