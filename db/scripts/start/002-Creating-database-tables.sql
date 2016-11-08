USE hallocasaapp;START TRANSACTION;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `authorization_code`
--

DROP TABLE IF EXISTS `authorization_code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authorization_code` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `client_id` varchar(100) NOT NULL,
  `auth_code` varchar(200) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `client_id_UNIQUE` (`client_id`),
  UNIQUE KEY `auth_code_UNIQUE` (`auth_code`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `city` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `city_name` varchar(300) NOT NULL,
  `state_id` int(11) NOT NULL,
  `default_lat_coordinate` decimal(5,2) DEFAULT NULL,
  `default_lng_coordinate` decimal(5,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk__city__state` (`state_id`),
  CONSTRAINT `city_ibfk_1` FOREIGN KEY (`state_id`) REFERENCES `state` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1155 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `country` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `country_name` varchar(80) NOT NULL,
  `java_code` varchar(15) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq__country__country_name` (`java_code`),
  UNIQUE KEY `uq__country__java_code` (`country_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `currency`
--

DROP TABLE IF EXISTS `currency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `currency` (
  `id` int(11) NOT NULL,
  `name` varchar(300) DEFAULT NULL,
  `abbreviation` varchar(30) DEFAULT NULL,
  `prefix` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `currency_exchange_data`
--

DROP TABLE IF EXISTS `currency_exchange_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `currency_exchange_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `currency_from_id` int(11) DEFAULT NULL,
  `currency_to_id` int(11) DEFAULT NULL,
  `rate_exchange` float(255,8) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `currency_from_id` (`currency_from_id`),
  KEY `currency_to_id` (`currency_to_id`),
  CONSTRAINT `currency_exchange_data_ibfk_1` FOREIGN KEY (`currency_from_id`) REFERENCES `currency` (`id`),
  CONSTRAINT `currency_exchange_data_ibfk_2` FOREIGN KEY (`currency_to_id`) REFERENCES `currency` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dbmaintain_scripts`
--

DROP TABLE IF EXISTS `dbmaintain_scripts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dbmaintain_scripts` (
  `file_name` varchar(150) DEFAULT NULL,
  `file_last_modified_at` bigint(20) DEFAULT NULL,
  `checksum` varchar(50) DEFAULT NULL,
  `executed_at` varchar(20) DEFAULT NULL,
  `succeeded` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `example`
--

DROP TABLE IF EXISTS `example`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `example` (
  `identifier` int(11) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `profile`
--

DROP TABLE IF EXISTS `profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `profile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `profile_name` varchar(80) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `profile_name_UNIQUE` (`profile_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `profile_use_case`
--

DROP TABLE IF EXISTS `profile_use_case`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `profile_use_case` (
  `profile_id` int(11) NOT NULL,
  `use_case_id` int(11) NOT NULL,
  PRIMARY KEY (`profile_id`,`use_case_id`),
  KEY `fk__profile_use_case__use_case` (`use_case_id`),
  CONSTRAINT `fk__profile_use_case__profile` FOREIGN KEY (`profile_id`) REFERENCES `profile` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk__profile_use_case__use_case` FOREIGN KEY (`use_case_id`) REFERENCES `use_case` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `property`
--

DROP TABLE IF EXISTS `property`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `property` (
  `property_id` varchar(20) NOT NULL,
  `property_type_id` int(11) NOT NULL,
  `property_proposal_id` int(11) NOT NULL,
  `property_location_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `country_id` int(11) NOT NULL,
  PRIMARY KEY (`property_id`),
  KEY `property_type_id` (`property_type_id`),
  KEY `property_proposal_id` (`property_proposal_id`),
  KEY `property_location_id` (`property_location_id`),
  KEY `country_id` (`country_id`),
  CONSTRAINT `property_ibfk_1` FOREIGN KEY (`property_type_id`) REFERENCES `property_type` (`id`),
  CONSTRAINT `property_ibfk_2` FOREIGN KEY (`property_proposal_id`) REFERENCES `property_proposal` (`id`),
  CONSTRAINT `property_ibfk_3` FOREIGN KEY (`property_location_id`) REFERENCES `property_location` (`id`),
  CONSTRAINT `property_ibfk_4` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `property_field`
--

DROP TABLE IF EXISTS `property_field`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `property_field` (
  `id` int(11) NOT NULL,
  `name` varchar(500) DEFAULT NULL,
  `basic` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `property_field_rule`
--

DROP TABLE IF EXISTS `property_field_rule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `property_field_rule` (
  `property_field_id` int(11) NOT NULL,
  `property_type_group_id` int(11) NOT NULL,
  `property_proposal_id` int(11) NOT NULL,
  `property_location_id` int(11) NOT NULL,
  `country_id` int(11) DEFAULT NULL,
  `render_field` char(2) DEFAULT NULL,
  KEY `property_field_id` (`property_field_id`),
  KEY `property_type_group_id` (`property_type_group_id`),
  KEY `property_proposal_id` (`property_proposal_id`),
  KEY `property_location_id` (`property_location_id`),
  KEY `country_id` (`country_id`),
  CONSTRAINT `property_field_rule_ibfk_1` FOREIGN KEY (`property_field_id`) REFERENCES `property_field` (`id`),
  CONSTRAINT `property_field_rule_ibfk_2` FOREIGN KEY (`property_type_group_id`) REFERENCES `property_type_group` (`id`),
  CONSTRAINT `property_field_rule_ibfk_3` FOREIGN KEY (`property_proposal_id`) REFERENCES `property_proposal` (`id`),
  CONSTRAINT `property_field_rule_ibfk_4` FOREIGN KEY (`property_location_id`) REFERENCES `property_location` (`id`),
  CONSTRAINT `property_field_rule_ibfk_5` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `property_field_value`
--

DROP TABLE IF EXISTS `property_field_value`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `property_field_value` (
  `property_id` varchar(20) NOT NULL,
  `property_field_id` int(11) NOT NULL,
  `property_value` varchar(800) DEFAULT NULL,
  PRIMARY KEY (`property_id`,`property_field_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `property_location`
--

DROP TABLE IF EXISTS `property_location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `property_location` (
  `id` int(11) NOT NULL,
  `name` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `property_proposal`
--

DROP TABLE IF EXISTS `property_proposal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `property_proposal` (
  `id` int(11) NOT NULL,
  `name` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `property_selector`
--

DROP TABLE IF EXISTS `property_selector`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `property_selector` (
  `id` int(11) NOT NULL,
  `property_field_id` int(11) NOT NULL,
  `property_selector_type_id` int(11) NOT NULL,
  `range_start` int(11) DEFAULT NULL,
  `range_end` int(11) DEFAULT NULL,
  `exclusions` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `property_field_id` (`property_field_id`),
  KEY `property_selector_type_id` (`property_selector_type_id`),
  CONSTRAINT `property_selector_ibfk_1` FOREIGN KEY (`property_field_id`) REFERENCES `property_field` (`id`),
  CONSTRAINT `property_selector_ibfk_2` FOREIGN KEY (`property_selector_type_id`) REFERENCES `property_selector_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `property_selector_option`
--

DROP TABLE IF EXISTS `property_selector_option`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `property_selector_option` (
  `id` int(11) NOT NULL,
  `property_selector_id` int(11) NOT NULL,
  `selector_value` varchar(300) DEFAULT NULL,
  KEY `property_selector_id` (`property_selector_id`),
  CONSTRAINT `property_selector_option_ibfk_1` FOREIGN KEY (`property_selector_id`) REFERENCES `property_selector` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `property_selector_type`
--

DROP TABLE IF EXISTS `property_selector_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `property_selector_type` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `property_type`
--

DROP TABLE IF EXISTS `property_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `property_type` (
  `id` int(11) NOT NULL,
  `name` varchar(500) DEFAULT NULL,
  `group_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `group_id` (`group_id`),
  CONSTRAINT `property_type_ibfk_1` FOREIGN KEY (`group_id`) REFERENCES `property_type_group` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `property_type_group`
--

DROP TABLE IF EXISTS `property_type_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `property_type_group` (
  `id` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `security_token`
--

DROP TABLE IF EXISTS `security_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `security_token` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `token_value` varchar(100) NOT NULL,
  `registered` datetime NOT NULL,
  `expires_in` bigint(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `state`
--

DROP TABLE IF EXISTS `state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `state` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `state_name` varchar(300) NOT NULL,
  `country_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk__state__country` (`country_id`),
  CONSTRAINT `state_ibfk_1` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `telephone`
--

DROP TABLE IF EXISTS `telephone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `telephone` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(100) NOT NULL,
  `prefix_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `prefix_id` (`prefix_id`),
  CONSTRAINT `telephone_ibfk_1` FOREIGN KEY (`prefix_id`) REFERENCES `telephone_prefix` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `telephone_prefix`
--

DROP TABLE IF EXISTS `telephone_prefix`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `telephone_prefix` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `prefix` int(11) NOT NULL,
  `description` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=208 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `token`
--

DROP TABLE IF EXISTS `token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `token` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_associated` int(11) DEFAULT NULL,
  `token_content` varchar(200) DEFAULT NULL,
  `due_date` datetime DEFAULT NULL,
  `active` tinyint(1) DEFAULT NULL,
  `expedition_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `use_case`
--

DROP TABLE IF EXISTS `use_case`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `use_case` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `use_case_name` varchar(120) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `use_case_name_UNIQUE` (`use_case_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(80) NOT NULL,
  `password` varchar(45) NOT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `web_site` varchar(80) DEFAULT NULL,
  `linked_in` varchar(80) DEFAULT NULL,
  `skype` varchar(45) DEFAULT NULL,
  `language` varchar(10) NOT NULL,
  `confirmed_flag` tinyint(1) NOT NULL DEFAULT '0',
  `user_description` text COMMENT 'JSON (MultilanguageText) with description for each language',
  `spoken_languages` text COMMENT 'JSON with an array of spoken languages',
  `country_id` int(11) DEFAULT NULL,
  `state_id` int(11) DEFAULT NULL,
  `IMAGE_NAME` varchar(100) DEFAULT '/userimage/user0.jpg',
  `city_id` int(11) DEFAULT NULL,
  `main_spoken_language` varchar(10) NOT NULL DEFAULT 'en',
  `user_telephone_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq__user__email` (`email`),
  KEY `fk__user__city` (`city_id`),
  KEY `fk__user__country` (`country_id`),
  KEY `fk__user__state` (`state_id`),
  KEY `fk__user__telephone` (`user_telephone_id`),
  CONSTRAINT `fk__user__city` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`),
  CONSTRAINT `fk__user__country` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`),
  CONSTRAINT `fk__user__state` FOREIGN KEY (`state_id`) REFERENCES `state` (`id`),
  CONSTRAINT `fk__user__telephone` FOREIGN KEY (`user_telephone_id`) REFERENCES `telephone` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_profile`
--

DROP TABLE IF EXISTS `user_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_profile` (
  `user_id` int(11) NOT NULL,
  `profile_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`profile_id`),
  KEY `fk__user_profile__profile_idx` (`profile_id`),
  CONSTRAINT `fk__user_profile__profile` FOREIGN KEY (`profile_id`) REFERENCES `profile` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk__user_profile__user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_type`
--

DROP TABLE IF EXISTS `user_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_type_name` text NOT NULL COMMENT 'JSON multilanguages field',
  `user_type_tooltip` text COMMENT 'JSON multilanguages field',
  `manage_tooltip` tinyint(1) NOT NULL DEFAULT '0',
  `manage_certificate` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_type_profiles`
--

DROP TABLE IF EXISTS `user_type_profiles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_type_profiles` (
  `user_type_id` int(11) NOT NULL,
  `profile_id` int(11) NOT NULL,
  PRIMARY KEY (`user_type_id`,`profile_id`),
  KEY `fk__user_type_profiles__profile` (`profile_id`),
  CONSTRAINT `fk__user_type_profiles__profile` FOREIGN KEY (`profile_id`) REFERENCES `profile` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk__user_type_profiles__user_type` FOREIGN KEY (`user_type_id`) REFERENCES `user_type` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_user_type`
--

DROP TABLE IF EXISTS `user_user_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_user_type` (
  `user_id` int(11) NOT NULL,
  `user_type_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`user_type_id`),
  KEY `fk__user_user_type__user_type` (`user_type_id`),
  CONSTRAINT `fk__user_user_type__user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk__user_user_type__user_type` FOREIGN KEY (`user_type_id`) REFERENCES `user_type` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

COMMIT;