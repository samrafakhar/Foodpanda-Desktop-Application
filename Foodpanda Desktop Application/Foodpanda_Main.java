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
public class Foodpanda_Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FoodPanda fp = FoodPanda.getInstance();
        Scanner input = new Scanner(System.in);
        System.out.println("Press 1 to Create a new Account\nPress 2 to Login to an existing account");
        String choice = input.nextLine();

        if(choice.equals("1")){
            fp.CreateAccount(); 
        }
        else if(choice.equals("2")){
            System.out.println("Enter your username");
            String username = input.nextLine();
            System.out.println("Enter your password");
            String password = input.nextLine();
            int userCategory = fp.AuthenticateLogin(username, password);
            while(userCategory == -1)
            {
                System.out.println("Invalid username or password. Please try again");
                System.out.println("Username: ");
                username = input.nextLine();
                System.out.println("Password: ");
                password = input.nextLine();
                userCategory = fp.AuthenticateLogin(username, password);
            }
            if(userCategory == 1)                           //means normal user, not admin or manager
            {          
                System.out.println("\nWelcome to Foodpanda\nYou have successfully logged in\n\n");
                
                int menu = 1;
                while(menu == 1){
                    System.out.println("Press 1 to View Restaurants");
                    System.out.println("Press 2 to Add a new Location");
                    System.out.println("Press 3 to logout");
                    int userInput = input.nextInt();
                    if(userInput == 1)
                    {
                      System.out.println("Press 1 to Apply Filter");
                    System.out.println("Press 2 to view all restaurants in your area");
                    int filter = input.nextInt();
                    if(filter == 1)
                    {
                        System.out.println("Choose a filter");
                        fp.displayTags();
                        input.nextLine();
                        String tag = input.nextLine();
                        System.out.println("\n");
                        fp.displayRestaurants(tag);
                    }
                    else if (filter == 2)
                    {
                        fp.displayRestaurants();
                        input.nextLine();
                    }
                    
                    System.out.println("Enter the name of the restaurant you wish to choose");
                        String restName = input.nextLine();
                        boolean flag = false;
                        for(int i=0; i<fp.restaurants.size(); i++){
                            if(fp.restaurants.get(i).name.equals(restName))
                                flag = true;
                        }
                        if(flag){
                            System.out.println("\nWelcome to " + restName +"!");
                            System.out.println("Press 1 to view menu");
                            System.out.println("Press 2 to add a review");
                            System.out.println("Press 3 to view reviews");
                            int ch = input.nextInt();
                            
                            String Description = null;
                            if(ch == 1)
                            {
                                 Restaurant obj = fp.SearchRestaurants(restName);
                                 
                                 
                                 //Adding items to cart
                                String num = null;
                                
                                 
                                System.out.println("\n\nMenu of the Restaurant\n");
                                fp.RequestMenu(obj);
                                
                                System.out.println("\nPress X to exit menu");
                                System.out.println("Press C to add items to cart\n");
                                
                                num = input.nextLine();
                                num = input.nextLine();
                                
                                if(num.equalsIgnoreCase("C")){
                                String itemName;
                                if(num.equalsIgnoreCase("c"))
                                { 
                                    System.out.println("\n\nPlease enter menuItem name you want to purchase");
                                    itemName = input.nextLine();
                                    fp.currentUser.AddToCart(itemName);
                                }
                               
                                System.out.println("\nPress X to stop adding items to cart\n");
                                System.out.println("Press C to continue adding items to cart\n");
                                num = input.nextLine();
                                
                                
                                while(num.equalsIgnoreCase("c"))
                                 {
                                     System.out.println("\n\nMenu of the Restaurant\n");
                                     fp.RequestMenu(obj);
                                     System.out.println("\n\nPlease enter menuItem name you want to purchase");
                                     itemName = input.nextLine();
                                     fp.currentUser.AddToCart(itemName);
                                     
                                     System.out.println("\n\nPress X to stop adding items to cart\n");
                                     System.out.println("Press C to continue adding items to cart\n");
                                     num = input.nextLine();
                                 }
                                 
                                 System.out.println("Press 1 to View Cart");
                                 System.out.println("Press 2 to Skip Viewing Cart");
                                 int number = input.nextInt();
                                 if(number ==1)
                                 {
                                    fp.currentUser.ViewCart();  
                                 }
                                 
                                 System.out.println("Press 1 to Edit Cart");
                                 System.out.println("Press 2 to Add Additional information");
                                 System.out.println("Press 3 when you're done");
                                 int in = input.nextInt();
                                 
                                 while(in != 3)
                                    {
                                        if(in == 1)
                                        {
                                            int k=0;
                                            System.out.println("Press 1 to Add Items in Cart");
                                            System.out.println("Press 2 to Remove Items in Cart");
                                            System.out.println("Press 3 when you're done");
                                            k = input.nextInt();

                                            while(k !=3)
                                            {
                                                if(k==1)
                                                {
                                                    System.out.println("\n\nMenu of the Restaurant\n");
                                                    fp.RequestMenu(obj);
                                                    System.out.println("\n\nPlease enter menuItem name you want to purchase");
                                                    itemName = input.nextLine();
                                                    itemName = input.nextLine();
                                                    fp.currentUser.additem(itemName);
                                                }
                                                else if(k==2)
                                                {
                                                    System.out.println("\n\nYour Cart\n");
                                                    fp.currentUser.ViewCart();
                                                    System.out.println("\n\nPlease enter menuItem name you want to remove");
                                                    itemName = input.nextLine();
                                                    itemName = input.nextLine();
                                                    fp.currentUser.removeFromCart(itemName);
                                                }

                                                System.out.println("Press 1 to continue Add Items in Cart");
                                                System.out.println("Press 2 to continue Remove Items in Cart");
                                                System.out.println("Press 3 when you're done");
                                                k = input.nextInt();
                                            }

                                                System.out.println("\n\n Updated Cart\n");
                                                fp.currentUser.ViewCart();
                                        }
                                        else if(in ==2)
                                        {
                                            System.out.println("Enter Additional information");
                                            Description = input.nextLine();
                                            Description = input.nextLine();
                                        }
                                        
                                          System.out.println("Press 1 to Edit Quantity of Items in Cart");
                                          System.out.println("Press 2 to Add Additional information");
                                          System.out.println("Press 3 When you're done");
                                          in = input.nextInt();
                                    }
                                 
                                 System.out.println("Press 2 to Confirm Order");
                                 System.out.println("Press 1 to Discard Order");
                                 int n= input.nextInt();
                                 
                                 if(n==1)
                                 {
                                     System.out.println("Your Order has been dicarded");
                                 }
                                 else if(n==2)
                                 {
                                     System.out.println("Press 1 to enter promoCode");
                                     System.out.println("Press 2 to skip promoCode");
                                     int inp = input.nextInt();
                                     if(inp == 1)
                                     {
                                         System.out.println("Press Enter promoCode");
                                         String promo = input.nextLine();
                                         promo = input.nextLine();
                                         boolean pro = fp.currentUser.ApplyPromoCode(promo);
                                         if(!pro)
                                         {
                                              fp.currentUser.ConfirmOrder(obj,Description);
                                         }
                                         
                                     }
                                     
                                     
                                     fp.currentUser.ConfirmOrder(obj,Description);
                                 }
                                     
                                 }
                               
                            }
                            else if(ch == 2)
                            {
                                System.out.println("Enter your rating");
                                int rate = input.nextInt();
                                input.nextLine();
                                System.out.println("Enter comments");
                                String com = input.nextLine();
                                fp.currentUser.AddReview(restName, rate, com);
                            }
                            else if(ch == 3)
                            {
                                Restaurant r = fp.SearchRestaurants(restName);
                                if( r != null){
                                    System.out.println("\nHere are the reviews: \n");
                                    r.DisplayReviews();
                                }
                            }
                            
                        }
                        else{
                            System.out.println("The restaurant does not exist");
                        }
                    }
                    else if (userInput == 2)
                    {
                        FoodPanda.getInstance().currentUser.AddLocation();
                    }
                    else if (userInput == 3){
                        return;
                    }
                    System.out.println("\nTo go back to the main menu, press 1");
                    System.out.println("To logout, press 2");
                    menu = input.nextInt();
                }
                
            }
            else if(userCategory == 2)                      //means foodpanda administrator
            {
                System.out.println("\nWelcome to Foodpanda\nYou have successfully logged in\nFoodPanda Administrator View\n\n");
                int menu = 1;
                
                while(menu == 1){
                    System.out.println("Press 1 to Add a new Restaurant");
                    System.out.println("Press 2 to Delete an existing Restaurant");
                    System.out.println("Press 3 to forward pending orders to restaurant managers");
                    int c = input.nextInt();
                    if(c == 1)
                    {
                        System.out.println("Enter restaurant name");
                        input.nextLine();
                        String name = input.nextLine();
                        System.out.println("Enter Contact Number");
                        String cn = input.nextLine();

                        System.out.println("\nHere is a list of restaurant managers");
                        fp.viewAvailableRestaurantManagers();
                         System.out.println("Enter the id of the manager of that restaurant");
                        System.out.println("\n");
                        int id = input.nextInt();
                        RestaurantManager rm = fp.getManager(id);
                        Restaurant rest = new Restaurant(name, cn, rm);
                        int restId = fp.currentUser.AddRestaurant(rest, id);
                        rest.id = restId;
                        System.out.println("Do you want to add tags to the restaurant for applying filter in search?");
                        input.nextLine();
                        List<String> tags = new ArrayList<>();
                        if(input.nextLine().equalsIgnoreCase("YES"))
                        {
                            System.out.println("\nHere is a list of available tags");
                            fp.displayTags();
                            System.out.print("Enter the name of tag to add it to the new restaurant");
                            String t = input.nextLine();
                            tags.add(t);
                            rest.addTagToRestaurant(t);

                            System.out.println("Do you wish to add another tag?");
                            String ans = input.nextLine();
                            while(ans.equalsIgnoreCase("YES")){
                                System.out.println("Enter the name of tag to add it to the new restaurant");
                                t = input.nextLine();
                                tags.add(t);
                                rest.addTagToRestaurant(t);
                                System.out.println("Do you wish to add another tag?");
                                ans = input.nextLine();
                            }
                        }
                        System.out.println("\nNow please add the location of the restaurant");
                        System.out.println("Choose from the following cities");
                        fp.ViewCities();
                        String city = input.nextLine();
                        System.out.println("Choose from the following areas");
                        fp.ViewAreas(city);
                        String area = input.nextLine();
                        Location restLocation = new Location("", area, city);
                        int locId = restLocation.GetLocationId();
                        rest.addLocationToRestaurant(restLocation, locId);
                        System.out.println("\nDo you wish to add the location of another branch?");
                        String ans = input.nextLine();
                        while(ans.equalsIgnoreCase("YES")){
                            System.out.println("Choose from the following cities");
                            fp.ViewCities();
                            city = input.nextLine();
                            System.out.println("Choose from the following areas");
                            fp.ViewAreas(city);
                            area = input.nextLine();
                            restLocation = new Location("", area, city);
                            locId = restLocation.GetLocationId();
                            rest.addLocationToRestaurant(restLocation, locId);

                            System.out.println("\nDo you wish to add the location of another branch?");
                            ans = input.nextLine();
                        }

                    }
                    else if (c == 2)
                    {
                        System.out.println("\nHere is a list of all the restaurants");
                        fp.displayAllRestaurants();
                        System.out.println("Enter the name of the restaurant to delete");
                        input.nextLine();
                        String r = input.nextLine();
                        Restaurant rest = fp.SearchRestaurants(r);
                        if(rest!=null){
                            fp.currentUser.DeleteRestaurant(rest);
                        }
                    }
                    else if(c == 3)
                    {
                        System.out.println("Forwarding pending orders to corresponsing restaurant managers");
                        for(int i =0; i< FoodPanda.getInstance().orders.size(); i++){
                            if(FoodPanda.getInstance().orders.get(i).status.equalsIgnoreCase("pending"))
                                fp.currentUser.ForwardOrder(FoodPanda.getInstance().orders.get(i));
                        }
                    }
                    
                    System.out.println("To go back to the main menu, press 1");
                    System.out.println("To logout, press 2");
                    menu = input.nextInt();
                }
                
            }
            else if(userCategory == 3)                      //means restaurant manager
            {
                System.out.println("\nWelcome to Foodpanda\nYou have successfully logged in\nRestaurant Manager View\n");
                System.out.println("Restaurant: " + fp.currentUser.getRestaurantName() +"\n\n");
                
                int menu = 1;
                
                while (menu == 1){
                    System.out.println("Press 1 to Add Menu");
                    System.out.println("Press 2 to Edit Menu");
                    System.out.println("Press 3 to Receive Pending Orders");
                    
                    int userChoice = input.nextInt();
                    if(userChoice == 1){
                        fp.currentUser.AddMenu();
                    }
                    else if(userChoice == 2){
                        fp.currentUser.EditMenu();
                    }
                    else if(userChoice == 3){
                        for(int i =0; i< FoodPanda.getInstance().orders.size(); i++){
                            if(FoodPanda.getInstance().orders.get(i).status.equalsIgnoreCase("Forwarding"))
                                if(fp.currentUser.getRestaurantID() == FoodPanda.getInstance().orders.get(i).restaurant.id)
                                fp.currentUser.ReceiveOrder(FoodPanda.getInstance().orders.get(i));
                        }
                    }
                    System.out.println("To go back to the main menu, press 1");
                    System.out.println("To logout, press 2");
                    menu = input.nextInt();
                }
            }
            
        }
        else{
            System.out.println("Wrong choice. Bye");
        }
        
    }
    
}
