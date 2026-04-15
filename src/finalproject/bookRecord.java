/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author rogan
 */
public class bookRecord {
    public String fileN;
    private static bookRecord inst;
    private FileWriter f;
    
    //creates file if file doesn't exist
    public static bookRecord getInstance(){
        if(inst==null){
            inst = new bookRecord("books.txt");
        }
        return inst;
    }
    
       //file creation
    private bookRecord(String n){
        fileN = n;
        try{
            File f1 = new File(fileN);
            f1.createNewFile();
        } catch (IOException e){
            System.out.println("File Creation Error has occured.");
        }
    }
    
    //Main function when admin adds book to database
    public void write(String m){
        try{
            f = new FileWriter(fileN,true);
            f.write(m + "\n");
            f.close();
        } catch (IOException e){
            System.out.println("Write Error has occured.");
        }
    }
    //creates a temporary file that adds everything BUT the chosen, deleted book, deletes the old file and makes the temporary file into the permanent books.txt
    public void delete(String m){
        try{
            File f1 = new File(fileN);
            File f2 = new File("bookstmp.txt");
            f2.createNewFile();
            f = new FileWriter("bookstmp.txt", true);
            FileReader fi = new FileReader(fileN);
            Scanner s = new Scanner(fi);
            while(s.hasNextLine()){
                String l = s.nextLine();
                String[] p = l.split("<");
                //System.out.println(l);
                String[] c = m.split("<");
                if(!p[0].equals(c[0])){
                    f.write(l + "\n");
                }
            }
            f.close();
            fi.close();
            s.close();
            if(f1.delete()){System.out.println("Deleted.");}
            else{System.out.println("erm");}
            f2.renameTo(new File(fileN));
        } catch(IOException e){
            System.out.println("Delete error has occured.");
        }
    }
    
}
