/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.7.17 : Database - mes-job
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`mes-job` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `mes-job`;

/*Table structure for table `job_check_item` */

DROP TABLE IF EXISTS `job_check_item`;

CREATE TABLE `job_check_item` (
  `id` char(32) NOT NULL,
  `check_kind` varchar(20) DEFAULT NULL,
  `check_item` varchar(200) NOT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `del_flag` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `job_check_item` */

/*Table structure for table `job_check_record` */

DROP TABLE IF EXISTS `job_check_record`;

CREATE TABLE `job_check_record` (
  `id` char(32) NOT NULL COMMENT '校检记录编号',
  `check_item` varchar(200) NOT NULL COMMENT '校检项',
  `job_check_item_id` char(32) DEFAULT NULL COMMENT '校检项id',
  `job_reward_amerce_info_id` char(32) DEFAULT NULL COMMENT '奖扣分信息id',
  `check_worker` varchar(32) NOT NULL COMMENT '校检工',
  `work_class` varchar(10) NOT NULL COMMENT '班次',
  `check_kind` varchar(20) NOT NULL COMMENT '校检分类',
  `workshop` varchar(50) NOT NULL COMMENT '车间',
  `machine_code` varchar(50) DEFAULT NULL COMMENT '机台号',
  `check_result` varchar(300) NOT NULL COMMENT '校检结果',
  `staff` varchar(32) NOT NULL COMMENT '责任人',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '修改人',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `del_flag` char(1) NOT NULL COMMENT '删除标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `job_check_record` */

/*Table structure for table `job_check_records` */

DROP TABLE IF EXISTS `job_check_records`;

CREATE TABLE `job_check_records` (
  `id` char(32) NOT NULL COMMENT '记录编号',
  `workshop` varchar(20) NOT NULL COMMENT '车间',
  `work_class` varchar(10) NOT NULL COMMENT '班次',
  `machine_code` varchar(50) DEFAULT NULL COMMENT '机台号',
  `production_order` varchar(50) DEFAULT NULL COMMENT '生产订单',
  `product` varchar(50) DEFAULT NULL COMMENT '产品',
  `shaft_code` varchar(50) DEFAULT NULL COMMENT '轴号',
  `check_kind` varchar(20) NOT NULL COMMENT '检查分类',
  `check_child_kind` varchar(20) DEFAULT NULL COMMENT '检查子类',
  `check_item` varchar(200) NOT NULL COMMENT '检查项编码',
  `check_item_name` varchar(2000) DEFAULT NULL COMMENT '检查项名称',
  `check_result` varchar(300) NOT NULL COMMENT '检查结果描述',
  `staff` varchar(32) DEFAULT NULL COMMENT '责任人',
  `handle_result` varchar(300) DEFAULT NULL COMMENT '处理结果',
  `checker` varchar(32) NOT NULL COMMENT '检查人',
  `check_time` datetime NOT NULL COMMENT '检查时间',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `create_by` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL COMMENT '状态',
  `update_date` datetime DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `del_flag` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `job_check_records` */

insert  into `job_check_records`(`id`,`workshop`,`work_class`,`machine_code`,`production_order`,`product`,`shaft_code`,`check_kind`,`check_child_kind`,`check_item`,`check_item_name`,`check_result`,`staff`,`handle_result`,`checker`,`check_time`,`status`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) values ('1c4706a1e31d4147afecb02a9d94e2ea','C12','W01','006','6','6','6','G01','G06','J0354','挡车工无人台位','7','E9988',NULL,'E9988','2018-04-13 19:14:11',1,NULL,'2018-04-13 18:48:58',NULL,'2018-04-13 19:14:11',NULL,'0'),('397f510619a4448e8a3dc6275b250fdf','C12','W01','001','001','001','001','G01','G05','J0025','不在岗','测试','E9966','222','E333','2018-04-13 16:57:40',2,NULL,'2018-02-08 16:25:41',NULL,'2018-04-13 16:57:40',NULL,'0'),('3ced370826174c40aa73ff7789384088','C12','W01','002','002','2','002','G01','G06','J0354','挡车工无人台位','333','E9966',NULL,'E9999','2018-04-13 16:02:27',1,NULL,'2018-04-10 22:18:55',NULL,'2018-04-13 16:02:27',NULL,'0'),('4e0027129e894d93a029ddde41ce717d','C12','W01','001','001','01','002','G01','G05','J0029','纱线通道有成块飞花','223','E333','222','E4199','2018-04-13 16:58:58',1,NULL,'2018-04-13 16:58:28',NULL,'2018-04-13 16:58:58',NULL,'0'),('5a0282d3582e479f99ef5442f56030c7','C11','W02','SB040102024075','134','B040102024075','','G02',NULL,'J0004',NULL,'11','E220',NULL,'E66','2018-01-20 10:19:11',1,NULL,'2018-01-20 10:19:11',NULL,'2018-01-20 10:19:11',NULL,'1'),('64cb30518d474e478e9c4f9146129bff','C12','W02','001','001','大提花','002','G02',NULL,'J0002','筒子成形不良','测试1','E220','22','E66','2018-04-13 16:15:50',1,NULL,'2018-02-05 14:59:56',NULL,'2018-04-13 16:15:50',NULL,'0'),('6b287fa7608e4371962f33234e898cdf','C12','W01','003','003','003','003','G01','G06','J0026','交班清洁不执行','003','E4199',NULL,'E9999','2018-04-13 16:04:02',1,NULL,'2018-04-13 15:59:32',NULL,'2018-04-13 16:04:02',NULL,'0'),('77a2001f0d27412085f789c497bca243','C12','W01','001','','','001','G01','G05','J0026','交班清洁不执行','test','E9988',NULL,'E9988','2018-04-13 16:03:08',1,NULL,'2018-04-10 21:20:28',NULL,'2018-04-13 16:03:08',NULL,'0'),('80df9e714f3c44548d14232ab148e86a','C12','W01','S201001602167',NULL,'T/C14*OE14','478','G03',NULL,'J0756','棉结杂质','左右轴棉球',NULL,NULL,'E484','2018-06-05 14:27:37',1,NULL,'2018-06-05 14:27:37',NULL,'2018-06-05 14:27:37',NULL,'0'),('8eee9189e0ce4377942a71173b23342a','C12','W01','002','002','002','002','G01','G05','J0043','因工艺需要返倒小筒子，数目不够或多于规定30个及以上','测试','E9988','121','E333','2018-04-13 17:19:03',2,NULL,'2018-02-08 16:26:27',NULL,'2018-04-13 17:19:03',NULL,'0'),('9849adb5924d4445b453fe675f70bf56','C12','W01','S003',NULL,'大提花','Z001','G03',NULL,'J0755',NULL,'测试',NULL,'测试','E66','2018-02-05 14:57:41',2,NULL,'2018-02-05 14:56:25',NULL,'2018-02-05 14:57:41',NULL,'1'),('9b347d2aa75542309c29a70e63a138cd','C12','W01','01','01','01','01','G01','G06','J0354',NULL,'ceshi','E9999',NULL,'E9988','2018-02-08 16:58:42',1,NULL,'2018-02-08 16:58:42',NULL,'2018-02-08 16:58:42',NULL,'1'),('a075fbd1139c4d01abe420cf8b95355c','C12','W02','001','001','001','001','现场管理检查','G05','J0024',NULL,'222','E333',NULL,'E9988','2018-02-08 16:18:35',1,NULL,'2018-02-08 16:18:35',NULL,'2018-02-08 16:18:35',NULL,'1'),('d5b6ba3d98bf4b08a29a4f5083810193','','W01','','','','','G02',NULL,'',NULL,'','E9988',NULL,'E9988','2018-02-27 21:47:25',1,NULL,'2018-02-27 21:47:25',NULL,'2018-02-27 21:47:25',NULL,'1'),('dd745895fdfc49e59ff3113008a8c467','C12','W01','002','002','002','002','G02',NULL,'J0002','筒子成形不良','test1','E9999','25','E9988','2018-04-13 17:19:32',1,NULL,'2018-04-13 16:06:19',NULL,'2018-04-13 17:19:32',NULL,'0');

/*Table structure for table `job_check_standard` */

DROP TABLE IF EXISTS `job_check_standard`;

CREATE TABLE `job_check_standard` (
  `id` char(32) NOT NULL COMMENT '检查项id',
  `check_kind` varchar(20) DEFAULT NULL COMMENT '检查分类',
  `check_child_kind` varchar(20) DEFAULT NULL COMMENT '检查子类',
  `process_name` varchar(50) NOT NULL COMMENT '工序',
  `check_item` varchar(200) NOT NULL COMMENT '检查项',
  `response_process` varchar(50) NOT NULL COMMENT '责任工序',
  `staff` varchar(32) NOT NULL COMMENT '责任人',
  `reward_amerce_standard` varchar(300) DEFAULT NULL COMMENT '扣分标准',
  `is_quality_accident` char(4) NOT NULL COMMENT '是否质量事故',
  `set_man` varchar(32) DEFAULT NULL COMMENT '设置人',
  `set_time` datetime DEFAULT NULL COMMENT '设置时间',
  `status` tinyint(4) DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `del_flag` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `job_check_standard` */

/*Table structure for table `job_craft_confirm_record` */

DROP TABLE IF EXISTS `job_craft_confirm_record`;

CREATE TABLE `job_craft_confirm_record` (
  `id` char(32) NOT NULL COMMENT '记录编号',
  `task_id` varchar(50) NOT NULL COMMENT '任务编号',
  `craft_kind` varchar(20) DEFAULT NULL COMMENT '工艺类型',
  `machine_code` varchar(50) NOT NULL COMMENT '机台号',
  `work_class` varchar(10) DEFAULT NULL COMMENT '班次',
  `craft_code` varchar(50) NOT NULL COMMENT '工艺卡编号',
  `check_result` varchar(200) NOT NULL COMMENT '核查结果',
  `checker` varchar(32) NOT NULL COMMENT '核查人',
  `status` tinyint(4) DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `del_flag` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `job_craft_confirm_record` */

insert  into `job_craft_confirm_record`(`id`,`task_id`,`craft_kind`,`machine_code`,`work_class`,`craft_code`,`check_result`,`checker`,`status`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) values ('d8d2562c094d4875aa4a00e672185ee1','01','整经','m001','甲班','工艺卡信息（erp）','通过','张三',1,NULL,'2018-06-07 14:14:35',NULL,'2018-06-07 14:14:35','','1'),('dd599f5e6e4e4406b4fea96d5f979851','03','整经','m003','甲班','工艺卡信息（erp）','通过','张三',1,NULL,'2018-06-07 14:14:39',NULL,'2018-06-07 14:14:39','','1'),('e1752722ce2b48bfacf3be057f43b146','02','整经','m002','甲班','工艺卡信息（erp）','通过','张三',1,NULL,'2018-06-07 14:14:38',NULL,'2018-06-07 14:14:38','','0');

/*Table structure for table `job_cut_shaft_record` */

DROP TABLE IF EXISTS `job_cut_shaft_record`;

CREATE TABLE `job_cut_shaft_record` (
  `id` char(32) NOT NULL COMMENT '记录编号',
  `order_id` varchar(50) NOT NULL COMMENT '生产订单',
  `product` varchar(50) DEFAULT NULL COMMENT '产品',
  `cut_shaft_kind` varchar(20) NOT NULL COMMENT '割轴类型',
  `workshop` varchar(50) DEFAULT NULL COMMENT '割轴车间',
  `workcenter` varchar(50) NOT NULL COMMENT '工作中心',
  `process` varchar(50) NOT NULL COMMENT '工序',
  `shaft_code` varchar(50) NOT NULL COMMENT '轴号',
  `machine_code` varchar(50) NOT NULL COMMENT '设备编号',
  `work_class` varchar(10) DEFAULT NULL COMMENT '班次',
  `work_group` varchar(50) DEFAULT NULL COMMENT '班组',
  `cutter` varchar(32) NOT NULL COMMENT '割轴人',
  `cut_shaft_length` decimal(20,2) NOT NULL COMMENT '割轴长度',
  `cut_shaft_detail` varchar(300) DEFAULT NULL COMMENT '割轴描述',
  `cut_shaft_cause` varchar(300) DEFAULT NULL COMMENT '割轴原因',
  `cut_time` datetime NOT NULL COMMENT '割轴时间',
  `status` tinyint(4) DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `del_flag` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `job_cut_shaft_record` */

/*Table structure for table `job_defect` */

DROP TABLE IF EXISTS `job_defect`;

CREATE TABLE `job_defect` (
  `id` char(32) NOT NULL,
  `record_id` char(32) DEFAULT NULL,
  `record_type` varchar(10) DEFAULT NULL,
  `shaft_code` varchar(50) DEFAULT NULL COMMENT '布轴号',
  `code` varchar(32) DEFAULT NULL COMMENT '疵点信息编号',
  `name` varchar(50) DEFAULT NULL COMMENT '疵点名称',
  `defect_type` varchar(1) DEFAULT NULL COMMENT '疵点类型（1普残，2死残）',
  `direction` varchar(1) DEFAULT NULL COMMENT '疵点方向（1经向，2纬向）',
  `yarn_resp_ratio` tinyint(3) DEFAULT NULL COMMENT '成纱车间责任比率',
  `sizing_resp_ratio` tinyint(3) DEFAULT NULL COMMENT '准备车间责任比率',
  `weaving_resp_ratio` tinyint(3) DEFAULT NULL COMMENT '织造车间责任比率',
  `grade` tinyint(2) DEFAULT NULL COMMENT '分数',
  `start_location` smallint(4) DEFAULT NULL COMMENT '疵点开始位置（匹）',
  `end_location` smallint(4) DEFAULT NULL COMMENT '疵点结束位置（匹）',
  `start_location_yard` decimal(10,4) DEFAULT NULL COMMENT '疵点开始位置（码）',
  `end_location_yard` decimal(10,4) DEFAULT NULL COMMENT '疵点结束位置（码）',
  `zonal_location` smallint(4) DEFAULT NULL COMMENT '纬向位置(cm）',
  `is_demotion` tinyint(1) DEFAULT NULL COMMENT '是否引起降等',
  `is_continuous` tinyint(1) DEFAULT NULL COMMENT '是否连残',
  `type` varchar(10) DEFAULT NULL COMMENT '检查类型',
  `remarks` varchar(255) DEFAULT NULL,
  `create_user` char(32) DEFAULT NULL,
  `create_user_name` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user` char(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_flag` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `job_defect` */

insert  into `job_defect`(`id`,`record_id`,`record_type`,`shaft_code`,`code`,`name`,`defect_type`,`direction`,`yarn_resp_ratio`,`sizing_resp_ratio`,`weaving_resp_ratio`,`grade`,`start_location`,`end_location`,`start_location_yard`,`end_location_yard`,`zonal_location`,`is_demotion`,`is_continuous`,`type`,`remarks`,`create_user`,`create_user_name`,`create_time`,`update_user`,`update_time`,`del_flag`) values ('0506b1544d9940a7b620171c72f0c2de','cad4f29a13604eb78ccc459e337c1054','inspect','B04201805240002	','CD18','长欠码','1','1',0,0,100,4,1,0,'5.0000',NULL,0,0,0,'1',NULL,'wangyanling','王彦玲','2018-06-05 13:56:37','wangyanling','2018-06-05 13:56:37','1'),('0779ccdc1220495eb49b9fae8d0c14e2','695d7c9eb1de4505b9143cd88f325bbd','inspect','	B04201805240001','CD23','断经','1','1',0,0,100,4,5,5,'30.0000','30.0000',66,1,0,'1',NULL,'wangyanling','王彦玲','2018-06-06 14:11:51','wangyanling','2018-06-06 14:12:01','1'),('0ae0309dc86e4afbb20e3472502679d3','e4b32ca0c561469caed73f85d93679d3','inspect','B04201805240002','CD06','条干','1','2',100,0,0,2,1,1,'100.0000','100.0000',0,0,0,'1',NULL,'admin','系统管理员','2018-08-24 16:01:05','admin','2018-08-24 16:01:05','0'),('0c5a0099261042bab131256eda41e6f5','3ccba69a1d8a4b10a27fb7e397cd4d40','inspect','B04201807270006','CD15','吊经','1','1',0,100,0,3,1,1,'20.0000','35.0000',0,0,1,'1',NULL,'admin','系统管理员','2018-08-09 21:55:23','admin','2018-08-09 21:55:41','0'),('1656e6e75cda4e02b7a6589ab155a248','3ccba69a1d8a4b10a27fb7e397cd4d40','inspect','B04201807270006','CD29','双纬','1','2',0,0,100,3,1,1,'20.0000','20.0000',0,0,0,'1',NULL,'admin','系统管理员','2018-08-09 21:55:02','admin','2018-08-09 21:55:02','0'),('1fca5cce73ec480687ba2bdf4c833845','14827ca8e9944d8c878b467708f87d4b','inspect','B04201807270006','CD18','长欠码','1','1',0,0,100,2,1,1,'50.0000','50.0000',0,0,0,'1',NULL,'zhaohuimin','赵慧敏','2018-08-09 19:58:58','zhaohuimin','2018-08-09 20:00:41','0'),('25fe4338045c410eb888d8d247d52b5c','e4b32ca0c561469caed73f85d93679d3','inspect','B04201805240002','CD07','错纬','1','2',100,0,0,2,1,1,'50.0000','50.0000',0,0,0,'1',NULL,'admin','系统管理员','2018-08-24 16:17:28','admin','2018-08-24 16:17:28','0'),('2ac29cf219ab46b78a6e2a41834dc313','e4b32ca0c561469caed73f85d93679d3','inspect','B04201805240002','CD06','条干','1','2',100,0,0,2,1,1,'50.0000','50.0000',0,0,0,'1',NULL,'admin','系统管理员','2018-08-24 16:02:29','admin','2018-08-24 16:02:29','0'),('35fee385f27f443ea494e6792883805e','6c0f2fd5bae84fde9d7b865e550ca1df','inspect','B04201805240001','CD16','松经','1','1',0,30,70,4,1,1,'261.0000','360.0000',0,0,1,'1',NULL,'wangyanling','王彦玲','2018-05-24 14:57:24','wangyanling','2018-05-24 14:57:47','0'),('41cd4ec0ce104b96b8758282415c7ae2','14827ca8e9944d8c878b467708f87d4b','inspect','B04201807270006','CD08','油纬','1','2',0,0,100,2,1,1,'50.0000','50.0000',0,0,0,'1',NULL,'zhaohuimin','赵慧敏','2018-08-09 19:59:30','zhaohuimin','2018-08-09 19:59:30','0'),('44f6567ea8554b579ceb7386a3b9dd68','e4b32ca0c561469caed73f85d93679d3','inspect','B04201805240002','CD15','吊经','1','1',0,100,0,2,1,1,'100.0000','100.0000',0,0,0,'1',NULL,'admin','系统管理员','2018-08-24 16:07:47','admin','2018-08-24 16:07:50','0'),('4d8507e724a143fa8ea104eace52affe','e4b32ca0c561469caed73f85d93679d3','inspect','B04201805240002','CD06','条干','1','2',100,0,0,2,1,1,'50.0000','50.0000',0,0,0,'1',NULL,'admin','系统管理员','2018-08-24 15:49:58','admin','2018-08-24 15:49:58','0'),('51033d1d0a984effa7c68122fdd00e30','6c0f2fd5bae84fde9d7b865e550ca1df','inspect','B04201805240001','CD15','吊经','1','1',0,100,0,2,1,0,'5.0000',NULL,120,0,0,'1',NULL,'huangxiaolei','黄晓磊','2018-06-05 09:16:59','huangxiaolei','2018-06-05 09:16:59','0'),('5123655e0b804561af0de82b4babdc8c','e4b32ca0c561469caed73f85d93679d3','inspect','B04201805240002','CD18','长欠码','1','1',0,100,0,3,1,1,'100.0000','100.0000',0,0,0,'1',NULL,'admin','系统管理员','2018-08-24 16:06:14','admin','2018-08-24 16:06:18','0'),('59af71cbfa4e4dc6ba4a9b5016798aaf','4768852f2e1a4ea394ca54b1f751a2b6','inspect','B04201805240002','CD05','油花','1','2',100,0,0,3,1,1,'5.0000','5.0000',0,0,0,'1',NULL,'wangaixia','王爱霞','2018-06-02 15:30:52','wangaixia','2018-06-02 15:30:52','0'),('628553341afc42b8863e9f7a348d9e62','6c0f2fd5bae84fde9d7b865e550ca1df','inspect','B04201805240001','CD15','吊经','1','1',0,100,0,3,1,1,'240.0000','260.0000',0,0,1,'1',NULL,'wangyanling','王彦玲','2018-05-24 14:55:37','wangyanling','2018-05-24 14:56:03','0'),('634be908a96c4feda2bbca3f7bbb89dc','4768852f2e1a4ea394ca54b1f751a2b6','inspect','B04201805240002','CD06','条干','1','2',100,0,0,2,1,1,'5.0000','5.0000',0,0,0,'1',NULL,'admin','系统管理员','2018-08-24 16:09:48','admin','2018-08-24 16:09:48','0'),('6a27be7d6fe248bab8a47f1f6f121275','695d7c9eb1de4505b9143cd88f325bbd','inspect','	B04201805240001','CD06','条干','1','2',100,0,0,4,5,5,'30.0000','30.0000',66,1,0,'1',NULL,'wangyanling','王彦玲','2018-06-06 14:13:15','wangyanling','2018-06-06 14:13:15','1'),('6f90d0b3bf5048399829b41bd15c5ec3','cad4f29a13604eb78ccc459e337c1054','inspect','B04201805240002	','CD18','长欠码','1','1',0,0,100,4,2,0,'4.0000',NULL,0,1,0,'1',NULL,'wangyanling','王彦玲','2018-06-05 13:57:04','wangyanling','2018-06-05 13:57:04','1'),('7f02c464a2aa46a1bcbf0a7bc25c5af9','731703ff46eb4c3a8fc96a186f1cca12','inspect','B04201807270006','CD06','条干','1','2',100,0,0,2,1,1,'50.0000','50.0000',0,0,0,'1',NULL,'admin','系统管理员','2018-08-24 15:46:55','admin','2018-08-24 15:46:55','0'),('8c15114db88d4e2bbe53e59c50d21ec4','c9e2f65d241849a29207e58d7cf830f2','inspect','B04201807270006','CD05','油花','1','2',100,0,0,2,1,1,'400.0000','400.0000',0,0,0,'1',NULL,'yujianhua','于建华','2018-08-06 20:30:49','yujianhua','2018-08-06 20:30:49','0'),('8c5b7ffa296647f3b0aa35c6b2383d9e','6c0f2fd5bae84fde9d7b865e550ca1df','inspect','B04201805240001','CD23','断经','1','1',0,0,100,4,1,1,'400.0000','405.0000',112,1,1,'1',NULL,'wangyanling','王彦玲','2018-05-24 14:59:49','wangyanling','2018-05-24 15:00:13','0'),('8dc73dbd6af0401fbea4b14fc0c67c92','e4b32ca0c561469caed73f85d93679d3','inspect','B04201805240002','CD23','断经','1','1',0,0,100,4,6,6,'230.0000','230.0000',0,1,0,'1',NULL,'wangaixia','王爱霞','2018-06-02 15:34:03','wangaixia','2018-06-02 15:34:25','0'),('9401453288114e36816d033990c86d46','e4b32ca0c561469caed73f85d93679d3','inspect','B04201805240002','CD17','筘路','2','1',0,70,30,4,1,6,'5.0000','230.0000',0,0,1,'1',NULL,'wangaixia','王爱霞','2018-06-02 15:33:07','wangaixia','2018-06-02 15:34:18','0'),('9779af783f3c4dfa998505b23a6d0703','6c0f2fd5bae84fde9d7b865e550ca1df','inspect','B04201805240001','CD05','油花','1','2',100,0,0,2,1,1,'5.0000','5.0000',0,0,0,'1',NULL,'wangyanling','王彦玲','2018-05-24 14:53:16','wangyanling','2018-05-24 14:53:16','0'),('9d61881b02fc4e0380b37c36f149ffd8','3ccba69a1d8a4b10a27fb7e397cd4d40','inspect','B04201807270006','CD03','经竹节','1','2',100,0,0,2,1,1,'5.0000','5.0000',0,0,0,'1',NULL,'admin','系统管理员','2018-08-09 21:54:31','admin','2018-08-09 21:54:31','0'),('a5b9860426ed48deb934446e48b77f4a','a434b274c7924162ab27f160b529c2e3','inspect','B04201805240002','CD06','条干','1','2',100,0,0,4,1,1,'5.0000','5.0000',0,1,0,'1',NULL,'wangaixia','王爱霞','2018-06-02 15:27:48','wangaixia','2018-06-02 15:27:48','1'),('a74c2d2d09534c9ab5cafbe1592e0a12','731703ff46eb4c3a8fc96a186f1cca12','inspect','B04201807270006','CD18','长欠码','1','1',0,0,100,2,1,1,'50.0000','50.0000',0,0,0,'1',NULL,'yujianhua','于建华','2018-08-06 20:29:17','yujianhua','2018-08-06 20:29:31','1'),('ac9560ac302f4706a5f997acdb5652d6','6c0f2fd5bae84fde9d7b865e550ca1df','inspect','B04201805240001','CD40','经纱起圈（纯棉）','1','1',0,0,100,3,1,1,'5.0000','5.0000',120,0,0,'1',NULL,'huangxiaolei','黄晓磊','2018-06-05 09:16:00','huangxiaolei','2018-06-05 09:16:48','0'),('bf45dbb5843e4aedb644025294837169','4768852f2e1a4ea394ca54b1f751a2b6','inspect','B04201805240002','CD15','吊经','1','1',0,100,0,2,1,1,'50.0000','50.0000',0,0,0,'1',NULL,'admin','系统管理员','2018-08-24 15:35:53','admin','2018-08-24 15:36:03','0'),('d22ed218a61e49e8b428189ec037eb68','6c0f2fd5bae84fde9d7b865e550ca1df','inspect','B04201805240001','CD06','条干','1','2',100,0,0,4,1,1,'50.0000','50.0000',0,0,0,'1',NULL,'wangyanling','王彦玲','2018-05-24 14:54:07','wangyanling','2018-05-24 14:54:07','0'),('d5d8cfcfaa474482a3424c20b4922c7e','9f5b6be5105f4b6a836aa7317801a68a','inspect','B04201807270003','CD10','油经','1','1',0,100,0,1,1,1,'50.0000','50.0000',0,0,0,'1',NULL,'admin','系统管理员','2018-08-24 16:08:55','admin','2018-08-24 16:08:57','0'),('d7cba4db3c224b92bc5f2b0b97b965f9','695d7c9eb1de4505b9143cd88f325bbd','inspect','	B04201805240001','CD15','吊经','1','1',0,100,0,4,1,0,'5.0000',NULL,66,1,0,'1',NULL,'wangyanling','王彦玲','2018-06-06 14:08:46','wangyanling','2018-06-06 14:08:46','1'),('db269a4fd892403abb9c5d7c0d82d85f','e4b32ca0c561469caed73f85d93679d3','inspect','B04201805240002','CD06','条干','1','2',100,0,0,2,1,1,'100.0000','100.0000',0,0,0,'1',NULL,'admin','系统管理员','2018-08-24 16:21:21','admin','2018-08-24 16:21:21','0');

/*Table structure for table `job_defect_copy1` */

DROP TABLE IF EXISTS `job_defect_copy1`;

CREATE TABLE `job_defect_copy1` (
  `id` char(32) NOT NULL,
  `inspect_record_id` char(32) DEFAULT NULL,
  `shaft_code` varchar(50) DEFAULT NULL COMMENT '布轴号',
  `code` varchar(32) DEFAULT NULL COMMENT '疵点信息编号',
  `name` varchar(50) DEFAULT NULL COMMENT '疵点名称',
  `defect_type` varchar(1) DEFAULT NULL COMMENT '疵点类型（1普残，2死残）',
  `direction` varchar(1) DEFAULT NULL COMMENT '疵点方向（1经向，2纬向）',
  `yard_resp_ratio` tinyint(3) DEFAULT NULL COMMENT '成纱车间责任比率',
  `sizing_resp_ratio` tinyint(3) DEFAULT NULL COMMENT '准备车间责任比率',
  `weaving_resp_ratio` tinyint(3) DEFAULT NULL COMMENT '织造车间责任比率',
  `grade` tinyint(2) DEFAULT NULL COMMENT '分数',
  `start_location` tinyint(4) DEFAULT NULL COMMENT '疵点开始位置（匹）',
  `end_location` tinyint(4) DEFAULT NULL COMMENT '疵点结束位置（匹）',
  `start_location_yard` decimal(10,4) DEFAULT NULL COMMENT '疵点开始位置（码）',
  `end_location_yard` decimal(10,4) DEFAULT NULL COMMENT '疵点结束位置（码）',
  `zonal_location` tinyint(4) DEFAULT NULL,
  `is_demotion` tinyint(1) DEFAULT NULL COMMENT '是否引起降等',
  `is_continuous` tinyint(1) DEFAULT NULL COMMENT '是否连残',
  `type` varchar(10) DEFAULT NULL COMMENT '检查类型',
  `remarks` varchar(255) DEFAULT NULL,
  `create_user` char(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user` char(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_flag` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `job_defect_copy1` */

/*Table structure for table `job_denting_record` */

DROP TABLE IF EXISTS `job_denting_record`;

CREATE TABLE `job_denting_record` (
  `id` char(32) NOT NULL,
  `task_id` varchar(50) DEFAULT NULL,
  `denting_task_id` varchar(50) DEFAULT NULL,
  `machine_code` varchar(50) NOT NULL,
  `frame_code` varchar(50) DEFAULT NULL,
  `work_class` varchar(10) NOT NULL,
  `worker` varchar(32) NOT NULL,
  `variety` varchar(50) NOT NULL,
  `reed_code` varchar(50) NOT NULL,
  `total_root_number` bigint(20) NOT NULL,
  `shaft_code` varchar(50) NOT NULL,
  `is_shaft_took` char(4) NOT NULL,
  `is_yarn_prepared` char(4) NOT NULL,
  `actual_root_number` bigint(20) NOT NULL,
  `is_doff_checked` char(4) NOT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `del_flag` char(1) DEFAULT NULL,
  `pros_cons_axis` varchar(4) DEFAULT NULL COMMENT '正反轴',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `job_denting_record` */

insert  into `job_denting_record`(`id`,`task_id`,`denting_task_id`,`machine_code`,`frame_code`,`work_class`,`worker`,`variety`,`reed_code`,`total_root_number`,`shaft_code`,`is_shaft_took`,`is_yarn_prepared`,`actual_root_number`,`is_doff_checked`,`status`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`,`pros_cons_axis`) values ('de7f1856c7aa4bec97e9bdcbbaddccbc','59_*_*-T-01',NULL,'S207005201001','','W01','E13','B020107322005','222',222,'222','是','否',222,'是',1,NULL,'2018-08-08 21:52:52',NULL,'2018-08-08 21:52:52','','0','正'),('ea9a79be93024304af27138ffbb1fc8d','30_*_*-T-01',NULL,'S207005202002','','W01','E2661','B020302030001','85.5',8368,'123','是','是',8368,'是',1,NULL,'2018-05-23 16:04:46',NULL,'2018-05-23 16:04:46','','0',NULL);

/*Table structure for table `job_down_check` */

DROP TABLE IF EXISTS `job_down_check`;

CREATE TABLE `job_down_check` (
  `id` char(32) NOT NULL,
  `shaft_code` varchar(50) DEFAULT NULL COMMENT '布轴编号',
  `material_name` varchar(50) DEFAULT NULL COMMENT '品种',
  `weaving_machine_code` varchar(50) DEFAULT NULL COMMENT '织机编号',
  `cloth_length` decimal(10,4) DEFAULT NULL COMMENT '布轴长度（码）',
  `cloth_length_pi` decimal(10,4) DEFAULT NULL,
  `total_grade` smallint(6) DEFAULT NULL COMMENT '共疵点分数',
  `first_class_length` decimal(10,4) DEFAULT NULL COMMENT '一等匹数',
  `second_class_length` decimal(10,4) DEFAULT NULL COMMENT '二等匹数',
  `status` tinyint(2) DEFAULT NULL COMMENT '状态（1已下机）',
  `remarks` varchar(255) DEFAULT NULL,
  `create_user` char(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user` char(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_flag` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `job_down_check` */

/*Table structure for table `job_down_check_record` */

DROP TABLE IF EXISTS `job_down_check_record`;

CREATE TABLE `job_down_check_record` (
  `id` char(32) NOT NULL,
  `machine_code` varchar(50) DEFAULT NULL COMMENT '验布机编号',
  `shaft_code` varchar(50) DEFAULT NULL COMMENT '布轴号',
  `material_code` varchar(32) DEFAULT NULL,
  `material_name` varchar(50) DEFAULT NULL COMMENT '品名',
  `weaving_machine_code` varchar(50) DEFAULT NULL COMMENT '织机编号',
  `weaving_worker` char(32) DEFAULT NULL,
  `weaving_worker_name` varchar(50) DEFAULT NULL,
  `weaving_office` varchar(50) DEFAULT NULL,
  `weaving_office_name` varchar(50) DEFAULT NULL,
  `create_user` char(32) DEFAULT NULL,
  `create_user_name` varchar(50) DEFAULT NULL,
  `check_office` varchar(50) DEFAULT NULL COMMENT '班',
  `check_office_name` varchar(50) DEFAULT NULL,
  `cloth_length` decimal(10,4) DEFAULT NULL COMMENT '验布长度',
  `cloth_length_pi` decimal(10,4) DEFAULT NULL,
  `total_grade` smallint(6) DEFAULT NULL,
  `first_class_length` decimal(10,4) DEFAULT NULL,
  `second_class_length` decimal(10,4) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `status` tinyint(2) DEFAULT NULL COMMENT '验布状态（0正在验，1已完成）',
  `quality_result` varchar(2) DEFAULT NULL COMMENT '质检结果（返工，降等，合格）',
  `remarks` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user` char(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_flag` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `job_down_check_record` */

/*Table structure for table `job_folding_record` */

DROP TABLE IF EXISTS `job_folding_record`;

CREATE TABLE `job_folding_record` (
  `id` char(32) NOT NULL,
  `segmentation_code` varchar(50) DEFAULT NULL,
  `machine_code` varchar(50) DEFAULT NULL,
  `folding_width` decimal(10,4) DEFAULT NULL,
  `speed` decimal(10,4) DEFAULT NULL,
  `quality_result` varchar(2) DEFAULT NULL COMMENT '质检结果',
  `remarks` varchar(255) DEFAULT NULL,
  `create_user` char(32) DEFAULT NULL,
  `worker_name` varchar(50) DEFAULT NULL,
  `worker_office` varchar(50) DEFAULT NULL,
  `worker_office_name` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user` char(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_flag` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `job_folding_record` */

insert  into `job_folding_record`(`id`,`segmentation_code`,`machine_code`,`folding_width`,`speed`,`quality_result`,`remarks`,`create_user`,`worker_name`,`worker_office`,`worker_office_name`,`create_time`,`update_user`,`update_time`,`del_flag`) values ('52d95eaecd2643029c9aedd112e6062a','B04201805240001-01','123','4.0000','70.0000',NULL,NULL,'wangyanling','王彦玲','C1401','整理车间运转甲班','2018-06-05 14:03:29','wangyanling','2018-06-05 14:04:49','0'),('ac459107216a41d8909a09f5354fa4ea','B04201805240001-02','234','6.0000','80.0000',NULL,NULL,'wangyanling','王彦玲','C1401','整理车间运转甲班','2018-06-05 16:48:45','wangyanling','2018-06-05 16:48:45','0');

/*Table structure for table `job_inspect_record` */

DROP TABLE IF EXISTS `job_inspect_record`;

CREATE TABLE `job_inspect_record` (
  `id` char(32) NOT NULL,
  `task_id` varchar(50) DEFAULT NULL COMMENT '验布任务id',
  `bill_no` varchar(50) DEFAULT NULL COMMENT '报工单号',
  `product_task_no` varchar(50) DEFAULT NULL COMMENT '生产任务书编号',
  `machine_code` varchar(50) DEFAULT NULL COMMENT '验布机编号',
  `work_center` varchar(10) DEFAULT NULL COMMENT '工作中心',
  `work_class` varchar(10) DEFAULT NULL COMMENT '班次',
  `inspector_left` char(32) DEFAULT NULL COMMENT '验布人（左）',
  `inspector_name_left` varchar(50) DEFAULT NULL,
  `inspector_center` char(32) DEFAULT NULL COMMENT '验布人（中）',
  `inspector_name_center` varchar(50) DEFAULT NULL,
  `inspector_right` char(32) DEFAULT NULL COMMENT '验布人（右）',
  `inspector_name_right` varchar(50) DEFAULT NULL,
  `shaft_code` varchar(50) DEFAULT NULL COMMENT '布轴号',
  `material_code` varchar(32) DEFAULT NULL,
  `material_name` varchar(50) DEFAULT NULL COMMENT '品名',
  `weaving_machine_code` varchar(50) DEFAULT NULL COMMENT '织机编号',
  `weaving_worker` char(32) DEFAULT NULL,
  `weaving_worker_name` varchar(50) DEFAULT NULL,
  `weaving_office` varchar(50) DEFAULT NULL,
  `weaving_office_name` varchar(50) DEFAULT NULL,
  `inspect_office` varchar(50) DEFAULT NULL COMMENT '班',
  `inspect_office_name` varchar(50) DEFAULT NULL,
  `cloth_length` decimal(10,4) DEFAULT NULL COMMENT '验布长度（码）',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `status` tinyint(2) DEFAULT NULL COMMENT '验布状态（0正在验，1已完成）',
  `is_meet_require` tinyint(1) DEFAULT NULL,
  `quality_result` varchar(2) DEFAULT NULL COMMENT '质检结果（返工，降等，合格）',
  `remarks` varchar(255) DEFAULT NULL,
  `create_user` char(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user` char(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_flag` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `job_inspect_record` */

insert  into `job_inspect_record`(`id`,`task_id`,`bill_no`,`product_task_no`,`machine_code`,`work_center`,`work_class`,`inspector_left`,`inspector_name_left`,`inspector_center`,`inspector_name_center`,`inspector_right`,`inspector_name_right`,`shaft_code`,`material_code`,`material_name`,`weaving_machine_code`,`weaving_worker`,`weaving_worker_name`,`weaving_office`,`weaving_office_name`,`inspect_office`,`inspect_office_name`,`cloth_length`,`start_time`,`end_time`,`status`,`is_meet_require`,`quality_result`,`remarks`,`create_user`,`create_time`,`update_user`,`update_time`,`del_flag`) values ('14827ca8e9944d8c878b467708f87d4b','8b7d927bf92b459eaddd76487a458d3a','C1058_1_1-01-01-01','201805-003','S209002400024','W14','W01','E884','仝亚丽','E220','汤艳萍','E69','刘朝阳','B04201807270006','C090600600130','坯布-LF60*LF60 173*130 106“小叶子   卷装','S201004209511','E1402','任俊千','C1302','二织运转乙班','C1405','整理车间成检乙班','100.0000','2018-08-09 19:55:32','2018-08-24 16:08:09',1,0,NULL,NULL,'zhaohuimin','2018-08-09 19:55:32','admin','2018-08-24 16:08:09','0'),('17116d70312e4ca0ba0f7d2671beae7d','836a4423170847d78370b359354db327','C1070_1_1-01-01-01','201807-001','S209002400024','W14','W01','E884','仝亚丽','E9999','薛建昌','E9988','张莉','B04201807270005','C073223220012','坯布-CVC32/2*32/2 100*53 63\"2/1碳黑1.06镶嵌CS18-14-1','S201004209511','E1402','任俊千','C1302','二织运转乙班','C1405','整理车间成检乙班','500.0000','2018-08-06 21:39:43','2018-08-06 21:39:52',1,0,NULL,NULL,'yujianhua','2018-08-06 21:39:43','yujianhua','2018-08-06 21:39:52','0'),('2e3d80a19b104d199411d417a313da0e','80ac1090ed8d4e059d71dccf560bbd74','C1070_1_1-30-01-01','201807-001','S209002600026','W14','W04','E220','汤艳萍','E9988','张莉','E9999','薛建昌','B04201807270003','C073223220012','坯布-CVC32/2*32/2 100*53 63\"2/1碳黑1.06镶嵌CS18-14-1','S201004109495','E1402','任俊千','C1302','二织运转乙班','C1404','整理车间常日班',NULL,'2018-08-20 21:57:47',NULL,0,0,NULL,NULL,'admin','2018-08-20 21:57:47','admin','2018-08-20 21:57:47','0'),('3a4d383985964bfb92e91fa8f495395c','8b7d927bf92b459eaddd76487a458d3a','C1058_1_1-01-01-01','201805-003','S209002400024','W14','W01','E884','仝亚丽','E884','仝亚丽','E884','仝亚丽','B04201807270006','C090600600130','坯布-LF60*LF60 173*130 106“小叶子   卷装','S201004209511','E1402','任俊千','C1302','二织运转乙班','C1405','整理车间成检乙班',NULL,'2018-08-09 17:03:27',NULL,0,0,NULL,NULL,'admin','2018-08-09 17:03:27','admin','2018-08-09 17:03:27','0'),('3ccba69a1d8a4b10a27fb7e397cd4d40','80ac1090ed8d4e059d71dccf560bbd74','C1070_1_1-30-01-01','201807-001','S209002600026','W14','W04','E220','汤艳萍','E220','汤艳萍','E220','汤艳萍','B04201807270006','C073223220012','坯布-CVC32/2*32/2 100*53 63\"2/1碳黑1.06镶嵌CS18-14-1','S201004209511','E1402','任俊千','C1302','二织运转乙班','C1404','整理车间常日班','35.0000','2018-08-09 21:53:42','2018-08-09 21:56:04',1,0,NULL,NULL,'admin','2018-08-09 21:53:42','admin','2018-08-09 21:56:04','0'),('3cdf72d4aa9b4b9db2a741dc9603d0c8','80ac1090ed8d4e059d71dccf560bbd74','C1070_1_1-30-01-01','201807-001','S209002600026','W14','W04','E220','汤艳萍','E9988','张莉','E9988','张莉','B04201807270003','C073223220012','坯布-CVC32/2*32/2 100*53 63\"2/1碳黑1.06镶嵌CS18-14-1','S201004109495','E1402','任俊千','C1302','二织运转乙班','C1404','整理车间常日班',NULL,'2018-08-24 09:15:22',NULL,0,0,NULL,NULL,'admin','2018-08-24 09:15:22','admin','2018-08-24 09:15:22','1'),('3f37a39ed50a44a2ae4796541b9ab0d1','80ac1090ed8d4e059d71dccf560bbd74','C1070_1_1-30-01-01','201807-001','S209002600026','W14','W04','E220','汤艳萍','E9999','薛建昌','E9988','张莉','B04201807270002','C073223220012','坯布-CVC32/2*32/2 100*53 63\"2/1碳黑1.06镶嵌CS18-14-1','S201004409543','E1402','任俊千','C1302','二织运转乙班','C1404','整理车间常日班',NULL,'2018-08-11 21:18:19',NULL,0,0,NULL,NULL,'admin','2018-08-11 21:18:19','admin','2018-08-11 21:18:19','0'),('40d81c20dc4d4b1bbdb0a79469fd01cd','80ac1090ed8d4e059d71dccf560bbd74','C1070_1_1-30-01-01','201807-001','S209002600026','W14','W04','E220','汤艳萍','E9988','张莉','E333','李双印','B04201807270002','C073223220012','坯布-CVC32/2*32/2 100*53 63\"2/1碳黑1.06镶嵌CS18-14-1','S201004409543','E1402','任俊千','C1302','二织运转乙班','C1404','整理车间常日班',NULL,'2018-08-11 21:55:11',NULL,0,0,NULL,NULL,'admin','2018-08-11 21:55:11','admin','2018-08-11 21:55:11','1'),('4768852f2e1a4ea394ca54b1f751a2b6','e8bb19a0693346b2bb98d4d080d6c477','C1055_1_1-24-01-01','201805-001','S209003100031','W14','W04','E882','王彦玲','E869','赵文英',NULL,'','B04201805240002','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','S201001509167','E785','范金恒','C1201','一织运转甲班','C1401','整理车间运转甲班','5.0000','2018-06-02 15:30:31','2018-08-24 16:09:49',1,0,NULL,NULL,'wangaixia','2018-06-02 15:30:31','admin','2018-08-24 16:09:49','0'),('528935bfb9af4424ad01564566e88f31','80ac1090ed8d4e059d71dccf560bbd74','C1070_1_1-30-01-01','201807-001','S209002600026','W14','W04','E220','汤艳萍','E220','汤艳萍','E220','汤艳萍','B04201807270004','C073223220012','坯布-CVC32/2*32/2 100*53 63\"2/1碳黑1.06镶嵌CS18-14-1','S201004109495','E1402','任俊千','C1302','二织运转乙班','C1404','整理车间常日班','30.0000','2018-08-09 22:49:18','2018-08-09 22:49:33',1,0,NULL,NULL,'zhaohuimin','2018-08-09 22:49:18','zhaohuimin','2018-08-09 22:49:33','0'),('528ba8855e864097af668195e41fef7c','80ac1090ed8d4e059d71dccf560bbd74','C1070_1_1-30-01-01','201807-001','S209002600026','W14','W04','E220','汤艳萍','E9988','张莉','E9966','梁著青','B04201807270002','C073223220012','坯布-CVC32/2*32/2 100*53 63\"2/1碳黑1.06镶嵌CS18-14-1','S201004409543','E1402','任俊千','C1302','二织运转乙班','C1404','整理车间常日班',NULL,'2018-08-11 21:18:47',NULL,0,0,NULL,NULL,'admin','2018-08-11 21:18:47','admin','2018-08-11 21:18:47','1'),('5e43050b4d4444ddab5a1a1c5e376ed3','80ac1090ed8d4e059d71dccf560bbd74','C1070_1_1-30-01-01','201807-001','S209002600026','W14','','E220','汤艳萍','E9988','张莉','E4199','赵瑞忠','B04201807270003','C073223220012','坯布-CVC32/2*32/2 100*53 63\"2/1碳黑1.06镶嵌CS18-14-1','S201004109495','E1402','任俊千','C1302','二织运转乙班','C1404','整理车间常日班',NULL,'2018-08-11 22:20:42',NULL,0,0,NULL,NULL,'admin','2018-08-11 22:20:42','admin','2018-08-11 22:20:42','1'),('608e85c4b903481e8a0f4bb53deb5814','80ac1090ed8d4e059d71dccf560bbd74','C1070_1_1-30-01-01','201807-001','S209002600026','W14','W04','E220','汤艳萍','E9988','张莉','E9988','张莉','B04201807270003','C073223220012','坯布-CVC32/2*32/2 100*53 63\"2/1碳黑1.06镶嵌CS18-14-1','S201004109495','E1402','任俊千','C1302','二织运转乙班','C1404','整理车间常日班',NULL,'2018-08-11 22:13:20',NULL,0,0,NULL,NULL,'admin','2018-08-11 22:13:20','admin','2018-08-11 22:13:20','1'),('633ec6b3cf7a40b8af901771e4243912','836a4423170847d78370b359354db327','C1070_1_1-01-01-01','201807-001','S209002400024','W14','','E884','仝亚丽','E884','仝亚丽','E884','仝亚丽','B04201807270006','C073223220012','坯布-CVC32/2*32/2 100*53 63\"2/1碳黑1.06镶嵌CS18-14-1','S201004209511','E1402','任俊千','C1302','二织运转乙班','C1405','整理车间成检乙班',NULL,'2018-08-09 17:05:33',NULL,0,0,NULL,NULL,'admin','2018-08-09 17:05:33','admin','2018-08-09 17:05:33','0'),('695d7c9eb1de4505b9143cd88f325bbd','bc6e5e226b1d409fabc12e707ca645f4','C1055_1_1-44-01-01','201805-001','','W14','W04','E6084','鲁书芳','E869','赵文英',NULL,'','	B04201805240001','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','S201001509167','E1354','李秋杰','C1203','一织运转丙班','C1401','整理车间运转甲班',NULL,'2018-06-06 14:08:19',NULL,0,0,NULL,NULL,'wangyanling','2018-06-06 14:08:19','wangyanling','2018-06-06 14:08:19','1'),('6c0f2fd5bae84fde9d7b865e550ca1df','34109878d14749cb961b369dc5bdb8c5','C1055_1_1-04-01-01','201805-001','S209003200032','W14','W04','E882','王彦玲','E6084','鲁书芳','E6084','鲁书芳','B04201805240001','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','S201001509167','E1354','李秋杰','C1203','一织运转丙班','C1401','整理车间运转甲班','500.0000','2018-05-24 14:51:49','2018-05-24 15:02:09',1,0,NULL,NULL,'wangyanling','2018-05-24 14:51:49','wangyanling','2018-05-24 15:02:09','0'),('6f5cc7d39ba146dbb5390a66bfe1689b','80ac1090ed8d4e059d71dccf560bbd74','C1070_1_1-30-01-01','201807-001','S209002600026','W14','W04','E220','汤艳萍','E9988','张莉','E9966','梁著青','B04201807270001','C073223220012','坯布-CVC32/2*32/2 100*53 63\"2/1碳黑1.06镶嵌CS18-14-1','S201004309527','E1402','任俊千','C1302','二织运转乙班','C1404','整理车间常日班',NULL,'2018-08-21 10:01:30',NULL,0,0,NULL,NULL,'admin','2018-08-21 10:01:30','admin','2018-08-21 10:01:30','0'),('731703ff46eb4c3a8fc96a186f1cca12','836a4423170847d78370b359354db327','C1070_1_1-01-01-01','201807-001','S209002400024','W14','W01','E884','仝亚丽','E9988','张莉','E9988','张莉','B04201807270006','C073223220012','坯布-CVC32/2*32/2 100*53 63\"2/1碳黑1.06镶嵌CS18-14-1','S201004209511','E1402','任俊千','C1302','二织运转乙班','C1405','整理车间成检乙班','50.0000','2018-08-06 20:28:45','2018-08-24 15:47:00',1,0,NULL,NULL,'yujianhua','2018-08-06 20:28:45','admin','2018-08-24 15:47:00','0'),('8444b6bc9fd24f598c206dc435fdab4d','836a4423170847d78370b359354db327','C1070_1_1-01-01-01','201807-001','S209002400024','W14','W01','E884','仝亚丽','E9988','张莉','E9966','梁著青','B04201807270004','C073223220012','坯布-CVC32/2*32/2 100*53 63\"2/1碳黑1.06镶嵌CS18-14-1','S201004109495','E1402','任俊千','C1302','二织运转乙班','C1405','整理车间成检乙班','200.0000','2018-08-07 15:18:11','2018-08-07 15:18:22',1,0,NULL,NULL,'admin','2018-08-07 15:18:11','admin','2018-08-07 15:18:22','0'),('9cda1e5287f945928a30a83d6966aaa7','80ac1090ed8d4e059d71dccf560bbd74','C1070_1_1-30-01-01','201807-001','S209002600026','W14','W04','E220','汤艳萍','E9988','张莉','E4199','赵瑞忠','B04201807270003','C073223220012','坯布-CVC32/2*32/2 100*53 63\"2/1碳黑1.06镶嵌CS18-14-1','S201004109495','E1402','任俊千','C1302','二织运转乙班','C1404','整理车间常日班',NULL,'2018-08-23 08:52:46',NULL,0,0,NULL,NULL,'yujianhua','2018-08-23 08:52:46','yujianhua','2018-08-23 08:52:46','0'),('9f5b6be5105f4b6a836aa7317801a68a','80ac1090ed8d4e059d71dccf560bbd74','C1070_1_1-30-01-01','201807-001','S209002600026','W14','W04','E220','汤艳萍','E9988','张莉','E9988','张莉','B04201807270003','C073223220012','坯布-CVC32/2*32/2 100*53 63\"2/1碳黑1.06镶嵌CS18-14-1','S201004109495','E1402','任俊千','C1302','二织运转乙班','C1404','整理车间常日班','50.0000','2018-08-24 16:08:47','2018-08-24 16:08:57',1,0,NULL,NULL,'admin','2018-08-24 16:08:47','admin','2018-08-24 16:08:57','0'),('a434b274c7924162ab27f160b529c2e3','e8bb19a0693346b2bb98d4d080d6c477','C1055_1_1-24-01-01','201805-001','S209003100031','W14','W04','E882','王彦玲','E869','赵文英',NULL,'','B04201805240002','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','S201001509167','E785','范金恒','C1201','一织运转甲班','C1401','整理车间运转甲班',NULL,'2018-06-02 15:24:36',NULL,0,0,NULL,NULL,'wangaixia','2018-06-02 15:24:36','wangaixia','2018-06-02 15:24:36','1'),('a853a5a046b64bf58272671b765a8090','80ac1090ed8d4e059d71dccf560bbd74','C1070_1_1-30-01-01','201807-001','S209002600026','W14','W04','E220','汤艳萍','E9988','张莉','E4199','赵瑞忠','B04201807270003','C073223220012','坯布-CVC32/2*32/2 100*53 63\"2/1碳黑1.06镶嵌CS18-14-1','S201004109495','E1402','任俊千','C1302','二织运转乙班','C1404','整理车间常日班',NULL,'2018-08-11 22:28:27',NULL,0,0,NULL,NULL,'admin','2018-08-11 22:28:27','admin','2018-08-11 22:28:27','1'),('c5f7e48e46344374b1467a96efbc985a','fc94d2d7a97d4ddb9fa9b3cbfc040a91','C1055_1_1-59-01-01','201805-001','S209003200032','W14','W04','E1936','蔡静',NULL,'','E2778','杨立霞','B04201805230005','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','S201001509167','E1354','李秋杰','C1203','一织运转丙班','C1401','整理车间运转甲班',NULL,'2018-06-07 15:21:07',NULL,0,0,NULL,NULL,'zhaohuimin','2018-06-07 15:21:07','zhaohuimin','2018-06-07 15:21:07','1'),('c9e2f65d241849a29207e58d7cf830f2','836a4423170847d78370b359354db327','C1070_1_1-01-01-01','201807-001','S209002400024','W14','W01','E884','仝亚丽','E9988','张莉','E1','崔秀艳','B04201807270006','C073223220012','坯布-CVC32/2*32/2 100*53 63\"2/1碳黑1.06镶嵌CS18-14-1','S201004209511','E1402','任俊千','C1302','二织运转乙班','C1405','整理车间成检乙班','5.0000','2018-08-06 20:30:31','2018-08-24 15:23:43',1,0,NULL,NULL,'yujianhua','2018-08-06 20:30:31','admin','2018-08-24 15:23:43','0'),('cad4f29a13604eb78ccc459e337c1054','e8bb19a0693346b2bb98d4d080d6c477','C1055_1_1-24-01-01','201805-001','S209003100031','W14','','E882','王彦玲','E869','赵文英','E4199','赵瑞忠','B04201805240002	','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','S201001509167','E785','范金恒','C1201','一织运转甲班','C1401','整理车间运转甲班',NULL,'2018-06-05 13:56:08',NULL,0,0,NULL,NULL,'wangyanling','2018-06-05 13:56:08','wangyanling','2018-06-05 13:56:08','1'),('de3c13a95b88426cbb1da03b33406f40','80ac1090ed8d4e059d71dccf560bbd74','C1070_1_1-30-01-01','201807-001','S209002600026','','W04','E220','汤艳萍','E9988','张莉','E4199','赵瑞忠','B04201807260005','C073223220012','坯布-CVC32/2*32/2 100*53 63\"2/1碳黑1.06镶嵌CS18-14-1','S201004309527','E778','赵书岭','C1301','二织运转甲班','C1404','整理车间常日班',NULL,'2018-08-12 16:18:59',NULL,0,0,NULL,NULL,'admin','2018-08-12 16:18:59','admin','2018-08-12 16:18:59','1'),('e4b32ca0c561469caed73f85d93679d3','e8bb19a0693346b2bb98d4d080d6c477','C1055_1_1-24-01-01','201805-001','S209003100031','W14','W04','E882','王彦玲','E869','赵文英',NULL,'','B04201805240002','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','S201001509167','E785','范金恒','C1201','一织运转甲班','C1401','整理车间运转甲班','100.0000','2018-06-02 15:32:23','2018-08-24 16:21:22',1,0,NULL,NULL,'wangaixia','2018-06-02 15:32:23','admin','2018-08-24 16:21:22','0'),('e575799b8b354702a1e61dc52b60fd6f','80ac1090ed8d4e059d71dccf560bbd74','C1070_1_1-30-01-01','201807-001','S209002600026','W14','','E220','汤艳萍','E9988','张莉','E9966','梁著青','B04201807270003','C073223220012','坯布-CVC32/2*32/2 100*53 63\"2/1碳黑1.06镶嵌CS18-14-1','S201004109495','E1402','任俊千','C1302','二织运转乙班','C1404','整理车间常日班',NULL,'2018-08-11 21:20:14',NULL,0,0,NULL,NULL,'admin','2018-08-11 21:20:14','admin','2018-08-11 21:20:14','1'),('eef4bdb3acc84f59882dec4a5cb0eb35','80ac1090ed8d4e059d71dccf560bbd74','C1070_1_1-30-01-01','201807-001','S209002600026','W14','W04','E220','汤艳萍','E9988','张莉','E9988','张莉','B04201807270002','C073223220012','坯布-CVC32/2*32/2 100*53 63\"2/1碳黑1.06镶嵌CS18-14-1','S201004409543','E1402','任俊千','C1302','二织运转乙班','C1404','整理车间常日班',NULL,'2018-08-11 20:33:26',NULL,0,0,NULL,NULL,'admin','2018-08-11 20:33:26','admin','2018-08-11 20:33:26','0'),('f1ae265596894244adc2227a58da1fdf','836a4423170847d78370b359354db327','C1070_1_1-01-01-01','201807-001','S209002400024','W14','W01','E884','仝亚丽','E9988','张莉','E9966','梁著青','B04201807270003','C073223220012','坯布-CVC32/2*32/2 100*53 63\"2/1碳黑1.06镶嵌CS18-14-1','S201004109495','E1402','任俊千','C1302','二织运转乙班','C1405','整理车间成检乙班','300.0000','2018-08-07 16:21:21','2018-08-07 16:21:29',1,0,NULL,NULL,'admin','2018-08-07 16:21:21','admin','2018-08-07 16:21:29','0');

/*Table structure for table `job_mending_record` */

DROP TABLE IF EXISTS `job_mending_record`;

CREATE TABLE `job_mending_record` (
  `id` char(32) NOT NULL,
  `segmentation_code` varchar(50) DEFAULT NULL,
  `operation_type` varchar(10) DEFAULT NULL,
  `worker_type` varchar(50) DEFAULT NULL,
  `worker` varchar(50) DEFAULT NULL,
  `worker_name` varchar(50) DEFAULT NULL,
  `quality_result` varchar(2) DEFAULT NULL COMMENT '质检结果',
  `remarks` varchar(255) DEFAULT NULL,
  `create_user` char(32) DEFAULT NULL,
  `create_user_name` varchar(50) DEFAULT NULL,
  `office` varchar(50) DEFAULT NULL,
  `office_name` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user` char(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_flag` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `job_mending_record` */

insert  into `job_mending_record`(`id`,`segmentation_code`,`operation_type`,`worker_type`,`worker`,`worker_name`,`quality_result`,`remarks`,`create_user`,`create_user_name`,`office`,`office_name`,`create_time`,`update_user`,`update_time`,`del_flag`) values ('5fef4b71d04e4f68b587a85501aaa7a8','B04201805240001-01','2','2','null','刘外',NULL,NULL,'huangxiaolei','黄晓磊','C1104','准备常日班','2018-06-05 10:26:43','huangxiaolei','2018-06-05 10:26:43','0'),('973fb1ddcbe34b6fb70dd02562cbe05b','B04201805240001-01','1','2','null','刘外',NULL,NULL,'huangxiaolei','黄晓磊','C1104','准备常日班','2018-06-05 10:26:43','huangxiaolei','2018-06-05 10:26:43','1');

/*Table structure for table `job_mixing_record` */

DROP TABLE IF EXISTS `job_mixing_record`;

CREATE TABLE `job_mixing_record` (
  `id` char(32) NOT NULL,
  `task_id` varchar(50) DEFAULT NULL,
  `mixing_worker` varchar(32) NOT NULL,
  `work_class` varchar(10) NOT NULL,
  `operation_card_code` varchar(50) NOT NULL,
  `batch_no` varchar(50) NOT NULL COMMENT '浆液批次号',
  `number` decimal(20,2) NOT NULL,
  `unit` varchar(10) DEFAULT NULL,
  `mixing_barrel` varchar(50) DEFAULT NULL,
  `check_result` varchar(100) DEFAULT NULL COMMENT '浆液检查结果',
  `status` tinyint(4) DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `del_flag` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `job_mixing_record` */

/*Table structure for table `job_pick_confirm_record` */

DROP TABLE IF EXISTS `job_pick_confirm_record`;

CREATE TABLE `job_pick_confirm_record` (
  `id` char(32) NOT NULL COMMENT '记录编号',
  `task_id` varchar(50) DEFAULT NULL COMMENT '任务编号',
  `picker` varchar(32) NOT NULL COMMENT '领料人',
  `shipper` varchar(32) DEFAULT NULL COMMENT '发料人',
  `work_class` varchar(10) DEFAULT NULL COMMENT '班次',
  `workshop` varchar(50) DEFAULT NULL COMMENT '车间',
  `materiel_code` varchar(50) NOT NULL COMMENT '物料编码',
  `materiel_model` varchar(100) DEFAULT NULL COMMENT '型号',
  `material_specification` varchar(100) DEFAULT NULL COMMENT '规格',
  `materiel_name` varchar(100) DEFAULT NULL COMMENT '物料名称',
  `batch_code` varchar(50) DEFAULT NULL COMMENT '批次号',
  `plan_num` decimal(20,2) DEFAULT NULL COMMENT '计划数量',
  `act_num` decimal(20,2) NOT NULL COMMENT '实际数量',
  `unit` varchar(10) NOT NULL COMMENT '单位',
  `status` tinyint(4) NOT NULL COMMENT '状态',
  `create_by` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `del_flag` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `job_pick_confirm_record` */

insert  into `job_pick_confirm_record`(`id`,`task_id`,`picker`,`shipper`,`work_class`,`workshop`,`materiel_code`,`materiel_model`,`material_specification`,`materiel_name`,`batch_code`,`plan_num`,`act_num`,`unit`,`status`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) values ('035bd3b7d09f40338c15eebb7b562d07',NULL,'admin_emp',NULL,NULL,'C12','B020302030001',NULL,NULL,'浆轴-T/JC（30+30）&times;20   132&times;62   63&rdquo; 双经布（双幅）','-','120.36','120.36','匹',1,NULL,'2018-06-06 09:00:10',NULL,'2018-06-06 09:00:10',NULL,'0'),('0fde08cbd5d2449b8e7b847f46c2dcd4',NULL,'admin_emp',NULL,NULL,'C12','R010305020101',NULL,NULL,'65/35T/JC20S','-','13.26','13.26','公斤',1,NULL,'2018-06-06 09:00:40',NULL,'2018-06-06 09:00:40',NULL,'0'),('1fca03b51e2745aa9b829a50eb9314ae',NULL,'admin_emp',NULL,NULL,'C12','B020302030001',NULL,NULL,'浆轴-T/JC（30+30）&times;20   132&times;62   63&rdquo; 双经布（双幅）','-','120.36','888.00','匹',1,NULL,'2018-08-06 13:54:20',NULL,'2018-08-06 13:54:20',NULL,'0'),('2e926526fa524c4598aa3e8668de8d00',NULL,'E1529',NULL,NULL,'C11','R010305030101',NULL,NULL,'65/35T/JC30S','-','34.20','2333.00','公斤',1,NULL,'2018-05-23 09:34:35',NULL,'2018-05-23 09:34:35',NULL,'0'),('2ec0707040804e76a0597d6b093f775b',NULL,'E1952',NULL,NULL,'C12','R010501060101',NULL,NULL,'LFTE60S','-','0.56','500.00','公斤',1,NULL,'2018-06-14 13:51:18',NULL,'2018-06-14 13:51:18',NULL,'0'),('4ff518432ba44fcea8fc15c2a793c136',NULL,'admin_emp',NULL,NULL,'C11','R010305030101',NULL,NULL,'65/35T/JC30S','-','3833.82','222.00','公斤',1,NULL,'2018-08-06 13:52:51',NULL,'2018-08-06 13:52:51',NULL,'0'),('807c0e5dc6fb4a07ac69e23b1cba2f6a',NULL,'E661',NULL,NULL,'C11','R010501060101',NULL,NULL,'LFTE60S','-','810.35','1900.00','公斤',1,NULL,'2018-05-30 09:35:59',NULL,'2018-05-30 09:35:59',NULL,'0'),('831f9a2ddc794680bad2074de6f2835d',NULL,'admin_emp',NULL,NULL,'C12','B020509525001',NULL,NULL,'浆轴-LF60*LF60 173*130 106&quot;小叶子','-','4.72','3.00','米',1,NULL,'2018-08-02 22:10:15',NULL,'2018-08-02 22:10:15',NULL,'0'),('98a5354525d84fb199f8a3809ee08d94',NULL,'E13',NULL,NULL,'C11','R010501060101',NULL,NULL,'LFTE60S','-','827.45','5470.80','公斤',1,NULL,'2018-06-04 14:24:11',NULL,'2018-06-04 14:24:11',NULL,'0'),('b083285896c34c99af23bdfb9ee183a1',NULL,'admin_emp',NULL,NULL,'C11','R010305030101',NULL,NULL,'65/35T/JC30S','-','3833.82','344.00','公斤',1,NULL,'2018-08-06 13:53:37',NULL,'2018-08-06 13:53:37',NULL,'0'),('f838e62c8c4642e093174e3d900412ae',NULL,'E1354',NULL,NULL,'C12','R010305020101',NULL,NULL,'65/35T/JC20S','-','1.56','450.00','公斤',1,NULL,'2018-05-23 15:19:40',NULL,'2018-05-23 15:19:40',NULL,'0');

/*Table structure for table `job_pick_record` */

DROP TABLE IF EXISTS `job_pick_record`;

CREATE TABLE `job_pick_record` (
  `id` char(32) NOT NULL,
  `task_id` varchar(50) DEFAULT NULL,
  `department` varchar(50) NOT NULL,
  `helper` varchar(32) NOT NULL,
  `work_class` varchar(10) NOT NULL,
  `variety` varchar(50) NOT NULL,
  `batch_code` varchar(50) DEFAULT NULL,
  `shoe` varchar(50) DEFAULT NULL,
  `number` decimal(20,2) NOT NULL,
  `unit` varchar(10) NOT NULL,
  `weight` varchar(20) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `del_flag` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `job_pick_record` */

/*Table structure for table `job_prepare_workshop_template` */

DROP TABLE IF EXISTS `job_prepare_workshop_template`;

CREATE TABLE `job_prepare_workshop_template` (
  `id` char(32) NOT NULL COMMENT '模板编号',
  `version_code` varchar(50) DEFAULT NULL COMMENT '版本号',
  `pick_template_code` varchar(50) NOT NULL COMMENT '领料模板编号',
  `up_shaft_template_code` varchar(50) NOT NULL COMMENT '上轴模板编号',
  `up_yarn_template_code` varchar(50) NOT NULL COMMENT '上筒模板编号',
  `warping_template_code` varchar(50) NOT NULL COMMENT '整经模板编号',
  `spool_template_code` varchar(50) NOT NULL COMMENT '络筒模板编号',
  `mixing_template_code` varchar(50) NOT NULL COMMENT '调浆模板编号',
  `slashing_template_code` varchar(50) NOT NULL COMMENT '浆纱模板编号',
  `denting_template_code` varchar(50) NOT NULL COMMENT '穿筘模板编号',
  `status` tinyint(4) NOT NULL COMMENT '发布状态',
  `create_by` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `del_flag` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `job_prepare_workshop_template` */

/*Table structure for table `job_process` */

DROP TABLE IF EXISTS `job_process`;

CREATE TABLE `job_process` (
  `id` char(32) NOT NULL COMMENT '工序id',
  `process_code` varchar(50) NOT NULL COMMENT '工序编码',
  `process_name` varchar(50) NOT NULL COMMENT '工序名称',
  `parent_process` varchar(50) DEFAULT NULL COMMENT '上级工序',
  `workshop` varchar(50) NOT NULL COMMENT '车间',
  `is_submit` char(4) NOT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `del_flag` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工序表';

/*Data for the table `job_process` */

insert  into `job_process`(`id`,`process_code`,`process_name`,`parent_process`,`workshop`,`is_submit`,`status`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) values ('JP01','JP01','上筒','10','准备车间','0',1,NULL,'2017-10-25 20:52:33',NULL,'2017-10-25 20:52:33',NULL,'0'),('JP02','JP02','整经上轴','10','准备车间','0',1,NULL,'2017-10-25 20:52:33',NULL,'2017-10-25 20:52:33',NULL,'0'),('JP03','JP03','整经','10','准备车间','1',1,NULL,'2017-10-25 20:52:33',NULL,'2017-10-25 20:52:33',NULL,'0'),('JP04','JP04','络筒','20','准备车间','0',1,NULL,'2017-10-25 20:52:34',NULL,'2017-10-25 20:52:34',NULL,'0'),('JP05','JP05','调浆','30','准备车间','0',1,NULL,'2017-10-25 20:52:34',NULL,'2017-10-25 20:52:34',NULL,'0'),('JP06','JP06','浆纱上轴','40','准备车间','0',1,NULL,'2017-10-25 20:52:34',NULL,'2017-10-25 20:52:34',NULL,'0'),('JP07','JP07','浆纱','40','准备车间','1',1,NULL,'2017-10-25 20:52:34',NULL,'2017-10-25 20:52:34',NULL,'0'),('JP08','JP08','穿筘上轴','50','准备车间','0',1,NULL,'2017-10-25 20:52:34',NULL,'2017-10-25 20:52:34',NULL,'0'),('JP09','JP09','穿筘','50','准备车间','1',1,NULL,'2017-10-25 20:52:34',NULL,'2017-10-25 20:52:34',NULL,'0'),('JP10','JP10','结经上轴','60','织布车间','0',1,NULL,'2017-10-25 20:52:34',NULL,'2017-10-25 20:52:34',NULL,'0'),('JP11','JP11','结经','60','织布车间','1',1,NULL,'2017-10-25 20:52:34',NULL,'2017-10-25 20:52:34',NULL,'0'),('JP12','JP12','上纬纱','70','织布车间','0',1,NULL,'2017-10-25 20:52:34',NULL,'2017-10-25 20:52:34',NULL,'0'),('JP13','JP13','织布','70','织布车间','1',1,NULL,'2017-10-25 20:52:34',NULL,'2017-10-25 20:52:34',NULL,'0'),('JP14','JP14','验布','13','整理车间','1',1,NULL,'2017-10-25 20:52:34',NULL,'2017-10-25 20:52:34',NULL,'0'),('JP15','JP15','分等分段','13','整理车间','1',1,NULL,'2017-10-25 20:52:34',NULL,'2017-10-25 20:52:34',NULL,'0'),('JP16','JP16','拼件','13','整理车间','1',1,NULL,'2017-10-25 20:52:34',NULL,'2017-10-25 20:52:34',NULL,'0'),('JP17','JP17','卷布','16','整理车间','1',1,NULL,'2017-10-25 20:52:34',NULL,'2017-10-25 20:52:34',NULL,'0'),('JP18','JP18','打包','16','整理车间','1',1,NULL,'2017-10-25 20:52:34',NULL,'2017-10-25 20:52:34',NULL,'0'),('JP19','JP19','上穿轴','70','织布车间','0',1,NULL,'2017-10-25 20:52:34',NULL,'2017-10-25 20:52:34',NULL,'0');

/*Table structure for table `job_reward_amerce_info` */

DROP TABLE IF EXISTS `job_reward_amerce_info`;

CREATE TABLE `job_reward_amerce_info` (
  `id` char(32) NOT NULL COMMENT '信息记录',
  `reward_amerce_source` varchar(20) NOT NULL COMMENT '奖扣问题来源',
  `reward_amerce_id` char(32) NOT NULL COMMENT '奖扣问题编号',
  `reward_amerce_name` varchar(2000) DEFAULT NULL COMMENT '奖扣问题名称',
  `worker_kind` varchar(50) NOT NULL COMMENT '工种',
  `staff` varchar(32) NOT NULL COMMENT '责任人',
  `reward_amerce_type` char(4) NOT NULL COMMENT '奖扣类型',
  `reward_amerce_score` decimal(5,2) NOT NULL COMMENT '奖扣分',
  `inspect_staff` varchar(32) NOT NULL COMMENT '处理人',
  `inspect_time` datetime NOT NULL COMMENT '处理时间',
  `status` tinyint(4) DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `del_flag` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='奖扣分信息表';

/*Data for the table `job_reward_amerce_info` */

insert  into `job_reward_amerce_info`(`id`,`reward_amerce_source`,`reward_amerce_id`,`reward_amerce_name`,`worker_kind`,`staff`,`reward_amerce_type`,`reward_amerce_score`,`inspect_staff`,`inspect_time`,`status`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) values ('3c9785bf66fb43529b50dcde837bbdae','G05','J0002','筒子成形不良','GZ30000046','E9988','扣','7.00','E9966','2018-04-13 19:09:59',NULL,NULL,'2018-04-13 19:09:59',NULL,'2018-04-13 19:09:59',NULL,'0'),('3cb4d6d414fe4329af1413d94d17f6f3','G04','J0003','小辫','GZ30000046','E4199','奖','2.00','E9966','2018-04-13 19:25:27',NULL,NULL,'2018-04-13 19:25:27',NULL,'2018-04-13 19:25:27',NULL,'0'),('53c763a155e44c0a8b256c81f2bd441d','G06','J0003','小辫','GZ30000047','E9966','奖','5.00','E4199','2018-04-13 19:12:04',NULL,NULL,'2018-04-13 19:12:04',NULL,'2018-04-13 19:12:04',NULL,'0'),('564d0876d5a643299b0cd37e3edecdee','G06','J0002','筒子成形不良','GZ30000046','E3002','扣','6.00','E4199','2018-04-13 19:19:39',NULL,NULL,'2018-04-13 19:19:39',NULL,'2018-04-13 19:19:39',NULL,'0'),('6295f9ff85664e468e723726ff9df550','G04','J0002','筒子成形不良','GZ30000047','E4199','奖','3.00','E333','2018-04-13 19:25:27',NULL,NULL,'2018-04-13 19:25:27',NULL,'2018-04-13 19:25:27',NULL,'0'),('d08162687dcb46efa14d71026fc1e307','G08','J0657','关车日空压气阀未关到位、未关，电源未关','GZ30000046','E9966','奖','5.00','E9966','2018-04-13 19:12:04',NULL,NULL,'2018-04-13 19:12:04',NULL,'2018-04-13 19:12:04',NULL,'0'),('dd15d46e345145018d7918685d3eb865','G07','J0320','更衣室乱挂（放）衣物、毛巾、鞋子等','GZ30000047','E9988','扣','6.00','E9966','2018-04-13 19:12:04',NULL,NULL,'2018-04-13 19:12:04',NULL,'2018-04-13 19:12:04',NULL,'0'),('e9930eeba81243e98038673a2b2bb98e','G04','J0006','外卷','GZ30000046','E9988','扣','1.00','E9966','2018-04-13 19:25:27',NULL,NULL,'2018-04-13 19:25:27',NULL,'2018-04-13 19:25:27',NULL,'0');

/*Table structure for table `job_segmentation_cloth` */

DROP TABLE IF EXISTS `job_segmentation_cloth`;

CREATE TABLE `job_segmentation_cloth` (
  `id` char(32) NOT NULL,
  `segmentation_record_id` char(32) DEFAULT NULL,
  `segmentation_code` varchar(50) DEFAULT NULL COMMENT '分段布编号',
  `shaft_code` varchar(50) DEFAULT NULL COMMENT '布轴编号',
  `material_code` varchar(50) DEFAULT NULL,
  `material_name` varchar(150) DEFAULT NULL,
  `cloth_length` decimal(10,4) DEFAULT NULL,
  `serial_number` int(10) DEFAULT NULL COMMENT '顺序号',
  `length` decimal(10,4) DEFAULT NULL COMMENT '分段布匹长度（米）',
  `rank` char(32) DEFAULT NULL COMMENT '等级',
  `rank_name` char(50) DEFAULT NULL,
  `mending_status` varchar(2) DEFAULT NULL COMMENT '修布状态',
  `folding_status` varchar(2) DEFAULT NULL COMMENT '折布状态',
  `folding_width` decimal(10,4) DEFAULT NULL COMMENT '折幅（cm）',
  `is_mending_out` tinyint(1) DEFAULT NULL COMMENT '是否修布领出',
  `is_mending_in` tinyint(1) DEFAULT NULL COMMENT '是否修布还回',
  `status` varchar(2) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `create_user` char(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user` char(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_flag` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `job_segmentation_cloth` */

insert  into `job_segmentation_cloth`(`id`,`segmentation_record_id`,`segmentation_code`,`shaft_code`,`material_code`,`material_name`,`cloth_length`,`serial_number`,`length`,`rank`,`rank_name`,`mending_status`,`folding_status`,`folding_width`,`is_mending_out`,`is_mending_in`,`status`,`remarks`,`create_user`,`create_time`,`update_user`,`update_time`,`del_flag`) values ('03a6a95f98834290a0b15f6eb0202a13','8df805d4438141778217a3b3aa96ffd4','B04201807270005-05','B04201807270005','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','500.0000',5,'10.0000','2','出口单双','0','0',NULL,0,0,'1',NULL,'yujianhua','2018-08-06 21:42:13','yujianhua','2018-08-06 21:42:13','0'),('07dccd98d4f842fc812527e2cfd58f65','c2ca58b25007437c9297031b54276d8f','B04201807270003-11','B04201807270003','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','300.0000',11,'15.0000','3','深杂','0','0',NULL,0,0,'1',NULL,'admin','2018-08-07 16:24:33','admin','2018-08-07 16:24:33','0'),('15aa07be7e704a62b9c243ed9958ddac','bba5925ebe4c4553906e44b03840e08a','B04201807270001-02','B04201807270001','C073223220012','坯布-CVC32/2*32/2 100*53 63\"2/1碳黑1.06镶嵌CS18-14-1','240.0300',2,'10.0000','1','出口大联','0','0',NULL,0,0,'1',NULL,'admin','2018-08-22 10:18:25','admin','2018-08-22 10:18:25','0'),('15cabf1f827d476a904abb737aa3221a','c2ca58b25007437c9297031b54276d8f','B04201807270003-01','B04201807270003','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','300.0000',1,'15.0000','1','出口大联','0','0',NULL,0,0,'1',NULL,'admin','2018-08-07 16:24:33','admin','2018-08-07 16:24:33','0'),('24b141a43f724e69b39242e747b3d146','4b3637a025fe4b7ea1131e77f9a5713d','B04201807260005-01','B04201807260005','C073223220012','坯布-CVC32/2*32/2 100*53 63\"2/1碳黑1.06镶嵌CS18-14-1','240.0300',1,'10.0000','1','出口大联','0','0',NULL,0,0,'1',NULL,'admin','2018-08-23 13:49:28','admin','2018-08-23 13:49:28','0'),('24be73f32f114caca66c504e3663430e','c2ca58b25007437c9297031b54276d8f','B04201807270003-04','B04201807270003','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','300.0000',4,'15.0000','1','出口大联','0','0',NULL,0,0,'1',NULL,'admin','2018-08-07 16:24:33','admin','2018-08-07 16:24:33','0'),('26e2f1b9c8e94ee5afc16943339c642e','bba5925ebe4c4553906e44b03840e08a','B04201807270001-01','B04201807270001','C073223220012','坯布-CVC32/2*32/2 100*53 63\"2/1碳黑1.06镶嵌CS18-14-1','240.0300',1,'60.0000','1','出口大联','0','0',NULL,0,0,'1',NULL,'admin','2018-08-22 10:18:25','admin','2018-08-22 10:18:25','0'),('2d48cb7fe9fd4165b62f60a92659009a','8df805d4438141778217a3b3aa96ffd4','B04201807270005-06','B04201807270005','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','500.0000',6,'10.0000','2','出口单双','0','0',NULL,0,0,'1',NULL,'yujianhua','2018-08-06 21:42:13','yujianhua','2018-08-06 21:42:13','0'),('2d632a97235549129c440881c3908e2c','ff9dd99ee44a4c03ba561e414f41553f','B04201807270004-03','B04201807270004','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','200.0000',3,'20.0000','2','出口单双','0','0',NULL,0,0,'1',NULL,'admin','2018-08-07 15:21:15','admin','2018-08-07 15:21:15','0'),('3cb7bc07d0c548669a4a0911482f2974','c927034dab284119b3b22dc53ae9a9a7','B04201805240001-01','B04201805240001','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','500.0000',1,'120.0000','1','出口大联','3','1','4.0000',1,0,'1',NULL,'wangyanling','2018-05-24 15:49:55','wangyanling','2018-05-24 15:49:55','0'),('3f014ace76714bfe96545b05c6831f62','8df805d4438141778217a3b3aa96ffd4','B04201807270005-13','B04201807270005','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','500.0000',13,'10.0000','5','混等拼件','0','0',NULL,0,0,'1',NULL,'yujianhua','2018-08-06 21:42:13','yujianhua','2018-08-06 21:42:13','0'),('43377d0022354808954a25ebb6615e40','c2ca58b25007437c9297031b54276d8f','B04201807270003-10','B04201807270003','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','300.0000',10,'15.0000','3','深杂','0','0',NULL,0,0,'1',NULL,'admin','2018-08-07 16:24:33','admin','2018-08-07 16:24:33','0'),('4405621e8c0a4ee38b0d109238d24cb0','c2ca58b25007437c9297031b54276d8f','B04201807270003-06','B04201807270003','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','300.0000',6,'15.0000','2','出口单双','0','0',NULL,0,0,'1',NULL,'admin','2018-08-07 16:24:33','admin','2018-08-07 16:24:33','0'),('44f1089d86554493add0e7df8f0c4552','ff9dd99ee44a4c03ba561e414f41553f','B04201807270004-05','B04201807270004','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','200.0000',5,'20.0000','3','深杂','0','0',NULL,0,0,'1',NULL,'admin','2018-08-07 15:21:15','admin','2018-08-07 15:21:15','0'),('4872715a92d84642b5d8c837cab12867','2183793be2a0464fae8aa9680e6e76d8','	B04201805240001-01','	B04201805240001','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','500.0000',1,'10.0000','1','出口大联','0','0',NULL,0,0,'1',NULL,'admin','2018-08-21 09:52:27','admin','2018-08-21 09:52:27','0'),('50a67b0ef4fa4a7bb47afe41ee362f9e','c927034dab284119b3b22dc53ae9a9a7','B04201805240001-03','B04201805240001','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','500.0000',3,'150.0000','2','出口单双','0','0',NULL,0,0,'1',NULL,'wangyanling','2018-05-24 15:49:55','wangyanling','2018-05-24 15:49:55','0'),('5b7eb9fdb15c4c9f845e66e5d16a87f1','4216e391402d4d4f9001ab8e7cb0293d','B04201807270002-01','B04201807270002','C073223220012','坯布-CVC32/2*32/2 100*53 63\"2/1碳黑1.06镶嵌CS18-14-1','240.0300',1,'10.0000','1','出口大联','0','0',NULL,0,0,'1',NULL,'admin','2018-08-23 13:29:43','admin','2018-08-23 13:29:43','0'),('5c852e444ef643f89a52d610723ce15e','8df805d4438141778217a3b3aa96ffd4','B04201807270005-11','B04201807270005','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','500.0000',11,'10.0000','4','大中零','0','0',NULL,0,0,'1',NULL,'yujianhua','2018-08-06 21:42:13','yujianhua','2018-08-06 21:42:13','0'),('6c885d18c7e5440abcb150d4c0f23dda','ff9dd99ee44a4c03ba561e414f41553f','B04201807270004-06','B04201807270004','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','200.0000',6,'20.0000','3','深杂','0','0',NULL,0,0,'1',NULL,'admin','2018-08-07 15:21:15','admin','2018-08-07 15:21:15','0'),('6d4e1e03373846dca828a58e280c26a7','c2ca58b25007437c9297031b54276d8f','B04201807270003-03','B04201807270003','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','300.0000',3,'15.0000','1','出口大联','0','0',NULL,0,0,'1',NULL,'admin','2018-08-07 16:24:33','admin','2018-08-07 16:24:33','0'),('6ec41a90a3f942d0984c9437c8049d48','c927034dab284119b3b22dc53ae9a9a7','B04201805240001-02','B04201805240001','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','500.0000',2,'120.0000','1','出口大联','0','1','6.0000',0,0,'1',NULL,'wangyanling','2018-05-24 15:49:55','wangyanling','2018-05-24 15:49:55','0'),('6f963d7293404ba69af618d21a3dcacc','3c49af9f89cc427c83ac409e61f5d889','B04201807270006-01','B04201807270006','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','400.0000',1,'100.0000','1','出口大联','0','0',NULL,0,0,'1',NULL,'yujianhua','2018-08-06 20:33:26','yujianhua','2018-08-06 20:33:26','0'),('712a8f566db4451185f2110a6558a221','c2ca58b25007437c9297031b54276d8f','B04201807270003-13','B04201807270003','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','300.0000',13,'15.0000','4','大中零','0','0',NULL,0,0,'1',NULL,'admin','2018-08-07 16:24:33','admin','2018-08-07 16:24:33','0'),('72cedf47262c4e4395b8ea91870441ef','8df805d4438141778217a3b3aa96ffd4','B04201807270005-02','B04201807270005','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','500.0000',2,'10.0000','1','出口大联','0','0',NULL,0,0,'1',NULL,'yujianhua','2018-08-06 21:42:13','yujianhua','2018-08-06 21:42:13','0'),('777a91b8796a40d08de04c5ba272d5f8','c2ca58b25007437c9297031b54276d8f','B04201807270003-12','B04201807270003','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','300.0000',12,'15.0000','4','大中零','0','0',NULL,0,0,'1',NULL,'admin','2018-08-07 16:24:33','admin','2018-08-07 16:24:33','0'),('7f725cddfa404f4994209b9d7b2b45ee','bba5925ebe4c4553906e44b03840e08a','B04201807270001-04','B04201807270001','C073223220012','坯布-CVC32/2*32/2 100*53 63\"2/1碳黑1.06镶嵌CS18-14-1','240.0300',4,'20.0000','1','出口大联','0','0',NULL,0,0,'1',NULL,'admin','2018-08-22 10:18:25','admin','2018-08-22 10:18:25','0'),('80eaf9a6b8e7416395ac0c846698349d','8df805d4438141778217a3b3aa96ffd4','B04201807270005-08','B04201807270005','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','500.0000',8,'10.0000','3','深杂','0','0',NULL,0,0,'1',NULL,'yujianhua','2018-08-06 21:42:13','yujianhua','2018-08-06 21:42:13','0'),('8166722aafc247c5852a72898bfee5f9','8df805d4438141778217a3b3aa96ffd4','B04201807270005-10','B04201807270005','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','500.0000',10,'10.0000','4','大中零','0','0',NULL,0,0,'1',NULL,'yujianhua','2018-08-06 21:42:13','yujianhua','2018-08-06 21:42:13','0'),('823f4bc327034ef09cc20f5d8ab38d54','ff9dd99ee44a4c03ba561e414f41553f','B04201807270004-09','B04201807270004','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','200.0000',9,'20.0000','5','混等拼件','0','0',NULL,0,0,'1',NULL,'admin','2018-08-07 15:21:15','admin','2018-08-07 15:21:15','0'),('8289cf93abcc4221a6e0bb9d7fab6247','c2ca58b25007437c9297031b54276d8f','B04201807270003-19','B04201807270003','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','300.0000',19,'4.3200','6','小/次零','0','0',NULL,0,0,'1',NULL,'admin','2018-08-07 16:24:33','admin','2018-08-07 16:24:33','0'),('84f87cbbf4f64f009d56f2e8a1b6277a','8df805d4438141778217a3b3aa96ffd4','B04201807270005-15','B04201807270005','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','500.0000',15,'10.0000','6','小/次零','0','0',NULL,0,0,'1',NULL,'yujianhua','2018-08-06 21:42:13','yujianhua','2018-08-06 21:42:13','0'),('87c7f0a60e6a49708d19e76714be7a15','c2ca58b25007437c9297031b54276d8f','B04201807270003-14','B04201807270003','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','300.0000',14,'15.0000','4','大中零','0','0',NULL,0,0,'1',NULL,'admin','2018-08-07 16:24:33','admin','2018-08-07 16:24:33','0'),('8ef50ba91bb241a7ad5e65cd91b0568d','8df805d4438141778217a3b3aa96ffd4','B04201807270005-09','B04201807270005','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','500.0000',9,'10.0000','3','深杂','0','0',NULL,0,0,'1',NULL,'yujianhua','2018-08-06 21:42:13','yujianhua','2018-08-06 21:42:13','0'),('91722fef00e64256949cacedef003a73','ff9dd99ee44a4c03ba561e414f41553f','B04201807270004-10','B04201807270004','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','200.0000',10,'2.2800','5','混等拼件','0','0',NULL,0,0,'1',NULL,'admin','2018-08-07 15:21:15','admin','2018-08-07 15:21:15','0'),('98415992cc4a41a48b1751e93e0b5442','c2ca58b25007437c9297031b54276d8f','B04201807270003-05','B04201807270003','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','300.0000',5,'15.0000','2','出口单双','0','0',NULL,0,0,'1',NULL,'admin','2018-08-07 16:24:33','admin','2018-08-07 16:24:33','0'),('9997141275c941d78321147b9a9f294d','8df805d4438141778217a3b3aa96ffd4','B04201807270005-16','B04201807270005','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','500.0000',16,'217.2000','6','小/次零','0','0',NULL,0,0,'1',NULL,'yujianhua','2018-08-06 21:42:13','yujianhua','2018-08-06 21:42:13','0'),('9b69dc37e29f462bafddb20200ab14e8','c2ca58b25007437c9297031b54276d8f','B04201807270003-17','B04201807270003','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','300.0000',17,'15.0000','5','混等拼件','0','0',NULL,0,0,'1',NULL,'admin','2018-08-07 16:24:33','admin','2018-08-07 16:24:33','0'),('9bb1f2a804414d4da066d5c28615811c','ff9dd99ee44a4c03ba561e414f41553f','B04201807270004-07','B04201807270004','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','200.0000',7,'20.0000','4','大中零','0','0',NULL,0,0,'1',NULL,'admin','2018-08-07 15:21:15','admin','2018-08-07 15:21:15','0'),('a7987dcf5c514c24b22b8930f6249c49','c2ca58b25007437c9297031b54276d8f','B04201807270003-07','B04201807270003','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','300.0000',7,'15.0000','2','出口单双','0','0',NULL,0,0,'1',NULL,'admin','2018-08-07 16:24:33','admin','2018-08-07 16:24:33','0'),('a977f2a8cee54a8f87a4f9a2f3961b7b','8df805d4438141778217a3b3aa96ffd4','B04201807270005-12','B04201807270005','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','500.0000',12,'10.0000','4','大中零','0','0',NULL,0,0,'1',NULL,'yujianhua','2018-08-06 21:42:13','yujianhua','2018-08-06 21:42:13','0'),('af10f44d56844f61b43b4745e5d62f55','3c49af9f89cc427c83ac409e61f5d889','B04201807270006-02','B04201807270006','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','400.0000',2,'100.0000','2','出口单双','0','0',NULL,0,0,'1',NULL,'yujianhua','2018-08-06 20:33:26','yujianhua','2018-08-06 20:33:26','0'),('b28b000c09bd4c2da3fed80444a37fa7','c2ca58b25007437c9297031b54276d8f','B04201807270003-02','B04201807270003','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','300.0000',2,'15.0000','1','出口大联','0','0',NULL,0,0,'1',NULL,'admin','2018-08-07 16:24:33','admin','2018-08-07 16:24:33','0'),('b9860cf7b647410b9ca1075c22bc3a72','8df805d4438141778217a3b3aa96ffd4','B04201807270005-14','B04201807270005','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','500.0000',14,'100.0000','5','混等拼件','0','0',NULL,0,0,'1',NULL,'yujianhua','2018-08-06 21:42:13','yujianhua','2018-08-06 21:42:13','0'),('b9b6ef95842844d4a9ff0aea3fae75ec','c2ca58b25007437c9297031b54276d8f','B04201807270003-08','B04201807270003','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','300.0000',8,'15.0000','3','深杂','0','0',NULL,0,0,'1',NULL,'admin','2018-08-07 16:24:33','admin','2018-08-07 16:24:33','0'),('ba39409738a64c288a23dc060c0af993','c2ca58b25007437c9297031b54276d8f','B04201807270003-18','B04201807270003','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','300.0000',18,'15.0000','5','混等拼件','0','0',NULL,0,0,'1',NULL,'admin','2018-08-07 16:24:33','admin','2018-08-07 16:24:33','0'),('be626718571e49c6a1374089cca3b780','ff9dd99ee44a4c03ba561e414f41553f','B04201807270004-01','B04201807270004','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','200.0000',1,'20.0000','1','出口大联','0','0',NULL,0,0,'1',NULL,'admin','2018-08-07 15:21:15','admin','2018-08-07 15:21:15','0'),('c209089143754c008f40889da0b48d7d','8df805d4438141778217a3b3aa96ffd4','B04201807270005-04','B04201807270005','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','500.0000',4,'10.0000','2','出口单双','0','0',NULL,0,0,'1',NULL,'yujianhua','2018-08-06 21:42:13','yujianhua','2018-08-06 21:42:13','0'),('c5479f210c444ffc8e0903645ef90700','2183793be2a0464fae8aa9680e6e76d8','	B04201805240001-02','	B04201805240001','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','500.0000',2,'490.0000','1','出口大联','0','0',NULL,0,0,'1',NULL,'admin','2018-08-21 09:52:27','admin','2018-08-21 09:52:27','0'),('c643f2b74e144b589aa032f91c347093','ff9dd99ee44a4c03ba561e414f41553f','B04201807270004-08','B04201807270004','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','200.0000',8,'20.0000','4','大中零','0','0',NULL,0,0,'1',NULL,'admin','2018-08-07 15:21:15','admin','2018-08-07 15:21:15','0'),('c93334a95db1450a9184dec1b3c8f6f6','c927034dab284119b3b22dc53ae9a9a7','B04201805240001-04','B04201805240001','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','500.0000',4,'67.2000','2','出口单双','0','0',NULL,0,0,'1',NULL,'wangyanling','2018-05-24 15:49:55','wangyanling','2018-05-24 15:49:55','0'),('ce8c81b9de4d42ac92034992ad5f579f','8df805d4438141778217a3b3aa96ffd4','B04201807270005-03','B04201807270005','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','500.0000',3,'10.0000','2','出口单双','0','0',NULL,0,0,'1',NULL,'yujianhua','2018-08-06 21:42:13','yujianhua','2018-08-06 21:42:13','0'),('cf530dea1dda4becbb50951baa02c67a','8df805d4438141778217a3b3aa96ffd4','B04201807270005-01','B04201807270005','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','500.0000',1,'10.0000','1','出口大联','0','0',NULL,0,0,'1',NULL,'yujianhua','2018-08-06 21:42:13','yujianhua','2018-08-06 21:42:13','0'),('e11a0d11c1b846c2b2c9dcebc35817ea','8df805d4438141778217a3b3aa96ffd4','B04201807270005-07','B04201807270005','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','500.0000',7,'10.0000','3','深杂','0','0',NULL,0,0,'1',NULL,'yujianhua','2018-08-06 21:42:13','yujianhua','2018-08-06 21:42:13','0'),('e7b1f703e1f4418a83590cf121443147','3c49af9f89cc427c83ac409e61f5d889','B04201807270006-03','B04201807270006','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','400.0000',3,'165.7600','3','深杂','0','0',NULL,0,0,'1',NULL,'yujianhua','2018-08-06 20:33:26','yujianhua','2018-08-06 20:33:26','0'),('ec968504635741e8aef4ed6a6ed9ef2f','c2ca58b25007437c9297031b54276d8f','B04201807270003-16','B04201807270003','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','300.0000',16,'15.0000','5','混等拼件','0','0',NULL,0,0,'1',NULL,'admin','2018-08-07 16:24:33','admin','2018-08-07 16:24:33','0'),('f0625d147cce4777badb194314497df8','ff9dd99ee44a4c03ba561e414f41553f','B04201807270004-04','B04201807270004','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','200.0000',4,'20.0000','2','出口单双','0','0',NULL,0,0,'1',NULL,'admin','2018-08-07 15:21:15','admin','2018-08-07 15:21:15','0'),('f6c26e586d3547b6b519d28eca182456','c2ca58b25007437c9297031b54276d8f','B04201807270003-15','B04201807270003','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','300.0000',15,'15.0000','4','大中零','0','0',NULL,0,0,'1',NULL,'admin','2018-08-07 16:24:33','admin','2018-08-07 16:24:33','0'),('fa64311fbe984c17887fd063ef7d6848','ff9dd99ee44a4c03ba561e414f41553f','B04201807270004-02','B04201807270004','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','200.0000',2,'20.0000','1','出口大联','0','0',NULL,0,0,'1',NULL,'admin','2018-08-07 15:21:15','admin','2018-08-07 15:21:15','0'),('fd69d83a886b4e718d80d969e5cccd84','bba5925ebe4c4553906e44b03840e08a','B04201807270001-03','B04201807270001','C073223220012','坯布-CVC32/2*32/2 100*53 63\"2/1碳黑1.06镶嵌CS18-14-1','240.0300',3,'10.0000','1','出口大联','0','0',NULL,0,0,'1',NULL,'admin','2018-08-22 10:18:25','admin','2018-08-22 10:18:25','0'),('ff7b66aeb25b47e8a8cb903df782c7ee','c2ca58b25007437c9297031b54276d8f','B04201807270003-09','B04201807270003','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','300.0000',9,'15.0000','3','深杂','0','0',NULL,0,0,'1',NULL,'admin','2018-08-07 16:24:33','admin','2018-08-07 16:24:33','0');

/*Table structure for table `job_segmentation_record` */

DROP TABLE IF EXISTS `job_segmentation_record`;

CREATE TABLE `job_segmentation_record` (
  `id` char(32) NOT NULL,
  `task_id` char(32) DEFAULT NULL COMMENT '任务id',
  `bill_no` varchar(50) DEFAULT NULL COMMENT '报工单号',
  `product_task_no` varchar(50) DEFAULT NULL COMMENT '生产任务书编号',
  `shaft_code` varchar(50) DEFAULT NULL COMMENT '布轴编号',
  `material_code` varchar(50) DEFAULT NULL COMMENT '物料编码',
  `material_name` varchar(100) DEFAULT NULL COMMENT '品名',
  `work_center` varchar(10) DEFAULT NULL COMMENT '工作中心',
  `work_class` varchar(10) DEFAULT NULL COMMENT '班次',
  `cloth_length` decimal(10,4) DEFAULT NULL COMMENT '布轴长度（码）',
  `create_user` char(32) DEFAULT NULL,
  `create_user_name` varchar(50) DEFAULT NULL,
  `worker_office` varchar(50) DEFAULT NULL COMMENT '班',
  `worker_office_name` varchar(50) DEFAULT NULL,
  `segmentation_number` int(6) DEFAULT NULL COMMENT '分成段数',
  `remaining_length` decimal(10,4) DEFAULT NULL COMMENT '剩余长度',
  `quality_result` varchar(2) DEFAULT NULL COMMENT '质检结果',
  `status` varchar(1) DEFAULT NULL COMMENT '状态',
  `remarks` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user` char(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_flag` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `job_segmentation_record` */

insert  into `job_segmentation_record`(`id`,`task_id`,`bill_no`,`product_task_no`,`shaft_code`,`material_code`,`material_name`,`work_center`,`work_class`,`cloth_length`,`create_user`,`create_user_name`,`worker_office`,`worker_office_name`,`segmentation_number`,`remaining_length`,`quality_result`,`status`,`remarks`,`create_time`,`update_user`,`update_time`,`del_flag`) values ('2183793be2a0464fae8aa9680e6e76d8','c97615f26c8c4037ba98ba0bdc91cbbc','C1055_1_1-39-01-01','201805-001','	B04201805240001','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','W14','','500.0000','wangaixia','王爱霞','C1404','整理车间常日班',2,'0.0000',NULL,'0',NULL,'2018-06-06 13:41:34','admin','2018-08-21 09:52:27','0'),('3c49af9f89cc427c83ac409e61f5d889','ff022b1babad425196a41c673091ae79','C1055_1_1-62-01-01','201805-001','B04201807270006','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','W14','W01','400.0000','yujianhua','于建华','C1101','准备运转甲班',3,'0.0000',NULL,'1',NULL,'2018-08-06 20:33:26','yujianhua','2018-08-06 20:33:26','0'),('4216e391402d4d4f9001ab8e7cb0293d','5075f01c86e1494799fbc15038039742','C1070_1_1-31-01-01','201807-001','B04201807270002','C073223220012','坯布-CVC32/2*32/2 100*53 63\"2/1碳黑1.06镶嵌CS18-14-1','W14','W04','240.0300','admin','系统管理员','C1104','准备常日班',1,'230.0300',NULL,'0',NULL,'2018-08-23 13:29:43','admin','2018-08-23 13:29:43','0'),('4b3637a025fe4b7ea1131e77f9a5713d','5075f01c86e1494799fbc15038039742','C1070_1_1-31-01-01','201807-001','B04201807260005','C073223220012','坯布-CVC32/2*32/2 100*53 63\"2/1碳黑1.06镶嵌CS18-14-1','W14','W04','240.0300','admin','系统管理员','C1104','准备常日班',1,'230.0300',NULL,'0',NULL,'2018-08-23 13:49:28','admin','2018-08-23 13:49:28','0'),('8df805d4438141778217a3b3aa96ffd4','ff022b1babad425196a41c673091ae79','C1055_1_1-62-01-01','201805-001','B04201807270005','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','W14','','500.0000','yujianhua','于建华','C1101','准备运转甲班',16,'0.0000',NULL,'1',NULL,'2018-08-06 21:42:13','yujianhua','2018-08-06 21:42:13','0'),('bba5925ebe4c4553906e44b03840e08a','5075f01c86e1494799fbc15038039742','C1070_1_1-31-01-01','201807-001','B04201807270001','C073223220012','坯布-CVC32/2*32/2 100*53 63\"2/1碳黑1.06镶嵌CS18-14-1','W14','W04','240.0300','zhaohuimin','赵慧敏','C1404','整理车间常日班',4,'140.0300',NULL,'1',NULL,'2018-08-09 22:52:23','admin','2018-08-22 10:18:25','0'),('c2ca58b25007437c9297031b54276d8f','ff022b1babad425196a41c673091ae79','C1055_1_1-62-01-01','201805-001','B04201807270003','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','W14','','300.0000','admin','系统管理员','C1104','准备常日班',19,'0.0000',NULL,'1',NULL,'2018-08-07 16:24:33','admin','2018-08-07 16:24:33','0'),('c927034dab284119b3b22dc53ae9a9a7','0c42c622a89f42308a31da6ee1debbb7','C1055_1_1-05-01-01','201805-001','B04201805240001','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','W14','W04','500.0000','wangyanling','王彦玲','C1401','整理车间运转甲班',4,'0.0000',NULL,'1',NULL,'2018-05-24 15:49:52','wangyanling','2018-05-24 15:49:55','0'),('ff9dd99ee44a4c03ba561e414f41553f','ff022b1babad425196a41c673091ae79','C1055_1_1-62-01-01','201805-001','B04201807270004','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','W14','W01','200.0000','admin','系统管理员','C1104','准备常日班',10,'0.6000',NULL,'1',NULL,'2018-08-07 15:21:15','admin','2018-08-07 15:21:15','0');

/*Table structure for table `job_slashing_record` */

DROP TABLE IF EXISTS `job_slashing_record`;

CREATE TABLE `job_slashing_record` (
  `id` char(32) NOT NULL,
  `erp_bill_no` varchar(64) DEFAULT NULL,
  `task_id` varchar(50) NOT NULL,
  `variety` varchar(50) DEFAULT NULL,
  `material_name` varchar(200) DEFAULT NULL,
  `machine_code` varchar(50) NOT NULL,
  `shaft_code` varchar(50) NOT NULL,
  `serial_no` varchar(50) DEFAULT NULL COMMENT '序列号',
  `slashing_worker` varchar(32) NOT NULL,
  `work_class` varchar(10) NOT NULL,
  `work_team` varchar(50) DEFAULT NULL COMMENT '班组',
  `work_center` varchar(50) DEFAULT NULL COMMENT '工作中心',
  `number` decimal(20,2) NOT NULL,
  `unit` varchar(10) DEFAULT NULL,
  `unit_name` varchar(20) DEFAULT NULL,
  `factory` varchar(50) DEFAULT NULL,
  `yarn_code` varchar(50) DEFAULT NULL,
  `pn_info` varchar(20) DEFAULT NULL,
  `match_shaft_worker` varchar(32) DEFAULT NULL,
  `match_shaft_code` varchar(50) DEFAULT NULL,
  `match_shaft_number` bigint(20) DEFAULT NULL,
  `downer` varchar(32) DEFAULT NULL,
  `down_time` datetime DEFAULT NULL,
  `checker` varchar(32) DEFAULT NULL,
  `change_duty` varchar(20) DEFAULT NULL,
  `change_duty_worker` varchar(32) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `del_flag` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `job_slashing_record` */

insert  into `job_slashing_record`(`id`,`erp_bill_no`,`task_id`,`variety`,`material_name`,`machine_code`,`shaft_code`,`serial_no`,`slashing_worker`,`work_class`,`work_team`,`work_center`,`number`,`unit`,`unit_name`,`factory`,`yarn_code`,`pn_info`,`match_shaft_worker`,`match_shaft_code`,`match_shaft_number`,`downer`,`down_time`,`checker`,`change_duty`,`change_duty_worker`,`status`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) values ('01a9709ec04c481c849c349ed379afa1','30_*_*','30_*_*-03-01-01','B020302030001','浆轴-T/JC（30+30）&times;20   132&times;62   63&rdquo; 双经布（双幅）','S202005101001','','B02201806040002','E2854','W01','C1101','W04','600.00','U001','米','','','','','',NULL,'E2854','2018-06-04 15:34:00','','','',1,NULL,'2018-06-04 15:34:36',NULL,'2018-06-04 15:34:36','','1'),('0bef7ec380ff47d5bc86f5369ce4b672','38_*_*','38_*_*-02-01-01','B020409604002','浆轴-LF60*LF60 173*156 118&quot;XC60319','S202005103003','238','B02201806070007','E1202','W01','C1101','W04','54.00','','请选择','','','正','E2432','239',54,'','2018-06-11 15:17:00','','','',1,NULL,'2018-06-07 15:16:27',NULL,'2018-06-07 15:16:27','','1'),('0ced22c854fb452a904b9e6c7093fcbe','38_*_*','38_*_*-02-01-01','B020409604002','浆轴-LF60*LF60 173*156 118&quot;XC60319','S202005103003','066','B02201807010009','E1202','W01','C1101','W04','45.00','U005','匹','洛阳联众','黑头','反','','116',NULL,'E2434','2018-07-01 15:00:00','E678','','E549',1,NULL,'2018-07-01 14:24:39',NULL,'2018-07-01 14:24:39','','1'),('12d89058d7cd429784e27d4a826d9733','30_*_*','30_*_*-01-01-01','B020302030001','浆轴-T/JC（30+30）&times;20   132&times;62   63&rdquo; 双经布（双幅）','S202005102002','','B02201806070001','E6028','W01','C1101','W04','192.00','','请选择','','','','','',NULL,'',NULL,'','','',1,NULL,'2018-06-07 12:14:54',NULL,'2018-06-07 12:14:54','','1'),('192f9f3d5bd14aac8a3a4636d4bd23e4','41_*_*','41_*_*-04-01-01','B020404060001','浆轴-LF60*LF60 173*156 118&quot;XY60245','S202005106006','222','B02201808080001','E13','W01','C1101','W04','111.00','U005','匹','','','','','',NULL,'',NULL,'','','E1411',1,NULL,'2018-08-08 21:45:17',NULL,'2018-08-08 21:45:17','','1'),('1bbaf85fb3514a2f946cccbfaf250822','30_*_*','30_*_*-01-01-01','B020302030001','浆轴-T/JC（30+30）&times;20   132&times;62   63&rdquo; 双经布（双幅）','S202005102002','123','B02201805230001','E6028','W01','C1101','W04','192.00','U005','匹','','','','','124',NULL,'',NULL,'','','',1,NULL,'2018-05-23 15:17:16',NULL,'2018-05-23 15:17:16','','1'),('1ea1884f4de04a5bab51bf77068f90af','30_*_*','30_*_*-01-01-01','B020302030001','浆轴-T/JC（30+30）&times;20   132&times;62   63&rdquo; 双经布（双幅）','S202005102002','123','B02201806020001','E6028','W01','C1101','W04','2350.00','','请选择','','','','','',NULL,'',NULL,'','','',1,NULL,'2018-06-02 14:22:08',NULL,'2018-06-02 14:22:08','','1'),('2d93de7b06b54b5598489b1fc2304aec','38_*_*','38_*_*-02-01-01','B020409604002','浆轴-LF60*LF60 173*156 118&quot;XC60319','S202005103003','240','B02201806070008','E1202','W01','C1101','W04','54.00','','请选择','','','正','E2432','241',54,'','2018-06-11 15:17:00','','','',1,NULL,'2018-06-07 15:16:41',NULL,'2018-06-07 15:16:41','','1'),('37dfc677f3b049f4a31e15ee71352926','30_*_*','30_*_*-01-01-01','B020302030001','浆轴-T/JC（30+30）&times;20   132&times;62   63&rdquo; 双经布（双幅）','S202005102002','123','B02201806070002','E6028','W01','C1101','W04','192.00','','请选择','','','','','',NULL,'',NULL,'','','',1,NULL,'2018-06-07 12:15:01',NULL,'2018-06-07 12:15:01','','1'),('49814ca8f84948b195ae7e33b7c2d670','38_*_*','38_*_*-02-01-01','B020409604002','浆轴-LF60*LF60 173*156 118&quot;XC60319','S202005103003','066','B02201807010003','E1202','W01','C1101','W04','45.00','U005','匹','洛阳联众黑头','','反','','',NULL,'E1202','2018-07-01 14:17:00','E6042','','E671',1,NULL,'2018-07-01 14:17:46',NULL,'2018-07-01 14:17:46','','1'),('55fb402fe7d14e2a99863ead524cc9e8','38_*_*','38_*_*-02-01-01','B020409604002','浆轴-LF60*LF60 173*156 118&quot;XC60319','S202005103003','066','B02201807010006','E1202','W01','C1101','W04','45.00','U005','匹','洛阳联众','黑头','反','','',NULL,'E2434','2018-07-01 15:00:00','E678','','E549',1,NULL,'2018-07-01 14:21:51',NULL,'2018-07-01 14:21:51','','1'),('6c481a9eceaf450793c816373ca8de3d','30_*_*','30_*_*-01-01-01','B020302030001','浆轴-T/JC（30+30）&times;20   132&times;62   63&rdquo; 双经布（双幅）','S202005102002','123','B02201805230002','E6028','W01','C1101','W04','192.00','U005','匹','','','','','124',NULL,'E6028','2018-05-19 15:18:00','E6042','','',1,NULL,'2018-05-23 15:18:59',NULL,'2018-05-23 15:18:59','','1'),('779e43f1bcf14b68a5228950196ef9f2','38_*_*','38_*_*-02-01-01','B020409604002','浆轴-LF60*LF60 173*156 118&quot;XC60319','S202005103003','066','B02201807010007','E1202','W01','C1101','W04','45.00','U005','匹','洛阳联众','黑头','反','','',NULL,'E2434','2018-07-01 15:00:00','E678','','E549',1,NULL,'2018-07-01 14:21:56',NULL,'2018-07-01 14:21:56','','1'),('952edc8d8c344d54b2b9521e9095232d','38_*_*','38_*_*-02-01-01','B020409604002','浆轴-LF60*LF60 173*156 118&quot;XC60319','S202005103003','066','B02201807010002','E1202','W01','C1101','W04','45.00','U005','匹','洛阳联众黑头','','反','','',NULL,'E1202','2018-07-12 14:17:00','E6042','','E671',1,NULL,'2018-07-01 14:17:13',NULL,'2018-07-01 14:17:13','','1'),('a85c86cc48d7452884c61808087c49e5','38_*_*','38_*_*-02-01-01','B020409604002','浆轴-LF60*LF60 173*156 118&quot;XC60319','S202005103003','116','B02201807010008','E1202','W01','C1101','W04','45.00','U005','匹','洛阳联众','黑头','正','','116',NULL,'E2434','2018-07-01 14:25:00','E678','','E549',1,NULL,'2018-07-01 14:24:21',NULL,'2018-07-01 14:24:21','','0'),('aba31889bc1b49679b9678d3b0518591','38_*_*','38_*_*-02-01-01','B020409604002','浆轴-LF60*LF60 173*156 118&quot;XC60319','S202005103003','123','B02201806070004','E1202','W01','C1101','W04','28.00','','请选择','','','正','','124',28,'E671','2018-06-12 14:54:00','E6042','','',1,NULL,'2018-06-07 14:53:09',NULL,'2018-06-07 14:53:09','','1'),('ac717e3e68f545ef879c78ca98da4f61','30_*_*','30_*_*-01-01-01','B020302030001','浆轴-T/JC（30+30）&times;20   132&times;62   63&rdquo; 双经布（双幅）','S202005102002','123','B02201806070003','E6028','W01','C1101','W04','192.00','','请选择','','','','','',NULL,'',NULL,'','','',1,NULL,'2018-06-07 12:15:04',NULL,'2018-06-07 12:15:04','','1'),('c5af348593b54a6481e9c802bd092bea','38_*_*','38_*_*-02-01-01','B020409604002','浆轴-LF60*LF60 173*156 118&quot;XC60319','S202005103003','236','B02201806070006','E1202','W01','C1101','W04','54.00','','请选择','','','正','E2432','237',54,'','2018-06-11 15:17:00','','','',1,NULL,'2018-06-07 15:16:17',NULL,'2018-06-07 15:16:17','','1'),('df91c294c2ab431ebf39b0a1a541779f','38_*_*','38_*_*-02-01-01','B020409604002','浆轴-LF60*LF60 173*156 118&quot;XC60319','S202005103003','116','B02201807010001','E1202','W01','C1101','W04','45.00','U005','匹','洛阳联众黑头','','正','','',NULL,'E1202','2018-07-12 14:17:00','E6042','','E671',1,NULL,'2018-07-01 14:16:53',NULL,'2018-07-01 14:16:53','','1'),('ec5eb115276b42b8ae0d9ec08ce228a4','30_*_*','30_*_*-01-01-01','B020302030001','浆轴-T/JC（30+30）&times;20   132&times;62   63&rdquo; 双经布（双幅）','S202005102002','123','B02201805230004','E6028','W01','C1101','W04','192.00','','请选择','','','','E6028','124',192,'E6028','2018-05-19 15:41:00','E6042','','',1,NULL,'2018-05-23 15:41:44',NULL,'2018-05-23 15:41:44','','1'),('ef8c44d857ac4cc6bd864b57d9a78259','38_*_*','38_*_*-02-01-01','B020409604002','浆轴-LF60*LF60 173*156 118&quot;XC60319','S202005103003','116','B02201807010004','E1202','W01','C1101','W04','45.00','U005','匹','洛阳联众黑头','','正','','',NULL,'E1202','2018-07-01 14:17:00','E6042','','E671',1,NULL,'2018-07-01 14:18:01',NULL,'2018-07-01 14:18:01','','1'),('f050f9c8e7d8432ab27678d7c61bfeca','30_*_*','30_*_*-01-01-01','B020302030001','浆轴-T/JC（30+30）&times;20   132&times;62   63&rdquo; 双经布（双幅）','S202005102002','123','B02201805230005','E6028','W01','C1101','W04','192.00','','请选择','','','','E6028','124',192,'E6028','2018-05-19 15:41:00','E6042','','',1,NULL,'2018-05-23 15:41:57',NULL,'2018-05-23 15:41:57','','1'),('f256b630557341cf9237bad7067c3afd','38_*_*','38_*_*-02-01-01','B020409604002','浆轴-LF60*LF60 173*156 118&quot;XC60319','S202005103003','234','B02201806070005','E1202','W01','C1101','W04','54.00','','请选择','','','正','E2432','235',54,'','2018-06-11 15:17:00','','','',1,NULL,'2018-06-07 15:16:03',NULL,'2018-06-07 15:16:03','','1'),('f3f6dcaa752644a3b5a17c9e28a257df','38_*_*','38_*_*-02-01-01','B020409604002','浆轴-LF60*LF60 173*156 118&quot;XC60319','S202005103003','066','B02201807010010','E1202','W01','C1101','W04','45.00','U005','匹','洛阳联众','黑头','反','','066',NULL,'E2434','2018-07-01 15:00:00','E678','','E549',1,NULL,'2018-07-01 14:24:51',NULL,'2018-07-01 14:24:51','','0'),('ff61e00251914f16b56385ac96e94977','38_*_*','38_*_*-02-01-01','B020409604002','浆轴-LF60*LF60 173*156 118&quot;XC60319','S202005103003','116','B02201807010005','E1202','W01','C1101','W04','45.00','U005','匹','洛阳联众','黑头','正','','',NULL,'E2434','2018-07-01 14:22:00','E678','','E549',1,NULL,'2018-07-01 14:21:29',NULL,'2018-07-01 14:21:29','','1');

/*Table structure for table `job_spool_record` */

DROP TABLE IF EXISTS `job_spool_record`;

CREATE TABLE `job_spool_record` (
  `id` char(32) NOT NULL,
  `task_id` varchar(50) DEFAULT NULL,
  `spool_worker` varchar(32) NOT NULL,
  `work_class` varchar(10) NOT NULL,
  `machine_code` varchar(50) DEFAULT NULL,
  `variety` varchar(50) NOT NULL,
  `factory_name` varchar(50) DEFAULT NULL,
  `shoe` varchar(50) DEFAULT NULL,
  `weight` varchar(20) NOT NULL,
  `number` bigint(20) NOT NULL,
  `weakness` varchar(200) DEFAULT NULL,
  `checker` varchar(32) DEFAULT NULL,
  `change_duty` varchar(20) DEFAULT NULL,
  `change_duty_worker` varchar(32) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `del_flag` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `job_spool_record` */

/*Table structure for table `job_submit` */

DROP TABLE IF EXISTS `job_submit`;

CREATE TABLE `job_submit` (
  `id` char(32) NOT NULL,
  `erp_bill_no` varchar(64) DEFAULT NULL COMMENT '订单号',
  `task_id` varchar(50) NOT NULL COMMENT '任务编号',
  `parent_process` varchar(50) DEFAULT NULL COMMENT '上级工序',
  `task_kind` varchar(50) NOT NULL COMMENT '任务类型（子工序）',
  `task_mark` varchar(10) DEFAULT NULL COMMENT '任务标识',
  `worker` varchar(32) NOT NULL COMMENT '工人',
  `work_type` varchar(50) DEFAULT NULL COMMENT '工种',
  `workshop` varchar(50) NOT NULL COMMENT '车间',
  `work_class` varchar(10) NOT NULL COMMENT '班次',
  `work_team` varchar(50) DEFAULT NULL COMMENT '班组',
  `material` varchar(50) NOT NULL COMMENT '物料编码',
  `material_name` varchar(200) DEFAULT NULL COMMENT '物料名称',
  `number` decimal(20,2) NOT NULL COMMENT '数量',
  `unit` varchar(10) NOT NULL COMMENT '单位',
  `use_time` varchar(32) DEFAULT '8' COMMENT '生产时间',
  `checker` varchar(32) DEFAULT NULL,
  `check_status` tinyint(4) NOT NULL,
  `submit_time` datetime NOT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `del_flag` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `job_submit` */

insert  into `job_submit`(`id`,`erp_bill_no`,`task_id`,`parent_process`,`task_kind`,`task_mark`,`worker`,`work_type`,`workshop`,`work_class`,`work_team`,`material`,`material_name`,`number`,`unit`,`use_time`,`checker`,`check_status`,`submit_time`,`status`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) values ('0087f3f5bb6040aa97d7b55090109623','61_*_*','61_*_*-02-05-01','70','JP13','0','E2174','GZ23010501','C13','W01','C1303','B040107322001','布轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','120.00','U004','8','fangyanxia',1,'2018-07-25 09:37:06',1,'fangyanxia','2018-07-25 09:37:06',NULL,'2018-07-25 09:37:59','','0'),('00ff7268f577468d90500617b85b99e5','192_*_*','192_*_*-01-02-01','70','JP13','0','E785','11010508','C12','W01','C1201','B040102024075',NULL,'11.00','U005','8','值班长A',0,'2018-04-10 11:02:21',0,'fanjinheng','2018-03-16 15:37:55',NULL,'2018-03-16 15:37:55','','0'),('0166589e3b904892beb1d186cdde5290','S1037_1_1','S1037_1_1-09-01-01','13','JP17','0','E857','14010406','C14','W01',NULL,'C070320320045',NULL,'2.00','U004','8','值班长A',1,'2018-04-10 11:02:24',1,NULL,'2018-01-25 16:13:31',NULL,'2018-01-25 17:07:36','','0'),('02ab77ab508d4468bb408a3e8f908b6c','42_*_*','42_*_*-05-01-01','10','JP02','0','E13','GZ11010508','C11','W01','C1101','B010104060001','经轴-LF60*LF60 173*156 118&quot;XY60245','10.00','U002','8','yujianhua',0,'2018-08-08 21:23:48',0,'yujianhua','2018-08-08 21:23:48',NULL,'2018-08-08 21:23:48','','0'),('0325873714e24280be4ed2429b946ceb','C1070_1_1','C1070_1_1-31-01-01','13','JP15','0','E221','GZ14010403','C14','W04','C1404','C073223220012','坯布-CVC32/2*32/2 100*53 63&quot;2/1碳黑1.06镶嵌CS18-14-1','20.00','U001','8','zhaohuimin',1,'2018-08-09 22:20:37',1,'zhaohuimin','2018-08-09 22:20:37',NULL,'2018-08-09 22:22:39','','0'),('041f4eaa77a4406ca99011233fa65475','36_*_*','36_*_*-02-01-01','10','JP03','0','E6043','GZ11010504','C11','W01','C1101','B010109525001','经轴-LF60*LF60 173*130 106&quot;小叶子','500.00','U001','8','fengdongyan',1,'2018-06-04 14:40:01',1,'fengdongyan','2018-06-04 14:40:01',NULL,'2018-06-04 14:42:28','','0'),('0439c0b39f5f4ae092a046e1b6f50609','S1037_2_1','S1037_2_1-01-03-01','13','JP14','0','E2172,E6109','14010404','C14','W04','C1401','C020240240075',NULL,'200.00','U001','8','值班长A',1,'2018-04-10 11:02:29',1,'admin','2018-03-19 15:11:58',NULL,'2018-03-19 15:13:43','','0'),('04ebf027dcd14335b96e8f57425d4e7e','30_*_*','30_*_*-01-01-01','40','JP07','0','E6028','11010501','C11','W01','C1101','B020302030001','浆轴-T/JC（30+30）&times;20   132&times;62   63&rdquo; 双经布（双幅）','192.00','U005','8','chenjiping',0,'2018-06-07 14:42:56',0,'chenjiping','2018-06-07 14:42:56',NULL,'2018-06-07 14:42:56','','0'),('05c3ed9ca3514d4fba20a4a05cec88e6','S1037_1_1','S1037_1_1-04-02-01','13','JP14','0','E884','14010404','C14','W01',NULL,'C070320320045',NULL,'300.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-23 21:04:03',NULL,'2018-01-23 21:12:10','','0'),('05de1a1cdadc4d87ba3c0a4a9316cd81','S1037_1_1','S1037_1_1-08-01-01','13','JP16','0','E1110','14010402','C14','W01',NULL,'C070320320045',NULL,'100.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-24 16:39:56',NULL,'2018-01-24 16:40:33','','0'),('05fb0ae385f4412cb6c13c455bfcfd1f','S1037_1_1','S1037_1_1-08-01-01','13','JP16','0','E1110','14010402','C14','W01',NULL,'C070320320045',NULL,'100.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-24 16:46:28',NULL,'2018-01-24 16:49:57','','0'),('066b033dbc264b368ca678143b6762ac','S1037_1_1','S1037_1_1-09-01-01','13','JP17','0','E857','14010406','C14','W01',NULL,'C070320320045',NULL,'2.00','U004','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-25 16:13:17',NULL,'2018-01-25 17:07:36','','0'),('080c1eff572946c98eb48e6e32ce80a1','189_*_*','189_*_*-01-02-01','70','JP13','0','E2960','23010501','C12','W01',NULL,'B040107032045',NULL,'200.00','U005','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-22 16:42:55',NULL,'2018-01-22 16:43:03','','0'),('09f708f1d75b4e8ab0cd6fd3d2c2853d','S1037_1_1','S1037_1_1-08-01-01','13','JP16','0','E1110','14010402','C14','W01',NULL,'C070320320045',NULL,'200.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-24 16:59:28',NULL,'2018-01-24 23:35:16','','0'),('0a2c0d9395c34877b5788912058211cd','S1037_1_1','S1037_1_1-07-01-01','16','JP18','0','E857','14010409','C14','W01',NULL,'C070320320045',NULL,'100.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-25 14:55:28',NULL,'2018-01-25 17:06:53','','0'),('0a97403fe18b40ffb2a03d80e60f0e9d','189_*_*','189_*_*-01-03-01','70','JP13','0','E2960','23010501','C12','W01',NULL,'B040107032045',NULL,'200.00','U005','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-22 16:34:52',NULL,'2018-01-22 16:38:04','','0'),('0aad753f4afc4c33834d0dc4c6900cb2','S1037_1_1','S1037_1_1-08-02-01','13','JP16','0','E884','14010402','C14','W01',NULL,'C070320320045',NULL,'300.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-23 21:17:00',NULL,'2018-01-23 21:27:28','','0'),('0ab9d7d046e7485c936349f4db07511c','280_*_*','280_*_*-T-04','50','JP09','1','E13','GZ30000073','C11','W01','C1101','B020101021001',NULL,'2221.00','U005','8','yujianhua',1,'2018-04-10 11:08:41',1,'yujianhua','2018-04-10 10:22:30',NULL,'2018-04-10 16:01:16','','0'),('0ac865a9aedc4578bd1412be6ac3558a','S1037_2_1','S1037_2_1-03-01-01','13','JP16','0','E4947','14010402','C14','W01',NULL,'C020240240075',NULL,'300.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-23 16:24:49',NULL,'2018-01-23 16:25:25','','0'),('0ae8b961e8274939b0e64de416c75d0c','61_*_*','61_*_*-02-02-01','70','JP13','0','E760','GZ11010508','C13','W01','C1303','B040107322001','布轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','6.00','U005','8','liuhongyi',2,'2018-07-25 11:04:30',1,'liuhongyi','2018-07-25 11:04:30',NULL,'2018-07-25 11:07:53','','0'),('0afa3892b9d24e2ab10138b0389cb917','S1037_1_1','S1037_1_1-08-01-01','13','JP16','0','E1110','14010402','C14','W01',NULL,'C070320320045',NULL,'100.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-24 16:39:58',NULL,'2018-01-24 16:40:34','','0'),('0d79bce5add548c48dd9a816a6d7359a','198_*_*','198_*_*-01-04-01','10','JP01','0','E13','00010301','C11','W01',NULL,'B010102024075',NULL,'500.00','U005','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-18 21:05:27',NULL,'2018-01-23 20:53:19','','0'),('0d7eb77ddbdd4e90a242a7372588f3c3','198_*_*','198_*_*-01-02-01','10','JP02','0','E6','11010505','C11','W01',NULL,'B010102024075',NULL,'50.00','U002','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-18 16:38:39',NULL,'2018-01-18 16:39:44','','0'),('0dd81eacc1a84f81be46987e6397469e','S1037_1_1','S1037_1_1-07-02-01','16','JP18','0','E233','14010409','C14','W01',NULL,'C070320320045',NULL,'100.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-23 21:54:24',NULL,'2018-01-24 10:31:12','','0'),('0f464d9fd4114a238015cd24f92595d8','S1037_1_1','S1037_1_1-05-01-01','13','JP14','0','E798,E817,E233,E884,E1110,E857','14010404','C14','W01',NULL,'C070320320045',NULL,'20.00','U004','8','值班长A',2,'2018-04-10 11:07:13',1,NULL,'2018-01-24 21:24:31',NULL,'2018-01-25 17:30:30','','0'),('0fb80238625041cb8cce48b6493d223a','34_*_*','34_*_*-02-02-01','70','JP12','0','E1471','GZ23010505','C12','W01','C1202','B040509525001','布轴-LF60*LF60 173*130 106&quot;小叶子','6.00','U005','8','qianlonglong',1,'2018-06-14 10:26:14',1,'qianlonglong','2018-06-14 10:26:14',NULL,'2018-06-14 10:29:51','','0'),('152d76dd286c4631a36d4139035528d7','C1070_1_1','C1070_1_1-30-01-01','13','JP14','0','E220','GZ14010404','C14','W04','C1404','C073223220012','坯布-CVC32/2*32/2 100*53 63&quot;2/1碳黑1.06镶嵌CS18-14-1','70.00','U001','8','admin',1,'2018-08-09 21:58:14',1,'admin','2018-08-09 21:58:14',NULL,'2018-08-09 22:22:45','','0'),('1531bb33bacc42dc8362a41d11f341e9','198_*_*','198_*_*-01-05-01','10','JP03','0','E6043','11010504','C11','W01',NULL,'B010102024075',NULL,'999.00','U005','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-18 16:47:11',NULL,'2018-01-18 16:47:55','','0'),('160c01717741487087baaf622c56444d','210_*_*','210_*_*-01-01-01','10','JP01','0','E13','00010301','C11','W01','C1101','B010101021001',NULL,'100.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-02-06 20:51:10',NULL,'2018-02-06 21:06:19','','0'),('19575fa48efb4bee86a28968dd08e248','210_*_*','210_*_*-03-03-02','10','JP03','0','E2546','11010504','C11','W02',NULL,'B010101021001',NULL,'70.00','U001','8','值班长A',0,'2018-04-10 11:07:13',0,NULL,'2018-02-06 10:06:51',NULL,'2018-02-06 10:07:04','','0'),('1da95fa8bf0c459ebdbddf84581ef277','198_*_*','198_*_*-01-01-01','10','JP01','0','E13','00010301','C11','W01',NULL,'B010102024075',NULL,'50.00','U002','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-18 16:15:59',NULL,'2018-01-18 16:16:42','','0'),('1dc94bb036634e27b588d610375c1bc3','S1037_1_1','S1037_1_1-07-02-01','16','JP18','0','E233','14010409','C14','W01',NULL,'C070320320045',NULL,'300.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-23 21:54:39',NULL,'2018-01-24 15:46:17','','0'),('1f73d1a61bd047cc93e4a5f53ee26ad3','S1037_1_1','S1037_1_1-08-01-01','13','JP16','0','E1110','14010402','C14','W01',NULL,'C070320320045',NULL,'200.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-24 16:46:36',NULL,'2018-01-24 16:51:21','','0'),('1f74fbb86f8646168781a231303e1caf','S1037_1_1','S1037_1_1-06-01-01','13','JP15','0','E798','14010403','C14','W01',NULL,'C070320320045',NULL,'300.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-24 16:05:12',NULL,'2018-01-24 16:05:38','','0'),('208b81cfd72f46178f5f74424c345a57','198_*_*','198_*_*-T-34','50','JP09','1','E13','11010512','C11','W01','C1101','B010102024075',NULL,'18.00','U005','8','值班长A',2,'2018-04-10 11:08:41',1,'yujianhua','2018-03-15 22:05:46',NULL,'2018-03-15 22:06:32','','0'),('215253856a1b4457962edddd251f3977','S1037_1_1','S1037_1_1-05-01-01','13','JP14','0','E798,E817,E233,E884,E1110,E857','14010404','C14','W01',NULL,'C070320320045',NULL,'20.00','U004','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-24 21:23:30',NULL,'2018-01-25 17:30:51','','0'),('25a26c2dc90948f1bdba33f462bdf3d5','S1037_1_1','S1037_1_1-07-02-01','16','JP18','0','E233','14010409','C14','W01',NULL,'C070320320045',NULL,'300.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-23 21:54:46',NULL,'2018-01-24 11:00:46','','0'),('25d83b3e0b414dd2a9aad751c6f6a0ed','210_*_*','210_*_*-01-02-01','10','JP01','0','E13','00010301','C11','W01','C1101','B010101021001',NULL,'654.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-03-01 21:56:00',NULL,'2018-03-05 09:43:37','','0'),('2671450077c743e3a621de3bb8f9567d','210_*_*','210_*_*-01-01-01','10','JP01','0','E13','00010301','C11','W01','C1101','B010101021001',NULL,'100.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-02-07 14:20:14',NULL,'2018-02-07 14:34:30','','0'),('2b2221a929014ae0a4364d07c5d529eb','189_*_*','189_*_*-03-01-01','70','JP13','0','E2716','23010501','C12','W01',NULL,'B040107032045',NULL,'100.00','U005','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-30 14:13:12',NULL,'2018-01-30 14:13:39','','0'),('2dc6303d735d481aaf26ddf445e27494','S1037_2_1','S1037_2_1-11-01-01','16','JP18','0','E3934','14010409','C14','W04',NULL,'C020240240075',NULL,'56.00','U004','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-19 17:15:20',NULL,'2018-01-19 17:15:32','','0'),('2df1bcbb9fa44be28f24e9b05ead7cb9','190_*_*','190_*_*-01-01-01','40','JP07','0','E1302','11010501','C11','W01',NULL,'B020107032045',NULL,'1002.00','U005','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-20 14:51:15',NULL,'2018-01-20 15:51:27','','0'),('2e57ac2afe944c32aeee68476f34f311','S1037_1_1','S1037_1_1-09-02-01','13','JP17','0','E857','14010406','C14','W01',NULL,'C070320320045',NULL,'20.00','U004','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-24 21:26:50',NULL,'2018-01-25 17:07:58','','0'),('2f3f1427aa2e450ab791916c9dfa047c','189_*_*','189_*_*-01-05-01','70','JP13','0','E2960','23010501','C12','W01',NULL,'B040107032045',NULL,'200.00','U005','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-22 16:33:30',NULL,'2018-01-22 16:33:53','','0'),('308a5dbb1a634c05b2cff83501329d50','S1037_1_1','S1037_1_1-09-01-01','13','JP17','0','E857','14010406','C14','W01',NULL,'C070320320045',NULL,'1.00','U010','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-25 16:16:51',NULL,'2018-01-25 17:07:35','','0'),('317e9fa2605c49598df67c6ae815d404','42_*_*','42_*_*-05-01-01','10','JP02','0','E13','GZ11010508','C11','W01','C1101','B010104060001','经轴-LF60*LF60 173*156 118&quot;XY60245','1.00','U008','8','yujianhua',0,'2018-08-08 21:25:02',0,'yujianhua','2018-08-08 21:25:02',NULL,'2018-08-08 21:25:02','1','0'),('32c3b821729548dd8ef8b3808fb10f4f','210_*_*','210_*_*-01-01-01','10','JP01','0','E13','00010301','C11','W01',NULL,'B010101021001',NULL,'200.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-02-06 16:12:28',NULL,'2018-02-06 16:17:53','','0'),('33bd6d0361054bcdb3aec031dc1a4205','S1037_1_1','S1037_1_1-07-02-01','16','JP18','0','E233','14010409','C14','W01',NULL,'C070320320045',NULL,'200.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-23 21:54:35',NULL,'2018-01-24 10:47:40','','0'),('35292ce1041e40abaa3d076006b96575','S1037_1_1','S1037_1_1-09-02-01','13','JP17','0','E857','14010406','C14','W01',NULL,'C070320320045',NULL,'100.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-25 14:56:54',NULL,'2018-01-25 17:07:58','','0'),('356af37284ec497580b52e9d035a34c3','S1037_1_1','S1037_1_1-06-01-01','13','JP15','0','E798','14010403','C14','W01',NULL,'C070320320045',NULL,'300.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-24 16:05:13',NULL,'2018-01-24 16:05:37','','0'),('3573307fd976454ebc4a0e78287dbdf7','42_*_*','42_*_*-04-01-01','10','JP01','0','E13','GZ00010301','C11','W01','C1101','B010104060001','经轴-LF60*LF60 173*156 118&quot;XY60245','60.00','U002','8','yujianhua',0,'2018-08-02 16:16:48',0,'yujianhua','2018-08-02 16:16:48',NULL,'2018-08-02 16:16:48','','0'),('3623fc8f4f2e4a1e980905c5f335528d','S1037_1_1','S1037_1_1-06-01-01','13','JP15','0','E798','14010403','C14','W01',NULL,'C070320320045',NULL,'200.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-24 16:05:07',NULL,'2018-01-24 16:05:40','','0'),('37357ee36c274cc8b5e8c9e6e818682b','S1037_1_1','S1037_1_1-08-02-01','13','JP16','0','E884','14010402','C14','W01',NULL,'C070320320045',NULL,'100.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-23 21:17:04',NULL,'2018-01-23 21:18:23','','0'),('374526ebfe824dc98614dfdefc0cda4b','39_*_*','39_*_*-01-01-01','10','JP03','0','E661','GZ11010504','C11','W01','C1101','B010109604002','经轴-LF60*LF60 173*156 118&quot;XC60319','137718.00','U001','8','jiaqiuju',1,'2018-06-07 14:39:50',1,'jiaqiuju','2018-06-07 14:39:50',NULL,'2018-06-07 14:41:28','','0'),('38413d54e6954e7eb9beaa023038c303','S1037_1_1','S1037_1_1-13-01-01','13','JP15','0','E1026','14010403','C14','W01',NULL,'C070320320045',NULL,'100.00','U005','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-02-01 15:13:26',NULL,'2018-02-01 15:28:45','','0'),('3b7f71d191eb408c94c67a75640e93f2','S1037_1_1','S1037_1_1-08-01-01','13','JP16','0','E1110','14010402','C14','W01',NULL,'C070320320045',NULL,'300.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-24 16:26:58',NULL,'2018-01-24 16:36:05','','0'),('3ba7035e53994a58aa15d1c222d1ac37','192_*_*','192_*_*-01-01-01','70','JP13','0','E3649','23010501','C12','W01',NULL,'B040102024075',NULL,'101.00','U005','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-18 19:42:29',NULL,'2018-01-18 19:43:38','','0'),('4051307156694439aad279015c45a37d','61_*_*','61_*_*-02-02-01','70','JP13','0','E2220','GZ23010501','C13','W01','C1303','B040107322001','布轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','6.00','U005','8','xulili',1,'2018-07-25 11:11:19',1,'xulili','2018-07-25 11:11:19',NULL,'2018-07-25 11:11:35','','0'),('40cde097fd074cec881e36c3ed1b8491','209_*_*','209_*_*-01-05-01','30','JP05','0','E13','11010503','C11','W01','C1101','B020101021001',NULL,'55.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-03-01 15:47:03',NULL,'2018-03-15 00:06:26','','0'),('41bf4fe237af4bc1883f53f426ca4387','S1037_1_1','S1037_1_1-08-01-01','13','JP16','0','E1110','14010402','C14','W01',NULL,'C070320320045',NULL,'300.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-24 16:40:08',NULL,'2018-01-24 16:43:10','','0'),('41ce3bcf2b0440cc817dd2503cc67a20','210_*_*','210_*_*-01-04-01','10','JP01','0','E13','00010301','C11','W01','C1101','B010101021001',NULL,'200.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-02-07 15:22:29',NULL,'2018-02-07 15:22:59','','0'),('42e9089502ce45238d04dfebafb0d432','S1037_1_1','S1037_1_1-09-02-01','13','JP17','0','E857','14010406','C14','W01',NULL,'C070320320045',NULL,'100.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-25 14:56:05',NULL,'2018-01-25 17:07:58','','0'),('43d0753086b74338a4d2de4329b06383','S1037_1_1','S1037_1_1-08-01-01','13','JP16','0','E1110','14010402','C14','W01',NULL,'C070320320045',NULL,'100.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-24 16:59:23',NULL,'2018-01-24 17:00:01','','0'),('450ede1e2ba145b0abd6d9a040bb7a1c','198_*_*','198_*_*-01-02-01','10','JP03','0','E6043','11010504','C11','W01',NULL,'B010102024075',NULL,'1002.00','U005','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-18 16:38:17',NULL,'2018-01-18 16:39:48','','0'),('4560366da4b54e9596fbc11ba81ae998','36_*_*','36_*_*-02-01-01','10','JP03','0','E6043','GZ11010504','C11','W01','C1101','B010109525001','经轴-LF60*LF60 173*130 106&quot;小叶子','500.00','U001','8','fengdongyan',1,'2018-06-04 14:36:28',1,'fengdongyan','2018-06-04 14:36:28',NULL,'2018-06-04 14:42:55','','0'),('45f4501d298f4747a71c8df9f7df74fc','S1037_2_1','S1037_2_1-12-01-01','16','JP18','0','E223,E809,E4917,E4944','14010409','C14','W05',NULL,'C020240240075',NULL,'11.00','U004','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-19 18:15:59',NULL,'2018-01-19 18:16:14','','0'),('485a64f9ff9b418898e17d3895dd9599','198_*_*','198_*_*-01-02-01','10','JP03','0','E4563,E3610','11010508','C11','W01',NULL,'B010102024075',NULL,'1002.00','U005','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-18 16:39:08',NULL,'2018-01-18 16:39:28','','0'),('48e77fc3b59b48528a8be424f30d54ce','S1037_2_1','S1037_2_1-01-03-01','13','JP14','0','E4947','14010404','C14','W01',NULL,'C020240240075',NULL,'200.00','U005','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-23 16:29:28',NULL,'2018-01-23 16:29:49','','0'),('4a7a9a303c9c44768b3eb768cdee7649','192_*_*','192_*_*-01-03-01','70','JP13','0','E3649','23010501','C12','W01',NULL,'B040102024075',NULL,'100.00','U005','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-18 19:43:22',NULL,'2018-01-18 19:43:28','','0'),('4a7c5b3243e9421a97c65eed14160a5f','192_*_*','192_*_*-01-02-01','70','JP13','0','E785','11010508','C12','W01','C1201','B040102024075',NULL,'1000.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-02-08 19:08:34',NULL,'2018-02-08 19:08:58','','0'),('4aa1282929994a2bb049a00f678e8aca','280_*_*','280_*_*-04-01-01','40','JP07','0','E13','11010501','C11','W01','C1101','B020101021001','浆轴-OE21*16 128*60 63&quot;纱卡-190','223.00','U001','8','yujianhua',1,'2018-04-10 11:07:13',1,'yujianhua','2018-04-09 22:15:29',NULL,'2018-04-10 16:06:35','','0'),('4ad1219c22124cdbb92d4a36f9d0af81','S1037_1_1','S1037_1_1-06-01-01','13','JP15','0','E798','14010403','C14','W01',NULL,'C070320320045',NULL,'20.00','U004','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-24 21:25:03',NULL,'2018-01-25 17:29:04','','0'),('4b3bf8d3290548ce871a1a5b09d28c83','S1037_1_1','S1037_1_1-08-01-01','13','JP16','0','E1110','14010402','C14','W01',NULL,'C070320320045',NULL,'100.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-24 16:59:24',NULL,'2018-01-24 17:01:20','','0'),('4dbcd1bfa1154be7af46b6caf02c0101','30_*_*','30_*_*-03-01-01','40','JP07','0','E2854','GZ11010508','C11','W01','C1101','B020302030001','浆轴-T/JC（30+30）&times;20   132&times;62   63&rdquo; 双经布（双幅）','500.00','U001','8','quxiumei',1,'2018-06-04 15:35:05',1,'quxiumei','2018-06-04 15:35:05',NULL,'2018-06-04 15:53:04','','0'),('4eecee41d4c145bc941a7b6542e25269','34_*_*','34_*_*-02-01-03','70','JP13','0','E1738','GZ11010508','C12','W03','C1201','B040509525001','布轴-LF60*LF60 173*130 106&quot;小叶子','3.00','U005','8','qilijun',1,'2018-06-14 14:55:30',1,'qilijun','2018-06-14 14:55:30',NULL,'2018-06-14 14:56:07','','0'),('52ddd505835e46108f8e7a463778067c','191_*_*','191_*_*-01-02-01','10','JP03','0','E2509','11010504','C11','W01',NULL,'B010107032045',NULL,'510.00','U005','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-20 15:11:25',NULL,'2018-01-20 15:11:33','','0'),('52e8cf16ee1742309f81277205a9db4f','210_*_*','210_*_*-01-01-01','10','JP01','0','E13','00010301','C11','W01','C1101','B010101021001',NULL,'50.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-02-07 17:17:02',NULL,'2018-03-05 09:43:37','','0'),('530baf55868b45448655373cfdddcc9e','198_*_*','198_*_*-01-03-01','10','JP03','0','E6043','11010504','C11','W01',NULL,'B010102024075',NULL,'1003.00','U005','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-18 16:47:38',NULL,'2018-01-18 16:47:49','','0'),('5372cee48cf94d53bf5c8b2014577712','S1037_1_1','S1037_1_1-08-01-01','13','JP16','0','E1110','14010402','C14','W01',NULL,'C070320320045',NULL,'300.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-24 16:09:09',NULL,'2018-01-24 16:10:14','','0'),('53ca641fc1db4e85a5630ab3b064757c','198_*_*','198_*_*-T-22','50','JP09','1','E13','11010512','C11','W01','C1101','B010102024075',NULL,'100.00','U001','8','值班长A',0,'2018-04-10 11:08:41',0,'yujianhua','2018-03-15 14:42:51',NULL,'2018-03-15 14:42:51','','0'),('556edce76cf247238fb15be2f0829047','193_*_*','193_*_*-01-01-01','40','JP07','0','E28','11010501','C11','W01',NULL,'B020102024075',NULL,'302.00','U005','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-18 18:31:13',NULL,'2018-01-18 18:31:42','','0'),('55922d7aec4c4917a69429d376d681e3','S1037_2_1','S1037_2_1-01-03-01','13','JP14','0','E4947','14010404','C14','W01',NULL,'C020240240075',NULL,'200.00','U005','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-23 16:29:32',NULL,'2018-01-23 16:29:44','','0'),('562324b2083b4ffd9e837020fb1ded81','281_*_*','281_*_*-01-03-01','10','JP01','0','E13','00010301','C11','W01','C1101','B010101021001','经轴-OE21*16 128*60 63','1203.00','U001','8','yujianhua',1,'2018-04-10 11:07:13',1,'yujianhua','2018-04-09 20:35:41',NULL,'2018-04-10 10:32:45','','0'),('586f7f2f3e564c6eb514405c70c3c572','61_*_*','61_*_*-02-03-01','70','JP13','0','E2220','GZ23010501','C13','W01','C1303','B040107322001','布轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','6.00','U005','8','xulili',1,'2018-07-25 11:12:18',1,'xulili','2018-07-25 11:12:18',NULL,'2018-07-25 11:12:28','','0'),('590938ab2e8640d3a155da1abf195a97','S1037_1_1','S1037_1_1-06-01-01','13','JP15','0','E798','14010403','C14','W01',NULL,'C070320320045',NULL,'100.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-24 15:51:08',NULL,'2018-01-24 15:52:45','','0'),('59f9c667798b430a86dbf6e59a66bf1d','S1037_1_1','S1037_1_1-08-01-01','13','JP16','0','E1110','14010402','C14','W01',NULL,'C070320320045',NULL,'200.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-24 16:09:02',NULL,'2018-01-24 16:10:15','','0'),('5ca4f8951cd2467dbda75b4a05420e3e','S1037_1_1','S1037_1_1-08-01-01','13','JP16','0','E1110','14010402','C14','W01',NULL,'C070320320045',NULL,'200.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-24 16:09:00',NULL,'2018-01-24 16:10:15','','0'),('5cece2160f384f84a1d446cb2c328722','38_*_*','38_*_*-02-01-01','40','JP07','0','E1202','GZ11010501','C11','W01','C1101','B020409604002','浆轴-LF60*LF60 173*156 118&quot;XC60319','324.00','U005','8','jiaozhiguang',1,'2018-06-07 15:17:08',1,'jiaozhiguang','2018-06-07 15:17:08',NULL,'2018-06-07 15:17:46','','0'),('5df5c9e8a7154769b19b5c3b9df4d011','S1037_1_1','S1037_1_1-08-01-01','13','JP16','0','E1110','14010402','C14','W01',NULL,'C070320320045',NULL,'300.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-24 16:40:10',NULL,'2018-01-24 16:43:11','','0'),('5ed3fdd55cd542dba181a9e3f5179172','210_*_*','210_*_*-01-02-01','10','JP01','0','E13','00010301','C11','W01','C1101','B010101021001',NULL,'222.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,'yujianhua','2018-03-01 22:12:56',NULL,'2018-03-05 09:43:37','','0'),('60bfd6f4988741efba9acf2c5ab7758e','42_*_*','42_*_*-04-01-01','10','JP01','0','E13','GZ00010301','C11','W01','C1101','B010104060001','经轴-LF60*LF60 173*156 118&quot;XY60245','60.00','U008','8','yujianhua',0,'2018-08-08 21:20:32',0,'yujianhua','2018-08-08 21:20:32',NULL,'2018-08-08 21:20:32','','0'),('632e87c27a7e4a7fbee0b393c3943f5a','42_*_*','42_*_*-04-01-01','10','JP02','0','E3610','GZ11010508','C11','W01','C1101','B010104060001','经轴-LF60*LF60 173*156 118&quot;XY60245','5000.00','U001','8','huozhihu',0,'2018-08-02 16:29:03',0,'huozhihu','2018-08-02 16:29:03',NULL,'2018-08-02 16:29:03','','0'),('6410e38a636047d39d227d6ae5d8fc76','S1037_1_1','S1037_1_1-08-01-01','13','JP16','0','E1110','14010402','C14','W01',NULL,'C070320320045',NULL,'200.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-24 16:26:53',NULL,'2018-01-24 16:36:04','','0'),('65a20267bc7647f8b06e29122b0d9beb','S1037_1_1','S1037_1_1-04-02-01','13','JP14','0','E884','14010404','C14','W01',NULL,'C070320320045',NULL,'200.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-23 21:03:56',NULL,'2018-01-23 21:08:38','','0'),('65a26afd75374c1086c175a37c25fb31','210_*_*','210_*_*-01-01-01','10','JP01','0','E13','00010301','C11','W01','C1101','B010101021001',NULL,'200.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-02-07 14:20:23',NULL,'2018-02-07 14:34:30','','0'),('65f3620459ae43f1805cc14e1b8863f6','30_*_*','30_*_*-01-01-01','40','JP07','0','E6028','11010501','C11','W01','C1101','B020302030001','浆轴-T/JC（30+30）&times;20   132&times;62   63&rdquo; 双经布（双幅）','192.00','U005','8','chenjiping',0,'2018-05-24 13:48:15',0,'chenjiping','2018-05-24 13:48:15',NULL,'2018-05-24 13:48:15','','0'),('666fe04b77344231ba7d43bd43f6dbf7','198_*_*','198_*_*-T-22','50','JP09','1','E13','11010512','C11','W01','C1101','B010102024075',NULL,'33.00','U001','8','值班长A',1,'2018-04-10 11:08:41',1,'yujianhua','2018-03-14 23:13:46',NULL,'2018-03-15 10:49:50','','0'),('66aaf6e03a1749b5b9f7e058ca63d07d','34_*_*','34_*_*-02-02-01','70','JP13','0','E790','GZ23010501','C12','W01','C1202','B040509525001','布轴-LF60*LF60 173*130 106&quot;小叶子','35.00','U004','8','zhanglimin',1,'2018-06-05 15:31:14',1,'zhanglimin','2018-06-05 15:31:14',NULL,'2018-06-14 10:34:44','','0'),('6841e171cfcd49d1b4f6d10c667abcba','210_*_*','210_*_*-01-01-01','10','JP01','0','E13','00010301','C11','W01','C1101','B010101021001',NULL,'100.00','U005','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-02-07 14:27:30',NULL,'2018-02-07 14:34:30','','0'),('68c6516fcade4bef8b177b0d6761368a','189_*_*','189_*_*-01-04-01','70','JP13','0','E2960','23010501','C12','W01',NULL,'B040107032045',NULL,'200.00','U005','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-22 16:42:00',NULL,'2018-01-22 16:42:09','','0'),('6a89b05c486541e68c30565a2ea40930','209_*_*','209_*_*-01-05-01','40','JP07','0','E13','11010508','C11','W01','C1101','B020101021001',NULL,'30.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-03-01 15:43:01',NULL,'2018-03-15 14:52:41','','0'),('6c5905f0c5054481b00bd309a02e44b9','210_*_*','210_*_*-01-01-01','10','JP01','0','E13','00010301','C11','W01',NULL,'B010101021001',NULL,'100.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-02-06 16:12:21',NULL,'2018-02-06 16:17:53','','0'),('6c7dac03f15e4de08f10ddce8f781ce5','S1037_1_1','S1037_1_1-06-01-01','13','JP15','0','E798','14010403','C14','W01',NULL,'C070320320045',NULL,'20.00','U004','8','值班长A',2,'2018-04-10 11:07:13',1,NULL,'2018-01-24 21:25:21',NULL,'2018-01-25 17:27:38','','0'),('7092d6f89f7f41f994b3a002a6d0bfe0','209_*_*','209_*_*-01-05-01','40','JP07','0','E13','11010508','C11','W01','C1101','B020101021001',NULL,'25.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-03-01 15:41:14',NULL,'2018-03-15 14:52:41','','0'),('71063b86703d49868b7fa5485744bf88','190_*_*','190_*_*-01-02-01','40','JP07','0','E1302','11010501','C11','W01',NULL,'B020107032045',NULL,'1000.00','U005','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-20 14:50:47',NULL,'2018-01-20 15:51:32','','0'),('7149368f75d7467fb30d017279b87599','S1037_1_1','S1037_1_1-08-01-01','13','JP16','0','E1110','14010402','C14','W01',NULL,'C070320320045',NULL,'200.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-24 16:46:38',NULL,'2018-01-24 16:51:23','','0'),('7168552a29e34f749f56c191e5e866fa','S1037_1_1','S1037_1_1-08-01-01','13','JP16','0','E1110','14010402','C14','W01',NULL,'C070320320045',NULL,'100.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-24 16:08:54',NULL,'2018-01-24 16:10:45','','0'),('71acd090d2974cf08da16a51ef74e9a9','61_*_*','61_*_*-02-04-01','70','JP13','0','E2174','GZ23010501','C13','W01','C1303','B040107322001','布轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','120.00','U004','8','fangyanxia',1,'2018-07-25 10:49:55',1,'fangyanxia','2018-07-25 10:49:55',NULL,'2018-07-25 10:50:29','','0'),('765ffb8cbdb84cfcb2e1afd878e0be3d','210_*_*','210_*_*-01-01-01','10','JP01','0','E13','00010301','C11','W01','C1101','B010101021001',NULL,'200.00','U005','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-02-07 14:27:36',NULL,'2018-02-07 14:34:30','','0'),('76c29c47de8942419f0cc1cf5843150c','210_*_*','210_*_*-01-04-01','10','JP01','0','E13','00010301','C11','W01','C1101','B010101021001',NULL,'200.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-02-07 15:21:24',NULL,'2018-02-07 15:21:42','','0'),('78114ef1d67e461f9fd190cbcd67f8d4','198_*_*','198_*_*-T-22','50','JP09','1','E13','11010512','C11','W01','C1101','B010102024075',NULL,'55.00','U001','8','值班长A',0,'2018-04-10 11:08:41',0,'yujianhua','2018-03-15 14:42:46',NULL,'2018-03-15 14:42:46','','0'),('7989a7ec1e01430596f95a46256635eb','210_*_*','210_*_*-02-01-01','10','JP03','0','E13','11010504','C11','W01',NULL,'B010101021001',NULL,'200.00','U001','8','值班长A',2,'2018-04-10 11:07:13',1,NULL,'2018-02-02 09:54:31',NULL,'2018-02-02 09:54:55','','0'),('7b08194db0734299bccbf7468a668569','61_*_*','61_*_*-02-02-01','70','JP13','0','E760','GZ11010508','C13','W01','C1303','B040107322001','布轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','6.00','U005','8','liuhongyi',1,'2018-07-25 11:00:49',1,'liuhongyi','2018-07-25 11:00:49',NULL,'2018-07-25 11:08:04','','0'),('7c8d87d384404eb5a119a7a74b68b591','34_*_*','34_*_*-02-02-01','70','JP12','0','E1471','GZ23010505','C12','W01','C1202','B040509525001','布轴-LF60*LF60 173*130 106&quot;小叶子','6.00','U005','8','qianlonglong',1,'2018-06-14 10:40:09',1,'qianlonglong','2018-06-14 10:40:09',NULL,'2018-06-14 10:40:36','','0'),('7ce5589c54d24eda974d5112ba68d081','S1037_2_1','S1037_2_1-07-05-01','13','JP14','0','E3862','14010404','C14','W01',NULL,'C020240240075',NULL,'100.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-25 09:59:00',NULL,'2018-01-25 09:59:42','','0'),('7e374e9c84d5419b92f8ff38d6948ce3','210_*_*','210_*_*-01-04-01','10','JP01','0','E13','00010301','C11','W01','C1101','B010101021001',NULL,'200.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-02-07 15:22:26',NULL,'2018-02-07 15:22:59','','0'),('7f911625c3904b69a2bd9013888a8f81','S1037_1_1','S1037_1_1-07-02-01','16','JP18','0','E233','14010409','C14','W01',NULL,'C070320320045',NULL,'100.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-23 21:54:26',NULL,'2018-01-24 10:33:45','','0'),('7ff54a70d2a3411c9f6338b691ccdc6f','210_*_*','210_*_*-01-04-01','10','JP01','0','E13','00010301','C11','W01','C1101','B010101021001',NULL,'200.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-02-07 17:04:48',NULL,'2018-02-07 17:15:07','','0'),('80375cad3fa14c3c936ac643576e1a77','210_*_*','210_*_*-01-01-01','10','JP01','0','E13','00010301','C11','W01','C1101','B010101021001',NULL,'300.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-02-06 20:51:37',NULL,'2018-02-06 21:06:19','','0'),('8063678b9bf9443d9d32f4cb9c88faee','210_*_*','210_*_*-01-01-01','10','JP01','0','E13','00010301','C11','W01','C1101','B010101021001',NULL,'300.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-02-07 14:20:29',NULL,'2018-02-07 14:34:30','','0'),('82bb7ec8e2584d82ae510b9efee53cd3','28_*_*','28_*_*-04-01-01','70','JP13','0','E85','GZ11010508','C12','W01','C1204','B040302030001','布轴-T/JC（30+30）&times;20   132&times;62   63&rdquo; 双经布（双幅）','600.00','U001','8','zhangkuo',1,'2018-06-04 20:20:26',1,'zhangkuo','2018-06-04 20:20:26',NULL,'2018-06-04 20:20:35','','0'),('8317585fc90547cd99a593229999980a','S1037_1_1','S1037_1_1-08-01-01','13','JP16','0','E1110','14010402','C14','W01',NULL,'C070320320045',NULL,'46.00','U004','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-24 21:25:40',NULL,'2018-01-24 23:35:15','','0'),('83709486d7ec4f9c8e067ca4f364863e','S1037_2_1','S1037_2_1-01-03-01','13','JP14','0','E4947','14010404','C14','W01',NULL,'C020240240075',NULL,'100.00','U005','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-23 16:29:22',NULL,'2018-01-23 16:29:57','','0'),('8591c115d83b42d0bca9753af984f34f','S1037_1_1','S1037_1_1-06-01-01','13','JP15','0','E798','14010403','C14','W01',NULL,'C070320320045',NULL,'200.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-24 15:51:14',NULL,'2018-01-24 15:52:44','','0'),('8652f55f6dd045e49493db3e48d96668','S1037_1_1','S1037_1_1-04-02-01','13','JP14','0','E884','14010404','C14','W01',NULL,'C070320320045',NULL,'100.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-23 21:03:38',NULL,'2018-01-23 21:05:34','','0'),('865510b4407a4b0aa165cf3a86809e35','S1037_1_1','S1037_1_1-06-01-01','13','JP15','0','E798','14010403','C14','W01',NULL,'C070320320045',NULL,'300.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-24 15:51:21',NULL,'2018-01-24 15:51:56','','0'),('8665d98c9c43401bb0611dae29acd51b','S1037_2_1','S1037_2_1-03-01-01','13','JP16','0','E4947','14010402','C14','W01',NULL,'C020240240075',NULL,'200.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-23 16:24:34',NULL,'2018-01-23 16:26:19','','0'),('8751930dc4f64e55948e8dbb17a8455f','198_*_*','198_*_*-01-01-01','10','JP03','0','E6043','11010504','C11','W01',NULL,'B010102024075',NULL,'1001.00','U005','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-18 16:28:22',NULL,'2018-01-18 16:28:33','','0'),('8784a2e085e34c18950bf9dbaf03d68d','279_*_*','279_*_*-02-01-01','70','JP13','0','E785','23010501','C12','W01','C1201','B040101010116','布轴-OE10*OE10 40*36 63&quot;平纹-190','2202.00','U001','8','fanjinheng',0,'2018-04-10 11:07:13',0,'fanjinheng','2018-04-09 20:30:03',NULL,'2018-04-09 20:30:03','','0'),('89068750248b490a90293aed16f96ad4','S1037_1_1','S1037_1_1-08-02-01','13','JP16','0','E884','14010402','C14','W01',NULL,'C070320320045',NULL,'100.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-23 21:13:42',NULL,'2018-01-23 21:24:12','','0'),('895fb6859cab4a5dbe75db27314270a4','S1037_1_1','S1037_1_1-06-01-01','13','JP15','0','E798','14010403','C14','W01',NULL,'C070320320045',NULL,'300.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-24 15:51:20',NULL,'2018-01-24 15:52:41','','0'),('8a768e49868647598cec3113cf128148','S1037_2_1','S1037_2_1-01-03-01','13','JP14','0','E4947','14010404','C14','W01',NULL,'C020240240075',NULL,'200.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-22 23:09:54',NULL,'2018-01-23 20:44:58','','0'),('8bd770eb09ac4f00a2efc7aaf5417b43','S1037_1_1','S1037_1_1-08-01-01','13','JP16','0','E1110','14010402','C14','W01',NULL,'C070320320045',NULL,'300.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-24 16:09:07',NULL,'2018-01-24 16:10:45','','0'),('8de0da22dea9434fa7d99e2ea2803c06','C1055_1_1','C1055_1_1-05-01-01','13','JP15','0','E882','GZ14010403','C14','W04','C1401','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63&rdquo;双经布','500.00','U001','8','wangyanling',1,'2018-06-05 13:57:52',1,'wangyanling','2018-06-05 13:57:52',NULL,'2018-08-06 22:39:54','','0'),('8e2641d5c10b4bf792e275fa88c38a93','S1037_1_1','S1037_1_1-08-01-01','13','JP16','0','E1110','14010402','C14','W01',NULL,'C070320320045',NULL,'200.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-24 16:26:52',NULL,'2018-01-24 16:36:03','','0'),('8f640ead2c5a4ed3a305756aca72bffa','34_*_*','34_*_*-T-01','60','JP11','1','E1165','GZ23010503','C12','W01','C1202','B040509525001',NULL,'18334.00','U006','8','zhanglingling',1,'2018-06-05 15:22:49',1,'zhanglingling','2018-06-05 15:22:49',NULL,'2018-06-14 10:34:49','','0'),('906f716f874840eb80d59f0e94fac02e','198_*_*','198_*_*-01-01-01','10','JP02','0','E6','11010505','C11','W01',NULL,'B010102024075',NULL,'20.00','U002','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-18 16:16:17',NULL,'2018-01-18 16:16:45','','0'),('90c25c24b4914854a1b047efc11cebd5','192_*_*','192_*_*-01-04-01','70','JP13','0','E3649','23010501','C12','W01',NULL,'B040102024075',NULL,'101.00','U005','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-18 19:43:08',NULL,'2018-01-18 19:43:32','','0'),('923f04b7f2f145b9aa5f782e56039193','42_*_*','42_*_*-04-01-01','10','JP01','0','E13','GZ00010301','C11','W01','C1101','B010104060001','经轴-LF60*LF60 173*156 118&quot;XY60245','23.00','U001','8','yujianhua',0,'2018-08-06 22:22:30',0,'yujianhua','2018-08-06 22:22:30',NULL,'2018-08-06 22:22:30','','0'),('92b4a9c42b5448c8acf08e2b3a7db85d','198_*_*','198_*_*-T-13','50','JP08','1','E13','11010511','C11','W01','C1101','B010102024075',NULL,'55.00','U001','8','值班长A',1,'2018-04-10 11:08:41',1,'yujianhua','2018-03-15 11:01:26',NULL,'2018-03-15 11:08:49','','0'),('92d357b8cbcf46ba8ffe2a8d4ff59d18','198_*_*','198_*_*-T-22','50','JP09','1','E13','11010512','C11','W01','C1101','B010102024075',NULL,'56.00','U001','8','值班长A',1,'2018-04-10 11:08:41',1,'yujianhua','2018-03-15 18:37:33',NULL,'2018-03-15 18:38:46','','0'),('95d07523018b4bb3955510ced29d7092','198_*_*','198_*_*-T-22','50','JP09','1','E13','11010512','C11','W01','C1101','B010102024075',NULL,'100.00','U001','8','值班长A',0,'2018-04-10 11:08:41',0,'yujianhua','2018-03-15 14:44:06',NULL,'2018-03-15 14:44:06','','0'),('988c57ed50164d61823199f6406adda4','42_*_*','42_*_*-05-01-01','10','JP02','0','E13','GZ11010508','C11','W01','C1101','B010104060001','经轴-LF60*LF60 173*156 118&quot;XY60245','10.00','U002','8','yujianhua',0,'2018-08-08 21:23:19',0,'yujianhua','2018-08-08 21:23:19',NULL,'2018-08-08 21:23:19','','0'),('98b88eaad30647d18a072088dde72ab1','191_*_*','191_*_*-01-01-01','10','JP03','0','E2509','11010504','C11','W01',NULL,'B010107032045',NULL,'200.00','U005','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-20 14:40:03',NULL,'2018-01-20 14:40:10','','0'),('98dde85e28b848f392f2ec5b01cc732f','189_*_*','189_*_*-01-01-01','70','JP13','0','E2960','23010501','C12','W01',NULL,'B040107032045',NULL,'200.00','U005','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-22 16:38:51',NULL,'2018-01-22 16:39:04','','0'),('99da294a7e1d4024b32f1c97ced16675','S1037_1_1','S1037_1_1-08-01-01','13','JP16','0','E1110','14010402','C14','W01',NULL,'C070320320045',NULL,'200.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-24 16:40:02',NULL,'2018-01-24 16:43:08','','0'),('9a9695e4f2cf42feabb25f1c83e815cd','S1037_1_1','S1037_1_1-08-01-01','13','JP16','0','E1110','14010402','C14','W01',NULL,'C070320320045',NULL,'100.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-24 16:46:27',NULL,'2018-01-24 16:49:53','','0'),('9b6bef9d53524575bcc23b955f1110f7','210_*_*','210_*_*-01-01-01','10','JP03','0','E6043','11010504','C11','W01','C1101','B010101021001',NULL,'500.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-02-01 16:42:50',NULL,'2018-02-06 16:19:34','','0'),('9c1510bbadec4e5ea7d56196238f02c5','S1037_1_1','S1037_1_1-08-01-01','13','JP16','0','E1110','14010402','C14','W01',NULL,'C070320320045',NULL,'200.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-24 16:59:30',NULL,'2018-01-24 23:35:16','','0'),('9c49e616eaf040c58e6a7a1261afe0d4','36_*_*','36_*_*-02-01-01','10','JP01','0','E13','GZ00010301','C11','W01','C1101','B010109525001','经轴-LF60*LF60 173*130 106&quot;小叶子','200.00','U008','8','yujianhua',1,'2018-06-04 14:27:34',1,'yujianhua','2018-06-04 14:27:34',NULL,'2018-06-04 14:28:23','','0'),('9e944454086047f0accc2f9c1ac35325','198_*_*','198_*_*-01-04-01','10','JP03','0','E6043','11010504','C11','W01',NULL,'B010102024075',NULL,'1000.00','U005','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-18 16:47:25',NULL,'2018-01-18 16:47:53','','0'),('a01c7b3d5e9a49969c19b86185065687','34_*_*','34_*_*-02-01-01','70','JP13','0','E1471','GZ11010508','C12','W01','C1202','B040509525001','布轴-LF60*LF60 173*130 106&quot;小叶子','3.00','U005','8','qianlonglong',0,'2018-06-14 14:57:51',0,'qianlonglong','2018-06-14 14:57:51',NULL,'2018-06-14 14:57:51','','0'),('a0d6a26ccf464a71b9648f9db36ffd20','61_*_*','61_*_*-02-04-01','70','JP13','0','E2174','GZ23010501','C13','W01','C1303','B040107322001','布轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','120.00','U004','8','fangyanxia',1,'2018-07-25 10:51:13',1,'fangyanxia','2018-07-25 10:51:13',NULL,'2018-07-25 10:51:23','','0'),('a2f5d257fb9f404f95e1a0043ee72e1e','280_*_*','280_*_*-T-01','50','JP09','1','E13','GZ30000073','C11','W01','C1101','B020101021001',NULL,'100.00','U001','8','yujianhua',1,'2018-04-10 16:07:36',1,'yujianhua','2018-04-10 16:07:36',NULL,'2018-04-10 16:08:06','','0'),('a32934d97a084f2bbe7ec070a2261d65','S1037_1_1','S1037_1_1-08-01-01','13','JP16','0','E1110','14010402','C14','W01',NULL,'C070320320045',NULL,'200.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-24 16:40:02',NULL,'2018-01-24 16:43:09','','0'),('a379a93a98d44197bf389d887a9a3913','193_*_*','193_*_*-01-02-01','40','JP07','0','E28','11010501','C11','W01',NULL,'B020102024075',NULL,'302.00','U005','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-18 18:31:29',NULL,'2018-01-18 18:31:38','','0'),('a48c6d1e725d4c0685ff49fbabf0ba0b','36_*_*','36_*_*-02-01-01','10','JP03','0','E6043','GZ11010504','C11','W01','C1101','B010109525001','经轴-LF60*LF60 173*130 106&quot;小叶子','500.00','U001','8','fengdongyan',1,'2018-06-04 14:34:54',1,'fengdongyan','2018-06-04 14:34:54',NULL,'2018-06-04 14:42:55','','0'),('a48e99a2f4694fcbbfdb1282dca1285f','198_*_*','198_*_*-01-01-01','10','JP03','0','E4563,E3610','11010508','C11','W01',NULL,'B010102024075',NULL,'1001.00','U005','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-18 16:28:07',NULL,'2018-01-18 16:28:36','','0'),('a57d34abde134bd2a19ce2e2464d5fe9','C1055_1_1','C1055_1_1-06-01-01','16','JP18',NULL,'E882,E869','GZ14010409','C14','W04',NULL,'C020300200000',NULL,'237.53','U004','8',NULL,0,'2018-08-06 22:37:54',0,NULL,'2018-08-06 22:37:54',NULL,'2018-08-06 22:37:54','2','0'),('a6865976e8c545f8a6d0649f20182b8f','210_*_*','210_*_*-01-04-01','10','JP01','0','E13','00010301','C11','W01','C1101','B010101021001',NULL,'100.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-02-07 15:02:03',NULL,'2018-02-07 15:02:30','','0'),('a80c57b959be436cab8d1dbbc8a4f7f1','192_*_*','192_*_*-02-01-01','70','JP12','0','E785,E752,E3318,E2181','23010505','C12','W01',NULL,'B040102024075',NULL,'203.00','U005','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-19 17:59:44',NULL,'2018-01-19 17:59:50','','0'),('a8115fbda8b446c8913f364249e18115','210_*_*','210_*_*-01-01-01','10','JP01','0','E13','00010301','C11','W01','C1101','B010101021001',NULL,'300.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-02-07 14:20:32',NULL,'2018-02-07 14:34:30','','0'),('a95142e542ed4c8cb24a6c69c3e48541','42_*_*','42_*_*-04-01-01','10','JP03','0','E2509','GZ11010504','C11','W01','C1101','B010104060001','经轴-LF60*LF60 173*156 118&quot;XY60245','222.00','U001','8','zhangcuicui',0,'2018-08-02 16:35:51',0,'zhangcuicui','2018-08-02 16:35:51',NULL,'2018-08-02 16:35:51','','0'),('aa96c848107043ce9027fd0fd9a3d46d','S1037_1_1','S1037_1_1-08-01-01','13','JP16','0','E1110','14010402','C14','W01',NULL,'C070320320045',NULL,'100.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-24 16:26:48',NULL,'2018-01-24 16:36:02','','0'),('aa9d59b897e9443ea52f7a3da22be3e0','61_*_*','61_*_*-02-01-01','70','JP19','0','E2550','GZ23020506','C13','W01','C1303','B040107322001','布轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','1.00','U002','8','wumengdan',0,'2018-07-31 09:53:08',0,'wumengdan','2018-07-31 09:53:08',NULL,'2018-07-31 09:53:08','','0'),('ab14b75eddc2445fb893ce94a9a74de4','S1037_1_1','S1037_1_1-07-01-01','16','JP18','0','E857','14010409','C14','W01',NULL,'C070320320045',NULL,'22.00','U004','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-24 21:25:58',NULL,'2018-01-25 17:06:53','','0'),('ab9403e4bf3d4960ad339f266c433d7c','189_*_*','189_*_*-01-01-01','70','JP13','0','E2960','23010501','C12','W01',NULL,'B040107032045',NULL,'200.00','U005','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-22 16:40:07',NULL,'2018-01-22 16:40:17','','0'),('ac392f56b21c4e9fb098f1f920bbeeef','210_*_*','210_*_*-01-01-01','10','JP01','0','E13','00010301','C11','W01','C1101','B010101021001',NULL,'100.00','U005','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-02-07 14:27:31',NULL,'2018-02-07 14:34:30','','0'),('ac6c3ecf622c4d9f88fade0739b014a2','S1037_1_1','S1037_1_1-06-01-01','13','JP15','0','E798','14010403','C14','W01',NULL,'C070320320045',NULL,'200.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-24 15:51:15',NULL,'2018-01-24 15:53:54','','0'),('adf4a1fc128b49a78cdff6912378b9bf','210_*_*','210_*_*-01-01-01','10','JP01','0','E13','00010301','C11','W01',NULL,'B010101021001',NULL,'500.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-02-06 16:12:08',NULL,'2018-02-06 16:17:53','','0'),('af179180afa7409599d495ff5ccc3efe','210_*_*','210_*_*-01-01-01','10','JP01','0','E13','00010301','C11','W01','C1101','B010101021001',NULL,'200.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-02-06 20:51:33',NULL,'2018-02-06 21:06:19','','0'),('af737c96070d426e8031d9411a9408bc','38_*_*','38_*_*-02-01-01','40','JP07','0','E1202','GZ11010501','C11','W01','C1101','B020409604002','浆轴-LF60*LF60 173*156 118&quot;XC60319','56.00','U005','8','jiaozhiguang',1,'2018-06-07 14:53:29',1,'jiaozhiguang','2018-06-07 14:53:29',NULL,'2018-06-07 14:56:14','','0'),('b38e0358b1db4194858c59fda299c89b','192_*_*','192_*_*-01-05-01','70','JP13','0','E3649','23010501','C12','W01',NULL,'B040102024075',NULL,'102.00','U005','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-18 19:42:44',NULL,'2018-01-18 19:43:36','','0'),('b3dc41fb0ad346a1aaf0c2cf4961cf3c','192_*_*','192_*_*-T-25','60','JP11','1','E785','23010503','C12','W01','C1201','B040102024075',NULL,'18.00','U005','8','值班长A',1,'2018-04-10 11:08:41',1,'fanjinheng','2018-03-16 15:30:15',NULL,'2018-03-16 15:30:23','','0'),('b4ca5c4757a748b89e212556a3743907','192_*_*','192_*_*-01-01-01','70','JP13','0','E785','11010508','C12','W01','C1201','B040102024075',NULL,'12.00','U005','8','值班长A',0,'2018-04-10 11:07:13',0,'fanjinheng','2018-03-16 15:38:11',NULL,'2018-03-16 15:38:11','','0'),('b5f73b0ac98b4147b3487dbcc4d9887f','210_*_*','210_*_*-01-01-01','10','JP01','0','E13','00010301','C11','W01',NULL,'B010101021001',NULL,'100.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-02-06 16:12:33',NULL,'2018-02-06 16:17:53','','0'),('b888f72c113a47f8b26c3ca329182992','210_*_*','210_*_*-01-02-01','10','JP01','0','E13','00010301','C11','W01','C1101','B010101021001',NULL,'55.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,'yujianhua','2018-03-05 09:34:11',NULL,'2018-03-05 09:43:37','','0'),('ba87963bf2ba469db024d1730f0bf346','S1037_1_1','S1037_1_1-09-02-01','13','JP17','0','E857','14010406','C14','W01',NULL,'C070320320045',NULL,'100.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-25 14:57:08',NULL,'2018-01-25 17:07:58','','0'),('baede9fbcd664e01978f1f020ece7cc8','198_*_*','198_*_*-T-22','50','JP09','1','E13','11010512','C11','W01','C1101','B010102024075',NULL,'22.00','U001','8','值班长A',0,'2018-04-10 11:08:41',0,'yujianhua','2018-03-15 14:46:40',NULL,'2018-03-15 14:46:40','','0'),('bce752fcadc049a484f914b6373a1dc5','210_*_*','210_*_*-01-02-01','10','JP01','0','E13','00010301','C11','W01','C1101','B010101021001',NULL,'520.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,'yujianhua','2018-03-01 22:31:55',NULL,'2018-03-05 09:43:37','','0'),('bdc77f3493964f7e88a33486281e6878','210_*_*','210_*_*-01-04-01','10','JP01','0','E13','00010301','C11','W01','C1101','B010101021001',NULL,'200.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-02-07 17:16:50',NULL,'2018-03-05 09:43:37','','0'),('bdd91ba3323e4260a4adfd44c89364cf','S1037_1_1','S1037_1_1-06-01-01','13','JP15','0','E798','14010403','C14','W01',NULL,'C070320320045',NULL,'100.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-24 16:05:00',NULL,'2018-01-24 16:05:43','','0'),('be7a21814ee5491fb24125b64e0c2d52','191_*_*','191_*_*-01-05-01','10','JP03','0','E2509','11010504','C11','W01',NULL,'B010107032045',NULL,'420.00','U005','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-20 15:10:35',NULL,'2018-01-20 15:11:44','','0'),('c023fb6df32f44a8ab6cbdf143e5dfff','C1055_1_1','C1055_1_1-63-01-01','13','JP16',NULL,'E884','GZ14010402','C14','W01',NULL,'C020300200000',NULL,'1086.05','U004','8',NULL,0,'2018-08-07 18:34:34',0,NULL,'2018-08-07 18:34:34',NULL,'2018-08-07 18:34:34','1','0'),('c0c03b6ffbd845ddb28c63ef4f8978b8','210_*_*','210_*_*-01-01-01','10','JP01','0','E13','00010301','C11','W01','C1101','B010101021001',NULL,'200.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-02-07 14:20:22',NULL,'2018-02-07 14:34:30','','0'),('c11610914995420fb6496be7811d805d','41_*_*','41_*_*-04-01-01','40','JP07','0','E13','GZ11010501','C11','W01','C1101','B020404060001','浆轴-LF60*LF60 173*156 118&quot;XY60245','11.00','U009','8','yujianhua',0,'2018-08-08 21:45:32',0,'yujianhua','2018-08-08 21:45:32',NULL,'2018-08-08 21:45:32','','0'),('c1633e6e1db4485c81b41c3f72fcb167','S1037_2_1','S1037_2_1-02-02-01','13','JP15','0','E2401','14010403','C14','W01',NULL,'C020240240075',NULL,'100.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-25 23:18:25',NULL,'2018-01-25 23:20:40','','0'),('c37d5df183434f24812b8d5e90abd748','191_*_*','191_*_*-01-04-01','10','JP03','0','E2509','11010504','C11','W01',NULL,'B010107032045',NULL,'500.00','U005','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-20 15:11:10',NULL,'2018-01-20 15:11:36','','0'),('c3f4b3d76eb54b82b8b6b8e36345abd2','S1037_1_1','S1037_1_1-06-01-01','13','JP15','0','E798','14010403','C14','W01',NULL,'C070320320045',NULL,'100.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-24 16:04:58',NULL,'2018-01-24 16:05:45','','0'),('c4d7181a26434155b3f587e68d3fefc7','61_*_*','61_*_*-02-03-01','70','JP13','0','E2220','GZ23010501','C13','W01','C1303','B040107322001','布轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','6.00','U005','8','xulili',1,'2018-07-25 11:11:00',1,'xulili','2018-07-25 11:11:00',NULL,'2018-07-25 11:11:48','','0'),('c8214cbb25254cf0b05b760fc8d5f9b9','61_*_*','61_*_*-02-02-01','70','JP13','0','E760','GZ11010508','C13','W01','C1303','B040107322001','布轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','6.00','U005','8','liuhongyi',1,'2018-07-25 10:58:35',1,'liuhongyi','2018-07-25 10:58:35',NULL,'2018-07-25 11:08:09','','0'),('c862457abad4466aba1b9dd221907bc9','S1037_1_1','S1037_1_1-04-02-01','13','JP14','0','E884','14010404','C14','W01',NULL,'C070320320045',NULL,'100.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-23 21:03:43',NULL,'2018-01-23 21:05:58','','0'),('c866fe0a2836437183fab51a2d0e51df','198_*_*','198_*_*-01-04-01','10','JP01','0','E13','00010301','C11','W01',NULL,'B010102024075',NULL,'500.00','U005','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-18 22:25:03',NULL,'2018-01-23 20:53:52','','0'),('c87938e46870427b9e2673455aa27bee','28_*_*','28_*_*-04-01-01','70','JP12','0','E66','GZ23010505','C12','W01','C1204','B040302030001','布轴-T/JC（30+30）&times;20   132&times;62   63&rdquo; 双经布（双幅）','600.00','U001','8','wanglibo',1,'2018-06-04 20:16:58',1,'wanglibo','2018-06-04 20:16:58',NULL,'2018-06-04 20:17:12','','0'),('ca01a2f05fe34e67a98d0a3e219eea56','192_*_*','192_*_*-01-01-01','70','JP13','0','E785','11010508','C12','W01','C1201','B040102024075',NULL,'1000.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-02-08 19:08:48',NULL,'2018-02-08 19:08:56','','0'),('ca17d6802d2f40019c807c9eab44685a','S1037_2_1','S1037_2_1-03-01-01','13','JP16','0','E4947','14010402','C14','W01',NULL,'C020240240075',NULL,'300.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-23 16:24:45',NULL,'2018-01-23 16:25:46','','0'),('ccf27484616b4a4ea40653550831e314','S1037_1_1','S1037_1_1-08-01-01','13','JP16','0','E1110','14010402','C14','W01',NULL,'C070320320045',NULL,'300.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-24 16:26:59',NULL,'2018-01-24 16:36:06','','0'),('cdab22797f984887a524b16220858bcc','S1037_1_1','S1037_1_1-08-02-01','13','JP16','0','E884','14010402','C14','W01',NULL,'C070320320045',NULL,'100.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-23 21:17:06',NULL,'2018-01-23 21:18:19','','0'),('cf0b16b73dfe41f29db2b672be50eba0','S1037_2_1','S1037_2_1-01-03-01','13','JP14','0','E4947','14010404','C14','W01',NULL,'C020240240075',NULL,'200.00','U005','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-23 16:29:15',NULL,'2018-01-23 16:38:45','','0'),('d2861d463f0244c29167ec73e3aada0f','280_*_*','280_*_*-T-01','50','JP09','1','E13','GZ30000073','C11','W01','C1101','B020101021001',NULL,'100.00','U001','8','yujianhua',1,'2018-04-10 16:07:40',1,'yujianhua','2018-04-10 16:07:40',NULL,'2018-04-10 16:08:06','','0'),('d663e87b6f9b442ba6a229a1e7f18d74','34_*_*','34_*_*-02-02-01','70','JP13','0','E1471','GZ11010508','C12','W01','C1202','B040509525001','布轴-LF60*LF60 173*130 106&quot;小叶子','6.00','U005','8','qianlonglong',1,'2018-06-14 14:58:11',1,'qianlonglong','2018-06-14 14:58:11',NULL,'2018-06-14 14:59:45','','0'),('da8714ff02664111a2e7610d58d32782','198_*_*','198_*_*-T-34','50','JP09','1','E13','11010512','C11','W01','C1101','B010102024075',NULL,'9.00','U005','8','值班长A',1,'2018-04-10 11:08:41',1,'yujianhua','2018-03-15 22:07:19',NULL,'2018-03-15 22:07:36','','0'),('dabbbfd7324544b79ad54749dc523708','192_*_*','192_*_*-T-28','60','JP11','1','E785','23010503','C12','W01','C1201','B040102024075',NULL,'25.00','U001','8','值班长A',0,'2018-04-10 11:08:41',0,NULL,'2018-03-17 18:51:09',NULL,'2018-03-17 18:51:09','','0'),('dc573c926a624875bba490182476e27c','210_*_*','210_*_*-03-03-02','10','JP03','0','E2546','11010504','C11','W02',NULL,'B010101021001',NULL,'70.00','U001','8','值班长A',0,'2018-04-10 11:07:13',0,NULL,'2018-02-06 10:05:38',NULL,'2018-02-06 10:05:57','','0'),('dd9a64f101a14cc49250dc89845dabdf','S1037_1_1','S1037_1_1-08-02-01','13','JP16','0','E884','14010402','C14','W01',NULL,'C070320320045',NULL,'100.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-23 21:16:45',NULL,'2018-01-23 21:25:46','','0'),('de336d991205443b9f29e9ddc3860194','192_*_*','192_*_*-01-02-01','70','JP13','0','E3649','23010501','C12','W01',NULL,'B040102024075',NULL,'101.00','U005','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-18 19:42:56',NULL,'2018-01-18 19:43:34','','0'),('deb85d3318e349b692f870ca30eb5239','S1037_1_1','S1037_1_1-08-01-01','13','JP16','0','E1110','14010402','C14','W01',NULL,'C070320320045',NULL,'100.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-24 16:08:55',NULL,'2018-01-24 16:10:15','','0'),('e39928621062415f92b44f4e9f60e2c0','210_*_*','210_*_*-01-01-01','10','JP02','0','E4563','11010505','C11','W01',NULL,'B010101021001',NULL,'5.00','U002','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-25 10:48:51',NULL,'2018-01-25 14:11:12','','0'),('e6bc959895004538bb35f86537821842','28_*_*','28_*_*-02-01-02','70','JP12','0','E785','GZ23010505','C12','W02','C1201','B040302030001','布轴-T/JC（30+30）&times;20   132&times;62   63&rdquo; 双经布（双幅）','500.00','U001','8','fanjinheng',0,'2018-05-31 22:03:15',0,'fanjinheng','2018-05-31 22:03:15',NULL,'2018-05-31 22:03:15','','0'),('e7fe3c176531449392e471131fbd4455','S1037_1_1','S1037_1_1-08-01-01','13','JP16','0','E1110','14010402','C14','W01',NULL,'C070320320045',NULL,'100.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-24 16:26:47',NULL,'2018-01-24 16:35:59','','0'),('e84776718b97444e8081b5b406ceeb5c','S1037_1_1','S1037_1_1-08-02-01','13','JP16','0','E884','14010402','C14','W01',NULL,'C070320320045',NULL,'200.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-23 21:16:53',NULL,'2018-01-23 21:26:42','','0'),('eae2da8698c4401a92454a53a3c52d0c','S1037_1_1','S1037_1_1-08-01-01','13','JP16','0','E1110','14010402','C14','W01',NULL,'C070320320045',NULL,'300.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-24 16:46:44',NULL,'2018-01-24 17:00:01','','0'),('eb80621cc49c4fdea3dcbd0e73ed22fd','210_*_*','210_*_*-01-01-01','10','JP01','0','E13','00010301','C11','W01','C1101','B010101021001',NULL,'200.00','U005','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-02-07 14:27:38',NULL,'2018-02-07 14:34:30','','0'),('ebcafe2b51434303bcd610286e111871','198_*_*','198_*_*-01-04-01','10','JP01','0','E13','00010301','C11','W01',NULL,'B010102024075',NULL,'222.00','U005','8','值班长A',2,'2018-04-10 11:07:13',1,NULL,'2018-01-18 20:43:53',NULL,'2018-01-18 21:05:42','','0'),('ec64d94fb86c426ea0846c1054b3c972','198_*_*','198_*_*-T-22','50','JP09','1','E13','11010512','C11','W01','C1101','B010102024075',NULL,'55.00','U001','8','值班长A',0,'2018-04-10 11:08:41',0,'yujianhua','2018-03-15 14:21:20',NULL,'2018-03-15 14:21:20','','0'),('ec849f9261c144f986efcf30dbc527d9','192_*_*','192_*_*-T-25','60','JP11','1','E785','23010503','C12','W01','C1201','B040102024075',NULL,'18.00','U005','8','值班长A',2,'2018-04-10 11:08:41',1,'fanjinheng','2018-03-16 15:29:49',NULL,'2018-03-16 15:30:02','','0'),('ecb8a0ba095b4653809d2a37a397d3bd','S1037_1_1','S1037_1_1-07-02-01','16','JP18','0','E233','14010409','C14','W01',NULL,'C070320320045',NULL,'200.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-23 21:54:31',NULL,'2018-01-24 15:25:32','','0'),('ed2624d0ee6646df8c93cb65e95ec6b2','210_*_*','210_*_*-01-01-01','10','JP01','0','E13','00010301','C11','W01','C1101','B010101021001',NULL,'100.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-02-07 14:20:17',NULL,'2018-02-07 14:34:30','','0'),('edfb5cf4df094e758959d367ccbae6b2','280_*_*','280_*_*-T-01','50','JP09','1','E13','GZ30000073','C11','W01','C1101','B020101021001',NULL,'200.00','U001','8','yujianhua',1,'2018-04-10 16:07:44',1,'yujianhua','2018-04-10 16:07:44',NULL,'2018-04-10 16:08:06','','0'),('efe997b52c074fc2b7d121805d9d4cfd','61_*_*','61_*_*-02-05-01','70','JP13','0','E2174','GZ23010501','C13','W01','C1303','B040107322001','布轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','120.00','U004','8','fangyanxia',1,'2018-07-25 09:37:26',1,'fangyanxia','2018-07-25 09:37:26',NULL,'2018-07-25 09:37:59','','0'),('f112b7d35be94de99c011e9fee3fd3f1','210_*_*','210_*_*-01-04-01','10','JP01','0','E13','00010301','C11','W01','C1101','B010101021001',NULL,'100.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-02-07 16:49:00',NULL,'2018-02-07 16:49:21','','0'),('f1ffb75cf9ac4dc7bc5c20345b7ce2a0','198_*_*','198_*_*-T-34','50','JP09','1','E13','11010512','C11','W01','C1101','B010102024075',NULL,'9.00','U005','8','值班长A',1,'2018-04-10 11:08:41',1,'yujianhua','2018-03-15 22:07:16',NULL,'2018-03-15 22:08:01','','0'),('f3321af45bde450c88002c95d9b472fd','210_*_*','210_*_*-01-01-01','10','JP01','0','E13','00010301','C11','W01','C1101','B010101021001',NULL,'100.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-02-07 17:13:59',NULL,'2018-02-07 17:15:07','','0'),('f363410ca1344ead8c6ef9f5e89c4ec8','60_*_*','60_*_*-01-01-01','10','JP03','0','E2546','GZ11010504','C11','W01','C1102','B010107322001','经轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','132250.00','U001','8','kangchunhui',0,'2018-07-24 14:19:49',0,NULL,'2018-07-24 14:19:49',NULL,'2018-07-24 14:19:49','','0'),('f4048e065bc7422383e4c9d631e4d41b','S1037_1_1','S1037_1_1-09-02-01','13','JP17','0','E857','14010406','C14','W01',NULL,'C070320320045',NULL,'21.87','U004','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-24 21:26:17',NULL,'2018-01-25 17:07:58','','0'),('f47278d398c14ae1832c732e255f5aa0','191_*_*','191_*_*-01-03-01','10','JP03','0','E2509','11010504','C11','W01',NULL,'B010107032045',NULL,'480.00','U005','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-20 15:10:54',NULL,'2018-01-20 15:11:39','','0'),('f4f3be94855a42428dc7670440a80b3d','198_*_*','198_*_*-01-02-01','10','JP01','0','E13','00010301','C11','W01',NULL,'B010102024075',NULL,'20.00','U002','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-18 16:38:54',NULL,'2018-01-18 16:39:41','','0'),('f61902adb1b849518831a75ee2848407','210_*_*','210_*_*-01-02-01','10','JP01','0','E13','00010301','C11','W01','C1101','B010101021001',NULL,'321.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-03-01 21:46:05',NULL,'2018-03-05 09:43:37','','0'),('f7e0a0ccf8ab4f41962ec40270b991c4','S1037_1_1','S1037_1_1-06-01-01','13','JP15','0','E798','14010403','C14','W01',NULL,'C070320320045',NULL,'100.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-24 15:51:06',NULL,'2018-01-24 15:53:55','','0'),('f83aadfb9e724f359491118ff535df2c','61_*_*','61_*_*-02-02-01','70','JP13','0','E760','GZ11010508','C13','W01','C1303','B040107322001','布轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','6.00','U005','8','liuhongyi',2,'2018-07-25 11:02:52',1,'liuhongyi','2018-07-25 11:02:52',NULL,'2018-07-25 11:07:57','','0'),('f85230ea7d594de793c67091c759b134','S1037_1_1','S1037_1_1-08-01-01','13','JP16','0','E1110','14010402','C14','W01',NULL,'C070320320045',NULL,'300.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-24 16:46:42',NULL,'2018-01-24 16:54:33','','0'),('f94d1b178f3e482ba5d226b48d9cf0fd','30_*_*','30_*_*-01-01-01','40','JP07','0','E6028','11010501','C11','W01','C1101','B020302030001','浆轴-T/JC（30+30）&times;20   132&times;62   63&rdquo; 双经布（双幅）','192.00','U005','8','chenjiping',0,'2018-06-07 12:14:35',0,'chenjiping','2018-06-07 12:14:35',NULL,'2018-06-07 12:14:35','','0'),('f95fb1bc3ca84032882ae725d1023f07','210_*_*','210_*_*-01-01-01','10','JP01','0','E13','00010301','C11','W01','C1101','B010101021001',NULL,'100.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-02-06 20:51:25',NULL,'2018-02-06 21:06:19','','0'),('fa071c0226404591914b708ceedbe862','210_*_*','210_*_*-01-02-01','10','JP01','0','E13','00010301','C11','W01','C1101','B010101021001',NULL,'520.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,'yujianhua','2018-03-01 22:31:55',NULL,'2018-03-05 09:43:37','','0'),('fa4b75bb2ae044579556337ef420a445','61_*_*','61_*_*-02-01-01','70','JP13','0','E2220','GZ23010501','C13','W01','C1303','B040107322001','布轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','6.00','U005','8','xulili',1,'2018-07-25 11:11:11',1,'xulili','2018-07-25 11:11:11',NULL,'2018-07-25 11:11:39','','0'),('faf6e3c459994a7db3ba8bd456eb6b8b','42_*_*','42_*_*-05-01-01','10','JP03','0','E13','GZ11010504','C11','W01','C1101','B010104060001','经轴-LF60*LF60 173*156 118&quot;XY60245','10.00','U002','8','yujianhua',0,'2018-08-08 21:30:02',0,'yujianhua','2018-08-08 21:30:02',NULL,'2018-08-08 21:30:02','','0'),('fb5eca74d66c49a6b2d14676145f32e5','S1037_1_1','S1037_1_1-06-01-01','13','JP15','0','E798','14010403','C14','W01',NULL,'C070320320045',NULL,'200.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,NULL,'2018-01-24 16:05:05',NULL,'2018-01-24 16:05:42','','0'),('fd04856f2ab0402ba9198f1268f570ed','210_*_*','210_*_*-01-02-01','10','JP01','0','E13','00010301','C11','W01','C1101','B010101021001',NULL,'111.00','U001','8','值班长A',1,'2018-04-10 11:07:13',1,'yujianhua','2018-03-01 22:11:07',NULL,'2018-03-05 09:43:37','','0');

/*Table structure for table `job_substandard_cloth_record` */

DROP TABLE IF EXISTS `job_substandard_cloth_record`;

CREATE TABLE `job_substandard_cloth_record` (
  `id` char(32) NOT NULL,
  `task_id` varchar(50) NOT NULL,
  `checker` varchar(32) NOT NULL,
  `responsible_worker` varchar(32) NOT NULL,
  `substandard_kind` varchar(10) NOT NULL,
  `substandard_name` varchar(100) NOT NULL,
  `variety` varchar(50) NOT NULL,
  `batch_code` varchar(50) DEFAULT NULL,
  `trace_content` varchar(300) NOT NULL,
  `is_solved` char(4) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `del_flag` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `job_substandard_cloth_record` */

/*Table structure for table `job_tem_submit` */

DROP TABLE IF EXISTS `job_tem_submit`;

CREATE TABLE `job_tem_submit` (
  `id` varchar(64) NOT NULL,
  `sheet_id` varchar(64) NOT NULL,
  `work_shop` varchar(64) DEFAULT NULL,
  `task_user` varchar(64) DEFAULT NULL,
  `task_type` varchar(10) DEFAULT NULL,
  `task_desc` varchar(4000) DEFAULT NULL,
  `plan_begin_date` datetime DEFAULT NULL,
  `plan_end_date` datetime DEFAULT NULL,
  `task_starter` varchar(64) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `del_flag` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `job_tem_submit` */

/*Table structure for table `job_template` */

DROP TABLE IF EXISTS `job_template`;

CREATE TABLE `job_template` (
  `id` char(32) NOT NULL COMMENT '模板编号',
  `template_kind` varchar(20) NOT NULL COMMENT '模板类型',
  `template_name` varchar(50) NOT NULL COMMENT '模板名称',
  `status` tinyint(4) NOT NULL COMMENT '启用状态',
  `is_released` char(4) NOT NULL COMMENT '发布状态',
  `release_time` datetime NOT NULL COMMENT '发布时间',
  `version_code` varchar(50) NOT NULL COMMENT '版本号',
  `create_by` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `del_flag` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `job_template` */

/*Table structure for table `job_template_manage` */

DROP TABLE IF EXISTS `job_template_manage`;

CREATE TABLE `job_template_manage` (
  `id` char(32) NOT NULL COMMENT '模板编号',
  `template_code` varchar(50) NOT NULL,
  `template_kind` varchar(20) NOT NULL COMMENT '模板类型',
  `template_name` varchar(50) NOT NULL COMMENT '模板名称',
  `process_code` varchar(50) DEFAULT NULL,
  `work_type_code` varchar(50) DEFAULT NULL,
  `status` tinyint(4) NOT NULL COMMENT '启用状态',
  `is_released` char(4) NOT NULL COMMENT '发布状态',
  `release_time` datetime NOT NULL COMMENT '发布时间',
  `version_code` varchar(50) NOT NULL COMMENT '版本号',
  `create_by` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `del_flag` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `job_template_manage` */

insert  into `job_template_manage`(`id`,`template_code`,`template_kind`,`template_name`,`process_code`,`work_type_code`,`status`,`is_released`,`release_time`,`version_code`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) values ('0a4b953587e044c68a6650fd5c07ad51','CZ0001','操作录入','上筒模板','JP01','GZ00010301',1,'1','2017-12-26 17:01:39','v0.0.1',NULL,'2017-09-12 11:14:11',NULL,'2017-12-26 17:01:39',NULL,'0'),('12159ab546d14c2998af0d518b4095ff','CZ0002','操作录入','上轴模板','JP02','GZ11010508',1,'1','2017-09-12 11:14:11','v0.0.1',NULL,'2017-09-12 11:14:11',NULL,'2017-09-12 11:14:11',NULL,'0'),('12159ab546d14c2998af0d518b4095fm','CZ0002','操作录入','上轴模板','JP06','GZ11010502',1,'1','2017-09-12 11:14:11','v0.0.1',NULL,'2017-09-12 11:14:11',NULL,'2017-09-12 11:14:11',NULL,'0'),('12159ab546d14c2998af0d518b4096f','CZ0016','操作录入','上轴模板','JP08','GZ11010511',1,'1','2017-09-12 11:14:11','v0.0.1',NULL,'2017-09-12 11:14:11',NULL,'2017-09-12 11:14:11',NULL,'0'),('12159ab546d14c2998af0d518b4098e','CZ0002','操作录入','上轴模板','JP19','GZ23020506',1,'1','2017-09-12 11:14:11','v0.0.1',NULL,'2017-09-12 11:14:11',NULL,'2017-09-12 11:14:11',NULL,'0'),('12159ab546d14c2998af0d518b4098fe','CZ0016','操作录入','上轴模板','JP10','GZ23010504',1,'1','2017-09-12 11:14:11','v0.0.1',NULL,'2017-09-12 11:14:11',NULL,'2017-09-12 11:14:11',NULL,'0'),('1d563bdc3c71432aadfc93224d30dbdc','CZ0003','操作录入','整经模板','JP03','GZ11010510',1,'1','2017-11-07 09:34:24','v0.0.1',NULL,'2017-09-12 11:14:11',NULL,'2017-11-07 09:34:24',NULL,'0'),('1d563bdc3c71432aadfc93224d30dbfb','CZ0003','操作录入','整经模板','JP03','GZ11010504',1,'1','2017-11-07 09:34:24','v0.0.1',NULL,'2017-09-12 11:14:11',NULL,'2017-11-07 09:34:24',NULL,'0'),('1ee70bcd54a347e7a7112afe38bcfaa6','CZ0004','操作录入','络筒模板','JP04','GZ11010506',1,'1','2017-10-23 19:52:26','v0.0.1',NULL,'2017-09-12 11:14:11',NULL,'2017-10-23 19:52:26',NULL,'0'),('286c369ab36748c3b537fe6b0e26022b','CZ0005','操作录入','调浆模板','JP05','GZ11010503',1,'1','2017-10-31 19:35:59','v0.0.1',NULL,'2017-09-12 11:14:10',NULL,'2017-10-31 19:35:59',NULL,'0'),('306a265f0c664cba9e5344e2b4cd7ce6','CZ0006','操作录入','浆纱模板','JP07','GZ11010501',1,'1','2017-11-05 17:20:49','v0.0.1',NULL,'2017-09-12 11:14:10',NULL,'2017-11-05 17:20:49',NULL,'0'),('306a265f0c664cba9e5344e2b4cd7cmd','CZ0006','操作录入','浆纱模板','JP07','GZ11010508',1,'1','2017-11-05 17:20:49','v0.0.1',NULL,'2017-09-12 11:14:10',NULL,'2017-11-05 17:20:49',NULL,'0'),('3a3df5ccd8d241f5b1136c629ec8e768','CZ0007','操作录入','穿筘模板','JP09','GZ11010512',1,'1','2017-09-12 11:14:11','v0.0.1',NULL,'2017-09-12 11:14:11',NULL,'2017-10-23 18:46:07',NULL,'0'),('5e23f401d75347d79d2672935c33166a','CZ0008','操作录入','派纱模板','JP12','GZ23010505',1,'1','2017-10-23 19:40:39','v0.0.1',NULL,'2017-09-12 11:14:10',NULL,'2017-10-23 19:40:39',NULL,'0'),('6542e5430e6c4ff29a96ed24d1927b0e','CZ0009','操作录入','结经模板','JP11','GZ23010503',1,'1','2017-09-12 11:14:11','v0.0.1',NULL,'2017-09-12 11:14:11',NULL,'2017-09-12 11:14:11',NULL,'0'),('753478c840f44723b222abf5f015748d','CZ0010','操作录入','织布落布模板','JP13','GZ23010501',1,'1','2017-09-12 11:14:11','v0.0.1',NULL,'2017-09-12 11:14:11',NULL,'2017-09-12 11:14:11',NULL,'0'),('753478c840f44723b222abf5f0157567','CZ0010','操作录入','织布落布模板','JP13','GZ11010508',1,'1','2017-09-12 11:14:11','v0.0.1',NULL,'2017-09-12 11:14:11',NULL,'2017-09-12 11:14:11',NULL,'0'),('7ed8fb6b54214babb3f785a19f83c199','CZ0011','操作录入','打包模板','JP18','GZ14010409',1,'1','2017-11-30 09:40:24','v0.0.1',NULL,'2017-10-22 20:57:25',NULL,'2017-11-30 09:40:24',NULL,'0'),('a11350a406744f9ab7f9717873a4e5f4','CZ0012','操作录入','验布模板','JP14','GZ14010404',1,'1','2017-10-22 20:57:25','v0.0.1',NULL,'2017-10-22 20:57:25',NULL,'2017-10-23 19:18:00',NULL,'0'),('a4b5493cbb2244f8af546087a217d535','CZ0013','操作录入','分等分段模板','JP15','GZ14010403',1,'1','2017-10-22 20:57:24','v0.0.1',NULL,'2017-10-22 20:57:24',NULL,'2017-10-22 20:57:24',NULL,'0'),('a5689eeffa8641b8afe632e3d4f13f2b','CZ0014','操作录入','拼件模板','JP16','GZ14010402',1,'1','2017-10-22 20:57:24','v0.0.1',NULL,'2017-10-22 20:57:24',NULL,'2017-10-22 20:57:24',NULL,'0'),('a5689eeffa8641b8afe632e3d4f13f3c','CZ0015','操作录入','卷布模板','JP17','GZ14010406',1,'1','2017-10-22 20:57:24','v0.0.1',NULL,'2017-10-22 20:57:24',NULL,'2017-10-22 20:57:24',NULL,'0');

/*Table structure for table `job_tying_record` */

DROP TABLE IF EXISTS `job_tying_record`;

CREATE TABLE `job_tying_record` (
  `id` char(32) NOT NULL,
  `task_id` varchar(50) DEFAULT NULL,
  `shaft_code` varchar(50) DEFAULT NULL,
  `tying_worker` varchar(32) NOT NULL,
  `work_class` varchar(10) NOT NULL,
  `machine_code` varchar(50) NOT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `del_flag` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `job_tying_record` */

insert  into `job_tying_record`(`id`,`task_id`,`shaft_code`,`tying_worker`,`work_class`,`machine_code`,`status`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) values ('30c137675ea6465995b1d583da0edabd','34_*_*-T-01','631','E1165','W01','S208000100001',1,NULL,'2018-06-05 15:21:06',NULL,'2018-06-05 15:21:06','','0'),('81877ace3ba24ca2ba3ce65aec0edbbd','34_*_*-T-01','571','E1165','W01','S208000100001',1,NULL,'2018-06-05 15:20:59',NULL,'2018-06-05 15:20:59','','0');

/*Table structure for table `job_upper_latitude_record` */

DROP TABLE IF EXISTS `job_upper_latitude_record`;

CREATE TABLE `job_upper_latitude_record` (
  `id` char(32) NOT NULL,
  `task_id` varchar(50) DEFAULT NULL,
  `doff_worker` varchar(32) NOT NULL,
  `work_class` varchar(10) NOT NULL,
  `machine_code` varchar(50) NOT NULL,
  `variety` varchar(50) NOT NULL,
  `number` decimal(20,2) NOT NULL,
  `unit` varchar(10) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `del_flag` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `job_upper_latitude_record` */

insert  into `job_upper_latitude_record`(`id`,`task_id`,`doff_worker`,`work_class`,`machine_code`,`variety`,`number`,`unit`,`status`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) values ('0ba64b5394074c869ab4c971a5c816d8','34_*_*-02-02-01','E1471','W01','S201000501041','U005','6.00',NULL,1,NULL,'2018-06-14 10:25:36',NULL,'2018-06-14 10:25:36','','1'),('17e8413986464dae8452a334e7ab32c3','34_*_*-02-02-01','E1471','W01','S201000501041','U005','6.00',NULL,1,NULL,'2018-06-14 10:39:35',NULL,'2018-06-14 10:39:35','','0'),('2a2200cca3844f74a1ea57ca3035d3f5','28_*_*-02-01-02','E785','W02','S201001509167','U001','500.00',NULL,1,NULL,'2018-05-31 22:02:49',NULL,'2018-05-31 22:02:49','','1'),('429230fd8b364a368747b72f6ccecc60','28_*_*-04-01-01','E66','W01','S208000100001','U001','600.00',NULL,1,NULL,'2018-06-04 20:16:43',NULL,'2018-06-04 20:16:43','','1'),('a79edfeed54445b7b6cb99f4d9480900','61_*_*-02-03-02','E3655','W02','S201004309527','U003','12.00',NULL,1,NULL,'2018-07-25 13:11:06',NULL,'2018-07-25 13:11:06','','0'),('f885380494ca48d4bcc5839ad05aaf1f','34_*_*-02-02-01','E1471','W01','S201000501041','U005','6.00',NULL,1,NULL,'2018-06-14 10:25:44',NULL,'2018-06-14 10:25:44','','1');

/*Table structure for table `job_upper_shaft_record` */

DROP TABLE IF EXISTS `job_upper_shaft_record`;

CREATE TABLE `job_upper_shaft_record` (
  `id` char(32) NOT NULL,
  `task_id` varchar(50) DEFAULT NULL,
  `upper` varchar(32) NOT NULL,
  `work_class` varchar(10) NOT NULL,
  `variety` varchar(50) NOT NULL,
  `material_name` varchar(200) DEFAULT NULL COMMENT '品名',
  `is_empty_shaft` char(4) DEFAULT NULL,
  `shaft_code` varchar(50) NOT NULL,
  `work_procedure` varchar(50) NOT NULL,
  `work_procedure_name` varchar(200) DEFAULT NULL COMMENT '工序名称',
  `machine_code` varchar(50) NOT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `del_flag` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `job_upper_shaft_record` */

insert  into `job_upper_shaft_record`(`id`,`task_id`,`upper`,`work_class`,`variety`,`material_name`,`is_empty_shaft`,`shaft_code`,`work_procedure`,`work_procedure_name`,`machine_code`,`status`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) values ('282c8a63d03a4f36915aeee82d2c0048','61_*_*-02-01-01','E2550','W01','B040107322001','布轴-CVC 32/2×32/2  100×53  63\" 2/1碳黑1.06镶嵌 CS18-14-1','否','S12345','JP19','上穿轴','S201004109495',1,NULL,'2018-07-31 09:52:22',NULL,'2018-07-31 09:52:22','','0'),('2a43c4479ae14622aa7201b1f2ad8adf','30_*_*-01-01-01','E13,E4707','W01','B020302030001','浆轴-T/JC（30+30）×20   132×62   63” 双经布（双幅）','是','1','JP06','上轴','S202005102002',1,NULL,'2018-06-06 08:42:18',NULL,'2018-06-06 08:42:18','','0'),('3a7b8d3805694644b25cb2911da87990','30_*_*-01-01-01','E13,E4707','W01','B020302030001','浆轴-T/JC（30+30）×20   132×62   63” 双经布（双幅）','是','1','JP06','上轴','S202005102002',1,NULL,'2018-06-06 08:42:14',NULL,'2018-06-06 08:42:14','','0'),('505e479600c94f059855cbba41c4c389','61_*_*-02-01-03','E591','W03','B040107322001','布轴-CVC 32/2×32/2  100×53  63\" 2/1碳黑1.06镶嵌 CS18-14-1','是','S089088','JP19','上穿轴','S201004109495',1,NULL,'2018-07-31 09:12:52',NULL,'2018-07-31 09:12:52','','0'),('548323a783f94fbf8fd445659245c269','34_*_*-02-02-03','E583','W03','B040509525001','布轴-LF60*LF60 173*130 106\"小叶子','是','s88987','JP19','上穿轴','S201000501041',1,NULL,'2018-07-31 09:58:39',NULL,'2018-07-31 09:58:39','','0'),('7a0cff54154a48d0b81957bd08b0ec49','61_*_*-02-01-03','E591','W03','B040107322001','布轴-CVC 32/2×32/2  100×53  63\" 2/1碳黑1.06镶嵌 CS18-14-1','否','S050041','JP19','上穿轴','S201004109495',1,NULL,'2018-07-31 09:08:01',NULL,'2018-07-31 09:08:01','','0'),('b8e3123bdcfc4101866f3ef57c3d2e79','42_*_*-05-01-01','E13','W01','B010104060001','经轴-LF60*LF60 173*156 118\"XY60245','是','Y678','JP02','整经上轴','S203005007007',1,NULL,'2018-08-08 21:22:36',NULL,'2018-08-08 21:22:36','','0'),('c738a613aaac4695b800748231c7b85f','30_*_*-01-01-01','E13,E4707','W01','B020302030001','浆轴-T/JC（30+30）×20   132×62   63” 双经布（双幅）','是','1、2、3、4、5、6、7、8、9、10','JP06','上轴','S202005102002',1,NULL,'2018-06-06 08:42:37',NULL,'2018-06-06 08:42:37','','0'),('d25f9ee8081541f59cc7bdc0f3748573','42_*_*-04-01-01','E3610','W01','B010104060001','经轴-LF60*LF60 173*156 118\"XY60245','是','x456','JP02','整经上轴','S203005006006',1,NULL,'2018-08-02 16:28:41',NULL,'2018-08-02 16:28:41','','0');

/*Table structure for table `job_upper_yarn_record` */

DROP TABLE IF EXISTS `job_upper_yarn_record`;

CREATE TABLE `job_upper_yarn_record` (
  `id` char(32) NOT NULL,
  `task_id` varchar(50) DEFAULT NULL,
  `unpack_team` varchar(32) NOT NULL,
  `work_class` varchar(10) NOT NULL,
  `variety` varchar(50) NOT NULL,
  `number` bigint(20) NOT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `del_flag` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `job_upper_yarn_record` */

insert  into `job_upper_yarn_record`(`id`,`task_id`,`unpack_team`,`work_class`,`variety`,`number`,`start_time`,`end_time`,`status`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) values ('03f27cacc7874695a9360e3bb3b28849','36_*_*-02-01-01','E13','W01','B010109525001',200,'2018-05-27 14:26:00','2018-05-27 17:00:00',1,NULL,'2018-06-04 14:27:04',NULL,'2018-06-04 14:27:04','','0'),('21c8db2dced64edab83bd8b17874cea3','39_*_*-01-01-01','E6','W01','B010109604002',1,'2018-06-10 14:00:00','2018-06-14 14:26:00',1,NULL,'2018-06-19 14:26:00',NULL,'2018-06-19 14:26:00','','0'),('503755c0fd58431390375e294b57fa3d','36_*_*-01-01-01','E6','W01','B010109525001',1,'2018-06-10 10:26:00','2018-06-10 10:26:00',1,NULL,'2018-06-07 10:27:06',NULL,'2018-06-07 10:27:06','','0'),('903f6d2a1b1441e2bf6f61974e323c33','42_*_*-04-01-01','E13','W01','B010104060001',50,'2018-06-10 11:00:00','2018-06-10 16:11:00',1,NULL,'2018-08-02 16:15:23',NULL,'2018-08-02 16:15:23','有公司','0'),('ad59944c9ebc498faf4d28e21b469871','36_*_*-02-01-01','E13','W01','B010109525001',200,'2018-05-27 14:25:00','2018-05-27 17:00:00',1,NULL,'2018-06-04 14:25:51',NULL,'2018-06-04 14:25:51','','0'),('bd2cf6b1e60f452db845bf830ded2c45','39_*_*-01-01-01','E6','W01','B010109604002',1,'2018-06-10 14:00:00','2018-06-10 15:00:00',1,NULL,'2018-06-07 15:06:25',NULL,'2018-06-07 15:06:25','','0'),('c68dcadc029848258a9b12a833c29678','42_*_*-04-01-01','E13','W01','B010104060001',50,'2018-06-10 11:00:00','2018-06-10 16:11:00',1,NULL,'2018-08-02 16:15:33',NULL,'2018-08-02 16:15:33','有公司','1'),('d64c7100a03b411db4f830ef70612a1b','42_*_*-04-01-01','E13','W01','B010104060001',100,'2018-08-08 21:14:00','2018-08-08 22:00:00',1,NULL,'2018-08-08 21:17:43',NULL,'2018-08-08 21:17:43','','0');

/*Table structure for table `job_warping_record` */

DROP TABLE IF EXISTS `job_warping_record`;

CREATE TABLE `job_warping_record` (
  `id` char(32) NOT NULL,
  `erp_bill_no` varchar(64) DEFAULT NULL,
  `task_id` varchar(50) NOT NULL,
  `warping_worker` varchar(32) NOT NULL,
  `work_class` varchar(10) NOT NULL,
  `work_team` varchar(50) DEFAULT NULL COMMENT '班组',
  `work_center` varchar(50) DEFAULT NULL COMMENT '工作中心',
  `variety` varchar(50) NOT NULL,
  `material_name` varchar(200) DEFAULT NULL,
  `shaft_code` varchar(50) NOT NULL,
  `serial_no` varchar(50) DEFAULT NULL COMMENT '序列号',
  `number` decimal(20,2) NOT NULL COMMENT '数量',
  `unit` varchar(10) NOT NULL COMMENT '单位',
  `unit_name` varchar(20) DEFAULT NULL,
  `machine_code` varchar(50) NOT NULL,
  `root_number` bigint(20) NOT NULL,
  `downer` varchar(32) NOT NULL,
  `down_time` datetime DEFAULT NULL,
  `weakness` varchar(200) DEFAULT NULL,
  `checker` varchar(32) DEFAULT NULL,
  `change_duty` varchar(20) DEFAULT NULL,
  `change_duty_worker` varchar(32) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `del_flag` char(1) DEFAULT NULL,
  `factory` varchar(50) DEFAULT NULL COMMENT '厂家',
  `yarn_code` varchar(50) DEFAULT NULL COMMENT '管头',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `job_warping_record` */

insert  into `job_warping_record`(`id`,`erp_bill_no`,`task_id`,`warping_worker`,`work_class`,`work_team`,`work_center`,`variety`,`material_name`,`shaft_code`,`serial_no`,`number`,`unit`,`unit_name`,`machine_code`,`root_number`,`downer`,`down_time`,`weakness`,`checker`,`change_duty`,`change_duty_worker`,`status`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`,`factory`,`yarn_code`) values ('0104781e23614e9db5ffee154392271e','39_*_*','39_*_*-01-01-01','E661','W01','C1101','W01','B010109604002','经轴-LF60*LF60 173*156 118&quot;XC60319','233','B01201807010006','3635.00','U001','米','S203005002001',680,'E3610','2018-06-10 14:19:00','','E24','','',1,NULL,'2018-07-01 12:56:11',NULL,'2018-07-01 12:56:11','','0',NULL,NULL),('0121508c6245493dbc00439a722bb812','60_*_*','60_*_*-01-01-01','E2546','W01','C1102','W01','B010107322001','经轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','245','B01201807240003','13225.00','U001','米','S203005007007',649,'E2728','2018-07-23 14:02:00','无','E15','','',1,NULL,'2018-07-24 14:02:19',NULL,'2018-07-24 14:02:19','','1',NULL,NULL),('0149c58f467c471e88796b4508f15272','39_*_*','39_*_*-01-01-01','E661','W01','C1101','W01','B010109604002','经轴-LF60*LF60 173*156 118&quot;XC60319','627','B01201807010001','3635.00','U001','米','S203005002001',680,'E3610','2018-06-10 14:19:00','','E24','','',1,NULL,'2018-07-01 12:55:27',NULL,'2018-07-01 12:55:27','','0',NULL,NULL),('01a6c1838726409eb44a92bd1829c976','39_*_*','39_*_*-01-01-01','E661','W01','C1101','W01','B010109604002','经轴-LF60*LF60 173*156 118&quot;XC60319','9','B01201806070009','7651.00','U001','米','S203005002002',680,'E3290','2018-06-10 14:19:00','','E24','','',1,NULL,'2018-06-07 14:22:28',NULL,'2018-06-07 14:22:28','','1',NULL,NULL),('022b5b07358b4345bfb532ea956c6994','39_*_*','39_*_*-01-01-01','E661','W01','C1101','W01','B010109604002','经轴-LF60*LF60 173*156 118&quot;XC60319','1','B01201806180001','7651.00','','请选择','S203005002002',680,'E3610','2018-06-18 11:35:00','','','','',1,NULL,'2018-06-18 11:36:02',NULL,'2018-06-18 11:36:02','','1',NULL,NULL),('0278daafe3ec48e18a0de965d6a0d473','36_*_*','36_*_*-01-01-01','E661','W01','C1101','W01','B010109525001','经轴-LF60*LF60 173*130 106&quot;小叶子','13','B01201805300013','9974.00','U001','米','S203005002002',705,'E3610','2018-05-27 09:28:00','','E24','','',1,NULL,'2018-05-30 09:30:22',NULL,'2018-05-30 09:30:22','','1',NULL,NULL),('02f36f92724549b88aae38764ab1a643','39_*_*','39_*_*-01-01-01','E661','W01','C1101','W01','B010109604002','经轴-LF60*LF60 173*156 118&quot;XC60319','10','B01201806070010','7651.00','U001','米','S203005002002',680,'E3290','2018-06-10 14:19:00','','E24','','',1,NULL,'2018-06-07 14:22:33',NULL,'2018-06-07 14:22:33','','1',NULL,NULL),('033ef95fd30848e49fc1d664c172afb8','60_*_*','60_*_*-01-01-01','E2546','W01','C1102','W01','B010107322001','经轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','274','B01201807240019','13225.00','U001','米','S203005007007',649,'E2728','2018-07-23 14:12:00','无','E15','','',1,NULL,'2018-07-24 14:12:08',NULL,'2018-07-24 14:12:08','','1',NULL,NULL),('037c9e36cd684dfe8f9ccab5223f2439','36_*_*','36_*_*-02-01-01','E6043','W01','C1101','W01','B010109525001','经轴-LF60*LF60 173*130 106&quot;小叶子','123','B01201806040001','500.00','U001','米','S203005001001',4,'E6043','2018-06-04 14:34:00','无','E13','','',1,NULL,'2018-06-04 14:34:30',NULL,'2018-06-04 14:34:30','','1',NULL,NULL),('0907cec6c34c4b7da578d1bd5d7798e6','39_*_*','39_*_*-01-01-01','E661','W01','C1101','W01','B010109604002','经轴-LF60*LF60 173*156 118&quot;XC60319','2','B01201806070002','7651.00','U001','米','S203005002002',680,'E3290','2018-06-10 14:19:00','','E24','','',1,NULL,'2018-06-07 14:21:52',NULL,'2018-06-07 14:21:52','','1',NULL,NULL),('09423554df884c22881878374a1f08f2','36_*_*','36_*_*-01-01-01','E661','W01','C1101','W01','B010109525001','经轴-LF60*LF60 173*130 106&quot;小叶子','11','B01201805300011','9974.00','U001','米','S203005002002',705,'E3610','2018-05-27 09:28:00','','E24','','',1,NULL,'2018-05-30 09:30:11',NULL,'2018-05-30 09:30:11','','0',NULL,NULL),('0d45476177784adea7f1f19766fdf2e3','60_*_*','60_*_*-01-01-01','E2546','W01','C1102','W01','B010107322001','经轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','426','B01201807240007','13225.00','','请选择','S203005007007',607,'E2728','2018-07-23 14:05:00','无','E15','','',1,NULL,'2018-07-24 14:05:30',NULL,'2018-07-24 14:05:30','','1',NULL,NULL),('12c9189eefac468e9591d0bb0ca67599','60_*_*','60_*_*-01-01-01','E2546','W01','C1102','W01','B010107322001','经轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','426','B01201807240029','13225.00','U001','米','S203005007007',607,'E2728','2018-07-23 14:05:00','','E15','','',1,NULL,'2018-07-24 14:18:31',NULL,'2018-07-24 14:18:31','','1',NULL,NULL),('1cdcc954f3154d34af70c2f109b2e3bd','60_*_*','60_*_*-01-01-01','E2546','W02','C1102','W01','B010107322001','经轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','3','B01201807240027','13225.00','U001','米','S203005007007',649,'E2728','2018-07-23 14:16:00','','','','',1,NULL,'2018-07-24 14:16:13',NULL,'2018-07-24 14:16:13','','0',NULL,NULL),('1d9e2530d56d42f894c0398aee632480','60_*_*','60_*_*-01-01-01','E2546','W01','C1102','W01','B010107322001','经轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','268','B01201807240006','13225.00','U001','米','S203005007007',649,'E2728','2018-07-23 14:02:00','无','E15','0','',1,NULL,'2018-07-24 14:02:49',NULL,'2018-07-24 14:02:49','','1',NULL,NULL),('1ddfb131e8e441f586537c37be28ed6a','36_*_*','36_*_*-01-01-01','E661','W01','C1101','W01','B010109525001','经轴-LF60*LF60 173*130 106&quot;小叶子','6','B01201805300006','9974.00','U001','米','S203005002002',705,'E3610','2018-05-27 09:28:00','','E24','','',1,NULL,'2018-05-30 09:29:44',NULL,'2018-05-30 09:29:44','','0',NULL,NULL),('1fc1f5ac8c18417ab4d59c16d3e4b156','60_*_*','60_*_*-01-01-01','E2546','W01','C1102','W01','B010107322001','经轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','268','B01201807240005','13225.00','U001','米','S203005007007',649,'E2728','2018-07-23 14:02:00','无','E15','','',1,NULL,'2018-07-24 14:02:40',NULL,'2018-07-24 14:02:40','','1',NULL,NULL),('1fe028f927e54e96b6df2559f692dc53','39_*_*','39_*_*-01-01-01','E661','W01','C1101','W01','B010109604002','经轴-LF60*LF60 173*156 118&quot;XC60319','4','B01201806070019','22953.00','U001','米','S203005002002',680,'E3290','2018-06-10 14:39:00','','E24','','',1,NULL,'2018-06-07 14:39:13',NULL,'2018-06-07 14:39:13','','1',NULL,NULL),('20b432617478434fa636d43d8dd5863b','39_*_*','39_*_*-01-01-01','E661','W01','C1101','W01','B010109604002','经轴-LF60*LF60 173*156 118&quot;XC60319','419','B01201807010003','3635.00','U001','米','S203005002001',680,'E3610','2018-06-10 14:19:00','','E24','','',1,NULL,'2018-07-01 12:55:46',NULL,'2018-07-01 12:55:46','','0',NULL,NULL),('20c306552b6d4c4aad43500715974f8e','42_*_*','42_*_*-05-01-01','E13','W01','C1101','W01','B010104060001','经轴-LF60*LF60 173*156 118&quot;XY60245','666666','B01201808180004','10.00','U001','米','S203005007007',2,'E9999','2018-08-18 17:00:00','','','','',1,NULL,'2018-08-18 16:45:44',NULL,'2018-08-18 16:45:44','','0','洛阳','黑头'),('23615088d47f42be98e3b39a22de5794','39_*_*','39_*_*-01-01-01','E661','W01','C1101','W01','B010109604002','经轴-LF60*LF60 173*156 118&quot;XC60319','614','B01201807010008','3635.00','U001','米','S203005002001',680,'E3610','2018-06-10 14:19:00','','E24','','',1,NULL,'2018-07-01 12:56:29',NULL,'2018-07-01 12:56:29','','0',NULL,NULL),('242783af676546019725160b1d282a55','36_*_*','36_*_*-01-01-01','E661','W01','C1101','W01','B010109525001','经轴-LF60*LF60 173*130 106&quot;小叶子','1','B01201805300001','9974.00','U001','米','S203005002002',706,'E3610','2018-05-27 09:28:00','','E24','','',1,NULL,'2018-05-30 09:29:03',NULL,'2018-05-30 09:29:03','','0',NULL,NULL),('24879fa7f82446b3a06ac439aa725bb7','39_*_*','39_*_*-01-01-01','E661','W01','C1101','W01','B010109604002','经轴-LF60*LF60 173*156 118&quot;XC60319','5','B01201806070005','7651.00','U001','米','S203005002002',680,'E3290','2018-06-10 14:19:00','','E24','','',1,NULL,'2018-06-07 14:22:08',NULL,'2018-06-07 14:22:08','','1',NULL,NULL),('2504e5f31a594fbab641054cedced266','39_*_*','39_*_*-01-01-01','E661','W01','C1101','W01','B010109604002','经轴-LF60*LF60 173*156 118&quot;XC60319','1','B01201806070016','22953.00','U001','米','S203005002002',680,'E3290','2018-06-10 14:39:00','','E24','','',1,NULL,'2018-06-07 14:38:56',NULL,'2018-06-07 14:38:56','','1',NULL,NULL),('2627326fe54e44059284145ca71c7f39','60_*_*','60_*_*-01-01-01','E2546','W01','C1102','W01','B010107322001','经轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','294','B01201807240008','13225.00','','请选择','S203005007007',649,'E2728','2018-07-23 14:05:00','无','E15','','',1,NULL,'2018-07-24 14:05:44',NULL,'2018-07-24 14:05:44','','1',NULL,NULL),('2c49ab141554495da6744ebd29697935','60_*_*','60_*_*-01-01-01','E2546','W01','C1102','W01','B010107322001','经轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','426','B01201807240015','13225.00','U001','米','S203005007007',607,'E2728','2018-07-23 14:12:00','无','E15','','',1,NULL,'2018-07-24 14:11:34',NULL,'2018-07-24 14:11:34','','1',NULL,NULL),('3091ee0ea8054117bf96ffbae17869e3','60_*_*','60_*_*-01-01-01','E2546','W02','C1102','W01','B010107322001','经轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','569','B01201807240021','13225.00','U001','米','S203005007007',649,'E2728','2018-07-23 14:16:00','','','','',1,NULL,'2018-07-24 14:15:28',NULL,'2018-07-24 14:15:28','','0',NULL,NULL),('354d38c4120948929f7eba60b65cf7b6','39_*_*','39_*_*-01-01-01','E661','W01','C1101','W01','B010109604002','经轴-LF60*LF60 173*156 118&quot;XC60319','346','B01201807010013','3635.00','U001','米','S203005002001',681,'E3610','2018-06-10 14:19:00','','E24','','',1,NULL,'2018-07-01 12:57:12',NULL,'2018-07-01 12:57:12','','0',NULL,NULL),('35ed26d6d88e4c32a339c4404f5cf877','36_*_*','36_*_*-01-01-01','E661','W01','C1101','W01','B010109525001','经轴-LF60*LF60 173*130 106&quot;小叶子','7','B01201805300007','9974.00','U001','米','S203005002002',705,'E3610','2018-05-27 09:28:00','','E24','','',1,NULL,'2018-05-30 09:29:50',NULL,'2018-05-30 09:29:50','','0',NULL,NULL),('36c197e79b304781ac0f6e29cdf9f0c6','60_*_*','60_*_*-01-01-01','E2546','W01','C1102','W01','B010107322001','经轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','569','B01201807240002','13225.00','U001','米','S203005007007',649,'E2728','2018-07-23 14:02:00','无','E15','','',1,NULL,'2018-07-24 14:02:12',NULL,'2018-07-24 14:02:12','','1',NULL,NULL),('3bd2f54fe952408795a9a5c781b160da','60_*_*','60_*_*-01-01-01','E2546','W01','C1102','W01','B010107322001','经轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','274','B01201807240014','13225.00','','请选择','S203005007007',649,'E2728','2018-07-23 14:08:00','无','E15','','',1,NULL,'2018-07-24 14:08:07',NULL,'2018-07-24 14:08:07','','1',NULL,NULL),('3c3c2430369549c8b0b56c180dffef28','39_*_*','39_*_*-01-01-01','E661','W01','C1101','W01','B010109604002','经轴-LF60*LF60 173*156 118&quot;XC60319','13','B01201806070013','7651.00','U001','米','S203005002002',680,'E3290','2018-06-10 14:19:00','','E24','','',1,NULL,'2018-06-07 14:22:50',NULL,'2018-06-07 14:22:50','','1',NULL,NULL),('3d9959ad131e4f27a555c0d2921ce3ef','60_*_*','60_*_*-01-01-01','E2546','W01','C1102','W01','B010107322001','经轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','426','B01201807240030','13225.00','U001','米','S203005007007',607,'E2728','2018-07-23 14:21:00','','','','',1,NULL,'2018-07-24 14:22:17',NULL,'2018-07-24 14:22:17','','0',NULL,NULL),('410fc3d62c564d6a99f86e73128cce62','60_*_*','60_*_*-01-01-01','E2546','W02','C1102','W01','B010107322001','经轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','268','B01201807240024','13225.00','U001','米','S203005007007',649,'E2728','2018-07-23 14:16:00','','','','',1,NULL,'2018-07-24 14:15:50',NULL,'2018-07-24 14:15:50','','0',NULL,NULL),('4725e0bc9d44442bb1283ffae701be7b','60_*_*','60_*_*-01-01-01','E2546','W01','C1102','W01','B010107322001','经轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','294','B01201807240009','13225.00','','请选择','S203005007007',276,'E2728','2018-07-23 14:05:00','无','E15','','',1,NULL,'2018-07-24 14:05:52',NULL,'2018-07-24 14:05:52','','1',NULL,NULL),('489d4d84c8914f708398d687d3870a48','39_*_*','39_*_*-01-01-01','E661','W01','C1101','W01','B010109604002','经轴-LF60*LF60 173*156 118&quot;XC60319','118','B01201807010009','3635.00','U001','米','S203005002001',681,'E3610','2018-06-10 14:19:00','','E24','','',1,NULL,'2018-07-01 12:56:43',NULL,'2018-07-01 12:56:43','','0',NULL,NULL),('4d2cb6536a5b4fae8451183b8a7bfc01','39_*_*','39_*_*-01-01-01','E661','W01','C1101','W01','B010109604002','经轴-LF60*LF60 173*156 118&quot;XC60319','1','B01201806070001','7651.00','U001','米','S203005002002',680,'E3290','2018-06-10 14:19:00','','E24','','',1,NULL,'2018-06-07 14:21:46',NULL,'2018-06-07 14:21:46','','1',NULL,NULL),('4fb12c57cd4b4f41a9bee57dc276116d','36_*_*','36_*_*-01-01-01','E661','W01','C1101','W01','B010109525001','经轴-LF60*LF60 173*130 106&quot;小叶子','4','B01201805300004','9974.00','U001','米','S203005002002',705,'E3610','2018-05-27 09:28:00','','E24','','',1,NULL,'2018-05-30 09:29:34',NULL,'2018-05-30 09:29:34','','0',NULL,NULL),('539a28797b0445afa588e66ac6ce430c','60_*_*','60_*_*-01-01-01','E2546','W01','C1102','W01','B010107322001','经轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','294','B01201807240011','13225.00','','请选择','S203005007007',274,'E2728','2018-07-23 14:05:00','无','E15','','',1,NULL,'2018-07-24 14:06:08',NULL,'2018-07-24 14:06:08','','1',NULL,NULL),('5a0c130043354668b77cb9c6a1c017a3','39_*_*','39_*_*-01-01-01','E661','W01','C1101','W01','B010109604002','经轴-LF60*LF60 173*156 118&quot;XC60319','12','B01201806070012','7651.00','U001','米','S203005002002',680,'E3290','2018-06-10 14:19:00','','E24','','',1,NULL,'2018-06-07 14:22:44',NULL,'2018-06-07 14:22:44','','1',NULL,NULL),('5c7e65f27c724174bb4a65dddee7d07b','60_*_*','60_*_*-01-01-01','E2546','W01','C1102','W01','B010107322001','经轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','300','B01201807240001','13225.00','U001','米','S203005007007',649,'E2728','2018-07-23 14:02:00','无','E15','','',1,NULL,'2018-07-24 14:02:00',NULL,'2018-07-24 14:02:00','','1',NULL,NULL),('5d9f048f6d7a44ebb8eac7141f7b069a','39_*_*','39_*_*-01-01-01','E661','W01','C1101','W01','B010109604002','经轴-LF60*LF60 173*156 118&quot;XC60319','4','B01201806070004','7651.00','U001','米','S203005002002',680,'E3290','2018-06-10 14:19:00','','E24','','',1,NULL,'2018-06-07 14:22:03',NULL,'2018-06-07 14:22:03','','1',NULL,NULL),('61199528a439432bb6404d7d2d6336d3','60_*_*','60_*_*-01-01-01','E2546','W01','C1102','W01','B010107322001','经轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','276','B01201807240012','13225.00','','请选择','S203005007007',649,'E2728','2018-07-23 14:08:00','无','E15','','',1,NULL,'2018-07-24 14:07:51',NULL,'2018-07-24 14:07:51','','1',NULL,NULL),('61ed06821cc14481b9795e820dd8d1bc','36_*_*','36_*_*-01-01-01','E661','W01','C1101','W01','B010109525001','经轴-LF60*LF60 173*130 106&quot;小叶子','8','B01201805300008','9974.00','U001','米','S203005002002',705,'E3610','2018-05-27 09:28:00','','E24','','',1,NULL,'2018-05-30 09:29:55',NULL,'2018-05-30 09:29:55','','0',NULL,NULL),('635ec8be46254d43b4b0829acf415d50','39_*_*','39_*_*-01-01-01','E661','W01','C1101','W01','B010109604002','经轴-LF60*LF60 173*156 118&quot;XC60319','131','B01201807010005','3635.00','U001','米','S203005002001',680,'E3610','2018-06-10 14:19:00','','E24','','',1,NULL,'2018-07-01 12:56:01',NULL,'2018-07-01 12:56:01','','0',NULL,NULL),('641f5bd2f588401ebacf071b0258b43e','42_*_*','42_*_*-05-01-01','E13','W01','C1101','W01','B010104060001','经轴-LF60*LF60 173*156 118&quot;XY60245','123456','B01201808180002','10.00','','请选择','S203005007007',2,'E9999','2018-08-18 17:00:00','','','','',1,NULL,'2018-08-18 16:34:26',NULL,'2018-08-18 16:34:26','','0','洛阳','黑头'),('67bb5a4368f04b01b2e034d4b895203f','60_*_*','60_*_*-01-01-01','E2546','W01','C1102','W01','B010107322001','经轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','13','B01201807240004','13225.00','U001','米','S203005007007',649,'E2728','2018-07-23 14:02:00','无','E15','','',1,NULL,'2018-07-24 14:02:28',NULL,'2018-07-24 14:02:28','','1',NULL,NULL),('685534617d36455fb11310042fca022c','60_*_*','60_*_*-01-01-01','E2546','W01','C1102','W01','B010107322001','经轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','3','B01201807240013','13225.00','','请选择','S203005007007',649,'E2728','2018-07-23 14:08:00','无','E15','','',1,NULL,'2018-07-24 14:07:59',NULL,'2018-07-24 14:07:59','','1',NULL,NULL),('6a7c23db7f3d4910836513f5bd19b367','36_*_*','36_*_*-01-01-01','E661','W01','C1101','W01','B010109525001','经轴-LF60*LF60 173*130 106&quot;小叶子','10','B01201805300010','9974.00','U001','米','S203005002002',705,'E3610','2018-05-27 09:28:00','','E24','','',1,NULL,'2018-05-30 09:30:06',NULL,'2018-05-30 09:30:06','','0',NULL,NULL),('6c85368768374ad49809948d589f6237','60_*_*','60_*_*-01-01-01','E2546','W01','C1102','W01','B010107322001','经轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','3','B01201807240018','13225.00','U001','米','S203005007007',649,'E2728','2018-07-23 14:12:00','无','E15','','',1,NULL,'2018-07-24 14:12:02',NULL,'2018-07-24 14:12:02','','1',NULL,NULL),('6d304759747c41dda44270b737b6f6f3','60_*_*','60_*_*-01-01-01','E2546','W02','C1102','W01','B010107322001','经轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','13','B01201807240023','13225.00','U001','米','S203005007007',649,'E2728','2018-07-23 14:16:00','','','','',1,NULL,'2018-07-24 14:15:43',NULL,'2018-07-24 14:15:43','','0',NULL,NULL),('6e98b40d76364717a28ddb54605ee490','39_*_*','39_*_*-01-01-01','E661','W01','C1101','W01','B010109604002','经轴-LF60*LF60 173*156 118&quot;XC60319','15','B01201806070015','7651.00','U001','米','S203005002002',680,'E3290','2018-06-10 14:19:00','','E24','','',1,NULL,'2018-06-07 14:22:59',NULL,'2018-06-07 14:22:59','','1',NULL,NULL),('6fd4204caf9a4a6dbeebcac8dfc380f4','36_*_*','36_*_*-01-01-01','E661','W01','C1101','W01','B010109525001','经轴-LF60*LF60 173*130 106&quot;小叶子','12','B01201805300012','9974.00','U001','米','S203005002002',705,'E3610','2018-05-27 09:28:00','','E24','','',1,NULL,'2018-05-30 09:30:16',NULL,'2018-05-30 09:30:16','','0',NULL,NULL),('71b95f13a5cb40e09547e7ec43f78d18','39_*_*','39_*_*-01-01-01','E661','W01','C1101','W01','B010109604002','经轴-LF60*LF60 173*156 118&quot;XC60319','5','B01201806070020','22953.00','U001','米','S203005002002',680,'E3290','2018-06-10 14:39:00','','E24','','',1,NULL,'2018-06-07 14:39:19',NULL,'2018-06-07 14:39:19','','1',NULL,NULL),('7c66ad76a1a54083ab837635e71c3ec5','39_*_*','39_*_*-01-01-01','E661','W01','C1101','W01','B010109604002','经轴-LF60*LF60 173*156 118&quot;XC60319','14','B01201806070014','7651.00','U001','米','S203005002002',680,'E3290','2018-06-10 14:19:00','','E24','','',1,NULL,'2018-06-07 14:22:54',NULL,'2018-06-07 14:22:54','','1',NULL,NULL),('7f44882e2b0b461eb5c1e7349246d566','31_*_*','31_*_*-05-01-01','E6035','W01','C1103','W01','B010102030001','经轴-T/JC（30+30）&times;20   132&times;62   63&rdquo; 双经布（双幅）','123','B01201805230001','77700.00','U001','米','S203005003003',69,'E1400','2018-05-16 09:56:00','','E556','','',1,NULL,'2018-05-23 09:58:54',NULL,'2018-05-23 09:58:54','','1',NULL,NULL),('7ffe8973b9dd43cc81d54b4d52be7225','39_*_*','39_*_*-01-01-01','E661','W01','C1101','W01','B010109604002','经轴-LF60*LF60 173*156 118&quot;XC60319','308','B01201807010010','3635.00','U001','米','S203005002001',681,'E3610','2018-06-10 14:19:00','','E24','','',1,NULL,'2018-07-01 12:56:50',NULL,'2018-07-01 12:56:50','','0',NULL,NULL),('8021aaaf7c1e426a8d834c7f12b027fc','39_*_*','39_*_*-01-01-01','E661','W01','C1101','W01','B010109604002','经轴-LF60*LF60 173*156 118&quot;XC60319','2','B01201806070017','22953.00','U001','米','S203005002002',680,'E3290','2018-06-10 14:39:00','','E24','','',1,NULL,'2018-06-07 14:39:03',NULL,'2018-06-07 14:39:03','','1',NULL,NULL),('85cbb52cd7a24b46994ac434523c0343','60_*_*','60_*_*-01-01-01','E2546','W01','C1102','W01','B010107322001','经轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','276','B01201807240017','13225.00','U001','米','S203005007007',649,'E2728','2018-07-23 14:12:00','无','E15','','',1,NULL,'2018-07-24 14:11:55',NULL,'2018-07-24 14:11:55','','1',NULL,NULL),('895e70552c184f98b7b6a3f94995765b','39_*_*','39_*_*-01-01-01','E661','W01','C1101','W01','B010109604002','经轴-LF60*LF60 173*156 118&quot;XC60319','11','B01201806070011','7651.00','U001','米','S203005002002',680,'E3290','2018-06-10 14:19:00','','E24','','',1,NULL,'2018-06-07 14:22:38',NULL,'2018-06-07 14:22:38','','1',NULL,NULL),('90f8c07c9640405b9e726a40452d5138','60_*_*','60_*_*-01-01-01','E2546','W02','C1102','W01','B010107322001','经轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','245','B01201807240022','13225.00','U001','米','S203005007007',649,'E2728','2018-07-23 14:16:00','','','','',1,NULL,'2018-07-24 14:15:36',NULL,'2018-07-24 14:15:36','','0',NULL,NULL),('9409afb71a0f498e885e71d0d2cb8b3f','39_*_*','39_*_*-01-01-01','E661','W01','C1101','W01','B010109604002','经轴-LF60*LF60 173*156 118&quot;XC60319','6','B01201806070021','22953.00','U001','米','S203005002002',680,'E3290','2018-06-10 14:39:00','','E24','','',1,NULL,'2018-06-07 14:39:24',NULL,'2018-06-07 14:39:24','','1',NULL,NULL),('968c682c2c7b478e86e030897959240f','36_*_*','36_*_*-02-01-01','E6043','W01','C1101','W01','B010109525001','经轴-LF60*LF60 173*130 106&quot;小叶子','123','B01201806040002','500.00','','请选择','S203005001001',4,'E6043','2018-06-04 15:00:00','无','E13','','',1,NULL,'2018-06-04 14:39:42',NULL,'2018-06-04 14:39:42','','1',NULL,NULL),('9f07af03e63542bab4658ee800bb1009','36_*_*','36_*_*-01-01-01','E661','W01','C1101','W01','B010109525001','经轴-LF60*LF60 173*130 106&quot;小叶子','3','B01201805300003','9974.00','U001','米','S203005002002',705,'E3610','2018-05-27 09:28:00','','E24','','',1,NULL,'2018-05-30 09:29:28',NULL,'2018-05-30 09:29:28','','0',NULL,NULL),('a36ca40f924340a3bb2bdaf35b8acb41','39_*_*','39_*_*-01-01-01','E661','W01','C1101','W01','B010109604002','经轴-LF60*LF60 173*156 118&quot;XC60319','6','B01201806070006','7651.00','U001','米','S203005002002',680,'E3290','2018-06-10 14:19:00','','E24','','',1,NULL,'2018-06-07 14:22:13',NULL,'2018-06-07 14:22:13','','1',NULL,NULL),('a6b311e89c874e66a88ee1b96af2105e','60_*_*','60_*_*-01-01-01','E2546','W01','C1102','W01','B010107322001','经轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','294','B01201807240016','13225.00','U001','米','S203005007007',649,'E2728','2018-07-23 14:12:00','无','E15','','',1,NULL,'2018-07-24 14:11:48',NULL,'2018-07-24 14:11:48','','1',NULL,NULL),('ae87ce809d0e473e8e38c5078a901711','36_*_*','36_*_*-01-01-01','E661','W01','C1101','W01','B010109525001','经轴-LF60*LF60 173*130 106&quot;小叶子','9','B01201805300009','9974.00','U001','米','S203005002002',705,'E3610','2018-05-27 09:28:00','','E24','','',1,NULL,'2018-05-30 09:30:00',NULL,'2018-05-30 09:30:00','','0',NULL,NULL),('af081410a7184a83a9b8a98e29f7bebb','39_*_*','39_*_*-01-01-01','E661','W01','C1101','W01','B010109604002','经轴-LF60*LF60 173*156 118&quot;XC60319','8','B01201806070008','7651.00','U001','米','S203005002002',680,'E3290','2018-06-10 14:19:00','','E24','','',1,NULL,'2018-06-07 14:22:23',NULL,'2018-06-07 14:22:23','','1',NULL,NULL),('b024d84737fa46b28ea4a6b1dc070998','60_*_*','60_*_*-01-01-01','E2546','W02','C1102','W01','B010107322001','经轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','276','B01201807240026','13225.00','U001','米','S203005007007',649,'E2728','2018-07-23 14:16:00','','','','',1,NULL,'2018-07-24 14:16:07',NULL,'2018-07-24 14:16:07','','0',NULL,NULL),('be7219174a164fe9b4473ac7306c6b04','39_*_*','39_*_*-01-01-01','E661','W01','C1101','W01','B010109604002','经轴-LF60*LF60 173*156 118&quot;XC60319','372','B01201807010012','3635.00','U001','米','S203005002001',681,'E3610','2018-06-10 14:19:00','','E24','','',1,NULL,'2018-07-01 12:57:05',NULL,'2018-07-01 12:57:05','','0',NULL,NULL),('c092c22025b040b8b81abbf38bdde47c','39_*_*','39_*_*-01-01-01','E661','W01','C1101','W01','B010109604002','经轴-LF60*LF60 173*156 118&quot;XC60319','88','B01201807010004','3635.00','U001','米','S203005002001',680,'E3610','2018-06-10 14:19:00','','E24','','',1,NULL,'2018-07-01 12:55:53',NULL,'2018-07-01 12:55:53','','0',NULL,NULL),('c7ec3e0764a44452b0b030714625252c','39_*_*','39_*_*-01-01-01','E661','W01','C1101','W01','B010109604002','经轴-LF60*LF60 173*156 118&quot;XC60319','150','B01201807010015','3635.00','U001','米','S203005002001',681,'E3610','2018-06-10 14:19:00','','E24','','',1,NULL,'2018-07-01 12:57:26',NULL,'2018-07-01 12:57:26','','0',NULL,NULL),('c864b348ba7f4fcca259a0473bfc392b','36_*_*','36_*_*-01-01-01','E661','W01','C1101','W01','B010109525001','经轴-LF60*LF60 173*130 106&quot;小叶子','5','B01201805300005','9974.00','U001','米','S203005002002',705,'E3610','2018-05-27 09:28:00','','E24','','',1,NULL,'2018-05-30 09:29:39',NULL,'2018-05-30 09:29:39','','0',NULL,NULL),('ce3c3b0e06444fa78a927a6c67546420','39_*_*','39_*_*-01-01-01','E661','W01','C1101','W01','B010109604002','经轴-LF60*LF60 173*156 118&quot;XC60319','3','B01201806070018','22953.00','U001','米','S203005002002',680,'E3290','2018-06-10 14:39:00','','E24','','',1,NULL,'2018-06-07 14:39:08',NULL,'2018-06-07 14:39:08','','1',NULL,NULL),('d04772047f604ecd807949a90dfe8cee','60_*_*','60_*_*-01-01-01','E2546','W02','C1102','W01','B010107322001','经轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','294','B01201807240025','13225.00','U001','米','S203005007007',649,'E2728','2018-07-23 14:16:00','','','','',1,NULL,'2018-07-24 14:16:00',NULL,'2018-07-24 14:16:00','','0',NULL,NULL),('d4b20f6b466941778beed7e820a96d7e','60_*_*','60_*_*-01-01-01','E2546','W02','C1102','W01','B010107322001','经轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','300','B01201807240020','13225.00','U001','米','S203005007007',649,'E2728','2018-07-23 14:16:00','','','','',1,NULL,'2018-07-24 14:15:21',NULL,'2018-07-24 14:15:21','','0',NULL,NULL),('d6897817debb4b5cb0a7e6605c63878b','39_*_*','39_*_*-01-01-01','E661','W01','C1101','W01','B010109604002','经轴-LF60*LF60 173*156 118&quot;XC60319','236','B01201807010011','3635.00','U001','米','S203005002001',681,'E3610','2018-06-10 14:19:00','','E24','','',1,NULL,'2018-07-01 12:56:56',NULL,'2018-07-01 12:56:56','','0',NULL,NULL),('da9827737d6e409fa1309ec2bf062021','42_*_*','42_*_*-04-01-01','E2509','W01','C1101','W01','B010104060001','经轴-LF60*LF60 173*156 118&quot;XY60245','x456','B01201808020001','50.00','U001','米','S203005006006',1,'E2854','2018-06-10 20:00:00','','E9988','','E4199',1,NULL,'2018-08-02 16:35:36',NULL,'2018-08-02 16:35:36','','0',NULL,NULL),('e24786549387498db8c9211e698a3892','39_*_*','39_*_*-01-01-01','E661','W01','C1101','W01','B010109604002','经轴-LF60*LF60 173*156 118&quot;XC60319','536','B01201807010007','3635.00','U001','米','S203005002001',680,'E3610','2018-06-10 14:19:00','','E24','','',1,NULL,'2018-07-01 12:56:19',NULL,'2018-07-01 12:56:19','','0',NULL,NULL),('e6865a6c780f4d02ac407bb33702e6ab','39_*_*','39_*_*-01-01-01','E661','W01','C1101','W01','B010109604002','经轴-LF60*LF60 173*156 118&quot;XC60319','3','B01201806070003','7651.00','U001','米','S203005002002',680,'E3290','2018-06-10 14:19:00','','E24','','',1,NULL,'2018-06-07 14:21:57',NULL,'2018-06-07 14:21:57','','1',NULL,NULL),('f02fc68e9d41455288dfffcfe3fbd245','39_*_*','39_*_*-01-01-01','E661','W01','C1101','W01','B010109604002','经轴-LF60*LF60 173*156 118&quot;XC60319','424','B01201807010014','3635.00','U001','米','S203005002001',681,'E3610','2018-06-10 14:19:00','','E24','','',1,NULL,'2018-07-01 12:57:19',NULL,'2018-07-01 12:57:19','','0',NULL,NULL),('f097149b915d435cb11edba5bbb3ef5d','42_*_*','42_*_*-05-01-01','E13','W01','C1101','W01','B010104060001','经轴-LF60*LF60 173*156 118&quot;XY60245','123456','B01201808180003','10.00','U001','米','S203005007007',2,'E9999','2018-08-18 17:00:00','','','','',1,NULL,'2018-08-18 16:35:32',NULL,'2018-08-18 16:35:32','','0','洛阳','黑头'),('f0b668302d104e398e6ea231c72065f2','60_*_*','60_*_*-01-01-01','E2546','W02','C1102','W01','B010107322001','经轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','274','B01201807240028','13225.00','U001','米','S203005007007',649,'E2728','2018-07-23 14:16:00','','','','',1,NULL,'2018-07-24 14:16:19',NULL,'2018-07-24 14:16:19','','0',NULL,NULL),('f264bf61d72c46e3a49d364813d5cd03','60_*_*','60_*_*-01-01-01','E2546','W01','C1102','W01','B010107322001','经轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','294','B01201807240010','13225.00','','请选择','S203005007007',3,'E2728','2018-07-23 14:05:00','无','E15','','',1,NULL,'2018-07-24 14:05:59',NULL,'2018-07-24 14:05:59','','1',NULL,NULL),('f51d5e620abe470c98782b9ede97070a','39_*_*','39_*_*-01-01-01','E661','W01','C1101','W01','B010109604002','经轴-LF60*LF60 173*156 118&quot;XC60319','7','B01201806070007','7651.00','U001','米','S203005002002',680,'E3290','2018-06-10 14:19:00','','E24','','',1,NULL,'2018-06-07 14:22:18',NULL,'2018-06-07 14:22:18','','1',NULL,NULL),('f5f19c25521f49f98cdf92bd3add7c9a','39_*_*','39_*_*-01-01-01','E661','W01','C1101','W01','B010109604002','经轴-LF60*LF60 173*156 118&quot;XC60319','559','B01201807010002','3635.00','U001','米','S203005002001',680,'E3610','2018-06-10 14:19:00','','E24','','',1,NULL,'2018-07-01 12:55:38',NULL,'2018-07-01 12:55:38','','0',NULL,NULL),('fa68e7cbfd744f78be7fc95c604c29db','36_*_*','36_*_*-01-01-01','E661','W01','C1101','W01','B010109525001','经轴-LF60*LF60 173*130 106&quot;小叶子','2','B01201805300002','9974.00','U001','米','S203005002002',706,'E3610','2018-05-27 09:28:00','','E24','','',1,NULL,'2018-05-30 09:29:12',NULL,'2018-05-30 09:29:12','','0',NULL,NULL);

/*Table structure for table `job_weaving_record` */

DROP TABLE IF EXISTS `job_weaving_record`;

CREATE TABLE `job_weaving_record` (
  `id` char(32) NOT NULL,
  `erp_bill_no` varchar(64) DEFAULT NULL COMMENT '订单号',
  `task_id` varchar(50) NOT NULL,
  `workshop` varchar(50) NOT NULL COMMENT '车间',
  `work_center` varchar(50) DEFAULT NULL COMMENT '工作中心',
  `machine_code` varchar(50) NOT NULL,
  `weaving_worker` varchar(32) NOT NULL,
  `work_class` varchar(10) NOT NULL,
  `work_team` varchar(50) DEFAULT NULL COMMENT '班组',
  `variety` varchar(50) NOT NULL,
  `material_name` varchar(200) DEFAULT NULL COMMENT '品名',
  `change_duty` varchar(20) DEFAULT NULL,
  `change_duty_worker` varchar(32) DEFAULT NULL,
  `shaft_code` varchar(50) DEFAULT NULL,
  `serial_no` varchar(50) DEFAULT NULL COMMENT ' 序列号',
  `downer` varchar(32) DEFAULT NULL,
  `down_time` datetime DEFAULT NULL,
  `number` decimal(20,2) NOT NULL,
  `unit` varchar(10) DEFAULT NULL,
  `unit_name` varchar(20) DEFAULT NULL COMMENT '单位名称',
  `receiver` varchar(32) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `del_flag` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `job_weaving_record` */

insert  into `job_weaving_record`(`id`,`erp_bill_no`,`task_id`,`workshop`,`work_center`,`machine_code`,`weaving_worker`,`work_class`,`work_team`,`variety`,`material_name`,`change_duty`,`change_duty_worker`,`shaft_code`,`serial_no`,`downer`,`down_time`,`number`,`unit`,`unit_name`,`receiver`,`status`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) values ('01f7757a8021401cbb0eeac8784fbd10','61_*_*','61_*_*-02-02-02','C13','W13','S201004209511','E778','W02','C1301','B040107322001','布轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','','','01','B04201807250012','E3655','2018-07-25 20:00:00','6.00','U005','匹','',1,NULL,'2018-07-25 17:21:15',NULL,'2018-07-25 17:21:15','','0'),('07a2c382910241e89d536b49bc9865be','61_*_*','61_*_*-02-02-01','C13','W13','S201004209511','E2220','W01','C1303','B040107322001','布轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','','','3','B04201807250007','E760','2018-07-25 14:00:00','6.00','U005','匹','',1,NULL,'2018-07-25 11:10:31',NULL,'2018-07-25 11:10:31','','0'),('102a57aec6484d7b9fcb98546f33267f','34_*_*','34_*_*-02-02-01','C12','W09','S201000501041','E1471','W01','C1202','B040509525001','布轴-LF60*LF60 173*130 106&quot;小叶子','','','3','B04201806140005','E1471','2018-06-13 15:40:00','6.00','U005','匹','',1,NULL,'2018-06-14 11:49:45',NULL,'2018-06-14 11:49:45','','0'),('155165cc82e840c3a1299ddb331dabbe','61_*_*','61_*_*-02-01-02','C13','W13','S201004109495','E778','W02','C1301','B040107322001','布轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','','','1','B04201807260004','E3655','2018-07-27 02:00:00','6.00','U005','匹','',1,NULL,'2018-07-26 13:37:01',NULL,'2018-07-26 13:37:01','','0'),('1e122d51bb1645809bf0d0173cd98458','34_*_*','34_*_*-02-01-03','C12','W09','S201000401031','E1738','W03','C1201','B040509525001','布轴-LF60*LF60 173*130 106&quot;小叶子','','','1','B04201806140003','E1738','2018-06-08 06:00:00','3.00','U005','匹','',1,NULL,'2018-06-14 11:45:22',NULL,'2018-06-14 11:45:22','','0'),('215faa7cffa141da85342ad28fad45e1','61_*_*','61_*_*-02-01-02','C13','W13','S201004109495','E778','W02','C1301','B040107322001','布轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','','','04','B04201807250015','E3655','2018-07-25 23:00:00','6.00','U005','匹','',1,NULL,'2018-07-25 17:25:15',NULL,'2018-07-25 17:25:15','','0'),('21da77c7d8ef4e5f9dadf4d8cfc68d07','61_*_*','61_*_*-02-01-03','C13','W13','S201004109495','E1402','W03','C1302','B040107322001','布轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','','','1','B04201807270004','E773','2018-07-27 14:00:00','6.00','U005','匹','',1,NULL,'2018-07-27 13:05:34',NULL,'2018-07-27 13:05:34','','0'),('241a39b99b5b4cc6a8f54b29a8414a3a','61_*_*','61_*_*-02-04-02','C13','W13','S201004409543','E778','W02','C1301','B040107322001','布轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','','','2','B04201807260002','E3655','2018-07-26 21:00:00','6.00','U005','匹','',1,NULL,'2018-07-26 13:28:38',NULL,'2018-07-26 13:28:38','','0'),('2683a8eeabed40e7838d10f966c4df12','28_*_*','28_*_*-02-01-01','C12','W10','S201001509167','E1354','W01','C1203','B040302030001','布轴-T/JC（30+30）&times;20   132&times;62   63&rdquo; 双经布（双幅）','','','325','B04201805230005','E1687','2018-05-23 16:14:00','3.00','U005','匹','E65',1,NULL,'2018-05-23 16:14:55',NULL,'2018-05-23 16:14:55','','1'),('2a5f56a619fa4c1b989b01bee202d0b7','28_*_*','28_*_*-02-01-01','C12','W10','S201001509167','E1354','W01','C1203','B040302030001','布轴-T/JC（30+30）&times;20   132&times;62   63&rdquo; 双经布（双幅）','','','341','B04201805230011','E1687','2018-05-23 16:14:00','3.00','U005','匹','E65',1,NULL,'2018-05-23 16:15:27',NULL,'2018-05-23 16:15:27','','1'),('32cec003dcf7419294469b6559c10a44','34_*_*','34_*_*-02-02-03','C12','W09','S201000501041','E1738','W03','C1201','B040509525001','布轴-LF60*LF60 173*130 106&quot;小叶子','','','3','B04201807040003','E1738','2018-06-17 13:23:00','6.00','U005','匹','',1,NULL,'2018-07-04 16:22:18',NULL,'2018-07-04 16:22:18','','0'),('393b64fda90e47c6ba3259cceeb17b99','28_*_*','28_*_*-02-01-01','C12','W10','S201001509167','E1354','W01','C1203','B040302030001','布轴-T/JC（30+30）&times;20   132&times;62   63&rdquo; 双经布（双幅）','','','328','B04201805230008','E1687','2018-05-23 16:14:00','3.00','U005','匹','E65',1,NULL,'2018-05-23 16:15:15',NULL,'2018-05-23 16:15:15','','1'),('3ebab3757fa84ceb90abecd88d25bd59','34_*_*','34_*_*-02-02-03','C12','W09','S201000501041','E1738','W03','C1201','B040509525001','布轴-LF60*LF60 173*130 106&quot;小叶子','','','12','B04201807040009','E1738','2018-06-26 18:09:00','6.00','U005','匹','',1,NULL,'2018-07-04 16:41:49',NULL,'2018-07-04 16:41:49','','0'),('4fbc50a8bfa54b848a9cdab42636a943','61_*_*','61_*_*-02-03-03','C13','W13','S201004309527','E1402','W03','C1302','B040107322001','布轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','','','1','B04201807260001','E773','2018-07-25 11:00:00','6.00','U005','匹','',1,NULL,'2018-07-26 08:13:42',NULL,'2018-07-26 08:13:42','','0'),('54412625f34d42b9808eea4b88ca58b8','34_*_*','34_*_*-02-01-03','C12','W09','S201000401031','E1738','W03','C1201','B040509525001','布轴-LF60*LF60 173*130 106&quot;小叶子','','','12','B04201806140001','E1738','2018-06-13 10:44:00','6.00','U005','匹','',1,NULL,'2018-06-14 10:43:58',NULL,'2018-06-14 10:43:58','','1'),('56d8285749fb4d70a98a126ee90750f1','61_*_*','61_*_*-02-04-01','C13','W13','S201004409543','E2174','W01','C1303','B040107322001','布轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','','','3','B04201807250003','E760','2018-07-25 15:00:00','120.00','U004','码','',1,NULL,'2018-07-25 10:55:10',NULL,'2018-07-25 10:55:10','','1'),('5c70e6b3b2d0484fab93a8cd3c28c3da','61_*_*','61_*_*-02-04-02','C13','W13','S201004409543','E778','W02','C1301','B040107322001','布轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','','','02','B04201807250014','E3655','2018-07-25 20:00:00','6.00','U005','匹','',1,NULL,'2018-07-25 17:23:24',NULL,'2018-07-25 17:23:24','','0'),('60548a1a3a934bdca54d218f7af14aa0','34_*_*','34_*_*-02-01-03','C12','W09','S201000401031','E1738','W03','C1201','B040509525001','布轴-LF60*LF60 173*130 106&quot;小叶子','','','11','B04201807040008','E1738','2018-06-22 06:14:00','6.00','U005','匹','',1,NULL,'2018-07-04 16:41:03',NULL,'2018-07-04 16:41:03','','0'),('62c98e8c9f824e518de3e4790ee07587','61_*_*','61_*_*-02-02-01','C13','W13','S201004209511','E760','W01','C1303','B040107322001','布轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','','','4','B04201807250004','E760','2018-07-25 16:00:00','6.00','U005','匹','',1,NULL,'2018-07-25 11:03:35',NULL,'2018-07-25 11:03:35','','0'),('62ed3aeb9f3a47e6a2f0dc939549f071','34_*_*','34_*_*-02-02-03','C12','W09','S201000501041','E1738','W03','C1201','B040509525001','布轴-LF60*LF60 173*130 106&quot;小叶子','','','4','B04201807040004','E1738','2018-07-26 18:23:00','6.00','U005','匹','',1,NULL,'2018-07-04 16:22:56',NULL,'2018-07-04 16:22:56','','1'),('6b341c24e8474499ae2762a8f21d965a','28_*_*','28_*_*-04-01-01','C12','W06','S208000100001','E85','W01','C1204','B040302030001','布轴-T/JC（30+30）&times;20   132&times;62   63&rdquo; 双经布（双幅）','','','123','B04201806040001','E85','2018-06-04 20:19:00','600.00','U001','米','',1,NULL,'2018-06-04 20:20:06',NULL,'2018-06-04 20:20:06','','1'),('6cf754ac216d4468b15ab2e2abbb92bb','34_*_*','34_*_*-02-01-01','C12','W09','S201000401031','E1471','W01','C1202','B040509525001','布轴-LF60*LF60 173*130 106&quot;小叶子','','','4','B04201806150001','E1471','2018-06-14 17:40:00','9.00','U005','匹','',1,NULL,'2018-06-15 14:27:35',NULL,'2018-06-15 14:27:35','','0'),('6ec475bc6b7e4233a46a9de6aef09094','61_*_*','61_*_*-02-02-02','C13','W13','S201004209511','E778','W02','C1301','B040107322001','布轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','','','1','B04201807250011','E3655','2018-07-25 16:56:00','6.00','U005','匹','',1,NULL,'2018-07-25 16:51:03',NULL,'2018-07-25 16:51:03','','0'),('6f59ca4a118a4161bc0d640cf95cfc52','34_*_*','34_*_*-02-01-01','C12','W09','S201000401031','E1471','W01','C1202','B040509525001','布轴-LF60*LF60 173*130 106&quot;小叶子','','','2','B04201806140004','E1471','2018-06-08 15:00:00','3.00','U005','匹','',1,NULL,'2018-06-14 11:48:56',NULL,'2018-06-14 11:48:56','','0'),('71ee369b0c114478a527475791e4186e','28_*_*','28_*_*-02-01-01','C12','W10','S201001509167','E1354','W01','C1203','B040302030001','布轴-T/JC（30+30）&times;20   132&times;62   63&rdquo; 双经布（双幅）','','','880','B04201805240001','E1687','2018-05-24 08:59:00','12.00','U005','匹','',1,NULL,'2018-05-24 09:02:58',NULL,'2018-05-24 09:02:58','','1'),('762bcdbfe7d54ff991e58cd8e0c8f0e6','28_*_*','28_*_*-02-01-01','C12','W10','S201001509167','E1354','W01','C1203','B040302030001','布轴-T/JC（30+30）&times;20   132&times;62   63&rdquo; 双经布（双幅）','','','329','B04201805230009','E1687','2018-05-23 16:14:00','3.00','U005','匹','E65',1,NULL,'2018-05-23 16:15:20',NULL,'2018-05-23 16:15:20','','1'),('763294f9d78d495d8c7ad15f09c0720b','34_*_*','34_*_*-02-02-02','C12','W09','S201000501041','E1687','W02','C1203','B040509525001','布轴-LF60*LF60 173*130 106&quot;小叶子','','','7','B04201807040007','E1687','2018-06-21 21:29:00','6.00','U005','匹','',1,NULL,'2018-07-04 16:28:36',NULL,'2018-07-04 16:28:36','','0'),('80866864fd624637ae8a78b7ae1f4019','61_*_*','61_*_*-02-03-01','C13','W13','S201004309527','E2220','W01','C1303','B040107322001','布轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','','','1','B04201807250005','E760','2018-07-25 13:00:00','6.00','U005','匹','',1,NULL,'2018-07-25 11:09:36',NULL,'2018-07-25 11:09:36','','0'),('85fce3e36e4d450ea927eb2c3d013174','34_*_*','34_*_*-02-02-01','C12','W09','S201000501041','E1471','W01','C1202','B040509525001','布轴-LF60*LF60 173*130 106&quot;小叶子','','','13','B04201807040010','E1471','2018-07-02 18:09:00','9.00','U005','匹','',1,NULL,'2018-07-04 16:43:54',NULL,'2018-07-04 16:43:54','','0'),('88701e604ceb468d8dd00e946bac1ac1','28_*_*','28_*_*-02-01-01','C12','W10','S201001509167','E1354','W01','C1203','B040302030001','布轴-T/JC（30+30）&times;20   132&times;62   63&rdquo; 双经布（双幅）','','','322','B04201805230002','E1687','2018-05-23 16:14:00','3.00','U005','匹','E65',1,NULL,'2018-05-23 16:14:00',NULL,'2018-05-23 16:14:00','','1'),('8954d08f80794f1ea540d6c5bc77d06f','61_*_*','61_*_*-02-04-03','C13','W13','S201004409543','E1402','W03','C1302','B040107322001','布轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','','','2','B04201807270002','E773','2018-07-27 14:00:00','6.00','U005','匹','',1,NULL,'2018-07-27 13:03:00',NULL,'2018-07-27 13:03:00','','0'),('9241ad4164d3431cb2c501f9290e5357','28_*_*','28_*_*-02-01-01','C12','W10','S201001509167','E1354','W01','C1203','B040302030001','布轴-T/JC（30+30）&times;20   132&times;62   63&rdquo; 双经布（双幅）','','','323','B04201805230003','E1687','2018-05-23 16:14:00','3.00','U005','匹','E65',1,NULL,'2018-05-23 16:14:19',NULL,'2018-05-23 16:14:19','','1'),('9797b319dc764049a75cef061d4fde23','61_*_*','61_*_*-02-02-02','C13','W13','S201004209511','E778','W02','C1301','B040107322001','布轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','','','1','B04201807260003','E3655','2018-07-26 22:00:00','6.00','U005','匹','',1,NULL,'2018-07-26 13:32:28',NULL,'2018-07-26 13:32:28','','0'),('9c8e39eca573451eaf9f09303031c93c','61_*_*','61_*_*-02-01-01','C13','W13','S201004109495','E2220','W01','C1303','B040107322001','布轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','','','2','B04201807250006','E760','2018-07-25 15:00:00','6.00','U005','匹','',1,NULL,'2018-07-25 11:10:06',NULL,'2018-07-25 11:10:06','','0'),('9ff3c16676a244008a5fb72727547697','61_*_*','61_*_*-02-02-01','C13','W13','S201004209511','E760','W01','C1303','B040107322001','布轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','','','1','B04201807250001','E760','2018-07-25 13:27:00','6.00','U005','匹','',1,NULL,'2018-07-25 09:27:19',NULL,'2018-07-25 09:27:19','','0'),('a2bc7502878f40f6b1da590414653f42','61_*_*','61_*_*-02-03-02','C13','W13','S201004309527','E778','W02','C1301','B040107322001','布轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','','','1','B04201807250009','E3655','2018-07-26 13:40:00','6.00','U005','匹','',1,NULL,'2018-07-25 13:41:55',NULL,'2018-07-25 13:41:55','','1'),('ad4ffe3ebeae4a90b53172186810f0d2','61_*_*','61_*_*-02-02-03','C13','W13','S201004209511','E1402','W03','C1302','B040107322001','布轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','','','1','B04201807270006','E773','2018-07-27 15:00:00','6.00','U005','匹','',1,NULL,'2018-07-27 13:07:33',NULL,'2018-07-27 13:07:33','','0'),('b093ff167c0a4c3282d7cbd50bfa766d','61_*_*','61_*_*-02-03-02','C13','W13','S201004309527','E778','W02','C1301','B040107322001','布轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','','','1','B04201807250010','E3655','2018-07-25 19:00:00','6.00','U005','匹','',1,NULL,'2018-07-25 16:18:11',NULL,'2018-07-25 16:18:11','','0'),('b32c5005315b43b1b693f2d945e5ce54','28_*_*','28_*_*-02-01-02','C12','W10','S201001509167','E785','W02','C1201','B040302030001','布轴-T/JC（30+30）&times;20   132&times;62   63&rdquo; 双经布（双幅）','','','888','B04201805240002','E1687','2018-05-24 09:55:00','12.00','U005','匹','',1,NULL,'2018-05-24 09:55:52',NULL,'2018-05-24 09:55:52','','1'),('b8651b0d07cb482d83416ee95ea475ab','34_*_*','34_*_*-02-02-01','C12','W09','S201000501041','E1471','W01','C1202','B040509525001','布轴-LF60*LF60 173*130 106&quot;小叶子','','','5','B04201807040005','E1471','2018-07-02 18:26:00','6.00','U005','匹','',1,NULL,'2018-07-04 16:25:06',NULL,'2018-07-04 16:25:06','','1'),('c512cf194d484ad58cd43e55836529d6','28_*_*','28_*_*-02-01-01','C12','W10','S201001509167','E1354','W01','C1203','B040302030001','布轴-T/JC（30+30）&times;20   132&times;62   63&rdquo; 双经布（双幅）','','','327','B04201805230007','E1687','2018-05-23 16:14:00','3.00','U005','匹','E65',1,NULL,'2018-05-23 16:15:11',NULL,'2018-05-23 16:15:11','','1'),('c671c24e99f64002abcddc1f9a1f98c3','61_*_*','61_*_*-05-01-01','C13','W11','S201002004222','E1411','W01','C1304','B040107322001','布轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','','','1','B04201808150001','E2508','2018-08-15 15:41:00','232.00','U001','米','',1,NULL,'2018-08-15 15:43:46',NULL,'2018-08-15 15:43:46','','0'),('c6cced5f13e94dca8dbe0e358d5dd431','34_*_*','34_*_*-02-01-03','C12','W09','S201000401031','E1738','W03','C1201','B040509525001','布轴-LF60*LF60 173*130 106&quot;小叶子','','','2','B04201807040002','E1738','2018-06-26 15:22:00','6.00','U005','匹','',1,NULL,'2018-07-04 16:21:32',NULL,'2018-07-04 16:21:32','','0'),('c8d55befd12e43bba179820af744e1e9','61_*_*','61_*_*-02-02-03','C13','W13','S201004209511','E1402','W03','C1302','B040107322001','布轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','','','1','B04201807270005','E773','2018-07-27 15:00:00','6.00','U005','匹','',1,NULL,'2018-07-27 13:07:27',NULL,'2018-07-27 13:07:27','','0'),('ce74deaf11e44fc8a71c622341fed7ff','28_*_*','28_*_*-02-01-01','C12','W10','S201001509167','E1354','W01','C1203','B040302030001','布轴-T/JC（30+30）&times;20   132&times;62   63&rdquo; 双经布（双幅）','','','340','B04201805230010','E1687','2018-05-23 16:14:00','3.00','U005','匹','E65',1,NULL,'2018-05-23 16:15:24',NULL,'2018-05-23 16:15:24','','1'),('cef87e4033fc4787a0039f0de118db42','28_*_*','28_*_*-02-01-01','C12','W10','S201001509167','E1354','W01','C1203','B040302030001','布轴-T/JC（30+30）&times;20   132&times;62   63&rdquo; 双经布（双幅）','','','324','B04201805230004','E1687','2018-05-23 16:14:00','3.00','U005','匹','E65',1,NULL,'2018-05-23 16:14:25',NULL,'2018-05-23 16:14:25','','1'),('dbbc1bee0ef34fa7b17ad6ca6a02f565','61_*_*','61_*_*-05-01-01','C13','W11','S201002004222','E1411','W01','C1304','B040107322001','布轴-CVC 32/2×32/2  100×53  63\" 2/1碳黑1.06镶嵌 CS18-14-1','','','12','B04201808150004','E9988','2018-08-15 17:07:00','12.00','U001','米','',1,NULL,'2018-08-15 20:24:57',NULL,'2018-08-15 20:24:57','','0'),('de6d6d267f6e49e1b49b324c07c50e1b','61_*_*','61_*_*-02-03-02','C13','W13','S201004309527','E778','W02','C1301','B040107322001','布轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','','','S000001','B04201807250008','E3655','2018-07-25 13:16:00','12.00','U005','匹','',1,NULL,'2018-07-25 13:17:44',NULL,'2018-07-25 13:17:44','','1'),('dfdd7c593a98441aa5a87b1d2751d756','34_*_*','34_*_*-02-01-02','C12','W09','S201000401031','E1687','W02','C1203','B040509525001','布轴-LF60*LF60 173*130 106&quot;小叶子','','','6','B04201807040006','E1687','2018-06-18 12:28:00','6.00','U005','匹','',1,NULL,'2018-07-04 16:27:38',NULL,'2018-07-04 16:27:38','','0'),('e13edcc526bd4b94b75a162002bd947f','61_*_*','61_*_*-02-03-03','C13','W13','S201004309527','E1402','W03','C1302','B040107322001','布轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','','','1','B04201807270001','E773','2018-07-27 13:04:00','6.00','U005','匹','',1,NULL,'2018-07-27 12:59:51',NULL,'2018-07-27 12:59:51','','0'),('e46f54a4b6f94615b5aa48fd309ae0e4','28_*_*','28_*_*-02-01-01','C12','W10','S201001509167','E1354','W01','C1203','B040302030001','布轴-T/JC（30+30）&times;20   132&times;62   63&rdquo; 双经布（双幅）','','','321','B04201805230001','E1687','2018-06-12 15:09:00','12.00','U005','匹','',1,NULL,'2018-05-23 15:15:16',NULL,'2018-05-23 15:15:16','','1'),('e5a7df8ca110428faf7d2e318cf52f70','61_*_*','61_*_*-02-04-01','C13','W13','S201004409543','E2174','W01','C1303','B040107322001','布轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','','','2','B04201807250002','E760','2018-07-25 15:00:00','6.00','U005','匹','',1,NULL,'2018-07-25 10:49:37',NULL,'2018-07-25 10:49:37','','0'),('eafcee56233b4b7db18a76097f53636d','28_*_*','28_*_*-02-01-01','C12','W10','S201001509167','E1354','W01','C1203','B040302030001','布轴-T/JC（30+30）&times;20   132&times;62   63&rdquo; 双经布（双幅）','','','326','B04201805230006','E1687','2018-05-23 16:14:00','3.00','U005','匹','E65',1,NULL,'2018-05-23 16:15:07',NULL,'2018-05-23 16:15:07','','1'),('f2ece449cec14772967fe7ddcaabc8e4','34_*_*','34_*_*-02-01-03','C12','W09','S201000401031','E1738','W03','C1201','B040509525001','布轴-LF60*LF60 173*130 106&quot;小叶子','','','1','B04201807040001','E1738','2018-07-04 16:21:00','6.00','U005','匹','',1,NULL,'2018-07-04 16:20:34',NULL,'2018-07-04 16:20:34','','1'),('f5c9133d3c934f7fb114447e7b001388','34_*_*','34_*_*-02-01-03','C12','W09','S201000401031','E1738','W03','C1201','B040509525001','布轴-LF60*LF60 173*130 106&quot;小叶子','','','01','B04201806140002','E1738','2018-06-11 10:47:00','6.00','U005','匹','',1,NULL,'2018-06-14 10:45:57',NULL,'2018-06-14 10:45:57','','1'),('f6b4c9950d794898bf71d4ab48c83ca9','34_*_*','34_*_*-02-02-01','C12','W09','S201000501041','E790','W01','C1202','B040509525001','布轴-LF60*LF60 173*130 106&quot;小叶子','','','12','B04201806050001','E1471','2018-06-05 16:30:00','35.00','U004','码','',1,NULL,'2018-06-05 15:30:38',NULL,'2018-06-05 15:30:38','','1'),('fce2dc93ab894658a35733c32584ca80','61_*_*','61_*_*-02-03-02','C13','W13','S201004309527','E778','W02','C1301','B040107322001','布轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','','','1','B04201807260005','E3655','2018-07-27 03:00:00','6.00','U005','匹','',1,NULL,'2018-07-26 13:39:24',NULL,'2018-07-26 13:39:24','','0'),('fda048cf6a494d14a3f07d26b7d258a7','61_*_*','61_*_*-02-01-03','C13','W13','S201004109495','E1402','W03','C1302','B040107322001','布轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','','','1','B04201807270003','E773','2018-07-27 14:00:00','6.00','U005','匹','',1,NULL,'2018-07-27 13:05:26',NULL,'2018-07-27 13:05:26','','0'),('ff141054e1704c49a1c822e629369b3d','61_*_*','61_*_*-02-04-02','C13','W13','S201004409543','E778','W02','C1301','B040107322001','布轴-CVC 32/2&times;32/2  100&times;53  63&quot; 2/1碳黑1.06镶嵌 CS18-14-1','','','02','B04201807250013','E3655','2018-07-25 20:00:00','6.00','U016','立方米每小时','',1,NULL,'2018-07-25 17:22:38',NULL,'2018-07-25 17:22:38','','1');

/*Table structure for table `job_weaving_workshop_template` */

DROP TABLE IF EXISTS `job_weaving_workshop_template`;

CREATE TABLE `job_weaving_workshop_template` (
  `id` char(32) NOT NULL COMMENT '模板编号',
  `version_code` varchar(50) DEFAULT NULL COMMENT '版本号',
  `up_shaft_template_code` varchar(50) NOT NULL COMMENT '上轴模板编号',
  `up_latitude_template_code` varchar(50) NOT NULL COMMENT '上纬纱模板编号',
  `tying_template_code` varchar(50) NOT NULL COMMENT '结经模板编号',
  `weaving_template_code` varchar(50) NOT NULL COMMENT '织布模板编号',
  `status` tinyint(4) NOT NULL COMMENT '发布状态',
  `create_by` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `del_flag` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `job_weaving_workshop_template` */

/*Table structure for table `job_workshop` */

DROP TABLE IF EXISTS `job_workshop`;

CREATE TABLE `job_workshop` (
  `id` char(32) NOT NULL COMMENT '车间id',
  `ws_name` varchar(20) NOT NULL COMMENT '车间名称',
  `status` tinyint(4) DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `del_flag` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车间表';

/*Data for the table `job_workshop` */

/*Table structure for table `job_workshop_combinecloth` */

DROP TABLE IF EXISTS `job_workshop_combinecloth`;

CREATE TABLE `job_workshop_combinecloth` (
  `id` char(32) NOT NULL COMMENT 'id',
  `task_id` varchar(64) DEFAULT NULL COMMENT '任务编号--唯一的任务编号',
  `combine_id` varchar(32) DEFAULT NULL COMMENT '拼件编号',
  `pack_number` int(10) NOT NULL AUTO_INCREMENT COMMENT '包号',
  `local_number` varchar(32) DEFAULT NULL COMMENT '库位编号--批次包号',
  `area_region` varchar(32) DEFAULT NULL COMMENT '库位区域',
  `batch_number` varchar(20) DEFAULT NULL COMMENT '批次号',
  `cloth_variety` varchar(225) DEFAULT NULL COMMENT '布匹品种',
  `cloth_level` varchar(64) DEFAULT NULL COMMENT '布匹等级',
  `combine_worker` varchar(32) DEFAULT NULL COMMENT '拼件人',
  `worker_no` varchar(32) DEFAULT NULL COMMENT '工人工号',
  `start_time` datetime DEFAULT NULL COMMENT '拼件开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '拼件结束时间',
  `combine_status` varchar(2) DEFAULT '0' COMMENT '拼件状态: 0-拼件中,1-拼件完 ,2-打包完 ,3已入库',
  `pack_demand` varchar(225) DEFAULT NULL COMMENT '成包要求',
  `total_length` decimal(10,2) DEFAULT NULL COMMENT '总长度(米）',
  `require_length` decimal(10,2) DEFAULT NULL COMMENT '要求长度(码）',
  `fold_length` decimal(10,2) DEFAULT NULL COMMENT '折幅(cm)',
  `create_user` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志 0-正常 1-删除',
  `status` char(1) DEFAULT '0' COMMENT '状态--用于标识是拼件还是成包 0--拼件 1--成包',
  `shift_date` datetime DEFAULT NULL COMMENT '排班日期',
  `shift_id` varchar(32) DEFAULT NULL COMMENT '班次',
  `shift_style` varchar(32) DEFAULT NULL COMMENT '班别',
  `workcenter_id` varchar(32) DEFAULT NULL COMMENT '工作中心',
  `goods_id` varchar(32) DEFAULT NULL COMMENT '物料编码',
  `erp_bill_no` varchar(50) DEFAULT NULL COMMENT '生产任务订单',
  `goods_name` varchar(50) DEFAULT NULL COMMENT '订单物料名称',
  `qty_unit` varchar(32) DEFAULT NULL COMMENT '订单单位',
  `bill_no` varchar(32) DEFAULT NULL COMMENT '任务编号--报功编号',
  `device_id` varchar(32) DEFAULT NULL COMMENT '设备编号',
  `trade_name` varchar(100) DEFAULT NULL COMMENT '品名（英文）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `pack_number` (`pack_number`) USING BTREE,
  UNIQUE KEY `local_number` (`local_number`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

/*Data for the table `job_workshop_combinecloth` */

insert  into `job_workshop_combinecloth`(`id`,`task_id`,`combine_id`,`pack_number`,`local_number`,`area_region`,`batch_number`,`cloth_variety`,`cloth_level`,`combine_worker`,`worker_no`,`start_time`,`end_time`,`combine_status`,`pack_demand`,`total_length`,`require_length`,`fold_length`,`create_user`,`create_time`,`update_user`,`update_time`,`remarks`,`del_flag`,`status`,`shift_date`,`shift_id`,`shift_style`,`workcenter_id`,`goods_id`,`erp_bill_no`,`goods_name`,`qty_unit`,`bill_no`,`device_id`,`trade_name`) values ('09e315cc757e4e959a72610175501f64','4d1679979b3248e7a114a2928de8d6d4','2018080700010',19,'a-001','整理运转甲班拼件一组','B10162','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','5','于建华','E13','2018-08-07 13:37:10',NULL,'0','该条生产任务书的交布条件没有设置!','10.94','0.00','0.00','yujianhua','2018-08-07 13:37:10',NULL,NULL,NULL,'0','0','2018-06-14 00:00:00','W01','C1405','W14','C020300200000','C1055_1_1','坯布-T/JC65/35(30+30)*20 132*62 63&rdquo;双经布','米','C1055_1_1-63-01-01','C14010101',''),('09e456b425c7462abf46fc098e0edb91','4d1679979b3248e7a114a2928de8d6d4','2018080700003',12,'2018871','整理运转甲班拼件一组','B10155','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','2','于建华','E13','2018-08-07 08:45:34',NULL,'0','该条生产任务书的交布条件没有设置!','10.94','0.00','0.00','yujianhua','2018-08-07 08:45:34',NULL,NULL,NULL,'0','0','2018-06-14 00:00:00','W01','C1405','W14','C020300200000','C1055_1_1','坯布-T/JC65/35(30+30)*20 132*62 63&rdquo;双经布','米','C1055_1_1-63-01-01','C14010101',''),('0e2e1dcfeae047808248bb6a8c58a877','','2018081800001',38,'B2110','','B10188','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','1','黄晓磊','E1529','2018-08-18 18:27:19','2018-08-18 18:27:29','1','该条生产任务书的交布条件没有设置!','120.00','0.00','4.00','huangxiaolei','2018-08-18 18:27:19','huangxiaolei','2018-08-18 18:27:29',NULL,'0','0',NULL,'W01','C1405','','C020300200000','','坯布-T/JC65/35(30+30)*20 132*62 63&rdquo;双经布','','C01201808180001','',''),('0e4649188f584f74a95f8479307cd37f','4d1679979b3248e7a114a2928de8d6d4','2018080700022',31,'321-456-789','整理运转甲班拼件一组','B10174','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','2','系统管理员','admin_emp','2018-08-07 18:28:59',NULL,'0','该条生产任务书的交布条件没有设置!','15.00','0.00','0.00','admin','2018-08-07 18:28:59','admin','2018-08-21 21:33:52',NULL,'0','0','2018-06-14 00:00:00','W01','C1405','W14','C020300200000','C1055_1_1','坯布-T/JC65/35(30+30)*20 132*62 63&rdquo;双经布','米','C1055_1_1-63-01-01','C14010101',''),('1640aa39a6ef4e4f8c9ed0fcf1afc995','4d1679979b3248e7a114a2928de8d6d4','2018080700020',29,'0159','整理运转甲班拼件一组','B10172','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','4','系统管理员','admin_emp','2018-08-07 15:39:19',NULL,'0','该条生产任务书的交布条件没有设置!','41.87','0.00','0.00','admin','2018-08-07 15:39:19','admin','2018-08-07 15:40:07',NULL,'0','0','2018-06-14 00:00:00','W01','C1405','W14','C020300200000','C1055_1_1','坯布-T/JC65/35(30+30)*20 132*62 63&rdquo;双经布','米','C1055_1_1-63-01-01','C14010101',''),('188099885ae64ed5801f1f21411fcebc','568f70f65ef5425a8757b10f9464975d','2018052400002',2,'018-58-391','整理运转甲班拼件一组','B10002','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','2','王彦玲','E882','2018-05-24 16:16:17','2018-05-24 16:17:00','2','该条生产任务书的交布条件没有设置!','237.53','0.00','0.00','wangyanling','2018-05-24 16:16:17','wangyanling','2018-05-24 16:45:20',NULL,'0','0','2018-06-14 00:00:00','W04','C1401','W14','C020300200000','C1055_1_1','坯布-T/JC65/35(30+30)*20 132*62 63&rdquo;双经布','米','C1055_1_1-07-01-01','C14010101',NULL),('25ee9a06a3724bf8840d78153d637c3c','4d1679979b3248e7a114a2928de8d6d4','2018080700012',21,'2018-8-8','整理运转甲班拼件一组','B10164','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','2','于建华','E13','2018-08-07 15:05:10',NULL,'0','该条生产任务书的交布条件没有设置!','10.94','0.00','0.00','yujianhua','2018-08-07 15:05:10',NULL,NULL,NULL,'0','0','2018-06-14 00:00:00','W01','C1405','W14','C020300200000','C1055_1_1','坯布-T/JC65/35(30+30)*20 132*62 63&rdquo;双经布','米','C1055_1_1-63-01-01','C14010101',''),('2a39bbf9905f4e0a82bea0431d6c6f4c','','2018082300002',41,'Bw12','','B10191','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','2','系统管理员','admin_emp','2018-08-23 22:48:41','2018-08-23 23:01:04','1','该条生产任务书的交布条件没有设置!','187.20','0.00','0.00','admin','2018-08-23 22:48:41','admin','2018-08-23 23:01:04',NULL,'0','0',NULL,'W01','C1405','','C020300200000','','坯布-T/JC65/35(30+30)*20 132*62 63&rdquo;双经布','','C01201808230002','','坯布-T/JC65/35(30+30)*20 132*62 63&rdquo;双经布'),('2aa3d49b4399450c8e9b420ea28f537d','4d1679979b3248e7a114a2928de8d6d4','2018080700011',20,'2018-8-7','整理运转甲班拼件一组','B10163','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','5','于建华','E13','2018-08-07 14:45:51',NULL,'0','该条生产任务书的交布条件没有设置!','357.83','0.00','0.00','yujianhua','2018-08-07 14:45:51','yujianhua','2018-08-07 15:03:34',NULL,'0','0','2018-06-14 00:00:00','W01','C1405','W14','C020300200000','C1055_1_1','坯布-T/JC65/35(30+30)*20 132*62 63&rdquo;双经布','米','C1055_1_1-63-01-01','C14010101',''),('38a50e185c0f4edca4e16d067fba4d8a','4d1679979b3248e7a114a2928de8d6d4','2018081000003',33,'2018810','整理运转甲班拼件一组','B10181','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','4','于建华','E13','2018-08-10 10:12:46',NULL,'0','该条生产任务书的交布条件没有设置!','30.00','0.00','0.00','yujianhua','2018-08-10 10:12:46','yujianhua','2018-08-10 10:12:46',NULL,'0','0','2018-06-14 00:00:00','W01','C1405','W14','C020300200000','C1055_1_1','坯布-T/JC65/35(30+30)*20 132*62 63&rdquo;双经布','米','C1055_1_1-63-01-01','C14010101',''),('3980643b148b484ab663845f71e9b145','4d1679979b3248e7a114a2928de8d6d4','2018080700001',10,'201887','整理运转甲班拼件一组','B10153','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','2','于建华','E13','2018-08-07 08:32:09',NULL,'0','该条生产任务书的交布条件没有设置!','10.94','0.00','0.00','yujianhua','2018-08-07 08:32:09',NULL,NULL,NULL,'0','0','2018-06-14 00:00:00','W01','C1405','W14','C020300200000','C1055_1_1','坯布-T/JC65/35(30+30)*20 132*62 63&rdquo;双经布','米','C1055_1_1-63-01-01','C14010101',''),('419d6e99271046c8b25e3251ecaa15a6','4d1679979b3248e7a114a2928de8d6d4','2018080600003',6,'999','整理运转甲班拼件一组','B10149','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','2','系统管理员','admin_emp','2018-08-06 20:35:13',NULL,'0','该条生产任务书的交布条件没有设置!','109.36','0.00','0.00','admin','2018-08-06 20:35:13',NULL,NULL,NULL,'0','0','2018-06-14 00:00:00','W01','C1405','W14','C020300200000','C1055_1_1','坯布-T/JC65/35(30+30)*20 132*62 63&rdquo;双经布','米','C1055_1_1-63-01-01','C14010101','999'),('44c71c18ad9d46f68f849c08d455ecf9','fcd6f2f713644af7b2addaa7953bca94','2018080200001',3,'3333333','整理运转甲班整理一组','B10146','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','1','系统管理员','admin_emp','2018-08-02 15:36:57',NULL,'0','该条生产任务书的交布条件没有设置!','131.23','0.00','6.00','admin','2018-08-02 15:36:57',NULL,NULL,NULL,'0','0','2018-06-14 00:00:00','W04','C1401','W14','C020300200000','C1055_1_1','坯布-T/JC65/35(30+30)*20 132*62 63&rdquo;双经布','米','C1055_1_1-32-01-01','C140101',''),('4bec99a03168467497c887de372093e7','568f70f65ef5425a8757b10f9464975d','2018052400001',1,'018-58-390','整理运转甲班拼件一组','B10001','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','1','王彦玲','E882','2018-05-24 16:12:46','2018-05-24 16:14:22','1','该条生产任务书的交布条件没有设置!','131.23','0.00','0.00','wangyanling','2018-05-24 16:12:46','wangyanling','2018-05-24 16:14:22',NULL,'0','0','2018-06-14 00:00:00','W04','C1401','W14','C020300200000','C1055_1_1','坯布-T/JC65/35(30+30)*20 132*62 63&rdquo;双经布','米','C1055_1_1-07-01-01','C14010101',NULL),('5bf4b1ed0dde479991f82a84caaa875c','4d1679979b3248e7a114a2928de8d6d4','2018080700019',28,'0789','整理运转甲班拼件一组','B10171','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','4','系统管理员','admin_emp','2018-08-07 15:31:31',NULL,'0','该条生产任务书的交布条件没有设置!','20.00','0.00','0.00','admin','2018-08-07 15:31:31',NULL,NULL,NULL,'0','0','2018-06-14 00:00:00','W01','C1405','W14','C020300200000','C1055_1_1','坯布-T/JC65/35(30+30)*20 132*62 63&rdquo;双经布','米','C1055_1_1-63-01-01','C14010101','0123'),('5df026cb78bc46dab26b684219dd6748','4d1679979b3248e7a114a2928de8d6d4','2018080600005',8,'201888','整理运转甲班拼件一组','B10151','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','1','于建华','E13','2018-08-06 22:10:16',NULL,'0','该条生产任务书的交布条件没有设置!','10.94','0.00','0.00','yujianhua','2018-08-06 22:10:16',NULL,NULL,NULL,'0','0','2018-06-14 00:00:00','W01','C1405','W14','C020300200000','C1055_1_1','坯布-T/JC65/35(30+30)*20 132*62 63&rdquo;双经布','米','C1055_1_1-63-01-01','C14010101',''),('6f75de0986b44337a77996091c0b5009','5a2ff3cc2fc1485e88b6b645812298d2','2018081000004',34,'123456789','整理运转甲班整理一组','B10182','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','5','于建华','E13','2018-08-10 12:04:44',NULL,'0','该条生产任务书的交布条件没有设置!','15.00','0.00','0.00','yujianhua','2018-08-10 12:04:44',NULL,NULL,NULL,'0','0','2018-08-09 00:00:00','W04','C1404','W14','C020300200000','C1055_1_1','坯布-T/JC65/35(30+30)*20 132*62 63&rdquo;双经布','米','C1055_1_1-84-01-01','C140101','666'),('75d3d176a43043b186a01e4c6ade4ac7','4d1679979b3248e7a114a2928de8d6d4','2018080700014',23,'0147','整理运转甲班拼件一组','B10166','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','1','系统管理员','admin_emp','2018-08-07 15:18:35',NULL,'0','该条生产任务书的交布条件没有设置!','21.87','0.00','0.00','admin','2018-08-07 15:18:35','admin','2018-08-07 15:18:35',NULL,'0','0','2018-06-14 00:00:00','W01','C1405','W14','C020300200000','C1055_1_1','坯布-T/JC65/35(30+30)*20 132*62 63&rdquo;双经布','米','C1055_1_1-63-01-01','C14010101',''),('85e55322e7174c3b928ccde1c7d96531','4d1679979b3248e7a114a2928de8d6d4','2018080700016',25,'0369','整理运转甲班拼件一组','B10168','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','2','系统管理员','admin_emp','2018-08-07 15:23:27',NULL,'0','该条生产任务书的交布条件没有设置!','21.87','0.00','0.00','admin','2018-08-07 15:23:27',NULL,NULL,NULL,'0','0','2018-06-14 00:00:00','W01','C1405','W14','C020300200000','C1055_1_1','坯布-T/JC65/35(30+30)*20 132*62 63&rdquo;双经布','米','C1055_1_1-63-01-01','C14010101',''),('99318426a1da4ab49c7b6655a0d7ca20','568f70f65ef5425a8757b10f9464975d','2018080700021',30,'987','整理运转甲班拼件一组','B10173','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','1','系统管理员','admin_emp','2018-08-07 16:22:13','2018-08-09 20:33:33','1','该条生产任务书的交布条件没有设置!','45.00','0.00','0.00','admin','2018-08-07 16:22:13','zhaohuimin','2018-08-09 20:33:33',NULL,'0','0','2018-06-14 00:00:00','W04','C1401','W14','C020300200000','C1055_1_1','坯布-T/JC65/35(30+30)*20 132*62 63&rdquo;双经布','米','C1055_1_1-07-01-01','C14010101',''),('aed842bbbc0e47c2aad325d05440cd49','4d1679979b3248e7a114a2928de8d6d4','2018080700005',14,'1q2w2w1','整理运转甲班拼件一组','B10157','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','3','于建华','E13','2018-08-07 09:13:41',NULL,'0','该条生产任务书的交布条件没有设置!','21.88','0.00','0.00','yujianhua','2018-08-07 09:13:41','yujianhua','2018-08-07 10:51:58',NULL,'0','0','2018-06-14 00:00:00','W01','C1405','W14','C020300200000','C1055_1_1','坯布-T/JC65/35(30+30)*20 132*62 63&rdquo;双经布','米','C1055_1_1-63-01-01','C14010101',''),('ba9b63dfe6a84b41ab348416b9d2c9a1','4d1679979b3248e7a114a2928de8d6d4','2018080600001',4,'123123','整理运转甲班拼件一组','B10147','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','1','系统管理员','admin_emp','2018-08-06 20:33:54',NULL,'0','该条生产任务书的交布条件没有设置!','100.00','0.00','0.00','admin','2018-08-06 20:33:54','admin','2018-08-21 10:16:36',NULL,'0','0','2018-06-14 00:00:00','W01','C1405','W14','C020300200000','C1055_1_1','坯布-T/JC65/35(30+30)*20 132*62 63&rdquo;双经布','米','C1055_1_1-63-01-01','C14010101',''),('c069c43512e540d499f6461708f75046','4d1679979b3248e7a114a2928de8d6d4','2018080700023',32,'123-456','整理运转甲班拼件一组','B10175','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','3','系统管理员','admin_emp','2018-08-07 18:33:40','2018-08-07 18:35:06','2','该条生产任务书的交布条件没有设置!','30.00','0.00','0.00','admin','2018-08-07 18:33:40','zhaohuimin','2018-08-09 20:39:49',NULL,'0','0','2018-06-14 00:00:00','W01','C1405','W14','C020300200000','C1055_1_1','坯布-T/JC65/35(30+30)*20 132*62 63&rdquo;双经布','米','C1055_1_1-63-01-01','C14010101',''),('ca2949e7c5c549a6bca5ec25adaae6eb','4d1679979b3248e7a114a2928de8d6d4','2018080700015',24,'0258','整理运转甲班拼件一组','B10167','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','2','系统管理员','admin_emp','2018-08-07 15:22:07',NULL,'0','该条生产任务书的交布条件没有设置!','24.15','0.00','0.00','admin','2018-08-07 15:22:07','admin','2018-08-07 16:00:32',NULL,'0','0','2018-06-14 00:00:00','W01','C1405','W14','C020300200000','C1055_1_1','坯布-T/JC65/35(30+30)*20 132*62 63&rdquo;双经布','米','C1055_1_1-63-01-01','C14010101',''),('d0876dce9c624b4387f25c77900ab23b','4d1679979b3248e7a114a2928de8d6d4','2018080600004',7,'123','整理运转甲班拼件一组','B10150','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','3','系统管理员','admin_emp','2018-08-06 21:30:00',NULL,'0','该条生产任务书的交布条件没有设置!','181.28','0.00','0.00','admin','2018-08-06 21:30:00',NULL,NULL,NULL,'0','0','2018-06-14 00:00:00','W01','C1405','W14','C020300200000','C1055_1_1','坯布-T/JC65/35(30+30)*20 132*62 63&rdquo;双经布','米','C1055_1_1-63-01-01','C14010101',''),('d1a4d315b1ba4d4cba533e68b8768ed3','4d1679979b3248e7a114a2928de8d6d4','2018080700017',26,'0123','整理运转甲班拼件一组','B10169','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','3','系统管理员','admin_emp','2018-08-07 15:25:53',NULL,'0','该条生产任务书的交布条件没有设置!','20.00','0.00','0.00','admin','2018-08-07 15:25:53',NULL,NULL,NULL,'0','0','2018-06-14 00:00:00','W01','C1405','W14','C020300200000','C1055_1_1','坯布-T/JC65/35(30+30)*20 132*62 63&rdquo;双经布','米','C1055_1_1-63-01-01','C14010101',''),('d2d76be2ae6a4145a592373c66359fa1','4d1679979b3248e7a114a2928de8d6d4','2018080700018',27,'0456','整理运转甲班拼件一组','B10170','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','3','系统管理员','admin_emp','2018-08-07 15:29:48',NULL,'0','该条生产任务书的交布条件没有设置!','20.00','0.00','0.00','admin','2018-08-07 15:29:48',NULL,NULL,NULL,'0','0','2018-06-14 00:00:00','W01','C1405','W14','C020300200000','C1055_1_1','坯布-T/JC65/35(30+30)*20 132*62 63&rdquo;双经布','米','C1055_1_1-63-01-01','C14010101',''),('d46a7257344d4dfa8ab5201976c06df8','5a2ff3cc2fc1485e88b6b645812298d2','2018081000005',35,'987654311','整理运转甲班整理一组','B10183','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','5','于建华','E13','2018-08-10 12:05:15','2018-08-11 10:00:53','1','该条生产任务书的交布条件没有设置!','30.00','0.00','0.00','yujianhua','2018-08-10 12:05:15','yujianhua','2018-08-11 10:00:53',NULL,'0','0','2018-08-09 00:00:00','W04','C1404','W14','C020300200000','C1055_1_1','坯布-T/JC65/35(30+30)*20 132*62 63&rdquo;双经布','米','C1055_1_1-84-01-01','C140101','777'),('d4e48c883f484213b7671e6fe300d27a','5a2ff3cc2fc1485e88b6b645812298d2','2018081000008',36,'888888','整理运转甲班整理一组','B10186','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','3','于建华','E13','2018-08-10 14:03:38','2018-08-11 09:59:48','1','该条生产任务书的交布条件没有设置!','30.00','0.00','0.00','yujianhua','2018-08-10 14:03:38','yujianhua','2018-08-11 09:59:48',NULL,'0','0','2018-08-09 00:00:00','W04','C1404','W14','C020300200000','C1055_1_1','坯布-T/JC65/35(30+30)*20 132*62 63&rdquo;双经布','米','C1055_1_1-84-01-01','C140101','T/JC65/35(30+30)*20 132*62 63'),('d504d8e7c6624d1bac8470e07e123ed8','','2018081700001',37,'B20180817001','','B10187','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','2','系统管理员','admin_emp','2018-08-17 22:03:53',NULL,'0','该条生产任务书的交布条件没有设置!','67.20','0.00','0.00','admin','2018-08-17 22:03:53',NULL,NULL,NULL,'0','0','2018-08-17 00:00:00','W01','C1405','','C020300200000','','坯布-T/JC65/35(30+30)*20 132*62 63&rdquo;双经布','','C01201808170001','',''),('e4e18b19f42e44e1884d124bd384119e','4d1679979b3248e7a114a2928de8d6d4','2018080700013',22,'147','整理运转甲班拼件一组','B10165','坯布-T/JC65/35(30+30)*20 132*62 63”双经布','1','系统管理员','admin_emp','2018-08-07 15:11:12',NULL,'0','该条生产任务书的交布条件没有设置!','21.88','0.00','0.00','admin','2018-08-07 15:11:12','admin','2018-08-07 15:11:52',NULL,'0','0','2018-06-14 00:00:00','W01','C1405','W14','C020300200000','C1055_1_1','坯布-T/JC65/35(30+30)*20 132*62 63&rdquo;双经布','米','C1055_1_1-63-01-01','C14010101','');

/*Table structure for table `job_workshop_combinecloth_items` */

DROP TABLE IF EXISTS `job_workshop_combinecloth_items`;

CREATE TABLE `job_workshop_combinecloth_items` (
  `id` char(32) NOT NULL COMMENT 'id',
  `combine_id` char(32) DEFAULT NULL COMMENT '拼件编号',
  `segement_parent` varchar(32) DEFAULT NULL COMMENT '分段布的轴号',
  `segment_number` varchar(32) DEFAULT NULL COMMENT '分段布编号',
  `cloth_level` varchar(10) DEFAULT NULL COMMENT '分段布等级',
  `segment_sequence` int(10) DEFAULT NULL COMMENT '分段布匹顺序号',
  `fold_length` decimal(15,2) DEFAULT NULL COMMENT '分段布折幅',
  `segment_length` decimal(15,2) DEFAULT NULL COMMENT '分段布长度码',
  `set_status` varchar(5) DEFAULT NULL COMMENT '状态 拼件  减件两种状态',
  `combine_worker` varchar(10) DEFAULT NULL COMMENT '拼件工',
  `combine_worker_no` varchar(10) DEFAULT NULL,
  `combine_time` datetime DEFAULT NULL COMMENT '拼件时间',
  `delete_worker` varchar(10) DEFAULT NULL COMMENT '减件人',
  `delete_worker_no` varchar(10) DEFAULT NULL COMMENT '删除员工工号',
  `delete_time` datetime DEFAULT NULL COMMENT '减件时间',
  `delete_reason` varchar(225) DEFAULT NULL COMMENT '减件原因',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志 0-未删除 1-删除',
  `create_time` datetime DEFAULT NULL,
  `create_user` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `job_workshop_combinecloth_items` */

insert  into `job_workshop_combinecloth_items`(`id`,`combine_id`,`segement_parent`,`segment_number`,`cloth_level`,`segment_sequence`,`fold_length`,`segment_length`,`set_status`,`combine_worker`,`combine_worker_no`,`combine_time`,`delete_worker`,`delete_worker_no`,`delete_time`,`delete_reason`,`del_flag`,`create_time`,`create_user`,`update_time`,`update_user`) values ('0c384b8f184b4fae946c6bffb7813b2b','2018080700021','B04201807270003','B04201807270003-03','1',3,'0.00','15.00','拼件','系统管理员','admin_emp','2018-08-07 16:22:59',NULL,NULL,NULL,NULL,'0','2018-08-07 16:22:59','admin',NULL,NULL),('1b5dbd3a7b74404b831e27b0d2b57195','2018080700005','B04201807270005','B04201807270005-09','3',9,'0.00','10.00','拼件','于建华','E13','2018-08-07 10:51:58',NULL,NULL,NULL,NULL,'0','2018-08-07 10:51:58','yujianhua',NULL,NULL),('1bdf6cb4f60f4d4ba0702e3da7dd5423','2018080700022','B04201807270003','B04201807270003-05','2',5,'0.00','15.00','拼件','系统管理员','admin_emp','2018-08-07 18:29:01',NULL,NULL,NULL,NULL,'1','2018-08-07 18:29:01','admin',NULL,NULL),('1d0f65c2f84a4a3792a9294c0b4c70d6','2018080700020','B04201807270004','B04201807270004-09','5',9,'0.00','20.00','拼件','系统管理员','admin_emp','2018-08-07 15:40:07',NULL,NULL,NULL,NULL,'0','2018-08-07 15:40:07','admin',NULL,NULL),('1ee961984881411098e4cdb1b4bb4e28','2018080700015','B04201807270004','B04201807270004-03','2',3,'0.00','20.00','拼件','系统管理员','admin_emp','2018-08-07 15:22:07',NULL,NULL,NULL,NULL,'0','2018-08-07 15:22:07','admin',NULL,NULL),('2916ecc08c464df3a45ca4303215f47a','2018081000003','B04201807270003','B04201807270003-12','4',12,'0.00','15.00','拼件','于建华','E13','2018-08-10 10:12:46',NULL,NULL,NULL,NULL,'0','2018-08-10 10:12:46','yujianhua',NULL,NULL),('313d4852b419432d8305baca0b2f8384','2018081000004','B04201807270003','B04201807270003-16','5',16,'0.00','15.00','拼件','于建华','E13','2018-08-10 12:04:44',NULL,NULL,NULL,NULL,'0','2018-08-10 12:04:44','yujianhua',NULL,NULL),('330facd69d924ca58bbf0c079372abcb','2018080700011','B04201807270005','B04201807270005-14','5',14,'0.00','100.00','拼件','于建华','E13','2018-08-07 14:45:51',NULL,NULL,NULL,NULL,'0','2018-08-07 14:45:51','yujianhua',NULL,NULL),('3964645ea356417cb4ccc9ebb8e1c552','2018080600001','B04201807270006','B04201807270006-01','1',1,'0.00','100.00','减件','系统管理员','admin_emp','2018-08-06 20:33:54','admin','admin_emp','2018-08-21 10:16:17','','1','2018-08-06 20:33:54','admin',NULL,NULL),('39a00c472eed4b189c4fc783d4196622','2018080700001','B04201807270005','B04201807270005-04','2',4,'0.00','10.00','拼件','于建华','E13','2018-08-07 08:32:09',NULL,NULL,NULL,NULL,'0','2018-08-07 08:32:09','yujianhua',NULL,NULL),('3dec1e01440a48b5872ecad69ccc4070','2018080700005','B04201807270005','B04201807270005-08','3',8,'0.00','10.00','拼件','于建华','E13','2018-08-07 09:13:40',NULL,NULL,NULL,NULL,'0','2018-08-07 09:13:40','yujianhua',NULL,NULL),('4c82ccdafab64230a0d56a478a3f031e','2018080700014','B04201807270004','B04201807270004-02','1',2,'0.00','20.00','拼件','系统管理员','admin_emp','2018-08-07 15:18:35',NULL,NULL,NULL,NULL,'0','2018-08-07 15:18:35','admin',NULL,NULL),('5c919604f9bc4cae8bda87e75a9318cf','2018080700012','B04201807270005','B04201807270005-06','2',6,'0.00','10.00','拼件','于建华','E13','2018-08-07 15:05:10',NULL,NULL,NULL,NULL,'0','2018-08-07 15:05:10','yujianhua',NULL,NULL),('5db8ef8768974701a3de6dedde619fb8','2018080700003','B04201807270005','B04201807270005-05','2',5,'0.00','10.00','拼件','于建华','E13','2018-08-07 08:45:34',NULL,NULL,NULL,NULL,'0','2018-08-07 08:45:34','yujianhua',NULL,NULL),('6185125f26a84facb6ae4421f6226399','2018082300002','B04201805240001','B04201805240001-04','2',4,'0.00','67.20','拼件','系统管理员','admin_emp','2018-08-23 22:48:41',NULL,NULL,NULL,NULL,'1','2018-08-23 22:48:41','admin',NULL,NULL),('661bd3e0dcbf469d9471015196416aa4','2018081000008','B04201807270003','B04201807270003-10','3',10,'0.00','15.00','拼件','于建华','E13','2018-08-10 14:03:38',NULL,NULL,NULL,NULL,'0','2018-08-10 14:03:38','yujianhua',NULL,NULL),('6d5b095a94584e659769e73b08db32ab','2018080700015','B04201807270004','B04201807270004-10','5',10,'0.00','2.28','拼件','系统管理员','admin_emp','2018-08-07 16:00:32',NULL,NULL,NULL,NULL,'0','2018-08-07 16:00:32','admin',NULL,NULL),('6d7d9744fc5e40d5b8fda37eeb430859','2018081000003','B04201807270003','B04201807270003-13','4',13,'0.00','15.00','拼件','于建华','E13','2018-08-10 10:12:46',NULL,NULL,NULL,NULL,'0','2018-08-10 10:12:46','yujianhua',NULL,NULL),('6f5611c081e342f7b8f2eb4a8d6bad40','2018080700016','B04201807270004','B04201807270004-04','2',4,'0.00','20.00','拼件','系统管理员','admin_emp','2018-08-07 15:23:27',NULL,NULL,NULL,NULL,'0','2018-08-07 15:23:27','admin',NULL,NULL),('723455397d39470aa5e04c98115fd618','2018081000005','B04201807270003','B04201807270003-17','5',17,'0.00','15.00','拼件','于建华','E13','2018-08-10 12:05:15',NULL,NULL,NULL,NULL,'0','2018-08-10 12:05:15','yujianhua',NULL,NULL),('7636d08fbfdb470c9df0374a922d4ceb','2018082300002','B04201805240001','B04201805240001-01','1',1,'4.00','120.00','拼件','系统管理员','admin_emp','2018-08-23 23:00:49',NULL,NULL,NULL,NULL,'0','2018-08-23 23:00:49','admin',NULL,NULL),('77f233fbc3f249d7a07ee10bb2e760c5','2018080700020','B04201807270004','B04201807270004-08','4',8,'0.00','20.00','拼件','系统管理员','admin_emp','2018-08-07 15:39:19',NULL,NULL,NULL,NULL,'0','2018-08-07 15:39:19','admin',NULL,NULL),('83cdc840d1ba446b976de740d2df5578','2018080700023','B04201807270003','B04201807270003-08','3',8,'0.00','15.00','拼件','系统管理员','admin_emp','2018-08-07 18:33:40',NULL,NULL,NULL,NULL,'0','2018-08-07 18:33:40','admin',NULL,NULL),('84d5a8fda8e240a4b8ad62bc850babe0','2018080600005','B04201807270005','B04201807270005-02','1',2,'0.00','10.00','拼件','于建华','E13','2018-08-06 22:10:16',NULL,NULL,NULL,NULL,'0','2018-08-06 22:10:16','yujianhua',NULL,NULL),('8643159eceab4318b652af796fd21283','2018080600003','B04201807270006','B04201807270006-02','2',2,'0.00','100.00','拼件','系统管理员','admin_emp','2018-08-06 20:35:13',NULL,NULL,NULL,NULL,'0','2018-08-06 20:35:13','admin',NULL,NULL),('938f6d15b81b4021a2f41b66ec00a4d0','2018080700014','B04201807270004','B04201807270004-01','1',1,'0.00','20.00','减件','系统管理员','admin_emp','2018-08-07 15:18:35','admin','admin_emp','2018-08-07 15:56:55','','1','2018-08-07 15:18:35','admin',NULL,NULL),('93bfd5c0fe1642b888af3de67369f15d','2018080700017','B04201807270004','B04201807270004-05','3',5,'0.00','20.00','拼件','系统管理员','admin_emp','2018-08-07 15:25:53',NULL,NULL,NULL,NULL,'0','2018-08-07 15:25:53','admin',NULL,NULL),('993ee45494a14f7c98af66fab0e874f3','2018080700021','B04201807270003','B04201807270003-02','1',2,'0.00','15.00','拼件','系统管理员','admin_emp','2018-08-07 16:22:13',NULL,NULL,NULL,NULL,'0','2018-08-07 16:22:13','admin',NULL,NULL),('9f0cd66880de44019e068e7ebadb246b','2018080700018','B04201807270004','B04201807270004-06','3',6,'0.00','20.00','拼件','系统管理员','admin_emp','2018-08-07 15:29:48',NULL,NULL,NULL,NULL,'0','2018-08-07 15:29:48','admin',NULL,NULL),('a72f1482efb240e88227037e6f1c423a','2018052400002','B04201805240001','B04201805240001-03','2',3,'0.00','150.00','拼件','王彦玲','E882','2018-05-24 16:16:17',NULL,NULL,NULL,NULL,'0','2018-05-24 16:16:17','wangyanling',NULL,NULL),('a7d2a0f3970b4811b9b19cad7b0dee48','2018080600001','B04201807270006','B04201807270006-01','1',1,'0.00','100.00','拼件','系统管理员','admin_emp','2018-08-21 10:16:36',NULL,NULL,NULL,NULL,'0','2018-08-21 10:16:36','admin',NULL,NULL),('ab17284f91bc499caff02038d14c5a75','2018080700023','B04201807270003','B04201807270003-09','3',9,'0.00','15.00','拼件','系统管理员','admin_emp','2018-08-07 18:33:40',NULL,NULL,NULL,NULL,'0','2018-08-07 18:33:40','admin',NULL,NULL),('ab3d3855e24a4875b10e72f69e7e9a65','2018080700011','B04201807270005','B04201807270005-16','6',16,'0.00','217.20','拼件','于建华','E13','2018-08-07 15:03:34',NULL,NULL,NULL,NULL,'0','2018-08-07 15:03:34','yujianhua',NULL,NULL),('ac12dbb7ef084298af4b81aee9e3da6a','2018080700021','B04201807270003','B04201807270003-01','1',1,'0.00','15.00','拼件','系统管理员','admin_emp','2018-08-07 16:22:13',NULL,NULL,NULL,NULL,'0','2018-08-07 16:22:13','admin',NULL,NULL),('bd946cfdfecf457bbf6c3597a24f920b','2018080700019','B04201807270004','B04201807270004-07','4',7,'0.00','20.00','拼件','系统管理员','admin_emp','2018-08-07 15:31:31',NULL,NULL,NULL,NULL,'0','2018-08-07 15:31:31','admin',NULL,NULL),('c5e34d0979ca4b8aba3e133109c66dd6','2018080700013','B04201807270005','B04201807270005-03','2',3,'0.00','10.00','拼件','系统管理员','admin_emp','2018-08-07 15:11:52',NULL,NULL,NULL,NULL,'0','2018-08-07 15:11:52','admin',NULL,NULL),('c67880b16f9a43fd94bfa15288aba1ae','2018080700022','B04201807270003','B04201807270003-05','2',5,'0.00','15.00','拼件','系统管理员','admin_emp','2018-08-21 21:33:51',NULL,NULL,NULL,NULL,'0','2018-08-21 21:33:52','admin',NULL,NULL),('c758d8688c684fb8ac2b021474f51b2c','2018080700013','B04201807270005','B04201807270005-01','1',1,'0.00','10.00','拼件','系统管理员','admin_emp','2018-08-07 15:11:12',NULL,NULL,NULL,NULL,'0','2018-08-07 15:11:12','admin',NULL,NULL),('cefcc4c2566e4058a14c0ca631d8115a','2018081000005','B04201807270003','B04201807270003-18','5',18,'0.00','15.00','拼件','于建华','E13','2018-08-10 12:05:15',NULL,NULL,NULL,NULL,'0','2018-08-10 12:05:15','yujianhua',NULL,NULL),('d2f921abc7a143ddb5333089ac905279','2018081000008','B04201807270003','B04201807270003-11','3',11,'0.00','15.00','拼件','于建华','E13','2018-08-10 14:03:38',NULL,NULL,NULL,NULL,'0','2018-08-10 14:03:38','yujianhua',NULL,NULL),('e0837cd1788b42adb2d2271c17815d7d','2018082300002','B04201805240001','B04201805240001-04','2',4,'0.00','67.20','拼件','系统管理员','admin_emp','2018-08-23 23:00:49',NULL,NULL,NULL,NULL,'0','2018-08-23 23:00:49','admin',NULL,NULL),('e76245524b3c40848526c9a5a3e582be','2018080700011','B04201807270005','B04201807270005-15','6',15,'0.00','10.00','拼件','于建华','E13','2018-08-07 15:03:34',NULL,NULL,NULL,NULL,'0','2018-08-07 15:03:34','yujianhua',NULL,NULL),('f2981a1b74ce470095d7c9865b69b5d0','2018080700010','B04201807270005','B04201807270005-13','5',13,'0.00','10.00','拼件','于建华','E13','2018-08-07 13:37:10',NULL,NULL,NULL,NULL,'0','2018-08-07 13:37:10','yujianhua',NULL,NULL),('f4dd3c9ef4174ee281a9553ed03dcfc9','2018080200001','B04201805240001','B04201805240001-02','1',2,'6.00','120.00','拼件','系统管理员','admin_emp','2018-08-02 15:36:57',NULL,NULL,NULL,NULL,'0','2018-08-02 15:36:57','admin',NULL,NULL),('f5fbd681698f4d56915658f403f51a18','2018080600004','B04201807270006','B04201807270006-03','3',3,'0.00','165.76','拼件','系统管理员','admin_emp','2018-08-06 21:29:55',NULL,NULL,NULL,NULL,'0','2018-08-06 21:29:55','admin',NULL,NULL);

/*Table structure for table `job_workshop_enterstorage` */

DROP TABLE IF EXISTS `job_workshop_enterstorage`;

CREATE TABLE `job_workshop_enterstorage` (
  `id` char(64) NOT NULL COMMENT 'id',
  `combine_id` char(64) DEFAULT NULL COMMENT '拼件编号',
  `enter_worker` varchar(32) DEFAULT NULL COMMENT '入库工',
  `worker_no` varchar(32) DEFAULT NULL COMMENT '员工编号',
  `enter_time` datetime DEFAULT NULL COMMENT '入库时间',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL,
  `update_user` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL,
  `remark` varchar(225) DEFAULT NULL COMMENT '备注',
  `del_flag` char(2) DEFAULT '0' COMMENT '删除标志 0-正常 1-删除',
  `status` varchar(10) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `job_workshop_enterstorage` */

/*Table structure for table `job_workshop_packcloth` */

DROP TABLE IF EXISTS `job_workshop_packcloth`;

CREATE TABLE `job_workshop_packcloth` (
  `id` char(32) NOT NULL COMMENT 'id',
  `task_id` varchar(64) DEFAULT NULL COMMENT '任务编号',
  `bale_machine_id` char(32) DEFAULT NULL COMMENT '打包机编号',
  `combine_id` varchar(32) DEFAULT NULL COMMENT '拼件编号',
  `total_length` decimal(10,2) DEFAULT NULL COMMENT '拼件包的总长度（码）',
  `bale_time` datetime DEFAULT NULL COMMENT '打包时间',
  `bale_worker` varchar(20) DEFAULT NULL COMMENT '打包工',
  `worker_no` varchar(32) DEFAULT NULL COMMENT '员工编号',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '标志',
  `del_flag` char(2) DEFAULT '0' COMMENT '删除标志',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `shift_date` datetime DEFAULT NULL COMMENT '排班日期',
  `shift_id` varchar(32) DEFAULT NULL COMMENT '班次',
  `shift_style` varchar(32) DEFAULT NULL COMMENT '班别',
  `workcenter_id` varchar(32) DEFAULT NULL COMMENT '工作中心',
  `goods_id` varchar(50) DEFAULT NULL COMMENT '物料编码',
  `goods_name` varchar(50) DEFAULT NULL COMMENT '物料名称',
  `qty_unit` varchar(50) DEFAULT NULL COMMENT '订单单位',
  `erp_bill_no` varchar(50) DEFAULT NULL COMMENT '生产订单号',
  `batch_number` varchar(32) DEFAULT NULL COMMENT '批次号',
  `pack_number` varchar(32) DEFAULT NULL COMMENT '包号',
  `cloth_level` varchar(32) DEFAULT NULL COMMENT '布匹等级',
  `bill_no` varchar(32) DEFAULT NULL COMMENT '任务编号--报功编号',
  PRIMARY KEY (`id`),
  KEY `erp_bill_no` (`erp_bill_no`) USING BTREE,
  KEY `workcenter_id` (`workcenter_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `job_workshop_packcloth` */

insert  into `job_workshop_packcloth`(`id`,`task_id`,`bale_machine_id`,`combine_id`,`total_length`,`bale_time`,`bale_worker`,`worker_no`,`create_user`,`create_time`,`update_user`,`update_time`,`remark`,`del_flag`,`status`,`shift_date`,`shift_id`,`shift_style`,`workcenter_id`,`goods_id`,`goods_name`,`qty_unit`,`erp_bill_no`,`batch_number`,`pack_number`,`cloth_level`,`bill_no`) values ('19e267b99130421faaa6945bb4e87c89','0ba9163bc0f7460da236d59f5c800aff','S215000200002','2018080700023','30.00','2018-08-09 20:39:49','赵慧敏','E804','zhaohuimin','2018-08-09 20:39:49',NULL,NULL,NULL,'0',NULL,'2018-06-14 00:00:00','W04','C1401','W17','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63&rdquo;双经布','米','C1055_1_1','B10175','32','3','C1055_1_1-06-01-01'),('c73de64ab14741a58bd5478405bda966','0ba9163bc0f7460da236d59f5c800aff','S215000200002','2018052400002','237.53','2018-05-24 16:45:20','王彦玲','E882','wangyanling','2018-05-24 16:45:20',NULL,NULL,NULL,'0',NULL,'2018-06-14 00:00:00','W04','C1401','W17','C020300200000','坯布-T/JC65/35(30+30)*20 132*62 63&rdquo;双经布','米','C1055_1_1','B10002','2','2','C1055_1_1-06-01-01');

/*Table structure for table `job_workshop_rollcloth` */

DROP TABLE IF EXISTS `job_workshop_rollcloth`;

CREATE TABLE `job_workshop_rollcloth` (
  `id` varchar(32) NOT NULL,
  `task_id` varchar(64) DEFAULT NULL COMMENT '任务编号',
  `weaving_id` varchar(32) DEFAULT NULL COMMENT '织机编号',
  `clothbeam_id` char(32) DEFAULT NULL COMMENT '布轴编号',
  `segement_id` char(32) DEFAULT NULL COMMENT '布段编号',
  `weavworker_id` varchar(32) DEFAULT NULL COMMENT '织布挡车工',
  `cloth_variety` varchar(32) DEFAULT NULL COMMENT '布匹品种',
  `segement_sequence` int(10) DEFAULT NULL COMMENT '分段布匹顺序号',
  `roll_machine_id` varchar(32) DEFAULT NULL COMMENT '卷布机编号',
  `fold_length` decimal(15,0) DEFAULT NULL COMMENT '折幅(cm）',
  `segement_length` decimal(10,2) DEFAULT NULL COMMENT '分段布长度米',
  `segement_level` varchar(32) DEFAULT NULL COMMENT '分段布等级',
  `rollmachine_speed` decimal(10,0) DEFAULT NULL COMMENT '卷布机车速（m/h）',
  `roll_worker` varchar(32) DEFAULT NULL COMMENT '卷布工',
  `worker_no` varchar(32) DEFAULT NULL COMMENT '员工编号',
  `roll_time` datetime DEFAULT NULL COMMENT '卷布时间',
  `create_user` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `del_flag` char(2) DEFAULT '0' COMMENT '0-正常 1-删除',
  `status` tinyint(4) DEFAULT NULL,
  `bill_no` varchar(32) DEFAULT NULL COMMENT '报功编号',
  `goods_id` varchar(32) DEFAULT NULL COMMENT '物料编码',
  `goods_name` varchar(64) DEFAULT NULL COMMENT '物料名称',
  `erp_bill_no` varchar(50) DEFAULT NULL COMMENT '生产订单号',
  `shift_id` varchar(32) DEFAULT NULL COMMENT '班次',
  `shift_style` varchar(32) DEFAULT NULL COMMENT '班别',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `job_workshop_rollcloth` */

/*Table structure for table `job_workshop_subcloth` */

DROP TABLE IF EXISTS `job_workshop_subcloth`;

CREATE TABLE `job_workshop_subcloth` (
  `id` char(32) NOT NULL,
  `condition_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '交布条件编号',
  `task_id` char(32) DEFAULT NULL COMMENT '任务编号',
  `exports_min_size1` varchar(20) DEFAULT NULL COMMENT '出口品合格1档最低码数',
  `exports_equ_pack1` varchar(20) DEFAULT NULL COMMENT '出口品合格1档成包码数',
  `exports_max_item1` varchar(20) DEFAULT NULL COMMENT '出口品合格1档成件限制数',
  `exports_min_size2` varchar(20) DEFAULT NULL COMMENT '出口品合格2档最低码数',
  `exports_equ_pack2` varchar(20) DEFAULT NULL COMMENT '出口品合格2档成包码数',
  `exports_max_item2` varchar(20) DEFAULT NULL COMMENT '出口品合格2档成件限制数',
  `exports_min_size3` varchar(20) DEFAULT NULL COMMENT '出口品合格3档最低码数',
  `exports_equ_pack3` varchar(20) DEFAULT NULL COMMENT '出口品合格3档成包码数',
  `exports_max_item3` varchar(20) DEFAULT NULL COMMENT '出口品合格3档成件限制数',
  `big_equ_pack` varchar(20) DEFAULT NULL COMMENT '大中零成包码数',
  `mixed_equ_pack` varchar(20) DEFAULT NULL COMMENT '混等拼件成包码数',
  `small_equ_pack` varchar(20) DEFAULT NULL COMMENT '小次零成包码数',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` char(1) DEFAULT '0' COMMENT '0-未删除 1-已删除',
  `remarks` varchar(200) DEFAULT NULL COMMENT '标志',
  PRIMARY KEY (`id`),
  UNIQUE KEY `condition` (`condition_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `job_workshop_subcloth` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
