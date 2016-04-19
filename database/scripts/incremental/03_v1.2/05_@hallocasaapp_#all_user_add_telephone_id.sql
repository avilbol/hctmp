ALTER TABLE `hallocasaapp`.`user` ADD COLUMN `user_telephone_id` INT(11);
ALTER TABLE `hallocasaapp`.`user` ADD FOREIGN KEY(`user_telephone_id`) REFERENCES `telephone`(`user_id`);