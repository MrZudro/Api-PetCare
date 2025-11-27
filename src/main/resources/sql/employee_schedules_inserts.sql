-- ================================================================
-- INSERTS DE EMPLEADOS Y HORARIOS PARA SISTEMA DE AGENDAMIENTO
-- ================================================================

-- NUEVOS EMPLEADOS (3 adicionales)

-- 2 Veterinarios adicionales
INSERT INTO user (dtype, names, last_names, document_number, email, password_hash, role, birth_date, created_date, address, phone, is_deleted, id_document_type)
VALUES 
('Employee', 'Luis', 'Veterinario', '1003', 'vet3@petcare.com', '$2a$12$TDzaB0a23T.YFgcQKk0sYuqaMZqtWxn1hlaoVLaA2TAZ569xIDLOi', 'EMPLOYEE', '1988-03-25', NOW(), 'Calle 50 #30-40', '3009991111', false, 1),
('Employee', 'Sofia', 'Veterinaria', '1004', 'vet4@petcare.com', '$2a$12$TDzaB0a23T.YFgcQKk0sYuqaMZqtWxn1hlaoVLaA2TAZ569xIDLOi', 'EMPLOYEE', '1992-07-18', NOW(), 'Carrera 15 #25-30', '3008882222', false, 1);

-- 1 Cajero adicional
INSERT INTO user (dtype, names, last_names, document_number, email, password_hash, role, birth_date, created_date, address, phone, is_deleted, id_document_type)
VALUES 
('Employee', 'Juan', 'Cajero', '2003', 'cashier3@petcare.com', '$2a$12$TDzaB0a23T.YFgcQKk0sYuqaMZqtWxn1hlaoVLaA2TAZ569xIDLOi', 'EMPLOYEE', '1996-09-12', NOW(), 'Avenida 68 #45-20', '3106665555', false, 1);

-- Registros en tabla employee
INSERT INTO employee (id, employee_code, salary, cargo)
VALUES 
((SELECT id FROM user WHERE email = 'vet3@petcare.com'), 'VET003', 5000000.00, 'VETERINARIAN'),
((SELECT id FROM user WHERE email = 'vet4@petcare.com'), 'VET004', 5000000.00, 'VETERINARIAN'),
((SELECT id FROM user WHERE email = 'cashier3@petcare.com'), 'CASH003', 2500000.00, 'CASHIER');


-- ================================================================
-- HORARIOS (SCHEDULES) PARA TODOS LOS EMPLEADOS
-- ================================================================

-- Variables para IDs de empleados
SET @vet1_id = (SELECT id FROM user WHERE email = 'vet1@petcare.com');
SET @vet2_id = (SELECT id FROM user WHERE email = 'vet2@petcare.com');
SET @vet3_id = (SELECT id FROM user WHERE email = 'vet3@petcare.com');
SET @vet4_id = (SELECT id FROM user WHERE email = 'vet4@petcare.com');
SET @cash1_id = (SELECT id FROM user WHERE email = 'cashier1@petcare.com');
SET @cash2_id = (SELECT id FROM user WHERE email = 'cashier2@petcare.com');
SET @cash3_id = (SELECT id FROM user WHERE email = 'cashier3@petcare.com');

-- ================================================================
-- VETERINARIOS - Horarios
-- Lunes a Viernes: 8:00-12:00 y 13:00-17:00 (descanso al mediodía)
-- Sábados: 8:00-12:00
-- ================================================================

-- Carlos Veterinario (VET001) - vet1@petcare.com
INSERT INTO schedules (day, start_time, end_time, id_employee) VALUES
('Lunes', '08:00:00', '12:00:00', @vet1_id),
('Lunes', '13:00:00', '17:00:00', @vet1_id),
('Martes', '08:00:00', '12:00:00', @vet1_id),
('Martes', '13:00:00', '17:00:00', @vet1_id),
('Miércoles', '08:00:00', '12:00:00', @vet1_id),
('Miércoles', '13:00:00', '17:00:00', @vet1_id),
('Jueves', '08:00:00', '12:00:00', @vet1_id),
('Jueves', '13:00:00', '17:00:00', @vet1_id),
('Viernes', '08:00:00', '12:00:00', @vet1_id),
('Viernes', '13:00:00', '17:00:00', @vet1_id),
('Sábado', '08:00:00', '12:00:00', @vet1_id);

