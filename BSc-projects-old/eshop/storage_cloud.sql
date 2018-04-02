drop database IF EXISTS storage_cloud;
create database storage_cloud;
use storage_cloud;

CREATE TABLE `users` (
  `username` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `mymoney` int(11) DEFAULT NULL,
  `wra` bigint(20) DEFAULT NULL,
  `xrewsi` double NOT NULL DEFAULT '0',
  `login` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`username`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `cards` (
  `card` varchar(20) DEFAULT NULL,
  `date` varchar(20) DEFAULT NULL,
  `cvv` varchar(3) DEFAULT NULL,
  `username` varchar(45) NOT NULL,
  PRIMARY KEY (`username`),
  KEY `fk_CARDS_USERS` (`username`),
  CONSTRAINT `fk_CARDS_USERS` FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `space` (
  `mylimit` int(20) DEFAULT NULL,
  `public_size` int(20) DEFAULT NULL,
  `username` varchar(45) NOT NULL,
  `current` int(20) DEFAULT NULL,
  `bonus` int(11) DEFAULT NULL,
  PRIMARY KEY (`username`),
  KEY `fk_SPACE_USERS` (`username`),
  CONSTRAINT `fk_SPACE_USERS` FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `directories` (
  `name` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  PRIMARY KEY (`name`,`username`),
  KEY `fk_DIRECTORIES_USERS1` (`username`),
  CONSTRAINT `fk_DIRECTORIES_USERS1` FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `files` (
  `name` varchar(45) NOT NULL,
  `size` int(11) NOT NULL,
  `is_public` tinyint(1) NOT NULL,
  `price` int(11) DEFAULT NULL,
  `popularity` int(11) NOT NULL,
  `directory` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `apodosi` bigint(20) DEFAULT NULL,
  `wra` bigint(20) DEFAULT '0',
  PRIMARY KEY (`name`,`directory`,`username`),
  KEY `fk_FILES_DIRECTORIES1` (`directory`,`username`),
  CONSTRAINT `fk_FILES_DIRECTORIES1` FOREIGN KEY (`directory`, `username`) REFERENCES `directories` (`name`, `username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

