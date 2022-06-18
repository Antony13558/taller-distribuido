-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: bd_ventas3
-- ------------------------------------------------------
-- Server version	8.0.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoria` (
  `IdCategoria` int unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `descripcion` text,
  PRIMARY KEY (`IdCategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (1,'Bebidas','heladas'),(2,'Snacks','Dulces'),(6,'Verduras','Verduras frescas');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_ventas`
--

DROP TABLE IF EXISTS `detalle_ventas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalle_ventas` (
  `IdDetalleVentas` int unsigned NOT NULL AUTO_INCREMENT,
  `IdVentas` int unsigned NOT NULL,
  `IdProducto` int unsigned NOT NULL,
  `cantidad` int unsigned DEFAULT NULL,
  `precioUnit` double DEFAULT NULL,
  `total` double DEFAULT NULL,
  `codVenta` varchar(45) NOT NULL,
  PRIMARY KEY (`IdDetalleVentas`,`IdVentas`,`IdProducto`),
  KEY `fk_detalle_ventas_ventas1_idx` (`IdVentas`),
  KEY `fk_detalle_ventas_producto1_idx` (`IdProducto`),
  CONSTRAINT `fk_detalle_ventas_producto1` FOREIGN KEY (`IdProducto`) REFERENCES `producto` (`IdProducto`),
  CONSTRAINT `fk_detalle_ventas_ventas1` FOREIGN KEY (`IdVentas`) REFERENCES `ventas` (`IdVentas`)
) ENGINE=InnoDB AUTO_INCREMENT=130 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_ventas`
--

LOCK TABLES `detalle_ventas` WRITE;
/*!40000 ALTER TABLE `detalle_ventas` DISABLE KEYS */;
INSERT INTO `detalle_ventas` VALUES (106,76,4,3,2,6,'F17-00076'),(107,77,4,3,2,6,'F17-00077'),(108,78,4,2,2,4,'F17-00078'),(109,79,4,2,2,4,'B16-00079'),(110,80,4,2,2,4,'F17-00080'),(111,81,4,1,2,2,'F17-00081'),(112,82,4,1,2,2,'B16-00082'),(113,82,4,3,2,6,'B16-00082'),(114,82,4,2,2,4,'B16-00082'),(115,83,4,2,2,4,'F17-00083'),(116,84,4,2,2,4,'F17-00084'),(117,85,4,2,2,4,'F17-00085'),(118,86,4,1,2,2,'B16-00086'),(119,86,4,1,2,2,'B16-00086'),(120,87,4,1,2,2,'B16-00087'),(121,87,4,1,2,2,'B16-00087'),(122,87,4,2,2,4,'B16-00087'),(123,88,4,1,2,2,'B16-00088'),(124,89,4,2,2,4,'B16-00089'),(125,90,4,2,2,4,'F17-00090'),(126,91,4,2,2,4,'B16-00091'),(127,91,5,5,3,15,'B16-00091'),(128,91,6,5,1,5,'B16-00091'),(129,92,4,20,2,40,'B16-00092');
/*!40000 ALTER TABLE `detalle_ventas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleado`
--

DROP TABLE IF EXISTS `empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empleado` (
  `IdEmpleado` int unsigned NOT NULL AUTO_INCREMENT,
  `Dni` varchar(45) NOT NULL,
  `Nombres` varchar(45) DEFAULT NULL,
  `Telefono` int DEFAULT NULL,
  `contrasena` varchar(25) NOT NULL,
  `idRol` int unsigned NOT NULL,
  `Apellidos` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`IdEmpleado`),
  KEY `fk_vendedor_roles1_idx` (`idRol`),
  CONSTRAINT `fk_vendedor_roles1` FOREIGN KEY (`idRol`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleado`
--

LOCK TABLES `empleado` WRITE;
/*!40000 ALTER TABLE `empleado` DISABLE KEYS */;
INSERT INTO `empleado` VALUES (2,'123','Carlos',1231231,'123',1,'Caceda'),(3,'70660955','Mario',7895665,'123',2,'Vargas'),(5,'1703562','Aquiles',79846,'123',3,'Milk');
/*!40000 ALTER TABLE `empleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto` (
  `IdProducto` int unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `descripcion` varchar(50) DEFAULT NULL,
  `categoria` int unsigned NOT NULL,
  `stock` int unsigned DEFAULT NULL,
  `precioCoste` double DEFAULT NULL,
  `precioVenta` double DEFAULT NULL,
  `codProducto` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`IdProducto`),
  KEY `fk_producto_categoria1_idx` (`categoria`),
  CONSTRAINT `fk_producto_categoria1` FOREIGN KEY (`categoria`) REFERENCES `categoria` (`IdCategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (4,'InkaCola','Producto 100% Peru',1,16,1,2,'123'),(5,'CocaCola','De USA',1,15,1,3,'456'),(6,'ChesseTreese','Snacksss',2,45,0.5,1,'789');
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'administrador'),(2,'vendedor'),(3,'almacenero');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ventas`
--

DROP TABLE IF EXISTS `ventas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ventas` (
  `IdVentas` int unsigned NOT NULL AUTO_INCREMENT,
  `nomCliente` varchar(45) DEFAULT NULL,
  `dni_o_ruc` varchar(20) DEFAULT NULL,
  `fechaVenta` date DEFAULT NULL,
  `tipoDeComprobante` varchar(45) DEFAULT NULL,
  `IdEmpleado` int unsigned NOT NULL,
  `codVenta` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`IdVentas`),
  KEY `fk_ventas_vendedor_idx` (`IdEmpleado`),
  CONSTRAINT `fk_ventas_vendedor` FOREIGN KEY (`IdEmpleado`) REFERENCES `empleado` (`IdEmpleado`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ventas`
--

LOCK TABLES `ventas` WRITE;
/*!40000 ALTER TABLE `ventas` DISABLE KEYS */;
INSERT INTO `ventas` VALUES (76,'Angelo','4005847','2022-05-22','FACTURA',2,'F17-00076'),(77,'sadad','123123','2022-05-25','FACTURA',2,'F17-00077'),(78,'asdas','123','2022-05-25','FACTURA',2,'F17-00078'),(79,'xcv','123','2022-05-25','BOLETA',2,'B16-00079'),(80,'sadf','234','2022-05-25','FACTURA',2,'F17-00080'),(81,'asdasd','123','2022-05-25','FACTURA',2,'F17-00081'),(82,'arge','2132','2022-05-25','BOLETA',2,'B16-00082'),(83,'sasd','123231','2022-05-26','FACTURA',2,'F17-00083'),(84,'sdsadsd','12312','2022-05-26','FACTURA',2,'F17-00084'),(85,'sdcsdf','1323','2022-05-26','FACTURA',2,'F17-00085'),(86,'QWDQ','123','2022-05-26','BOLETA',2,'B16-00086'),(87,'QWDQ','123','2022-05-26','BOLETA',2,'B16-00087'),(88,'fsdf','123','2022-05-26','BOLETA',2,'B16-00088'),(89,'awdeq','123','2022-05-26','BOLETA',2,'B16-00089'),(90,'sdfdsdf','123','2022-05-26','FACTURA',2,'F17-00090'),(91,'asda','123213','2022-05-26','BOLETA',2,'B16-00091'),(92,'sdad','123','2022-05-26','BOLETA',2,'B16-00092'),(93,'sadasd','12323','2022-05-26','BOLETA',2,'B16-00093');
/*!40000 ALTER TABLE `ventas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-26 13:51:16
