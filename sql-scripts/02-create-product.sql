CREATE DATABASE  IF NOT EXISTS `dsnackerstore`;
USE `dsnackerstore`;

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `sku` varchar(10) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `description` varchar(600) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `quantity` int NOT NULL,
  `price` float NOT NULL,
  `status` bit(1) NOT NULL,
  PRIMARY KEY (`sku`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `dsnackerstore`.`product` (`sku`, `name`, `description`, `quantity`, `price`, `status`) 
VALUES ('AD2022', 'Adidas Dict 2022', 'Free in your', 20, 100, 1);
INSERT INTO `dsnackerstore`.`product` (`sku`, `name`, `description`, `quantity`, `price`, `status`) 
VALUES ('AB2021', 'Adidas Breaknet 2021', 'Cool, fast, feasiable', 20, 50, 1);
INSERT INTO `dsnackerstore`.`product` (`sku`, `name`, `description`, `quantity`, `price`, `status`) 
VALUES ('AB2022', 'Adidas Breaknet 2022', 'Cool, fast, feasiable', 10, 100, 1);
INSERT INTO `dsnackerstore`.`product` (`sku`, `name`, `description`, `quantity`, `price`, `status`) 
VALUES ('NW2022', 'Nike Weekend 2022', 'Free & Fast', 20, 70, 1);
INSERT INTO `dsnackerstore`.`product` (`sku`, `name`, `description`, `quantity`, `price`, `status`) 
VALUES ('NL2022', 'Nike Lowkey 2022', 'Cool & Secret', 22, 111, 1);

SELECT * FROM dsnackerstore.product;