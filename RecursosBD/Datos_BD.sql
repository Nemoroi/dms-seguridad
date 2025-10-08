USE DMS_Seguridad;

-- ===========================================
-- INSERTAR ROLES
-- ===========================================
INSERT INTO Rol (Nombre_Rol, Descripcion) VALUES
('Admin', 'Administrador con acceso completo'),
('ManagerSSOMA', 'Manager de seguridad y salud ocupacional'),
('UsuarioReportador', 'Usuario que registra reportes'),
('UsuarioTecnico', 'Usuario que ejecuta planes de acción');

-- ===========================================
-- INSERTAR USUARIOS
-- (Clave simulada, en real debería ir hasheada)
-- ===========================================
INSERT INTO Usuario (Nombre_Completo, Usuario, Clave, Email, Telefono, RolID) VALUES
('Carlos Pérez', 'cperez', '1234', 'cperez@empresa.com', '987654321', 1), -- Admin
('Laura Gómez', 'lgomez', '1234', 'lgomez@empresa.com', '987654322', 2), -- ManagerSSOMA
('Juan Torres', 'jtorres', '1234', 'jtorres@empresa.com', '987654323', 3), -- Reportador
('María Ruiz', 'mruiz', '1234', 'mruiz@empresa.com', '987654324', 4); -- Técnico

-- ===========================================
-- INSERTAR ÁREAS
-- ===========================================
INSERT INTO Area (NombreArea, JefeID) VALUES
('Producción', 1),  -- Carlos Pérez jefe
('Mantenimiento', 2); -- Laura Gómez jefa

-- ===========================================
-- INSERTAR ZONAS
-- ===========================================
INSERT INTO Zona (NombreZona, AreaID) VALUES
('Zona Norte', 1),
('Zona Sur', 1),
('Zona Taller', 2);

-- ===========================================
-- INSERTAR VISTAS
-- ===========================================
INSERT INTO Vistas (Nombre_Vista, Descripcion, Ruta) VALUES
('Usuarios', 'Módulo para mantenimiento de Usuarios', '/usuarios/listar'),
('Roles', 'Módulo para mantenimiento de Roles', '/roles/listar'),
('Vistas', 'Módulo para mantenimiento de vistas', '/vistas/listar'),
('Asignacion de Vistas', 'Administración Vistas', '/rol_vistas/listar');

-- ===========================================
-- ASIGNAR VISTAS A ROLES
-- ===========================================
INSERT INTO Rol_Vista (RolID, VistaID, Permisos) VALUES
(1, 1, 'Admin'),   -- Admin tiene dashboard admin
(1, 2, 'Admin'),   -- Admin ve todo
(1, 3, 'Admin'),
(1, 4, 'Admin'),
(2, 2, 'Escritura'), -- Manager puede crear reportes
(2, 3, 'Escritura'), -- Manager puede asignar planes
(3, 2, 'Escritura'), -- Reportador puede crear reportes
(4, 3, 'Escritura'); -- Técnico puede ejecutar planes

-- ===========================================
-- INSERTAR REPORTES
-- ===========================================
INSERT INTO Reporte (UsuarioID, AreaID, ZonaID, ManagerID, Titulo, Descripcion) VALUES
(3, 1, 1, 2, 'Fuga de aceite en máquina', 'Se detectó fuga en la prensa hidráulica en zona norte.'),
(3, 1, 2, 2, 'Obstrucción en pasillo', 'Un palet bloquea la salida de emergencia en zona sur.');

-- ===========================================
-- INSERTAR REUNIONES
-- ===========================================
INSERT INTO Reunion (ReporteID, ManagerID, Observaciones) VALUES
(1, 2, 'Se revisó la fuga de aceite, requiere plan de acción'),
(2, 2, 'Se discutió la obstrucción, requiere acciones correctivas');

-- ===========================================
-- INSERTAR PLANES DE ACCIÓN
-- ===========================================
INSERT INTO PlanAccion (ReunionID, Descripcion, ManagerID, ResponsableID, FechaCompromiso) VALUES
(1, 'Cambiar manguera hidráulica y limpiar área afectada', 2, 4, '2025-10-05'),
(2, 'Retirar palet y señalizar pasillo', 2, 4, '2025-10-07');

-- ===========================================
-- ACTUALIZAR PLANES DE ACCIÓN (ejecución de uno)
-- ===========================================
UPDATE PlanAccion
SET Estado = 'Ejecutado',
    FechaEjecucion = NOW(),
    DescripcionEjecucion = 'Se retiró el palet y se colocaron señales de seguridad',
    Evidencia = 'evidencia_palet.jpg'
WHERE PlanID = 2;

-- ===========================================
-- INSERTAR HISTORIAL DE CAMBIOS
-- ===========================================
INSERT INTO PlanAccion_Historial (PlanID, UsuarioID, EstadoAnterior, EstadoNuevo, Comentario) VALUES
(1, 4, 'Pendiente', 'En Proceso', 'Se inició la reparación de la manguera'),
(2, 4, 'Pendiente', 'Ejecutado', 'Palet retirado y señalización realizada');
