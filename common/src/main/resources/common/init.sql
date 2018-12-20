-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: common
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
-- Table structure for table `migration`
--

DROP TABLE IF EXISTS `migration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `migration` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `file_name` varchar(255) DEFAULT NULL COMMENT '文件名称',
  `file_mdkey` varchar(255) DEFAULT NULL COMMENT '文件key',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `migration`
--

LOCK TABLES `migration` WRITE;
/*!40000 ALTER TABLE `migration` DISABLE KEYS */;
INSERT INTO `migration` VALUES (1,'common/init.sql','ae286d2e5b447b0e167a8bdc49c59b53','2018-12-04 09:38:58');
/*!40000 ALTER TABLE `migration` ENABLE KEYS */;
UNLOCK TABLES;

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
  `visibility` tinyint(4) DEFAULT '1' COMMENT '是否可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=405 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `model_msg`
--

LOCK TABLES `model_msg` WRITE;
/*!40000 ALTER TABLE `model_msg` DISABLE KEYS */;
INSERT INTO `model_msg` VALUES (225,'common','ver','699ad87c7687d41954ee878f6f28bef4','/common/ver/login','2018-12-07 05:51:05',NULL,1),(226,'common','ver','2cbbc42c6562c7941aa7cc5e62475cff','/common/ver/getImage','2018-12-07 05:51:05',NULL,1),(227,'common','ver','e70dff9e6b15cc617b91c1eea918abf1','/common/ver/logout','2018-12-07 05:51:05',NULL,1),(228,'common','ver','fcf5607978a3c6cb0550042feab4acae','/common/ver/getAllUrl','2018-12-07 05:51:06',NULL,1),(229,'common','sysconfig','e68a72e8038e22d3fd413c4868b4ee4c','/common/sysconfig/index','2018-12-07 05:51:06',NULL,1),(230,'common','sysconfig','9e3b82da03f657ce73100bc44b8e6aa8','/common/sysconfig/add','2018-12-07 05:51:06',NULL,1),(231,'common','sysconfig','8229fc4dd7de4c747eae6f9f5fa95973','/common/sysconfig/rev','2018-12-07 05:51:06',NULL,1),(232,'common','sysconfig','7525b1fce19b03b430e22f1fc1d22b85','/common/sysconfig/save','2018-12-07 05:51:06',NULL,1),(233,'common','sysconfig','c3e732a9bfa91ea27817aec5b4533ce6','/common/sysconfig/restore','2018-12-07 05:51:06',NULL,1),(234,'common','sysconfig','ad997ad575dafc371e89734c2c0b1c4d','/common/sysconfig/listRecycleByPage','2018-12-07 05:51:06',NULL,1),(235,'common','sysconfig','a3ba4306125b44be8e3a24130e449d9f','/common/sysconfig/listByPage','2018-12-07 05:51:06',NULL,1),(236,'common','sysconfig','ca065cea4e2a985db1e7d60cf24d4eb5','/common/sysconfig/listSelfByPage','2018-12-07 05:51:06',NULL,1),(237,'common','sysconfig','04224ca88eab1123d7d7a114ab000905','/common/sysconfig/editById','2018-12-07 05:51:06',NULL,1),(238,'common','sysconfig','668d2761fd84f86462dc1e9e256f9426','/common/sysconfig/editSelf','2018-12-07 05:51:06',NULL,1),(239,'common','sysconfig','25426d3391575cf815cf036aa71309a7','/common/sysconfig/batchRev','2018-12-07 05:51:06',NULL,1),(240,'common','sysconfig','efbaeea847f63f6c09f37a95ddddc140','/common/sysconfig/batchDel','2018-12-07 05:51:06',NULL,1),(241,'common','sysconfig','70994e610cdf2cb0cec427ac986b1674','/common/sysconfig/del','2018-12-07 05:51:06',NULL,1),(242,'common','sysconfig','779f02c19878fcd8b80d1e99e9862848','/common/sysconfig/indexRecycle','2018-12-07 05:51:06',NULL,1),(243,'common','urlmanage','dfe2dcc83af51e9069a7e823fda78cbd','/common/urlmanage/index','2018-12-07 05:51:06',NULL,1),(244,'common','urlmanage','c877fba9f0a4c6d45e6ff92c14004b86','/common/urlmanage/add','2018-12-07 05:51:06',NULL,1),(245,'common','urlmanage','80d48463a59d358b486304e5355a1242','/common/urlmanage/rev','2018-12-07 05:51:06',NULL,1),(246,'common','urlmanage','dd28d7af9c277dfd3e93e9795b038382','/common/urlmanage/save','2018-12-07 05:51:06',NULL,1),(247,'common','urlmanage','55bd8255c0ed4e162388c424c0f6212f','/common/urlmanage/restore','2018-12-07 05:51:06',NULL,1),(248,'common','urlmanage','970e4d91ecd26e0861aa7f6bd788dfc3','/common/urlmanage/listRecycleByPage','2018-12-07 05:51:06',NULL,1),(249,'common','urlmanage','f79c01eebd1a8d7921f208a11cc523fa','/common/urlmanage/listByPage','2018-12-07 05:51:06',NULL,1),(250,'common','urlmanage','6284ff7b09f5b9bcd5c48b1c2692c351','/common/urlmanage/listSelfByPage','2018-12-07 05:51:06',NULL,1),(251,'common','urlmanage','332da1b08349a3eef13368140981bd9b','/common/urlmanage/editById','2018-12-07 05:51:06',NULL,1),(252,'common','urlmanage','b52c984a37bcd5785cf9e4a4880836d0','/common/urlmanage/editSelf','2018-12-07 05:51:06',NULL,1),(253,'common','urlmanage','29cab5f1c7984d517e23a81dc1b50588','/common/urlmanage/batchRev','2018-12-07 05:51:06',NULL,1),(254,'common','urlmanage','351aa7bae304700661430af6641289ac','/common/urlmanage/batchDel','2018-12-07 05:51:06',NULL,1),(255,'common','urlmanage','2a99357c629090f5262bdf309a7bf5b1','/common/urlmanage/del','2018-12-07 05:51:07',NULL,1),(256,'common','urlmanage','2bb8c6b2cedafe50ae3a448cc3aef05b','/common/urlmanage/indexRecycle','2018-12-07 05:51:07',NULL,1),(257,'common','user','cdc6c2c5967a2bfde38043a43a4f373f','/common/user/list','2018-12-07 05:51:07',NULL,1),(258,'common','user','a4af42882e8f8298182c6e9a091f2391','/common/user/getRoles','2018-12-07 05:51:07',NULL,1),(259,'common','user','36d365d725cddb6ddb5270762e9983c5','/common/user/index','2018-12-07 05:51:07',NULL,1),(260,'common','user','0c2255b571bafd5a68d93443d4f5b4bb','/common/user/add','2018-12-07 05:51:07',NULL,1),(261,'common','user','d7721bb696b0e92ca36444bb27d1e113','/common/user/rev','2018-12-07 05:51:07',NULL,1),(262,'common','user','b9c9272f8b9837ca5342be2e2c0e13d4','/common/user/save','2018-12-07 05:51:07',NULL,1),(263,'common','user','51979d0c4dc8c16f5d8893de2cb61456','/common/user/restore','2018-12-07 05:51:07',NULL,1),(264,'common','user','acd0d1a3ef72dadb932d2d44bc190aee','/common/user/listRecycleByPage','2018-12-07 05:51:07',NULL,1),(265,'common','user','4d4d9af576ad10c9e6195a87e188fc74','/common/user/listByPage','2018-12-07 05:51:07',NULL,1),(266,'common','user','201531cd94b7e2e59ad312130718be6e','/common/user/listSelfByPage','2018-12-07 05:51:07',NULL,1),(267,'common','user','6ff2ff3b972f47bb69099e38ef75aa12','/common/user/editById','2018-12-07 05:51:07',NULL,1),(268,'common','user','b8401a9559f1259560efb4c00a9b66ee','/common/user/editSelf','2018-12-07 05:51:07',NULL,1),(269,'common','user','4727a9ffc007489823b698b92511024f','/common/user/batchRev','2018-12-07 05:51:07',NULL,1),(270,'common','user','10db99cf171052382b2a8bb67272f1b8','/common/user/batchDel','2018-12-07 05:51:07',NULL,1),(271,'common','user','87ec009241d853066dfacf7ce67932f1','/common/user/del','2018-12-07 05:51:07',NULL,1),(272,'common','user','b989f0b18853d7766cc8831f002f9d3f','/common/user/indexRecycle','2018-12-07 05:51:07',NULL,1),(273,'chipin','config','6d480a9d5931b5cb1929fa0f8aedf081','/chipin/config/index','2018-12-07 05:51:07',NULL,1),(274,'chipin','config','efd91706e00928bb9aca3a770ce09599','/chipin/config/add','2018-12-07 05:51:07',NULL,1),(275,'chipin','config','b06266eb929b7e933025d4c06301a021','/chipin/config/rev','2018-12-07 05:51:07',NULL,1),(276,'chipin','config','78078da85b39e7df7701451fceffa42e','/chipin/config/save','2018-12-07 05:51:07',NULL,1),(277,'chipin','config','fe341b739ed7144713230d0ead8f7344','/chipin/config/restore','2018-12-07 05:51:07',NULL,1),(278,'chipin','config','989efb476da40f35c1b5f1e6bf63b11a','/chipin/config/listRecycleByPage','2018-12-07 05:51:07',NULL,1),(279,'chipin','config','35275d3661698c92ee2c020bc5249620','/chipin/config/listByPage','2018-12-07 05:51:07',NULL,1),(280,'chipin','config','4dda5e6c2991d48d457e9f88bb496045','/chipin/config/listSelfByPage','2018-12-07 05:51:07',NULL,1),(281,'chipin','config','ccd84b250d82b95d2f722fed2032e48c','/chipin/config/editById','2018-12-07 05:51:07',NULL,1),(282,'chipin','config','4901d78af0847c63c3133aafa507cbd6','/chipin/config/editSelf','2018-12-07 05:51:07',NULL,1),(283,'chipin','config','32be8c9e4d7874748e68ae6a5fa67513','/chipin/config/batchRev','2018-12-07 05:51:07',NULL,1),(284,'chipin','config','2fbf85615b6883d0bcbda2e50a891d05','/chipin/config/batchDel','2018-12-07 05:51:07',NULL,1),(285,'chipin','config','cd55055916db99d5a2c1e32b97f9bc5a','/chipin/config/del','2018-12-07 05:51:07',NULL,1),(286,'chipin','config','298ffd7f6a69249de5b05cf82906646b','/chipin/config/indexRecycle','2018-12-07 05:51:07',NULL,1),(287,'chipin','lottery','46b06d15ffe06d17d06b6136f97a5a3a','/chipin/lottery/index','2018-12-07 05:51:07',NULL,1),(288,'chipin','lottery','2b5e7e36dbcd415189bae7f748c96919','/chipin/lottery/list','2018-12-07 05:51:07',NULL,1),(289,'chipin','lottery','95a60e4e8ba5fbac5694ad3f6e2e696b','/chipin/lottery/add','2018-12-07 05:51:07',NULL,1),(290,'chipin','lottery','24006e1d731f4935046dbf8d03c58463','/chipin/lottery/rev','2018-12-07 05:51:07',NULL,1),(291,'chipin','lottery','dae55b58565a7a7c5ca6aef9bba8924e','/chipin/lottery/save','2018-12-07 05:51:07',NULL,1),(292,'chipin','lottery','b872a8206add68017a59ddd33b615017','/chipin/lottery/restore','2018-12-07 05:51:07',NULL,1),(293,'chipin','lottery','b41a62c1dcae03fa253180a36c7109e4','/chipin/lottery/listRecycleByPage','2018-12-07 05:51:07',NULL,1),(294,'chipin','lottery','6e22a710512140503109500bd6c5830a','/chipin/lottery/listByPage','2018-12-07 05:51:08',NULL,1),(295,'chipin','lottery','d7618e22ecccb55724fcc5f4095955e9','/chipin/lottery/listSelfByPage','2018-12-07 05:51:08',NULL,1),(296,'chipin','lottery','b987fbc8eb8c31134135e9e8ecc9f226','/chipin/lottery/editById','2018-12-07 05:51:08',NULL,1),(297,'chipin','lottery','e1aa5f8fb855320a7d97a437593d4763','/chipin/lottery/editSelf','2018-12-07 05:51:08',NULL,1),(298,'chipin','lottery','337e9da3a625781cbb0ddfd3f8807557','/chipin/lottery/batchRev','2018-12-07 05:51:08',NULL,1),(299,'chipin','lottery','2792c0e4a96d967529a00859277f56a8','/chipin/lottery/batchDel','2018-12-07 05:51:08',NULL,1),(300,'chipin','lottery','100ad29ecd55611e9172c08e5711a2a0','/chipin/lottery/del','2018-12-07 05:51:08',NULL,1),(301,'chipin','lottery','c0edec82cd97da9e5e696b8a0e7cd638','/chipin/lottery/indexRecycle','2018-12-07 05:51:08',NULL,1),(302,'chipin','task','e4291ba16e037dc9a53b47bdc9e1e11a','/chipin/task/index','2018-12-07 05:51:08',NULL,1),(303,'chipin','task','87c6497f2a9724932cecbb8e3a6a5d5e','/chipin/task/add','2018-12-07 05:51:08',NULL,1),(304,'chipin','task','8eb5a55399c768d681e1ee56bf1c2015','/chipin/task/editById','2018-12-07 05:51:08',NULL,1),(305,'chipin','task','7a20307170392ea9aeeb191303d3806d','/chipin/task/del','2018-12-07 05:51:08',NULL,1),(306,'chipin','task','374df4aaf4b61710ae291a7c9d8d5b0a','/chipin/task/startMyJob','2018-12-07 05:51:08',NULL,1),(307,'chipin','task','dc828897f4ffad935bc5062abed7fe5d','/chipin/task/stopJob','2018-12-07 05:51:08',NULL,1),(308,'chipin','task','876122d340d64d5f2668843c1b15dfd2','/chipin/task/startJob','2018-12-07 05:51:08',NULL,1),(309,'chipin','task','6f962657559f99b13c7ed563449729d3','/chipin/task/stopMyJob','2018-12-07 05:51:08',NULL,1),(310,'chipin','task','82e573b89a39ae5dd5a8e120ec5787cd','/chipin/task/getFileContent','2018-12-07 05:51:08',NULL,1),(311,'chipin','task','b30a381a038dc4609ef61adf07882768','/chipin/task/rev','2018-12-07 05:51:08',NULL,1),(312,'chipin','task','738ae29d96d8d140e140413b3cfdb7c0','/chipin/task/save','2018-12-07 05:51:08',NULL,1),(313,'chipin','task','9d7a16b475dfd243dee27a81e0478209','/chipin/task/restore','2018-12-07 05:51:08',NULL,1),(314,'chipin','task','97439df868769ea834d83834229214b3','/chipin/task/listRecycleByPage','2018-12-07 05:51:08',NULL,1),(315,'chipin','task','f330b25bb12ff1040b1cd0c3ee08ceb0','/chipin/task/listByPage','2018-12-07 05:51:08',NULL,1),(316,'chipin','task','d427e2a463f34328767fa4157e3645bd','/chipin/task/listSelfByPage','2018-12-07 05:51:08',NULL,1),(317,'chipin','task','91dcb4653e767e046ded51841c5b5dbd','/chipin/task/editSelf','2018-12-07 05:51:08',NULL,1),(318,'chipin','task','1290d0d606c722a6fa24332e90530ee9','/chipin/task/batchRev','2018-12-07 05:51:08',NULL,1),(319,'chipin','task','73ef6b389e2b9001e1a5de6f1973f448','/chipin/task/batchDel','2018-12-07 05:51:08',NULL,1),(320,'chipin','task','c36264e80ebe4bee8b9d37997308743a','/chipin/task/indexRecycle','2018-12-07 05:51:08',NULL,1),(321,'chipin','userLottery','7e939cb4dcd92e473f6bf471e03ee44f','/chipin/userLottery/save','2018-12-07 05:51:08',NULL,1),(322,'chipin','userLottery','b1af5c730bdee8574d2d791fbe37548d','/chipin/userLottery/listSelfByPage','2018-12-07 05:51:08',NULL,1),(323,'chipin','userLottery','559a1ff2d529a00c1d72c8bf75248cda','/chipin/userLottery/del','2018-12-07 05:51:08',NULL,1),(324,'chipin','userLottery','988f344ab13e408f985ebfd719d63ca8','/chipin/userLottery/index','2018-12-07 05:51:08',NULL,1),(325,'chipin','userLottery','da36207b2d65f902b253f3aad1993820','/chipin/userLottery/add','2018-12-07 05:51:08',NULL,1),(326,'chipin','userLottery','660da2450f0e2d23917be30d16b6af68','/chipin/userLottery/rev','2018-12-07 05:51:08',NULL,1),(327,'chipin','userLottery','8bda27a17bb43fa150048199c8f14e76','/chipin/userLottery/restore','2018-12-07 05:51:08',NULL,1),(328,'chipin','userLottery','020808f862b60a830cfdeca616657cf5','/chipin/userLottery/listRecycleByPage','2018-12-07 05:51:08',NULL,1),(329,'chipin','userLottery','d230a439e8e35496f7d8580490a15746','/chipin/userLottery/listByPage','2018-12-07 05:51:08',NULL,1),(330,'chipin','userLottery','c16d434e3aac6cc447c9c8eedc835550','/chipin/userLottery/editById','2018-12-07 05:51:08',NULL,1),(331,'chipin','userLottery','454ddbcc8e467b1963737e3f78703370','/chipin/userLottery/editSelf','2018-12-07 05:51:08',NULL,1),(332,'chipin','userLottery','f639906d24989a6ffa12956aa5748e7f','/chipin/userLottery/batchRev','2018-12-07 05:51:08',NULL,1),(333,'chipin','userLottery','442a95b84fbef77254729fdacccfa26e','/chipin/userLottery/batchDel','2018-12-07 05:51:08',NULL,1),(334,'chipin','userLottery','5c9fa36c89514128657b6f66877984fe','/chipin/userLottery/indexRecycle','2018-12-07 05:51:08',NULL,1),(335,'permission','objPermission','83227f78d62b8e6778db6ac8a07fe3ae','/permission/objPermission/add','2018-12-07 05:51:08',NULL,1),(336,'permission','objPermission','ffde05390176298a1375c6a26cca2d39','/permission/objPermission/index','2018-12-07 05:51:08',NULL,1),(337,'permission','objPermission','9269102574e24ebaac139e7bd3afd906','/permission/objPermission/rev','2018-12-07 05:51:08',NULL,1),(338,'permission','objPermission','10ad824ac7cad405a47edffee5d595ed','/permission/objPermission/save','2018-12-07 05:51:08',NULL,1),(339,'permission','objPermission','fb03c397414ba160b41747c508fe55df','/permission/objPermission/restore','2018-12-07 05:51:09',NULL,1),(340,'permission','objPermission','39318dbf2f1e7ac6f74c8275af1929a3','/permission/objPermission/listRecycleByPage','2018-12-07 05:51:09',NULL,1),(341,'permission','objPermission','bf27f66025f55dbd4bfed4168901d1d3','/permission/objPermission/listByPage','2018-12-07 05:51:09',NULL,1),(342,'permission','objPermission','98d431107ffed9db313a8a43928d34aa','/permission/objPermission/listSelfByPage','2018-12-07 05:51:09',NULL,1),(343,'permission','objPermission','2fa8af50b0b29f7f1626fb83b00d2874','/permission/objPermission/editById','2018-12-07 05:51:09',NULL,1),(344,'permission','objPermission','fe1dfc4e6d6f4155a0656fbdf0f84943','/permission/objPermission/editSelf','2018-12-07 05:51:09',NULL,1),(345,'permission','objPermission','58dbfd1c58411a76ee4a40262da2f7b1','/permission/objPermission/batchRev','2018-12-07 05:51:09',NULL,1),(346,'permission','objPermission','6facc43b3241a7ce6779a8c5774552d5','/permission/objPermission/batchDel','2018-12-07 05:51:09',NULL,1),(347,'permission','objPermission','8f1ce62dbe1462fce9096d1ac8c17d69','/permission/objPermission/del','2018-12-07 05:51:09',NULL,1),(348,'permission','objPermission','878498bf5b674660849a4810e6591bce','/permission/objPermission/indexRecycle','2018-12-07 05:51:09',NULL,1),(349,'common','urlmanage','6b2274eedc82e8dc2725e3af2f0fb2ca','/common/urlmanage/loadSub','2018-12-07 06:53:27',NULL,1),(350,'common','modelmsg','3ad715b111715f45f6667eb95bb57dd1','/common/modelmsg/loadModuleName','2018-12-07 09:02:33',NULL,1),(351,'common','modelmsg','a2da8704e20387f314bd28c90bae4c7a','/common/modelmsg/loadByParam','2018-12-08 03:54:40',NULL,1),(352,'chipin','lottery','9246ace5c72e68e7f1a155db45b9c2d1','/chipin/lottery/copy','2018-12-08 13:38:12',NULL,1),(353,'common','index','612e43a075a600249cc6fa2ebe43300f','/common/index','2018-12-10 07:38:35',NULL,1),(354,'chipin','getAuthent','061f922951b2036c2a61b9a45895f116','/chipin/getAuthent','2018-12-10 07:38:36',NULL,1),(355,'chipin','getRule','2a151f1301228ad22be7ed7730e82b82','/chipin/getRule','2018-12-10 07:38:36',NULL,1),(356,'chipin','secretguid','00e6a43ce3ce15fc37903b1427d65907','/chipin/secretguid','2018-12-10 07:38:36',NULL,1),(357,'chipin','index','442b23ece4e5077e1dbbd808c8f04633','/chipin/index','2018-12-10 07:38:36',NULL,1),(358,'chipin','add','5e27d53999e6ffc7f558e027e7be1d0f','/chipin/add','2018-12-10 07:38:36',NULL,1),(359,'chipin','rev','b270eba3abc08366c559d04f1eb9a3ee','/chipin/rev','2018-12-10 07:38:36',NULL,1),(360,'chipin','save','a94c0d424e814b9cde495ccffcfccca3','/chipin/save','2018-12-10 07:38:36',NULL,1),(361,'chipin','restore','20c777ad75b5dfa685a75348a9d7dbc5','/chipin/restore','2018-12-10 07:38:36',NULL,1),(362,'chipin','listRecycleByPage','ccd1730e64349ba89c14f0f3ee46a276','/chipin/listRecycleByPage','2018-12-10 07:38:36',NULL,1),(363,'chipin','listByPage','d70008e1298b78edabe7a9dc099602ea','/chipin/listByPage','2018-12-10 07:38:36',NULL,1),(364,'chipin','listSelfByPage','a8246b8df910d32ac46f81184cedccf2','/chipin/listSelfByPage','2018-12-10 07:38:36',NULL,1),(365,'chipin','editById','86319d4f3851da893c03164c503c5134','/chipin/editById','2018-12-10 07:38:36',NULL,1),(366,'chipin','editSelf','d59130320489ad07ff02c1bb2921c7d2','/chipin/editSelf','2018-12-10 07:38:36',NULL,1),(367,'chipin','batchRev','46e6fe8e49cd582103e1b70a35f81128','/chipin/batchRev','2018-12-10 07:38:36',NULL,1),(368,'chipin','batchDel','b0a48f93365da03d6ce0ec5e15965958','/chipin/batchDel','2018-12-10 07:38:36',NULL,1),(369,'chipin','del','e05391c08f070173bca0556b014c46a3','/chipin/del','2018-12-10 07:38:36',NULL,1),(370,'chipin','indexRecycle','4b1bf17a76c7b1aa337716b22532d5e7','/chipin/indexRecycle','2018-12-10 07:38:36',NULL,1),(371,'controllerReflect','getUrlMapping','3bfd81b8d1d2b3baa1a5314d9fed13fe','/controllerReflect/getUrlMapping','2018-12-10 07:38:37',NULL,1),(372,'permission','iap','70597c6e758a06e5a291b8bf84f7f58c','/permission/iap','2018-12-10 07:38:37',NULL,1),(373,'permission','test','4ffbd3c52e1ad8ae6d0dd68d8b673930','/permission/test','2018-12-10 07:38:37',NULL,1),(374,'permission','getMenu','0d9dacfa2e67acafc04e3f0ba73da278','/permission/getMenu','2018-12-10 07:38:37',NULL,1),(375,'permission','getTab','cf65198714299da511d0804f155ccaa2','/permission/getTab','2018-12-10 07:38:37',NULL,1),(376,'permission','getOwnUrl','98a7302ca4bbb5c1e40089adc86b471e','/permission/getOwnUrl','2018-12-10 07:38:37',NULL,1),(377,'chipinlog','chipinLog','66a195be66dc4949f514a548b99ed41b','/chipinlog/chipinLog/index','2018-12-10 09:09:03',NULL,1),(378,'chipinlog','chipinLog','de763d128fcdfb2db38dab750ed786da','/chipinlog/chipinLog/add','2018-12-10 09:09:03',NULL,1),(379,'chipinlog','chipinLog','ee12953108445f069427bf79c498e4ee','/chipinlog/chipinLog/rev','2018-12-10 09:09:03',NULL,1),(380,'chipinlog','chipinLog','6ce7159b3e1317a84b7e53c7a06c0326','/chipinlog/chipinLog/save','2018-12-10 09:09:03',NULL,1),(381,'chipinlog','chipinLog','9ea1be8b282bdebbc2ba67ff91038bf8','/chipinlog/chipinLog/restore','2018-12-10 09:09:03',NULL,1),(382,'chipinlog','chipinLog','4f9ab6c7a07127c4fdb54049be4b0bd7','/chipinlog/chipinLog/listByPage','2018-12-10 09:09:03',NULL,1),(383,'chipinlog','chipinLog','b0ce51f01147d34a406483d32b6c00c1','/chipinlog/chipinLog/listSelfByPage','2018-12-10 09:09:03',NULL,1),(384,'chipinlog','chipinLog','baf9d1c078cffc1e26e5c97a45b32d76','/chipinlog/chipinLog/editById','2018-12-10 09:09:03',NULL,1),(385,'chipinlog','chipinLog','0dae9a026c7317cac36812c7796fa101','/chipinlog/chipinLog/editSelf','2018-12-10 09:09:03',NULL,1),(386,'chipinlog','chipinLog','fd455a19dc14dda3cd9513a08bc303a1','/chipinlog/chipinLog/batchRev','2018-12-10 09:09:03',NULL,1),(387,'chipinlog','chipinLog','215d221bd46c9d6a70d35096c488e1fe','/chipinlog/chipinLog/batchDel','2018-12-10 09:09:03',NULL,1),(388,'chipinlog','chipinLog','aa6b71de1ccaec5d9840b478c736da78','/chipinlog/chipinLog/del','2018-12-10 09:09:03',NULL,1),(389,'chipinlog','chipinLog','69d1c88183f3b814c581c21647b016ca','/chipinlog/chipinLog/indexRecycle','2018-12-10 09:09:03',NULL,1),(390,'chipinlog','chipinLog','1fa06569d5194d967e940e54187f05bb','/chipinlog/chipinLog/listRecycleByPage','2018-12-10 09:09:03',NULL,1),(391,'chipin','chipinLog','c329bec2424c5db862bf67552488adcd','/chipin/chipinLog/index','2018-12-10 09:16:18',NULL,1),(392,'chipin','chipinLog','badfed1fe44550c4a14dfc95de56f89a','/chipin/chipinLog/add','2018-12-10 09:16:19',NULL,1),(393,'chipin','chipinLog','08f6cc650b353e3fda445706825b7717','/chipin/chipinLog/rev','2018-12-10 09:16:19',NULL,1),(394,'chipin','chipinLog','f590c60f443a7bdc8b8bad3a21e2cc92','/chipin/chipinLog/save','2018-12-10 09:16:19',NULL,1),(395,'chipin','chipinLog','7e298a3d6a3c0c42f56f1c6107a264d5','/chipin/chipinLog/restore','2018-12-10 09:16:19',NULL,1),(396,'chipin','chipinLog','8b407c2bc0666907a6e9175260df34b6','/chipin/chipinLog/listRecycleByPage','2018-12-10 09:16:19',NULL,1),(397,'chipin','chipinLog','958b37fa3f67aa5f2427af1a6f27735f','/chipin/chipinLog/listByPage','2018-12-10 09:16:19',NULL,1),(398,'chipin','chipinLog','d009743c60db096d92babdeb10a786f0','/chipin/chipinLog/listSelfByPage','2018-12-10 09:16:19',NULL,1),(399,'chipin','chipinLog','d2c2d0b374a2be9fe7edadc2139e5179','/chipin/chipinLog/editById','2018-12-10 09:16:19',NULL,1),(400,'chipin','chipinLog','598350873ec78bba2928c92ca0dd904e','/chipin/chipinLog/editSelf','2018-12-10 09:16:19',NULL,1),(401,'chipin','chipinLog','11df278126c5fd8d4491ee49e46fee15','/chipin/chipinLog/batchRev','2018-12-10 09:16:19',NULL,1),(402,'chipin','chipinLog','afbb545bb6bc512284c79d0589bf0c8c','/chipin/chipinLog/batchDel','2018-12-10 09:16:19',NULL,1),(403,'chipin','chipinLog','1a3fb0ea578bf52860f1ff073796e828','/chipin/chipinLog/del','2018-12-10 09:16:19',NULL,1),(404,'chipin','chipinLog','9dddd8ee8013f0d2aebe7f0371f45896','/chipin/chipinLog/indexRecycle','2018-12-10 09:16:19',NULL,1);
/*!40000 ALTER TABLE `model_msg` ENABLE KEYS */;
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
  `visibility` tinyint(4) DEFAULT '0' COMMENT '是否可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_config`
