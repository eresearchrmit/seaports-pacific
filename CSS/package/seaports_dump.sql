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
INSERT INTO `climate_emission_scenario` VALUES (1,'low','B1'),(2,'medium','A1B'),(3,'high','A2');
/*!40000 ALTER TABLE `climate_emission_scenario` ENABLE KEYS */;
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
  `display_name` varchar(255) DEFAULT NULL,
  `help_text` text,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `data_source`
--

LOCK TABLES `data_source` WRITE;
/*!40000 ALTER TABLE `data_source` DISABLE KEYS */;
INSERT INTO `data_source` VALUES ('ObservedTrend',1,'Observed Trend','','observedTrend'),('ObservedExtreme',2,'Observed Extreme','Extreme Temperature, rainfall, wind, sea level expressed as the number of years before an extreme variable returns to a place.','observedExtreme'),('ObservedTrend',3,'Projected Climate Change','','projectedClimateChange'),('ProjectedClimateExtreme',4,'Projected Climate Extreme','','projectedClimateExtreme'),('PastClimateImpactDescription',5,'Past Climate Impact Description','<p><i>This section identifies your current vulnerability to particular climate related events. When considering the questions below, think of the impact on all aspects of port\'s business: infrastructure (dredging, drainage, berths, storage, rail, road), port assets (machinery, buildings, equipment), people (injuries, work disruptions), legal (regulatory requirements, contract negotiations), financial (loss of income, increased costs), reputation.</i></p><p><i>Please complete this page for each different climate related event that has impacted the port in recent years.</i><p>Rate the effect that the impact will have on the port:<br />1: No impact or slight reduction in efficiency / no real cost<br />2: Interruption measured in hours, slight delays / small cost<br />3: Interruption measured in days / some costs<br />4: Operations halted for weeks / significant costs<br />5: Operations suspended indefinitely / major costs<br /></p>','pastClimateImpactDescription'),('CurrentClimateVulnerability',6,'Current Climate Vulnerability Assessment','<p><i>Click <a href=\"/resources/docs/matrix-current-climate-vulnerability.docx\" target=\"_blank\">here</a> to download a document to help you to prepare this assessment.</i></p><p><i>Select an event type and assess the vulnerability of the Seaport in the different domains listed below.</i></p><p>Rate the effect that the impact will have on the port:<br /> 1: No impact or slight reduction in efficiency / no real cost<br />2: Interruption measured in hours, slight delays / small cost<br />3: Interruption measured in days / some costs<br />4: Operations halted for weeks / significant costs<br />5: Operations suspended indefinitely / major costs</p>','currentClimateVulnerability'),('FutureClimateRisk',7,'Future Climate Risk Assessment','<p><i>Click <a href=\"/resources/docs/matrix-future-climate-risk.docx\" target=\"_blank\">here</a> to download a document to help you to prepare this assessment.</i></p>','futureClimateRisk'),('Trade',8,'Trade Data','<p>Here is some broad trade data for the whole nation. Choose import or export data. You can repeat the process to get both sets of data. Here is where your port will have the best data for you to upload using the &quot;Add File&quot; function</p>','trade'),('Demographics',9,'Demographics','','demographics');
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
INSERT INTO `data_source_display_types` VALUES (1,1),(1,5),(2,3),(3,3),(3,5),(4,3),(4,2),(5,2),(5,3),(6,3),(7,1),(7,3),(8,3),(8,2),(9,3),(9,2);
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
INSERT INTO `data_source_element_category` VALUES (1,1),(2,1),(3,2),(4,2),(5,1),(5,4),(6,1),(6,4),(7,2),(7,4),(8,3),(9,3);
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
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `data_source_parameter`
--

LOCK TABLES `data_source_parameter` WRITE;
/*!40000 ALTER TABLE `data_source_parameter` DISABLE KEYS */;
INSERT INTO `data_source_parameter` VALUES (1,'<h6>MIN/MAX TEMPERATURE</h6><p>Minimum/Maximum air temperature in degrees Celsius (&#176;C).</p><h6>RAINFALL</h6><p>Mean rainfall in millimetres (mm).</p></p><h6>SEA LEVEL</h6><p>Change in sea level (cm).</p><p>Values are given as change from modelled baseline (1981-2000) climate.</p>','DROPDOWN','Variable',1),(2,'<h6>?</h6>','DROPDOWN','Variable',2),(3,'<h6>TEMPERATURE/RAINFALL</h6><p>changes to annual mean surface air temperature (&#176;C) and rainfall (mm/day) relative to 1990 for 3 time periods (centred on 2030, 2055, 2090)  and 3 emissions scenarios (low, medium, high).</p>','DROPDOWN','Variable',3),(4,'<h6>Extreme Temperature, rainfall, wind, sea level expressed as the number of years before an extreme variable is projected to return to a place in future time periods (2025, 2050, 2075, 2100).</h6>','DROPDOWN','Variable',4),(5,'<p>Disruptive climate-related events are those that caused a significant alteration to the &quote;normal&quot; functioning of the port, whether this was for a few hours, a few weeks, or longer.</p>','DROPDOWN','Weather Event',5),(6,'<p>The year the disruptive event occured.</p>','DROPDOWN','Date',5),(7,'<p>Direct impacts are those that specifically impacted the port, for example, heavy rain on site. Indirect impacts are those that causing a flow-on impact to the port business, for example, shut down of electricity supply due to climate impacts, leaving the port without power. Select either Direct or Indirect. If the event caused both Direct and Indirect impacts, select the one that was MORE significant.</p>','RADIO','Direct or indirect impact',5),(8,'<p>Describe how the climate related event impacted your business. E.g.: Rain caused onsite flooding; the cyclone damaged roads bringing cargo to the port.</p>','TEXT','Impact',5),(9,'<p>Impacts on dredging schedules, alterations to channels, navigation aids, impacts to shipping.</p>','RADIO','Marine infrastructure',5),(10,'<p>Impact on seawalls, revetments, berths, piers.</p>','RADIO','Port infrastructure',5),(11,'Impact on paving, drainage systems, warehouses, silos, buildings.','RADIO','Port superstructure',5),(12,'Impact on road, rail, inland waterways.','RADIO','Supply chain',5),(13,'<p>Impact  to moving goods/bulk commodities to and from boats and across the port; impact on equipment.</p>','RADIO','Operations',5),(14,'<p>Loss of staff/contractor time to injury on site, or inability to get to work because of the climate event.</p>','RADIO','Workforce',5),(15,'<p>Loss of income, increase in costs.</p>','RADIO','Financial',5),(16,'<p>Cost of compliance, cost of non-compliance.</p>','RADIO','Legal/Regulatory',5),(17,'<p>Impact to the natural environment caused by port operations as a result of the climate related event. i.e.: excessive dust due to dry, windy conditions; contaminated water/chemical spill due to flooding.</p>','RADIO','Environment',5),(18,'<p>Impact on trade throughput due to a climate event at port or elsewhere; change in shipping schedule; change to trade goods.</p>','RADIO','Trade',5),(19,'<p>Impact on partners, leaseholders or community from climate events at the port.</p>','RADIO','Stakeholders',5),(20,'<p>Loss of good name; impact on stakeholders.</p>','RADIO','Reputation',5),(21,'<p>Specify in the text box provided any other business consequences not listed above.</p>','TEXT','Other business consequences',5),(22,'<p>What did the Port learn from the event? Did it ignore the event or make changes?</p>','RADIO','Would you say your response was adequate?',5),(23,'<p>Changes may be to management systems, to safety protocols, maintenance processes, communication protocols, insurance coverage and so forth.</p>','TEXT','What were the changes implemented as a result of this event?',5),(24,'Disruptive climate-related events are those that caused a significant alteration to the &quot;the normal&quot; functioning of the port, whether this was for a few hours,  a few weeks, or longer.','DROPDOWN','Event Type',6),(25,'<p>Dredged  channels, navigation aids, entrance to harbour.</p>','RADIO','Marine Infrastructure',6),(26,'<p>Seawalls, revetments, berths, piers.</p>','RADIO','Port Infrastructure',6),(27,'<p>Paving, drainage systems, warehouses, silos, buildings.</p>','RADIO','Port Superstructure',6),(28,'<p>Moving goods/bulk commodities to and from boats and across the port; equipment.</p>','RADIO','Operations',6),(29,'<p>Connecting road, rail, inland waterways.</p>','RADIO','Supply Chain',6),(30,'<p>Staff/contractors  working in all areas of the port;  travelling to and from work.</p>','RADIO','Workforce',6),(31,'<p>Loss of income, increase in costs.</p>','RADIO','Financial',6),(32,'<p>Cost of compliance, cost of non-compliance.</p>','RADIO','Legal/Regulations',6),(33,'<p>Natural environment impacted by port operations as a result of the climate related event. i.e.: excessive dust due to dry, windy conditions; contaminated water/chemical spill due to flooding.</p>','RADIO','Environment',6),(34,'<p>Partners, leaseholders or community.</p>','RADIO','Stakeholders',6),(35,'<p>Good name or loss of good name of the business.</p>','RADIO','Reputation',6),(36,'<p>Disruptive climate-related events are those that caused a significant alteration to the &quot;the normal&quot; functioning of the port, whether this was for a few hours,  a few weeks, or longer</p>','DROPDOWN','Event Type',7),(37,'<p>Area of port business that might be impacted.</p>','DROPDOWN','Risk Area',7),(38,'<p>Describe the type of climate risk, for example, storms lead to sediment moving and channels need clearing for navigation</p>','TEXT','Description',7),(39,'<p>Include details of the climate risk and identify thresholds if possible, for example, if storms are more frequent and shipping channels are affected; more dredging will be needed. Dredging is currently carried out annually.</p>','TEXT','Details of Risk (threshold)',7),(40,'<p>Detail the consequences of the future risk, for example, if more dredging will be needed; dredging is currently carried out annually but it might have to be scheduled more frequently.  Consequences are:  the dredger will be hired more frequently and shipping lanes will be closed more often.</p>','TEXT','Future Consequences',7),(41,'<p>Rate the effect that the impact will have on the port. This rating will be combined with your rating of how likely the climate event is to occur, to give you a Risk Level in the Climate Risk Assessment Table.</p><p>1: No impact or slight reduction in efficiency / no real cost<br />2: Interruption measured in hours, slight delays / small cost<br />3: Interruption measured in days / some costs<br />4: Operations halted for weeks / significant costs<br />5: Operations suspended indefinitely / major costs</p>','RADIO','Consequence Rating',7),(42,'<p>Rate how often the climate event might occur. This rating will be combined with your rating of the effect if would have on the port (Consequence), to give you a Risk Level in the Climate Risk Assessment Table.</p><p>1: Recurrent events  / single event – almost impossible<br/>2: Recurrent events / single event – unlikely<br/>3: Recurrent events– once in your career (50 years) / single event  - possible<br/>4: Recurrent events - several times in your  career (10 or 20 years) / single event - probable<br/>5: Recurrent events –expect this every year/ single event – very likely to happen</p>','RADIO','Likelihood',7),(43,'<h6>Import/Export</h6><p>Choose \'import\' to get the top 10 imports by monetary value over a decade.</p><p>Choose \'export\' to get the top 10 exports by monetary value over a decade.</p>','RADIO','Import/Export',8),(44,'<h6>Total Population</h6><p>Total population is based on the de facto definition of population, which counts all residents regardless of legal status or citizenship--except for refugees not permanently settled in the country of asylum, who are generally considered part of the population of their country of origin. The values shown are midyear estimates.</p>','DROPDOWN','Variable',9);
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
) ENGINE=InnoDB AUTO_INCREMENT=228 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `data_source_parameter_option`
--

