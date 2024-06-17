INSERT INTO customers (name, email, age, password, phone_number) VALUES ('Ivan Melnik', 'ivenmelnik@gmail.com', 23, '12', '380684378031');
INSERT INTO customers (name, email, age, password, phone_number) VALUES ('Sara Vazovski', 'sarik@gmail.com', 40, '13', '380684378032');
INSERT INTO customers (name, email, age, password, phone_number) VALUES ('Emilia Klark', 'emmy@gmail.com', 21, '14','380684378033' );

INSERT INTO employers (name, address) VALUES ('arasaka', ' Corpo Plaza, Night City');
INSERT INTO employers (name, address) VALUES ('militech', 'Reconciliation Park');
INSERT INTO employers (name, address) VALUES ('biotechnica', 'Night City region');

INSERT INTO accounts (number, currency, balance, customer_id) VALUES ('18457405-2528-475a-b6d6-dbb09c4bfbad', 'EUR', 200000.0, 1);
INSERT INTO accounts (number, currency, balance, customer_id) VALUES ('55396c09-4ee6-4ef2-a695-b719d253ef16', 'USD', 125.0, 2);
INSERT INTO accounts (number, currency, balance, customer_id) VALUES ('c41eac72-c755-4eb4-b5dc-6652fbb5974b', 'UAH', 1000000.0, 3);

INSERT INTO customer_employer (customer_id, employer_id) VALUES (1, 1);
INSERT INTO customer_employer (customer_id, employer_id) VALUES (2, 2);
INSERT INTO customer_employer (customer_id, employer_id) VALUES (3, 3);
