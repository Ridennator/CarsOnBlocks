/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MainPackage;
import CarsOnBlocks.Security.Asimetric;
import CarsOnBlocks.Security.PBE;
import CarsOnBlocks.utils.SecurityUtils;
import java.io.File;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Key;
import java.security.KeyPair;

/**
 *
 * @author Rodrigo Maia & Rúben Poupado
 * 
 * Classe que regista um cliente com a segurança de chaves privadas e públicas, recolhido e guardado num arraylist da classe CarRegistry.
 */
public class Client implements Serializable{

    static void Login(String user, String pass) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    // Variável String que servirá como identificador do nome do cliente a que se destina.
    private String name;
    // Variável String que servirá como identificador da palavra-chave do cliente a que se destina.
    private String password;
    // Variável Key que servirá como identificador de uma chave privada do cliente a que se destina.
    private Key privKey;
    // Variável Key que servirá como identificador de uma chave pública do cliente a que se destina.
    private Key pubKey;

    // Variável boolean que servirá como identificador ao estado de atividade perante o cliente a que se destina.
    boolean isLogged = false;
    // Variável boolean que servirá como identificador da existência do mesmo cliente perante à ação do registo.
    boolean alreadyExists = false;
    
    // Construtor que inicializa um cliente pré-definido.
    public Client(){
        this.name = "Henrico Chivaldori";
    }
    
    // Construtor que inicializa um cliente com o paramêtro definido.
    public Client(String name){
        this.name = name;
    }
    
    // Método que retorna o nome do cliente.
    public String getName() {
        return name;
    }
    
    // Método que regista um cliente com chaves assimétricas
    public void register(String username, String password) throws Exception{
        if(Files.exists(Paths.get(username + ".priv"))){
            System.out.println("Nome de utilizador não disponível.");
            alreadyExists = true;
            return;
        }
        
        KeyPair keys = Asimetric.generateKeyPair(2048);
        pubKey = keys.getPublic();
        privKey = keys.getPrivate();
        Asimetric.saveKey(pubKey, username + ".pub");
        
        byte [] secret = PBE.encrypt(privKey.getEncoded(), password);
        
        Files.write(Paths.get(username + ".priv"), secret);
        
        System.out.println("Registo efetuado com sucesso.");

    }
    
    // Método que efetua o login de um cliente com desencriptação.
    public void login(String username, String password) throws Exception{
        // Conta não existe?
        if(!Files.exists(Paths.get(username + ".priv"))){
            System.out.println("Utilizador inexistente, tente novamente.");
            return;
        }
        try{
            // Login do utilizador
            byte[] privateKey = Files.readAllBytes(Paths.get(username + ".priv"));
            System.out.println(privateKey);
            PBE.decrypt(privateKey, password);
            System.out.println("Login efetuado com sucesso.");
            isLogged = true;
        }catch (Exception ex){
            System.out.println("Login falhado (password?).");
        }
    }
    
    // Método que retorna a chave privada do cliente.
    public Key getPrivKey() {
        return privKey;
    }
    
    // Método que retorna a chave privada do cliente.
    public Key getPubKey() {
        return pubKey;
    }
    
    // Método que retorna um boolean para determinar se um cliente efetuou o login.
    public boolean getLoginStatus(){
        return isLogged;
    }
    
    // Método que retorna uma String representativa do objeto.
    public String toString(){
        return getName();
    }
    
    // Variável de serialização da classe.
    private static final long serialVersionUID = 202210281537L;
}
