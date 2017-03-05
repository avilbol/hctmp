USE hallocasaappmig;

SELECT * FROM property_field_condition_option
WHERE
	property_field_id = (SELECT id FROM property_field WHERE name='Square Meters Built')
    AND
	(parent_property_field_id, parent_property_field_option_id) 
    IN (
     (
      (SELECT id FROM property_field WHERE name='Country'),
      (SELECT id FROM country WHERE country_name='Colombia')
	 ),
     (
      (SELECT id FROM property_field WHERE name='Property type'),
      (SELECT id FROM property_type_group WHERE name='Lotes')
	 ),
     (
      (SELECT id FROM property_field WHERE name='Property location'),
      (SELECT id FROM property_location WHERE name='City center')
	 ),
     (
      (SELECT id FROM property_field WHERE name='Property proposal'),
      (SELECT id FROM property_proposal WHERE name='Buy')
	 )
	);
    
SELECT * FROM property_field_condition_option
WHERE
	(property_field_id) IN 
    (
		(SELECT id FROM property_field WHERE name='Condition'),
        (SELECT id FROM property_field WHERE name='Heating'),
        (SELECT id FROM property_field WHERE name='Parking Spots'),
        (SELECT id FROM property_field WHERE name='Method of Construction'),
        (SELECT id FROM property_field WHERE name='Year of Construction'),
        (SELECT id FROM property_field WHERE name='Last Modernization')
    )
    AND
	(parent_property_field_id, parent_property_field_option_id) 
    IN (
     (
      (SELECT id FROM property_field WHERE name='Property type'),
      (SELECT id FROM property_type_group WHERE name='Lotes')
	 )
	);
    
    

SELECT * FROM property_field_condition_option
WHERE
	(property_field_id) IN 
    (
		(SELECT id FROM property_field WHERE name='Suitable For')
    )
    AND
	(parent_property_field_id, parent_property_field_option_id) 
    IN (
     (
      (SELECT id FROM property_field WHERE name='Property type'),
      (SELECT id FROM property_type_group WHERE name='Stand-Alone')
	 )
	);

UPDATE property_field 
SET name = '(Estimated) Monthly Rent', 
lang = 'hallocasa.propertyfield.estimatedmonthlyrent'
WHERE id = 60;
