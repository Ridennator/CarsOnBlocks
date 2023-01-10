/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CarsOnBlocks;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import templarCoin.core.User;

/**
 *
 * @author Rodrigo Maia & Rúben Poupado
 */
public class CarInfo implements Serializable{
    private Car car;
    private String status;
    
    private String coords;
    private String timestamp;
    
    private User user;
    private int speed;
    
    public CarInfo(Car car, String timestamp){
        this.car = car;
        try {
            this.user = User.load("System");
        } catch (Exception ex) {
            Logger.getLogger(CarInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.timestamp = timestamp;
        this.status = "Available";
        this.coords = "Not tracking.";
        this.speed = 0;
    }
    
    public CarInfo(Car car, User user, String timestamp, String status, String coords, int speed){
        this.car = car;
        this.user = user;
        this.timestamp = timestamp;
        this.status = status;
        this.coords = coords;
        this.speed = speed;
    }
    
    public CarInfo(){}
    
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

    public User getUser() {
        return user;
    }
    
    public String getUserName() {
        if (user != null)
            return user.getName();
        else
            return "System";
    }

    public void setUser(User user) {
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
        return getTimestamp() + " - " + getStatus() + " - " + getUserName() + " - " + getCoords() +  " - " + getSpeed() + "km/h" + " - " + getCar();
    }
    
    private static final long serialVersionUID = 202210281537L;
}
