USE hallocasaapp;
alter table lang add locale varchar(4);
update lang set locale='en' where id = 1;
update lang set locale='es' where id = 2;
update lang set locale='de' where id = 3;