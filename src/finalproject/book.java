/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;

import javafx.beans.property.*;

/**
 *
 * @author rogan
 */
public class book {
    
    //similarly with user, initial variables in Simple()Property due to table interactions, if they are not in such a state, tables will not live update and will display weirdly
    private SimpleDoubleProperty price;
    private SimpleStringProperty title;
    private SimpleBooleanProperty check;
    
    public book(String t, double p){
        this.title = new SimpleStringProperty(t);
        this.price = new SimpleDoubleProperty(p);
        this.check = new SimpleBooleanProperty(false);
    }
    
    public String getTitle(){
        return title.get();
    }
    
    public void setTitle(String t){
        this.title.set(t);
    }
    
    public double getPrice(){
        return price.get();
    }
    
    public void setPrice(double d){
        this.price.set(d);
    }
    
    public boolean getCheck(){
        return check.get();
    }
    
    public SimpleStringProperty titleProperty(){
        return title;
    }
    
    public SimpleDoubleProperty priceProperty(){
        return price;
    }
    
    public SimpleBooleanProperty selectedProperty(){
        return check;
    }
}
