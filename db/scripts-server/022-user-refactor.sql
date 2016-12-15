USE hallocasaappmig;
drop table user_profile;
drop table user_user_type;
drop table user_type_profiles;
drop table user_type;
truncate table user;
CREATE TABLE `user_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` text NOT NULL COMMENT 'developer help purpose',
  `lang` text NOT NULL COMMENT 'multilanguages key field',
  `tooltip_lang` text COMMENT 'multilanguages key field',
  `manage_tooltip` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
CREATE TABLE `user_user_type` (
  `user_id` int(11) NOT NULL,
  `user_type_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`user_type_id`),
  KEY `fk__user_user_type__user_type` (`user_type_id`),
  CONSTRAINT `fk__user_user_type__user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk__user_user_type__user_type` FOREIGN KEY (`user_type_id`) REFERENCES `user_type` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `user_profile` (
  `user_id` int(11) NOT NULL,
  `profile_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`profile_id`),
  KEY `fk__user_profile__profile_idx` (`profile_id`),
  CONSTRAINT `fk__user_profile__profile` FOREIGN KEY (`profile_id`) REFERENCES `profile` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk__user_profile__user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `user_type_profiles` (
  `user_type_id` int(11) NOT NULL,
  `profile_id` int(11) NOT NULL,
  PRIMARY KEY (`user_type_id`,`profile_id`),
  KEY `fk__user_type_profiles__profile` (`profile_id`),
  CONSTRAINT `fk__user_type_profiles__profile` FOREIGN KEY (`profile_id`) REFERENCES `profile` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk__user_type_profiles__user_type` FOREIGN KEY (`user_type_id`) REFERENCES `user_type` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
alter table user 
	drop foreign key fk__user__telephone,
	drop column state,
    drop column user_description,
    drop column user_telephone_id,
    drop column spoken_languages,
    drop column main_spoken_language;
alter table user
    modify column language int(11) not null;
alter table user
	change column `IMAGE_NAME` `image_link` varchar(100);
alter table user
    add column telephone_prefix_id int(11),
    add column telephone_number varchar(100);
alter table user
	add constraint `fk__user__language` 
	foreign key (`language`)
    references `lang` (`id`);
alter table user
	add constraint `fk__user__telephone_prefix` 
	foreign key (`telephone_prefix_id`)
    references `telephone_prefix` (`id`);

CREATE TABLE `user_language` (
  `user_id` int(11) NOT NULL,
  `language_id` int(11) NOT NULL,
  `is_main_language` tinyint(1) NOT NULL,
  PRIMARY KEY (`user_id`, `language_id`),
  KEY `fk__user__language__user` (`user_id`),
  KEY `fk__user__language__language` (`language_id`),
  CONSTRAINT `fk__user__language__user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `fk__user__language__language` FOREIGN KEY (`language_id`) REFERENCES `lang` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `user_description` (
  `user_id` int(11) NOT NULL,
  `language_id` int(11) NOT NULL,
  `value` text NOT NULL,
  PRIMARY KEY (`user_id`, `language_id`),
  KEY `fk__user__description__user` (`user_id`),
  KEY `fk__user__description__language` (`language_id`),
  CONSTRAINT `fk__user__description__user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `fk__user__description__language` FOREIGN KEY (`language_id`) REFERENCES `lang` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO user (id, email, password, first_name, last_name, web_site, linked_in, skype, language, confirmed_flag, country_id, state_id, image_link, city_id, telephone_prefix_id, telephone_number)
values
(1, 'development@hallocasa.com', '26239de944662b89a61df8c350e47f48', null, null, null, null, null, 1, 1, null, null, 'user25.jpg', null, null, null);
