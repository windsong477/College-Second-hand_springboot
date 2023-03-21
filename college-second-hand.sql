/*
 Navicat Premium Data Transfer

 Source Server         : ceshi
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3306
 Source Schema         : college-second-hand

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 21/03/2023 14:01:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `Aid` int(0) NOT NULL AUTO_INCREMENT,
  `Ausername` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Apassword` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '123',
  `Aphone` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `Aavatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `Atime` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`Aid`) USING BTREE,
  UNIQUE INDEX `Ausername`(`Ausername`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 231 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (225, 'admin', '123qwe', '12345678912', 'http://localhost:9090/file/download/31fca9a0495e4f98b669a57273e22efc.jpg', '2022-12-01 20:57:56');
INSERT INTO `admin` VALUES (231, '111', '123', NULL, NULL, '2022-12-22 01:50:07');

-- ----------------------------
-- Table structure for collection
-- ----------------------------
DROP TABLE IF EXISTS `collection`;
CREATE TABLE `collection`  (
  `Sid` int(0) NOT NULL,
  `Gid` int(0) NOT NULL,
  `Ctime` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`Sid`, `Gid`) USING BTREE,
  INDEX `C-Gid`(`Gid`) USING BTREE,
  CONSTRAINT `C-Gid` FOREIGN KEY (`Gid`) REFERENCES `goods` (`Gid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `C-sid` FOREIGN KEY (`Sid`) REFERENCES `student` (`Sid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of collection
-- ----------------------------
INSERT INTO `collection` VALUES (2, 1, '2023-01-04 00:22:57');
INSERT INTO `collection` VALUES (4, 5, '2022-12-27 13:27:30');
INSERT INTO `collection` VALUES (4, 37, '2022-12-27 13:26:07');
INSERT INTO `collection` VALUES (4, 38, '2023-01-03 16:38:46');

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `Gid` int(0) NOT NULL AUTO_INCREMENT,
  `Sid` int(0) NULL DEFAULT NULL,
  `Gname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `Gbuyprice` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `Gsellprice` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `Gphoto` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `Gdescribe` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `Gaudit` enum('0','1') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0',
  `Gstatus` enum('0','1','2','3') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0',
  `Gtime` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `Gupdate_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `SELLusername` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`Gid`) USING BTREE,
  INDEX `Sid`(`Sid`) USING BTREE,
  CONSTRAINT `Sid` FOREIGN KEY (`Sid`) REFERENCES `student` (`Sid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (1, 4, 'JAVA程序设计书籍', '20', '50', 'https://img.alicdn.com/imgextra/i4/2210679153628/O1CN01w9KZYu1cffb0BCKNW_!!2210679153628.jpg', '8成新，绝对良心啊！有木有！！', '1', '1', '2022-12-02 11:04:18', '2023-01-09 12:17:23', '帅哥强');
INSERT INTO `goods` VALUES (5, 2, '烧饼新鲜出炉！！', '9999', '14.6', '', '赛西施的王旭彤，你不想拥有一个吗？', '1', '1', '2022-12-02 21:13:01', '2023-03-21 13:59:41', '可爱彤');
INSERT INTO `goods` VALUES (13, 2, '测试1', NULL, '8', 'http://localhost:9090/file/download/a70a5b80e6d74822a078de0b85c8e3ce.jpg', NULL, '1', '2', '2022-12-25 17:10:30', '2023-01-09 12:19:05', '可爱彤');
INSERT INTO `goods` VALUES (36, 2, '测试2', NULL, '66', 'http://localhost:9090/file/download/ee848cfa7b6146ddb41d91992e11a2b0.jpg', NULL, '1', '1', '2022-12-25 21:07:32', '2023-01-09 12:19:08', '可爱彤');
INSERT INTO `goods` VALUES (37, 2, '测试3', NULL, '123', 'http://localhost:9090/file/download/95c1d9237c6540ddae0bcc5b90db1503.png', NULL, '1', '1', '2022-12-25 21:30:54', '2023-01-09 12:19:10', '可爱彤');
INSERT INTO `goods` VALUES (38, 2, '测试4', NULL, '123', 'http://localhost:9090/file/download/1aa12ee1c4304aab82a2793639cdf82d.jpg', NULL, '1', '2', '2022-12-26 17:28:32', '2023-01-09 12:19:12', '可爱彤');
INSERT INTO `goods` VALUES (39, 2, '好看的头像', '0.1', '0.1', 'http://localhost:9090/file/download/0b9164f676fb47c592da37ddf14724d5.jpg', '随便晒张图片，凑凑热闹', '1', '1', '2022-12-26 22:23:22', '2023-01-09 12:21:44', '可爱彤');
INSERT INTO `goods` VALUES (40, 4, '高科技智能水壶', '90', '30', 'https://s5.mogucdn.com/mlcdn/c45406/220418_7580f1edb1l12k1h6665agca57ij5_640x960.jpg', '这是一个神奇的水壶，它能实现你的愿望', '1', '1', '2023-01-09 12:14:23', '2023-01-09 12:18:37', '帅哥强');

-- ----------------------------
-- Table structure for leave
-- ----------------------------
DROP TABLE IF EXISTS `leave`;
CREATE TABLE `leave`  (
  `Lid` int(0) NOT NULL AUTO_INCREMENT,
  `Gid` int(0) NOT NULL,
  `Sid` int(0) NOT NULL,
  `Lmessage` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `Ltime` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`Lid`, `Gid`, `Sid`) USING BTREE,
  INDEX `L-Gid`(`Gid`) USING BTREE,
  INDEX `L-Sid`(`Sid`) USING BTREE,
  CONSTRAINT `L-Gid` FOREIGN KEY (`Gid`) REFERENCES `goods` (`Gid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `L-Sid` FOREIGN KEY (`Sid`) REFERENCES `student` (`Sid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of leave
-- ----------------------------
INSERT INTO `leave` VALUES (1, 5, 4, '好可爱，我要买！！！', '2023-01-02 22:07:46');
INSERT INTO `leave` VALUES (4, 5, 2, '那可不，这可是的镇店之宝哦~不容小视！', '2023-01-02 22:37:48');
INSERT INTO `leave` VALUES (8, 38, 4, '请问这个可以便宜一点吗？', '2023-01-03 16:37:50');
INSERT INTO `leave` VALUES (10, 38, 2, '不可以哦~亲，这个最低价了呢，过了这村就没这店了', '2023-01-03 16:40:39');
INSERT INTO `leave` VALUES (13, 5, 2, '走过路过不要错过，快进来看一看了哇！心动不如行动，大家赶快行动起来，难得的机会啦，跳楼大减价有没有，14.6你买不到吃亏，买不到上当，你什么都买不到，除了这个旭彤，简直太棒了有没有，赶快抢购吧！抓紧时间，最后一次机会，赶快行动起来吧~特卖特卖！冬季大优惠，难得难得！', '2023-01-03 22:56:22');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `Oid` int(0) NOT NULL AUTO_INCREMENT,
  `BUYSid` int(0) NULL DEFAULT NULL,
  `SELLSid` int(0) NULL DEFAULT NULL,
  `Gid` int(0) NULL DEFAULT NULL,
  `Oprice` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `Otime` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `Oupdate_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`Oid`) USING BTREE,
  INDEX `SELLSid`(`SELLSid`) USING BTREE,
  INDEX `BUYSid`(`BUYSid`) USING BTREE,
  INDEX `O-Gid`(`Gid`) USING BTREE,
  CONSTRAINT `BUYSid` FOREIGN KEY (`BUYSid`) REFERENCES `student` (`Sid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `O-Gid` FOREIGN KEY (`Gid`) REFERENCES `goods` (`Gid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `SELLSid` FOREIGN KEY (`SELLSid`) REFERENCES `student` (`Sid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES (10, 4, 2, 5, '14.6', '2022-12-01 20:38:19', '2022-12-06 00:44:49');
INSERT INTO `order` VALUES (21, 4, 2, 38, '123', '2022-12-29 15:13:07', NULL);
INSERT INTO `order` VALUES (22, 4, 2, 13, '8', '2022-12-29 19:36:24', NULL);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `Sid` int(0) NOT NULL AUTO_INCREMENT,
  `Susername` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Sno` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `Spassword` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '123',
  `Sschool` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `Scollege` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `Sgrade` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `Sclass` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `Smajor` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `Ssex` enum('F','M','A') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'A',
  `Sqq` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `Swx` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `Sphone` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `Sadress` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `Savatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `Stime` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`Sid`) USING BTREE,
  UNIQUE INDEX `Susername`(`Susername`) USING BTREE,
  INDEX `Sid`(`Sid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (2, '可爱彤', '11123456', '123', 'xx大学', '计算机科学与技术学院', '大三', '计科202', '计算机科学与技术', 'M', '0000', 'yyyy', '10010', '北校区11栋522', 'http://localhost:9090/file/download/d52f3d9cde9a468bbe30635d037542bb.png', '2022-12-02 15:39:32');
INSERT INTO `student` VALUES (4, '帅哥强', '2224567', '123', 'xx大学', '计算机科学与技术学院', '大三', '计科202', '计算机', 'A', '1111', 'xxxx', '10086', '北校区11栋608', NULL, '2022-12-01 22:45:43');

SET FOREIGN_KEY_CHECKS = 1;
