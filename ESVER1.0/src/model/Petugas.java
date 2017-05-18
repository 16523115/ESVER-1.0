/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author lowlight
 */
public class Petugas {
    String username = null;
    String password = null;
    
    public Petugas(String _username, String _password){
        this.username = _username;
        this.password = _password;
    }
    
    public String getUsername(){
        return username;
    }
    
    public String getPassword(){
        return password;
    }

}
