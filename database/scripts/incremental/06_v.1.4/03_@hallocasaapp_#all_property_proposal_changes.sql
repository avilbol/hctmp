UPDATE hallocasaapp.property_field_rule SET property_proposal_id = 1 WHERE property_proposal_id = 2;
UPDATE hallocasaapp.property SET property_proposal_id = 1 WHERE property_proposal_id = 2;
DELETE FROM hallocasaapp.property_proposal WHERE id = 2;