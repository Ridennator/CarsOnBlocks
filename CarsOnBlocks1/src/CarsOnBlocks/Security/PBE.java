/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CarsOnBlocks.Security;

import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

/**
 *
 * @author doctmanso
 */
public class PBE {
     //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    //:::::::::::        PASSWORD BASED ENCRYPTATION                 :::::::::::
    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    /**
     * Cria um objecto de cifragem com PBE SHA1 e triple DES
     *
     * @param mode Cipher.DECRYPT_MODE ou Cipher.ENCRYPT_MODE
     * @param password password de da cifra
     * @return Objecto de cifragem
     * @throws Exception
     */
    //:::::::::::::::::::::::IMPORTANTE::::::::::::::::::
     //Cria um objecto que permite enclriptar e desencriptar dados utilizando uma password
    public static Cipher createCipherPBE(int mode, String password) throws Exception {
        //:::::::::   1 - gerar uma chave secreta  :::::::::::::::::::::::::::::::
        //transformar a password nos parametros na chave
        PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
        //algoritmo de cifragem
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithSHA1AndDESede");
        //gerar a chave
        SecretKey key = keyFactory.generateSecret(keySpec);
        //::::::::: 2 -  adicionar sal á chave  :::::::::::::::::::::::::::::::
        // usae o SHA1 para gerar um conjunto de  bytes a partir da passaord
        MessageDigest md = MessageDigest.getInstance("SHA1");
        md.update(password.getBytes());
        //usar os primeiros 8 bytes
        byte[] digest = Arrays.copyOf(md.digest(), 8);
        //fazer 1000 iterações com o sal
        PBEParameterSpec paramSpec = new PBEParameterSpec(digest, 1000);
        //3 - Gerar o objecto de cifragem
        Cipher cipher = Cipher.getInstance(key.getAlgorithm());
        //:::::::::   4 - iniciar a cifra ::::::::: ::::::::: ::::::::: ::::::::: 
        // iniciar o objeto de cifragem com os parâmetros
        cipher.init(mode, key, paramSpec);
        return cipher;
    }
    
    
    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    //:::::::::::               ENCRYPT /  DECRYPT                   :::::::::::
    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    /**
     * encripta dados usando uma password de texto
     *
     * @param data dados para encriptar
     * @param password password de encriptação
     * @return dados encriptados
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data, String password) throws Exception {
        //criar um objecto de cifragem da chave
        Cipher cipher = createCipherPBE(Cipher.ENCRYPT_MODE, password);
        //cifrar os dados
        return cipher.doFinal(data);
    }

    /**
     * desencripta dados usando uma password de texto
     *
     * @param data dados para desencriptar
     * @param password password de desencriptação
     * @return dados desencriptados
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data, String password) throws Exception {
        //criar um objecto de cifragem da chave
        Cipher cipher = createCipherPBE(Cipher.DECRYPT_MODE, password);
        //cifrar os dados
        return cipher.doFinal(data);
    }

    
}
