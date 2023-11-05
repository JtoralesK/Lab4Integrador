DROP DATABASE IF EXISTS Banco;
CREATE DATABASE Banco;
USE Banco;

CREATE TABLE provincias
(
    id_provincia BIGINT NOT NULL AUTO_INCREMENT,
    provincia CHAR(50) NOT NULL,
    CONSTRAINT PK_Provincias PRIMARY KEY (id_provincia)
);

CREATE TABLE localidades
(
    id_localidad BIGINT NOT NULL AUTO_INCREMENT,
    id_provincia BIGINT NOT NULL,
    localidad CHAR(50) NOT NULL,
    CONSTRAINT PK_Localidades PRIMARY KEY (id_localidad),
    CONSTRAINT FK_ProvinciasXLocalidades FOREIGN KEY (id_provincia) REFERENCES provincias (id_provincia)
);

CREATE TABLE nacionalidades
(
    id_nacionalidad BIGINT NOT NULL AUTO_INCREMENT,
    nacionalidad CHAR(50) NOT NULL,
    CONSTRAINT PK_Nacionalidades PRIMARY KEY (id_nacionalidad)
);

CREATE TABLE tipo_usuarios
(
    id_tipo_usuario INT NOT NULL AUTO_INCREMENT,
    rol CHAR(20) NOT NULL,
    CONSTRAINT PK_TipoDeUsuarios PRIMARY KEY (id_tipo_usuario)
);

CREATE TABLE usuarios
(
    id_usuario BIGINT NOT NULL AUTO_INCREMENT,
    usuario CHAR(30) NOT NULL,
    contraseña CHAR(30) NOT NULL,
    id_tipo_usuario INT NOT NULL,
    estado BIT NOT NULL,
    CONSTRAINT PK_usuarios PRIMARY KEY (ID_usuario),
    CONSTRAINT FK_TipoUsuarioUsuario FOREIGN KEY (id_tipo_usuario) REFERENCES tipo_usuarios (id_tipo_usuario)
);

CREATE TABLE sexos
(
    id_sexo INT NOT NULL AUTO_INCREMENT,
    Descripcion CHAR(20) NOT NULL,
    CONSTRAINT PK_Sexos PRIMARY KEY (id_sexo)
);

CREATE TABLE clientes
(
    id_cliente BIGINT NOT NULL AUTO_INCREMENT,
    dni CHAR(10) NOT NULL,
    cuil CHAR(20) NOT NULL,
    nombre CHAR(50) NOT NULL,
    apellido CHAR(50) NOT NULL,
    id_sexo INT NOT NULL,
    id_nacionalidad BIGINT NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    direccion CHAR(255) NOT NULL,
    id_localidad BIGINT NOT NULL,
    mail CHAR(60) NOT NULL,
    telefono BIGINT NOT NULL,
    ID_usuario  BIGINT NOT NULL,
    CONSTRAINT PK_Clientes PRIMARY KEY (id_cliente),
    CONSTRAINT AK_dni UNIQUE(dni),
    CONSTRAINT FK_ClientesXNacionalidades FOREIGN KEY (id_nacionalidad) REFERENCES nacionalidades (id_nacionalidad),
    CONSTRAINT FK_ClientesXSexos FOREIGN KEY (id_sexo) REFERENCES Sexos (id_sexo),
    CONSTRAINT FK_ClientesXLocalidades FOREIGN KEY (id_localidad) REFERENCES localidades (id_localidad),
    CONSTRAINT FK_UsuarioCliente FOREIGN KEY (ID_usuario) REFERENCES usuarios (ID_usuario)
);

CREATE TABLE tipo_cuentas
(
    id_tipo_cuenta INT NOT NULL AUTO_INCREMENT,
    nombre CHAR(30) NOT NULL,
    CONSTRAINT PK_TipoDeCuentas PRIMARY KEY (id_tipo_cuenta)
);

