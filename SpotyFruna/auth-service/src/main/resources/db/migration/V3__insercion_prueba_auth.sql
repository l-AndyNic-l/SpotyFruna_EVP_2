-- Insertar 20 registros de autenticación (auth)
-- Usando los IDs de usuario del 1 al 20 (asumiendo que se insertaron en ese orden)
-- Estados: 1=activo, 2=inactivo, 3=suspendido, 4=bloqueado

INSERT INTO auth (usuario, id_estado, token, fecha_registro, fecha_expiracion) VALUES
-- USUARIOS ACTIVOS (estado 1) - fecha_expiracion NULL o futura
(1, 1, 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxIiwibmFtZSI6IlNhYnJpbmEgQ2FycGVudGVyIn0.abc123token1', '2026-05-16 08:00:00', NULL),
(2, 1, 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIyIiwibmFtZSI6IlRheWxvciBTd2lmdCJ9.xyz789token2', '2026-05-16 09:30:00', DATE_ADD(NOW(), INTERVAL 7 DAY)),
(3, 1, 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIzIiwibmFtZSI6IkJhZCBCdW5ueSJ9.def456token3', '2026-05-16 10:15:00', NULL),
(4, 1, 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI0IiwibmFtZSI6Ikthcm9sIEdpcmFsZG8ifQ.ghi789token4', '2026-05-16 11:00:00', DATE_ADD(NOW(), INTERVAL 14 DAY)),

-- USUARIOS INACTIVOS (estado 2) - fecha_expiracion pasada o NULL
(5, 2, 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI1IiwibmFtZSI6IkVkIFNoZWVyYW4ifQ.jkl012token5', '2026-05-15 14:20:00', '2026-05-15 23:59:59'),
(6, 2, 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI2IiwibmFtZSI6IkJpbGxpZSBFaWxpc2gifQ.mno345token6', '2026-05-14 16:45:00', '2026-05-14 20:30:00'),
(7, 2, 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI3IiwibmFtZSI6IkRyYWtlIEF1YnJleSJ9.pqr678token7', '2026-05-13 09:00:00', '2026-05-13 18:00:00'),
(8, 2, 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI4IiwibmFtZSI6IkFyaWFuYSBHcmFuZGUifQ.stu901token8', '2026-05-12 12:00:00', NULL),

-- USUARIOS SUSPENDIDOS (estado 3) - siempre tienen fecha_expiracion
(9, 3, 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI5IiwibmFtZSI6IlRoZSBXZWVrbmQifQ.vwx234token9', '2026-05-10 08:00:00', '2026-05-25 23:59:59'),
(10, 3, 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMCIsIm5hbWUiOiJTaGFraXJhIE1lYmFyYWsifQ.yzabc345token10', '2026-05-09 15:30:00', '2026-05-30 23:59:59'),
(11, 3, 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMSIsIm5hbWUiOiJDYXJsb3MgTG9wZXoifQ.def678token11', '2026-05-08 10:00:00', '2026-06-01 23:59:59'),
(12, 3, 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMiIsIm5hbWUiOiJNYXJpYSBGZXJuYW5kZXoifQ.ghi901token12', '2026-05-07 14:00:00', '2026-05-28 23:59:59'),

-- USUARIOS BLOQUEADOS (estado 4) - siempre tienen fecha_expiracion
(13, 4, 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMyIsIm5hbWUiOiJKYXZpZXIgUm9kcmlndWV6In0.jkl234token13', '2026-05-06 09:15:00', '2026-06-15 23:59:59'),
(14, 4, 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxNCIsIm5hbWUiOiJMdXVyYSBHb21leiJ9.mno567token14', '2026-05-05 11:45:00', '2026-06-10 23:59:59'),
(15, 4, 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxNSIsIm5hbWUiOiJBbmRyZXMgTWFydGluZXoifQ.pqr890token15', '2026-05-04 16:20:00', '2026-05-31 23:59:59'),
(16, 4, 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxNiIsIm5hbWUiOiJWYWxlbnRpbmEgRGlheiJ9.stu123token16', '2026-05-03 08:30:00', '2026-06-20 23:59:59'),

-- MÁS USUARIOS ACTIVOS (estado 1)
(17, 1, 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxNyIsIm5hbWUiOiJTZWJhc3RpYW4gUm9qYXMifQ.vwx456token17', '2026-05-16 12:00:00', NULL),
(18, 1, 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxOCIsIm5hbWUiOiJDYW1pbGEgU2FuY2hleiJ9.xyz789token18', '2026-05-16 13:30:00', DATE_ADD(NOW(), INTERVAL 5 DAY)),
(19, 2, 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxOSIsIm5hbWUiOiJGZWxpcGUgSGVycmVyYSJ9.abc012token19', '2026-05-14 10:00:00', '2026-05-14 22:00:00'),
(20, 4, 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIyMCIsIm5hbWUiOiJEYW5pZWxhIENhc3RybyJ9.def345token20', '2026-05-02 09:00:00', '2026-06-05 23:59:59');