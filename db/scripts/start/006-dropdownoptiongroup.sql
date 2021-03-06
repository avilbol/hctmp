use hallocasaappmig;
START TRANSACTION;

create table dropdown_option_group(
        id int not null primary key,
        name varchar(200),
        depends_on_lang tinyint(1)
);

create table dropdown_option(
        id int not null primary key,
        dropdown_option_group_id int not null,
        option_id int not null,
        name varchar(200),
        data1 varchar(200),
        data2 varchar(200),
        data3 varchar(200),
        data4 varchar(200),
        data5 varchar(200),
        data6 varchar(200),
        data7 varchar(200),
        data8 varchar(200),
        foreign key(dropdown_option_group_id) references dropdown_option_group(id)
);

insert into dropdown_option_group values
(1, 'Neigborhood', 1),
(2, 'Estrato - Colombia', 0),
(3, 'Estrato - Panama', 0),
(4, 'Estrato - Costa Rica', 0),
(5, 'Estrato - Chile', 0),
(6, 'Estrato - Argentina', 0),
(7, 'Estrato - Peru', 0),
(8, 'Estrato - Ecuador', 0),
(9, 'Kind of Road', 1),
(10, 'Condition', 1),
(11, 'Security', 1),
(12, 'Optional features', 1),
(13, 'Suitable for', 1),
(14, 'Heating', 1),
(15, 'Basement', 1),
(16, 'Drinking water', 1),
(17, 'Sewage water', 1),
(18, 'Year of construction', 1),
(19, 'Method of construction', 1),
(20, 'Type of soil', 1),
(21, 'Agriculture', 1),
(22, 'Last modernization', 1),
(23, 'Price development in last 5 years', 1),
(24, 'Inclination', 1),
(25, 'Agent Fee', 1),
(26, 'Annual tax rate on the property', 1);
insert into dropdown_option(id,dropdown_option_group_id,option_id,name,data1)
values
(1,1,1,'Center','hallocasa.pending.center'),
(2,1,2,'North','hallocasa.pending.north'),
(3,1,3,'North-West','hallocasa.pending.northwest'),
(4,1,4,'North-East','hallocasa.pending.northeast'),
(5,1,5,'South','hallocasa.pending.south'),
(6,1,6,'South-West','hallocasa.pending.southwest'),
(7,1,7,'South-East','hallocasa.pending.southeast'),
(8,1,8,'East','hallocasa.pending.east'),
(9,1,9,'West','hallocasa.pending.west'),

(10,2,1,'colombia estrato 1','1'),
(11,2,2,'colombia estrato 2','2'),
(12,2,3,'colombia estrato 3','3'),
(13,2,4,'colombia estrato 4','4'),
(14,2,5,'colombia estrato 5','5'),
(15,2,6,'colombia estrato 6','6'),

(16,3,1,'panama estrato a','A'),
(17,3,2,'panama estrato b','B'),
(18,3,3,'panama estrato c','C'),
(19,3,4,'panama estrato d','D'),
(20,3,5,'panama estrato e','E'),

(21,4,1,'costa rica estrato 1','1'),
(22,4,2,'costa rica estrato 1.5','1.5'),
(23,4,3,'costa rica estrato 2','2'),
(24,4,4,'costa rica estrato 2.5','2.5'),
(25,4,5,'costa rica estrato 3','3'),
(26,4,1,'costa rica estrato 3.5','3.5'),
(27,4,2,'costa rica estrato 4','4'),
(28,4,3,'costa rica estrato 4.5','4.5'),
(29,4,4,'costa rica estrato 5','5'),
(30,4,5,'costa rica estrato 5.5','5.5'),
(31,4,1,'costa rica estrato 6','6'),
(32,4,2,'costa rica estrato 6.5','6.5'),
(33,4,3,'costa rica estrato 7','7'),
(34,4,4,'costa rica estrato 7.5','7.5'),

(35,5,1,'chile estrato AB','AB'),
(36,5,2,'chile estrato C1','C1'),
(37,5,3,'chile estrato C2','C2'),
(38,5,4,'chile estrato C3','C3'),
(39,5,5,'chile estrato D','D'),
(40,5,6,'chile estrato E','E'),

