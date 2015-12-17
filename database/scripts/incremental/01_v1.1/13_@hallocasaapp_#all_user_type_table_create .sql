CREATE TABLE `hallocasaapp`.`user_type` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `user_type_name` TEXT NOT NULL COMMENT 'JSON multilanguages field',
  `user_type_tooltip` TEXT COMMENT 'JSON multilanguages field',
  `manage_tooltip` TINYINT(1) NOT NULL DEFAULT '0',
  `manage_certificate` TINYINT(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)  COMMENT '' );

CREATE TABLE `hallocasaapp`.`user_type_profiles` (
  `user_type_id` INT NOT NULL COMMENT '',
  `profile_id` INT NOT NULL COMMENT '',
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
  `user_id` INT NOT NULL COMMENT '',
  `user_type_id` INT NOT NULL COMMENT'',
  PRIMARY KEY (`user_id`, `user_type_id`)  COMMENT '',
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
