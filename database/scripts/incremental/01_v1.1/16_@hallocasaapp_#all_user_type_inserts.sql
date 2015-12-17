INSERT INTO `hallocasaapp`.`user_type`(
	`id`,
    `user_type_name`,
    `user_type_tooltip`,
    `manage_tooltip`,
    `manage_certificate`) 
    VALUES(
    1, 
    "{'en':'Investor','de':'Investor','es':'Inversor'}", 
    null,
    0,
    1),
    
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
    "{'en':'Translator','de':'Übersetzer','es':'Traductor'}",
    null,
    0,
    1
    ),
    
    (
    6, 
    "{'en':'Property Manager','de':'Hausverwalter','es':'Manejador de propiedades'}",
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
    );