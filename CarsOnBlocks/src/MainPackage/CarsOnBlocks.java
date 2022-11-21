/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackage;

/**
 *
 * @author Rodrigo Maia & Rúben Poupado
 */
public class CarsOnBlocks {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // To Test Here Later
        CarRegistry biggusBlockChain = new CarRegistry();
        
        // Another Car
        Car car1 = new Car("jdisah3JKA#4w", "Ferrari", "Redlinger Deez 2008");
        biggusBlockChain.addCar(car1);
        CarInfo carInfoFerrari = new CarInfo(biggusBlockChain.carRegistry.get(1), biggusBlockChain.clientRegistry.get(0), "29/10/2022 16:30:00", "Reserved", "Latitude: 37.362153 / N 41° 27' 41.141'' Longitude: -5.712781 / W 9° 22' 58.011''", 40);
        biggusBlockChain.addCarInfo(carInfoFerrari);
        
        // Info to the Reliant Robin car
        CarInfo carInfo1 = new CarInfo(biggusBlockChain.carRegistry.get(0), biggusBlockChain.clientRegistry.get(0), "29/10/2022 16:30:00", "Reserved", "Latitude: 37.362153 / N 41° 27' 41.141'' Longitude: -5.712781 / W 9° 22' 58.011''", 40);
        biggusBlockChain.addCarInfo(carInfo1);
        CarInfo carInfo2 = new CarInfo(biggusBlockChain.carRegistry.get(0), biggusBlockChain.clientRegistry.get(0), "29/10/2022 16:35:00", "Reserved", "Latitude: 42.278983 / N 40° 29' 42.341'' Longitude: -2.182781 / W 9° 23' 56.205''", 51);
        biggusBlockChain.addCarInfo(carInfo2);
        
        // Pull Reliant Robin's logs
        biggusBlockChain.pullCarLogs(biggusBlockChain.carRegistry.get(0));
    }
    
}
