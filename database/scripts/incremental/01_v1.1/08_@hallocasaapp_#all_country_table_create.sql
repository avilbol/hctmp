CREATE TABLE `hallocasaapp`.`country` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `country_name` VARCHAR(80) NOT NULL COMMENT '',
  `java_code` VARCHAR(15) NOT NULL COMMENT '',
  PRIMARY KEY `pk__country` (`id`)  COMMENT '',
  UNIQUE INDEX `uq__country__country_name` (`java_code` ASC)  COMMENT '',
  UNIQUE INDEX `uq__country__java_code` (`country_name` ASC)  COMMENT '');
