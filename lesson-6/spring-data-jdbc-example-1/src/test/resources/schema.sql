CREATE TABLE IF NOT EXISTS EMPLOYEES
(
    id           bigserial,
    full_name    VARCHAR(255),
    email        VARCHAR(255),
    phone_number VARCHAR(255),
    birth_date   TIMESTAMP,

    PRIMARY KEY (ID)
);