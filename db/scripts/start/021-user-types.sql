USE hallocasaapp;
drop table user_user_type;
drop table user_type_profiles;
drop table user_type;
CREATE TABLE `user_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` text NOT NULL COMMENT 'developer help purpose',
  `lang` text NOT NULL COMMENT 'multilanguages key field',
  `tooltip_lang` text COMMENT 'multilanguages key field',
  `manage_tooltip` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
CREATE TABLE `user_user_type` (
  `user_id` int(11) NOT NULL,
  `user_type_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`user_type_id`),
  KEY `fk__user_user_type__user_type` (`user_type_id`),
  CONSTRAINT `fk__user_user_type__user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk__user_user_type__user_type` FOREIGN KEY (`user_type_id`) REFERENCES `user_type` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `user_type_profiles` (
  `user_type_id` int(11) NOT NULL,
  `profile_id` int(11) NOT NULL,
  PRIMARY KEY (`user_type_id`,`profile_id`),
  KEY `fk__user_type_profiles__profile` (`profile_id`),
  CONSTRAINT `fk__user_type_profiles__profile` FOREIGN KEY (`profile_id`) REFERENCES `profile` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk__user_type_profiles__user_type` FOREIGN KEY (`user_type_id`) REFERENCES `user_type` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO user_type (id, name, lang, manage_tooltip, tooltip_lang)
VALUES
(1, 'Broker', 'hallocasa.usertype.broker.pending', 0, null),
(2, 'Notary', 'hallocasa.usertype.notary.pending', 0, null),
(3, 'Appraiser', 'hallocasa.usertype.appraiser.pending', 0, null),
(4, 'Translator', 'hallocasa.usertype.translator.pending', 0, null),
(5, 'Administrator', 'hallocasa.usertype.administrator.pending', 0, null),
(6, 'Expert', 'hallocasa.usertype.expert.pending', 1, 'hallocasa.usertype.expert.tooltip.pending'),
(7, 'Lawyer', 'hallocasa.usertype.lawyer.pending', 0, null);
