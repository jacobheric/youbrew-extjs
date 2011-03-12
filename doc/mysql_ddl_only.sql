SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `youbrew` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;

-- -----------------------------------------------------
-- Table `youbrew`.`malt`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `youbrew`.`malt` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(128) NOT NULL COMMENT 'Descriptive name of malt' ,
  `color` SMALLINT NULL COMMENT 'SRM value' ,
  `potential` DOUBLE NULL COMMENT 'Potential SG ' ,
  `yield` DOUBLE NULL COMMENT 'Yield percentage' ,
  `origin` VARCHAR(45) NULL COMMENT 'Country of origin' ,
  `description` VARCHAR(1024) NULL COMMENT 'Additional description of malt' ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB
COMMENT = 'The malts that make up your grain bill';


-- -----------------------------------------------------
-- Table `youbrew`.`hop`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `youbrew`.`hop` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(128) NOT NULL COMMENT 'name of hop' ,
  `alpha` VARCHAR(45) NOT NULL ,
  `origin` VARCHAR(45) NULL ,
  `description` VARCHAR(1028) NULL COMMENT 'Additional hop description' ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB
COMMENT = 'The hops that bitter your brew.';


-- -----------------------------------------------------
-- Table `youbrew`.`yeast`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `youbrew`.`yeast` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(128) NOT NULL COMMENT 'Name of yeast' ,
  `attenuation` SMALLINT NOT NULL COMMENT 'Attenuation percentage' ,
  `description` VARCHAR(1028) NULL COMMENT 'Additional description' ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB
COMMENT = 'yeast table';


-- -----------------------------------------------------
-- Table `youbrew`.`recipe`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `youbrew`.`recipe` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(256) NOT NULL COMMENT 'Name of recipe' ,
  `start` DATETIME NULL COMMENT 'start date & time' ,
  `end` DATETIME NULL COMMENT 'end date/time' ,
  `brew_notes` VARCHAR(1028) NULL COMMENT 'notes on the brew session' ,
  `taste_notes` VARCHAR(1028) NULL COMMENT 'Notes on taste' ,
  `yeast_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) ,
  INDEX `yeast_fk` (`yeast_id` ASC) ,
  CONSTRAINT `yeast_fk`
    FOREIGN KEY (`yeast_id` )
    REFERENCES `youbrew`.`yeast` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'recipe table';


-- -----------------------------------------------------
-- Table `youbrew`.`recipe_malts`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `youbrew`.`recipe_malts` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `malt_id` INT NOT NULL COMMENT 'the malt pk' ,
  `recipe_id` INT NOT NULL COMMENT 'the recipe pk' ,
  PRIMARY KEY (`id`) ,
  INDEX `recipe_fk` (`recipe_id` ASC) ,
  INDEX `malt_fk` (`malt_id` ASC) ,
  CONSTRAINT `recipe_fk`
    FOREIGN KEY (`recipe_id` )
    REFERENCES `youbrew`.`recipe` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `malt_fk`
    FOREIGN KEY (`malt_id` )
    REFERENCES `youbrew`.`malt` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Joins malts to recipes.';


-- -----------------------------------------------------
-- Table `youbrew`.`recipe_hops`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `youbrew`.`recipe_hops` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `recipe_id` INT NOT NULL COMMENT 'recipe pk' ,
  `hop_id` INT NOT NULL COMMENT 'hop pk' ,
  PRIMARY KEY (`id`) ,
  INDEX `recipe_hop_fk` (`recipe_id` ASC) ,
  INDEX `hop_recipe_fk` (`hop_id` ASC) ,
  CONSTRAINT `recipe_hop_fk`
    FOREIGN KEY (`recipe_id` )
    REFERENCES `youbrew`.`recipe` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `hop_recipe_fk`
    FOREIGN KEY (`hop_id` )
    REFERENCES `youbrew`.`hop` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Joins hops to recipes';


CREATE USER 'youbrew_user'@'localhost' IDENTIFIED BY 'somethinggood';
GRANT ALL PRIVILEGES ON youbrew.* TO 'youbrew_user'@'%' WITH GRANT OPTION;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
