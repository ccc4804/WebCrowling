CREATE TABLE IF NOT EXISTS `collection`.`keyword` (
  `keyword` VARCHAR(45) NOT NULL,
  `enable` VARCHAR(1) NOT NULL DEFAULT 'Y',
  PRIMARY KEY (`keyword`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;

CREATE TABLE IF NOT EXISTS `collection`.`disease` (
  `disease` VARCHAR(45) NOT NULL,
  `good_food` VARCHAR(45) NULL,
  `bad_food` VARCHAR(45) NULL,
  `good_exercise` VARCHAR(45) NULL,
  `bad_exercise` VARCHAR(45) NULL,
  PRIMARY KEY (`disease`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


CREATE TABLE IF NOT EXISTS `collection`.`food` (
  `food` VARCHAR(45) NOT NULL,
  `good` VARCHAR(45) NULL,
  `bad` VARCHAR(45) NULL,
  PRIMARY KEY (`food`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


CREATE TABLE IF NOT EXISTS `collection`.`food_info` (
  `food` VARCHAR(45) NOT NULL,
  `info` VARCHAR(45) NULL,
  `url` VARCHAR(45) NULL,
  PRIMARY KEY (`food`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;

CREATE TABLE IF NOT EXISTS `collection`.`exercise` (
  `exercise` VARCHAR(45) NOT NULL,
  `good` VARCHAR(45) NULL,
  `bad` VARCHAR(45) NULL,
  PRIMARY KEY (`exercise`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;



CREATE TABLE IF NOT EXISTS `collection`.`exercise_info` (
  `exercise` VARCHAR(45) NOT NULL,
  `info` VARCHAR(45) NULL,
  `url` VARCHAR(45) NULL,
  PRIMARY KEY (`exercise`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;

