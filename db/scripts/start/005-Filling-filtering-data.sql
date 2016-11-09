use hallocasaapp;
START TRANSACTION;


DROP TABLE IF EXISTS `filter_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `filter_type` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `use_slider` tinyint(1) NOT NULL,
  `validate_min` tinyint(1) NOT NULL,
  `validate_max` tinyint(1) NOT NULL,
  `allow_multiple` tinyint(1) NOT NULL,
  `use_search` tinyint(1) NOT NULL,
  `use_sort` tinyint(1) NOT NULL,
  `use_select_all` tinyint(1) NOT NULL,
  `use_remote_list` tinyint(1) NOT NULL,
  `use_links` tinyint(1) NOT NULL,
  `use_yes_no_dropdown` tinyint(1) NOT NULL,
  `use_checkbox` tinyint(1) NOT NULL,
  `use_radio` tinyint(1) NOT NULL,
  `use_text` tinyint(1) NOT NULL,
  `toggle` tinyint(1) NOT NULL,
  `sort_sign` tinyint(1) NOT NULL,
  `filter_type_nature` varchar(30) DEFAULT NULL,
  `range_field_presentation` varchar(30) DEFAULT NULL,
  `range_only_from` tinyint(1) DEFAULT NULL,
  `range_only_to` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `filter_type`
--
LOCK TABLES `filter_type` WRITE;
/*!40000 ALTER TABLE `filter_type` DISABLE KEYS */;
INSERT INTO `filter_type` VALUES (1,'Hybrid Dropdown',0,0,0,1,1,0,1,0,0,0,0,0,0,0,0,'DROPDOWN',NULL,NULL,NULL),(2,'Checkbox two options',0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,'YESNO',NULL,NULL,NULL),(3,'Yes/No with Sort',0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,'YESNO',NULL,NULL,NULL),(4,'Unique Dropdown',0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'DROPDOWN',NULL,NULL,NULL),(5,'Single Range',0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,'RANGE','INTEGER',0,0),(6,'Slider Range',1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'RANGE','INTEGER',0,0),(7,'Single Crcy Range',0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,'RANGE','CURRENCY',0,0),(8,'Slider Crcy Range',1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'RANGE','CURRENCY',0,0),(9,'Single Dbl Range',0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,'RANGE','DOUBLE',0,0),(10,'Slider Dbl Range',1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'RANGE','DOUBLE',0,0),(11,'Available From Range',0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'RANGE','DATE',1,0);
/*!40000 ALTER TABLE `filter_type` ENABLE KEYS */;
UNLOCK TABLES;




DROP TABLE IF EXISTS `filter_nature`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `filter_nature` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `filter_nature`
--

LOCK TABLES `filter_nature` WRITE;
/*!40000 ALTER TABLE `filter_nature` DISABLE KEYS */;
INSERT INTO `filter_nature` VALUES (1,'Básico'),(2,'Avanzado');
/*!40000 ALTER TABLE `filter_nature` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `filter`
--

DROP TABLE IF EXISTS `filter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `filter` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `filter_type_id` int(11) NOT NULL,
  `filter_nature_id` int(11) NOT NULL,
  `min_value` decimal(10,2) DEFAULT NULL,
  `max_value` decimal(10,2) DEFAULT NULL,
  `yes_text` varchar(30) DEFAULT NULL,
  `no_text` varchar(30) DEFAULT NULL,
  `use_static_filter_options` tinyint(1) DEFAULT NULL,
  `api_operation` varchar(50) DEFAULT NULL,
  `show_choice` varchar(10) DEFAULT NULL,
  `force_all_filter_options` tinyint(1) DEFAULT NULL,
  `min_value_date_in_past` tinyint(1) DEFAULT NULL,
  `min_value_date_in_future` tinyint(1) DEFAULT NULL,
  `max_value_date_in_past` tinyint(1) DEFAULT NULL,
  `max_value_date_in_future` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `filter_type_id` (`filter_type_id`),
  KEY `filter_nature_id` (`filter_nature_id`),
  CONSTRAINT `filter_ibfk_1` FOREIGN KEY (`filter_type_id`) REFERENCES `filter_type` (`id`),
  CONSTRAINT `filter_ibfk_2` FOREIGN KEY (`filter_nature_id`) REFERENCES `filter_nature` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `filter`
--

