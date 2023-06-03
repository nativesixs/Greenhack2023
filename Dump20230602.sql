CREATE DATABASE  IF NOT EXISTS `firstfloor` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `firstfloor`;
-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: firstfloor
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `javatable`
--

DROP TABLE IF EXISTS `javatable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `javatable` (
  `sk` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `javatable`
--

LOCK TABLES `javatable` WRITE;
/*!40000 ALTER TABLE `javatable` DISABLE KEYS */;
INSERT INTO `javatable` VALUES ('112023-06-12'),('112023-06-13'),('112023-06-14'),('112023-06-15'),('112023-06-16'),('112023-06-17'),('112023-06-18'),('112023-06-19'),('112023-06-03'),('112023-06-04'),('112023-06-05'),('112023-06-06'),('112023-06-07'),('112023-06-08'),('112023-06-09'),('112023-06-10'),('112023-06-11');
/*!40000 ALTER TABLE `javatable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `master_table`
--

DROP TABLE IF EXISTS `master_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `master_table` (
  `id_chair` int NOT NULL,
  `monitors_n` int DEFAULT NULL,
  `side_direction` varchar(45) DEFAULT NULL,
  `temperature` int DEFAULT NULL,
  PRIMARY KEY (`id_chair`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `master_table`
--

LOCK TABLES `master_table` WRITE;
/*!40000 ALTER TABLE `master_table` DISABLE KEYS */;
INSERT INTO `master_table` VALUES (1,2,'WEST',20),(2,0,'EAST',22);
/*!40000 ALTER TABLE `master_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seating`
--

DROP TABLE IF EXISTS `seating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seating` (
  `id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seating`
--

LOCK TABLES `seating` WRITE;
/*!40000 ALTER TABLE `seating` DISABLE KEYS */;
INSERT INTO `seating` VALUES (191),(192),(193);
/*!40000 ALTER TABLE `seating` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status_table`
--

DROP TABLE IF EXISTS `status_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `status_table` (
  `id` int NOT NULL,
  `status` varchar(45) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `sk` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='g';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status_table`
--

LOCK TABLES `status_table` WRITE;
/*!40000 ALTER TABLE `status_table` DISABLE KEYS */;
INSERT INTO `status_table` VALUES (1,'BUSY','2023-07-22',NULL),(2,'BUSY','2023-06-03',NULL),(3,'BUSY','2023-06-03',NULL),(4,'BUSY','2023-06-03',NULL),(6,'BUSY','2023-06-03',NULL),(7,'BUSY','2023-06-03',NULL),(12,'BUSY','2023-06-03',NULL),(8,'BUSY','2023-06-03',NULL),(11,'BUSY','2023-06-03',NULL);
/*!40000 ALTER TABLE `status_table` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-03 12:27:29
