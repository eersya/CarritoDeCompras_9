drop database if exists sssusers;
create database sssusers;

use sssusers;
create table users(id_user int primary key auto_increment,
			name varchar(20), lastname varchar(30),
			email varchar(22), password varchar(16),
			address tinytext, city int, 
			foreign key(city) references ssscities.cities(id_city));


create table vendors(id_vendor int primary key auto_increment, id_vuser int not null,
                        foreign key (id_vuser) references sssusers.users(id_user));

#drop user 'a'@'localhost';
create user 'a'@'localhost' identified by '8991*';
grant select, delete, execute, update on sssusers.* to 'a'@'localhost';
grant select on mysql.proc to 'a'@'localhost';

/*drop user 'b'@'localhost';
create user 'b'@'localhost' identified by '4C67*b346';
grant execute on procedure ssscite.view_vendor;
flush privileges;*/