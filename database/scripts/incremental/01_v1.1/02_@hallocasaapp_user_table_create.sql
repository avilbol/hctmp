CREATE TABLE `hallocasaapp`.`user` (
  `id` INT NOT NULL COMMENT '',
  `email` VARCHAR(80) NOT NULL COMMENT '',
  `password` VARCHAR(45) NOT NULL COMMENT '',
  `language_id` INT NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  UNIQUE INDEX `uq__user__email` (`email` ASC)  COMMENT '');

ALTER TABLE `hallocasaapp`.`user` 
ADD INDEX `fk__user__language_idx` (`language_id` ASC)  COMMENT '';
ALTER TABLE `hallocasaapp`.`user` 
ADD CONSTRAINT `fk__user__language`
  FOREIGN KEY (`language_id`)
  REFERENCES `hallocasaapp`.`language` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

