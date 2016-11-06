USE hallocasaapp;
START TRANSACTION;
/*
-- authorization_code
*/
INSERT INTO `authorization_code` (`id`,`client_id`,`auth_code`) VALUES (1,'hallocasa_davasquez_api_tester','0a33ea311f854b5d6ffffe171224bd80');
INSERT INTO `authorization_code` (`id`,`client_id`,`auth_code`) VALUES (2,'hallocasa_oguerrero_api_tester','7c0e62d2803b65efca67ebeddc2cbaa2');
INSERT INTO `authorization_code` (`id`,`client_id`,`auth_code`) VALUES (3,'hallocasa_avilbol_api_tester','c3262724a345b473cccdba7a883ec8e3');
/*
-- user
*/
INSERT INTO `hallocasaapp`.`user` (`id`, `email`, `password`, `language`, `confirmed_flag`, `spoken_languages`, `IMAGE_NAME`) VALUES ('2', 'development@hallocasa.com', '26239de944662b89a61df8c350e47f48', 'es', '1', '[]', '/userimage/user0.jpg');
COMMIT;