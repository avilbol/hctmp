CREATE TABLE `hallocasaapp`.`profile` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `profile_name` VARCHAR(80) NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  UNIQUE INDEX `profile_name_UNIQUE` (`profile_name` ASC)  COMMENT '');

CREATE TABLE `hallocasaapp`.`use_case` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `use_case_name` VARCHAR(120) NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  UNIQUE INDEX `use_case_name_UNIQUE` (`use_case_name` ASC)  COMMENT '');

CREATE TABLE `hallocasaapp`.`profile_use_case` (
  `profile_id` INT NOT NULL COMMENT '',
  `use_case_id` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`profile_id`, `use_case_id`)  COMMENT '',
  CONSTRAINT `fk__profile_use_case__profile`
    FOREIGN KEY (`profile_id`)
    REFERENCES `hallocasaapp`.`profile` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk__profile_use_case__use_case`
    FOREIGN KEY (`use_case_id`)
    REFERENCES `hallocasaapp`.`use_case` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE `hallocasaapp`.`user_profile` (
  `user_id` INT NOT NULL COMMENT '',
  `profile_id` INT NOT NULL COMMENT '',
  PRIMARY KEY (`user_id`, `profile_id`)  COMMENT '',
  INDEX `fk__user_profile__profile_idx` (`profile_id` ASC)  COMMENT '',
  CONSTRAINT `fk__user_profile__user`
    FOREIGN KEY (`user_id`)
    REFERENCES `hallocasaapp`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk__user_profile__profile`
    FOREIGN KEY (`profile_id`)
    REFERENCES `hallocasaapp`.`profile` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);



