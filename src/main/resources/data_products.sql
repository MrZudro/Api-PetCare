-- Insertar Categorías (si no existen)
INSERT INTO category (name) SELECT 'Alimentos' WHERE NOT EXISTS (SELECT 1 FROM category WHERE name = 'Alimentos');
INSERT INTO category (name) SELECT 'Juguetes' WHERE NOT EXISTS (SELECT 1 FROM category WHERE name = 'Juguetes');
INSERT INTO category (name) SELECT 'Accesorios' WHERE NOT EXISTS (SELECT 1 FROM category WHERE name = 'Accesorios');
INSERT INTO category (name) SELECT 'Salud' WHERE NOT EXISTS (SELECT 1 FROM category WHERE name = 'Salud');
INSERT INTO category (name) SELECT 'Higiene' WHERE NOT EXISTS (SELECT 1 FROM category WHERE name = 'Higiene');

-- Insertar Subcategorías
-- Alimentos
INSERT INTO subcategory (name, categoria_id) 
SELECT 'Perro Adulto', id FROM category WHERE name = 'Alimentos'
AND NOT EXISTS (SELECT 1 FROM subcategory WHERE name = 'Perro Adulto');

INSERT INTO subcategory (name, categoria_id) 
SELECT 'Gato Adulto', id FROM category WHERE name = 'Alimentos'
AND NOT EXISTS (SELECT 1 FROM subcategory WHERE name = 'Gato Adulto');

-- Juguetes
INSERT INTO subcategory (name, categoria_id) 
SELECT 'Pelotas', id FROM category WHERE name = 'Juguetes'
AND NOT EXISTS (SELECT 1 FROM subcategory WHERE name = 'Pelotas');

-- Accesorios
INSERT INTO subcategory (name, categoria_id) 
SELECT 'Collares', id FROM category WHERE name = 'Accesorios'
AND NOT EXISTS (SELECT 1 FROM subcategory WHERE name = 'Collares');

-- Salud
INSERT INTO subcategory (name, categoria_id) 
SELECT 'Vitaminas', id FROM category WHERE name = 'Salud'
AND NOT EXISTS (SELECT 1 FROM subcategory WHERE name = 'Vitaminas');

-- Insertar Productos
INSERT INTO product (name, picture, price, stock, brand, description, sku, is_active) 
SELECT 'Chunky Adulto Pollo', 'https://res.cloudinary.com/dvvhnrvav/image/upload/v1732338000/chunky_pollo_adulto.jpg', 55000.00, 50, 'Chunky', 'Alimento premium para perros adultos sabor a pollo.', 'CHK-001', 'ACTIVE'
WHERE NOT EXISTS (SELECT 1 FROM product WHERE sku = 'CHK-001');

INSERT INTO product (name, picture, price, stock, brand, description, sku, is_active) 
SELECT 'Whiskas Carne', 'https://res.cloudinary.com/dvvhnrvav/image/upload/v1732338000/whiskas_carne.jpg', 45000.00, 40, 'Whiskas', 'Alimento húmedo para gatos sabor carne.', 'WSK-002', 'ACTIVE'
WHERE NOT EXISTS (SELECT 1 FROM product WHERE sku = 'WSK-002');

INSERT INTO product (name, picture, price, stock, brand, description, sku, is_active) 
SELECT 'Pelota de Goma', 'https://res.cloudinary.com/dvvhnrvav/image/upload/v1732338000/pelota_goma.jpg', 15000.00, 100, 'PetToy', 'Pelota resistente para morder.', 'TOY-003', 'ACTIVE'
WHERE NOT EXISTS (SELECT 1 FROM product WHERE sku = 'TOY-003');

INSERT INTO product (name, picture, price, stock, brand, description, sku, is_active) 
SELECT 'Collar Reflectivo', 'https://res.cloudinary.com/dvvhnrvav/image/upload/v1732338000/collar_reflectivo.jpg', 25000.00, 30, 'SafePet', 'Collar ajustable con banda reflectiva para paseos nocturnos.', 'ACC-004', 'ACTIVE'
WHERE NOT EXISTS (SELECT 1 FROM product WHERE sku = 'ACC-004');

INSERT INTO product (name, picture, price, stock, brand, description, sku, is_active) 
SELECT 'Multivitamínico Canino', 'https://res.cloudinary.com/dvvhnrvav/image/upload/v1732338000/vitaminas_canino.jpg', 35000.00, 20, 'VetLife', 'Suplemento vitamínico para fortalecer el sistema inmune.', 'HLT-005', 'ACTIVE'
WHERE NOT EXISTS (SELECT 1 FROM product WHERE sku = 'HLT-005');

-- Relacionar Productos con Subcategorías (Tabla intermedia product_subcategory)
-- Chunky -> Perro Adulto
INSERT INTO product_subcategory (id_product, id_subcategory)
SELECT p.id, s.id 
FROM product p, subcategory s 
WHERE p.sku = 'CHK-001' AND s.name = 'Perro Adulto'
AND NOT EXISTS (SELECT 1 FROM product_subcategory ps WHERE ps.id_product = p.id AND ps.id_subcategory = s.id);

-- Whiskas -> Gato Adulto
INSERT INTO product_subcategory (id_product, id_subcategory)
SELECT p.id, s.id 
FROM product p, subcategory s 
WHERE p.sku = 'WSK-002' AND s.name = 'Gato Adulto'
AND NOT EXISTS (SELECT 1 FROM product_subcategory ps WHERE ps.id_product = p.id AND ps.id_subcategory = s.id);

-- Pelota -> Pelotas
INSERT INTO product_subcategory (id_product, id_subcategory)
SELECT p.id, s.id 
FROM product p, subcategory s 
WHERE p.sku = 'TOY-003' AND s.name = 'Pelotas'
AND NOT EXISTS (SELECT 1 FROM product_subcategory ps WHERE ps.id_product = p.id AND ps.id_subcategory = s.id);

-- Collar -> Collares
INSERT INTO product_subcategory (id_product, id_subcategory)
SELECT p.id, s.id 
FROM product p, subcategory s 
WHERE p.sku = 'ACC-004' AND s.name = 'Collares'
AND NOT EXISTS (SELECT 1 FROM product_subcategory ps WHERE ps.id_product = p.id AND ps.id_subcategory = s.id);

-- Vitaminas -> Vitaminas
INSERT INTO product_subcategory (id_product, id_subcategory)
SELECT p.id, s.id 
FROM product p, subcategory s 
WHERE p.sku = 'HLT-005' AND s.name = 'Vitaminas'
AND NOT EXISTS (SELECT 1 FROM product_subcategory ps WHERE ps.id_product = p.id AND ps.id_subcategory = s.id);
