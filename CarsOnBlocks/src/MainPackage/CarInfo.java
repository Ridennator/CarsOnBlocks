/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackage;

import java.io.Serializable;

/**
 *
 * @author Rodrigo Maia & Rúben Poupado
 */
public class CarInfo implements Serializable{
    private Car car;
    private String status;
    //hash
    private String coords;
    private String timestamp;
    //hash
    private Client user;
    private int speed;
    
    public CarInfo(Car car, Client user, String timestamp, String status, String coords, int speed){
        this.car = car;
        this.user = user;
        this.timestamp = timestamp;
        this.status = status;
        this.coords = coords;
        this.speed = speed;
    }
    
    public CarInfo(Car car, Client user){
        this.timestamp = "21:58 16/02/2009";
        this.car = car;
        this.status = "Reserved";
        this.user = user;
        this.coords = "-";
    }
    
    public void setCar(Car car){
        this.car = car;
    }
    
    public Car getCar(){
        return car;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCoords() {
        return coords;
    }

    public void setCoords(String cords) {
        this.coords = cords;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Client getUser() {
        return user;
    }
    
    public String getUserName() {
        return user.getName();
    }

    public void setUser(Client user) {
        this.user = user;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    @Override
    public String toString() {
        return "Timestamp=" + getTimestamp() + ", status=" + getStatus() + ", user=" + getUserName() + ", coords=" + getCoords() +  ", km/h=" + getSpeed();
    }
    
    public static long SerializableVersionId = 123;
}
