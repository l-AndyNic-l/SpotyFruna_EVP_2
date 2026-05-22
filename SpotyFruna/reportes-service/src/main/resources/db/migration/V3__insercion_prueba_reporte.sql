-- Insertar 20 reportes con usuarios del 1 al 20
INSERT INTO REPORTE (administrador, usuario, descripcion, fecha_enviado, fecha_resuelto, id_tipo_reporte, id_estado) VALUES
-- Reportes de Error (id_tipo_reporte = 1)
(1, 1, 'Error al intentar reproducir canciones en modo offline', '2024-01-10', '2024-01-12', 1, 3),
(2, 2, 'La aplicación se cierra inesperadamente al abrirla', '2024-01-15', NULL, 1, 2),
(1, 3, 'Error 404 al intentar descargar el reporte semanal', '2024-01-20', '2024-01-21', 1, 3),
(3, 4, 'Problema con la sincronización de playlists entre dispositivos', '2024-01-25', NULL, 1, 1),
(2, 5, 'Error al procesar el pago de suscripción premium', '2024-02-01', '2024-02-03', 1, 3),
(1, 6, 'Bug en el contador de reproducciones de canciones', '2024-02-05', NULL, 1, 2),
(3, 7, 'No se cargan las imágenes de los álbumes', '2024-02-10', '2024-02-11', 1, 3),

-- Reportes de Queja (id_tipo_reporte = 2)
(2, 8, 'Mala calidad de audio en las canciones en streaming', '2024-01-12', '2024-01-15', 2, 3),
(1, 9, 'Tiempo de espera excesivo para soporte técnico', '2024-01-18', '2024-01-20', 2, 3),
(3, 10, 'Publicidad excesiva en versión gratuita', '2024-01-22', NULL, 2, 1),
(2, 11, 'Interfaz poco intuitiva en la versión móvil', '2024-01-28', '2024-01-30', 2, 3),
(1, 12, 'Cancelación de suscripción no procesada correctamente', '2024-02-03', '2024-02-05', 2, 3),
(3, 13, 'Recomendaciones musicales irrelevantes', '2024-02-08', NULL, 2, 2),

-- Reportes de Consulta (id_tipo_reporte = 3)
(2, 14, '¿Cómo puedo transferir mi playlist a otro usuario?', '2024-01-05', '2024-01-07', 3, 3),
(1, 15, '¿Cuál es la política de reembolso del plan anual?', '2024-01-14', '2024-01-15', 3, 3),
(3, 16, '¿Cómo funciona el sistema de recomendaciones?', '2024-01-19', '2024-01-20', 3, 3),
(2, 17, 'Consulta sobre uso de datos en streaming', '2024-01-24', NULL, 3, 1),
(1, 18, '¿Puedo compartir mi cuenta con familiares?', '2024-02-02', '2024-02-03', 3, 3),

-- Reportes de Solicitud (id_tipo_reporte = 4)
(3, 19, 'Solicito función de reproducción con letras en tiempo real', '2024-01-08', '2024-01-10', 4, 3),
(2, 20, 'Solicito integración con redes sociales', '2024-01-17', NULL, 4, 2),
(1, 1, 'Solicitar modo oscuro para la aplicación', '2024-01-23', '2024-01-25', 4, 3),
(3, 2, 'Solicito estadísticas detalladas de mis hábitos de escucha', '2024-01-29', NULL, 4, 1),
(2, 3, 'Solicito poder editar metadatos de mis canciones', '2024-02-04', '2024-02-06', 4, 3);