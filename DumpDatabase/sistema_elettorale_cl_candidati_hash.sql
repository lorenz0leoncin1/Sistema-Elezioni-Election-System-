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
-- Table structure for table `candidati_hash`
--

DROP TABLE IF EXISTS `candidati_hash`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `candidati_hash` (
  `votazione` int NOT NULL,
  `candidato` varchar(50) NOT NULL,
  `numeroVoti` int NOT NULL,
  PRIMARY KEY (`votazione`,`candidato`),
  KEY `candidato_idx` (`candidato`),
  CONSTRAINT `candidato` FOREIGN KEY (`candidato`) REFERENCES `candidati` (`nome_candidato`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `votazione` FOREIGN KEY (`votazione`) REFERENCES `votazione` (`id_votazione`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `candidati_hash`
--

LOCK TABLES `candidati_hash` WRITE;
/*!40000 ALTER TABLE `candidati_hash` DISABLE KEYS */;
INSERT INTO `candidati_hash` VALUES (1,'Giorgia Meloni',50),(1,'Giuseppe Conte',50003),(1,'Silvio Berlusconi',1509999),(6,'Enrico Letta',15),(6,'Giorgia Meloni',53),(6,'Giuseppe Conte',16),(6,'Silvio Berlusconi',18),(7,'0Referendum = NO',3),(7,'0Referendum = SI',2),(8,'0Referendum = NO',15),(8,'0Referendum = SI',32),(9,'0Referendum = NO',15),(9,'0Referendum = SI',31),(10,'0Referendum = NO',20000000),(10,'0Referendum = SI',35000000),(16,'Giorgia Meloni',0),(16,'Matteo Salvini',0),(16,'Silvio Berlusconi',0),(17,'1partito=Fratelli d\'Italia',0),(17,'1partito=Italia Viva ',0),(17,'1partito=Lega',0),(18,'0Referendum = NO',0),(18,'0Referendum = SI',1),(19,'Giuseppe Conte',2),(19,'Luigi Brugnaro',1),(19,'Matteo Renzi',3),(20,'0Referendum = NO',2),(20,'0Referendum = SI',2),(21,'1partito=Italia Viva ',0),(21,'1partito=Lega',7),(21,'Matteo Renzi',0),(21,'Matteo Salvini',2);
/*!40000 ALTER TABLE `candidati_hash` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-31 23:51:16
