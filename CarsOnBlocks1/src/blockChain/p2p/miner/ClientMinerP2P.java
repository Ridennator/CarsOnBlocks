/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockChain.p2p.miner;

import blockChain.miner.Miner;
import myUtils.RMI;

/**
 *
 * @author manso
 */
public class ClientMinerP2P {

    public static void main(String[] args) throws Exception {
        String message = "Hello distributed World";
        System.out.println("Message = " + message);
        IminerRemoteP2P remoteMiner = (IminerRemoteP2P) RMI.getRemote("localhost", 10012, "miner");
        int nonce = remoteMiner.mine(message, 4);        
        System.out.println("Nonce = " + nonce);
        System.out.println("HASH  = " + Miner.getHash(message,nonce));
    }

}