LOCK TABLES `filter` WRITE;
/*!40000 ALTER TABLE `filter` DISABLE KEYS */;
INSERT INTO `filter` VALUES (1,'Property types',1,1,NULL,NULL,NULL,NULL,1,'/property_data/types',NULL,0,NULL,NULL,NULL,NULL),(2,'Buy / Rent',1,1,NULL,NULL,NULL,NULL,1,'/property_data/proposals',NULL,0,NULL,NULL,NULL,NULL),(3,'Property locations',1,1,NULL,NULL,NULL,NULL,1,'/property_data/locations',NULL,0,NULL,NULL,NULL,NULL),(4,'Languages',1,1,NULL,NULL,NULL,NULL,1,'/languages',NULL,0,NULL,NULL,NULL,NULL),(5,'Market Price',7,1,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(6,'Square Meters Total',5,1,5.00,1000000.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(7,'Country',1,1,NULL,NULL,NULL,NULL,1,'/countries',NULL,0,NULL,NULL,NULL,NULL),(8,'Department',1,1,NULL,NULL,NULL,NULL,0,NULL,'SHOW',0,NULL,NULL,NULL,NULL),(9,'Town',1,1,NULL,NULL,NULL,NULL,0,NULL,'SHOW',0,NULL,NULL,NULL,NULL),(10,'Neighborhood',1,1,NULL,NULL,NULL,NULL,0,NULL,'SHOW',0,NULL,NULL,NULL,NULL),(11,'Estrato',1,1,NULL,NULL,NULL,NULL,1,NULL,'SHOW',0,NULL,NULL,NULL,NULL),(12,'Rooms',4,2,NULL,NULL,NULL,NULL,1,NULL,'SHOW',NULL,NULL,NULL,NULL,NULL),(13,'Bathrooms',4,2,NULL,NULL,NULL,NULL,1,NULL,'SHOW',NULL,NULL,NULL,NULL,NULL),(14,'Condition',1,2,NULL,NULL,NULL,NULL,1,NULL,'SHOW',0,NULL,NULL,NULL,NULL),(15,'Furnished',2,2,NULL,NULL,NULL,NULL,NULL,NULL,'SHOW',NULL,NULL,NULL,NULL,NULL),(16,'Floor',1,2,NULL,NULL,NULL,NULL,1,NULL,'SHOW',0,NULL,NULL,NULL,NULL),(17,'Optional Features',1,2,NULL,NULL,NULL,NULL,1,NULL,'SHOW',0,NULL,NULL,NULL,NULL),(18,'Suitable For',1,2,NULL,NULL,NULL,NULL,1,NULL,'SHOW',0,NULL,NULL,NULL,NULL),(19,'Parking Spots',1,2,NULL,NULL,NULL,NULL,1,NULL,'SHOW',0,NULL,NULL,NULL,NULL),(20,'Basement',2,2,NULL,NULL,NULL,NULL,NULL,NULL,'SHOW',NULL,NULL,NULL,NULL,NULL),(21,'Full Basement',2,2,NULL,NULL,NULL,NULL,NULL,NULL,'SHOW',NULL,NULL,NULL,NULL,NULL),(22,'Balcony/Rooftop',2,2,NULL,NULL,NULL,NULL,NULL,NULL,'SHOW',NULL,NULL,NULL,NULL,NULL),(23,'Garden',2,2,NULL,NULL,NULL,NULL,NULL,NULL,'SHOW',NULL,NULL,NULL,NULL,NULL),(24,'Publishing Date',3,2,NULL,NULL,NULL,NULL,NULL,NULL,'SHOW',NULL,NULL,NULL,NULL,NULL),(25,'Rented',2,2,NULL,NULL,NULL,NULL,NULL,NULL,'SHOW',NULL,NULL,NULL,NULL,NULL),(26,'Annual Return on Investment',4,1,NULL,NULL,NULL,NULL,1,NULL,'SHOW',0,NULL,NULL,NULL,NULL),(27,'Available from',11,2,NULL,NULL,NULL,NULL,NULL,NULL,'SHOW',NULL,0,0,0,0);
/*!40000 ALTER TABLE `filter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `filter_condition`
--

DROP TABLE IF EXISTS `filter_condition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `filter_condition` (
  `id` int(11) NOT NULL,
  `filter_id` int(11) DEFAULT NULL,
  `search_specific` tinyint(1) DEFAULT NULL,
  `contains_at_least_one` tinyint(1) DEFAULT NULL,
  `contains_all` tinyint(1) DEFAULT NULL,
  `contains_number` tinyint(1) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `selected_number_any` int(11) DEFAULT NULL,
  `min_operand` varchar(10) DEFAULT NULL,
  `max_operand` varchar(10) DEFAULT NULL,
  `min_value` decimal(10,2) DEFAULT NULL,
  `max_value` decimal(10,2) DEFAULT NULL,
  `apply_min_constraint` tinyint(1) DEFAULT NULL,
  `apply_max_constraint` tinyint(1) DEFAULT NULL,
  `apply` tinyint(1) DEFAULT NULL,
  `min_date_value` date DEFAULT NULL,
  `max_date_value` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `filter_condition`
--

LOCK TABLES `filter_condition` WRITE;
/*!40000 ALTER TABLE `filter_condition` DISABLE KEYS */;
INSERT INTO `filter_condition` VALUES (1,7,0,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(2,8,0,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(3,9,0,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `filter_condition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `filter_listing_step`
--

DROP TABLE IF EXISTS `filter_listing_step`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `filter_listing_step` (
  `filter_id` int(11) NOT NULL,
  `sequence_before` varchar(30) DEFAULT NULL,
  `sequence_filter_id` int(11) DEFAULT NULL,
  `sequence_after` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`filter_id`),
  KEY `sequence_filter_id` (`sequence_filter_id`),
  CONSTRAINT `filter_listing_step_ibfk_1` FOREIGN KEY (`filter_id`) REFERENCES `filter` (`id`),
  CONSTRAINT `filter_listing_step_ibfk_2` FOREIGN KEY (`sequence_filter_id`) REFERENCES `filter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `filter_listing_step`
--

LOCK TABLES `filter_listing_step` WRITE;
/*!40000 ALTER TABLE `filter_listing_step` DISABLE KEYS */;
INSERT INTO `filter_listing_step` VALUES (8,NULL,7,NULL),(9,NULL,8,NULL),(10,NULL,9,NULL);
/*!40000 ALTER TABLE `filter_listing_step` ENABLE KEYS */;
UNLOCK TABLES;



DROP TABLE IF EXISTS `filter_showing_step`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `filter_showing_step` (
  `filter_id` int(11) NOT NULL,
  `sequence_before` varchar(30) DEFAULT NULL,
  `filter_condition_id` int(11) DEFAULT NULL,
  `sequence_after` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`filter_id`),
  KEY `filter_condition_id` (`filter_condition_id`),
  CONSTRAINT `filter_showing_step_ibfk_1` FOREIGN KEY (`filter_id`) REFERENCES `filter` (`id`),
  CONSTRAINT `filter_showing_step_ibfk_2` FOREIGN KEY (`filter_condition_id`) REFERENCES `filter_condition` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `filter_showing_step`
--

LOCK TABLES `filter_showing_step` WRITE;
/*!40000 ALTER TABLE `filter_showing_step` DISABLE KEYS */;
INSERT INTO `filter_showing_step` VALUES (8,NULL,1,NULL),(9,NULL,2,NULL),(10,NULL,3,NULL);
/*!40000 ALTER TABLE `filter_showing_step` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `property_field_filter`
--

DROP TABLE IF EXISTS `property_field_filter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `property_field_filter` (
  `filter_id` int(11) NOT NULL,
  `property_field_id` int(11) NOT NULL,
  KEY `filter_id` (`filter_id`),
  KEY `property_field_id` (`property_field_id`),
  CONSTRAINT `property_field_filter_ibfk_1` FOREIGN KEY (`filter_id`) REFERENCES `filter` (`id`),
  CONSTRAINT `property_field_filter_ibfk_2` FOREIGN KEY (`property_field_id`) REFERENCES `property_field` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `property_field_filter`
--

LOCK TABLES `property_field_filter` WRITE;
/*!40000 ALTER TABLE `property_field_filter` DISABLE KEYS */;
INSERT INTO `property_field_filter` VALUES (4,1),(5,5),(6,6),(8,7),(9,8),(10,15),(11,16),(12,17),(13,18),(14,19),(15,20),(16,21),(17,22),(18,23),(19,24),(20,25),(21,26),(22,27),(23,28),(25,30),(27,29);
/*!40000 ALTER TABLE `property_field_filter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `property_field_option`
--

DROP TABLE IF EXISTS `property_field_option`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `property_field_option` (
  `id` int(11) NOT NULL,
  `property_field_id` int(11) NOT NULL,
  `option_id` int(11) NOT NULL,
  `data1` varchar(500) NOT NULL,
  `data2` varchar(500) DEFAULT NULL,
  `data3` varchar(500) DEFAULT NULL,
  `data4` varchar(500) DEFAULT NULL,
  `data5` varchar(500) DEFAULT NULL,
  `data6` varchar(500) DEFAULT NULL,
  `data7` varchar(500) DEFAULT NULL,
  `data8` varchar(500) DEFAULT NULL,
  `extended_data1` text,
  `extended_data2` text,
  PRIMARY KEY (`id`),
  KEY `property_field_id` (`property_field_id`),
  CONSTRAINT `property_field_option_ibfk_1` FOREIGN KEY (`property_field_id`) REFERENCES `property_field` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


COMMIT;