-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: permission
-- ------------------------------------------------------
-- Server version	5.7.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `model_msg`
--

DROP TABLE IF EXISTS `model_msg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `model_msg` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `app_name` varchar(20) DEFAULT NULL COMMENT '应用名称',
  `module_name` varchar(20) DEFAULT NULL COMMENT '模块名称',
  `md_key` varchar(128) DEFAULT NULL COMMENT 'url的md5加密',
  `url` varchar(255) DEFAULT NULL COMMENT '模块链接',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varchar(50) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `model_msg`
--

LOCK TABLES `model_msg` WRITE;
/*!40000 ALTER TABLE `model_msg` DISABLE KEYS */;
INSERT INTO `model_msg` VALUES (1,'chipin','lottery','2b5e7e36dbcd415189bae7f748c96919','/chipin/lottery/list','2018-10-29 09:50:09',NULL),(2,'chipin','lottery','46b06d15ffe06d17d06b6136f97a5a3a','/chipin/lottery/index','2018-10-29 09:50:09',NULL),(3,'chipin','lottery','95a60e4e8ba5fbac5694ad3f6e2e696b','/chipin/lottery/add','2018-10-29 09:50:09',NULL),(4,'chipin','lottery','24006e1d731f4935046dbf8d03c58463','/chipin/lottery/rev','2018-10-29 09:50:09',NULL),(5,'chipin','lottery','dae55b58565a7a7c5ca6aef9bba8924e','/chipin/lottery/save','2018-10-29 09:50:10',NULL),(6,'chipin','lottery','b987fbc8eb8c31134135e9e8ecc9f226','/chipin/lottery/editById','2018-10-29 09:50:10',NULL),(7,'chipin','lottery','337e9da3a625781cbb0ddfd3f8807557','/chipin/lottery/batchRev','2018-10-29 09:50:10',NULL),(8,'chipin','lottery','6e22a710512140503109500bd6c5830a','/chipin/lottery/listByPage','2018-10-29 09:50:10',NULL),(9,'chipin','lottery','100ad29ecd55611e9172c08e5711a2a0','/chipin/lottery/del','2018-10-29 09:50:10',NULL),(10,'chipin','sysconfig','a5c69c5cf2a9332a370a7ed99665060d','/chipin/sysconfig/index','2018-10-29 09:50:10',NULL),(11,'chipin','sysconfig','ee79e9908117822a7512169bf637755e','/chipin/sysconfig/add','2018-10-29 09:50:10',NULL),(12,'chipin','sysconfig','a0af19c3f99464ff96f9e91094cbd14b','/chipin/sysconfig/rev','2018-10-29 09:50:10',NULL),(13,'chipin','sysconfig','14538376fd85d6a1c84ad5e729bdbb33','/chipin/sysconfig/save','2018-10-29 09:50:10',NULL),(14,'chipin','sysconfig','71202269f986ac05584ede3f4545b269','/chipin/sysconfig/editById','2018-10-29 09:50:10',NULL),(15,'chipin','sysconfig','138710cedcea43fc5fc21347b7830990','/chipin/sysconfig/batchRev','2018-10-29 09:50:10',NULL),(16,'chipin','sysconfig','0ee2c7d2226621e425f204e97aa6330a','/chipin/sysconfig/listByPage','2018-10-29 09:50:10',NULL),(17,'chipin','sysconfig','4c05066deb130155516cee6de3916f5c','/chipin/sysconfig/del','2018-10-29 09:50:10',NULL),(18,'chipin','task','8eb5a55399c768d681e1ee56bf1c2015','/chipin/task/editById','2018-10-29 09:50:10',NULL),(19,'chipin','task','7a20307170392ea9aeeb191303d3806d','/chipin/task/del','2018-10-29 09:50:10',NULL),(20,'chipin','task','876122d340d64d5f2668843c1b15dfd2','/chipin/task/startJob','2018-10-29 09:50:10',NULL),(21,'chipin','task','dc828897f4ffad935bc5062abed7fe5d','/chipin/task/stopJob','2018-10-29 09:50:10',NULL),(22,'chipin','task','82e573b89a39ae5dd5a8e120ec5787cd','/chipin/task/getFileContent','2018-10-29 09:50:10',NULL),(23,'chipin','task','e4291ba16e037dc9a53b47bdc9e1e11a','/chipin/task/index','2018-10-29 09:50:10',NULL),(24,'chipin','task','87c6497f2a9724932cecbb8e3a6a5d5e','/chipin/task/add','2018-10-29 09:50:10',NULL),(25,'chipin','task','b30a381a038dc4609ef61adf07882768','/chipin/task/rev','2018-10-29 09:50:10',NULL),(26,'chipin','task','738ae29d96d8d140e140413b3cfdb7c0','/chipin/task/save','2018-10-29 09:50:10',NULL),(27,'chipin','task','1290d0d606c722a6fa24332e90530ee9','/chipin/task/batchRev','2018-10-29 09:50:10',NULL),(28,'chipin','task','f330b25bb12ff1040b1cd0c3ee08ceb0','/chipin/task/listByPage','2018-10-29 09:50:10',NULL),(29,'chipin','user','f8f39bb0773600f6688588c2beb61a8a','/chipin/user/list','2018-10-29 09:50:10',NULL),(30,'chipin','user','4185e8e3c57112c45027e36e4755106d','/chipin/user/index','2018-10-29 09:50:10',NULL),(31,'chipin','user','95f69c0f50c09c52d2b7a35e2c43411f','/chipin/user/add','2018-10-29 09:50:10',NULL),(32,'chipin','user','521d6e62b448af0f5582d2e4de933a24','/chipin/user/rev','2018-10-29 09:50:10',NULL),(33,'chipin','user','41aa2c848271c488c0a4b76be23fa674','/chipin/user/save','2018-10-29 09:50:10',NULL),(34,'chipin','user','275ef88c6d28cf32c6dcbbda6792e09b','/chipin/user/editById','2018-10-29 09:50:10',NULL),(35,'chipin','user','039cb523f69889c1b33108fb47d2ae38','/chipin/user/batchRev','2018-10-29 09:50:10',NULL),(36,'chipin','user','620c5e61f029522d897510e2eaf83115','/chipin/user/listByPage','2018-10-29 09:50:10',NULL),(37,'chipin','user','17546a9123f57f2fe2adbc3212bd011c','/chipin/user/del','2018-10-29 09:50:10',NULL),(38,'chipin','userLottery','7e939cb4dcd92e473f6bf471e03ee44f','/chipin/userLottery/save','2018-10-29 09:50:10',NULL),(39,'chipin','userLottery','559a1ff2d529a00c1d72c8bf75248cda','/chipin/userLottery/del','2018-10-29 09:50:10',NULL),(40,'chipin','userLottery','988f344ab13e408f985ebfd719d63ca8','/chipin/userLottery/index','2018-10-29 09:50:10',NULL),(41,'chipin','userLottery','da36207b2d65f902b253f3aad1993820','/chipin/userLottery/add','2018-10-29 09:50:10',NULL),(42,'chipin','userLottery','660da2450f0e2d23917be30d16b6af68','/chipin/userLottery/rev','2018-10-29 09:50:10',NULL),(43,'chipin','userLottery','c16d434e3aac6cc447c9c8eedc835550','/chipin/userLottery/editById','2018-10-29 09:50:10',NULL),(44,'chipin','userLottery','f639906d24989a6ffa12956aa5748e7f','/chipin/userLottery/batchRev','2018-10-29 09:50:10',NULL),(45,'chipin','userLottery','d230a439e8e35496f7d8580490a15746','/chipin/userLottery/listByPage','2018-10-29 09:50:10',NULL);
/*!40000 ALTER TABLE `model_msg` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `obj_permission`
--

DROP TABLE IF EXISTS `obj_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `obj_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `obj_id` bigint(20) DEFAULT NULL COMMENT '对象ID',
  `obj_type` varchar(20) DEFAULT NULL COMMENT '对象类型',
  `md_key` varchar(128) DEFAULT NULL COMMENT 'url的md5加密值',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varbinary(50) DEFAULT NULL COMMENT '创建人',
  `url` varchar(255) DEFAULT NULL COMMENT '访问链接',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `obj_permission`
--

LOCK TABLES `obj_permission` WRITE;
/*!40000 ALTER TABLE `obj_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `obj_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `obj_relation`
--

DROP TABLE IF EXISTS `obj_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `obj_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `obj_id` bigint(20) DEFAULT NULL COMMENT '对象ID',
  `obj_type` varchar(20) DEFAULT NULL COMMENT '对象类型',
  `rel_id` bigint(20) DEFAULT NULL COMMENT '关联ID',
  `rel_type` varchar(20) DEFAULT NULL COMMENT '关联类型',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_user` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `obj_relation`
--

LOCK TABLES `obj_relation` WRITE;
/*!40000 ALTER TABLE `obj_relation` DISABLE KEYS */;
/*!40000 ALTER TABLE `obj_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_config`
--

DROP TABLE IF EXISTS `sys_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `cfg_key_prefix` varchar(50) DEFAULT NULL COMMENT 'key前缀',
  `cfg_key_suffix` varchar(50) DEFAULT NULL COMMENT 'key后缀',
  `cfg_value` varchar(255) DEFAULT NULL COMMENT '配置值',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varchar(50) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_config`
--

LOCK TABLES `sys_config` WRITE;
/*!40000 ALTER TABLE `sys_config` DISABLE KEYS */;
INSERT INTO `sys_config` VALUES (1,'INTERCEPTOR','APP','chipin,permission','2018-10-29 13:51:22',NULL);
/*!40000 ALTER TABLE `sys_config` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-31 10:18:58
