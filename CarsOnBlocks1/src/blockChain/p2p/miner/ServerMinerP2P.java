/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package blockChain.p2p.miner;

import myUtils.RMI;

/**
 *
 * @author aluno
 */
public class ServerMinerP2P {

    public static final int PORT = 10012;
    public static final String NAME = "miner";

    public static void main(String[] args) throws Exception {
        RemoteMinerP2P remote = new RemoteMinerP2P(PORT, null);
        RMI.startRemoteObject(remote, PORT, NAME);

    }

}
