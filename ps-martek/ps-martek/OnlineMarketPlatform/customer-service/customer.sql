-- tgapos.customer definition

CREATE TABLE `customer` (
  `_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `city` varchar(255) NOT NULL,
  `mobileNumber` DECIMAL(12,0),
  `addresses` varchar(255) DEFAULT NULL,
    `cardDetails` varchar(255) DEFAULT NULL,
        `contactDetails` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- tgapos.carddetails definition

CREATE TABLE `carddetails` (
  `card_id` int(11) NOT NULL AUTO_INCREMENT,
  `cardname` varchar(100) DEFAULT NULL,
  `cardnumber` varchar(100) NOT NULL,
  PRIMARY KEY (`card_id`),
  UNIQUE KEY `cardnumber` (`cardnumber`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- tgapos.address definition

CREATE TABLE `address` (
  `address_id` int(11) NOT NULL AUTO_INCREMENT,
  `line1` varchar(255) NOT NULL,
  `line2` varchar(255) DEFAULT NULL,
  `city` varchar(255) NOT NULL,
  `pinCode` varchar(255) NOT NULL,
  `billingAddress` char(1) DEFAULT NULL,
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;