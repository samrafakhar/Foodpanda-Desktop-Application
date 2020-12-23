/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodpanda_main;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dell i7
 */
public class Database {
    Connection con;
    Statement stmt;
    
    Database(){
        try{
            String conn = "jdbc:sqlserver://localhost:1433;databaseName=fp";
            con=DriverManager.getConnection(conn,"user","123");
            stmt = con.createStatement(); 
            
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    void displayAll(){
        try
        {
            ResultSet rs=stmt.executeQuery("select * from [dbo].[user]");
             while(rs.next())
             {
                System.out.println(rs.getInt(1)+"  "+rs.getString(2));
             }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    void viewCities(){
        try
        {
            ResultSet rs=stmt.executeQuery("select distinct(city) from [dbo].[location]");
             while(rs.next())
             {
                System.out.println(rs.getString(1));
             }
        }
        catch(Exception e)
        {
            System.out.println("Exception in viewCities");
            System.out.println(e);
        }
    }
    void viewAreas(String c){
        try
        {
            String query = "select distinct(area) from [dbo].[location] where city=?";
             PreparedStatement preparedStmt = con.prepareStatement(query);
             preparedStmt.setString(1, c);
             ResultSet rs = preparedStmt.executeQuery();
             
             while(rs.next())
             {
                System.out.println(rs.getString(1));
             }
        }
        catch(Exception e)
        {
            System.out.println("Exception in viewAreas");
            System.out.println(e);
        }
    }
    int checkUsername(String u){
        try
        {
            String query = "select * from [dbo].[person] where username=?";
             PreparedStatement preparedStmt = con.prepareStatement(query);
             preparedStmt.setString(1, u);
             ResultSet rs = preparedStmt.executeQuery();
             
             if(rs.next()){
                 return -1;
             }
             
        }
        catch(Exception e)
        {
            System.out.println("Exception in checkUsername");
            System.out.println(e);
        }
        return 1;
    }
    
    void insertUser(String name, String email, String username, String password, String contactNumber){
        try
        {
            String query = "insert into Person values(?,?,?,?)";
             PreparedStatement preparedStmt = con.prepareStatement(query);
             preparedStmt.setString   (1, name);
             preparedStmt.setString(2, email);
             preparedStmt.setString(3, username);
             preparedStmt.setString(4, password);

             preparedStmt.executeUpdate();
             
             query = "insert into [dbo].[user] select personID, ? from Person where username = ?";
             preparedStmt = con.prepareStatement(query);
             preparedStmt.setString   (1, contactNumber);
             preparedStmt.setString   (2, username);

             preparedStmt.executeUpdate();
             
         }
        catch(Exception e)
        {
            System.out.println("Exception in insertUser");
            System.out.println(e);
        }
    }
    
    void addLocation(String username, String address, String area, String city){
        try
        {
             String query = "insert into [dbo].[location] values(?,?,?)";
             PreparedStatement preparedStmt = con.prepareStatement(query);
             preparedStmt.setString(1, address);
             preparedStmt.setString(2, area);
             preparedStmt.setString(3, city);
             
             preparedStmt.executeUpdate();
             
             query = "select locationID from [dbo].[location] where [address]=? and [area] =? and [city] =?";
             preparedStmt = con.prepareStatement(query);
             preparedStmt.setString(1, address);
             preparedStmt.setString(2, area);
             preparedStmt.setString(3, city);
             
             ResultSet rs = preparedStmt.executeQuery();
             int locationID;
             
             rs.next();
             locationID = rs.getInt(1);
             
             query = "select personID from Person where username=?";
             preparedStmt = con.prepareStatement(query);
             preparedStmt.setString(1, username);
             rs = preparedStmt.executeQuery();
             int userID;
             
             rs.next();
             userID = rs.getInt(1);
             
             
             query = "insert into [dbo].[userLocations]  values(?,?)";
             preparedStmt = con.prepareStatement(query);
             preparedStmt.setInt(1, userID);
             preparedStmt.setInt(2, locationID);
  
             preparedStmt.executeUpdate();
             
         }
        catch(Exception e)
        {
            System.out.println("Exception in addLocation");
            System.out.println(e);
        }
    }
    
    User loginUser(String username, String password){
        
        try{
            String query = "select top(1)* from [dbo].[person] "
                    + "join [dbo].[user] on personID = userID join" +
                    " [dbo].[userLocations] on personID = [dbo].[userLocations].userID"
                    + " join [dbo].[location] on [dbo].[userLocations].locationID "
                    + "= [dbo].[location].locationID where [username]=? and [password]=?";
             PreparedStatement preparedStmt = con.prepareStatement(query);
             preparedStmt.setString(1, username);
             preparedStmt.setString(2, password);
             ResultSet rs = preparedStmt.executeQuery();
             
             if(rs.next()){
                 String name = rs.getString(2);
                 String email = rs.getString(3);
                 String cn = rs.getString(7);
                 String add = rs.getString(10);
                 String area = rs.getString(11);
                 String city = rs.getString(12);
                 Location loc = new Location(add, area, city);
                 User u = new User(name, email, username, password, cn, loc);
                 return u;

             }
        }
        catch(Exception e){
            System.out.println("Exception in loginUser");
            System.out.println(e);
        }
        
        return null;
    }
    
    FoodpandaAdministrator loginAdmin(String username, String password){
        
        try{
            String query = "select * from [dbo].[person] join "
                    +" [dbo].[foodpandaAdministrator] on [personID] = "
                    +" [administratorID] where [username]=? and [password]=?";
             PreparedStatement preparedStmt = con.prepareStatement(query);
             preparedStmt.setString(1, username);
             preparedStmt.setString(2, password);
             ResultSet rs = preparedStmt.executeQuery();
             
             if(rs.next()){
                 String name = rs.getString(2);
                 String email = rs.getString(3);
                 
                 FoodpandaAdministrator f = new FoodpandaAdministrator(name, email, username, password);
                 return f;
             }
        }
        catch(Exception e){
            System.out.println("Exception in loginAdmin");
            System.out.println(e);
        }
        
        return null;
    }
    
    RestaurantManager loginManager(String username, String password){
        
        try{
            String query = "select * from [dbo].[person] join "
                    +" [dbo].[restaurantManager] on [personID] = "
                    +" [managerID] where [username]=? and [password]=?";
             PreparedStatement preparedStmt = con.prepareStatement(query);
             preparedStmt.setString(1, username);
             preparedStmt.setString(2, password);
             ResultSet rs = preparedStmt.executeQuery();
             
             if(rs.next()){
                 String name = rs.getString(2);
                 String email = rs.getString(3);
                 int i = rs.getInt(1);
                 RestaurantManager r = new RestaurantManager(i, name, email, username, password);
                 return r;
             }
        }
        catch(Exception e){
            System.out.println("Exception in loginManager");
            System.out.println(e);
        }
        
        return null;
    }
    
    void fetchAllAreas(String username){
        try{
            String query = "select [address], [area], [city] from [dbo].[person] "
                    + "join [dbo].[user] on personID = userID join" +
                    " [dbo].[userLocations] on personID = [dbo].[userLocations].userID"
                    + " join [dbo].[location] on [dbo].[userLocations].locationID "
                    + "= [dbo].[location].locationID where [username]=?";
             PreparedStatement preparedStmt = con.prepareStatement(query);
             preparedStmt.setString(1, username);
             
             ResultSet rs = preparedStmt.executeQuery();
             
             while(rs.next()){
                 String address = rs.getString(1);
                 String area = rs.getString(2);
                 String city = rs.getString(3);
                 
                 Location loc = new Location(address, area, city);
                 FoodPanda.getInstance().currentUser.AddLocation(loc);
             }
        }
        catch(Exception e){
            System.out.println("Exception in fetchAllAreas");
            System.out.println(e);
        }
    }
    
    List<PromoCode> fetchPromoCodes(){
        List<PromoCode> pc = new ArrayList<>();
        try{
            String query = "select * from [dbo].[promoCode]";
             PreparedStatement preparedStmt = con.prepareStatement(query);
             
             ResultSet rs = preparedStmt.executeQuery();
             
             while(rs.next()){
                 int id = rs.getInt(1);
                 int percentage = rs.getInt(2);
                 String code = rs.getString(3);
                  PromoCode p = new PromoCode(code, percentage);
                  pc.add(p);

             }
        }
        catch(Exception e){
            System.out.println("Exception in fetchPromoCode");
            System.out.println(e);
        }
        
        return pc;
    }
    
    List<Location> getRestaurantLocations(int id){
        List<Location> locations = new ArrayList<>();
        try{
            String query = " select [address], [area], [city] from restaurantLocations join location on "
             +" restaurantLocations.locationID = location.locationID " +
"            where restaurantLocations.restaurantID = ?";
             PreparedStatement preparedStmt = con.prepareStatement(query);
              preparedStmt.setInt(1, id);
             
             ResultSet rs2 = preparedStmt.executeQuery();
                   
                    
             while(rs2.next()){
                 Location l = new Location(rs2.getString(1),rs2.getString(2), rs2.getString(3));
                 locations.add(l);
             }
 
          }
          catch (Exception ex){
            System.out.println("Exception in getting locations");
            System.out.println(ex);
          }
        
        return locations;
    }
    
    List<String> getRestaurantTags(int id){
         List<String> tags = new ArrayList<>();
        try{
                     String query = "select distinct tag.name from restaurant join restaurantTags on restaurant.restaurantID = restaurantTags.restaurantID " +
                        " join tag on tag.tagID = restaurantTags.tagID where restaurant.restaurantID = ?";
                    PreparedStatement preparedStmt = con.prepareStatement(query);
                    preparedStmt.setInt(1, id);
             
                    ResultSet rs2 = preparedStmt.executeQuery();
                   
                    
                    while(rs2.next()){
                         tags.add(rs2.getString(1));
                    }
 
                 }
                 catch (Exception ex){
                     System.out.println("Exception in getting tags");
                     System.out.println(ex);
                 }
        
        return tags;
    }
    
    MenuItem getMenuItem(String name)
    {
         try{
            
                    String query = "Select * from menuItem where name= ?";
                    PreparedStatement preparedStmt = con.prepareStatement(query);
                    preparedStmt.setString(1,name);
             
                    ResultSet rs2 = preparedStmt.executeQuery();
                    if(rs2.next()){
                        MenuItem item = new MenuItem(rs2.getString(2),rs2.getString(3),rs2.getInt(4),rs2.getString(5));
                        return item;
                    }
                 }
                 catch (Exception ex){
                     System.out.println("Menu Item does not exists");
                     System.out.println("Exception in getMenuItem");
                     System.out.println(ex);
                 }
        return null;
    }
    PromoCode getPromoCode(String name)
    {
         try{
            
                    String query = "Select * from promoCode where code = ?";
                    PreparedStatement preparedStmt = con.prepareStatement(query);
                    preparedStmt.setString(1,name);
             
                    ResultSet rs2 = preparedStmt.executeQuery();
                    if(rs2.next()){
                        PromoCode item = new PromoCode(rs2.getString(3),rs2.getInt(2));
                        return item;
                    }
                 }
                 catch (Exception ex){
                     System.out.println("PromoCode does not exists");
                     System.out.println("Exception in getPromoCode");
                     System.out.println(ex);
                 }
        return null;
    }
    
    List<MenuItem> RequestMenu(Restaurant required)
    {
         List<MenuItem> items = new ArrayList<>();
         List<String> Types = new ArrayList<>();
         
        try{
           
                    String query = "Select menuID from restaurant where name= ?";
                    PreparedStatement preparedStmt = con.prepareStatement(query);
                    preparedStmt.setString(1,required.name);
             
                    ResultSet rs2 = preparedStmt.executeQuery();
                    rs2.next();
                   
                    query = "Select distinct(C.type)\n" +
"from menuAndItems as A join menu as B on A.menuID =B.menuID join menuItem as C on A.itemID=C.itemID\n" +
"where A.menuID = ?";
                    PreparedStatement prepared = con.prepareStatement(query);
                    prepared.setInt(1,rs2.getInt(1));

                    ResultSet rs3 = prepared.executeQuery();
                   
                    while(rs3.next()){
                         Types.add(rs3.getString(1));
                         
                         query = "Select C.name,C.description,C.price,C.type\n" +
"from menuAndItems as A join menu as B on A.menuID =B.menuID join menuItem as C on A.itemID=C.itemID\n" +
"where A.menuID = ? AND C.type Like ?\n" +
"GROUP BY C.name,C.description,C.price,C.type";
                         
                         PreparedStatement prepare = con.prepareStatement(query);
                         prepare.setInt(1,rs2.getInt(1));
                         prepare.setString(2, rs3.getString(1));
                         
                         ResultSet rs4 = prepare.executeQuery();
                         
                         System.out.println("\n\n"+"Section: " + rs3.getString(1)+"\n\n");
                         while(rs4.next())
                         {
                             System.out.println(rs4.getString(1)+ "\t" + rs4.getString(2) + "\t" +rs4.getInt(3)+ "\t" +rs4.getString(4));
                             MenuItem n = new MenuItem(rs4.getString(1),rs4.getString(2),rs4.getInt(3),rs4.getString(4));
                             items.add(n);
                         }
                         
                    }
 
                   
                 }
                 catch (Exception ex){
                     System.out.println("Exception in getting menu");
                     System.out.println(ex);
                 }
       
        return items;
    }
    
    List<Restaurant> fetchAllRestaurants(){
        List<Restaurant> r = new ArrayList<>();
        try{
            String query = "select * from [dbo].[Restaurant]";
             PreparedStatement preparedStmt = con.prepareStatement(query);
             
             ResultSet rs = preparedStmt.executeQuery();
             
             while(rs.next()){
                 int id = rs.getInt(1);
                 String name = rs.getString(2);
                 String num = rs.getString(3);
                  Restaurant rest = new Restaurant(id, name, num);
                  rest.tags = getRestaurantTags(id);
                  rest.locations = getRestaurantLocations(id);
                  r.add(rest);

             }
        }
        catch(Exception e){
            System.out.println("Exception in fetchAllRestaurants");
            System.out.println(e);
        }
        
        return r;
    }
    
    void displayTags(){
        try
        {
            ResultSet rs=stmt.executeQuery("select * from [dbo].[tag]");
             while(rs.next())
             {
                System.out.println(rs.getString(2));
             }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    int getId(Person u){
        int id=0;
        try{
            String query = "select * from [dbo].[person] where username = ?";
             PreparedStatement preparedStmt = con.prepareStatement(query);
             preparedStmt.setString(1, u.username);
             
             ResultSet rs = preparedStmt.executeQuery();
             
             if(rs.next()){
                 id = rs.getInt(1);
             }
        }
        catch(Exception e){
            System.out.println("Exception in getUserId");
            System.out.println(e);
        }
        return id;
    }
    
    void StoreReview(Review r){
        try
        {
            String query = "insert into Review values(?,?,?,?)";
             PreparedStatement preparedStmt = con.prepareStatement(query);
             preparedStmt.setInt  (1, r.rate);
             preparedStmt.setString(2, r.comments);
             preparedStmt.setInt(3, getId(r.user));
             preparedStmt.setInt(4, r.rest.id);

             preparedStmt.executeUpdate();
   
         }
        catch(Exception e)
        {
            System.out.println("Exception in StoreReview");
            System.out.println(e);
        }
    }
    
    void DisplayReviews(int id){
     
        try
        {
            String query = "select username, rating, comment from "
                    + "review join [dbo].person on personID = userID " +
                        " where restaurantID = ?";
             PreparedStatement preparedStmt = con.prepareStatement(query);
             preparedStmt.setInt(1, id);
             ResultSet rs = preparedStmt.executeQuery();
             while(rs.next())
             {
                System.out.println("Username: " + rs.getString(1) + "\nRating: " + rs.getInt(2) + "\nComments: " + rs.getString(3) +"\n");
             }
        }
        catch(Exception e)
        {
            System.out.println("Exception in DisplayReviews");
            System.out.println(e);
        }
    }
    
    int InsertRestaurant(Restaurant r, int id)
    {
        try
        {
            String query = "insert into Restaurant values(?,?,?, NULL)";
             PreparedStatement preparedStmt = con.prepareStatement(query);
             preparedStmt.setString(1, r.name);
             preparedStmt.setString(2, r.contactNumber);
             preparedStmt.setInt(3, id);
             preparedStmt.executeUpdate();
   
         }
        catch(Exception e)
        {
            System.out.println("Exception in InsertRestaurant");
            System.out.println(e);
        }
        int restId = 0;
        try{
            String query = "select * from Restaurant where name = ? and contactNumber = ?";
             PreparedStatement preparedStmt = con.prepareStatement(query);
             preparedStmt.setString(1, r.name);
             preparedStmt.setString(2, r.contactNumber);
             ResultSet rs = preparedStmt.executeQuery();
             if(rs.next()){
                 restId = rs.getInt(1);
             }
        }
        catch(Exception e){
            System.out.println("Exception in InsertRestaurant");
            System.out.println(e);
        }
        return restId;
    }
    void insertPromoCode(int discount,String code){
        try
        {
            String query = "insert into promoCode values(?,?)";
             PreparedStatement preparedStmt = con.prepareStatement(query);
             preparedStmt.setInt   (1, discount);
             preparedStmt.setString(2, code);
            

             preparedStmt.executeUpdate();
           
         }
        catch(Exception e)
        {
            System.out.println("Exception in insertPromoCode");
            System.out.println(e);
        }
    }
    
    
    void DeleteRestaurantLocations(Restaurant r)
    {
        try
        {
            String query = "delete from restaurantLocations where restaurantID = ?";
             PreparedStatement preparedStmt = con.prepareStatement(query);
             preparedStmt.setInt(1, r.id);

             preparedStmt.executeUpdate();
   
         }
        catch(Exception e)
        {
            System.out.println("Exception in DeleteRestaurantLocations");
            System.out.println(e);
        }
    }
    void DeleteRestaurantTags(Restaurant r)
    {
        try
        {
            String query = "delete from restaurantTags where restaurantID = ?";
             PreparedStatement preparedStmt = con.prepareStatement(query);
             preparedStmt.setInt(1, r.id);

             preparedStmt.executeUpdate();
   
         }
        catch(Exception e)
        {
            System.out.println("Exception in DeleteRestaurantTags");
            System.out.println(e);
        }
    }
    
    void DeleteRestaurant(Restaurant r)
    {
        try
        {
            DeleteRestaurantLocations(r);
            DeleteRestaurantTags(r);
            String query = "delete from Restaurant where restaurantID = ?";
             PreparedStatement preparedStmt = con.prepareStatement(query);
             preparedStmt.setInt(1, r.id);

             preparedStmt.executeUpdate();
   
         }
        catch(Exception e)
        {
            System.out.println("Exception in DeleteRestaurant");
            System.out.println(e);
        }
    }
    
    void viewAvailableRestaurantManagers()
    {
        try
        {
            ResultSet rs=stmt.executeQuery("select personID, name from Person join " +
            "(select * from restaurantManager " +
            " except " +
            " select restaurantManager.managerID from restaurant join restaurantManager " +
            " on restaurant.managerID = restaurantManager.managerID) as T1" +
            " on Person.personID = T1.managerID");
             while(rs.next())
             {
                System.out.println(rs.getInt(1)+"  "+rs.getString(2));
             }
        }
        catch(Exception e)
        {
            System.out.println("Error in ViewAvailableRestaurantManagers");
            System.out.println(e);
        }
    }
    
    RestaurantManager getManager(int id)
    {
        RestaurantManager manager;
        try
        {
            String query = "select * from restaurantManager join person on personID = managerID " +
                "where managerID = ?";
             PreparedStatement preparedStmt = con.prepareStatement(query);
             preparedStmt.setInt(1, id);
             
             ResultSet rs = preparedStmt.executeQuery();
             if(rs.next())
             {
                 String name = rs.getString(3);
                 String email = rs.getString(4);
                 String username = rs.getString(5);
                 String password = rs.getString(6);
                 int i = rs.getInt(1);
                 manager = new RestaurantManager(i, name, email, username, password);
                 return manager;
             }
             
         }
        catch(Exception e)
        {
            System.out.println("Exception in getManager");
            System.out.println(e);
        }
       return null;
    }
    
    
    void AddTagToRestaurant(int id, String tag)
    {
        try
        {
            String query = "insert into restaurantTags " +
                           " select ?, tag.tagID from tag where tag.name = ?";
             PreparedStatement preparedStmt = con.prepareStatement(query);
             preparedStmt.setInt(1, id);
             preparedStmt.setString(2, tag);

             preparedStmt.executeUpdate();
   
         }
        catch(Exception e)
        {
            System.out.println("Exception in AddTagToRestaurant");
            System.out.println(e);
        }
    }
    
    int GetLocationId(String area, String city){
        int id =0;
        try{
            String query = "select top(1)locationID from location where area = ? and city = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
             preparedStmt.setString(1, area);
             preparedStmt.setString(2, city);

            ResultSet rs= preparedStmt.executeQuery();
            if(rs.next()){
                id = rs.getInt(1);
            }
        }
        catch(Exception e){
            System.out.println("Exception in GetLocationId");
            System.out.println(e);
        }
        return id;
    }
    
    void AddLocationToRestaurant(int rid, int lid)
    {
     try
        {
            String query = "insert into restaurantLocations values(?,?) ";
                           
             PreparedStatement preparedStmt = con.prepareStatement(query);
             preparedStmt.setInt(1, rid);
             preparedStmt.setInt(2, lid);

             preparedStmt.executeUpdate();
   
         }
        catch(Exception e)
        {
            System.out.println("Exception in AddLocationToRestaurant");
            System.out.println(e);
        }   
    }
    
    
    List<MenuItem> getItemsOfMenu(int id){
        try
        {
            String query = "select * from [dbo].[menuAndItems] join [dbo].[menuItem] on " +
                    " [dbo].[menuAndItems].itemID = [dbo].[menuItem].itemID " +
                    " where [dbo].[menuAndItems].menuID = ?";

            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, id);

            ResultSet rs = preparedStmt.executeQuery();
            List<MenuItem> items = new ArrayList<>();
            while(rs.next()){
                String name = rs.getString(4);
                String desc = rs.getString(5);
                int price = rs.getInt(6);
                String type = rs.getString(7);
                MenuItem item = new MenuItem(name, desc, price, type);
                items.add(item);
            }
            return items;
        }
        catch(Exception e)
        {
            System.out.println("Exception in getItemsOfMenu");
            System.out.println(e);
        }  
        return null;
    }
    
    Restaurant getRestaurantOfManager(int id)
    {
        try
        {
            String query = "select * from restaurant where managerID = ? ";
                           
             PreparedStatement preparedStmt = con.prepareStatement(query);
             preparedStmt.setInt(1, id);

             ResultSet rs = preparedStmt.executeQuery();
             if(rs.next()){
                 int i = rs.getInt(1);
                 String name = rs.getString(2);
                 String cn = rs.getString(3);
                 List<MenuItem> items = getItemsOfMenu(id); 
                 Menu menu = new Menu(items);
                 Restaurant rest = new Restaurant(i, name, cn, menu);
                 return rest;
             }
         }
        catch(Exception e)
        {
            System.out.println("Exception in getRestaurantOfManager");
            System.out.println(e);
        }  
        return null;
    }
    
    int AddItem(MenuItem i)
    {
        try
        {
            String query = "insert into menuItem values(?,?,?,?) ";   
             PreparedStatement preparedStmt = con.prepareStatement(query);
             preparedStmt.setString(1, i.name);
             preparedStmt.setString(2, i.description);
             preparedStmt.setInt(3, i.price);
             preparedStmt.setString(4, i.type);

             preparedStmt.executeUpdate();
             int id =0;
             try{
                query = "select * from menuItem where name =? and description=? and price=? and type=?";
                preparedStmt = con.prepareStatement(query);
                preparedStmt.setString(1, i.name);
                preparedStmt.setString(2, i.description);
                preparedStmt.setInt(3, i.price);
                preparedStmt.setString(4, i.type);

                ResultSet rs= preparedStmt.executeQuery();
                if(rs.next()){
                    id = rs.getInt(1);
                }
                return id;
            }
            catch(Exception e){
                System.out.println("Exception in AddItem");
                System.out.println(e);
            }
             
             
         }
        catch(Exception e)
        {
            System.out.println("Exception in AddItem");
            System.out.println(e);
        }  
        return 0;
    }
    
    int getMenuId(String restName)
    {
        int id =0;
        try{
            String query = "select * from Restaurant where name =?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
             preparedStmt.setString(1, restName);

            ResultSet rs= preparedStmt.executeQuery();
            if(rs.next()){
                id = rs.getInt(5);
            }
        }
        catch(Exception e){
            System.out.println("Exception in getMenuId");
            System.out.println(e);
        }
        return id;
    }
    
    void AddItemToMenu(String restName, MenuItem i)
    {
        int itemId = AddItem(i);
        int menuId = getMenuId(restName);
        
        try
        {
            String query = "insert into [dbo].[menuAndItems] values(?,?) ";
                           
             PreparedStatement preparedStmt = con.prepareStatement(query);
             preparedStmt.setInt(1, menuId);
             preparedStmt.setInt(2, itemId);

             preparedStmt.executeUpdate();
   
         }
        catch(Exception e)
        {
            System.out.println("Exception in AddItemToMenu");
            System.out.println(e);
        }  
        
    }
    
    void CreateMenu(String restName)
    {
        try
        {
             String query = "INSERT INTO menu DEFAULT VALUES  ";
                           
             PreparedStatement preparedStmt = con.prepareStatement(query);
             preparedStmt.executeUpdate(); 
             
             try{
                query = "select top(1)* from menu ORDER BY menuID DESC";
                preparedStmt = con.prepareStatement(query);
               
                int menuId = 0;
                ResultSet rs= preparedStmt.executeQuery();
                if(rs.next()){
                    menuId = rs.getInt(1);
                    AddMenuToRestaurant(restName, menuId);
                }
            }
            catch(Exception e){
                System.out.println("Exception in CreateMenu");
                System.out.println(e);
            }
             
         }
        catch(Exception e)
        {
            System.out.println("Exception in CreateMenu");
            System.out.println(e);
        }
        
    }
    
    void AddMenuToRestaurant(String restName, int menuID)
    {
        try
        {
            String query = "update restaurant set menuID = ? where name = ? ";
                           
             PreparedStatement preparedStmt = con.prepareStatement(query);
             preparedStmt.setInt(1, menuID);
             preparedStmt.setString(2, restName);

             preparedStmt.executeUpdate();
   
         }
        catch(Exception e)
        {
            System.out.println("Exception in AddMenuToRestaurant");
            System.out.println(e);
        }  
    
    }
    
    
    /*List<MenuItem> RequestMenu(Restaurant required)
    {
         List<MenuItem> items = new ArrayList<>();
         List<String> Types = new ArrayList<>();
         
        try{
           
                    String query = "Select menuID from restaurant where name= ?";
                    PreparedStatement preparedStmt = con.prepareStatement(query);
                    preparedStmt.setString(1,required.name);
             
                    ResultSet rs2 = preparedStmt.executeQuery();
                    rs2.next();
                    query = "Select distinct(C.type)\n" +
                    "from menuAndItems as A join menu as B on A.menuID =B.menuID join menuItem as C on A.itemID=C.itemID\n" +
                    "where A.menuID = ?";
                    PreparedStatement prepared = con.prepareStatement(query);
                    prepared.setInt(1,rs2.getInt(1));

                    ResultSet rs3 = prepared.executeQuery();
                   
                    while(rs3.next()){
                         Types.add(rs3.getString(1));
                         
                         query = "Select C.name,C.description,C.price,C.type\n" +
                        "from menuAndItems as A join menu as B on A.menuID =B.menuID join menuItem as C on A.itemID=C.itemID\n" +
                        "where A.menuID = ? AND C.type Like ?\n" +
                        "GROUP BY C.name,C.description,C.price,C.type";
                         
                         PreparedStatement prepare = con.prepareStatement(query);
                         prepare.setInt(1,rs2.getInt(1));
                         prepare.setString(2, rs3.getString(1));
                         
                         ResultSet rs4 = prepare.executeQuery();
                         
                         System.out.println("Section: " + rs3.getString(1));
                         while(rs4.next())
                         {
                             System.out.println(rs4.getString(1)+ " " + rs4.getString(2) + "    " +rs4.getInt(3));
                             MenuItem n = new MenuItem(rs4.getString(1),rs4.getString(2),rs4.getInt(3),rs4.getString(4));
                             items.add(n);
                         }
                         
                    }
 
                   
                 }
                 catch (Exception ex){
                     System.out.println("Exception in getting tags");
                     System.out.println(ex);
                 }
       
        return items;
    }*/
    
    void viewSections(Restaurant r)
    {
        try
        {
            String query = "select distinct([type]) from restaurant join menuAndItems on restaurant.menuID = menuAndItems.menuID join menuItem on menuItem.itemID\n" +
                            " = menuAndItems.itemID where restaurant.name = ?";
                           
             PreparedStatement preparedStmt = con.prepareStatement(query);
             preparedStmt.setString(1, r.name);

             ResultSet rs = preparedStmt.executeQuery();
             while(rs.next()){
                 System.out.println(rs.getString(1));
             }
   
         }
        catch(Exception e)
        {
            System.out.println("Exception in viewSections");
            System.out.println(e);
        }  
        
    }
    
    int getItemId(String restName, String itemName)
    {
        int id = 0;
        try
        {
            String query = "select menuItem.itemID from menuItem join menuAndItems on menuItem.itemID = menuAndItems.itemID join restaurant\n" +
"on restaurant.menuID = menuAndItems.menuID\n" +
"where restaurant.name = ? and menuItem.name = ?";
                           
             PreparedStatement preparedStmt = con.prepareStatement(query);
             preparedStmt.setString(1, restName);
             preparedStmt.setString(2, itemName);

             ResultSet rs = preparedStmt.executeQuery();
             if(rs.next()){
                 id = rs.getInt(1);
             }
             return id;
         }
        catch(Exception e)
        {
            System.out.println("Exception in getItemId");
            System.out.println(e);
        }  
        return id;
    }
    
    void EditItemName(String restName, String oldName, String newName)
    {
        int itemID = getItemId(restName, oldName);
        try
        {
            String query = "update menuItem set name = ? where itemID = ?";
                           
             PreparedStatement preparedStmt = con.prepareStatement(query);
             preparedStmt.setString(1, newName);
             preparedStmt.setInt(2, itemID);

             preparedStmt.executeUpdate();
             
         }
        catch(Exception e)
        {
            System.out.println("Exception in EditItemName");
            System.out.println(e);
        } 
        
    }
    
    void EditItemDescription(String restName, String itemName, String desc)
    {
        int itemID = getItemId(restName, itemName);
        try
        {
            String query = "update menuItem set description = ? where itemID = ?";
                           
             PreparedStatement preparedStmt = con.prepareStatement(query);
             preparedStmt.setString(1, desc);
             preparedStmt.setInt(2, itemID);

             preparedStmt.executeUpdate();
             
         }
        catch(Exception e)
        {
            System.out.println("Exception in EditItemDescription");
            System.out.println(e);
        } 
    }
    
    void EditItemPrice(String restName, String itemName, int price){
        int itemID = getItemId(restName, itemName);
        try
        {
            String query = "update menuItem set price = ? where itemID = ?";
                           
             PreparedStatement preparedStmt = con.prepareStatement(query);
             preparedStmt.setInt(1, price);
             preparedStmt.setInt(2, itemID);

             preparedStmt.executeUpdate();
             
         }
        catch(Exception e)
        {
            System.out.println("Exception in EditItemPrice");
            System.out.println(e);
        } 
    }
    
    void deleteItem(String restName, String itemName)
    {
        int itemID = getItemId(restName, itemName);
        try
        {
            String query = "delete from menuAndItems where itemID = ?\n" +
                "delete from menuItem where itemID = ?";
                           
             PreparedStatement preparedStmt = con.prepareStatement(query);
             preparedStmt.setInt(1, itemID);
             preparedStmt.setInt(2, itemID);

             preparedStmt.executeUpdate();
             
         }
        catch(Exception e)
        {
            System.out.println("Exception in deleteItem");
            System.out.println(e);
        } 
    }
        
    List<Order> getAllOrders()
    {
        List<Order> orders = new ArrayList<>();
        try
        {
            String query = "select * from [order] join [OrderRestaurant] on [order].orderID = [OrderRestaurant].orderID \n" +
"join restaurant on restaurant.restaurantID = OrderRestaurant.restaurantID";
                           
             PreparedStatement preparedStmt = con.prepareStatement(query);

             ResultSet rs = preparedStmt.executeQuery();
             
             while(rs.next())
             {
                 int id = rs.getInt(1);
                 int p = rs.getInt(2);
                 String s = rs.getString(3);
                 Order_Status status =  new Pending();
                 if(s.equalsIgnoreCase("Forwarding"))
                     status = new Forwarding();
                 else if(s.equalsIgnoreCase("Pending"))
                     status = new Pending();
                 else if(s.equalsIgnoreCase("Cancelled"))
                     status = new Cancelled();
                 else if(s.equalsIgnoreCase("Confirmed"))
                     status = new Confirmed();
                 Restaurant rest = new Restaurant(rs.getInt(7), rs.getString(9), rs.getString(10));
                 Order obj = new Order(id, p, s, status, rest);
                 orders.add(obj);
             }
             
         }
        catch(Exception e)
        {
            System.out.println("Exception in getAllOrders");
            System.out.println(e);
        } 

        return orders;
    }
    
    void changeOrderStatus(Order ord, String status){
        try
        {
            String query;
            query = "update [order] set [status] = ? where orderID = ?";
                           
             PreparedStatement preparedStmt = con.prepareStatement(query);
             preparedStmt.setString(1, status);
             preparedStmt.setInt(2, ord.orderID);

             preparedStmt.executeUpdate();
             
         }
        catch(Exception e)
        {
            System.out.println("Exception in changeOrderStatus");
            System.out.println(e);
        } 
    }
    void AddOrder(Order details)
    {
         try
        {
            String query ="select userID from dbo.[user] where contactNumber = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, details.id.contactNumber);
           
            ResultSet rs2 = preparedStmt.executeQuery();
            rs2.next();
             
           
            query = "insert into [order]( [totalPrice],[status],[userID],[desc] ) values(?,?,?,?)";
             
           PreparedStatement preparedSt = con.prepareStatement(query);
           preparedSt.setInt(1, details.totalPrice);
           preparedSt.setString(2, details.status);
           preparedSt.setInt(3, rs2.getInt(1));
           preparedSt.setString(4, details.Description);
           
           
           preparedSt.executeUpdate();
         
           
           query = "select restaurantID from restaurant where [restaurantID]=?";
           PreparedStatement preparedS = con.prepareStatement(query);
           preparedS.setInt(1, details.restaurant.id);
         
           ResultSet rs4 = preparedS.executeQuery();
           int fk=0;
           while(rs4.next())
           {
               
               fk=rs4.getInt(1);
           }
           
           
           query = "select orderID from [order] where [totalPrice]=? and [status]=? and [userID]= ?";
           PreparedStatement preparedT = con.prepareStatement(query);
           preparedT.setInt(1, details.totalPrice);
           preparedT.setString(2, details.status);
           preparedT.setInt(3, rs2.getInt(1));
         
         
           ResultSet rs5 = preparedT.executeQuery();
           int oid = 0;
           
           if(rs5.next())
           {
               oid = rs5.getInt(1);
           }
          
           if(oid != 0){
            query = "insert into dbo.[OrderRestaurant] values(?,?)";
            PreparedStatement prepared = con.prepareStatement(query);
            prepared.setInt(1, oid);
            prepared.setInt(2, fk);

             prepared.executeUpdate();


            System.out.println("Your order is successfully placed");
           }
           else{
               System.out.println("Your order could not be placed.Please try again later");
           }
           /*for(MenuItem u: details.items){
               
            query="select itemID from menu join menuAndItems on menu.menuID= menuAndItems.menuID where menuID=?";
            PreparedStatement prepare = con.prepareStatement(query);
            prepare.setString(1, details.restaurant.menu.);
           
           
           query = "INSERT INTO  dbo.[OrderItems]([orderID],[itemID]) values (?,?)";
           PreparedStatement prepare = con.prepareStatement(query);
           prepare.setInt(1, details.orderID);
           prepare.setInt(2, rs4.getInt(1));
           
           prepare.executeQuery();
           }*/
           
         }
        catch(Exception e)
        {
            System.out.println("Your order couldn't be placed");
            System.out.println("Exception in AddOrder");
            System.out.println(e);
        }
    }
}
