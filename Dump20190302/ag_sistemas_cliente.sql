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
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cliente` (
  `n_Cuenta` varchar(10) NOT NULL,
  `nombre` varchar(20) DEFAULT NULL,
  `apellido` varchar(45) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `caracteristica_Celular` int(4) DEFAULT NULL,
  `numero_Celular` int(9) DEFAULT NULL,
  `caracteristica_Fijo` int(4) DEFAULT NULL,
  `numero_Fijo` int(15) DEFAULT NULL,
  `persona_Usuario` varchar(10) NOT NULL,
  PRIMARY KEY (`n_Cuenta`),
  KEY `fk_cliente_persona1_idx1` (`persona_Usuario`),
  CONSTRAINT `fk_cliente_persona1` FOREIGN KEY (`persona_Usuario`) REFERENCES `persona` (`Usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES ('1-PAN','Ariel Alejando','Gatti ','arielgatti@hotmail.com',351,155208478,351,4565426,'admin'),('2-MON','Jose','Cabrera','joseangelcabrera@gmail.com',0,0,351,0,'admin'),('3-MON','Pablo','Gatti','pabloadrielgatti2@gmail.com',351,153119148,351,843191,'admin'),('4-MON','','Cabrera','pgatti114@alumnos.iua.edu.ar',0,0,0,0,'admin');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-02 13:21:36
