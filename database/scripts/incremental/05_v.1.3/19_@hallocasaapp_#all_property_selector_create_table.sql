CREATE TABLE hallocasaapp.property_selector(
       id INTEGER PRIMARY KEY,
       property_field_id INTEGER NOT NULL,
       property_selector_type_id INTEGER NOT NULL,
       range_start INTEGER,
       range_end INTEGER,
       exclusions VARCHAR(100),
       FOREIGN KEY(property_field_id) REFERENCES property_field(id),
       FOREIGN KEY(property_selector_type_id) REFERENCES property_selector_type(id)
);
