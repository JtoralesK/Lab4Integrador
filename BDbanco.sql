DROP DATABASE IF EXISTS Banco;
CREATE DATABASE Banco;
USE Banco;

CREATE TABLE provincias
(
    id_provincia BIGINT AUTO_INCREMENT  NOT NULL,
    provincia CHAR(50) NOT NULL,
    CONSTRAINT PK_Provincias PRIMARY KEY (id_provincia)
);

CREATE TABLE localidades
(
    id_localidad BIGINT AUTO_INCREMENT NOT NULL,
    id_provincia BIGINT NOT NULL,
    localidad CHAR(50) NOT NULL,
    CONSTRAINT PK_Localidades PRIMARY KEY (id_localidad),
    CONSTRAINT FK_ProvinciasXLocalidades FOREIGN KEY (id_provincia) REFERENCES provincias (id_provincia)
);

CREATE TABLE nacionalidades
(
    id_nacionalidad BIGINT AUTO_INCREMENT NOT NULL,
    nacionalidad CHAR(50) NOT NULL,
    CONSTRAINT PK_Nacionalidades PRIMARY KEY (id_nacionalidad)
);

CREATE TABLE tipo_usuarios
(
    id_tipo_usuario INT AUTO_INCREMENT NOT NULL,
    rol CHAR(20) NOT NULL,
    CONSTRAINT PK_TipoDeUsuarios PRIMARY KEY (id_tipo_usuario)
);

CREATE TABLE usuarios
(
    id_usuario BIGINT AUTO_INCREMENT NOT NULL,
    usuario CHAR(30) NOT NULL,
    contraseña CHAR(30) NOT NULL,
    id_tipo_usuario INT NOT NULL,
    estado BIT NOT NULL,
    CONSTRAINT PK_usuarios PRIMARY KEY (ID_usuario),
    CONSTRAINT FK_TipoUsuarioUsuario FOREIGN KEY (id_tipo_usuario) REFERENCES tipo_usuarios (id_tipo_usuario)
);

CREATE TABLE sexos
(
    id_sexo INT AUTO_INCREMENT NOT NULL,
    descripcion CHAR(20) NOT NULL,
    CONSTRAINT PK_Sexos PRIMARY KEY (id_sexo)
);


CREATE TABLE clientes 
(
    id_cliente BIGINT AUTO_INCREMENT NOT NULL,
    dni int NOT NULL,
    cuil BIGINT NOT NULL,
    nombre CHAR(50) NOT NULL,
    apellido CHAR(50) NOT NULL,
    id_sexo INT NOT NULL,
    id_nacionalidad BIGINT NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    direccion CHAR(255) NOT NULL,
    id_localidad BIGINT NOT NULL,
    mail CHAR(60) NOT NULL,
    telefono BIGINT NOT NULL,
    id_usuario  BIGINT NOT NULL,
    CONSTRAINT PK_Clientes PRIMARY KEY (id_cliente),
    CONSTRAINT AK_dni UNIQUE(dni),
    CONSTRAINT FK_ClientesXNacionalidades FOREIGN KEY (id_nacionalidad) REFERENCES nacionalidades (id_nacionalidad),
    CONSTRAINT FK_ClientesXSexos FOREIGN KEY (id_sexo) REFERENCES Sexos (id_sexo),
    CONSTRAINT FK_ClientesXLocalidades FOREIGN KEY (id_localidad) REFERENCES localidades (id_localidad),
    CONSTRAINT FK_UsuarioCliente FOREIGN KEY (ID_usuario) REFERENCES usuarios (ID_usuario)
);
ALTER TABLE clientes AUTO_INCREMENT = 101;

CREATE TABLE tipo_cuentas
(
    id_tipo_cuenta INT AUTO_INCREMENT NOT NULL,
    nombre CHAR(30) NOT NULL,
    CONSTRAINT PK_TipoDeCuentas PRIMARY KEY (id_tipo_cuenta)
);

