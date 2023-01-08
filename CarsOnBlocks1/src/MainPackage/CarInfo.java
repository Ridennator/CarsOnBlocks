/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackage;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Rodrigo Maia & Rúben Poupado
 * 
 * Classe que contém as várias informações de um carro e cliente para uma determinada data, usada para a gestão da informação dos carros no sistema através do uso da Blockchain na classe CarRegistry.
 */

public class CarInfo implements Serializable{
    // Variável Car que servirá como identificador do carro a que a informação se especifica.
    private Car car;
    // Variável String que servirá como identificador do estado do carro na data designada.
    private String status;
    // Variável String que servirá como identificador das coordenadas do carro na data designada.
    private String coords;
    // Variável Date que servirá como identificador da data designada da informação.
    private Date timestamp;
    // Variável Client que servirá como identificador do cliente do carro na data designada.
    private Client user;
    // Variável int que servirá como identificador da velocidade (km/h) do carro na data designada.
    private int speed;
    
    // Construtor que inicializa uma log de um carro de um cliente com os paramêtros definidos.
    public CarInfo(Car car, Client user, Date timestamp, String status, String coords, int speed){
        this.car = car;
        this.user = user;
        this.timestamp = timestamp;
        this.status = status;
        this.coords = coords;
        this.speed = speed;
    }
    
    // Método que atribui o Car à log.
    public void setCar(Car car){
        this.car = car;
    }
    
    // Método que retorna o Car da log.
    public Car getCar(){
        return car;
    }
    
    // Método que retorna o status da log.
    public String getStatus() {
        return status;
    }

    // Método que atribui o status à log.
    public void setStatus(String status) {
        this.status = status;
    }

    // Método que retorna as coordenadas da log.
    public String getCoords() {
        return coords;
    }

    // Método que atribui as coordenadas à log.
    public void setCoords(String cords) {
        this.coords = cords;
    }

    // Método que retorna a timestamp da log.
    public Date getTimestamp() {
        return timestamp;
    }

    // Método que atribui a timestamp à log.
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    // Método que retorna o cliente da log.
    public Client getUser() {
        return user;
    }
    
    // Método que retorna o nome do cliente da log.
    public String getUserName() {
        return user.getName();
    }

    // Método que atribui o cliente à log.
    public void setUser(Client user) {
        this.user = user;
    }

    // Método que retorna a velocidade da log.
    public int getSpeed() {
        return speed;
    }

    // Método que atribui a velocidade à log.
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    // Método que retorna uma String representativa do objeto.
    @Override
    public String toString() {
        return getTimestamp() + " - " + getStatus() + " - " + getUserName() + " - " + getCoords() +  " - " + getSpeed() + "km/h";
    }
    
    // Variável de serialização da classe.
    private static final long serialVersionUID = 202210281537L;
}
