/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CarsOnBlocks;


import java.io.File;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

/**
 *
 * @author Rodrigo Maia & Rúben Poupado
 * 
 * Classe de especificação de um objeto carro com o seu respetivo modelo e fabricante, com um ID incremental, esta classe é usada para a gestão dos carros no sistema através de uma arraylist da classe CarRegistry.
 */
public class Car implements Serializable{
    // Variável int que servirá como identificador único para a distinção dos carros.
    public int id;
    // Variável string que servirá como identificador do fabricante de um carro.
    private String manufacturer;
    // Variável string que servirá como identificador do modelo de um carro.
    private String model;
    
    // Constructor que inicializa um carro pré-definido
    public Car(){
        this.id = 0;
        this.manufacturer = "Reliant";
        this.model = "1977 Reliant Robin (Mk 1)";
    }
    
    // Constructor que inicializa um carro com os paramêtros definidos.
    public Car(int hashedID, String manufacturer, String model){
        this.id = hashedID;
        this.manufacturer = manufacturer;
        this.model = model;
    }
    
    // Método que retorna o ID de um carro.
    public int getId() {
        return id;
    }
    
    // Método que atribui o ID ao carro.
    public void setId(int id) {
        this.id = id;
    }

    // Método que retorna o fabricante de um carro.
    public String getManufacturer() {
        return manufacturer;
    }
    
    // Método que atribui o fabricante ao carro.
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    // Método que retorna o modelo de um carro.
    public String getModel() {
        return model;
    }
    
    // Método que atribui o modelo ao carro.
    public void setModel(String model) {
        this.model = model;
    }
    
    // Método que retorna uma String representativa do objeto.
    public String toString(){
        return getModel() + " - " + getManufacturer();
    }
    
    private static final long serialVersionUID = 202210281537L;
}