CREATE TABLE cuentas
(
    n_cuenta BIGINT NOT NULL,
    id_cliente BIGINT NOT NULL,
    id_tipo_cuenta INT NOT NULL,
    saldo DECIMAL(15, 2) NOT NULL,
    fecha_creacion DATE NOT NULL,
    cbu CHAR(30) NOT NULL,
    estado BIT NOT NULL,
    CONSTRAINT nroCuenta UNIQUE (n_cuenta),
    CONSTRAINT cbu UNIQUE (cbu),
    CONSTRAINT Cuentas PRIMARY KEY (n_cuenta,id_cliente),
    CONSTRAINT FK_CuentasXClientes FOREIGN KEY (id_cliente) REFERENCES clientes (id_cliente),
    CONSTRAINT FK_CuentasXTipoDeCuentas FOREIGN KEY (id_tipo_cuenta) REFERENCES tipo_cuentas (id_tipo_cuenta)
);

CREATE TABLE estado_prestamo
(
    id_estado INT AUTO_INCREMENT NOT NULL,
    descripcion CHAR(20) NOT NULL,
    CONSTRAINT PK_Estado_Prestamo PRIMARY KEY (id_estado)
);
ALTER TABLE estado_prestamo AUTO_INCREMENT = 0;

CREATE TABLE tipo_movimientos
(
    id_tipo_movimiento INT AUTO_INCREMENT NOT NULL,
    descripcion CHAR(20) NOT NULL,
    CONSTRAINT PK_TipoDeUsuarios PRIMARY KEY (id_tipo_movimiento)
);

CREATE TABLE movimientos
(
    id_movimiento BIGINT AUTO_INCREMENT NOT NULL,
    n_cuenta BIGINT NOT NULL,
    id_cliente BIGINT NOT NULL,
    id_tipo_movimiento INT NOT NULL,
    fecha DATE NOT NULL,
    hora TIME NOT NULL,
    importe DECIMAL(15, 2) NOT NULL,
    concepto CHAR(30) NULL,
    CONSTRAINT PK_Movimientos PRIMARY KEY (id_movimiento),
    CONSTRAINT FK_PK_MovimientossXCuentas FOREIGN KEY (n_cuenta,id_cliente) REFERENCES cuentas (n_cuenta,id_cliente),
    CONSTRAINT FK_MovimientosXTipoDeMovimientos FOREIGN KEY (id_tipo_movimiento) REFERENCES tipo_movimientos (id_tipo_movimiento)
);

CREATE TABLE prestamos
(
    id_prestamo BIGINT AUTO_INCREMENT NOT NULL,
	n_cuenta BIGINT NOT NULL,
    id_cliente BIGINT NOT NULL,
    importe DECIMAL(15, 2) NOT NULL,
    fecha_solicitud DATE NOT NULL,
    id_estado INT NOT NULL,
    plazo INT NOT NULL,
    fecha_revision DATE,
    interes DECIMAL(3,2) DEFAULT 1.15 NOT NULL,
    CONSTRAINT PK_Prestamos PRIMARY KEY (id_prestamo),
    CONSTRAINT FK_prestamosXCuentas FOREIGN KEY (n_cuenta,id_cliente) REFERENCES cuentas (n_cuenta,id_cliente),
    CONSTRAINT FK_PrestamosXestado_prestamo FOREIGN KEY (id_estado) REFERENCES estado_prestamo(id_estado)
);
ALTER TABLE prestamos AUTO_INCREMENT = 1;

CREATE TABLE pagos_prestamos
(
    id_prestamo BIGINT  NOT NULL,
    id_movimiento BIGINT NOT NULL,
    fecha DATE NOT NULL,
    cuota INT NOT NULL,
    CONSTRAINT PK_Prestamos_Movimientos PRIMARY KEY (id_prestamo, id_movimiento),
    CONSTRAINT FK_Prestamos_MovimientosXMovimientos FOREIGN KEY (id_movimiento) REFERENCES movimientos (id_movimiento),
    CONSTRAINT FK_Prestamos_MovimientosXPrestamos FOREIGN KEY (id_prestamo) REFERENCES prestamos (id_prestamo)
);


/*************************************************************************************************************************************************************************
DATOS
*************************************************************************************************************************************************************************/
use Banco;

INSERT INTO provincias (provincia)
SELECT "BsAs" union
SELECT "Chaco" union
SELECT "Catamarca" union
SELECT "Chubut" union
SELECT "Córdoba" union
SELECT "Corrientes" union
SELECT "Entre Ríos" union
SELECT "Formosa" union
SELECT "Jujuy" union
SELECT "La Pampa" union
SELECT "La Rioja" union
SELECT "Mendoza" union
SELECT "Misiones" union
SELECT "Neuquén" union
SELECT "Río Negro" union
SELECT  "Salta" union
SELECT "San Juan" union
SELECT "San Luis" union
SELECT "Santa Cruz" union
SELECT "Santa Fe" union
SELECT "Santiago del Estero" union
SELECT "Tierra del Fuego" union
SELECT "Tucumán";

