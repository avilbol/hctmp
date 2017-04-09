USE hallocasaappmig;
START TRANSACTION;
ALTER TABLE hallocasaappmig.property add column publish_date datetime not null;
ALTER TABLE hallocasaappmig.filter add column lang varchar(200) default 'hallocasa-pending';
COMMIT;