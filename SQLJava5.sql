create database AssJava5

CREATE TABLE Orders (
  Id int primary key identity(1,1),
  UserID varchar(200),
  CreateDate date,
  Address nvarchar(200),
  TotalAmount Decimal,
  foreign key (UserID) REFERENCES Accounts (Username)
);

CREATE TABLE Accounts (
  Username varchar(200)primary key,
  Password varchar(200),
  Fullname Nvarchar(200),
  Email varchar(200),
  Active bit,
  Admin bit,

);
CREATE TABLE Product(
  Id int identity(1,1) primary key,
  Name Nvarchar(200),
  Price Decimal,
  Quantity int,
  Category nvarchar(100),
  [Power] int,
  Color nvarchar(50),
);

CREATE TABLE OrderDetails (
  Id int identity(1,1) primary key,
  OrderId int,
  ProductId int,
  Quantity int,
  Price Decimal,
  foreign key (OrderId) REFERENCES Orders (Id),
  foreign key (ProductId) REFERENCES Product (Id)
);

SELECT NEW ThongKe(P.id, P.name, SUM(OD.quantity) AS totalQuantity)
FROM Product p
JOIN OrderDetails OD ON P.id = OD.productId
GROUP BY P.id, P.name
ORDER BY totalQuantity DESC
LIMIT 10;


		select s1_0.name,sum(o1_0.Quantity) from Product s1_0 join OrderDetails o1_0 on s1_0.id=o1_0.ProductId group by s1_0.id,s1_0.name order by sum(o1_0.Quantity) desc