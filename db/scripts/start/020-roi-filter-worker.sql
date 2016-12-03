USE hallocasaapp;
START TRANSACTION;
UPDATE filter SET filter_worker_option = 'ROI' WHERE id = 26;
COMMIT;
