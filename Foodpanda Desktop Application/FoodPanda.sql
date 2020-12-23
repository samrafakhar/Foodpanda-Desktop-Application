--create database fp
--use fp

--drop database fp


CREATE TABLE [dbo].[person] (
    [personID] INT        NOT NULL identity(1,1),
    [name]     VARCHAR (50) NOT NULL,
    [email]    VARCHAR (50) NOT NULL,
    [username] VARCHAR (10) NOT NULL,
    [password] VARCHAR (10) NOT NULL,
    CONSTRAINT [PK_person] PRIMARY KEY ([personID])
);

CREATE TABLE [dbo].[user] (
    [userID]         INT        NOT NULL,
    [contactNumber]  VARCHAR (15) NULL,
    CONSTRAINT [PK_user] PRIMARY KEY ([userID]),
    CONSTRAINT [FK_user_person] FOREIGN KEY ([userID]) REFERENCES [dbo].[person] ([personID])
);

CREATE TABLE [dbo].[restaurantManager] (
    [managerID] INT NOT NULL,
    CONSTRAINT [PK_restaurantManager] PRIMARY KEY ([managerID]),
    CONSTRAINT [FK_restaurantManager_person] FOREIGN KEY ([managerID]) REFERENCES [dbo].[person] ([personID])
);

CREATE TABLE [dbo].[promoCode] (
    [codeID]             INT        NOT NULL identity(1,1),
    [discountPercentage] INT        NULL,
    [code]               VARCHAR (10) NULL,
    CONSTRAINT [PK_promoCode] PRIMARY KEY ([codeID])
);

CREATE TABLE [dbo].[location] (
    [address]    VARCHAR (50) NULL,
    [area]       VARCHAR (20) NULL,
    [city]       VARCHAR (20) NULL,
    [locationID] INT        NOT NULL identity(1,1),
    CONSTRAINT [PK_location] PRIMARY KEY ([locationID])
);


CREATE TABLE [dbo].[menuItem] (
[itemID]      INT        NOT NULL identity(1,1),
    [name]        VARCHAR (100)        NULL,
    [description] VARCHAR (100) NULL,
    [price]       INT        NULL,
    [type]        VARCHAR (15) NULL,
    CONSTRAINT [PK_menuItem] PRIMARY KEY ([itemID])
);

CREATE TABLE [dbo].[menu] (
    [menuID]   INT        NOT NULL identity(1,1),
    CONSTRAINT [PK_sectionItems] PRIMARY KEY ([menuID])
);    



CREATE TABLE [dbo].[menuAndItems] (
    [menuID]   INT       NOT NULL,
    [itemID]     INT        NOT NULL,
    CONSTRAINT [PK_sectionItem] PRIMARY KEY ([menuID], [itemID] ),
CONSTRAINT [FK_restaurant_menu] FOREIGN KEY ([menuID]) REFERENCES [dbo].[menu] ([menuID]),
    CONSTRAINT [FK_sectionItems_menuItem] FOREIGN KEY ([itemID]) REFERENCES [dbo].[menuItem] ([itemID]),
);


drop table  [dbo].[OrderRestaurant]
drop table  [dbo].[order]

CREATE TABLE [dbo].[order] (
    [orderID]    INT identity(1,1) NOT NULL,
    [totalPrice] INT NOT NULL,
    [status]     VARCHAR (10) NOT NULL,
    [userID]     INT        NOT NULL,
    [desc]  Varchar(20) NULL, 
    CONSTRAINT [PK_order] PRIMARY KEY ([orderID]),
    CONSTRAINT [FK_order_user] FOREIGN KEY ([userID]) REFERENCES [dbo].[user] ([userID])
);

CREATE TABLE [dbo].[restaurant] (
    [restaurantID]  INT        NOT NULL identity(1,1),
    [name]          VARCHAR (20) NULL,
    [contactNumber] VARCHAR (15) NULL,
    [managerID]     INT        NULL,
    [menuID]        INT        NULL,
    CONSTRAINT [PK_restaurant] PRIMARY KEY ([restaurantID]),

    CONSTRAINT [FK_restaurant_menus] FOREIGN KEY ([menuID]) REFERENCES [dbo].[menu] ([menuID]),
    CONSTRAINT [FK_restaurant_restaurantManager] FOREIGN KEY ([managerID]) REFERENCES [dbo].[restaurantManager] ([managerID])
);

