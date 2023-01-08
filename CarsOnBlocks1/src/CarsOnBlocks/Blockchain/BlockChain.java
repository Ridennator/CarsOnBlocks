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
package CarsOnBlocks.Blockchain;

import MainPackage.CarInfo;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created on 22/08/2022, 10:09:17
 *
 * @author IPT - computer
 * @version 1.0
 */

// Classe que faz a gestão da técnologia blockchain com o suporte da classe 'Block'
public class BlockChain implements Serializable {
    
    // Variável ArrayList de Blocks que servirá como identificador da blockchain do sistema.
    ArrayList<Block> chain = new ArrayList<>();

    public ArrayList<Block> getChain() {
        return chain;
    }

    /**
     * gets the last block hash of the chain
     * @return last hash in the chain
     */
    public String getLastBlockHash() {
        //Genesis block
        if (chain.isEmpty()) {
            return String.format("%08d", 0);
        }
        //hash of the last in the list
        return chain.get(chain.size() - 1).currentHash;
    }
    /**
     * adds data to the blockChain
     * @param data data to add in the block
     * @param dificulty dificulty of block to miners (POW)
     */
    public void add(CarInfo carinfo, int dificulty) throws Exception {
        //hash of previous block
        String prevHash = getLastBlockHash();
        //mining block
        int nonce = Miner.getNonce(prevHash + carinfo, dificulty);
        // int nonce = miner.mine(txtMessage.getText(), (int) spZeros.getValue());
        //build new block
        Block newBlock = new Block(prevHash, carinfo, nonce);
        //add new block to the chain
        chain.add(newBlock);
    }

    public Block get(int index) {
        return chain.get(index);
    }

    public String toString() {
        StringBuilder txt = new StringBuilder();
        txt.append("Blockchain size = " + chain.size() + "\n");
        for (Block block : chain) {
            txt.append(block.toString() + "\n");
        }
        return txt.toString();
    }

    public void save() throws Exception {
        try {
            FileOutputStream writeData = new FileOutputStream("data/carInfoRegistry.ser");
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

            writeStream.writeObject(chain);
            writeStream.flush();
            writeStream.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Erro a guardar registo: Blockchain");
        }
    }

    public void load() throws Exception {
        try { 
            FileInputStream readData = new FileInputStream("data/carInfoRegistry.ser");
            ObjectInputStream readStream = new ObjectInputStream(readData);
            chain = (ArrayList<Block>) readStream.readObject();
            readStream.close();
            } catch (Exception e){
                System.out.println("Não há blockchain registada.");
            }
    }

    public boolean isValid() throws Exception {
        //Validate blocks
        for (Block block : chain) {
            if (!block.isValid()) {
                return false;
            }
        }
        //validate Links
        //starts in the second block
        for (int i = 1; i < chain.size(); i++) {
            //previous hash !=  hash of previous
            if (chain.get(i).previousHash != chain.get(i - 1).currentHash) {
                return false;
            }
        }
        return true;
    }

    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    private static final long serialVersionUID = 202208221009L;
    //:::::::::::::::::::::::::::  Copyright(c) M@nso  2022  :::::::::::::::::::
    ///////////////////////////////////////////////////////////////////////////
}
