-- db/migration/V1__create_tables.sql

CREATE TABLE customers (
                           customer_id SERIAL PRIMARY KEY,
                           first_name VARCHAR(50) NOT NULL,
                           last_name VARCHAR(50) NOT NULL,
                           email VARCHAR(100) NOT NULL,
                           phone VARCHAR(20)
);

CREATE TABLE addresses (
                           address_id SERIAL PRIMARY KEY,
                           customer_id INT NOT NULL,
                           street VARCHAR(100) NOT NULL,
                           city VARCHAR(50) NOT NULL,
                           state VARCHAR(50) NOT NULL,
                           country VARCHAR(50) NOT NULL,
                           zip_code VARCHAR(20) NOT NULL,
                           FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

CREATE TABLE products (
                          product_id SERIAL PRIMARY KEY,
                          product_name VARCHAR(100) NOT NULL,
                          description TEXT NOT NULL,
                          price DECIMAL(10,2) NOT NULL
);

CREATE TABLE orders (
                        order_id SERIAL PRIMARY KEY,
                        customer_id INT NOT NULL,
                        order_date DATE NOT NULL,
                        total_amount DECIMAL(10,2) NOT NULL,
                        FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

CREATE TABLE order_items (
                             order_item_id SERIAL PRIMARY KEY,
                             order_id INT NOT NULL,
                             product_id INT NOT NULL,
                             quantity INT NOT NULL,
                             price DECIMAL(10,2) NOT NULL,
                             FOREIGN KEY (order_id) REFERENCES orders(order_id),
                             FOREIGN KEY (product_id) REFERENCES products(product_id)
);
