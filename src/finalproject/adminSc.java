/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.event.*;
import javafx.stage.*;
import javafx.geometry.*;

/**
 *
 * @author rogan
 */

//Main admin screen, holds pathways towards admin-book and admin-user
public class adminSc extends Application {
    public void start (Stage primaryS){
        //Buttons to go to either admin-book, admin-user, or back to log-in screen
        Button b = new Button();
        b.setText("Books");
        b.setMaxSize(150, 100);
        
        Button c = new Button();
        c.setText("Customers");
        c.setMaxSize(150, 100);
        
        Button l = new Button();
        l.setText("Logout");
        l.setMaxSize(150, 100);
        
        b.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                adminBook n = new adminBook();
                n.start(primaryS);
            }
        });
        
        c.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                adminUser n = new adminUser();
                n.start(primaryS);
            }
        });
        
        l.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                login n = new login();
                try {
                    n.start(primaryS);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        VBox v = new VBox();
        v.setAlignment(Pos.CENTER);
        v.getChildren().add(b);
        v.getChildren().add(c);
        v.getChildren().add(l);
        v.setSpacing(25);
        
        Scene s = new Scene(v, 1080, 700);
        primaryS.setWidth(1080);
        primaryS.setHeight(700);
        primaryS.setTitle("Admin");
        primaryS.setScene(s);
        primaryS.show();
    }
}
