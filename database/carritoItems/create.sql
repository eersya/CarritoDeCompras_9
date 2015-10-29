
# CarritoItems

drop database if exists carritoItems;
create database carritoItems;
use carritoItems;

create table items(id_item int primary key auto_increment, title varchar(30),
		description tinytext,
                price decimal(10), vendor int, picturepath varchar(100),
		foreign key(vendor) references carritoUsers.vendors(id_vendor));

# drop user 'c'@'localhost' ;
create user 'c'@'localhost' identified by 'kkkk134';
grant select,execute on carritoItems.* to 'c'@'localhost';
grant select on mysql.proc to 'c'@'localhost';

# Procedures

delimiter //
	create procedure add_item(In title_s varchar(30), description_s tinytext, 
                                    price_s decimal(10), email_s varchar(22), picturepath_s varchar(100))
    begin
        declare iduser int;
        select id_user into iduser
        from sssusers.users where sssusers.users.email=email_s;
        insert into items(title,description,price,vendor,picturepath) 
            values(title_s, description_s, price_s, iduser,picturepath_s);
    end//
