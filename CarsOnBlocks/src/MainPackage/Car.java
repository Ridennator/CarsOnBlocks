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
public class Car {
    //hash
    protected String id;
    private String manufacturer;
    private String model;
    
    // Mais tarde, adicionar especificações do carro?
    public Car(){
        this.id = "aaa000";
        this.manufacturer = "Reliant";
        this.model = "1977 Reliant Robin (Mk 1)";
    }
    
    public Car(String hashedID, String manufacturer, String model){
        this.id = hashedID;
        this.manufacturer = manufacturer;
        this.model = model;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
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
}
