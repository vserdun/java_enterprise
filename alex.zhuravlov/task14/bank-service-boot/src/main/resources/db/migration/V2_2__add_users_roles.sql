CREATE TABLE `users_roles` (
  `users_user_id` int(11) NOT NULL,
  `roles_id` bigint(20) NOT NULL,
  PRIMARY KEY (`users_user_id`,`roles_id`),
  KEY `FKa62j07k5mhgifpp955h37ponj` (`roles_id`),
  CONSTRAINT `FKa62j07k5mhgifpp955h37ponj` FOREIGN KEY (`roles_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `FKmaps3ffbyjaxkt50q1c7s7v5j` FOREIGN KEY (`users_user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;