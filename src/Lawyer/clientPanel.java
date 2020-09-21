/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lawyer;

public class clientPanel {
    private String Name, Phone, Pass, Email, Address;
    private int ID;
    private byte[] picture;

    public clientPanel(String Name, String Phone, String Email, String Address, int ID) {
        this.Name = Name;
        this.Phone = Phone;
        this.Email = Email;
        this.Address = Address;
        this.ID = ID;
    }

    public clientPanel(String Name, String Phone, String Pass, String Email, String Address, byte[] picture) {
        this.Name = Name;
        this.Phone = Phone;
        this.Pass = Pass;
        this.Email = Email;
        this.Address = Address;
        this.picture = picture;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String Pass) {
        this.Pass = Pass;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }    
}
