USE petcare;

INSERT INTO document_type (name, abreviation) VALUES
('Cédula de Ciudadanía', 'CC'),
('Cédula de Extranjería', 'CE'),
('Tarjeta de Identidad', 'TI'),
('Pasaporte', 'PAS'),
('Registro Civil', 'RC'),
('Permiso Proteccion Temporal','PPT'),
('Numero de identificacion tributaria', 'NIT');

INSERT INTO specie (name) VALUES
('Perro'),
('Gato'),
('Ave');

INSERT INTO race (name, id_specie) VALUES
-- Razas de Perro (id_specie = 1)
('Labrador Retriever', 1),
('Bulldog Francés', 1),
('Pastor Alemán', 1),
('Golden Retriever', 1),
('Beagle', 1),
('Poodle', 1),
('Chihuahua', 1),
('Siberian Husky', 1),
('Rottweiler', 1),
('Dachshund (Perro Salchicha)', 1),
('Yorkshire Terrier', 1),
('Boxer', 1),
('Shih Tzu', 1),

-- Razas de Gato (id_specie = 2)
('Siamés', 2),
('Maine Coon', 2),
('Persa', 2),
('Ragdoll', 2),
('Sphynx', 2),
('British Shorthair', 2),
('Abisinio', 2),
('Bengalí', 2),
('Gato Común Europeo (Mestizo)', 2), -- Aunque es mestizo, es la población doméstica más común y a veces se considera una "raza"
('Scottish Fold', 2),
('Angora Turco', 2),
('Azul Ruso', 2),
('Exótico de Pelo Corto', 2),

-- Razas de Ave (Gallinas, Patos, Pavos, etc. domésticas) (id_specie = 3)
('Gallina Leghorn', 3), -- (Gallina, productora de huevos)
('Gallina Plymouth Rock', 3), -- (Gallina, doble propósito)
('Gallina Rhode Island Red', 3), -- (Gallina, doble propósito)
('Gallina Brahma', 3), -- (Gallina, carne y ornamental)
('Pato Pekín', 3), -- (Pato, carne)
('Pato Muscovy (Pato Criollo)', 3), -- (Pato)
('Pavo Bronceado de Pecho Ancho', 3), -- (Pavo, carne)
('Ganso Toulouse', 3), -- (Ganso)
('Canario Timbrado Español', 3), -- (Pájaro cantor)
('Periquito Común (Cacatúa de Cresta Amarilla)', 3), -- (Pájaro, ornamental/mascota)
('Cacatúa Ninfa (Cacatúa Carolina)', 3), -- (Pájaro, ornamental/mascota)
('Paloma Mensajera (Columba livia)', 3); -- (Paloma doméstica)

INSERT INTO locality (name) VALUES
('Usaquén'),
('Chapinero'),
('Santa Fe'),
('San Cristóbal'),
('Usme'),
('Tunjuelito'),
('Bosa'),
('Kennedy'),
('Fontibón'),
('Engativá'),
('Suba'),
('Barrios Unidos'),
('Teusaquillo'),
('Los Mártires'),
('Antonio Nariño'),
('Puente Aranda'),
('La Candelaria'),
('Rafael Uribe Uribe'),
('Ciudad Bolívar'),
('Sumapaz');

INSERT INTO neighborhood (name, id_locality) VALUES
-- Localidad 1: Usaquén (ID 1)
('Santa Bárbara', 1), ('Cedritos', 1), ('Santa Ana', 1), ('El Contador', 1),
('Verbenal', 1), ('Bella Suiza', 1), ('El Chicó Norte', 1), ('Los Rosales', 1),
('La Uribe', 1), ('San Cristóbal Norte', 1), ('Toberín', 1), ('Country Club', 1),
('Bosque de Pinos', 1), ('Barrancas', 1), ('El Codito', 1), ('La Pradera', 1),
('Tibabita', 1), ('Torca', 1), ('Las Margaritas', 1), ('El Bosque', 1),