(41,6,1,'argentina estrato AB/C1','AB/C1'),
(42,6,2,'argentina estrato C2','C2'),
(43,6,3,'argentina estrato C3','C3'),
(44,6,4,'argentina estrato D1','D1'),
(45,6,5,'argentina estrato D2/E','D2/E'),

(46,7,1,'peru estrato A','A'),
(47,7,2,'peru estrato B','B'),
(48,7,3,'peru estrato C','C'),
(49,7,4,'peru estrato D','D'),
(50,7,5,'peru estrato E','E'),

(51,8,1,'ecuador estrato A','A'),
(52,8,2,'ecuador estrato B','B'),
(53,8,3,'ecuador estrato C+','C+'),
(54,8,4,'ecuador estrato C-','C-'),
(55,8,5,'ecuador estrato D','D'),

(56,9,1,'Highway','hallocasa.pending.highway'),
(57,9,2,'Country Road','hallocasa.pending.countryroad'),
(58,9,3,'Paved Road','hallocasa.pending.pavedroad'),
(59,9,4,'Macadam Road','hallocasa.pending.macadamroad'),
(60,9,5,'4x4 Necessary','hallocasa.pending.44necessary'),
(61,9,6,'Kind of road - other','hallocasa.pending.other'),

(62,10,1,'First-time use','hallocasa.pending.firsttimeuse'),
(63,10,2,'Just renovated','hallocasa.pending.justrenovated'),
(64,10,3,'Reconstructed','hallocasa.pending.reconstructed'),
(65,10,4,'Modernized','hallocasa.pending.modernized'),
(66,10,5,'Renovation necessary','hallocasa.pending.renovationnecessary'),
(67,10,6,'Restoration necessary','hallocasa.pending.restorationnecessary'),
(68,10,7,'Demolition necessary','hallocasa.pending.demolitionnecessary'),
(69,10,8,'Condition - other','hallocasa.pending.other'),

(70,11,1,'24x7 Vigilance','hallocasa.pending.247vigilance'),
(71,11,2,'Alarm','hallocasa.pending.alarm'),
(72,11,3,'Electric Fence','hallocasa.pending.electricfence'),
(73,11,4,'Porter','hallocasa.pending.porter'),
(74,11,5,'Surveillance Camera','hallocasa.pending.surveillancecamera'),
(75,11,6,'Security - other','hallocasa.pending.other'),

(76,12,1,'Pool','hallocasa.pending.pool'),
(77,12,2,'BBQ','hallocasa.pending.bbq'),
(78,12,3,'Telephone','hallocasa.pending.telephone'),
(79,12,4,'Internet','hallocasa.pending.internet'),
(80,12,5,'Ascensor','hallocasa.pending.ascensor'),
(81,12,6,'Sauna','hallocasa.pending.sauna'),
(82,12,7,'Built-In Kitchen','hallocasa.pending.builtinkitchen'),

(83,13,1,'Families','hallocasa.pending.families'),
(84,13,2,'Elderly People','hallocasa.pending.elderlypeople'),
(85,13,3,'Vacation','hallocasa.pending.vacation'),
(86,13,4,'Singles','hallocasa.pending.singles'),
(87,13,5,'Students','hallocasa.pending.students'),
(88,13,6,'Handicaped Tenants','hallocasa.pending.handicapedtenants'),
(89,13,7,'Tenants with Pets','hallocasa.pending.tenantswithpets'),
(90,13,8,'Suitable for - other','hallocasa.other'),

(91,14,1,'Gas','hallocasa.pending.gas'),
(92,14,2,'Petrol','hallocasa.pending.petrol'),
(93,14,3,'Electric Energy','hallocasa.pending.electricenergy'),
(94,14,4,'Wood','hallocasa.pending.wood'),
(95,14,5,'Coal','hallocasa.pending.coal'),
(96,14,6,'Heating - other','hallocasa.pending.other'),
(97,14,7,'Heating - none','hallocasa.pending.none'),

(98,15,1,'Full Basement','hallocasa.pending.fullbasement'),
(99,15,2,'Partial Basement','hallocasa.pending.partialbasement'),
(100,15,3,'Basement - none','hallocasa.pending.none'),

