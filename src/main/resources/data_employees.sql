-- Insert Users (Employees)
-- Password hash for '123456' (BCrypt)
-- Correct Hash provided by user: $2a$12$TDzaB0a23T.YFgcQKk0sYuqaMZqtWxn1hlaoVLaA2TAZ569xIDLOi
-- Table name restored to 'user'
-- DTYPE 'Employee' included for Hibernate Joined Inheritance

-- 2 Veterinarians
INSERT INTO user (dtype, names, last_names, document_number, email, password_hash, role, birth_date, created_date, address, phone, is_deleted, id_document_type)
VALUES 
('Employee', 'Carlos', 'Veterinario', '1001', 'vet1@petcare.com', '$2a$12$TDzaB0a23T.YFgcQKk0sYuqaMZqtWxn1hlaoVLaA2TAZ569xIDLOi', 'EMPLOYEE', '1985-05-15', NOW(), 'Calle 123 #45-67', '3001112222', false, 1),
('Employee', 'Ana', 'Veterinaria', '1002', 'vet2@petcare.com', '$2a$12$TDzaB0a23T.YFgcQKk0sYuqaMZqtWxn1hlaoVLaA2TAZ569xIDLOi', 'EMPLOYEE', '1990-08-20', NOW(), 'Carrera 80 #12-34', '3003334444', false, 1);

-- 2 Cashiers
INSERT INTO user (dtype, names, last_names, document_number, email, password_hash, role, birth_date, created_date, address, phone, is_deleted, id_document_type)
VALUES 
('Employee', 'Pedro', 'Cajero', '2001', 'cashier1@petcare.com', '$2a$12$TDzaB0a23T.YFgcQKk0sYuqaMZqtWxn1hlaoVLaA2TAZ569xIDLOi', 'EMPLOYEE', '1995-02-10', NOW(), 'Avenida Siempre Viva 123', '3105556666', false, 1),
('Employee', 'Maria', 'Cajera', '2002', 'cashier2@petcare.com', '$2a$12$TDzaB0a23T.YFgcQKk0sYuqaMZqtWxn1hlaoVLaA2TAZ569xIDLOi', 'EMPLOYEE', '1998-11-30', NOW(), 'Calle 100 #20-30', '3107778888', false, 1);

-- Insert Employees (Linked to Users by ID)
INSERT INTO employee (id, employee_code, salary, cargo)
VALUES 
((SELECT id FROM user WHERE email = 'vet1@petcare.com'), 'VET001', 5000000.00, 'VETERINARIAN'),
((SELECT id FROM user WHERE email = 'vet2@petcare.com'), 'VET002', 5000000.00, 'VETERINARIAN'),
((SELECT id FROM user WHERE email = 'cashier1@petcare.com'), 'CASH001', 2500000.00, 'CASHIER'),
((SELECT id FROM user WHERE email = 'cashier2@petcare.com'), 'CASH002', 2500000.00, 'CASHIER');
