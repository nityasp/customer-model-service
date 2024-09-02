CREATE TABLE customer (
    id SERIAL PRIMARY KEY,
    prefix VARCHAR(10),
    first_name VARCHAR(100) NOT NULL,
    middle_name VARCHAR(100),
    last_name VARCHAR(100) NOT NULL,
    suffix VARCHAR(10),
    email VARCHAR(255) NOT NULL UNIQUE,
    phone_number VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO customer (prefix, first_name, middle_name, last_name, suffix, email, phone_number)
VALUES ('Mr.', 'John', 'A.', 'Doe', 'Jr.', 'john.doe@example.com', '123-456-7890');

SELECT * FROM customer;
SELECT * FROM customer WHERE email = 'john.doe@example.com';
UPDATE customer
SET first_name = 'Jonathan', last_name = 'Doe', phone_number = '098-765-4321', updated_at = CURRENT_TIMESTAMP
WHERE email = 'john.doe@example.com';
DELETE FROM customer WHERE email = 'john.doe@example.com';

