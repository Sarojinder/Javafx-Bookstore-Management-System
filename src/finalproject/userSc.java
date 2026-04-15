/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.event.*;
import javafx.stage.*;
import javafx.geometry.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author rogan
 */
public class userSc extends Application {
    static ArrayList<book> purchase = new ArrayList<book>();
    public void start (Stage primaryS){
        //purchase array list: since multiple books can be chosen to purchase, once purchase option is selected, must keep all selected books in reach for other classes
        //purchase is cleared here so previosly purchased books are not added back into the payment
        purchase.clear();
        //have to interact with logged in user, using static variable from login works
        user U = login.loggedUser;
        if(U.getPoints()<1000){
            U.setStatus(new silverStatus());
        } else{
            U.setStatus(new goldStatus());
        }
        U.request();
        String gr = ("Hello " + U.getUser() + ". You have " + U.getPoints() + " points. " + U.getStatus());
        Label greet = new Label(gr);
        
        //setting up for table of purchasable books
        bookRecord b = bookRecord.getInstance();
        ArrayList<book> bo = new ArrayList<>();
        TableView<book> boo = new TableView<book>();
        boo.setEditable(true);
        
        //arraylist setup
        try {
            FileReader r = new FileReader(b.fileN);
            Scanner s = new Scanner(r);
            while(s.hasNextLine()){
                String line = s.nextLine();
                String[] p = line.split("<");
                System.out.println(p[0] + " " + p[1]);
                bo.add(new book(p[0], Double.parseDouble(p[1])));
            }
            r.close();
            s.close();
        }catch(IOException e){
            System.out.println("File does not exist.");
        }
        //table setup
        ObservableList<book> li = FXCollections.observableArrayList(bo);
        boo.setItems(li);
        
        TableColumn<book,String> nameC = new TableColumn<book,String>("Book name");
        nameC.setCellValueFactory(new PropertyValueFactory("title"));
        TableColumn<book, Double> priC = new TableColumn<book, Double>("Price");
        priC.setCellValueFactory(new PropertyValueFactory("price"));
        TableColumn<book, Boolean> check = new TableColumn<book, Boolean>("Selected");
        check.setCellFactory(CheckBoxTableCell.forTableColumn(check));
        check.setCellValueFactory(data -> data.getValue().selectedProperty());
        
        nameC.setMinWidth(200);
        priC.setMinWidth(200);
        check.setMinWidth(200);
        
        boo.getColumns().setAll(nameC, priC, check);
        VBox table = new VBox();
        table.getChildren().add(boo);
        
        Button buy = new Button();
        buy.setText("Buy");
        buy.setMaxSize(150,100);
        
        Button buyRP = new Button();
        buyRP.setText("Redeem points and Buy");
        buyRP.setMaxSize(150,100);
        
        //for both buy options, has a for loop going through each book saved in arraylist, checking if they're checked with a boolean property attached to them through the table
        buy.setOnAction(new EventHandler<ActionEvent>(){
           public void handle(ActionEvent e){
               for(book pb : bo){
                   if(pb.getCheck()){
                       purchase.add(pb);
                   }
               }
               buy n = new buy();
               n.start(primaryS);
           } 
        });
        
        buyRP.setOnAction(new EventHandler<ActionEvent>(){
           public void handle(ActionEvent e){
               for(book pb : bo){
                   if(pb.getCheck()){
                       purchase.add(pb);
                   }
               }
               buyRP n = new buyRP();
               n.start(primaryS);
           } 
        });
        
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
        
        HBox three = new HBox();
        three.setAlignment(Pos.CENTER);
        three.getChildren().add(buy);
        three.getChildren().add(buyRP);
        three.getChildren().add(l);
        three.setSpacing(8);
        
        VBox v = new VBox();
        v.setAlignment(Pos.CENTER);
        v.getChildren().add(greet);
        v.getChildren().add(table);
        v.getChildren().add(three);
        v.setSpacing(25);
        
        Scene s = new Scene(v, 1080, 700);
        primaryS.setWidth(1080);
        primaryS.setHeight(700);
        primaryS.setTitle("Purchase Screen");
        primaryS.setScene(s);
        primaryS.show();
    }
}
