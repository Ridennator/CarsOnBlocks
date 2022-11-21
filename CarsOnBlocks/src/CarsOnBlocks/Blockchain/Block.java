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
import java.io.Serializable;

/**
 * Created on 22/08/2022, 09:23:49
 * 
 * Block with consensus of Proof of Work
 *
 * @author IPT - computer
 * @version 1.0
 */
public class Block implements Serializable {

    String previousHash; // link to previous block
    CarInfo carInfo;     // data in the block
    int nonce;           // proof of work 
    String currentHash;  // Hash of block

    public Block(String previousHash, CarInfo carInfo, int nonce) throws Exception {
        this.previousHash = previousHash;
        this.carInfo = carInfo;
        this.nonce = nonce;
        this.currentHash = calculateHash();
    }

    public String calculateHash() throws Exception {
        return Hash.getHash(nonce + previousHash + carInfo);
    }

    public String toString() {
        return // (isValid() ? "OK\t" : "ERROR\t")+
                 String.format("[ %8s", previousHash) + " <- " + 
                   String.format("%-10s", carInfo) +  String.format(" %7d ] = ", nonce) + 
                String.format("%8s",currentHash);

    }

    public boolean isValid() throws Exception {
        return currentHash.equals(calculateHash());
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public CarInfo getCarInfo() {
        return carInfo;
    }

    public int getNonce() {
        return nonce;
    }

    public String getCurrentHash() {
        return currentHash;
    }
    
    public String getFullInfo(){
        return "Previous :\n\t " + previousHash+
                "\ndata  :\n\t" + carInfo +
                "\nNonce : " + nonce+
                "\nHash  :\n\t"+ currentHash;
    }

    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    private static final long serialVersionUID = 202208220923L;
    //:::::::::::::::::::::::::::  Copyright(c) M@nso  2022  :::::::::::::::::::
    ///////////////////////////////////////////////////////////////////////////

}
