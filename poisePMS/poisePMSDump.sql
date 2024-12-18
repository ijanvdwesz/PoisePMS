-- MySQL dump 10.13  Distrib 8.4.1, for Win64 (x86_64)
--
-- Host: localhost    Database: poisepms
-- ------------------------------------------------------
-- Server version	8.4.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `architects`
--

DROP TABLE IF EXISTS `architects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `architects` (
  `ArchitectID` int NOT NULL AUTO_INCREMENT,
  `Firstname` varchar(200) DEFAULT NULL,
  `Surname` varchar(300) DEFAULT NULL,
  `Telephone` varchar(20) DEFAULT NULL,
  `Email` varchar(250) DEFAULT NULL,
  `PhysicalAddress` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ArchitectID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `architects`
--

LOCK TABLES `architects` WRITE;
/*!40000 ALTER TABLE `architects` DISABLE KEYS */;
INSERT INTO `architects` VALUES (-1,'To be assigned','N/A','N/A','N/A','N/A'),(4,'Koos','Van der Merwe','0834178340','kvandmerwe@gmail.com','66 Swempie Str Thabazimbi'),(5,'Jannie','Stone','0648341833','StoneJ@gmail.com','45 Mandela Street Thabazimbi'),(6,'Justice','Mngceba','0796993403','MngcebaJustice99@gmail.com','43 Krombek Ave Mngcibe'),(8,'Jasmine','Smith','0798335302','jasminesmith44@gmail.com','77 Kerk str Jamestown'),(9,'Alicia','Smith','0998735260','AliceSmith66@gmail.com','41 Steve Biko str Thabazimbi'),(11,'Dawie','Venter','079923389','D.Venter@DVdesigns.com','90Fanie str Ventersdorp'),(12,'Justin','Virgin','092246652','JV.99@gmail.com','99 Green Str Sophiatown');
/*!40000 ALTER TABLE `architects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contractors`
--

DROP TABLE IF EXISTS `contractors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contractors` (
  `ContractorID` int NOT NULL AUTO_INCREMENT,
  `Firstname` varchar(200) DEFAULT NULL,
  `Surname` varchar(300) DEFAULT NULL,
  `Telephone` varchar(20) DEFAULT NULL,
  `Email` varchar(250) DEFAULT NULL,
  `PhysicalAddress` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ContractorID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contractors`
--

LOCK TABLES `contractors` WRITE;
/*!40000 ALTER TABLE `contractors` DISABLE KEYS */;
INSERT INTO `contractors` VALUES (-1,'To be assigned','N/A','N/A','N/A','N/A'),(3,'Abraham','Nelson','0675416698','abnelson@gmail.com','43 Krummel Str Northam'),(5,'Zelda ','Van Graan','0792245300','zvgraan69@gmail.com','99 Hammerkop Str Thabazimbi'),(6,'Madiba ','Makgae','0796335305','mmconstruct@gmail.com','67 4th ave Randburg'),(7,'Jenice','Peppers','0789935431','peppyj@outlook.com','24 6th avenue Northam'),(8,'Jyang','Lee','0667891010','Jyanglee@gmail.com','1 kontrak street'),(10,'Jimmy','Cronje','0756112561','jimsemail@gmail.com','55 Bok Str Thabazimbi');
/*!40000 ALTER TABLE `contractors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
  `CustomerID` int NOT NULL AUTO_INCREMENT,
  `Firstname` varchar(200) DEFAULT NULL,
  `Surname` varchar(300) DEFAULT NULL,
  `Telephone` varchar(20) DEFAULT NULL,
  `Email` varchar(250) DEFAULT NULL,
  `PhysicalAddress` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`CustomerID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (-1,'To be assigned','N/A','N/A','N/A','N/A'),(4,'Asalha Kuhn','cornerStore','99059906997','MyFriend@punyab.com','merensky 55'),(5,'HoodRat PSg','sorry','675849301928','yoyoyomail@gmail.com','JackBabzStreet48'),(6,'Jack Stevens','Yes','098980809809','toosalty@gmail.com','beach street 22 '),(7,'jovijj','no','098909890','jovi@mymail.com','24herestr'),(8,'naampie','Idk','0909090909090','jj@gmail.com','joustraat56'),(9,'yjan','Repeat','89898989','yjan@gmail.com','mystraat55'),(10,'cusom123','jackson','09890','mailme@jack.co.za','address123'),(11,'name1','jackson','098909880','name@email.com','piesangstraat5'),(13,'Cassie','Strongs','0999000','Strongs@gmail.com','Address22');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `projects`
--

DROP TABLE IF EXISTS `projects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `projects` (
  `ProjectID` int NOT NULL AUTO_INCREMENT,
  `ProjectName` varchar(80) NOT NULL,
  `BuildingType` varchar(30) NOT NULL,
  `ERFNumber` varchar(50) DEFAULT NULL,
  `TotalFee` decimal(20,2) NOT NULL,
  `TotalPaid` decimal(20,2) NOT NULL,
  `Deadline` date NOT NULL,
  `ArchitectID` int DEFAULT NULL,
  `ContractorID` int DEFAULT NULL,
  `CustomerID` int DEFAULT NULL,
  `Finalized` tinyint(1) DEFAULT '0',
  `CompletionDate` date DEFAULT NULL,
  PRIMARY KEY (`ProjectID`),
  KEY `fk_ArchitectID` (`ArchitectID`),
  KEY `fk_customer` (`CustomerID`),
  KEY `fk_contractor` (`ContractorID`),
  CONSTRAINT `fk_architect` FOREIGN KEY (`ArchitectID`) REFERENCES `architects` (`ArchitectID`) ON DELETE SET NULL,
  CONSTRAINT `fk_ArchitectID` FOREIGN KEY (`ArchitectID`) REFERENCES `architects` (`ArchitectID`) ON DELETE SET NULL,
  CONSTRAINT `fk_contractor` FOREIGN KEY (`ContractorID`) REFERENCES `contractors` (`ContractorID`) ON DELETE SET NULL,
  CONSTRAINT `fk_customer` FOREIGN KEY (`CustomerID`) REFERENCES `customers` (`CustomerID`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projects`
--

LOCK TABLES `projects` WRITE;
/*!40000 ALTER TABLE `projects` DISABLE KEYS */;
INSERT INTO `projects` VALUES (6,'Kauffman Ranch','Ranch','566',780000.00,780000.00,'2025-01-01',5,5,5,0,NULL),(7,'Kriel Shelter','5','9909',22000.00,11.00,'1997-01-25',11,10,11,0,'2001-01-01'),(10,'Mzanzi Castle','Castle','778',9000000.00,3000000.00,'2028-01-01',8,8,8,0,NULL),(11,'Jesse Office','Office','445',3000000.00,1000000.00,'2025-01-01',8,8,8,0,NULL);
/*!40000 ALTER TABLE `projects` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-09-18 16:03:26