-- Localidad 2: Chapinero (ID 2)
('Chicó', 2), ('El Nogal', 2), ('Rosales', 2), ('Quinta Camacho', 2),
('Marly', 2), ('Chapinero Central', 2), ('El Retiro', 2), ('La Cabrera', 2),
('Lago Gaitán', 2), ('Pardo Rubio', 2), ('San Isidro', 2), ('Antiguo Country', 2),
('Serranías', 2), ('Emaús', 2), ('Granada', 2), ('María Cristina', 2),
('Porciúncula', 2), ('El Refugio', 2), ('Chicó Alto', 2), ('Inglaterra', 2),

-- Localidad 3: Santa Fe (ID 3)
('La Candelaria', 3), ('Las Nieves', 3), ('La Macarena', 3), ('Las Cruces', 3),
('Bosque Izquierdo', 3), ('San Diego', 3), ('Parque Central Bavaria', 3),
('San Martín', 3), ('Torres del Parque', 3), ('Germania', 3), ('El Consuelo', 3),
('Egipto', 3), ('La Perseverancia', 3), ('San Bernardo', 3), ('Vitelma', 3),

-- Localidad 4: San Cristóbal (ID 4)
('20 de Julio', 4), ('La Gloria', 4), ('San Blas', 4), ('Los Libertadores', 4),
('El Sociego', 4), ('Altos del Zipa', 4), ('La Victoria', 4), ('Villa Javier', 4),
('Guerrero', 4), ('Montecarlo', 4), ('El Triunfo', 4), ('Moralba', 4),
('Velódromo', 4), ('Ramírez', 4), ('San Dionisio', 4),

-- Localidad 5: Usme (ID 5)
('La Aurora', 5), ('Betania', 5), ('Comuneros', 5), ('Santa Librada', 5),
('Chuniza', 5), ('El Uval', 5), ('La Fiscala', 5), ('Ciudadela Usme', 5),
('Gran Yomasa', 5), ('Alaska', 5), ('Samaná', 5), ('Tocas', 5),
('El Curubo', 5), ('El Pedregal', 5), ('Usme Pueblo', 5),

-- Localidad 6: Tunjuelito (ID 6)
('El Tunal', 6), ('Venecia', 6), ('San Benito', 6), ('Muzú', 6),
('Fátima', 6), ('Isla del Sol', 6), ('Abraham Lincoln', 6), ('Tunal Oriental', 6),
('Nuevo Muzú', 6), ('San Carlos', 6), ('Santa Lucía', 6), ('Tejar de Ontario', 6),
('Samoré', 6), ('Rincón de Venecia', 6), ('El Carmen', 6),

-- Localidad 7: Bosa (ID 7)
('Bosa Centro', 7), ('El Porvenir', 7), ('San Bernardino', 7), ('Bosa Nova', 7),
('Chicalá', 7), ('La Paz', 7), ('El Corzo', 7), ('Escocia', 7),
('La Estación', 7), ('La Libertad', 7), ('Brasil', 7), ('Villa Anny', 7),
('Holanda', 7), ('Jardines de la Libertad', 7), ('El Triunfo', 7), ('Islandia', 7),
('Antonia Santos', 7), ('San Martín', 7), ('Villa Nhora', 7), ('Carbonel', 7),

-- Localidad 8: Kennedy (ID 8)
('Timiza', 8), ('Castilla', 8), ('Patio Bonito', 8), ('Corabastos', 8),
('Las Américas', 8), ('Ciudad Kennedy Central', 8), ('El Tintal', 8),
('Britalia', 8), ('Álamos de Castilla', 8), ('Dindalito', 8), ('La Igualdad', 8),
('Marsella', 8), ('Carvajal', 8), ('Hipotecho', 8), ('Mandalay', 8),
('Nueva Marsella', 8), ('Villa Alsacia', 8), ('Banderas', 8), ('Tierra Buena', 8),
('Calandaima', 8),