/*****************************************************************************/
INSERT INTO  localidades(id_provincia,localidad)
select 1,"Bs.As" union
select 1,"Caba" union
select 2,"Resistencia" union 
select 4,"Rawson" union
select 5,"Córdoba" union
select 7,"Paraná" union 
select 8,"Formosa" union
select 10,"Santa Rosa" union
select 13,"Posadas" union
select 14,"Neuquén" union 
select 15,"Viedma" union 
select 16,"Salta" union
select 17,"San Juan" union
select 20,"Santa Fe" union
select 22,"Ushuaia";
 
/**************************************************************************/
INSERT INTO  nacionalidades(nacionalidad)
select "Argentino" union
select "Peruano" union
select "Brasilero" union
select "Uruguayo" union
select "Maxicano" union
select "EEUU" union
select "Chino" union
select "Chileno" union
select "Venezolano" union
select "Ruso" union
select "Ecuatoriano" union
select "Boliviano" union
select "Ucraniano" union
select "Italiano" union
select "Español";

/********************************************************************************************/
INSERT INTO  tipo_usuarios(rol)
select "Administrador" union
select "cliente";

/*********************************************************************************************/
INSERT INTO  usuarios(usuario,contraseña,id_tipo_usuario,estado)
select "Jean5011","50111",1,1 union
select "Franco","12345",1,1 union
select "Agus","123123",1,1 union
select "Javier","12345",1,1 union
select "Jean211","1456",2,1 union
select "Franco5011","46665",2,1 union
select "Agustin777","46466",2,1 union
select "Javier456","565665",2,1 union
select "Ariana679","66464",2,1 union
select "Juana1234","656656",2,1 union
select "GustavoM","56456",2,1 union
select "Mariana1212","54654",2,1 union
select "JuanJuan","54645",2,1 union
select "JuanaPP","5465454",2,1 union
select "cFonseca-acc","5458754",2,1 union
select "Carla15","3546546",2,1 union
select "Mariano001","45654",2,1 union
select "RutNah","546456",2,1 union
select "Flor15","545454",2,1 union
select "Jvivi","654654",2,1 union
select "Walter1987","5645546",2,1 union
select "Gabiii","786538",2,1 union
select "Martu505","5435487",2,1 union
select "Jefe","12345",1,1;

/****************************************************************/
INSERT INTO  sexos(descripcion)
select "Masculino" Union
select "Femenino " Union
select "Trans M" Union
select "Trans F" Union
select "Indefinido";

/*****************************************************************/
INSERT INTO  clientes(dni,cuil,nombre,apellido,id_sexo,id_nacionalidad,fecha_nacimiento,direccion,id_localidad,mail,telefono,id_usuario)
select 78984123,207853981,"Jean","Esquen",1,2,"2002-09-01","Urquiza 1126",1,"jeanesuqen@gmail.com",1153535,5 union
select 78924789,207897891,"Franco","Marcolongo",1,1,"2001-08-25","Martinez 322",5,"Franco@gmail.com",1135353,6 union
select 78941287,207869871,"Agustin","Sbernini",3,1,"2001-08-26","Calle 123",4,"Agustin@gmail.com",1196666,7 union
select 86442156,208664561,"Javier","Torales",1,1,"2001-08-27","Martinez 1322",5,"Javier@gmail.com",1158988,8 union
select 65441264,206564641,"Ariana","Martines",2,3,"2001-10-28","Roca 1132,",14,"Ari@hotmail.com",1199999,9 union
select 45667845,204536451,"Juana","Herrera",2,4,"1987-11-08","Valle 2663",13,"boquita@gmailcom",1118925,10 union
select 64565645,206256451,"Gustavo","Martin",1,4,"1999-05-31","Jose perez 2434",9,"jeanesuqen@gmail.com",1155756,11 union
select 45694546,204565461,"Mariana","Martin",2,5,"1996-02-06","Villa luro 3445",2,"River@gmail.com",1139654,12 union
select 45497635,204524451,"Juan","Perez",1,6,"1990-08-01","Markes 2334",9,"Juan5100@gmail.com",1189355,13 union
select 45656490,204566461,"Juana","Esquel",4,8,"2002-07-23","San martin 2344",12,"Juana@hotmail.com",1125686,14 union
select 54803456,205464561,"Carlos","Fonseca",1,9,"1993-06-30","Samiento 3445",10,"CFA@gmail.com",1125668,15 union
select 54194654,205468541,"Carla","Fonseca",5,5,"1985-05-05","Congreso 2455",3,"Carla1234@gmail.com",1182656,16 union
select 64424656,206452461,"Mariano","Ramirez",5,4,"2000-10-19","Palermo 3545",5,"Mramirez@gmail.com",1155665,17 union
select 54664345,205461451,"Rut","Nah",2,15,"2000-11-03","Peru 2455",7,"Rut1234@gmail.com",1123205,18 union
select 64568824,206445641,"Flor","Arispe",2,14,"2001-09-07","Mexico 3545",6,"Farispe@hotmail.com",1189654,15;

