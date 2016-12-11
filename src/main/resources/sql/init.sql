CREATE DATABASE my_demo;
CREATE USER my_demo_app WITH CONNECTION LIMIT 100 PASSWORD 'md53f7df36cc733438c72a64cb718b0c239';

\c my_demo

CREATE TABLE applicant (
    id SERIAL PRIMARY KEY,
    first_name varchar(50) NOT NULL,
    last_name varchar(50) NOT NULL,
    birth_date date NOT NULL,
    gender int NOT NULL,
    motivation varchar(1000) NOT NULL
);
REVOKE ALL ON applicant FROM my_demo_app;
-- Keep access privileges at a minimum.
GRANT INSERT, SELECT ON applicant TO my_demo_app;
GRANT USAGE ON applicant_id_seq TO my_demo_app;



