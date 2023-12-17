CREATE DATABASE gamebar;
use gamebar;

-- 1

CREATE TABLE employees (
    id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL
);

CREATE TABLE categories (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE products (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    category_id INT NOT NULL
);

-- 2

INSERT INTO employees(first_name, last_name)
values ('test', 'test'),
('test', 'test'),
('test', 'test');

-- 3

ALTER TABLE employees
add column middle_name VARCHAR(50);

-- 4

ALTER TABLE products
ADD CONSTRAINT fk_products_categories
FOREIGN KEY products(category_id)
REFERENCES categories(id);

-- 5

ALTER TABLE employees
MODIFY COLUMN middle_name VARCHAR(100);
