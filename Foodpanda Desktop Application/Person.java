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
public class Person {
    String name;
    String email;
    String username;
    String password;
    
    Person(){
        
    }
    
    Person(String n, String e, String u, String p){
        name = n;
        email = e;
        username = u;
        password = p;
    }
    
    Person(String u, String p){
        username = u;
        password = p;
    }
    
    void addPromoCode(String k, int j){
        
    }
    void AddLocation(){
        
    }
    void additem(String itemname){
        
    }
    void printAddresses(){
        
    }
    
    void AddLocation(Location loc){
    
    }
    
    Location getCurrentAddress(){
        return null;
    }
    
    void AddReview(String restName, int rate, String comment){
        
    }
    int AddRestaurant(Restaurant r, int id){
        return 0;
    }
    void DeleteRestaurant(Restaurant r){
        
    }
     void PlaceOrder() {
        
    }

    
    void ViewCart() {
        
    }
    
    void ConfirmOrder(Restaurant obj,String Desc)
    {
        
    }
    boolean ApplyPromoCode(String code)
    {
        return false;
    }
    void removeFromCart(String i) 
    {
    }
    void AddMenu(){
        
    }
    
    void EditMenu(){
        
    }
    
    void ForwardOrder(Order ord){
        
    }
    
    void ReceiveOrder(Order ord){
        
    }
    
    int getRestaurantID(){
        return 0;
    }
    void AddToCart(String i)
    {
        
    }
    
    String getRestaurantName(){
        return "";
    }
}
