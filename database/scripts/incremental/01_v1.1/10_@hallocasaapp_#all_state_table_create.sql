CREATE TABLE `hallocasaapp`.`state` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `state_name` VARCHAR(80) NOT NULL COMMENT '',
  `country_id` INT NOT NULL,
  PRIMARY KEY `pk__state` (`id`)  COMMENT '',
  FOREIGN KEY `fk__state__country` (`country_id`) REFERENCES `hallocasaapp`.`country`(`id`));
