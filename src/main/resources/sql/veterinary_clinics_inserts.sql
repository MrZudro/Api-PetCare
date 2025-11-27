-- =====================================================
-- SQL INSERT STATEMENTS FOR VETERINARY CLINICS
-- Incluye los tres nuevos campos: puntuacion, ubicacion, horarioPrincipal
-- =====================================================

-- NOTA: Asegúrese de tener el id_document_type correcto en su base de datos
-- En este ejemplo, asumimos que existe un DocumentType con id = 1 (ej: NIT)

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

-- =====================================================
-- NOTAS DE USO:
-- =====================================================
-- 1. Verifique el valor de 'id_document_type' en su tabla 'document_type'
--    y ajuste los valores si es necesario
-- 2. El campo 'puntuacion' inicia en 5.0 por defecto
-- 3. El campo 'ubicacion' usa el formato: "Localidad - Barrio"
-- 4. El campo 'horario_principal' es un JSON con los 7 días de la semana
-- 5. Puede ejecutar estos INSERT después de agregar las columnas a la BD
