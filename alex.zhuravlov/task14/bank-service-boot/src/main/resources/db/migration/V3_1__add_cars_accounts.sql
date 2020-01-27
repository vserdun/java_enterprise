CREATE TABLE `cards_accounts` (
  `card_id` int(11) NOT NULL,
  `account_id` int(11) NOT NULL,
  PRIMARY KEY (`card_id`,`account_id`),
  KEY `FKqt3yk1wpdwtsgg933yubonvwg` (`account_id`),
  CONSTRAINT `FKna2ho2r15kd6p1duh51eixxrg` FOREIGN KEY (`card_id`) REFERENCES `cards` (`card_id`),
  CONSTRAINT `FKqt3yk1wpdwtsgg933yubonvwg` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;