/*******************************************************************************************************************************/
INSERT INTO  tipo_cuentas(nombre)
select "Caja de Ahorros" union
select "Cuenta Corriente";

/************************************************************************************************************************************/
INSERT INTO  cuentas(n_cuenta,id_cliente,id_tipo_cuenta,saldo,fecha_creacion,cbu,estado)
select 1,101,1,110000,"2020-01-01","456546",1 union
select 2,101,2,4141111,"2021-11-02","645645",1 union
select 3,101,1,455555,"2021-06-19","156516",1 union
select 4,103,1,46646,"2022-04-02","116888",1 union
select 5,103,2,44454,"2021-12-15","151893",1 union
select 6,103,1,54445,"2020-10-10","328599",1 union
select 7,115,2,66646,"2020-12-12","369258",1 union
select 8,112,1,44466,"2011-12-21","852963",1 union
select 9,115,1,44656,"2021-08-26","741963",1 union
select 10,115,2,44444,"2022-09-19","369147",1 union
select 11,102,1,44555,"2021-03-19","852167",1 union
select 12,102,1,44554,"2022-06-03","978645",1 union
select 13,112,2,445455,"2022-12-19","321456",1 union
select 14,106,1,45454,"2015-06-01","671649",1 union
select 15,107,1,545454,"2018-08-18","943349",1 union
select 16,104,1,35588,"2020-08-18","464546",1 union
select 17,105,1,55656,"2021-07-29","565656",1 union
select 18,108,1,654654,"2021-10-30","596556",1 union
select 19,109,2,5465666,"2021-12-18","456856",1 union
select 20,110,2,5615565,"2021-08-01","565665",1 union
select 21,113,1,55456,"2022-03-03","125555",1 union
select 22,111,1,125555,"2022-05-02","154553",1 union
select 23,114,2,2555333,"2021-01-08","552258",1;

/***************************************************************/
INSERT INTO  estado_prestamo(descripcion)
select "Rechazado" union
select "Aprobado" union
select "Pendiente";

/****************************************************************/
INSERT INTO tipo_movimientos(descripcion)
SELECT "Transferencia" UNION
SELECT "Alta de un préstamo" UNION
SELECT "Alta de cuenta" UNION
SELECT "Pago de préstamo";

