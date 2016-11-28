alter table hallocasaapp.filter add filter_worker_option varchar(100);

update hallocasaapp.filter set filter_worker_option='PROPERTY_LOCATION_KEY' WHERE id IN (3);
update hallocasaapp.filter set filter_worker_option='PROPERTY_TYPE_KEY' WHERE id IN (1);
update hallocasaapp.filter set filter_worker_option='COUNTRY_KEY' WHERE id IN (7);
update hallocasaapp.filter set filter_worker_option='PROPERTY_PROPOSAL_KEY' WHERE id IN (2);
update hallocasaapp.filter set filter_worker_option='RANGE' WHERE id IN (6, 12, 13, 27);
update hallocasaapp.filter set filter_worker_option='DROPDOWN' WHERE id IN (8, 9, 10, 14, 17, 18, 19, 20, 28, 29, 30, 31, 32, 33, 34);
update hallocasaapp.filter set filter_worker_option='BOOLEAN' WHERE id IN (15, 22, 23, 25);

-- updating filter type of basement (checkbox two options, to dropdown)
update hallocasaapp.filter set filter_type_id = 1 WHERE id = 20;
-- deleting publishing date and full basement filters
delete from hallocasaapp.filter where id in (21, 24); 
-- deleting yes/no with sort filter type
delete from hallocasaapp.filter_type where id = 3;
