-- MySQL dump 10.13  Distrib 5.7.28, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: cinema
-- ------------------------------------------------------
-- Server version	5.7.28

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `admin_name` varchar(20) COLLATE utf8_bin NOT NULL,
  `admin_password` char(6) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`admin_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES ('admin','123456');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cinema_seat`
--

DROP TABLE IF EXISTS `cinema_seat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cinema_seat` (
  `film_name` varchar(40) COLLATE utf8_bin NOT NULL,
  `film_playtime` datetime NOT NULL,
  `film_playroom` int(2) NOT NULL,
  `seat` varchar(20) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`film_name`,`film_playroom`,`film_playtime`,`seat`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cinema_seat`
--

LOCK TABLES `cinema_seat` WRITE;
/*!40000 ALTER TABLE `cinema_seat` DISABLE KEYS */;
INSERT INTO `cinema_seat` VALUES ('南方车站的聚会','2019-12-10 15:00:00',6,'1-9'),('南方车站的聚会','2019-12-10 15:00:00',6,'2-5'),('南方车站的聚会','2019-12-10 15:00:00',6,'2-9'),('南方车站的聚会','2019-12-10 15:00:00',6,'3-5');
/*!40000 ALTER TABLE `cinema_seat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `film_arrange`
--

DROP TABLE IF EXISTS `film_arrange`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `film_arrange` (
  `film_playtime` datetime NOT NULL,
  `film_playroom` int(2) NOT NULL,
  `film_name` varchar(40) COLLATE utf8_bin NOT NULL,
  `film_price` double DEFAULT NULL,
  PRIMARY KEY (`film_playtime`,`film_name`,`film_playroom`),
  KEY `film_name` (`film_name`),
  CONSTRAINT `film_name` FOREIGN KEY (`film_name`) REFERENCES `film_info` (`film_name`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `film_arrange`
--

LOCK TABLES `film_arrange` WRITE;
/*!40000 ALTER TABLE `film_arrange` DISABLE KEYS */;
INSERT INTO `film_arrange` VALUES ('2019-12-10 15:00:00',6,'南方车站的聚会',20),('2019-12-10 15:00:00',5,'平原上的夏洛克',20),('2019-12-10 16:00:00',3,'流浪地球',20),('2019-12-10 17:00:00',6,'我不是药神',20),('2019-12-10 19:00:00',4,'复仇者联盟4',40),('2019-12-10 19:00:00',6,'飞驰人生',40),('2019-12-10 20:00:00',1,'哪吒之魔童降世',30),('2019-12-10 20:00:00',7,'绿皮书',40),('2019-12-10 22:00:00',1,'少年的你',30),('2019-12-11 12:00:00',5,'平原上的夏洛克',15),('2019-12-11 12:00:00',6,'我不是药神',15),('2019-12-11 12:00:00',2,'流浪地球',15),('2019-12-11 12:00:00',7,'绿皮书',15),('2019-12-11 14:00:00',2,'南方车站的聚会',20),('2019-12-11 14:00:00',1,'少年的你',20),('2019-12-11 20:00:00',3,'哪吒之魔童降世',40),('2019-12-11 20:00:00',4,'复仇者联盟4',40),('2019-12-11 20:00:00',6,'飞驰人生',40),('2019-12-12 06:00:00',1,'南方车站的聚会',20),('2019-12-12 09:00:00',2,'南方车站的聚会',20),('2019-12-12 10:00:00',3,'南方车站的聚会',20),('2019-12-12 12:00:00',5,'平原上的夏洛克',15),('2019-12-12 12:00:00',6,'我不是药神',15),('2019-12-12 12:00:00',2,'流浪地球',15),('2019-12-12 12:00:00',7,'绿皮书',15),('2019-12-12 14:00:00',2,'南方车站的聚会',20),('2019-12-12 14:00:00',4,'南方车站的聚会',20),('2019-12-12 14:00:00',5,'南方车站的聚会',20),('2019-12-12 14:00:00',6,'南方车站的聚会',20),('2019-12-12 14:00:00',7,'南方车站的聚会',20),('2019-12-12 14:00:00',1,'少年的你',20),('2019-12-12 16:00:00',4,'南方车站的聚会',20),('2019-12-12 16:00:00',5,'南方车站的聚会',20),('2019-12-12 16:00:00',6,'南方车站的聚会',20),('2019-12-12 16:00:00',7,'南方车站的聚会',20),('2019-12-12 20:00:00',3,'哪吒之魔童降世',40),('2019-12-12 20:00:00',4,'复仇者联盟4',40),('2019-12-12 20:00:00',6,'飞驰人生',40);
/*!40000 ALTER TABLE `film_arrange` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `film_info`
--

DROP TABLE IF EXISTS `film_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `film_info` (
  `film_name` varchar(40) COLLATE utf8_bin NOT NULL,
  `shelt_time` date DEFAULT NULL,
  `unshelt_time` date DEFAULT NULL,
  `film_info` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `starring` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `film_type` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `duration` int(3) DEFAULT NULL,
  `film_score` double DEFAULT NULL,
  `film_director` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`film_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `film_info`
--

LOCK TABLES `film_info` WRITE;
/*!40000 ALTER TABLE `film_info` DISABLE KEYS */;
INSERT INTO `film_info` VALUES ('两只老虎','2018-11-29','2018-12-29','低配绑匪遇上了极品人质，威逼不成，反被人质利诱，替人质办了三件事的荒诞喜剧。','葛优 / 赵薇 / 乔杉 / 范伟 / 闫妮','剧情/喜剧',96,6.3,'李非'),('何以为家','2020-04-29','2021-05-30','法庭上，十二岁的男孩赞恩向法官状告他的亲生父母，原因是，他们给了他生命。是什么样的经历让一个孩子做出如此不可思议的举动？','赞恩·阿尔·拉菲亚 / 约丹诺斯·希费罗','剧情',117,9.1,'娜丁·拉巴基'),('冰雪奇缘2','2018-11-22','2018-12-22','安娜、艾莎一伙人将深入神秘魔法森林，发现到艾伦戴尔王国长久以来深藏的秘密，一个有着风火水土元素的魔法国度，以及艾莎魔法来源的真相。','克里斯汀·贝尔 / 伊迪娜·门泽尔','喜剧/动画/冒险',104,7.3,'克里斯·巴克'),('利刃出鞘','2018-11-29','2018-12-30','富豪小说家霍华德·斯隆比在自己85岁生日第二天，被发现在自家庄园离奇自杀，遗留了亿万遗产。久负盛名的大侦探布兰科（丹尼尔·克雷格饰）突然被匿名人士雇佣调查此案真相。','丹尼尔·克雷格/安娜·德·阿玛斯','喜剧/悬疑/犯罪',130,8.4,'莱恩·约翰逊'),('南方车站的聚会','2019-12-06','2020-01-06','南方某城市，重案队长刘队重金悬赏在逃罪犯周泽农。陪泳女刘爱爱 、周泽农曾经的好友华华、五年未见的妻子杨淑俊，各色人等各怀心事，相继被卷入这场罪与罚的追击旋涡。','胡歌 / 桂纶镁 / 廖凡 / 万茜 / 奇道','剧情/犯罪',113,7.7,'刁亦男'),('哪吒之魔童降世','2019-07-26','2020-01-27','天地灵气孕育出一颗能量巨大的混元珠，元始天尊将混元珠提炼成灵珠和魔丸，灵珠投胎为人，助周伐纣时可堪大用；而魔丸则会诞出魔王，为祸人间。然而阴差阳错，灵珠和魔丸竟然被掉包。本应是灵珠英雄的哪吒却成了混世大魔王。','吕艳婷 / 囧森瑟夫 / 瀚墨 / 陈浩 / 绿绮','动画/奇幻',110,8.5,'饺子'),('复仇者联盟4','2019-04-24','2020-05-25','一声响指，宇宙间半数生命灰飞烟灭。几近绝望的复仇者们在惊奇队长的帮助下找到灭霸归隐之处，却得知六颗无限宝石均被销毁，希望彻底破灭。','小罗伯特·唐尼 / 克里斯·埃文斯','动作/科幻/奇幻/冒险',181,8.5,'安东尼/ 乔·罗素'),('头号玩家','2020-03-30','2021-05-01','故事发生在2045年，虚拟现实技术已经渗透到了人类生活的每一个角落。詹姆斯哈利迪一手建造了名为“绿洲”的虚拟现实游戏世界，临终前，他宣布自己在游戏中设置了一个彩蛋，找到这枚彩蛋的人即可成为绿洲的继承人。','泰伊·谢里丹 / 奥利维亚·库克 / 本·门德尔森','动作/科幻/冒险',140,8.7,'史蒂文·斯皮尔伯格'),('少年的你','2019-10-25','2019-12-30','陈念是一名即将参加高考的高三学生，同校女生胡晓蝶的跳楼自杀让她的生活陷入了困顿之中。胡晓蝶死后，陈念遭到了以魏莱为首的三人组的霸凌。','周冬雨 / 易烊千玺 / 尹昉 / 周也 / 吴越','剧情/爱情/犯罪',135,8.4,'曾国祥'),('平原上的夏洛克','2019-11-29','2020-02-10','超英翻盖新房 ，占义、树河前来帮忙，没想到树河却因意外车祸入院，司机肇事逃逸，超英和占义化身“平原侦探”，踏上了一段令人啼笑皆非的荒诞追凶之旅。','徐朝英 / 张占义 / 宿树河','剧情/喜剧/悬疑',98,7.8,'徐磊'),('我不是药神','2019-07-05','2020-08-06','普通中年男子程勇经营着一家保健品店，失意又失婚。不速之客吕受益的到来，让他开辟了一条去印度买药做“代购”的新事业，虽然困难重重，但他在这条“买药之路”上发现了商机，一发不可收拾地做起了治疗慢粒白血病的印度仿制药独家代理商。','徐峥 / 王传君 / 周一围 / 谭卓 / 章宇','剧情/喜剧',117,9,'文牧野'),('无双 無雙','2020-09-30','2021-10-31','身陷囹圄的李问被引渡回港，他原本隶属于一个的跨国假钞制贩组织。该组织曾犯下过多宗恶性案件，而首脑“画家”不仅始终逍遥法外，连真面目都没人知道。','周润发 / 郭富城 / 张静初 / 冯文娟 / 廖启智','剧情/动作/犯罪',130,8.1,'庄文强'),('无名之辈','2020-11-16','2021-12-17','来自乡村的笨贼眼镜和大头抢了一家手机店，慌乱之中逃进坐着轮椅的单身女子嘉旗的家中。嘉旗早已失去活着的欲望，她强横地要求俩笨贼杀死自己。','陈建斌 / 任素汐 / 潘斌龙 / 章宇','剧情/喜剧',108,8.1,'饶晓志'),('流浪地球','2019-02-05','2020-03-16','近未来，科学家们发现太阳急速衰老膨胀，短时间内包括地球在内的整个太阳系都将被太阳所吞没。为了自救，人类提出一个名为“流浪地球”的大胆计划，即倾全球之力在地球表面建造上万座发动机和转向发动机，推动地球离开太阳系，用2500年的时间奔往另外一个栖息之地。','屈楚萧 / 吴京 / 李光洁 / 吴孟达 / 赵今麦','科幻 / 灾难',125,7.9,'郭帆'),('海王','2020-12-07','2021-01-08','许多年前，亚特兰蒂斯女王和人类相知相恋，共同孕育了爱情的结晶——后来被陆地人称为海王的亚瑟·库瑞。','杰森·莫玛 / 艾梅柏·希尔德 / 威廉·达福','动作/奇幻/冒险',143,7.6,'温子仁'),('白蛇：缘起','2020-01-11','2021-02-12','幽暗洞中，白蛇苦苦修炼却不得其法，小青见此情景，将发髻上的碧玉簪子取下，令白蛇攥在手中。那一刻，五百年前的记忆瞬间苏醒。','张喆 / 杨天翔 / 唐小喜 / 刘薇 / 张遥函','爱情/动画/奇幻',99,7.9,'黄家康'),('红海行动','2020-02-16','2021-03-18','中东国家伊维亚共和国发生政变，武装冲突不断升级。刚刚在索马里执行完解救人质任务的海军护卫舰临沂号，受命前往伊维亚执行撤侨任务。','张译 / 黄景瑜 / 海清 / 杜江 / 蒋璐霞','动作/战争',138,8.3,'林超贤'),('绿皮书','2019-03-01','2020-04-02','托尼（维果·莫腾森 Viggo Mortensen 饰）是一个吊儿郎当游手好闲的混混，在一家夜总会做侍者。这间夜总会因故要停业几个月，可托尼所要支付的房租和生活费','维果·莫腾森 / 马赫沙拉·阿里','剧情/喜剧/传记',130,8.9,'彼得·法雷里'),('调音师','2020-04-03','2021-05-04','双目失明的钢琴家阿卡什为了参加国际大赛，平日里通过私人授课赚取经费。事实上他的眼睛完全正常，只不过希望通过这种方式感受不同的生活。','阿尤斯曼·库拉纳 / 塔布 / 拉迪卡·艾普特','喜剧/悬疑/犯罪',139,8.3,'斯里兰姆·拉格万'),('飞驰人生','2019-02-05','2020-03-06','曾经叱咤风云的拉力赛车手张驰，五年前因私自赛车而被禁赛。从天堂跌落谷底，张驰饱尝生活的艰辛。','沈腾 / 黄景瑜 / 尹正 / 张本煜 / 尹昉','喜剧',98,6.9,'韩寒');
/*!40000 ALTER TABLE `film_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase_record`
--

DROP TABLE IF EXISTS `purchase_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `purchase_record` (
  `record_id` int(10) NOT NULL AUTO_INCREMENT,
  `user_number` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `film_name` varchar(40) COLLATE utf8_bin DEFAULT NULL,
  `film_scene` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `purchase_time` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `seat_number` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `ticket_code` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`record_id`),
  UNIQUE KEY `record_id_UNIQUE` (`record_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase_record`
--

LOCK TABLES `purchase_record` WRITE;
/*!40000 ALTER TABLE `purchase_record` DISABLE KEYS */;
INSERT INTO `purchase_record` VALUES (1,'2017012979','南方车站的聚会','2019-12-10 15:00:00/6','2019-12-10 13:50:19','1-9','1975'),(2,'2017012979','南方车站的聚会','2019-12-10 15:00:00/6','2019-12-10 13:50:19','2-5','1965'),(3,'2017012979','南方车站的聚会','2019-12-10 15:00:00/6','2019-12-10 13:50:19','2-9','1880'),(4,'2017012979','南方车站的聚会','2019-12-10 15:00:00/6','2019-12-10 13:50:19','3-5','1162');
/*!40000 ALTER TABLE `purchase_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_info` (
  `user_number` varchar(20) COLLATE utf8_bin NOT NULL,
  `user_password` varchar(6) COLLATE utf8_bin DEFAULT NULL,
  `phone_number` varchar(11) COLLATE utf8_bin DEFAULT NULL,
  `user_name` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `user_sex` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `user_birthday` date DEFAULT NULL,
  PRIMARY KEY (`user_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_info`
--

LOCK TABLES `user_info` WRITE;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
INSERT INTO `user_info` VALUES ('2017012979','123456','18329300217','无极滴司机','女','1999-03-04');
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'cinema'
--

--
-- Dumping routines for database 'cinema'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-10 22:27:09
