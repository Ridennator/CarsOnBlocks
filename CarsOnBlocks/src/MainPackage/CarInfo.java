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
public class CarInfo {
    private Car car;
    private String status;
    //hash
    private String coords;
    private String timestamp;
    //hash
    private Client user;
    private int speed;
    
    public CarInfo(Car car, String status, String coords, String timestamp, Client user, int speed){
        this.timestamp = timestamp;
        this.car = car;
        this.status = status;
        this.user = user;
        this.coords = coords;
        this.speed = speed;
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
        return "Car{timestamp=" + getTimestamp() + ", status=" + getStatus() + ", user=" + getUser() + ", coords=" + getCoords() +  ", speed=" + getSpeed() + "}";
    }
}
