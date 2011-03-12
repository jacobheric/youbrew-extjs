-- MySQL dump 10.13  Distrib 5.1.42, for apple-darwin9.5.0 (i386)
--
-- Host: localhost    Database: youbrew
-- ------------------------------------------------------
-- Server version	5.1.42

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

CREATE SCHEMA IF NOT EXISTS `youbrew` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE youbrew;
--
-- Table structure for table `hop`
--

DROP TABLE IF EXISTS `hop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hop` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL COMMENT 'name of hop',
  `alpha` varchar(45) NOT NULL,
  `origin` varchar(45) DEFAULT NULL,
  `description` varchar(1028) DEFAULT NULL COMMENT 'Additional hop description',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='The hops that bitter your brew.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hop`
--

LOCK TABLES `hop` WRITE;
/*!40000 ALTER TABLE `hop` DISABLE KEYS */;
/*!40000 ALTER TABLE `hop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `malt`
--

DROP TABLE IF EXISTS `malt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `malt` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL COMMENT 'Descriptive name of malt',
  `color` smallint(6) DEFAULT NULL COMMENT 'SRM value',
  `potential` double DEFAULT NULL COMMENT 'Potential SG ',
  `yield` double DEFAULT NULL COMMENT 'Yield percentage',
  `origin` varchar(45) DEFAULT NULL COMMENT 'Country of origin',
  `description` varchar(1024) DEFAULT NULL COMMENT 'Additional description of malt',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='The malts that make up your grain bill';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `malt`
--

LOCK TABLES `malt` WRITE;
/*!40000 ALTER TABLE `malt` DISABLE KEYS */;
/*!40000 ALTER TABLE `malt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recipe`
--

DROP TABLE IF EXISTS `recipe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recipe` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(256) NOT NULL COMMENT 'Name of recipe',
  `start` datetime DEFAULT NULL COMMENT 'start date & time',
  `end` datetime DEFAULT NULL COMMENT 'end date/time',
  `brew_notes` varchar(1028) DEFAULT NULL COMMENT 'notes on the brew session',
  `taste_notes` varchar(1028) DEFAULT NULL COMMENT 'Notes on taste',
  `yeast_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `yeast_fk` (`yeast_id`),
  CONSTRAINT `yeast_fk` FOREIGN KEY (`yeast_id`) REFERENCES `yeast` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1 COMMENT='recipe table';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipe`
--

LOCK TABLES `recipe` WRITE;
/*!40000 ALTER TABLE `recipe` DISABLE KEYS */;
INSERT INTO `recipe` VALUES (1,'Plain Old Pale',NULL,NULL,'Easy Brew Day','Tastes like bark/dirt1',2),(2,'Ignatius P. Reilly Pale',NULL,NULL,'Lazy brew day','Crazy tasting beer',1),(3,'Charter House Ale',NULL,NULL,'A cold and rainy brew day','A test taste note',2),(5,'Pugnacious Porter',NULL,NULL,'It was a dark and stormy brew night','Tastes like dath itself (whatever death might taste like)',2),(6,'Randy Imperial Red',NULL,NULL,'It was a bare naked brew day','Mischevious flavor',1),(10,'Timbuk Too',NULL,NULL,'Wild brew day','Every flavor (do I taste buffalo wings?) and the kitchen sink in there ',3),(11,'Hearty Hedon',NULL,NULL,'Exhausting, mistake-ridden','Big, hoppy, wonderful',2),(12,'Small Ale',NULL,NULL,'Smooth day, efficient mash, this is the second runnings','Clean, simple',1),(13,'Rainy Day #12',NULL,NULL,'Brew day clipped along, like poetry','Goes down fast',2),(14,'Alpha Pale',NULL,NULL,'Lots of hop additions, early boil, middle boil, often','Big nose',2),(15,'Serious Black Ale',NULL,NULL,'Scary brew day','Lots of roast malt & chocolate flavors',1),(16,'The Great Went Ale',NULL,NULL,'Big batch','Where did it go?',2);
/*!40000 ALTER TABLE `recipe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recipe_hops`
--

DROP TABLE IF EXISTS `recipe_hops`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recipe_hops` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `recipe_id` int(11) NOT NULL COMMENT 'recipe pk',
  `hop_id` int(11) NOT NULL COMMENT 'hop pk',
  PRIMARY KEY (`id`),
  KEY `recipe_hop_fk` (`recipe_id`),
  KEY `hop_recipe_fk` (`hop_id`),
  CONSTRAINT `hop_recipe_fk` FOREIGN KEY (`hop_id`) REFERENCES `hop` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `recipe_hop_fk` FOREIGN KEY (`recipe_id`) REFERENCES `recipe` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Joins hops to recipes';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipe_hops`
--

LOCK TABLES `recipe_hops` WRITE;
/*!40000 ALTER TABLE `recipe_hops` DISABLE KEYS */;
/*!40000 ALTER TABLE `recipe_hops` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recipe_malts`
--

DROP TABLE IF EXISTS `recipe_malts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recipe_malts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `malt_id` int(11) NOT NULL COMMENT 'the malt pk',
  `recipe_id` int(11) NOT NULL COMMENT 'the recipe pk',
  PRIMARY KEY (`id`),
  KEY `recipe_fk` (`recipe_id`),
  KEY `malt_fk` (`malt_id`),
  CONSTRAINT `malt_fk` FOREIGN KEY (`malt_id`) REFERENCES `malt` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `recipe_fk` FOREIGN KEY (`recipe_id`) REFERENCES `recipe` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Joins malts to recipes.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipe_malts`
--

LOCK TABLES `recipe_malts` WRITE;
/*!40000 ALTER TABLE `recipe_malts` DISABLE KEYS */;
/*!40000 ALTER TABLE `recipe_malts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yeast`
--

DROP TABLE IF EXISTS `yeast`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `yeast` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL COMMENT 'Name of yeast',
  `attenuation` smallint(6) NOT NULL COMMENT 'Attenuation percentage',
  `description` varchar(1028) DEFAULT NULL COMMENT 'Additional description',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 COMMENT='yeast table';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yeast`
--

LOCK TABLES `yeast` WRITE;
/*!40000 ALTER TABLE `yeast` DISABLE KEYS */;
INSERT INTO `yeast` VALUES (1,'California Ale',70,'California Ale WLP001'),(2,'Northwest Ale',68,'Mild, malty'),(3,'Trappist',75,'Brettanomyces, etc.');
/*!40000 ALTER TABLE `yeast` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2011-03-12 14:23:08

CREATE USER 'youbrew_user'@'localhost' IDENTIFIED BY 'youbrew_pass';
GRANT ALL PRIVILEGES ON youbrew.* TO 'youbrew_user'@'%' WITH GRANT OPTION;
