-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: smdb
-- ------------------------------------------------------
-- Server version	8.0.28

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



drop database if exists `smdb`;
create database `smdb`;
use `smdb`;
--
-- Table structure for table `classrooms`
--

DROP TABLE IF EXISTS `classrooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `classrooms` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `NameClass` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classrooms`
--

LOCK TABLES `classrooms` WRITE;
/*!40000 ALTER TABLE `classrooms` DISABLE KEYS */;
INSERT INTO `classrooms` VALUES (1,'k34dl'),(2,'k35dl'),(3,'k34dh'),(4,'k35dh');
/*!40000 ALTER TABLE `classrooms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `classsubject`
--

DROP TABLE IF EXISTS `classsubject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `classsubject` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `ClassId` int DEFAULT NULL,
  `SubjectId` int DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `fk_classroom` (`ClassId`),
  KEY `fk_subject` (`SubjectId`),
  CONSTRAINT `fk_classroom` FOREIGN KEY (`ClassId`) REFERENCES `classrooms` (`Id`) ON DELETE CASCADE,
  CONSTRAINT `fk_subject` FOREIGN KEY (`SubjectId`) REFERENCES `subjects` (`Id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classsubject`
--

LOCK TABLES `classsubject` WRITE;
/*!40000 ALTER TABLE `classsubject` DISABLE KEYS */;
INSERT INTO `classsubject` VALUES (9,1,1),(10,1,2),(11,1,3),(12,1,4),(13,1,5),(14,2,6),(15,2,7),(16,2,8),(17,2,9),(18,3,10),(19,3,11),(20,3,12),(21,3,13),(22,4,10),(23,4,11),(24,4,14);
/*!40000 ALTER TABLE `classsubject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `results`
--

DROP TABLE IF EXISTS `results`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `results` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `ClassSubjectId` int DEFAULT NULL,
  `StudentId` int DEFAULT NULL,
  `NameTest` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Mark` float DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `fk_classSubject` (`ClassSubjectId`),
  KEY `fk_resultStudent` (`StudentId`),
  CONSTRAINT `fk_classSubject` FOREIGN KEY (`ClassSubjectId`) REFERENCES `classsubject` (`Id`) ON DELETE CASCADE,
  CONSTRAINT `fk_resultStudent` FOREIGN KEY (`StudentId`) REFERENCES `students` (`Id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `results`
--

LOCK TABLES `results` WRITE;
/*!40000 ALTER TABLE `results` DISABLE KEYS */;
INSERT INTO `results` VALUES (1,10,1,'bài tập lớn',45),(2,10,2,'bài tập lớn',85),(3,10,3,'bài tập lớn',78),(4,10,4,'bài tập lớn',98),(5,10,5,'bài tập lớn',100),(6,10,6,'bài tập lớn',100),(7,10,7,'bài tập lớn',76),(8,10,8,'bài tập lớn',68),(9,10,9,'bài tập lớn',86),(10,10,10,'bài tập lớn',100),(11,10,11,'bài tập lớn',100);
/*!40000 ALTER TABLE `results` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `students` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `LastName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Gender` bit(1) DEFAULT NULL,
  `Dob` datetime DEFAULT NULL,
  `Phone` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ClassId` int DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students`
--

LOCK TABLES `students` WRITE;
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
INSERT INTO `students` VALUES (1,'Kiều Trí ','Lăng',_binary '','2002-06-16 00:00:00','0987654322','Hà Nội',1),(2,'Khuất Tuấn ','Anh',_binary '','2002-09-05 00:00:00','0987654323','Hà Nội',1),(3,'Lê Đăng','Bình',_binary '','2002-12-18 00:00:00','0987654321','Hà Nội',1),(4,'Nguyễn Thị','Bích',_binary '\0','2002-02-21 00:00:00','0987654321','Hà Nội',1),(5,'Vũ Tuấn','Kiệt',_binary '','2002-11-21 00:00:00','0987654321','Hà Nội',1),(6,'Nguyễn Văn','Duy',_binary '','2002-09-27 00:00:00','0987654321','Hà Nội',1),(7,'La Thị','Hường',_binary '\0','2002-06-01 00:00:00','0987654321','Hà Nội',1),(8,'Nguyễn Mai','Linh',_binary '\0','2002-09-06 00:00:00','0987654321','Hà Nội',1),(9,'Đào Hồng','Nhung',_binary '\0','2002-11-21 00:00:00','0987654321','Hà Nội',1),(10,'Phạm Quang','Linh',_binary '','2002-01-07 00:00:00','0987654321','Hà Nội',1),(11,'Hoàng Ngọc','Sơn',_binary '','2002-06-05 00:00:00','0987654321','Hà Nội',1),(12,'Hoàng Nhật','Ánh',_binary '\0','2003-02-14 00:00:00','0987654123','Hà Nội',4);
/*!40000 ALTER TABLE `students` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subjects`
--

DROP TABLE IF EXISTS `subjects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subjects` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `NameSubject` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subjects`
--

LOCK TABLES `subjects` WRITE;
/*!40000 ALTER TABLE `subjects` DISABLE KEYS */;
INSERT INTO `subjects` VALUES (1,'asp .net'),(2,'php'),(3,'java'),(4,'android'),(5,'python'),(6,'django'),(7,'ruby on rails'),(8,'laravel'),(9,'spring boot'),(10,'photoshop basics'),(11,'photoshop advanced'),(12,'web design'),(13,'UI/UX basics'),(14,'blender');
/*!40000 ALTER TABLE `subjects` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-06  8:09:07
