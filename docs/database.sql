SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS `openhaus` ;
CREATE SCHEMA IF NOT EXISTS `openhaus` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE `openhaus` ;

-- -----------------------------------------------------
-- Table `openhaus`.`Account`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `openhaus`.`Account` ;

CREATE  TABLE IF NOT EXISTS `openhaus`.`Account` (
  `Username` VARCHAR(100) NOT NULL ,
  `Password` VARCHAR(100) NOT NULL ,
  PRIMARY KEY (`Username`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `openhaus`.`Topic`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `openhaus`.`Topic` ;

CREATE  TABLE IF NOT EXISTS `openhaus`.`Topic` (
  `ID` INT NOT NULL AUTO_INCREMENT ,
  `Title` VARCHAR(200) NOT NULL ,
  PRIMARY KEY (`ID`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `openhaus`.`Thread`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `openhaus`.`Thread` ;

CREATE  TABLE IF NOT EXISTS `openhaus`.`Thread` (
  `ID` INT NOT NULL AUTO_INCREMENT ,
  `Title` VARCHAR(200) NOT NULL ,
  `Date` DATE NOT NULL ,
  `Views` INT NOT NULL ,
  `Open` TINYINT(1)  NOT NULL ,
  `Sticky` TINYINT(1)  NOT NULL ,
  `Username` VARCHAR(100) NOT NULL ,
  `Topic_ID` INT NOT NULL ,
  PRIMARY KEY (`ID`) ,
  INDEX `fk_Thread_Account1` (`Username` ASC) ,
  INDEX `fk_Thread_Topic1` (`Topic_ID` ASC) ,
  CONSTRAINT `fk_Thread_Account1`
    FOREIGN KEY (`Username` )
    REFERENCES `openhaus`.`Account` (`Username` )
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Thread_Topic1`
    FOREIGN KEY (`Topic_ID` )
    REFERENCES `openhaus`.`Topic` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `openhaus`.`Post`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `openhaus`.`Post` ;

CREATE  TABLE IF NOT EXISTS `openhaus`.`Post` (
  `ID` INT NOT NULL AUTO_INCREMENT ,
  `Data` VARCHAR(500) NOT NULL ,
  `Date` DATE NOT NULL ,
  `Likes` INT NOT NULL ,
  `Dislikes` INT NOT NULL ,
  `Username` VARCHAR(100) NOT NULL ,
  `Thread_ID` INT NOT NULL ,
  PRIMARY KEY (`ID`) ,
  INDEX `fk_Post_Account` (`Username` ASC) ,
  INDEX `fk_Post_Thread1` (`Thread_ID` ASC) ,
  CONSTRAINT `fk_Post_Account`
    FOREIGN KEY (`Username` )
    REFERENCES `openhaus`.`Account` (`Username` )
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Post_Thread1`
    FOREIGN KEY (`Thread_ID` )
    REFERENCES `openhaus`.`Thread` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
