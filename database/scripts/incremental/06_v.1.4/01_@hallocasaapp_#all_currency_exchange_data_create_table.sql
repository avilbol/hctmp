CREATE TABLE currency_exchange_data(
	id int primary key,
    currency_from_id int,
    currency_to_id int,
    rate_exchange decimal,
    update_date datetime,
    foreign key(currency_from_id) references hallocasaapp.currency(id),
    foreign key(currency_to_id) references hallocasaapp.currency(id)
);