-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema assigone_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema assigone_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `assigone_db` DEFAULT CHARACTER SET utf8 ;
USE `assigone_db` ;

-- -----------------------------------------------------
-- Table `assigone_db`.`client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `assigone_db`.`client` (
  `client_id` INT(11) NOT NULL,
  `client_address` VARCHAR(255) NULL DEFAULT NULL,
  `client_cnp` VARCHAR(255) NOT NULL,
  `client_email` VARCHAR(255) NULL DEFAULT NULL,
  `client_id_number` VARCHAR(255) NULL DEFAULT NULL,
  `client_name` VARCHAR(255) NULL DEFAULT NULL,
  `client_phone` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`client_id`),
  UNIQUE INDEX `UK_ox8dmaa6lcretsbovkdwrpj9s` (`client_cnp` ASC),
  UNIQUE INDEX `UK_qdi8jus6a830ewpqrmbeeec67` (`client_email` ASC),
  UNIQUE INDEX `UK_230gn63phqh72lvq29cr650tb` (`client_id_number` ASC),
  UNIQUE INDEX `UK_hn712qxtx7c6nhbeqje09qufx` (`client_phone` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `assigone_db`.`account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `assigone_db`.`account` (
  `account_id` VARCHAR(255) NOT NULL,
  `account_balance` FLOAT NULL DEFAULT NULL,
  `account_creation` DATETIME NULL DEFAULT NULL,
  `account_type` VARCHAR(255) NULL DEFAULT NULL,
  `client_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`account_id`),
  INDEX `FKpei4spdwejr1mbu98yngbojd8` (`client_id` ASC),
  CONSTRAINT `FKpei4spdwejr1mbu98yngbojd8`
    FOREIGN KEY (`client_id`)
    REFERENCES `assigone_db`.`client` (`client_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `assigone_db`.`employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `assigone_db`.`employee` (
  `employee_id` INT(11) NOT NULL,
  `employee_address` VARCHAR(255) NULL DEFAULT NULL,
  `employee_cnp` VARCHAR(255) NOT NULL,
  `employee_email` VARCHAR(255) NULL DEFAULT NULL,
  `employee_name` VARCHAR(255) NULL DEFAULT NULL,
  `employee_phone` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`employee_id`),
  UNIQUE INDEX `UK_22amj3kh0312q9juhv3vocwkl` (`employee_cnp` ASC),
  UNIQUE INDEX `UK_cvljv0g3cx4s25idkb6t1wsl4` (`employee_email` ASC),
  UNIQUE INDEX `UK_d167q2yj41g8j6tut85695xpx` (`employee_phone` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `assigone_db`.`hibernate_sequence`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `assigone_db`.`hibernate_sequence` (
  `next_val` BIGINT(20) NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `assigone_db`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `assigone_db`.`user` (
  `employee_id` INT(11) NOT NULL,
  `employee_password` VARCHAR(255) NULL DEFAULT NULL,
  `employee_rights` VARCHAR(255) NULL DEFAULT NULL,
  `employee_username` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`employee_id`),
  UNIQUE INDEX `UK_4brbwk4ae6x0g4atqj87di8cr` (`employee_username` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `assigone_db`.`operation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `assigone_db`.`operation` (
  `operation_time` DATETIME NOT NULL,
  `operation_name` VARCHAR(255) NULL DEFAULT NULL,
  `client_id` INT(11) NULL DEFAULT NULL,
  `employee_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`operation_time`),
  INDEX `FKluf4rcgrkxu77oakijwr5y91u` (`client_id` ASC),
  INDEX `FKtbsodv35h0gnu7wqebahvvkir` (`employee_id` ASC),
  CONSTRAINT `FKluf4rcgrkxu77oakijwr5y91u`
    FOREIGN KEY (`client_id`)
    REFERENCES `assigone_db`.`client` (`client_id`),
  CONSTRAINT `FKtbsodv35h0gnu7wqebahvvkir`
    FOREIGN KEY (`employee_id`)
    REFERENCES `assigone_db`.`user` (`employee_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
