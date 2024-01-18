CREATE TABLE IF NOT EXISTS users(
    user_id SERIAL PRIMARY KEY ,
    user_first_name VARCHAR(255),
    user_username VARCHAR(255) UNIQUE NOT NULL ,
    email VARCHAR(255) UNIQUE NOT NULL ,
    user_password VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS brand(
    brand_id SERIAL PRIMARY KEY ,
    brand_name VARCHAR(255),
    brand_website VARCHAR(255),
    brand_description VARCHAR(255)
);