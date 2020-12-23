/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodpanda_main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author dell i7
 */
public class User extends Person {
    String contactNumber;
    List<Location> addresses = new ArrayList<>();
    Location currentAddress = new Location();
    Cart obj=new Cart();
    
    User(){
        
    }
    
    User(String n, String e, String u, String p, String cN, Location cl){
        super(n,e,u,p);
        contactNumber = cN;
        currentAddress = cl;
    }
    
    User (String u, String p){
        super(u, p);
    }
    
    @Override
    void AddLocation(){
        Database db = new Database();
        Scanner input = new Scanner(System.in);
        System.out.println("Choose from the following cities");
        db.viewCities();
        String city = input.nextLine();
        System.out.println("Choose from the following areas");
        db.viewAreas(city);
        String area = input.nextLine();
        System.out.println("Enter your address");
        String add = input.nextLine();
        Location loc = new Location(add, area, city);
    
        db.addLocation(username, add, area, city);
        addresses.add(loc);
        currentAddress = loc;
    }
    @Override
    void AddLocation(Location loc){
        addresses.add(loc);
    }
    
    @Override
    void printAddresses(){
        for(int i=0; i<addresses.size(); i++)
            System.out.println(addresses.get(i).address + addresses.get(i).area + addresses.get(i).city);
    }
    
    @Override
    Location getCurrentAddress(){
        return currentAddress;
    }
    
    @Override
    void AddReview(String restName, int rate, String comment){
        FoodPanda fp = FoodPanda.getInstance();
        Restaurant r = new Restaurant();
        for(int i=0; i<fp.restaurants.size(); i++)
            if(fp.restaurants.get(i).name.equals(restName))
                r = fp.restaurants.get(i);
        Review review = new Review(rate, comment, this, r);
        review.Store();
    }
    
        @Override
    void AddToCart(String i)
    {
        Database con = new Database();
        MenuItem item = con.getMenuItem(i);
        obj.addItem(item);
    }
    
    @Override
    void ViewCart() 
    {
        obj.viewCart();
    }
    
    @Override
    void removeFromCart(String i) 
    {
        Database con = new Database();
        MenuItem item = con.getMenuItem(i);
        obj.removeItem(item);
    }
    
    @Override
    boolean ApplyPromoCode(String code){
        Database con = new Database();
        PromoCode item = con.getPromoCode(code);
        return obj.ApplyPromoCode(item);
    }
    
    @Override
    void additem(String itemname)
    {
        Database con = new Database();
        MenuItem item = con.getMenuItem(itemname);
        obj.addItem(item); 
    }
    @Override
    void ConfirmOrder(Restaurant ob,String Desc)
    {
        Order r = new Order(ob,obj.totalPrice,Desc,this);
        Database con = new Database();
        con.AddOrder(r);
    }
}
