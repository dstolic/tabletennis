CREATE DATABASE  IF NOT EXISTS `tabletennis` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `tabletennis`;
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
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `player`
--

LOCK TABLES `player` WRITE;
/*!40000 ALTER TABLE `player` DISABLE KEYS */;
INSERT INTO `player` VALUES (2,'Dušan','Trtica',1740,1),(3,'Miloš','Ignjatović',828,1),(4,'Marko','Baračkov',810,1),(5,'Nenad','Raković',645,1),(6,'Vladimir','Canić',628,1),(7,'Miloš','Šolaić',518,1),(8,'Miloš','Dečov',429,1),(9,'Dejan','Hadžiomerović',408,1),(10,'Jovan','Ćušić',305,1),(11,'Stefan','Stanojević',270,1),(12,'Nemanja','Manot',269,1),(13,'Miloš','Vulinović',266,1),(14,'Dragomir','Krstić',265,1),(15,'Goran','Pantić',257,1),(16,'Marko','Zelenbaba',197,1),(17,'Dušan','Nastić',195,1),(18,'Aleksandar','Marin',173,1),(19,'Ilija','Ćeriman',155,1),(20,'Časlav','Šabani',150,1),(21,'Marko','Milenković',147,1),(22,'Nikola','Gladović',139,1),(23,'Ivan','Petrović',136,1),(24,'Danka','Mihajlović',135,1),(25,'Marko','Krsmanović',125,1),(26,'Nikola','Ristivojević',115,1),(27,'Mladen','Stanojević',110,1),(28,'Aleksandar','Jocić',100,1),(29,'Milorad','Čolović',89,1),(30,'Nikola','Rahman',89,1),(31,'Dejan','Ilić',80,1),(32,'Bojan','Božović',75,1),(33,'Branislav','Caran',70,1),(34,'Đorđe','Živković',70,1),(35,'Boško','Pešić',65,1),(36,'Stefan','Bibić',60,1),(37,'Uglješa','Milić',60,1),(38,'Marko','Milošević',50,1),(39,'Miloš','Milutinović',45,1),(40,'Snežana','Toskić',45,1),(41,'Ivan','Nikolić',40,1),(42,'Miloš','P.',40,1),(43,'Bojan','Vujnović',35,1),(44,'Dalibor','Dragišić',35,1),(45,'Gabriela','Mate',35,1),(46,'Jovana','Dakić',35,1),(47,'Miloš','Vukeljić',20,1),(48,'Vladimir','Čurlin',10,1),(49,'Lazar','Nikolić',5,1),(50,'Verica','Petrović',5,1),(51,'Borislav','Meši',0,1),(52,'Predrag','Zečević',0,1),(53,'Vladimir','Stojanović',0,1);
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

-- Dump completed on 2018-05-16 11:38:45
