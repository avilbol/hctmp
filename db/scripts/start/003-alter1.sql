USE hallocasaapp;
START TRANSACTION;
ALTER TABLE hallocasaapp.property add column publish_date datetime not null;
ALTER TABLE hallocasaapp.filter add column lang varchar(200) default 'hallocasa-pending';
COMMIT;