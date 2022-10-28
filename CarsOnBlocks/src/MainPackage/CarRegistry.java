/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackage;

import java.util.ArrayList;

/**
 *
 * @author Rodrigo Maia & Rúben Poupado
 */
public class CarRegistry {
    public ArrayList<Car> carRegistry;
    public ArrayList<CarInfo> carInfoRegistry;
    public ArrayList<Client> clientRegistry;
    
    public CarRegistry(){
        carRegistry = new ArrayList<>();
        carInfoRegistry = new ArrayList<>();
        clientRegistry = new ArrayList<>();
        // Initial car test
        carRegistry.add(new Car());
        // Initial client test
        clientRegistry.add(new Client());
        // Initial car info registry test
        carInfoRegistry.add(new CarInfo(carRegistry.get(0), "Reserved", "Latitude: 39.578983 / N 39° 34' 44.341'' Longitude: -8.382781 / W 8° 22' 58.011''", "28/10/2022 16:34:16", clientRegistry.get(0), 62));
        
    }
}
