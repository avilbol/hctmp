use hallocasaappmig;

alter table currency add lang varchar(100) default 'hallocasa.pending';
update hallocasaappmig.currency set name = 'Colombian Peso' where id = 1;
update hallocasaappmig.currency set name = 'Euro' where id = 2;
update hallocasaappmig.currency set name = 'US Dollar' where id = 3;
update hallocasaappmig.currency set name = 'Canadian Dollar' where id = 4;
update hallocasaappmig.currency set name = 'British Pound' where id = 5;
update hallocasaappmig.currency set name = 'Swiss Franc' where id = 6;
update hallocasaappmig.currency set name = 'Australlian Dollar' where id = 7;