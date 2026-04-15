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
import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author rogan
 */

//Screen where admin can alter user information (add or delete users to database)
public class adminUser extends Application{
    
    //Table and arraylist initialized, arraylist required for table to work
    TableView<user> users = new TableView<user>();
    ArrayList<user> user = new ArrayList<>();
    public void start(Stage primaryS){
        userRecord u = userRecord.getInstance();
       
        //setting up arraylist
        try {
            FileReader r = new FileReader(u.fileN);
            Scanner s = new Scanner(r);
            while(s.hasNextLine()){
                String line = s.nextLine();
                String[] p = line.split("<");
                user.add(new user(p[0], (p[1]), Integer.parseInt(p[2])));
            }
            r.close();
            s.close();
        }catch(IOException e){
            System.out.println("File does not exist.");
        }
        
        //observablelist for tableview, checks for any changes made in real time and updates table accordingly
        ObservableList<user> li = FXCollections.observableArrayList(user);
        users.setItems(li);
        
        //setting up table
        TableColumn<user,String> nameC = new TableColumn<user,String>("Username");
        nameC.setCellValueFactory(new PropertyValueFactory("user"));
        TableColumn<user,String> passC = new TableColumn<user,String>("Password");
        passC.setCellValueFactory(new PropertyValueFactory("pass"));
        TableColumn<user,Integer> pointsC = new TableColumn<user,Integer>("Points");
        pointsC.setCellValueFactory(new PropertyValueFactory("points"));
        
        nameC.setMinWidth(200);
        passC.setMinWidth(200);
        pointsC.setMinWidth(200);
        
        users.getColumns().setAll(nameC, passC, pointsC);
        VBox table = new VBox();
        table.getChildren().add(users);
        
        //setting up adding screen
        TextField name = new TextField();
        TextField pass = new TextField();
        Button add = new Button();
        
        name.setMaxWidth(200);
        name.setAlignment(Pos.TOP_CENTER);
        name.setPromptText("Username");
        pass.setMaxWidth(200);
        pass.setAlignment(Pos.TOP_CENTER);
        pass.setPromptText("Password");
        add.setText("Add");
        add.setMaxSize(50, 5);
        add.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                // symbol "<" is added to divide parts of the string to ensure that each part (username, password, points) can be easily identified
                u.write(name.getText() + "<" + pass.getText() + "<0");
                user.add(new user(name.getText(), pass.getText(), 0));
                li.add(new user(name.getText(), pass.getText(), 0));
            }
        });
        
        HBox two = new HBox(name, pass, add);
        two.setAlignment(Pos.TOP_CENTER);
        two.setSpacing(5);
        
        //setting up delete and logout buttons
        Button del = new Button();
        Button ex = new Button();
        del.setText("Delete");
        del.setMaxSize(100,5);
        del.setOnAction(new EventHandler<ActionEvent>(){
           public void handle(ActionEvent e){
               //users.getSelectionModel is what allows the admin to click a row directly and delete that specific row, can get that specific data from that row and delete from the arraylist and textfile
               user delete = users.getSelectionModel().getSelectedItem();
               u.delete(delete.getUser() + "<" + delete.getPass());
               li.remove(delete);
           } 
        });
        ex.setText("Exit");
        ex.setMaxSize(50, 50);
        ex.setOnAction(new EventHandler<ActionEvent>(){
           public void handle(ActionEvent e){
               adminSc a = new adminSc();
               a.start(primaryS);
           } 
        });
        
        HBox three = new HBox(del, ex);
        three.setAlignment(Pos.TOP_CENTER);
        three.setSpacing(5);
        
        VBox overall = new VBox();
        overall.getChildren().addAll(table, two, three);
        overall.setAlignment(Pos.CENTER);
        overall.setSpacing(10);
        
        Scene s = new Scene(overall, 1080, 700);
        primaryS.setWidth(1080);
        primaryS.setHeight(700);
        primaryS.setTitle("Admin - Users");
        primaryS.setScene(s);
        primaryS.show();
        
    }
}
