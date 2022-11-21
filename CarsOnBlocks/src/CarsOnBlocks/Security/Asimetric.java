/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CarsOnBlocks.Security;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

/**
 *
 * @author doctmanso
 */
public class Asimetric {

    /**
     * encrypta dados utilizado uma chave binária
     *
     * @param data dados para encriptar
     * @param key chave de encriptação
     * @return dados encriptados
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data, Key key) throws Exception {
        //criar um objecto de cifragem da chave
        Cipher cipher = Cipher.getInstance(key.getAlgorithm());
        //configurar o objecto para cifrar
        cipher.init(Cipher.ENCRYPT_MODE, key);
        //cifrar os dados
        return cipher.doFinal(data);
    }

    /**
     * desencripta dados utilizado uma chave binária
     *
     * @param data dados para desencriptar
     * @param key chave de desencriptacao
     * @return dados desencriptados
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data, Key key) throws Exception {
        //criar um objecto de cifragem da chave
        Cipher cipher = Cipher.getInstance(key.getAlgorithm());
        //configurar o objecto para cifrar
        cipher.init(Cipher.DECRYPT_MODE, key);
        //decifrar os dados
        return cipher.doFinal(data);
    }

    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    //:::::::::::                    R S A                           :::::::::::
    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    /**
     * gera um par de chave RSA
     *
     * @param size tamanho da chave
     * @return par de chaves
     * @throws Exception
     */
    public static KeyPair generateKeyPair(int size) throws Exception {
        System.out.println("Generating RSA " + size + " keys ...");
        // gerador de chaves RSA
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        //tamanho da chave
        keyGen.initialize(size);
        return keyGen.generateKeyPair();
    }

    /**
     * Guarda uma chave num ficheiro
     *
     * @param key chave
     * @param fileName nome do ficheiro
     * @throws IOException
     */
    public static void saveKey(Key key, String fileName) throws IOException {
        Files.write(Paths.get(fileName), key.getEncoded());
    }

    public static Key loadPublicKey(String file) throws Exception {
        //ler o ficheiro
        byte[] data = Files.readAllBytes(Paths.get(file));
        //especifacção do encoding da chave publica X509
        X509EncodedKeySpec pubSpec = new X509EncodedKeySpec(data);
        //objecto para grerar a chave RSA
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        //Gerar a chave pública
        return keyFactory.generatePublic(pubSpec);
    }

    public static Key loadPrivateKey(String file) throws Exception {
        //ler o ficheiro
        byte[] data = Files.readAllBytes(Paths.get(file));
        //especificações da chave privada PKCS8
        PKCS8EncodedKeySpec privSpec = new PKCS8EncodedKeySpec(data);
        //objecto para grerar a chave RSA
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        //Gerar a chave privada
        return keyFactory.generatePrivate(privSpec);
    }

    /**
     * gera uma chave publica atraves de array de bytes
     *
     * @param pubData dados da chave publica
     * @return chave publica
     * @throws Exception
     */
    public static Key getPublicKey(byte[] pubData) throws Exception {
        //especifacção do encoding da chave publica X509
        X509EncodedKeySpec pubSpec = new X509EncodedKeySpec(pubData);
        //objecto para grerar a chave RSA
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        //Gerar a chave pública
        return keyFactory.generatePublic(pubSpec);
    }

    /**
     * gera uma chave privada atraves de array de bytes
     *
     * @param pubData dados da chave privada
     * @return chave publica
     * @throws Exception
     */
    public static Key getPrivateKey(byte[] privData) throws Exception {
        //especificações da chave privada PKCS8
        PKCS8EncodedKeySpec privSpec = new PKCS8EncodedKeySpec(privData);
        //objecto para grerar a chave RSA
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        //Gerar a chave privada
        return keyFactory.generatePrivate(privSpec);
    }

}
