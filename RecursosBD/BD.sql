-- ===========================================
-- CREACIÓN DE BASE DE DATOS
-- ===========================================
CREATE DATABASE IF NOT EXISTS DMS_Seguridad;
USE DMS_Seguridad;

-- ===========================================
-- TABLA ROL
-- ===========================================
CREATE TABLE Rol (
    RolID INT AUTO_INCREMENT PRIMARY KEY,
    Nombre_Rol VARCHAR(50) UNIQUE NOT NULL,
    Descripcion TEXT
);

-- ===========================================
-- TABLA USUARIO
-- ===========================================
CREATE TABLE Usuario (
    UsuarioID INT AUTO_INCREMENT PRIMARY KEY,
    Nombre_Completo VARCHAR(100) NOT NULL,
    Usuario VARCHAR(50) UNIQUE NOT NULL,
    Clave VARCHAR(255) NOT NULL,         -- guardar hasheada
    Email VARCHAR(100) UNIQUE,
    Telefono VARCHAR(20),
    RolID INT NOT NULL,
    Estado ENUM('Activo','Inactivo') DEFAULT 'Activo',
    Fecha_Registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (RolID) REFERENCES Rol(RolID)
);

-- ===========================================
-- TABLA AREA
-- ===========================================
CREATE TABLE Area (
    AreaID INT AUTO_INCREMENT PRIMARY KEY,
    Nombre_Area VARCHAR(100) NOT NULL,
    JefeID INT NULL,                -- Usuario jefe del área
    FOREIGN KEY (JefeID) REFERENCES Usuario(UsuarioID)
);

-- ===========================================
-- TABLA ZONA
-- ===========================================
CREATE TABLE Zona (
    ZonaID INT AUTO_INCREMENT PRIMARY KEY,
    Nombre_Zona VARCHAR(100) NOT NULL,
    AreaID INT NOT NULL,
    FOREIGN KEY (AreaID) REFERENCES Area(AreaID)
);

-- ===========================================
-- TABLA VISTAS (módulos accesibles)
-- ===========================================
CREATE TABLE Vistas (
    VistaID INT AUTO_INCREMENT PRIMARY KEY,
    Nombre_Vista VARCHAR(100) NOT NULL,
    Descripcion TEXT,
	Ruta TEXT
);

-- ===========================================
-- TABLA DE ASIGNACIÓN DE VISTAS A ROLES
-- ===========================================
CREATE TABLE Rol_Vista (
    Rol_VistaID INT AUTO_INCREMENT PRIMARY KEY,
    RolID INT NOT NULL,
    VistaID INT NOT NULL,
    Permisos ENUM('Lectura','Escritura','Admin') DEFAULT 'Lectura',
    Fecha_Asignacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (RolID) REFERENCES Rol(RolID),
    FOREIGN KEY (VistaID) REFERENCES Vistas(VistaID),
    UNIQUE (RolID, VistaID) -- evita duplicados
);

-- ===========================================
-- TABLA REPORTES
-- ===========================================
CREATE TABLE Reporte (
    ReporteID INT AUTO_INCREMENT PRIMARY KEY,
    UsuarioID INT NOT NULL,             -- quien reporta
    AreaID INT NOT NULL,
    ZonaID INT NOT NULL,
    ManagerID INT NOT NULL,             -- manager SSOMA revisor
    Titulo VARCHAR(150) NOT NULL,
    Descripcion TEXT NOT NULL,
    Fecha_Reporte TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    Estado ENUM('Abierto','En Revisión','Con Planes de Acción','Cerrado') DEFAULT 'Abierto',
    FOREIGN KEY (UsuarioID) REFERENCES Usuario(UsuarioID),
    FOREIGN KEY (AreaID) REFERENCES Area(AreaID),
    FOREIGN KEY (ZonaID) REFERENCES Zona(ZonaID),
    FOREIGN KEY (ManagerID) REFERENCES Usuario(UsuarioID)
);

-- ===========================================
-- TABLA REUNIONES
-- ===========================================
CREATE TABLE Reunion (
    ReunionID INT AUTO_INCREMENT PRIMARY KEY,
    ReporteID INT NOT NULL,
    ManagerID INT NOT NULL,             -- quién dirige la reunión
    FechaReunion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    Observaciones TEXT,
    FOREIGN KEY (ReporteID) REFERENCES Reporte(ReporteID),
    FOREIGN KEY (ManagerID) REFERENCES Usuario(UsuarioID)
);

-- ===========================================
-- TABLA PLANES DE ACCIÓN
-- ===========================================
CREATE TABLE PlanAccion (
    PlanID INT AUTO_INCREMENT PRIMARY KEY,
    ReunionID INT NOT NULL,             -- pertenece a una reunión
    Descripcion TEXT NOT NULL,          -- acción a ejecutar

    ManagerID INT NOT NULL,             -- manager asignado/creador
    ResponsableID INT NOT NULL,         -- usuario encargado de ejecutar

    FechaCreacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FechaCompromiso DATE,               -- fecha límite

    -- datos de ejecución
    FechaEjecucion TIMESTAMP NULL,      
    DescripcionEjecucion TEXT NULL,     
    Evidencia VARCHAR(255) NULL,        

    -- cierre por manager
    FechaCierre TIMESTAMP NULL,         
    ManagerCierraID INT NULL,           

    Estado ENUM('Pendiente','En Proceso','Ejecutado','Cerrado') DEFAULT 'Pendiente',

    FOREIGN KEY (ReunionID) REFERENCES Reunion(ReunionID),
    FOREIGN KEY (ManagerID) REFERENCES Usuario(UsuarioID),
    FOREIGN KEY (ResponsableID) REFERENCES Usuario(UsuarioID),
    FOREIGN KEY (ManagerCierraID) REFERENCES Usuario(UsuarioID)
);

-- ===========================================
-- TABLA HISTORIAL DE CAMBIOS EN PLANES DE ACCIÓN
-- ===========================================
CREATE TABLE PlanAccion_Historial (
    HistorialID INT AUTO_INCREMENT PRIMARY KEY,
    PlanID INT NOT NULL,
    UsuarioID INT NOT NULL,             -- quién realizó el cambio
    EstadoAnterior VARCHAR(20),
    EstadoNuevo VARCHAR(20),
    FechaCambio TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    Comentario TEXT,
    FOREIGN KEY (PlanID) REFERENCES PlanAccion(PlanID),
    FOREIGN KEY (UsuarioID) REFERENCES Usuario(UsuarioID)
);
