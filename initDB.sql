-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: blogapp
-- ------------------------------------------------------
-- Server version	8.4.0

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
-- Table structure for table `blogs`
--

DROP TABLE IF EXISTS `blogs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blogs` (
  `id` varchar(255) NOT NULL,
  `content` longtext NOT NULL,
  `created_at` bigint DEFAULT NULL,
  `description` text,
  `image` varchar(255) DEFAULT NULL,
  `keyword` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `updated_at` bigint DEFAULT NULL,
  `blogger_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4i3ubwdi17sx97xmy2roj7crr` (`blogger_id`),
  CONSTRAINT `FK4i3ubwdi17sx97xmy2roj7crr` FOREIGN KEY (`blogger_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blogs`
--

LOCK TABLES `blogs` WRITE;
/*!40000 ALTER TABLE `blogs` DISABLE KEYS */;
/*!40000 ALTER TABLE `blogs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comments` (
  `id` varchar(255) NOT NULL,
  `content` text NOT NULL,
  `created_at` bigint DEFAULT NULL,
  `root_id` varchar(255) DEFAULT NULL,
  `blog_id` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9aakob3a7aghrm94k9kmbrjqd` (`blog_id`),
  KEY `FK8omq0tc18jd43bu5tjh6jvraq` (`user_id`),
  CONSTRAINT `FK8omq0tc18jd43bu5tjh6jvraq` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FK9aakob3a7aghrm94k9kmbrjqd` FOREIGN KEY (`blog_id`) REFERENCES `blogs` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permissions`
--

DROP TABLE IF EXISTS `permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permissions` (
  `id` varchar(255) NOT NULL,
  `active` bit(1) NOT NULL,
  `api_path` varchar(255) NOT NULL,
  `method` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permissions`
--

LOCK TABLES `permissions` WRITE;
/*!40000 ALTER TABLE `permissions` DISABLE KEYS */;
INSERT INTO `permissions` VALUES ('CREATE_BLOG',_binary '','/blog-api/blogs','POST','CREATE_BLOG'),('CREATE_COMMENT',_binary '','/blog-api/blogs/comments','POST','CREATE_COMMENT'),('DELETE_BLOG',_binary '','/blog-api/blogs/{id}','DELETE','DELETE_BLOG'),('DELETE_COMMENT',_binary '','/blog-api/blogs/comments/{id}','DELETE','DELETE_COMMENT'),('GET_ACCOUNT',_binary '','/blog-api/admin/auth/account','GET','GET_ACCOUNT'),('GET_ALL_BLOGS',_binary '','/blog-api/blogs','GET','GET_ALL_BLOGS'),('GET_ALL_COMMENTS',_binary '','/blog-api/blogs/comments','GET','GET_ALL_COMMENTS'),('GET_BLOG_BY_ID',_binary '','/blog-api/blogs/{id}','GET','GET_BLOG_BY_ID'),('GET_BLOGS_PAGINATION',_binary '','/blog-api/blogs-pagination','GET','GET_BLOGS_PAGINATION'),('GET_CAROUSEL_BLOGS',_binary '','/blog-api/blogs/carousel','GET','GET_CAROUSEL_BLOGS'),('GET_COMMENTS_BY_BLOG_ID',_binary '','/blog-api/blogs/comments/{blogId}','GET','GET_COMMENTS_BY_BLOG_ID'),('GET_CONTACT_ADMIN_INFO',_binary '','/blog-api/settings/contact','GET','GET_CONTACT_ADMIN_INFO'),('GET_RECENT_BLOGS',_binary '','/blog-api/blogs/recent','GET','GET_RECENT_BLOGS'),('GET_SETTING',_binary '','/blog-api/settings','GET','GET_SETTING'),('LOGIN_ADMIN',_binary '','/blog-api/admin/auth/login','POST','LOGIN_ADMIN'),('LOGIN_SOCIAL_MEDIA',_binary '','/blog-api/auth/social-media','POST','LOGIN_SOCIAL_MEDIA'),('LOGOUT',_binary '','/blog-api/auth/logout','POST','LOGOUT'),('READ_USER',_binary '','/blog-api/users/{id}','GET','READ_USER'),('REFRESH_TOKEN',_binary '','/blog-api/auth/refresh','POST','REFRESH_TOKEN'),('UPDATE_BLOG',_binary '','/blog-api/blogs','PUT','UPDATE_BLOG'),('UPDATE_SETTING',_binary '','/blog-api/settings','PUT','UPDATE_SETTING'),('UPDATE_USER',_binary '','/blog-api/users','PUT','UPDATE_USER');
/*!40000 ALTER TABLE `permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_permission`
--

DROP TABLE IF EXISTS `role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_permission` (
  `role_id` varchar(255) NOT NULL,
  `permission_id` varchar(255) NOT NULL,
  KEY `FK2xn8qv4vw30i04xdxrpvn3bdi` (`permission_id`),
  KEY `FKtfgq8q9blrp0pt1pvggyli3v9` (`role_id`),
  CONSTRAINT `FK2xn8qv4vw30i04xdxrpvn3bdi` FOREIGN KEY (`permission_id`) REFERENCES `permissions` (`id`),
  CONSTRAINT `FKtfgq8q9blrp0pt1pvggyli3v9` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_permission`
--

LOCK TABLES `role_permission` WRITE;
/*!40000 ALTER TABLE `role_permission` DISABLE KEYS */;
INSERT INTO `role_permission` VALUES ('ADMIN','CREATE_BLOG'),('ADMIN','CREATE_COMMENT'),('ADMIN','DELETE_BLOG'),('ADMIN','DELETE_COMMENT'),('ADMIN','GET_ACCOUNT'),('ADMIN','GET_ALL_BLOGS'),('ADMIN','GET_ALL_COMMENTS'),('ADMIN','GET_BLOG_BY_ID'),('ADMIN','GET_BLOGS_PAGINATION'),('ADMIN','GET_CAROUSEL_BLOGS'),('ADMIN','GET_COMMENTS_BY_BLOG_ID'),('ADMIN','GET_CONTACT_ADMIN_INFO'),('ADMIN','GET_RECENT_BLOGS'),('ADMIN','GET_SETTING'),('ADMIN','LOGIN_ADMIN'),('ADMIN','LOGIN_SOCIAL_MEDIA'),('ADMIN','LOGOUT'),('ADMIN','READ_USER'),('ADMIN','REFRESH_TOKEN'),('ADMIN','UPDATE_BLOG'),('ADMIN','UPDATE_SETTING'),('ADMIN','UPDATE_USER'),('USER','GET_ACCOUNT'),('USER','REFRESH_TOKEN'),('USER','LOGOUT'),('USER','LOGIN_SOCIAL_MEDIA'),('USER','GET_ALL_BLOGS'),('USER','GET_BLOGS_PAGINATION'),('USER','GET_BLOG_BY_ID'),('USER','GET_CAROUSEL_BLOGS'),('USER','GET_RECENT_BLOGS'),('USER','GET_ALL_COMMENTS'),('USER','GET_COMMENTS_BY_BLOG_ID'),('USER','CREATE_COMMENT'),('USER','GET_SETTING'),('USER','GET_CONTACT_ADMIN_INFO');
/*!40000 ALTER TABLE `role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` varchar(255) NOT NULL,
  `active` bit(1) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES ('ADMIN',_binary '','ADMIN'),('USER',_binary '','USER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `settings`
--

DROP TABLE IF EXISTS `settings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `settings` (
  `id` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `facebook_link` varchar(255) DEFAULT NULL,
  `fanpage_facebook_link` varchar(255) DEFAULT NULL,
  `instagram_link` varchar(255) DEFAULT NULL,
  `messenger_link` varchar(255) DEFAULT NULL,
  `site_name` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `x_link` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `settings`
--

LOCK TABLES `settings` WRITE;
/*!40000 ALTER TABLE `settings` DISABLE KEYS */;
INSERT INTO `settings` VALUES ('setting','Chào mừng các bạn đến với website của tôi!','thuanhmdev@gmail.com','','','','','ThuanHM','Trang chủ','');
/*!40000 ALTER TABLE `settings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` varchar(255) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `image_provider` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `provider` enum('GOOGLE','SYSTEM') DEFAULT NULL,
  `refresh_token` mediumtext,
  `username` varchar(255) NOT NULL,
  `role_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKp56c1712k691lhsyewcssf40f` (`role_id`),
  CONSTRAINT `FKp56c1712k691lhsyewcssf40f` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('03164884-02fa-49d6-8ba7-1441efddd48b','/avatar/default_user.png',NULL,'ADMIN','$2a$10$8PsAxt7wv9b9e1TnXzqRle2X1/wcJQq.9lx0sl15S8y40ofH48JSW','SYSTEM',NULL,'admin','ADMIN');
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

-- Dump completed on 2024-10-15  0:18:36
