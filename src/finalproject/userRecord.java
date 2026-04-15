/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;

import java.io.*;
import java.util.Scanner;
/**
 *
 * @author rogan
 */
public class userRecord {
    public String fileN;
    private static userRecord inst;
    private FileWriter f;
    
    //creates file if file doesn't exist
    public static userRecord getInstance(){
        if(inst==null){
            inst = new userRecord("customers.txt");
        }
        return inst;
    }
    
       //file creation
    private userRecord(String n){
        fileN = n;
        try{
            File f1 = new File(fileN);
            f1.createNewFile();
            f = new FileWriter(fileN, true);
            f.close();
        } catch (IOException e){
            System.out.println("File Creation Error has occured.");
        }
    }
    
    //Main function when admin adds users to the database
    public void write(String m){
        try{
            f = new FileWriter(fileN,true);
            f.write(m + "\n");
            f.close();
        } catch (IOException e){
            System.out.println("Write Error has occured.");
        }
    }
    
    //creates a temporary file that adds everything BUT the chosen, deleted user, deletes the old file and makes the temporary file into the permanent customers.txt
     public void delete(String m){
        try{
            File f1 = new File(fileN);
            File f2 = new File("userstmp.txt");
            f2.createNewFile();
            f = new FileWriter("userstmp.txt", true);
            FileReader fi = new FileReader(fileN);
            Scanner s = new Scanner(fi);
            while(s.hasNextLine()){
                String l = s.nextLine();
                String[] p = l.split("<");
                String[] c = m.split("<");
                if(!p[0].equals(c[0])){
                    f.write(l + "\n");
                }
            }
            f.close();
            fi.close();
            s.close();
            f1.delete();
            f2.renameTo(new File(fileN));
        } catch(IOException e){
            System.out.println("Delete error has occured.");
        }
    }
    
}
