USE hallocasaapp;
START TRANSACTION;
update filter SET  filter_worker_option='CURRENCY_RANGE' WHERE id = 5;
update filter SET  filter_worker_option='DROPDOWN' WHERE id = 4;
COMMIT;