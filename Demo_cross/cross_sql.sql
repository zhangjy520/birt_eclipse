/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.7.17 : Database - weichatarticle
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`weichatarticle` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `weichatarticle`;

/*Table structure for table `tb_account` */

DROP TABLE IF EXISTS `tb_account`;

CREATE TABLE `tb_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(500) DEFAULT NULL,
  `password` varchar(500) DEFAULT NULL,
  `updatTime` datetime DEFAULT NULL,
  `creatTime` datetime DEFAULT NULL,
  `type` int(5) DEFAULT NULL,
  `content` int(5) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `tb_account` */

insert  into `tb_account`(`id`,`username`,`password`,`updatTime`,`creatTime`,`type`,`content`) values (1,'张三','1','2018-08-21 09:04:46',NULL,1,1),(3,'李四','1',NULL,NULL,1,2),(4,'王五','1',NULL,NULL,2,3),(5,'赵六','1',NULL,NULL,2,3),(6,'张三','2',NULL,NULL,1,4),(7,'李四','2',NULL,NULL,3,5),(8,'王五','2',NULL,NULL,1,1),(9,'赵六','2',NULL,NULL,2,3),(10,'张三','3',NULL,NULL,3,4),(11,'李四','3',NULL,NULL,1,5),(12,'王五','3',NULL,NULL,1,6),(13,'赵六','3',NULL,NULL,1,2);

/*Table structure for table `tb_article` */

DROP TABLE IF EXISTS `tb_article`;

CREATE TABLE `tb_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `imgUrl` varchar(500) DEFAULT NULL,
  `title` varchar(512) DEFAULT NULL,
  `content` text,
  `toUrl` varchar(500) DEFAULT NULL,
  `weight` int(11) DEFAULT NULL,
  `isBanner` tinyint(1) DEFAULT NULL,
  `updatTime` datetime DEFAULT NULL,
  `creatTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `tb_article` */

insert  into `tb_article`(`id`,`pid`,`imgUrl`,`title`,`content`,`toUrl`,`weight`,`isBanner`,`updatTime`,`creatTime`) values (1,1,'https://gss0.baidu.com/-4o3dSag_xI4khGko9WTAnF6hhy/zhidao/pic/item/d52a2834349b033b5f1a8f2c1ece36d3d439bdb1.jpg','3','4','https://gss0.baidu.com/-4o3dSag_xI4khGko9WTAnF6hhy/zhidao/pic/item/d52a2834349b033b5f1a8f2c1ece36d3d439bdb1.jpg',6,1,NULL,NULL);

/*Table structure for table `tb_type` */

DROP TABLE IF EXISTS `tb_type`;

CREATE TABLE `tb_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(8) DEFAULT NULL,
  `weight` varchar(500) DEFAULT NULL,
  `updatTime` datetime DEFAULT NULL,
  `creatTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_type` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
