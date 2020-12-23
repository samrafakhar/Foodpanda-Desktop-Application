use fp

drop database fp

insert into [dbo].[location]
values
('', 'Gulberg', 'Lahore'),
('', 'Johar Town', 'Lahore'),
('', 'Wapda Town', 'Lahore'),
('', 'DHA', 'Lahore'),
('', 'Bahria Town', 'Lahore'),
('', 'Allama Iqbal Town', 'Lahore'),
('', 'Lake City', 'Lahore'),
('', 'Cantt', 'Lahore'),
('', 'Valencia', 'Lahore'),
('', 'Bahria Town', 'Islamabad'),
('', 'Blue Area', 'Islamabad'),
('', 'DHA', 'Islamabad'),
('', 'F7', 'Islamabad'),
('', 'F8', 'Islamabad'),
('', 'F9', 'Islamabad'),
('', 'DHA', 'Karachi'),
('', 'Bahria Town', 'Karachi'),
('', 'Korangi Town', 'Karachi'),
('', 'Kemari Town', 'Karachi'),
('', 'Clifton', 'Karachi'),
('', 'Iqbal Town', 'Faisalabad'),
('', 'Jinnah Town', 'Faisalabad'),
('', 'Madina Town', 'Faisalabad'),
('', 'Lyallpur Town', 'Faisalabad')

select * from restaurantManager

insert into Restaurant
values 
('Mcdonalds', '111244622', 2, NULL),
('KFC', '111121121', 3, NULL),
('California Pizza', '03331209297', 4, NULL),
('Arcadian Cafe', '0421234567', 5, NULL),
('Johnny and Jugnu', '03001234563', 6, NULL),
('Burger Lab', '1111234455', 10, NULL),
('Burger Hub', '0423456729', 11, NULL),
('OPTP', '03215678432', 12, NULL),
('Subway', '03234567482', 13, NULL),
('Dunkin Donuts', '0421238765', 14, NULL),
('Jalal Sons', '03435674839', 15, NULL)

select * from restaurantTags join tag on restaurantTags.tagID = tag.tagID join restaurant on restaurant.restaurantID 
= restaurantTags.restaurantID

insert into restaurantTags 
select 10, tag.tagID from tag where tag.name = 'Dessert'

select top(1)locationID from location where area = 'Johar Town' and city = 'Lahore'

select * from restaurantLocations

insert into Restaurant
values
('Bundu Khan', '03002345432', 16, NULL),
('What a Paratha', '0423657489', 17, NULL),
('Salt n pepper', '03214321876', 18, NULL),
('Qabail', '03002398690', NULL, NULL)

delete from location where locationID = 27

select * from location
select * from restaurant

insert into restaurantLocations values
(1,1), (1,2), (1,4), (1,5), (1,6), (1,7), (1,8), (1,9), (1,10), (1,11), (1,12), (1,13), (1,14),
(1,15), (1,16), (1,17), (1,18), (1,19), (1,20), (1,21), (1,22), (1,23), (1,24),
(2,1), (2,2), (2,4), (2,5), (2,6), (2,7), (2,8), (2,9), (2,10), (2,11), (2,12), (2,13), (2,14),
(2,15), (2,16), (2,17), (2,18), (2,19), (2,20), (2,21), (2,22), (2,23), (2,24),
(3, 1), (3,2), (3,3), (3,4), (3,11), (3,16), (3,20),
(4,1), (4,2), (4,3), (4,4),
(5,2), (5,4), (5,3),
(6,2), (6,4),
(7,1),
(8,1), (8,2), (8,4), (8,5), (8,6), (8,7), (8,8), (8,9), (8,10), (8,11), (8,12), (8,13), (8,14),
(8,15), (8,16), (8,17), (8,18), (8,19), (8,20), (8,21), (8,22), (8,23), (8,24),
(9,1), (9,2), (9,4), (9,5), (9,6), (9,7), (9,8), (9,9), (9,10), (9,11), (9,12), (9,13), (9,14),
(9,15), (9,16), (9,17), (9,18), (9,19), (9,20), (9,21), (9,22), (9,23), (9,24)




insert into tag
values 
('Fast Food'),
('Pakistani'),('Beverage'),('Healthy'),('American')


insert into restaurantTags
values 
(1,1),
(2,1), (3,1), (5,1), (6,1), (7,1), (8,1), (9,1), (10, 1), (11,1),
(12, 2), (13, 2), (14, 2), (15, 2), (1,3), (2,3), (3,3), (4,3), (5,3), (6,3), (7,3),
(8,3), (9,3), (10,3), (11,3), (12,3), (13,3), (14, 3), (15, 3), (9,4), (1,5), (2,5), 
(3,5), (8,5), (9,5),(10,5)




