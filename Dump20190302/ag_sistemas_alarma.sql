-- MySQL dump 10.13  Distrib 8.0.14, for Win64 (x86_64)
--
-- Host: localhost    Database: ag_sistemas
-- ------------------------------------------------------
-- Server version	8.0.14

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `alarma`
--

DROP TABLE IF EXISTS `alarma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `alarma` (
  `n_Serie` varchar(11) NOT NULL,
  `marca_Modelo` varchar(10) DEFAULT NULL,
  `cant_Zonas` int(11) DEFAULT NULL,
  `marca_Modelo_backup` varchar(20) DEFAULT NULL,
  `empresa_Monitoreo` varchar(45) DEFAULT NULL,
  `fecha_Bateria` date DEFAULT NULL,
  `fecha_Preventivo` date DEFAULT NULL,
  `fecha_Instalacion` varchar(45) DEFAULT NULL,
  `nota` varchar(45) DEFAULT NULL,
  `casa_Direccion` varchar(45) NOT NULL,
  PRIMARY KEY (`n_Serie`),
  KEY `fk_alarma_casa1_idx` (`casa_Direccion`),
  CONSTRAINT `fk_alarma_casa1` FOREIGN KEY (`casa_Direccion`) REFERENCES `casa` (`direccion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alarma`
--

LOCK TABLES `alarma` WRITE;
/*!40000 ALTER TABLE `alarma` DISABLE KEYS */;
INSERT INTO `alarma` VALUES ('DCS7000','',0,'','','2019-02-19','2019-02-19','2019-02-19','','F.Miranda'),('DSC7000','',0,'','','2020-01-03','2019-02-12','2019-02-12','','F.Zelada '),('DSC7000-1','DSC7000',4,'NO','MONITORA','2019-02-13','2019-02-13','2019-02-13','','Evacio Garrone '),('DSC7002','',0,'','','2019-02-18','2019-02-19','2019-02-19','','F.Miranda'),('dsc7575','dsc',3,'','monitora','2020-08-20','1999-12-10','1999-12-10','','Garrone'),('SP7000','SP70001',4,'NO','PANCER','2019-02-13','2019-02-12','2019-02-12','QUINCHO','F.Miranda'),('sp7575','sp7575',0,'','','2019-02-19','2019-02-19','2019-02-19','','publica2');
/*!40000 ALTER TABLE `alarma` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-02 13:21:35