(101,16,1,'Well','hallocasa.pending.well'),
(102,16,2,'Fresh water','hallocasa.pending.freshwater'),
(103,16,3,'Floating stream','hallocasa.pending.floatingstream'),
(104,16,4,'River','hallocasa.pending.river'),
(105,16,5,'Tank','hallocasa.pending.tank'),
(106,16,6,'Tub','hallocasa.pending.tub'),
(107,16,7,'Drinking water - other','hallocasa.pending.drinkingwater'),

(108,17,1,'Septic tank','hallocasa.pending.septictank'),
(109,17,2,'Main sewer','hallocasa.pending.mainsewer'),
(110,17,3,'Sewage water - other','hallocasa.pending.other'),
(111,17,4,'Sewage water - none','hallocasa.pending.none'),

(112,18,1,'New Project','hallocasa.pending.newproject'),
(113,18,2,'Within last 10 years','hallocasa.pending.withinlast10years'),
(114,18,3,'Within last 20 years','hallocasa.pending.withinlast20years'),
(115,18,4,'Within last 30 years','hallocasa.pending.withinlast30years'),
(116,18,5,'Within last 50 years','hallocasa.pending.withinlast50years'),
(117,18,6,'Within last 75 years','hallocasa.pending.withinlast75years'),
(118,18,7,'Within last 100 years','hallocasa.pending.withinlast100years'),
(119,18,8,'Older than 100 years','hallocasa.pending.olderthan100years'),

(120,19,1,'Massive house','hallocasa.pending.massivehouse'),
(121,19,2,'Prefabricated house','hallocasa.pending.prefabricatedhouse'),
(122,19,3,'Energy-saving','hallocasa.pending.energysaving'),
(123,19,4,'Framehouse','hallocasa.pending.framehouse'),
(124,19,5,'Architected house','hallocasa.pending.architectedhouse'),
(125,19,6,'Method of construction - other','hallocasa.pending.other'),

(126,20,1,'Swamp','hallocasa.pending.massivehouse'),
(127,20,2,'Rocks','hallocasa.pending.prefabricatedhouse'),
(128,20,3,'Sand','hallocasa.pending.energysaving'),
(129,20,4,'Clay','hallocasa.pending.framehouse'),
(130,20,5,'Within last 10 years','hallocasa.pending.withinlast10years'),
(131,20,6,'Within last 20 years','hallocasa.pending.withinlast20years'),
(132,20,7,'Within last 30 years','hallocasa.pending.withinlast30years'),

(133,21,1,'Apple','hallocasa.pending.apple'),
(134,21,2,'Avocado','hallocasa.pending.avocado'),
(135,21,3,'Banana','hallocasa.pending.banana'),
(136,21,4,'Bellpeper','hallocasa.pending.bellpeper'),
(137,21,5,'Blackberry','hallocasa.pending.blackberry'),
(138,21,6,'Borojo','hallocasa.pending.borojo'),
(139,21,7,'Cantaloupe','hallocasa.pending.cantaloupe'),
(140,21,8,'Carambola','hallocasa.pending.carambola'),
(141,21,9,'Carrots','hallocasa.pending.carrots'),
(142,21,10,'Coconut','hallocasa.pending.coconut'),
(143,21,11,'Corn','hallocasa.pending.corn'),
(144,21,12,'Feijoa','hallocasa.pending.feijoa'),
(145,21,13,'Grape','hallocasa.pending.grape'),
(146,21,14,'Grapefruit','hallocasa.pending.grapefruit'),
(147,21,16,'Guama','hallocasa.pending.guama'),
(148,21,17,'Guava','hallocasa.pending.guava'),
(149,21,18,'Lemon','hallocasa.pending.lemon'),
(150,21,19,'Lulo','hallocasa.pending.lulo'),
(151,21,20,'Mango','hallocasa.pending.mango'),
(152,21,21,'Mangosteen','hallocasa.pending.mangosteen'),
(153,21,22,'Maracuya','hallocasa.pending.maracuya'),
(154,21,23,'Mushrooms','hallocasa.pending.mushrooms'),
(155,21,24,'Nispero','hallocasa.pending.nispero'),
(156,21,25,'Noni','hallocasa.pending.noni'),
(157,21,26,'Onions','hallocasa.pending.onions'),
(158,21,27,'Orange','hallocasa.pending.orange'),
(159,21,28,'Papaya','hallocasa.pending.papaya'),
(160,21,29,'Passion Fruit','hallocasa.pending.passionfruit'),
(161,21,30,'Peach','hallocasa.pending.peach'),
(162,21,31,'Pear','hallocasa.pending.pear'),
(163,21,32,'Pineapple','hallocasa.pending.pineapple'),
(164,21,33,'Pitahaya','hallocasa.pending.pitahaya'),
(165,21,34,'Plum','hallocasa.pending.plum'),
(166,21,35,'Potatoes','hallocasa.pending.potatoes'),
(167,21,36,'Salad','hallocasa.pending.salad'),
(168,21,37,'Soursop','hallocasa.pending.soursop'),
(169,21,38,'Strawberry','hallocasa.pending.strawberry'),
(170,21,39,'Sugar Cane','hallocasa.pending.sugarcane'),
(171,21,40,'Tamarillo','hallocasa.pending.tamarillo'),
(172,21,41,'Tomatoes','hallocasa.pending.tomatoes'),
(173,21,42,'Uchuva','hallocasa.pending.uchuva'),
(174,21,43,'Yuca','hallocasa.pending.yuca'),
(175,21,44,'Zapote','hallocasa.pending.zapote'),
(176,21,45,'Agriculture - other','hallocasa.pending.other'),

