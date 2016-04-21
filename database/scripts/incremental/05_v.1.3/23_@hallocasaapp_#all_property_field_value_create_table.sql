CREATE TABLE hallocasaapp.property_field_value(
      property_id VARCHAR(20) NOT NULL,
      property_field_id INTEGER NOT NULL,
      property_value VARCHAR(800),
      PRIMARY KEY(property_id, property_field_id)
);