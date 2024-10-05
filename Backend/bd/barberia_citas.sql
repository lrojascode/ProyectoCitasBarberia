CREATE DATABASE IF NOT EXISTS barberia_citas;
USE barberia_citas;

-- Tabla roles
CREATE TABLE roles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(35) NOT NULL
);

-- Tabla users
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(30) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(130) NOT NULL,
    role_id BIGINT NOT NULL,
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

-- Tabla customers
CREATE TABLE customers (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    users_id BIGINT,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    FOREIGN KEY (users_id) REFERENCES users(id)
);

-- Tabla services
CREATE TABLE services (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(130) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    duration_minutes INT NOT NULL
);

-- Tabla employees
CREATE TABLE employees (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    profession VARCHAR(70) NOT NULL,
    picture MEDIUMBLOB,
    working_days VARCHAR(7) NOT NULL,
    active TINYINT(1) DEFAULT 1
);

-- Relación empleados - servicios (employees_services)
CREATE TABLE employees_services (
    employees_id BIGINT NOT NULL,
    services_id BIGINT NOT NULL,
    PRIMARY KEY (employees_id, services_id),
    FOREIGN KEY (employees_id) REFERENCES employees(id),
    FOREIGN KEY (services_id) REFERENCES services(id)
);

-- Tabla employee_schedules (horarios de empleados)
CREATE TABLE employee_schedules (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    employees_id BIGINT NOT NULL,
    from_hour TIME NOT NULL,
    to_hour TIME NOT NULL,
    FOREIGN KEY (employees_id) REFERENCES employees(id)
);

-- Tabla employee_absent_on (ausencias de empleados)
CREATE TABLE employee_absent_on (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    employees_id BIGINT NOT NULL,
    date DATE NOT NULL,
    from_hour TIME,
    to_hour TIME,
    FOREIGN KEY (employees_id) REFERENCES employees(id)
);

-- Tabla appointments (citas)
CREATE TABLE appointments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customers_id BIGINT NOT NULL,
    employees_id BIGINT NOT NULL,
    services_id BIGINT NOT NULL,
    datetime TIMESTAMP NOT NULL,
    end_time TIME NOT NULL,
    cancelled TINYINT(1) DEFAULT 0,
    FOREIGN KEY (customers_id) REFERENCES customers(id),
    FOREIGN KEY (employees_id) REFERENCES employees(id),
    FOREIGN KEY (services_id) REFERENCES services(id)
);

-- Insertar roles
INSERT INTO roles (name) VALUES
('Admin'),
('Customer');

-- Insertar usuarios
INSERT INTO users (username, password, email, role_id) VALUES
('adminUser', 'adminPass', 'admin@barbershop.com', 1),
('employee1', 'emp1Pass', 'employee1@barbershop.com', 2),
('employee2', 'emp2Pass', 'employee2@barbershop.com', 2),
('customer1', 'cust1Pass', 'customer1@example.com', 3),
('customer2', 'cust2Pass', 'customer2@example.com', 3),
('customer3', 'cust3Pass', 'customer3@example.com', 3);

-- Insertar clientes (relacionados con los usuarios)
INSERT INTO customers (users_id, first_name, last_name, phone) VALUES
(4, 'Juan', 'Pérez', '555-1234'),
(5, 'María', 'García', '555-5678'),
(6, 'Carlos', 'López', '555-9012');

-- Insertar servicios
INSERT INTO services (name, description, price, duration_minutes) VALUES
('Corte de Cabello', 'Corte de cabello estándar para hombres', 15.00, 30),
('Arreglo de Barba', 'Recorte y perfilado de barba', 10.00, 20),
('Afeitado Clásico', 'Afeitado con navaja tradicional', 12.00, 25),
('Corte y Barba', 'Combo de corte de cabello y arreglo de barba', 22.00, 45);

-- Insertar empleados
INSERT INTO employees (first_name, last_name, profession, working_days, active) VALUES
('Miguel', 'Rodríguez', 'Barbero', 'Mon-Fri', 1),
('Sofía', 'Martínez', 'Barbera', 'Tue-Sat', 1);

-- Relacionar empleados con servicios (employees_services)
INSERT INTO employees_services (employees_id, services_id) VALUES
(1, 1),
(1, 2),
(1, 4),
(2, 1),
(2, 3),
(2, 4);

-- Insertar horarios de empleados (employee_schedules)
INSERT INTO employee_schedules (employees_id, from_hour, to_hour) VALUES
(1, '09:00:00', '17:00:00'),
(2, '10:00:00', '18:00:00');

-- Insertar ausencias de empleados (employee_absent_on)
-- Miguel estará ausente el 2024-10-15 de 13:00 a 17:00
INSERT INTO employee_absent_on (employees_id, date, from_hour, to_hour) VALUES
(1, '2024-10-15', '13:00:00', '17:00:00');

-- Insertar citas (appointments)
INSERT INTO appointments (customers_id, employees_id, services_id, datetime, end_time, cancelled) VALUES
(1, 1, 1, '2024-10-10 10:00:00', '10:30:00', 0),  -- Juan con Miguel para Corte de Cabello
(2, 2, 3, '2024-10-11 11:00:00', '11:25:00', 0),  -- María con Sofía para Afeitado Clásico
(3, 1, 4, '2024-10-12 14:00:00', '14:45:00', 0); -- Carlos con Miguel para Corte y Barba

