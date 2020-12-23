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
public class FoodpandaAdministrator extends Person {
    
    FoodpandaAdministrator(String n, String e, String u, String p){
        super(n,e,u,p);
    }
    
    @Override
     void ForwardOrder(Order order)
       {
           System.out.println("Foodpanda administrator recieved the order: " + order.orderID);
           System.out.println("Foodpanda administrator forwarding the order to "+order.getOrderRestaurant().name+  " restaurant manager");
           
           Forwarding forward = new Forwarding();
           forward.doAction(order);
           Database db = new Database();
           db.changeOrderStatus(order, "Forwarding");
       }
     
     @Override
     int AddRestaurant(Restaurant r, int id){
         FoodPanda fp = FoodPanda.getInstance();
        if(!fp.restaurants.contains(r)){
            fp.restaurants.add(r);
            Database db = new Database();
            return db.InsertRestaurant(r, id);
        }
        else {
            System.out.println("Retaurant is already present in the list");
        }
        return 0;
    }
     @Override
    void DeleteRestaurant(Restaurant r){
        FoodPanda fp = FoodPanda.getInstance();
        if(fp.restaurants.contains(r)){
            fp.restaurants.remove(r);
            Database db = new Database();
            db.DeleteRestaurant(r);
        }
        else {
            System.out.println("Restaurant list does not contain this restaurant");
        }
    }
}
