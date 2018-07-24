-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: tabletennis
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Table structure for table `competition`
--

DROP TABLE IF EXISTS `competition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `competition` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT '',
  `completed` tinyint(4) NOT NULL DEFAULT '0',
  `current` tinyint(4) NOT NULL DEFAULT '0',
  `category` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `competition`
--

LOCK TABLES `competition` WRITE;
/*!40000 ALTER TABLE `competition` DISABLE KEYS */;
INSERT INTO `competition` VALUES (1,'Test','Test desc',0,1,'SENIOR'),(2,'Test2','Test2 desc',0,0,'MEDIOR');
/*!40000 ALTER TABLE `competition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `competition_group`
--

DROP TABLE IF EXISTS `competition_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `competition_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `competition_id` bigint(20) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_comp_idx` (`competition_id`),
  CONSTRAINT `fk_comp` FOREIGN KEY (`competition_id`) REFERENCES `competition` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=154 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `competition_group`
--

LOCK TABLES `competition_group` WRITE;
/*!40000 ALTER TABLE `competition_group` DISABLE KEYS */;
INSERT INTO `competition_group` VALUES (146,1,'A'),(147,1,'B'),(148,1,'C'),(149,1,'D'),(150,1,'E'),(151,1,'F'),(152,1,'G'),(153,1,'H');
/*!40000 ALTER TABLE `competition_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `competition_player`
--

DROP TABLE IF EXISTS `competition_player`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `competition_player` (
  `competition_id` bigint(20) NOT NULL,
  `player_id` bigint(20) NOT NULL,
  `seed` tinyint(4) NOT NULL DEFAULT '0',
  `active` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`competition_id`,`player_id`),
  UNIQUE KEY `fk_comp_player` (`competition_id`,`player_id`),
  KEY `fk_competition_idx` (`competition_id`),
  KEY `fk_player_idx` (`player_id`),
  CONSTRAINT `fk_competition` FOREIGN KEY (`competition_id`) REFERENCES `competition` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_player` FOREIGN KEY (`player_id`) REFERENCES `player` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `competition_player`
--

LOCK TABLES `competition_player` WRITE;
/*!40000 ALTER TABLE `competition_player` DISABLE KEYS */;
INSERT INTO `competition_player` VALUES (1,2,1,1),(1,3,1,1),(1,4,1,1),(1,5,1,1),(1,6,1,1),(1,7,1,1),(1,8,1,1),(1,9,1,1),(1,10,0,1),(1,11,0,1),(1,12,0,1),(1,13,0,1),(1,14,0,1),(1,15,0,1),(1,16,0,1),(1,17,0,1),(1,18,0,1),(1,19,0,1),(1,20,0,1),(1,21,0,1),(1,22,0,1),(1,23,0,1),(1,24,0,1),(1,25,0,1),(1,26,0,1),(1,27,0,1),(1,28,0,1),(1,29,0,1),(1,30,0,1),(1,31,0,1),(1,32,0,1),(1,33,0,1);
/*!40000 ALTER TABLE `competition_player` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `competition_properties`
--

DROP TABLE IF EXISTS `competition_properties`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `competition_properties` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `competition_id` bigint(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `value` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_comp_prop_idx` (`competition_id`),
  CONSTRAINT `fk_comp_prop` FOREIGN KEY (`competition_id`) REFERENCES `competition` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `competition_properties`
--

