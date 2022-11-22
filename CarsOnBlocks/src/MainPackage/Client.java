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
 */
public class Client implements Serializable{

    static void Login(String user, String pass) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    private String name;
    
    private String password;
    private Key privKey;
    private Key pubKey;

    boolean isLogged = false;
    boolean alreadyExists = false;
    
    public Client(){
        this.name = "Henrico Chivaldori";
    }
    
    public Client(String name){
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
        
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
    
    //Login na aplicação
    public void login(String username, String password) throws Exception{
        //Conta não existe?
        if(!Files.exists(Paths.get(username + ".priv"))){
            System.out.println("Utilizador inexistente, tente novamente.");
            return;
        }
        try{
            //Login do utilizador
            byte[] privateKey = Files.readAllBytes(Paths.get(username + ".priv"));
            System.out.println(privateKey);
            PBE.decrypt(privateKey, password);
            System.out.println("Login efetuado com sucesso.");
            isLogged = true;
        }catch (Exception ex){
            System.out.println("Login falhado (password?).");
        }

    }
    
    //Chave privada
    public Key getPrivKey() {
        return privKey;
    }
    
    //Chave Publica
    public Key getPubKey() {
        return pubKey;
    }
    
    //Saber se o utilizador tem login feito ou não
    public boolean getLoginStatus(){
        return isLogged;
    }
    
    public String toString(){
        return getName();
    }
    
    private static final long serialVersionUID = 202210281537L;
}
