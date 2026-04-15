/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package finalproject;

/**
 *
 * @author rogan
 */
public interface UserStatus {
    String handleStatus();
}

class goldStatus implements UserStatus{
    public String handleStatus(){
        return ("Status: Gold");
    }
}

class silverStatus implements UserStatus{
    public String handleStatus(){
        return ("Status: Silver");
    }
}