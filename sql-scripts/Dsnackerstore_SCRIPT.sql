CREATE USER 'dsnackerstore'@'localhost' IDENTIFIED BY 'dsnackerstore';

GRANT ALL PRIVILEGES ON * . * TO 'dsnackerstore'@'localhost';

ALTER USER 'dsnackerstore'@'localhost' IDENTIFIED WITH mysql_native_password BY 'dsnackerstore';


CREATE DATABASE  IF NOT EXISTS `dsnackerstore` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `dsnackerstore`;
-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: dsnackerstore
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authorities` (
  `username` varchar(50) CHARACTER SET latin1 NOT NULL,
  `authority` varchar(50) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`username`,`authority`),
  UNIQUE KEY `authorities_idx_1` (`username`,`authority`),
  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authorities`
--

LOCK TABLES `authorities` WRITE;
/*!40000 ALTER TABLE `authorities` DISABLE KEYS */;
INSERT INTO `authorities` VALUES ('baoanh','ROLE_EMPLOYEE'),('tueminh','ROLE_CUSTOMER');
/*!40000 ALTER TABLE `authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date_buy` datetime NOT NULL,
  `total` float DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `address` varchar(400) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `username` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `status` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_order_to_user_idx` (`username`),
  CONSTRAINT `fk_order_to_user` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (32,'2023-02-13 07:56:32',149,'Tuệ Minh','0935757897','Thu Duc, Ho Chi Minh City','tueminh','confirmed'),(33,'2023-02-13 07:58:00',149,'Tuệ Minh','0935757897','Thu Duc, Ho Chi Minh City','tueminh','confirmed'),(34,'2023-02-13 07:58:41',275,'Tuệ Minh','0935757897','Thu Duc, Ho Chi Minh City','tueminh','pending'),(35,'2023-02-13 07:59:48',177,'Thế Anh','09784224556','Dong Da, Ha Noi',NULL,'confirmed'),(36,'2023-02-13 08:02:09',297,'Bảo Vy','09754878977','Đà Lạt, Lâm Đồng',NULL,'confirmed'),(37,'2023-02-13 08:03:11',49,'Anh Khương','09754157878','Quận 1, TP. Hồ Chí Minh',NULL,'refused'),(38,'2023-02-13 08:47:05',176,'Duc Dao','0934968585','Thu Duc, Ho Chi Minh City',NULL,'confirmed');
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_detail`
--

DROP TABLE IF EXISTS `order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_detail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `sku` varchar(20) DEFAULT NULL,
  `order_id` int DEFAULT NULL,
  `price` float NOT NULL,
  `quantity` int NOT NULL,
  `total` float NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_detail_to_order_idx` (`order_id`),
  KEY `fk_order_detail_to_product_idx` (`sku`),
  CONSTRAINT `fk_detail_to_order` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_order_detail_to_product` FOREIGN KEY (`sku`) REFERENCES `product` (`sku`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_detail`
--

LOCK TABLES `order_detail` WRITE;
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
INSERT INTO `order_detail` VALUES (44,'NBC2022',32,50,1,50),(45,'AX2023',32,99,1,99),(46,'NBC2022',33,50,1,50),(47,'NK2023',33,99,1,99),(48,'NBC2022',34,50,1,50),(49,'AB2022',34,77,1,77),(50,'NL2022',34,49,1,49),(51,'ABC2023',34,99,1,99),(52,'NK2023',35,99,1,99),(53,'AH2023',35,78,1,78),(54,'AX2023',36,99,1,99),(55,'ABC2023',36,99,2,198),(56,'NL2022',37,49,1,49),(57,'AB2022',38,77,1,77),(58,'NK2023',38,99,1,99);
/*!40000 ALTER TABLE `order_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `sku` varchar(20) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `description` varchar(600) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `quantity` int NOT NULL,
  `price` float NOT NULL,
  `status` bit(1) NOT NULL,
  `img` varchar(2083) DEFAULT NULL,
  PRIMARY KEY (`sku`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES ('AB2022','Adidas Breaknet 2022','Cool, fast, feasiable.!',41,77,_binary '','https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/0b3008b093944479af51ad6600a9c497_9366/Forum_Low_Shoes_Green_H05109_01_standard.jpg'),('ABC2023','Adidas Brand Center 2023','Edited 2023.',100,99,_binary '','https://assets.myntassets.com/dpr_1.5,q_60,w_400,c_limit,fl_progressive/assets/images/18372888/2022/7/25/7e2b5db1-5ce8-43ba-9e98-0eaaa7409c601658739551501-ADIDAS-Originals-Men-Casual-Shoes-1071658739551351-1.jpg'),('AD2022','Adidas Dict 2022','',10,89,_binary '','https://i.pinimg.com/736x/1b/cb/9b/1bcb9bb7ed787d886c25714074cd04c5.jpg'),('AH2023','Adidas Air 2023','Zero to Hero',10,78,_binary '','https://images.unsplash.com/photo-1603787081151-cbebeef20092?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=464&q=80'),('AX2023','Adidas Extra 2023',NULL,20,99,_binary '','https://vn-test-11.slatic.net/p/959cb09c245e52a764adc2f2b27babce.jpg'),('NAMX2022','Nike Air Max 2022','Cool & Fast.!!!',21,79,_binary '\0','https://secure-render.nike.com/is/image/nikeid/?layer=0&src=ir(nikeidrender/am90365SU22_v1?obj=/s/g2&color=eef0f5&show&obj=/s/g6&color=edecee&show&obj=/s/g7&color=EAF0F8&show&obj=/s/g8&color=eef0f5&show&obj=/s/g9&color=edecee&show&obj=/s/g10&color=d59a87&show&obj=/s/g11&color=f1f2f3&show&obj=/s/g1/leather&color=edecee&show&obj=/s/g3/canvas&color=222720&show&obj=/s/g4/canvas&color=a4a1aa&show&obj=/s/g5/leather&color=edecee&show&obj=/s/g12/win&color=f1f2f3&show&obj=/s/g13&color=f3f3f4&show&obj=/s/g14&color=f3f3f4&show&obj=/s/g15&color=eef1f5&show&obj=/s/g16/solid&color=272729&show&obj=/s&req=object&wid=300)undefined&fmt=png8-alpha&posN=0,-0.1&icc=AdobeRGB'),('NBC2022','Nike Basic 2022','Basic Nike 2022.',22,50,_binary '','https://rukminim1.flixcart.com/image/832/832/xif0q/shoe/m/m/v/-original-imagg7szgcmfgauh.jpeg?q=70'),('NCF2023','Nike ColorFul 2023','Colorful, impressive, yoyo.',30,99,_binary '','https://static.nike.com/a/images/t_PDP_1280_v1/f_auto,q_auto:eco/2c212665-4de6-433e-82c0-83b1d5124be7/dbreak-womens-shoes-qLjcF9.png'),('NK2023','NikeRun 2023','Let go!!!!',40,99,_binary '','https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/2a87e3da-58fd-4cdf-8b11-aee7501a8ac5/revolution-6-next-nature-road-running-shoes-NC0P7k.png'),('NKR2023','Nike White Raw 2023','Full white box, so whiteeee.',20,49,_binary '','https://static.nike.com/a/images/f_auto,cs_srgb/w_1536,c_limit/2148e485-f33c-4cb1-ac6c-79a37aca28b2/men-s-shoes-clothing-accessories.png'),('NL2022','Nike Lowkey 2022','Cool & Secret',22,49,_binary '','https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/ae07b5f1-8847-4f07-957b-6bc625b9b308/sb-force-58-skate-shoes-LJNW5L.png'),('NW2022','Nike Weekend 2022','Free & Fast',20,70,_binary '\0','https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/e837b2bd-4786-4677-b356-9d65d360822a/air-max-systm-mens-shoes-TwgLWK.png');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_detail`
--

DROP TABLE IF EXISTS `user_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_detail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `fullname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `email` varchar(50) COLLATE utf8mb3_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_detail_to_user_idx` (`username`),
  CONSTRAINT `fk_detail_to_user` FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_detail`
--

LOCK TABLES `user_detail` WRITE;
/*!40000 ALTER TABLE `user_detail` DISABLE KEYS */;
INSERT INTO `user_detail` VALUES (18,'tueminh','Tue Minh','tueminh@gmail.com'),(19,'baoanh','Bao Anh','baoanh@dsnacker.com');
/*!40000 ALTER TABLE `user_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `username` varchar(50) CHARACTER SET latin1 NOT NULL,
  `password` char(68) CHARACTER SET latin1 NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('baoanh','$2a$10$NC7nucjW5IvzpzrYO82m9OisHpAXo77seOFWnf5RnI7/C55PyBKcm',1),('tueminh','$2a$10$NC7nucjW5IvzpzrYO82m9OisHpAXo77seOFWnf5RnI7/C55PyBKcm',1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-13  9:44:58