LOCK TABLES `competition_properties` WRITE;
/*!40000 ALTER TABLE `competition_properties` DISABLE KEYS */;
INSERT INTO `competition_properties` VALUES (3,1,'FORMAT','CUP'),(4,1,'NUMBER_OF_SEEDS','8'),(5,1,'NUMBER_OF_PLAYERS','32'),(6,1,'CATEGORY','SENIOR');
/*!40000 ALTER TABLE `competition_properties` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `game_set`
--

DROP TABLE IF EXISTS `game_set`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `game_set` (
  `game_id` bigint(20) NOT NULL,
  `set_no` tinyint(4) NOT NULL,
  `points_home` tinyint(4) NOT NULL DEFAULT '0',
  `points_away` tinyint(4) NOT NULL DEFAULT '0',
  KEY `game_id_idx` (`game_id`),
  CONSTRAINT `game_id` FOREIGN KEY (`game_id`) REFERENCES `games` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `game_set`
--

LOCK TABLES `game_set` WRITE;
/*!40000 ALTER TABLE `game_set` DISABLE KEYS */;
/*!40000 ALTER TABLE `game_set` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `games`
--

DROP TABLE IF EXISTS `games`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `games` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `competition_id` bigint(20) NOT NULL,
  `player_home` bigint(20) NOT NULL,
  `player_away` bigint(20) NOT NULL,
  `points_home` int(3) NOT NULL DEFAULT '0',
  `points_away` int(3) NOT NULL DEFAULT '0',
  `finished` tinyint(4) NOT NULL DEFAULT '0',
  `group_id` int(3) NOT NULL DEFAULT '0',
  `round` int(3) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `competition_idx` (`competition_id`),
  KEY `player_home_idx` (`player_home`),
  KEY `player_away_idx` (`player_away`),
  CONSTRAINT `competition` FOREIGN KEY (`competition_id`) REFERENCES `competition` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `player_away` FOREIGN KEY (`player_away`) REFERENCES `player` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `player_home` FOREIGN KEY (`player_home`) REFERENCES `player` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2161 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `games`
--

LOCK TABLES `games` WRITE;
/*!40000 ALTER TABLE `games` DISABLE KEYS */;
INSERT INTO `games` VALUES (2113,1,2,31,0,0,0,146,1),(2114,1,11,18,0,0,0,146,1),(2115,1,2,18,0,0,0,146,2),(2116,1,31,11,0,0,0,146,2),(2117,1,2,11,0,0,0,146,3),(2118,1,18,31,0,0,0,146,3),(2119,1,3,33,0,0,0,147,1),(2120,1,13,24,0,0,0,147,1),(2121,1,3,24,0,0,0,147,2),(2122,1,33,13,0,0,0,147,2),(2123,1,3,13,0,0,0,147,3),(2124,1,24,33,0,0,0,147,3),(2125,1,4,30,0,0,0,148,1),(2126,1,15,21,0,0,0,148,1),(2127,1,4,21,0,0,0,148,2),(2128,1,30,15,0,0,0,148,2),(2129,1,4,15,0,0,0,148,3),(2130,1,21,30,0,0,0,148,3),(2131,1,5,27,0,0,0,149,1),(2132,1,10,22,0,0,0,149,1),(2133,1,5,22,0,0,0,149,2),(2134,1,27,10,0,0,0,149,2),(2135,1,5,10,0,0,0,149,3),(2136,1,22,27,0,0,0,149,3),(2137,1,6,28,0,0,0,150,1),(2138,1,17,23,0,0,0,150,1),(2139,1,6,23,0,0,0,150,2),(2140,1,28,17,0,0,0,150,2),(2141,1,6,17,0,0,0,150,3),(2142,1,23,28,0,0,0,150,3),(2143,1,7,32,0,0,0,151,1),(2144,1,12,20,0,0,0,151,1),(2145,1,7,20,0,0,0,151,2),(2146,1,32,12,0,0,0,151,2),(2147,1,7,12,0,0,0,151,3),(2148,1,20,32,0,0,0,151,3),(2149,1,8,29,0,0,0,152,1),(2150,1,14,25,0,0,0,152,1),(2151,1,8,25,0,0,0,152,2),(2152,1,29,14,0,0,0,152,2),(2153,1,8,14,0,0,0,152,3),(2154,1,25,29,0,0,0,152,3),(2155,1,9,26,0,0,0,153,1),(2156,1,16,19,0,0,0,153,1),(2157,1,9,19,0,0,0,153,2),(2158,1,26,16,0,0,0,153,2),(2159,1,9,16,0,0,0,153,3),(2160,1,19,26,0,0,0,153,3);
/*!40000 ALTER TABLE `games` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_player`
--

DROP TABLE IF EXISTS `group_player`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group_player` (
  `group_id` bigint(20) NOT NULL,
  `player_id` bigint(20) NOT NULL,
  PRIMARY KEY (`group_id`,`player_id`),
  KEY `player_id_idx` (`player_id`),
  CONSTRAINT `group_id` FOREIGN KEY (`group_id`) REFERENCES `competition_group` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `player_id` FOREIGN KEY (`player_id`) REFERENCES `player` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_player`
--

LOCK TABLES `group_player` WRITE;
/*!40000 ALTER TABLE `group_player` DISABLE KEYS */;
INSERT INTO `group_player` VALUES (146,2),(147,3),(148,4),(149,5),(150,6),(151,7),(152,8),(153,9),(149,10),(146,11),(151,12),(147,13),(152,14),(148,15),(153,16),(150,17),(146,18),(153,19),(151,20),(148,21),(149,22),(150,23),(147,24),(152,25),(153,26),(149,27),(150,28),(152,29),(148,30),(146,31),(151,32),(147,33);
/*!40000 ALTER TABLE `group_player` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `player`
--

DROP TABLE IF EXISTS `player`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `player` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `points` bigint(20) NOT NULL,
  `active` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `player`
--

LOCK TABLES `player` WRITE;
/*!40000 ALTER TABLE `player` DISABLE KEYS */;
INSERT INTO `player` VALUES (2,'Dušan','Trtica',1740,1),(3,'Miloš','Ignjatović',828,1),(4,'Marko','Baračkov',810,1),(5,'Nenad','Raković',645,1),(6,'Vladimir','Canić',628,1),(7,'Miloš','Šolaić',518,1),(8,'Miloš','Dečov',429,1),(9,'Dejan','Hadžiomerović',408,1),(10,'Jovan','Ćušić',305,1),(11,'Stefan','Stanojević',270,1),(12,'Nemanja','Manot',269,1),(13,'Miloš','Vulinović',266,1),(14,'Dragomir','Krstić',265,1),(15,'Goran','Pantić',257,1),(16,'Marko','Zelenbaba',197,1),(17,'Dušan','Nastić',195,1),(18,'Aleksandar','Marin',173,1),(19,'Ilija','Ćeriman',155,1),(20,'Časlav','Šabani',150,1),(21,'Marko','Milenković',147,1),(22,'Nikola','Gladović',139,1),(23,'Ivan','Petrović',136,1),(24,'Danka','Mihajlović',135,1),(25,'Marko','Krsmanović',125,1),(26,'Nikola','Ristivojević',115,1),(27,'Mladen','Stanojević',110,1),(28,'Aleksandar','Jocić',100,1),(29,'Milorad','Čolović',89,1),(30,'Nikola','Rahman',89,1),(31,'Dejan','Ilić',80,1),(32,'Bojan','Božović',75,1),(33,'Branislav','Caran',70,1),(34,'Đorđe','Živković',70,1),(35,'Boško','Pešić',65,1),(36,'Stefan','Bibić',60,1),(37,'Uglješa','Milić',60,1),(38,'Marko','Milošević',50,1),(39,'Miloš','Milutinović',45,1),(40,'Snežana','Toskić',45,1),(41,'Ivan','Nikolić',40,1),(42,'Miloš','P.',40,1),(43,'Bojan','Vujnović',35,1),(44,'Dalibor','Dragišić',35,1),(45,'Gabriela','Mate',35,1),(46,'Jovana','Dakić',35,1),(47,'Miloš','Vukeljić',20,1),(48,'Vladimir','Čurlin',10,1),(49,'Lazar','Nikolić',5,1),(50,'Verica','Petrović',5,1),(51,'Borislav','Meši',0,0),(52,'Predrag','Zečević',0,0),(53,'Vladimir','Stojanović',0,0);
/*!40000 ALTER TABLE `player` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-07-24 16:27:49
