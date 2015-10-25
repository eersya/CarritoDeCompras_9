drop database if exists ssscities;
create database ssscities;
use ssscities;
create table cities(id_city int primary key auto_increment,city varchar(20) not null);

insert into cities(city) values("MX");
