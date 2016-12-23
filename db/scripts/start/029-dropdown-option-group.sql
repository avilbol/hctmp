USE hallocasaapp;
alter table dropdown_option_group 
drop depends_on_lang;
alter table dropdown_option_group 
add column group_translation_management varchar(60) NOT NULL DEFAULT 'TOTAL';
alter table dropdown_option
add column depends_on_lang tinyint(1);

update dropdown_option_group set group_translation_management = 'NONE' WHERE id = 2;
update dropdown_option_group set group_translation_management = 'NONE' WHERE id = 3;
update dropdown_option_group set group_translation_management = 'NONE' WHERE id = 4;
update dropdown_option_group set group_translation_management = 'NONE' WHERE id = 5;
update dropdown_option_group set group_translation_management = 'NONE' WHERE id = 6;
update dropdown_option_group set group_translation_management = 'NONE' WHERE id = 7;
update dropdown_option_group set group_translation_management = 'NONE' WHERE id = 8;
update dropdown_option_group set group_translation_management = 'NONE' WHERE id = 27;

update dropdown_option_group set group_translation_management = 'PARTIAL' WHERE id = 25;
update dropdown_option_group set group_translation_management = 'PARTIAL' WHERE id = 26;

update dropdown_option set depends_on_lang = 1 WHERE dropdown_option_group_id = 25 AND option_id = 21;
update dropdown_option set depends_on_lang = 1 WHERE dropdown_option_group_id = 26 AND option_id = 51;