/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package finalproject;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.geometry.*;
/**
 *
 * @author rogan
 */
public class login extends Application{
    
    //ArrayList is to sort through users to ensure log-in can happen for specific user, loggedUser is to ensure accessibility of the logged-in user's info in other classes
    ArrayList<user> users = new ArrayList<>();
    static user loggedUser;
            
    public void start(Stage primaryS) throws Exception{
        
        //Creating customers.txt file and then reading and inputting data from customers.txt into the arraylist for later
        userRecord rec = userRecord.getInstance();
        
        try {
            FileReader r = new FileReader(rec.fileN);
            Scanner s = new Scanner(r);
            while(s.hasNextLine()){
                String line = s.nextLine();
                String[] p = line.split("<");
                users.add(new user(p[0], p[1], Integer.parseInt(p[2])));
            }
            r.close();
            s.close();
        }catch(IOException e){
            System.out.println("File does not exist.");
        }
        
        Label w = new Label("Welcome to our bookstore!");
        Label u = new Label("Username:");
        Label p = new Label("Password:");
        
        //Setting up GUI
        TextField us = new TextField();
        PasswordField pa = new PasswordField();
        Button bu = new Button();        
        
        us.setMaxWidth(150);
        us.setAlignment(Pos.TOP_CENTER);
        pa.setMaxWidth(150);
        pa.setAlignment(Pos.TOP_CENTER);
        
        HBox user = new HBox(u, us);
        user.setAlignment(Pos.TOP_CENTER);
        user.setSpacing(8);
        HBox pass = new HBox(p, pa);
        pass.setAlignment(Pos.TOP_CENTER);
        pass.setSpacing(8);
        
        bu.setText("Log In");
        bu.setMaxSize(80,8);
        bu.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                String logU = us.getText();
                String logP = pa.getText();
                //Two different ways to login (admin or user) depicted here
                if(logU.equals("admin") && logP.equals("admin")){
                    adminSc a = new adminSc();
                    a.start(primaryS);
                }else{
                    //for loop checks each user within the previously made arraylist and compares that with the inputted username, if it matches, then login is successful
                    for (user c : users){
                        if(c.getUser().equals(logU) && c.getPass().equals(logP)){
                            loggedUser = c;
                            userSc a = new userSc();
                            a.start(primaryS);
                        }
                    }
                }
            }
        });
        
      VBox b = new VBox();
      
      b.getChildren().add(w);
      b.getChildren().add(user);
      b.getChildren().add(pass);
      b.getChildren().add(bu);
      b.setAlignment(Pos.CENTER);
      b.setSpacing(20);
        
      Scene s = new Scene(b, 1080, 700);
      primaryS.setWidth(1080);
      primaryS.setHeight(700);
      primaryS.setTitle("Bookstore");
      primaryS.setScene(s);
      primaryS.show();
    }
    
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
