/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lawyer;

/**
 *
 * @author Asus
 */
public class lawyerPanel {
    private String LName, LType, LPhone, LAddress, LPass, LEmail, LNID;
    private float LRating;
    private int LWin, LLoss, LTotal, LID;
    private String picture;
    
    public lawyerPanel(String LName, String LType, String LPhone, String LAddress, String LPass,
         String LEmail, String LNID, float LRating, int LWin, int LLoss, int LTotal, int LID, String image){
        this.LName = LName;
        this.LType = LType;
        this.LPhone = LPhone;
        this.LAddress = LAddress;
        this.LPass = LPass;
        this.LEmail = LEmail;
        this.LNID = LNID;
        this.LRating = LRating;
        this.LWin = LWin;
        this.LLoss = LLoss;
        this.LTotal = LTotal;
        this.LID = LID;
        this.picture = image;
    }

    public lawyerPanel(String LName, String image){
        this.LName = LName;
        this.picture = image;
    }
    public String getLName() {
        return LName;
    }

    public void setLName(String LName) {
        this.LName = LName;
    }

    public String getLType() {
        return LType;
    }

    public void setLType(String LType) {
        this.LType = LType;
    }

    public String getLPhone() {
        return LPhone;
    }

    public void setLPhone(String LPhone) {
        this.LPhone = LPhone;
    }

    public String getLAddress() {
        return LAddress;
    }

    public void setLAddress(String LAddress) {
        this.LAddress = LAddress;
    }

    public String getLPass() {
        return LPass;
    }

    public void setLPass(String LPass) {
        this.LPass = LPass;
    }

    public String getLEmail() {
        return LEmail;
    }

    public void setLEmail(String LEmail) {
        this.LEmail = LEmail;
    }

    public String getLNID() {
        return LNID;
    }

    public void setLNID(String LNID) {
        this.LNID = LNID;
    }

    public float getLRating() {
        return LRating;
    }

    public void setLRating(float LRating) {
        this.LRating = LRating;
    }

    public int getLWin() {
        return LWin;
    }

    public void setLWin(int LWin) {
        this.LWin = LWin;
    }

    public int getLLoss() {
        return LLoss;
    }

    public void setLLoss(int LLoss) {
        this.LLoss = LLoss;
    }

    public int getLTotal() {
        return LTotal;
    }

    public void setLTotal(int LTotal) {
        this.LTotal = LTotal;
    }

    public int getLID() {
        return LID;
    }

    public void setLID(int LID) {
        this.LID = LID;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
