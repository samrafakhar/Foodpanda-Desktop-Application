/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodpanda_main;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dell i7
 */
public class Menu {
    
    List<MenuItem> items = new ArrayList<>();
    
    Menu(){
        
    }
    
    Menu(List<MenuItem> it){
        for(int i=0; i<it.size(); i++)
            items.add(it.get(i));
    }
    
    Boolean isEmpty(){
        return (items.isEmpty());
    }
     
    void AddItemToMenu(String restaurantName, MenuItem i){
        items.add(i);
        Database db = new Database();
        db.AddItemToMenu(restaurantName, i);
    }
    
}