-- Localidad 9: Fontibón (ID 9)
('Hayuelos', 9), ('Modelia', 9), ('Ciudad Salitre Occidental', 9),
('Capellanía', 9), ('Fontibón Centro', 9), ('Grancolombiano', 9),
('La Esperanza', 9), ('Puerta de Teja', 9), ('Villemar', 9), ('El Tintal Central', 9),
('Aeropuerto El Dorado', 9), ('La Laguna', 9), ('Puente Grande', 9),
('Bohíos de Hunza', 9), ('Zona Franca', 9),

-- Localidad 10: Engativá (ID 10)
('Quinta Mutis', 10), ('Modelia', 10), ('Engativá Pueblo', 10), ('Álamos', 10),
('El Cortijo', 10), ('Garcés Navas', 10), ('Boyacá Real', 10), ('Las Ferias', 10),
('Ciudadela Colsubsidio', 10), ('Gran Granada', 10), ('Bachué', 10),
('Florida Blanca', 10), ('Villaluz', 10), ('La Cabaña', 10), ('El Salitre', 10),

-- Localidad 11: Suba (ID 11)
('Colina Campestre', 11), ('Mazurén', 11), ('Suba Centro', 11),
('Tibabuyes', 11), ('Las Villas', 11), ('Compartir', 11), ('El Rincón', 11),
('La Gaitana', 11), ('Pontevedra', 11), ('Niza', 11), ('La Carolina', 11),
('San José de Bavaria', 11), ('El Prado', 11), ('Villa Elisa', 11),
('Bilbao', 11), ('Toscana', 11), ('Campanella', 11), ('Guaymaral', 11),
('Atenas', 11), ('Lisboa', 11),

-- Localidad 12: Barrios Unidos (ID 12)
('La Castellana', 12), ('Los Andes', 12), ('12 de Octubre', 12),
('San Fernando', 12), ('Siete de Agosto', 12), ('Chapinero Occidental', 12),
('El Polo Club', 12), ('Jardín Valparaíso', 12), ('La Patria', 12),
('Quinta Mutis', 12), ('Rionegro', 12), ('Alcázares', 12),

-- Localidad 13: Teusaquillo (ID 13)
('Teusaquillo', 13), ('La Soledad', 13), ('Park Way', 13), ('Quinta Paredes', 13),
('Salitre Greco', 13), ('Galerías', 13), ('Armenia', 13), ('Estrella', 13),
('Paloquemao', 13), ('La Esmeralda', 13), ('Belalcázar', 13), ('Ciudad Universitaria', 13),

-- Localidad 14: Los Mártires (ID 14)
('Ricaurte', 14), ('Paloquemao', 14), ('San Victorino', 14),
('La Estanzuela', 14), ('Voto Nacional', 14), ('Santa Isabel', 14),
('La Favorita', 14), ('Eduardo Santos', 14), ('Veraguas', 14), ('El Progreso', 14),
('Samper Mendoza', 14), ('Colseguros', 14),

-- Localidad 15: Antonio Nariño (ID 15)
('Ciudad Jardín Sur', 15), ('Restrepo', 15), ('Policarpa', 15),
('La Fraguita', 15), ('Luna Park', 15), ('San Antonio', 15), ('Caracas', 15),
('San Jorge Central', 15), ('Ciudad Berna', 15), ('Sevilla', 15),
('Villa Mayor', 15), ('Eduardo Frey', 15),

-- Localidad 16: Puente Aranda (ID 16)
('Puente Aranda', 16), ('Ciudad Montes', 16), ('Zona Industrial', 16),
('La Primavera', 16), ('Muzú', 16), ('Batallón Caldas', 16),
('Salazar Gómez', 16), ('Industrial Centenario', 16), ('La Guaca', 16),
('Ortezal', 16), ('San Rafael', 16), ('Gorgonzola', 16),

-- Localidad 17: La Candelaria (ID 17)
('Centro Administrativo', 17), ('Belén', 17), ('Las Aguas', 17),
('Egipto', 17), ('La Catedral', 17), ('Santa Bárbara', 17),
('La Concordia', 17), ('Nueva Santa Fe de Bogotá', 17),

