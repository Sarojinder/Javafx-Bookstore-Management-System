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
public class adminBook extends Application{
    //table needed on this screen, setting up for it here
    TableView<book> books = new TableView<book>();
    ArrayList<book> book = new ArrayList<>();
    public void start(Stage primaryS){
        bookRecord b = bookRecord.getInstance();
       
        //setting up book arraylist
        try {
            FileReader r = new FileReader(b.fileN);
            Scanner s = new Scanner(r);
            while(s.hasNextLine()){
                String line = s.nextLine();
                String[] p = line.split("<");
                book.add(new book(p[0], Double.parseDouble(p[1])));
            }
            r.close();
            s.close();
        }catch(IOException e){
            System.out.println("File does not exist.");
        }
        
        //setting up observablelist for tableview
        ObservableList<book> li = FXCollections.observableArrayList(book);
        books.setItems(li);
        
        //setting up columns
        TableColumn<book,String> nameC = new TableColumn<book,String>("Book name");
        nameC.setCellValueFactory(new PropertyValueFactory("title"));
        TableColumn<book, Double> priC = new TableColumn<book, Double>("Price");
        priC.setCellValueFactory(new PropertyValueFactory("price"));
        
        nameC.setMinWidth(200);
        priC.setMinWidth(200);
        
        books.getColumns().setAll(nameC, priC);
        VBox table = new VBox();
        table.getChildren().add(books);
        
        TextField name = new TextField();
        TextField price = new TextField();
        Button add = new Button();
        
        name.setMaxWidth(200);
        name.setAlignment(Pos.TOP_CENTER);
        name.setPromptText("Book Name");
        price.setMaxWidth(200);
        price.setAlignment(Pos.TOP_CENTER);
        price.setPromptText("Price");
        add.setText("Add");
        add.setMaxSize(50, 5);
        add.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                //boolean and first for loop ensures that no duplicate books can be added
                boolean copy = false;
                for(book co : book){
                    System.out.println(co.getTitle() + "|" + name.getText());
                    if(co.getTitle().equals(name.getText())){
                        copy = true;
                    }
                }
                if(!copy){
                    b.write(name.getText() + "<" + price.getText());
                    book.add(new book(name.getText(), Double.parseDouble(price.getText())));
                    li.add(new book(name.getText(), Double.parseDouble(price.getText())));
                }
            }
        });
        
        HBox two = new HBox(name, price, add);
        two.setAlignment(Pos.TOP_CENTER);
        two.setSpacing(5);
        
        Button del = new Button();
        Button ex = new Button();
        del.setText("Delete");
        del.setMaxSize(100,5);
        del.setOnAction(new EventHandler<ActionEvent>(){
           public void handle(ActionEvent e){
               //similar to adminUser delete, look their for comments related to this section
               book delete = books.getSelectionModel().getSelectedItem();
               b.delete(delete.getTitle() + "<" + delete.getPrice());
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
        primaryS.setTitle("Admin - Books");
        primaryS.setScene(s);
        primaryS.show();
        
    }
}
