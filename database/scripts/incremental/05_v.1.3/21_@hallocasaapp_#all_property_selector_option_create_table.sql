CREATE TABLE hallocasaapp.property_selector_option(
       id INTEGER NOT NULL,
       property_selector_id INTEGER NOT NULL,
       selector_value VARCHAR(300),
       FOREIGN KEY(property_selector_id) REFERENCES property_selector(id)
);
