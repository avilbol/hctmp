USE hallocasaappmig;
alter table user add register_date datetime not null;
SET SQL_SAFE_UPDATES = 0;
update user set register_date='2016-12-04 04:54:22' where id = 1;
SET SQL_SAFE_UPDATES = 1;