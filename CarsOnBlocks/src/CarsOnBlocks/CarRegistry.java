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

/**
 *
 * @author Rodrigo Maia & Rúben Poupado
 */
public class CarRegistry implements Serializable{
    public String PATH = "data/";
    
    // Lista de Carros
    public ArrayList<Car> carRegistry;
    // Blockchain da informação dos Carros durante períodos de tempo
    public BlockChain carInfoRegistry;
    // Lista de Utilizadores
    public ArrayList<User> userRegistry;
    
    // Construtor
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
    
    // Adiciona um utilizador à lista, dado um utilizador
    public void addUser(User user){
        userRegistry.add(user);
    }
    
    // Adiciona um carro à lista, dado um carro
    public void addCar(Car car){
        carRegistry.add(car);
    }
    
    // Adiciona informação de um carro à blockchain, dado a informação e a sua dificuldade
    public void addCarInfo(CarInfo carInfo, int difficulty){
        try {
            carInfoRegistry.add(carInfo.toString(), difficulty);
        } catch (Exception ex) {
            Logger.getLogger(CarRegistry.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Retorna o tamanho da lista de carros
    public int getCarRegistrySize(){
        return carRegistry.size();
    }
    
    // Retorna a lista de carros
    public List<Car> getCarsList(){
        List<Car> carList = new ArrayList<>();
        carList.addAll(carRegistry);
        return carList;
    }
    
    // Retorna a lista de carros de um utilizador
    public List<Car> getCarsList(User user){
        List<Car> carList = new ArrayList<>();
        // Itera pelos carros
        for (Car car : carRegistry){
            boolean carFound = false;
            System.out.println("Checking car: " + car.toString());
            // Itera pela blockchain
            for (int i=carInfoRegistry.getChain().size()-1; i>=0; i--){
                System.out.println("Comparing to: " + carInfoRegistry.get(i).getData());
                // Verifica se o update mais recente do carro na blockchain tem o user como dono, se não tiver, o carro não é dele; próximo carro.
                if (carInfoRegistry.get(i).getData().contains(car.toString())){
                    carFound = true;
                    System.out.println("Found first car register in blockchain: " + carInfoRegistry.get(i).getData());
                    if (carInfoRegistry.get(i).getData().contains(user.getName())){
                        System.out.println("This car belongs to the user " + user.getName());
                        carList.add(car);
                        break;
                    }
                }
                if (carFound) break;
            }
        }
        return carList;
    }
    
    // Retorna uma lista de carros disponíveis
    public List<String> getAvailableCarsList(){
        List<String> carList = new ArrayList<>();
        for (Car car : carRegistry){
            System.out.println("Checking car: " + car.toString());
            boolean isAvailable = true;
            for (int i=carInfoRegistry.getChain().size()-1; i>=0; i--){
                System.out.println("Comparing to: " + carInfoRegistry.get(i).getData());
                if (carInfoRegistry.get(i).getData().contains(car.toString()))
                    if (carInfoRegistry.get(i).getData().contains("Reserved")){
                        System.out.println("Car is Reserved! Discarding!");
                        isAvailable = false;
                        break;
                    } else {
                        System.out.println("Car is available!");
                        break;
                    }
            }
            if (isAvailable){
                carList.add(car.toString());
                System.out.println("Car added!");
            }
        }
        return carList;
    }
    
    // Retorna uma lista de informações, dado um carro
    public List<String> getCarInfoList(Car car){
        List<String> carInfoList = new ArrayList<>();
        
        for (int i=carInfoRegistry.getChain().size()-1; i>=0; i--) {
            if (carInfoRegistry.get(i).getData().contains(car.toString()))
                carInfoList.add(carInfoRegistry.get(i).getData());
        }
        
        return carInfoList;
    }
    
    // Retorna lista de informações de carros, dado um utilizador
    public List<String> getCarInfoList(User user){
        List<String> carInfoList = new ArrayList<>();
        
        for (int i=carInfoRegistry.getChain().size()-1; i>=0; i--) {
            if (carInfoRegistry.get(i).getData().contains(user.toString())){
                carInfoList.add(carInfoRegistry.get(i).getData());
            }         
        }
        
        return carInfoList;
    }

    // Retorna a lista de utilizadores
    public List<User> getUsersList(){
        List<User> userList = new ArrayList<>();
        userList.addAll(userRegistry);
        return userList;
    }

    // Carrega os dados guardados das listas e da blockchain
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
                FileInputStream readData = new FileInputStream(PATH + "userRegistry.ser");
                ObjectInputStream readStream = new ObjectInputStream(readData);
                userRegistry = (ArrayList<User>) readStream.readObject();
                readStream.close();
            } catch (Exception e){
                System.out.println("Não há utilizadores registados.");
            }
        }
    }
    
    // Guarda os dados das listas e da blockchain
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
    
    // Converte uma string de um instante da informação de um carro para o respetivo objeto
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
