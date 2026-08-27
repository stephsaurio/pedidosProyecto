CREATE DATABASE IF NOT EXISTS `mydb`
/*!40100 DEFAULT CHARACTER SET utf8mb3 */
/*!80016 DEFAULT ENCRYPTION='N' */;

USE `mydb`;

-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)

SET FOREIGN_KEY_CHECKS = 0;

SELECT * from cliente;

-- -----------------------------------------------------
-- Table structure for table `cliente`
-- -----------------------------------------------------

DROP TABLE IF EXISTS `cliente`;
ALTER TABLE cliente MODIFY Password VARCHAR(255);
CREATE TABLE `cliente` (
  `idCliente` int NOT NULL AUTO_INCREMENT,
  `Nombre_usuario` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `NombreCompleto` varchar(50) NOT NULL,
  `Telefono` int NOT NULL,
  `Correo_electronico` varchar(45) NOT NULL,
  `NIT` varchar(45) NOT NULL,
  `Rol` varchar(20) NOT NULL DEFAULT 'CLIENTE',

  PRIMARY KEY (`idCliente`),
  UNIQUE KEY `NombreUsuario_UNIQUE` (`Nombre_usuario`),
  UNIQUE KEY `NIT_UNIQUE` (`NIT`),
  UNIQUE KEY `idCliente_UNIQUE` (`idCliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;


-- -----------------------------------------------------
-- Table structure for table `lote`
-- -----------------------------------------------------

DROP TABLE IF EXISTS `lote`;

CREATE TABLE `lote` (
  `idLote` int NOT NULL AUTO_INCREMENT,
  `Estilo` varchar(45) DEFAULT NULL,
  `Stock` int DEFAULT NULL,
  `Talla` double DEFAULT NULL,

  PRIMARY KEY (`idLote`),
  UNIQUE KEY `idLote_UNIQUE` (`idLote`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;


-- -----------------------------------------------------
-- Table structure for table `pedido`
-- -----------------------------------------------------

DROP TABLE IF EXISTS `pedido`;

CREATE TABLE `pedido` (
  `idPedido` int NOT NULL AUTO_INCREMENT,
  `Fecha` date NOT NULL,
  `Estado` varchar(45) NOT NULL,
  `Total` double NOT NULL,
  `Cliente_idCliente` int NOT NULL,

  PRIMARY KEY (`idPedido`),
  UNIQUE KEY `idPedido_UNIQUE` (`idPedido`),
  KEY `fk_Pedido_Cliente1_idx` (`Cliente_idCliente`),

  CONSTRAINT `fk_Pedido_Cliente1`
    FOREIGN KEY (`Cliente_idCliente`)
    REFERENCES `cliente` (`idCliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;


-- -----------------------------------------------------
-- Table structure for table `producto`
-- -----------------------------------------------------

DROP TABLE IF EXISTS `producto`;

CREATE TABLE `producto` (
  `idProducto` int NOT NULL AUTO_INCREMENT,
  `Nombre_producto` varchar(45) NOT NULL,
  `Descripcion` longtext NOT NULL,
  `Precio` double NOT NULL,
  `Lote_idLote` int NOT NULL,

  PRIMARY KEY (`idProducto`),
  UNIQUE KEY `idProducto_UNIQUE` (`idProducto`),
  KEY `fk_Producto_Lote1_idx` (`Lote_idLote`),

  CONSTRAINT `fk_Producto_Lote1`
    FOREIGN KEY (`Lote_idLote`)
    REFERENCES `lote` (`idLote`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;


-- -----------------------------------------------------
-- Table structure for table `detalle_pedido`
-- -----------------------------------------------------

DROP TABLE IF EXISTS `detalle_pedido`;

CREATE TABLE `detalle_pedido` (
  `idDetalle` int NOT NULL AUTO_INCREMENT,
  `Precio_unitario` double NOT NULL,
  `Cantidad` int NOT NULL,
  `Sub_total` double NOT NULL,
  `Pedido_idPedido` int NOT NULL,
  `Producto_idProducto` int NOT NULL,

  PRIMARY KEY (`idDetalle`),
  UNIQUE KEY `idDetalle_UNIQUE` (`idDetalle`),
  KEY `fk_Detalle_pedido_Pedido1_idx` (`Pedido_idPedido`),
  KEY `fk_Detalle_pedido_Producto1_idx` (`Producto_idProducto`),

  CONSTRAINT `fk_Detalle_pedido_Pedido1`
    FOREIGN KEY (`Pedido_idPedido`)
    REFERENCES `pedido` (`idPedido`),

  CONSTRAINT `fk_Detalle_pedido_Producto1`
    FOREIGN KEY (`Producto_idProducto`)
    REFERENCES `producto` (`idProducto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- -----------------------------------------------------
-- Table structure for table `factura`
-- -----------------------------------------------------

DROP TABLE IF EXISTS `factura`;

CREATE TABLE `factura` (
  `idFactura` int NOT NULL AUTO_INCREMENT,
  `Direccion` varchar(50) NOT NULL,
  `Fecha_emision` date NOT NULL,
  `Nombre_Archivo` varchar(45) NOT NULL,
  `rutaSFTP` longtext NOT NULL,
  `Pedido_idPedido` int NOT NULL,

  PRIMARY KEY (`idFactura`),
  UNIQUE KEY `idFactura_UNIQUE` (`idFactura`),
  KEY `fk_Factura_Pedido1_idx` (`Pedido_idPedido`),

  CONSTRAINT `fk_Factura_Pedido1`
    FOREIGN KEY (`Pedido_idPedido`)
    REFERENCES `pedido` (`idPedido`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- =====================================================
-- TABLA: AuditLog
-- =====================================================

DROP TABLE IF EXISTS `AuditLog`;

CREATE TABLE `AuditLog` (
  `idAuditLog` int NOT NULL AUTO_INCREMENT,
  `idCliente` int NOT NULL,
  `Tabla_afectada` varchar(50) NOT NULL,
  `Id_registro` int NOT NULL,
  `Fecha_hora` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `IP_address` varchar(45) DEFAULT NULL,
  `Valores_anteriores` JSON DEFAULT NULL,
  `Valores_nuevos` JSON DEFAULT NULL,

  PRIMARY KEY (`idAuditLog`),

  KEY `fk_AuditLog_Cliente_idx` (`idCliente`),

  CONSTRAINT `fk_AuditLog_Cliente`
    FOREIGN KEY (`idCliente`)
    REFERENCES `cliente` (`idCliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;


-- -----------------------------------------------------
-- Administrador inicial
-- -----------------------------------------------------



SET FOREIGN_KEY_CHECKS = 1;