-- Localidad 18: Rafael Uribe Uribe (ID 18)
('Quiroga', 18), ('Olaya', 18), ('Marco Fidel Suárez', 18),
('Gustavo Restrepo', 18), ('Bosque de San Carlos', 18), ('Diana Turbay', 18),
('Claret', 18), ('San José Sur', 18), ('Molinos', 18), ('Los Puentes', 18),
('Grnja. San Pablo', 18), ('Arrayanes', 18), ('La Paz', 18), ('Centenario', 18),

-- Localidad 19: Ciudad Bolívar (ID 19)
('Arborizadora Alta', 19), ('Lucero', 19), ('San Francisco', 19),
('El Paraíso', 19), ('Ismael Perdomo', 19), ('Caracolí', 19), ('Potosí', 19),
('El Mochuelo', 19), ('Candelaria La Nueva', 19), ('Meissen', 19),
('Jerusalén', 19), ('República de Canadá', 19), ('Madelena', 19),
('Sumapaz', 19), ('Sierra Morena', 19),

-- Localidad 20: Sumapaz (ID 20)
('San Juan', 20), ('Betania', 20), ('Chicaque', 20), ('Nazareth', 20),
('Peñalisa', 20), ('La Unión', 20), ('El Hato', 20), ('Raizal', 20),
('Laguneta', 20), ('El Tíbet', 20), ('Aposentos', 20);

-- DATOS INSERTADOS DE EMPLOYEE POR QUE AUN NO HAY FORM DE REGISTRO (EL password de estos es: 123456, ingresen con el correo que cada uno tiene)
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

INSERT INTO employee (id, employee_code, salary, cargo)
VALUES 
((SELECT id FROM user WHERE email = 'vet1@petcare.com'), 'VET001', 5000000.00, 'VETERINARIAN'),
((SELECT id FROM user WHERE email = 'vet2@petcare.com'), 'VET002', 5000000.00, 'VETERINARIAN'),
((SELECT id FROM user WHERE email = 'cashier1@petcare.com'), 'CASH001', 2500000.00, 'CASHIER'),
((SELECT id FROM user WHERE email = 'cashier2@petcare.com'), 'CASH002', 2500000.00, 'CASHIER');

-- ####INSERTCION CLINICAS VETERINARIAS####

-- Clínica 1: VetCare Central
INSERT INTO veterinary_clinic 
  (name, address, phone, email, document_number, id_document_type, puntuacion, ubicacion, horario_principal)
VALUES 
  (
    'VetCare Central',
    'Calle 45 #23-67',
    '3201234567',
    'contacto@vetcarecentral.com',
    '900123456-1',
    1,
    5.0,
    'Chapinero - Quinta Camacho',
    '{"Lunes": "8:00 AM - 6:00 PM", "Martes": "8:00 AM - 6:00 PM", "Miércoles": "8:00 AM - 6:00 PM", "Jueves": "8:00 AM - 6:00 PM", "Viernes": "8:00 AM - 6:00 PM", "Sábado": "9:00 AM - 2:00 PM", "Domingo": "Cerrado"}'
  );

-- Clínica 2: Mascotas Felices
INSERT INTO veterinary_clinic 
  (name, address, phone, email, document_number, id_document_type, puntuacion, ubicacion, horario_principal)
VALUES 
  (
    'Mascotas Felices',
    'Carrera 15 #85-24',
    '3109876543',
    'info@mascotasfelices.com',
    '900234567-2',
    1,
    5.0,
    'Usaquén - Santa Bárbara',
    '{"Lunes": "9:00 AM - 7:00 PM", "Martes": "9:00 AM - 7:00 PM", "Miércoles": "9:00 AM - 7:00 PM", "Jueves": "9:00 AM - 7:00 PM", "Viernes": "9:00 AM - 7:00 PM", "Sábado": "10:00 AM - 4:00 PM", "Domingo": "10:00 AM - 2:00 PM"}'
  );

-- Clínica 3: Hospital Veterinario San Francisco
INSERT INTO veterinary_clinic 
  (name, address, phone, email, document_number, id_document_type, puntuacion, ubicacion, horario_principal)
