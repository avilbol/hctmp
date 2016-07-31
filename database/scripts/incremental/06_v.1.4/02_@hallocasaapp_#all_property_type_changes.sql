UPDATE hallocasaapp.property SET property_type_id=1 WHERE property_type_id IN (3, 7, 8, 13);
DELETE FROM hallocasaapp.property_type WHERE id IN (3, 7, 8, 13);

UPDATE hallocasaapp.property_type SET name = '{''en'':''Lot with Built'',''de'':''Bebautes Grundst\\u00FCck'',''es'':''Finca con Casa''}' WHERE id = 5;
UPDATE hallocasaapp.property_type SET name = '{''en'':''Warehouse'',''de'':''Lagerhalle'',''es'':''Bodega''}' WHERE id = 10;

INSERT INTO hallocasaapp.property_type (id, name, group_id) VALUES (15, '{''en'':''Detached House'',''de'':''Einfamilienhaus'',''es'':''Casa Unifamiliar''}',3);
INSERT INTO hallocasaapp.property_type (id, name, group_id) VALUES (16, '{''en'':''Apartment Building'',''de'':''Mehrfamilienhaus'',''es'':''Edificio de Apartamentos''}',3);
INSERT INTO hallocasaapp.property_type (id, name, group_id) VALUES (17, '{''en'':''Airport'',''de'':''Flughafen'',''es'':''Aeropuerto''}',3);
INSERT INTO hallocasaapp.property_type (id, name, group_id) VALUES (18, '{''en'':''Factory'',''de'':''Fabrik'',''es'':''F\\u00E1brica''}',3);
INSERT INTO hallocasaapp.property_type (id, name, group_id) VALUES (19, '{''en'':''School'',''de'':''Schule'',''es'':''Escuela''}',3);
INSERT INTO hallocasaapp.property_type (id, name, group_id) VALUES (20, '{''en'':''Theater'',''de'':''Theater'',''es'':''Teatro''}',3);
INSERT INTO hallocasaapp.property_type (id, name, group_id) VALUES (21, '{''en'':''Kindergarten'',''de'':''Kindergarten'',''es'':''Jard\\u00EDn Infantil''}',3);
INSERT INTO hallocasaapp.property_type (id, name, group_id) VALUES (22, '{''en'':''Car Park'',''de'':''Parkplatz'',''es'':''Estacionamiento''}',3);
INSERT INTO hallocasaapp.property_type (id, name, group_id) VALUES (23, '{''en'':''Hospital'',''de'':''Krankenhaus'',''es'':''Hospital''}',3);