-- Ana Veterinaria (VET002) - vet2@petcare.com
INSERT INTO schedules (day, start_time, end_time, id_employee) VALUES
('Lunes', '08:00:00', '12:00:00', @vet2_id),
('Lunes', '13:00:00', '17:00:00', @vet2_id),
('Martes', '08:00:00', '12:00:00', @vet2_id),
('Martes', '13:00:00', '17:00:00', @vet2_id),
('Miércoles', '08:00:00', '12:00:00', @vet2_id),
('Miércoles', '13:00:00', '17:00:00', @vet2_id),
('Jueves', '08:00:00', '12:00:00', @vet2_id),
('Jueves', '13:00:00', '17:00:00', @vet2_id),
('Viernes', '08:00:00', '12:00:00', @vet2_id),
('Viernes', '13:00:00', '17:00:00', @vet2_id),
('Sábado', '08:00:00', '12:00:00', @vet2_id);

-- Luis Veterinario (VET003) - vet3@petcare.com
INSERT INTO schedules (day, start_time, end_time, id_employee) VALUES
('Lunes', '08:00:00', '12:00:00', @vet3_id),
('Lunes', '13:00:00', '17:00:00', @vet3_id),
('Martes', '08:00:00', '12:00:00', @vet3_id),
('Martes', '13:00:00', '17:00:00', @vet3_id),
('Miércoles', '08:00:00', '12:00:00', @vet3_id),
('Miércoles', '13:00:00', '17:00:00', @vet3_id),
('Jueves', '08:00:00', '12:00:00', @vet3_id),
('Jueves', '13:00:00', '17:00:00', @vet3_id),
('Viernes', '08:00:00', '12:00:00', @vet3_id),
('Viernes', '13:00:00', '17:00:00', @vet3_id),
('Sábado', '08:00:00', '12:00:00', @vet3_id);

-- Sofia Veterinaria (VET004) - vet4@petcare.com
INSERT INTO schedules (day, start_time, end_time, id_employee) VALUES
('Lunes', '08:00:00', '12:00:00', @vet4_id),
('Lunes', '13:00:00', '17:00:00', @vet4_id),
('Martes', '08:00:00', '12:00:00', @vet4_id),
('Martes', '13:00:00', '17:00:00', @vet4_id),
('Miércoles', '08:00:00', '12:00:00', @vet4_id),
('Miércoles', '13:00:00', '17:00:00', @vet4_id),
('Jueves', '08:00:00', '12:00:00', @vet4_id),
('Jueves', '13:00:00', '17:00:00', @vet4_id),
('Viernes', '08:00:00', '12:00:00', @vet4_id),
('Viernes', '13:00:00', '17:00:00', @vet4_id),
('Sábado', '08:00:00', '12:00:00', @vet4_id);

-- ================================================================
-- CAJEROS - Horarios
-- Lunes a Viernes: 7:00-16:00
-- Sábados: 7:00-13:00
-- ================================================================

-- Pedro Cajero (CASH001) - cashier1@petcare.com
INSERT INTO schedules (day, start_time, end_time, id_employee) VALUES
('Lunes', '07:00:00', '16:00:00', @cash1_id),
('Martes', '07:00:00', '16:00:00', @cash1_id),
('Miércoles', '07:00:00', '16:00:00', @cash1_id),
('Jueves', '07:00:00', '16:00:00', @cash1_id),
('Viernes', '07:00:00', '16:00:00', @cash1_id),
('Sábado', '07:00:00', '13:00:00', @cash1_id);

-- Maria Cajera (CASH002) - cashier2@petcare.com
INSERT INTO schedules (day, start_time, end_time, id_employee) VALUES
('Lunes', '07:00:00', '16:00:00', @cash2_id),
('Martes', '07:00:00', '16:00:00', @cash2_id),
('Miércoles', '07:00:00', '16:00:00', @cash2_id),
('Jueves', '07:00:00', '16:00:00', @cash2_id),
('Viernes', '07:00:00', '16:00:00', @cash2_id),
('Sábado', '07:00:00', '13:00:00', @cash2_id);

-- Juan Cajero (CASH003) - cashier3@petcare.com
INSERT INTO schedules (day, start_time, end_time, id_employee) VALUES
('Lunes', '07:00:00', '16:00:00', @cash3_id),
('Martes', '07:00:00', '16:00:00', @cash3_id),
('Miércoles', '07:00:00', '16:00:00', @cash3_id),
('Jueves', '07:00:00', '16:00:00', @cash3_id),
('Viernes', '07:00:00', '16:00:00', @cash3_id),
('Sábado', '07:00:00', '13:00:00', @cash3_id);

-- ================================================================
-- RESUMEN
-- ================================================================
-- Total Empleados: 7 (4 Veterinarios, 3 Cajeros)
-- Total Horarios: 62
-- - Veterinarios: 11 registros × 4 = 44 horarios
-- - Cajeros: 6 registros × 3 = 18 horarios
-- ================================================================
