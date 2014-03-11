CREATE DATABASE  IF NOT EXISTS `seaports` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `seaports`;
-- MySQL dump 10.13  Distrib 5.6.13, for osx10.6 (i386)
--
-- Host: 127.0.0.1    Database: seaports
-- ------------------------------------------------------
-- Server version	5.6.15

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
-- Table structure for table `climate_emission_scenario`
--

DROP TABLE IF EXISTS `climate_emission_scenario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `climate_emission_scenario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `climate_emission_scenario`
--

LOCK TABLES `climate_emission_scenario` WRITE;
/*!40000 ALTER TABLE `climate_emission_scenario` DISABLE KEYS */;
INSERT INTO `climate_emission_scenario` VALUES (1,'medium','A1B'),(2,'high','A1FI'),(3,'No CO2 emissions increase','Base');
/*!40000 ALTER TABLE `climate_emission_scenario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `climate_model`
--

DROP TABLE IF EXISTS `climate_model`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `climate_model` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `climate_model`
--

LOCK TABLES `climate_model` WRITE;
/*!40000 ALTER TABLE `climate_model` DISABLE KEYS */;
INSERT INTO `climate_model` VALUES (1,'csiro_mk3_5','The CSIRO Mk3.5 climate model','CSIRO Mk3.5'),(2,'mri_cgcm2_3_2','The MRI-CGCM 2.3.2 climate model','MRI-CGCM2.3.2'),(3,'ipsl_cm4','The IPSL-CM4 climate model','IPSL-CM4'),(4,'miroc_3_2_medres','The Miroc 3.2 MedRes climate model','MIROC 3.2 (medres)'),(5,'cccma_cgcm3_1_t63','The CCCMA CGCM 3.1 T63 climate model','CCCMA CGCM 3.1 T63'),(6,'reference','Reference climate model: considering there is no change','(Reference)');
/*!40000 ALTER TABLE `climate_model` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cmar_data`
--

DROP TABLE IF EXISTS `cmar_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cmar_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `creation_date` datetime DEFAULT NULL,
  `model_name` varchar(255) DEFAULT NULL,
  `value` longtext,
  `year` int(11) DEFAULT NULL,
  `climate_emission_scenario_id` int(11) DEFAULT NULL,
  `climate_model_id` int(11) DEFAULT NULL,
  `region_id` int(11) DEFAULT NULL,
  `climate_variable_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK64859B4EE13746FC` (`climate_model_id`),
  KEY `FK64859B4EEC39C8A9` (`climate_variable_id`),
  KEY `FK64859B4E18AA137B` (`climate_emission_scenario_id`),
  KEY `FK64859B4E6B59B27` (`region_id`),
  CONSTRAINT `FK64859B4E6B59B27` FOREIGN KEY (`region_id`) REFERENCES `region` (`id`),
  CONSTRAINT `FK64859B4E18AA137B` FOREIGN KEY (`climate_emission_scenario_id`) REFERENCES `climate_emission_scenario` (`id`),
  CONSTRAINT `FK64859B4EE13746FC` FOREIGN KEY (`climate_model_id`) REFERENCES `climate_model` (`id`),
  CONSTRAINT `FK64859B4EEC39C8A9` FOREIGN KEY (`climate_variable_id`) REFERENCES `csiro_variable` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cmar_data`
--

LOCK TABLES `cmar_data` WRITE;
/*!40000 ALTER TABLE `cmar_data` DISABLE KEYS */;
INSERT INTO `cmar_data` VALUES (1,'2008-07-27 00:00:00','Most Likely','-34.5,151.5,167;-33.5,152.5,176;-32.5,152.5,168;-31.5,156;-30.5,153.5,164;-29.5,153.5,163;-28.5,153.5,159',2030,1,3,1,5),(2,'2008-07-27 00:00:00','Most Likely','-34.5,151.5,437;-33.5,152.5,447;-32.5,152.5,445;-31.5,153.5,439;-30.5,153.5,436;-29.5,153.5,434;-28.5,153.5,429',2070,1,3,1,5),(3,'2008-07-27 00:00:00','Most Likely','-39.5,146.5,128;-39.5,147.5,132;-38.5,148.5,156;-38.5,149.5,163;-37.5,150.5,180;-36.5,150.5,176;-35.5,151.5,172;-34.5,151.5,167',2030,1,3,2,5),(4,'2008-07-27 00:00:00','Most Likely','-39.5,146.5,369;-39.5,147.5,375;-38.5,148.5,416;-38.5,149.5,426;-37.5,150.5,444;-36.5,150.5,440;-35.5,151.5,440;-34.5,151.5,437',2070,1,3,2,5),(5,'2008-07-27 00:00:00','Most Likely','-27.5,113.5,125;-28.5,113.5,124;-29.5,114.5,121;-30.5,114.5,120;-31.5,115.5,119;-32.5,115.5,118;-33.5,114.5,118;-34.5,114.5,117;-34.5,115.5,117;-35.5,116.5,119;-35.5,117.5,119;-35.5,118.5,121;-34.5,119.5,118;-34.5,120.5,118;-34.5,121.5,123;-34.5,122.5,123;-34.5,123.5,124;-33.5,124.5,120',2030,1,4,3,5),(6,'2008-07-27 00:00:00','Most Likely','-27.5,113.5,370;-28.5,113.5,369;-29.5,114.5,365;-30.5,114.5,363;-31.5,115.5,361;-32.5,115.5,360;-33.5,114.5,360;-34.5,114.5,360;-34.5,115.5,361;-35.5,116.5,363;-35.5,117.5,363;-35.5,118.5,364;-34.5,119.5,354;-34.5,120.5,354;-34.5,121.5,361;-34.5,122.5,360;-34.5,123.5,360;-33.5,124.5,354',2070,1,4,3,5);
/*!40000 ALTER TABLE `cmar_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `csiro_data`
--

DROP TABLE IF EXISTS `csiro_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `csiro_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `baseline` double DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `model_name` varchar(255) DEFAULT NULL,
  `value` double DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  `climate_emission_scenario_id` int(11) DEFAULT NULL,
  `climate_model_id` int(11) DEFAULT NULL,
  `region_id` int(11) DEFAULT NULL,
  `csiro_variable_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKFFD9FC53E13746FC` (`climate_model_id`),
  KEY `FKFFD9FC5318AA137B` (`climate_emission_scenario_id`),
  KEY `FKFFD9FC536B59B27` (`region_id`),
  KEY `FKFFD9FC53377894FA` (`csiro_variable_id`),
  CONSTRAINT `FKFFD9FC53377894FA` FOREIGN KEY (`csiro_variable_id`) REFERENCES `csiro_variable` (`id`),
  CONSTRAINT `FKFFD9FC5318AA137B` FOREIGN KEY (`climate_emission_scenario_id`) REFERENCES `climate_emission_scenario` (`id`),
  CONSTRAINT `FKFFD9FC536B59B27` FOREIGN KEY (`region_id`) REFERENCES `region` (`id`),
  CONSTRAINT `FKFFD9FC53E13746FC` FOREIGN KEY (`climate_model_id`) REFERENCES `climate_model` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=217 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `csiro_data`
--

LOCK TABLES `csiro_data` WRITE;
/*!40000 ALTER TABLE `csiro_data` DISABLE KEYS */;
INSERT INTO `csiro_data` VALUES (1,16.1,'2008-07-27 00:00:00','Hotter & Drier',1.3,2030,1,1,1,1),(2,16.1,'2008-07-27 00:00:00','Hotter & Drier',2.4,2055,1,1,1,1),(3,16.1,'2008-07-27 00:00:00','Hotter & Drier',3,2070,1,1,1,1),(4,16.1,'2008-07-27 00:00:00','Hotter & Drier',1.2,2030,2,1,1,1),(5,16.1,'2008-07-27 00:00:00','Hotter & Drier',3,2055,2,1,1,1),(6,16.1,'2008-07-27 00:00:00','Hotter & Drier',4.1,2070,2,1,1,1),(7,16.1,'2008-07-27 00:00:00','Most Likely',1,2030,1,3,1,1),(8,16.1,'2008-07-27 00:00:00','Most Likely',2,2055,1,3,1,1),(9,16.1,'2008-07-27 00:00:00','Most Likely',2.5,2070,1,3,1,1),(10,16.1,'2008-07-27 00:00:00','Most Likely',1,2030,2,3,1,1),(11,16.1,'2008-07-27 00:00:00','Most Likely',2.4,2055,2,3,1,1),(12,16.1,'2008-07-27 00:00:00','Most Likely',3.4,2070,2,3,1,1),(13,16.1,'2008-07-27 00:00:00','Cooler & Wetter',0.7,2030,1,4,1,1),(14,16.1,'2008-07-27 00:00:00','Cooler & Wetter',1.3,2055,1,4,1,1),(15,16.1,'2008-07-27 00:00:00','Cooler & Wetter',1.7,2070,1,4,1,1),(16,16.1,'2008-07-27 00:00:00','Cooler & Wetter',0.7,2030,2,4,1,1),(17,16.1,'2008-07-27 00:00:00','Cooler & Wetter',1.7,2055,2,4,1,1),(18,16.1,'2008-07-27 00:00:00','Cooler & Wetter',2.3,2070,2,4,1,1),(19,4.6,'2008-07-27 00:00:00','Hotter & Drier',0,2030,1,1,1,2),(20,4.6,'2008-07-27 00:00:00','Hotter & Drier',0,2055,1,1,1,2),(21,4.6,'2008-07-27 00:00:00','Hotter & Drier',0.1,2070,1,1,1,2),(22,4.6,'2008-07-27 00:00:00','Hotter & Drier',0,2030,2,1,1,2),(23,4.6,'2008-07-27 00:00:00','Hotter & Drier',0.1,2055,2,1,1,2),(24,4.6,'2008-07-27 00:00:00','Hotter & Drier',0.1,2070,2,1,1,2),(25,4.6,'2008-07-27 00:00:00','Most Likely',-2.1,2030,1,3,1,2),(26,4.6,'2008-07-27 00:00:00','Most Likely',-4,2055,1,3,1,2),(27,4.6,'2008-07-27 00:00:00','Most Likely',-5,2070,1,3,1,2),(28,4.6,'2008-07-27 00:00:00','Most Likely',-2,2030,2,3,1,2),(29,4.6,'2008-07-27 00:00:00','Most Likely',-4.9,2055,2,3,1,2),(30,4.6,'2008-07-27 00:00:00','Most Likely',-6.8,2070,2,3,1,2),(31,4.6,'2008-07-27 00:00:00','Cooler & Wetter',-1.3,2030,1,4,1,2),(32,4.6,'2008-07-27 00:00:00','Cooler & Wetter',-2.4,2055,1,4,1,2),(33,4.6,'2008-07-27 00:00:00','Cooler & Wetter',-3,2070,1,4,1,2),(34,4.6,'2008-07-27 00:00:00','Cooler & Wetter',-1.2,2030,2,4,1,2),(35,4.6,'2008-07-27 00:00:00','Cooler & Wetter',-2.9,2055,2,4,1,2),(36,4.6,'2008-07-27 00:00:00','Cooler & Wetter',-4.1,2070,2,4,1,2),(37,1029.8,'2008-07-27 00:00:00','Hotter & Drier',-4.7,2030,1,1,1,3),(38,1029.8,'2008-07-27 00:00:00','Hotter & Drier',-8.8,2055,1,1,1,3),(39,1029.8,'2008-07-27 00:00:00','Hotter & Drier',-11.1,2070,1,1,1,3),(40,1029.8,'2008-07-27 00:00:00','Hotter & Drier',-4.5,2030,2,1,1,3),(41,1029.8,'2008-07-27 00:00:00','Hotter & Drier',-10.9,2055,2,1,1,3),(42,1029.8,'2008-07-27 00:00:00','Hotter & Drier',-15.1,2070,2,1,1,3),(43,1029.8,'2008-07-27 00:00:00','Most Likely',-4.5,2030,1,3,1,3),(44,1029.8,'2008-07-27 00:00:00','Most Likely',-8.4,2055,1,3,1,3),(45,1029.8,'2008-07-27 00:00:00','Most Likely',-10.6,2070,1,3,1,3),(46,1029.8,'2008-07-27 00:00:00','Most Likely',-4.3,2030,2,3,1,3),(47,1029.8,'2008-07-27 00:00:00','Most Likely',-10.4,2055,2,3,1,3),(48,1029.8,'2008-07-27 00:00:00','Most Likely',-14.4,2070,2,3,1,3),(49,1029.8,'2008-07-27 00:00:00','Cooler & Wetter',4.1,2030,1,4,1,3),(50,1029.8,'2008-07-27 00:00:00','Cooler & Wetter',7.7,2055,1,4,1,3),(51,1029.8,'2008-07-27 00:00:00','Cooler & Wetter',9.7,2070,1,4,1,3),(52,1029.8,'2008-07-27 00:00:00','Cooler & Wetter',4,2030,2,4,1,3),(53,1029.8,'2008-07-27 00:00:00','Cooler & Wetter',9.5,2055,2,4,1,3),(54,1029.8,'2008-07-27 00:00:00','Cooler & Wetter',13.2,2070,2,4,1,3),(55,74.8,'2008-07-27 00:00:00','Hotter & Drier',-1.3,2030,1,1,1,4),(56,74.8,'2008-07-27 00:00:00','Hotter & Drier',-5.6,2055,1,1,1,4),(57,74.8,'2008-07-27 00:00:00','Hotter & Drier',-7.1,2070,1,1,1,4),(58,74.8,'2008-07-27 00:00:00','Hotter & Drier',-2.9,2030,2,1,1,4),(59,74.8,'2008-07-27 00:00:00','Hotter & Drier',-7,2055,2,1,1,4),(60,74.8,'2008-07-27 00:00:00','Hotter & Drier',-9.7,2070,2,1,1,4),(61,74.8,'2008-07-27 00:00:00','Most Likely',-0.2,2030,1,3,1,4),(62,74.8,'2008-07-27 00:00:00','Most Likely',-0.4,2055,1,3,1,4),(63,74.8,'2008-07-27 00:00:00','Most Likely',-0.5,2070,1,3,1,4),(64,74.8,'2008-07-27 00:00:00','Most Likely',-0.2,2030,2,3,1,4),(65,74.8,'2008-07-27 00:00:00','Most Likely',-0.5,2055,2,3,1,4),(66,74.8,'2008-07-27 00:00:00','Most Likely',-0.7,2070,2,3,1,4),(67,74.8,'2008-07-27 00:00:00','Cooler & Wetter',1.2,2030,1,4,1,4),(68,74.8,'2008-07-27 00:00:00','Cooler & Wetter',2.3,2055,1,4,1,4),(69,74.8,'2008-07-27 00:00:00','Cooler & Wetter',2.9,2070,1,4,1,4),(70,74.8,'2008-07-27 00:00:00','Cooler & Wetter',1.2,2030,2,4,1,4),(71,74.8,'2008-07-27 00:00:00','Cooler & Wetter',2.9,2055,2,4,1,4),(72,74.8,'2008-07-27 00:00:00','Cooler & Wetter',4,2070,2,4,1,4),(73,12.8,'2008-07-27 00:00:00','Hotter & Drier',1.1,2030,1,1,2,1),(74,12.8,'2008-07-27 00:00:00','Hotter & Drier',2,2055,1,1,2,1),(75,12.8,'2008-07-27 00:00:00','Hotter & Drier',2.6,2070,1,1,2,1),(76,12.8,'2008-07-27 00:00:00','Hotter & Drier',1,2030,2,1,2,1),(77,12.8,'2008-07-27 00:00:00','Hotter & Drier',2.5,2055,2,1,2,1),(78,12.8,'2008-07-27 00:00:00','Hotter & Drier',3.5,2070,2,1,2,1),(79,12.8,'2008-07-27 00:00:00','Most Likely',1,2030,1,3,2,1),(80,12.8,'2008-07-27 00:00:00','Most Likely',1.9,2055,1,3,2,1),(81,12.8,'2008-07-27 00:00:00','Most Likely',2.4,2070,1,3,2,1),(82,12.8,'2008-07-27 00:00:00','Most Likely',1,2030,2,3,2,1),(83,12.8,'2008-07-27 00:00:00','Most Likely',2.4,2055,2,3,2,1),(84,12.8,'2008-07-27 00:00:00','Most Likely',3.3,2070,2,3,2,1),(85,12.8,'2008-07-27 00:00:00','Cooler & Wetter',0.6,2030,1,4,2,1),(86,12.8,'2008-07-27 00:00:00','Cooler & Wetter',1.2,2055,1,4,2,1),(87,12.8,'2008-07-27 00:00:00','Cooler & Wetter',1.5,2070,1,4,2,1),(88,12.8,'2008-07-27 00:00:00','Cooler & Wetter',0.6,2030,2,4,2,1),(89,12.8,'2008-07-27 00:00:00','Cooler & Wetter',1.5,2055,2,4,2,1),(90,12.8,'2008-07-27 00:00:00','Cooler & Wetter',2,2070,2,4,2,1),(91,5.3,'2008-07-27 00:00:00','Hotter & Drier',-1.6,2030,1,1,2,2),(92,5.3,'2008-07-27 00:00:00','Hotter & Drier',-3.1,2055,1,1,2,2),(93,5.3,'2008-07-27 00:00:00','Hotter & Drier',-3.9,2070,1,1,2,2),(94,5.3,'2008-07-27 00:00:00','Hotter & Drier',-1.6,2030,2,1,2,2),(95,5.3,'2008-07-27 00:00:00','Hotter & Drier',-3.8,2055,2,1,2,2),(96,5.3,'2008-07-27 00:00:00','Hotter & Drier',-5.3,2070,2,1,2,2),(97,5.3,'2008-07-27 00:00:00','Most Likely',-2.7,2030,1,3,2,2),(98,5.3,'2008-07-27 00:00:00','Most Likely',-5,2055,1,3,2,2),(99,5.3,'2008-07-27 00:00:00','Most Likely',-6.4,2070,1,3,2,2),(100,5.3,'2008-07-27 00:00:00','Most Likely',-2.6,2030,2,3,2,2),(101,5.3,'2008-07-27 00:00:00','Most Likely',-6.3,2055,2,3,2,2),(102,5.3,'2008-07-27 00:00:00','Most Likely',-8.7,2070,2,3,2,2),(103,5.3,'2008-07-27 00:00:00','Cooler & Wetter',0.1,2030,1,4,2,2),(104,5.3,'2008-07-27 00:00:00','Cooler & Wetter',0.2,2055,1,4,2,2),(105,5.3,'2008-07-27 00:00:00','Cooler & Wetter',0.2,2070,1,4,2,2),(106,5.3,'2008-07-27 00:00:00','Cooler & Wetter',0.1,2030,2,4,2,2),(107,5.3,'2008-07-27 00:00:00','Cooler & Wetter',0.2,2055,2,4,2,2),(108,5.3,'2008-07-27 00:00:00','Cooler & Wetter',0.3,2070,2,4,2,2),(109,870,'2008-07-27 00:00:00','Hotter & Drier',-4.5,2030,1,1,2,3),(110,870,'2008-07-27 00:00:00','Hotter & Drier',-8.5,2055,1,1,2,3),(111,870,'2008-07-27 00:00:00','Hotter & Drier',-10.8,2070,1,1,2,3),(112,870,'2008-07-27 00:00:00','Hotter & Drier',-4.4,2030,2,1,2,3),(113,870,'2008-07-27 00:00:00','Hotter & Drier',-10.6,2055,2,1,2,3),(114,870,'2008-07-27 00:00:00','Hotter & Drier',-14.7,2070,2,1,2,3),(115,870,'2008-07-27 00:00:00','Most Likely',-4.1,2030,1,3,2,3),(116,870,'2008-07-27 00:00:00','Most Likely',-7.7,2055,1,3,2,3),(117,870,'2008-07-27 00:00:00','Most Likely',-9.8,2070,1,3,2,3),(118,870,'2008-07-27 00:00:00','Most Likely',-4,2030,2,3,2,3),(119,870,'2008-07-27 00:00:00','Most Likely',-9.6,2055,2,3,2,3),(120,870,'2008-07-27 00:00:00','Most Likely',-13.3,2070,2,3,2,3),(121,870,'2008-07-27 00:00:00','Cooler & Wetter',2,2030,1,4,2,3),(122,870,'2008-07-27 00:00:00','Cooler & Wetter',3.7,2055,1,4,2,3),(123,870,'2008-07-27 00:00:00','Cooler & Wetter',4.6,2070,1,4,2,3),(124,870,'2008-07-27 00:00:00','Cooler & Wetter',1.9,2030,2,4,2,3),(125,870,'2008-07-27 00:00:00','Cooler & Wetter',4.6,2055,2,4,2,3),(126,870,'2008-07-27 00:00:00','Cooler & Wetter',6.3,2070,2,4,2,3),(127,71.3,'2008-07-27 00:00:00','Hotter & Drier',-2.2,2030,1,1,2,4),(128,71.3,'2008-07-27 00:00:00','Hotter & Drier',-4.2,2055,1,1,2,4),(129,71.3,'2008-07-27 00:00:00','Hotter & Drier',-5.3,2070,1,1,2,4),(130,71.3,'2008-07-27 00:00:00','Hotter & Drier',-2.1,2030,2,1,2,4),(131,71.3,'2008-07-27 00:00:00','Hotter & Drier',-5.2,2055,2,1,2,4),(132,71.3,'2008-07-27 00:00:00','Hotter & Drier',-7.2,2070,2,1,2,4),(133,71.3,'2008-07-27 00:00:00','Most Likely',-0.8,2030,1,3,2,4),(134,71.3,'2008-07-27 00:00:00','Most Likely',-1.6,2055,1,3,2,4),(135,71.3,'2008-07-27 00:00:00','Most Likely',-2,2070,1,3,2,4),(136,71.3,'2008-07-27 00:00:00','Most Likely',-0.8,2030,2,3,2,4),(137,71.3,'2008-07-27 00:00:00','Most Likely',-2,2055,2,3,2,4),(138,71.3,'2008-07-27 00:00:00','Most Likely',-2.7,2070,2,3,2,4),(139,71.3,'2008-07-27 00:00:00','Cooler & Wetter',0.2,2030,1,4,2,4),(140,71.3,'2008-07-27 00:00:00','Cooler & Wetter',0.3,2055,1,4,2,4),(141,71.3,'2008-07-27 00:00:00','Cooler & Wetter',0.4,2070,1,4,2,4),(142,71.3,'2008-07-27 00:00:00','Cooler & Wetter',0.2,2030,2,4,2,4),(143,71.3,'2008-07-27 00:00:00','Cooler & Wetter',0.4,2055,2,4,2,4),(144,71.3,'2008-07-27 00:00:00','Cooler & Wetter',0.6,2070,2,4,2,4),(145,17.6,'2008-07-27 00:00:00','Hotter & Drier',1.1,2030,1,1,3,1),(146,17.6,'2008-07-27 00:00:00','Hotter & Drier',2.1,2055,1,1,3,1),(147,17.6,'2008-07-27 00:00:00','Hotter & Drier',2.6,2070,1,1,3,1),(148,17.6,'2008-07-27 00:00:00','Hotter & Drier',1.1,2030,2,1,3,1),(149,17.6,'2008-07-27 00:00:00','Hotter & Drier',2.6,2055,2,1,3,1),(150,17.6,'2008-07-27 00:00:00','Hotter & Drier',3.6,2070,2,1,3,1),(151,17.6,'2008-07-27 00:00:00','Most Likely',0.7,2030,1,4,3,1),(152,17.6,'2008-07-27 00:00:00','Most Likely',1.2,2055,1,4,3,1),(153,17.6,'2008-07-27 00:00:00','Most Likely',2.6,2070,1,4,3,1),(154,17.6,'2008-07-27 00:00:00','Most Likely',0.6,2030,2,4,3,1),(155,17.6,'2008-07-27 00:00:00','Most Likely',1.5,2055,2,4,3,1),(156,17.6,'2008-07-27 00:00:00','Most Likely',2.1,2070,2,4,3,1),(157,17.6,'2008-07-27 00:00:00','Cooler & Wetter',0.9,2030,1,5,3,1),(158,17.6,'2008-07-27 00:00:00','Cooler & Wetter',1.7,2055,1,5,3,1),(159,17.6,'2008-07-27 00:00:00','Cooler & Wetter',2.1,2070,1,5,3,1),(160,17.6,'2008-07-27 00:00:00','Cooler & Wetter',0.9,2030,2,5,3,1),(161,17.6,'2008-07-27 00:00:00','Cooler & Wetter',2.1,2055,2,5,3,1),(162,17.6,'2008-07-27 00:00:00','Cooler & Wetter',2.9,2070,2,5,3,1),(163,5.4,'2008-07-27 00:00:00','Hotter & Drier',-1.5,2030,1,1,3,2),(164,5.4,'2008-07-27 00:00:00','Hotter & Drier',-2.8,2055,1,1,3,2),(165,5.4,'2008-07-27 00:00:00','Hotter & Drier',-3.6,2070,1,1,3,2),(166,5.4,'2008-07-27 00:00:00','Hotter & Drier',-1.5,2030,2,1,3,2),(167,5.4,'2008-07-27 00:00:00','Hotter & Drier',-3.5,2055,2,1,3,2),(168,5.4,'2008-07-27 00:00:00','Hotter & Drier',-4.9,2070,2,1,3,2),(169,5.4,'2008-07-27 00:00:00','Most Likely',1.1,2030,1,4,3,2),(170,5.4,'2008-07-27 00:00:00','Most Likely',2.1,2055,1,4,3,2),(171,5.4,'2008-07-27 00:00:00','Most Likely',2.6,2070,1,4,3,2),(172,5.4,'2008-07-27 00:00:00','Most Likely',1.1,2030,2,4,3,2),(173,5.4,'2008-07-27 00:00:00','Most Likely',2.6,2055,2,4,3,2),(174,5.4,'2008-07-27 00:00:00','Most Likely',3.6,2070,2,4,3,2),(175,5.4,'2008-07-27 00:00:00','Cooler & Wetter',0.3,2030,1,5,3,2),(176,5.4,'2008-07-27 00:00:00','Cooler & Wetter',0.5,2055,1,5,3,2),(177,5.4,'2008-07-27 00:00:00','Cooler & Wetter',0.6,2070,1,5,3,2),(178,5.4,'2008-07-27 00:00:00','Cooler & Wetter',0.3,2030,2,5,3,2),(179,5.4,'2008-07-27 00:00:00','Cooler & Wetter',0.6,2055,2,5,3,2),(180,5.4,'2008-07-27 00:00:00','Cooler & Wetter',0.9,2070,2,5,3,2),(181,441.4,'2008-07-27 00:00:00','Hotter & Drier',-6.2,2030,1,1,3,3),(182,441.4,'2008-07-27 00:00:00','Hotter & Drier',-11.6,2055,1,1,3,3),(183,441.4,'2008-07-27 00:00:00','Hotter & Drier',-14.7,2070,1,1,3,3),(184,441.4,'2008-07-27 00:00:00','Hotter & Drier',-6,2030,2,1,3,3),(185,441.4,'2008-07-27 00:00:00','Hotter & Drier',-14.5,2055,2,1,3,3),(186,441.4,'2008-07-27 00:00:00','Hotter & Drier',-20.1,2070,2,1,3,3),(187,441.4,'2008-07-27 00:00:00','Most Likely',-6.7,2030,1,4,3,3),(188,441.4,'2008-07-27 00:00:00','Most Likely',-12.6,2055,1,4,3,3),(189,441.4,'2008-07-27 00:00:00','Most Likely',-16,2070,1,4,3,3),(190,441.4,'2008-07-27 00:00:00','Most Likely',-6.5,2030,2,4,3,3),(191,441.4,'2008-07-27 00:00:00','Most Likely',-15.7,2055,2,4,3,3),(192,441.4,'2008-07-27 00:00:00','Most Likely',-21.7,2070,2,4,3,3),(193,441.4,'2008-07-27 00:00:00','Cooler & Wetter',0.8,2030,1,5,3,3),(194,441.4,'2008-07-27 00:00:00','Cooler & Wetter',1.4,2055,1,5,3,3),(195,441.4,'2008-07-27 00:00:00','Cooler & Wetter',1.8,2070,1,5,3,3),(196,441.4,'2008-07-27 00:00:00','Cooler & Wetter',0.7,2030,2,5,3,3),(197,441.4,'2008-07-27 00:00:00','Cooler & Wetter',1.8,2055,2,5,3,3),(198,441.4,'2008-07-27 00:00:00','Cooler & Wetter',2.5,2070,2,5,3,3),(199,59.2,'2008-07-27 00:00:00','Hotter & Drier',-1.5,2030,1,1,3,4),(200,59.2,'2008-07-27 00:00:00','Hotter & Drier',-2.9,2055,1,1,3,4),(201,59.2,'2008-07-27 00:00:00','Hotter & Drier',-3.7,2070,1,1,3,4),(202,59.2,'2008-07-27 00:00:00','Hotter & Drier',-1.5,2030,2,1,3,4),(203,59.2,'2008-07-27 00:00:00','Hotter & Drier',-3.6,2055,2,1,3,4),(204,59.2,'2008-07-27 00:00:00','Hotter & Drier',-5,2070,2,1,3,4),(205,59.2,'2008-07-27 00:00:00','Most Likely',-0.9,2030,1,4,3,4),(206,59.2,'2008-07-27 00:00:00','Most Likely',-1.8,2055,1,4,3,4),(207,59.2,'2008-07-27 00:00:00','Most Likely',-2.3,2070,1,4,3,4),(208,59.2,'2008-07-27 00:00:00','Most Likely',-0.9,2030,2,4,3,4),(209,59.2,'2008-07-27 00:00:00','Most Likely',-2.2,2055,2,4,3,4),(210,59.2,'2008-07-27 00:00:00','Most Likely',-3.1,2070,2,4,3,4),(211,59.2,'2008-07-27 00:00:00','Cooler & Wetter',-0.2,2030,1,5,3,4),(212,59.2,'2008-07-27 00:00:00','Cooler & Wetter',-0.3,2055,1,5,3,4),(213,59.2,'2008-07-27 00:00:00','Cooler & Wetter',-0.4,2070,1,5,3,4),(214,59.2,'2008-07-27 00:00:00','Cooler & Wetter',-0.2,2030,2,5,3,4),(215,59.2,'2008-07-27 00:00:00','Cooler & Wetter',-0.4,2055,2,5,3,4),(216,59.2,'2008-07-27 00:00:00','Cooler & Wetter',-0.6,2070,2,5,3,4);
/*!40000 ALTER TABLE `csiro_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `csiro_variable`
--

DROP TABLE IF EXISTS `csiro_variable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `csiro_variable` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `short_name` varchar(255) DEFAULT NULL,
  `uom` varchar(255) DEFAULT NULL,
  `uom_variation` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `csiro_variable`
--

LOCK TABLES `csiro_variable` WRITE;
/*!40000 ALTER TABLE `csiro_variable` DISABLE KEYS */;
INSERT INTO `csiro_variable` VALUES (1,'Forecasted hange of temperature from now','Temperature','T','&#8451;','&#8451;'),(2,'Forecasted wind speed','Wind speed','WS','km/h','%'),(3,'Forecasted rain fall','Rainfall','RF','mm/y','%'),(4,'Forecasted relative humidity','Relative humidity','RH','%','%'),(5,'Forecasted sea level rise','Sea Level Rise','SLR','mm','%');
/*!40000 ALTER TABLE `csiro_variable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `data_element_option`
--

DROP TABLE IF EXISTS `data_element_option`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `data_element_option` (
  `data_element_id` int(11) NOT NULL,
  `data_source_parameter_option_id` int(11) NOT NULL,
  KEY `FKE760C84DF77D5B18` (`data_element_id`),
  KEY `FKE760C84DB61772EC` (`data_source_parameter_option_id`),
  CONSTRAINT `FKE760C84DB61772EC` FOREIGN KEY (`data_source_parameter_option_id`) REFERENCES `data_source_parameter_option` (`id`),
  CONSTRAINT `FKE760C84DF77D5B18` FOREIGN KEY (`data_element_id`) REFERENCES `element` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `data_element_option`
--

LOCK TABLES `data_element_option` WRITE;
/*!40000 ALTER TABLE `data_element_option` DISABLE KEYS */;
/*!40000 ALTER TABLE `data_element_option` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `data_source`
--

DROP TABLE IF EXISTS `data_source`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `data_source` (
  `type` varchar(31) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `data_source`
--

LOCK TABLES `data_source` WRITE;
/*!40000 ALTER TABLE `data_source` DISABLE KEYS */;
INSERT INTO `data_source` VALUES ('Csiro',1,'CSIRO'),('Cmar',2,'CMAR');
/*!40000 ALTER TABLE `data_source` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `data_source_display_types`
--

DROP TABLE IF EXISTS `data_source_display_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `data_source_display_types` (
  `data_source_id` int(11) NOT NULL,
  `display_type_id` int(11) NOT NULL,
  KEY `FK892DA30D18125712` (`display_type_id`),
  KEY `FK892DA30D1490C93C` (`data_source_id`),
  CONSTRAINT `FK892DA30D1490C93C` FOREIGN KEY (`data_source_id`) REFERENCES `data_source` (`id`),
  CONSTRAINT `FK892DA30D18125712` FOREIGN KEY (`display_type_id`) REFERENCES `display_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `data_source_display_types`
--

LOCK TABLES `data_source_display_types` WRITE;
/*!40000 ALTER TABLE `data_source_display_types` DISABLE KEYS */;
INSERT INTO `data_source_display_types` VALUES (1,1),(1,3),(1,5),(2,3),(2,4);
/*!40000 ALTER TABLE `data_source_display_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `data_source_element_category`
--

DROP TABLE IF EXISTS `data_source_element_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `data_source_element_category` (
  `data_source_id` int(11) NOT NULL,
  `element_category_id` int(11) NOT NULL,
  KEY `FK1EE3D4F01490C93C` (`data_source_id`),
  KEY `FK1EE3D4F0A4607006` (`element_category_id`),
  CONSTRAINT `FK1EE3D4F0A4607006` FOREIGN KEY (`element_category_id`) REFERENCES `element_category` (`id`),
  CONSTRAINT `FK1EE3D4F01490C93C` FOREIGN KEY (`data_source_id`) REFERENCES `data_source` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `data_source_element_category`
--

LOCK TABLES `data_source_element_category` WRITE;
/*!40000 ALTER TABLE `data_source_element_category` DISABLE KEYS */;
INSERT INTO `data_source_element_category` VALUES (1,3),(2,3);
/*!40000 ALTER TABLE `data_source_element_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `data_source_parameter`
--

DROP TABLE IF EXISTS `data_source_parameter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `data_source_parameter` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` text,
  `display` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `datasource_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKDAA36C7A8D3FB27` (`datasource_id`),
  CONSTRAINT `FKDAA36C7A8D3FB27` FOREIGN KEY (`datasource_id`) REFERENCES `data_source` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `data_source_parameter`
--

LOCK TABLES `data_source_parameter` WRITE;
/*!40000 ALTER TABLE `data_source_parameter` DISABLE KEYS */;
INSERT INTO `data_source_parameter` VALUES (1,'<h6>MEAN TEMPERATURE</h6><p>Mean air temperature in degrees Celsius (&#176;C) as measured at 2 m above ground. Values are given as change from modelled baseline (1981-2000) climate.</p><h6>RAINFALL</h6><p>Mean rainfall in millimetres (mm). Values are given as change from modelled baseline (1981-2000) climate.</p><h6>DAILY RELATIVE HUMIDITY</h6><p>Calculated at 2 m above ground and expressed in percent (%). Values are given as change from modelled baseline (1981-2000) climate.</p><h6>WIND SPEED</h6><p>Mean wind speed, in metres per second (m/sec) as measured at 10m above the ground. Values are given as change from modelled baseline (1981-2000) climate.</p>','DROPDOWN','Variable',1),(2,'<p>The emission scenarios represent the future development of greenhouse gas emissions and are based on assumptions about economic, technological and population growth. The two emissions scenarios that are available here are from the \'A1 storyline\' and were developed by the IPCC Special Report on Emissions Scenarios (SRES).</p><p>As a general guide:</p><p>Emission Scenario A1B: medium CO2 emissions, peaking around 2030</p><p>Emission Scenario A1FI: high CO2 emissions, increasing throughout the 21st century</p>','RADIO','Emission Scenario',1),(3,'<p>Time periods are expressed relative to the 1981-2000 baseline period and are centred on a given decade. For example, the 2030s time period refers to the period 2020-2039.</p> <p>Three future time periods are available: 2030, 2055 and 2070.</p>','DROPDOWN','Year',1),(4,'<p><h6>Sea Level Rise</h6>Sea level data was derived from the CSIRO Marine and Atmospheric Research (CMAR). Data provided by CMAR was taken from the average of 17 climate model simulations for the medium (A1B) emissions scenario. This average was plotted around the Australian coastline to allow the identification of particular locations. Figures from these locations were then added to the globally averaged sea level projections for 2030 and 2070, using only the medium emissions scenario. These global projections included estimates for ice-sheet melt and were taken from the 50th percentile range.</p>','DROPDOWN','Variable',2),(5,'<p>The emission scenarios represent the future development of greenhouse gas emissions and are based on assumptions about economic, technological and population growth. The two emissions scenarios that are available here are from the \'A1 storyline\' and were developed by the IPCC Special Report on Emissions Scenarios (SRES).</p><p>As a general guide:</p><p>Emission Scenario A1B: medium CO2 emissions, peaking around 2030</p><p>Emission Scenario A1FI: high CO2 emissions, increasing throughout the 21st century</p>','RADIO','Emission Scenario',2),(6,'<p>Time periods are expressed relative to the 1981-2000 baseline period and are centred on a given decade. For example, the 2030s time period refers to the period 2020-2039.</p> <p>Three future time periods are available: 2030, 2055 and 2070.</p>','DROPDOWN','Year',2);
/*!40000 ALTER TABLE `data_source_parameter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `data_source_parameter_option`
--

DROP TABLE IF EXISTS `data_source_parameter_option`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `data_source_parameter_option` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `position` int(11) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `data_source_parameter_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5D74FBDA25DF5EF7` (`data_source_parameter_id`),
  CONSTRAINT `FK5D74FBDA25DF5EF7` FOREIGN KEY (`data_source_parameter_id`) REFERENCES `data_source_parameter` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `data_source_parameter_option`
--

LOCK TABLES `data_source_parameter_option` WRITE;
/*!40000 ALTER TABLE `data_source_parameter_option` DISABLE KEYS */;
INSERT INTO `data_source_parameter_option` VALUES (1,'Temperature',1,'Temperature',1),(2,'Wind Speed',2,'Wind Speed',1),(3,'Rainfall',3,'Rainfall',1),(4,'Relative Humidity',4,'Relative Humidity',1),(5,'Medium (A1B)',1,'A1B',2),(6,'High (A1FI)',2,'A1FI',2),(7,'2030',1,'2030',3),(8,'2055',2,'2055',3),(9,'2070',3,'2070',3),(10,'Sea Level Rise',1,'Sea Level Rise',4),(11,'Medium (A1B)',1,'A1B',5),(12,'2030',1,'2030',6);
/*!40000 ALTER TABLE `data_source_parameter_option` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `data_source_seaports`
--

DROP TABLE IF EXISTS `data_source_seaports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `data_source_seaports` (
  `data_source_id` int(11) NOT NULL,
  `seaport_code` varchar(255) NOT NULL,
  KEY `FKABC97E328082F87F` (`seaport_code`),
  KEY `FKABC97E321490C93C` (`data_source_id`),
  CONSTRAINT `FKABC97E321490C93C` FOREIGN KEY (`data_source_id`) REFERENCES `data_source` (`id`),
  CONSTRAINT `FKABC97E328082F87F` FOREIGN KEY (`seaport_code`) REFERENCES `seaport` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `data_source_seaports`
--

LOCK TABLES `data_source_seaports` WRITE;
/*!40000 ALTER TABLE `data_source_seaports` DISABLE KEYS */;
INSERT INTO `data_source_seaports` VALUES (1,'FJLEV'),(1,'FJSUV'),(1,'FJLTK'),(1,'FJSVU'),(1,'FJLBS'),(2,'FJLEV'),(2,'FJSUV'),(2,'FJLTK'),(2,'FJSVU'),(2,'FJLBS');
/*!40000 ALTER TABLE `data_source_seaports` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `display_type`
--

DROP TABLE IF EXISTS `display_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `display_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `display_type`
--

LOCK TABLES `display_type` WRITE;
/*!40000 ALTER TABLE `display_type` DISABLE KEYS */;
INSERT INTO `display_type` VALUES (1,'Text'),(2,'Graph'),(3,'Table'),(4,'Map'),(5,'Picture');
/*!40000 ALTER TABLE `display_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `element`
--

DROP TABLE IF EXISTS `element`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `element` (
  `type` varchar(31) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `creation_date` datetime DEFAULT NULL,
  `included` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `position` int(11) DEFAULT NULL,
  `content` longblob,
  `content_type` varchar(255) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `report_id` int(11) DEFAULT NULL,
  `data_source_id` int(11) DEFAULT NULL,
  `display_type_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9CE31EFC18125712` (`display_type_id`),
  KEY `FK9CE31EFCED3BF327` (`report_id`),
  KEY `FK9CE31EFC45538CC9` (`category_id`),
  KEY `FK9CE31EFC1490C93C` (`data_source_id`),
  CONSTRAINT `FK9CE31EFC1490C93C` FOREIGN KEY (`data_source_id`) REFERENCES `data_source` (`id`),
  CONSTRAINT `FK9CE31EFC18125712` FOREIGN KEY (`display_type_id`) REFERENCES `display_type` (`id`),
  CONSTRAINT `FK9CE31EFC45538CC9` FOREIGN KEY (`category_id`) REFERENCES `element_category` (`id`),
  CONSTRAINT `FK9CE31EFCED3BF327` FOREIGN KEY (`report_id`) REFERENCES `report` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `element`
--

LOCK TABLES `element` WRITE;
/*!40000 ALTER TABLE `element` DISABLE KEYS */;
/*!40000 ALTER TABLE `element` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `element_category`
--

DROP TABLE IF EXISTS `element_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `element_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description_text` text,
  `help_text` text,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `element_category`
--

LOCK TABLES `element_category` WRITE;
/*!40000 ALTER TABLE `element_category` DISABLE KEYS */;
INSERT INTO `element_category` VALUES (1,'<h6>Non-Climate Context</h6><p class=\"justified\">Non-climate data helps set the operational context of ports. It also provides a starting point for consideration of possible impacts of non-climate factors into the future. For example, population growth along the coast can constrain a port\'s ability to expand in the future, and to retreat as sea level rise and climatic conditions change. National population growth can also be a driver of increased activity at container import ports, which may lead to congestion problems. <p class=\"justified\">This tab identifies particular non-climate-related information.  It includes trade and population data. Note that only limited data may be available for some ports. <p class=\"justified\">Two publicly available data sets are offered within this section. These are urban pressure data derived from the Australian Bureau of Statistics (ABS), and freight data from Ports Australia and individual ports\' published annual reports. <p class=\"justified\">Recognising that non-climate information will be gathered by ports themselves, this section allows for ports to upload port-specific files and information regarding organisational objectives, current risks, or data on throughput volume or the types of activity that characterise the port. <p class=\"justified\">Users can select from three main data sources on this tab:</p> <p class=\"justified\"><strong>\"ABS\"</strong> data, which provides Population change data;</p><p class=\"justified\"><strong>\"Ports Australia data\"</strong> which provides data on:</p><ul><li>Freight throughput by cargo type,</li><li>Commercial vessel calls by type, and</li> <li>Export commodities by type</li></ul><p class=\"justified\"><strong>\"Custom file\"</strong> data which can be text and/or images provided by the port, relating to their current, non-climate context.</p><a href=\"/public/guidelines#non-climate\" target=\"_blank\">Read more...</a>','<h6>Non-Climate Context</h6><p class=&quot;justified&quot;>This tab identifies particular non-climate-related information.  It includes trade and population data. Note that only limited data may be available for some ports. <p class=&quot;justified&quot;>Two publicly available data sets are offered within this section. These are urban pressure data derived from the Australian Bureau of Statistics (ABS), and freight data from Ports Australia and individual ports\' published annual reports.<p class=&quot;justified&quot;>Recognising that non-climate information will be gathered by ports themselves, this section allows for ports to upload port-specific files and information regarding organisational objectives, current risks, or data on throughput volume or the types of activity that characterise the port. <p class=&quot;justified&quot;>Users can select from three main data sources on this tab:</p><p class=&quot;justified&quot;><strong>&quot;ABS&quot;</strong> data, which provides Population change data;</p><p class=&quot;justified&quot;><strong>&quot;Ports Australia data&quot;</strong> which provides data on:</p><ul><li>Freight throughput by cargo type,</li><li>Commercial vessel calls by type, and</li><li>Export commodities by type</li></ul><p class=&quot;justified&quot;><strong>&quot;Custom file&quot;</strong> data which can be text and/or images provided by the port, relating to their current, non-climate context.</p><a href=&quot;/public/guidelines#non-climate&quot; target=&quot;_blank&quot;>Read more...</a>','Non-climate context'),(2,'<h6>Observed Climate</h6><p class=\"justified\">Climate change is already increasing the intensity and frequency of many extreme weather events. Extreme events occur naturally, however, climate change is influencing these events and record-breaking weather is becoming more common.</p> <p class=\"justified\">This tab sets the historical and current context of climate and marine observations for ports, to assist ports understand their current climate context. It includes data publicly available from the Bureau of Meteorology and the CSIRO.</p> Three types of data have been selected: <ul><li>national trends for temperature and rainfall;</li><li>global and national trends for sea-level rise; and</li><li>specific weather station data (including examples of extreme weather events).</li></ul><p>Users select from two main data sources in this tab:</p><strong>\"CSIRO and BoM trend data\"</strong> which provides national or global data on: <ul>	<li>Trend in mean temperatures,</li><li>Trend in maximum temperatures,</li><li>Trend in total annual rainfall,</li><li>Long-term sea level rise measurements and</li> <li>Shorter term changes in sea level.</li></ul><strong>\"ACORN-SAT\"</strong> which provides data from specific weather stations for:<ul><li>Mean measurements (annual mean surface temperature, annual mean rainfall, annual mean relative humidity and annual mean wind speed)</li><li>Extreme measurements (highest temperature, highest daily rainfall, maximum wind gust)</li></ul><br /><p>All data has been chosen to support the data in the Future Climate section. Please note, however, that the Future Climate is based on modelled data and is not directly comparable to the factual data sourced in this section.</p><br /><p class=\"justified\">For further information about extreme weather in Australia, head to the Bureau of Meteorology\'s \"<a href=\"http://www.bom.gov.au/climate/change/index.shtml#tabs=Climate-change-tracker&tracker=trend-maps\" target=\"_blank\">Climate Change Tracker</a>\" website.</p> <br /><a href=\"/public/guidelines#observed-climate\" target=\"_blank\">Read more...</a>','<h6>Observed Climate</h6><p class=&quot;justified&quot;>This tab sets the historical and current context of climate and marine observations for ports, to assist ports understand their current climate context. It includes data publicly available from the Bureau of Meteorology and the CSIRO.</p> Three types of data have been selected: <ul><li>national trends for temperature and rainfall;</li><li>global and national trends for sea-level rise; and</li><li>specific weather station data (including examples of extreme weather events).</li></ul><p>Users select from two main data sources in this tab:</p><strong>&quot;CSIRO and BoM trend data&quot;</strong> which provides national or global data on: <ul><li>Trend in mean temperatures,</li><li>Trend in maximum temperatures,</li>	<li>Trend in total annual rainfall,</li><li>Long-term sea level rise measurements and</li><li>Shorter term changes in sea level.</li></ul><strong>&quot;ACORN-SAT&quot;</strong> which provides data from specific weather stations for:<ul><li>Mean measurements (annual mean surface temperature, annual mean rainfall, annual mean relative humidity and annual mean wind speed)</li><li>Extreme measurements (highest temperature, highest daily rainfall, maximum wind gust)</li></ul><br /><p class=&quot;justified&quot;>For further information about extreme weather in Australia, head to the Bureau of Meteorology\'s &quot;<a href=&quot;http://www.bom.gov.au/climate/change/index.shtml#tabs=Climate-change-tracker&tracker=trend-maps&quot; target=&quot;_blank&quot;>Climate Change Tracker</a>&quot; website.</p><br /><a href=&quot;/public/guidelines#observed-climate&quot; target=&quot;_blank&quot;>Read more...</a>','Observed climate'),(3,'<h6>Future Climate</h6><p class=\"justified\">The future climate context faced by ports is an important factor in future planning and risk assessment processes. Direct impacts on ports, and indirect impacts on supply chains, will impact capital investment, maintenance requirements, as well as knowledge, skill and training requirements for personnel. Understanding potential future climate impacts allows ports to adequately assess their future planning options, and accommodate the most appropriate adaptation choices. <p class=\"justified\">This tab assists ports to identify some of the future climate projections data relevant for their region. <p class=\"justified\">Global climate models were selected using CSIRO\'s Climate Futures tool. Projections were classified using two climate variables: rainfall and temperature, and grouped into \"climate futures\", for example \"hotter &amp; drier\" or \"cooler and wetter\". Likelihoods were then assigned according to the number of climate models that fell within each category. In this application, three global climate models from the following categories are offered for users to choose from: \"hotter &amp; drier\", \"cooler &amp; wetter\" and \"most likely\"</p><p class=\"justified\">Users select from two data sources (CSIRO and CMAR), and then select from a choice of variables.</p><strong>\"CSIRO\"</strong> which displays future climate projection data for:<ul><li>Temperature, rainfall wind speed and relative humidity</li><li>Two emissions scenarios: A1B (medium) and A1FI (high)</li><li>Three climate models: most likely, hotter &amp; drier, and cooler &amp; wetter</li><li>Three time periods: 2030, 2055 and 2070</li></ul><p class=\"justified\"><strong>\"CMAR\"</strong> which only displays sea-level rise data for the \"medium emissions scenario (A1B)\", using a \"most likely\" climate model, for two time periods, \"2030\" and \"2070\".</p><br /><a href=\"/public/guidelines#future-climate\" target=\"_blank\">Read more...</a>','<h6>Future Climate</h6><p class=&quot;justified&quot;>This tab assists ports to identify some of the future climate projections data relevant for their region.   <p class=&quot;justified&quot;>Global climate models were selected using CSIRO\'s Climate Futures tool. Projections were classified using two climate variables: rainfall and temperature, and grouped into &quot;climate futures&quot;, for example &quot;hotter &amp; drier&quot; or &quot;cooler and wetter&quot;. Likelihoods were then assigned according to the number of climate models that fell within each category. In this application, three global climate models from the following categories are offered for users to choose from: &quot;hotter &amp; drier&quot;, &quot;cooler &amp; wetter&quot; and &quot;most likely&quot;</p><p class=&quot;justified&quot;>Users select from two data sources (CSIRO and CMAR), and then select from a choice of variables.</p><strong>&quot;CSIRO&quot;</strong> which displays future climate projection data for:<ul><li>Temperature, rainfall wind speed and relative humidity</li><li>Two emissions scenarios: A1B (medium) and A1FI (high)</li><li>Three climate models: most likely, hotter &amp; drier, and cooler &amp; wetter</li><li>Three time periods: 2030, 2055 and 2070</li></ul><p class=&quot;justified&quot;><strong>&quot;CMAR&quot;</strong> which only displays sea-level rise data for the &quot;medium emissions scenario (A1B)&quot;, using a &quot;most likely&quot; climate model, for two time periods, &quot;2030&quot; and &quot;2070&quot;.</p><br /><a href=&quot;/public/guidelines#future-climate&quot; target=&quot;_blank&quot;>Read more...</a>','Future climate'),(4,'<h6>Applications</h6><strong>Concrete Deterioration Model</strong><p class=\"justified\">Climate change will affect the rate of deterioration of materials such as concrete, timber and steel. The main construction material at ports is concrete and the rate of its deterioration will affect maintenance schedules, budgets and long term plans for refurbishment and replacement.</p><p class=\"justified\">This tab provides access to a tool designed by engineers that models rates of concrete deterioration under conditions of climate change in a port environment.</p>It provides:<ol><li>a set of example outputs for those who are not engineers, and</li><li>the tool for the engineers that have the knowledge to use it</li></ol>The tool enables the user to define:<ul><li>The concrete: e.g.: distance from coast, zone of activity (atmosphere, tidal, splash, submerged), size of structure,  depth of cover, diameter of rebar, water-to-cement ratio, cement content, depth of carbonation from maintenance records etc.;</li><li>The potential climate influences (following the range provided in the Future Climate tab), and;</li><li>A date range for all years 2013 - 2070</li></ul><p class=\"justified\">Data is not currently available for port areas in the South South West Flatlands West (SSWFW) Region.</p>				<strong>Vulnerability Assessment</strong><p class=\"justified\">When considering impacts of climate change, the term vulnerability represents exposure to a particular climate variable combined with the level of sensitivity to that variable, or the degree of impact.</p><p class=\"justified\">The Climate Smart Seaports Tool assists users to investigate vulnerability to current extreme climate-related events. How a port copes with, and responds to current extreme weather events, can be an indication of how well it will cope with future climate change.</p><p class=\"justified\">This tab allows users to identify the current vulnerability of a nominated port to particular climate related events. It presents a series of questions, exploring how recent climate-related events have disrupted operations at the port, and what this has meant for the port\'s business.</p> <p class=\"justified\">When considering the questions in this tab, think of the impact on port assets (machinery, buildings, equipment), infrastructure (drainage, rail, road, berths), and people (injuries, work disruptions).</p><a href=\"/public/guidelines#applications\" target=\"_blank\">Read more...</a>','<h6>Applications</h6><strong>Concrete Deterioration Model</strong><p class=&quot;justified&quot;>This tab connects users to an engineering tool that models rates of concrete deterioration under conditions of climate change in a port environment.</p> It provides:<ol><li>a set of example outputs for those who are not engineers, and</li><li>the tool for the engineers that have the knowledge to use it</li></ol>The tool enables the user to define: <ul><li>The concrete: e.g.: distance from coast, zone of activity (atmosphere, tidal, splash, submerged), size of structure,  depth of cover, diameter of rebar, water-to-cement ratio, cement content, depth of carbonation from maintenance records etc.;</li><li>The potential climate influences (following the range provided in the Future Climate tab), and;</li><li>A date range for all years 2013 - 2070</li></ul> <p class=&quot;justified&quot;>Data is not currently available for port areas in the South South West Flatlands West (SSWFW) Region.</p> <strong>Vulnerability Assessment</strong><p class=&quot;justified&quot;>This tab allows users to identify the current vulnerability of a nominated port to particular climate related events. It presents a series of questions, exploring how recent climate-related events have disrupted operations at the port, and what this has meant for the port\'s business.</p> <p class=&quot;justified&quot;>When considering the questions in this tab, think of the impact on port assets (machinery, buildings, equipment), infrastructure (drainage, rail, road, berths), and people (injuries, work disruptions).</p> <a href=&quot;/public/guidelines#applications&quot; target=&quot;_blank&quot;>Read more...</a>','Applications');
/*!40000 ALTER TABLE `element_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `region`
--

DROP TABLE IF EXISTS `region`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `region` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `coordinates` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `region`
--

LOCK TABLES `region` WRITE;
/*!40000 ALTER TABLE `region` DISABLE KEYS */;
INSERT INTO `region` VALUES (1,'176.284180,-15.891800 -178.046875,-15.891800 -178.046875,-20.070571 176.284180,-20.070571','Fiji'),(2,'','Papua New Guinea'),(3,'','Vaunuatu'),(4,'','Tonga'),(5,'','American Samoa'),(6,'','Wallis and Futuna'),(7,'','Solomon Islands'),(8,'','New Caledonia');
/*!40000 ALTER TABLE `region` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `report` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `access` varchar(255) DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `purpose` varchar(255) DEFAULT NULL,
  `owner_login` varchar(255) DEFAULT NULL,
  `seaport_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKC84C5534FC226F6D` (`seaport_id`),
  KEY `FKC84C5534E51AB3D5` (`owner_login`),
  CONSTRAINT `FKC84C5534E51AB3D5` FOREIGN KEY (`owner_login`) REFERENCES `user` (`username`),
  CONSTRAINT `FKC84C5534FC226F6D` FOREIGN KEY (`seaport_id`) REFERENCES `seaport` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
/*!40000 ALTER TABLE `report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report_publication`
--

DROP TABLE IF EXISTS `report_publication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `report_publication` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` longblob,
  `creation_date` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `owner_login` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK44BA27A1E51AB3D5` (`owner_login`),
  CONSTRAINT `FK44BA27A1E51AB3D5` FOREIGN KEY (`owner_login`) REFERENCES `user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report_publication`
--

LOCK TABLES `report_publication` WRITE;
/*!40000 ALTER TABLE `report_publication` DISABLE KEYS */;
/*!40000 ALTER TABLE `report_publication` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seaport`
--

DROP TABLE IF EXISTS `seaport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `seaport` (
  `code` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `urban_center` varchar(255) DEFAULT NULL,
  `region_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`code`),
  KEY `FK755195506B59B27` (`region_id`),
  CONSTRAINT `FK755195506B59B27` FOREIGN KEY (`region_id`) REFERENCES `region` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seaport`
--

LOCK TABLES `seaport` WRITE;
/*!40000 ALTER TABLE `seaport` DISABLE KEYS */;
INSERT INTO `seaport` VALUES ('FJLBS','Lambasa','Lambasa',1),('FJLEV','Levuka','Levuka',1),('FJLTK','Lautoka','Lautoka',1),('FJSUV','Suva','Suva',1),('FJSVU','Savu Savu','Savu Savu',1),('PGATP','Aitape','Aitape',2),('PGBAA','Bialla','Bialla',2),('PGDAU','Daru','Daru',2),('PGGUR','Alotau','Alotau',2),('PGKIE','Kieta','Kieta',2),('PGKIM','Kimbe','Kimbe',2),('PGKVG','Kavieng','Kavieng',2),('PGLAE','Lae','Lae',2),('PGLOR','Lorengau','Lorengau, Manus Island',2),('PGMAG','Madang','Madang',2),('PGPOM','Port Moresby','Port Moresby',2),('PGRAB','Rabaul','Rabaul',2),('PGROR','Oro Bay','Buna',2),('PGSQT','Samarai','Samarai',2),('PGVAI','Vanimo','Vanimo',2),('PGWWK','Wewak','Wewak',2);
/*!40000 ALTER TABLE `seaport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `username` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `nla_number` varchar(255) DEFAULT NULL,
  `non_locked` bit(1) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `roles` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('admin','email@company.com','','Admin','Admin',NULL,'','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','ROLE_ADMIN'),('user','email@company.com','','User','User',NULL,'','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','ROLE_USER');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-02-12  9:51:03
