
use sssusers;


delimiter //
create trigger add_vendor after insert on users for each row
    begin
        insert into vendors (id_vuser) values(new.id_user);
    end //

