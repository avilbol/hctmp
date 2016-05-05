CREATE TABLE TOKEN (
	`id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	`id_associated` INT,
	`token_content` VARCHAR(200),
	`due_date` DATETIME,
	`active` TINYINT(1),
	`expedition_date` DATETIME
);