delimiter //
CREATE procedure add_user(IN  name_s varchar(20), lastname_s varchar(30),
				email_s varchar(22), password_s varchar(16),
				address_s tinytext, city_s varchar(2))
begin
	declare city_k INT;
	select id_city into city_k 
	from ssscities.cities where city=city_s;
	insert into sssusers.users(name, lastname, email, password,
				address, city) VALUES
			(name_s, lastname_s, email_s, password_s, address_s, city_k);
end //

use sssusers;
delimiter //
create procedure exists_user(IN email_s varchar(22))
begin
    select count(*) from sssusers.users where email=email_s;
end//

select * from  sssusers.vendors;
call exists_user('andore124@gmail.com');
delete from sssusers.vendors where id_vendor=8;
delete from sssusers.users where email='andore124@gmail.com';

/*delimiter //
CREATE procedure view_vendor(IN  name_s varchar(20), lastname_s varchar(30),
				email_s varchar(22), password_s varchar(16),
				address_s tinytext, city_s varchar(2))
begin
	declare city_k INT;
	select id_city into city_k 
	from ssscities.cities where city=city_s;
	insert into sssusers.users(name, lastname, email, password,
				address, city) VALUES
			(name_s, lastname_s, email_s, password_s, address_s, city_k);
end //*/

