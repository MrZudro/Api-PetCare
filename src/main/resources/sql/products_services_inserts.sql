-- =====================================================
-- SQL INSERT STATEMENTS FOR CATEGORIES, SUBCATEGORIES, PRODUCTS AND SERVICES
-- Include test data for Products (5) and Services (5)
-- =====================================================

-- ===== CATEGORIES =====
INSERT INTO category (name) VALUES 
('Alimentos'),
('Juguetes'),
('Higiene'),
('Accesorios');

-- ===== SUBCATEGORIES =====
-- Assuming IDs: Category 1=Alimentos, 2=Juguetes, 3=Higiene, 4=Accesorios
INSERT INTO subcategory (name, categoria_id) VALUES 
-- Alimentos (1)
('Concentrado', 1),
('Humedo', 1),
('Natural', 1),
-- Juguetes (2)
('Interactivo', 2),
('Masticacion', 2),
-- Higiene (3)
('Liquido', 3),
('Cepillado', 3),
-- Accesorios (4)
('Paseo', 4),
('Memoria', 4),
('Fijo', 4),
('Automatico', 4),
('Viaje', 4),
('Vertical', 4),
('Proteccion', 4),
('Mineral', 4);

-- =====================================================
-- PRODUCTS (5 items)
-- =====================================================
-- Assuming subcategory IDs from above inserts

-- Product 1: Comida Seca Premium para Perros
INSERT INTO product (name, picture, price, stock, brand, description, sku, is_active)
VALUES (
    'Comida Seca Premium para Perros',
    'https://placehold.co/400x300/a3e1ff/000000?text=Comida+Seca+Premium',
    45990.00,
    50,
    'HILLS',
    'Formula avanzada con pollo y arroz, ideal para perros adultos de razas medianas.',
    'SKU-HILL-001',
    'ACTIVE'
);

-- Link to Subcategory: Concentrado (ID should be 1)
INSERT INTO product_subcategory (id_product, id_subcategory)
SELECT id, 1 FROM product WHERE sku = 'SKU-HILL-001';

-- Product 2: Juguete Dispensador de Premios
INSERT INTO product (name, picture, price, stock, brand, description, sku, is_active)
VALUES (
    'Juguete Dispensador de Premios',
    'https://placehold.co/400x300/b8e8b8/000000?text=Juguete+Interactivo',
    12500.00,
    30,
    'CHUNKY',
    'Juguete interactivo que estimula mentalmente a tu mascota.',
    'SKU-CHUNK-002',
    'ACTIVE'
);

-- Link to Subcategory: Inter activo (ID should be 4)
INSERT INTO product_subcategory (id_product, id_subcategory)
SELECT id, 4 FROM product WHERE sku = 'SKU-CHUNK-002';

-- Product 3: Champu Hipoalergenico
INSERT INTO product (name, picture, price, stock, brand, description, sku, is_active)
VALUES (
    'Champu Hipoalergenico',
    'https://placehold.co/400x300/f7d3e2/000000?text=Champu+Hipoalergenico',
    18750.00,
    40,
    'HILLS',
    'Formula suave con avena, ideal para pieles sensibles y cachorros.',
    'SKU-HILL-003',
    'ACTIVE'
);

-- Link to Subcategory: Liquido (ID should be 6)
INSERT INTO product_subcategory (id_product, id_subcategory)
SELECT id, 6 FROM product WHERE sku = 'SKU-HILL-003';

-- Product 4: Cama Ortopedica Grande
INSERT INTO product (name, picture, price, stock, brand, description, sku, is_active)
VALUES (
    'Cama Ortopedica Grande',
    'https://placehold.co/400x300/c7c7f7/000000?text=Cama+Ortopedica',
    89990.00,
    15,
    'CHUNKY',
    'Cama de espuma viscoelastica, perfecta para perros mayores con problemas articulares.',
    'SKU-CHUNK-004',
    'ACTIVE'
);

-- Link to Subcategory: Memoria (ID should be 9)
INSERT INTO product_subcategory (id_product, id_subcategory)
SELECT id, 9 FROM product WHERE sku = 'SKU-CHUNK-004';

-- Product 5: Snacks de Salmon Naturales
INSERT INTO product (name, picture, price, stock, brand, description, sku, is_active)
VALUES (
    'Snacks de Salmon Naturales',
    'https://placehold.co/400x300/ffd8a6/000000?text=Snacks+Salmon',
    24900.00,
    60,
    'HILLS',
    '100% salmon deshidratado, sin aditivos ni conservantes.',
    'SKU-HILL-005',
    'ACTIVE'
);

