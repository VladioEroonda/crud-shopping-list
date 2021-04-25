CREATE TABLE shopping_list_1
(
    id    SERIAL,
    name  VARCHAR(100),
    count INTEGER,
    price NUMERIC(10, 2),
    session_id VARCHAR (40),
    PRIMARY KEY (id)
);

INSERT INTO shopping_list_1 (name, count, price) VALUES ('Coca Cola 1 l',2,77.50);