LOCK TABLES `data_source_parameter_option` WRITE;
/*!40000 ALTER TABLE `data_source_parameter_option` DISABLE KEYS */;
INSERT INTO `data_source_parameter_option` VALUES (1,'Minimum Temperatures',1,'Minimum Temperatures',1),(2,'Maximum Temperatures',2,'Maximum Temperatures',1),(3,'Rainfall',3,'Rainfall',1),(4,'Sea Level',4,'Sea Level',1),(5,'Extreme maximum temperature',1,'Extreme maximum temperature',2),(6,'Extreme rainfall (> 200mm)',2,'Extreme rainfall',2),(7,'Extreme wind (> 80kt, > 148km/h)',3,'Extreme wind',2),(8,'Extreme sea level (> 2.4/2.8m)',4,'Extreme sea level',2),(9,'Temperature',1,'Surface Air Temperature',3),(10,'Rainfall',2,'Total Rainfall',3),(11,'Extreme maximum temperature',1,'Extreme maximum temperature',4),(12,'Extreme rainfall (> 200mm)',2,'Extreme rainfall',4),(13,'Extreme wind (> 80kt, > 148km/h)',3,'Extreme wind',4),(14,'Extreme sea level (> 2.4/2.8m)',4,'Extreme sea level',4),(15,'Heatwave',1,'Heatwave',5),(16,'Strong wind',2,'Strong wind',5),(17,'Heavy rain',3,'Heavy rain',5),(18,'Electrical storm',4,'Electrical storm',5),(19,'Storm (wind and rain combined)',5,'Storm (wind and rain combined)',5),(20,'Cyclone',6,'Cyclone',5),(21,'Hail',7,'Hail',5),(22,'Storm surge',8,'Storm surge',5),(23,'Sea level rise',9,'Sea level rise',5),(24,'Fog',10,'Fog',5),(25,'Drought',11,'Drought',5),(26,'Flood',12,'Flood',5),(27,'Other',13,'Other',5),(28,'1983',1,'1983',6),(29,'1984',2,'1984',6),(30,'1985',3,'1985',6),(31,'1986',4,'1986',6),(32,'1987',5,'1987',6),(33,'1988',6,'1988',6),(34,'1989',7,'1989',6),(35,'1990',8,'1990',6),(36,'1991',9,'1991',6),(37,'1992',10,'1992',6),(38,'1993',11,'1993',6),(39,'1994',12,'1994',6),(40,'1995',13,'1995',6),(41,'1996',14,'1996',6),(42,'1997',15,'1997',6),(43,'1998',16,'1998',6),(44,'1999',17,'1999',6),(45,'2000',18,'2000',6),(46,'2001',19,'2001',6),(47,'2002',20,'2002',6),(48,'2003',21,'2003',6),(49,'2004',22,'2004',6),(50,'2005',23,'2005',6),(51,'2006',24,'2006',6),(52,'2007',25,'2007',6),(53,'2008',26,'2008',6),(54,'2009',27,'2009',6),(55,'2010',28,'2010',6),(56,'2011',29,'2011',6),(57,'2012',30,'2012',6),(58,'2013',31,'2013',6),(59,'Direct',1,'1',7),(60,'Indirect',2,'0',7),(61,'',1,'',8),(62,'No Impact',1,'0',9),(63,'Insignificant',2,'1',9),(64,'Moderate',3,'2',9),(65,'Major',4,'3',9),(66,'Extreme',5,'4',9),(67,'No Impact',1,'0',10),(68,'Insignificant',2,'1',10),(69,'Moderate',3,'2',10),(70,'Major',4,'3',10),(71,'Extreme',5,'4',10),(72,'No Impact',1,'0',11),(73,'Insignificant',2,'1',11),(74,'Moderate',3,'2',11),(75,'Major',4,'3',11),(76,'Extreme',5,'4',11),(77,'No Impact',1,'0',12),(78,'Insignificant',2,'1',12),(79,'Moderate',3,'2',12),(80,'Major',4,'3',12),(81,'Extreme',5,'4',12),(82,'No Impact',1,'0',13),(83,'Insignificant',2,'1',13),(84,'Moderate',3,'2',13),(85,'Major',4,'3',13),(86,'Extreme',5,'4',13),(87,'No Impact',1,'0',14),(88,'Insignificant',2,'1',14),(89,'Moderate',3,'2',14),(90,'Major',4,'3',14),(91,'Extreme',5,'4',14),(92,'No Impact',1,'0',15),(93,'Insignificant',2,'1',15),(94,'Moderate',3,'2',15),(95,'Major',4,'3',15),(96,'Extreme',5,'4',15),(97,'No Impact',1,'0',16),(98,'Insignificant',2,'1',16),(99,'Moderate',3,'2',16),(100,'Major',4,'3',16),(101,'Extreme',5,'4',16),(102,'No Impact',1,'0',17),(103,'Insignificant',2,'1',17),(104,'Moderate',3,'2',17),(105,'Major',4,'3',17),(106,'Extreme',5,'4',17),(107,'No Impact',1,'0',18),(108,'Insignificant',2,'1',18),(109,'Moderate',3,'2',18),(110,'Major',4,'3',18),(111,'Extreme',5,'4',18),(112,'No Impact',1,'0',19),(113,'Insignificant',2,'1',19),(114,'Moderate',3,'2',19),(115,'Major',4,'3',19),(116,'Extreme',5,'4',19),(117,'No Impact',1,'0',20),(118,'Insignificant',2,'1',20),(119,'Moderate',3,'2',20),(120,'Major',4,'3',20),(121,'Extreme',5,'4',20),(122,'',1,'',21),(123,'Yes',1,'1',22),(124,'No',2,'0',22),(125,'',1,'',23),(126,'Sea current',1,'Sea current',24),(127,'Wave climate',2,'Wave climate',24),(128,'Sea Surface Temperature',3,'Sea Surface Temperature',24),(129,'Sea acidity',4,'Sea acidity',24),(130,'Storm surge',5,'Storm surge',24),(131,'Cyclone',6,'Cyclone',24),(132,'Intense rainfall',7,'Intense rainfall',24),(133,'Wind speed/direction',8,'Wind speed/direction',24),(134,'Heat wave',9,'Heat wave',24),(135,'Drought',10,'Drought',24),(136,'Not vulnerable',1,'1',25),(137,'Could be vulnerable',2,'2',25),(138,'Somewhat vulnerable',3,'3',25),(139,'Moderately vulnerable',4,'4',25),(140,'Significantly vulnerable',5,'5',25),(141,'Not vulnerable',1,'1',26),(142,'Could be vulnerable',2,'2',26),(143,'Somewhat vulnerable',3,'3',26),(144,'Moderately vulnerable',4,'4',26),(145,'Significantly vulnerable',5,'5',26),(146,'Not vulnerable',1,'1',27),(147,'Could be vulnerable',2,'2',27),(148,'Somewhat vulnerable',3,'3',27),(149,'Moderately vulnerable',4,'4',27),(150,'Significantly vulnerable',5,'5',27),(151,'Not vulnerable',1,'1',28),(152,'Could be vulnerable',2,'2',28),(153,'Somewhat vulnerable',3,'3',28),(154,'Moderately vulnerable',4,'4',28),(155,'Significantly vulnerable',5,'5',28),(156,'Not vulnerable',1,'1',29),(157,'Could be vulnerable',2,'2',29),(158,'Somewhat vulnerable',3,'3',29),(159,'Moderately vulnerable',4,'4',29),(160,'Significantly vulnerable',5,'5',29),(161,'Not vulnerable',1,'1',30),(162,'Could be vulnerable',2,'2',30),(163,'Somewhat vulnerable',3,'3',30),(164,'Moderately vulnerable',4,'4',30),(165,'Significantly vulnerable',5,'5',30),(166,'Not vulnerable',1,'1',31),(167,'Could be vulnerable',2,'2',31),(168,'Somewhat vulnerable',3,'3',31),(169,'Moderately vulnerable',4,'4',31),(170,'Significantly vulnerable',5,'5',31),(171,'Not vulnerable',1,'1',32),(172,'Could be vulnerable',2,'2',32),(173,'Somewhat vulnerable',3,'3',32),(174,'Moderately vulnerable',4,'4',32),(175,'Significantly vulnerable',5,'5',32),(176,'Not vulnerable',1,'1',33),(177,'Could be vulnerable',2,'2',33),(178,'Somewhat vulnerable',3,'3',33),(179,'Moderately vulnerable',4,'4',33),(180,'Significantly vulnerable',5,'5',33),(181,'Not vulnerable',1,'1',34),(182,'Could be vulnerable',2,'2',34),(183,'Somewhat vulnerable',3,'3',34),(184,'Moderately vulnerable',4,'4',34),(185,'Significantly vulnerable',5,'5',34),(186,'Not vulnerable',1,'1',35),(187,'Could be vulnerable',2,'2',35),(188,'Somewhat vulnerable',3,'3',35),(189,'Moderately vulnerable',4,'4',35),(190,'Significantly vulnerable',5,'5',35),(191,'Sea current',1,'Sea current',36),(192,'Wave climate',2,'Wave climate',36),(193,'Sea Surface Temperature',3,'Sea Surface Temperature',36),(194,'Sea acidity',4,'Sea acidity',36),(195,'Storm surge',5,'Storm surge',36),(196,'Cyclone',6,'Cyclone',36),(197,'Intense rainfall',7,'Intense rainfall',36),(198,'Wind speed/direction',8,'Wind speed/direction',36),(199,'Heat wave',9,'Heat wave',36),(200,'Drought',10,'Drought',36),(201,'Marine Infrastructure',1,'Marine Infrastructure',37),(202,'Port Infrastructure',1,'Port Infrastructure',37),(203,'Port Superstructure',1,'Port Superstructure',37),(204,'Supply Chain',1,'Supply Chain',37),(205,'Operations',1,'Operations',37),(206,'Workforce',1,'Workforce',37),(207,'Financial',1,'Financial',37),(208,'Legal/Regulations',1,'Legal/Regulations',37),(209,'Environment',1,'Environment',37),(210,'Stakeholders',1,'Stakeholders',37),(211,'Reputation',1,'Reputation',37),(212,'',1,'',38),(213,'',1,'',39),(214,'',1,'',40),(215,'None or Negligible',1,'0',41),(216,'Minor',2,'1',41),(217,'Medium',3,'2',41),(218,'Major',4,'3',41),(219,'Extreme',5,'4',41),(220,'Rare',1,'0',42),(221,'Unlikely',2,'1',42),(222,'Possible',3,'2',42),(223,'Probable',4,'3',42),(224,'Almost Certain',5,'4',42),(225,'Import',1,'Import',43),(226,'Export',2,'Export',43),(227,'Total Population',1,'Total Population',44);
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
INSERT INTO `data_source_seaports` VALUES (1,'FJSUV'),(1,'FJLTK'),(1,'FJMAL'),(1,'FJLEV'),(1,'FJWAI'),(1,'PGGUR'),(1,'PGATP'),(1,'PGBUA'),(1,'PGDAU'),(1,'PGKVG'),(1,'PGKIE'),(1,'PGKIM'),(1,'PGLAE'),(1,'PGLOR'),(1,'PGMAG'),(1,'PGROR'),(1,'PGPOM'),(1,'PGRAB'),(1,'PGVAI'),(1,'PGWWK'),(2,'FJSUV'),(2,'FJLTK'),(2,'FJMAL'),(2,'FJLEV'),(2,'FJWAI'),(3,'FJSUV'),(3,'FJLTK'),(3,'FJMAL'),(3,'FJLEV'),(3,'FJWAI'),(3,'PGGUR'),(3,'PGATP'),(3,'PGBUA'),(3,'PGDAU'),(3,'PGKVG'),(3,'PGKIE'),(3,'PGKIM'),(3,'PGLAE'),(3,'PGLOR'),(3,'PGMAG'),(3,'PGROR'),(3,'PGPOM'),(3,'PGRAB'),(3,'PGVAI'),(3,'PGWWK'),(4,'FJSUV'),(4,'FJLTK'),(4,'FJMAL'),(4,'FJLEV'),(4,'FJWAI'),(5,'FJSUV'),(5,'FJLTK'),(5,'FJMAL'),(5,'FJLEV'),(5,'FJWAI'),(5,'PGGUR'),(5,'PGATP'),(5,'PGBUA'),(5,'PGDAU'),(5,'PGKVG'),(5,'PGKIE'),(5,'PGKIM'),(5,'PGLAE'),(5,'PGLOR'),(5,'PGMAG'),(5,'PGROR'),(5,'PGPOM'),(5,'PGRAB'),(5,'PGVAI'),(5,'PGWWK'),(6,'FJSUV'),(6,'FJLTK'),(6,'FJMAL'),(6,'FJLEV'),(6,'FJWAI'),(6,'PGGUR'),(6,'PGATP'),(6,'PGBUA'),(6,'PGDAU'),(6,'PGKVG'),(6,'PGKIE'),(6,'PGKIM'),(6,'PGLAE'),(6,'PGLOR'),(6,'PGMAG'),(6,'PGROR'),(6,'PGPOM'),(6,'PGRAB'),(6,'PGVAI'),(6,'PGWWK'),(7,'FJSUV'),(7,'FJLTK'),(7,'FJMAL'),(7,'FJLEV'),(7,'FJWAI'),(7,'PGGUR'),(7,'PGATP'),(7,'PGBUA'),(7,'PGDAU'),(7,'PGKVG'),(7,'PGKIE'),(7,'PGKIM'),(7,'PGLAE'),(7,'PGLOR'),(7,'PGMAG'),(7,'PGROR'),(7,'PGPOM'),(7,'PGRAB'),(7,'PGVAI'),(7,'PGWWK'),(8,'FJSUV'),(8,'FJLTK'),(8,'FJMAL'),(8,'FJLEV'),(8,'FJWAI'),(9,'FJSUV'),(9,'FJLTK'),(9,'FJMAL'),(9,'FJLEV'),(9,'FJWAI'),(9,'PGGUR'),(9,'PGATP'),(9,'PGBUA'),(9,'PGDAU'),(9,'PGKVG'),(9,'PGKIE'),(9,'PGKIM'),(9,'PGLAE'),(9,'PGLOR'),(9,'PGMAG'),(9,'PGROR'),(9,'PGPOM'),(9,'PGRAB'),(9,'PGVAI'),(9,'PGWWK');
/*!40000 ALTER TABLE `data_source_seaports` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `demographics_data`
--

DROP TABLE IF EXISTS `demographics_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `demographics_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `creation_date` datetime DEFAULT NULL,
  `source_name` varchar(255) DEFAULT NULL,
  `value` double DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  `region_id` int(11) DEFAULT NULL,
  `variable_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK76F2C7DB6B59B27` (`region_id`),
  KEY `FK76F2C7DB4FF564BF` (`variable_id`),
  CONSTRAINT `FK76F2C7DB4FF564BF` FOREIGN KEY (`variable_id`) REFERENCES `demographics_variable` (`id`),
  CONSTRAINT `FK76F2C7DB6B59B27` FOREIGN KEY (`region_id`) REFERENCES `region` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `demographics_data`
--

LOCK TABLES `demographics_data` WRITE;
/*!40000 ALTER TABLE `demographics_data` DISABLE KEYS */;
INSERT INTO `demographics_data` VALUES (1,'2012-01-01 00:00:00','World Development Indicators of The World Bank (based on United Nations World Population Prospects)',393383,1960,1,1),(2,'2012-01-01 00:00:00','World Development Indicators of The World Bank (based on United Nations World Population Prospects)',520529,1970,1,1),(3,'2012-01-01 00:00:00','World Development Indicators of The World Bank (based on United Nations World Population Prospects)',635256,1980,1,1),(4,'2012-01-01 00:00:00','World Development Indicators of The World Bank (based on United Nations World Population Prospects)',728339,1990,1,1),(5,'2012-01-01 00:00:00','World Development Indicators of The World Bank (based on United Nations World Population Prospects)',811647,2000,1,1),(6,'2012-01-01 00:00:00','World Development Indicators of The World Bank (based on United Nations World Population Prospects)',860559,2010,1,1),(7,'2012-01-01 00:00:00','World Development Indicators of The World Bank (based on United Nations World Population Prospects)',1966957,1960,2,1),(8,'2012-01-01 00:00:00','World Development Indicators of The World Bank (based on United Nations World Population Prospects)',2434754,1970,2,1),(9,'2012-01-01 00:00:00','World Development Indicators of The World Bank (based on United Nations World Population Prospects)',3215483,1980,2,1),(10,'2012-01-01 00:00:00','World Development Indicators of The World Bank (based on United Nations World Population Prospects)',4157903,1990,2,1),(11,'2012-01-01 00:00:00','World Development Indicators of The World Bank (based on United Nations World Population Prospects)',5379226,2000,2,1),(12,'2012-01-01 00:00:00','World Development Indicators of The World Bank (based on United Nations World Population Prospects)',6858945,2010,2,1);
/*!40000 ALTER TABLE `demographics_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `demographics_variable`
--

DROP TABLE IF EXISTS `demographics_variable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `demographics_variable` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` text,
  `name` varchar(255) DEFAULT NULL,
  `short_name` varchar(255) DEFAULT NULL,
  `uom` varchar(255) DEFAULT NULL,
  `uom_variation` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `demographics_variable`
--

LOCK TABLES `demographics_variable` WRITE;
/*!40000 ALTER TABLE `demographics_variable` DISABLE KEYS */;
INSERT INTO `demographics_variable` VALUES (1,'Total population is based on the de facto definition of population, which counts all residents regardless of legal status or citizenship--except for refugees not permanently settled in the country of asylum, who are generally considered part of the population of their country of origin. The values shown are midyear estimates.','Total Population','Pop','people','');
/*!40000 ALTER TABLE `demographics_variable` ENABLE KEYS */;
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
  `full_width` bit(1) DEFAULT NULL,
  `included` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `page_break_after` bit(1) DEFAULT NULL,
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
INSERT INTO `element_category` VALUES (1,'<p class=\"justified\">Climate change is already increasing the intensity and frequency of many extreme weather events. These occur naturally, however, climate change is influencing their intensity and their return periods; and record-breaking weather is becoming more common. </p><p class=\"justified\">This tab sets the historical and current context of climate and marine observations to assist ports to understand their current climate context. It includes:<ul><li>limate and marine data is publicly available from the CSIRO the Australian Bureau of Meteorology and the Fiji Meteorological service.</li><li>Applications to help ports identify their current and historical vulnerability to climate impacts are included here.</li></ul></p><p class=\"justified\">You can:<ul><li>ADD DATA - add climate and marine data to your report, investigate</li><li>APPLICATIONS to help you understand port vulnerability to the current climate</li><li>ADD TEXT - add your own text boxes and analysis <li>ADD FILE - add your own information/pictures</li></ul></p><h6>CLIMATE AND MARINE DATA</h6><p class=\"justified\">Three types of data have been selected:<ul><li>National trends for temperature and rainfall; </li><li>Regional trends for sea-level rise; and</li><li>Return periods (expressed in years) for extreme weather at specific locations (where available). </li></ul></p><p class=\"justified\">Users select from two main climate data sources in this tab: <ul><li>Observed trend</li><li>Observed extreme</li></ul></p><p class=\"justified\">All data has been chosen to support the data in the Future Climate section. Please note, however, that the Future Climate is based on modelled data and is not directly comparable to the factual data sourced in this section.</p?<p class=\"justified\">For further information about climate and marine conditions in the Pacific head to the Bureau of Meteorology for:<br /><a href=\"http://www.bom.gov.au/pacific/projects/pslm/index.shtml\" target=\"_blank\">Pacific Sea Level Monitoring Project</a><br /><a href=\"http://www.bom.gov.au/climate/pacific/projects.shtml\" target=\"_blank\">Pacific Seasonal Prediction and database of historical climate information and trends </a><br /><a href=\"http://www.bom.gov.au/cosppac/comp/\" target=\"_blank\">Climate and Ocean Monitoring and Prediction (COSPAC)</a></p><h6>APPLICATIONS</h6><p>Users select from two Applications:<ul><li>Port vulnerability response to specific extreme weather events in the past. This provides a visual check of how past climate events affected the port and space for the port to enter comments about how it managed this event. You can use this aid for as many climate events as you want.</li><li>Current climate risk assessment of port vulnerability to extreme weather events. This provides a downloadable matrix with a priority rating. You can use this to create a list of current port vulnerability and upload the most important risks to the online table.</li></ul></p>','<p class=&quot;justified&quot;>Climate change is already increasing the intensity and frequency of many extreme weather events. These occur naturally, however, climate change is influencing their intensity and their return periods; and record-breaking weather is becoming more common. </p><p class=&quot;justified&quot;>This tab sets the historical and current context of climate and marine observations to assist ports to understand their current climate context. It includes:<ul><li>limate and marine data is publicly available from the CSIRO the Australian Bureau of Meteorology and the Fiji Meteorological service.</li><li>Applications to help ports identify their current and historical vulnerability to climate impacts are included here.</li></ul></p><p class=&quot;justified&quot;>You can:<ul><li>ADD DATA - add climate and marine data to your report, investigate</li><li>APPLICATIONS to help you understand port vulnerability to the current climate</li><li>ADD TEXT - add your own text boxes and analysis <li>ADD FILE - add your own information/pictures</li></ul></p><h6>CLIMATE AND MARINE DATA</h6><p class=&quot;justified&quot;>Three types of data have been selected:<ul><li>National trends for temperature and rainfall; </li><li>Regional trends for sea-level rise; and</li><li>Return periods (expressed in years) for extreme weather at specific locations (where available). </li></ul></p><p class=&quot;justified&quot;>Users select from two main climate data sources in this tab: <ul><li>Observed trend</li><li>Observed extreme</li></ul></p><p class=&quot;justified&quot;>All data has been chosen to support the data in the Future Climate section. Please note, however, that the Future Climate is based on modelled data and is not directly comparable to the factual data sourced in this section.</p><p class=&quot;justified&quot;>For further information about climate and marine conditions in the Pacific head to the Bureau of Meteorology for:<br /><a href=&quot;http://www.bom.gov.au/pacific/projects/pslm/index.shtml&quot; target=&quot;_blank&quot;>Pacific Sea Level Monitoring Project</a><br /><a href=&quot;http://www.bom.gov.au/climate/pacific/projects.shtml&quot; target=&quot;_blank&quot;>Pacific Seasonal Prediction and database of historical climate information and trends</a><br /><a href=&quot;http://www.bom.gov.au/cosppac/comp/&quot; target=&quot;_blank&quot;>Climate and Ocean Monitoring and Prediction (COSPAC)</a></p><h6>APPLICATIONS</h6><p>Users select from two Applications:<ul><li>Port vulnerability response to specific extreme weather events in the past. This provides a visual check of how past climate events affected the port and space for the port to enter comments about how it managed this event. You can use this aid for as many climate events as you want.</li><li>Current climate risk assessment of port vulnerability to extreme weather events. This provides a downloadable matrix with a priority rating. You can use this to create a list of current port vulnerability and upload the most important risks to the online table.</li></ul></p>','Observed climate &amp; marine'),(2,'<p class=\"justified\">The future climate context faced by ports is an important factor in future planning and risk assessment processes. Direct impacts on ports, and indirect impacts on supply chains, will impact capital investment, maintenance requirements as well as knowledge, skill and training requirements for personnel. Understanding potential future climate impacts allows ports to adequately assess their future planning options, and accommodate the most appropriate adaptation choices.</p><p class=\"justified\">This tab assists ports to identify some of the future climate projections data relevant for their region. </p><p class=\"justified\">It includes:<ul><li>Projected climate and marine data publicly available from the CSIRO the Australian Bureau of Meteorology Pacific Climate Change Science Program and the Fiji Meteorological Service.</li><li>Applications to help ports identify their future risk to changing climate impacts</li></ul></p><p class=\"justified\">You can:<ul><li>ADD DATA - add future climate and marine data and investigate APPLICATIONS to help you understand port risks from climate change</li><li>ADD TEXT - add your own text boxes</li><li>ADD FILE - add your own information/pictures</li></ul></p><h6>CLIMATE AND MARINE DATA</h6><p class=\"justified\">Three types of data have been selected:<ul><li>Projected trends for temperature and rainfall from a base of 1990, using 3 emission scenarios (low, medium and high emissions) and centring on 3 time periods (2030, 2055, 2090).</li><li>Projected global trends for sea-level rise; and</li><li>Projected return periods (expressed in years) for extreme weather at specific locations (where available).</li></ul></p><p class=\"justified\">Users select from two main climate and marine data sources in this tab:<ul><li>Projected climate change</li><li>Projected extreme</li></ul></p><p class=\"justified\">For further information about projected future climate and marine conditions in the Pacific head to the <a href=\"http://www.pacificclimatechangescience.org/\" target=\"_blank\" title=\"PCCSP\">Pacific Climate Change Science Project</a>  (PCCSP).<br />This project produced a series of reports that can be found here: <a href=\"http://www.pacificclimatechangescience.org/publications/reports/\" target=\"_blank\"> http://www.pacificclimatechangescience.org/publications/reports/</a><h6>AIDS TO IDENTIFYING FUTURE PORT VULNERABILITY TO CLIMATE IMPACTS</h6><p class=\"justified\">Users select:<br />Future climate risk assessment of port vulnerability to a changing climate. This provides a downloadable matrix with a priority rating that is derived from likelihood and consequence tables.</p><p class=\"justified\">Building on the knowledge gained using the current vulnerability aid, you can use this aid to create a list of possible future port climate risks and upload the most important risks to the online table.</p>','<p class=&quot;justified&quot;>The future climate context faced by ports is an important factor in future planning and risk assessment processes. Direct impacts on ports, and indirect impacts on supply chains, will impact capital investment, maintenance requirements as well as knowledge, skill and training requirements for personnel. Understanding potential future climate impacts allows ports to adequately assess their future planning options, and accommodate the most appropriate adaptation choices.</p><p class=&quot;justified&quot;>This tab assists ports to identify some of the future climate projections data relevant for their region. </p><p class=&quot;justified&quot;>It includes:<ul><li>Projected climate and marine data publicly available from the CSIRO the Australian Bureau of Meteorology Pacific Climate Change Science Program and the Fiji Meteorological Service.</li><li>Applications to help ports identify their future risk to changing climate impacts</li></ul></p><p class=&quot;justified&quot;>You can:<ul><li>ADD DATA - add future climate and marine data and investigate APPLICATIONS to help you understand port risks from climate change</li><li>ADD TEXT - add your own text boxes</li><li>ADD FILE - add your own information/pictures</li></ul></p><h6>CLIMATE AND MARINE DATA</h6><p class=&quot;justified&quot;>Three types of data have been selected:<ul><li>Projected trends for temperature and rainfall from a base of 1990, using 3 emission scenarios (low, medium and high emissions) and centring on 3 time periods (2030, 2055, 2090).</li><li>Projected global trends for sea-level rise; and</li><li>Projected return periods (expressed in years) for extreme weather at specific locations (where available).</li></ul></p><p class=&quot;justified&quot;>Users select from two main climate and marine data sources in this tab:<ul><li>Projected climate change</li><li>Projected extreme</li></ul></p><p class=&quot;justified&quot;>For further information about projected future climate and marine conditions in the Pacific head to the <a href=&quot;http://www.pacificclimatechangescience.org/&quot; target=&quot;_blank&quot; title=&quot;PCCSP&quot;>Pacific Climate Change Science Project</a> (PCCSP).<br />This project produced a series of reports that can be found here: <a href=&quot;http://www.pacificclimatechangescience.org/publications/reports/&quot; target=&quot;_blank&quot;> http://www.pacificclimatechangescience.org/publications/reports/</a><h6>AIDS TO IDENTIFYING FUTURE PORT VULNERABILITY TO CLIMATE IMPACTS</h6><p class=&quot;justified&quot;>Users select:<br />Future climate risk assessment of port vulnerability to a changing climate. This provides a downloadable matrix with a priority rating that is derived from likelihood and consequence tables.</p><p class=&quot;justified&quot;>Building on the knowledge gained using the current vulnerability aid, you can use this aid to create a list of possible future port climate risks and upload the most important risks to the online table.</p>','Future climate &amp; marine'),(3,'<p class=\"justified\">Non-climate data helps set the operational context of ports. It also provides a starting point for consideration of possible impacts of non-climate factors into the future. For example, population growth along the coast can constrain a port\'s ability to expand in the future, and to retreat as sea level rise and climatic conditions change. National population growth can also be a driver of increased activity at container import ports, which may lead to congestion problems. </p><p class=\"justified\">This tab includes trade and population data. Note that only limited data may be available for some ports. </p><p class=\"justified\">Two publicly available data sets are offered within this section. These are urban pressure data derived from the World Bank and trade data from Fiji Statistics. </p><p class=\"justified\">Ports are encouraged to upload port-specific files and information regarding organisational objectives, current risks, or data on throughput volume or the types of activity that characterise the port. </p><p class=\"justified\">You can:<br />ADD DATA -add non-climate demographic and trade data<br />ADD TEXT - add your own text boxes<br />ADD FILE - add your own information/pictures</p><p class=\"justified\">Users can select from two data sources on this tab:<ul><li><b>Demographics</b>, which provides population change data;</li><li><b>Trade Data</b> which provides country-side import and export data</li></ul></p>','<p class=&quot;justified&quot;>Non-climate data helps set the operational context of ports. It also provides a starting point for consideration of possible impacts of non-climate factors into the future. For example, population growth along the coast can constrain a port\'s ability to expand in the future, and to retreat as sea level rise and climatic conditions change. National population growth can also be a driver of increased activity at container import ports, which may lead to congestion problems. </p><p class=&quot;justified&quot;>This tab includes trade and population data. Note that only limited data may be available for some ports. </p><p class=&quot;justified&quot;>Two publicly available data sets are offered within this section. These are urban pressure data derived from the World Bank and trade data from Fiji Statistics. </p><p class=&quot;justified&quot;>Ports are encouraged to upload port-specific files and information regarding organisational objectives, current risks, or data on throughput volume or the types of activity that characterise the port. </p><p class=&quot;justified&quot;>You can:<br />ADD DATA -add non-climate demographic and trade data<br />ADD TEXT - add your own text boxes<br />ADD FILE - add your own information/pictures</p><p class=&quot;justified&quot;>Users can select from two data sources on this tab:<ul><li><b>Demographics</b>, which provides population change data;</li><li><b>Trade Data</b> which provides country-side import and export data</li></ul></p>','Non-climate context'),(4,'<p class=\"justified\">A port can vulnerable to many climate impacts including: storm, floods, cyclones, rain, heatwaves etc. In the future these impacts may increase or decrease under climate change and there may be as yet unrecognised climate impacts that will appear. The port knows best what weather current affects its operations. These Applications help the port gather the information only they have.</p><p class=\"justified\">Applications (in the form of questionnaires and tables) can be accessed and downloaded to support:<ol><li>Identify port responses to extreme weather events in the past<br />How a port has coped with, and responded to past extreme weather events, can be an indication of how well it will cope with a future climate change. This APPLICATION appears as a ‘spider graph’ showing which area of port business was most affected by a past weather event</li><li>Collate perceptions of port vulnerability to the current climate<br />Climate affects different part of the port business, bringing all the climate impacts into one table and ranking them according to their severity will help the port understand the full extent of its current vulnerability to the climate. This APPLICATION consists of a downloadable table correlating climate impacts and areas of port business.</li><li>Providing a priority rating for the risk of future climate impacts to the port<br />Future climate risks to the port may be existing climate risks or completely new one. This APPLICATION consists of (i) a downloadable table to record the climate change description, the risk. and a current threshold that could be exceeded in the future. It has a priority rating attached that includes a consequence table and a likelihood table.</li></ol></p><p class=\"justified\">When considering the questions in this tab, think of the impact on port assets and operations (machinery, buildings, equipment), infrastructure (drainage, rail, road, berths), trade (goods moving in and out of a port) and people (injuries, work disruptions).</p>','<p class=&quot;justified&quot;>A port can vulnerable to many climate impacts including: storm, floods, cyclones, rain, heatwaves etc. In the future these impacts may increase or decrease under climate change and there may be as yet unrecognised climate impacts that will appear. The port knows best what weather current affects its operations. These Applications help the port gather the information only they have.</p><p class=&quot;justified&quot;>Applications (in the form of questionnaires and tables) can be accessed and downloaded to support:<ol><li>Identify port responses to extreme weather events in the past<br />How a port has coped with, and responded to past extreme weather events, can be an indication of how well it will cope with a future climate change. This APPLICATION appears as a ‘spider graph’ showing which area of port business was most affected by a past weather event</li><li>Collate perceptions of port vulnerability to the current climate<br />Climate affects different part of the port business, bringing all the climate impacts into one table and ranking them according to their severity will help the port understand the full extent of its current vulnerability to the climate. This APPLICATION consists of a downloadable table correlating climate impacts and areas of port business.</li><li>Providing a priority rating for the risk of future climate impacts to the port<br />Future climate risks to the port may be existing climate risks or completely new one. This APPLICATION consists of (i) a downloadable table to record the climate change description, the risk. and a current threshold that could be exceeded in the future. It has a priority rating attached that includes a consequence table and a likelihood table.</li></ol></p><p class=&quot;justified&quot;>When considering the questions in this tab, think of the impact on port assets and operations (machinery, buildings, equipment), infrastructure (drainage, rail, road, berths), trade (goods moving in and out of a port) and people (injuries, work disruptions).</p>','Applications');
/*!40000 ALTER TABLE `element_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `extreme_data`
--

DROP TABLE IF EXISTS `extreme_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `extreme_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `creation_date` datetime DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `source_name` varchar(255) DEFAULT NULL,
  `value` double DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  `region_id` int(11) DEFAULT NULL,
  `extreme_variable_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKBFABC75D6F95769C` (`extreme_variable_id`),
  KEY `FKBFABC75D6B59B27` (`region_id`),
  CONSTRAINT `FKBFABC75D6B59B27` FOREIGN KEY (`region_id`) REFERENCES `region` (`id`),
  CONSTRAINT `FKBFABC75D6F95769C` FOREIGN KEY (`extreme_variable_id`) REFERENCES `extreme_variable` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `extreme_data`
--

LOCK TABLES `extreme_data` WRITE;
/*!40000 ALTER TABLE `extreme_data` DISABLE KEYS */;
INSERT INTO `extreme_data` VALUES (1,'2011-01-01 00:00:00','Nadi Airport','Fiji Meteorological Services',2.9,2010,1,1),(2,'2011-01-01 00:00:00','Laucala Bay','Fiji Meteorological Services',23.1,2010,1,1),(3,'2011-01-01 00:00:00','Nabouwalu','Fiji Meteorological Services',25.3,2010,1,1),(4,'2011-01-01 00:00:00','Vunisea','Fiji Meteorological Services',40.3,2010,1,1),(5,'2011-01-01 00:00:00','Lakeba','Fiji Meteorological Services',77.7,2010,1,1),(6,'2011-01-01 00:00:00','Rotuma','Fiji Meteorological Services',74.3,2010,1,1),(7,'2011-01-01 00:00:00','Nadi Airport','Fiji Meteorological Services',5.4,2010,1,2),(8,'2011-01-01 00:00:00','Laucala Bay','Fiji Meteorological Services',2.9,2010,1,2),(9,'2011-01-01 00:00:00','Nabouwalu','Fiji Meteorological Services',7.2,2010,1,2),(10,'2011-01-01 00:00:00','Vunisea','Fiji Meteorological Services',9.6,2010,1,2),(11,'2011-01-01 00:00:00','Lakeba','Fiji Meteorological Services',6.1,2010,1,2),(12,'2011-01-01 00:00:00','Rotuma','Fiji Meteorological Services',4.4,2010,1,2),(13,'2011-01-01 00:00:00','Nadi Airport','Fiji Meteorological Services',7.5,2010,1,3),(14,'2011-01-01 00:00:00','Laucala Bay','Fiji Meteorological Services',21.3,2010,1,3),(15,'2011-01-01 00:00:00','Nabouwalu','Fiji Meteorological Services',205,2010,1,3),(16,'2011-01-01 00:00:00','Vunisea','Fiji Meteorological Services',10.8,2010,1,3),(17,'2011-01-01 00:00:00','Lakeba','Fiji Meteorological Services',282.2,2010,1,3),(18,'2011-01-01 00:00:00','Rotuma','Fiji Meteorological Services',83.6,2010,1,3),(19,'2011-01-01 00:00:00','Suva Bay','Fiji Meteorological Services',98.9,2010,1,4),(20,'2011-01-01 00:00:00','Lautoka','Fiji Meteorological Services',80.2,2010,1,4),(21,'2011-01-01 00:00:00','Nadi Airport','Fiji Meteorological Services',1.9,2025,1,1),(22,'2011-01-01 00:00:00','Laucala Bay','Fiji Meteorological Services',13.4,2025,1,1),(23,'2011-01-01 00:00:00','Nabouwalu','Fiji Meteorological Services',14.9,2025,1,1),(24,'2011-01-01 00:00:00','Vunisea','Fiji Meteorological Services',24.4,2025,1,1),(25,'2011-01-01 00:00:00','Lakeba','Fiji Meteorological Services',43.3,2025,1,1),(26,'2011-01-01 00:00:00','Rotuma','Fiji Meteorological Services',39.4,2025,1,1),(27,'2011-01-01 00:00:00','Nadi Airport','Fiji Meteorological Services',1.4,2050,1,1),(28,'2011-01-01 00:00:00','Laucala Bay','Fiji Meteorological Services',7.7,2050,1,1),(29,'2011-01-01 00:00:00','Nabouwalu','Fiji Meteorological Services',8.7,2050,1,1),(30,'2011-01-01 00:00:00','Vunisea','Fiji Meteorological Services',14.4,2050,1,1),(31,'2011-01-01 00:00:00','Lakeba','Fiji Meteorological Services',23.9,2050,1,1),(32,'2011-01-01 00:00:00','Rotuma','Fiji Meteorological Services',20.5,2050,1,1),(33,'2011-01-01 00:00:00','Nadi Airport','Fiji Meteorological Services',1.1,2075,1,1),(34,'2011-01-01 00:00:00','Laucala Bay','Fiji Meteorological Services',4.2,2075,1,1),(35,'2011-01-01 00:00:00','Nabouwalu','Fiji Meteorological Services',4.8,2075,1,1),(36,'2011-01-01 00:00:00','Vunisea','Fiji Meteorological Services',7.9,2075,1,1),(37,'2011-01-01 00:00:00','Lakeba','Fiji Meteorological Services',12.2,2075,1,1),(38,'2011-01-01 00:00:00','Rotuma','Fiji Meteorological Services',9.9,2075,1,1),(39,'2011-01-01 00:00:00','Nadi Airport','Fiji Meteorological Services',1,2100,1,1),(40,'2011-01-01 00:00:00','Laucala Bay','Fiji Meteorological Services',3,2100,1,1),(41,'2011-01-01 00:00:00','Nabouwalu','Fiji Meteorological Services',3.3,2100,1,1),(42,'2011-01-01 00:00:00','Vunisea','Fiji Meteorological Services',5.5,2100,1,1),(43,'2011-01-01 00:00:00','Lakeba','Fiji Meteorological Services',7.9,2100,1,1),(44,'2011-01-01 00:00:00','Rotuma','Fiji Meteorological Services',6.2,2100,1,1),(45,'2011-01-01 00:00:00','Nadi Airport','Fiji Meteorological Services',6.1,2025,1,2),(46,'2011-01-01 00:00:00','Laucala Bay','Fiji Meteorological Services',3.1,2025,1,2),(47,'2011-01-01 00:00:00','Nabouwalu','Fiji Meteorological Services',8.3,2025,1,2),(48,'2011-01-01 00:00:00','Vunisea','Fiji Meteorological Services',11.5,2025,1,2),(49,'2011-01-01 00:00:00','Lakeba','Fiji Meteorological Services',6.9,2025,1,2),(50,'2011-01-01 00:00:00','Rotuma','Fiji Meteorological Services',4.9,2025,1,2),(51,'2011-01-01 00:00:00','Nadi Airport','Fiji Meteorological Services',6.9,2050,1,2),(52,'2011-01-01 00:00:00','Laucala Bay','Fiji Meteorological Services',3.5,2050,1,2),(53,'2011-01-01 00:00:00','Nabouwalu','Fiji Meteorological Services',9.7,2050,1,2),(54,'2011-01-01 00:00:00','Vunisea','Fiji Meteorological Services',13.9,2050,1,2),(55,'2011-01-01 00:00:00','Lakeba','Fiji Meteorological Services',8.1,2050,1,2),(56,'2011-01-01 00:00:00','Rotuma','Fiji Meteorological Services',5.6,2050,1,2),(57,'2011-01-01 00:00:00','Nadi Airport','Fiji Meteorological Services',8,2075,1,2),(58,'2011-01-01 00:00:00','Laucala Bay','Fiji Meteorological Services',4,2075,1,2),(59,'2011-01-01 00:00:00','Nabouwalu','Fiji Meteorological Services',11.7,2075,1,2),(60,'2011-01-01 00:00:00','Vunisea','Fiji Meteorological Services',17.7,2075,1,2),(61,'2011-01-01 00:00:00','Lakeba','Fiji Meteorological Services',9.7,2075,1,2),(62,'2011-01-01 00:00:00','Rotuma','Fiji Meteorological Services',6.5,2075,1,2),(63,'2011-01-01 00:00:00','Nadi Airport','Fiji Meteorological Services',8.9,2100,1,2),(64,'2011-01-01 00:00:00','Laucala Bay','Fiji Meteorological Services',4.4,2100,1,2),(65,'2011-01-01 00:00:00','Nabouwalu','Fiji Meteorological Services',13.4,2100,1,2),(66,'2011-01-01 00:00:00','Vunisea','Fiji Meteorological Services',21,2100,1,2),(67,'2011-01-01 00:00:00','Lakeba','Fiji Meteorological Services',11,2100,1,2),(68,'2011-01-01 00:00:00','Rotuma','Fiji Meteorological Services',7.3,2100,1,2),(69,'2011-01-01 00:00:00','Nadi Airport','Fiji Meteorological Services',6.6,2025,1,3),(70,'2011-01-01 00:00:00','Laucala Bay','Fiji Meteorological Services',18.3,2025,1,3),(71,'2011-01-01 00:00:00','Nabouwalu','Fiji Meteorological Services',159,2025,1,3),(72,'2011-01-01 00:00:00','Vunisea','Fiji Meteorological Services',9.6,2025,1,3),(73,'2011-01-01 00:00:00','Lakeba','Fiji Meteorological Services',215.2,2025,1,3),(74,'2011-01-01 00:00:00','Rotuma','Fiji Meteorological Services',68.4,2025,1,3),(75,'2011-01-01 00:00:00','Nadi Airport','Fiji Meteorological Services',5.8,2050,1,3),(76,'2011-01-01 00:00:00','Laucala Bay','Fiji Meteorological Services',15.8,2050,1,3),(77,'2011-01-01 00:00:00','Nabouwalu','Fiji Meteorological Services',124.2,2050,1,3),(78,'2011-01-01 00:00:00','Vunisea','Fiji Meteorological Services',8.6,2050,1,3),(79,'2011-01-01 00:00:00','Lakeba','Fiji Meteorological Services',165.3,2050,1,3),(80,'2011-01-01 00:00:00','Rotuma','Fiji Meteorological Services',56.4,2050,1,3),(81,'2011-01-01 00:00:00','Nadi Airport','Fiji Meteorological Services',5.1,2075,1,3),(82,'2011-01-01 00:00:00','Laucala Bay','Fiji Meteorological Services',13.5,2075,1,3),(83,'2011-01-01 00:00:00','Nabouwalu','Fiji Meteorological Services',95.3,2075,1,3),(84,'2011-01-01 00:00:00','Vunisea','Fiji Meteorological Services',7.6,2075,1,3),(85,'2011-01-01 00:00:00','Lakeba','Fiji Meteorological Services',124.6,2075,1,3),(86,'2011-01-01 00:00:00','Rotuma','Fiji Meteorological Services',45.9,2075,1,3),(87,'2011-01-01 00:00:00','Nadi Airport','Fiji Meteorological Services',4.7,2100,1,3),(88,'2011-01-01 00:00:00','Laucala Bay','Fiji Meteorological Services',12.3,2100,1,3),(89,'2011-01-01 00:00:00','Nabouwalu','Fiji Meteorological Services',81.1,2100,1,3),(90,'2011-01-01 00:00:00','Vunisea','Fiji Meteorological Services',7.1,2100,1,3),(91,'2011-01-01 00:00:00','Lakeba','Fiji Meteorological Services',104.9,2100,1,3),(92,'2011-01-01 00:00:00','Rotuma','Fiji Meteorological Services',40.4,2100,1,3),(93,'2011-01-01 00:00:00','Suva Bay','Fiji Meteorological Services',18.7,2025,1,4),(94,'2011-01-01 00:00:00','Lautoka','Fiji Meteorological Services',26.9,2025,1,4),(95,'2011-01-01 00:00:00','Suva Bay','Fiji Meteorological Services',3.8,2050,1,4),(96,'2011-01-01 00:00:00','Lautoka','Fiji Meteorological Services',9.1,2050,1,4),(97,'2011-01-01 00:00:00','Suva Bay','Fiji Meteorological Services',1.1,2075,1,4),(98,'2011-01-01 00:00:00','Lautoka','Fiji Meteorological Services',2.7,2075,1,4),(99,'2011-01-01 00:00:00','Suva Bay','Fiji Meteorological Services',1,2100,1,4),(100,'2011-01-01 00:00:00','Lautoka','Fiji Meteorological Services',1,2100,1,4);
/*!40000 ALTER TABLE `extreme_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `extreme_variable`
--

DROP TABLE IF EXISTS `extreme_variable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `extreme_variable` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` text,
  `name` varchar(255) DEFAULT NULL,
  `short_name` varchar(255) DEFAULT NULL,
  `uom` varchar(255) DEFAULT NULL,
  `uom_variation` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `extreme_variable`
--

LOCK TABLES `extreme_variable` WRITE;
/*!40000 ALTER TABLE `extreme_variable` DISABLE KEYS */;
INSERT INTO `extreme_variable` VALUES (1,'Return periods of exceeding maximum temperature of 35&#8451','Extreme maximum temperature','T > 35&#8451','year','%'),(2,'Return periods of exceeding daily extreme rainfall (200mm)','Extreme rainfall','RF > 200mm','year','%'),(3,'Return periods of exceeding daily extreme wind of 80 knot (148km/h)','Extreme wind','WS > 80kt','year','%'),(4,'Return periods of extreme sea level (2.4m/2.8m)','Extreme sea level','Extreme SL','year','%');
/*!40000 ALTER TABLE `extreme_variable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `observed_trend_data`
--

DROP TABLE IF EXISTS `observed_trend_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `observed_trend_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `baseline_value` double DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `measure_season` varchar(255) DEFAULT NULL,
  `percentage_value` double DEFAULT NULL,
  `period_end` datetime DEFAULT NULL,
  `period_start` datetime DEFAULT NULL,
  `source_name` varchar(255) DEFAULT NULL,
  `value` double DEFAULT NULL,
  `region_id` int(11) DEFAULT NULL,
  `variable_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK420C16E36B59B27` (`region_id`),
  KEY `FK420C16E3501B20F8` (`variable_id`),
  CONSTRAINT `FK420C16E3501B20F8` FOREIGN KEY (`variable_id`) REFERENCES `observed_trend_variable` (`id`),
  CONSTRAINT `FK420C16E36B59B27` FOREIGN KEY (`region_id`) REFERENCES `region` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `observed_trend_data`
--

LOCK TABLES `observed_trend_data` WRITE;
/*!40000 ALTER TABLE `observed_trend_data` DISABLE KEYS */;
INSERT INTO `observed_trend_data` VALUES (1,21.95,'2011-01-01 00:00:00','annual',NULL,'2010-01-01 00:00:00','1961-01-01 00:00:00','Fiji Meteorological Services',0.6,1,1),(2,23,'2011-01-01 00:00:00','hot wet season (May - Oct)',NULL,'2010-01-01 00:00:00','1961-01-01 00:00:00','Fiji Meteorological Services',0.7,1,1),(3,20.97,'2011-01-01 00:00:00','cool dry season (Nov - Apr)',NULL,'2010-01-01 00:00:00','1961-01-01 00:00:00','Fiji Meteorological Services',1,1,1),(4,28.4,'2011-01-01 00:00:00','annual',NULL,'2010-01-01 00:00:00','1961-01-01 00:00:00','Fiji Meteorological Services',1.1,1,2),(5,29.65,'2011-01-01 00:00:00','hot wet season (May - Oct)',NULL,'2010-01-01 00:00:00','1961-01-01 00:00:00','Fiji Meteorological Services',1.2,1,2),(6,27.25,'2011-01-01 00:00:00','cool dry season (Nov - Apr)',NULL,'2010-01-01 00:00:00','1961-01-01 00:00:00','Fiji Meteorological Services',1,1,2),(7,2550,'2011-01-01 00:00:00','annual',0.03,'2010-01-01 00:00:00','1961-01-01 00:00:00','Fiji Meteorological Services',0.65,1,3),(8,1600,'2011-01-01 00:00:00','hot wet season (May - Oct)',0.08,'2010-01-01 00:00:00','1961-01-01 00:00:00','Fiji Meteorological Services',1.3,1,3),(9,700,'2011-01-01 00:00:00','cool dry season (Nov - Apr)',0.11,'2010-01-01 00:00:00','1961-01-01 00:00:00','Fiji Meteorological Services',0.76,1,3),(10,-1.7976931348623157e308,'2011-01-01 00:00:00','annual',NULL,'2010-01-01 00:00:00','1993-01-01 00:00:00','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 2 Country Reports, Ch 5 Fiji Islands',-1.7976931348623157e308,1,4),(11,-1.7976931348623157e308,'2011-01-01 00:00:00','hot wet season (May - Oct)',NULL,'2010-01-01 00:00:00','1993-01-01 00:00:00','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 2 Country Reports, Ch 5 Fiji Islands',-1.7976931348623157e308,1,4),(12,-1.7976931348623157e308,'2011-01-01 00:00:00','cool dry season (Nov - Apr)',NULL,'2010-01-01 00:00:00','1993-01-01 00:00:00','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 2 Country Reports, Ch 5 Fiji Islands',-1.7976931348623157e308,1,4),(13,-1.7976931348623157e308,'2011-01-01 00:00:00','annual',NULL,'2010-01-01 00:00:00','1950-01-01 00:00:00','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 2 Country Reports, Ch 5 Fiji Islands',-1.7976931348623157e308,2,4);
/*!40000 ALTER TABLE `observed_trend_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `observed_trend_variable`
--

DROP TABLE IF EXISTS `observed_trend_variable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `observed_trend_variable` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` text,
  `name` varchar(255) DEFAULT NULL,
  `short_name` varchar(255) DEFAULT NULL,
  `uom` varchar(255) DEFAULT NULL,
  `uom_variation` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `observed_trend_variable`
--

LOCK TABLES `observed_trend_variable` WRITE;
/*!40000 ALTER TABLE `observed_trend_variable` DISABLE KEYS */;
INSERT INTO `observed_trend_variable` VALUES (1,'Trend in minimum temperatures','Minimum Temperatures','MinTemp','&#8451;','&#8451;'),(2,'Trend in maximum temperatures','Maximum Temperatures','MaxTemp','&#8451;','&#8451;'),(3,'Trend in rainfall','Rainfall','RF','mm/y','%'),(4,'Short-term changes in sea level','Sea Level','SLR','%','%');
/*!40000 ALTER TABLE `observed_trend_variable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `projected_climate_change_data`
--

DROP TABLE IF EXISTS `projected_climate_change_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `projected_climate_change_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `creation_date` datetime DEFAULT NULL,
  `measure_season` varchar(255) DEFAULT NULL,
  `source_name` varchar(255) DEFAULT NULL,
  `value` double DEFAULT NULL,
  `variation` double DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  `climate_emission_scenario_id` int(11) DEFAULT NULL,
  `region_id` int(11) DEFAULT NULL,
  `variable_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKA9B1C078BD99B971` (`climate_emission_scenario_id`),
  KEY `FKA9B1C0786B59B27` (`region_id`),
  KEY `FKA9B1C07864548D2E` (`variable_id`),
  CONSTRAINT `FKA9B1C07864548D2E` FOREIGN KEY (`variable_id`) REFERENCES `projected_climate_change_variable` (`id`),
  CONSTRAINT `FKA9B1C0786B59B27` FOREIGN KEY (`region_id`) REFERENCES `region` (`id`),
  CONSTRAINT `FKA9B1C078BD99B971` FOREIGN KEY (`climate_emission_scenario_id`) REFERENCES `climate_emission_scenario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projected_climate_change_data`
--

LOCK TABLES `projected_climate_change_data` WRITE;
/*!40000 ALTER TABLE `projected_climate_change_data` DISABLE KEYS */;
INSERT INTO `projected_climate_change_data` VALUES (1,'2012-01-01 00:00:00','Annual','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Fiji Islands',0.6,0.4,2030,1,1,1),(2,'2012-01-01 00:00:00','Annual','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Fiji Islands',1,0.5,2055,1,1,1),(3,'2012-01-01 00:00:00','Annual','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Fiji Islands',1.4,0.7,2090,1,1,1),(4,'2012-01-01 00:00:00','Annual','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Fiji Islands',0.7,0.5,2030,2,1,1),(5,'2012-01-01 00:00:00','Annual','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Fiji Islands',1.4,0.5,2055,2,1,1),(6,'2012-01-01 00:00:00','Annual','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Fiji Islands',2.1,0.8,2090,2,1,1),(7,'2012-01-01 00:00:00','Annual','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Fiji Islands',0.7,0.3,2030,3,1,1),(8,'2012-01-01 00:00:00','Annual','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Fiji Islands',1.4,0.3,2055,3,1,1),(9,'2012-01-01 00:00:00','Annual','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Fiji Islands',2.6,0.6,2090,3,1,1),(10,'2012-01-01 00:00:00','Annual','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Fiji Islands',3,11,2030,1,1,2),(11,'2012-01-01 00:00:00','Annual','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Fiji Islands',1,10,2055,1,1,2),(12,'2012-01-01 00:00:00','Annual','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Fiji Islands',2,14,2090,1,1,2),(13,'2012-01-01 00:00:00','Annual','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Fiji Islands',1,12,2030,2,1,2),(14,'2012-01-01 00:00:00','Annual','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Fiji Islands',3,14,2055,2,1,2),(15,'2012-01-01 00:00:00','Annual','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Fiji Islands',3,16,2090,2,1,2),(16,'2012-01-01 00:00:00','Annual','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Fiji Islands',2,13,2030,3,1,2),(17,'2012-01-01 00:00:00','Annual','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Fiji Islands',4,13,2055,3,1,2),(18,'2012-01-01 00:00:00','Annual','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Fiji Islands',7,15,2090,3,1,2),(19,'2012-01-01 00:00:00','Wet season (Nov-Apr)','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Fiji Islands',5,10,2030,1,1,2),(20,'2012-01-01 00:00:00','Wet season (Nov-Apr)','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Fiji Islands',5,12,2055,1,1,2),(21,'2012-01-01 00:00:00','Wet season (Nov-Apr)','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Fiji Islands',5,18,2090,1,1,2),(22,'2012-01-01 00:00:00','Wet season (Nov-Apr)','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Fiji Islands',3,11,2030,2,1,2),(23,'2012-01-01 00:00:00','Wet season (Nov-Apr)','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Fiji Islands',6,16,2055,2,1,2),(24,'2012-01-01 00:00:00','Wet season (Nov-Apr)','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Fiji Islands',8,19,2090,2,1,2),(25,'2012-01-01 00:00:00','Wet season (Nov-Apr)','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Fiji Islands',5,14,2030,3,1,2),(26,'2012-01-01 00:00:00','Wet season (Nov-Apr)','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Fiji Islands',7,13,2055,3,1,2),(27,'2012-01-01 00:00:00','Wet season (Nov-Apr)','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Fiji Islands',14,14,2090,3,1,2),(28,'2012-01-01 00:00:00','Dry season (May-Oct)','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Fiji Islands',-1,13,2030,1,1,2),(29,'2012-01-01 00:00:00','Dry season (May-Oct)','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Fiji Islands',-5,14,2055,1,1,2),(30,'2012-01-01 00:00:00','Dry season (May-Oct)','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Fiji Islands',-3,17,2090,1,1,2),(31,'2012-01-01 00:00:00','Dry season (May-Oct)','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Fiji Islands',-2,17,2030,2,1,2),(32,'2012-01-01 00:00:00','Dry season (May-Oct)','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Fiji Islands',-2,16,2055,2,1,2),(33,'2012-01-01 00:00:00','Dry season (May-Oct)','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Fiji Islands',-4,19,2090,2,1,2),(34,'2012-01-01 00:00:00','Dry season (May-Oct)','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Fiji Islands',-2,12,2030,3,1,2),(35,'2012-01-01 00:00:00','Dry season (May-Oct)','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Fiji Islands',-1,18,2055,3,1,2),(36,'2012-01-01 00:00:00','Dry season (May-Oct)','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Fiji Islands',-1,22,2090,3,1,2),(37,'2012-01-01 00:00:00','Annual','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Papua New Guinea',0.7,0.4,2030,1,2,1),(38,'2012-01-01 00:00:00','Annual','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Papua New Guinea',1.1,0.5,2055,1,2,1),(39,'2012-01-01 00:00:00','Annual','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Papua New Guinea',1.6,0.6,2090,1,2,1),(40,'2012-01-01 00:00:00','Annual','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Papua New Guinea',0.8,0.4,2030,2,2,1),(41,'2012-01-01 00:00:00','Annual','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Papua New Guinea',1.5,0.5,2055,2,2,1),(42,'2012-01-01 00:00:00','Annual','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Papua New Guinea',2.4,0.8,2090,2,2,1),(43,'2012-01-01 00:00:00','Annual','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Papua New Guinea',0.7,0.3,2030,3,2,1),(44,'2012-01-01 00:00:00','Annual','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Papua New Guinea',1.5,0.4,2055,3,2,1),(45,'2012-01-01 00:00:00','Annual','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Papua New Guinea',2.8,0.6,2090,3,2,1),(46,'2012-01-01 00:00:00','Annual','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Papua New Guinea',3,13,2030,1,2,2),(47,'2012-01-01 00:00:00','Annual','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Papua New Guinea',8,13,2055,1,2,2),(48,'2012-01-01 00:00:00','Annual','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Papua New Guinea',10,14,2090,1,2,2),(49,'2012-01-01 00:00:00','Annual','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Papua New Guinea',3,13,2030,2,2,2),(50,'2012-01-01 00:00:00','Annual','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Papua New Guinea',7,17,2055,2,2,2),(51,'2012-01-01 00:00:00','Annual','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Papua New Guinea',15,20,2090,2,2,2),(52,'2012-01-01 00:00:00','Annual','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Papua New Guinea',5,9,2030,3,2,2),(53,'2012-01-01 00:00:00','Annual','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Papua New Guinea',7,13,2055,3,2,2),(54,'2012-01-01 00:00:00','Annual','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Papua New Guinea',15,21,2090,3,2,2),(55,'2012-01-01 00:00:00','Wet season (Nov-Apr)','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Papua New Guinea',4,12,2030,1,2,2),(56,'2012-01-01 00:00:00','Wet season (Nov-Apr)','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Papua New Guinea',10,13,2055,1,2,2),(57,'2012-01-01 00:00:00','Wet season (Nov-Apr)','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Papua New Guinea',12,12,2090,1,2,2),(58,'2012-01-01 00:00:00','Wet season (Nov-Apr)','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Papua New Guinea',5,11,2030,2,2,2),(59,'2012-01-01 00:00:00','Wet season (Nov-Apr)','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Papua New Guinea',9,17,2055,2,2,2),(60,'2012-01-01 00:00:00','Wet season (Nov-Apr)','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Papua New Guinea',16,18,2090,2,2,2),(61,'2012-01-01 00:00:00','Wet season (Nov-Apr)','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Papua New Guinea',6,10,2030,3,2,2),(62,'2012-01-01 00:00:00','Wet season (Nov-Apr)','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Papua New Guinea',8,12,2055,3,2,2),(63,'2012-01-01 00:00:00','Wet season (Nov-Apr)','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Papua New Guinea',15,20,2090,3,2,2),(64,'2012-01-01 00:00:00','Dry season (May-Oct)','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Papua New Guinea',1,15,2030,1,2,2),(65,'2012-01-01 00:00:00','Dry season (May-Oct)','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Papua New Guinea',7,16,2055,1,2,2),(66,'2012-01-01 00:00:00','Dry season (May-Oct)','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Papua New Guinea',10,16,2090,1,2,2),(67,'2012-01-01 00:00:00','Dry season (May-Oct)','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Papua New Guinea',1,16,2030,2,2,2),(68,'2012-01-01 00:00:00','Dry season (May-Oct)','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Papua New Guinea',5,20,2055,2,2,2),(69,'2012-01-01 00:00:00','Dry season (May-Oct)','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Papua New Guinea',15,24,2090,2,2,2),(70,'2012-01-01 00:00:00','Dry season (May-Oct)','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Papua New Guinea',4,12,2030,3,2,2),(71,'2012-01-01 00:00:00','Dry season (May-Oct)','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Papua New Guinea',6,17,2055,3,2,2),(72,'2012-01-01 00:00:00','Dry season (May-Oct)','Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Papua New Guinea',15,26,2090,3,2,2);
/*!40000 ALTER TABLE `projected_climate_change_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `projected_climate_change_variable`
--

DROP TABLE IF EXISTS `projected_climate_change_variable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `projected_climate_change_variable` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` text,
  `name` varchar(255) DEFAULT NULL,
  `short_name` varchar(255) DEFAULT NULL,
  `uom` varchar(255) DEFAULT NULL,
  `uom_variation` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projected_climate_change_variable`
--

LOCK TABLES `projected_climate_change_variable` WRITE;
/*!40000 ALTER TABLE `projected_climate_change_variable` DISABLE KEYS */;
INSERT INTO `projected_climate_change_variable` VALUES (1,'Projected multi-model mean changes in annual mean surface air temperature for 2030, 2055 and 2090, relative to 1990, under the A2 (high), A1B (medium) and B1 (low) emissions scenarios. All models agree on warming in all locations.','Surface Air Temperature','T','&#8451;',''),(2,'Projected multi-modal mean changes in annual rainfall (mm/day) for 2030, 2055 and 2090, relative to 1990, under the A2 (high); A1B (medium) and B1 (low) emissions scenarios. Regions where at least 80% of the models agree on the direction of change are stippled.','Total Rainfall','RF','mm/day','');
/*!40000 ALTER TABLE `projected_climate_change_variable` ENABLE KEYS */;
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
INSERT INTO `region` VALUES (1,'176.284180,-15.891800 -178.046875,-15.891800 -178.046875,-20.070571 176.284180,-20.070571','Fiji'),(2,'','Papua New Guinea'),(3,'','Vanuatu'),(4,'','Tonga'),(5,'','American Samoa'),(6,'','Wallis and Futuna'),(7,'','Solomon Islands'),(8,'','New Caledonia');
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
  `template` varchar(255) DEFAULT NULL,
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
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
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
INSERT INTO `seaport` VALUES ('FJLEV',-17.685122,178.837091,'Levuka','Levuka',1),('FJLTK',-17.608857,177.438925,'Lautoka','Lautoka',1),('FJMAL',-16.360833,179.366413,'Malau','Malau',1),('FJSUV',-18.133425,178.423022,'Suva','Suva',1),('FJWAI',-16.939701,178.661095,'Wairiki','Wairiki',1),('PGATP',-3.143782,142.351887,'Aitape','Aitape',2),('PGBUA',-5.432515,154.671298,'Buka','Buka',2),('PGDAU',-9.067936,143.209952,'Daru','Daru',2),('PGGUR',-10.31624,150.45528,'Alotau','Alotau',2),('PGKIE',-6.218631,155.639023,'Kieta','Kieta',2),('PGKIM',-5.545805,150.14484,'Kimbe','Kimbe',2),('PGKVG',-2.585032,150.78926,'Kavieng','Kavieng',2),('PGLAE',-6.737482,147.00896,'Lae','Lae',2),('PGLOR',-2.025895,147.271036,'Lorengau','Lorengau, Manus Island',2),('PGMAG',-5.214895,145.803209,'Madang','Madang',2),('PGPOM',-9.463927,147.153606,'Port Moresby','Port Moresby',2),('PGRAB',-4.202961,152.163644,'Rabaul','Rabaul',2),('PGROR',-8.896389,148.494167,'Oro Bay','Buna',2),('PGVAI',-2.682094,141.297859,'Vanimo','Vanimo',2),('PGWWK',-3.571131,143.64454,'Wewak','Wewak',2);
/*!40000 ALTER TABLE `seaport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `text_input`
--

DROP TABLE IF EXISTS `text_input`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `text_input` (
  `data_element_id` int(11) NOT NULL,
  `inputs` text,
  KEY `FK823EAA38F77D5B18` (`data_element_id`),
  CONSTRAINT `FK823EAA38F77D5B18` FOREIGN KEY (`data_element_id`) REFERENCES `element` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `text_input`
--

LOCK TABLES `text_input` WRITE;
/*!40000 ALTER TABLE `text_input` DISABLE KEYS */;
/*!40000 ALTER TABLE `text_input` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trade_data`
--

DROP TABLE IF EXISTS `trade_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trade_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `creation_date` datetime DEFAULT NULL,
  `imported` bit(1) DEFAULT NULL,
  `source_name` varchar(255) DEFAULT NULL,
  `value` double DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  `region_id` int(11) DEFAULT NULL,
  `variable_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6FC4F5A56B59B27` (`region_id`),
  KEY `FK6FC4F5A588E0B0A7` (`variable_id`),
  CONSTRAINT `FK6FC4F5A588E0B0A7` FOREIGN KEY (`variable_id`) REFERENCES `trade_variable` (`id`),
  CONSTRAINT `FK6FC4F5A56B59B27` FOREIGN KEY (`region_id`) REFERENCES `region` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=221 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trade_data`
--

LOCK TABLES `trade_data` WRITE;
/*!40000 ALTER TABLE `trade_data` DISABLE KEYS */;
INSERT INTO `trade_data` VALUES (1,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',92188,2000,1,1),(2,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',101315,2001,1,1),(3,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',85442,2002,1,1),(4,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',91724,2003,1,1),(5,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',93194,2004,1,1),(6,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',94194,2005,1,1),(7,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',120325,2006,1,1),(8,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',115771,2007,1,1),(9,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',146714,2008,1,1),(10,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',175259,2009,1,1),(11,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',254995,2010,1,1),(12,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',40899,2000,1,2),(13,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',40357,2001,1,2),(14,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',36165,2002,1,2),(15,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',40441,2003,1,2),(16,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',50311,2004,1,2),(17,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',47201,2005,1,2),(18,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',54858,2006,1,2),(19,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',61742,2007,1,2),(20,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',65681,2008,1,2),(21,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',62812,2009,1,2),(22,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',75096,2010,1,2),(23,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',295991,2000,1,3),(24,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',309811,2001,1,3),(25,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',321294,2002,1,3),(26,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',323452,2003,1,3),(27,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',338380,2004,1,3),(28,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',384578,2005,1,3),(29,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',398521,2006,1,3),(30,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',391572,2007,1,3),(31,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',458837,2008,1,3),(32,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',384693,2009,1,3),(33,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',311184,2010,1,3),(34,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',106736,2000,1,4),(35,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',166914,2001,1,4),(36,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',197857,2002,1,4),(37,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',196599,2003,1,4),(38,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',180985,2004,1,4),(39,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',282782,2005,1,4),(40,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',296711,2006,1,4),(41,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',297447,2007,1,4),(42,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',421457,2008,1,4),(43,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',256691,2009,1,4),(44,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',413579,2010,1,4),(45,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',9956,2000,1,5),(46,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',11034,2001,1,5),(47,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',12746,2002,1,5),(48,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',17111,2003,1,5),(49,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',16390,2004,1,5),(50,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',17475,2005,1,5),(51,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',19107,2006,1,5),(52,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',28842,2007,1,5),(53,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',36046,2008,1,5),(54,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',36397,2009,1,5),(55,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',44209,2010,1,5),(56,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',45549,2000,1,6),(57,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',43354,2001,1,6),(58,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',40793,2002,1,6),(59,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',32134,2003,1,6),(60,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',42967,2004,1,6),(61,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',47167,2005,1,6),(62,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',38811,2006,1,6),(63,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',52657,2007,1,6),(64,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',60259,2008,1,6),(65,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',38381,2009,1,6),(66,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',81338,2010,1,6),(67,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',370704,2000,1,7),(68,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',335747,2001,1,7),(69,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',242316,2002,1,7),(70,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',263615,2003,1,7),(71,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',269199,2004,1,7),(72,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',145275,2005,1,7),(73,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',114441,2006,1,7),(74,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',112819,2007,1,7),(75,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',114631,2008,1,7),(76,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',98522,2009,1,7),(77,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',117231,2010,1,7),(78,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',70971,2000,1,8),(79,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',87576,2001,1,8),(80,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',72240,2002,1,8),(81,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',79424,2003,1,8),(82,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',92128,2004,1,8),(83,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',67927,2005,1,8),(84,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',50886,2006,1,8),(85,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',3874,2007,1,8),(86,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',29941,2008,1,8),(87,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',48844,2009,1,8),(88,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',152764,2010,1,8),(89,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',11857,2000,1,9),(90,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',12599,2001,1,9),(91,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',13828,2002,1,9),(92,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',17627,2003,1,9),(93,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',18560,2004,1,9),(94,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',18379,2005,1,9),(95,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',22096,2006,1,9),(96,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',28101,2007,1,9),(97,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',31182,2008,1,9),(98,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',30583,2009,1,9),(99,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',53996,2010,1,9),(100,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',19210,2000,1,10),(101,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',16604,2001,1,10),(102,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',16644,2002,1,10),(103,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',16316,2003,1,10),(104,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',20165,2004,1,10),(105,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',19277,2005,1,10),(106,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',17847,2006,1,10),(107,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',30157,2007,1,10),(108,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',29600,2008,1,10),(109,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',27013,2009,1,10),(110,'2011-01-01 00:00:00','\0','Fiji Bureau of Statistics',25548,2010,1,10),(111,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',78268,2000,1,1),(112,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',147952,2001,1,1),(113,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',139635,2002,1,1),(114,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',138584,2003,1,1),(115,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',141813,2004,1,1),(116,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',138948,2005,1,1),(117,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',135554,2006,1,1),(118,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',130190,2007,1,1),(119,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',148275,2008,1,1),(120,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',139721,2009,1,1),(121,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',205658,2010,1,1),(122,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',102922,2000,1,2),(123,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',110395,2001,1,2),(124,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',116969,2002,1,2),(125,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',124077,2003,1,2),(126,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',131250,2004,1,2),(127,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',132351,2005,1,2),(128,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',153943,2006,1,2),(129,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',164864,2007,1,2),(130,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',218010,2008,1,2),(131,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',184591,2009,1,2),(132,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',220897,2010,1,2),(133,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',56310,2000,1,3),(134,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',70603,2001,1,3),(135,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',74419,2002,1,3),(136,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',90641,2003,1,3),(137,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',100518,2004,1,3),(138,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',107368,2005,1,3),(139,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',124764,2006,1,3),(140,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',120948,2007,1,3),(141,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',172149,2008,1,3),(142,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',213730,2009,1,3),(143,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',172689,2010,1,3),(144,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',339995,2000,1,4),(145,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',449118,2001,1,4),(146,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',441388,2002,1,4),(147,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',470534,2003,1,4),(148,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',596086,2004,1,4),(149,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',802607,2005,1,4),(150,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',1043453,2006,1,4),(151,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',976998,2007,1,4),(152,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',1252068,2008,1,4),(153,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',743070,2009,1,4),(154,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',1130356,2010,1,4),(155,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',102191,2000,1,5),(156,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',124250,2001,1,5),(157,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',122642,2002,1,5),(158,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',133876,2003,1,5),(159,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',160585,2004,1,5),(160,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',151955,2005,1,5),(161,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',167912,2006,1,5),(162,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',149499,2007,1,5),(163,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',199212,2008,1,5),(164,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',177143,2009,1,5),(165,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',210780,2010,1,5),(166,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',79886,2000,1,11),(167,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',90468,2001,1,11),(168,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',90717,2002,1,11),(169,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',107778,2003,1,11),(170,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',123284,2004,1,11),(171,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',147223,2005,1,11),(172,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',144641,2006,1,11),(173,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',142915,2007,1,11),(174,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',153128,2008,1,11),(175,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',127090,2009,1,11),(176,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',162778,2010,1,11),(177,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',387064,2000,1,7),(178,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',270421,2001,1,7),(179,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',212433,2002,1,7),(180,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',244547,2003,1,7),(181,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',246619,2004,1,7),(182,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',173699,2005,1,7),(183,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',156591,2006,1,7),(184,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',153214,2007,1,7),(185,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',156786,2008,1,7),(186,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',134290,2009,1,7),(187,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',171058,2010,1,7),(188,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',88528,2000,1,12),(189,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',91741,2001,1,12),(190,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',111718,2002,1,12),(191,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',128675,2003,1,12),(192,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',158135,2004,1,12),(193,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',170483,2005,1,12),(194,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',188769,2006,1,12),(195,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',163719,2007,1,12),(196,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',186299,2008,1,12),(197,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',160477,2009,1,12),(198,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',175126,2010,1,12),(199,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',265232,2000,1,10),(200,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',299750,2001,1,10),(201,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',279854,2002,1,10),(202,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',311030,2003,1,10),(203,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',364917,2004,1,10),(204,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',406195,2005,1,10),(205,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',465707,2006,1,10),(206,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',409854,2007,1,10),(207,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',516053,2008,1,10),(208,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',446007,2009,1,10),(209,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',448633,2010,1,10),(210,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',95843,2000,1,13),(211,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',119297,2001,1,13),(212,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',145488,2002,1,13),(213,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',266239,2003,1,13),(214,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',193807,2004,1,13),(215,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',184937,2005,1,13),(216,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',226968,2006,1,13),(217,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',170656,2007,1,13),(218,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',233874,2008,1,13),(219,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',164120,2009,1,13),(220,'2011-01-01 00:00:00','','Fiji Bureau of Statistics',182615,2010,1,13);
/*!40000 ALTER TABLE `trade_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trade_variable`
--

DROP TABLE IF EXISTS `trade_variable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trade_variable` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` text,
  `name` varchar(255) DEFAULT NULL,
  `short_name` varchar(255) DEFAULT NULL,
  `uom` varchar(255) DEFAULT NULL,
  `uom_variation` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trade_variable`
--

LOCK TABLES `trade_variable` WRITE;
/*!40000 ALTER TABLE `trade_variable` DISABLE KEYS */;
INSERT INTO `trade_variable` VALUES (1,'Live animals and animal products','Live animals and animal products','','FDJ',''),(2,'Vegetable products','Vegetable Products','','FDJ',''),(3,'Prepared food, beverages, tobacco','Prepared food, beverages, tobacco','','FDJ',''),(4,'Mineral products','Mineral products','','FDJ',''),(5,'Chemical products','Chemical','','FDJ',''),(6,'Wood, cork, rushes','Wood, cork, rushes','','FDJ',''),(7,'Textiles','Textiles','','FDJ',''),(8,'Precious, semi-precious stones and metal','Precious, semi-precious stones and metal','','FDJ',''),(9,'Metal','Metal','','FDJ',''),(10,'Machinery, electrical applicances, parts','Machinery, electrical applicances, parts','','FDJ',''),(11,'Plastic, rubber articles','Plastic, rubber articles','','FDJ',''),(12,'Base metal','Base metal','','FDJ',''),(13,'Transport equipment','Transport equipment','','FDJ','');
/*!40000 ALTER TABLE `trade_variable` ENABLE KEYS */;
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
INSERT INTO `user` VALUES ('admin','email@company.com','','Admin','Admin','','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','ROLE_ADMIN'),('user','email@company.com','','User','User','','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','ROLE_USER');
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

-- Dump completed on 2014-06-10 10:33:52
