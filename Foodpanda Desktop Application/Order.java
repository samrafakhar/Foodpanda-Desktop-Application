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
public class Order {
    int totalPrice;
    int orderID;
    String status;
    Order_Status state;
    String Description;
    User id;
    Restaurant restaurant;
    Order(){
        
    }
    
    Order(int tp,String desc, User uid){
        id= uid;
        totalPrice = tp;
        Description = desc;
        status = "Pending";
    }
    
    
    Order( Restaurant r, int tp,String desc,User uid)
    {
        restaurant=r;
        id = uid;
        totalPrice = tp;
        Description = desc;
        status = "Pending";
    }
    
    Order(int o, int p, String st, Order_Status s, Restaurant r){
        orderID = o;
        totalPrice = p;
        status = st;
        state = s;
        restaurant = r;
    }
    
    Order(int tp){
        totalPrice = tp;
       
        Pending pending = new Pending();
        pending.doAction(this);
    }
    
    
    Order( Restaurant r, int tp)
    {
        restaurant=r;
        totalPrice = tp;
        Pending pending = new Pending();
        pending.doAction(this);
    }
    
    void setState(Order_Status s)
    {
        state = s;
    }
    
    public Restaurant getOrderRestaurant()
    {
        return restaurant;
    }
    
}
