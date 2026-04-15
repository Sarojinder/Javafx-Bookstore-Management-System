/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
public class buy extends Application{
    public void start(Stage primaryS){
        
        userRecord u = userRecord.getInstance();
        
        Label cost = new Label();
        double c = 0;
        //taking arraylist of purchased books, adding their costs together 
        for(book b : userSc.purchase){
            c+=b.getPrice();
        }
        cost.setText("Total Cost: $" + c);
        
        Label pc = new Label();
        int points = (int) (c*10);
        int po = points+login.loggedUser.getPoints();
        login.loggedUser.setPoints(po);
        if(login.loggedUser.getPoints()<1000){
            login.loggedUser.setStatus(new silverStatus());
            System.out.println("Silver");
        } else{
            login.loggedUser.setStatus(new goldStatus());
            System.out.println("Gold");
        }
        login.loggedUser.request();
        pc.setText("Points: " + login.loggedUser.getPoints() + " | " + login.loggedUser.getStatus());
        
        //similar function to delete function in bookRecord but instead of leaving the one deleted book out, instead replacing the points at the end with the user's proper points after the purchase
        try{
            File f1 = new File(u.fileN);
            File f2 = new File("userstmp.txt");
            f2.createNewFile();
            FileWriter f = new FileWriter("userstmp.txt", true);
            FileReader fi = new FileReader(u.fileN);
            Scanner s = new Scanner(fi);
            while(s.hasNextLine()){
                String l = s.nextLine();
                String[] p = l.split("<");
                if(p[0].equals(login.loggedUser.getUser())){
                    f.write(p[0]+"<"+p[1]+"<"+login.loggedUser.getPoints()+"\n");
                }else{
                    f.write(l + "\n");
                }
            }
            f.close();
            fi.close();
            s.close();
            if(f1.delete()){System.out.println("Deleted.");}
            else{System.out.println("erm");}
            f2.renameTo(new File(u.fileN));
        } catch(IOException e){
            System.out.println("Delete error has occured.");
        }
        
        Button l = new Button();
        l.setText("Logout");
        l.setMaxSize(150, 100);
        
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
        v.getChildren().add(cost);
        v.getChildren().add(pc);
        v.getChildren().add(l);
        v.setSpacing(50);
        
        Scene s = new Scene(v, 1080, 700);
        primaryS.setWidth(1080);
        primaryS.setHeight(700);
        primaryS.setTitle("Buying...");
        primaryS.setScene(s);
        primaryS.show();
        
    }
}
