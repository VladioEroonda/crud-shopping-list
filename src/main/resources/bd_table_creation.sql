CREATE TABLE shopping_list_1
(
    id    SERIAL,
    name  VARCHAR(100) NOT NULL,
    count INTEGER NOT NULL,
    price NUMERIC(10, 2) NOT NULL,
    session_id VARCHAR (40) NOT NULL,
    is_changing boolean NOT NULL,
    PRIMARY KEY (id)
);