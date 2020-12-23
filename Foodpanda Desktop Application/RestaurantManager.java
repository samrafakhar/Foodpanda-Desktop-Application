/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodpanda_main;


import java.util.Scanner;

/**
 *
 * @author dell i7
 */
public class RestaurantManager extends Person {
    int id;
    Restaurant MyRestaurant = new Restaurant();
    
    RestaurantManager(int i, String n, String e, String u, String p){
        super(n,e,u,p);
        id = i;
        Database db = new Database();
        MyRestaurant = db.getRestaurantOfManager(id);
        if(MyRestaurant != null){
            MyRestaurant.menu.items = db.getItemsOfMenu(db.getMenuId(MyRestaurant.name));
            MyRestaurant.rm = this;
        }
    }
    RestaurantManager(String n, String e, String u, String p, Restaurant r){
        super(n,e,u,p);
        MyRestaurant = r;
    }
   
    int getRestaurantID(){
        return MyRestaurant.id;
    }
    
    @Override
    String getRestaurantName(){
        return MyRestaurant.name;
    }
    
    //restaurant manager
        @Override
       void ReceiveOrder(Order order)
       {
           String restName=order.getOrderRestaurant().name;
           System.out.println("restaurant manager of " + restName + " recieved the order: " + order.orderID);
           processOrder(order);
       }
       
       void processOrder(Order order)
       {
           System.out.println("To confirm order press 1. to cancel it press 2");
           Scanner scan=new Scanner(System.in); 
           int c=scan.nextInt();
           
           if(c==1){
               Database db = new Database();
               db.changeOrderStatus(order, "Confirmed");
               Confirmed confirm = new Confirmed();
               confirm.doAction(order);

               prepareOrder(order);
           }
           
           else{
               Database db = new Database();
               db.changeOrderStatus(order, "Cancelled");
               Cancelled cancel = new Cancelled();
               cancel.doAction(order);
           }
       }
       
       void prepareOrder(Order order)
       {
           Prepared prepare = new Prepared();
           prepare.doAction(order);
       }
       
       // restaurnat manager has an object of Restaurant class. MyRestaurant
       
       @Override
       void AddMenu()
       {
           if(MyRestaurant.menu.isEmpty())
           {
               Scanner input = new Scanner(System.in);
               Database db = new Database();
               db.CreateMenu(MyRestaurant.name);
               System.out.println("\nPress S to add a new section");
               
               String choice = input.nextLine();
               while(choice.equalsIgnoreCase("S")){
                   System.out.println("\nEnter Section Name");

                   String section = input.nextLine();
                   System.out.println("\nTo add an item, press i");
                   String it = input.nextLine();
                   while(it.equalsIgnoreCase("i")){
                       
                       System.out.println("Enter item name");
                       String n = input.nextLine();
                       System.out.println("Enter item description");
                       String desc = input.nextLine();
                       System.out.println("Enter item price");
                       int p = input.nextInt();
                       
                       MenuItem item = new MenuItem(n, desc, p, section);
                       MyRestaurant.menu.items.add(item);
                       db.AddItemToMenu(MyRestaurant.name, item);
                       
                       System.out.println("\nTo add another item, press i\nTo stop adding items, press E");
                       input.nextLine();
                       it = input.nextLine();
                   }
                   
                   
                    System.out.println("\nPress S to add another new section");
                    System.out.println("Press E to exit");
                    choice = input.nextLine();
               }
               
               
           }
           else{
               System.out.println("Menu already exists");
           }
       }
       
