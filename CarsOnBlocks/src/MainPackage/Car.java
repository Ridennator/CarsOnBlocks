/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackage;


import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

/**
 *
 * @author Rodrigo Maia & Rúben Poupado
 */
public class Car {
    public static String car_path = "cars/";
    //hash
    public int id;
    private String manufacturer;
    private String model;
    
    // Mais tarde, adicionar especificações do carro?
    public Car(){
        this.id = 0;
        this.manufacturer = "Reliant";
        this.model = "1977 Reliant Robin (Mk 1)";
    }
    
    public Car(int hashedID, String manufacturer, String model){
        this.id = hashedID;
        this.manufacturer = manufacturer;
        this.model = model;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
    
    public void register(String manufacturer, String model){
        new File(car_path).mkdirs();
        Car c = new Car(id,manufacturer,model);
        System.out.println(c);
        //Files.write(Paths.get(car_path + id + ".txt"),c);
    }
}
