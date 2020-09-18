-- MySQL dump 10.13  Distrib 8.0.21, for Linux (x86_64)
--
-- Host: localhost    Database: chatapp
-- ------------------------------------------------------
-- Server version	8.0.21-0ubuntu0.20.04.4

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
-- Table structure for table `Chat`
--
USE `chatapp` ;
DROP TABLE IF EXISTS `Chat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Chat` (
  `ChatID` int NOT NULL AUTO_INCREMENT,
  `ConversationID` int DEFAULT NULL,
  `Mode` int NOT NULL,
  `UserSendID` int NOT NULL,
  `UserReceiveID` int NOT NULL,
  `Content` varchar(640) CHARACTER SET latin1 DEFAULT NULL,
  `SentTime` bigint DEFAULT NULL,
  PRIMARY KEY (`ChatID`),
  KEY `fk_Chat_2_idx` (`UserSendID`),
  KEY `fk_Chat_3_idx` (`UserReceiveID`),
  CONSTRAINT `fk_Chat_2` FOREIGN KEY (`UserSendID`) REFERENCES `User` (`UserID`),
  CONSTRAINT `fk_Chat_3` FOREIGN KEY (`UserReceiveID`) REFERENCES `User` (`UserID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf16;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Chat`
--

LOCK TABLES `Chat` WRITE;
/*!40000 ALTER TABLE `Chat` DISABLE KEYS */;
INSERT INTO `Chat` VALUES (3,NULL,1,26,34,'Hello, how are you?',1599550850),(4,NULL,1,34,26,'Im good, how are you?',1599550908),(5,NULL,1,26,57,'Hello 57',1599559639),(6,NULL,1,57,26,'Hello 26',1599559771);
/*!40000 ALTER TABLE `Chat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Conversation`
--

DROP TABLE IF EXISTS `Conversation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Conversation` (
  `ConversationID` int NOT NULL AUTO_INCREMENT,
  `UserStartID` int NOT NULL,
  `Mode` int NOT NULL,
  `Name` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`ConversationID`),
  KEY `fk_Conversation_1_idx` (`UserStartID`),
  CONSTRAINT `fk_Conversation_1` FOREIGN KEY (`UserStartID`) REFERENCES `User` (`UserID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf16;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Conversation`
--

LOCK TABLES `Conversation` WRITE;
/*!40000 ALTER TABLE `Conversation` DISABLE KEYS */;
/*!40000 ALTER TABLE `Conversation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ConversationMember`
--

DROP TABLE IF EXISTS `ConversationMember`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ConversationMember` (
  `ConversationMemberID` int NOT NULL AUTO_INCREMENT,
  `ConversationID` int NOT NULL,
  `MemberID` int NOT NULL,
  `NotSeenChat` int DEFAULT NULL,
  PRIMARY KEY (`ConversationMemberID`),
  KEY `fk_ConversationMember_1_idx` (`ConversationID`),
  KEY `fk_ConversationMember_2_idx` (`MemberID`),
  CONSTRAINT `fk_ConversationMember_1` FOREIGN KEY (`ConversationID`) REFERENCES `Conversation` (`ConversationID`),
  CONSTRAINT `fk_ConversationMember_2` FOREIGN KEY (`MemberID`) REFERENCES `User` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ConversationMember`
--

LOCK TABLES `ConversationMember` WRITE;
/*!40000 ALTER TABLE `ConversationMember` DISABLE KEYS */;
/*!40000 ALTER TABLE `ConversationMember` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Friend`
--

DROP TABLE IF EXISTS `Friend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Friend` (
  `FriendID` int NOT NULL AUTO_INCREMENT,
  `UserYouID` int NOT NULL,
  `UserMeID` int NOT NULL,
  PRIMARY KEY (`FriendID`),
  KEY `fk_Friend_1_idx` (`UserYouID`),
  KEY `fk_Friend_2_idx` (`UserMeID`),
  CONSTRAINT `fk_Friend_1` FOREIGN KEY (`UserYouID`) REFERENCES `User` (`UserID`),
  CONSTRAINT `fk_Friend_2` FOREIGN KEY (`UserMeID`) REFERENCES `User` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Friend`
--

LOCK TABLES `Friend` WRITE;
/*!40000 ALTER TABLE `Friend` DISABLE KEYS */;
/*!40000 ALTER TABLE `Friend` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FriendRequest`
--

DROP TABLE IF EXISTS `FriendRequest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `FriendRequest` (
  `FriendRequestID` int NOT NULL AUTO_INCREMENT,
  `UserSendID` int NOT NULL,
  `UserReplyID` int NOT NULL,
  `Time` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`FriendRequestID`),
  KEY `fk_FriendRequest_1_idx` (`UserSendID`),
  KEY `fk_FriendRequest_2_idx` (`UserReplyID`),
  CONSTRAINT `fk_FriendRequest_1` FOREIGN KEY (`UserSendID`) REFERENCES `User` (`UserID`),
  CONSTRAINT `fk_FriendRequest_2` FOREIGN KEY (`UserReplyID`) REFERENCES `User` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FriendRequest`
--

LOCK TABLES `FriendRequest` WRITE;
/*!40000 ALTER TABLE `FriendRequest` DISABLE KEYS */;
/*!40000 ALTER TABLE `FriendRequest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `User` (
  `UserID` int NOT NULL AUTO_INCREMENT,
  `Username` varchar(30) CHARACTER SET latin1 NOT NULL,
  `Fullname` varchar(45) CHARACTER SET latin1 NOT NULL,
  `Password` varchar(160) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `Online` bit(1) DEFAULT NULL,
  `AvatarFilePath` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `BirthDay` date DEFAULT NULL,
  `Sex` bit(1) DEFAULT NULL,
  PRIMARY KEY (`UserID`),
  UNIQUE KEY `Username_UNIQUE` (`Username`),
  KEY `index3` (`Fullname`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf16 COLLATE=utf16_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (26,'minhduc','Minh Duc','$2a$04$tgk0wDjnALrrrImWPyzMBunvpJgJXrOSOg895q.RKp0yWeOe6qiUW',NULL,NULL,NULL,NULL),(28,'doraemon','Doraemon','$2a$04$DKUZEMnEV9Y5esbXGZEmUutKAiFuji.6G8PxK3YjPk0IdeGMjEe8O',NULL,NULL,NULL,NULL),(34,'xeko','Xeko','$2a$04$nnb9N5BeW1S2vhuU6Be9COUs03AwibPMu9Xj/CqjKByGdlJTdtvUG',NULL,NULL,NULL,NULL),(49,'nobita','Nobita','$2a$04$Y3fgY5EVOv/7c0jPbP6kf.pFh8hN.qTCuDkppAKiQD1EnAE2CFSd.',NULL,NULL,NULL,NULL),(50,'shizuka','Shizuka','$2a$04$L7PiZbYws8OxCCcPcV2ywOaZ5gV/sUp5N.gt4yrxP2aggu8IRXnQW',NULL,NULL,NULL,NULL),(54,'chaien','chaien','$2a$04$0sNxBicVV9wdEGEFsJcBpeDAHTp1eqbfZNOSC8afTuxU.v34VWCxe',NULL,NULL,NULL,NULL),(55,'thieuvt','thieuvt','$2a$04$GcBhOst6tHPKjfJSNDue4e1fb7AnggIBxDUq.hlEC9MwdODTjoIWy',NULL,NULL,NULL,NULL),(56,'thieu','123','$2a$04$u8LIk0a84cm1fztw1X8.0ONH5vqTiWaEw6hMKOkNSWJ.nC0QDxDHK',NULL,NULL,NULL,NULL),(57,'asd','asd','$2a$04$tVbcD3r/KwwGevKnlBerDebGLRIHFeLRlLXPzAcvaqSYOnrvr4eQu',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-09-11 13:25:41
