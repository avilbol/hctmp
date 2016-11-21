alter table hallocasaapp.filter add column use_property_field tinyint(0) default 1;
update hallocasaapp.filter set use_property_field = 0 WHERE id = 24;
update hallocasaapp.filter set use_property_field = 0 WHERE id = 26;