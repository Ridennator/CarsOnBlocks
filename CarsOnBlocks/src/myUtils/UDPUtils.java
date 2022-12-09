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
package myUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created on 14/12/2021, 18:40:31
 *
 * @author IPT - computer
 * @version 1.0
 */
public class UDPUtils {

//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::.
//::                                                                          ::
//::            U D P   S O C K E T S                                         ::
//::                                                         (c) m@nso2021    ::
//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
////////////////////////////////////////////////////////////////////////////////      
    /**
     * sends objects to ip:port with udp sockets
     *
     * @param adresss address of ojects destination
     * @param port port of ojects destination
     * @param object object to send
     * @return returns a DatagramPacket with the adrees of the sokect used by sending object
     * @throws Exception
     */
    public static DatagramPacket sendUDPobject(String adresss, int port, Serializable object) throws Exception {
        //socket para enviar os dados -> porta automática
        DatagramSocket socket = new DatagramSocket();
        //construir um pacote para enviar dados para o endereço
        InetAddress adress = InetAddress.getByName(adresss);
        //para todos os objetos a serem enviados
        System.out.print("Sending " + object + " to " + adresss + ":" + port);
        //converter o objeto em array de bytes
        byte[] data = objectToByteArray(object);
        //contruir o pacote ( dados + endereço)
        DatagramPacket pak = new DatagramPacket(data, data.length, adress, port);
        //enviar o pacote
        socket.send(pak);
        System.out.println(".... Done!");

        //modificar o endereço e o porto do pacote
        pak.setPort(socket.getLocalPort());
        pak.setAddress(socket.getInetAddress());
        //fechar o socket
        socket.close();
        return pak;
    }

    /**
     * reads an object from a udp socket
     *
     * @return object readed
     * @throws Exception
     */
    public static Object getUDPobject(int port) throws Exception {
        //contruir um pacote para receber dados
        byte[] array = new byte[4096]; // máximo 4096 bytes
        DatagramPacket data = new DatagramPacket(array, array.length);
        //construir um socket no porto 10.010
        DatagramSocket socket = new DatagramSocket(port);
        System.out.println("Listen UDP port " + port);
        //esperar pelo objeto
        socket.receive(data);
        System.out.println("Message from " + data.getAddress() + ":" + data.getPort());
        //fechar o socket
        socket.close();
        // converter os bytes em objeto
        return byteArrayToObject(data.getData());
    }

    /**
     * reads an object from a udp socket
     *
     * @param port port to read
     * @return Datagram Readed
     * @throws Exception
     */
    public static DatagramPacket getDatagramPacket(int port) throws Exception {
        //contruir um pacote para receber dados
        byte[] array = new byte[4096]; // máximo 4096 bytes
        DatagramPacket data = new DatagramPacket(array, array.length);
        //construir um socket no porto 10.010
        DatagramSocket socket = new DatagramSocket(port);
        System.out.println("Listen UDP port " + port);
        //esperar pelo objeto
        socket.receive(data);
        //fechar o socket
        socket.close();
        // converter os bytes em objetos
        return data;
    }

    public static Serializable getObject(DatagramPacket packet) throws Exception {
        return byteArrayToObject(packet.getData());
    }

//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
//::                                                                          ::
//::           S E R I A L I Z E R                                            ::
//::                                                         (c) m@nso2019    ::
//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
////////////////////////////////////////////////////////////////////////////////   
    /**
     * converte um objecto num array de bytes
     *
     * @param obj Serializable object
     * @return array of bytes
     * @throws IOException
     */
    public static byte[] objectToByteArray(Serializable obj) throws IOException {
        //stream em memoria
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        //stream de objectos
        ObjectOutputStream o = new ObjectOutputStream(b);
        //escrever o objecto na stream
        o.writeObject(obj);
        //converter para bytes
        return b.toByteArray();
    }

    /**
     * converte array de bytes num objecto
     *
     * @param bytes array of bytes
     * @return Serializable object
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Serializable byteArrayToObject(byte[] bytes) throws Exception {
        //stream em memoria
        ByteArrayInputStream b = new ByteArrayInputStream(bytes);
        //stream de objectos
        ObjectInputStream o = new ObjectInputStream(b);
        //ler o objecto da stream
        return (Serializable) o.readObject();
    }
    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    private static final long serialVersionUID = 202112141840L;
    //:::::::::::::::::::::::::::  Copyright(c) M@nso  2021  :::::::::::::::::::
    ///////////////////////////////////////////////////////////////////////////
}
