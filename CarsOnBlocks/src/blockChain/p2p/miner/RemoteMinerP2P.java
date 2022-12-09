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

package blockChain.p2p.miner;

import blockChain.miner.Miner;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author aluno
 */
public class RemoteMinerP2P extends UnicastRemoteObject implements IminerRemoteP2P {

    private RemoteMiningListenerP2P listener; // listener
    private Miner miner; //objeto para minar
    private List<IminerRemoteP2P> network; // network of miners
    private String address; // nome do servidor

    /**
     * constructor
     *
     * @param port number of the port
     * @param listener
     * @throws RemoteException
     */
    public RemoteMinerP2P(int port, RemoteMiningListenerP2P listener) throws RemoteException {
        //passar a porta para superclasse
        super(port);
        try {
            this.listener = listener;
            //criar um mineiro
            miner = new Miner(listener);
            //criar a lista da rede
            network = new CopyOnWriteArrayList<>();
            //atualizar o endereço do objeto remoto
            address = "//" + InetAddress.getLocalHost().getHostAddress() + ":" + port + "/" + IminerRemoteP2P.NAME;

        } catch (UnknownHostException e) {
            address = "unknow" + ":" + port;
        }
        //:::::::::: Notificar o listener ::::::::::::::
        if (listener != null) {
            listener.onStart(this);
        }
    }

    //:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::  
    //:::::                                                         :::::::::::::
    //:::::               E N C A P S U L A M E N T O 
    //:::::                                                         :::::::::::::
    //:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    /**
     * gets the nonce of miner
     *
     * @return
     * @throws RemoteException
     */
    @Override
    public int getNonce() throws RemoteException {
        return miner.getNonce();
    }

    /**
     * gets the nonce of miner
     *
     * @return
     * @throws RemoteException
     */
    @Override
    public String getMessage() throws RemoteException {
        return miner.getMessage();
    }

    /**
     * verify if miner is mining
     *
     * @return is mining
     * @throws RemoteException
     */
    @Override
    public boolean isMining() throws RemoteException {
        return miner.isMining();
    }

    @Override
    public String getHash() throws RemoteException {
        return miner.getHash();
    }

    @Override
    public int getTicketNumber() throws RemoteException {
        return miner.getTicket();
    }

    @Override
    public String getAdress() {
        return address;
    }

    //:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    //:::::                                                         :::::::::::::
    //:::::                R E D E   M I N E I R A 
    //:::::                                                         :::::::::::::
    //:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    @Override
    public void addNode(IminerRemoteP2P ir) throws RemoteException {
        //remover os nós que não respondem  
        for (IminerRemoteP2P remote : network) {
            try {
                remote.getAdress();
            } catch (Exception ex) {
                //something is wrong
                network.remove(remote);
                 if (listener != null) {
                    listener.onException("Remove Node Network", ex);
                }
            }
        }

        //se a rede não tiver o no
        if (!network.contains(ir)) {
            //adicionar o mineiro
            network.add(ir);
            //espalhar o mineiro pela rede
            //para cada no remoto
            for (IminerRemoteP2P remote : network) {

                //adicionar o novo no ao remoto
                remote.addNode(ir); // pedir para adiconar o novo nó
                //adicionar o this ao remoto
                remote.addNode(this); // pedir para adcionar o proprio no
                if (listener != null) {
                    listener.onMessage("Add Node to Remote", remote.getAdress());
                }

            }
            //:::::::::: Notificar o listener ::::::::::::::
            if (listener != null) {
                listener.onAddNode(ir);
            }
        }
    }
//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

    /**
     * compara se dois objetos remotos são igauis Necessário para verificar se o
     * nó está numa coleção
     *
     * @param remote
     * @return
     */
    @Override
    public boolean equals(Object remote) {
        try {
            //comparar o endereço dos objetos remotos
            return ((IminerRemoteP2P) remote).getAdress().equals(getAdress());
        } catch (RemoteException ex) {
            return false;
        }
    }

    //:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    /**
     * retorna a lista dos nos
     *
     * @return
     * @throws RemoteException
     */
    @Override
    public List<IminerRemoteP2P> getNetwork() throws RemoteException {
        return network;
    }

    //:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    //:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::  
    //:::::                                                         :::::::::::::
    //:::::                M I N A R       B L O C O S 
    //:::::                                                         :::::::::::::
    //:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    //:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    //:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::  
    //:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::    
    /**
     * calculate the nonce of the mssage
     *
     * @param message messagem
     * @param zeros number of zeros
     * @return nonce
     * @throws RemoteException
     */
    @Override
    public int mine(String message, int zeros) throws RemoteException {
        //executar a mineração com o mineiro
        try {
            startMining(message, zeros);
            return miner.waitToNonce();
        } catch (Exception ex) {
            throw new RemoteException("mining", ex);
        }
    }

    /**
     * Start mining the message
     *
     * @param message message
     * @param zeros number of zeros
     * @throws RemoteException
     */
    @Override
    public void startMining(String message, int zeros) throws RemoteException {
        try {
            //se já estiver a minar - Não faz nada
            if (miner.isMining()) {
                return;
            }
            //colocar o mineiro a minar
            miner.startMining(message, zeros);

            //Por todos os mineiros da rede a minar
            for (IminerRemoteP2P remote : network) {
                remote.startMining(message, zeros);
            }
            //:::::::::: Notificar o listener ::::::::::::::
            listener.onStartMining(message, zeros);

        } catch (Exception ex) {
            throw new RemoteException("Start mining", ex);
        }
    }

    /**
     * Stop mining the message
     *
     * @param nonce nonce of message
     * @throws RemoteException
     */
    @Override
    public void stopMining(int nonce) throws RemoteException {
        //se o mineiro estiver a minar
        if (miner.isMining()) {
            //parar o mineiro
            miner.stopMining(nonce);
            //:::::::::: Notificar o listener ::::::::::::::
            listener.onStopMining(nonce);
            listener.onMessage("Stop My Miner",getAdress());
        }
        //parar todos os mineiros da rede rede
        for (IminerRemoteP2P remote : network) {
            //se o mineiro remoro estiver a minar
            if (remote.isMining()) {
                //parar o mineiro remoto
                remote.stopMining(nonce);
                listener.onStopMining(remote.getNonce());
                listener.onMessage("Stop Remote Mining", remote.getAdress());
            }
        }

    }

}
