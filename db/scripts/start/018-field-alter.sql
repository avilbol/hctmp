USE hallocasaappmig;
START TRANSACTION;
update property_field set data1_type='INT',data2_type='DOUBLE',text_type=null where id = 60;
COMMIT;