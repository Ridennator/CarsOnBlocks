/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CarsOnBlocks;

import blockChain.chain.Block;
import blockChain.chain.BlockChain;
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
import templarCoin.core.User;

/**
 *
 * @author Rodrigo Maia & Rúben Poupado
 */
public class CarRegistry implements Serializable{
    public String PATH = "data/";
    
    public ArrayList<Car> carRegistry;
    public BlockChain carInfoRegistry;
    public ArrayList<User> userRegistry;
    
    public CarRegistry(){
        carRegistry = new ArrayList<>();
        carInfoRegistry = new BlockChain();
        userRegistry = new ArrayList<>();
        // Initial car test
        //addCar(new Car());
        // Initial client test
        //addClient(new Client());
        // Initial car info registry test
        //addCarInfo(new CarInfo(carRegistry.get(0), clientRegistry.get(0), "28/10/2022 16:34:16", "Reserved", "Latitude: 39.578983 / N 39° 34' 44.341'' Longitude: -8.382781 / W 8° 22' 58.011''", 62));   
    }
    
    public void addUser(User user){
        userRegistry.add(user);
    }
    
    public void addCar(Car car){
        carRegistry.add(car);
    }
    
    public void addCarInfo(CarInfo carInfo){
        try {
            carInfoRegistry.add(carInfo.toString(), 4);
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
    
    public List<CarInfo> getCarInfoList(Car car){
        List<CarInfo> carInfoList = new ArrayList<>();
        
        for (Block chain : carInfoRegistry.getChain()) {
            System.out.println("Chain data: " + chain.getData());
        }
        /*
        for (Block chain : carInfoRegistry.getChain()) {
            if (chain.getCarInfo().getCar().getId() == car.getId())
                carInfoList.add(chain.getCarInfo());
        }
        */
        return carInfoList;
    }
    

    public List<CarInfo> getCarInfoList(User client){
        List<CarInfo> carInfoList = new ArrayList<>();
        
        for (Block chain : carInfoRegistry.getChain()) {
            System.out.println("Chain data: " + chain.getData());
        }
        
        /*
        for (Block chain : carInfoRegistry.getChain()) {
            System.out.println(chain);
            if (chain.getCarInfo().getUser().getPubKey().equals(client.getPubKey()))
                carInfoList.add(chain.getCarInfo());
        }
        */
        
        return carInfoList;
    }

    
    public List<User> getClientsList(){
        List<User> clientList = new ArrayList<>();
        clientList.addAll(userRegistry);
        return clientList;
    }

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
                carInfoRegistry.load("data/carInfoRegistry.bck");
            } catch (Exception e){
                System.out.println("Não há informação registada dos carros.");
            }
            
            try { 
                FileInputStream readData = new FileInputStream(PATH + "clientRegistry.ser");
                ObjectInputStream readStream = new ObjectInputStream(readData);
                userRegistry = (ArrayList<User>) readStream.readObject();
                readStream.close();
            } catch (Exception e){
                System.out.println("Não há utilizadores registados.");
            }
        }
    }
    
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
            carInfoRegistry.save("data/carInfoRegistry.bck");
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Erro a guardar registo: " + carInfoRegistry);
        }
        
        try {
            FileOutputStream writeData = new FileOutputStream(PATH + "userRegistry.ser");
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

            writeStream.writeObject(userRegistry);
            writeStream.flush();
            writeStream.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Erro a guardar registo: " + userRegistry);
        }
    }
    
    public String CarInfoToString(){
        return carInfoRegistry.toString();
    }
    
    public CarInfo ConvertToObject(String string){
        CarInfo carinfo = new CarInfo();
        String[] variables = string.split(" - ");
        if (variables.length == 6){
            // Get Timestamp below (String -> DateFormat)
            carinfo.setTimestamp(variables[0]);
            //this.timestamp = DateFormat.parse(variables[0]);
            carinfo.setStatus(variables[1]);
            // Get User below (String -> User)
            // this.user = User.getUserList().get(int index))
            carinfo.setUser(User.getUserList().get(User.getUserList().indexOf(variables[2])));
            carinfo.setCoords(variables[3]);
            carinfo.setSpeed(Integer.parseInt(variables[4]));
            // Get Car below (String -> Car)
            carinfo.setCar(carRegistry.get(carRegistry.indexOf(variables[5])));
        } else {
            System.out.println("Informação inválida ou corrupta!");
        }
        return carinfo;
    }
    
    private static final long serialVersionUID = 202210281537L;
}
