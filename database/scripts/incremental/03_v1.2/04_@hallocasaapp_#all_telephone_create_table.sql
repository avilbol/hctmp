CREATE TABLE `hallocasaapp`.`telephone` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
	`number` varchar(100) NOT NULL,
    `prefix_id` int(11) NOT NULL,
    `user_id` int(11) NOT NULL,
    PRIMARY KEY(`id`),
	FOREIGN KEY(`prefix_id`) REFERENCES `telephone_prefix`(`id`),
    FOREIGN KEY(`user_id`) REFERENCES `user`(`id`)
);