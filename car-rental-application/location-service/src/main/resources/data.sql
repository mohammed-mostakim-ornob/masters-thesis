insert into location
(code, name)
values
    ('BER', 'Berlin'),
    ('FRA', 'Frankfurt'),
    ('MUN', 'München'),
    ('HAM', 'Hamburg'),
    ('STU', 'Stuttgart');

insert into distance
(from_location, to_location, distance)
values
    ('BER', 'FRA', 0.00),
    ('BER', 'MUN', 0.00),
    ('BER', 'HAM', 0.00),
    ('BER', 'STU', 0.00),

    ('FRA', 'BER', 0.00),
    ('FRA', 'MUN', 0.00),
    ('FRA', 'HAM', 0.00),
    ('FRA', 'STU', 0.00),

    ('MUN', 'BER', 0.00),
    ('MUN', 'FRA', 0.00),
    ('MUN', 'HAM', 0.00),
    ('MUN', 'STU', 0.00),

    ('HAM', 'BER', 0.00),
    ('HAM', 'FRA', 0.00),
    ('HAM', 'MUN', 0.00),
    ('HAM', 'STU', 0.00),

    ('STU', 'BER', 0.00),
    ('STU', 'FRA', 0.00),
    ('STU', 'MUN', 0.00),
    ('STU', 'HAM', 0.00);

