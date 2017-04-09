USE hallocasaappmig;
START TRANSACTION;
INSERT INTO dropdown_option_group VALUES (27, 'Annual return on investment', false);
UPDATE filter SET dropdown_option_group_id = 27 WHERE id = 26;
INSERT INTO dropdown_option (id, dropdown_option_group_id, option_id, name, data1) values 
(272, 27, 1, '0 - 5 %', '0 - 5 %'),
(273, 27, 2, '5 - 10 %', '5 - 10 %'),
(274, 27, 3, '10 - 15 %', '10 - 15 %'),
(275, 27, 4, '15 - 20 %', '15 - 20 %'),
(276, 27, 5, '20 - 25 %', '20 - 25 %'),
(277, 27, 6, '25 - 30 %', '25 - 30 %'),
(278, 27, 7, '30 - 35 %', '30 - 35 %'),
(279, 27, 8, '35 - 40 %', '35 - 40 %'),
(280, 27, 9, '40 - 45 %', '40 - 45 %'),
(281, 27, 10, '45 - 50 %', '45 - 50 %'),
(282, 27, 11, '50 - 55 %', '50 - 55 %'),
(283, 27, 12, '55 - 60 %', '55 - 60 %'),
(284, 27, 13, '60 - 65 %', '60 - 65 %'),
(285, 27, 14, '65 - 70 %', '65 - 70 %'),
(286, 27, 15, '70 - 75 %', '70 - 75 %'),
(287, 27, 16, '75 - 80 %', '75 - 80 %'),
(288, 27, 17, '80 - 85 %', '80 - 85 %'),
(289, 27, 18, '85 - 90 %', '85 - 90 %'),
(290, 27, 19, '90 - 95 %', '90 - 95 %'),
(291, 27, 20, '95 - 100 %', '95 - 100 %');
COMMIT;
