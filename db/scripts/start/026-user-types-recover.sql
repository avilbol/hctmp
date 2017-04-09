USE hallocasaappmig;
INSERT INTO user_type (id, name, lang, manage_tooltip, tooltip_lang)
VALUES
(1, 'Broker', 'hallocasa.usertype.broker.pending', 0, null),
(2, 'Notary', 'hallocasa.usertype.notary.pending', 0, null),
(3, 'Appraiser', 'hallocasa.usertype.appraiser.pending', 0, null),
(4, 'Translator', 'hallocasa.usertype.translator.pending', 0, null),
(5, 'Administrator', 'hallocasa.usertype.administrator.pending', 0, null),
(6, 'Expert', 'hallocasa.usertype.expert.pending', 1, 'hallocasa.usertype.expert.tooltip.pending'),
(7, 'Lawyer', 'hallocasa.usertype.lawyer.pending', 0, null);