/*
 Navicat Premium Data Transfer

 Source Server         : YANG
 Source Server Type    : MySQL
 Source Server Version : 50645
 Source Host           : localhost:3306
 Source Schema         : topshow

 Target Server Type    : MySQL
 Target Server Version : 50645
 File Encoding         : 65001

 Date: 22/11/2019 10:42:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for about
-- ----------------------------
DROP TABLE IF EXISTS `about`;
CREATE TABLE `about`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of about
-- ----------------------------
INSERT INTO `about` VALUES (1, '金彦舞蹈石家庄店隶属于河北晗根舞蹈健身有限公司，我店坐落于石家庄市核心商圈中山路银座东方城市购物广场\n金彦舞蹈倡导绿色健康环保的生活理念，依靠自身强大的师资力量为您提供专业的健身指导，是以中国舞、爵士舞、钢管舞、肚皮舞等多元化舞蹈融合康体塑性为一体的舞蹈美体机构\n本店营业面积1200平方米，挑高4.7米，四面通透落地大开窗，24小时中央空调，新风系统接入，以强大的师资团队、专业的硬件设施、完善的服务理念迎接每一位热爱舞蹈，热爱生活的你，为您的健康学习营造专属平台。');

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `adminname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员账号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员密码',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员手机号',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员邮箱',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员实际状态',
  `forceTime` datetime(0) NULL DEFAULT NULL COMMENT '管理生效时间',
  `lastLoginTime` datetime(0) NOT NULL COMMENT '上次登录时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理员表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('ADMIN7826110349', 'TOPSHOW', 'MjVmOWU3OTQzMjNiNDUzODg1ZjUxODFmMWI2MjRkMGI=', '18802927580', '344295704@qq.com', '0', '2019-11-07 18:56:07', '2019-11-07 18:56:10');

-- ----------------------------
-- Table structure for admin_nav
-- ----------------------------
DROP TABLE IF EXISTS `admin_nav`;
CREATE TABLE `admin_nav`  (
  `nav_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '导航栏ID',
  `nav_parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父级ID',
  `nav_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '导航栏标题',
  `nav_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '导航路径',
  `nav_index` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '索引',
  `is_target` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否打开新标签',
  `nav_icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `nav_spread` tinyint(4) NULL DEFAULT 0 COMMENT '是否展开',
  `nav_role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限ID',
  PRIMARY KEY (`nav_id`) USING BTREE,
  INDEX `nav_parent_id`(`nav_parent_id`) USING BTREE,
  INDEX `nav_role`(`nav_role`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin_nav
-- ----------------------------
INSERT INTO `admin_nav` VALUES (1, 0, '首页内容', '0', 'contentManagement', NULL, '&#xe63c;', 0, '1,3');
INSERT INTO `admin_nav` VALUES (2, 1, '舞蹈培训师资团队', 'danceteacher/list', 'articleList', NULL, 'icon-text', 0, '1,3');
INSERT INTO `admin_nav` VALUES (3, 1, '培训项目', 'dancetraining/list', 'otherPage', NULL, '&#xe630;', 0, '1,3');
INSERT INTO `admin_nav` VALUES (6, 0, '关于我们', '0', 'about us', NULL, 'icon-icon10', 0, '1,3');
INSERT INTO `admin_nav` VALUES (7, 6, '公司简介', 'about/page', 'about', NULL, '&#xe612;', 0, '1,3');
INSERT INTO `admin_nav` VALUES (26, 1, 'BANNER信息', 'banner/list', 'banner', NULL, 'icon-mokuai', 0, '1,3');
INSERT INTO `admin_nav` VALUES (27, 1, '视频管理', 'video/list', 'video', NULL, 'icon-mokuai', 0, '1,3');
INSERT INTO `admin_nav` VALUES (28, 1, '课程管理', 'cources/list', 'cources', NULL, 'layui-icon layui-icon-app', 0, '1,3');
INSERT INTO `admin_nav` VALUES (29, 6, '留言管理', 'suggestion/list', 'suggestion', NULL, 'icon-icon10', 0, '1,3');
INSERT INTO `admin_nav` VALUES (30, 6, '新闻管理', 'news/list', 'news', NULL, 'icon-mokuai', 0, '1,3');
INSERT INTO `admin_nav` VALUES (31, 6, '动态课程表', 'cources/table/list', 'table_add', NULL, 'icon-mokuai', 0, '1,3');

-- ----------------------------
-- Table structure for banner
-- ----------------------------
DROP TABLE IF EXISTS `banner`;
CREATE TABLE `banner`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `imglocal` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of banner
-- ----------------------------
INSERT INTO `banner` VALUES (1, '/static/img/banner.jpg');
INSERT INTO `banner` VALUES (2, '/static/img/banner.jpg');
INSERT INTO `banner` VALUES (3, '/static/img/banner.jpg');
INSERT INTO `banner` VALUES (4, '/static/img/banner.jpg');
INSERT INTO `banner` VALUES (5, '/static/img/banner.jpg');
INSERT INTO `banner` VALUES (6, '/static/img/banner.jpg');

-- ----------------------------
-- Table structure for cources
-- ----------------------------
DROP TABLE IF EXISTS `cources`;
CREATE TABLE `cources`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `tableimg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of cources
-- ----------------------------
INSERT INTO `cources` VALUES (1, '/static/img/kebiao-cources.png/');
INSERT INTO `cources` VALUES (2, '/static/img/kebiao-cources.png/');

-- ----------------------------
-- Table structure for dance_teacher
-- ----------------------------
DROP TABLE IF EXISTS `dance_teacher`;
CREATE TABLE `dance_teacher`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '舞蹈老师名字',
  `graduate_school` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '毕业学院',
  `teaching_qualification` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '教学资质',
  `teaching_scope` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '教学范围',
  `describe` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '描述',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `international` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '国家',
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '导师图片',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '舞蹈老师数据表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of dance_teacher
-- ----------------------------
INSERT INTO `dance_teacher` VALUES ('DANCETEACHER1032754618', '土豆', '北京舞蹈学院', '五年舞蹈经验\n一年以上任教经验\n组织三次学校晚会舞蹈', '掌握hiphop，poping，breaking舞种', '', 25, '中国', '/admin/uploads/teacher/20191116160211360.jpg');
INSERT INTO `dance_teacher` VALUES ('DANCETEACHER4263510789', 'YOYO', '北京舞蹈学院', '教龄3年\n2014年获得高级瑜伽资深导师资格证\n2015年获得热力瑜伽资深导师资格证\n2016年获得空中瑜伽资深导师资格证\n2019年参加念瑜伽维密瘦身沙龙的学习\n专注教培3年，用心做瑜伽，用爱去奉献！', '瑜伽', '', 25, '中国', '/admin/uploads/teacher/20191116111951793.jpg');
INSERT INTO `dance_teacher` VALUES ('DANCETEACHER4621057891', '苏苏老师', '北京舞蹈学院', '曽学习于Jerome,David Lim,Ben Faustino,Subin Kim,小P,申旭阔,神童童等国内外大师', 'Jazz Hip-Hop', '河北《冀将舞战》VOL.2成人齐舞组亚军\n这就是街舞石家庄补给站成人齐舞组第四名', 25, '中国', '/admin/uploads/teacher/20191116112111777.jpg');
INSERT INTO `dance_teacher` VALUES ('DANCETEACHER8419103572', '伊利', '北京舞蹈学院', '中国舞蹈家协会注册街舞老师\n2019年赴北京T.I以及西安等地进修\n曾跟随韩国知名舞蹈大师HeeSoo老师学习\n在Ark培训中参与编排的舞蹈获奖', 'Hip-hop jazz', '中国舞蹈家协会注册街舞老师\n2019年赴北京T.I以及西安等地进修\n曾跟随韩国知名舞蹈大师HeeSoo老师学习\n在Ark培训中参与编排的舞蹈获奖', 25, '中国', '/admin/uploads/teacher/20191116161031514.jpg');
INSERT INTO `dance_teacher` VALUES ('DANCETEACHER8651710943', '魏魏', '北京舞蹈学院', '2015年参加校园舞蹈大赛获得第一名，2016年深造考国际教练证 ，2019年赴成都舞邦进修，先后跟李悦莎莎，龙菲，abby, yorking, 兔子，大杭等知名大师学习，以性感力量风格为主，深受学员喜爱。', '韩舞 jazz', '对于舞蹈的阐述：我们都不是天才，过程乏味，结果才会美好。\n总结：能把最简单的舞蹈跳出不一样的味道才是真正的高水平', 25, '中国', '/admin/uploads/teacher/20191116111913840.jpg');
INSERT INTO `dance_teacher` VALUES ('DANCETEACHER9437261108', '李雪', '北京舞蹈学院', '2011年开始学习传统哈他初、中、高级瑜伽，获得亚洲瑜伽导师协会高级导师资格证书。\n擅长传统哈他瑜伽，高温瑜伽，球瑜伽，普拉提，流瑜伽，产后系列修复等。\n教学精准，认真负责，富有亲和力。', '传统哈他瑜伽，高温瑜伽，球瑜伽，普拉提，流瑜伽，产后系列修复等。', '瑜伽格言：不惑于流派，不困于体式;不畏将来，不念过往;不乱于心，安心当下。如此，便是瑜伽。', 25, '中国', '/admin/uploads/teacher/20191116165912947.jpg');
INSERT INTO `dance_teacher` VALUES ('DANCETEACHER9437431108', '小雪', '北京舞蹈学院', '中国舞蹈家协会注册中国舞老师\n2017年参加民间民族舞大赛获得第一名\n2018年参加邯郸市剧目竞赛演出获得优秀教师\n2018年获得中国舞蹈家协会教师资格证\n2019年在贵州都匀国际影视城参演舞蹈表演《敦煌》\n', '芭蕾形体 民间民族舞 古典舞', '教学理念：舞蹈 不仅通过技术训练改变外显气质，更重要是进行相关知识的多元化实施和整合，让舞蹈特有的育人功能发挥出来，使其成为学生学习的重要途径，进而走向健康人格理念下的快乐舞蹈教育。', 21, '中国', '/admin/uploads/teacher/20191116111826325.jpg');

-- ----------------------------
-- Table structure for dance_training
-- ----------------------------
DROP TABLE IF EXISTS `dance_training`;
CREATE TABLE `dance_training`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `englishname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '英文名字',
  `dancename` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '舞蹈名字',
  `danceimg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '舞蹈配图',
  `dancedetail` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '舞蹈简介',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '培训舞蹈表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of dance_training
-- ----------------------------
INSERT INTO `dance_training` VALUES ('DANCETRAINING1034927156', 'folk dance', '民族舞', '/admin/uploads/training/20191116145615660.jpg', '民族舞泛指产生并流传于民间、受民俗文化制约、即兴表演但风格相对稳定、以自娱为主要功能的舞蹈形式。不同地区、国家、民族的民间舞蹈，由于受生存环境、风俗习惯、生活方式、民族性格、文化传统、宗教信仰等因素影响，以及受表演者的年龄性别等生理条件所限，在表演技巧和风格上有着十分明显的差异。民间舞不乏朴实无华、形式多样、内容丰富、形象生动等特点，历来都是各国古典舞、民间舞、宫廷舞和专业舞蹈创作不可或缺的素材来源。\n民族民间舞是一个多层次的概念和可伸缩的界面，它可以包容各种程度的加工。民族舞是一个民族的标志物，（如孔雀舞等，就属于民族舞）是一个民族乃至一个国家的灵魂。它需要艺术家进行高度的再创造，从民间来最后又回到民间去，练习民族舞好处有如下：1、 有较强趣味性 2、 培养舞者气质 3、 让人心情愉悦 4、有益身心。');
INSERT INTO `dance_training` VALUES ('DANCETRAINING1045231987', 'Latin dance', '拉丁舞', '/admin/uploads/training/20191116145643144.jpg', '国际标准拉丁舞又指体育舞蹈，分为伦巴、恰恰、牛仔、桑巴和斗牛五支舞；协会分为世界体育舞蹈联合会（WDSF）和世界国际标准舞总会（WDC）、中国国际标准舞总会(CBDF)、中国体育舞蹈联合会(CDSF)等协会。\n拉丁舞是体育竞技舞蹈，爆发力，极强的风格，技巧是它的特点，有很大的竞技体育舞蹈发挥空间，现在已经入亚运会正式比赛项目，拉丁舞2014年11月12日正式申请进入奥运会，现正在审批阶段，拉丁舞每年最高赛事WDSF协会会在世界各地成员国选择不同地点举行，对于世界上所有参与国际标准舞工作的人士而言，英国的黑池可谓是国际标准舞活动之首。WDC会在固定地点英国举办黑池舞蹈节，深受欧洲人民喜爱。');
INSERT INTO `dance_training` VALUES ('DANCETRAINING1045235568', 'Jazz', '爵士舞', '/admin/uploads/training/20191116145703972.jpg', '爵士舞，即美国现代舞，是一种急促又富动感的节奏型舞蹈，是属于一种外放性的舞蹈，不像古典芭蕾舞或现代舞所表现的一种内敛性的舞蹈。爵士舞蹈最初是非洲舞蹈的延伸，经被贩卖作为奴隶的黑人群体带到美国本土，而在美国逐渐演进形成本土化、大众化的舞蹈。\n爵士舞主要是追求愉快、活泼、有生气的一种舞蹈。它的特征是可自由自在的跳，不必像传统式的古典芭蕾必须局限於一种形式与遵守固有的姿态。随着美国流行文化的发展，伴随着芭蕾舞普及和百老汇文化的推广，特别是街舞文化体系的出现，爵士舞已经和以往的马甲、西裤、皮鞋、手杖的表演形式完全不同，在博采众家之长之后慢慢发展出属于自己的独特风格，早已不再是自由即兴的表演格式，在20世纪末期的流行音乐及舞蹈MV的发展大潮中，流行天王迈克尔杰克逊为杰出代表。为了适应现代表演的需要，之前那种完全自由化的风格已经慢慢被各种风格的规律和要求所限制，变成一种自由与规律并存的风格。');
INSERT INTO `dance_training` VALUES ('DANCETRAINING153968247', 'Pole dance', '钢管舞', '/admin/uploads/training/20191116145719426.jpg', '钢管舞（Pole dance），是利用钢管为道具，综合爵士舞、现代舞、民族舞、芭蕾舞、瑜伽、肚皮舞、拉丁舞等各种不同风格舞种，又集合杂技、艺术体操、健身类别的运动而衍生出来的新型舞蹈。2007年在中国举办首届钢管舞公开赛，带动了中国钢管舞朝健康积极的方向发展，2008年中国加入世界钢管舞协会，标志着中国钢管舞也朝竞技道路发展。\n竞技钢管舞由舞蹈、体操、杂技等元素存在，是集多种艺术于一体的运动，但由于比较小众并没受到体育部门立项，这并不影响它的发展速度；据悉，每年全球有近百万人投入练习钢管舞，有专家预测它未来极有可能成为奥运会项目。');
INSERT INTO `dance_training` VALUES ('DANCETRAINING1789234510', 'balletto', '芭蕾形体', '/admin/uploads/training/2019111614573904.jpg', '芭蕾是一门古典艺术，是起源于意大利、成长于法国、成熟于俄罗斯、盛行于世界的古典舞种。它是在古希腊人体艺术的基础上发展起来的，在长期的实践中形成了科学的训练体系。');
INSERT INTO `dance_training` VALUES ('DANCETRAINING4581067139', 'Classical dancing', '古典舞', '/admin/uploads/training/2019111614580266.jpg', '古典舞是指在民间传统舞蹈的基础上，经过历代专业工作者提炼、整理、加工、创造，并经过较长时期艺术实践的检验，流传下来的被认为是具有一定典范意义的和古典风格特点的舞蹈。\n中国的古典舞创立于五十年代，曾一度被一些人称作“戏曲舞蹈”。它本身就是介于戏曲与舞蹈之间的混合物，也就是说还未完全从戏曲中蜕变出来，称它为戏曲。');
INSERT INTO `dance_training` VALUES ('DANCETRAINING584712693', 'Modern dance ', '现代舞', '/admin/uploads/training/20191116145822191.jpg', '现代舞是20世纪初在西方兴起的一种与古典芭蕾相对立的舞蹈派别。其主要美学观点是反对古典芭蕾的因循守旧、脱离现实生活和单纯追求技巧的形式主义倾向。\n主张摆脱古典芭蕾舞过于僵化的动作程式的束缚，以合乎自然运动法则的舞蹈动作，自由地抒发人的真实情感，强调舞蹈艺术要反映现代社会生活。');
INSERT INTO `dance_training` VALUES ('DANCETRAINING7610349128', 'Yoga', '瑜伽', '/admin/uploads/training/20191116145838332.jpg', '古典舞是指在民间传统舞蹈的基础上，经过历代专业工作者提炼、整理、加工、创造，并经过较长时期艺术实践的检验，流传下来的被认为是具有一定典范意义的和古典风格特点的舞蹈。\n中国的古典舞创立于五十年代，曾一度被一些人称作“戏曲舞蹈”。它本身就是介于戏曲与舞蹈之间的混合物，也就是说还未完全从戏曲中蜕变出来，称它为戏曲。');
INSERT INTO `dance_training` VALUES ('DANCETRAINING9452108713', 'Street Dance', '街舞', '/admin/uploads/training/2019111614590935.jpg', '街舞是起源于美国，基于不同的街头文化或音乐风格而产生的多个不同种类的舞蹈的统称，最早的街舞舞种为Locking，起源于20世纪六十年代。\n（注：街舞的英文翻译有且仅有Street Dance这一种，Hiphop只是其中的一种） ，动作是由各种走、跑、跳组合而成，并通过头、颈、肩、上肢、躯干等关节的屈伸、转动、绕环、摆振、波浪形扭动等连贯组合而成的，各个动作都有其特定的健身效果，既注意了上肢与下肢、腹部与背部、头部与躯干动作的协调，又注意了组成各环节各部分独立运动');
INSERT INTO `dance_training` VALUES ('DANCETRAINING9754281061', 'China classic dance', '中国舞', '/admin/uploads/training/20191116152821264.jpg', '中国舞是经过历代专业舞者、学者的创造、整理、提炼、加工、田野调查，并经过较长期艺术实践的检验流传下来的具有一定典范意义的中国古典风格的特色舞蹈。\n中国舞的身韵训练充分体现了我们中华民族鲜明的民族特色。基训中则借鉴结合了芭蕾的训练体系，融合戏曲武术的手眼身法步等技术、技巧，当中仍然有着独具一格的民族特性，(如孙颖教授开创的中国古典舞汉唐学派)表演时与其它舞种中的技术、风格有着很大的不同。');
INSERT INTO `dance_training` VALUES ('DANCETRAINING9812731054', 'Belly Dance', '肚皮舞', '/admin/uploads/training/20191116145959144.jpg', '肚皮舞是一种带有阿拉伯风情的舞蹈形式，起源于中东地区，并在中东和巴基斯坦、印度、伊朗等其他受阿拉伯文化影响的地区取得长足发展，19世纪末传入欧美地区，至今已遍布世界各地，成为一种较为知名的国际性舞蹈。\n肚皮舞是较为女性的舞蹈，其特色是舞者随着变化万千的快速节奏摆动臀部和腹部，舞姿优美，变化多端，而且多彰显阿拉伯风情，以神秘著称。近些年，肚皮舞也作为一种深受女士喜爱的减肥方式在世界各地广为流行。');

-- ----------------------------
-- Table structure for news
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `release_time` datetime(0) NULL DEFAULT NULL COMMENT '发布时间',
  `browse_volume` int(11) NULL DEFAULT NULL COMMENT '浏览量',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of news
-- ----------------------------
INSERT INTO `news` VALUES (1, '高考中考结束了！趁着暑假开始，好好练舞吧！', '/admin/uploads/news/2019111119324370.JPG', '2019-11-09 21:30:21', 50, '大学毕业以后，没人会经常问你毕业于哪所名校，曾经全校第几名，也没人问过你曾经拿过几次奖学金。别人在意的是，谈吐不俗的你，气质不凡的你，姿态优雅的你，有所特长的你，连谈恋爱的“小姐姐”们都喜欢被有才艺的“小哥哥”们撩，连自称有特长的你去华为面试时都需要现场展示才艺，连做一个小学财务都偏爱于有艺术特长的应聘者……另外，这年头没点才艺都不敢结婚了……随着人们的艺术欣赏水平不断升级，很多企业在用人时对审美水平也有了要求。艺术涵养已经在一个人的综合素质中显得越来越重要。 但是，很多孩子由于从小被禁锢在各种的公式定理中，逻辑思维能力强却缺乏足够的发散思维，而这恰恰又是艺术涵养的基础。关于思维，舞蹈有话说 ：舞蹈是一种容空间性、时间性和综合性的动态造型艺术。舞蹈是美的艺术，美在其中，乐在其中。舞蹈是情的艺术，情在其中，意在其中。每一支独立的舞蹈，都诉说着一个美丽的故事，那是生活智慧和思想层次的写照。');
INSERT INTO `news` VALUES (2, '我们为什么要跳舞？听听这13句最纯净的回答！', '/admin/uploads/news/20191116131137396.jpg', '2019-11-11 11:07:09', 50, '1、跳舞是表达激情和生命力的最好方式。2、舞蹈是一种肢体语言，它充满活力给人激情。3、舞蹈是一种美，而美是能够穿透人心的东西。4、舞蹈是最公平的，你必须靠自己的努力和付出。5、舞蹈真正跳得出色的，都是他人无法模仿的。6、你站在台上舞蹈，看舞蹈的人在台下看你。7、舞蹈的旅途没有完美，曲折亦是风景。8、舞蹈不是情景剧，只要努力，你也会拥有。9、舞蹈的真谛是，越跳越自信，越跳越优雅。10、旋转跳舞，可以让自己活得更精彩！11、舞蹈能够释放我们的能量，因为舞蹈时我们是快乐的。12、对于舞者来说，舞蹈是一种成长，一种收获，一种荣誉！');
INSERT INTO `news` VALUES (4, '学舞蹈会长不高？还八字脚？？', '/admin/uploads/news/20191116131616794.jpg', '2019-11-13 11:07:09', 50, '很多时候，父母想把孩子送去学舞蹈，也没多想让孩子成为优秀的舞蹈演员，只是想让孩子练练形体气质、培养一下兴趣，多一项才艺。可是，各种可靠或者不可靠的消息却让父母们有点犹豫，比如：只有体形好的孩子才能去跳舞？学舞蹈会影响孩子长个儿，还会变“八字脚”？学舞蹈对孩子的骨骼发育不好…… 不要慌，下面就来为大家解答这些疑问~Question1、练舞蹈会让孩子长不高，有这回事吗？不会的。长个子是骨骼的事，而舞蹈练得是身体的柔韧性，拉的是韧带，两不相关。软开度的练习是为了使动作舒展，完成更多较难动作所做的基础练习，目的在于使韧带放松，关节灵活，跟是否抑制个头完全没有关系。Question2、有些父母把孩子送去学跳舞，是希望他可以借此减减肥，能达到这个效果吗？一般来说，跳舞不能达到全身减肥效果，它能达到的效果是局部减肥，比如腰腹部。所以想通过跳舞让孩子完全变瘦是不太可能的。Question3、孩子会跳出“八字脚”吗？因为芭蕾基础训练要求“开、绷、直”，所以，跳舞可以对此进行矫正。但是跳舞练出“八字脚”的情况，几乎很少会出现。Question4、学舞蹈对孩子的骨骼发育有好处吗？要选择与孩子骨骼成熟度匹配的训练强度。比如芭蕾舞，父母在决定开始足尖训练之前应该确保他们的脚和脚踝已经发育到了足够的强度。关于孩子学舞蹈特别注意：大腿特别粗的孩子不适合通过跳舞来减肥。因为在进行基础训练，特别是芭蕾基础训练的时候，力量都在腿和脚上，这会让腿部变得非常结实，从某种程度上来说，两条腿看上去会显得更粗。这就是为什么芭蕾舞演员一定要求双腿很修长的原因，这可以保证腿在长了肌肉后，看上去仍然很修长，很美。如何避免意外伤害？对老师来说，除了必须用科学的教学方法外，还要考虑到孩子的承受能力，逐步推进，让孩子做他力所能及的动作，不要过早地让孩子学一些有难度的技巧性动作。如果需要让孩子学一些有难度的动作，一定要对孩子进行一对一的辅导。孩子学舞蹈父母要怎么做父母要用鼓励、引导、奖励的方式，让孩子坚持学下去。没兴趣学不好”是针对成年人而言的，成年人成就一项学业，没兴趣就不可能学好，但孩子可不是，不能拿成人的思路套孩子。版权声明本文来源于互联网，不代表本站立场。如有侵权，请联系站长删除。');
INSERT INTO `news` VALUES (5, '游戏手机都不值得买游戏手机都不值得买游戏手机都不值得买游戏手机都不值得买', '/static/img/1503968439182918.jpg', '2019-11-08 11:07:09', 50, '肚皮舞是较为女性的舞蹈，其特色是舞者随着变化万千的快速节奏摆动臀部和腹部，舞姿优美，变化多端，而且多彰显阿拉伯风情，以神秘著称。近些年，肚皮舞也作为一种深受女士喜爱的减肥方式在世界各地广为流行。');
INSERT INTO `news` VALUES (6, '游戏手机都不值得买游戏手机都不值得买游戏手机都不值得买游戏手机都不值得买', '/static/img/1503968439182918.jpg', '2019-11-21 11:07:09', 50, '<p style=\"text-align: center;\">肚皮舞是较为女性的舞蹈，其特色是舞者随着变化万千的快速节奏摆动臀部和腹部，舞姿优美，变化多端，而且多彰显阿拉伯风情，以神秘著称。近些年，肚皮舞也作为一种深受女士喜爱的减肥方式在世界各地广为流行。</p>');
INSERT INTO `news` VALUES (11, 'asdfasdfasdfasdf', '/admin/uploads/news/20191120143459909.jpg', '2019-11-20 14:35:08', 23, '<h1 style=\"text-align: center;\"><b>sda fsdaf&nbsp;</b></h1><table><colgroup><col width=\"16.65278934221482%\"><col width=\"16.65278934221482%\"><col width=\"16.65278934221482%\"><col width=\"16.65278934221482%\"><col width=\"16.65278934221482%\"><col width=\"16.736053288925895%\"></colgroup><thead><tr><th><br></th><th><br></th><th>asrfrsad</th><th>sdfsadf</th><th>sadf</th><th>sadf</th></tr></thead><tbody><tr><td><br></td><td><br></td><td>dsaf</td><td><br></td><td>sadf</td><td>sadf</td></tr><tr><td><br></td><td><br></td><td>dsaf</td><td>sdfsadfs</td><td>asdfasd</td><td>fasdf</td></tr><tr><td><br></td><td><br></td><td>asdf</td><td>sdf</td><td>adfsadf</td><td>asdfas</td></tr><tr><td><br></td><td><br></td><td><br></td><td>asdfasdf</td><td><br></td><td><br></td></tr></tbody></table>');

-- ----------------------------
-- Table structure for storefront
-- ----------------------------
DROP TABLE IF EXISTS `storefront`;
CREATE TABLE `storefront`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '店面ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '店面名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of storefront
-- ----------------------------
INSERT INTO `storefront` VALUES ('10132947561574263774261', '东郊店(金花路店)');

-- ----------------------------
-- Table structure for suggestion
-- ----------------------------
DROP TABLE IF EXISTS `suggestion`;
CREATE TABLE `suggestion`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of suggestion
-- ----------------------------
INSERT INTO `suggestion` VALUES (1, '杨彬', '18292167152', '奥术大师大所大');
INSERT INTO `suggestion` VALUES (2, 'administrator', '18292167150', '啊是哒哒哒');
INSERT INTO `suggestion` VALUES (3, 'aaa', '17600302409', '奥术大师多');
INSERT INTO `suggestion` VALUES (4, 'adminsein', '17600302409', '奥术大师大所');

-- ----------------------------
-- Table structure for table_cources
-- ----------------------------
DROP TABLE IF EXISTS `table_cources`;
CREATE TABLE `table_cources`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程名称',
  `star_class` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程星级',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '舞蹈类型',
  `effect` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '舞蹈作用，介绍',
  `start_time` time(0) NULL DEFAULT NULL COMMENT '课程开始时间',
  `end_time` time(0) NULL DEFAULT NULL COMMENT '结束时间',
  `week` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '星期ID',
  `storefront` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '店面ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `week`(`week`) USING BTREE,
  INDEX `table_ course_ibfk_2`(`storefront`) USING BTREE,
  CONSTRAINT `table_cources_ibfk_2` FOREIGN KEY (`storefront`) REFERENCES `storefront` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `table_cources_ibfk_3` FOREIGN KEY (`week`) REFERENCES `week` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of table_cources
-- ----------------------------
INSERT INTO `table_cources` VALUES ('10246853791574333684832', 'admin', '4', '成品舞', '悬链肌肉', '09:00:00', '13:00:00', '71046358971574247275310', '10132947561574263774261');
INSERT INTO `table_cources` VALUES ('10867214951574338274151', 'adminsa', '3', '成品舞', '悬链肌肉', '15:00:00', '16:00:00', '67341681051574247226155', '10132947561574263774261');
INSERT INTO `table_cources` VALUES ('10968142371574337048524', 'admin按', '3', '成品舞', '悬链肌肉', '14:00:00', '14:30:00', '11976352101574247112256', '10132947561574263774261');
INSERT INTO `table_cources` VALUES ('10986721351574335568211', 'admin', '4', '成品舞', '悬链肌肉', '10:00:00', '13:00:00', '51065329141574247214318', '10132947561574263774261');
INSERT INTO `table_cources` VALUES ('12683510741574335427306', 'admin', '3', '成品舞', '悬链肌肉', '11:00:00', '14:00:00', '39657101421574247168608', '10132947561574263774261');
INSERT INTO `table_cources` VALUES ('13104582761574338868269', 'admin', '4', '成品舞', '悬链肌肉', '13:00:00', '14:00:00', '51065329141574247214318', '10132947561574263774261');
INSERT INTO `table_cources` VALUES ('1623758491574342465428', '肚皮舞HJ', '3', '成品舞', '悬链肌肉', '13:00:00', '16:00:00', '71046358971574247275310', '10132947561574263774261');
INSERT INTO `table_cources` VALUES ('19726310841574333388582', 'admin', '4', '成品舞', '悬链肌肉', '17:00:00', '18:00:00', '21964327101574247282899', '10132947561574263774261');
INSERT INTO `table_cources` VALUES ('23954110871574329507005', '肚皮舞', '3', '成品舞', '悬链肌肉', '10:00:00', '12:00:00', '39657101421574247168608', '10132947561574263774261');
INSERT INTO `table_cources` VALUES ('25678911031574335949910', 'admin', '3', '成品舞', '悬链肌肉', '10:00:00', '12:00:00', '21964327101574247282899', '10132947561574263774261');
INSERT INTO `table_cources` VALUES ('25938104671574336305414', 'admin', '4', '成品舞', '悬链肌肉', '13:00:00', '16:00:00', '51065329141574247214318', '10132947561574263774261');
INSERT INTO `table_cources` VALUES ('27108915631574332258819', '肚皮舞', '3', '成品舞', '悬链肌肉', '09:00:00', '11:00:00', '67341681051574247226155', '10132947561574263774261');
INSERT INTO `table_cources` VALUES ('29311048751574336517696', 'admin', '4', '成品舞', '悬链肌肉', '13:00:00', '14:00:00', '71046358971574247275310', '10132947561574263774261');
INSERT INTO `table_cources` VALUES ('36184210951574333718680', 'admin', '3', '成品舞', '悬链肌肉', '16:00:00', '20:00:00', '39657101421574247168608', '10132947561574263774261');
INSERT INTO `table_cources` VALUES ('39108671251574335893249', 'admin', '3', '成品舞', '悬链肌肉', '13:00:00', '14:00:00', '39657101421574247168608', '10132947561574263774261');
INSERT INTO `table_cources` VALUES ('39410157861574333510560', 'admin', '4', '成品舞', '悬链肌肉', '11:00:00', '18:00:00', '51065329141574247214318', '10132947561574263774261');
INSERT INTO `table_cources` VALUES ('41037195261574332729168', 'adminxi', '2', '成品舞', '悬链肌肉', '14:00:00', '16:00:00', '11976352101574247112256', '10132947561574263774261');
INSERT INTO `table_cources` VALUES ('41759361081574333612834', 'admin', '4', '成品舞', '悬链肌肉', '15:00:00', '17:00:00', '67341681051574247226155', '10132947561574263774261');
INSERT INTO `table_cources` VALUES ('42975361081574336773594', 'admin', '4', '成品舞', '悬链肌肉', '11:00:00', '22:00:00', '47196108431574247199561', '10132947561574263774261');
INSERT INTO `table_cources` VALUES ('47815321091574336730098', 'admin', '1', '成品舞', '悬链肌肉', '10:00:00', '13:00:00', '47196108431574247199561', '10132947561574263774261');
INSERT INTO `table_cources` VALUES ('47910651231574336235317', 'admin', '3', '成品舞', '悬链肌肉', '10:00:00', '11:00:00', '39657101421574247168608', '10132947561574263774261');
INSERT INTO `table_cources` VALUES ('54862109371574334890756', 'admin', '3', '成品舞', '悬链肌肉', '10:00:00', '12:00:00', '71046358971574247275310', '10132947561574263774261');
INSERT INTO `table_cources` VALUES ('57860910931565797075245', '阿斯顿', '4', '水电费', '梵蒂冈', '13:18:26', '13:18:28', '39657101421574247168608', '10132947561574263774261');
INSERT INTO `table_cources` VALUES ('57860910931574297075245', 'asfda', '5', '打算', '发过火', '13:17:07', '13:17:09', '47196108431574247199561', '10132947561574263774261');
INSERT INTO `table_cources` VALUES ('57860910934574297075245', '阿斯顿法', '4', '发过火', '即可', '13:18:00', '13:18:02', '51065329141574247214318', '10132947561574263774261');
INSERT INTO `table_cources` VALUES ('57861210931574297075245', '啊发顺丰', '2', '按时', '国防生的', '13:15:12', '13:15:14', '11976352101574247112256', '10132947561574263774261');
INSERT INTO `table_cources` VALUES ('57861210931574297075286', '奥术大师', '1', '阿打算', '阿女方', '10:34:05', '10:34:07', '21964327101574247282899', '10132947561574263774261');
INSERT INTO `table_cources` VALUES ('57861210931574297075287', '相持不下', '1', '水电费', '胜多负少', '04:00:00', '13:00:00', '21964327101574247282899', '10132947561574263774261');
INSERT INTO `table_cources` VALUES ('57861210931574297075845', '阿斯顿发', '5', '阿达', '似懂非懂', '13:16:05', '13:16:08', '71046358971574247275310', '10132947561574263774261');
INSERT INTO `table_cources` VALUES ('57861210931574297089245', '阿斯VB', '5', '规划局', '4儿童', '13:16:39', '13:16:41', '67341681051574247226155', '10132947561574263774261');
INSERT INTO `table_cources` VALUES ('5826749311574316771996', '肚皮舞', '4', '成品舞', '悬链肌肉', '12:30:00', '14:00:00', '11976352101574247112256', '10132947561574263774261');
INSERT INTO `table_cources` VALUES ('5826914371574333944985', 'admin', '1', '成品舞', '悬链肌肉', '12:00:00', '13:00:00', '67341681051574247226155', '10132947561574263774261');
INSERT INTO `table_cources` VALUES ('61104982751574316960765', '肚皮舞', '5', '成品舞', '悬链肌肉', '10:00:00', '11:00:00', '47196108431574247199561', '10132947561574263774261');
INSERT INTO `table_cources` VALUES ('61472810531574338310833', 'admin', '4', '成品舞', '悬链肌肉', '16:00:00', '17:00:00', '67341681051574247226155', '10132947561574263774261');
INSERT INTO `table_cources` VALUES ('61537984101574338767801', 'adminv', '4', '成品舞', '悬链肌肉', '16:00:00', '17:00:00', '51065329141574247214318', '10132947561574263774261');
INSERT INTO `table_cources` VALUES ('62531041981574338916459', 'admin', '1', '成品舞', '悬链肌肉', '16:00:00', '17:00:00', '51065329141574247214318', '10132947561574263774261');
INSERT INTO `table_cources` VALUES ('65210793841574337457349', 'admin', '4', '成品舞', '悬链肌肉', '15:00:00', '21:00:00', '11976352101574247112256', '10132947561574263774261');
INSERT INTO `table_cources` VALUES ('69210485731574340027598', '肚皮舞', '3', '成品舞', '悬链肌肉', '13:00:00', '14:00:00', '39657101421574247168608', '10132947561574263774261');
INSERT INTO `table_cources` VALUES ('71362108451574335990140', 'admin', '3', '成品舞', '悬链肌肉', '10:00:00', '14:00:00', '67341681051574247226155', '10132947561574263774261');
INSERT INTO `table_cources` VALUES ('76510891341574335302028', 'admin', '3', '成品舞', '悬链肌肉', '12:00:00', '13:00:00', '21964327101574247282899', '10132947561574263774261');
INSERT INTO `table_cources` VALUES ('79103281641574338991264', 'admin', '3', '成品舞', '悬链肌肉', '19:00:00', '20:00:00', '51065329141574247214318', '10132947561574263774261');
INSERT INTO `table_cources` VALUES ('81279653101574333907422', 'admin', '3', '成品舞', '悬链肌肉', '12:00:00', '15:00:00', '21964327101574247282899', '10132947561574263774261');
INSERT INTO `table_cources` VALUES ('82159103461574337407802', 'admin', '3', '成品舞', '悬链肌肉', '15:00:00', '16:00:00', '11976352101574247112256', '10132947561574263774261');
INSERT INTO `table_cources` VALUES ('83126457101574332063141', '肚皮舞', '3', '成品舞', '悬链肌肉', '11:00:00', '14:00:00', '47196108431574247199561', '10132947561574263774261');
INSERT INTO `table_cources` VALUES ('86135109721574336829073', '肚皮舞s', '3', '成品舞', '悬链肌肉', '10:00:00', '20:00:00', '71046358971574247275310', '10132947561574263774261');
INSERT INTO `table_cources` VALUES ('91042176831574333832404', 'admin', '4', '成品舞', '悬链肌肉', '16:00:00', '17:00:00', '47196108431574247199561', '10132947561574263774261');
INSERT INTO `table_cources` VALUES ('91673104821574332568010', '肚皮舞', '3', '成品舞', '悬链肌肉', '13:00:00', '14:00:00', '71046358971574247275310', '10132947561574263774261');
INSERT INTO `table_cources` VALUES ('93548210161574316807981', '肚皮舞', '5', '成品舞', '悬链肌肉', '14:00:00', '15:00:00', '39657101421574247168608', '10132947561574263774261');
INSERT INTO `table_cources` VALUES ('95724106381574337518295', 'admin', '4', '成品舞', '悬链肌肉', '13:00:00', '16:00:00', '21964327101574247282899', '10132947561574263774261');
INSERT INTO `table_cources` VALUES ('98141073651574335515121', 'admin', '3', '成品舞', '悬链肌肉', '12:00:00', '13:00:00', '47196108431574247199561', '10132947561574263774261');

-- ----------------------------
-- Table structure for video
-- ----------------------------
DROP TABLE IF EXISTS `video`;
CREATE TABLE `video`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `video_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `local` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of video
-- ----------------------------
INSERT INTO `video` VALUES (1, 'http://cdn.gv-photo.com/uploads/2018/09/231700569664.gif', 'http://aa.jinyanwudao.com/czy.mp4', '蔡泽元小会员携手教练同台演绎爵士风采');
INSERT INTO `video` VALUES (2, 'http://cdn.gv-photo.com/uploads/2018/09/231700569664.gif', 'http://aa.jinyanwudao.com/shaoerlading.mp4', '学员花朵-少儿拉丁舞 MP4');
INSERT INTO `video` VALUES (4, 'http://cdn.gv-photo.com/uploads/2018/09/231700569664.gif', 'http://aa.jinyanwudao.com/lddp.mp4', '金彦兰岛肚皮舞课程展示');

-- ----------------------------
-- Table structure for week
-- ----------------------------
DROP TABLE IF EXISTS `week`;
CREATE TABLE `week`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '星期ID',
  `describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `english` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '英文',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of week
-- ----------------------------
INSERT INTO `week` VALUES ('11976352101574247112256', '星期一', 'monday');
INSERT INTO `week` VALUES ('21964327101574247282899', '星期二', 'tuesday');
INSERT INTO `week` VALUES ('39657101421574247168608', '星期三', 'wednesday');
INSERT INTO `week` VALUES ('47196108431574247199561', '星期四', 'thursday');
INSERT INTO `week` VALUES ('51065329141574247214318', '星期五', 'friday');
INSERT INTO `week` VALUES ('67341681051574247226155', '星期六', 'saturday');
INSERT INTO `week` VALUES ('71046358971574247275310', '星期天', 'sunday');

SET FOREIGN_KEY_CHECKS = 1;
