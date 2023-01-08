/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CarsOnBlocks;

import CarsOnBlocks.Blockchain.Block;
import CarsOnBlocks.Blockchain.BlockChain;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rodrigo Maia & Rúben Poupado
 * 
 * Classe com a função de uma base de dados, no qual tem contido arraylists dos carros e cliente e a blockchain da informação dos carros,
 * este suporta a interface iterativa 'MineUI' com os seus métodos de retonar listas e guardar/carregar.
 */
public class CarRegistry implements Serializable{
    // Variável String que servirá como identificador da diretoria a guardar os dados do sistema.
    public String PATH = "data/";
    // Variável ArrayList<Car> que servirá como identificador lista dos carros registados no sistema.
    public ArrayList<Car> carRegistry;
    // Variável BlockChain que servirá como identificador de blockchain que guarda seguramente as logs dos carros registados no sistema.
    public BlockChain carInfoRegistry;
    // Variável ArrayList<Client> que servirá como identificador lista dos clientes registados no sistema.
    public ArrayList<Client> clientRegistry;
    
    // Constructor que inicializa as listas e a blockchain da classe
    public CarRegistry(){
        carRegistry = new ArrayList<>();
        carInfoRegistry = new BlockChain();
        clientRegistry = new ArrayList<>();
        // Initial car test
        //addCar(new Car());
        // Initial client test
        //addClient(new Client());
        // Initial car info registry test
        //addCarInfo(new CarInfo(carRegistry.get(0), clientRegistry.get(0), "28/10/2022 16:34:16", "Reserved", "Latitude: 39.578983 / N 39° 34' 44.341'' Longitude: -8.382781 / W 8° 22' 58.011''", 62));   
    }
    
    // Método que insere um objeto client na lista de clientes da classe.
    public void addClient(Client client){
        clientRegistry.add(client);
    }
    
    // Método que insere um objeto car na lista de carros da classe.
    public void addCar(Car car){
        carRegistry.add(car);
    }
    
    // Método que insere um objeto carInfo na blockchain que guarda as logs dos carros.
    public void addCarInfo(CarInfo carInfo){
        try {
            carInfoRegistry.add(carInfo, 4);
        } catch (Exception ex) {
            Logger.getLogger(CarRegistry.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Método que retorna o tamanho da arraylist da lista de carros.
    public int getCarRegistrySize(){
        return carRegistry.size();
    }
    
    // Método que retorna a lista de carros.
    public List<Car> getCarsList(){
        List<Car> carList = new ArrayList<>();
        carList.addAll(carRegistry);
        return carList;
    }
    
    // Método que retorna a lista de logs de um carro.
    public List<CarInfo> getCarInfoList(Car car){
        List<CarInfo> carInfoList = new ArrayList<>();
        for (Block chain : carInfoRegistry.getChain()) {
            if (chain.getCarInfo().getCar().getId() == car.getId())
                carInfoList.add(chain.getCarInfo());
        }
        return carInfoList;
    }
    
    // Método que retorna a lista de logs dos carros de um cliente.
    public List<CarInfo> getCarInfoList(Client client){
        List<CarInfo> carInfoList = new ArrayList<>();
        for (Block chain : carInfoRegistry.getChain()) {
            System.out.println(chain);
            if (chain.getCarInfo().getUser().getPubKey().equals(client.getPubKey()))
                carInfoList.add(chain.getCarInfo());
        }
        return carInfoList;
    }
    
    // Método que retorna a lista de clientes
    public List<Client> getClientsList(){
        List<Client> clientList = new ArrayList<>();
        clientList.addAll(clientRegistry);
        return clientList;
    }

    // Método que carrega os dados dos ficheiros guardados para os arraylists e a blockchain respetiva da classe.
    public void load(){
        if(!Files.exists(Paths.get(PATH))){
            System.out.println("Não há dados registados.");
        } else {
            try { 
                FileInputStream readData = new FileInputStream(PATH + "carRegistry.ser");
                ObjectInputStream readStream = new ObjectInputStream(readData);
                carRegistry = (ArrayList<Car>) readStream.readObject();
                readStream.close();
            } catch (Exception e){
                System.out.println("Não há carros registados.");
            }
            
            try { 
                carInfoRegistry.load();
            } catch (Exception e){
                System.out.println("Não há informação registada dos carros.");
            }
            
            try { 
                FileInputStream readData = new FileInputStream(PATH + "clientRegistry.ser");
                ObjectInputStream readStream = new ObjectInputStream(readData);
                clientRegistry = (ArrayList<Client>) readStream.readObject();
                readStream.close();
            } catch (Exception e){
                System.out.println("Não há clientes registados.");
            }
        }
    }
    
    // Método que guarda os dados das arraylists e a blockchain respetiva da classe em ficheiros da pasta correspondente.
    public void save(){
        if (!Files.exists(Paths.get(PATH)))
            new File(PATH).mkdirs();
        
        try {
            FileOutputStream writeData = new FileOutputStream(PATH + "carRegistry.ser");
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

            writeStream.writeObject(carRegistry);
            writeStream.flush();
            writeStream.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Erro a guardar registo: " + carRegistry);
        }
        
        try {
            carInfoRegistry.save();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Erro a guardar registo: " + carInfoRegistry);
        }
        
        try {
            FileOutputStream writeData = new FileOutputStream(PATH + "clientRegistry.ser");
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

            writeStream.writeObject(clientRegistry);
            writeStream.flush();
            writeStream.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Erro a guardar registo: " + clientRegistry);
        }
    }
    
    // Variável de serialização da classe.
    private static final long serialVersionUID = 202210281537L;
}
