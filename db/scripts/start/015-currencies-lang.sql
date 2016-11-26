use hallocasaapp;

alter table currency add lang varchar(100) default 'hallocasa.pending';
update hallocasaapp.currency set name = 'Colombian Peso' where id = 1;
update hallocasaapp.currency set name = 'Euro' where id = 2;
update hallocasaapp.currency set name = 'US Dollar' where id = 3;
update hallocasaapp.currency set name = 'Canadian Dollar' where id = 4;
update hallocasaapp.currency set name = 'British Pound' where id = 5;
update hallocasaapp.currency set name = 'Swiss Franc' where id = 6;
update hallocasaapp.currency set name = 'Australlian Dollar' where id = 7;