VALUES 
  (
    'Hospital Veterinario San Francisco',
    'Avenida 68 #42-15',
    '3157654321',
    'servicios@hosvetsanfrancisco.com',
    '900345678-3',
    1,
    5.0,
    'Engativá - Bolivia',
    '{"Lunes": "7:00 AM - 8:00 PM", "Martes": "7:00 AM - 8:00 PM", "Miércoles": "7:00 AM - 8:00 PM", "Jueves": "7:00 AM - 8:00 PM", "Viernes": "7:00 AM - 8:00 PM", "Sábado": "8:00 AM - 6:00 PM", "Domingo": "9:00 AM - 3:00 PM"}'
  );

-- ======= PRODUCTOS, SUBBCATEGORIAS, CATEGORIAS Y TABLAS INTERMEDIAS PARA FILTROS ===========
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

-- SEGUNDA TANDA DE SERVICIOS --

-- Service 6: Vacunación Completa
INSERT INTO services (name, description, picture, status)
VALUES (
    'Vacunación Completa',
    'Programa completo de vacunación para perros y gatos. Incluye vacunas esenciales contra rabia, parvovirus, moquillo y más. Protege a tu mascota contra enfermedades graves.',
    'https://placehold.co/600x400/A8D8FF/000000?text=Vacunacion',
    'ACTIVE'
);

-- Link to Veterinary Clinics (VetCare Central y Mascotas Felices)
INSERT INTO veterinary_clinic_service (id_veterinary_clinic, id_service)
SELECT 1, id FROM services WHERE name = 'Vacunación Completa';

INSERT INTO veterinary_clinic_service (id_veterinary_clinic, id_service)
SELECT 2, id FROM services WHERE name = 'Vacunación Completa';

-- Service 7: Desparasitación y Control
INSERT INTO services (name, description, picture, status)
VALUES (
    'Desparasitación y Control',
    'Tratamiento completo contra parásitos internos y externos. Incluye desparasitación oral y aplicación de pipetas antipulgas. Control preventivo trimestral recomendado.',
    'https://placehold.co/600x400/B8E8D0/000000?text=Desparasitacion',
    'ACTIVE'
);

-- Link to all three clinics
INSERT INTO veterinary_clinic_service (id_veterinary_clinic, id_service)
SELECT 1, id FROM services WHERE name = 'Desparasitación y Control';

INSERT INTO veterinary_clinic_service (id_veterinary_clinic, id_service)
SELECT 2, id FROM services WHERE name = 'Desparasitación y Control';

INSERT INTO veterinary_clinic_service (id_veterinary_clinic, id_service)
SELECT 3, id FROM services WHERE name = 'Desparasitación y Control';

-- Service 8: Limpieza Dental
INSERT INTO services (name, description, picture, status)
VALUES (
    'Limpieza Dental',
    'Profilaxis dental profesional con ultrasonido. Elimina el sarro acumulado, previene enfermedades periodontales y mejora la salud bucal de tu mascota.',
    'https://placehold.co/600x400/FFE4B5/000000?text=Limpieza+Dental',
    'ACTIVE'
);

-- Link to Hospital Veterinario San Francisco
INSERT INTO veterinary_clinic_service (id_veterinary_clinic, id_service)
SELECT 3, id FROM services WHERE name = 'Limpieza Dental';

-- Service 9: Radiografías Digitales
INSERT INTO services (name, description, picture, status)
VALUES (
    'Radiografías Digitales',
    'Estudios radiográficos de última generación para diagnóstico preciso de fracturas, problemas articulares y enfermedades internas. Resultados inmediatos.',
    'https://placehold.co/600x400/E8C8FF/000000?text=Radiografias',
    'ACTIVE'
);

-- Link to Hospital Veterinario San Francisco
INSERT INTO veterinary_clinic_service (id_veterinary_clinic, id_service)
SELECT 3, id FROM services WHERE name = 'Radiografías Digitales';

