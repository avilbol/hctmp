USE hallocasaappmig;
-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: localhost    Database: hallocasaappmig
-- ------------------------------------------------------
-- Server version	5.6.27-log

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authorization_code`
--

LOCK TABLES `authorization_code` WRITE;
/*!40000 ALTER TABLE `authorization_code` DISABLE KEYS */;
INSERT INTO `authorization_code` VALUES (1,'hallocasa-view','9bd17bd71e7422b9d05ba4c975132bf5'),(2,'hallocasa_davasquez_api_tester','0a33ea311f854b5d6ffffe171224bd80'),(3,'hallocasa_oguerrero_api_tester','7c0e62d2803b65efca67ebeddc2cbaa2'),(4,'hallocasa_avilbol_api_tester','c3262724a345b473cccdba7a883ec8e3');
/*!40000 ALTER TABLE `authorization_code` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES (33,'{\'de\':\'Medell\\u00EDn\',\'en\':\'Medell\\u00EDn\',\'es\':\'Medell\\u00EDn\'}',2,6.24,-75.58),(34,'{\'de\':\'Abejorral\',\'en\':\'Abejorral\',\'es\':\'Abejorral\'}',2,NULL,NULL),(35,'{\'de\':\'Abriaqu\\u00ED\',\'en\':\'Abriaqu\\u00ED\',\'es\':\'Abriaqu\\u00ED\'}',2,NULL,NULL),(36,'{\'de\':\'Alejandr\\u00EDa\',\'en\':\'Alejandr\\u00EDa\',\'es\':\'Alejandr\\u00EDa\'}',2,NULL,NULL),(37,'{\'de\':\'Amag\\u00E1\',\'en\':\'Amag\\u00E1\',\'es\':\'Amag\\u00E1\'}',2,NULL,NULL),(38,'{\'de\':\'Amalfi\',\'en\':\'Amalfi\',\'es\':\'Amalfi\'}',2,NULL,NULL),(39,'{\'de\':\'Andes\',\'en\':\'Andes\',\'es\':\'Andes\'}',2,NULL,NULL),(40,'{\'de\':\'Angel\\u00F3polis\',\'en\':\'Angel\\u00F3polis\',\'es\':\'Angel\\u00F3polis\'}',2,NULL,NULL),(41,'{\'de\':\'Angostura\',\'en\':\'Angostura\',\'es\':\'Angostura\'}',2,NULL,NULL),(42,'{\'de\':\'Anor\\u00ED\',\'en\':\'Anor\\u00ED\',\'es\':\'Anor\\u00ED\'}',2,NULL,NULL),(43,'{\'de\':\'Santa F\\u00E9 De Antioquia\',\'en\':\'Santa F\\u00E9 De Antioquia\',\'es\':\'Santa F\\u00E9 De Antioquia\'}',2,NULL,NULL),(44,'{\'de\':\'Anz\\u00E1\',\'en\':\'Anz\\u00E1\',\'es\':\'Anz\\u00E1\'}',2,NULL,NULL),(45,'{\'de\':\'Apartad\\u00F3\',\'en\':\'Apartad\\u00F3\',\'es\':\'Apartad\\u00F3\'}',2,NULL,NULL),(46,'{\'de\':\'Arboletes\',\'en\':\'Arboletes\',\'es\':\'Arboletes\'}',2,NULL,NULL),(47,'{\'de\':\'Argelia\',\'en\':\'Argelia\',\'es\':\'Argelia\'}',2,NULL,NULL),(48,'{\'de\':\'Armenia\',\'en\':\'Armenia\',\'es\':\'Armenia\'}',2,NULL,NULL),(49,'{\'de\':\'Barbosa\',\'en\':\'Barbosa\',\'es\':\'Barbosa\'}',2,NULL,NULL),(50,'{\'de\':\'Belmira\',\'en\':\'Belmira\',\'es\':\'Belmira\'}',2,NULL,NULL),(51,'{\'de\':\'Bello\',\'en\':\'Bello\',\'es\':\'Bello\'}',2,NULL,NULL),(52,'{\'de\':\'Betania\',\'en\':\'Betania\',\'es\':\'Betania\'}',2,NULL,NULL),(53,'{\'de\':\'Betulia\',\'en\':\'Betulia\',\'es\':\'Betulia\'}',2,NULL,NULL),(54,'{\'de\':\'Ciudad Bol\\u00EDvar\',\'en\':\'Ciudad Bol\\u00EDvar\',\'es\':\'Ciudad Bol\\u00EDvar\'}',2,NULL,NULL),(55,'{\'de\':\'Brice\\u00F1o\',\'en\':\'Brice\\u00F1o\',\'es\':\'Brice\\u00F1o\'}',2,NULL,NULL),(56,'{\'de\':\'Buritic\\u00E1\',\'en\':\'Buritic\\u00E1\',\'es\':\'Buritic\\u00E1\'}',2,NULL,NULL),(57,'{\'de\':\'C\\u00E1ceres\',\'en\':\'C\\u00E1ceres\',\'es\':\'C\\u00E1ceres\'}',2,NULL,NULL),(58,'{\'de\':\'Caicedo\',\'en\':\'Caicedo\',\'es\':\'Caicedo\'}',2,NULL,NULL),(59,'{\'de\':\'Caldas\',\'en\':\'Caldas\',\'es\':\'Caldas\'}',2,NULL,NULL),(60,'{\'de\':\'Campamento\',\'en\':\'Campamento\',\'es\':\'Campamento\'}',2,NULL,NULL),(61,'{\'de\':\'Ca\\u00F1asgordas\',\'en\':\'Ca\\u00F1asgordas\',\'es\':\'Ca\\u00F1asgordas\'}',2,NULL,NULL),(62,'{\'de\':\'Caracol\\u00ED\',\'en\':\'Caracol\\u00ED\',\'es\':\'Caracol\\u00ED\'}',2,NULL,NULL),(63,'{\'de\':\'Caramanta\',\'en\':\'Caramanta\',\'es\':\'Caramanta\'}',2,NULL,NULL),(64,'{\'de\':\'Carepa\',\'en\':\'Carepa\',\'es\':\'Carepa\'}',2,NULL,NULL),(65,'{\'de\':\'El Carmen De Viboral\',\'en\':\'El Carmen De Viboral\',\'es\':\'El Carmen De Viboral\'}',2,NULL,NULL),(66,'{\'de\':\'Carolina\',\'en\':\'Carolina\',\'es\':\'Carolina\'}',2,NULL,NULL),(67,'{\'de\':\'Caucasia\',\'en\':\'Caucasia\',\'es\':\'Caucasia\'}',2,NULL,NULL),(68,'{\'de\':\'Chigorod\\u00F3\',\'en\':\'Chigorod\\u00F3\',\'es\':\'Chigorod\\u00F3\'}',2,NULL,NULL),(69,'{\'de\':\'Cisneros\',\'en\':\'Cisneros\',\'es\':\'Cisneros\'}',2,NULL,NULL),(70,'{\'de\':\'Cocorn\\u00E1\',\'en\':\'Cocorn\\u00E1\',\'es\':\'Cocorn\\u00E1\'}',2,NULL,NULL),(71,'{\'de\':\'Concepci\\u00F3n\',\'en\':\'Concepci\\u00F3n\',\'es\':\'Concepci\\u00F3n\'}',2,NULL,NULL),(72,'{\'de\':\'Concordia\',\'en\':\'Concordia\',\'es\':\'Concordia\'}',2,NULL,NULL),(73,'{\'de\':\'Copacabana\',\'en\':\'Copacabana\',\'es\':\'Copacabana\'}',2,NULL,NULL),(74,'{\'de\':\'Dabeiba\',\'en\':\'Dabeiba\',\'es\':\'Dabeiba\'}',2,NULL,NULL),(75,'{\'de\':\'Donmat\\u00EDas\',\'en\':\'Donmat\\u00EDas\',\'es\':\'Donmat\\u00EDas\'}',2,NULL,NULL),(76,'{\'de\':\'Eb\\u00E9jico\',\'en\':\'Eb\\u00E9jico\',\'es\':\'Eb\\u00E9jico\'}',2,NULL,NULL),(77,'{\'de\':\'El Bagre\',\'en\':\'El Bagre\',\'es\':\'El Bagre\'}',2,NULL,NULL),(78,'{\'de\':\'Entrerr\\u00EDos\',\'en\':\'Entrerr\\u00EDos\',\'es\':\'Entrerr\\u00EDos\'}',2,NULL,NULL),(79,'{\'de\':\'Envigado\',\'en\':\'Envigado\',\'es\':\'Envigado\'}',2,NULL,NULL),(80,'{\'de\':\'Fredonia\',\'en\':\'Fredonia\',\'es\':\'Fredonia\'}',2,NULL,NULL),(81,'{\'de\':\'Frontino\',\'en\':\'Frontino\',\'es\':\'Frontino\'}',2,NULL,NULL),(82,'{\'de\':\'Giraldo\',\'en\':\'Giraldo\',\'es\':\'Giraldo\'}',2,NULL,NULL),(83,'{\'de\':\'Girardota\',\'en\':\'Girardota\',\'es\':\'Girardota\'}',2,NULL,NULL),(84,'{\'de\':\'G\\u00F3mez Plata\',\'en\':\'G\\u00F3mez Plata\',\'es\':\'G\\u00F3mez Plata\'}',2,NULL,NULL),(85,'{\'de\':\'Granada\',\'en\':\'Granada\',\'es\':\'Granada\'}',2,NULL,NULL),(86,'{\'de\':\'Guadalupe\',\'en\':\'Guadalupe\',\'es\':\'Guadalupe\'}',2,NULL,NULL),(87,'{\'de\':\'Guarne\',\'en\':\'Guarne\',\'es\':\'Guarne\'}',2,NULL,NULL),(88,'{\'de\':\'Guatap\\u00E9\',\'en\':\'Guatap\\u00E9\',\'es\':\'Guatap\\u00E9\'}',2,NULL,NULL),(89,'{\'de\':\'Heliconia\',\'en\':\'Heliconia\',\'es\':\'Heliconia\'}',2,NULL,NULL),(90,'{\'de\':\'Hispania\',\'en\':\'Hispania\',\'es\':\'Hispania\'}',2,NULL,NULL),(91,'{\'de\':\'Itag\\u00FC\\u00ED\',\'en\':\'Itag\\u00FC\\u00ED\',\'es\':\'Itag\\u00FC\\u00ED\'}',2,NULL,NULL),(92,'{\'de\':\'Ituango\',\'en\':\'Ituango\',\'es\':\'Ituango\'}',2,NULL,NULL),(93,'{\'de\':\'Jard\\u00EDn\',\'en\':\'Jard\\u00EDn\',\'es\':\'Jard\\u00EDn\'}',2,NULL,NULL),(94,'{\'de\':\'Jeric\\u00F3\',\'en\':\'Jeric\\u00F3\',\'es\':\'Jeric\\u00F3\'}',2,NULL,NULL),(95,'{\'de\':\'La Ceja\',\'en\':\'La Ceja\',\'es\':\'La Ceja\'}',2,NULL,NULL),(96,'{\'de\':\'La Estrella\',\'en\':\'La Estrella\',\'es\':\'La Estrella\'}',2,NULL,NULL),(97,'{\'de\':\'La Pintada\',\'en\':\'La Pintada\',\'es\':\'La Pintada\'}',2,NULL,NULL),(98,'{\'de\':\'La Uni\\u00F3n\',\'en\':\'La Uni\\u00F3n\',\'es\':\'La Uni\\u00F3n\'}',2,NULL,NULL),(99,'{\'de\':\'Liborina\',\'en\':\'Liborina\',\'es\':\'Liborina\'}',2,NULL,NULL),(100,'{\'de\':\'Maceo\',\'en\':\'Maceo\',\'es\':\'Maceo\'}',2,NULL,NULL),(101,'{\'de\':\'Marinilla\',\'en\':\'Marinilla\',\'es\':\'Marinilla\'}',2,NULL,NULL),(102,'{\'de\':\'Montebello\',\'en\':\'Montebello\',\'es\':\'Montebello\'}',2,NULL,NULL),(103,'{\'de\':\'Murind\\u00F3\',\'en\':\'Murind\\u00F3\',\'es\':\'Murind\\u00F3\'}',2,NULL,NULL),(104,'{\'de\':\'Mutat\\u00E1\',\'en\':\'Mutat\\u00E1\',\'es\':\'Mutat\\u00E1\'}',2,NULL,NULL),(105,'{\'de\':\'Nari\\u00F1o\',\'en\':\'Nari\\u00F1o\',\'es\':\'Nari\\u00F1o\'}',2,NULL,NULL),(106,'{\'de\':\'Necocl\\u00ED\',\'en\':\'Necocl\\u00ED\',\'es\':\'Necocl\\u00ED\'}',2,NULL,NULL),(107,'{\'de\':\'Nech\\u00ED\',\'en\':\'Nech\\u00ED\',\'es\':\'Nech\\u00ED\'}',2,NULL,NULL),(108,'{\'de\':\'Olaya\',\'en\':\'Olaya\',\'es\':\'Olaya\'}',2,NULL,NULL),(109,'{\'de\':\'Pe\\u00F1ol\',\'en\':\'Pe\\u00F1ol\',\'es\':\'Pe\\u00F1ol\'}',2,NULL,NULL),(110,'{\'de\':\'Peque\',\'en\':\'Peque\',\'es\':\'Peque\'}',2,NULL,NULL),(111,'{\'de\':\'Pueblorrico\',\'en\':\'Pueblorrico\',\'es\':\'Pueblorrico\'}',2,NULL,NULL),(112,'{\'de\':\'Puerto Berr\\u00EDo\',\'en\':\'Puerto Berr\\u00EDo\',\'es\':\'Puerto Berr\\u00EDo\'}',2,NULL,NULL),(113,'{\'de\':\'Puerto Nare\',\'en\':\'Puerto Nare\',\'es\':\'Puerto Nare\'}',2,NULL,NULL),(114,'{\'de\':\'Puerto Triunfo\',\'en\':\'Puerto Triunfo\',\'es\':\'Puerto Triunfo\'}',2,NULL,NULL),(115,'{\'de\':\'Remedios\',\'en\':\'Remedios\',\'es\':\'Remedios\'}',2,NULL,NULL),(116,'{\'de\':\'Retiro\',\'en\':\'Retiro\',\'es\':\'Retiro\'}',2,NULL,NULL),(117,'{\'de\':\'Rionegro\',\'en\':\'Rionegro\',\'es\':\'Rionegro\'}',2,NULL,NULL),(118,'{\'de\':\'Sabanalarga\',\'en\':\'Sabanalarga\',\'es\':\'Sabanalarga\'}',2,NULL,NULL),(119,'{\'de\':\'Sabaneta\',\'en\':\'Sabaneta\',\'es\':\'Sabaneta\'}',2,NULL,NULL),(120,'{\'de\':\'Salgar\',\'en\':\'Salgar\',\'es\':\'Salgar\'}',2,NULL,NULL),(121,'{\'de\':\'San Andr\\u00E9s De Cuerqu\\u00EDa\',\'en\':\'San Andr\\u00E9s De Cuerqu\\u00EDa\',\'es\':\'San Andr\\u00E9s De Cuerqu\\u00EDa\'}',2,NULL,NULL),(122,'{\'de\':\'San Carlos\',\'en\':\'San Carlos\',\'es\':\'San Carlos\'}',2,NULL,NULL),(123,'{\'de\':\'San Francisco\',\'en\':\'San Francisco\',\'es\':\'San Francisco\'}',2,NULL,NULL),(124,'{\'de\':\'San Jer\\u00F3nimo\',\'en\':\'San Jer\\u00F3nimo\',\'es\':\'San Jer\\u00F3nimo\'}',2,NULL,NULL),(125,'{\'de\':\'San Jos\\u00E9 De La Monta\\u00F1a\',\'en\':\'San Jos\\u00E9 De La Monta\\u00F1a\',\'es\':\'San Jos\\u00E9 De La Monta\\u00F1a\'}',2,NULL,NULL),(126,'{\'de\':\'San Juan De Urab\\u00E1\',\'en\':\'San Juan De Urab\\u00E1\',\'es\':\'San Juan De Urab\\u00E1\'}',2,NULL,NULL),(127,'{\'de\':\'San Luis\',\'en\':\'San Luis\',\'es\':\'San Luis\'}',2,NULL,NULL),(128,'{\'de\':\'San Pedro De Los Milagros\',\'en\':\'San Pedro De Los Milagros\',\'es\':\'San Pedro De Los Milagros\'}',2,NULL,NULL),(129,'{\'de\':\'San Pedro De Urab\\u00E1\',\'en\':\'San Pedro De Urab\\u00E1\',\'es\':\'San Pedro De Urab\\u00E1\'}',2,NULL,NULL),(130,'{\'de\':\'San Rafael\',\'en\':\'San Rafael\',\'es\':\'San Rafael\'}',2,NULL,NULL),(131,'{\'de\':\'San Roque\',\'en\':\'San Roque\',\'es\':\'San Roque\'}',2,NULL,NULL),(132,'{\'de\':\'San Vicente Ferrer\',\'en\':\'San Vicente Ferrer\',\'es\':\'San Vicente Ferrer\'}',2,NULL,NULL),(133,'{\'de\':\'Santa B\\u00E1rbara\',\'en\':\'Santa B\\u00E1rbara\',\'es\':\'Santa B\\u00E1rbara\'}',2,NULL,NULL),(134,'{\'de\':\'Santa Rosa De Osos\',\'en\':\'Santa Rosa De Osos\',\'es\':\'Santa Rosa De Osos\'}',2,NULL,NULL),(135,'{\'de\':\'Santo Domingo\',\'en\':\'Santo Domingo\',\'es\':\'Santo Domingo\'}',2,NULL,NULL),(136,'{\'de\':\'El Santuario\',\'en\':\'El Santuario\',\'es\':\'El Santuario\'}',2,NULL,NULL),(137,'{\'de\':\'Segovia\',\'en\':\'Segovia\',\'es\':\'Segovia\'}',2,NULL,NULL),(138,'{\'de\':\'Sons\\u00F3n\',\'en\':\'Sons\\u00F3n\',\'es\':\'Sons\\u00F3n\'}',2,NULL,NULL),(139,'{\'de\':\'Sopetr\\u00E1n\',\'en\':\'Sopetr\\u00E1n\',\'es\':\'Sopetr\\u00E1n\'}',2,NULL,NULL),(140,'{\'de\':\'T\\u00E1mesis\',\'en\':\'T\\u00E1mesis\',\'es\':\'T\\u00E1mesis\'}',2,NULL,NULL),(141,'{\'de\':\'Taraz\\u00E1\',\'en\':\'Taraz\\u00E1\',\'es\':\'Taraz\\u00E1\'}',2,NULL,NULL),(142,'{\'de\':\'Tarso\',\'en\':\'Tarso\',\'es\':\'Tarso\'}',2,NULL,NULL),(143,'{\'de\':\'Titirib\\u00ED\',\'en\':\'Titirib\\u00ED\',\'es\':\'Titirib\\u00ED\'}',2,NULL,NULL),(144,'{\'de\':\'Toledo\',\'en\':\'Toledo\',\'es\':\'Toledo\'}',2,NULL,NULL),(145,'{\'de\':\'Turbo\',\'en\':\'Turbo\',\'es\':\'Turbo\'}',2,NULL,NULL),(146,'{\'de\':\'Uramita\',\'en\':\'Uramita\',\'es\':\'Uramita\'}',2,NULL,NULL),(147,'{\'de\':\'Urrao\',\'en\':\'Urrao\',\'es\':\'Urrao\'}',2,NULL,NULL),(148,'{\'de\':\'Valdivia\',\'en\':\'Valdivia\',\'es\':\'Valdivia\'}',2,NULL,NULL),(149,'{\'de\':\'Valpara\\u00EDso\',\'en\':\'Valpara\\u00EDso\',\'es\':\'Valpara\\u00EDso\'}',2,NULL,NULL),(150,'{\'de\':\'Vegach\\u00ED\',\'en\':\'Vegach\\u00ED\',\'es\':\'Vegach\\u00ED\'}',2,NULL,NULL),(151,'{\'de\':\'Venecia\',\'en\':\'Venecia\',\'es\':\'Venecia\'}',2,NULL,NULL),(152,'{\'de\':\'Vig\\u00EDa Del Fuerte\',\'en\':\'Vig\\u00EDa Del Fuerte\',\'es\':\'Vig\\u00EDa Del Fuerte\'}',2,NULL,NULL),(153,'{\'de\':\'Yal\\u00ED\',\'en\':\'Yal\\u00ED\',\'es\':\'Yal\\u00ED\'}',2,NULL,NULL),(154,'{\'de\':\'Yarumal\',\'en\':\'Yarumal\',\'es\':\'Yarumal\'}',2,NULL,NULL),(155,'{\'de\':\'Yolomb\\u00F3\',\'en\':\'Yolomb\\u00F3\',\'es\':\'Yolomb\\u00F3\'}',2,NULL,NULL),(156,'{\'de\':\'Yond\\u00F3\',\'en\':\'Yond\\u00F3\',\'es\':\'Yond\\u00F3\'}',2,NULL,NULL),(157,'{\'de\':\'Zaragoza\',\'en\':\'Zaragoza\',\'es\':\'Zaragoza\'}',2,NULL,NULL),(158,'{\'de\':\'Barranquilla\',\'en\':\'Barranquilla\',\'es\':\'Barranquilla\'}',4,10.96,-74.79),(159,'{\'de\':\'Baranoa\',\'en\':\'Baranoa\',\'es\':\'Baranoa\'}',4,NULL,NULL),(160,'{\'de\':\'Campo De La Cruz\',\'en\':\'Campo De La Cruz\',\'es\':\'Campo De La Cruz\'}',4,NULL,NULL),(161,'{\'de\':\'Candelaria\',\'en\':\'Candelaria\',\'es\':\'Candelaria\'}',4,NULL,NULL),(162,'{\'de\':\'Galapa\',\'en\':\'Galapa\',\'es\':\'Galapa\'}',4,NULL,NULL),(163,'{\'de\':\'Juan De Acosta\',\'en\':\'Juan De Acosta\',\'es\':\'Juan De Acosta\'}',4,NULL,NULL),(164,'{\'de\':\'Luruaco\',\'en\':\'Luruaco\',\'es\':\'Luruaco\'}',4,NULL,NULL),(165,'{\'de\':\'Malambo\',\'en\':\'Malambo\',\'es\':\'Malambo\'}',4,NULL,NULL),(166,'{\'de\':\'Manat\\u00ED\',\'en\':\'Manat\\u00ED\',\'es\':\'Manat\\u00ED\'}',4,NULL,NULL),(167,'{\'de\':\'Palmar De Varela\',\'en\':\'Palmar De Varela\',\'es\':\'Palmar De Varela\'}',4,NULL,NULL),(168,'{\'de\':\'Pioj\\u00F3\',\'en\':\'Pioj\\u00F3\',\'es\':\'Pioj\\u00F3\'}',4,NULL,NULL),(169,'{\'de\':\'Polonuevo\',\'en\':\'Polonuevo\',\'es\':\'Polonuevo\'}',4,NULL,NULL),(170,'{\'de\':\'Ponedera\',\'en\':\'Ponedera\',\'es\':\'Ponedera\'}',4,NULL,NULL),(171,'{\'de\':\'Puerto Colombia\',\'en\':\'Puerto Colombia\',\'es\':\'Puerto Colombia\'}',4,NULL,NULL),(172,'{\'de\':\'Repel\\u00F3n\',\'en\':\'Repel\\u00F3n\',\'es\':\'Repel\\u00F3n\'}',4,NULL,NULL),(173,'{\'de\':\'Sabanagrande\',\'en\':\'Sabanagrande\',\'es\':\'Sabanagrande\'}',4,NULL,NULL),(174,'{\'de\':\'Sabanalarga\',\'en\':\'Sabanalarga\',\'es\':\'Sabanalarga\'}',4,NULL,NULL),(175,'{\'de\':\'Santa Luc\\u00EDa\',\'en\':\'Santa Luc\\u00EDa\',\'es\':\'Santa Luc\\u00EDa\'}',4,NULL,NULL),(176,'{\'de\':\'Santo Tom\\u00E1s\',\'en\':\'Santo Tom\\u00E1s\',\'es\':\'Santo Tom\\u00E1s\'}',4,NULL,NULL),(177,'{\'de\':\'Soledad\',\'en\':\'Soledad\',\'es\':\'Soledad\'}',4,NULL,NULL),(178,'{\'de\':\'Suan\',\'en\':\'Suan\',\'es\':\'Suan\'}',4,NULL,NULL),(179,'{\'de\':\'Tubar\\u00E1\',\'en\':\'Tubar\\u00E1\',\'es\':\'Tubar\\u00E1\'}',4,NULL,NULL),(180,'{\'de\':\'Usiacur\\u00ED\',\'en\':\'Usiacur\\u00ED\',\'es\':\'Usiacur\\u00ED\'}',4,NULL,NULL),(181,'{\'de\':\'Bogot\\u00E1, D.C.\',\'en\':\'Bogot\\u00E1, D.C.\',\'es\':\'Bogot\\u00E1, D.C.\'}',5,4.65,-74.05),(182,'{\'de\':\'Cartagena De Indias\',\'en\':\'Cartagena De Indias\',\'es\':\'Cartagena De Indias\'}',6,NULL,NULL),(183,'{\'de\':\'Ach\\u00ED\',\'en\':\'Ach\\u00ED\',\'es\':\'Ach\\u00ED\'}',6,NULL,NULL),(184,'{\'de\':\'Altos Del Rosario\',\'en\':\'Altos Del Rosario\',\'es\':\'Altos Del Rosario\'}',6,NULL,NULL),(185,'{\'de\':\'Arenal\',\'en\':\'Arenal\',\'es\':\'Arenal\'}',6,NULL,NULL),(186,'{\'de\':\'Arjona\',\'en\':\'Arjona\',\'es\':\'Arjona\'}',6,NULL,NULL),(187,'{\'de\':\'Arroyohondo\',\'en\':\'Arroyohondo\',\'es\':\'Arroyohondo\'}',6,NULL,NULL),(188,'{\'de\':\'Barranco De Loba\',\'en\':\'Barranco De Loba\',\'es\':\'Barranco De Loba\'}',6,NULL,NULL),(189,'{\'de\':\'Calamar\',\'en\':\'Calamar\',\'es\':\'Calamar\'}',6,NULL,NULL),(190,'{\'de\':\'Cantagallo\',\'en\':\'Cantagallo\',\'es\':\'Cantagallo\'}',6,NULL,NULL),(191,'{\'de\':\'Cicuco\',\'en\':\'Cicuco\',\'es\':\'Cicuco\'}',6,NULL,NULL),(192,'{\'de\':\'C\\u00F3rdoba\',\'en\':\'C\\u00F3rdoba\',\'es\':\'C\\u00F3rdoba\'}',6,NULL,NULL),(193,'{\'de\':\'Clemencia\',\'en\':\'Clemencia\',\'es\':\'Clemencia\'}',6,NULL,NULL),(194,'{\'de\':\'El Carmen De Bol\\u00EDvar\',\'en\':\'El Carmen De Bol\\u00EDvar\',\'es\':\'El Carmen De Bol\\u00EDvar\'}',6,NULL,NULL),(195,'{\'de\':\'El Guamo\',\'en\':\'El Guamo\',\'es\':\'El Guamo\'}',6,NULL,NULL),(196,'{\'de\':\'El Pe\\u00F1\\u00F3n\',\'en\':\'El Pe\\u00F1\\u00F3n\',\'es\':\'El Pe\\u00F1\\u00F3n\'}',6,NULL,NULL),(197,'{\'de\':\'Hatillo De Loba\',\'en\':\'Hatillo De Loba\',\'es\':\'Hatillo De Loba\'}',6,NULL,NULL),(198,'{\'de\':\'Magangu\\u00E9\',\'en\':\'Magangu\\u00E9\',\'es\':\'Magangu\\u00E9\'}',6,NULL,NULL),(199,'{\'de\':\'Mahates\',\'en\':\'Mahates\',\'es\':\'Mahates\'}',6,NULL,NULL),(200,'{\'de\':\'Margarita\',\'en\':\'Margarita\',\'es\':\'Margarita\'}',6,NULL,NULL),(201,'{\'de\':\'Mar\\u00EDa La Baja\',\'en\':\'Mar\\u00EDa La Baja\',\'es\':\'Mar\\u00EDa La Baja\'}',6,NULL,NULL),(202,'{\'de\':\'Montecristo\',\'en\':\'Montecristo\',\'es\':\'Montecristo\'}',6,NULL,NULL),(203,'{\'de\':\'Momp\\u00F3s\',\'en\':\'Momp\\u00F3s\',\'es\':\'Momp\\u00F3s\'}',6,NULL,NULL),(204,'{\'de\':\'Morales\',\'en\':\'Morales\',\'es\':\'Morales\'}',6,NULL,NULL),(205,'{\'de\':\'Noros\\u00ED\',\'en\':\'Noros\\u00ED\',\'es\':\'Noros\\u00ED\'}',6,NULL,NULL),(206,'{\'de\':\'Pinillos\',\'en\':\'Pinillos\',\'es\':\'Pinillos\'}',6,NULL,NULL),(207,'{\'de\':\'Regidor\',\'en\':\'Regidor\',\'es\':\'Regidor\'}',6,NULL,NULL),(208,'{\'de\':\'R\\u00EDo Viejo\',\'en\':\'R\\u00EDo Viejo\',\'es\':\'R\\u00EDo Viejo\'}',6,NULL,NULL),(209,'{\'de\':\'San Crist\\u00F3bal\',\'en\':\'San Crist\\u00F3bal\',\'es\':\'San Crist\\u00F3bal\'}',6,NULL,NULL),(210,'{\'de\':\'San Estanislao\',\'en\':\'San Estanislao\',\'es\':\'San Estanislao\'}',6,NULL,NULL),(211,'{\'de\':\'San Fernando\',\'en\':\'San Fernando\',\'es\':\'San Fernando\'}',6,NULL,NULL),(212,'{\'de\':\'San Jacinto\',\'en\':\'San Jacinto\',\'es\':\'San Jacinto\'}',6,NULL,NULL),(213,'{\'de\':\'San Jacinto Del Cauca\',\'en\':\'San Jacinto Del Cauca\',\'es\':\'San Jacinto Del Cauca\'}',6,NULL,NULL),(214,'{\'de\':\'San Juan Nepomuceno\',\'en\':\'San Juan Nepomuceno\',\'es\':\'San Juan Nepomuceno\'}',6,NULL,NULL),(215,'{\'de\':\'San Mart\\u00EDn De Loba\',\'en\':\'San Mart\\u00EDn De Loba\',\'es\':\'San Mart\\u00EDn De Loba\'}',6,NULL,NULL),(216,'{\'de\':\'San Pablo\',\'en\':\'San Pablo\',\'es\':\'San Pablo\'}',6,NULL,NULL),(217,'{\'de\':\'Santa Catalina\',\'en\':\'Santa Catalina\',\'es\':\'Santa Catalina\'}',6,NULL,NULL),(218,'{\'de\':\'Santa Rosa\',\'en\':\'Santa Rosa\',\'es\':\'Santa Rosa\'}',6,NULL,NULL),(219,'{\'de\':\'Santa Rosa Del Sur\',\'en\':\'Santa Rosa Del Sur\',\'es\':\'Santa Rosa Del Sur\'}',6,NULL,NULL),(220,'{\'de\':\'Simit\\u00ED\',\'en\':\'Simit\\u00ED\',\'es\':\'Simit\\u00ED\'}',6,NULL,NULL),(221,'{\'de\':\'Soplaviento\',\'en\':\'Soplaviento\',\'es\':\'Soplaviento\'}',6,NULL,NULL),(222,'{\'de\':\'Talaigua Nuevo\',\'en\':\'Talaigua Nuevo\',\'es\':\'Talaigua Nuevo\'}',6,NULL,NULL),(223,'{\'de\':\'Tiquisio\',\'en\':\'Tiquisio\',\'es\':\'Tiquisio\'}',6,NULL,NULL),(224,'{\'de\':\'Turbaco\',\'en\':\'Turbaco\',\'es\':\'Turbaco\'}',6,NULL,NULL),(225,'{\'de\':\'Turban\\u00E1\',\'en\':\'Turban\\u00E1\',\'es\':\'Turban\\u00E1\'}',6,NULL,NULL),(226,'{\'de\':\'Villanueva\',\'en\':\'Villanueva\',\'es\':\'Villanueva\'}',6,NULL,NULL),(227,'{\'de\':\'Zambrano\',\'en\':\'Zambrano\',\'es\':\'Zambrano\'}',6,NULL,NULL),(228,'{\'de\':\'Tunja\',\'en\':\'Tunja\',\'es\':\'Tunja\'}',7,NULL,NULL),(229,'{\'de\':\'Almeida\',\'en\':\'Almeida\',\'es\':\'Almeida\'}',7,NULL,NULL),(230,'{\'de\':\'Aquitania\',\'en\':\'Aquitania\',\'es\':\'Aquitania\'}',7,NULL,NULL),(231,'{\'de\':\'Arcabuco\',\'en\':\'Arcabuco\',\'es\':\'Arcabuco\'}',7,NULL,NULL),(232,'{\'de\':\'Bel\\u00E9n\',\'en\':\'Bel\\u00E9n\',\'es\':\'Bel\\u00E9n\'}',7,NULL,NULL),(233,'{\'de\':\'Berbeo\',\'en\':\'Berbeo\',\'es\':\'Berbeo\'}',7,NULL,NULL),(234,'{\'de\':\'Bet\\u00E9itiva\',\'en\':\'Bet\\u00E9itiva\',\'es\':\'Bet\\u00E9itiva\'}',7,NULL,NULL),(235,'{\'de\':\'Boavita\',\'en\':\'Boavita\',\'es\':\'Boavita\'}',7,NULL,NULL),(236,'{\'de\':\'Boyac\\u00E1\',\'en\':\'Boyac\\u00E1\',\'es\':\'Boyac\\u00E1\'}',7,NULL,NULL),(237,'{\'de\':\'Brice\\u00F1o\',\'en\':\'Brice\\u00F1o\',\'es\':\'Brice\\u00F1o\'}',7,NULL,NULL),(238,'{\'de\':\'Buenavista\',\'en\':\'Buenavista\',\'es\':\'Buenavista\'}',7,NULL,NULL),(239,'{\'de\':\'Busbanz\\u00E1\',\'en\':\'Busbanz\\u00E1\',\'es\':\'Busbanz\\u00E1\'}',7,NULL,NULL),(240,'{\'de\':\'Caldas\',\'en\':\'Caldas\',\'es\':\'Caldas\'}',7,NULL,NULL),(241,'{\'de\':\'Campohermoso\',\'en\':\'Campohermoso\',\'es\':\'Campohermoso\'}',7,NULL,NULL),(242,'{\'de\':\'Cerinza\',\'en\':\'Cerinza\',\'es\':\'Cerinza\'}',7,NULL,NULL),(243,'{\'de\':\'Chinavita\',\'en\':\'Chinavita\',\'es\':\'Chinavita\'}',7,NULL,NULL),(244,'{\'de\':\'Chiquinquir\\u00E1\',\'en\':\'Chiquinquir\\u00E1\',\'es\':\'Chiquinquir\\u00E1\'}',7,NULL,NULL),(245,'{\'de\':\'Chiscas\',\'en\':\'Chiscas\',\'es\':\'Chiscas\'}',7,NULL,NULL),(246,'{\'de\':\'Chita\',\'en\':\'Chita\',\'es\':\'Chita\'}',7,NULL,NULL),(247,'{\'de\':\'Chitaraque\',\'en\':\'Chitaraque\',\'es\':\'Chitaraque\'}',7,NULL,NULL),(248,'{\'de\':\'Chivat\\u00E1\',\'en\':\'Chivat\\u00E1\',\'es\':\'Chivat\\u00E1\'}',7,NULL,NULL),(249,'{\'de\':\'Ci\\u00E9nega\',\'en\':\'Ci\\u00E9nega\',\'es\':\'Ci\\u00E9nega\'}',7,NULL,NULL),(250,'{\'de\':\'C\\u00F3mbita\',\'en\':\'C\\u00F3mbita\',\'es\':\'C\\u00F3mbita\'}',7,NULL,NULL),(251,'{\'de\':\'Coper\',\'en\':\'Coper\',\'es\':\'Coper\'}',7,NULL,NULL),(252,'{\'de\':\'Corrales\',\'en\':\'Corrales\',\'es\':\'Corrales\'}',7,NULL,NULL),(253,'{\'de\':\'Covarach\\u00EDa\',\'en\':\'Covarach\\u00EDa\',\'es\':\'Covarach\\u00EDa\'}',7,NULL,NULL),(254,'{\'de\':\'Cubar\\u00E1\',\'en\':\'Cubar\\u00E1\',\'es\':\'Cubar\\u00E1\'}',7,NULL,NULL),(255,'{\'de\':\'Cucaita\',\'en\':\'Cucaita\',\'es\':\'Cucaita\'}',7,NULL,NULL),(256,'{\'de\':\'Cu\\u00EDtiva\',\'en\':\'Cu\\u00EDtiva\',\'es\':\'Cu\\u00EDtiva\'}',7,NULL,NULL),(257,'{\'de\':\'Ch\\u00EDquiza\',\'en\':\'Ch\\u00EDquiza\',\'es\':\'Ch\\u00EDquiza\'}',7,NULL,NULL),(258,'{\'de\':\'Chivor\',\'en\':\'Chivor\',\'es\':\'Chivor\'}',7,NULL,NULL),(259,'{\'de\':\'Duitama\',\'en\':\'Duitama\',\'es\':\'Duitama\'}',7,NULL,NULL),(260,'{\'de\':\'El Cocuy\',\'en\':\'El Cocuy\',\'es\':\'El Cocuy\'}',7,NULL,NULL),(261,'{\'de\':\'El Espino\',\'en\':\'El Espino\',\'es\':\'El Espino\'}',7,NULL,NULL),(262,'{\'de\':\'Firavitoba\',\'en\':\'Firavitoba\',\'es\':\'Firavitoba\'}',7,NULL,NULL),(263,'{\'de\':\'Floresta\',\'en\':\'Floresta\',\'es\':\'Floresta\'}',7,NULL,NULL),(264,'{\'de\':\'Gachantiv\\u00E1\',\'en\':\'Gachantiv\\u00E1\',\'es\':\'Gachantiv\\u00E1\'}',7,NULL,NULL),(265,'{\'de\':\'G\\u00E1meza\',\'en\':\'G\\u00E1meza\',\'es\':\'G\\u00E1meza\'}',7,NULL,NULL),(266,'{\'de\':\'Garagoa\',\'en\':\'Garagoa\',\'es\':\'Garagoa\'}',7,NULL,NULL),(267,'{\'de\':\'Guacamayas\',\'en\':\'Guacamayas\',\'es\':\'Guacamayas\'}',7,NULL,NULL),(268,'{\'de\':\'Guateque\',\'en\':\'Guateque\',\'es\':\'Guateque\'}',7,NULL,NULL),(269,'{\'de\':\'Guayat\\u00E1\',\'en\':\'Guayat\\u00E1\',\'es\':\'Guayat\\u00E1\'}',7,NULL,NULL),(270,'{\'de\':\'G\\u00FCic\\u00E1n\',\'en\':\'G\\u00FCic\\u00E1n\',\'es\':\'G\\u00FCic\\u00E1n\'}',7,NULL,NULL),(271,'{\'de\':\'Iza\',\'en\':\'Iza\',\'es\':\'Iza\'}',7,NULL,NULL),(272,'{\'de\':\'Jenesano\',\'en\':\'Jenesano\',\'es\':\'Jenesano\'}',7,NULL,NULL),(273,'{\'de\':\'Jeric\\u00F3\',\'en\':\'Jeric\\u00F3\',\'es\':\'Jeric\\u00F3\'}',7,NULL,NULL),(274,'{\'de\':\'Labranzagrande\',\'en\':\'Labranzagrande\',\'es\':\'Labranzagrande\'}',7,NULL,NULL),(275,'{\'de\':\'La Capilla\',\'en\':\'La Capilla\',\'es\':\'La Capilla\'}',7,NULL,NULL),(276,'{\'de\':\'La Victoria\',\'en\':\'La Victoria\',\'es\':\'La Victoria\'}',7,NULL,NULL),(277,'{\'de\':\'La Uvita\',\'en\':\'La Uvita\',\'es\':\'La Uvita\'}',7,NULL,NULL),(278,'{\'de\':\'Villa De Leyva\',\'en\':\'Villa De Leyva\',\'es\':\'Villa De Leyva\'}',7,NULL,NULL),(279,'{\'de\':\'Macanal\',\'en\':\'Macanal\',\'es\':\'Macanal\'}',7,NULL,NULL),(280,'{\'de\':\'Marip\\u00ED\',\'en\':\'Marip\\u00ED\',\'es\':\'Marip\\u00ED\'}',7,NULL,NULL),(281,'{\'de\':\'Miraflores\',\'en\':\'Miraflores\',\'es\':\'Miraflores\'}',7,NULL,NULL),(282,'{\'de\':\'Mongua\',\'en\':\'Mongua\',\'es\':\'Mongua\'}',7,NULL,NULL),(283,'{\'de\':\'Mongu\\u00ED\',\'en\':\'Mongu\\u00ED\',\'es\':\'Mongu\\u00ED\'}',7,NULL,NULL),(284,'{\'de\':\'Moniquir\\u00E1\',\'en\':\'Moniquir\\u00E1\',\'es\':\'Moniquir\\u00E1\'}',7,NULL,NULL),(285,'{\'de\':\'Motavita\',\'en\':\'Motavita\',\'es\':\'Motavita\'}',7,NULL,NULL),(286,'{\'de\':\'Muzo\',\'en\':\'Muzo\',\'es\':\'Muzo\'}',7,NULL,NULL),(287,'{\'de\':\'Nobsa\',\'en\':\'Nobsa\',\'es\':\'Nobsa\'}',7,NULL,NULL),(288,'{\'de\':\'Nuevo Col\\u00F3n\',\'en\':\'Nuevo Col\\u00F3n\',\'es\':\'Nuevo Col\\u00F3n\'}',7,NULL,NULL),(289,'{\'de\':\'Oicat\\u00E1\',\'en\':\'Oicat\\u00E1\',\'es\':\'Oicat\\u00E1\'}',7,NULL,NULL),(290,'{\'de\':\'Otanche\',\'en\':\'Otanche\',\'es\':\'Otanche\'}',7,NULL,NULL),(291,'{\'de\':\'Pachavita\',\'en\':\'Pachavita\',\'es\':\'Pachavita\'}',7,NULL,NULL),(292,'{\'de\':\'P\\u00E1ez\',\'en\':\'P\\u00E1ez\',\'es\':\'P\\u00E1ez\'}',7,NULL,NULL),(293,'{\'de\':\'Paipa\',\'en\':\'Paipa\',\'es\':\'Paipa\'}',7,NULL,NULL),(294,'{\'de\':\'Pajarito\',\'en\':\'Pajarito\',\'es\':\'Pajarito\'}',7,NULL,NULL),(295,'{\'de\':\'Panqueba\',\'en\':\'Panqueba\',\'es\':\'Panqueba\'}',7,NULL,NULL),(296,'{\'de\':\'Pauna\',\'en\':\'Pauna\',\'es\':\'Pauna\'}',7,NULL,NULL),(297,'{\'de\':\'Paya\',\'en\':\'Paya\',\'es\':\'Paya\'}',7,NULL,NULL),(298,'{\'de\':\'Paz De R\\u00EDo\',\'en\':\'Paz De R\\u00EDo\',\'es\':\'Paz De R\\u00EDo\'}',7,NULL,NULL),(299,'{\'de\':\'Pesca\',\'en\':\'Pesca\',\'es\':\'Pesca\'}',7,NULL,NULL),(300,'{\'de\':\'Pisba\',\'en\':\'Pisba\',\'es\':\'Pisba\'}',7,NULL,NULL),(301,'{\'de\':\'Puerto Boyac\\u00E1\',\'en\':\'Puerto Boyac\\u00E1\',\'es\':\'Puerto Boyac\\u00E1\'}',7,NULL,NULL),(302,'{\'de\':\'Qu\\u00EDpama\',\'en\':\'Qu\\u00EDpama\',\'es\':\'Qu\\u00EDpama\'}',7,NULL,NULL),(303,'{\'de\':\'Ramiriqu\\u00ED\',\'en\':\'Ramiriqu\\u00ED\',\'es\':\'Ramiriqu\\u00ED\'}',7,NULL,NULL),(304,'{\'de\':\'R\\u00E1quira\',\'en\':\'R\\u00E1quira\',\'es\':\'R\\u00E1quira\'}',7,NULL,NULL),(305,'{\'de\':\'Rond\\u00F3n\',\'en\':\'Rond\\u00F3n\',\'es\':\'Rond\\u00F3n\'}',7,NULL,NULL),(306,'{\'de\':\'Saboy\\u00E1\',\'en\':\'Saboy\\u00E1\',\'es\':\'Saboy\\u00E1\'}',7,NULL,NULL),(307,'{\'de\':\'S\\u00E1chica\',\'en\':\'S\\u00E1chica\',\'es\':\'S\\u00E1chica\'}',7,NULL,NULL),(308,'{\'de\':\'Samac\\u00E1\',\'en\':\'Samac\\u00E1\',\'es\':\'Samac\\u00E1\'}',7,NULL,NULL),(309,'{\'de\':\'San Eduardo\',\'en\':\'San Eduardo\',\'es\':\'San Eduardo\'}',7,NULL,NULL),(310,'{\'de\':\'San Jos\\u00E9 De Pare\',\'en\':\'San Jos\\u00E9 De Pare\',\'es\':\'San Jos\\u00E9 De Pare\'}',7,NULL,NULL),(311,'{\'de\':\'San Luis De Gaceno\',\'en\':\'San Luis De Gaceno\',\'es\':\'San Luis De Gaceno\'}',7,NULL,NULL),(312,'{\'de\':\'San Mateo\',\'en\':\'San Mateo\',\'es\':\'San Mateo\'}',7,NULL,NULL),(313,'{\'de\':\'San Miguel De Sema\',\'en\':\'San Miguel De Sema\',\'es\':\'San Miguel De Sema\'}',7,NULL,NULL),(314,'{\'de\':\'San Pablo De Borbur\',\'en\':\'San Pablo De Borbur\',\'es\':\'San Pablo De Borbur\'}',7,NULL,NULL),(315,'{\'de\':\'Santana\',\'en\':\'Santana\',\'es\':\'Santana\'}',7,NULL,NULL),(316,'{\'de\':\'Santa Mar\\u00EDa\',\'en\':\'Santa Mar\\u00EDa\',\'es\':\'Santa Mar\\u00EDa\'}',7,NULL,NULL),(317,'{\'de\':\'Santa Rosa De Viterbo\',\'en\':\'Santa Rosa De Viterbo\',\'es\':\'Santa Rosa De Viterbo\'}',7,NULL,NULL),(318,'{\'de\':\'Santa Sof\\u00EDa\',\'en\':\'Santa Sof\\u00EDa\',\'es\':\'Santa Sof\\u00EDa\'}',7,NULL,NULL),(319,'{\'de\':\'Sativanorte\',\'en\':\'Sativanorte\',\'es\':\'Sativanorte\'}',7,NULL,NULL),(320,'{\'de\':\'Sativasur\',\'en\':\'Sativasur\',\'es\':\'Sativasur\'}',7,NULL,NULL),(321,'{\'de\':\'Siachoque\',\'en\':\'Siachoque\',\'es\':\'Siachoque\'}',7,NULL,NULL),(322,'{\'de\':\'Soat\\u00E1\',\'en\':\'Soat\\u00E1\',\'es\':\'Soat\\u00E1\'}',7,NULL,NULL),(323,'{\'de\':\'Socot\\u00E1\',\'en\':\'Socot\\u00E1\',\'es\':\'Socot\\u00E1\'}',7,NULL,NULL),(324,'{\'de\':\'Socha\',\'en\':\'Socha\',\'es\':\'Socha\'}',7,NULL,NULL),(325,'{\'de\':\'Sogamoso\',\'en\':\'Sogamoso\',\'es\':\'Sogamoso\'}',7,NULL,NULL),(326,'{\'de\':\'Somondoco\',\'en\':\'Somondoco\',\'es\':\'Somondoco\'}',7,NULL,NULL),(327,'{\'de\':\'Sora\',\'en\':\'Sora\',\'es\':\'Sora\'}',7,NULL,NULL),(328,'{\'de\':\'Sotaquir\\u00E1\',\'en\':\'Sotaquir\\u00E1\',\'es\':\'Sotaquir\\u00E1\'}',7,NULL,NULL),(329,'{\'de\':\'Sorac\\u00E1\',\'en\':\'Sorac\\u00E1\',\'es\':\'Sorac\\u00E1\'}',7,NULL,NULL),(330,'{\'de\':\'Susac\\u00F3n\',\'en\':\'Susac\\u00F3n\',\'es\':\'Susac\\u00F3n\'}',7,NULL,NULL),(331,'{\'de\':\'Sutamarch\\u00E1n\',\'en\':\'Sutamarch\\u00E1n\',\'es\':\'Sutamarch\\u00E1n\'}',7,NULL,NULL),(332,'{\'de\':\'Sutatenza\',\'en\':\'Sutatenza\',\'es\':\'Sutatenza\'}',7,NULL,NULL),(333,'{\'de\':\'Tasco\',\'en\':\'Tasco\',\'es\':\'Tasco\'}',7,NULL,NULL),(334,'{\'de\':\'Tenza\',\'en\':\'Tenza\',\'es\':\'Tenza\'}',7,NULL,NULL),(335,'{\'de\':\'Tiban\\u00E1\',\'en\':\'Tiban\\u00E1\',\'es\':\'Tiban\\u00E1\'}',7,NULL,NULL),(336,'{\'de\':\'Tibasosa\',\'en\':\'Tibasosa\',\'es\':\'Tibasosa\'}',7,NULL,NULL),(337,'{\'de\':\'Tinjac\\u00E1\',\'en\':\'Tinjac\\u00E1\',\'es\':\'Tinjac\\u00E1\'}',7,NULL,NULL),(338,'{\'de\':\'Tipacoque\',\'en\':\'Tipacoque\',\'es\':\'Tipacoque\'}',7,NULL,NULL),(339,'{\'de\':\'Toca\',\'en\':\'Toca\',\'es\':\'Toca\'}',7,NULL,NULL),(340,'{\'de\':\'Tog\\u00FC\\u00ED\',\'en\':\'Tog\\u00FC\\u00ED\',\'es\':\'Tog\\u00FC\\u00ED\'}',7,NULL,NULL),(341,'{\'de\':\'T\\u00F3paga\',\'en\':\'T\\u00F3paga\',\'es\':\'T\\u00F3paga\'}',7,NULL,NULL),(342,'{\'de\':\'Tota\',\'en\':\'Tota\',\'es\':\'Tota\'}',7,NULL,NULL),(343,'{\'de\':\'Tunungu\\u00E1\',\'en\':\'Tunungu\\u00E1\',\'es\':\'Tunungu\\u00E1\'}',7,NULL,NULL),(344,'{\'de\':\'Turmequ\\u00E9\',\'en\':\'Turmequ\\u00E9\',\'es\':\'Turmequ\\u00E9\'}',7,NULL,NULL),(345,'{\'de\':\'Tuta\',\'en\':\'Tuta\',\'es\':\'Tuta\'}',7,NULL,NULL),(346,'{\'de\':\'Tutaz\\u00E1\',\'en\':\'Tutaz\\u00E1\',\'es\':\'Tutaz\\u00E1\'}',7,NULL,NULL),(347,'{\'de\':\'\\u00DAmbita\',\'en\':\'\\u00DAmbita\',\'es\':\'\\u00DAmbita\'}',7,NULL,NULL),(348,'{\'de\':\'Ventaquemada\',\'en\':\'Ventaquemada\',\'es\':\'Ventaquemada\'}',7,NULL,NULL),(349,'{\'de\':\'Viracach\\u00E1\',\'en\':\'Viracach\\u00E1\',\'es\':\'Viracach\\u00E1\'}',7,NULL,NULL),(350,'{\'de\':\'Zetaquira\',\'en\':\'Zetaquira\',\'es\':\'Zetaquira\'}',7,NULL,NULL),(351,'{\'de\':\'Manizales\',\'en\':\'Manizales\',\'es\':\'Manizales\'}',8,NULL,NULL),(352,'{\'de\':\'Aguadas\',\'en\':\'Aguadas\',\'es\':\'Aguadas\'}',8,NULL,NULL),(353,'{\'de\':\'Anserma\',\'en\':\'Anserma\',\'es\':\'Anserma\'}',8,NULL,NULL),(354,'{\'de\':\'Aranzazu\',\'en\':\'Aranzazu\',\'es\':\'Aranzazu\'}',8,NULL,NULL),(355,'{\'de\':\'Belalc\\u00E1zar\',\'en\':\'Belalc\\u00E1zar\',\'es\':\'Belalc\\u00E1zar\'}',8,NULL,NULL),(356,'{\'de\':\'Chinchin\\u00E1\',\'en\':\'Chinchin\\u00E1\',\'es\':\'Chinchin\\u00E1\'}',8,NULL,NULL),(357,'{\'de\':\'Filadelfia\',\'en\':\'Filadelfia\',\'es\':\'Filadelfia\'}',8,NULL,NULL),(358,'{\'de\':\'La Dorada\',\'en\':\'La Dorada\',\'es\':\'La Dorada\'}',8,NULL,NULL),(359,'{\'de\':\'La Merced\',\'en\':\'La Merced\',\'es\':\'La Merced\'}',8,NULL,NULL),(360,'{\'de\':\'Manzanares\',\'en\':\'Manzanares\',\'es\':\'Manzanares\'}',8,NULL,NULL),(361,'{\'de\':\'Marmato\',\'en\':\'Marmato\',\'es\':\'Marmato\'}',8,NULL,NULL),(362,'{\'de\':\'Marquetalia\',\'en\':\'Marquetalia\',\'es\':\'Marquetalia\'}',8,NULL,NULL),(363,'{\'de\':\'Marulanda\',\'en\':\'Marulanda\',\'es\':\'Marulanda\'}',8,NULL,NULL),(364,'{\'de\':\'Neira\',\'en\':\'Neira\',\'es\':\'Neira\'}',8,NULL,NULL),(365,'{\'de\':\'Norcasia\',\'en\':\'Norcasia\',\'es\':\'Norcasia\'}',8,NULL,NULL),(366,'{\'de\':\'P\\u00E1cora\',\'en\':\'P\\u00E1cora\',\'es\':\'P\\u00E1cora\'}',8,NULL,NULL),(367,'{\'de\':\'Palestina\',\'en\':\'Palestina\',\'es\':\'Palestina\'}',8,NULL,NULL),(368,'{\'de\':\'Pensilvania\',\'en\':\'Pensilvania\',\'es\':\'Pensilvania\'}',8,NULL,NULL),(369,'{\'de\':\'Riosucio\',\'en\':\'Riosucio\',\'es\':\'Riosucio\'}',8,NULL,NULL),(370,'{\'de\':\'Risaralda\',\'en\':\'Risaralda\',\'es\':\'Risaralda\'}',8,NULL,NULL),(371,'{\'de\':\'Salamina\',\'en\':\'Salamina\',\'es\':\'Salamina\'}',8,NULL,NULL),(372,'{\'de\':\'Saman\\u00E1\',\'en\':\'Saman\\u00E1\',\'es\':\'Saman\\u00E1\'}',8,NULL,NULL),(373,'{\'de\':\'San Jos\\u00E9\',\'en\':\'San Jos\\u00E9\',\'es\':\'San Jos\\u00E9\'}',8,NULL,NULL),(374,'{\'de\':\'Sup\\u00EDa\',\'en\':\'Sup\\u00EDa\',\'es\':\'Sup\\u00EDa\'}',8,NULL,NULL),(375,'{\'de\':\'Victoria\',\'en\':\'Victoria\',\'es\':\'Victoria\'}',8,NULL,NULL),(376,'{\'de\':\'Villamar\\u00EDa\',\'en\':\'Villamar\\u00EDa\',\'es\':\'Villamar\\u00EDa\'}',8,NULL,NULL),(377,'{\'de\':\'Viterbo\',\'en\':\'Viterbo\',\'es\':\'Viterbo\'}',8,NULL,NULL),(378,'{\'de\':\'Florencia\',\'en\':\'Florencia\',\'es\':\'Florencia\'}',9,NULL,NULL),(379,'{\'de\':\'Albania\',\'en\':\'Albania\',\'es\':\'Albania\'}',9,NULL,NULL),(380,'{\'de\':\'Bel\\u00E9n De Los Andaqu\\u00EDes\',\'en\':\'Bel\\u00E9n De Los Andaqu\\u00EDes\',\'es\':\'Bel\\u00E9n De Los Andaqu\\u00EDes\'}',9,NULL,NULL),(381,'{\'de\':\'Cartagena Del Chair\\u00E1\',\'en\':\'Cartagena Del Chair\\u00E1\',\'es\':\'Cartagena Del Chair\\u00E1\'}',9,NULL,NULL),(382,'{\'de\':\'Curillo\',\'en\':\'Curillo\',\'es\':\'Curillo\'}',9,NULL,NULL),(383,'{\'de\':\'El Doncello\',\'en\':\'El Doncello\',\'es\':\'El Doncello\'}',9,NULL,NULL),(384,'{\'de\':\'El Pauj\\u00EDl\',\'en\':\'El Pauj\\u00EDl\',\'es\':\'El Pauj\\u00EDl\'}',9,NULL,NULL),(385,'{\'de\':\'La Monta\\u00F1ita\',\'en\':\'La Monta\\u00F1ita\',\'es\':\'La Monta\\u00F1ita\'}',9,NULL,NULL),(386,'{\'de\':\'Mil\\u00E1n\',\'en\':\'Mil\\u00E1n\',\'es\':\'Mil\\u00E1n\'}',9,NULL,NULL),(387,'{\'de\':\'Morelia\',\'en\':\'Morelia\',\'es\':\'Morelia\'}',9,NULL,NULL),(388,'{\'de\':\'Puerto Rico\',\'en\':\'Puerto Rico\',\'es\':\'Puerto Rico\'}',9,NULL,NULL),(389,'{\'de\':\'San Jos\\u00E9 Del Fragua\',\'en\':\'San Jos\\u00E9 Del Fragua\',\'es\':\'San Jos\\u00E9 Del Fragua\'}',9,NULL,NULL),(390,'{\'de\':\'San Vicente Del Cagu\\u00E1n\',\'en\':\'San Vicente Del Cagu\\u00E1n\',\'es\':\'San Vicente Del Cagu\\u00E1n\'}',9,NULL,NULL),(391,'{\'de\':\'Solano\',\'en\':\'Solano\',\'es\':\'Solano\'}',9,NULL,NULL),(392,'{\'de\':\'Solita\',\'en\':\'Solita\',\'es\':\'Solita\'}',9,NULL,NULL),(393,'{\'de\':\'Valpara\\u00EDso\',\'en\':\'Valpara\\u00EDso\',\'es\':\'Valpara\\u00EDso\'}',9,NULL,NULL),(394,'{\'de\':\'Popay\\u00E1n\',\'en\':\'Popay\\u00E1n\',\'es\':\'Popay\\u00E1n\'}',11,NULL,NULL),(395,'{\'de\':\'Almaguer\',\'en\':\'Almaguer\',\'es\':\'Almaguer\'}',11,NULL,NULL),(396,'{\'de\':\'Argelia\',\'en\':\'Argelia\',\'es\':\'Argelia\'}',11,NULL,NULL),(397,'{\'de\':\'Balboa\',\'en\':\'Balboa\',\'es\':\'Balboa\'}',11,NULL,NULL),(398,'{\'de\':\'Bol\\u00EDvar\',\'en\':\'Bol\\u00EDvar\',\'es\':\'Bol\\u00EDvar\'}',11,NULL,NULL),(399,'{\'de\':\'Buenos Aires\',\'en\':\'Buenos Aires\',\'es\':\'Buenos Aires\'}',11,NULL,NULL),(400,'{\'de\':\'Cajib\\u00EDo\',\'en\':\'Cajib\\u00EDo\',\'es\':\'Cajib\\u00EDo\'}',11,NULL,NULL),(401,'{\'de\':\'Caldono\',\'en\':\'Caldono\',\'es\':\'Caldono\'}',11,NULL,NULL),(402,'{\'de\':\'Caloto\',\'en\':\'Caloto\',\'es\':\'Caloto\'}',11,NULL,NULL),(403,'{\'de\':\'Corinto\',\'en\':\'Corinto\',\'es\':\'Corinto\'}',11,NULL,NULL),(404,'{\'de\':\'El Tambo\',\'en\':\'El Tambo\',\'es\':\'El Tambo\'}',11,NULL,NULL),(405,'{\'de\':\'Florencia\',\'en\':\'Florencia\',\'es\':\'Florencia\'}',11,NULL,NULL),(406,'{\'de\':\'Guachen\\u00E9\',\'en\':\'Guachen\\u00E9\',\'es\':\'Guachen\\u00E9\'}',11,NULL,NULL),(407,'{\'de\':\'Guap\\u00ED\',\'en\':\'Guap\\u00ED\',\'es\':\'Guap\\u00ED\'}',11,NULL,NULL),(408,'{\'de\':\'Inz\\u00E1\',\'en\':\'Inz\\u00E1\',\'es\':\'Inz\\u00E1\'}',11,NULL,NULL),(409,'{\'de\':\'Jambal\\u00F3\',\'en\':\'Jambal\\u00F3\',\'es\':\'Jambal\\u00F3\'}',11,NULL,NULL),(410,'{\'de\':\'La Sierra\',\'en\':\'La Sierra\',\'es\':\'La Sierra\'}',11,NULL,NULL),(411,'{\'de\':\'La Vega\',\'en\':\'La Vega\',\'es\':\'La Vega\'}',11,NULL,NULL),(412,'{\'de\':\'L\\u00F3pez De Micay\',\'en\':\'L\\u00F3pez De Micay\',\'es\':\'L\\u00F3pez De Micay\'}',11,NULL,NULL),(413,'{\'de\':\'Mercaderes\',\'en\':\'Mercaderes\',\'es\':\'Mercaderes\'}',11,NULL,NULL),(414,'{\'de\':\'Miranda\',\'en\':\'Miranda\',\'es\':\'Miranda\'}',11,NULL,NULL),(415,'{\'de\':\'Morales\',\'en\':\'Morales\',\'es\':\'Morales\'}',11,NULL,NULL),(416,'{\'de\':\'Padilla\',\'en\':\'Padilla\',\'es\':\'Padilla\'}',11,NULL,NULL),(417,'{\'de\':\'P\\u00E1ez\',\'en\':\'P\\u00E1ez\',\'es\':\'P\\u00E1ez\'}',11,NULL,NULL),(418,'{\'de\':\'Pat\\u00EDa\',\'en\':\'Pat\\u00EDa\',\'es\':\'Pat\\u00EDa\'}',11,NULL,NULL),(419,'{\'de\':\'Piamonte\',\'en\':\'Piamonte\',\'es\':\'Piamonte\'}',11,NULL,NULL),(420,'{\'de\':\'Piendam\\u00F3\',\'en\':\'Piendam\\u00F3\',\'es\':\'Piendam\\u00F3\'}',11,NULL,NULL),(421,'{\'de\':\'Puerto Tejada\',\'en\':\'Puerto Tejada\',\'es\':\'Puerto Tejada\'}',11,NULL,NULL),(422,'{\'de\':\'Purac\\u00E9\',\'en\':\'Purac\\u00E9\',\'es\':\'Purac\\u00E9\'}',11,NULL,NULL),(423,'{\'de\':\'Rosas\',\'en\':\'Rosas\',\'es\':\'Rosas\'}',11,NULL,NULL),(424,'{\'de\':\'San Sebasti\\u00E1n\',\'en\':\'San Sebasti\\u00E1n\',\'es\':\'San Sebasti\\u00E1n\'}',11,NULL,NULL),(425,'{\'de\':\'Santander De Quilichao\',\'en\':\'Santander De Quilichao\',\'es\':\'Santander De Quilichao\'}',11,NULL,NULL),(426,'{\'de\':\'Santa Rosa\',\'en\':\'Santa Rosa\',\'es\':\'Santa Rosa\'}',11,NULL,NULL),(427,'{\'de\':\'Silvia\',\'en\':\'Silvia\',\'es\':\'Silvia\'}',11,NULL,NULL),(428,'{\'de\':\'Sotara\',\'en\':\'Sotara\',\'es\':\'Sotara\'}',11,NULL,NULL),(429,'{\'de\':\'Su\\u00E1rez\',\'en\':\'Su\\u00E1rez\',\'es\':\'Su\\u00E1rez\'}',11,NULL,NULL),(430,'{\'de\':\'Sucre\',\'en\':\'Sucre\',\'es\':\'Sucre\'}',11,NULL,NULL),(431,'{\'de\':\'Timb\\u00EDo\',\'en\':\'Timb\\u00EDo\',\'es\':\'Timb\\u00EDo\'}',11,NULL,NULL),(432,'{\'de\':\'Timbiqu\\u00ED\',\'en\':\'Timbiqu\\u00ED\',\'es\':\'Timbiqu\\u00ED\'}',11,NULL,NULL),(433,'{\'de\':\'Torib\\u00EDo\',\'en\':\'Torib\\u00EDo\',\'es\':\'Torib\\u00EDo\'}',11,NULL,NULL),(434,'{\'de\':\'Totor\\u00F3\',\'en\':\'Totor\\u00F3\',\'es\':\'Totor\\u00F3\'}',11,NULL,NULL),(435,'{\'de\':\'Villa Rica\',\'en\':\'Villa Rica\',\'es\':\'Villa Rica\'}',11,NULL,NULL),(436,'{\'de\':\'Valledupar\',\'en\':\'Valledupar\',\'es\':\'Valledupar\'}',12,NULL,NULL),(437,'{\'de\':\'Aguachica\',\'en\':\'Aguachica\',\'es\':\'Aguachica\'}',12,NULL,NULL),(438,'{\'de\':\'Agust\\u00EDn Codazzi\',\'en\':\'Agust\\u00EDn Codazzi\',\'es\':\'Agust\\u00EDn Codazzi\'}',12,NULL,NULL),(439,'{\'de\':\'Astrea\',\'en\':\'Astrea\',\'es\':\'Astrea\'}',12,NULL,NULL),(440,'{\'de\':\'Becerril\',\'en\':\'Becerril\',\'es\':\'Becerril\'}',12,NULL,NULL),(441,'{\'de\':\'Bosconia\',\'en\':\'Bosconia\',\'es\':\'Bosconia\'}',12,NULL,NULL),(442,'{\'de\':\'Chimichagua\',\'en\':\'Chimichagua\',\'es\':\'Chimichagua\'}',12,NULL,NULL),(443,'{\'de\':\'Chiriguan\\u00E1\',\'en\':\'Chiriguan\\u00E1\',\'es\':\'Chiriguan\\u00E1\'}',12,NULL,NULL),(444,'{\'de\':\'Curuman\\u00ED\',\'en\':\'Curuman\\u00ED\',\'es\':\'Curuman\\u00ED\'}',12,NULL,NULL),(445,'{\'de\':\'El Copey\',\'en\':\'El Copey\',\'es\':\'El Copey\'}',12,NULL,NULL),(446,'{\'de\':\'El Paso\',\'en\':\'El Paso\',\'es\':\'El Paso\'}',12,NULL,NULL),(447,'{\'de\':\'Gamarra\',\'en\':\'Gamarra\',\'es\':\'Gamarra\'}',12,NULL,NULL),(448,'{\'de\':\'Gonz\\u00E1lez\',\'en\':\'Gonz\\u00E1lez\',\'es\':\'Gonz\\u00E1lez\'}',12,NULL,NULL),(449,'{\'de\':\'La Gloria\',\'en\':\'La Gloria\',\'es\':\'La Gloria\'}',12,NULL,NULL),(450,'{\'de\':\'La Jagua De Ibirico\',\'en\':\'La Jagua De Ibirico\',\'es\':\'La Jagua De Ibirico\'}',12,NULL,NULL),(451,'{\'de\':\'Manaure Balc\\u00F3n Del Cesar\',\'en\':\'Manaure Balc\\u00F3n Del Cesar\',\'es\':\'Manaure Balc\\u00F3n Del Cesar\'}',12,NULL,NULL),(452,'{\'de\':\'Pailitas\',\'en\':\'Pailitas\',\'es\':\'Pailitas\'}',12,NULL,NULL),(453,'{\'de\':\'Pelaya\',\'en\':\'Pelaya\',\'es\':\'Pelaya\'}',12,NULL,NULL),(454,'{\'de\':\'Pueblo Bello\',\'en\':\'Pueblo Bello\',\'es\':\'Pueblo Bello\'}',12,NULL,NULL),(455,'{\'de\':\'R\\u00EDo De Oro\',\'en\':\'R\\u00EDo De Oro\',\'es\':\'R\\u00EDo De Oro\'}',12,NULL,NULL),(456,'{\'de\':\'La Paz\',\'en\':\'La Paz\',\'es\':\'La Paz\'}',12,NULL,NULL),(457,'{\'de\':\'San Alberto\',\'en\':\'San Alberto\',\'es\':\'San Alberto\'}',12,NULL,NULL),(458,'{\'de\':\'San Diego\',\'en\':\'San Diego\',\'es\':\'San Diego\'}',12,NULL,NULL),(459,'{\'de\':\'San Mart\\u00EDn\',\'en\':\'San Mart\\u00EDn\',\'es\':\'San Mart\\u00EDn\'}',12,NULL,NULL),(460,'{\'de\':\'Tamalameque\',\'en\':\'Tamalameque\',\'es\':\'Tamalameque\'}',12,NULL,NULL),(461,'{\'de\':\'Monter\\u00EDa\',\'en\':\'Monter\\u00EDa\',\'es\':\'Monter\\u00EDa\'}',14,NULL,NULL),(462,'{\'de\':\'Ayapel\',\'en\':\'Ayapel\',\'es\':\'Ayapel\'}',14,NULL,NULL),(463,'{\'de\':\'Buenavista\',\'en\':\'Buenavista\',\'es\':\'Buenavista\'}',14,NULL,NULL),(464,'{\'de\':\'Canalete\',\'en\':\'Canalete\',\'es\':\'Canalete\'}',14,NULL,NULL),(465,'{\'de\':\'Ceret\\u00E9\',\'en\':\'Ceret\\u00E9\',\'es\':\'Ceret\\u00E9\'}',14,NULL,NULL),(466,'{\'de\':\'Chim\\u00E1\',\'en\':\'Chim\\u00E1\',\'es\':\'Chim\\u00E1\'}',14,NULL,NULL),(467,'{\'de\':\'Chin\\u00FA\',\'en\':\'Chin\\u00FA\',\'es\':\'Chin\\u00FA\'}',14,NULL,NULL),(468,'{\'de\':\'Ci\\u00E9naga De Oro\',\'en\':\'Ci\\u00E9naga De Oro\',\'es\':\'Ci\\u00E9naga De Oro\'}',14,NULL,NULL),(469,'{\'de\':\'Cotorra\',\'en\':\'Cotorra\',\'es\':\'Cotorra\'}',14,NULL,NULL),(470,'{\'de\':\'La Apartada\',\'en\':\'La Apartada\',\'es\':\'La Apartada\'}',14,NULL,NULL),(471,'{\'de\':\'Lorica\',\'en\':\'Lorica\',\'es\':\'Lorica\'}',14,NULL,NULL),(472,'{\'de\':\'Los C\\u00F3rdobas\',\'en\':\'Los C\\u00F3rdobas\',\'es\':\'Los C\\u00F3rdobas\'}',14,NULL,NULL),(473,'{\'de\':\'Momil\',\'en\':\'Momil\',\'es\':\'Momil\'}',14,NULL,NULL),(474,'{\'de\':\'Montel\\u00EDbano\',\'en\':\'Montel\\u00EDbano\',\'es\':\'Montel\\u00EDbano\'}',14,NULL,NULL),(475,'{\'de\':\'Mo\\u00F1itos\',\'en\':\'Mo\\u00F1itos\',\'es\':\'Mo\\u00F1itos\'}',14,NULL,NULL),(476,'{\'de\':\'Planeta Rica\',\'en\':\'Planeta Rica\',\'es\':\'Planeta Rica\'}',14,NULL,NULL),(477,'{\'de\':\'Pueblo Nuevo\',\'en\':\'Pueblo Nuevo\',\'es\':\'Pueblo Nuevo\'}',14,NULL,NULL),(478,'{\'de\':\'Puerto Escondido\',\'en\':\'Puerto Escondido\',\'es\':\'Puerto Escondido\'}',14,NULL,NULL),(479,'{\'de\':\'Puerto Libertador\',\'en\':\'Puerto Libertador\',\'es\':\'Puerto Libertador\'}',14,NULL,NULL),(480,'{\'de\':\'Pur\\u00EDsima De La Concepci\\u00F3n\',\'en\':\'Pur\\u00EDsima De La Concepci\\u00F3n\',\'es\':\'Pur\\u00EDsima De La Concepci\\u00F3n\'}',14,NULL,NULL),(481,'{\'de\':\'Sahag\\u00FAn\',\'en\':\'Sahag\\u00FAn\',\'es\':\'Sahag\\u00FAn\'}',14,NULL,NULL),(482,'{\'de\':\'San Andr\\u00E9s De Sotavento\',\'en\':\'San Andr\\u00E9s De Sotavento\',\'es\':\'San Andr\\u00E9s De Sotavento\'}',14,NULL,NULL),(483,'{\'de\':\'San Antero\',\'en\':\'San Antero\',\'es\':\'San Antero\'}',14,NULL,NULL),(484,'{\'de\':\'San Bernardo Del Viento\',\'en\':\'San Bernardo Del Viento\',\'es\':\'San Bernardo Del Viento\'}',14,NULL,NULL),(485,'{\'de\':\'San Carlos\',\'en\':\'San Carlos\',\'es\':\'San Carlos\'}',14,NULL,NULL),(486,'{\'de\':\'San Jos\\u00E9 De Ur\\u00E9\',\'en\':\'San Jos\\u00E9 De Ur\\u00E9\',\'es\':\'San Jos\\u00E9 De Ur\\u00E9\'}',14,NULL,NULL),(487,'{\'de\':\'San Pelayo\',\'en\':\'San Pelayo\',\'es\':\'San Pelayo\'}',14,NULL,NULL),(488,'{\'de\':\'Tierralta\',\'en\':\'Tierralta\',\'es\':\'Tierralta\'}',14,NULL,NULL),(489,'{\'de\':\'Tuch\\u00EDn\',\'en\':\'Tuch\\u00EDn\',\'es\':\'Tuch\\u00EDn\'}',14,NULL,NULL),(490,'{\'de\':\'Valencia\',\'en\':\'Valencia\',\'es\':\'Valencia\'}',14,NULL,NULL),(491,'{\'de\':\'Agua De Dios\',\'en\':\'Agua De Dios\',\'es\':\'Agua De Dios\'}',15,NULL,NULL),(492,'{\'de\':\'Alb\\u00E1n\',\'en\':\'Alb\\u00E1n\',\'es\':\'Alb\\u00E1n\'}',15,NULL,NULL),(493,'{\'de\':\'Anapoima\',\'en\':\'Anapoima\',\'es\':\'Anapoima\'}',15,NULL,NULL),(494,'{\'de\':\'Anolaima\',\'en\':\'Anolaima\',\'es\':\'Anolaima\'}',15,NULL,NULL),(495,'{\'de\':\'Arbel\\u00E1ez\',\'en\':\'Arbel\\u00E1ez\',\'es\':\'Arbel\\u00E1ez\'}',15,NULL,NULL),(496,'{\'de\':\'Beltr\\u00E1n\',\'en\':\'Beltr\\u00E1n\',\'es\':\'Beltr\\u00E1n\'}',15,NULL,NULL),(497,'{\'de\':\'Bituima\',\'en\':\'Bituima\',\'es\':\'Bituima\'}',15,NULL,NULL),(498,'{\'de\':\'Bojac\\u00E1\',\'en\':\'Bojac\\u00E1\',\'es\':\'Bojac\\u00E1\'}',15,NULL,NULL),(499,'{\'de\':\'Cabrera\',\'en\':\'Cabrera\',\'es\':\'Cabrera\'}',15,NULL,NULL),(500,'{\'de\':\'Cachipay\',\'en\':\'Cachipay\',\'es\':\'Cachipay\'}',15,NULL,NULL),(501,'{\'de\':\'Cajic\\u00E1\',\'en\':\'Cajic\\u00E1\',\'es\':\'Cajic\\u00E1\'}',15,NULL,NULL),(502,'{\'de\':\'Caparrap\\u00ED\',\'en\':\'Caparrap\\u00ED\',\'es\':\'Caparrap\\u00ED\'}',15,NULL,NULL),(503,'{\'de\':\'C\\u00E1queza\',\'en\':\'C\\u00E1queza\',\'es\':\'C\\u00E1queza\'}',15,NULL,NULL),(504,'{\'de\':\'Carmen De Carupa\',\'en\':\'Carmen De Carupa\',\'es\':\'Carmen De Carupa\'}',15,NULL,NULL),(505,'{\'de\':\'Chaguan\\u00ED\',\'en\':\'Chaguan\\u00ED\',\'es\':\'Chaguan\\u00ED\'}',15,NULL,NULL),(506,'{\'de\':\'Ch\\u00EDa\',\'en\':\'Ch\\u00EDa\',\'es\':\'Ch\\u00EDa\'}',15,NULL,NULL),(507,'{\'de\':\'Chipaque\',\'en\':\'Chipaque\',\'es\':\'Chipaque\'}',15,NULL,NULL),(508,'{\'de\':\'Choach\\u00ED\',\'en\':\'Choach\\u00ED\',\'es\':\'Choach\\u00ED\'}',15,NULL,NULL),(509,'{\'de\':\'Chocont\\u00E1\',\'en\':\'Chocont\\u00E1\',\'es\':\'Chocont\\u00E1\'}',15,NULL,NULL),(510,'{\'de\':\'Cogua\',\'en\':\'Cogua\',\'es\':\'Cogua\'}',15,NULL,NULL),(511,'{\'de\':\'Cota\',\'en\':\'Cota\',\'es\':\'Cota\'}',15,NULL,NULL),(512,'{\'de\':\'Cucunub\\u00E1\',\'en\':\'Cucunub\\u00E1\',\'es\':\'Cucunub\\u00E1\'}',15,NULL,NULL),(513,'{\'de\':\'El Colegio\',\'en\':\'El Colegio\',\'es\':\'El Colegio\'}',15,NULL,NULL),(514,'{\'de\':\'El Pe\\u00F1\\u00F3n\',\'en\':\'El Pe\\u00F1\\u00F3n\',\'es\':\'El Pe\\u00F1\\u00F3n\'}',15,NULL,NULL),(515,'{\'de\':\'El Rosal\',\'en\':\'El Rosal\',\'es\':\'El Rosal\'}',15,NULL,NULL),(516,'{\'de\':\'Facatativ\\u00E1\',\'en\':\'Facatativ\\u00E1\',\'es\':\'Facatativ\\u00E1\'}',15,NULL,NULL),(517,'{\'de\':\'F\\u00F3meque\',\'en\':\'F\\u00F3meque\',\'es\':\'F\\u00F3meque\'}',15,NULL,NULL),(518,'{\'de\':\'Fosca\',\'en\':\'Fosca\',\'es\':\'Fosca\'}',15,NULL,NULL),(519,'{\'de\':\'Funza\',\'en\':\'Funza\',\'es\':\'Funza\'}',15,NULL,NULL),(520,'{\'de\':\'F\\u00FAquene\',\'en\':\'F\\u00FAquene\',\'es\':\'F\\u00FAquene\'}',15,NULL,NULL),(521,'{\'de\':\'Fusagasug\\u00E1\',\'en\':\'Fusagasug\\u00E1\',\'es\':\'Fusagasug\\u00E1\'}',15,NULL,NULL),(522,'{\'de\':\'Gachal\\u00E1\',\'en\':\'Gachal\\u00E1\',\'es\':\'Gachal\\u00E1\'}',15,NULL,NULL),(523,'{\'de\':\'Gachancip\\u00E1\',\'en\':\'Gachancip\\u00E1\',\'es\':\'Gachancip\\u00E1\'}',15,NULL,NULL),(524,'{\'de\':\'Gachet\\u00E1\',\'en\':\'Gachet\\u00E1\',\'es\':\'Gachet\\u00E1\'}',15,NULL,NULL),(525,'{\'de\':\'Gama\',\'en\':\'Gama\',\'es\':\'Gama\'}',15,NULL,NULL),(526,'{\'de\':\'Girardot\',\'en\':\'Girardot\',\'es\':\'Girardot\'}',15,NULL,NULL),(527,'{\'de\':\'Granada\',\'en\':\'Granada\',\'es\':\'Granada\'}',15,NULL,NULL),(528,'{\'de\':\'Guachet\\u00E1\',\'en\':\'Guachet\\u00E1\',\'es\':\'Guachet\\u00E1\'}',15,NULL,NULL),(529,'{\'de\':\'Guaduas\',\'en\':\'Guaduas\',\'es\':\'Guaduas\'}',15,NULL,NULL),(530,'{\'de\':\'Guasca\',\'en\':\'Guasca\',\'es\':\'Guasca\'}',15,NULL,NULL),(531,'{\'de\':\'Guataqu\\u00ED\',\'en\':\'Guataqu\\u00ED\',\'es\':\'Guataqu\\u00ED\'}',15,NULL,NULL),(532,'{\'de\':\'Guatavita\',\'en\':\'Guatavita\',\'es\':\'Guatavita\'}',15,NULL,NULL),(533,'{\'de\':\'Guayabal De S\\u00EDquima\',\'en\':\'Guayabal De S\\u00EDquima\',\'es\':\'Guayabal De S\\u00EDquima\'}',15,NULL,NULL),(534,'{\'de\':\'Guayabetal\',\'en\':\'Guayabetal\',\'es\':\'Guayabetal\'}',15,NULL,NULL),(535,'{\'de\':\'Guti\\u00E9rrez\',\'en\':\'Guti\\u00E9rrez\',\'es\':\'Guti\\u00E9rrez\'}',15,NULL,NULL),(536,'{\'de\':\'Jerusal\\u00E9n\',\'en\':\'Jerusal\\u00E9n\',\'es\':\'Jerusal\\u00E9n\'}',15,NULL,NULL),(537,'{\'de\':\'Jun\\u00EDn\',\'en\':\'Jun\\u00EDn\',\'es\':\'Jun\\u00EDn\'}',15,NULL,NULL),(538,'{\'de\':\'La Calera\',\'en\':\'La Calera\',\'es\':\'La Calera\'}',15,NULL,NULL),(539,'{\'de\':\'La Mesa\',\'en\':\'La Mesa\',\'es\':\'La Mesa\'}',15,NULL,NULL),(540,'{\'de\':\'La Palma\',\'en\':\'La Palma\',\'es\':\'La Palma\'}',15,NULL,NULL),(541,'{\'de\':\'La Pe\\u00F1a\',\'en\':\'La Pe\\u00F1a\',\'es\':\'La Pe\\u00F1a\'}',15,NULL,NULL),(542,'{\'de\':\'La Vega\',\'en\':\'La Vega\',\'es\':\'La Vega\'}',15,NULL,NULL),(543,'{\'de\':\'Lenguazaque\',\'en\':\'Lenguazaque\',\'es\':\'Lenguazaque\'}',15,NULL,NULL),(544,'{\'de\':\'Machet\\u00E1\',\'en\':\'Machet\\u00E1\',\'es\':\'Machet\\u00E1\'}',15,NULL,NULL),(545,'{\'de\':\'Madrid\',\'en\':\'Madrid\',\'es\':\'Madrid\'}',15,NULL,NULL),(546,'{\'de\':\'Manta\',\'en\':\'Manta\',\'es\':\'Manta\'}',15,NULL,NULL),(547,'{\'de\':\'Medina\',\'en\':\'Medina\',\'es\':\'Medina\'}',15,NULL,NULL),(548,'{\'de\':\'Mosquera\',\'en\':\'Mosquera\',\'es\':\'Mosquera\'}',15,NULL,NULL),(549,'{\'de\':\'Nari\\u00F1o\',\'en\':\'Nari\\u00F1o\',\'es\':\'Nari\\u00F1o\'}',15,NULL,NULL),(550,'{\'de\':\'Nemoc\\u00F3n\',\'en\':\'Nemoc\\u00F3n\',\'es\':\'Nemoc\\u00F3n\'}',15,NULL,NULL),(551,'{\'de\':\'Nilo\',\'en\':\'Nilo\',\'es\':\'Nilo\'}',15,NULL,NULL),(552,'{\'de\':\'Nimaima\',\'en\':\'Nimaima\',\'es\':\'Nimaima\'}',15,NULL,NULL),(553,'{\'de\':\'Nocaima\',\'en\':\'Nocaima\',\'es\':\'Nocaima\'}',15,NULL,NULL),(554,'{\'de\':\'Venecia\',\'en\':\'Venecia\',\'es\':\'Venecia\'}',15,NULL,NULL),(555,'{\'de\':\'Pacho\',\'en\':\'Pacho\',\'es\':\'Pacho\'}',15,NULL,NULL),(556,'{\'de\':\'Paime\',\'en\':\'Paime\',\'es\':\'Paime\'}',15,NULL,NULL),(557,'{\'de\':\'Pandi\',\'en\':\'Pandi\',\'es\':\'Pandi\'}',15,NULL,NULL),(558,'{\'de\':\'Paratebueno\',\'en\':\'Paratebueno\',\'es\':\'Paratebueno\'}',15,NULL,NULL),(559,'{\'de\':\'Pasca\',\'en\':\'Pasca\',\'es\':\'Pasca\'}',15,NULL,NULL),(560,'{\'de\':\'Puerto Salgar\',\'en\':\'Puerto Salgar\',\'es\':\'Puerto Salgar\'}',15,NULL,NULL),(561,'{\'de\':\'Pul\\u00ED\',\'en\':\'Pul\\u00ED\',\'es\':\'Pul\\u00ED\'}',15,NULL,NULL),(562,'{\'de\':\'Quebradanegra\',\'en\':\'Quebradanegra\',\'es\':\'Quebradanegra\'}',15,NULL,NULL),(563,'{\'de\':\'Quetame\',\'en\':\'Quetame\',\'es\':\'Quetame\'}',15,NULL,NULL),(564,'{\'de\':\'Quipile\',\'en\':\'Quipile\',\'es\':\'Quipile\'}',15,NULL,NULL),(565,'{\'de\':\'Apulo\',\'en\':\'Apulo\',\'es\':\'Apulo\'}',15,NULL,NULL),(566,'{\'de\':\'Ricaurte\',\'en\':\'Ricaurte\',\'es\':\'Ricaurte\'}',15,NULL,NULL),(567,'{\'de\':\'San Antonio Del Tequendama\',\'en\':\'San Antonio Del Tequendama\',\'es\':\'San Antonio Del Tequendama\'}',15,NULL,NULL),(568,'{\'de\':\'San Bernardo\',\'en\':\'San Bernardo\',\'es\':\'San Bernardo\'}',15,NULL,NULL),(569,'{\'de\':\'San Cayetano\',\'en\':\'San Cayetano\',\'es\':\'San Cayetano\'}',15,NULL,NULL),(570,'{\'de\':\'San Francisco\',\'en\':\'San Francisco\',\'es\':\'San Francisco\'}',15,NULL,NULL),(571,'{\'de\':\'San Juan De Rioseco\',\'en\':\'San Juan De Rioseco\',\'es\':\'San Juan De Rioseco\'}',15,NULL,NULL),(572,'{\'de\':\'Sasaima\',\'en\':\'Sasaima\',\'es\':\'Sasaima\'}',15,NULL,NULL),(573,'{\'de\':\'Sesquil\\u00E9\',\'en\':\'Sesquil\\u00E9\',\'es\':\'Sesquil\\u00E9\'}',15,NULL,NULL),(574,'{\'de\':\'Sibat\\u00E9\',\'en\':\'Sibat\\u00E9\',\'es\':\'Sibat\\u00E9\'}',15,NULL,NULL),(575,'{\'de\':\'Silvania\',\'en\':\'Silvania\',\'es\':\'Silvania\'}',15,NULL,NULL),(576,'{\'de\':\'Simijaca\',\'en\':\'Simijaca\',\'es\':\'Simijaca\'}',15,NULL,NULL),(577,'{\'de\':\'Soacha\',\'en\':\'Soacha\',\'es\':\'Soacha\'}',15,NULL,NULL),(578,'{\'de\':\'Sop\\u00F3\',\'en\':\'Sop\\u00F3\',\'es\':\'Sop\\u00F3\'}',15,NULL,NULL),(579,'{\'de\':\'Subachoque\',\'en\':\'Subachoque\',\'es\':\'Subachoque\'}',15,NULL,NULL),(580,'{\'de\':\'Suesca\',\'en\':\'Suesca\',\'es\':\'Suesca\'}',15,NULL,NULL),(581,'{\'de\':\'Supat\\u00E1\',\'en\':\'Supat\\u00E1\',\'es\':\'Supat\\u00E1\'}',15,NULL,NULL),(582,'{\'de\':\'Susa\',\'en\':\'Susa\',\'es\':\'Susa\'}',15,NULL,NULL),(583,'{\'de\':\'Sutatausa\',\'en\':\'Sutatausa\',\'es\':\'Sutatausa\'}',15,NULL,NULL),(584,'{\'de\':\'Tabio\',\'en\':\'Tabio\',\'es\':\'Tabio\'}',15,NULL,NULL),(585,'{\'de\':\'Tausa\',\'en\':\'Tausa\',\'es\':\'Tausa\'}',15,NULL,NULL),(586,'{\'de\':\'Tena\',\'en\':\'Tena\',\'es\':\'Tena\'}',15,NULL,NULL),(587,'{\'de\':\'Tenjo\',\'en\':\'Tenjo\',\'es\':\'Tenjo\'}',15,NULL,NULL),(588,'{\'de\':\'Tibacuy\',\'en\':\'Tibacuy\',\'es\':\'Tibacuy\'}',15,NULL,NULL),(589,'{\'de\':\'Tibirita\',\'en\':\'Tibirita\',\'es\':\'Tibirita\'}',15,NULL,NULL),(590,'{\'de\':\'Tocaima\',\'en\':\'Tocaima\',\'es\':\'Tocaima\'}',15,NULL,NULL),(591,'{\'de\':\'Tocancip\\u00E1\',\'en\':\'Tocancip\\u00E1\',\'es\':\'Tocancip\\u00E1\'}',15,NULL,NULL),(592,'{\'de\':\'Topaip\\u00ED\',\'en\':\'Topaip\\u00ED\',\'es\':\'Topaip\\u00ED\'}',15,NULL,NULL),(593,'{\'de\':\'Ubal\\u00E1\',\'en\':\'Ubal\\u00E1\',\'es\':\'Ubal\\u00E1\'}',15,NULL,NULL),(594,'{\'de\':\'Ubaque\',\'en\':\'Ubaque\',\'es\':\'Ubaque\'}',15,NULL,NULL),(595,'{\'de\':\'Villa De San Diego De Ubat\\u00E9\',\'en\':\'Villa De San Diego De Ubat\\u00E9\',\'es\':\'Villa De San Diego De Ubat\\u00E9\'}',15,NULL,NULL),(596,'{\'de\':\'Une\',\'en\':\'Une\',\'es\':\'Une\'}',15,NULL,NULL),(597,'{\'de\':\'\\u00DAtica\',\'en\':\'\\u00DAtica\',\'es\':\'\\u00DAtica\'}',15,NULL,NULL),(598,'{\'de\':\'Vergara\',\'en\':\'Vergara\',\'es\':\'Vergara\'}',15,NULL,NULL),(599,'{\'de\':\'Vian\\u00ED\',\'en\':\'Vian\\u00ED\',\'es\':\'Vian\\u00ED\'}',15,NULL,NULL),(600,'{\'de\':\'Villag\\u00F3mez\',\'en\':\'Villag\\u00F3mez\',\'es\':\'Villag\\u00F3mez\'}',15,NULL,NULL),(601,'{\'de\':\'Villapinz\\u00F3n\',\'en\':\'Villapinz\\u00F3n\',\'es\':\'Villapinz\\u00F3n\'}',15,NULL,NULL),(602,'{\'de\':\'Villeta\',\'en\':\'Villeta\',\'es\':\'Villeta\'}',15,NULL,NULL),(603,'{\'de\':\'Viot\\u00E1\',\'en\':\'Viot\\u00E1\',\'es\':\'Viot\\u00E1\'}',15,NULL,NULL),(604,'{\'de\':\'Yacop\\u00ED\',\'en\':\'Yacop\\u00ED\',\'es\':\'Yacop\\u00ED\'}',15,NULL,NULL),(605,'{\'de\':\'Zipac\\u00F3n\',\'en\':\'Zipac\\u00F3n\',\'es\':\'Zipac\\u00F3n\'}',15,NULL,NULL),(606,'{\'de\':\'Zipaquir\\u00E1\',\'en\':\'Zipaquir\\u00E1\',\'es\':\'Zipaquir\\u00E1\'}',15,NULL,NULL),(607,'{\'de\':\'Quibd\\u00F3\',\'en\':\'Quibd\\u00F3\',\'es\':\'Quibd\\u00F3\'}',13,NULL,NULL),(608,'{\'de\':\'Acand\\u00ED\',\'en\':\'Acand\\u00ED\',\'es\':\'Acand\\u00ED\'}',13,NULL,NULL),(609,'{\'de\':\'Alto Baud\\u00F3\',\'en\':\'Alto Baud\\u00F3\',\'es\':\'Alto Baud\\u00F3\'}',13,NULL,NULL),(610,'{\'de\':\'Atrato\',\'en\':\'Atrato\',\'es\':\'Atrato\'}',13,NULL,NULL),(611,'{\'de\':\'Bagad\\u00F3\',\'en\':\'Bagad\\u00F3\',\'es\':\'Bagad\\u00F3\'}',13,NULL,NULL),(612,'{\'de\':\'Bah\\u00EDa Solano\',\'en\':\'Bah\\u00EDa Solano\',\'es\':\'Bah\\u00EDa Solano\'}',13,NULL,NULL),(613,'{\'de\':\'Bajo Baud\\u00F3\',\'en\':\'Bajo Baud\\u00F3\',\'es\':\'Bajo Baud\\u00F3\'}',13,NULL,NULL),(614,'{\'de\':\'Bojay\\u00E1\',\'en\':\'Bojay\\u00E1\',\'es\':\'Bojay\\u00E1\'}',13,NULL,NULL),(615,'{\'de\':\'El Cant\\u00F3n Del San Pablo\',\'en\':\'El Cant\\u00F3n Del San Pablo\',\'es\':\'El Cant\\u00F3n Del San Pablo\'}',13,NULL,NULL),(616,'{\'de\':\'Carmen Del Dari\\u00E9n\',\'en\':\'Carmen Del Dari\\u00E9n\',\'es\':\'Carmen Del Dari\\u00E9n\'}',13,NULL,NULL),(617,'{\'de\':\'C\\u00E9rtegui\',\'en\':\'C\\u00E9rtegui\',\'es\':\'C\\u00E9rtegui\'}',13,NULL,NULL),(618,'{\'de\':\'Condoto\',\'en\':\'Condoto\',\'es\':\'Condoto\'}',13,NULL,NULL),(619,'{\'de\':\'El Carmen De Atrato\',\'en\':\'El Carmen De Atrato\',\'es\':\'El Carmen De Atrato\'}',13,NULL,NULL),(620,'{\'de\':\'El Litoral Del San Juan\',\'en\':\'El Litoral Del San Juan\',\'es\':\'El Litoral Del San Juan\'}',13,NULL,NULL),(621,'{\'de\':\'Istmina\',\'en\':\'Istmina\',\'es\':\'Istmina\'}',13,NULL,NULL),(622,'{\'de\':\'Jurad\\u00F3\',\'en\':\'Jurad\\u00F3\',\'es\':\'Jurad\\u00F3\'}',13,NULL,NULL),(623,'{\'de\':\'Llor\\u00F3\',\'en\':\'Llor\\u00F3\',\'es\':\'Llor\\u00F3\'}',13,NULL,NULL),(624,'{\'de\':\'Medio Atrato\',\'en\':\'Medio Atrato\',\'es\':\'Medio Atrato\'}',13,NULL,NULL),(625,'{\'de\':\'Medio Baud\\u00F3\',\'en\':\'Medio Baud\\u00F3\',\'es\':\'Medio Baud\\u00F3\'}',13,NULL,NULL),(626,'{\'de\':\'Medio San Juan\',\'en\':\'Medio San Juan\',\'es\':\'Medio San Juan\'}',13,NULL,NULL),(627,'{\'de\':\'N\\u00F3vita\',\'en\':\'N\\u00F3vita\',\'es\':\'N\\u00F3vita\'}',13,NULL,NULL),(628,'{\'de\':\'Nuqu\\u00ED\',\'en\':\'Nuqu\\u00ED\',\'es\':\'Nuqu\\u00ED\'}',13,NULL,NULL),(629,'{\'de\':\'R\\u00EDo Ir\\u00F3\',\'en\':\'R\\u00EDo Ir\\u00F3\',\'es\':\'R\\u00EDo Ir\\u00F3\'}',13,NULL,NULL),(630,'{\'de\':\'R\\u00EDo Quito\',\'en\':\'R\\u00EDo Quito\',\'es\':\'R\\u00EDo Quito\'}',13,NULL,NULL),(631,'{\'de\':\'Riosucio\',\'en\':\'Riosucio\',\'es\':\'Riosucio\'}',13,NULL,NULL),(632,'{\'de\':\'San Jos\\u00E9 Del Palmar\',\'en\':\'San Jos\\u00E9 Del Palmar\',\'es\':\'San Jos\\u00E9 Del Palmar\'}',13,NULL,NULL),(633,'{\'de\':\'Sip\\u00ED\',\'en\':\'Sip\\u00ED\',\'es\':\'Sip\\u00ED\'}',13,NULL,NULL),(634,'{\'de\':\'Tad\\u00F3\',\'en\':\'Tad\\u00F3\',\'es\':\'Tad\\u00F3\'}',13,NULL,NULL),(635,'{\'de\':\'Ungu\\u00EDa\',\'en\':\'Ungu\\u00EDa\',\'es\':\'Ungu\\u00EDa\'}',13,NULL,NULL),(636,'{\'de\':\'Uni\\u00F3n Panamericana\',\'en\':\'Uni\\u00F3n Panamericana\',\'es\':\'Uni\\u00F3n Panamericana\'}',13,NULL,NULL),(637,'{\'de\':\'Neiva\',\'en\':\'Neiva\',\'es\':\'Neiva\'}',18,NULL,NULL),(638,'{\'de\':\'Acevedo\',\'en\':\'Acevedo\',\'es\':\'Acevedo\'}',18,NULL,NULL),(639,'{\'de\':\'Agrado\',\'en\':\'Agrado\',\'es\':\'Agrado\'}',18,NULL,NULL),(640,'{\'de\':\'Aipe\',\'en\':\'Aipe\',\'es\':\'Aipe\'}',18,NULL,NULL),(641,'{\'de\':\'Algeciras\',\'en\':\'Algeciras\',\'es\':\'Algeciras\'}',18,NULL,NULL),(642,'{\'de\':\'Altamira\',\'en\':\'Altamira\',\'es\':\'Altamira\'}',18,NULL,NULL),(643,'{\'de\':\'Baraya\',\'en\':\'Baraya\',\'es\':\'Baraya\'}',18,NULL,NULL),(644,'{\'de\':\'Campoalegre\',\'en\':\'Campoalegre\',\'es\':\'Campoalegre\'}',18,NULL,NULL),(645,'{\'de\':\'Colombia\',\'en\':\'Colombia\',\'es\':\'Colombia\'}',18,NULL,NULL),(646,'{\'de\':\'El\\u00EDas\',\'en\':\'El\\u00EDas\',\'es\':\'El\\u00EDas\'}',18,NULL,NULL),(647,'{\'de\':\'Garz\\u00F3n\',\'en\':\'Garz\\u00F3n\',\'es\':\'Garz\\u00F3n\'}',18,NULL,NULL),(648,'{\'de\':\'Gigante\',\'en\':\'Gigante\',\'es\':\'Gigante\'}',18,NULL,NULL),(649,'{\'de\':\'Guadalupe\',\'en\':\'Guadalupe\',\'es\':\'Guadalupe\'}',18,NULL,NULL),(650,'{\'de\':\'Hobo\',\'en\':\'Hobo\',\'es\':\'Hobo\'}',18,NULL,NULL),(651,'{\'de\':\'\\u00CDquira\',\'en\':\'\\u00CDquira\',\'es\':\'\\u00CDquira\'}',18,NULL,NULL),(652,'{\'de\':\'Isnos\',\'en\':\'Isnos\',\'es\':\'Isnos\'}',18,NULL,NULL),(653,'{\'de\':\'La Argentina\',\'en\':\'La Argentina\',\'es\':\'La Argentina\'}',18,NULL,NULL),(654,'{\'de\':\'La Plata\',\'en\':\'La Plata\',\'es\':\'La Plata\'}',18,NULL,NULL),(655,'{\'de\':\'N\\u00E1taga\',\'en\':\'N\\u00E1taga\',\'es\':\'N\\u00E1taga\'}',18,NULL,NULL),(656,'{\'de\':\'Oporapa\',\'en\':\'Oporapa\',\'es\':\'Oporapa\'}',18,NULL,NULL),(657,'{\'de\':\'Paicol\',\'en\':\'Paicol\',\'es\':\'Paicol\'}',18,NULL,NULL),(658,'{\'de\':\'Palermo\',\'en\':\'Palermo\',\'es\':\'Palermo\'}',18,NULL,NULL),(659,'{\'de\':\'Palestina\',\'en\':\'Palestina\',\'es\':\'Palestina\'}',18,NULL,NULL),(660,'{\'de\':\'Pital\',\'en\':\'Pital\',\'es\':\'Pital\'}',18,NULL,NULL),(661,'{\'de\':\'Pitalito\',\'en\':\'Pitalito\',\'es\':\'Pitalito\'}',18,NULL,NULL),(662,'{\'de\':\'Rivera\',\'en\':\'Rivera\',\'es\':\'Rivera\'}',18,NULL,NULL),(663,'{\'de\':\'Saladoblanco\',\'en\':\'Saladoblanco\',\'es\':\'Saladoblanco\'}',18,NULL,NULL),(664,'{\'de\':\'San Agust\\u00EDn\',\'en\':\'San Agust\\u00EDn\',\'es\':\'San Agust\\u00EDn\'}',18,NULL,NULL),(665,'{\'de\':\'Santa Mar\\u00EDa\',\'en\':\'Santa Mar\\u00EDa\',\'es\':\'Santa Mar\\u00EDa\'}',18,NULL,NULL),(666,'{\'de\':\'Suaza\',\'en\':\'Suaza\',\'es\':\'Suaza\'}',18,NULL,NULL),(667,'{\'de\':\'Tarqui\',\'en\':\'Tarqui\',\'es\':\'Tarqui\'}',18,NULL,NULL),(668,'{\'de\':\'Tesalia\',\'en\':\'Tesalia\',\'es\':\'Tesalia\'}',18,NULL,NULL),(669,'{\'de\':\'Tello\',\'en\':\'Tello\',\'es\':\'Tello\'}',18,NULL,NULL),(670,'{\'de\':\'Teruel\',\'en\':\'Teruel\',\'es\':\'Teruel\'}',18,NULL,NULL),(671,'{\'de\':\'Timan\\u00E1\',\'en\':\'Timan\\u00E1\',\'es\':\'Timan\\u00E1\'}',18,NULL,NULL),(672,'{\'de\':\'Villavieja\',\'en\':\'Villavieja\',\'es\':\'Villavieja\'}',18,NULL,NULL),(673,'{\'de\':\'Yaguar\\u00E1\',\'en\':\'Yaguar\\u00E1\',\'es\':\'Yaguar\\u00E1\'}',18,NULL,NULL),(674,'{\'de\':\'Riohacha\',\'en\':\'Riohacha\',\'es\':\'Riohacha\'}',19,NULL,NULL),(675,'{\'de\':\'Albania\',\'en\':\'Albania\',\'es\':\'Albania\'}',19,NULL,NULL),(676,'{\'de\':\'Barrancas\',\'en\':\'Barrancas\',\'es\':\'Barrancas\'}',19,NULL,NULL),(677,'{\'de\':\'Dibulla\',\'en\':\'Dibulla\',\'es\':\'Dibulla\'}',19,NULL,NULL),(678,'{\'de\':\'Distracci\\u00F3n\',\'en\':\'Distracci\\u00F3n\',\'es\':\'Distracci\\u00F3n\'}',19,NULL,NULL),(679,'{\'de\':\'El Molino\',\'en\':\'El Molino\',\'es\':\'El Molino\'}',19,NULL,NULL),(680,'{\'de\':\'Fonseca\',\'en\':\'Fonseca\',\'es\':\'Fonseca\'}',19,NULL,NULL),(681,'{\'de\':\'Hatonuevo\',\'en\':\'Hatonuevo\',\'es\':\'Hatonuevo\'}',19,NULL,NULL),(682,'{\'de\':\'La Jagua Del Pilar\',\'en\':\'La Jagua Del Pilar\',\'es\':\'La Jagua Del Pilar\'}',19,NULL,NULL),(683,'{\'de\':\'Maicao\',\'en\':\'Maicao\',\'es\':\'Maicao\'}',19,NULL,NULL),(684,'{\'de\':\'Manaure\',\'en\':\'Manaure\',\'es\':\'Manaure\'}',19,NULL,NULL),(685,'{\'de\':\'San Juan Del Cesar\',\'en\':\'San Juan Del Cesar\',\'es\':\'San Juan Del Cesar\'}',19,NULL,NULL),(686,'{\'de\':\'Uribia\',\'en\':\'Uribia\',\'es\':\'Uribia\'}',19,NULL,NULL),(687,'{\'de\':\'Urumita\',\'en\':\'Urumita\',\'es\':\'Urumita\'}',19,NULL,NULL),(688,'{\'de\':\'Villanueva\',\'en\':\'Villanueva\',\'es\':\'Villanueva\'}',19,NULL,NULL),(689,'{\'de\':\'Santa Marta\',\'en\':\'Santa Marta\',\'es\':\'Santa Marta\'}',20,NULL,NULL),(690,'{\'de\':\'Algarrobo\',\'en\':\'Algarrobo\',\'es\':\'Algarrobo\'}',20,NULL,NULL),(691,'{\'de\':\'Aracataca\',\'en\':\'Aracataca\',\'es\':\'Aracataca\'}',20,NULL,NULL),(692,'{\'de\':\'Ariguan\\u00ED\',\'en\':\'Ariguan\\u00ED\',\'es\':\'Ariguan\\u00ED\'}',20,NULL,NULL),(693,'{\'de\':\'Cerro De San Antonio\',\'en\':\'Cerro De San Antonio\',\'es\':\'Cerro De San Antonio\'}',20,NULL,NULL),(694,'{\'de\':\'Chivolo\',\'en\':\'Chivolo\',\'es\':\'Chivolo\'}',20,NULL,NULL),(695,'{\'de\':\'Ci\\u00E9naga\',\'en\':\'Ci\\u00E9naga\',\'es\':\'Ci\\u00E9naga\'}',20,NULL,NULL),(696,'{\'de\':\'Concordia\',\'en\':\'Concordia\',\'es\':\'Concordia\'}',20,NULL,NULL),(697,'{\'de\':\'El Banco\',\'en\':\'El Banco\',\'es\':\'El Banco\'}',20,NULL,NULL),(698,'{\'de\':\'El Pi\\u00F1\\u00F3n\',\'en\':\'El Pi\\u00F1\\u00F3n\',\'es\':\'El Pi\\u00F1\\u00F3n\'}',20,NULL,NULL),(699,'{\'de\':\'El Ret\\u00E9n\',\'en\':\'El Ret\\u00E9n\',\'es\':\'El Ret\\u00E9n\'}',20,NULL,NULL),(700,'{\'de\':\'Fundaci\\u00F3n\',\'en\':\'Fundaci\\u00F3n\',\'es\':\'Fundaci\\u00F3n\'}',20,NULL,NULL),(701,'{\'de\':\'Guamal\',\'en\':\'Guamal\',\'es\':\'Guamal\'}',20,NULL,NULL),(702,'{\'de\':\'Nueva Granada\',\'en\':\'Nueva Granada\',\'es\':\'Nueva Granada\'}',20,NULL,NULL),(703,'{\'de\':\'Pedraza\',\'en\':\'Pedraza\',\'es\':\'Pedraza\'}',20,NULL,NULL),(704,'{\'de\':\'Piji\\u00F1o Del Carmen\',\'en\':\'Piji\\u00F1o Del Carmen\',\'es\':\'Piji\\u00F1o Del Carmen\'}',20,NULL,NULL),(705,'{\'de\':\'Pivijay\',\'en\':\'Pivijay\',\'es\':\'Pivijay\'}',20,NULL,NULL),(706,'{\'de\':\'Plato\',\'en\':\'Plato\',\'es\':\'Plato\'}',20,NULL,NULL),(707,'{\'de\':\'Puebloviejo\',\'en\':\'Puebloviejo\',\'es\':\'Puebloviejo\'}',20,NULL,NULL),(708,'{\'de\':\'Remolino\',\'en\':\'Remolino\',\'es\':\'Remolino\'}',20,NULL,NULL),(709,'{\'de\':\'Sabanas De San \\u00C1ngel\',\'en\':\'Sabanas De San \\u00C1ngel\',\'es\':\'Sabanas De San \\u00C1ngel\'}',20,NULL,NULL),(710,'{\'de\':\'Salamina\',\'en\':\'Salamina\',\'es\':\'Salamina\'}',20,NULL,NULL),(711,'{\'de\':\'San Sebasti\\u00E1n De Buenavista\',\'en\':\'San Sebasti\\u00E1n De Buenavista\',\'es\':\'San Sebasti\\u00E1n De Buenavista\'}',20,NULL,NULL),(712,'{\'de\':\'San Zen\\u00F3n\',\'en\':\'San Zen\\u00F3n\',\'es\':\'San Zen\\u00F3n\'}',20,NULL,NULL),(713,'{\'de\':\'Santa Ana\',\'en\':\'Santa Ana\',\'es\':\'Santa Ana\'}',20,NULL,NULL),(714,'{\'de\':\'Santa B\\u00E1rbara De Pinto\',\'en\':\'Santa B\\u00E1rbara De Pinto\',\'es\':\'Santa B\\u00E1rbara De Pinto\'}',20,NULL,NULL),(715,'{\'de\':\'Sitionuevo\',\'en\':\'Sitionuevo\',\'es\':\'Sitionuevo\'}',20,NULL,NULL),(716,'{\'de\':\'Tenerife\',\'en\':\'Tenerife\',\'es\':\'Tenerife\'}',20,NULL,NULL),(717,'{\'de\':\'Zapay\\u00E1n\',\'en\':\'Zapay\\u00E1n\',\'es\':\'Zapay\\u00E1n\'}',20,NULL,NULL),(718,'{\'de\':\'Zona Bananera\',\'en\':\'Zona Bananera\',\'es\':\'Zona Bananera\'}',20,NULL,NULL),(719,'{\'de\':\'Villavicencio\',\'en\':\'Villavicencio\',\'es\':\'Villavicencio\'}',21,NULL,NULL),(720,'{\'de\':\'Acac\\u00EDas\',\'en\':\'Acac\\u00EDas\',\'es\':\'Acac\\u00EDas\'}',21,NULL,NULL),(721,'{\'de\':\'Barranca De Up\\u00EDa\',\'en\':\'Barranca De Up\\u00EDa\',\'es\':\'Barranca De Up\\u00EDa\'}',21,NULL,NULL),(722,'{\'de\':\'Cabuyaro\',\'en\':\'Cabuyaro\',\'es\':\'Cabuyaro\'}',21,NULL,NULL),(723,'{\'de\':\'Castilla La Nueva\',\'en\':\'Castilla La Nueva\',\'es\':\'Castilla La Nueva\'}',21,NULL,NULL),(724,'{\'de\':\'San Luis De Cubarral\',\'en\':\'San Luis De Cubarral\',\'es\':\'San Luis De Cubarral\'}',21,NULL,NULL),(725,'{\'de\':\'Cumaral\',\'en\':\'Cumaral\',\'es\':\'Cumaral\'}',21,NULL,NULL),(726,'{\'de\':\'El Calvario\',\'en\':\'El Calvario\',\'es\':\'El Calvario\'}',21,NULL,NULL),(727,'{\'de\':\'El Castillo\',\'en\':\'El Castillo\',\'es\':\'El Castillo\'}',21,NULL,NULL),(728,'{\'de\':\'El Dorado\',\'en\':\'El Dorado\',\'es\':\'El Dorado\'}',21,NULL,NULL),(729,'{\'de\':\'Fuente De Oro\',\'en\':\'Fuente De Oro\',\'es\':\'Fuente De Oro\'}',21,NULL,NULL),(730,'{\'de\':\'Granada\',\'en\':\'Granada\',\'es\':\'Granada\'}',21,NULL,NULL),(731,'{\'de\':\'Guamal\',\'en\':\'Guamal\',\'es\':\'Guamal\'}',21,NULL,NULL),(732,'{\'de\':\'Mapirip\\u00E1n\',\'en\':\'Mapirip\\u00E1n\',\'es\':\'Mapirip\\u00E1n\'}',21,NULL,NULL),(733,'{\'de\':\'Mesetas\',\'en\':\'Mesetas\',\'es\':\'Mesetas\'}',21,NULL,NULL),(734,'{\'de\':\'La Macarena\',\'en\':\'La Macarena\',\'es\':\'La Macarena\'}',21,NULL,NULL),(735,'{\'de\':\'Uribe\',\'en\':\'Uribe\',\'es\':\'Uribe\'}',21,NULL,NULL),(736,'{\'de\':\'Lejan\\u00EDas\',\'en\':\'Lejan\\u00EDas\',\'es\':\'Lejan\\u00EDas\'}',21,NULL,NULL),(737,'{\'de\':\'Puerto Concordia\',\'en\':\'Puerto Concordia\',\'es\':\'Puerto Concordia\'}',21,NULL,NULL),(738,'{\'de\':\'Puerto Gait\\u00E1n\',\'en\':\'Puerto Gait\\u00E1n\',\'es\':\'Puerto Gait\\u00E1n\'}',21,NULL,NULL),(739,'{\'de\':\'Puerto L\\u00F3pez\',\'en\':\'Puerto L\\u00F3pez\',\'es\':\'Puerto L\\u00F3pez\'}',21,NULL,NULL),(740,'{\'de\':\'Puerto Lleras\',\'en\':\'Puerto Lleras\',\'es\':\'Puerto Lleras\'}',21,NULL,NULL),(741,'{\'de\':\'Puerto Rico\',\'en\':\'Puerto Rico\',\'es\':\'Puerto Rico\'}',21,NULL,NULL),(742,'{\'de\':\'Restrepo\',\'en\':\'Restrepo\',\'es\':\'Restrepo\'}',21,NULL,NULL),(743,'{\'de\':\'San Carlos De Guaroa\',\'en\':\'San Carlos De Guaroa\',\'es\':\'San Carlos De Guaroa\'}',21,NULL,NULL),(744,'{\'de\':\'San Juan De Arama\',\'en\':\'San Juan De Arama\',\'es\':\'San Juan De Arama\'}',21,NULL,NULL),(745,'{\'de\':\'San Juanito\',\'en\':\'San Juanito\',\'es\':\'San Juanito\'}',21,NULL,NULL),(746,'{\'de\':\'San Mart\\u00EDn\',\'en\':\'San Mart\\u00EDn\',\'es\':\'San Mart\\u00EDn\'}',21,NULL,NULL),(747,'{\'de\':\'Vistahermosa\',\'en\':\'Vistahermosa\',\'es\':\'Vistahermosa\'}',21,NULL,NULL),(748,'{\'de\':\'Pasto\',\'en\':\'Pasto\',\'es\':\'Pasto\'}',22,NULL,NULL),(749,'{\'de\':\'Alb\\u00E1n\',\'en\':\'Alb\\u00E1n\',\'es\':\'Alb\\u00E1n\'}',22,NULL,NULL),(750,'{\'de\':\'Aldana\',\'en\':\'Aldana\',\'es\':\'Aldana\'}',22,NULL,NULL),(751,'{\'de\':\'Ancuy\\u00E1\',\'en\':\'Ancuy\\u00E1\',\'es\':\'Ancuy\\u00E1\'}',22,NULL,NULL),(752,'{\'de\':\'Arboleda\',\'en\':\'Arboleda\',\'es\':\'Arboleda\'}',22,NULL,NULL),(753,'{\'de\':\'Barbacoas\',\'en\':\'Barbacoas\',\'es\':\'Barbacoas\'}',22,NULL,NULL),(754,'{\'de\':\'Bel\\u00E9n\',\'en\':\'Bel\\u00E9n\',\'es\':\'Bel\\u00E9n\'}',22,NULL,NULL),(755,'{\'de\':\'Buesaco\',\'en\':\'Buesaco\',\'es\':\'Buesaco\'}',22,NULL,NULL),(756,'{\'de\':\'Col\\u00F3n\',\'en\':\'Col\\u00F3n\',\'es\':\'Col\\u00F3n\'}',22,NULL,NULL),(757,'{\'de\':\'Consac\\u00E1\',\'en\':\'Consac\\u00E1\',\'es\':\'Consac\\u00E1\'}',22,NULL,NULL),(758,'{\'de\':\'Contadero\',\'en\':\'Contadero\',\'es\':\'Contadero\'}',22,NULL,NULL),(759,'{\'de\':\'C\\u00F3rdoba\',\'en\':\'C\\u00F3rdoba\',\'es\':\'C\\u00F3rdoba\'}',22,NULL,NULL),(760,'{\'de\':\'Cuasp\\u00FAd\',\'en\':\'Cuasp\\u00FAd\',\'es\':\'Cuasp\\u00FAd\'}',22,NULL,NULL),(761,'{\'de\':\'Cumbal\',\'en\':\'Cumbal\',\'es\':\'Cumbal\'}',22,NULL,NULL),(762,'{\'de\':\'Cumbitara\',\'en\':\'Cumbitara\',\'es\':\'Cumbitara\'}',22,NULL,NULL),(763,'{\'de\':\'Chachag\\u00FC\\u00ED\',\'en\':\'Chachag\\u00FC\\u00ED\',\'es\':\'Chachag\\u00FC\\u00ED\'}',22,NULL,NULL),(764,'{\'de\':\'El Charco\',\'en\':\'El Charco\',\'es\':\'El Charco\'}',22,NULL,NULL),(765,'{\'de\':\'El Pe\\u00F1ol\',\'en\':\'El Pe\\u00F1ol\',\'es\':\'El Pe\\u00F1ol\'}',22,NULL,NULL),(766,'{\'de\':\'El Rosario\',\'en\':\'El Rosario\',\'es\':\'El Rosario\'}',22,NULL,NULL),(767,'{\'de\':\'El Tabl\\u00F3n De G\\u00F3mez\',\'en\':\'El Tabl\\u00F3n De G\\u00F3mez\',\'es\':\'El Tabl\\u00F3n De G\\u00F3mez\'}',22,NULL,NULL),(768,'{\'de\':\'El Tambo\',\'en\':\'El Tambo\',\'es\':\'El Tambo\'}',22,NULL,NULL),(769,'{\'de\':\'Funes\',\'en\':\'Funes\',\'es\':\'Funes\'}',22,NULL,NULL),(770,'{\'de\':\'Guachucal\',\'en\':\'Guachucal\',\'es\':\'Guachucal\'}',22,NULL,NULL),(771,'{\'de\':\'Guaitarilla\',\'en\':\'Guaitarilla\',\'es\':\'Guaitarilla\'}',22,NULL,NULL),(772,'{\'de\':\'Gualmat\\u00E1n\',\'en\':\'Gualmat\\u00E1n\',\'es\':\'Gualmat\\u00E1n\'}',22,NULL,NULL),(773,'{\'de\':\'Iles\',\'en\':\'Iles\',\'es\':\'Iles\'}',22,NULL,NULL),(774,'{\'de\':\'Imu\\u00E9s\',\'en\':\'Imu\\u00E9s\',\'es\':\'Imu\\u00E9s\'}',22,NULL,NULL),(775,'{\'de\':\'Ipiales\',\'en\':\'Ipiales\',\'es\':\'Ipiales\'}',22,NULL,NULL),(776,'{\'de\':\'La Cruz\',\'en\':\'La Cruz\',\'es\':\'La Cruz\'}',22,NULL,NULL),(777,'{\'de\':\'La Florida\',\'en\':\'La Florida\',\'es\':\'La Florida\'}',22,NULL,NULL),(778,'{\'de\':\'La Llanada\',\'en\':\'La Llanada\',\'es\':\'La Llanada\'}',22,NULL,NULL),(779,'{\'de\':\'La Tola\',\'en\':\'La Tola\',\'es\':\'La Tola\'}',22,NULL,NULL),(780,'{\'de\':\'La Uni\\u00F3n\',\'en\':\'La Uni\\u00F3n\',\'es\':\'La Uni\\u00F3n\'}',22,NULL,NULL),(781,'{\'de\':\'Leiva\',\'en\':\'Leiva\',\'es\':\'Leiva\'}',22,NULL,NULL),(782,'{\'de\':\'Linares\',\'en\':\'Linares\',\'es\':\'Linares\'}',22,NULL,NULL),(783,'{\'de\':\'Los Andes\',\'en\':\'Los Andes\',\'es\':\'Los Andes\'}',22,NULL,NULL),(784,'{\'de\':\'Mag\\u00FC\\u00ED\',\'en\':\'Mag\\u00FC\\u00ED\',\'es\':\'Mag\\u00FC\\u00ED\'}',22,NULL,NULL),(785,'{\'de\':\'Mallama\',\'en\':\'Mallama\',\'es\':\'Mallama\'}',22,NULL,NULL),(786,'{\'de\':\'Mosquera\',\'en\':\'Mosquera\',\'es\':\'Mosquera\'}',22,NULL,NULL),(787,'{\'de\':\'Nari\\u00F1o\',\'en\':\'Nari\\u00F1o\',\'es\':\'Nari\\u00F1o\'}',22,NULL,NULL),(788,'{\'de\':\'Olaya Herrera\',\'en\':\'Olaya Herrera\',\'es\':\'Olaya Herrera\'}',22,NULL,NULL),(789,'{\'de\':\'Ospina\',\'en\':\'Ospina\',\'es\':\'Ospina\'}',22,NULL,NULL),(790,'{\'de\':\'Francisco Pizarro\',\'en\':\'Francisco Pizarro\',\'es\':\'Francisco Pizarro\'}',22,NULL,NULL),(791,'{\'de\':\'Policarpa\',\'en\':\'Policarpa\',\'es\':\'Policarpa\'}',22,NULL,NULL),(792,'{\'de\':\'Potos\\u00ED\',\'en\':\'Potos\\u00ED\',\'es\':\'Potos\\u00ED\'}',22,NULL,NULL),(793,'{\'de\':\'Providencia\',\'en\':\'Providencia\',\'es\':\'Providencia\'}',22,NULL,NULL),(794,'{\'de\':\'Puerres\',\'en\':\'Puerres\',\'es\':\'Puerres\'}',22,NULL,NULL),(795,'{\'de\':\'Pupiales\',\'en\':\'Pupiales\',\'es\':\'Pupiales\'}',22,NULL,NULL),(796,'{\'de\':\'Ricaurte\',\'en\':\'Ricaurte\',\'es\':\'Ricaurte\'}',22,NULL,NULL),(797,'{\'de\':\'Roberto Pay\\u00E1n\',\'en\':\'Roberto Pay\\u00E1n\',\'es\':\'Roberto Pay\\u00E1n\'}',22,NULL,NULL),(798,'{\'de\':\'Samaniego\',\'en\':\'Samaniego\',\'es\':\'Samaniego\'}',22,NULL,NULL),(799,'{\'de\':\'Sandon\\u00E1\',\'en\':\'Sandon\\u00E1\',\'es\':\'Sandon\\u00E1\'}',22,NULL,NULL),(800,'{\'de\':\'San Bernardo\',\'en\':\'San Bernardo\',\'es\':\'San Bernardo\'}',22,NULL,NULL),(801,'{\'de\':\'San Lorenzo\',\'en\':\'San Lorenzo\',\'es\':\'San Lorenzo\'}',22,NULL,NULL),(802,'{\'de\':\'San Pablo\',\'en\':\'San Pablo\',\'es\':\'San Pablo\'}',22,NULL,NULL),(803,'{\'de\':\'San Pedro De Cartago\',\'en\':\'San Pedro De Cartago\',\'es\':\'San Pedro De Cartago\'}',22,NULL,NULL),(804,'{\'de\':\'Santa B\\u00E1rbara\',\'en\':\'Santa B\\u00E1rbara\',\'es\':\'Santa B\\u00E1rbara\'}',22,NULL,NULL),(805,'{\'de\':\'Santacruz\',\'en\':\'Santacruz\',\'es\':\'Santacruz\'}',22,NULL,NULL),(806,'{\'de\':\'Sapuyes\',\'en\':\'Sapuyes\',\'es\':\'Sapuyes\'}',22,NULL,NULL),(807,'{\'de\':\'Taminango\',\'en\':\'Taminango\',\'es\':\'Taminango\'}',22,NULL,NULL),(808,'{\'de\':\'Tangua\',\'en\':\'Tangua\',\'es\':\'Tangua\'}',22,NULL,NULL),(809,'{\'de\':\'San Andr\\u00E9s De Tumaco\',\'en\':\'San Andr\\u00E9s De Tumaco\',\'es\':\'San Andr\\u00E9s De Tumaco\'}',22,NULL,NULL),(810,'{\'de\':\'T\\u00FAquerres\',\'en\':\'T\\u00FAquerres\',\'es\':\'T\\u00FAquerres\'}',22,NULL,NULL),(811,'{\'de\':\'Yacuanquer\',\'en\':\'Yacuanquer\',\'es\':\'Yacuanquer\'}',22,NULL,NULL),(812,'{\'de\':\'C\\u00FAcuta\',\'en\':\'C\\u00FAcuta\',\'es\':\'C\\u00FAcuta\'}',23,NULL,NULL),(813,'{\'de\':\'\\u00C1brego\',\'en\':\'\\u00C1brego\',\'es\':\'\\u00C1brego\'}',23,NULL,NULL),(814,'{\'de\':\'Arboledas\',\'en\':\'Arboledas\',\'es\':\'Arboledas\'}',23,NULL,NULL),(815,'{\'de\':\'Bochalema\',\'en\':\'Bochalema\',\'es\':\'Bochalema\'}',23,NULL,NULL),(816,'{\'de\':\'Bucarasica\',\'en\':\'Bucarasica\',\'es\':\'Bucarasica\'}',23,NULL,NULL),(817,'{\'de\':\'C\\u00E1cota\',\'en\':\'C\\u00E1cota\',\'es\':\'C\\u00E1cota\'}',23,NULL,NULL),(818,'{\'de\':\'C\\u00E1chira\',\'en\':\'C\\u00E1chira\',\'es\':\'C\\u00E1chira\'}',23,NULL,NULL),(819,'{\'de\':\'Chin\\u00E1cota\',\'en\':\'Chin\\u00E1cota\',\'es\':\'Chin\\u00E1cota\'}',23,NULL,NULL),(820,'{\'de\':\'Chitag\\u00E1\',\'en\':\'Chitag\\u00E1\',\'es\':\'Chitag\\u00E1\'}',23,NULL,NULL),(821,'{\'de\':\'Convenci\\u00F3n\',\'en\':\'Convenci\\u00F3n\',\'es\':\'Convenci\\u00F3n\'}',23,NULL,NULL),(822,'{\'de\':\'Cucutilla\',\'en\':\'Cucutilla\',\'es\':\'Cucutilla\'}',23,NULL,NULL),(823,'{\'de\':\'Durania\',\'en\':\'Durania\',\'es\':\'Durania\'}',23,NULL,NULL),(824,'{\'de\':\'El Carmen\',\'en\':\'El Carmen\',\'es\':\'El Carmen\'}',23,NULL,NULL),(825,'{\'de\':\'El Tarra\',\'en\':\'El Tarra\',\'es\':\'El Tarra\'}',23,NULL,NULL),(826,'{\'de\':\'El Zulia\',\'en\':\'El Zulia\',\'es\':\'El Zulia\'}',23,NULL,NULL),(827,'{\'de\':\'Gramalote\',\'en\':\'Gramalote\',\'es\':\'Gramalote\'}',23,NULL,NULL),(828,'{\'de\':\'Hacar\\u00ED\',\'en\':\'Hacar\\u00ED\',\'es\':\'Hacar\\u00ED\'}',23,NULL,NULL),(829,'{\'de\':\'Herr\\u00E1n\',\'en\':\'Herr\\u00E1n\',\'es\':\'Herr\\u00E1n\'}',23,NULL,NULL),(830,'{\'de\':\'Labateca\',\'en\':\'Labateca\',\'es\':\'Labateca\'}',23,NULL,NULL),(831,'{\'de\':\'La Esperanza\',\'en\':\'La Esperanza\',\'es\':\'La Esperanza\'}',23,NULL,NULL),(832,'{\'de\':\'La Playa\',\'en\':\'La Playa\',\'es\':\'La Playa\'}',23,NULL,NULL),(833,'{\'de\':\'Los Patios\',\'en\':\'Los Patios\',\'es\':\'Los Patios\'}',23,NULL,NULL),(834,'{\'de\':\'Lourdes\',\'en\':\'Lourdes\',\'es\':\'Lourdes\'}',23,NULL,NULL),(835,'{\'de\':\'Mutiscua\',\'en\':\'Mutiscua\',\'es\':\'Mutiscua\'}',23,NULL,NULL),(836,'{\'de\':\'Oca\\u00F1a\',\'en\':\'Oca\\u00F1a\',\'es\':\'Oca\\u00F1a\'}',23,NULL,NULL),(837,'{\'de\':\'Pamplona\',\'en\':\'Pamplona\',\'es\':\'Pamplona\'}',23,NULL,NULL),(838,'{\'de\':\'Pamplonita\',\'en\':\'Pamplonita\',\'es\':\'Pamplonita\'}',23,NULL,NULL),(839,'{\'de\':\'Puerto Santander\',\'en\':\'Puerto Santander\',\'es\':\'Puerto Santander\'}',23,NULL,NULL),(840,'{\'de\':\'Ragonvalia\',\'en\':\'Ragonvalia\',\'es\':\'Ragonvalia\'}',23,NULL,NULL),(841,'{\'de\':\'Salazar\',\'en\':\'Salazar\',\'es\':\'Salazar\'}',23,NULL,NULL),(842,'{\'de\':\'San Calixto\',\'en\':\'San Calixto\',\'es\':\'San Calixto\'}',23,NULL,NULL),(843,'{\'de\':\'San Cayetano\',\'en\':\'San Cayetano\',\'es\':\'San Cayetano\'}',23,NULL,NULL),(844,'{\'de\':\'Santiago\',\'en\':\'Santiago\',\'es\':\'Santiago\'}',23,NULL,NULL),(845,'{\'de\':\'Sardinata\',\'en\':\'Sardinata\',\'es\':\'Sardinata\'}',23,NULL,NULL),(846,'{\'de\':\'Silos\',\'en\':\'Silos\',\'es\':\'Silos\'}',23,NULL,NULL),(847,'{\'de\':\'Teorama\',\'en\':\'Teorama\',\'es\':\'Teorama\'}',23,NULL,NULL),(848,'{\'de\':\'Tib\\u00FA\',\'en\':\'Tib\\u00FA\',\'es\':\'Tib\\u00FA\'}',23,NULL,NULL),(849,'{\'de\':\'Toledo\',\'en\':\'Toledo\',\'es\':\'Toledo\'}',23,NULL,NULL),(850,'{\'de\':\'Villa Caro\',\'en\':\'Villa Caro\',\'es\':\'Villa Caro\'}',23,NULL,NULL),(851,'{\'de\':\'Villa Del Rosario\',\'en\':\'Villa Del Rosario\',\'es\':\'Villa Del Rosario\'}',23,NULL,NULL),(852,'{\'de\':\'Armenia\',\'en\':\'Armenia\',\'es\':\'Armenia\'}',25,NULL,NULL),(853,'{\'de\':\'Buenavista\',\'en\':\'Buenavista\',\'es\':\'Buenavista\'}',25,NULL,NULL),(854,'{\'de\':\'Calarc\\u00E1\',\'en\':\'Calarc\\u00E1\',\'es\':\'Calarc\\u00E1\'}',25,NULL,NULL),(855,'{\'de\':\'Circasia\',\'en\':\'Circasia\',\'es\':\'Circasia\'}',25,NULL,NULL),(856,'{\'de\':\'C\\u00F3rdoba\',\'en\':\'C\\u00F3rdoba\',\'es\':\'C\\u00F3rdoba\'}',25,NULL,NULL),(857,'{\'de\':\'Filandia\',\'en\':\'Filandia\',\'es\':\'Filandia\'}',25,NULL,NULL),(858,'{\'de\':\'G\\u00E9nova\',\'en\':\'G\\u00E9nova\',\'es\':\'G\\u00E9nova\'}',25,NULL,NULL),(859,'{\'de\':\'La Tebaida\',\'en\':\'La Tebaida\',\'es\':\'La Tebaida\'}',25,NULL,NULL),(860,'{\'de\':\'Montenegro\',\'en\':\'Montenegro\',\'es\':\'Montenegro\'}',25,NULL,NULL),(861,'{\'de\':\'Pijao\',\'en\':\'Pijao\',\'es\':\'Pijao\'}',25,NULL,NULL),(862,'{\'de\':\'Quimbaya\',\'en\':\'Quimbaya\',\'es\':\'Quimbaya\'}',25,NULL,NULL),(863,'{\'de\':\'Salento\',\'en\':\'Salento\',\'es\':\'Salento\'}',25,NULL,NULL),(864,'{\'de\':\'Pereira\',\'en\':\'Pereira\',\'es\':\'Pereira\'}',26,NULL,NULL),(865,'{\'de\':\'Ap\\u00EDa\',\'en\':\'Ap\\u00EDa\',\'es\':\'Ap\\u00EDa\'}',26,NULL,NULL),(866,'{\'de\':\'Balboa\',\'en\':\'Balboa\',\'es\':\'Balboa\'}',26,NULL,NULL),(867,'{\'de\':\'Bel\\u00E9n De Umbr\\u00EDa\',\'en\':\'Bel\\u00E9n De Umbr\\u00EDa\',\'es\':\'Bel\\u00E9n De Umbr\\u00EDa\'}',26,NULL,NULL),(868,'{\'de\':\'Dosquebradas\',\'en\':\'Dosquebradas\',\'es\':\'Dosquebradas\'}',26,NULL,NULL),(869,'{\'de\':\'Gu\\u00E1tica\',\'en\':\'Gu\\u00E1tica\',\'es\':\'Gu\\u00E1tica\'}',26,NULL,NULL),(870,'{\'de\':\'La Celia\',\'en\':\'La Celia\',\'es\':\'La Celia\'}',26,NULL,NULL),(871,'{\'de\':\'La Virginia\',\'en\':\'La Virginia\',\'es\':\'La Virginia\'}',26,NULL,NULL),(872,'{\'de\':\'Marsella\',\'en\':\'Marsella\',\'es\':\'Marsella\'}',26,NULL,NULL),(873,'{\'de\':\'Mistrat\\u00F3\',\'en\':\'Mistrat\\u00F3\',\'es\':\'Mistrat\\u00F3\'}',26,NULL,NULL),(874,'{\'de\':\'Pueblo Rico\',\'en\':\'Pueblo Rico\',\'es\':\'Pueblo Rico\'}',26,NULL,NULL),(875,'{\'de\':\'Quinch\\u00EDa\',\'en\':\'Quinch\\u00EDa\',\'es\':\'Quinch\\u00EDa\'}',26,NULL,NULL),(876,'{\'de\':\'Santa Rosa De Cabal\',\'en\':\'Santa Rosa De Cabal\',\'es\':\'Santa Rosa De Cabal\'}',26,NULL,NULL),(877,'{\'de\':\'Santuario\',\'en\':\'Santuario\',\'es\':\'Santuario\'}',26,NULL,NULL),(878,'{\'de\':\'Bucaramanga\',\'en\':\'Bucaramanga\',\'es\':\'Bucaramanga\'}',28,NULL,NULL),(879,'{\'de\':\'Aguada\',\'en\':\'Aguada\',\'es\':\'Aguada\'}',28,NULL,NULL),(880,'{\'de\':\'Albania\',\'en\':\'Albania\',\'es\':\'Albania\'}',28,NULL,NULL),(881,'{\'de\':\'Aratoca\',\'en\':\'Aratoca\',\'es\':\'Aratoca\'}',28,NULL,NULL),(882,'{\'de\':\'Barbosa\',\'en\':\'Barbosa\',\'es\':\'Barbosa\'}',28,NULL,NULL),(883,'{\'de\':\'Barichara\',\'en\':\'Barichara\',\'es\':\'Barichara\'}',28,NULL,NULL),(884,'{\'de\':\'Barrancabermeja\',\'en\':\'Barrancabermeja\',\'es\':\'Barrancabermeja\'}',28,NULL,NULL),(885,'{\'de\':\'Betulia\',\'en\':\'Betulia\',\'es\':\'Betulia\'}',28,NULL,NULL),(886,'{\'de\':\'Bol\\u00EDvar\',\'en\':\'Bol\\u00EDvar\',\'es\':\'Bol\\u00EDvar\'}',28,NULL,NULL),(887,'{\'de\':\'Cabrera\',\'en\':\'Cabrera\',\'es\':\'Cabrera\'}',28,NULL,NULL),(888,'{\'de\':\'California\',\'en\':\'California\',\'es\':\'California\'}',28,NULL,NULL),(889,'{\'de\':\'Capitanejo\',\'en\':\'Capitanejo\',\'es\':\'Capitanejo\'}',28,NULL,NULL),(890,'{\'de\':\'Carcas\\u00ED\',\'en\':\'Carcas\\u00ED\',\'es\':\'Carcas\\u00ED\'}',28,NULL,NULL),(891,'{\'de\':\'Cepit\\u00E1\',\'en\':\'Cepit\\u00E1\',\'es\':\'Cepit\\u00E1\'}',28,NULL,NULL),(892,'{\'de\':\'Cerrito\',\'en\':\'Cerrito\',\'es\':\'Cerrito\'}',28,NULL,NULL),(893,'{\'de\':\'Charal\\u00E1\',\'en\':\'Charal\\u00E1\',\'es\':\'Charal\\u00E1\'}',28,NULL,NULL),(894,'{\'de\':\'Charta\',\'en\':\'Charta\',\'es\':\'Charta\'}',28,NULL,NULL),(895,'{\'de\':\'Chima\',\'en\':\'Chima\',\'es\':\'Chima\'}',28,NULL,NULL),(896,'{\'de\':\'Chipat\\u00E1\',\'en\':\'Chipat\\u00E1\',\'es\':\'Chipat\\u00E1\'}',28,NULL,NULL),(897,'{\'de\':\'Cimitarra\',\'en\':\'Cimitarra\',\'es\':\'Cimitarra\'}',28,NULL,NULL),(898,'{\'de\':\'Concepci\\u00F3n\',\'en\':\'Concepci\\u00F3n\',\'es\':\'Concepci\\u00F3n\'}',28,NULL,NULL),(899,'{\'de\':\'Confines\',\'en\':\'Confines\',\'es\':\'Confines\'}',28,NULL,NULL),(900,'{\'de\':\'Contrataci\\u00F3n\',\'en\':\'Contrataci\\u00F3n\',\'es\':\'Contrataci\\u00F3n\'}',28,NULL,NULL),(901,'{\'de\':\'Coromoro\',\'en\':\'Coromoro\',\'es\':\'Coromoro\'}',28,NULL,NULL),(902,'{\'de\':\'Curit\\u00ED\',\'en\':\'Curit\\u00ED\',\'es\':\'Curit\\u00ED\'}',28,NULL,NULL),(903,'{\'de\':\'El Carmen De Chucur\\u00ED\',\'en\':\'El Carmen De Chucur\\u00ED\',\'es\':\'El Carmen De Chucur\\u00ED\'}',28,NULL,NULL),(904,'{\'de\':\'El Guacamayo\',\'en\':\'El Guacamayo\',\'es\':\'El Guacamayo\'}',28,NULL,NULL),(905,'{\'de\':\'El Pe\\u00F1\\u00F3n\',\'en\':\'El Pe\\u00F1\\u00F3n\',\'es\':\'El Pe\\u00F1\\u00F3n\'}',28,NULL,NULL),(906,'{\'de\':\'El Play\\u00F3n\',\'en\':\'El Play\\u00F3n\',\'es\':\'El Play\\u00F3n\'}',28,NULL,NULL),(907,'{\'de\':\'Encino\',\'en\':\'Encino\',\'es\':\'Encino\'}',28,NULL,NULL),(908,'{\'de\':\'Enciso\',\'en\':\'Enciso\',\'es\':\'Enciso\'}',28,NULL,NULL),(909,'{\'de\':\'Flori\\u00E1n\',\'en\':\'Flori\\u00E1n\',\'es\':\'Flori\\u00E1n\'}',28,NULL,NULL),(910,'{\'de\':\'Floridablanca\',\'en\':\'Floridablanca\',\'es\':\'Floridablanca\'}',28,NULL,NULL),(911,'{\'de\':\'Gal\\u00E1n\',\'en\':\'Gal\\u00E1n\',\'es\':\'Gal\\u00E1n\'}',28,NULL,NULL),(912,'{\'de\':\'G\\u00E1mbita\',\'en\':\'G\\u00E1mbita\',\'es\':\'G\\u00E1mbita\'}',28,NULL,NULL),(913,'{\'de\':\'Gir\\u00F3n\',\'en\':\'Gir\\u00F3n\',\'es\':\'Gir\\u00F3n\'}',28,NULL,NULL),(914,'{\'de\':\'Guaca\',\'en\':\'Guaca\',\'es\':\'Guaca\'}',28,NULL,NULL),(915,'{\'de\':\'Guadalupe\',\'en\':\'Guadalupe\',\'es\':\'Guadalupe\'}',28,NULL,NULL),(916,'{\'de\':\'Guapot\\u00E1\',\'en\':\'Guapot\\u00E1\',\'es\':\'Guapot\\u00E1\'}',28,NULL,NULL),(917,'{\'de\':\'Guavat\\u00E1\',\'en\':\'Guavat\\u00E1\',\'es\':\'Guavat\\u00E1\'}',28,NULL,NULL),(918,'{\'de\':\'G\\u00FCepsa\',\'en\':\'G\\u00FCepsa\',\'es\':\'G\\u00FCepsa\'}',28,NULL,NULL),(919,'{\'de\':\'Hato\',\'en\':\'Hato\',\'es\':\'Hato\'}',28,NULL,NULL),(920,'{\'de\':\'Jes\\u00FAs Mar\\u00EDa\',\'en\':\'Jes\\u00FAs Mar\\u00EDa\',\'es\':\'Jes\\u00FAs Mar\\u00EDa\'}',28,NULL,NULL),(921,'{\'de\':\'Jord\\u00E1n\',\'en\':\'Jord\\u00E1n\',\'es\':\'Jord\\u00E1n\'}',28,NULL,NULL),(922,'{\'de\':\'La Belleza\',\'en\':\'La Belleza\',\'es\':\'La Belleza\'}',28,NULL,NULL),(923,'{\'de\':\'Land\\u00E1zuri\',\'en\':\'Land\\u00E1zuri\',\'es\':\'Land\\u00E1zuri\'}',28,NULL,NULL),(924,'{\'de\':\'La Paz\',\'en\':\'La Paz\',\'es\':\'La Paz\'}',28,NULL,NULL),(925,'{\'de\':\'Lebrija\',\'en\':\'Lebrija\',\'es\':\'Lebrija\'}',28,NULL,NULL),(926,'{\'de\':\'Los Santos\',\'en\':\'Los Santos\',\'es\':\'Los Santos\'}',28,NULL,NULL),(927,'{\'de\':\'Macaravita\',\'en\':\'Macaravita\',\'es\':\'Macaravita\'}',28,NULL,NULL),(928,'{\'de\':\'M\\u00E1laga\',\'en\':\'M\\u00E1laga\',\'es\':\'M\\u00E1laga\'}',28,NULL,NULL),(929,'{\'de\':\'Matanza\',\'en\':\'Matanza\',\'es\':\'Matanza\'}',28,NULL,NULL),(930,'{\'de\':\'Mogotes\',\'en\':\'Mogotes\',\'es\':\'Mogotes\'}',28,NULL,NULL),(931,'{\'de\':\'Molagavita\',\'en\':\'Molagavita\',\'es\':\'Molagavita\'}',28,NULL,NULL),(932,'{\'de\':\'Ocamonte\',\'en\':\'Ocamonte\',\'es\':\'Ocamonte\'}',28,NULL,NULL),(933,'{\'de\':\'Oiba\',\'en\':\'Oiba\',\'es\':\'Oiba\'}',28,NULL,NULL),(934,'{\'de\':\'Onzaga\',\'en\':\'Onzaga\',\'es\':\'Onzaga\'}',28,NULL,NULL),(935,'{\'de\':\'Palmar\',\'en\':\'Palmar\',\'es\':\'Palmar\'}',28,NULL,NULL),(936,'{\'de\':\'Palmas Del Socorro\',\'en\':\'Palmas Del Socorro\',\'es\':\'Palmas Del Socorro\'}',28,NULL,NULL),(937,'{\'de\':\'P\\u00E1ramo\',\'en\':\'P\\u00E1ramo\',\'es\':\'P\\u00E1ramo\'}',28,NULL,NULL),(938,'{\'de\':\'Piedecuesta\',\'en\':\'Piedecuesta\',\'es\':\'Piedecuesta\'}',28,NULL,NULL),(939,'{\'de\':\'Pinchote\',\'en\':\'Pinchote\',\'es\':\'Pinchote\'}',28,NULL,NULL),(940,'{\'de\':\'Puente Nacional\',\'en\':\'Puente Nacional\',\'es\':\'Puente Nacional\'}',28,NULL,NULL),(941,'{\'de\':\'Puerto Parra\',\'en\':\'Puerto Parra\',\'es\':\'Puerto Parra\'}',28,NULL,NULL),(942,'{\'de\':\'Puerto Wilches\',\'en\':\'Puerto Wilches\',\'es\':\'Puerto Wilches\'}',28,NULL,NULL),(943,'{\'de\':\'Rionegro\',\'en\':\'Rionegro\',\'es\':\'Rionegro\'}',28,NULL,NULL),(944,'{\'de\':\'Sabana De Torres\',\'en\':\'Sabana De Torres\',\'es\':\'Sabana De Torres\'}',28,NULL,NULL),(945,'{\'de\':\'San Andr\\u00E9s\',\'en\':\'San Andr\\u00E9s\',\'es\':\'San Andr\\u00E9s\'}',28,NULL,NULL),(946,'{\'de\':\'San Benito\',\'en\':\'San Benito\',\'es\':\'San Benito\'}',28,NULL,NULL),(947,'{\'de\':\'San Gil\',\'en\':\'San Gil\',\'es\':\'San Gil\'}',28,NULL,NULL),(948,'{\'de\':\'San Joaqu\\u00EDn\',\'en\':\'San Joaqu\\u00EDn\',\'es\':\'San Joaqu\\u00EDn\'}',28,NULL,NULL),(949,'{\'de\':\'San Jos\\u00E9 De Miranda\',\'en\':\'San Jos\\u00E9 De Miranda\',\'es\':\'San Jos\\u00E9 De Miranda\'}',28,NULL,NULL),(950,'{\'de\':\'San Miguel\',\'en\':\'San Miguel\',\'es\':\'San Miguel\'}',28,NULL,NULL),(951,'{\'de\':\'San Vicente De Chucur\\u00ED\',\'en\':\'San Vicente De Chucur\\u00ED\',\'es\':\'San Vicente De Chucur\\u00ED\'}',28,NULL,NULL),(952,'{\'de\':\'Santa B\\u00E1rbara\',\'en\':\'Santa B\\u00E1rbara\',\'es\':\'Santa B\\u00E1rbara\'}',28,NULL,NULL),(953,'{\'de\':\'Santa Helena Del Op\\u00F3n\',\'en\':\'Santa Helena Del Op\\u00F3n\',\'es\':\'Santa Helena Del Op\\u00F3n\'}',28,NULL,NULL),(954,'{\'de\':\'Simacota\',\'en\':\'Simacota\',\'es\':\'Simacota\'}',28,NULL,NULL),(955,'{\'de\':\'Socorro\',\'en\':\'Socorro\',\'es\':\'Socorro\'}',28,NULL,NULL),(956,'{\'de\':\'Suaita\',\'en\':\'Suaita\',\'es\':\'Suaita\'}',28,NULL,NULL),(957,'{\'de\':\'Sucre\',\'en\':\'Sucre\',\'es\':\'Sucre\'}',28,NULL,NULL),(958,'{\'de\':\'Surat\\u00E1\',\'en\':\'Surat\\u00E1\',\'es\':\'Surat\\u00E1\'}',28,NULL,NULL),(959,'{\'de\':\'Tona\',\'en\':\'Tona\',\'es\':\'Tona\'}',28,NULL,NULL),(960,'{\'de\':\'Valle De San Jos\\u00E9\',\'en\':\'Valle De San Jos\\u00E9\',\'es\':\'Valle De San Jos\\u00E9\'}',28,NULL,NULL),(961,'{\'de\':\'V\\u00E9lez\',\'en\':\'V\\u00E9lez\',\'es\':\'V\\u00E9lez\'}',28,NULL,NULL),(962,'{\'de\':\'Vetas\',\'en\':\'Vetas\',\'es\':\'Vetas\'}',28,NULL,NULL),(963,'{\'de\':\'Villanueva\',\'en\':\'Villanueva\',\'es\':\'Villanueva\'}',28,NULL,NULL),(964,'{\'de\':\'Zapatoca\',\'en\':\'Zapatoca\',\'es\':\'Zapatoca\'}',28,NULL,NULL),(965,'{\'de\':\'Sincelejo\',\'en\':\'Sincelejo\',\'es\':\'Sincelejo\'}',29,NULL,NULL),(966,'{\'de\':\'Buenavista\',\'en\':\'Buenavista\',\'es\':\'Buenavista\'}',29,NULL,NULL),(967,'{\'de\':\'Caimito\',\'en\':\'Caimito\',\'es\':\'Caimito\'}',29,NULL,NULL),(968,'{\'de\':\'Coloso\',\'en\':\'Coloso\',\'es\':\'Coloso\'}',29,NULL,NULL),(969,'{\'de\':\'Corozal\',\'en\':\'Corozal\',\'es\':\'Corozal\'}',29,NULL,NULL),(970,'{\'de\':\'Cove\\u00F1as\',\'en\':\'Cove\\u00F1as\',\'es\':\'Cove\\u00F1as\'}',29,NULL,NULL),(971,'{\'de\':\'Chal\\u00E1n\',\'en\':\'Chal\\u00E1n\',\'es\':\'Chal\\u00E1n\'}',29,NULL,NULL),(972,'{\'de\':\'El Roble\',\'en\':\'El Roble\',\'es\':\'El Roble\'}',29,NULL,NULL),(973,'{\'de\':\'Galeras\',\'en\':\'Galeras\',\'es\':\'Galeras\'}',29,NULL,NULL),(974,'{\'de\':\'Guaranda\',\'en\':\'Guaranda\',\'es\':\'Guaranda\'}',29,NULL,NULL),(975,'{\'de\':\'La Uni\\u00F3n\',\'en\':\'La Uni\\u00F3n\',\'es\':\'La Uni\\u00F3n\'}',29,NULL,NULL),(976,'{\'de\':\'Los Palmitos\',\'en\':\'Los Palmitos\',\'es\':\'Los Palmitos\'}',29,NULL,NULL),(977,'{\'de\':\'Majagual\',\'en\':\'Majagual\',\'es\':\'Majagual\'}',29,NULL,NULL),(978,'{\'de\':\'Morroa\',\'en\':\'Morroa\',\'es\':\'Morroa\'}',29,NULL,NULL),(979,'{\'de\':\'Ovejas\',\'en\':\'Ovejas\',\'es\':\'Ovejas\'}',29,NULL,NULL),(980,'{\'de\':\'Palmito\',\'en\':\'Palmito\',\'es\':\'Palmito\'}',29,NULL,NULL),(981,'{\'de\':\'Sampu\\u00E9s\',\'en\':\'Sampu\\u00E9s\',\'es\':\'Sampu\\u00E9s\'}',29,NULL,NULL),(982,'{\'de\':\'San Benito Abad\',\'en\':\'San Benito Abad\',\'es\':\'San Benito Abad\'}',29,NULL,NULL),(983,'{\'de\':\'San Juan De Betulia\',\'en\':\'San Juan De Betulia\',\'es\':\'San Juan De Betulia\'}',29,NULL,NULL),(984,'{\'de\':\'San Marcos\',\'en\':\'San Marcos\',\'es\':\'San Marcos\'}',29,NULL,NULL),(985,'{\'de\':\'San Onofre\',\'en\':\'San Onofre\',\'es\':\'San Onofre\'}',29,NULL,NULL),(986,'{\'de\':\'San Pedro\',\'en\':\'San Pedro\',\'es\':\'San Pedro\'}',29,NULL,NULL),(987,'{\'de\':\'San Luis De Sinc\\u00E9\',\'en\':\'San Luis De Sinc\\u00E9\',\'es\':\'San Luis De Sinc\\u00E9\'}',29,NULL,NULL),(988,'{\'de\':\'Sucre\',\'en\':\'Sucre\',\'es\':\'Sucre\'}',29,NULL,NULL),(989,'{\'de\':\'Santiago De Tol\\u00FA\',\'en\':\'Santiago De Tol\\u00FA\',\'es\':\'Santiago De Tol\\u00FA\'}',29,NULL,NULL),(990,'{\'de\':\'Tol\\u00FA Viejo\',\'en\':\'Tol\\u00FA Viejo\',\'es\':\'Tol\\u00FA Viejo\'}',29,NULL,NULL),(991,'{\'de\':\'Ibagu\\u00E9\',\'en\':\'Ibagu\\u00E9\',\'es\':\'Ibagu\\u00E9\'}',30,NULL,NULL),(992,'{\'de\':\'Alpujarra\',\'en\':\'Alpujarra\',\'es\':\'Alpujarra\'}',30,NULL,NULL),(993,'{\'de\':\'Alvarado\',\'en\':\'Alvarado\',\'es\':\'Alvarado\'}',30,NULL,NULL),(994,'{\'de\':\'Ambalema\',\'en\':\'Ambalema\',\'es\':\'Ambalema\'}',30,NULL,NULL),(995,'{\'de\':\'Anzo\\u00E1tegui\',\'en\':\'Anzo\\u00E1tegui\',\'es\':\'Anzo\\u00E1tegui\'}',30,NULL,NULL),(996,'{\'de\':\'Armero Guayabal\',\'en\':\'Armero Guayabal\',\'es\':\'Armero Guayabal\'}',30,NULL,NULL),(997,'{\'de\':\'Ataco\',\'en\':\'Ataco\',\'es\':\'Ataco\'}',30,NULL,NULL),(998,'{\'de\':\'Cajamarca\',\'en\':\'Cajamarca\',\'es\':\'Cajamarca\'}',30,NULL,NULL),(999,'{\'de\':\'Carmen De Apical\\u00E1\',\'en\':\'Carmen De Apical\\u00E1\',\'es\':\'Carmen De Apical\\u00E1\'}',30,NULL,NULL),(1000,'{\'de\':\'Casabianca\',\'en\':\'Casabianca\',\'es\':\'Casabianca\'}',30,NULL,NULL),(1001,'{\'de\':\'Chaparral\',\'en\':\'Chaparral\',\'es\':\'Chaparral\'}',30,NULL,NULL),(1002,'{\'de\':\'Coello\',\'en\':\'Coello\',\'es\':\'Coello\'}',30,NULL,NULL),(1003,'{\'de\':\'Coyaima\',\'en\':\'Coyaima\',\'es\':\'Coyaima\'}',30,NULL,NULL),(1004,'{\'de\':\'Cunday\',\'en\':\'Cunday\',\'es\':\'Cunday\'}',30,NULL,NULL),(1005,'{\'de\':\'Dolores\',\'en\':\'Dolores\',\'es\':\'Dolores\'}',30,NULL,NULL),(1006,'{\'de\':\'Espinal\',\'en\':\'Espinal\',\'es\':\'Espinal\'}',30,NULL,NULL),(1007,'{\'de\':\'Falan\',\'en\':\'Falan\',\'es\':\'Falan\'}',30,NULL,NULL),(1008,'{\'de\':\'Flandes\',\'en\':\'Flandes\',\'es\':\'Flandes\'}',30,NULL,NULL),(1009,'{\'de\':\'Fresno\',\'en\':\'Fresno\',\'es\':\'Fresno\'}',30,NULL,NULL),(1010,'{\'de\':\'Guamo\',\'en\':\'Guamo\',\'es\':\'Guamo\'}',30,NULL,NULL),(1011,'{\'de\':\'Herveo\',\'en\':\'Herveo\',\'es\':\'Herveo\'}',30,NULL,NULL),(1012,'{\'de\':\'Honda\',\'en\':\'Honda\',\'es\':\'Honda\'}',30,NULL,NULL),(1013,'{\'de\':\'Icononzo\',\'en\':\'Icononzo\',\'es\':\'Icononzo\'}',30,NULL,NULL),(1014,'{\'de\':\'L\\u00E9rida\',\'en\':\'L\\u00E9rida\',\'es\':\'L\\u00E9rida\'}',30,NULL,NULL),(1015,'{\'de\':\'L\\u00EDbano\',\'en\':\'L\\u00EDbano\',\'es\':\'L\\u00EDbano\'}',30,NULL,NULL),(1016,'{\'de\':\'San Sebasti\\u00E1n De Mariquita\',\'en\':\'San Sebasti\\u00E1n De Mariquita\',\'es\':\'San Sebasti\\u00E1n De Mariquita\'}',30,NULL,NULL),(1017,'{\'de\':\'Melgar\',\'en\':\'Melgar\',\'es\':\'Melgar\'}',30,NULL,NULL),(1018,'{\'de\':\'Murillo\',\'en\':\'Murillo\',\'es\':\'Murillo\'}',30,NULL,NULL),(1019,'{\'de\':\'Natagaima\',\'en\':\'Natagaima\',\'es\':\'Natagaima\'}',30,NULL,NULL),(1020,'{\'de\':\'Ortega\',\'en\':\'Ortega\',\'es\':\'Ortega\'}',30,NULL,NULL),(1021,'{\'de\':\'Palocabildo\',\'en\':\'Palocabildo\',\'es\':\'Palocabildo\'}',30,NULL,NULL),(1022,'{\'de\':\'Piedras\',\'en\':\'Piedras\',\'es\':\'Piedras\'}',30,NULL,NULL),(1023,'{\'de\':\'Planadas\',\'en\':\'Planadas\',\'es\':\'Planadas\'}',30,NULL,NULL),(1024,'{\'de\':\'Prado\',\'en\':\'Prado\',\'es\':\'Prado\'}',30,NULL,NULL),(1025,'{\'de\':\'Purificaci\\u00F3n\',\'en\':\'Purificaci\\u00F3n\',\'es\':\'Purificaci\\u00F3n\'}',30,NULL,NULL),(1026,'{\'de\':\'Rioblanco\',\'en\':\'Rioblanco\',\'es\':\'Rioblanco\'}',30,NULL,NULL),(1027,'{\'de\':\'Roncesvalles\',\'en\':\'Roncesvalles\',\'es\':\'Roncesvalles\'}',30,NULL,NULL),(1028,'{\'de\':\'Rovira\',\'en\':\'Rovira\',\'es\':\'Rovira\'}',30,NULL,NULL),(1029,'{\'de\':\'Salda\\u00F1a\',\'en\':\'Salda\\u00F1a\',\'es\':\'Salda\\u00F1a\'}',30,NULL,NULL),(1030,'{\'de\':\'San Antonio\',\'en\':\'San Antonio\',\'es\':\'San Antonio\'}',30,NULL,NULL),(1031,'{\'de\':\'San Luis\',\'en\':\'San Luis\',\'es\':\'San Luis\'}',30,NULL,NULL),(1032,'{\'de\':\'Santa Isabel\',\'en\':\'Santa Isabel\',\'es\':\'Santa Isabel\'}',30,NULL,NULL),(1033,'{\'de\':\'Su\\u00E1rez\',\'en\':\'Su\\u00E1rez\',\'es\':\'Su\\u00E1rez\'}',30,NULL,NULL),(1034,'{\'de\':\'Valle De San Juan\',\'en\':\'Valle De San Juan\',\'es\':\'Valle De San Juan\'}',30,NULL,NULL),(1035,'{\'de\':\'Venadillo\',\'en\':\'Venadillo\',\'es\':\'Venadillo\'}',30,NULL,NULL),(1036,'{\'de\':\'Villahermosa\',\'en\':\'Villahermosa\',\'es\':\'Villahermosa\'}',30,NULL,NULL),(1037,'{\'de\':\'Villarrica\',\'en\':\'Villarrica\',\'es\':\'Villarrica\'}',30,NULL,NULL),(1038,'{\'de\':\'Cali\',\'en\':\'Cali\',\'es\':\'Cali\'}',31,3.44,-76.52),(1039,'{\'de\':\'Alcal\\u00E1\',\'en\':\'Alcal\\u00E1\',\'es\':\'Alcal\\u00E1\'}',31,NULL,NULL),(1040,'{\'de\':\'Andaluc\\u00EDa\',\'en\':\'Andaluc\\u00EDa\',\'es\':\'Andaluc\\u00EDa\'}',31,NULL,NULL),(1041,'{\'de\':\'Ansermanuevo\',\'en\':\'Ansermanuevo\',\'es\':\'Ansermanuevo\'}',31,NULL,NULL),(1042,'{\'de\':\'Argelia\',\'en\':\'Argelia\',\'es\':\'Argelia\'}',31,NULL,NULL),(1043,'{\'de\':\'Bol\\u00EDvar\',\'en\':\'Bol\\u00EDvar\',\'es\':\'Bol\\u00EDvar\'}',31,NULL,NULL),(1044,'{\'de\':\'Buenaventura\',\'en\':\'Buenaventura\',\'es\':\'Buenaventura\'}',31,NULL,NULL),(1045,'{\'de\':\'Guadalajara De Buga\',\'en\':\'Guadalajara De Buga\',\'es\':\'Guadalajara De Buga\'}',31,NULL,NULL),(1046,'{\'de\':\'Bugalagrande\',\'en\':\'Bugalagrande\',\'es\':\'Bugalagrande\'}',31,NULL,NULL),(1047,'{\'de\':\'Caicedonia\',\'en\':\'Caicedonia\',\'es\':\'Caicedonia\'}',31,NULL,NULL),(1048,'{\'de\':\'Calima\',\'en\':\'Calima\',\'es\':\'Calima\'}',31,NULL,NULL),(1049,'{\'de\':\'Candelaria\',\'en\':\'Candelaria\',\'es\':\'Candelaria\'}',31,NULL,NULL),(1050,'{\'de\':\'Cartago\',\'en\':\'Cartago\',\'es\':\'Cartago\'}',31,NULL,NULL),(1051,'{\'de\':\'Dagua\',\'en\':\'Dagua\',\'es\':\'Dagua\'}',31,NULL,NULL),(1052,'{\'de\':\'El \\u00C1guila\',\'en\':\'El \\u00C1guila\',\'es\':\'El \\u00C1guila\'}',31,NULL,NULL),(1053,'{\'de\':\'El Cairo\',\'en\':\'El Cairo\',\'es\':\'El Cairo\'}',31,NULL,NULL),(1054,'{\'de\':\'El Cerrito\',\'en\':\'El Cerrito\',\'es\':\'El Cerrito\'}',31,NULL,NULL),(1055,'{\'de\':\'El Dovio\',\'en\':\'El Dovio\',\'es\':\'El Dovio\'}',31,NULL,NULL),(1056,'{\'de\':\'Florida\',\'en\':\'Florida\',\'es\':\'Florida\'}',31,NULL,NULL),(1057,'{\'de\':\'Ginebra\',\'en\':\'Ginebra\',\'es\':\'Ginebra\'}',31,NULL,NULL),(1058,'{\'de\':\'Guacar\\u00ED\',\'en\':\'Guacar\\u00ED\',\'es\':\'Guacar\\u00ED\'}',31,NULL,NULL),(1059,'{\'de\':\'Jamund\\u00ED\',\'en\':\'Jamund\\u00ED\',\'es\':\'Jamund\\u00ED\'}',31,NULL,NULL),(1060,'{\'de\':\'La Cumbre\',\'en\':\'La Cumbre\',\'es\':\'La Cumbre\'}',31,NULL,NULL),(1061,'{\'de\':\'La Uni\\u00F3n\',\'en\':\'La Uni\\u00F3n\',\'es\':\'La Uni\\u00F3n\'}',31,NULL,NULL),(1062,'{\'de\':\'La Victoria\',\'en\':\'La Victoria\',\'es\':\'La Victoria\'}',31,NULL,NULL),(1063,'{\'de\':\'Obando\',\'en\':\'Obando\',\'es\':\'Obando\'}',31,NULL,NULL),(1064,'{\'de\':\'Palmira\',\'en\':\'Palmira\',\'es\':\'Palmira\'}',31,NULL,NULL),(1065,'{\'de\':\'Pradera\',\'en\':\'Pradera\',\'es\':\'Pradera\'}',31,NULL,NULL),(1066,'{\'de\':\'Restrepo\',\'en\':\'Restrepo\',\'es\':\'Restrepo\'}',31,NULL,NULL),(1067,'{\'de\':\'Riofr\\u00EDo\',\'en\':\'Riofr\\u00EDo\',\'es\':\'Riofr\\u00EDo\'}',31,NULL,NULL),(1068,'{\'de\':\'Roldanillo\',\'en\':\'Roldanillo\',\'es\':\'Roldanillo\'}',31,NULL,NULL),(1069,'{\'de\':\'San Pedro\',\'en\':\'San Pedro\',\'es\':\'San Pedro\'}',31,NULL,NULL),(1070,'{\'de\':\'Sevilla\',\'en\':\'Sevilla\',\'es\':\'Sevilla\'}',31,NULL,NULL),(1071,'{\'de\':\'Toro\',\'en\':\'Toro\',\'es\':\'Toro\'}',31,NULL,NULL),(1072,'{\'de\':\'Trujillo\',\'en\':\'Trujillo\',\'es\':\'Trujillo\'}',31,NULL,NULL),(1073,'{\'de\':\'Tulu\\u00E1\',\'en\':\'Tulu\\u00E1\',\'es\':\'Tulu\\u00E1\'}',31,NULL,NULL),(1074,'{\'de\':\'Ulloa\',\'en\':\'Ulloa\',\'es\':\'Ulloa\'}',31,NULL,NULL),(1075,'{\'de\':\'Versalles\',\'en\':\'Versalles\',\'es\':\'Versalles\'}',31,NULL,NULL),(1076,'{\'de\':\'Vijes\',\'en\':\'Vijes\',\'es\':\'Vijes\'}',31,NULL,NULL),(1077,'{\'de\':\'Yotoco\',\'en\':\'Yotoco\',\'es\':\'Yotoco\'}',31,NULL,NULL),(1078,'{\'de\':\'Yumbo\',\'en\':\'Yumbo\',\'es\':\'Yumbo\'}',31,NULL,NULL),(1079,'{\'de\':\'Zarzal\',\'en\':\'Zarzal\',\'es\':\'Zarzal\'}',31,NULL,NULL),(1080,'{\'de\':\'Arauca\',\'en\':\'Arauca\',\'es\':\'Arauca\'}',3,NULL,NULL),(1081,'{\'de\':\'Arauquita\',\'en\':\'Arauquita\',\'es\':\'Arauquita\'}',3,NULL,NULL),(1082,'{\'de\':\'Cravo Norte\',\'en\':\'Cravo Norte\',\'es\':\'Cravo Norte\'}',3,NULL,NULL),(1083,'{\'de\':\'Fortul\',\'en\':\'Fortul\',\'es\':\'Fortul\'}',3,NULL,NULL),(1084,'{\'de\':\'Puerto Rond\\u00F3n\',\'en\':\'Puerto Rond\\u00F3n\',\'es\':\'Puerto Rond\\u00F3n\'}',3,NULL,NULL),(1085,'{\'de\':\'Saravena\',\'en\':\'Saravena\',\'es\':\'Saravena\'}',3,NULL,NULL),(1086,'{\'de\':\'Tame\',\'en\':\'Tame\',\'es\':\'Tame\'}',3,NULL,NULL),(1087,'{\'de\':\'Yopal\',\'en\':\'Yopal\',\'es\':\'Yopal\'}',10,NULL,NULL),(1088,'{\'de\':\'Aguazul\',\'en\':\'Aguazul\',\'es\':\'Aguazul\'}',10,NULL,NULL),(1089,'{\'de\':\'Ch\\u00E1meza\',\'en\':\'Ch\\u00E1meza\',\'es\':\'Ch\\u00E1meza\'}',10,NULL,NULL),(1090,'{\'de\':\'Hato Corozal\',\'en\':\'Hato Corozal\',\'es\':\'Hato Corozal\'}',10,NULL,NULL),(1091,'{\'de\':\'La Salina\',\'en\':\'La Salina\',\'es\':\'La Salina\'}',10,NULL,NULL),(1092,'{\'de\':\'Man\\u00ED\',\'en\':\'Man\\u00ED\',\'es\':\'Man\\u00ED\'}',10,NULL,NULL),(1093,'{\'de\':\'Monterrey\',\'en\':\'Monterrey\',\'es\':\'Monterrey\'}',10,NULL,NULL),(1094,'{\'de\':\'Nunch\\u00EDa\',\'en\':\'Nunch\\u00EDa\',\'es\':\'Nunch\\u00EDa\'}',10,NULL,NULL),(1095,'{\'de\':\'Orocu\\u00E9\',\'en\':\'Orocu\\u00E9\',\'es\':\'Orocu\\u00E9\'}',10,NULL,NULL),(1096,'{\'de\':\'Paz De Ariporo\',\'en\':\'Paz De Ariporo\',\'es\':\'Paz De Ariporo\'}',10,NULL,NULL),(1097,'{\'de\':\'Pore\',\'en\':\'Pore\',\'es\':\'Pore\'}',10,NULL,NULL),(1098,'{\'de\':\'Recetor\',\'en\':\'Recetor\',\'es\':\'Recetor\'}',10,NULL,NULL),(1099,'{\'de\':\'Sabanalarga\',\'en\':\'Sabanalarga\',\'es\':\'Sabanalarga\'}',10,NULL,NULL),(1100,'{\'de\':\'S\\u00E1cama\',\'en\':\'S\\u00E1cama\',\'es\':\'S\\u00E1cama\'}',10,NULL,NULL),(1101,'{\'de\':\'San Luis De Palenque\',\'en\':\'San Luis De Palenque\',\'es\':\'San Luis De Palenque\'}',10,NULL,NULL),(1102,'{\'de\':\'T\\u00E1mara\',\'en\':\'T\\u00E1mara\',\'es\':\'T\\u00E1mara\'}',10,NULL,NULL),(1103,'{\'de\':\'Tauramena\',\'en\':\'Tauramena\',\'es\':\'Tauramena\'}',10,NULL,NULL),(1104,'{\'de\':\'Trinidad\',\'en\':\'Trinidad\',\'es\':\'Trinidad\'}',10,NULL,NULL),(1105,'{\'de\':\'Villanueva\',\'en\':\'Villanueva\',\'es\':\'Villanueva\'}',10,NULL,NULL),(1106,'{\'de\':\'Mocoa\',\'en\':\'Mocoa\',\'es\':\'Mocoa\'}',24,NULL,NULL),(1107,'{\'de\':\'Col\\u00F3n\',\'en\':\'Col\\u00F3n\',\'es\':\'Col\\u00F3n\'}',24,NULL,NULL),(1108,'{\'de\':\'Orito\',\'en\':\'Orito\',\'es\':\'Orito\'}',24,NULL,NULL),(1109,'{\'de\':\'Puerto As\\u00EDs\',\'en\':\'Puerto As\\u00EDs\',\'es\':\'Puerto As\\u00EDs\'}',24,NULL,NULL),(1110,'{\'de\':\'Puerto Caicedo\',\'en\':\'Puerto Caicedo\',\'es\':\'Puerto Caicedo\'}',24,NULL,NULL),(1111,'{\'de\':\'Puerto Guzm\\u00E1n\',\'en\':\'Puerto Guzm\\u00E1n\',\'es\':\'Puerto Guzm\\u00E1n\'}',24,NULL,NULL),(1112,'{\'de\':\'Puerto Legu\\u00EDzamo\',\'en\':\'Puerto Legu\\u00EDzamo\',\'es\':\'Puerto Legu\\u00EDzamo\'}',24,NULL,NULL),(1113,'{\'de\':\'Sibundoy\',\'en\':\'Sibundoy\',\'es\':\'Sibundoy\'}',24,NULL,NULL),(1114,'{\'de\':\'San Francisco\',\'en\':\'San Francisco\',\'es\':\'San Francisco\'}',24,NULL,NULL),(1115,'{\'de\':\'San Miguel\',\'en\':\'San Miguel\',\'es\':\'San Miguel\'}',24,NULL,NULL),(1116,'{\'de\':\'Santiago\',\'en\':\'Santiago\',\'es\':\'Santiago\'}',24,NULL,NULL),(1117,'{\'de\':\'Valle Del Guamuez\',\'en\':\'Valle Del Guamuez\',\'es\':\'Valle Del Guamuez\'}',24,NULL,NULL),(1118,'{\'de\':\'Villagarz\\u00F3n\',\'en\':\'Villagarz\\u00F3n\',\'es\':\'Villagarz\\u00F3n\'}',24,NULL,NULL),(1119,'{\'de\':\'San Andr\\u00E9s\',\'en\':\'San Andr\\u00E9s\',\'es\':\'San Andr\\u00E9s\'}',27,NULL,NULL),(1120,'{\'de\':\'Providencia\',\'en\':\'Providencia\',\'es\':\'Providencia\'}',27,NULL,NULL),(1121,'{\'de\':\'Leticia\',\'en\':\'Leticia\',\'es\':\'Leticia\'}',1,NULL,NULL),(1122,'{\'de\':\'El Encanto\',\'en\':\'El Encanto\',\'es\':\'El Encanto\'}',1,NULL,NULL),(1123,'{\'de\':\'La Chorrera\',\'en\':\'La Chorrera\',\'es\':\'La Chorrera\'}',1,NULL,NULL),(1124,'{\'de\':\'La Pedrera\',\'en\':\'La Pedrera\',\'es\':\'La Pedrera\'}',1,NULL,NULL),(1125,'{\'de\':\'La Victoria\',\'en\':\'La Victoria\',\'es\':\'La Victoria\'}',1,NULL,NULL),(1126,'{\'de\':\'Mirit\\u00ED - Paran\\u00E1\',\'en\':\'Mirit\\u00ED - Paran\\u00E1\',\'es\':\'Mirit\\u00ED - Paran\\u00E1\'}',1,NULL,NULL),(1127,'{\'de\':\'Puerto Alegr\\u00EDa\',\'en\':\'Puerto Alegr\\u00EDa\',\'es\':\'Puerto Alegr\\u00EDa\'}',1,NULL,NULL),(1128,'{\'de\':\'Puerto Arica\',\'en\':\'Puerto Arica\',\'es\':\'Puerto Arica\'}',1,NULL,NULL),(1129,'{\'de\':\'Puerto Nari\\u00F1o\',\'en\':\'Puerto Nari\\u00F1o\',\'es\':\'Puerto Nari\\u00F1o\'}',1,NULL,NULL),(1130,'{\'de\':\'Puerto Santander\',\'en\':\'Puerto Santander\',\'es\':\'Puerto Santander\'}',1,NULL,NULL),(1131,'{\'de\':\'Tarapac\\u00E1\',\'en\':\'Tarapac\\u00E1\',\'es\':\'Tarapac\\u00E1\'}',1,NULL,NULL),(1132,'{\'de\':\'In\\u00EDrida\',\'en\':\'In\\u00EDrida\',\'es\':\'In\\u00EDrida\'}',16,NULL,NULL),(1133,'{\'de\':\'Barranco Minas\',\'en\':\'Barranco Minas\',\'es\':\'Barranco Minas\'}',16,NULL,NULL),(1134,'{\'de\':\'Mapiripana\',\'en\':\'Mapiripana\',\'es\':\'Mapiripana\'}',16,NULL,NULL),(1135,'{\'de\':\'San Felipe\',\'en\':\'San Felipe\',\'es\':\'San Felipe\'}',16,NULL,NULL),(1136,'{\'de\':\'Puerto Colombia\',\'en\':\'Puerto Colombia\',\'es\':\'Puerto Colombia\'}',16,NULL,NULL),(1137,'{\'de\':\'La Guadalupe\',\'en\':\'La Guadalupe\',\'es\':\'La Guadalupe\'}',16,NULL,NULL),(1138,'{\'de\':\'Cacahual\',\'en\':\'Cacahual\',\'es\':\'Cacahual\'}',16,NULL,NULL),(1139,'{\'de\':\'Pana Pana\',\'en\':\'Pana Pana\',\'es\':\'Pana Pana\'}',16,NULL,NULL),(1140,'{\'de\':\'Morichal\',\'en\':\'Morichal\',\'es\':\'Morichal\'}',16,NULL,NULL),(1141,'{\'de\':\'San Jos\\u00E9 Del Guaviare\',\'en\':\'San Jos\\u00E9 Del Guaviare\',\'es\':\'San Jos\\u00E9 Del Guaviare\'}',17,NULL,NULL),(1142,'{\'de\':\'Calamar\',\'en\':\'Calamar\',\'es\':\'Calamar\'}',17,NULL,NULL),(1143,'{\'de\':\'El Retorno\',\'en\':\'El Retorno\',\'es\':\'El Retorno\'}',17,NULL,NULL),(1144,'{\'de\':\'Miraflores\',\'en\':\'Miraflores\',\'es\':\'Miraflores\'}',17,NULL,NULL),(1145,'{\'de\':\'Mit\\u00FA\',\'en\':\'Mit\\u00FA\',\'es\':\'Mit\\u00FA\'}',32,NULL,NULL),(1146,'{\'de\':\'Carur\\u00FA\',\'en\':\'Carur\\u00FA\',\'es\':\'Carur\\u00FA\'}',32,NULL,NULL),(1147,'{\'de\':\'Pacoa\',\'en\':\'Pacoa\',\'es\':\'Pacoa\'}',32,NULL,NULL),(1148,'{\'de\':\'Taraira\',\'en\':\'Taraira\',\'es\':\'Taraira\'}',32,NULL,NULL),(1149,'{\'de\':\'Papunaua\',\'en\':\'Papunaua\',\'es\':\'Papunaua\'}',32,NULL,NULL),(1150,'{\'de\':\'Yavarat\\u00E9\',\'en\':\'Yavarat\\u00E9\',\'es\':\'Yavarat\\u00E9\'}',32,NULL,NULL),(1151,'{\'de\':\'Puerto Carre\\u00F1o\',\'en\':\'Puerto Carre\\u00F1o\',\'es\':\'Puerto Carre\\u00F1o\'}',33,NULL,NULL),(1152,'{\'de\':\'La Primavera\',\'en\':\'La Primavera\',\'es\':\'La Primavera\'}',33,NULL,NULL),(1153,'{\'de\':\'Santa Rosal\\u00EDa\',\'en\':\'Santa Rosal\\u00EDa\',\'es\':\'Santa Rosal\\u00EDa\'}',33,NULL,NULL),(1154,'{\'de\':\'Cumaribo\',\'en\':\'Cumaribo\',\'es\':\'Cumaribo\'}',33,NULL,NULL);
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

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
  `lang` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq__country__country_name` (`java_code`),
  UNIQUE KEY `uq__country__java_code` (`country_name`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES (1,'Colombia','CO','hallocasa.pending'),(2,'Argentina','AR','hallocasa.pending'),(3,'Chile','CL','hallocasa.pending'),(4,'Panamá','PA','hallocasa.pending'),(5,'Perú','PE','hallocasa.pending'),(6,'Canadá','CA','hallocasa.pending'),(7,'EEUU','US','hallocasa.pending'),(8,'Costa Rica','CR','hallocasa.pending'),(9,'Ecuador','EC','hallocasa.pending');
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `currency`
--

LOCK TABLES `currency` WRITE;
/*!40000 ALTER TABLE `currency` DISABLE KEYS */;
INSERT INTO `currency` VALUES (1,'{\'en\':\'Colombian Peso\',\'de\':\'Kolumbianischer Peso\',\'es\':\'Peso colombiano\'}','COP','$'),(2,'{\'en\':\'Euro\',\'de\':\'Euro\',\'es\':\'Euro\'}','EUR','$'),(3,'{\'en\':\'US Dollar\',\'de\':\'US Dollar\',\'es\':\'D\\u00F3lar estadounidense\'}','USD','$'),(4,'{\'en\':\'Canadian Dollar\',\'de\':\'Kanadischer Dollar\',\'es\':\'D\\u00F3lar canadiense\'}','CAD','$'),(5,'{\'en\':\'British Pound\',\'de\':\'Britisches Pfund\',\'es\':\'Libra brit\\u00E1nica\'}','GBP','$'),(6,'{\'en\':\'Swiss Franc\',\'de\':\'Schweizerfranken\',\'es\':\'Franco suizo\'}','CHF','$'),(7,'{\'en\':\'Australian Dollar\',\'de\':\'Australischer Dollar\',\'es\':\'Dolar australiano\'}','AUD','$');
/*!40000 ALTER TABLE `currency` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `currency_exchange_data`
--

LOCK TABLES `currency_exchange_data` WRITE;
/*!40000 ALTER TABLE `currency_exchange_data` DISABLE KEYS */;
/*!40000 ALTER TABLE `currency_exchange_data` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `dbmaintain_scripts`
--

LOCK TABLES `dbmaintain_scripts` WRITE;
/*!40000 ALTER TABLE `dbmaintain_scripts` DISABLE KEYS */;
INSERT INTO `dbmaintain_scripts` VALUES ('incremental/01_v1.1/08_@hallocasaappmig_#all_country_table_create.sql',1455412192323,'7c9afa60604139330d18e152cc437f5a','2016-09-01 19:35:07',1),('incremental/01_v1.1/09_@hallocasaappmig_#all_country_inserts.sql',1455412192325,'64477e6c9c6230c25daab467b2f1133f','2016-09-01 19:35:08',1),('incremental/01_v1.1/10_@hallocasaappmig_#all_state_table_create.sql',1455412192332,'d38b1ffa47b34387bcf18a4643c67bd8','2016-09-01 19:35:08',1),('incremental/01_v1.1/11_@hallocasaappmig_#all_user_table_create.sql',1455412192339,'d38ae89f5c7557ffb6c03b1465f6476c','2016-09-01 19:35:09',1),('incremental/01_v1.1/12_@hallocasaappmig_#all_profile_table_create.sql',1455412192347,'23edb233eb6dc7ca00cc280004a544bb','2016-09-01 19:35:09',1),('incremental/01_v1.1/13_@hallocasaappmig_#all_user_type_table_create .sql',1455412192354,'ac5280e74bc80ee729662ecc9def3b7a','2016-09-01 19:35:11',1),('incremental/01_v1.1/14_@hallocasaappmig_#all_use_case_table_inserts.sql',1455412192360,'21f93a96171812ff863a4bffa8dccd01','2016-09-01 19:35:12',1),('incremental/01_v1.1/15_@hallocasaappmig_#all_profile_table_inserts.sql',1455412192366,'846af90bc141a213095cb74df3685e1c','2016-09-01 19:35:13',1),('incremental/01_v1.1/16_@hallocasaappmig_#all_user_type_inserts.sql',1455412192373,'54426004120f19748132de56f1ebe3f8','2016-09-01 19:35:13',1),('incremental/01_v1.1/17_@hallocasaappmig_#all_state_inserts.sql',1472430867450,'d046fec95cadee71ceb46913ccc4c4bd','2016-09-01 19:35:13',1),('incremental/01_v1.1/50_@hallocasaappmig_#dev_user_inserts.sql',1455412192387,'d7178dbc62a657ab47e5b075357bfc8b','2016-09-01 19:35:14',1),('incremental/02_v1.1.1/01_@hallocasaappmig_#all_user_add_image_field.sql',1455412192393,'dcd53b86545f06bb0b2d5347705b342c','2016-09-01 19:35:14',1),('incremental/02_v1.1.1/02_@hallocasaappmig_#all_token_table_create.sql',1455412192400,'679de090fc4458eb3277ac9b042dfce3','2016-09-01 19:35:16',1),('incremental/02_v1.1.1/03_@hallocasaappmig_#all_city_table_create.sql',1455412192407,'b08e7bb6d16dd7a45bedd3a89ab7f289','2016-09-01 19:35:17',1),('incremental/02_v1.1.1/04_@hallocasaappmig_#all_user_alter_city_data.sql',1455412192414,'486982ece40da5041719dc2d724b2d20','2016-09-01 19:35:18',1),('incremental/02_v1.1.1/05_@hallocasaappmig_#all_state_add_states.sql',1455412192421,'a1b397e672e7bebb6f9855da0de0da1f','2016-09-01 19:36:11',1),('incremental/02_v1.1.1/06_@hallocasaappmig_#all_city_table_insert.sql',1461385079631,'51d17d36f65997ba9253c4ad132fea92','2016-09-01 19:36:15',1),('incremental/03_v1.2/01_@hallocasaappmig_#all_user_add_main_spoken_language.sql',1455412192440,'55270483e98e3990f99a2a7b54ce02a8','2016-09-01 19:36:26',1),('incremental/03_v1.2/02_@hallocasaappmig_#all_telephone_prefix_create_table.sql',1461384899161,'cf8590fb2685200265c7e48c7f0d552f','2016-09-01 19:36:34',1),('incremental/03_v1.2/03_@hallocasaappmig_#all_telephone_prefix_insert_data.sql',1461384899163,'1e233e84ea662f24c944b5957b4b44a1','2016-09-01 19:36:36',1),('incremental/03_v1.2/04_@hallocasaappmig_#all_telephone_create_table.sql',1461384899164,'688d7ee12a4bef42008bba2f09753aaa','2016-09-01 19:36:37',1),('incremental/03_v1.2/05_@hallocasaappmig_#all_user_add_telephone_id.sql',1461384899164,'b32a69df5f0d399210083f003c5b649a','2016-09-01 19:36:43',1),('incremental/03_v1.2/06_@hallocasaappmig_#all_user_refresh_foreign_keys.sql',1461384899166,'f2b5973cf56e3d4526fa9fcd8b2cece5','2016-09-01 19:37:09',1),('incremental/04_v1.2.1/01_@hallocasaappmig_#all_user_type_add_lawyer.sql',1461385079633,'faffd8a74be85bcc57461d382b5cdf31','2016-09-01 19:37:59',1),('incremental/04_v1.2.1/02_@hallocasaappmig_#all_state_move_bogota.sql',1461385079634,'4020ce0ea8debb7ee7daff9f75c35de0','2016-09-01 19:38:01',1),('incremental/04_v1.2.1/03_@hallocasaappmig_#all_user_type_change_ubersetzer.sql',1461385079634,'523574e22b8acc76e7ace6d430ca9949','2016-09-01 19:38:06',1),('incremental/04_v1.2.1/04_@hallocasaappmig_#all_telephone_delete_user_dependency.sql',1461385079636,'e9815ab7457a6e6cc3ec4708175b27a3','2016-09-01 19:38:08',1),('incremental/04_v1.2.1/06_@hallocasaappmig_#all_user_type_update_data.sql',1461385079637,'a41074fa38f29dfb51e402cb1e194dc0','2016-09-01 19:38:19',1),('incremental/05_v.1.3/01_@hallocasaappmig_#all_currency_create_table.sql',1472430867456,'cf0a2c99a309995ae904a017533e5fb1','2016-09-01 19:38:22',1),('incremental/05_v.1.3/02_@hallocasaappmig_#all_currency_insert_data.sql',1472430867456,'476faba1693eea02d7b458b80bc8c1b6','2016-09-01 19:38:29',1),('incremental/05_v.1.3/03_@hallocasaappmig_#all_property_type_group_create_table.sql',1472430867463,'4685c75708073f154f84737c924f37ed','2016-09-01 19:38:32',1),('incremental/05_v.1.3/04_@hallocasaappmig_#all_property_type_group_insert_data.sql',1472430867469,'35641f182ec2471d96a6b61c48a23f11','2016-09-01 19:38:39',1),('incremental/05_v.1.3/05_@hallocasaappmig_#all_property_type_create_table.sql',1472430867479,'dcb4f161be8f90b4c176496cfd8e3b31','2016-09-01 19:38:40',1),('incremental/05_v.1.3/06_@hallocasaappmig_#all_property_type_insert_data.sql',1472430867480,'46b8ba6207bcecfc798e26649d666046','2016-09-01 19:38:41',1),('incremental/05_v.1.3/07_@hallocasaappmig_#all_property_location_create_table.sql',1472430867485,'31e79fbda8f76b0e7decf9ccf8e9bed6','2016-09-01 19:38:42',1),('incremental/05_v.1.3/08_@hallocasaappmig_#all_property_location_insert_data.sql',1472430867485,'386b5a8376924f6ed209f6710c49fa00','2016-09-01 19:38:43',1),('incremental/05_v.1.3/09_@hallocasaappmig_#all_property_proposal_create_table.sql',1472430867489,'489937156f2dc191b8d3ee4766d259bd','2016-09-01 19:38:44',1),('incremental/05_v.1.3/10_@hallocasaappmig_#all_property_proposal_insert_data.sql',1472430867493,'71a073d778f94d39c0e8d1bf9f4122f4','2016-09-01 19:38:45',1),('incremental/05_v.1.3/11_@hallocasaappmig_#all_property_field_create_table.sql',1472430867497,'6c698fe37796226090d24383f6a8c226','2016-09-01 19:38:46',1),('incremental/05_v.1.3/12_@hallocasaappmig_#all_property_field_insert_data.sql',1472430867498,'2a2f8f81da55dedcce07cef0571e74ce','2016-09-01 19:38:47',1),('incremental/05_v.1.3/13_@hallocasaappmig_#all_property_field_rule_create_table.sql',1472430867501,'72de47af3b34f17676f729f906bf9bd9','2016-09-01 19:38:48',1),('incremental/05_v.1.3/14_@hallocasaappmig_#all_property_field_rule_insert_data.sql',1472430867502,'7aa4afb888c1103c986258b5c1704676','2016-09-01 19:38:51',1),('incremental/05_v.1.3/15_@hallocasaappmig_#all_property_create_table.sql',1472430867504,'b2d319b1e73f865a1b0b596660bcf8c2','2016-09-01 19:38:52',1),('incremental/05_v.1.3/16_@hallocasaappmig_#dev_property_insert_data.sql',1472430867508,'ea10be3b1eee380188fc09b77912ab6b','2016-09-01 19:38:54',1),('incremental/05_v.1.3/17_@hallocasaappmig_#all_property_selector_type_create_table.sql',1472430867512,'e929b89ddb9daacddd16f9486674e292','2016-09-01 19:38:54',1),('incremental/05_v.1.3/18_@hallocasaappmig_#all_property_selector_type_insert_data.sql',1472430867516,'af6aefa9c257241349847cfea896f05f','2016-09-01 19:38:56',1),('incremental/05_v.1.3/19_@hallocasaappmig_#all_property_selector_create_table.sql',1472430867517,'edf0e5d5c997c9b1f6301b40fa0dee7a','2016-09-01 19:38:56',1),('incremental/05_v.1.3/20_@hallocasaappmig_#all_property_selector_insert_data.sql',1472430867521,'fcde476da9e22eb996e53e4301e39b24','2016-09-01 19:38:58',1),('incremental/05_v.1.3/21_@hallocasaappmig_#all_property_selector_option_create_table.sql',1472430867525,'8095729ec6d15eb26217060158098485','2016-09-01 19:38:58',1),('incremental/05_v.1.3/22_@hallocasaappmig_#all_property_selector_option_insert_data.sql',1472430867528,'3c1d707961e970246420c407109cc1e2','2016-09-01 19:39:00',1),('incremental/05_v.1.3/23_@hallocasaappmig_#all_property_field_value_create_table.sql',1472430867534,'689720df5fcab0ca5c32a7b516969ef7','2016-09-01 19:39:00',1),('incremental/05_v.1.3/24_@hallocasaappmig_#dev_property_field_value_insert_data.sql',1472430867537,'ad26385f46385a885c8cd835ea63fb97','2016-09-01 19:39:02',1),('incremental/05_v.1.3/25_@hallocasaappmig_#all_property_field_add_data.sql',1472430867578,'8282e33b45541efa7034f9e28b008dab','2016-09-01 19:39:09',1),('incremental/05_v.1.3/26_@hallocasaappmig_#dev_property_field_value_add_data.sql',1472430867583,'f81ec40615b6a921e9b1936930d5bf8a','2016-09-01 19:39:12',1),('incremental/05_v.1.3/27_@hallocasaappmig_#dev_property_field_value_update_data.sql',1472430867587,'de8a76e2e37c6417762e2267f03c728d','2016-09-01 19:39:14',1),('incremental/05_v.1.3/28_@hallocasaappmig_#all_property_field_alter_table.sql',1472430867602,'57668f8bb1cae1baa5a0a206d2bd3583','2016-09-01 19:39:15',1),('incremental/05_v.1.3/29_@hallocasaappmig_#dev_property_field_update_data.sql',1472430867603,'9b1379229c840fcc38bb5a2f89f5cb28','2016-09-01 19:39:18',1),('incremental/05_v.1.3/30_@hallocasaappmig_#all_city_add_default_coordinate.sql',1472430867607,'f8af65ae68372f874d6443d147c918db','2016-09-01 19:39:20',1),('incremental/05_v.1.3/31_@hallocasaappmig_#all_city_insert_default_coordinates.sql',1472430867607,'ab1935ec1854be646701edfbdf396358','2016-09-01 19:39:43',1),('incremental/06_v.1.4/01_@hallocasaappmig_#all_currency_exchange_data_create_table.sql',1472430867608,'863fe8b45b4595cb75ec9bf587593cf6','2016-09-01 19:39:44',1),('incremental/06_v.1.4/02_@hallocasaappmig_#all_property_type_changes.sql',1472430867608,'4e741578bf96255bae92ed31fd68d22c','2016-09-01 19:40:00',1),('incremental/06_v.1.4/03_@hallocasaappmig_#all_property_proposal_changes.sql',1472430867609,'67da680a64a5f66fb869a15cbb12266a','2016-09-01 19:40:01',1);
/*!40000 ALTER TABLE `dbmaintain_scripts` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `example`
--

LOCK TABLES `example` WRITE;
/*!40000 ALTER TABLE `example` DISABLE KEYS */;
INSERT INTO `example` VALUES (5,'Esto est\\u00E1 funcionando');
/*!40000 ALTER TABLE `example` ENABLE KEYS */;
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
INSERT INTO `filter` VALUES (1,'Property types',1,1,NULL,NULL,NULL,NULL,1,'/property_data/types',NULL,0,NULL,NULL,NULL,NULL),(2,'Buy / Rent',1,1,NULL,NULL,NULL,NULL,1,'/property_data/proposals',NULL,0,NULL,NULL,NULL,NULL),(3,'Property locations',1,1,NULL,NULL,NULL,NULL,1,'/property_data/locations',NULL,0,NULL,NULL,NULL,NULL),(4,'Languages',1,1,NULL,NULL,NULL,NULL,1,'/languages',NULL,0,NULL,NULL,NULL,NULL),(5,'Market Price',7,1,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(6,'Square Meters Total',5,1,5.00,1000000.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(7,'Country',1,1,NULL,NULL,NULL,NULL,1,'/countries',NULL,0,NULL,NULL,NULL,NULL),(8,'Department',1,1,NULL,NULL,NULL,NULL,0,NULL,'SHOW',0,NULL,NULL,NULL,NULL),(9,'Town',1,1,NULL,NULL,NULL,NULL,0,NULL,'SHOW',0,NULL,NULL,NULL,NULL),(10,'Neighborhood',1,1,NULL,NULL,NULL,NULL,0,NULL,'SHOW',0,NULL,NULL,NULL,NULL),(12,'Rooms',4,2,NULL,NULL,NULL,NULL,1,NULL,'SHOW',NULL,NULL,NULL,NULL,NULL),(13,'Bathrooms',4,2,NULL,NULL,NULL,NULL,1,NULL,'SHOW',NULL,NULL,NULL,NULL,NULL),(14,'Condition',1,2,NULL,NULL,NULL,NULL,1,NULL,'SHOW',0,NULL,NULL,NULL,NULL),(15,'Furnished',2,2,NULL,NULL,NULL,NULL,NULL,NULL,'SHOW',NULL,NULL,NULL,NULL,NULL),(16,'Floor',1,2,NULL,NULL,NULL,NULL,1,NULL,'SHOW',0,NULL,NULL,NULL,NULL),(17,'Optional Features',1,2,NULL,NULL,NULL,NULL,1,NULL,'SHOW',0,NULL,NULL,NULL,NULL),(18,'Suitable For',1,2,NULL,NULL,NULL,NULL,1,NULL,'SHOW',0,NULL,NULL,NULL,NULL),(19,'Parking Spots',1,2,NULL,NULL,NULL,NULL,1,NULL,'SHOW',0,NULL,NULL,NULL,NULL),(20,'Basement',2,2,NULL,NULL,NULL,NULL,NULL,NULL,'SHOW',NULL,NULL,NULL,NULL,NULL),(21,'Full Basement',2,2,NULL,NULL,NULL,NULL,NULL,NULL,'SHOW',NULL,NULL,NULL,NULL,NULL),(22,'Balcony/Rooftop',2,2,NULL,NULL,NULL,NULL,NULL,NULL,'SHOW',NULL,NULL,NULL,NULL,NULL),(23,'Garden',2,2,NULL,NULL,NULL,NULL,NULL,NULL,'SHOW',NULL,NULL,NULL,NULL,NULL),(24,'Publishing Date',3,2,NULL,NULL,NULL,NULL,NULL,NULL,'SHOW',NULL,NULL,NULL,NULL,NULL),(25,'Rented',2,2,NULL,NULL,NULL,NULL,NULL,NULL,'SHOW',NULL,NULL,NULL,NULL,NULL),(26,'Annual Return on Investment',4,1,NULL,NULL,NULL,NULL,1,NULL,'SHOW',0,NULL,NULL,NULL,NULL),(27,'Available from',11,2,NULL,NULL,NULL,NULL,NULL,NULL,'SHOW',NULL,0,0,0,0),(28,'Estrato - Colombia',1,1,NULL,NULL,NULL,NULL,1,NULL,'SHOW',NULL,NULL,NULL,NULL,NULL),(29,'Estrato - Chile',1,1,NULL,NULL,NULL,NULL,1,NULL,'SHOW',NULL,NULL,NULL,NULL,NULL),(30,'Estrato - Costa Rica',1,1,NULL,NULL,NULL,NULL,1,NULL,'SHOW',NULL,NULL,NULL,NULL,NULL),(31,'Estrato - Ecuador',1,1,NULL,NULL,NULL,NULL,1,NULL,'SHOW',NULL,NULL,NULL,NULL,NULL),(32,'Estrato - Panamá',1,1,NULL,NULL,NULL,NULL,1,NULL,'SHOW',NULL,NULL,NULL,NULL,NULL),(33,'Estrato - Perú',1,1,NULL,NULL,NULL,NULL,1,NULL,'SHOW',NULL,NULL,NULL,NULL,NULL),(34,'Estrato - Argentina',1,1,NULL,NULL,NULL,NULL,1,NULL,'SHOW',NULL,NULL,NULL,NULL,NULL);
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
  `id` int(11) DEFAULT NULL,
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
INSERT INTO `filter_listing_step` VALUES (8,NULL,7,NULL,1),(9,NULL,8,NULL,2),(10,NULL,9,NULL,3);
/*!40000 ALTER TABLE `filter_listing_step` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `filter_nature`
--

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
-- Table structure for table `filter_showing_step`
--

DROP TABLE IF EXISTS `filter_showing_step`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `filter_showing_step` (
  `filter_id` int(11) NOT NULL,
  `sequence_before` varchar(30) DEFAULT NULL,
  `filter_condition_id` int(11) DEFAULT NULL,
  `sequence_after` varchar(30) DEFAULT NULL,
  `id` int(11) DEFAULT NULL,
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
INSERT INTO `filter_showing_step` VALUES (8,NULL,1,NULL,1),(9,NULL,2,NULL,2),(10,NULL,3,NULL,3);
/*!40000 ALTER TABLE `filter_showing_step` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `filter_type`
--

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
-- Dumping data for table `profile`
--

LOCK TABLES `profile` WRITE;
/*!40000 ALTER TABLE `profile` DISABLE KEYS */;
INSERT INTO `profile` VALUES (1,'/hallocasa/user/my-profile');
/*!40000 ALTER TABLE `profile` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `profile_use_case`
--

LOCK TABLES `profile_use_case` WRITE;
/*!40000 ALTER TABLE `profile_use_case` DISABLE KEYS */;
/*!40000 ALTER TABLE `profile_use_case` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `property`
--

LOCK TABLES `property` WRITE;
/*!40000 ALTER TABLE `property` DISABLE KEYS */;
INSERT INTO `property` VALUES ('TYrtHJI8',1,1,1,1,1);
/*!40000 ALTER TABLE `property` ENABLE KEYS */;
UNLOCK TABLES;

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
  `primary_key` tinyint(1) NOT NULL DEFAULT '0',
  `lang` varchar(200) NOT NULL DEFAULT 'hallocasa.pending',
  `multilanguage_value` tinyint(1) DEFAULT '0',
  `property_field_type_id` int(11) DEFAULT NULL,
  `property_field_value_type_id` int(11) DEFAULT NULL,
  `text_type` varchar(50) DEFAULT NULL,
  `data1_type` varchar(50) DEFAULT NULL,
  `data2_type` varchar(50) DEFAULT NULL,
  `data3_type` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `property_field_type_id` (`property_field_type_id`),
  KEY `property_field_value_type_id` (`property_field_value_type_id`),
  CONSTRAINT `property_field_ibfk_1` FOREIGN KEY (`property_field_type_id`) REFERENCES `property_field_type` (`id`),
  CONSTRAINT `property_field_ibfk_2` FOREIGN KEY (`property_field_value_type_id`) REFERENCES `property_field_value_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `property_field`
--

LOCK TABLES `property_field` WRITE;
/*!40000 ALTER TABLE `property_field` DISABLE KEYS */;
INSERT INTO `property_field` VALUES (1,'Languages',0,0,'hallocasa.pending',0,3,3,NULL,NULL,NULL,NULL),(2,'Title',1,0,'hallocasa.pending',1,2,5,NULL,'INT','TEXT',NULL),(3,'Property Description',1,0,'hallocasa.pending',1,2,5,NULL,'INT','TEXT',NULL),(4,'Location Description',0,0,'hallocasa.pending',1,2,5,NULL,'INT','TEXT',NULL),(5,'Market Price',1,0,'hallocasa.pending',0,9,4,NULL,'INT','DOUBLE',NULL),(6,'Square Meters Total',1,0,'hallocasa.pending',0,6,1,'INT',NULL,NULL,NULL),(7,'Department',0,0,'hallocasa.pending',0,1,2,NULL,NULL,NULL,NULL),(8,'Town',1,0,'hallocasa.pending',0,1,2,NULL,NULL,NULL,NULL),(9,'Address',1,0,'hallocasa.pending',0,2,1,'TEXT',NULL,NULL,NULL),(10,'Location',0,0,'hallocasa.pending',0,10,4,NULL,'DOUBLE','DOUBLE',NULL),(12,'Images',0,0,'hallocasa.pending',0,4,5,NULL,'FILE','BOOLEAN',NULL),(13,'Video',0,0,'hallocasa.pending',0,5,1,'TEXT',NULL,NULL,NULL),(15,'Location - Neighborhood',1,0,'hallocasa.pending',0,1,2,NULL,NULL,NULL,NULL),(17,'Rooms',0,0,'hallocasa.pending',0,6,1,'INT',NULL,NULL,NULL),(18,'Bathrooms',0,0,'hallocasa.pending',0,6,1,'INT',NULL,NULL,NULL),(19,'Condition',0,0,'hallocasa.pending',0,1,2,NULL,NULL,NULL,NULL),(20,'Furnished',0,0,'hallocasa.pending',0,7,1,'BOOLEAN',NULL,NULL,NULL),(21,'Floor',0,0,'hallocasa.pending',0,6,1,'INT',NULL,NULL,NULL),(22,'Optional features',0,0,'hallocasa.pending',0,3,3,NULL,NULL,NULL,NULL),(23,'Suitable for',0,0,'hallocasa.pending',0,3,3,NULL,NULL,NULL,NULL),(24,'Parking spots',0,0,'hallocasa.pending',0,6,1,'INT',NULL,NULL,NULL),(25,'Basement',0,0,'hallocasa.pending',0,7,1,'BOOLEAN',NULL,NULL,NULL),(27,'Balcony/Rooftop',0,0,'hallocasa.pending',0,7,1,'BOOLEAN',NULL,NULL,NULL),(28,'Garden/Terrace',0,0,'hallocasa.pending',0,7,1,'BOOLEAN',NULL,NULL,NULL),(29,'Available From',0,0,'hallocasa.pending',0,8,1,'DATE',NULL,NULL,NULL),(30,'Rented',0,0,'hallocasa.pending',0,7,1,'BOOLEAN',NULL,NULL,NULL),(31,'Property type',1,1,'hallocasa.pending',0,1,2,NULL,NULL,NULL,NULL),(32,'Property location',1,1,'hallocasa.pending',0,1,2,NULL,NULL,NULL,NULL),(33,'Property proposal',1,1,'hallocasa.pending',0,1,2,NULL,NULL,NULL,NULL),(34,'Country',1,1,'hallocasa.pending',0,1,2,NULL,NULL,NULL,NULL),(35,'Square Meters Built',0,0,'hallocasa.pending',0,6,1,NULL,NULL,NULL,NULL),(36,'Security',0,0,'hallocasa.pending',0,3,3,NULL,NULL,NULL,NULL),(37,'Estrato - Colombia',0,0,'hallocasa.pending',0,1,2,NULL,NULL,NULL,NULL),(38,'Estrato - Panamá',0,0,'hallocasa.pending',0,1,2,NULL,NULL,NULL,NULL),(39,'Estrato - Costa Rica',0,0,'hallocasa.pending',0,1,2,NULL,NULL,NULL,NULL),(40,'Estrato - Chile',0,0,'hallocasa.pending',0,1,2,NULL,NULL,NULL,NULL),(41,'Estrato - Argentina',0,0,'hallocasa.pending',0,1,2,NULL,NULL,NULL,NULL),(42,'Estrato - Perú',0,0,'hallocasa.pending',0,1,2,NULL,NULL,NULL,NULL),(43,'Estrato - Ecuador',0,0,'hallocasa.pending',0,1,2,NULL,NULL,NULL,NULL),(44,'Kind of Road',0,0,'hallocasa.pending',0,3,3,NULL,NULL,NULL,NULL),(45,'Heating',0,0,'hallocasa.pending',0,1,2,NULL,NULL,NULL,NULL),(46,'Number of Floors',0,0,'hallocasa.pending',0,6,1,'INT',NULL,NULL,NULL),(47,'Drinking Water',0,0,'hallocasa.pending',0,3,3,NULL,NULL,NULL,NULL),(48,'Sewage Water',0,0,'hallocasa.pending',0,3,3,NULL,NULL,NULL,NULL),(49,'Year of Construction',0,0,'hallocasa.pending',0,1,2,NULL,NULL,NULL,NULL),(50,'Method of Construction',0,0,'hallocasa.pending',0,1,2,NULL,NULL,NULL,NULL),(51,'Type of Soil',0,0,'hallocasa.pending',0,3,3,NULL,NULL,NULL,NULL),(52,'Agriculture',0,0,'hallocasa.pending',0,3,3,NULL,NULL,NULL,NULL),(53,'Last Modernization',0,0,'hallocasa.pending',0,1,2,NULL,NULL,NULL,NULL),(54,'Price Development in last 5 Years',0,0,'hallocasa.pending',0,1,2,NULL,NULL,NULL,NULL),(55,'Inclination',0,0,'hallocasa.pending',0,3,3,NULL,NULL,NULL,NULL),(56,'Agent Fee',0,0,'hallocasa.pending',0,1,2,NULL,NULL,NULL,NULL),(57,'Monthly Admin Fees for the Landlord',0,0,'hallocasa.pending',0,6,1,'DOUBLE',NULL,NULL,NULL),(58,'Additional Monthly Fees for the Landlord',0,0,'hallocasa.pending',0,6,1,'DOUBLE',NULL,NULL,NULL),(59,'Annual Tax Rate on the Property',0,0,'hallocasa.pending',0,1,2,NULL,NULL,NULL,NULL),(60,'Monthly Rent',0,0,'hallocasa.pending',0,9,4,'DOUBLE',NULL,NULL,NULL);
/*!40000 ALTER TABLE `property_field` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `property_field_condition_option`
--

DROP TABLE IF EXISTS `property_field_condition_option`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `property_field_condition_option` (
  `id` int(11) NOT NULL,
  `property_field_id` int(11) NOT NULL,
  `parent_property_field_id` int(11) NOT NULL,
  `parent_property_field_option_id` int(11) NOT NULL,
  `condition_level` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `property_field_id` (`property_field_id`),
  KEY `parent_property_field_id` (`parent_property_field_id`),
  CONSTRAINT `property_field_condition_option_ibfk_1` FOREIGN KEY (`property_field_id`) REFERENCES `property_field` (`id`),
  CONSTRAINT `property_field_condition_option_ibfk_2` FOREIGN KEY (`parent_property_field_id`) REFERENCES `property_field` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `property_field_condition_option`
--

LOCK TABLES `property_field_condition_option` WRITE;
/*!40000 ALTER TABLE `property_field_condition_option` DISABLE KEYS */;
INSERT INTO `property_field_condition_option` VALUES (1,1,31,1,0),(2,1,31,2,0),(3,1,31,3,0),(4,1,32,1,0),(5,1,32,2,0),(6,1,32,3,0),(7,1,33,1,0),(8,1,33,2,0),(9,1,34,1,0),(10,1,34,2,0),(11,1,34,3,0),(12,1,34,4,0),(13,1,34,5,0),(14,1,34,6,0),(15,1,34,7,0),(16,1,34,8,0),(17,1,34,9,0),(18,2,31,1,0),(19,2,31,2,0),(20,2,31,3,0),(21,2,32,1,0),(22,2,32,2,0),(23,2,32,3,0),(24,2,33,1,0),(25,2,33,2,0),(26,2,34,1,0),(27,2,34,2,0),(28,2,34,3,0),(29,2,34,4,0),(30,2,34,5,0),(31,2,34,6,0),(32,2,34,7,0),(33,2,34,8,0),(34,2,34,9,0),(35,3,31,1,0),(36,3,31,2,0),(37,3,31,3,0),(38,3,32,1,0),(39,3,32,2,0),(40,3,32,3,0),(41,3,33,1,0),(42,3,33,2,0),(43,3,34,1,0),(44,3,34,2,0),(45,3,34,3,0),(46,3,34,4,0),(47,3,34,5,0),(48,3,34,6,0),(49,3,34,7,0),(50,3,34,8,0),(51,3,34,9,0),(52,4,31,1,0),(53,4,31,2,0),(54,4,31,3,0),(55,4,32,1,0),(56,4,32,2,0),(57,4,32,3,0),(58,4,33,1,0),(59,4,33,2,0),(60,4,34,1,0),(61,4,34,2,0),(62,4,34,3,0),(63,4,34,4,0),(64,4,34,5,0),(65,4,34,6,0),(66,4,34,7,0),(67,4,34,8,0),(68,4,34,9,0),(69,5,31,1,0),(70,5,31,2,0),(71,5,31,3,0),(72,5,32,1,0),(73,5,32,2,0),(74,5,32,3,0),(75,5,33,1,0),(76,5,33,2,0),(77,5,34,1,0),(78,5,34,2,0),(79,5,34,3,0),(80,5,34,4,0),(81,5,34,5,0),(82,5,34,6,0),(83,5,34,7,0),(84,5,34,8,0),(85,5,34,9,0),(86,6,31,1,0),(87,6,31,2,0),(88,6,31,3,0),(89,6,32,1,0),(90,6,32,2,0),(91,6,32,3,0),(92,6,33,1,0),(93,6,33,2,0),(94,6,34,1,0),(95,6,34,2,0),(96,6,34,3,0),(97,6,34,4,0),(98,6,34,5,0),(99,6,34,6,0),(100,6,34,7,0),(101,6,34,8,0),(102,6,34,9,0),(103,7,31,1,0),(104,7,31,2,0),(105,7,31,3,0),(106,7,32,1,0),(107,7,32,2,0),(108,7,32,3,0),(109,7,33,1,0),(110,7,33,2,0),(111,7,34,1,0),(112,7,34,2,0),(113,7,34,3,0),(114,7,34,4,0),(115,7,34,5,0),(116,7,34,6,0),(117,7,34,7,0),(118,7,34,8,0),(119,7,34,9,0),(120,8,31,1,0),(121,8,31,2,0),(122,8,31,3,0),(123,8,32,1,0),(124,8,32,2,0),(125,8,32,3,0),(126,8,33,1,0),(127,8,33,2,0),(128,8,34,1,0),(129,8,34,2,0),(130,8,34,3,0),(131,8,34,4,0),(132,8,34,5,0),(133,8,34,6,0),(134,8,34,7,0),(135,8,34,8,0),(136,8,34,9,0),(137,9,31,1,0),(138,9,31,2,0),(139,9,31,3,0),(140,9,32,1,0),(141,9,32,2,0),(142,9,32,3,0),(143,9,33,1,0),(144,9,33,2,0),(145,9,34,1,0),(146,9,34,2,0),(147,9,34,3,0),(148,9,34,4,0),(149,9,34,5,0),(150,9,34,6,0),(151,9,34,7,0),(152,9,34,8,0),(153,9,34,9,0),(154,10,31,1,0),(155,10,31,2,0),(156,10,31,3,0),(157,10,32,1,0),(158,10,32,2,0),(159,10,32,3,0),(160,10,33,1,0),(161,10,33,2,0),(162,10,34,1,0),(163,10,34,2,0),(164,10,34,3,0),(165,10,34,4,0),(166,10,34,5,0),(167,10,34,6,0),(168,10,34,7,0),(169,10,34,8,0),(170,10,34,9,0),(171,12,31,1,0),(172,12,31,2,0),(173,12,31,3,0),(174,12,32,1,0),(175,12,32,2,0),(176,12,32,3,0),(177,12,33,1,0),(178,12,33,2,0),(179,12,34,1,0),(180,12,34,2,0),(181,12,34,3,0),(182,12,34,4,0),(183,12,34,5,0),(184,12,34,6,0),(185,12,34,7,0),(186,12,34,8,0),(187,12,34,9,0),(188,13,31,1,0),(189,13,31,2,0),(190,13,31,3,0),(191,13,32,1,0),(192,13,32,2,0),(193,13,32,3,0),(194,13,33,1,0),(195,13,33,2,0),(196,13,34,1,0),(197,13,34,2,0),(198,13,34,3,0),(199,13,34,4,0),(200,13,34,5,0),(201,13,34,6,0),(202,13,34,7,0),(203,13,34,8,0),(204,13,34,9,0),(205,15,31,1,0),(206,15,31,2,0),(207,15,31,3,0),(208,15,32,1,0),(209,15,32,2,0),(210,15,32,3,0),(211,15,33,1,0),(212,15,33,2,0),(213,15,34,1,0),(214,15,34,2,0),(215,15,34,3,0),(216,15,34,4,0),(217,15,34,5,0),(218,15,34,6,0),(219,15,34,7,0),(220,15,34,8,0),(221,15,34,9,0),(222,17,31,2,0),(223,17,31,3,0),(224,17,32,1,0),(225,17,32,2,0),(226,17,32,3,0),(227,17,33,1,0),(228,17,33,2,0),(229,17,34,1,0),(230,17,34,2,0),(231,17,34,3,0),(232,17,34,4,0),(233,17,34,5,0),(234,17,34,6,0),(235,17,34,7,0),(236,17,34,8,0),(237,17,34,9,0),(238,18,31,2,0),(239,18,31,3,0),(240,18,32,1,0),(241,18,32,2,0),(242,18,32,3,0),(243,18,33,1,0),(244,18,33,2,0),(245,18,34,1,0),(246,18,34,2,0),(247,18,34,3,0),(248,18,34,4,0),(249,18,34,5,0),(250,18,34,6,0),(251,18,34,7,0),(252,18,34,8,0),(253,18,34,9,0),(254,19,31,1,0),(255,19,31,2,0),(256,19,31,3,0),(257,19,32,1,0),(258,19,32,2,0),(259,19,32,3,0),(260,19,33,1,0),(261,19,33,2,0),(262,19,34,1,0),(263,19,34,2,0),(264,19,34,3,0),(265,19,34,4,0),(266,19,34,5,0),(267,19,34,6,0),(268,19,34,7,0),(269,19,34,8,0),(270,19,34,9,0),(271,20,31,2,0),(272,20,31,3,0),(273,20,32,1,0),(274,20,32,2,0),(275,20,32,3,0),(276,20,33,1,0),(277,20,33,2,0),(278,20,34,1,0),(279,20,34,2,0),(280,20,34,3,0),(281,20,34,4,0),(282,20,34,5,0),(283,20,34,6,0),(284,20,34,7,0),(285,20,34,8,0),(286,20,34,9,0),(287,21,31,3,0),(288,21,32,1,0),(289,21,32,2,0),(290,21,32,3,0),(291,21,33,1,0),(292,21,33,2,0),(293,21,34,1,0),(294,21,34,2,0),(295,21,34,3,0),(296,21,34,4,0),(297,21,34,5,0),(298,21,34,6,0),(299,21,34,7,0),(300,21,34,8,0),(301,21,34,9,0),(302,22,31,2,0),(303,22,31,3,0),(304,22,32,1,0),(305,22,32,2,0),(306,22,32,3,0),(307,22,33,1,0),(308,22,33,2,0),(309,22,34,1,0),(310,22,34,2,0),(311,22,34,3,0),(312,22,34,4,0),(313,22,34,5,0),(314,22,34,6,0),(315,22,34,7,0),(316,22,34,8,0),(317,22,34,9,0),(318,23,31,1,0),(319,23,31,2,0),(320,23,31,3,0),(321,23,32,1,0),(322,23,32,2,0),(323,23,32,3,0),(324,23,33,1,0),(325,23,33,2,0),(326,23,34,1,0),(327,23,34,2,0),(328,23,34,3,0),(329,23,34,4,0),(330,23,34,5,0),(331,23,34,6,0),(332,23,34,7,0),(333,23,34,8,0),(334,23,34,9,0),(335,24,31,1,0),(336,24,31,2,0),(337,24,31,3,0),(338,24,32,1,0),(339,24,32,2,0),(340,24,32,3,0),(341,24,33,1,0),(342,24,33,2,0),(343,24,34,1,0),(344,24,34,2,0),(345,24,34,3,0),(346,24,34,4,0),(347,24,34,5,0),(348,24,34,6,0),(349,24,34,7,0),(350,24,34,8,0),(351,24,34,9,0),(352,25,31,2,0),(353,25,32,1,0),(354,25,32,2,0),(355,25,32,3,0),(356,25,33,1,0),(357,25,33,2,0),(358,25,34,1,0),(359,25,34,2,0),(360,25,34,3,0),(361,25,34,4,0),(362,25,34,5,0),(363,25,34,6,0),(364,25,34,7,0),(365,25,34,8,0),(366,25,34,9,0),(367,27,31,2,0),(368,27,31,3,0),(369,27,32,1,0),(370,27,32,2,0),(371,27,32,3,0),(372,27,33,1,0),(373,27,33,2,0),(374,27,34,1,0),(375,27,34,2,0),(376,27,34,3,0),(377,27,34,4,0),(378,27,34,5,0),(379,27,34,6,0),(380,27,34,7,0),(381,27,34,8,0),(382,27,34,9,0),(383,28,31,2,0),(384,28,31,3,0),(385,28,32,1,0),(386,28,32,2,0),(387,28,32,3,0),(388,28,33,1,0),(389,28,33,2,0),(390,28,34,1,0),(391,28,34,2,0),(392,28,34,3,0),(393,28,34,4,0),(394,28,34,5,0),(395,28,34,6,0),(396,28,34,7,0),(397,28,34,8,0),(398,28,34,9,0),(399,29,31,1,0),(400,29,31,2,0),(401,29,31,3,0),(402,29,32,1,0),(403,29,32,2,0),(404,29,32,3,0),(405,29,33,1,0),(406,29,33,2,0),(407,29,34,1,0),(408,29,34,2,0),(409,29,34,3,0),(410,29,34,4,0),(411,29,34,5,0),(412,29,34,6,0),(413,29,34,7,0),(414,29,34,8,0),(415,29,34,9,0),(416,30,31,1,0),(417,30,31,2,0),(418,30,31,3,0),(419,30,32,1,0),(420,30,32,2,0),(421,30,32,3,0),(422,30,33,1,0),(423,30,34,1,0),(424,30,34,2,0),(425,30,34,3,0),(426,30,34,4,0),(427,30,34,5,0),(428,30,34,6,0),(429,30,34,7,0),(430,30,34,8,0),(431,30,34,9,0),(432,35,31,1,0),(433,35,31,2,0),(434,35,32,1,0),(435,35,32,2,0),(436,35,32,3,0),(437,35,33,1,0),(438,35,33,2,0),(439,35,34,1,0),(440,35,34,2,0),(441,35,34,3,0),(442,35,34,4,0),(443,35,34,5,0),(444,35,34,6,0),(445,35,34,7,0),(446,35,34,8,0),(447,35,34,9,0),(448,36,31,1,0),(449,36,31,2,0),(450,36,31,3,0),(451,36,32,1,0),(452,36,32,2,0),(453,36,32,3,0),(454,36,33,1,0),(455,36,33,2,0),(456,36,34,1,0),(457,36,34,2,0),(458,36,34,3,0),(459,36,34,4,0),(460,36,34,5,0),(461,36,34,6,0),(462,36,34,7,0),(463,36,34,8,0),(464,36,34,9,0),(465,37,31,1,0),(466,37,31,2,0),(467,37,31,3,0),(468,37,32,1,0),(469,37,32,2,0),(470,37,32,3,0),(471,37,33,1,0),(472,37,33,2,0),(473,37,34,1,0),(474,38,31,1,0),(475,38,31,2,0),(476,38,31,3,0),(477,38,32,1,0),(478,38,32,2,0),(479,38,32,3,0),(480,38,33,1,0),(481,38,33,2,0),(482,38,34,4,0),(483,39,31,1,0),(484,39,31,2,0),(485,39,31,3,0),(486,39,32,1,0),(487,39,32,2,0),(488,39,32,3,0),(489,39,33,1,0),(490,39,33,2,0),(491,39,34,8,0),(492,40,31,1,0),(493,40,31,2,0),(494,40,31,3,0),(495,40,32,1,0),(496,40,32,2,0),(497,40,32,3,0),(498,40,33,1,0),(499,40,33,2,0),(500,40,34,3,0),(501,41,31,1,0),(502,41,31,2,0),(503,41,31,3,0),(504,41,32,1,0),(505,41,32,2,0),(506,41,32,3,0),(507,41,33,1,0),(508,41,33,2,0),(509,41,34,2,0),(510,42,31,1,0),(511,42,31,2,0),(512,42,31,3,0),(513,42,32,1,0),(514,42,32,2,0),(515,42,32,3,0),(516,42,33,1,0),(517,42,33,2,0),(518,42,34,5,0),(519,43,31,1,0),(520,43,31,2,0),(521,43,31,3,0),(522,43,32,1,0),(523,43,32,2,0),(524,43,32,3,0),(525,43,33,1,0),(526,43,33,2,0),(527,43,34,9,0),(528,44,31,1,0),(529,44,31,2,0),(530,44,31,3,0),(531,44,32,2,0),(532,44,32,3,0),(533,44,33,1,0),(534,44,33,2,0),(535,44,34,1,0),(536,44,34,2,0),(537,44,34,3,0),(538,44,34,4,0),(539,44,34,5,0),(540,44,34,6,0),(541,44,34,7,0),(542,44,34,8,0),(543,44,34,9,0),(544,45,31,1,0),(545,45,31,2,0),(546,45,31,3,0),(547,45,32,1,0),(548,45,32,2,0),(549,45,32,3,0),(550,45,33,1,0),(551,45,33,2,0),(552,45,34,1,0),(553,45,34,2,0),(554,45,34,3,0),(555,45,34,4,0),(556,45,34,5,0),(557,45,34,6,0),(558,45,34,7,0),(559,45,34,8,0),(560,45,34,9,0),(561,46,31,2,0),(562,46,32,1,0),(563,46,32,2,0),(564,46,32,3,0),(565,46,33,1,0),(566,46,33,2,0),(567,46,34,1,0),(568,46,34,2,0),(569,46,34,3,0),(570,46,34,4,0),(571,46,34,5,0),(572,46,34,6,0),(573,46,34,7,0),(574,46,34,8,0),(575,46,34,9,0),(576,47,31,1,0),(577,47,32,3,0),(578,47,33,1,0),(579,47,33,2,0),(580,47,34,1,0),(581,47,34,2,0),(582,47,34,3,0),(583,47,34,4,0),(584,47,34,5,0),(585,47,34,6,0),(586,47,34,7,0),(587,47,34,8,0),(588,47,34,9,0),(589,47,31,1,1),(590,47,32,2,1),(591,47,33,1,1),(592,47,34,1,1),(593,47,34,2,1),(594,47,34,3,1),(595,47,34,4,1),(596,47,34,5,1),(597,47,34,6,1),(598,47,34,7,1),(599,47,34,8,1),(600,47,34,9,1),(601,48,31,1,0),(602,48,31,2,0),(603,48,32,1,0),(604,48,32,2,0),(605,48,32,3,0),(606,48,33,1,0),(607,48,33,2,0),(608,48,34,1,0),(609,48,34,2,0),(610,48,34,3,0),(611,48,34,4,0),(612,48,34,5,0),(613,48,34,6,0),(614,48,34,7,0),(615,48,34,8,0),(616,48,34,9,0),(617,49,31,1,0),(618,49,31,2,0),(619,49,31,3,0),(620,49,32,1,0),(621,49,32,2,0),(622,49,32,3,0),(623,49,33,1,0),(624,49,33,2,0),(625,49,34,1,0),(626,49,34,2,0),(627,49,34,3,0),(628,49,34,4,0),(629,49,34,5,0),(630,49,34,6,0),(631,49,34,7,0),(632,49,34,8,0),(633,49,34,9,0),(634,50,31,1,0),(635,50,31,2,0),(636,50,31,3,0),(637,50,32,1,0),(638,50,32,2,0),(639,50,32,3,0),(640,50,33,1,0),(641,50,33,2,0),(642,50,34,1,0),(643,50,34,2,0),(644,50,34,3,0),(645,50,34,4,0),(646,50,34,5,0),(647,50,34,6,0),(648,50,34,7,0),(649,50,34,8,0),(650,50,34,9,0),(651,51,31,1,0),(652,51,32,1,0),(653,51,32,2,0),(654,51,32,3,0),(655,51,33,1,0),(656,51,33,2,0),(657,51,34,1,0),(658,51,34,2,0),(659,51,34,3,0),(660,51,34,4,0),(661,51,34,5,0),(662,51,34,6,0),(663,51,34,7,0),(664,51,34,8,0),(665,51,34,9,0),(666,52,31,1,0),(667,52,32,1,0),(668,52,32,2,0),(669,52,32,3,0),(670,52,33,1,0),(671,52,33,2,0),(672,52,34,1,0),(673,52,34,2,0),(674,52,34,3,0),(675,52,34,4,0),(676,52,34,5,0),(677,52,34,6,0),(678,52,34,7,0),(679,52,34,8,0),(680,52,34,9,0),(681,53,31,1,0),(682,53,31,2,0),(683,53,31,3,0),(684,53,32,1,0),(685,53,32,2,0),(686,53,32,3,0),(687,53,33,1,0),(688,53,33,2,0),(689,53,34,1,0),(690,53,34,2,0),(691,53,34,3,0),(692,53,34,4,0),(693,53,34,5,0),(694,53,34,6,0),(695,53,34,7,0),(696,53,34,8,0),(697,53,34,9,0),(698,54,31,1,0),(699,54,31,2,0),(700,54,31,3,0),(701,54,32,1,0),(702,54,32,2,0),(703,54,32,3,0),(704,54,33,1,0),(705,54,34,1,0),(706,54,34,2,0),(707,54,34,3,0),(708,54,34,4,0),(709,54,34,5,0),(710,54,34,6,0),(711,54,34,7,0),(712,54,34,8,0),(713,54,34,9,0),(714,55,31,1,0),(715,55,32,1,0),(716,55,32,2,0),(717,55,32,3,0),(718,55,33,1,0),(719,55,33,2,0),(720,55,34,1,0),(721,55,34,2,0),(722,55,34,3,0),(723,55,34,4,0),(724,55,34,5,0),(725,55,34,6,0),(726,55,34,7,0),(727,55,34,8,0),(728,55,34,9,0),(729,56,31,1,0),(730,56,31,2,0),(731,56,31,3,0),(732,56,32,1,0),(733,56,32,2,0),(734,56,32,3,0),(735,56,33,1,0),(736,56,33,2,0),(737,56,34,1,0),(738,56,34,2,0),(739,56,34,3,0),(740,56,34,4,0),(741,56,34,5,0),(742,56,34,6,0),(743,56,34,7,0),(744,56,34,8,0),(745,56,34,9,0),(746,57,31,1,0),(747,57,31,2,0),(748,57,31,3,0),(749,57,32,1,0),(750,57,32,2,0),(751,57,32,3,0),(752,57,33,1,0),(753,57,33,2,0),(754,57,34,1,0),(755,57,34,2,0),(756,57,34,3,0),(757,57,34,4,0),(758,57,34,5,0),(759,57,34,6,0),(760,57,34,7,0),(761,57,34,8,0),(762,57,34,9,0),(763,58,31,1,0),(764,58,31,2,0),(765,58,31,3,0),(766,58,32,1,0),(767,58,32,2,0),(768,58,32,3,0),(769,58,33,1,0),(770,58,34,1,0),(771,58,34,2,0),(772,58,34,3,0),(773,58,34,4,0),(774,58,34,5,0),(775,58,34,6,0),(776,58,34,7,0),(777,58,34,8,0),(778,58,34,9,0),(779,59,31,1,0),(780,59,31,2,0),(781,59,31,3,0),(782,59,32,1,0),(783,59,32,2,0),(784,59,32,3,0),(785,59,33,1,0),(786,59,34,1,0),(787,59,34,2,0),(788,59,34,3,0),(789,59,34,4,0),(790,59,34,5,0),(791,59,34,6,0),(792,59,34,7,0),(793,59,34,8,0),(794,59,34,9,0),(795,60,31,1,0),(796,60,31,2,0),(797,60,31,3,0),(798,60,32,1,0),(799,60,32,2,0),(800,60,32,3,0),(801,60,33,1,0),(802,60,34,1,0),(803,60,34,2,0),(804,60,34,3,0),(805,60,34,4,0),(806,60,34,5,0),(807,60,34,6,0),(808,60,34,7,0),(809,60,34,8,0),(810,60,34,9,0);
/*!40000 ALTER TABLE `property_field_condition_option` ENABLE KEYS */;
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
INSERT INTO `property_field_filter` VALUES (4,1),(5,5),(6,6),(8,7),(9,8),(10,15),(12,17),(13,18),(14,19),(15,20),(16,21),(17,22),(18,23),(19,24),(20,25),(22,27),(23,28),(25,30),(27,29),(28,37),(29,40),(30,39),(31,43),(32,38),(33,42),(34,41),(1,31),(2,33),(3,32),(7,34);
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `property_field_option`
--

LOCK TABLES `property_field_option` WRITE;
/*!40000 ALTER TABLE `property_field_option` DISABLE KEYS */;
/*!40000 ALTER TABLE `property_field_option` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `property_field_rule`
--

LOCK TABLES `property_field_rule` WRITE;
/*!40000 ALTER TABLE `property_field_rule` DISABLE KEYS */;
INSERT INTO `property_field_rule` VALUES (1,1,1,1,NULL,'Y'),(1,1,1,2,NULL,'Y'),(1,1,1,3,NULL,'Y'),(1,1,1,1,NULL,'Y'),(1,1,1,2,NULL,'Y'),(1,1,1,3,NULL,'Y'),(1,2,1,1,NULL,'Y'),(1,2,1,2,NULL,'Y'),(1,2,1,3,NULL,'Y'),(1,2,1,1,NULL,'Y'),(1,2,1,2,NULL,'Y'),(1,2,1,3,NULL,'Y'),(1,3,1,1,NULL,'Y'),(1,3,1,2,NULL,'Y'),(1,3,1,3,NULL,'Y'),(1,3,1,1,NULL,'Y'),(1,3,1,2,NULL,'Y'),(1,3,1,3,NULL,'Y'),(2,1,1,1,NULL,'Y'),(2,1,1,2,NULL,'Y'),(2,1,1,3,NULL,'Y'),(2,1,1,1,NULL,'Y'),(2,1,1,2,NULL,'Y'),(2,1,1,3,NULL,'Y'),(2,2,1,1,NULL,'Y'),(2,2,1,2,NULL,'Y'),(2,2,1,3,NULL,'Y'),(2,2,1,1,NULL,'Y'),(2,2,1,2,NULL,'Y'),(2,2,1,3,NULL,'Y'),(2,3,1,1,NULL,'Y'),(2,3,1,2,NULL,'Y'),(2,3,1,3,NULL,'Y'),(2,3,1,1,NULL,'Y'),(2,3,1,2,NULL,'Y'),(2,3,1,3,NULL,'Y'),(3,1,1,1,NULL,'Y'),(3,1,1,2,NULL,'Y'),(3,1,1,3,NULL,'Y'),(3,1,1,1,NULL,'Y'),(3,1,1,2,NULL,'Y'),(3,1,1,3,NULL,'Y'),(3,2,1,1,NULL,'Y'),(3,2,1,2,NULL,'Y'),(3,2,1,3,NULL,'Y'),(3,2,1,1,NULL,'Y'),(3,2,1,2,NULL,'Y'),(3,2,1,3,NULL,'Y'),(3,3,1,1,NULL,'Y'),(3,3,1,2,NULL,'Y'),(3,3,1,3,NULL,'Y'),(3,3,1,1,NULL,'Y'),(3,3,1,2,NULL,'Y'),(3,3,1,3,NULL,'Y'),(4,1,1,1,NULL,'Y'),(4,1,1,2,NULL,'Y'),(4,1,1,3,NULL,'Y'),(4,1,1,1,NULL,'Y'),(4,1,1,2,NULL,'Y'),(4,1,1,3,NULL,'Y'),(4,2,1,1,NULL,'Y'),(4,2,1,2,NULL,'Y'),(4,2,1,3,NULL,'Y'),(4,2,1,1,NULL,'Y'),(4,2,1,2,NULL,'Y'),(4,2,1,3,NULL,'Y'),(4,3,1,1,NULL,'Y'),(4,3,1,2,NULL,'Y'),(4,3,1,3,NULL,'Y'),(4,3,1,1,NULL,'Y'),(4,3,1,2,NULL,'Y'),(4,3,1,3,NULL,'Y'),(5,1,1,1,NULL,'Y'),(5,1,1,2,NULL,'Y'),(5,1,1,3,NULL,'Y'),(5,1,1,1,NULL,'Y'),(5,1,1,2,NULL,'Y'),(5,1,1,3,NULL,'Y'),(5,2,1,1,NULL,'Y'),(5,2,1,2,NULL,'Y'),(5,2,1,3,NULL,'Y'),(5,2,1,1,NULL,'Y'),(5,2,1,2,NULL,'Y'),(5,2,1,3,NULL,'Y'),(5,3,1,1,NULL,'Y'),(5,3,1,2,NULL,'Y'),(5,3,1,3,NULL,'Y'),(5,3,1,1,NULL,'Y'),(5,3,1,2,NULL,'Y'),(5,3,1,3,NULL,'Y'),(6,1,1,1,NULL,'Y'),(6,1,1,2,NULL,'Y'),(6,1,1,3,NULL,'Y'),(6,1,1,1,NULL,'Y'),(6,1,1,2,NULL,'Y'),(6,1,1,3,NULL,'Y'),(6,2,1,1,NULL,'Y'),(6,2,1,2,NULL,'Y'),(6,2,1,3,NULL,'Y'),(6,2,1,1,NULL,'Y'),(6,2,1,2,NULL,'Y'),(6,2,1,3,NULL,'Y'),(6,3,1,1,NULL,'Y'),(6,3,1,2,NULL,'Y'),(6,3,1,3,NULL,'Y'),(6,3,1,1,NULL,'Y'),(6,3,1,2,NULL,'Y'),(6,3,1,3,NULL,'Y'),(7,1,1,1,NULL,'Y'),(7,1,1,2,NULL,'Y'),(7,1,1,3,NULL,'Y'),(7,1,1,1,NULL,'Y'),(7,1,1,2,NULL,'Y'),(7,1,1,3,NULL,'Y'),(7,2,1,1,NULL,'Y'),(7,2,1,2,NULL,'Y'),(7,2,1,3,NULL,'Y'),(7,2,1,1,NULL,'Y'),(7,2,1,2,NULL,'Y'),(7,2,1,3,NULL,'Y'),(7,3,1,1,NULL,'Y'),(7,3,1,2,NULL,'Y'),(7,3,1,3,NULL,'Y'),(7,3,1,1,NULL,'Y'),(7,3,1,2,NULL,'Y'),(7,3,1,3,NULL,'Y'),(8,1,1,1,NULL,'Y'),(8,1,1,2,NULL,'Y'),(8,1,1,3,NULL,'Y'),(8,1,1,1,NULL,'Y'),(8,1,1,2,NULL,'Y'),(8,1,1,3,NULL,'Y'),(8,2,1,1,NULL,'Y'),(8,2,1,2,NULL,'Y'),(8,2,1,3,NULL,'Y'),(8,2,1,1,NULL,'Y'),(8,2,1,2,NULL,'Y'),(8,2,1,3,NULL,'Y'),(8,3,1,1,NULL,'Y'),(8,3,1,2,NULL,'Y'),(8,3,1,3,NULL,'Y'),(8,3,1,1,NULL,'Y'),(8,3,1,2,NULL,'Y'),(8,3,1,3,NULL,'Y'),(9,1,1,1,NULL,'Y'),(9,1,1,2,NULL,'Y'),(9,1,1,3,NULL,'Y'),(9,1,1,1,NULL,'Y'),(9,1,1,2,NULL,'Y'),(9,1,1,3,NULL,'Y'),(9,2,1,1,NULL,'Y'),(9,2,1,2,NULL,'Y'),(9,2,1,3,NULL,'Y'),(9,2,1,1,NULL,'Y'),(9,2,1,2,NULL,'Y'),(9,2,1,3,NULL,'Y'),(9,3,1,1,NULL,'Y'),(9,3,1,2,NULL,'Y'),(9,3,1,3,NULL,'Y'),(9,3,1,1,NULL,'Y'),(9,3,1,2,NULL,'Y'),(9,3,1,3,NULL,'Y'),(10,1,1,1,NULL,'Y'),(10,1,1,2,NULL,'Y'),(10,1,1,3,NULL,'Y'),(10,1,1,1,NULL,'Y'),(10,1,1,2,NULL,'Y'),(10,1,1,3,NULL,'Y'),(10,2,1,1,NULL,'Y'),(10,2,1,2,NULL,'Y'),(10,2,1,3,NULL,'Y'),(10,2,1,1,NULL,'Y'),(10,2,1,2,NULL,'Y'),(10,2,1,3,NULL,'Y'),(10,3,1,1,NULL,'Y'),(10,3,1,2,NULL,'Y'),(10,3,1,3,NULL,'Y'),(10,3,1,1,NULL,'Y'),(10,3,1,2,NULL,'Y'),(10,3,1,3,NULL,'Y');
/*!40000 ALTER TABLE `property_field_rule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `property_field_type`
--

DROP TABLE IF EXISTS `property_field_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `property_field_type` (
  `id` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `property_field_type`
--

LOCK TABLES `property_field_type` WRITE;
/*!40000 ALTER TABLE `property_field_type` DISABLE KEYS */;
INSERT INTO `property_field_type` VALUES (1,'Dropdown'),(2,'Text field'),(3,'Checkbox group'),(4,'Image uploader'),(5,'Link text field'),(6,'Number field'),(7,'Yes / No'),(8,'Date'),(9,'Currency field'),(10,'Google maps');
/*!40000 ALTER TABLE `property_field_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `property_field_value`
--

DROP TABLE IF EXISTS `property_field_value`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `property_field_value` (
  `property_id` varchar(20) NOT NULL,
  `property_field_id` int(11) NOT NULL,
  `identifier` int(11) DEFAULT NULL,
  `text` text,
  `data1` varchar(500) DEFAULT NULL,
  `data2` varchar(500) DEFAULT NULL,
  `data3` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`property_id`,`property_field_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `property_field_value`
--

LOCK TABLES `property_field_value` WRITE;
/*!40000 ALTER TABLE `property_field_value` DISABLE KEYS */;
INSERT INTO `property_field_value` VALUES ('TYrtHJI8',1,NULL,NULL,NULL,NULL,NULL),('TYrtHJI8',2,NULL,NULL,NULL,NULL,NULL),('TYrtHJI8',3,NULL,NULL,NULL,NULL,NULL),('TYrtHJI8',4,NULL,NULL,NULL,NULL,NULL),('TYrtHJI8',5,NULL,NULL,NULL,NULL,NULL),('TYrtHJI8',6,NULL,NULL,NULL,NULL,NULL),('TYrtHJI8',7,NULL,NULL,NULL,NULL,NULL),('TYrtHJI8',8,NULL,NULL,NULL,NULL,NULL),('TYrtHJI8',9,NULL,NULL,NULL,NULL,NULL),('TYrtHJI8',10,NULL,NULL,NULL,NULL,NULL),('TYrtHJI8',11,NULL,NULL,NULL,NULL,NULL),('TYrtHJI8',12,NULL,NULL,NULL,NULL,NULL),('TYrtHJI8',13,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `property_field_value` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `property_field_value_type`
--

DROP TABLE IF EXISTS `property_field_value_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `property_field_value_type` (
  `id` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `property_field_value_type`
--

LOCK TABLES `property_field_value_type` WRITE;
/*!40000 ALTER TABLE `property_field_value_type` DISABLE KEYS */;
INSERT INTO `property_field_value_type` VALUES (1,'Text'),(2,'Identifier'),(3,'Identifier list'),(4,'Object'),(5,'Object list');
/*!40000 ALTER TABLE `property_field_value_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `property_filter_condition_option`
--

DROP TABLE IF EXISTS `property_filter_condition_option`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `property_filter_condition_option` (
  `id` int(11) NOT NULL,
  `filter_condition_id` int(11) NOT NULL,
  `property_field_option_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `filter_condition_id` (`filter_condition_id`),
  KEY `property_field_option_id` (`property_field_option_id`),
  CONSTRAINT `property_filter_condition_option_ibfk_1` FOREIGN KEY (`filter_condition_id`) REFERENCES `filter_condition` (`id`),
  CONSTRAINT `property_filter_condition_option_ibfk_2` FOREIGN KEY (`property_field_option_id`) REFERENCES `property_field_option` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `property_filter_condition_option`
--

LOCK TABLES `property_filter_condition_option` WRITE;
/*!40000 ALTER TABLE `property_filter_condition_option` DISABLE KEYS */;
/*!40000 ALTER TABLE `property_filter_condition_option` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `property_location`
--

DROP TABLE IF EXISTS `property_location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `property_location` (
  `id` int(11) NOT NULL,
  `name` varchar(500) DEFAULT NULL,
  `lang` varchar(200) DEFAULT 'hallocasa.pending',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `property_location`
--

LOCK TABLES `property_location` WRITE;
/*!40000 ALTER TABLE `property_location` DISABLE KEYS */;
INSERT INTO `property_location` VALUES (1,'{\'en\':\'City Center\',\'de\':\'Stadtzentrum\',\'es\':\'Urbano\'}','hallocasa.pending'),(2,'{\'en\':\'Suburb\',\'de\':\'Vorort\',\'es\':\'Suburbio\'}','hallocasa.pending'),(3,'{\'en\':\'Countryside\',\'de\':\'Landschaft\',\'es\':\'Rural\'}','hallocasa.pending');
/*!40000 ALTER TABLE `property_location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `property_proposal`
--

DROP TABLE IF EXISTS `property_proposal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `property_proposal` (
  `id` int(11) NOT NULL,
  `name` varchar(500) DEFAULT NULL,
  `lang` varchar(200) DEFAULT 'hallocasa.pending',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `property_proposal`
--

LOCK TABLES `property_proposal` WRITE;
/*!40000 ALTER TABLE `property_proposal` DISABLE KEYS */;
INSERT INTO `property_proposal` VALUES (1,'{\'en\':\'Sell\',\'de\':\'Verkaufen\',\'es\':\'Vender\'}','hallocasa.pending'),(2,'Rent','hallocasa.pending');
/*!40000 ALTER TABLE `property_proposal` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `property_selector`
--

LOCK TABLES `property_selector` WRITE;
/*!40000 ALTER TABLE `property_selector` DISABLE KEYS */;
/*!40000 ALTER TABLE `property_selector` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `property_selector_option`
--

LOCK TABLES `property_selector_option` WRITE;
/*!40000 ALTER TABLE `property_selector_option` DISABLE KEYS */;
/*!40000 ALTER TABLE `property_selector_option` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `property_selector_type`
--

LOCK TABLES `property_selector_type` WRITE;
/*!40000 ALTER TABLE `property_selector_type` DISABLE KEYS */;
INSERT INTO `property_selector_type` VALUES (1,'Specific options'),(2,'Range');
/*!40000 ALTER TABLE `property_selector_type` ENABLE KEYS */;
UNLOCK TABLES;

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
  `lang` varchar(200) DEFAULT 'hallocasa.pending',
  PRIMARY KEY (`id`),
  KEY `group_id` (`group_id`),
  CONSTRAINT `property_type_ibfk_1` FOREIGN KEY (`group_id`) REFERENCES `property_type_group` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `property_type`
--

LOCK TABLES `property_type` WRITE;
/*!40000 ALTER TABLE `property_type` DISABLE KEYS */;
INSERT INTO `property_type` VALUES (1,'{\'en\':\'Lot\',\'de\':\'Grundst\\u00FCck\',\'es\':\'Lote\'}',1,'hallocasa.pending'),(2,'{\'en\':\'Shopping Mall\',\'de\':\'Einkaufszentrum\',\'es\':\'Centro comercial\'}',2,'hallocasa.pending'),(4,'{\'en\':\'Hotel\',\'de\':\'Hotel\',\'es\':\'Hotel\'}',2,'hallocasa.pending'),(5,'{\'en\':\'Lot with Built\',\'de\':\'Bebautes Grundst\\u00FCck\',\'es\':\'Finca con Casa\'}',2,'hallocasa.pending'),(6,'{\'en\':\'Apartment\',\'de\':\'Wohnung\',\'es\':\'Apartamento\'}',3,'hallocasa.pending'),(9,'{\'en\':\'Penthouse\',\'de\':\'Dachterrassenwohnung\',\'es\':\'Penthouse\'}',3,'hallocasa.pending'),(10,'{\'en\':\'Warehouse\',\'de\':\'Lagerhalle\',\'es\':\'Bodega\'}',3,'hallocasa.pending'),(11,'{\'en\':\'Garage\',\'de\':\'Garage\',\'es\':\'Garaje\'}',3,'hallocasa.pending'),(12,'{\'en\':\'Restaurant\',\'de\':\'Restaurant\',\'es\':\'Restaurante\'}',3,'hallocasa.pending'),(14,'{\'en\':\'Office\',\'de\':\'B\\u00FCro\',\'es\':\'Oficina\'}',3,'hallocasa.pending'),(15,'{\'en\':\'Detached House\',\'de\':\'Einfamilienhaus\',\'es\':\'Casa Unifamiliar\'}',3,'hallocasa.pending'),(16,'{\'en\':\'Apartment Building\',\'de\':\'Mehrfamilienhaus\',\'es\':\'Edificio de Apartamentos\'}',3,'hallocasa.pending'),(17,'{\'en\':\'Airport\',\'de\':\'Flughafen\',\'es\':\'Aeropuerto\'}',3,'hallocasa.pending'),(18,'{\'en\':\'Factory\',\'de\':\'Fabrik\',\'es\':\'F\\u00E1brica\'}',3,'hallocasa.pending'),(19,'{\'en\':\'School\',\'de\':\'Schule\',\'es\':\'Escuela\'}',3,'hallocasa.pending'),(20,'{\'en\':\'Theater\',\'de\':\'Theater\',\'es\':\'Teatro\'}',3,'hallocasa.pending'),(21,'{\'en\':\'Kindergarten\',\'de\':\'Kindergarten\',\'es\':\'Jard\\u00EDn Infantil\'}',3,'hallocasa.pending'),(22,'{\'en\':\'Car Park\',\'de\':\'Parkplatz\',\'es\':\'Estacionamiento\'}',3,'hallocasa.pending'),(23,'{\'en\':\'Hospital\',\'de\':\'Krankenhaus\',\'es\':\'Hospital\'}',3,'hallocasa.pending');
/*!40000 ALTER TABLE `property_type` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `property_type_group`
--

LOCK TABLES `property_type_group` WRITE;
/*!40000 ALTER TABLE `property_type_group` DISABLE KEYS */;
INSERT INTO `property_type_group` VALUES (1,'Lotes'),(2,'Stand-Alone'),(3,'In-House');
/*!40000 ALTER TABLE `property_type_group` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `security_token`
--

LOCK TABLES `security_token` WRITE;
/*!40000 ALTER TABLE `security_token` DISABLE KEYS */;
INSERT INTO `security_token` VALUES (1,'alxvilbol@gmail.com:859963b8ebeb79250c0a53b9793ed770','2016-10-24 20:47:47',900000),(2,'alxvilbol@gmail.com:f122c37fb5f8b33d2fee7a473ae2b5bb','2016-10-24 21:02:19',900000),(3,'alxvilbol@gmail.com:b8279b4a8a3ea9a011d2a335b2c262b3','2016-10-24 21:07:49',900000),(4,'alxvilbol@gmail.com:2c599ff3b1e8c54b0dd5f8b0e2142a28','2016-10-24 21:08:02',900000),(5,'alxvilbol@gmail.com:fa3f2356822270edf6dfd2793839aeb6','2016-10-24 21:15:13',900000),(6,'alxvilbol@gmail.com:7985d018e61b15728fd1cae3eb01599f','2016-10-24 21:39:26',900000),(7,'alxvilbol@gmail.com:6677f671613c7df260c0a209f55bf9c7','2016-10-30 22:21:10',900000),(8,'alxvilbol@gmail.com:6f7f8559cb1d46950785852dad275c85','2016-10-30 22:23:47',900000),(9,'alxvilbol@gmail.com:7437dd45e5b7fc6c31a1d8f92078df00','2016-10-30 22:25:00',900000),(10,'alxvilbol@gmail.com:ff72a70c0d21bfb17350d09e3464c9e3','2016-11-05 19:45:03',900000),(11,'alxvilbol@gmail.com:bd4d1aec4a769048272b11055d1b06dc','2016-11-05 19:58:11',900000),(12,'alxvilbol@gmail.com:a50ff3730f2ea4ca18342a71cb4b3086','2016-11-05 19:59:04',900000),(13,'alxvilbol@gmail.com:74c009df9f51d73d135facddc021ead3','2016-11-05 20:13:23',900000),(14,'alxvilbol@gmail.com:cce3c14244ab405486aa1d55fea3734a','2016-11-05 20:15:26',900000),(15,'alxvilbol@gmail.com:26c3cfcb2632714071fa01d2af751d15','2016-11-05 20:27:28',900000),(16,'alxvilbol@gmail.com:7c97e513d84c54f24ec08cc2a8aecaa5','2016-11-05 20:29:19',900000),(17,'alxvilbol@gmail.com:6d739f268f2827863df5fac0c8bfaaea','2016-11-05 20:37:08',900000),(18,'alxvilbol@gmail.com:27896abf0a77030bf3efde39dbc7fac5','2016-11-05 20:38:51',900000),(19,'alxvilbol@gmail.com:0a3a2221b08f8931ce777382dab17bf3','2016-11-05 20:59:42',900000),(20,'development@hallocasa.com:e1f1746367defcbd199d8fa3e6ddba2c','2016-11-06 11:48:03',900000),(21,'alxvilbol@gmail.com:6ec2cd14c7254972df4c091a8c00a88e','2016-11-12 08:44:42',900000),(22,'alxvilbol@gmail.com:12f01d2660552301f80987d1489a9754','2016-11-12 10:08:07',900000),(23,'alxvilbol@gmail.com:94562ec63207a8ed8a72e8f76ab4f8f1','2016-11-12 10:24:46',900000),(24,'alxvilbol@gmail.com:76274740bfe94f3704c719e952a28c2b','2016-11-12 10:44:15',900000),(25,'alxvilbol@gmail.com:b5ee1ed05e87015acbe6851cc8c0dc6c','2016-11-12 11:12:18',900000),(26,'alxvilbol@gmail.com:c31b11c1d589b3cadc73b6bf989f237d','2016-11-12 11:28:07',900000),(27,'alxvilbol@gmail.com:7acb8db925226d12b8643575b0410be3','2016-11-12 11:47:39',900000),(28,'alxvilbol@gmail.com:4a73a32e2c70f3f8b87468de747248fa','2016-11-12 12:08:26',900000),(30,'alxvilbol@gmail.com:c42e5fd2c6963c4d8a3af4de257a5695','2016-11-12 12:53:50',900000),(32,'alxvilbol@gmail.com:4fa1c428c5b1b21fdfda849b36f3554c','2016-11-12 12:54:04',900000),(34,'alxvilbol@gmail.com:b3db3a1639139e10fd181b509fdee902','2016-11-13 13:30:24',900000),(35,'alxvilbol@gmail.com:c8bbd9916e77a3853334e6310f8bff14','2016-11-13 13:41:54',900000),(36,'alxvilbol@gmail.com:5744cdd76adc6fafb5701a2c6e4fd7b6','2016-11-13 13:45:43',900000),(37,'alxvilbol@gmail.com:19e02995fcdae3725925ed8617df0b7c','2016-11-13 13:52:11',900000),(38,'alxvilbol@gmail.com:ddd7a1f758ba0ed687580b8ac6f6cd61','2016-11-13 14:00:08',900000),(39,'alxvilbol@gmail.com:649aea14a3190a6b65756c103a3b23b4','2016-11-13 14:03:24',900000),(40,'alxvilbol@gmail.com:c9ca70c270d5bda3d876830011b825ac','2016-11-13 14:04:24',900000),(41,'alxvilbol@gmail.com:a1a3edc54ea83bccea1fd12ae800d3b8','2016-11-13 14:06:26',900000),(42,'alxvilbol@gmail.com:e54873b2c3fe9529ff9d0bd3ba771d0b','2016-11-13 14:58:15',900000),(43,'alxvilbol@gmail.com:76655f83e2315409658e378f9195ef10','2016-11-13 15:25:01',900000),(44,'alxvilbol@gmail.com:317d9a206b2eb8062d59041bf4a8a888','2016-11-13 16:04:31',900000),(45,'alxvilbol@gmail.com:70f21f80d1b73b6a86051e9bfe28ae0c','2016-11-13 16:08:23',900000),(46,'alxvilbol@gmail.com:f3b0af43dc9b3ec6a6cc73edf1c97bef','2016-11-13 16:14:17',900000),(47,'alxvilbol@gmail.com:bb0cf48763906274bbe4942e6fa90e96','2016-11-13 16:16:38',900000),(48,'alxvilbol@gmail.com:b0d55ad008928c229162915c23b88cde','2016-11-13 16:38:22',900000),(49,'alxvilbol@gmail.com:eb1275dd64f9765d5399e5b31973f3e4','2016-11-13 17:00:17',900000);
/*!40000 ALTER TABLE `security_token` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `state`
--

LOCK TABLES `state` WRITE;
/*!40000 ALTER TABLE `state` DISABLE KEYS */;
INSERT INTO `state` VALUES (1,'{\'de\':\'Amazonas\',\'en\':\'Amazonas\',\'es\':\'Amazonas\'}',1),(2,'{\'de\':\'Antioquia\',\'en\':\'Antioquia\',\'es\':\'Antioquia\'}',1),(3,'{\'de\':\'Arauca\',\'en\':\'Arauca\',\'es\':\'Arauca\'}',1),(4,'{\'de\':\'Atl\\u00E1ntico\',\'en\':\'Atl\\u00E1ntico\',\'es\':\'Atl\\u00E1ntico\'}',1),(5,'{\'de\':\'Bogotá D.C\',\'en\':\'Bogotá D.C\',\'es\':\'Bogotá D.C\'}',1),(6,'{\'de\':\'Bol\\u00EDvar\',\'en\':\'Bol\\u00EDvar\',\'es\':\'Bol\\u00EDvar\'}',1),(7,'{\'de\':\'Boyac\\u00E1\',\'en\':\'Boyac\\u00E1\',\'es\':\'Boyac\\u00E1\'}',1),(8,'{\'de\':\'Caldas\',\'en\':\'Caldas\',\'es\':\'Caldas\'}',1),(9,'{\'de\':\'Caquet\\u00E1\',\'en\':\'Caquet\\u00E1\',\'es\':\'Caquet\\u00E1\'}',1),(10,'{\'de\':\'Casanare\',\'en\':\'Casanare\',\'es\':\'Casanare\'}',1),(11,'{\'de\':\'Cauca\',\'en\':\'Cauca\',\'es\':\'Cauca\'}',1),(12,'{\'de\':\'Cesar\',\'en\':\'Cesar\',\'es\':\'Cesar\'}',1),(13,'{\'de\':\'Choc\\u00F3\',\'en\':\'Choc\\u00F3\',\'es\':\'Choc\\u00F3\'}',1),(14,'{\'de\':\'C\\u00F3rdoba\',\'en\':\'C\\u00F3rdoba\',\'es\':\'C\\u00F3rdoba\'}',1),(15,'{\'de\':\'Cundinamarca\',\'en\':\'Cundinamarca\',\'es\':\'Cundinamarca\'}',1),(16,'{\'de\':\'G\\u00FCainia\',\'en\':\'G\\u00FCainia\',\'es\':\'G\\u00FCainia\'}',1),(17,'{\'de\':\'Guaviare\',\'en\':\'Guaviare\',\'es\':\'Guaviare\'}',1),(18,'{\'de\':\'Huila\',\'en\':\'Huila\',\'es\':\'Huila\'}',1),(19,'{\'de\':\'La Guajira\',\'en\':\'La Guajira\',\'es\':\'La Guajira\'}',1),(20,'{\'de\':\'Magdalena\',\'en\':\'Magdalena\',\'es\':\'Magdalena\'}',1),(21,'{\'de\':\'Meta\',\'en\':\'Meta\',\'es\':\'Meta\'}',1),(22,'{\'de\':\'Nari\\u00F1o\',\'en\':\'Nari\\u00F1o\',\'es\':\'Nari\\u00F1o\'}',1),(23,'{\'de\':\'Norte de Santander\',\'en\':\'Norte de Santander\',\'es\':\'Norte de Santander\'}',1),(24,'{\'de\':\'Putumayo\',\'en\':\'Putumayo\',\'es\':\'Putumayo\'}',1),(25,'{\'de\':\'Quind\\u00EDo\',\'en\':\'Quind\\u00EDo\',\'es\':\'Quind\\u00EDo\'}',1),(26,'{\'de\':\'Risaralda\',\'en\':\'Risaralda\',\'es\':\'Risaralda\'}',1),(27,'{\'de\':\'San Andr\\u00E9s y Providencia\',\'en\':\'San Andr\\u00E9s y Providencia\',\'es\':\'San Andr\\u00E9s y Providencia\'}',1),(28,'{\'de\':\'Santander\',\'en\':\'Santander\',\'es\':\'Santander\'}',1),(29,'{\'de\':\'Sucre\',\'en\':\'Sucre\',\'es\':\'Sucre\'}',1),(30,'{\'de\':\'Tolima\',\'en\':\'Tolima\',\'es\':\'Tolima\'}',1),(31,'{\'de\':\'Valle del Cauca\',\'en\':\'Valle del Cauca\',\'es\':\'Valle del Cauca\'}',1),(32,'{\'de\':\'Vaup\\u00E9s\',\'en\':\'Vaup\\u00E9s\',\'es\':\'Vaup\\u00E9s\'}',1),(33,'{\'de\':\'Vichada\',\'en\':\'Vichada\',\'es\':\'Vichada\'}',1);
/*!40000 ALTER TABLE `state` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `telephone`
--

LOCK TABLES `telephone` WRITE;
/*!40000 ALTER TABLE `telephone` DISABLE KEYS */;
/*!40000 ALTER TABLE `telephone` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `telephone_prefix`
--

LOCK TABLES `telephone_prefix` WRITE;
/*!40000 ALTER TABLE `telephone_prefix` DISABLE KEYS */;
INSERT INTO `telephone_prefix` VALUES (1,1,'Canada'),(2,1,'North America'),(3,1,'United States'),(4,7,'Kazakhstan'),(5,7,'Russia'),(6,20,'Egypt'),(7,27,'South Africa'),(8,30,'Greece'),(9,31,'Netherlands'),(10,32,'Belgium'),(11,33,'France'),(12,34,'Spain'),(13,36,'Hungary'),(14,39,'Italy'),(15,40,'Romania'),(16,41,'Switzerland'),(17,43,'Austria'),(18,44,'United Kingdom'),(19,45,'Denmark'),(20,46,'Sweden'),(21,47,'Norway'),(22,48,'Poland'),(23,49,'Germany'),(24,51,'Peru'),(25,52,'Mexico'),(26,53,'Cuba'),(27,54,'Argentina'),(28,55,'Brazil'),(29,56,'Chile'),(30,57,'Colombia'),(31,58,'Venezuela'),(32,60,'Malaysia'),(33,61,'Australia'),(34,62,'Indonesia'),(35,63,'Philippines'),(36,64,'New Zealand'),(37,65,'Singapore'),(38,66,'Thailand'),(39,81,'Japan'),(40,82,'Korea (South)'),(41,84,'Viet Nam'),(42,86,'China'),(43,90,'Turkey'),(44,91,'India'),(45,92,'Pakistan'),(46,93,'Afghanistan'),(47,94,'Sri Lanka'),(48,95,'Myanmar (Burma)'),(49,98,'Iran'),(50,212,'Morocco'),(51,213,'Algeria'),(52,216,'Tunisia'),(53,218,'Libya'),(54,220,'Gambia'),(55,221,'Senegal'),(56,222,'Mauritania'),(57,223,'Mali'),(58,224,'Guinea'),(59,225,'Ivory Coast'),(60,226,'Burkina Faso'),(61,227,'Niger'),(62,228,'Togo'),(63,229,'Benin'),(64,230,'Mauritius'),(65,231,'Liberia'),(66,232,'Sierra Leone'),(67,233,'Ghana'),(68,234,'Nigeria'),(69,235,'Chad'),(70,236,'Central African Rep.'),(71,237,'Cameroon'),(72,238,'Cape Verde'),(73,239,'SÃ£o TomÃ© & PrÃ­ncipe'),(74,240,'Equatorial Guinea'),(75,241,'Gabon'),(76,242,'Congo'),(77,243,'Dem. Rep. Congo'),(78,244,'Angola'),(79,245,'Guinea-Bissau'),(80,246,'Diego Garcia'),(81,247,'Ascension'),(82,248,'Seychelles'),(83,249,'Sudan'),(84,250,'Rwanda'),(85,251,'Ethiopia'),(86,252,'Somalia'),(87,253,'Djibouti'),(88,254,'Kenya'),(89,255,'Tanzania'),(90,256,'Uganda'),(91,257,'Burundi'),(92,258,'Mozambique'),(93,260,'Zambia'),(94,261,'Madagascar'),(95,262,'French Indian Ocean'),(96,263,'Zimbabwe'),(97,264,'Namibia'),(98,265,'Malawi'),(99,266,'Lesotho'),(100,267,'Botswana'),(101,268,'Swaziland'),(102,269,'Comoros'),(103,290,'Saint Helena'),(104,291,'Eritrea'),(105,297,'Aruba'),(106,298,'Faroe Islands'),(107,299,'Greenland'),(108,350,'Gibraltar'),(109,351,'Portugal'),(110,352,'Luxembourg'),(111,353,'Ireland'),(112,354,'Iceland'),(113,355,'Albania'),(114,356,'Malta'),(115,357,'Cyprus'),(116,358,'Finland'),(117,359,'Bulgaria'),(118,370,'Lithuania'),(119,371,'Latvia'),(120,372,'Estonia'),(121,373,'Moldova'),(122,374,'Armenia'),(123,375,'Belarus'),(124,376,'Andorra'),(125,377,'Monaco'),(126,378,'San Marino'),(127,380,'Ukraine'),(128,381,'Serbia'),(129,382,'Montenegro'),(130,385,'Croatia'),(131,386,'Slovenia'),(132,387,'Bosnia - Herzegovina'),(133,388,'European Numbers'),(134,389,'Macedonia'),(135,420,'Czech Republic'),(136,421,'Slovakia'),(137,423,'Liechtenstein'),(138,500,'Falkland Islands'),(139,501,'Belize'),(140,502,'Guatemala'),(141,503,'El Salvador'),(142,504,'Honduras'),(143,505,'Nicaragua'),(144,506,'Costa Rica'),(145,507,'Panama'),(146,508,'St Pierre & MiquÃ©lon'),(147,509,'Haiti'),(148,590,'Guadeloupe'),(149,591,'Bolivia'),(150,592,'Guyana'),(151,593,'Ecuador'),(152,594,'Guiana (French)'),(153,595,'Paraguay'),(154,596,'Martinique'),(155,597,'Suriname'),(156,598,'Uruguay'),(157,599,'Netherlands Antilles'),(158,670,'Timor-Leste'),(159,673,'Brunei Darussalam'),(160,674,'Nauru'),(161,675,'Papua New Guinea'),(162,676,'Tonga'),(163,677,'Solomon Islands'),(164,678,'Vanuatu'),(165,679,'Fiji'),(166,680,'Palau'),(167,681,'Wallis and Futuna'),(168,682,'Cook Islands'),(169,683,'Niue'),(170,685,'Western Samoa'),(171,686,'Kiribati'),(172,687,'New Caledonia'),(173,688,'Tuvalu'),(174,689,'French Polynesia'),(175,690,'Tokelau'),(176,691,'Micronesia'),(177,692,'Marshall Islands'),(178,850,'Korea (North)'),(179,852,'Hong Kong'),(180,853,'Macau'),(181,855,'Cambodia'),(182,856,'Laos'),(183,880,'Bangladesh'),(184,886,'Taiwan/\"reserved\"'),(185,960,'Maldives'),(186,961,'Lebanon'),(187,962,'Jordan'),(188,963,'Syria'),(189,964,'Iraq'),(190,965,'Kuwait'),(191,966,'Saudi Arabia'),(192,967,'Yemen'),(193,968,'Oman'),(194,970,'Palestine'),(195,971,'United Arab Emirates'),(196,972,'Israel'),(197,973,'Bahrain'),(198,974,'Qatar'),(199,975,'Bhutan'),(200,976,'Mongolia'),(201,977,'Nepal'),(202,992,'Tajikistan'),(203,993,'Turkmenistan'),(204,994,'Azerbaijan'),(205,995,'Georgia'),(206,996,'Kyrgyzstan'),(207,998,'Uzbekistan');
/*!40000 ALTER TABLE `telephone_prefix` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `token`
--

LOCK TABLES `token` WRITE;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;
/*!40000 ALTER TABLE `token` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `use_case`
--

LOCK TABLES `use_case` WRITE;
/*!40000 ALTER TABLE `use_case` DISABLE KEYS */;
INSERT INTO `use_case` VALUES (1,'/hallocasa/user/my-profile');
/*!40000 ALTER TABLE `use_case` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'alxvilbol@gmail.com','d2392d23516b0ca59c4aaae13d3ddc39',NULL,NULL,NULL,NULL,NULL,NULL,'es',1,NULL,'[]',NULL,NULL,'/userimage/user0.jpg',NULL,'en',NULL),(2,'development@hallocasa.com','26239de944662b89a61df8c350e47f48',NULL,NULL,NULL,NULL,NULL,NULL,'es',1,NULL,'[]',NULL,NULL,'/userimage/user0.jpg',NULL,'en',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `user_profile`
--

LOCK TABLES `user_profile` WRITE;
/*!40000 ALTER TABLE `user_profile` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_profile` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `user_type`
--

LOCK TABLES `user_type` WRITE;
/*!40000 ALTER TABLE `user_type` DISABLE KEYS */;
INSERT INTO `user_type` VALUES (2,'{\'en\':\'Broker\',\'de\':\'Makler\',\'es\':\'Corredor\'}',NULL,0,1),(3,'{\'en\':\'Notary\',\'de\':\'Notar\',\'es\':\'Notario\'}',NULL,0,1),(4,'{\'en\':\'Appraiser\',\'de\':\'Gutachter\',\'es\':\'Tasador\'}',NULL,0,1),(5,'{\'en\':\'Translator\',\'de\':\'\\u00DCbersetzer\',\'es\':\'Traductor\'}',NULL,0,1),(6,'{\'en\':\'Administrator\',\'de\':\'Verwalter\',\'es\':\'Administrador\'}',NULL,0,0),(7,'{\'en\':\'Expert\',\'de\':\'Experte\',\'es\':\'Experto\'}','{\'en\':\'Investors will contact you in order to obtain market insights or custom questions\',\'de\':\'Investors will contact you in ordert to obtain market insights or custom questions\',\'es\':\'Investors will contact you in ordert to obtain market insights or custom questions\'}',1,0),(8,'{\'en\':\'Lawyer\',\'es\':\'Abogado\',\'de\':\'Anwalt\'}',NULL,0,0);
/*!40000 ALTER TABLE `user_type` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `user_type_profiles`
--

LOCK TABLES `user_type_profiles` WRITE;
/*!40000 ALTER TABLE `user_type_profiles` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_type_profiles` ENABLE KEYS */;
UNLOCK TABLES;

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

--
-- Dumping data for table `user_user_type`
--

LOCK TABLES `user_user_type` WRITE;
/*!40000 ALTER TABLE `user_user_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_user_type` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-14 13:26:01
