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
public class MenuItem {
    String name;
    String description;
    int price;
    String type;
    
    MenuItem(String Nam, String Descriptio, int Pric, String Typ)
    {
        name=Nam;
        description=Descriptio;
        price=Pric;
        type=Typ;
    }
    

    
    void Print()
    {
        System.out.print("         ");
        System.out.println(name);
        System.out.print("            ");
        System.out.println(description);
        System.out.print("            ");
        System.out.println(price);
        System.out.print("            ");
        System.out.println(type);
    }
    
       
}
