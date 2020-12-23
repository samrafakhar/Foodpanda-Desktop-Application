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
public class Cart {
    int totalPrice = 0;
    List<MenuItem> items = new ArrayList<>();
    List<PromoCode> PromoCodes = new ArrayList<> ();
    
    Cart(){
        Database db = new Database();
        PromoCodes = db.fetchPromoCodes();  
    }
    /*Cart(List<PromoCode> list){
        for(PromoCode p : list){
            PromoCodes.add(p);
        }
    }
    void addPromoCode(PromoCode code){
        PromoCodes.add(code);
    }*/
    void addItem(MenuItem item){
        if(item!=null){
        items.add(item);
        totalPrice += item.price;}
    }
    void removeItem(MenuItem item){
        if(item!=null){
            if(items.isEmpty()){
                System.out.println("Cart is already empty");
            }
            else
            {
                boolean f=false;

                for(MenuItem m: items)
                {
                    if(m.name.equalsIgnoreCase(item.name))
                    {
                       items.remove(m); 
                       totalPrice -= item.price;
                       f=true;
                    }
                }
                if(f==false)
                    System.out.println("The object is not present in cart");

            }
        }
        
    }
    boolean ApplyPromoCode(PromoCode codes){
        boolean f = false;
        for(PromoCode m: PromoCodes)
            {
                if(m.code.equalsIgnoreCase(codes.code))
                {
                    f=true;
                    float subtract = (float)codes.discountPercentage/100;
                    subtract *= totalPrice;
                    totalPrice -= (int)subtract;
                    System.out.println("Promo Code added successfully!");
                    viewCart();
                    return true;
                }
                
            }
       
        if(f==false){
            System.out.println("Sorry! PromoCode is not valid");
            return false;
        }
        return f;
    }
    void viewCart(){
        if(items.isEmpty()){
            System.out.println("Cart is empty");
        }
        else {
            System.out.println("Items in cart:");
            for(MenuItem m : items){
                m.Print();
            }
            System.out.println("Total price: " + totalPrice);
            
        }
    }
  
}
