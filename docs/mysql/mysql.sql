-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema chatapp
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `chatapp` ;

-- -----------------------------------------------------
-- Schema chatapp
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `chatapp` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `chatapp` ;

-- -----------------------------------------------------
-- Table `chatapp`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chatapp`.`User` (
  `UserID` INT NOT NULL AUTO_INCREMENT,
  `Username` VARCHAR(30) CHARACTER SET 'latin1' NOT NULL,
  `Fullname` VARCHAR(45) CHARACTER SET 'latin1' NOT NULL,
  `Password` VARCHAR(160) CHARACTER SET 'latin1' NOT NULL,
  `Online` BIT(1) NULL DEFAULT NULL,
  `AvatarFilePath` VARCHAR(45) CHARACTER SET 'latin1' NULL DEFAULT NULL,
  `BirthDay` DATE NULL DEFAULT NULL,
  `Sex` BIT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`UserID`),
  UNIQUE INDEX `Username_UNIQUE` (`Username` ASC) VISIBLE,
  INDEX `index3` (`Fullname` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 59
DEFAULT CHARACTER SET = utf16
COLLATE = utf16_unicode_ci;


-- -----------------------------------------------------
-- Table `chatapp`.`Chat`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chatapp`.`Chat` (
  `ChatID` INT NOT NULL AUTO_INCREMENT,
  `ConversationID` INT NULL DEFAULT NULL,
  `Mode` INT NOT NULL,
  `UserSendID` INT NOT NULL,
  `UserReceiveID` INT NOT NULL,
  `Content` VARCHAR(640) CHARACTER SET 'latin1' NULL DEFAULT NULL,
  `SentTime` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`ChatID`),
  INDEX `fk_Chat_2_idx` (`UserSendID` ASC) VISIBLE,
  INDEX `fk_Chat_3_idx` (`UserReceiveID` ASC) VISIBLE,
  CONSTRAINT `fk_Chat_2`
    FOREIGN KEY (`UserSendID`)
    REFERENCES `chatapp`.`User` (`UserID`),
  CONSTRAINT `fk_Chat_3`
    FOREIGN KEY (`UserReceiveID`)
    REFERENCES `chatapp`.`User` (`UserID`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf16;


-- -----------------------------------------------------
-- Table `chatapp`.`Conversation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chatapp`.`Conversation` (
  `ConversationID` INT NOT NULL AUTO_INCREMENT,
  `UserStartID` INT NOT NULL,
  `Mode` INT NOT NULL,
  `Name` VARCHAR(45) CHARACTER SET 'latin1' NULL DEFAULT NULL,
  PRIMARY KEY (`ConversationID`),
  INDEX `fk_Conversation_1_idx` (`UserStartID` ASC) VISIBLE,
  CONSTRAINT `fk_Conversation_1`
    FOREIGN KEY (`UserStartID`)
    REFERENCES `chatapp`.`User` (`UserID`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf16;


-- -----------------------------------------------------
-- Table `chatapp`.`ConversationMember`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chatapp`.`ConversationMember` (
  `ConversationMemberID` INT NOT NULL AUTO_INCREMENT,
  `ConversationID` INT NOT NULL,
  `MemberID` INT NOT NULL,
  `NotSeenChat` INT NULL DEFAULT NULL,
  PRIMARY KEY (`ConversationMemberID`),
  INDEX `fk_ConversationMember_1_idx` (`ConversationID` ASC) VISIBLE,
  INDEX `fk_ConversationMember_2_idx` (`MemberID` ASC) VISIBLE,
  CONSTRAINT `fk_ConversationMember_1`
    FOREIGN KEY (`ConversationID`)
    REFERENCES `chatapp`.`Conversation` (`ConversationID`),
  CONSTRAINT `fk_ConversationMember_2`
    FOREIGN KEY (`MemberID`)
    REFERENCES `chatapp`.`User` (`UserID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `chatapp`.`Friend`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chatapp`.`Friend` (
  `FriendID` INT NOT NULL AUTO_INCREMENT,
  `UserYouID` INT NOT NULL,
  `UserMeID` INT NOT NULL,
  PRIMARY KEY (`FriendID`),
  INDEX `fk_Friend_1_idx` (`UserYouID` ASC) VISIBLE,
  INDEX `fk_Friend_2_idx` (`UserMeID` ASC) VISIBLE,
  CONSTRAINT `fk_Friend_1`
    FOREIGN KEY (`UserYouID`)
    REFERENCES `chatapp`.`User` (`UserID`),
  CONSTRAINT `fk_Friend_2`
    FOREIGN KEY (`UserMeID`)
    REFERENCES `chatapp`.`User` (`UserID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `chatapp`.`FriendRequest`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chatapp`.`FriendRequest` (
  `FriendRequestID` INT NOT NULL AUTO_INCREMENT,
  `UserSendID` INT NOT NULL,
  `UserReplyID` INT NOT NULL,
  `Time` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`FriendRequestID`),
  INDEX `fk_FriendRequest_1_idx` (`UserSendID` ASC) VISIBLE,
  INDEX `fk_FriendRequest_2_idx` (`UserReplyID` ASC) VISIBLE,
  CONSTRAINT `fk_FriendRequest_1`
    FOREIGN KEY (`UserSendID`)
    REFERENCES `chatapp`.`User` (`UserID`),
  CONSTRAINT `fk_FriendRequest_2`
    FOREIGN KEY (`UserReplyID`)
    REFERENCES `chatapp`.`User` (`UserID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
