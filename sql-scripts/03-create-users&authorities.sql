USE `dsnackerstore`;

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` char(68) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `users` 
VALUES 
('john','{bcrypt}$2a$08$rRU3B20vClKghqejK1R3mus9nMjkfTNALt413VpepN1vXZ8W5qK7u',1),
('mary','{bcrypt}$2a$08$rRU3B20vClKghqejK1R3mus9nMjkfTNALt413VpepN1vXZ8W5qK7u',1),
('susan','{bcrypt}$2a$08$rRU3B20vClKghqejK1R3mus9nMjkfTNALt413VpepN1vXZ8W5qK7u',1);


--
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `authorities_idx_1` (`username`,`authority`),
  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `authorities` 
VALUES 
('john','ROLE_CUSTOMER'),
('mary','ROLE_EMPLOYEE'),
('susan','ROLE_OWNER');