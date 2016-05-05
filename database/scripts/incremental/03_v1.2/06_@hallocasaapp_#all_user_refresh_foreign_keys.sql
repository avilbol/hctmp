ALTER TABLE hallocasaapp.user DROP FOREIGN KEY user_ibfk_1;
ALTER TABLE hallocasaapp.user DROP FOREIGN KEY user_ibfk_2;
ALTER TABLE hallocasaapp.user DROP FOREIGN KEY user_ibfk_3;
ALTER TABLE hallocasaapp.user ADD CONSTRAINT fk__user__country FOREIGN KEY (country_id) REFERENCES country(id);
ALTER TABLE hallocasaapp.user ADD CONSTRAINT fk__user__state FOREIGN KEY (state_id) REFERENCES state(id);
ALTER TABLE hallocasaapp.user ADD CONSTRAINT fk__user__telephone FOREIGN KEY (user_telephone_id) REFERENCES telephone(id);