CREATE TABLE cuentas
(
    n_cuenta BIGINT NOT NULL AUTO_INCREMENT,
    id_cliente BIGINT NOT NULL,
    id_tipo_cuenta INT NOT NULL,
    saldo DECIMAL(15, 2) NOT NULL,
    fecha_creacion DATE NOT NULL,
    cbu CHAR(30) NOT NULL,
    estado BIT NOT NULL,
    CONSTRAINT Cuentas PRIMARY KEY (N_cuenta,id_cliente),
    CONSTRAINT FK_CuentasXClientes FOREIGN KEY (id_cliente) REFERENCES clientes (id_cliente),
    CONSTRAINT FK_CuentasXTipoDeCuentas FOREIGN KEY (id_tipo_cuenta) REFERENCES tipo_cuentas (id_tipo_cuenta)
);

CREATE TABLE estado_prestamo
(
    id_estado INT NOT NULL AUTO_INCREMENT,
    descripcion CHAR(20) NOT NULL,
    CONSTRAINT PK_Estado_Prestamo PRIMARY KEY (id_estado)
);

CREATE TABLE tipo_movimientos
(
    id_tipo_movimiento INT NOT NULL AUTO_INCREMENT,
    descripcion CHAR(20) NOT NULL,
    CONSTRAINT PK_TipoDeUsuarios PRIMARY KEY (id_tipo_movimiento)
);

CREATE TABLE movimientos
(
    id_movimiento BIGINT NOT NULL AUTO_INCREMENT,
    n_cuenta BIGINT NOT NULL,
    id_cliente BIGINT NOT NULL,
    id_tipo_movimiento INT NOT NULL,
    fecha DATE NOT NULL,
    hora TIME NOT NULL,
    importe DECIMAL(15, 2) NOT NULL,
    concepto CHAR(20) NULL,
    CONSTRAINT PK_Movimientos PRIMARY KEY (id_movimiento),
    CONSTRAINT FK_PK_MovimientossXCuentas FOREIGN KEY (n_cuenta,id_cliente) REFERENCES cuentas (n_cuenta,id_cliente),
    CONSTRAINT FK_MovimientosXTipoDeMovimientos FOREIGN KEY (id_tipo_movimiento) REFERENCES tipo_movimientos (id_tipo_movimiento)
);

CREATE TABLE prestamos
(
    id_prestamo BIGINT NOT NULL AUTO_INCREMENT,
	n_cuenta BIGINT NOT NULL,
    id_cliente BIGINT NOT NULL,
    importe DECIMAL(15, 2) NOT NULL,
    fecha_solicitud DATE NOT NULL,
    id_estado INT NOT NULL,
    Plazo INT NOT NULL,
    fecha_aprobacion DATE,
    CONSTRAINT PK_Prestamos PRIMARY KEY (id_prestamo),
    CONSTRAINT FK_prestamosXCuentas FOREIGN KEY (n_cuenta,id_cliente) REFERENCES cuentas (n_cuenta,id_cliente),
    CONSTRAINT FK_PrestamosXestado_prestamo FOREIGN KEY (id_estado) REFERENCES estado_prestamo(id_estado)
);

CREATE TABLE prestamos_movimientos
(
    id_prestamo BIGINT NOT NULL,
    id_movimiento BIGINT NOT NULL,
    CONSTRAINT PK_Prestamos_Movimientos PRIMARY KEY (id_prestamo, id_movimiento),
    CONSTRAINT FK_Prestamos_MovimientosXMovimientos FOREIGN KEY (id_movimiento) REFERENCES movimientos (id_movimiento),
    CONSTRAINT FK_Prestamos_MovimientosXPrestamos FOREIGN KEY (id_prestamo) REFERENCES prestamos (id_prestamo)
);

CREATE TABLE transferencias
(
    id_movimiento_origen BIGINT NOT NULL,
    id_movimiento_destino BIGINT NOT NULL,
    CONSTRAINT PK_Transferencias PRIMARY KEY (id_movimiento_origen, id_movimiento_destino),
    CONSTRAINT FK_TransferenciasXMovimientoCuentaOrigen FOREIGN KEY (id_movimiento_origen) REFERENCES movimientos (id_movimiento),
    CONSTRAINT FK_TransferenciasXMovimientoCuentaDestino FOREIGN KEY (id_movimiento_destino) REFERENCES movimientos (id_movimiento)
);