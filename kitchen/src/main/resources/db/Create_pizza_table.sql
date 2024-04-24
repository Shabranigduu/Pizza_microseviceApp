CREATE TABLE Pizza (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       quantity INTEGER

);

INSERT INTO Pizza (name, quantity)
VALUES
    ('Карбонара', 12),
    ('Четыре сыра', 6),
    ('Вегетарианская', 7),
    ('Пепперони с оливками', 9),
    ('Чесночная', 4),
    ('Барбекю', 11),
    ('Морская', 8),
    ('Мексиканская', 5),
    ('Куриная', 10),
    ('Тосканская', 6);