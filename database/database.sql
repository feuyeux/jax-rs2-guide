DROP DATABASE IF EXISTS `simple_service_book`;
CREATE DATABASE `simple_service_book`;
USE `simple_service_book`;
CREATE TABLE `simple_book` (
  `BOOKID` int(11) NOT NULL AUTO_INCREMENT,
  `BOOKNAME` varchar(128) DEFAULT NULL,
  `PUBLISHER` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`BOOKID`),
  UNIQUE KEY `BOOKID` (`BOOKID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
LOCK TABLES `simple_book` WRITE;
INSERT INTO `simple_book` VALUES (1,'Java Restful Web Service使用指南','cmpbook'),
(2,'JSF2和RichFaces4使用指南','phei');
UNLOCK TABLES;