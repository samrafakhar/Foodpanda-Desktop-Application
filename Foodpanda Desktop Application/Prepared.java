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
public class Prepared extends Order_Status {
    @Override
    void doAction(Order obj){
        System.out.println("The status of the order is: Prepared");
        obj.setState(this);
        obj.status = "Prepared";
    }
}
