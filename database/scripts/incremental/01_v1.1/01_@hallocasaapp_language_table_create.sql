CREATE TABLE `hallocasaapp`.`language` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `native_name` VARCHAR(45) NOT NULL COMMENT '',
  `english_name` VARCHAR(45) NOT NULL COMMENT '',
  `java_locale` VARCHAR(15) NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  UNIQUE INDEX `native_name_UNIQUE` (`native_name` ASC)  COMMENT '',
  UNIQUE INDEX `english_name_UNIQUE` (`english_name` ASC)  COMMENT '');