(177,22,1,'This year','hallocasa.pending.thisyear'),
(178,22,2,'Within last 3 years','hallocasa.pending.withinlast3years'),
(179,22,3,'Within last 5 years','hallocasa.pending.withinlast5years'),
(180,22,4,'Within last 10 years','hallocasa.pending.withinlast10years'),
(181,22,5,'Within last 20 years','hallocasa.pending.withinlast20years'),
(182,22,6,'Within last 30 years','hallocasa.pending.withinlast30years'),
(183,22,7,'Last modernization - none','hallocasa.pending.none'),

(184,23,1,'More than -40%','hallocasa.pending.morethanm40p'),
(185,23,2,'Between -39% and -30%','hallocasa.pending.betweenm39pandm30p'),
(186,23,3,'Between -29% and -20%','hallocasa.pending.betweenm29pandm20p'),
(187,23,4,'Between -19% and -10%','hallocasa.pending.betweenm19pandm10p'),
(188,23,5,'Between -9% and -5%','hallocasa.pending.betweenm9pandm5p'),
(189,23,6,'Between -5% and 0%','hallocasa.pending.betweenm5pand0p'),
(190,23,7,'Between 0% and +5%','hallocasa.pending.between0pand5p'),
(191,23,8,'Between +5% and +9%','hallocasa.pending.between5pand9p'),
(192,23,9,'Between +10% and +19%','hallocasa.pending.between10pand19p'),
(193,23,10,'Between +20% and +29%','hallocasa.pending.between20pand29p'),
(194,23,11,'Between +30% and +39%','hallocasa.pending.between30pand39p'),
(195,23,12,'More than +40%','hallocasa.pending.morethan40p'),

(196,24,1,'Plain','hallocasa.pending.plain'),
(197,24,2,'Undulated','hallocasa.pending.undulated'),
(198,24,3,'Cliffy','hallocasa.pending.cliffy'),
(199,24,4,'Inclination - other','hallocasa.pending.other'),

(200,25,1,'Agent Fee - 0,50%','0,50%'),
(201,25,2,'Agent Fee - 1%','1%'),
(202,25,3,'Agent Fee - 1,50%','1,50%'),
(203,25,4,'Agent Fee - 2%','2%'),
(204,25,5,'Agent Fee - 2,50%','2,50%'),
(205,25,6,'Agent Fee - 3%','3%'),
(206,25,7,'Agent Fee - 3,50%','3,50%'),
(207,25,8,'Agent Fee - 4%','4%'),
(208,25,9,'Agent Fee - 4,50%','4,50%'),
(209,25,10,'Agent Fee - 5%','5%'),
(210,25,11,'Agent Fee - 5,50%','5,50%'),
(211,25,12,'Agent Fee - 6%','6%'),
(212,25,13,'Agent Fee - 6,50%','6,50%'),
(213,25,14,'Agent Fee - 7%','7%'),
(214,25,15,'Agent Fee - 7,50%','7,50%'),
(215,25,16,'Agent Fee - 8%','8%'),
(216,25,17,'Agent Fee - 8,50%','8,50%'),
(217,25,18,'Agent Fee - 9%','9%'),
(218,25,19,'Agent Fee - 9,50%','9,50%'),
(219,25,20,'Agent Fee - 10%','10%'),
(220,25,21,'Agent Fee - More than 10%','hallocasa.pending.morethan10p'),

