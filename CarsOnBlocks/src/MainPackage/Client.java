/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MainPackage;
import CarsOnBlocks.Security.Asimetric;
import CarsOnBlocks.Security.PBE;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Key;
import java.security.KeyPair;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rodrigo Maia & Rúben Poupado
 */



public class Client {
    public static String USER_PATH = "users/";
    
    private String username;
    
    private String password;
    private Key privKey;
    private Key pubKey;

    boolean isLogged = false;
    boolean alreadyExists = false;
    
    public String getName() {
        return username;
    }
        
    public void register(String username, String password) throws Exception{
        new File(USER_PATH).mkdirs();
        
        if(Files.exists(Paths.get(USER_PATH + username + ".priv"))){
            System.out.println("Nome de utilizador não disponível.");
            alreadyExists = true;
            return;
        }
        
        KeyPair keys = Asimetric.generateKeyPair(2048);
        pubKey = keys.getPublic();
        privKey = keys.getPrivate();
        Asimetric.saveKey(pubKey, USER_PATH + username + ".pub");
        
        byte [] secret = PBE.encrypt(privKey.getEncoded(), password);
        
        Files.write(Paths.get(USER_PATH + username + ".priv"), secret);
        
        System.out.println("Registo efetuado com sucesso.");

    }
    
    //Login na aplicação
    public void login(String username, String password) throws Exception{
        //Conta não existe?
        if(!Files.exists(Paths.get(USER_PATH + username + ".priv"))){
            System.out.println("Utilizador inexistente, tente novamente.");
            return;
        }
        try{
            //Login do utilizador
            byte[] privateKey = Files.readAllBytes(Paths.get(USER_PATH + username + ".priv"));
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
    /*
    public static List<Client> getUserList() {
        List<Client> lst = new ArrayList<>();
        //Ler os ficheiros da path dos utilizadores
        File[] files = new File(user_path).listFiles();
        if (files == null) {
            return lst;
        }
        //contruir um user com cada ficheiros
        for (File file : files) {
            //se for uma chave publica
            if (file.getName().endsWith(".pub")) {
                //nome do utilizador
                String userName = file.getName().substring(0, file.getName().lastIndexOf("."));
                try {
                    lst.add(load(userName));
                } catch (Exception e) {
                }
            }
        }
        return lst;

    }*/
}
