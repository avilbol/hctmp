CREATE TABLE `hallocasaapp`.`translation` (
  `id` INT NOT NULL COMMENT '',
  `text_en` TEXT COMMENT '',
  `text_es` TEXT COMMENT '',
  `text_de` TEXT COMMENT ''
  PRIMARY KEY (`id`)  COMMENT '';

CREATE TABLE `hallocasaapp`.`user_type` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `user_type_name` VARCHAR(45) NOT NULL COMMENT '',
  `translation_id` INT NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  CONSTRAINT `fk__user_type__translation`
    FOREIGN KEY (`translation_id`)
    REFERENCES `hallocasaapp`.`translation` (`id`);

CREATE TABLE `hallocasaapp`.`user_type_profiles` (
  `user_type_id` INT NOT NULL '',
  `profile_id` INT NOT NULL '',
  PRIMARY KEY (`user_type_id`, `profile_id`)  COMMENT '',
  CONSTRAINT `fk__user_type_profiles__user_type`
    FOREIGN KEY (`user_type_id`)
    REFERENCES `hallocasaapp`.`user_type` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk__user_type_profiles__profile`
    FOREIGN KEY (`profile_id`)
    REFERENCES `hallocasaapp`.`profile` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE `hallocasaapp`.`user_user_type` (
  `user_id` INT NOT NULL '',
  `user_type_id` INT NOT NULL '',
  PRIMARY KEY (`user`, `user_type_id`)  COMMENT '',
  CONSTRAINT `fk__user_user_type__user`
    FOREIGN KEY (`user_id`)
    REFERENCES `hallocasaapp`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk__user_user_type__user_type`
    FOREIGN KEY (`user_type_id`)
    REFERENCES `hallocasaapp`.`user_type` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