--

LOCK TABLES `sys_config` WRITE;
/*!40000 ALTER TABLE `sys_config` DISABLE KEYS */;
INSERT INTO `sys_config` VALUES (1,'INTERCEPTOR','APP','','2018-11-23 19:39:21',NULL,1);
/*!40000 ALTER TABLE `sys_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `url_manage`
--

DROP TABLE IF EXISTS `url_manage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `url_manage` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `text` varchar(50) DEFAULT NULL COMMENT '名称',
  `href` varchar(128) DEFAULT NULL COMMENT '链接',
  `url_type` varchar(1) DEFAULT NULL COMMENT '链接类型',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父级ID',
  `md_url` varchar(128) DEFAULT NULL COMMENT 'md5加密链接',
  `app_name` varchar(64) DEFAULT NULL COMMENT '应用名称',
  `module_name` varchar(64) DEFAULT NULL COMMENT '模块名称',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varchar(50) DEFAULT NULL COMMENT '创建人',
  `root_id` bigint(20) DEFAULT NULL COMMENT '根ID',
  `visibility` tinyint(4) DEFAULT NULL COMMENT '是否可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `url_manage`
--

LOCK TABLES `url_manage` WRITE;
/*!40000 ALTER TABLE `url_manage` DISABLE KEYS */;
INSERT INTO `url_manage` VALUES (1,'系统管理','/common/admin','1',0,'f1ed438770c0e684810448a29fac1b4e','common','admin','2018-12-07 06:13:50','admin',0,1),(2,'下注系统','/common/chipin','1',0,'346154e7b140c622fb4bfc30b7c47387','common','chipin','2018-12-07 06:13:50','admin',0,1),(3,'系统管理','/common/admin/manage','2',0,'be104f970f3a964ceace27f388295486','common',NULL,'2018-12-07 06:13:50','admin',1,1),(4,'用户管理','/common/user/index','2',3,'36d365d725cddb6ddb5270762e9983c5','common','user','2018-12-07 06:13:50','admin',1,1),(5,'系统配置','/common/sysconfig/index','2',3,'e68a72e8038e22d3fd413c4868b4ee4c','common','sysconfig','2018-12-07 06:13:50','admin',1,1),(6,'下注管理','/common/chipin/manage','2',0,'e6353c9ea0c94c04002aab661457f495','chipin',NULL,'2018-12-07 06:13:50','admin',2,1),(7,'下注网站','/chipin/lottery/index','2',6,'46b06d15ffe06d17d06b6136f97a5a3a','chipin','lottery','2018-12-07 06:13:50','admin',2,1),(8,'网站用户','/chipin/userLottery/index','2',6,'988f344ab13e408f985ebfd719d63ca8','chipin','userLottery','2018-12-07 06:13:50','admin',2,1),(9,'下注任务','/chipin/task/index','2',6,'e4291ba16e037dc9a53b47bdc9e1e11a','chipin','task','2018-12-07 06:13:50','admin',2,1),(10,'平台配置','/chipin/config/index','2',6,'6d480a9d5931b5cb1929fa0f8aedf081','chipin','config','2018-12-07 06:36:53','admin',2,1),(11,'栏目管理','/common/urlmanage/index','2',3,'dfe2dcc83af51e9069a7e823fda78cbd','common','urlmanage','2018-12-07 06:13:50','admin',1,1),(12,'权限管理','/permission/objPermission/index','2',3,'72294e7d929d1d9c8a0b037275a81c8f','permission','objPermission','2018-12-07 08:15:58','admin',1,1),(13,'下注日志','/chipin/chipinLog/index','2',6,'c329bec2424c5db862bf67552488adcd','chipin','chipinLog','2018-12-10 09:15:15',NULL,2,1);
/*!40000 ALTER TABLE `url_manage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `user_name` varchar(64) DEFAULT NULL COMMENT '用户名',
  `true_name` varchar(64) DEFAULT NULL COMMENT '真实名',
  `pwd` varchar(128) DEFAULT NULL COMMENT '密码',
  `phone` char(20) DEFAULT NULL COMMENT '电话',
  `sex` char(1) DEFAULT '1' COMMENT '性别',
  `role_id` tinyint(4) DEFAULT NULL COMMENT '角色ID',
  `visibility` char(1) DEFAULT NULL COMMENT '是否可用',
  `role_name` varchar(64) DEFAULT NULL COMMENT '角色名称',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varchar(64) DEFAULT NULL COMMENT '创建人',
  `update_user` varchar(64) DEFAULT NULL COMMENT '修改人',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='用户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','管理员','e10adc3949ba59abbe56e057f20f883e',NULL,'1',1,'1','ADMIN','2018-11-04 16:25:01',NULL,NULL,'2018-10-28 23:09:39'),(2,'kai','恺','e10adc3949ba59abbe56e057f20f883e','13234566545','1',2,'1','COMMON','2018-11-23 14:02:13','kai',NULL,'2018-11-23 14:02:13'),(3,'zhangshan','张三','e10adc3949ba59abbe56e057f20f883e','13432345676','1',2,'0','COMMON','2018-12-10 06:29:58',NULL,NULL,'2018-12-10 06:29:58'),(4,'huang','黄','e10adc3949ba59abbe56e057f20f883e','13454545678','1',3,'1','MANAGER','2018-12-16 11:51:19','admin',NULL,'2018-12-16 11:51:19');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-20 15:46:18
