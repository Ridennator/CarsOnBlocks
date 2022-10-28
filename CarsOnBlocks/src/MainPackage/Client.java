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
public class Client {
    private String name;
    private String birthdate;
    // Hashed?
    private String NIF;
    // Hashed
    private String electronicCard;
    // Hashed - This one dictates the car they have access to
    private String electronicCardValue;

    public Client(){
        this.name = "Jane Doe";
        this.birthdate = "16/02/1992";
        this.NIF = "#adA#ouAWEU34auoSD";
        this.electronicCard = "$#43anAEWM53w98adN34wkj";
        this.electronicCardValue = "In4jmasDj53kw39AO3%$";
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getNIF() {
        return NIF;
    }

    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

    public String getElectronicCard() {
        return electronicCard;
    }

    public void setElectronicCard(String electronicCard) {
        this.electronicCard = electronicCard;
    }

    public String getElectronicCardValue() {
        return electronicCardValue;
    }

    public void setElectronicCardValue(String electronicCardValue) {
        this.electronicCardValue = electronicCardValue;
    }
    
    public String toString(){
        return "Client = " + getName() + ", Card = " + getElectronicCard() + ", Card value = " + getElectronicCardValue();
    }
}
