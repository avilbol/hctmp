use hallocasaapp;
START TRANSACTION;

ALTER TABLE filter add column dropdown_option_group_id int;
ALTER TABLE filter ADD CONSTRAINT filter_dropdown_option_group_id FOREIGN KEY (dropdown_option_group_id) REFERENCES dropdown_option_group(id);
ALTER TABLE filter DROP api_operation;


ALTER TABLE property_field add column dropdown_option_group_id int;
ALTER TABLE property_field ADD CONSTRAINT pfield_dropdown_option_group_id FOREIGN KEY (dropdown_option_group_id) REFERENCES dropdown_option_group(id);
ALTER TABLE property_field ADD COLUMN tooltip_lang varchar(200) default 'hallocasa.pending';

DELETE FROM filter_listing_step WHERE filter_id = 10;

COMMIT;



