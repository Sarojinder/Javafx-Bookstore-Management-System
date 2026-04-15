/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author rogan
 */
public class user {
    
    //similarly with book, initial variables in Simple()Property due to table interactions, if they are not in such a state, tables will not live update and will display weirdly
    private SimpleStringProperty userN;
    private SimpleStringProperty pass;
    private SimpleIntegerProperty points;
    private UserStatus status;
    private String s;
    
    public user(String u, String p, int po){
        this.userN = new SimpleStringProperty(u);
        this.pass = new SimpleStringProperty(p);
        this.points = new SimpleIntegerProperty(po);
        this.s = "";
    }
    
    public String getUser(){
        return this.userN.get();
    }
    
    public void setUser(String u){
        this.userN.set(u);
    }
    
    public String getPass(){
        return pass.get();
    }
    
    public void setPass(String p){
        this.pass.set(p);
    }
    
    public void setPoints(int p){
        this.points.set(p);
    }
    
    public int getPoints(){
        return points.get();
    }
    
    public void setStatus(UserStatus s){
        this.status = s;
    }
    
    public String getStatus(){
        return this.s;
    }
    
    public void request(){
        this.s = status.handleStatus();
    }
    
    public SimpleIntegerProperty pointsProperty(){
        return points;
    }
    
    public SimpleStringProperty userProperty(){
        return userN;
    }
    
    public SimpleStringProperty passProperty(){
        return pass;
    }
}