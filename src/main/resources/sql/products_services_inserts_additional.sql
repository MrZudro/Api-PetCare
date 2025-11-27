-- =====================================================
-- SQL INSERT STATEMENTS FOR ADDITIONAL SERVICES
-- 10 servicios adicionales distribuidos entre las 3 clínicas veterinarias
-- =====================================================

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

-- =====================================================
-- RESUMEN DE DISTRIBUCIÓN:
-- =====================================================
-- VetCare Central (ID 1): 4 servicios nuevos
-- - Vacunación Completa
-- - Desparasitación y Control
-- - Ecografía Veterinaria
-- - Análisis de Laboratorio
-- - Esterilización (Castración)
--
-- Mascotas Felices (ID 2): 4 servicios nuevos
-- - Vacunación Completa
-- - Desparasitación y Control
-- - Análisis de Laboratorio
-- - Corte de Uñas y Limpieza de Oídos
-- - Terapia de Comportamiento
--
-- Hospital Veterinario San Francisco (ID 3): 7 servicios nuevos
-- - Desparasitación y Control
-- - Limpieza Dental
-- - Radiografías Digitales
-- - Ecografía Veterinaria
-- - Análisis de Laboratorio
-- - Hospitalización 24/7
-- - Esterilización (Castración)
-- =====================================================
