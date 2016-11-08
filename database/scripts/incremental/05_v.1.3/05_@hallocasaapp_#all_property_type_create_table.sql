CREATE TABLE hallocasaapp.property_type(
       id INTEGER NOT NULL PRIMARY KEY,
       name VARCHAR(500),
       group_id INTEGER NOT NULL,
       FOREIGN KEY(group_id) REFERENCES hallocasaapp.property_type_group(id) 
);