-- Link to Subcategory: Natural (ID should be 3)
INSERT INTO product_subcategory (id_product, id_subcategory)
SELECT id, 3 FROM product WHERE sku = 'SKU-HILL-005';

-- =====================================================
-- SERVICES (5 items)
-- =====================================================

-- Service 1: Consulta Veterinaria General
INSERT INTO services (name, description, picture, status)
VALUES (
    'Consulta Veterinaria General',
    'Examen completo de salud, vacunación y desparasitación básica. Revisión profunda del estado físico y evaluación nutricional.',
    'https://placehold.co/600x400/A0B9E2/000000?text=Consulta+Vet',
    'ACTIVE'
);

-- Link to Veterinary Clinics (VetCare Central and Mascotas Felices)
-- Assuming veterinary_clinic IDs from previous inserts (1, 2, 3)
INSERT INTO veterinary_clinic_service (id_veterinary_clinic, id_service)
SELECT 1, id FROM services WHERE name = 'Consulta Veterinaria General';

INSERT INTO veterinary_clinic_service (id_veterinary_clinic, id_service)
SELECT 2, id FROM services WHERE name = 'Consulta Veterinaria General';

-- Service 2: Baño y Peluquería Premium
INSERT INTO services (name, description, picture, status)
VALUES (
    'Baño y Peluquería Premium',
    'Incluye corte, cepillado, limpieza de oídos, glándulas y tratamiento de hidratación con aceites esenciales.',
    'https://placehold.co/600x400/98DD98/000000?text=Grooming+Premium',
    'ACTIVE'
);

-- Link to Veterinary Clinic (Hospital Veterinario San Francisco)
INSERT INTO veterinary_clinic_service (id_veterinary_clinic, id_service)
SELECT 3, id FROM services WHERE name = 'Baño y Peluquería Premium';

-- Service 3: Entrenamiento de Obediencia
INSERT INTO services (name, description, picture, status)
VALUES (
    'Entrenamiento de Obediencia',
    'Curso intensivo de 8 sesiones para cachorros y adultos. Cubre comandos básicos (sentado, quieto, venir) y socialización guiada.',
    'https://placehold.co/600x400/FAD7A0/000000?text=Adiestramiento',
    'ACTIVE'
);

-- Link to Veterinary Clinics (VetCare Central and Mascotas Felices)
INSERT INTO veterinary_clinic_service (id_veterinary_clinic, id_service)
SELECT 1, id FROM services WHERE name = 'Entrenamiento de Obediencia';

INSERT INTO veterinary_clinic_service (id_veterinary_clinic, id_service)
SELECT 2, id FROM services WHERE name = 'Entrenamiento de Obediencia';

-- Service 4: Cirugía Programada (Menor)
INSERT INTO services (name, description, picture, status)
VALUES (
    'Cirugía Programada (Menor)',
    'Procedimientos quirúrgicos menores bajo anestesia supervisada, incluyendo monitoreo y cuidados post-operatorios iniciales.',
    'https://placehold.co/600x400/D9A9B4/000000?text=Cirugia+Vet',
    'ACTIVE'
);

-- Link to Veterinary Clinic (Hospital Veterinario San Francisco)
INSERT INTO veterinary_clinic_service (id_veterinary_clinic, id_service)
SELECT 3, id FROM services WHERE name = 'Cirugía Programada (Menor)';

-- Service 5: Fisioterapia y Rehabilitación
INSERT INTO services (name, description, picture, status)
VALUES (
    'Fisioterapia y Rehabilitación',
    'Sesiones especializadas para recuperar la movilidad tras una lesión o cirugía, utilizando hidroterapia y ejercicios asistidos.',
    'https://placehold.co/600x400/98DD98/000000?text=Fisioterapia',
    'ACTIVE'
);

-- Link to Veterinary Clinics (All three clinics offer this service)
INSERT INTO veterinary_clinic_service (id_veterinary_clinic, id_service)
SELECT 1, id FROM services WHERE name = 'Fisioterapia y Rehabilitación';

INSERT INTO veterinary_clinic_service (id_veterinary_clinic, id_service)
SELECT 2, id FROM services WHERE name = 'Fisioterapia y Rehabilitación';

INSERT INTO veterinary_clinic_service (id_veterinary_clinic, id_service)
SELECT 3, id FROM services WHERE name = 'Fisioterapia y Rehabilitación';

-- =====================================================
-- NOTES:
-- =====================================================
-- 1. IDs are auto-generated, so actual IDs may vary
-- 2. Adjust subcategory IDs if they don't match
-- 3. Adjust veterinary_clinic IDs based on previous inserts
-- 4. Execute these AFTER veterinary_clinics_inserts.sql
