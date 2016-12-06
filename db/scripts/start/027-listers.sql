USE hallocasaapp;
ALTER TABLE filter ADD lister_option VARCHAR(50);
UPDATE filter SET lister_option='STATE' WHERE id=8;
UPDATE filter SET lister_option='CITY'  WHERE id=9;
ALTER TABLE property_field ADD lister_option VARCHAR(50);
UPDATE property_field SET lister_option='STATE' WHERE id=7;
UPDATE property_field SET lister_option='CITY'  WHERE id=8;