       @Override 
       void EditMenu()
       {
           Database db = new Database();
           db.RequestMenu(MyRestaurant);
           Scanner input = new Scanner(System.in);
           System.out.println("\n\nPress 1 to Add a new item");
           System.out.println("Press 2 to edit an existing item");
           System.out.println("Press 3 to Delete an item");
           int choice = input.nextInt();
           if(choice == 1)
           {
               AddItem();
               
               System.out.println("\nTo add another item, press 1\nTo view the updated menu, press 2");
               int ch = input.nextInt();
               while( ch == 1){
                   AddItem();
                   System.out.println("\nTo add another item, press 1\nTo view the updated menu, press 2");
                   ch = input.nextInt();
               }
               if ( ch == 2 ){
                    System.out.println("\nHere is the Updated Menu");
                    db.RequestMenu(MyRestaurant);
               }
           }
           else if(choice == 2)
           {
               db.RequestMenu(MyRestaurant);
               EditItem();
               System.out.println("\nTo edit another item, press 1\nTo view the updated menu, press 2");
               int ch = input.nextInt();
               while( ch == 1){
                   EditItem();
                   System.out.println("\nTo edit another item, press 1\nTo view the updated menu, press 2");
                   ch = input.nextInt();
               }
               if ( ch == 2 ){
                    System.out.println("\nHere is the Updated Menu");
                    db.RequestMenu(MyRestaurant);
               }
               
           }
           else if(choice == 3)
           {
               db.RequestMenu(MyRestaurant);
               DeleteItem();
               
               System.out.println("\nTo delete another item, press 1\nTo view the updated menu, press 2");
               int ch = input.nextInt();
               while( ch == 1){
                   DeleteItem();
                   System.out.println("\nTo delete another item, press 1\nTo view the updated menu, press 2");
                   ch = input.nextInt();
               }
               if ( ch == 2 ){
                    System.out.println("\nHere is the Updated Menu");
                    db.RequestMenu(MyRestaurant);
               }
           } 
           
       }
       
       void AddItem()
       {
           if(!MyRestaurant.menu.isEmpty()){
                Database db = new Database();
                Scanner input = new Scanner(System.in);
                System.out.println("Which of the following sections do you wish to add the new item to?");
                db.viewSections(MyRestaurant);
                String section = input.nextLine();
                
                System.out.println("Enter new item details:");
                System.out.println("Name: ");
                String n = input.nextLine();
                System.out.println("Description: ");
                String desc = input.nextLine();
                System.out.println("Price: ");
                int p = input.nextInt();
                
                MenuItem item = new MenuItem(n, desc, p, section);
                db.AddItemToMenu(MyRestaurant.name, item);
                MyRestaurant.menu.items.add(item);
           }
      
       }
       
       void EditItem()
       {
            if(!MyRestaurant.menu.isEmpty()){
               Database db = new Database();
                Scanner input = new Scanner(System.in);
                System.out.println("Which of the following sections does the item belong to?");
                db.viewSections(MyRestaurant);
                String section = input.nextLine();
                
                System.out.println("Enter the name of the item to edit: ");
                String n = input.nextLine();
                
                System.out.println("To edit name press 1");
                System.out.println("To edit description press 2");
                System.out.println("To edit price press 3");
              
                System.out.println("To exit press 4");
                int choice = input.nextInt();
                if(choice == 1)
                {
                    System.out.println("Enter new name");
                    input.nextLine();
                    String newName = input.nextLine();
                    db.EditItemName(MyRestaurant.name, n, newName);
                    
                    for(int i=0; i<MyRestaurant.menu.items.size(); i++){
                        if(MyRestaurant.menu.items.get(i).name.equalsIgnoreCase(n)){
                            MyRestaurant.menu.items.get(i).name = newName;
                        }
                    }
                    
                }
                else if (choice == 2)
                {
                    System.out.println("Enter new description");
                    input.nextLine();
                    String desc = input.nextLine();
                    db.EditItemDescription(MyRestaurant.name, n, desc);
                    
                    for(int i=0; i<MyRestaurant.menu.items.size(); i++){
                        if(MyRestaurant.menu.items.get(i).name.equalsIgnoreCase(n)){
                            MyRestaurant.menu.items.get(i).description = desc;
                        }
                    }
                }
                else if (choice == 3)
                {
                    System.out.println("Enter new price");
                    int p = input.nextInt();
                    db.EditItemPrice(MyRestaurant.name, n, p);
                    for(int i=0; i<MyRestaurant.menu.items.size(); i++){
                        if(MyRestaurant.menu.items.get(i).name.equalsIgnoreCase(n)){
                            MyRestaurant.menu.items.get(i).price = p;
                        }
                    }
                }
            } 
                
       }
       
       void DeleteItem()
       {
           Database db = new Database();
           Scanner input = new Scanner(System.in);
         
           System.out.println("Enter the name of the item to delete: ");
           String n = input.nextLine();
           
           db.deleteItem(MyRestaurant.name, n);
           for(int i=0; i<MyRestaurant.menu.items.size(); i++){
                    if(MyRestaurant.menu.items.get(i).name.equalsIgnoreCase(n)){
                        MyRestaurant.menu.items.remove(i);
                    }
                }
       }
}
