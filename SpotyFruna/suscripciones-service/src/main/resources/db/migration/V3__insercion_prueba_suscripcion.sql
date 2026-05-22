-- Insertar 10 suscripciones para usuarios del 1 al 10
INSERT INTO SUSCRIPCION (fecha_inicio, fecha_termino, activado, id_plan, id_usuario) VALUES
                                                                                         ('2024-01-01', '2025-01-01', TRUE, 3, 1),   -- Usuario 1: Premium (anual)
                                                                                         ('2024-01-15', '2024-07-15', TRUE, 2, 2),   -- Usuario 2: Básico (6 meses)
                                                                                         ('2024-02-01', '2025-02-01', TRUE, 4, 3),   -- Usuario 3: Familiar (anual)
                                                                                         ('2024-02-10', '2024-08-10', FALSE, 1, 4),  -- Usuario 4: Gratuito (inactivo)
                                                                                         ('2024-03-01', '2024-09-01', TRUE, 2, 5),   -- Usuario 5: Básico (6 meses)
                                                                                         ('2024-03-15', '2025-03-15', TRUE, 3, 6),   -- Usuario 6: Premium (anual)
                                                                                         ('2024-04-01', '2024-10-01', TRUE, 4, 7),   -- Usuario 7: Familiar (6 meses)
                                                                                         ('2024-04-15', '2024-07-15', FALSE, 1, 8),  -- Usuario 8: Gratuito (inactivo)
                                                                                         ('2024-05-01', '2025-05-01', TRUE, 3, 9),   -- Usuario 9: Premium (anual)
                                                                                         ('2024-05-15', '2024-11-15', TRUE, 2, 10); -- Usuario 10: Básico (6 meses)