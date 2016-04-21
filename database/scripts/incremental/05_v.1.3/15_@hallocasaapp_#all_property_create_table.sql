CREATE TABLE hallocasaapp.property(
       property_id VARCHAR(20) PRIMARY KEY,
       property_type_id INTEGER NOT NULL,
       property_proposal_id INTEGER NOT NULL,
       property_location_id INTEGER NOT NULL,
       user_id INTEGER NOT NULL,
       country_id INTEGER NOT NULL,
       FOREIGN KEY(property_type_id) REFERENCES property_type(id),
       FOREIGN KEY(property_proposal_id) REFERENCES property_proposal(id),
       FOREIGN KEY(property_location_id) REFERENCES property_location(id),
       FOREIGN KEY(country_id) REFERENCES country(id)
);