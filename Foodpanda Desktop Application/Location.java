/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodpanda_main;

/**
 *
 * @author dell i7
 */
public class Location {
    String address;
    String city;
    String area;
    
    Location(){
        
    }
    Location(String add, String a, String c){
        address= add;
        city = c;
        area = a;
    }
    
    int GetLocationId(){
        Database db = new Database();
        return db.GetLocationId(area, city);
    }
    
}
