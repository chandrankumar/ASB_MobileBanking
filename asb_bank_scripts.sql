CREATE TABLE customer (
 customer_id INT NOT NULL AUTO_INCREMENT,
 full_name varchar(30),
 age INT,
 gender varchar(10),
 address varchar(50),
 occupation varchar(50),
 PRIMARY KEY (customer_id)
 );
 
 CREATE TABLE accounts (
 account_id INT NOT NULL AUTO_INCREMENT,
 account_type varchar(30),
 balance_amount Double,
 customer_id INT,
 PRIMARY KEY (account_id),
 FOREIGN key (customer_id) REFERENCES customer(customer_id)
 );
 
 
 select * from customer;
 select * from accounts;
 
 drop table accounts;
 drop table customer;