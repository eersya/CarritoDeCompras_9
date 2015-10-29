
# Carrito Users

drop database if exists carritoUsers;
create database carritoUsers;

use carritoUsers;
create table users(id_user int primary key auto_increment,
			name varchar(20), lastname varchar(30),
			email varchar(22), password varchar(16),
			address tinytext, city int, 
			foreign key(city) references carritoCities.cities(id_city));


create table vendors(id_vendor int primary key auto_increment, id_vuser int not null,
                        foreign key (id_vuser) references carritoUsers.users(id_user));

# drop user 'a'@'localhost';
create user 'a'@'localhost' identified by '8991*';
grant select, delete, execute, update on carritoUsers.* to 'a'@'localhost';
grant select on mysql.proc to 'a'@'localhost';

# Procedures

delimiter //
CREATE procedure add_user(IN  name_s varchar(20), lastname_s varchar(30),
				email_s varchar(22), password_s varchar(16),
				address_s tinytext, city_s varchar(2))
begin
	declare city_k INT;
	select id_city into city_k 
	from carritoCities.cities where city=city_s;
	insert into carritoUsers.users(name, lastname, email, password,
				address, city) VALUES
			(name_s, lastname_s, email_s, password_s, address_s, city_k);
end //

delimiter //
create procedure exists_user(IN email_s varchar(22))
begin
    select count(*) from carritoUsers.users where email=email_s;
end//

# Triggers

delimiter //
create trigger add_vendor after insert on users for each row
    begin
        insert into vendors (id_vuser) values(new.id_user);
end //