insert into Person
values
('Joyce Ali', 'joyce@gmail.com', 'ja123', '12345'),
('Rick Mills', 'rick@gmail.com', 'rm123', '12345'),
('Joan Beard', 'joan@gmail.com', 'jb123', '12345'),
('Joe Stanley', 'joe@gmail.com', 'js123', '12345'),
('Don Oliver', 'don@gmail.com', 'do123', '12345')

select * from person

insert into Person
values 
('Anna Wood' , 'anna@gmail.com', 'aw123', '12345'),
('Lewis Moore', 'lewis@gmail.com', 'lm123', '12345'),
('Sylvia Sanders', 'Sylvia@gmail.com', 'ss123', '12345'),
('Karl Manning', 'karl@gmail.com', 'km123', '12345'),
('Myron Knox', 'myron@gmail.com', 'mk123', '12345'),
('Whitney Navarro', 'whitney@gmail.com', 'wn123', '12345'),
('Ali Ahmad', 'ali@gmail.com', 'aa123', '12345'),
('Brandy Schneider', 'brandy@gmail.com', 'bs123', '12345'),
('Ruby Obrien', 'ruby@gmail.com', 'ro123', '12345')

insert into restaurantManager
values 
(13), (14), (15), (16), (17), (18), (10), (11), (12)




select * from Person

insert into restaurantManager
values
(2), (3), (4), (5), (6)

insert into Person
values 
('Liza Weeks', 'liza@gmail.com', 'lw123', '12345'),
('Edith Hanna', 'edith@gmail.com', 'eh123', '12345'),
('Dustin Fernandez', 'dustin@gmail.com', 'df123', '12345')

insert into [dbo].[foodpandaAdministrator]
values 
(7), (8), (9)


select * from restaurant

insert into tag
select 

insert into Person 
values 
('Dexter Travis', 'dexter@gmail.com', 'dt123', '12345'),
('Sonya Gillespie', 'sonya@gmail.com', 'sg123', '12345'),
('Sarah Callahan', 'sarah@gmail.com', 'sc123', '12345')

select * from restaurantManager

insert into restaurantManager 
values 
(19), (20), (21)


select personID, name from Person join 
(select * from restaurantManager
except
select restaurantManager.managerID from restaurant join restaurantManager 
on restaurant.managerID = restaurantManager.managerID) as T1
on Person.personID = T1.managerID


select * from restaurantManager join person on personID = managerID
where managerID = 5

select * from tag

insert into tag 
values 
('Chinese'),
('Asian'),
('Barbeque'),
('Dessert')


select * from restaurant

select area, city from restaurantLocations join restaurant on restaurantLocations.restaurantID = restaurant.restaurantID
join location on location.locationID = restaurantLocations.locationID where restaurant.name = 'The Wok'

select * from restaurantManager join person on personID = managerID
                where managerID = 2


select * from [dbo].[menuAndItems] join [dbo].[menuItem] on [dbo].[menuAndItems].itemID = [dbo].[menuItem].itemID
where [dbo].[menuAndItems].menuID = 3

select * from menuitem

select * from Restaurant where name = 'Mcdonalds'

INSERT INTO menu DEFAULT VALUES 

select * from menu


select * from restaurant

update restaurant 
set menuID = 20 
where name = 'Qabail'

select * from restaurant join restaurantManager on restaurant.managerID = restaurantManager.managerID join person on personID = restaurantManager.managerID
where restaurant.name = 'Mcdonalds'

select * from restaurant join menuAndItems on restaurant.menuID = menuAndItems.menuID join menuItem on menuAndItems.itemID = menuItem.itemID


select * from restaurant

select distinct([type]) from restaurant join menuAndItems on restaurant.menuID = menuAndItems.menuID join menuItem on menuItem.itemID
= menuAndItems.itemID where restaurant.name = 'Mcdonalds'



select menuItem.itemID from menuItem join menuAndItems on menuItem.itemID = menuAndItems.itemID join restaurant
on restaurant.menuID = menuAndItems.menuID
where restaurant.name = 'Mcdonalds' and menuItem.name = 'McChicken Burger'

update menuItem
set name = 'McChicken Burger'
where itemID = 4

select * from menuItem


delete from menuAndItems where itemID = 2
delete from menuItem where itemID = 2



select * from [order] join [OrderRestaurant] on [order].orderID = [OrderRestaurant].orderID 
join restaurant on restaurant.restaurantID = OrderRestaurant.restaurantID

insert into [order] values
(1, 1200, 'Pending', 1, 'lolol'),
(2, 2300, 'Pending', 1, 'llllllooolll')

insert into orderRestaurant
values (1, 1), (2, 2)

select * from restaurant join restaurantManager on restaurant.managerID = restaurantManager.managerID join person on personID = restaurant.managerID

select * from [order]

update [order] set [status] = 'Pending'

insert into promoCode([discountPercentage],[code]) values 
(10, 'Helo')