create table dbo.[OrderRestaurant](
[orderID]    INT        NOT NULL,
[restaurantID]  INT        NOT NULL,
CONSTRAINT [PK_or] PRIMARY KEY ([orderID],[restaurantID]),
CONSTRAINT [FK_R] FOREIGN KEY ([restaurantID]) REFERENCES [dbo].[restaurant] ([restaurantID]),
CONSTRAINT [FK_order] FOREIGN KEY ([orderID]) REFERENCES [dbo].[order] ([orderID])
)


CREATE TABLE [dbo].[tag] (
    [tagID] INT        NOT NULL identity(1,1),
    [name]  VARCHAR (10) NULL,
    CONSTRAINT [PK_tag] PRIMARY KEY ([tagID])
);
CREATE TABLE [dbo].[review] (
    [reviewID]     INT        NOT NULL identity(1,1),
    [rating]       INT        NULL,
    [comment]      VARCHAR (100) NULL,
    [userID]       INT        NULL,
    [restaurantID] INT        NULL,
    CONSTRAINT [PK_review] PRIMARY KEY ([reviewID]),
    CONSTRAINT [FK_review_restaurant] FOREIGN KEY ([restaurantID]) REFERENCES [dbo].[restaurant] ([restaurantID]),
    CONSTRAINT [FK_review_user] FOREIGN KEY ([userID]) REFERENCES [dbo].[user] ([userID])
);

CREATE TABLE [dbo].[foodpandaAdministrator] (
    [administratorID] INT NOT NULL ,
    CONSTRAINT [PK_foodpandaAdministrator] PRIMARY KEY ([administratorID]),
    CONSTRAINT [FK_foodpandaAdministrator_person] FOREIGN KEY ([administratorID]) REFERENCES [dbo].[person] ([personID])
);
CREATE TABLE [dbo].[restaurantLocations] (
    [restaurantID] INT NOT NULL,
    [locationID]   INT NOT NULL,
    CONSTRAINT [PK_restaurantLocations] PRIMARY KEY ([restaurantID]   , [locationID]),
    CONSTRAINT [FK_restaurantLocations_location] FOREIGN KEY ([locationID]) REFERENCES [dbo].[location] ([locationID]),
    CONSTRAINT [FK_restaurantLocations_restaurant] FOREIGN KEY ([restaurantID]) REFERENCES [dbo].[restaurant] ([restaurantID])
);

CREATE TABLE [dbo].[restaurantTags] (
    [restaurantID] INT NOT NULL,
    [tagID]        INT NOT NULL,
    CONSTRAINT [PK_restaurantTags] PRIMARY KEY ([restaurantID]   , [tagID]),
    CONSTRAINT [FK_restaurantTags_restaurant] FOREIGN KEY ([restaurantID]) REFERENCES [dbo].[restaurant] ([restaurantID]),
    CONSTRAINT [FK_restaurantTags_tag] FOREIGN KEY ([tagID]) REFERENCES [dbo].[tag] ([tagID])
);


CREATE TABLE [dbo].[userLocations] (
    [userID]     INT NOT NULL,
    [locationID] INT NOT NULL,
    CONSTRAINT [PK_userLocations] PRIMARY KEY ([userID]   , [locationID]),
    CONSTRAINT [FK_userLocations_location] FOREIGN KEY ([locationID]) REFERENCES [dbo].[location] ([locationID]),
    CONSTRAINT [FK_userLocations_user] FOREIGN KEY ([userID]) REFERENCES [dbo].[user] ([userID])
);
select * from [OrderRestaurant] join restaurant on OrderRestaurant.restaurantID=restaurant.restaurantID
select * from restaurantManager as r1 join restaurant as r on r1.managerID=r.managerID join person on personID=r1.managerID
select * from foodpandaAdministrator join person on administratorID=personID


select * fro

select * from [order]






select * from location
select * from [order]







