CREATE TABLE IF NOT EXISTS users(
    user_id SERIAL PRIMARY KEY ,
    user_first_name VARCHAR(255),
    user_username VARCHAR(255) UNIQUE NOT NULL ,
    email VARCHAR(255) UNIQUE NOT NULL ,
    user_password VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS brand(
    brand_id SERIAL PRIMARY KEY ,
    brand_name VARCHAR(255) UNIQUE NOT NULL ,
    brand_website VARCHAR(255),
    brand_description VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS category(
    category_id SERIAL PRIMARY KEY ,
    category_name VARCHAR(255) UNIQUE NOT NULL ,
    category_description VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS product(
    product_id SERIAL PRIMARY KEY ,
    product_name VARCHAR(255),
    product_creat_date DATE,
    category_id_fk INT REFERENCES category(category_id),
    brand_id_fk INT REFERENCES brand(brand_id)
);

CREATE TABLE IF NOT EXISTS shareholder(
    shareholder_id SERIAL PRIMARY KEY ,
    shareholder_name VARCHAR(255),
    shareholder_phone_number VARCHAR(255),
    shareholder_national_code VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS shareholder_brand(
    shareholder_id_fk INT REFERENCES shareholder(shareholder_id),
    brand_id_fk INT REFERENCES brand(brand_id)
);

