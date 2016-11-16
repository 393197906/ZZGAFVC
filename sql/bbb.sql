/*
SQLyog Ultimate v11.27 (32 bit)
MySQL - 5.5.40 : Database - face
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`face` /*!40100 DEFAULT CHARACTER SET utf8 */;

/*Table structure for table `face_camera` */

DROP TABLE IF EXISTS `face_camera`;

CREATE TABLE `face_camera` (
  `CameraId` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `CameraName` varchar(50) NOT NULL COMMENT '摄像机名称',
  `CameraIPAddress` varchar(50) NOT NULL COMMENT '摄像机ip地址',
  `CameraPlaceId` int(11) NOT NULL COMMENT '摄像机所在地ID',
  `PersonType` varchar(50) DEFAULT NULL COMMENT '监控人员类型',
  `CameraUser` varchar(50) NOT NULL COMMENT '摄像机登陆名',
  `CameraPassword` varchar(200) NOT NULL COMMENT '摄像机登陆密码',
  `CameraSourceId` varchar(50) NOT NULL COMMENT 'sourceId',
  `CameraUrl` varchar(200) NOT NULL COMMENT 'url',
  PRIMARY KEY (`CameraId`,`CameraSourceId`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `face_camera` */

insert  into `face_camera`(`CameraId`,`CameraName`,`CameraIPAddress`,`CameraPlaceId`,`PersonType`,`CameraUser`,`CameraPassword`,`CameraSourceId`,`CameraUrl`) values (6,'测试摄像机','192.168.5.142',64,NULL,'admin','admin123','c1','rtsp://admin:admin123@192.168.5.142:554/h264/01/sub/av_stream'),(8,'测试摄像机3','192.168.5.142',68,NULL,'admin','admin123','c3','rtsp://admin:admin123@192.168.5.142:554/h264/01/sub/av_stream'),(9,'测试摄像机4','192.168.5.142',69,NULL,'admin','admin123','c4','rtsp://admin:admin123@192.168.5.142:554/h264/01/sub/av_stream'),(10,'摄像机','192.168.5.142',64,NULL,'admin','admin123','Camera01','rtsp://admin:admin123@192.168.5.142:554/h264/01/sub/av_stream'),(11,'url','192.168.5.140',64,NULL,'admin','admin','url','rtsp://admin:admin123@192.168.5.142:554/h264/01/sub/av_stream');

/*Table structure for table `face_camera_place` */

DROP TABLE IF EXISTS `face_camera_place`;

CREATE TABLE `face_camera_place` (
  `placeId` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `placeName` varchar(50) DEFAULT NULL COMMENT '地点名称',
  `parentId` int(11) DEFAULT NULL COMMENT '父节点Id',
  PRIMARY KEY (`placeId`)
) ENGINE=MyISAM AUTO_INCREMENT=83 DEFAULT CHARSET=utf8;

/*Data for the table `face_camera_place` */

insert  into `face_camera_place`(`placeId`,`placeName`,`parentId`) values (64,'地点1',NULL),(68,'地点3',0),(69,'出口2',NULL);

/*Table structure for table `face_camera_to_user` */

DROP TABLE IF EXISTS `face_camera_to_user`;

CREATE TABLE `face_camera_to_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `cameraSourceId` varchar(50) DEFAULT NULL COMMENT '摄像机sourceId',
  `userId` int(11) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `face_camera_to_user` */

insert  into `face_camera_to_user`(`id`,`cameraSourceId`,`userId`) values (9,'url',5),(8,'Camera01',5),(7,'c1',5),(4,'c1',4),(5,'Camera01',3),(6,'c1',3);

/*Table structure for table `face_police_company` */

DROP TABLE IF EXISTS `face_police_company`;

CREATE TABLE `face_police_company` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(20) DEFAULT NULL COMMENT '单位名称',
  `parentId` int(11) DEFAULT '0' COMMENT '父级单位id',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `face_police_company` */

insert  into `face_police_company`(`id`,`name`,`parentId`) values (1,'枣庄市公安局',0),(2,'山亭分局',1),(3,'滕州市局',1),(4,'市中分局',1),(5,'峄城分局',1),(6,'薛城分局',1),(7,'台儿庄分局',1),(8,'光明路派出所',4),(9,'解放路派出所',4),(10,'常庄派出所',6);

/*Table structure for table `face_police_response` */

DROP TABLE IF EXISTS `face_police_response`;

CREATE TABLE `face_police_response` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `auditPolicemanId` int(11) DEFAULT NULL COMMENT '审核民警id',
  `auditTime` char(20) DEFAULT NULL COMMENT '审核时间',
  `responsePolicemanId` int(11) DEFAULT NULL COMMENT '出警民警id',
  `responseTime` char(20) DEFAULT NULL COMMENT '出警时间',
  `responseCompanyId` int(11) DEFAULT NULL COMMENT '出警单位id',
  `responseReason` varchar(50) DEFAULT NULL COMMENT '出警事由',
  `responseResult` varchar(50) DEFAULT NULL COMMENT '出警结果',
  `hitId` int(11) NOT NULL COMMENT '报警信息id',
  `hitTime` bigint(20) DEFAULT NULL COMMENT '报警时间',
  PRIMARY KEY (`id`,`hitId`)
) ENGINE=MyISAM AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;

/*Data for the table `face_police_response` */

insert  into `face_police_response`(`id`,`auditPolicemanId`,`auditTime`,`responsePolicemanId`,`responseTime`,`responseCompanyId`,`responseReason`,`responseResult`,`hitId`,`hitTime`) values (1,1,'2015-07-07 11:11:11',1,'2015-07-07 11:11:11',1,'我要出警','出警结束',10,NULL),(2,1,'2016-06-29 17:11:54',1,'2016-06-29 17:11:59',1,'事由1','结果1',100,NULL),(3,1,'2016-06-29 17:18:18',NULL,NULL,NULL,NULL,NULL,101,NULL),(4,1,'2016-06-29 17:37:31',NULL,NULL,NULL,NULL,NULL,102,NULL),(5,1,'2016-06-29 17:37:41',1,'2016-06-29 17:37:47',1,'事由1','结果1',103,NULL),(6,1,'2016-06-29 17:39:0',NULL,NULL,NULL,NULL,NULL,104,NULL),(7,2,'2016-06-29 17:39:15',1,'2016-06-29 17:39:21',1,'事由1','结果1',105,NULL),(8,1,'2016-06-29 17:44:27',1,'2016-06-29 17:44:48',1,'事由1','结果1',106,NULL),(9,2,'2016-06-29 17:46:27',NULL,NULL,NULL,NULL,NULL,107,NULL),(10,1,'2016-06-29 18:4:46',NULL,NULL,NULL,NULL,NULL,100,NULL),(11,1,'2016-06-30 9:7:38',1,'2016-06-30 09:07:46',1,'事由1','结果1',100,NULL),(12,1,'2016-06-30 11:48:30',NULL,NULL,NULL,NULL,NULL,100,NULL),(13,1,'2016-06-30 15:8:50',2,'2016-06-30 15:08:57',2,'事由1','结果1',100,NULL),(14,1,'2016-06-30 15:10:48',2,'2016-06-30 15:11:08',2,'事由1','结果1',100,NULL),(15,1,'2016-06-30 15:16:56',2,'2016-06-29 15:17:03',2,'事由1','结果1',1000,NULL),(16,1,'2016-06-30 15:38:57',2,'2016-06-30 15:39:02',2,'事由1','结果1',1001,NULL),(17,2,'2016-06-30 15:39:19',NULL,NULL,NULL,NULL,NULL,1002,NULL),(18,2,'2016-06-30 15:43:33',2,'2016-06-30 15:43:39',2,'事由1','结果1',1007,NULL),(19,1,'2016-06-30 17:5:19',2,'2016-06-30 17:05:28',2,'事由1','结果1',10010,NULL),(20,2,'2016-07-07 15:22:58',NULL,NULL,NULL,NULL,NULL,2361,NULL),(21,2,'2016-07-11 15:18:37',NULL,NULL,NULL,NULL,NULL,2442,NULL),(22,2,'2016-07-11 15:34:49',2,'2016-07-11 15:35:07',2,'事由1','结果1',2450,NULL),(23,2,'2016-07-12 10:28:7',NULL,NULL,NULL,NULL,NULL,2461,NULL),(24,1,'2016-07-12 10:28:44',2,'2016-07-12 10:29:09',2,'事由1','结果1',2462,NULL),(25,2,'2016-07-12 15:12:23',2,'2016-07-12 15:12:29',2,'事由1','结果1',2363,NULL),(26,2,'2016-07-12 15:12:46',NULL,NULL,NULL,NULL,NULL,2362,NULL),(27,2,'2016-07-12 15:13:11',NULL,NULL,NULL,NULL,NULL,2360,NULL),(28,2,'2016-07-12 15:13:44',NULL,NULL,NULL,NULL,NULL,2359,NULL),(29,2,'2016-07-12 15:18:0',NULL,NULL,NULL,NULL,NULL,2358,NULL),(30,5,'2016-07-20 15:32:10',2,'2016-07-20 15:32:17',2,'事由1','结果1',4646,NULL),(31,2,'2016-07-20 15:59:32',NULL,NULL,NULL,NULL,NULL,4657,NULL),(32,2,'2016-07-21 16:15:23',NULL,NULL,NULL,NULL,NULL,4724,NULL),(33,2,'2016-07-21 17:2:52',NULL,NULL,NULL,NULL,NULL,4863,1469091379),(34,2,'2016-07-22 14:57:49',NULL,NULL,NULL,NULL,NULL,5492,1469170233),(35,2,'2016-07-22 14:57:58',NULL,NULL,NULL,NULL,NULL,5491,1469169758),(36,2,'2016-07-22 14:58:8',NULL,NULL,NULL,NULL,NULL,5490,1469169758),(37,2,'2016-07-22 14:58:17',2,'2016-07-22 14:58:23',2,'事由1','结果1',5489,1469169757),(38,2,'2016-07-22 14:58:37',NULL,NULL,NULL,NULL,NULL,5488,1469169410),(39,2,'2016-07-22 16:45:53',2,'2016-07-22 16:46:13',2,'事由1','结果1',5500,1469177003),(40,2,'2016-07-22 17:32:28',NULL,NULL,NULL,NULL,NULL,5507,1469179900),(41,2,'2016-07-22 17:32:37',NULL,NULL,NULL,NULL,NULL,5506,1469179900),(42,2,'2016-07-22 17:32:47',NULL,NULL,NULL,NULL,NULL,5505,1469179892),(43,2,'2016-07-22 17:32:55',NULL,NULL,NULL,NULL,NULL,5504,1469179891),(44,2,'2016-07-22 17:33:3',NULL,NULL,NULL,NULL,NULL,5503,1469177877),(45,2,'2016-07-22 17:33:11',2,'2016-07-22 17:33:17',2,'事由1','结果1',5502,1469177034),(46,2,'2016-07-22 17:33:28',NULL,NULL,NULL,NULL,NULL,5501,1469177006),(47,2,'2016-07-25 9:24:52',NULL,NULL,NULL,NULL,NULL,3200,1468315769),(48,2,'2016-07-25 9:33:40',NULL,NULL,NULL,NULL,NULL,5509,1469407925),(49,2,'2016-07-25 9:33:54',2,'2016-07-25 09:34:02',2,'事由1','结果1',5508,1469407924),(50,2,'2016-07-25 10:21:55',NULL,NULL,NULL,NULL,NULL,5517,1469412081);

/*Table structure for table `face_policeman` */

DROP TABLE IF EXISTS `face_policeman`;

CREATE TABLE `face_policeman` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(20) DEFAULT NULL COMMENT '警员姓名',
  `companyId` int(11) DEFAULT NULL COMMENT '所属单位id',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `face_policeman` */

insert  into `face_policeman`(`id`,`name`,`companyId`) values (2,'测试民警2',2),(4,'测试民警4',4),(5,'测试民警5',6);

/*Table structure for table `face_user` */

DROP TABLE IF EXISTS `face_user`;

CREATE TABLE `face_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `permission` int(4) DEFAULT NULL COMMENT '权限（数字1-9）',
  `ofGroupId` int(11) DEFAULT NULL COMMENT '分组id',
  PRIMARY KEY (`id`,`username`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `face_user` */

insert  into `face_user`(`id`,`username`,`password`,`permission`,`ofGroupId`) values (1,'admin','21232F297A57A5A743894A0E4A801FC3',0,NULL),(5,'darunfa','E10ADC3949BA59ABBE56E057F20F883E',5,1),(3,'user2','7E58D63B60197CEB55A1C487989A3720',5,2),(4,'user3','92877AF70A45FD6A2ED7FE81E1236B78',5,4);

/*Table structure for table `face_user_group` */

DROP TABLE IF EXISTS `face_user_group`;

CREATE TABLE `face_user_group` (
  `groupId` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `groupName` varchar(50) NOT NULL COMMENT '分组名',
  `parentId` int(11) DEFAULT NULL COMMENT '父节点id',
  PRIMARY KEY (`groupId`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `face_user_group` */

insert  into `face_user_group`(`groupId`,`groupName`,`parentId`) values (1,'济南公安局',NULL),(2,'北京公安局1',NULL),(3,'南京公安局',NULL),(4,'测试分组',0),(5,'测试分组',0),(8,'测试公安局',0);

/*Table structure for table `face_user_login` */

DROP TABLE IF EXISTS `face_user_login`;

CREATE TABLE `face_user_login` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `loginTime` bigint(20) DEFAULT NULL COMMENT '用户登陆时间戳',
  `loginDay` bigint(20) DEFAULT NULL COMMENT '用户登陆某天时间戳',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=336 DEFAULT CHARSET=utf8;

/*Data for the table `face_user_login` */

insert  into `face_user_login`(`id`,`username`,`loginTime`,`loginDay`) values (312,'darunfa',1469087888,1469116800),(311,'admin',1469087762,1469116800),(310,'darunfa',1469087043,1469116800),(309,'admin',1469087019,1469116800),(308,'admin',1469086852,1469116800),(307,'darunfa',1469086828,1469116800),(306,'admin',1469085514,1469116800),(305,'darunfa',1469085507,1469116800),(304,'darunfa',1469082277,1469116800),(303,'darunfa',1469082741,1469116800),(302,'admin',1469082711,1469116800),(301,'admin',1469081261,1469116800),(300,'admin',1469081133,1469116800),(299,'admin',1469080985,1469116800),(298,'admin',1469081438,1469116800),(297,'admin',1469072204,1469116800),(296,'admin',1469068248,1469116800),(295,'admin',1469062873,1469116800),(294,'admin',1469062846,1469116800),(293,'admin',1469062709,1469116800),(292,'admin',1469006122,1469030400),(291,'admin',1469001863,1469030400),(290,'admin',1468999436,1469030400),(289,'admin',1468999301,1469030400),(288,'admin',1468999402,1469030400),(287,'admin',1468998907,1469030400),(286,'admin',1468998559,1469030400),(285,'admin',1468997765,1469030400),(284,'admin',1468981050,1469030400),(283,'admin',1468911266,1468944000),(282,'admin',1468910958,1468944000),(281,'admin',1468910836,1468944000),(280,'admin',1468897182,1468944000),(279,'admin',1468808338,1468857600),(278,'admin',1468807364,1468857600),(277,'admin',1468655875,1468684800),(276,'admin',1468572526,1468598400),(275,'admin',1468458551,1468512000),(274,'admin',1468381226,1468425600),(273,'admin',1468374887,1468425600),(272,'admin',1468304158,1468339200),(271,'admin',1468292374,1468339200),(270,'admin',1468290910,1468339200),(269,'admin',1468289688,1468339200),(268,'admin',1468285572,1468339200),(267,'admin',1468286196,1468339200),(266,'admin',1468284832,1468339200),(265,'admin',1468230905,1468252800),(264,'admin',1468230286,1468252800),(263,'jiamijia',1468229920,1468252800),(262,'admin',1468220839,1468252800),(261,'admin',1467945209,1467993600),(260,'test',1467945201,1467993600),(259,'admin',1467941545,1467993600),(258,'admin',1467940164,1467993600),(257,'admin',1467884771,1467907200),(256,'admin',1467019414,1467043200),(255,'admin',1467019247,1467043200),(254,'admin',1466997365,1467043200),(253,'test',1466997307,1467043200),(252,'test',1466997263,1467043200),(251,'admin',1466994970,1467043200),(250,'test',1466994961,1467043200),(249,'admin',1466731674,1466784000),(248,'admin',1466671610,1466697600),(247,'admin',1466652516,1466697600),(246,'test',1466652500,1466697600),(245,'test',1466652485,1466697600),(244,'admin',1466579700,1466611200),(243,'test',1466579683,1466611200),(242,'admin',1466580346,1466611200),(241,'admin',1466492209,1466524800),(240,'admin',1466492010,1466524800),(239,'admin',1466475081,1466524800),(238,'admin',1466474911,1466524800),(237,'admin',1466473019,1466524800),(236,'admin',1466408689,1466438400),(235,'admin',1466405496,1466438400),(234,'admin',1466405308,1466438400),(233,'admin',1466405266,1466438400),(232,'admin',1466405083,1466438400),(231,'admin',1466403653,1466438400),(230,'admin',1466403565,1466438400),(229,'admin',1466403066,1466438400),(228,'admin',1466402767,1466438400),(227,'admin',1466402602,1466438400),(226,'admin',1466401744,1466438400),(225,'admin',1466401737,1466438400),(224,'test',1466401711,1466438400),(223,'admin',1466401699,1466438400),(222,'admin',1466401605,1466463600),(221,'test',1466395289,1466438400),(220,'test',1466395238,1466438400),(219,'admin',1466395229,1466438400),(218,'admin',1466395204,1466438400),(217,'test',1466395131,1466438400),(216,'admin',1466395119,1466438400),(215,'admin',1466394763,1466438400),(214,'admin',1466394661,1466463600),(313,'admin',1469088838,1469116800),(314,'admin',1469409221,1469462400),(315,'admin',1469410097,1469462400),(316,'darunfa',1469410490,1469462400),(317,'admin',1469411715,1469462400),(318,'darunfa',1469411753,1469462400),(319,'admin',1469412542,1469462400),(320,'darunfa',1469412604,1469462400),(321,'admin',1469413129,1469462400),(322,'darunfa',1469413353,1469462400),(323,'admin',1469414851,1469462400),(324,'darunfa',1469415765,1469462400),(325,'admin',1469415937,1469462400),(326,'darunfa',1469416505,1469462400),(327,'admin',1469427947,1469462400),(328,'darunfa',1469430542,1469462400),(329,'darunfa',1469431944,1469462400),(330,'admin',1469432039,1469462400),(331,'admin',1469432813,1469462400),(332,'admin',1469433389,1469462400),(333,'admin',1469433543,1469462400),(334,'admin',1469434476,1469462400),(335,'admin',1469434667,1469462400);

/*Table structure for table `people_base` */

DROP TABLE IF EXISTS `people_base`;

CREATE TABLE `people_base` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `age` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `nativePlace` varchar(255) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `people_base` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