-- Service 10: Ecografía Veterinaria
INSERT INTO services (name, description, picture, status)
VALUES (
    'Ecografía Veterinaria',
    'Ultrasonido de alta resolución para evaluación de órganos internos, diagnóstico de embarazo y detección temprana de patologías abdominales y cardiacas.',
    'https://placehold.co/600x400/D0F0E8/000000?text=Ecografia',
    'ACTIVE'
);

-- Link to VetCare Central y Hospital Veterinario San Francisco
INSERT INTO veterinary_clinic_service (id_veterinary_clinic, id_service)
SELECT 1, id FROM services WHERE name = 'Ecografía Veterinaria';

INSERT INTO veterinary_clinic_service (id_veterinary_clinic, id_service)
SELECT 3, id FROM services WHERE name = 'Ecografía Veterinaria';

-- Service 11: Análisis de Laboratorio
INSERT INTO services (name, description, picture, status)
VALUES (
    'Análisis de Laboratorio',
    'Pruebas completas de sangre, orina y heces. Hemograma, perfil bioquímico, análisis hormonales y más. Resultados confiables para un diagnóstico certero.',
    'https://placehold.co/600x400/FFC8D8/000000?text=Laboratorio',
    'ACTIVE'
);

-- Link to all three clinics
INSERT INTO veterinary_clinic_service (id_veterinary_clinic, id_service)
SELECT 1, id FROM services WHERE name = 'Análisis de Laboratorio';

INSERT INTO veterinary_clinic_service (id_veterinary_clinic, id_service)
SELECT 2, id FROM services WHERE name = 'Análisis de Laboratorio';

INSERT INTO veterinary_clinic_service (id_veterinary_clinic, id_service)
SELECT 3, id FROM services WHERE name = 'Análisis de Laboratorio';

-- Service 12: Hospitalización 24/7
INSERT INTO services (name, description, picture, status)
VALUES (
    'Hospitalización 24/7',
    'Atención veterinaria continua las 24 horas del día. Área de cuidados intensivos equipada con oxigenoterapia, fluidoterapia y monitoreo constante para pacientes críticos.',
    'https://placehold.co/600x400/F0D8A8/000000?text=Hospitalizacion',
    'ACTIVE'
);

-- Link to Hospital Veterinario San Francisco
INSERT INTO veterinary_clinic_service (id_veterinary_clinic, id_service)
SELECT 3, id FROM services WHERE name = 'Hospitalización 24/7';

-- Service 13: Corte de Uñas y Limpieza de Oídos
INSERT INTO services (name, description, picture, status)
VALUES (
    'Corte de Uñas y Limpieza de Oídos',
    'Servicio de mantenimiento básico esencial para la salud de tu mascota. Incluye corte profesional de uñas y limpieza profunda de oídos para prevenir infecciones.',
    'https://placehold.co/600x400/C8E8FF/000000?text=Corte+Unhas',
    'ACTIVE'
);

-- Link to Mascotas Felices
INSERT INTO veterinary_clinic_service (id_veterinary_clinic, id_service)
SELECT 2, id FROM services WHERE name = 'Corte de Uñas y Limpieza de Oídos';

-- Service 14: Esterilización (Castración)
INSERT INTO services (name, description, picture, status)
VALUES (
    'Esterilización (Castración)',
    'Cirugía de esterilización segura y profesional para perros y gatos. Previene enfermedades reproductivas, reduce comportamientos no deseados y contribuye al control poblacional.',
    'https://placehold.co/600x400/E8D0C8/000000?text=Esterilizacion',
    'ACTIVE'
);

-- Link to VetCare Central y Hospital Veterinario San Francisco
INSERT INTO veterinary_clinic_service (id_veterinary_clinic, id_service)
SELECT 1, id FROM services WHERE name = 'Esterilización (Castración)';

INSERT INTO veterinary_clinic_service (id_veterinary_clinic, id_service)
SELECT 3, id FROM services WHERE name = 'Esterilización (Castración)';

