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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author rogan
 */
public class buyRP {
    public void start(Stage primaryS){
        userRecord u = userRecord.getInstance();
        
        Label cost = new Label();
        double c = 0;
        for(book b : userSc.purchase){
            c+=b.getPrice();
        }
        int pl = 0;
        //did the conversions, if for every 100 points redeemed, 1 dollar is saved, then for every point, one cent is saved
        //multipled and divided c by 100 to make math easier, doubles did not cooperate and instead skipped decimals, resulting in point totals that were off
        if(login.loggedUser.getPoints()!=0){
            c = c*100;
            for(int p = login.loggedUser.getPoints()-1; p > 0; p--){
                c-= 1;
                if(c==0){
                    pl = p;
                    break;
                }
            }
            c = Math.floor(c/100);
        }
        login.loggedUser.setPoints(pl);
        cost.setText("Total Cost: $" + c);
        
        Label pc = new Label();
        int points = (int) (c*10);
        System.out.println(points);
        int po = points+login.loggedUser.getPoints();
        login.loggedUser.setPoints(po);
        if(login.loggedUser.getPoints()<1000){
            login.loggedUser.setStatus(new silverStatus());
        } else{
            login.loggedUser.setStatus(new goldStatus());
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
            else{System.out.println("Not deleted.");}
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
