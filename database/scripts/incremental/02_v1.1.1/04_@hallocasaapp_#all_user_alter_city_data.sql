ALTER TABLE user DROP COLUMN city;
ALTER TABLE user ADD city_id INT(11) DEFAULT NULL;
ALTER TABLE user ADD CONSTRAINT fk__user__city FOREIGN KEY (city_id) REFERENCES city(id);