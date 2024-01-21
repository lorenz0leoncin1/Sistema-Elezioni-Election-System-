-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: sistema_elettorale_cl
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `votazione`
--

DROP TABLE IF EXISTS `votazione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `votazione` (
  `id_votazione` int NOT NULL AUTO_INCREMENT,
  `tipo_voto` varchar(45) NOT NULL,
  `mod_vittoria` varchar(45) NOT NULL,
  `stato` tinyint NOT NULL,
  `vincitore` varchar(45) DEFAULT NULL,
  `admin` varchar(45) NOT NULL,
  `titolo_votazione` varchar(45) NOT NULL,
  PRIMARY KEY (`id_votazione`),
  UNIQUE KEY `titolo_votazione_UNIQUE` (`titolo_votazione`),
  KEY `admin_idx` (`admin`),
  CONSTRAINT `admin` FOREIGN KEY (`admin`) REFERENCES `amministratore` (`UsernameAdmin`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `votazione`
--

LOCK TABLES `votazione` WRITE;
/*!40000 ALTER TABLE `votazione` DISABLE KEYS */;
INSERT INTO `votazione` VALUES (1,'Voto Ordinale','Maggioranza',1,'Silvio Berlusconi','lorenzoleoncini@gmail.com','prova1'),(6,'Voto Categorico','Maggioranza Assoluta',1,'Giorgia Meloni','1','MaggioranzaAssolutaProva1'),(7,'Referendum','Referendum Senza Quorum',0,'NO','1','ProvaReferendumSQ1'),(8,'Referendum','Referendum Senza Quorum',1,'SI','1','ProvaReferendumSQ2'),(9,'Referendum','Referendum con Quorum',1,'numero minimo di votanti non raggiunto','1','ReferendumCQ1'),(10,'Referendum','Referendum con Quorum',1,'SI','1','ReferendumCQ2'),(16,'Voto Ordinale','Maggioranza',1,'','1','dasdasdas'),(17,'Voto Categorico','Maggioranza',1,'','1','awrdasfasfas'),(18,'Referendum','Referendum con Quorum',1,'','1','test'),(19,'Voto Ordinale','Maggioranza',0,'Matteo Renzi','1','testOrdinale'),(20,'Referendum','Referendum con Quorum',0,'numero minimo di votanti non raggiunto','1','TestCQ1'),(21,'Voto Categorico Con Preferenze','Maggioranza Assoluta',1,'','1','asvsdvsdvsdavsadvasvas');
/*!40000 ALTER TABLE `votazione` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-31 23:51:15