(221,26,1,'Annual tax rate on the property - 0,50%','0,50%'),
(222,26,2,'Annual tax rate on the property - 1%','1%'),
(223,26,3,'Annual tax rate on the property - 1,50%','1,50%'),
(224,26,4,'Annual tax rate on the property - 2%','2%'),
(225,26,5,'Annual tax rate on the property - 2,50%','2,50%'),
(226,26,6,'Annual tax rate on the property - 3%','3%'),
(227,26,7,'Annual tax rate on the property - 3,50%','3,50%'),
(228,26,8,'Annual tax rate on the property - 4%','4%'),
(229,26,9,'Annual tax rate on the property - 4,50%','4,50%'),
(230,26,10,'Annual tax rate on the property - 5%','5%'),
(231,26,11,'Annual tax rate on the property - 5,50%','5,50%'),
(232,26,12,'Annual tax rate on the property - 6%','6%'),
(233,26,13,'Annual tax rate on the property - 6,50%','6,50%'),
(234,26,14,'Annual tax rate on the property - 7%','7%'),
(235,26,15,'Annual tax rate on the property - 7,50%','7,50%'),
(236,26,16,'Annual tax rate on the property - 8%','8%'),
(237,26,17,'Annual tax rate on the property - 8,50%','8,50%'),
(238,26,18,'Annual tax rate on the property - 9%','9%'),
(239,26,19,'Annual tax rate on the property - 9,50%','9,50%'),
(240,26,20,'Annual tax rate on the property - 10%','10%'),
(241,26,21,'Annual tax rate on the property - 10,50%','10,50%'),
(242,26,22,'Annual tax rate on the property - 11%','11%'),
(243,26,23,'Annual tax rate on the property - 11,50%','11,50%'),
(244,26,24,'Annual tax rate on the property - 12%','12%'),
(245,26,25,'Annual tax rate on the property - 12,50%','12,50%'),
(246,26,26,'Annual tax rate on the property - 13%','13%'),
(247,26,27,'Annual tax rate on the property - 13,50%','13,50%'),
(248,26,28,'Annual tax rate on the property - 14%','14%'),
(249,26,29,'Annual tax rate on the property - 14,50%','14,50%'),
(250,26,30,'Annual tax rate on the property - 15%','15%'),
(251,26,31,'Annual tax rate on the property - 15,50%','15,50%'),
(252,26,32,'Annual tax rate on the property - 16%','16%'),
(253,26,33,'Annual tax rate on the property - 16,50%','16,50%'),
(254,26,34,'Annual tax rate on the property - 17%','17%'),
(255,26,35,'Annual tax rate on the property - 17,50%','17,50%'),
(256,26,36,'Annual tax rate on the property - 18%','18%'),
(257,26,37,'Annual tax rate on the property - 18,50%','18,50%'),
(258,26,38,'Annual tax rate on the property - 19%','19%'),
(259,26,39,'Annual tax rate on the property - 19,50%','19,50%'),
(260,26,40,'Annual tax rate on the property - 20,00%','20,00%'),
(261,26,41,'Annual tax rate on the property - 20,50%','20,50%'),
(262,26,42,'Annual tax rate on the property - 21%','21%'),
(263,26,43,'Annual tax rate on the property - 21,50%','21,50%'),
(264,26,44,'Annual tax rate on the property - 22%','22%'),
(265,26,45,'Annual tax rate on the property - 22,50%','22,50%'),
(266,26,46,'Annual tax rate on the property - 23%','23%'),
(267,26,47,'Annual tax rate on the property - 23,50%','23,50%'),
(268,26,48,'Annual tax rate on the property - 24%','24%'),
(269,26,49,'Annual tax rate on the property - 24,50%','24,50%'),
(270,26,50,'Annual tax rate on the property - 25%','25%'),
(271,26,51,'Annual tax rate on the property - More than 25%','hallocasa.pending.morethan25p');

COMMIT;





