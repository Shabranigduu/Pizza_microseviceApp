-- DROP TABLE IF EXISTS courier CASCADE;
-- DROP TABLE IF EXISTS delivery CASCADE;


CREATE TABLE IF NOT EXISTS courier
(
    id    serial primary key,
    name  varchar(255),
    email varchar(255)
);

CREATE TABLE IF NOT EXISTS delivery
(
    order_id     int primary key,
    courier_id   int,
    is_delivered boolean default false,
    CONSTRAINT fk_courier_to_locations FOREIGN KEY (courier_id) REFERENCES courier (id)
);