/***********************************************************************************/
INSERT INTO movimientos(n_cuenta,id_cliente,id_tipo_movimiento,fecha,hora,importe)
select 1,101,2,"2023-02-08","11:00:00",1256 union
select 4,103,2,"2023-08-19","12:34:00",1589 union
select 3,101,2,"2023-05-17","13:54:00",0 union
select 15,107,2,"2023-09-23","17:50:00",1662 union
select 9,115,2,"2023-11-02","00:01:00",0 union
select 2,101,2,"2023-06-19","01:01:00",3225 union
select 10,115,2,"2023-07-08","02:01:00",2115 union
select 1,101,2,"2023-11-12","03:01:00",0 union
select 11,102,2,"2023-10-22","04:01:00",0  union
select 8,112,2,"2023-10-29","05:01:00",0 union 
select 12,102,2,"2023-01-30","06:01:00",16660 union
select 14,106,2,"2023-05-25","07:01:00",16056 union
select 14,106,2,"2023-08-24","08:01:00",8520 union
select 15,107,2,"2023-08-09","09:01:00",0 union 
select 2,101,2,"2023-08-10","10:01:00",1666 union
select 15,107,1,"2023-02-11","10:52:00",1588 union
select 3,101,1,"2023-02-12","22:39:00",2066 union
select 5,103,1,"2023-02-13","20:30:00",1800 union 
select 20,110,1,"2023-02-14","21:05:00",1555 union
select 6,103,1,"2023-02-15","14:00:00",5000 union
select 20,110,1,"2023-02-16","17:30:00",8099 union
select 2,101,1,"2023-02-17","18:30:00",2555 union 
select 3,101,1,"2023-02-18","12:20:00",1234 union
select 8,112,1,"2023-02-19","8:39:00",1212 union
select 3,101,1,"2023-02-20","14:52:00",1588 union
select 19,109,1,"2023-02-21","18:56:00",1544 union
select 10,115,1,"2023-02-22","20:20:00",2550 union
select 14,106,1,"2023-02-23","12:06:00",522 union
select 15,107,1,"2023-02-24","20:06:00",522 union
select 3,101,1,"2023-02-25","13:00:00",1000 union
select 2,101,4,"2023-02-11","10:52:00",1588 union
select 5,103,4,"2023-02-12","22:39:00",2066 union 
select 15,107,4,"2023-02-13","20:30:00",1800 union
select 16,104,4,"2023-02-14","21:05:00",1555 union
select 16,104,4,"2023-02-15","14:00:00",5000 union
select 21,113,4,"2023-02-16","17:30:00",8099 union
select 2,101,4,"2023-02-17","18:30:00",2555 union
select 3,101,4,"2023-02-18","12:20:00",1234 union
select 20,110,4,"2023-02-19","08:39:00",1212 union
select 15,107,4,"2023-02-20","14:52:00",1588 union
select 18,108,4,"2023-02-21","18:56:05",1544 union
select 7,115,4,"2023-02-22","20:20:00",2550 union
select 15,107,4,"2023-02-23","12:06:00",522 union
select 14,106,4,"2023-02-24","20:06:00",522 union
select 1,101,4,"2023-02-25","13:00:00",1000 union
select 2,101,1,"2023-02-26","12:00:00",1256 union
select 11,102,1,"2023-02-27","15:12:00",5552 union
select 21,113,3,"2023-02-28","22:30:00",5000 union
select 23,114,3,"2023-03-01","23:30:00",1000 union
select 14,106,1,"2023-03-02","10:20:00",3211;	

/********************************************************/
INSERT INTO prestamos(n_cuenta,id_cliente,importe,fecha_solicitud,Plazo,fecha_revision,id_estado)
select 1,101,124256,"2023-01-12",4,"2023-02-08",3 union
select 4,103,1581249,"2023-08-19",3,"2023-08-19",3 union
select 15,107,161262,"2023-09-23",6,"2023-09-23",2 union
select 10,115,212215,"2023-07-08",4,"2023-07-08",2 union
select 2,101,164466,"2023-11-12",12,"2023-12-12",3 union
select 13,112,161259,"2023-10-25",12,"2023-10-29",1 union
select 12,102,164266,"2023-01-20",9,"2023-01-30",2 union
select 14,106,16516,"2023-05-25",6,"2023-05-25",2 union
select 14,106,85620,"2023-08-24",9,"2023-08-24",3 union 
select 15,107,163022,"2023-08-09",3,"2023-08-09",1 union
select 3,101,67266,"2023-08-10",6,"2023-08-10",2 union
select 10,115,95945,"2023-11-02",4,"2023-11-02",1 union
select 1,101,32225,"2023-06-10",3,"2023-06-19",3 union
select 11,102,162198,"2023-10-22",12,"2023-10-22",1 union
select 2,101,92596,"2023-05-17",9,"2023-05-17",1;

/************************************************************************************/
INSERT INTO pagos_prestamos(id_prestamo,id_movimiento,fecha,cuota)
select 1,1,"2023-11-09",1 union
select 2,2,"2023-04-09",2 union
select 3,3,"2023-05-14",3 union
select 4,4,"2023-08-08",4 union
select 5,5,"2023-02-20",5 union
select 6,6,"2023-11-09",6 union
select 7,7,"2023-11-09",6 union
select 8,8,"2023-11-09",5 union
select 9,9,"2023-11-09",4 union
select 10,10,"2023-08-08",3 union
select 11,11,"2023-10-30",2 union
select 12,12,"2023-12-23",1 union
select 13,13,"2023-11-09",2 union
select 14,14,"2023-02-20",3 union
select 15,15,"2023-11-09",4;