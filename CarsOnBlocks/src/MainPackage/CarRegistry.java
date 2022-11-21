/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackage;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Rodrigo Maia & Rúben Poupado
 */
public class CarRegistry implements Serializable{
    public ArrayList<Car> carRegistry;
    // Mudar este dado para Blockchain
    public ArrayList<CarInfo> carInfoRegistry;
    public ArrayList<Client> clientRegistry;
    
    public CarRegistry(){
        carRegistry = new ArrayList<>();
        carInfoRegistry = new ArrayList<>();
        clientRegistry = new ArrayList<>();
        // Initial car test
        addCar(new Car());
        // Initial client test
        addClient(new Client());
        // Initial car info registry test
        addCarInfo(new CarInfo(carRegistry.get(0), clientRegistry.get(0), "28/10/2022 16:34:16", "Reserved", "Latitude: 39.578983 / N 39° 34' 44.341'' Longitude: -8.382781 / W 8° 22' 58.011''", 62));   
    }
    
    public void addClient(Client client){
        clientRegistry.add(client);
    }
    
    public void addCar(Car car){
        carRegistry.add(car);
    }
    
    public void addCarInfo(CarInfo carInfo){
        carInfoRegistry.add(carInfo);
    }
    
    // Pulls all the data on a car
    public ArrayList<String> pullCarLogs(Car car){
        ArrayList<String> carLogs = new ArrayList<String>();
        for (int i=0; i<carInfoRegistry.size(); i++){
            if (carInfoRegistry.get(i).getCar().equals(car)){
                carLogs.add(carInfoRegistry.get(i).toString());
                // Test
                System.out.println(carInfoRegistry.get(i).toString());
            }
        }
        return carLogs;
    }
    
    // Pull last data on a car
    public CarInfo pullLastCarLogs(Car car){
        for (int i=carInfoRegistry.size(); i>0; i--){
            if (carInfoRegistry.get(i).getCar().equals(car))
                return carInfoRegistry.get(i);
        }
        return null;
    }
    
    // Pulls all currently available cars to rent
    public ArrayList<String> availableCars(){
        ArrayList<String> availableCars = new ArrayList<String>();
        for (int i=0; i<carRegistry.size(); i++){
            CarInfo carInfo = pullLastCarLogs(carRegistry.get(i));
            if (!carInfo.equals(null))
                if (carInfo.getStatus() == "Available")
                    availableCars.add(carRegistry.get(i).toString());
        }
        return availableCars;
    }
    
    public static long SerializableVersionId = 123;
}
