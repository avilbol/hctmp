CREATE TABLE hallocasaapp.property_field_rule(
       property_field_id INTEGER NOT NULL, 
       property_type_group_id INTEGER NOT NULL, 
       property_proposal_id INTEGER NOT NULL, 
       property_location_id INTEGER NOT NULL, 
       country_id INTEGER, 
       render_field CHAR(2),
       FOREIGN KEY(property_field_id) REFERENCES property_field(id),
       FOREIGN KEY(property_type_group_id) REFERENCES property_type_group(id),
       FOREIGN KEY(property_proposal_id) REFERENCES property_proposal(id),
       FOREIGN KEY(property_location_id) REFERENCES property_location(id),
       FOREIGN KEY(country_id) REFERENCES country(id)
);