-- Service 15: Terapia de Comportamiento
INSERT INTO services (name, description, picture, status)
VALUES (
    'Terapia de Comportamiento',
    'Consulta especializada para problemas de conducta: ansiedad por separación, agresividad, miedos y fobias. Plan personalizado con técnicas de modificación de comportamiento.',
    'https://placehold.co/600x400/D8C8E8/000000?text=Terapia+Comportamiento',
    'ACTIVE'
);

-- Link to Mascotas Felices
INSERT INTO veterinary_clinic_service (id_veterinary_clinic, id_service)
SELECT 2, id FROM services WHERE name = 'Terapia de Comportamiento';

-- NUEVOS EMPLEADOS Y HORARIOS PARA TODOS LOS EMPLEADOS
-- NUEVOS EMPLEADOS Y NUEVOS HORARIOS PARA LOS MISMOS--

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

-- Inserciones para la tabla 'vaccine'
INSERT INTO vaccine (name, description, manufacturer, recommended_age_months, validity_months) VALUES 
('Rabia', 'Vacuna obligatoria contra la rabia para perros y gatos.', 'Zoetis', 3, 12),
('Múltiple Canina (DHPP)', 'Protege contra Distemper, Hepatitis, Parvovirus y Parainfluenza.', 'Boehringer Ingelheim', 2, 12),
('Leucemia Felina (FeLV)', 'Vacuna contra el virus de la leucemia felina.', 'Virbac', 3, 12),
('Tos de las Perreras (Bordetella)', 'Previene la traqueobronquitis infecciosa canina.', 'MSD Animal Health', 2, 6),
('Triple Felina (FVRCP)', 'Protege contra Rinotraqueitis, Calicivirus y Panleucopenia.', 'Merck', 2, 12);

-- Inserciones para la tabla 'conditions'
INSERT INTO conditions (name, description, icon) VALUES 
('Alergia Alimentaria', 'Reacción adversa a ciertos ingredientes en la comida.', '🥜'),
('Artritis', 'Inflamación de las articulaciones que causa dolor y rigidez.', '🦴'),
('Otitis Externa', 'Inflamación del canal auditivo externo.', '👂'),
('Diabetes Mellitus', 'Enfermedad crónica que afecta la capacidad de procesar azúcar en sangre.', '🩸'),
('Dermatitis Atópica', 'Inflamación crónica de la piel causada por alérgenos ambientales.', '🌸');

-- Inserciones para la tabla 'recipe' (Medicamentos base)
INSERT INTO recipe (medication_name, description, dosage_instructions, side_effects, contraindications) VALUES 
('Amoxicilina + Ácido Clavulánico', 'Antibiótico de amplio espectro para infecciones bacterianas.', 'Administrar con comida para evitar malestar estomacal. Completar todo el tratamiento.', 'Diarrea, vómitos, pérdida de apetito.', 'No usar en animales con hipersensibilidad a penicilinas.'),
('Carprofeno', 'Antiinflamatorio no esteroideo (AINE) para el dolor y la inflamación.', 'Dar una vez al día, preferiblemente por la mañana.', 'Vómitos, heces oscuras, letargo.', 'No usar en gatos ni en perros con problemas hepáticos o renales.'),
('Prednisolona', 'Corticosteroide para tratar inflamaciones y alergias.', 'Seguir estrictamente la pauta de reducción de dosis indicada por el veterinario.', 'Aumento de sed y orina, jadeo, aumento de apetito.', 'Evitar en animales con infecciones fúngicas sistémicas.'),
('Metronidazol', 'Antibiótico y antiparasitario para infecciones gastrointestinales.', 'Administrar directamente en la boca, tiene sabor amargo.', 'Salivación excesiva, náuseas, fatiga.', 'Precaución en animales gestantes o lactantes.'),
('Omeprazol', 'Protector gástrico para reducir la producción de ácido estomacal.', 'Administrar 30 minutos antes de la primera comida del día.', 'Rara vez causa efectos adversos, posible diarrea leve.', 'No hay contraindicaciones graves conocidas.');






