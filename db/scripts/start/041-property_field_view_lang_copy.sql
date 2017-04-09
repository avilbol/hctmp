USE hallocasaappmig;
SET SQL_SAFE_UPDATES=0;
UPDATE property_field SET view_lang=CONCAT(lang, ".viewlang");
SET SQL_SAFE_UPDATES=1;