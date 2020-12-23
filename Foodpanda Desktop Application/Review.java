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
public class Review {
    int rate;
    String comments;
    Person user;
    Restaurant rest;
    
    Review(){
        
    }
    Review(int r, String c, Person u, Restaurant re){
        rate = r;
        comments = c;
        user = u;
        rest = re;
    }
    
    void Store(){           //This function stores the current review into the db
        Database db = new Database();
        db.StoreReview(this);
    }
}
