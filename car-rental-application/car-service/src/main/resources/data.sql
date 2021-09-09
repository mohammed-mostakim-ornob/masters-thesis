insert into manufacturer
    (name)
values
    ('Audi'),
    ('BMW'),
    ('Mercedes');

insert into car
    (id, manufacturer_name, model, rent_per_kilo)
values
    (1L, 'Audi', 'A3', 0.1),
    (2L, 'BMW', '3 Series', 0.1),
    (3L, 'Mercedes', 'C Class', 0.1);

insert into car_location
    (car_id, location_code)
values
    (1L, 'BER'),
    (2L, 'FRA'),
    (3L, 'MUN');

