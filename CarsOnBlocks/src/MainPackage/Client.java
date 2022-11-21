/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MainPackage;
import CarsOnBlocks.Security.Asimetric;
import CarsOnBlocks.Security.PBE;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Key;
import java.security.KeyPair;

/**
 *
 * @author Rodrigo Maia & Rúben Poupado
 */
public class Client {
    private String name;
    private String birthdate;
    private String NIF;
    
    private String password;
    private Key privKey;
    private Key pubKey;

    boolean isLogged = false;
    boolean alreadyExists = false;
    
    public Client(){
        this.name = "Henrico Chivaldori";
        this.birthdate = "16/02/1972";
        this.NIF = "#adA#ouAWEU34auoSD";
    }
    
    public Client(String name, String NIF){
        this.name = name;
        this.NIF = NIF;
    }
    
    public Client(String name, String NIF, String birthdate){
        this.name = name;
        this.NIF = NIF;
        this.birthdate = birthdate;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getNIF() {
        return NIF;
    }

    public void setNIF(String NIF) {
        this.NIF = NIF;
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
        return "Client = " + getName();
    }
}
