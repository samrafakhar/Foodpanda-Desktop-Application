/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodpanda_main;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author dell i7
 */
public class Restaurant {
    int id;
    String name;
    String contactNumber;
    List<String> tags = new ArrayList<>();
    List<Location> locations = new ArrayList<>();
    public Menu menu = new Menu();
    public RestaurantManager rm;
    
    Restaurant(){
        
    }
    Restaurant(int i, String n, String c){
        id = i;
        name = n;
        contactNumber = c;
    }
    Restaurant(int i, String n, String c, List<String> t){
        id= i;
        name = n;
        contactNumber = c;
        tags = t;
    }
  
    Restaurant(String n, String c, RestaurantManager m){
        name = n;
        contactNumber = c;
        rm = m;
    }
    
    Restaurant (int i, String n, String c, Menu m){
        id = i;
        name = n;
        contactNumber = c;
        menu = m;
    }
    
    Menu getMenu()
    {
        return menu;
    }
    
    public RestaurantManager getRestaurantManager()
    {
        return rm;
    }
    
    void DisplayReviews(){
        Database db = new Database();
        db.DisplayReviews(this.id);
    }
    
    void addTagToRestaurant(String t){
        tags.add(t);
        Database db = new Database();
        db.AddTagToRestaurant(this.id, t);
        
    }
    
    void addLocationToRestaurant(Location loc, int locId){
        locations.add(loc);
        Database db = new Database();
        db.AddLocationToRestaurant(this.id, locId);
    }
}
