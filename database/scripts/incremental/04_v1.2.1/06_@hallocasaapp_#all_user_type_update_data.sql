SET FOREIGN_KEY_CHECKS=0;
DELETE FROM `hallocasaapp`.`user_type`;

INSERT INTO `hallocasaapp`.`user_type`(
	`id`,
    `user_type_name`,
    `user_type_tooltip`,
    `manage_tooltip`,
    `manage_certificate`) 
    VALUES
    (
    2, 
    "{'en':'Broker','de':'Makler','es':'Corredor'}",
    null,
    0,
    1
    ),
    
    (
    3, 
    "{'en':'Notary','de':'Notar','es':'Notario'}",
    null,
    0,
    1
    ),
    
    (
    4, 
    "{'en':'Appraiser','de':'Gutachter','es':'Tasador'}",
    null,
    0,
    1
    ),
    
    (
    5, 
    "{'en':'Translator','de':'\\u00DCbersetzer','es':'Traductor'}",
    null,
    0,
    1
    ),
    
    (
    6, 
    "{'en':'Administrator','de':'Verwalter','es':'Administrador'}",
    null,
    0,
    0
    ),
    
    (
    7, 
    "{'en':'Expert','de':'Experte','es':'Experto'}",
    "{'en':'Investors will contact you in order to obtain market insights or custom questions','de':'Investors will contact you in ordert to obtain market insights or custom questions','es':'Investors will contact you in ordert to obtain market insights or custom questions'}",
    1,
    0
    ),
	
	(
	8,
	"{'en':'Lawyer','es':'Abogado','de':'Anwalt'}", 
	null,
	0,
	0);
SET FOREIGN_KEY_CHECKS=1;