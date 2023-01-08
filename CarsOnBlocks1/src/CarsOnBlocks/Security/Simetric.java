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
import java.security.Provider;
import java.security.Security;
import java.util.Base64;
import java.util.Set;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author doctmanso
 */
public class Simetric {

    public static void listAlgorithms() {
        //lista do fornecedores
        Provider providers[] = Security.getProviders();
        for (Provider provider : providers) {
            //Serviços fornecidos
            Set<Provider.Service> services = provider.getServices();
            for (Provider.Service service : services) {
                //Serviço de confidencialidade
                if (service.getType().equalsIgnoreCase("Cipher")) {
                    System.out.printf("\n\t %-20s %-20s %s",
                            service.getProvider(), service.getType(), service.getAlgorithm());
                }
            }
        }
    }

    /**
     *
     *
     * /**
     * Gera uma chave de criptogradia simetrica
     *
     * @param algorithm algoritmo
     * @param keySize tamanho da chave
     * @return chave
     * @throws Exception
     */
    public static Key generateKey(String algorithm, int keySize) throws Exception {
        System.out.println("Generating " + algorithm + " - " + keySize + " key ...");
        // gerador de chaves
        KeyGenerator keyGen = KeyGenerator.getInstance(algorithm);
        //tamanho da chave
        keyGen.init(keySize);
        //gerar a chave
        Key key = keyGen.generateKey();
        System.out.println("Key :" + Base64.getEncoder().encodeToString(key.getEncoded()));
        return key;
    }

    /**
     *
     *
     * /**
     * Gera uma chave de criptogradia simetrica
     *
     * @param algorithm algoritmo
     * @return chave
     * @throws Exception
     */
    public static Key generateKey(String algorithm) throws Exception {
        System.out.println("Generating " + algorithm + " key ...");
        // gerador de chaves
        KeyGenerator keyGen = KeyGenerator.getInstance(algorithm);
        //gerar a chave
        Key key = keyGen.generateKey();
        System.out.println("Key :" + Base64.getEncoder().encodeToString(key.getEncoded()));
        return key;
    }

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
    //:::::::::::        SIMETRIC KEYS                               :::::::::::
    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
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

    /**
     * Le uma chave de um ficheiro
     *
     * @param fileName nome do ficheiro
     * @param algorithm algoritmo da cheve
     * @return
     * @throws IOException
     */
    public static Key loadKey(String fileName, String algorithm) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(fileName));
        Key key = new SecretKeySpec(encoded, algorithm);
        return key;
    }
}
