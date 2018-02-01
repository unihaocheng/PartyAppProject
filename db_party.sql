/*
SQLyog v10.2 
MySQL - 5.5.19 : Database - db_party
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_party` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `db_party`;

/*Table structure for table `db_com` */

DROP TABLE IF EXISTS `db_com`;

CREATE TABLE `db_com` (
  `id` int(10) DEFAULT NULL,
  `com` varchar(50) DEFAULT NULL,
  `user_id` int(10) DEFAULT NULL,
  `groupid` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `db_com` */

insert  into `db_com`(`id`,`com`,`user_id`,`groupid`) values (NULL,'嗨',3,4),(NULL,'嗨',3,4),(NULL,'嗨',3,4),(NULL,'嗨',3,4),(NULL,'嗨',3,4),(NULL,'嗨',3,4),(NULL,'大连',3,4),(NULL,'大连',3,4),(NULL,'大家好',3,4),(NULL,'聚会不错哦',3,5);

/*Table structure for table `db_group` */

DROP TABLE IF EXISTS `db_group`;

CREATE TABLE `db_group` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `introduct` varchar(500) DEFAULT NULL,
  `create_id` int(10) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `start_time` varchar(50) DEFAULT NULL,
  `end_time` varchar(50) DEFAULT NULL,
  `join_ids` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `db_group` */

insert  into `db_group`(`id`,`name`,`introduct`,`create_id`,`address`,`start_time`,`end_time`,`join_ids`) values (2,'数据库设计','实现数据库设计与开发。',1,NULL,NULL,NULL,NULL),(3,'数据结构','数据结构实现算法，教你分析问题，解决问题。',1,NULL,NULL,NULL,NULL),(4,'2017生日party','希望大家积极参与',3,NULL,NULL,NULL,NULL),(5,'生日会','大家聚聚',3,'一号楼602','01-16上午','01-17下午',NULL);

/*Table structure for table `db_message` */

DROP TABLE IF EXISTS `db_message`;

CREATE TABLE `db_message` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `userid` int(10) DEFAULT NULL,
  `groupid` int(10) DEFAULT NULL,
  `message` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `db_message` */

insert  into `db_message`(`id`,`userid`,`groupid`,`message`) values (2,1,2,'大家好'),(3,1,2,'有什么问题随时提出。'),(4,1,2,'数据库设计'),(5,2,2,'怎么更好的学习数据库？'),(6,2,3,'老师好，数据结构有什么用处'),(7,1,2,'多联系，多交流'),(8,2,2,'怎么使用mysql数据库？'),(9,3,4,'嗨'),(10,3,4,'大家好'),(11,3,5,'大家好'),(12,3,5,'欢迎来聚会哦');

/*Table structure for table `db_user` */

DROP TABLE IF EXISTS `db_user`;

CREATE TABLE `db_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `nickName` varchar(15) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `type` int(2) DEFAULT NULL,
  `pwd` varchar(18) DEFAULT NULL,
  `birtyday` varchar(50) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `db_user` */

insert  into `db_user`(`id`,`nickName`,`phone`,`type`,`pwd`,`birtyday`,`address`) values (1,'小鸡','18231851151',1,'123',NULL,NULL),(2,'小明','18231851152',0,'123',NULL,NULL),(3,'孔刘','18231851153',1,'123','6月12','中关村');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
