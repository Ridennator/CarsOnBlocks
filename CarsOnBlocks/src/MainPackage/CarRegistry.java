/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackage;

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
 */
public class CarRegistry implements Serializable{
    public String PATH = "data/";
    
    public ArrayList<Car> carRegistry;
    // Mudar este dado para Blockchain
    public BlockChain carInfoRegistry;
    public ArrayList<Client> clientRegistry;
    
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
    
    public void addClient(Client client){
        clientRegistry.add(client);
    }
    
    public void addCar(Car car){
        carRegistry.add(car);
    }
    
    public void addCarInfo(CarInfo carInfo){
        try {
            carInfoRegistry.add(carInfo, 4);
        } catch (Exception ex) {
            Logger.getLogger(CarRegistry.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getCarRegistrySize(){
        return carRegistry.size();
    }
    
    public List<Car> getCarsList(){
        List<Car> carList = new ArrayList<>();
        carList.addAll(carRegistry);
        return carList;
    }

public void load(){
        if(!Files.exists(Paths.get(PATH))){
            System.out.println("Não há dados registados.");
        } else {
            try { 
                ObjectInputStream in = new ObjectInputStream(new FileInputStream("carRegistry"));
                carRegistry = (ArrayList<Car>) in.readObject();
            } catch (Exception e){
                System.out.println("Não há carros registados.");
            }
            
            try { 
                ObjectInputStream in = new ObjectInputStream(new FileInputStream("carInfoRegistry"));
                carInfoRegistry = (BlockChain) in.readObject();
            } catch (Exception e){
                System.out.println("Não há informação registada dos carros.");
            }
            
            try { 
                ObjectInputStream in = new ObjectInputStream(new FileInputStream("clientRegistry"));
                clientRegistry = (ArrayList<Client>) in.readObject();
            } catch (Exception e){
                System.out.println("Não há clientes registados.");
            }
        }
    }
    
    public void save(){
        if (!Files.exists(Paths.get(PATH)))
            new File(PATH).mkdirs();
        
        try {
            FileOutputStream fos = new FileOutputStream(PATH + "carRegistry");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(carRegistry);
            oos.close();
        } catch (Exception e) {
            System.out.println("Erro a guardar registos dos carros!");
        }
        
        try {
            FileOutputStream fos = new FileOutputStream(PATH + "carInfoRegistry");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(carInfoRegistry);
            oos.close();
        } catch (Exception e) {
            System.out.println("Erro a guardar registos da informação dos carros!");
        }
        
        try {
            FileOutputStream fos = new FileOutputStream(PATH + "clientRegistry");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(clientRegistry);
            oos.close();
        } catch (Exception e) {
            System.out.println("Erro a guardar registos dos clientes!");
        }
    }
    
    public static long SerializableVersionId = 123;
}
