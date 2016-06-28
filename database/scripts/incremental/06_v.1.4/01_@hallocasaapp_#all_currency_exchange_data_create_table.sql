CREATE TABLE currency_exchange_data(
	id int primary key not null auto_increment,
    currency_from_id int,
    currency_to_id int,
    rate_exchange float(255,8),
    update_date datetime,
    foreign key(currency_from_id) references hallocasaapp.currency(id),
    foreign key(currency_to_id) references hallocasaapp.currency(id)
);