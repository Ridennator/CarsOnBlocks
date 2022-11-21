//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: 
//::                                                                         ::
//::     Antonio Manuel Rodrigues Manso                                      ::
//::                                                                         ::
//::     Biosystems & Integrative Sciences Institute                         ::
//::     Faculty of Sciences University of Lisboa                            ::
//::     http://www.fc.ul.pt/en/unidade/bioisi                               ::
//::                                                                         ::
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

package CarsOnBlocks.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created on 02/11/2022, 18:30:10 
 * @author aulas - computer
 */
public class Serializer {


    /**
     * converts an object to byte array
     *
     * @param object
     * @return byte array
     */
    public static byte[] objectToBytes(Object object) {
        try ( ByteArrayOutputStream bos = new ByteArrayOutputStream();  ObjectOutputStream out = new ObjectOutputStream(bos)) {
            out.writeObject(object);
            return bos.toByteArray();
        } catch (Exception e) { // not seralizable
            return new byte[]{0};
        }
    }

    /**
     * converts an byte array to byte Array
     *
     * @param data
     * @return byte array
     */
    public static Object bytesToObject(byte[] data) {
        try ( ByteArrayInputStream bin = new ByteArrayInputStream(data);  ObjectInputStream in = new ObjectInputStream(bin)) {
            return in.readObject();
        } catch (Exception e) { // not seralizable
            return null;
        }
    }
    
    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    private static final long serialVersionUID = 202211021830L;
    //:::::::::::::::::::::::::::  Copyright(c) M@nso  2022  :::::::::::::::::::
    ///////////////////////////////////////////////////////////////////////////
}