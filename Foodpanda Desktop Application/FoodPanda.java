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
public class FoodPanda {
    private static FoodPanda instance;
    List<Restaurant> restaurants = new ArrayList<>();
    List<PromoCode> PromoCodes = new ArrayList<> ();
    List<Order> orders = new ArrayList<>();
    Person currentUser;
    void RequestMenu(Restaurant required)
    {
        Database con = new Database();
        con.RequestMenu(required);
    }
    private FoodPanda(){
        Database db = new Database();
        restaurants = db.fetchAllRestaurants();
        orders = db.getAllOrders();
        PromoCodes = db.fetchPromoCodes(); 
        
    }
    public static synchronized FoodPanda getInstance(){
        if(instance == null)
            instance = new FoodPanda();
        return instance;
    }
    
    void CreateAccount(){
        
        Database db = new Database();
        
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your full name");
        String name = input.nextLine();
        System.out.println("Enter your email");
        String email = input.nextLine();
        System.out.println("Enter your contact number");
        String cn = input.nextLine();

        System.out.println("Choose from the following cities");
        db.viewCities();
        String city = input.nextLine();
        System.out.println("Choose from the following areas");
        db.viewAreas(city);
        String area = input.nextLine();
        System.out.println("Enter your address");
        String add = input.nextLine();
        
        
        System.out.println("Enter a unique username");
        String user = input.nextLine();
        
        while(db.checkUsername(user)==-1){
            System.out.println("That username is already taken. Enter a unique username");
            user = input.nextLine();
        }
        
        System.out.println("Enter password");
        String pass = input.nextLine();
        System.out.println("Confirm password");
        String pass2 = input.nextLine();
        
        while(!pass.equals(pass2)){
            System.out.println("The passwords do not match. Please try again.");
            System.out.println("Enter password");
            pass = input.nextLine();
            System.out.println("Confirm password");
            pass2 = input.nextLine();
        }
        
        Location loc = new Location(add, area, city);
        User u = new User(name, email, user, pass, cn, loc);
        
        db.insertUser(name, email, user, pass, cn);
        db.addLocation(user, add, area, city);
        currentUser = u;

    }
  
    int AuthenticateLogin(String username, String password){
        Database db = new Database();
        currentUser = db.loginUser(username, password);
        if(currentUser != null){
            db.fetchAllAreas(currentUser.username);
            return 1;
        }
        
        currentUser = db.loginAdmin(username, password);
        if(currentUser != null)
            return 2;
        
        currentUser = db.loginManager(username, password);
        if(currentUser!=null)
            return 3;  
        
        return -1;
    }
    
    void ViewCities(){
        Database db = new Database();
        db.viewCities();
    }
    
    void ViewAreas(String c){
        Database db = new Database();
        db.viewAreas(c);
    }
    
    Restaurant SearchRestaurants(String name){
        for(Restaurant r : restaurants){
            if(r.name.equalsIgnoreCase(name))
                return r;
        }
        System.out.println("Restaurant list does not contain this restaurant");
        return null;
    }
    
    void displayTags(){
        Database db = new Database();
        db.displayTags();
    }
    
    void displayRestaurants(String t)   //here we display restaurants with tag t and location according to the currentUser's location
    {
        for(int i=0; i<restaurants.size(); i++){
            if(restaurants.get(i).tags.contains(t)){
                for(int j=0; j<restaurants.get(i).locations.size(); j++){
                    if(restaurants.get(i).locations.get(j).area.equals(currentUser.getCurrentAddress().area) && 
                            restaurants.get(i).locations.get(j).city.equals(currentUser.getCurrentAddress().city)){
                        System.out.println(restaurants.get(i).name);
                        break;
                    }
                } 
            }
        }
    } 
    
    void displayAllRestaurants(){
        for(int i=0; i<restaurants.size(); i++){
            System.out.println(restaurants.get(i).name);
        }
    }
    
    void displayRestaurants(){
        
        for(int i=0; i<restaurants.size(); i++){
            for(int j=0; j<restaurants.get(i).locations.size(); j++){
                if(restaurants.get(i).locations.get(j).area.equals(currentUser.getCurrentAddress().area) &&
                  restaurants.get(i).locations.get(j).city.equals(currentUser.getCurrentAddress().city)){
                    System.out.println(restaurants.get(i).name);
                    break;
                }
            } 
        }
    }
    
    void viewAvailableRestaurantManagers(){
        Database db = new Database();
        db.viewAvailableRestaurantManagers();
    }
    
    RestaurantManager getManager(int id){
        Database db = new Database();
        RestaurantManager r = db.getManager(id);
        return r;
    }
    
    void AddReview(Review r){
        
    }
}
