CREATE TABLE `hallocasaapp`.`user` (
  `id` INT NOT NULL COMMENT '',
  `email` VARCHAR(80) NOT NULL COMMENT '',
  `password` VARCHAR(45) NOT NULL COMMENT '',
  `language` VARCHAR(10) NOT NULL COMMENT '',
  `active_flag` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  UNIQUE INDEX `uq__user__email` (`email` ASC)  COMMENT '');