/*
Navicat SQL Server Data Transfer

Source Server         : 本地SqlServer2014
Source Server Version : 120000
Source Host           : DESKTOP-53RP8P2:1433
Source Database       : class_web
Source Schema         : dbo

Target Server Type    : SQL Server
Target Server Version : 120000
File Encoding         : 65001

Date: 2017-11-08 21:31:33
*/


-- ----------------------------
-- Table structure for Album
-- ----------------------------
DROP TABLE [dbo].[Album]
GO
CREATE TABLE [dbo].[Album] (
[Album_id] int NOT NULL IDENTITY(1,1) ,
[User_id] nchar(12) NOT NULL ,
[Album_name] nchar(20) NOT NULL ,
[Album_time] datetime NOT NULL ,
[Album_description] nvarchar(50) NULL ,
[photo_num] int NOT NULL 
)


GO
DBCC CHECKIDENT(N'[dbo].[Album]', RESEED, 23)
GO

-- ----------------------------
-- Records of Album
-- ----------------------------
SET IDENTITY_INSERT [dbo].[Album] ON
GO
INSERT INTO [dbo].[Album] ([Album_id], [User_id], [Album_name], [Album_time], [Album_description], [photo_num]) VALUES (N'3', N'wr          ', N'班级相册                ', N'2016-03-03 00:00:00.000', N'默认的班级相册', N'72')
GO
GO
INSERT INTO [dbo].[Album] ([Album_id], [User_id], [Album_name], [Album_time], [Album_description], [photo_num]) VALUES (N'5', N'wr          ', N'高数答案                ', N'2016-04-04 00:00:00.000', N'答案共享', N'72')
GO
GO
INSERT INTO [dbo].[Album] ([Album_id], [User_id], [Album_name], [Album_time], [Album_description], [photo_num]) VALUES (N'6', N'xjw         ', N'五公里酷炫跑活动            ', N'2016-04-04 00:00:00.000', N'周末活动', N'72')
GO
GO
INSERT INTO [dbo].[Album] ([Album_id], [User_id], [Album_name], [Album_time], [Album_description], [photo_num]) VALUES (N'7', N'wyn         ', N'团会                  ', N'2017-02-26 19:21:21.167', N'展现班级风采.......  ', N'72')
GO
GO
INSERT INTO [dbo].[Album] ([Album_id], [User_id], [Album_name], [Album_time], [Album_description], [photo_num]) VALUES (N'8', N'wyn         ', N'军训                  ', N'2017-03-05 14:22:26.490', N'累并快乐着', N'72')
GO
GO
INSERT INTO [dbo].[Album] ([Album_id], [User_id], [Album_name], [Album_time], [Album_description], [photo_num]) VALUES (N'12', N'14201101    ', N'女生自拍                ', N'2016-01-01 00:00:00.000', N'感觉自己萌萌哒', N'72')
GO
GO
INSERT INTO [dbo].[Album] ([Album_id], [User_id], [Album_name], [Album_time], [Album_description], [photo_num]) VALUES (N'14', N'14201108    ', N'班级活动                ', N'2016-01-01 00:00:00.000', N'666', N'72')
GO
GO
INSERT INTO [dbo].[Album] ([Album_id], [User_id], [Album_name], [Album_time], [Album_description], [photo_num]) VALUES (N'23', N'xjw         ', N'测试相册                ', N'2017-10-17 10:29:52.557', N'我的相册', N'3')
GO
GO
SET IDENTITY_INSERT [dbo].[Album] OFF
GO

-- ----------------------------
-- Table structure for Announce
-- ----------------------------
DROP TABLE [dbo].[Announce]
GO
CREATE TABLE [dbo].[Announce] (
[Announce_id] int NOT NULL IDENTITY(1,1) ,
[User_id] nchar(12) NOT NULL ,
[content] nvarchar(50) NOT NULL ,
[theme] nchar(20) NOT NULL ,
[Announce_time] datetime NOT NULL 
)


GO
DBCC CHECKIDENT(N'[dbo].[Announce]', RESEED, 30)
GO

-- ----------------------------
-- Records of Announce
-- ----------------------------
SET IDENTITY_INSERT [dbo].[Announce] ON
GO
INSERT INTO [dbo].[Announce] ([Announce_id], [User_id], [content], [theme], [Announce_time]) VALUES (N'19', N'xjw         ', N'明天所有班级成员要到D304做课设，不得迟到', N'课设                  ', N'2017-03-11 17:33:20.907')
GO
GO
INSERT INTO [dbo].[Announce] ([Announce_id], [User_id], [content], [theme], [Announce_time]) VALUES (N'21', N'xjw         ', N'请各位算好自己的综合素测分，这两天发给学委', N'综合素测分登记             ', N'2017-03-11 17:38:04.733')
GO
GO
INSERT INTO [dbo].[Announce] ([Announce_id], [User_id], [content], [theme], [Announce_time]) VALUES (N'22', N'xjw         ', N'·明天上午上课请各位同学把学生证带去，明天收取学生证进行本学期的注册工作！', N'学生证注册               ', N'2017-03-11 18:36:56.657')
GO
GO
INSERT INTO [dbo].[Announce] ([Announce_id], [User_id], [content], [theme], [Announce_time]) VALUES (N'28', N'xjw         ', N'dadas', N'da                  ', N'2017-03-14 19:09:50.167')
GO
GO
INSERT INTO [dbo].[Announce] ([Announce_id], [User_id], [content], [theme], [Announce_time]) VALUES (N'29', N'xjw         ', N'明天上午上课请各位同学把学生证带去，明天收取学生证进行本学期的注册工作！ ', N'学生证注册               ', N'2017-03-17 17:43:17.657')
GO
GO
INSERT INTO [dbo].[Announce] ([Announce_id], [User_id], [content], [theme], [Announce_time]) VALUES (N'30', N'xjw         ', N'班级公告', N'测试                  ', N'2017-10-17 10:27:38.540')
GO
GO
SET IDENTITY_INSERT [dbo].[Announce] OFF
GO

-- ----------------------------
-- Table structure for Dynamic
-- ----------------------------
DROP TABLE [dbo].[Dynamic]
GO
CREATE TABLE [dbo].[Dynamic] (
[Dynamic_id] int NOT NULL IDENTITY(1,1) ,
[User_id] nchar(12) NOT NULL ,
[Album_id] int NOT NULL ,
[Dynamic_time] datetime NOT NULL ,
[Dynamic_content] nvarchar(50) NOT NULL ,
[page_view] int NOT NULL ,
[like] int NOT NULL 
)


GO
DBCC CHECKIDENT(N'[dbo].[Dynamic]', RESEED, 46)
GO

-- ----------------------------
-- Records of Dynamic
-- ----------------------------
SET IDENTITY_INSERT [dbo].[Dynamic] ON
GO
INSERT INTO [dbo].[Dynamic] ([Dynamic_id], [User_id], [Album_id], [Dynamic_time], [Dynamic_content], [page_view], [like]) VALUES (N'39', N'xjw         ', N'3', N'2017-03-13 19:20:25.987', N'欢迎来到班级主页', N'0', N'5')
GO
GO
INSERT INTO [dbo].[Dynamic] ([Dynamic_id], [User_id], [Album_id], [Dynamic_time], [Dynamic_content], [page_view], [like]) VALUES (N'40', N'14201126    ', N'6', N'2017-03-13 19:41:01.353', N'只有长得帅的才可以点赞', N'0', N'4')
GO
GO
INSERT INTO [dbo].[Dynamic] ([Dynamic_id], [User_id], [Album_id], [Dynamic_time], [Dynamic_content], [page_view], [like]) VALUES (N'41', N'14201111    ', N'3', N'2017-03-13 20:13:03.697', N'玩的很开心', N'0', N'1')
GO
GO
INSERT INTO [dbo].[Dynamic] ([Dynamic_id], [User_id], [Album_id], [Dynamic_time], [Dynamic_content], [page_view], [like]) VALUES (N'42', N'14201120    ', N'14', N'2017-03-13 20:14:03.277', N'个人风采', N'0', N'3')
GO
GO
INSERT INTO [dbo].[Dynamic] ([Dynamic_id], [User_id], [Album_id], [Dynamic_time], [Dynamic_content], [page_view], [like]) VALUES (N'44', N'14201120    ', N'3', N'2017-03-14 19:06:28.597', N'班级课设', N'0', N'0')
GO
GO
INSERT INTO [dbo].[Dynamic] ([Dynamic_id], [User_id], [Album_id], [Dynamic_time], [Dynamic_content], [page_view], [like]) VALUES (N'45', N'xjw         ', N'3', N'2017-10-17 10:25:16.747', N'测试', N'0', N'1')
GO
GO
INSERT INTO [dbo].[Dynamic] ([Dynamic_id], [User_id], [Album_id], [Dynamic_time], [Dynamic_content], [page_view], [like]) VALUES (N'46', N'xjw         ', N'23', N'2017-10-17 10:31:04.613', N'今天很开心', N'0', N'0')
GO
GO
SET IDENTITY_INSERT [dbo].[Dynamic] OFF
GO

-- ----------------------------
-- Table structure for Message
-- ----------------------------
DROP TABLE [dbo].[Message]
GO
CREATE TABLE [dbo].[Message] (
[Message_id] int NOT NULL IDENTITY(1,1) ,
[receive_id] nchar(12) NOT NULL ,
[send_id] nchar(12) NOT NULL ,
[Dynamic_id] int NULL ,
[Message_time] datetime NOT NULL ,
[Message_content] nvarchar(50) NOT NULL ,
[Message_type] nchar(20) NOT NULL 
)


GO
DBCC CHECKIDENT(N'[dbo].[Message]', RESEED, 76)
GO

-- ----------------------------
-- Records of Message
-- ----------------------------
SET IDENTITY_INSERT [dbo].[Message] ON
GO
INSERT INTO [dbo].[Message] ([Message_id], [receive_id], [send_id], [Dynamic_id], [Message_time], [Message_content], [Message_type]) VALUES (N'3', N'wyn         ', N'xjw         ', null, N'2017-02-03 00:00:00.000', N'很强', N'USER                ')
GO
GO
INSERT INTO [dbo].[Message] ([Message_id], [receive_id], [send_id], [Dynamic_id], [Message_time], [Message_content], [Message_type]) VALUES (N'10', N'xjw         ', N'wyn         ', null, N'2017-03-05 16:21:26.140', N'有趣', N'USER                ')
GO
GO
INSERT INTO [dbo].[Message] ([Message_id], [receive_id], [send_id], [Dynamic_id], [Message_time], [Message_content], [Message_type]) VALUES (N'11', N'xjw         ', N'wyn         ', null, N'2017-03-09 14:57:17.227', N'翻皮皮水', N'USER                ')
GO
GO
INSERT INTO [dbo].[Message] ([Message_id], [receive_id], [send_id], [Dynamic_id], [Message_time], [Message_content], [Message_type]) VALUES (N'56', N'xjw         ', N'SYSTEM      ', null, N'2017-03-12 11:13:54.637', N'您的密码已重置为123456,请尽快到个人中心修改密码！', N'SYSTEM              ')
GO
GO
INSERT INTO [dbo].[Message] ([Message_id], [receive_id], [send_id], [Dynamic_id], [Message_time], [Message_content], [Message_type]) VALUES (N'60', N'14201126    ', N'xjw         ', N'40', N'2017-03-13 20:23:39.587', N'很帅', N'USER                ')
GO
GO
INSERT INTO [dbo].[Message] ([Message_id], [receive_id], [send_id], [Dynamic_id], [Message_time], [Message_content], [Message_type]) VALUES (N'71', N'xjw         ', N'14201120    ', N'39', N'2017-03-14 19:07:11.523', N'晚上好', N'USER                ')
GO
GO
INSERT INTO [dbo].[Message] ([Message_id], [receive_id], [send_id], [Dynamic_id], [Message_time], [Message_content], [Message_type]) VALUES (N'73', N'14201120    ', N'xjw         ', N'42', N'2017-03-17 19:06:09.987', N'拍的不错', N'USER                ')
GO
GO
INSERT INTO [dbo].[Message] ([Message_id], [receive_id], [send_id], [Dynamic_id], [Message_time], [Message_content], [Message_type]) VALUES (N'75', N'14201120    ', N'xjw         ', N'42', N'2017-10-17 10:21:37.690', N'wdasd', N'USER                ')
GO
GO
INSERT INTO [dbo].[Message] ([Message_id], [receive_id], [send_id], [Dynamic_id], [Message_time], [Message_content], [Message_type]) VALUES (N'76', N'xjw         ', N'xjw         ', N'45', N'2017-10-17 10:25:42.490', N'很好玩', N'USER                ')
GO
GO
SET IDENTITY_INSERT [dbo].[Message] OFF
GO

-- ----------------------------
-- Table structure for Photo
-- ----------------------------
DROP TABLE [dbo].[Photo]
GO
CREATE TABLE [dbo].[Photo] (
[Photo_id] int NOT NULL IDENTITY(1,1) ,
[Dynamic_id] int NOT NULL ,
[User_id] nchar(12) NOT NULL ,
[Album_id] int NOT NULL ,
[Photo_time] datetime NOT NULL ,
[save] nvarchar(50) NOT NULL 
)


GO
DBCC CHECKIDENT(N'[dbo].[Photo]', RESEED, 89)
GO

-- ----------------------------
-- Records of Photo
-- ----------------------------
SET IDENTITY_INSERT [dbo].[Photo] ON
GO
INSERT INTO [dbo].[Photo] ([Photo_id], [Dynamic_id], [User_id], [Album_id], [Photo_time], [save]) VALUES (N'64', N'40', N'14201126    ', N'6', N'2017-03-13 19:41:01.390', N'upload/6/QQ鍥剧墖20170313181016.jpg')
GO
GO
INSERT INTO [dbo].[Photo] ([Photo_id], [Dynamic_id], [User_id], [Album_id], [Photo_time], [save]) VALUES (N'65', N'41', N'14201111    ', N'3', N'2017-03-13 20:13:03.783', N'upload/3/QQ鍥剧墖20170313180414.jpg')
GO
GO
INSERT INTO [dbo].[Photo] ([Photo_id], [Dynamic_id], [User_id], [Album_id], [Photo_time], [save]) VALUES (N'66', N'41', N'14201111    ', N'3', N'2017-03-13 20:13:04.027', N'upload/3/QQ鍥剧墖20170313180419.jpg')
GO
GO
INSERT INTO [dbo].[Photo] ([Photo_id], [Dynamic_id], [User_id], [Album_id], [Photo_time], [save]) VALUES (N'67', N'41', N'14201111    ', N'3', N'2017-03-13 20:13:04.047', N'upload/3/QQ鍥剧墖20170313180428.jpg')
GO
GO
INSERT INTO [dbo].[Photo] ([Photo_id], [Dynamic_id], [User_id], [Album_id], [Photo_time], [save]) VALUES (N'68', N'42', N'14201120    ', N'14', N'2017-03-13 20:14:03.313', N'upload/14/QQ鍥剧墖20170313180359.jpg')
GO
GO
INSERT INTO [dbo].[Photo] ([Photo_id], [Dynamic_id], [User_id], [Album_id], [Photo_time], [save]) VALUES (N'69', N'42', N'14201120    ', N'14', N'2017-03-13 20:14:03.607', N'upload/14/QQ鍥剧墖20170313180705.jpg')
GO
GO
INSERT INTO [dbo].[Photo] ([Photo_id], [Dynamic_id], [User_id], [Album_id], [Photo_time], [save]) VALUES (N'70', N'0', N'xjw         ', N'8', N'2017-03-13 20:23:00.637', N'upload/8/QQ鍥剧墖20170313180806.jpg')
GO
GO
INSERT INTO [dbo].[Photo] ([Photo_id], [Dynamic_id], [User_id], [Album_id], [Photo_time], [save]) VALUES (N'71', N'0', N'xjw         ', N'8', N'2017-03-13 20:23:05.397', N'upload/8/QQ鍥剧墖20170313180814.jpg')
GO
GO
INSERT INTO [dbo].[Photo] ([Photo_id], [Dynamic_id], [User_id], [Album_id], [Photo_time], [save]) VALUES (N'72', N'0', N'xjw         ', N'8', N'2017-03-13 20:23:12.497', N'upload/8/QQ鍥剧墖20170313180828.jpg')
GO
GO
INSERT INTO [dbo].[Photo] ([Photo_id], [Dynamic_id], [User_id], [Album_id], [Photo_time], [save]) VALUES (N'76', N'0', N'xjw         ', N'3', N'2017-03-14 13:04:53.047', N'upload/3/6.jpg')
GO
GO
INSERT INTO [dbo].[Photo] ([Photo_id], [Dynamic_id], [User_id], [Album_id], [Photo_time], [save]) VALUES (N'79', N'44', N'14201120    ', N'3', N'2017-03-14 19:06:28.967', N'upload/3/QQ鍥剧墖20170313180512.jpg')
GO
GO
INSERT INTO [dbo].[Photo] ([Photo_id], [Dynamic_id], [User_id], [Album_id], [Photo_time], [save]) VALUES (N'80', N'44', N'14201120    ', N'3', N'2017-03-14 19:06:28.990', N'upload/3/QQ鍥剧墖20170313180632.jpg')
GO
GO
INSERT INTO [dbo].[Photo] ([Photo_id], [Dynamic_id], [User_id], [Album_id], [Photo_time], [save]) VALUES (N'81', N'44', N'14201120    ', N'3', N'2017-03-14 19:06:29.033', N'upload/3/QQ鍥剧墖20170313180639.jpg')
GO
GO
INSERT INTO [dbo].[Photo] ([Photo_id], [Dynamic_id], [User_id], [Album_id], [Photo_time], [save]) VALUES (N'84', N'45', N'xjw         ', N'3', N'2017-10-17 10:25:16.853', N'upload/3/EmptySaleTypeResult.jpg')
GO
GO
INSERT INTO [dbo].[Photo] ([Photo_id], [Dynamic_id], [User_id], [Album_id], [Photo_time], [save]) VALUES (N'87', N'0', N'xjw         ', N'23', N'2017-10-17 10:30:13.083', N'upload/23/318562-106.jpg')
GO
GO
INSERT INTO [dbo].[Photo] ([Photo_id], [Dynamic_id], [User_id], [Album_id], [Photo_time], [save]) VALUES (N'88', N'0', N'xjw         ', N'23', N'2017-10-17 10:30:23.700', N'upload/23/350902-106.jpg')
GO
GO
INSERT INTO [dbo].[Photo] ([Photo_id], [Dynamic_id], [User_id], [Album_id], [Photo_time], [save]) VALUES (N'89', N'46', N'xjw         ', N'23', N'2017-10-17 10:31:04.750', N'upload/23/dc359a308d095ecd52f31f3e6360ee57.jpg')
GO
GO
SET IDENTITY_INSERT [dbo].[Photo] OFF
GO

-- ----------------------------
-- Table structure for Thumb_Up
-- ----------------------------
DROP TABLE [dbo].[Thumb_Up]
GO
CREATE TABLE [dbo].[Thumb_Up] (
[Dynamic_id] int NOT NULL ,
[User_id] nchar(12) NOT NULL 
)


GO

-- ----------------------------
-- Records of Thumb_Up
-- ----------------------------
INSERT INTO [dbo].[Thumb_Up] ([Dynamic_id], [User_id]) VALUES (N'3', N'14201103    ')
GO
GO
INSERT INTO [dbo].[Thumb_Up] ([Dynamic_id], [User_id]) VALUES (N'3', N'xjw         ')
GO
GO
INSERT INTO [dbo].[Thumb_Up] ([Dynamic_id], [User_id]) VALUES (N'6', N'xjw         ')
GO
GO
INSERT INTO [dbo].[Thumb_Up] ([Dynamic_id], [User_id]) VALUES (N'39', N'14201103    ')
GO
GO
INSERT INTO [dbo].[Thumb_Up] ([Dynamic_id], [User_id]) VALUES (N'39', N'14201111    ')
GO
GO
INSERT INTO [dbo].[Thumb_Up] ([Dynamic_id], [User_id]) VALUES (N'39', N'14201120    ')
GO
GO
INSERT INTO [dbo].[Thumb_Up] ([Dynamic_id], [User_id]) VALUES (N'39', N'14201126    ')
GO
GO
INSERT INTO [dbo].[Thumb_Up] ([Dynamic_id], [User_id]) VALUES (N'39', N'xjw         ')
GO
GO
INSERT INTO [dbo].[Thumb_Up] ([Dynamic_id], [User_id]) VALUES (N'40', N'14201103    ')
GO
GO
INSERT INTO [dbo].[Thumb_Up] ([Dynamic_id], [User_id]) VALUES (N'40', N'14201120    ')
GO
GO
INSERT INTO [dbo].[Thumb_Up] ([Dynamic_id], [User_id]) VALUES (N'40', N'14201126    ')
GO
GO
INSERT INTO [dbo].[Thumb_Up] ([Dynamic_id], [User_id]) VALUES (N'40', N'xjw         ')
GO
GO
INSERT INTO [dbo].[Thumb_Up] ([Dynamic_id], [User_id]) VALUES (N'41', N'xjw         ')
GO
GO
INSERT INTO [dbo].[Thumb_Up] ([Dynamic_id], [User_id]) VALUES (N'42', N'14201120    ')
GO
GO
INSERT INTO [dbo].[Thumb_Up] ([Dynamic_id], [User_id]) VALUES (N'42', N'Nchu142011  ')
GO
GO
INSERT INTO [dbo].[Thumb_Up] ([Dynamic_id], [User_id]) VALUES (N'42', N'xjw         ')
GO
GO
INSERT INTO [dbo].[Thumb_Up] ([Dynamic_id], [User_id]) VALUES (N'45', N'xjw         ')
GO
GO

-- ----------------------------
-- Table structure for User
-- ----------------------------
DROP TABLE [dbo].[User]
GO
CREATE TABLE [dbo].[User] (
[User_id] nchar(12) NOT NULL ,
[psd] nchar(10) NOT NULL ,
[nickname] nchar(10) NOT NULL ,
[telephone] nchar(12) NOT NULL ,
[signature] nvarchar(50) NOT NULL ,
[sex] nchar(5) NOT NULL ,
[head_portiait] nvarchar(50) NOT NULL ,
[level] bit NOT NULL ,
[check] bit NOT NULL ,
[login_time] datetime NOT NULL ,
[login_num] int NOT NULL ,
[online] bit NOT NULL 
)


GO

-- ----------------------------
-- Records of User
-- ----------------------------
INSERT INTO [dbo].[User] ([User_id], [psd], [nickname], [telephone], [signature], [sex], [head_portiait], [level], [check], [login_time], [login_num], [online]) VALUES (N'12201105    ', N'123456    ', N'张浩宇       ', N'182079210712', N'班级成员', N'男    ', N'images/Default/DefaultUserImg.jpg
', N'0', N'1', N'2016-01-01 00:00:00.000', N'1', N'0')
GO
GO
INSERT INTO [dbo].[User] ([User_id], [psd], [nickname], [telephone], [signature], [sex], [head_portiait], [level], [check], [login_time], [login_num], [online]) VALUES (N'14201101    ', N'123456    ', N'王嫣兰       ', N'15572423444 ', N'我是团支书', N'女    ', N'images/Default/DefaultUserImg.jpg
', N'0', N'1', N'2016-01-01 00:00:00.000', N'1', N'0')
GO
GO
INSERT INTO [dbo].[User] ([User_id], [psd], [nickname], [telephone], [signature], [sex], [head_portiait], [level], [check], [login_time], [login_num], [online]) VALUES (N'14201103    ', N'123456    ', N'李嘉丽       ', N'15723432244 ', N'我是学委', N'女    ', N'images/Default/DefaultUserImg.jpg
', N'0', N'1', N'2017-03-13 20:07:22.100', N'2', N'0')
GO
GO
INSERT INTO [dbo].[User] ([User_id], [psd], [nickname], [telephone], [signature], [sex], [head_portiait], [level], [check], [login_time], [login_num], [online]) VALUES (N'14201104    ', N'123456    ', N'廖美欢       ', N'13223132334 ', N'我是班级成员呢', N'女    ', N'images/Default/DefaultUserImg.jpg
', N'0', N'1', N'2016-01-01 00:00:00.000', N'1', N'0')
GO
GO
INSERT INTO [dbo].[User] ([User_id], [psd], [nickname], [telephone], [signature], [sex], [head_portiait], [level], [check], [login_time], [login_num], [online]) VALUES (N'14201105    ', N'123456    ', N'卢超素       ', N'187344234423', N'我是班级成员', N'女    ', N'images/Default/DefaultUserImg.jpg
', N'0', N'1', N'2016-01-01 00:00:00.000', N'1', N'0')
GO
GO
INSERT INTO [dbo].[User] ([User_id], [psd], [nickname], [telephone], [signature], [sex], [head_portiait], [level], [check], [login_time], [login_num], [online]) VALUES (N'14201106    ', N'123456    ', N'张琦        ', N'145291082911', N'我是15级教官队成员之一', N'男    ', N'images/Default/DefaultUserImg.jpg
', N'0', N'1', N'2016-01-01 00:00:00.000', N'1', N'0')
GO
GO
INSERT INTO [dbo].[User] ([User_id], [psd], [nickname], [telephone], [signature], [sex], [head_portiait], [level], [check], [login_time], [login_num], [online]) VALUES (N'14201107    ', N'123456    ', N'何昶源       ', N'132312323113', N'我是班级成员', N'男    ', N'images/Default/DefaultUserImg.jpg
', N'0', N'1', N'2016-01-01 00:00:00.000', N'1', N'0')
GO
GO
INSERT INTO [dbo].[User] ([User_id], [psd], [nickname], [telephone], [signature], [sex], [head_portiait], [level], [check], [login_time], [login_num], [online]) VALUES (N'14201108    ', N'123456    ', N'王启宁       ', N'132123131318', N'我是科技部部长', N'男    ', N'images/Default/DefaultUserImg.jpg
', N'0', N'1', N'2016-01-01 00:00:00.000', N'1', N'0')
GO
GO
INSERT INTO [dbo].[User] ([User_id], [psd], [nickname], [telephone], [signature], [sex], [head_portiait], [level], [check], [login_time], [login_num], [online]) VALUES (N'14201109    ', N'123456    ', N'任孟凯       ', N'132233212155', N'我是大长腿', N'男    ', N'images/Default/DefaultUserImg.jpg
', N'0', N'1', N'2016-01-01 00:00:00.000', N'1', N'0')
GO
GO
INSERT INTO [dbo].[User] ([User_id], [psd], [nickname], [telephone], [signature], [sex], [head_portiait], [level], [check], [login_time], [login_num], [online]) VALUES (N'14201110    ', N'123456    ', N'孙明峰       ', N'132341435145', N'我是班级成员之一', N'男    ', N'images/Default/DefaultUserImg.jpg
', N'0', N'1', N'2016-01-01 00:00:00.000', N'1', N'0')
GO
GO
INSERT INTO [dbo].[User] ([User_id], [psd], [nickname], [telephone], [signature], [sex], [head_portiait], [level], [check], [login_time], [login_num], [online]) VALUES (N'14201111    ', N'123456    ', N'崔平平       ', N'189879271123', N'我是班级成员', N'男    ', N'images/Default/DefaultUserImg.jpg
', N'0', N'1', N'2017-03-13 20:12:04.413', N'2', N'0')
GO
GO
INSERT INTO [dbo].[User] ([User_id], [psd], [nickname], [telephone], [signature], [sex], [head_portiait], [level], [check], [login_time], [login_num], [online]) VALUES (N'14201112    ', N'123456    ', N'高晨阳       ', N'102091821920', N'我是高手', N'男    ', N'upload/User/20170314180239.jpg', N'0', N'1', N'2017-03-14 17:59:23.107', N'2', N'0')
GO
GO
INSERT INTO [dbo].[User] ([User_id], [psd], [nickname], [telephone], [signature], [sex], [head_portiait], [level], [check], [login_time], [login_num], [online]) VALUES (N'14201113    ', N'123456    ', N'胡烨        ', N'187291773372', N'我实验做的好', N'男    ', N'images/Default/DefaultUserImg.jpg
', N'0', N'1', N'2016-01-01 00:00:00.000', N'1', N'0')
GO
GO
INSERT INTO [dbo].[User] ([User_id], [psd], [nickname], [telephone], [signature], [sex], [head_portiait], [level], [check], [login_time], [login_num], [online]) VALUES (N'14201114    ', N'123456    ', N'黄帅英       ', N'152672871891', N'爸爸最帅', N'男    ', N'images/Default/DefaultUserImg.jpg
', N'0', N'1', N'2016-01-01 00:00:00.000', N'1', N'0')
GO
GO
INSERT INTO [dbo].[User] ([User_id], [psd], [nickname], [telephone], [signature], [sex], [head_portiait], [level], [check], [login_time], [login_num], [online]) VALUES (N'14201115    ', N'123456    ', N'廖亮        ', N'121271782112', N'我爱学习', N'男    ', N'images/Default/DefaultUserImg.jpg
', N'0', N'1', N'2016-01-01 00:00:00.000', N'1', N'0')
GO
GO
INSERT INTO [dbo].[User] ([User_id], [psd], [nickname], [telephone], [signature], [sex], [head_portiait], [level], [check], [login_time], [login_num], [online]) VALUES (N'14201116    ', N'123456    ', N'刘金鑫       ', N'146527152171', N'去图书馆不', N'男    ', N'images/Default/DefaultUserImg.jpg
', N'0', N'1', N'2016-01-01 00:00:00.000', N'1', N'0')
GO
GO
INSERT INTO [dbo].[User] ([User_id], [psd], [nickname], [telephone], [signature], [sex], [head_portiait], [level], [check], [login_time], [login_num], [online]) VALUES (N'14201117    ', N'123456    ', N'欧阳杰       ', N'127862961211', N'我是班级成员', N'男    ', N'images/Default/DefaultUserImg.jpg
', N'0', N'1', N'2016-01-01 00:00:00.000', N'1', N'0')
GO
GO
INSERT INTO [dbo].[User] ([User_id], [psd], [nickname], [telephone], [signature], [sex], [head_portiait], [level], [check], [login_time], [login_num], [online]) VALUES (N'14201118    ', N'123456    ', N'彭德明       ', N'112987921012', N'我是班长', N'男    ', N'images/Default/DefaultUserImg.jpg
', N'0', N'1', N'2016-01-01 00:00:00.000', N'1', N'0')
GO
GO
INSERT INTO [dbo].[User] ([User_id], [psd], [nickname], [telephone], [signature], [sex], [head_portiait], [level], [check], [login_time], [login_num], [online]) VALUES (N'14201119    ', N'123456    ', N'饶烈        ', N'217889375812', N'我是班级成员', N'男    ', N'images/Default/DefaultUserImg.jpg
', N'0', N'1', N'2016-01-01 00:00:00.000', N'1', N'0')
GO
GO
INSERT INTO [dbo].[User] ([User_id], [psd], [nickname], [telephone], [signature], [sex], [head_portiait], [level], [check], [login_time], [login_num], [online]) VALUES (N'14201120    ', N'123456    ', N'唐智勤       ', N'127862773129', N'我是专业代课的', N'男    ', N'upload/User/QQ图片20170313180936.jpg', N'0', N'1', N'2017-03-14 19:05:50.773', N'5', N'0')
GO
GO
INSERT INTO [dbo].[User] ([User_id], [psd], [nickname], [telephone], [signature], [sex], [head_portiait], [level], [check], [login_time], [login_num], [online]) VALUES (N'14201124    ', N'123456    ', N'谢伟        ', N'12587132389 ', N'', N'男    ', N'images/Default/DefaultUserImg.jpg', N'1', N'1', N'2017-03-17 15:50:58.550', N'3', N'1')
GO
GO
INSERT INTO [dbo].[User] ([User_id], [psd], [nickname], [telephone], [signature], [sex], [head_portiait], [level], [check], [login_time], [login_num], [online]) VALUES (N'14201126    ', N'123456    ', N'许乾伦       ', N'176218912621', N'我是英雄联盟里手速最快的', N'男    ', N'images/Default/DefaultUserImg.jpg
', N'0', N'1', N'2017-03-13 20:23:51.737', N'5', N'0')
GO
GO
INSERT INTO [dbo].[User] ([User_id], [psd], [nickname], [telephone], [signature], [sex], [head_portiait], [level], [check], [login_time], [login_num], [online]) VALUES (N'14201127    ', N'123456    ', N'余弘成       ', N'112298791212', N'我很低调', N'男    ', N'images/Default/DefaultUserImg.jpg
', N'0', N'1', N'2016-01-01 00:00:00.000', N'1', N'0')
GO
GO
INSERT INTO [dbo].[User] ([User_id], [psd], [nickname], [telephone], [signature], [sex], [head_portiait], [level], [check], [login_time], [login_num], [online]) VALUES (N'14201128    ', N'123456    ', N'章思远       ', N'612926182111', N'我是大逼哥', N'男    ', N'images/Default/DefaultUserImg.jpg
', N'0', N'1', N'2016-01-01 00:00:00.000', N'1', N'0')
GO
GO
INSERT INTO [dbo].[User] ([User_id], [psd], [nickname], [telephone], [signature], [sex], [head_portiait], [level], [check], [login_time], [login_num], [online]) VALUES (N'14201129    ', N'123456    ', N'周祥        ', N'128767198261', N'兄弟我很强的', N'男    ', N'images/Default/DefaultUserImg.jpg
', N'0', N'1', N'2016-01-01 00:00:00.000', N'1', N'0')
GO
GO
INSERT INTO [dbo].[User] ([User_id], [psd], [nickname], [telephone], [signature], [sex], [head_portiait], [level], [check], [login_time], [login_num], [online]) VALUES (N'14201130    ', N'123456    ', N'周小军       ', N'112289798112', N'我是你die', N'男    ', N'images/Default/DefaultUserImg.jpg
', N'0', N'1', N'2016-01-01 00:00:00.000', N'1', N'0')
GO
GO
INSERT INTO [dbo].[User] ([User_id], [psd], [nickname], [telephone], [signature], [sex], [head_portiait], [level], [check], [login_time], [login_num], [online]) VALUES (N'14201131    ', N'123456    ', N'邹祥宇       ', N'127826812612', N'我是班级成员', N'男    ', N'images/Default/DefaultUserImg.jpg
', N'0', N'1', N'2016-01-01 00:00:00.000', N'1', N'0')
GO
GO
INSERT INTO [dbo].[User] ([User_id], [psd], [nickname], [telephone], [signature], [sex], [head_portiait], [level], [check], [login_time], [login_num], [online]) VALUES (N'14201133    ', N'123456    ', N'李荣杰       ', N'172862891122', N'我是第一届班长', N'男    ', N'images/Default/DefaultUserImg.jpg
', N'0', N'1', N'2016-01-01 00:00:00.000', N'1', N'0')
GO
GO
INSERT INTO [dbo].[User] ([User_id], [psd], [nickname], [telephone], [signature], [sex], [head_portiait], [level], [check], [login_time], [login_num], [online]) VALUES (N'14201134    ', N'123456    ', N'尚旭龙       ', N'287162981212', N'我很厉害', N'男    ', N'images/Default/DefaultUserImg.jpg
', N'0', N'1', N'2016-01-01 00:00:00.000', N'1', N'0')
GO
GO
INSERT INTO [dbo].[User] ([User_id], [psd], [nickname], [telephone], [signature], [sex], [head_portiait], [level], [check], [login_time], [login_num], [online]) VALUES (N'14201135    ', N'123456    ', N'武磊        ', N'171592121222', N'班级成员', N'男    ', N'images/Default/DefaultUserImg.jpg
', N'0', N'1', N'2016-01-01 00:00:00.000', N'1', N'0')
GO
GO
INSERT INTO [dbo].[User] ([User_id], [psd], [nickname], [telephone], [signature], [sex], [head_portiait], [level], [check], [login_time], [login_num], [online]) VALUES (N'Nchu142011  ', N'123456    ', N'Nchu142011', N'1320074071  ', N' ', N'男    ', N'images/Default/DefaultUserImg.jpg', N'0', N'1', N'2017-03-14 19:09:09.957', N'0', N'0')
GO
GO
INSERT INTO [dbo].[User] ([User_id], [psd], [nickname], [telephone], [signature], [sex], [head_portiait], [level], [check], [login_time], [login_num], [online]) VALUES (N'peaktop     ', N'123456    ', N'peaktop   ', N'1320074071  ', N' ', N'男    ', N'images/Default/DefaultUserImg.jpg', N'0', N'1', N'2017-08-01 21:27:09.773', N'0', N'0')
GO
GO
INSERT INTO [dbo].[User] ([User_id], [psd], [nickname], [telephone], [signature], [sex], [head_portiait], [level], [check], [login_time], [login_num], [online]) VALUES (N'SYSTEM      ', N'123456    ', N'SYSTEM    ', N'1           ', N'1', N'男    ', N'sladhldldsa', N'1', N'1', N'2016-01-01 00:00:00.000', N'1', N'0')
GO
GO
INSERT INTO [dbo].[User] ([User_id], [psd], [nickname], [telephone], [signature], [sex], [head_portiait], [level], [check], [login_time], [login_num], [online]) VALUES (N'tlx         ', N'123456    ', N'童林心       ', N'12671       ', N'yatsyuy', N'男    ', N'images/Default/DefaultUserImg.jpg
', N'0', N'1', N'2016-01-01 13:45:37.777', N'1', N'0')
GO
GO
INSERT INTO [dbo].[User] ([User_id], [psd], [nickname], [telephone], [signature], [sex], [head_portiait], [level], [check], [login_time], [login_num], [online]) VALUES (N'wr          ', N'123456    ', N'王睿        ', N'12314134546 ', N'这些程序', N'男    ', N'images/Default/DefaultUserImg.jpg
', N'0', N'1', N'2016-01-01 21:21:59.550', N'1', N'0')
GO
GO
INSERT INTO [dbo].[User] ([User_id], [psd], [nickname], [telephone], [signature], [sex], [head_portiait], [level], [check], [login_time], [login_num], [online]) VALUES (N'wyn         ', N'123456    ', N'吴宇楠       ', N'146436      ', N'sddsd', N'男    ', N'images/Default/DefaultUserImg.jpg
', N'1', N'1', N'2017-03-17 15:39:13.537', N'2', N'1')
GO
GO
INSERT INTO [dbo].[User] ([User_id], [psd], [nickname], [telephone], [signature], [sex], [head_portiait], [level], [check], [login_time], [login_num], [online]) VALUES (N'xjw         ', N'123456    ', N'徐嘉伟       ', N'15570357958 ', N'hello world!', N'男    ', N'upload/User/100.jpg', N'1', N'1', N'2017-10-17 13:31:12.967', N'83', N'1')
GO
GO

-- ----------------------------
-- View structure for View_Admin_User
-- ----------------------------
DROP VIEW [dbo].[View_Admin_User]
GO
CREATE VIEW [dbo].[View_Admin_User] AS 
SELECT   User_id
FROM      dbo.[User]
WHERE   ([level] = 1)
GO

-- ----------------------------
-- View structure for View_AdminMessage
-- ----------------------------
DROP VIEW [dbo].[View_AdminMessage]
GO
CREATE VIEW [dbo].[View_AdminMessage] AS 
SELECT
    Message_id,
    receive_id,
    send_id,
    Dynamic_id,
    Message_time,
    Message_content,
    Message_type
  FROM dbo.Message
  WHERE (receive_id = 'SYSTEM')
GO

-- ----------------------------
-- View structure for View_Album
-- ----------------------------
DROP VIEW [dbo].[View_Album]
GO
CREATE VIEW [dbo].[View_Album] AS 
SELECT   TOP (100) PERCENT Album_id, User_id, Album_name, Album_time, Album_description, photo_num
FROM      dbo.Album
ORDER BY Album_time DESC
GO

-- ----------------------------
-- View structure for View_Announce
-- ----------------------------
DROP VIEW [dbo].[View_Announce]
GO
CREATE VIEW [dbo].[View_Announce] AS 
SELECT   TOP (100) PERCENT Announce_id, User_id, [content], theme, Announce_time
FROM      dbo.Announce
ORDER BY Announce_id ASC
GO

-- ----------------------------
-- View structure for View_Check_Pend
-- ----------------------------
DROP VIEW [dbo].[View_Check_Pend]
GO
CREATE VIEW [dbo].[View_Check_Pend] AS 
SELECT   User_id
FROM      dbo.[User]
WHERE   ([check] = 0)
GO

-- ----------------------------
-- View structure for view_Dynamic
-- ----------------------------
DROP VIEW [dbo].[view_Dynamic]
GO
CREATE VIEW [dbo].[view_Dynamic] AS 
SELECT   TOP (100) PERCENT Dynamic_id, User_id, Album_id, Dynamic_time, Dynamic_content, page_view, [like]
FROM      dbo.Dynamic
ORDER BY Dynamic_id DESC
GO

-- ----------------------------
-- View structure for View_Hot_Dynamic
-- ----------------------------
DROP VIEW [dbo].[View_Hot_Dynamic]
GO
CREATE VIEW [dbo].[View_Hot_Dynamic] AS 
SELECT   TOP (5) Dynamic_id, User_id, Album_id, Dynamic_time, Dynamic_content, page_view, [like]
FROM      dbo.Dynamic
ORDER BY [like] DESC
GO

-- ----------------------------
-- View structure for View_New_Announce
-- ----------------------------
DROP VIEW [dbo].[View_New_Announce]
GO
CREATE VIEW [dbo].[View_New_Announce] AS 
SELECT   TOP (1) PERCENT Announce_id, User_id, [content], theme, Announce_time
FROM      dbo.Announce
ORDER BY Announce_time DESC
GO

-- ----------------------------
-- View structure for View_New_Dynamic
-- ----------------------------
DROP VIEW [dbo].[View_New_Dynamic]
GO
CREATE VIEW [dbo].[View_New_Dynamic] AS 
SELECT max(Dynamic.Dynamic_id) dynamic
FROM dbo.Dynamic
GO

-- ----------------------------
-- View structure for View_Online_User
-- ----------------------------
DROP VIEW [dbo].[View_Online_User]
GO
CREATE VIEW [dbo].[View_Online_User] AS 
SELECT TOP (100) PERCENT
    User_id,
    nickname,
    login_time,
    login_num,
    online
  FROM dbo.[User]
  WHERE [check] = 1 AND User_id !='SYSTEM'
  ORDER BY login_time DESC
GO

-- ----------------------------
-- View structure for View_SMessage
-- ----------------------------
DROP VIEW [dbo].[View_SMessage]
GO
CREATE VIEW [dbo].[View_SMessage] AS 
SELECT   Message_id, receive_id, send_id, Dynamic_id, Message_time, Message_content, Message_type
FROM      dbo.Message
WHERE   (Message_type = 'SYSTEM')
GO

-- ----------------------------
-- View structure for View_Statistic_User
-- ----------------------------
DROP VIEW [dbo].[View_Statistic_User]
GO
CREATE VIEW [dbo].[View_Statistic_User] AS 
SELECT   User_id, nickname, login_time, login_num, online
FROM      dbo.[User]
GO

-- ----------------------------
-- View structure for View_Userinfo
-- ----------------------------
DROP VIEW [dbo].[View_Userinfo]
GO
CREATE VIEW [dbo].[View_Userinfo] AS 
SELECT   User_id, nickname, telephone, signature, head_portiait, sex, [level], [check], login_time, login_num, online
FROM      dbo.[User]
GO

-- ----------------------------
-- Procedure structure for Pce_A_Album
-- ----------------------------
DROP PROCEDURE [dbo].[Pce_A_Album]
GO
create procedure [dbo].[Pce_A_Album](@Album_id int)
as

select *
from dbo.[Album]
where dbo.[Album].Album_id=@Album_id
GO

-- ----------------------------
-- Procedure structure for Pce_A_Dynamic
-- ----------------------------
DROP PROCEDURE [dbo].[Pce_A_Dynamic]
GO
create procedure [dbo].[Pce_A_Dynamic](@Dynamic_id int)
as

select *
from dbo.[Dynamic]
where dbo.[Dynamic].Dynamic_id=@Dynamic_id 
GO

-- ----------------------------
-- Procedure structure for Pce_A_Photo
-- ----------------------------
DROP PROCEDURE [dbo].[Pce_A_Photo]
GO
create procedure [dbo].[Pce_A_Photo](@Photo_id int)
as

select *
from dbo.[Photo]
where dbo.[Photo].Photo_id=@Photo_id

GO

-- ----------------------------
-- Procedure structure for Pce_Add_Album
-- ----------------------------
DROP PROCEDURE [dbo].[Pce_Add_Album]
GO
create procedure [dbo].[Pce_Add_Album](@User_id nchar(12),@Album_name nchar(20),@Album_description nvarchar(50))
as
insert into dbo.Album
values (@User_id,@Album_name,getdate(), @Album_description,0)
GO

-- ----------------------------
-- Procedure structure for Pce_Add_Announce
-- ----------------------------
DROP PROCEDURE [dbo].[Pce_Add_Announce]
GO
create procedure [dbo].[Pce_Add_Announce](@User_id nchar(12),@content nvarchar(50),@theme nchar(20))
as
insert into dbo.Announce
values (@User_id,@content,@theme,getdate())
GO

-- ----------------------------
-- Procedure structure for Pce_Add_Dynamic
-- ----------------------------
DROP PROCEDURE [dbo].[Pce_Add_Dynamic]
GO
create procedure [dbo].[Pce_Add_Dynamic](@User_id nchar(12),@Album_id int,@Dynamic_content nvarchar(50))
as
insert into dbo.Dynamic
values (@User_id,@Album_id,getdate(), @Dynamic_content,0,0)
GO

-- ----------------------------
-- Procedure structure for Pce_Add_Message
-- ----------------------------
DROP PROCEDURE [dbo].[Pce_Add_Message]
GO
create procedure [dbo].[Pce_Add_Message](@receive_id nchar(12),@send_id nchar(12),@Dynamic_id int,@Message_content nvarchar(50),@Message_type nchar(20))
as
insert into dbo.Message
values (@receive_id,@send_id,@Dynamic_id,getdate(),@Message_content,@Message_type)
GO

-- ----------------------------
-- Procedure structure for Pce_Add_Photo
-- ----------------------------
DROP PROCEDURE [dbo].[Pce_Add_Photo]
GO
create procedure [dbo].[Pce_Add_Photo](@Dynamic_id int,@User_id nchar(12),@Album_id int,@save nvarchar(50))
as
insert into dbo.Photo
values (@Dynamic_id,@User_id,@Album_id,getdate(),@save)
GO

-- ----------------------------
-- Procedure structure for Pce_Album_Change
-- ----------------------------
DROP PROCEDURE [dbo].[Pce_Album_Change]
GO
create procedure [dbo].[Pce_Album_Change](@Album_id int,@Album_name nchar(20),@Album_description nvarchar(50))
as
update Album
set Album_name = @Album_name,Album_description = @Album_description
where Album_id = @Album_id
GO

-- ----------------------------
-- Procedure structure for Pce_Album_Photo
-- ----------------------------
DROP PROCEDURE [dbo].[Pce_Album_Photo]
GO
create procedure [dbo].[Pce_Album_Photo](@Album_id int)
as
select *
from dbo.Photo
where dbo.Photo.Album_id=@Album_id
GO

-- ----------------------------
-- Procedure structure for Pce_Delete_Album
-- ----------------------------
DROP PROCEDURE [dbo].[Pce_Delete_Album]
GO
create procedure [dbo].[Pce_Delete_Album](@Album_id int)
as
delete
from dbo.Album
where dbo.Album.Album_id=@Album_id
GO

-- ----------------------------
-- Procedure structure for Pce_Delete_Announce
-- ----------------------------
DROP PROCEDURE [dbo].[Pce_Delete_Announce]
GO
create procedure [dbo].[Pce_Delete_Announce](@Announce_id int)
as

delete 
from dbo.Announce
where dbo.Announce.Announce_id=@Announce_id 
GO

-- ----------------------------
-- Procedure structure for Pce_Delete_Dynamic
-- ----------------------------
DROP PROCEDURE [dbo].[Pce_Delete_Dynamic]
GO
create procedure [dbo].[Pce_Delete_Dynamic](@Dynamic_id int)
as

delete 
from dbo.Dynamic
where dbo.Dynamic.Dynamic_id=@Dynamic_id 

GO

-- ----------------------------
-- Procedure structure for Pce_Delete_Message
-- ----------------------------
DROP PROCEDURE [dbo].[Pce_Delete_Message]
GO
create procedure [dbo].[Pce_Delete_Message](@Message_id int)
as

delete 
from dbo.Message
where dbo.Message.Message_id=@Message_id 
GO

-- ----------------------------
-- Procedure structure for Pce_Delete_Photo
-- ----------------------------
DROP PROCEDURE [dbo].[Pce_Delete_Photo]
GO
create procedure [dbo].[Pce_Delete_Photo](@Photo_id int)
as

delete 
from dbo.Photo
where dbo.Photo.Photo_id=@Photo_id 
GO

-- ----------------------------
-- Procedure structure for Pce_Delete_Uncheck_User
-- ----------------------------
DROP PROCEDURE [dbo].[Pce_Delete_Uncheck_User]
GO
create procedure [dbo].[Pce_Delete_Uncheck_User](@User_id nchar(12))
as
delete
from [User]
where [User].[check] = 0 and [User].User_id = @User_id

GO

-- ----------------------------
-- Procedure structure for Pce_Dynamic_Like
-- ----------------------------
DROP PROCEDURE [dbo].[Pce_Dynamic_Like]
GO
create procedure [dbo].[Pce_Dynamic_Like](@Dynamic_id int,@User_id nchar(12))
as
insert into Thumb_Up values(@Dynamic_id,@User_id)
update Dynamic
set [like] =( select count(*) from Thumb_Up  where Dynamic_id = @Dynamic_id)
where @Dynamic_id = Dynamic_id
GO

-- ----------------------------
-- Procedure structure for Pce_Dynamic_Message
-- ----------------------------
DROP PROCEDURE [dbo].[Pce_Dynamic_Message]
GO
create procedure [dbo].[Pce_Dynamic_Message](@Dynamic_id int)
as
select *
from dbo.Message
where dbo.Message.Dynamic_id=@Dynamic_id
GO

-- ----------------------------
-- Procedure structure for Pce_Dynamic_Pageview
-- ----------------------------
DROP PROCEDURE [dbo].[Pce_Dynamic_Pageview]
GO
create procedure [dbo].[Pce_Dynamic_Pageview](@Dynamic_id int)
as
update Dynamic
set dbo.Dynamic.page_view = Dynamic.page_view+1
where Dynamic_id=@Dynamic_id
GO

-- ----------------------------
-- Procedure structure for Pce_Dynamic_Photo
-- ----------------------------
DROP PROCEDURE [dbo].[Pce_Dynamic_Photo]
GO
create procedure [dbo].[Pce_Dynamic_Photo](@Dynamic_id int)
as
select *
from dbo.Photo
where dbo.Photo.Dynamic_id=@Dynamic_id
GO

-- ----------------------------
-- Procedure structure for Pce_Like_Check
-- ----------------------------
DROP PROCEDURE [dbo].[Pce_Like_Check]
GO
create procedure [dbo].[Pce_Like_Check](@User_id nchar(12),@Dynamic_id int)
as
if((select User_id from dbo.Thumb_Up where User_id=@User_id and Dynamic_id = @Dynamic_id) is null)
print 1
else 
print 0
GO

-- ----------------------------
-- Procedure structure for Pce_Personal_Dynamic
-- ----------------------------
DROP PROCEDURE [dbo].[Pce_Personal_Dynamic]
GO
create procedure [dbo].[Pce_Personal_Dynamic](@User_id nchar(12))
as

select *
from dbo.[Dynamic]
where dbo.[Dynamic].User_id=@User_id 
GO

-- ----------------------------
-- Procedure structure for Pce_Personal_Message
-- ----------------------------
DROP PROCEDURE [dbo].[Pce_Personal_Message]
GO
create procedure [dbo].[Pce_Personal_Message](@receive_id nchar(12))
as
select *
from dbo.Message
where dbo.Message.receive_id=@receive_id
GO

-- ----------------------------
-- Procedure structure for Pce_User_Change
-- ----------------------------
DROP PROCEDURE [dbo].[Pce_User_Change]
GO
create procedure [dbo].[Pce_User_Change](@User_id nchar(12),@nickname nchar(10),@telephone numeric(12,0),@signature nvarchar(50),@sex nchar(5),@head_portiait nvarchar(50))
as
update [User]
set [User].nickname=@nickname,[User].telephone=@telephone,[User].signature=@signature,[User].sex=@sex,[User].head_portiait=@head_portiait
where [User].User_id=@User_id
GO

-- ----------------------------
-- Procedure structure for Pce_User_Change_psd
-- ----------------------------
DROP PROCEDURE [dbo].[Pce_User_Change_psd]
GO
CREATE PROCEDURE [dbo].[Pce_User_Change_psd](@User_id NCHAR(12), @psdOld NCHAR(10), @psdNew NCHAR(10))
AS
  UPDATE [User]
  SET [User].psd = @psdNew
  WHERE [User].User_id = @User_id AND [User].psd = @psdOld;
GO

-- ----------------------------
-- Procedure structure for Pce_User_Changelevel
-- ----------------------------
DROP PROCEDURE [dbo].[Pce_User_Changelevel]
GO
create procedure [dbo].[Pce_User_Changelevel](@User_id nchar(12))
as
update [User]
set [User].level = 1
where User_id=@User_id
GO

-- ----------------------------
-- Procedure structure for Pce_User_Check
-- ----------------------------
DROP PROCEDURE [dbo].[Pce_User_Check]
GO
create procedure [dbo].[Pce_User_Check](@User_id nchar(12))
as
update [User]
set [User].[check] = 1
where User_id=@User_id

GO

-- ----------------------------
-- Procedure structure for Pce_User_Detail
-- ----------------------------
DROP PROCEDURE [dbo].[Pce_User_Detail]
GO
create procedure [dbo].[Pce_User_Detail](@User_id nchar(12))
as

select [User].User_id,[User].nickname,[User].head_portiait,[User].signature,[User].sex,[User].telephone,[User].login_num,[User].login_time,[User].online,[User].level,[User].[check]
from dbo.[User]
where dbo.[User].User_id=@User_id 
GO

-- ----------------------------
-- Procedure structure for Pce_User_Login
-- ----------------------------
DROP PROCEDURE [dbo].[Pce_User_Login]
GO
create procedure [dbo].[Pce_User_Login](@User_id nchar(12))
as
update [User]
set [User].online = 1,[User].login_time = getdate(),[User].login_num = [User].login_num + 1
where User_id=@User_id
GO

-- ----------------------------
-- Procedure structure for Pce_User_Login_Check
-- ----------------------------
DROP PROCEDURE [dbo].[Pce_User_Login_Check]
GO
CREATE PROCEDURE [dbo].[Pce_User_Login_Check](@User_id NCHAR(12), @psd NCHAR(10))
AS
  SELECT *
  FROM [User]
  WHERE User_id = @User_id AND psd = @psd AND [User].[check] = 1
GO

-- ----------------------------
-- Procedure structure for Pce_User_Logout
-- ----------------------------
DROP PROCEDURE [dbo].[Pce_User_Logout]
GO
create procedure [dbo].[Pce_User_Logout](@User_id nchar(12))
as
update [User]
set [User].online = 0
where User_id=@User_id
GO

-- ----------------------------
-- Procedure structure for Pce_User_Reg
-- ----------------------------
DROP PROCEDURE [dbo].[Pce_User_Reg]
GO
create procedure [dbo].[Pce_User_Reg](@User_id nchar(12),@psd nchar(10),@telephone NCHAR(12))
as
insert into dbo.[User]
values (@User_id,@psd,@User_id,@telephone,' ','男','images/Default/DefaultUserImg.jpg',0,0,getdate(),0,0)
GO

-- ----------------------------
-- Procedure structure for Pce_User_ResetPsd
-- ----------------------------
DROP PROCEDURE [dbo].[Pce_User_ResetPsd]
GO
create procedure [dbo].[Pce_User_ResetPsd](@User_id nchar(12))
as
update [User]
set [User].psd='123456'
where [User].User_id=@User_id
GO

-- ----------------------------
-- Procedure structure for Pce_User_Simpleinfo
-- ----------------------------
DROP PROCEDURE [dbo].[Pce_User_Simpleinfo]
GO
create procedure [dbo].[Pce_User_Simpleinfo](@User_id nchar(12))
as

select [User].User_id,[User].nickname,[User].head_portiait,[User].signature
from dbo.[User]
where dbo.[User].User_id=@User_id 
GO

-- ----------------------------
-- Indexes structure for table Album
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table Album
-- ----------------------------
ALTER TABLE [dbo].[Album] ADD PRIMARY KEY ([Album_id])
GO

-- ----------------------------
-- Indexes structure for table Announce
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table Announce
-- ----------------------------
ALTER TABLE [dbo].[Announce] ADD PRIMARY KEY ([Announce_id])
GO

-- ----------------------------
-- Indexes structure for table Dynamic
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table Dynamic
-- ----------------------------
ALTER TABLE [dbo].[Dynamic] ADD PRIMARY KEY ([Dynamic_id])
GO

-- ----------------------------
-- Indexes structure for table Message
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table Message
-- ----------------------------
ALTER TABLE [dbo].[Message] ADD PRIMARY KEY ([Message_id])
GO

-- ----------------------------
-- Indexes structure for table Photo
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table Photo
-- ----------------------------
ALTER TABLE [dbo].[Photo] ADD PRIMARY KEY ([Photo_id])
GO

-- ----------------------------
-- Triggers structure for table Photo
-- ----------------------------
DROP TRIGGER [dbo].[Tri_Album_Photonum]
GO
CREATE TRIGGER [dbo].[Tri_Album_Photonum]
ON [dbo].[Photo]
AFTER INSERT
AS
update dbo.Album
set dbo.[Album].photo_num = [Album].photo_num + 1
GO

-- ----------------------------
-- Indexes structure for table Thumb_Up
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table Thumb_Up
-- ----------------------------
ALTER TABLE [dbo].[Thumb_Up] ADD PRIMARY KEY ([Dynamic_id], [User_id])
GO

-- ----------------------------
-- Indexes structure for table User
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table User
-- ----------------------------
ALTER TABLE [dbo].[User] ADD PRIMARY KEY ([User_id])
GO

-- ----------------------------
-- Foreign Key structure for table [dbo].[Album]
-- ----------------------------
ALTER TABLE [dbo].[Album] ADD FOREIGN KEY ([User_id]) REFERENCES [dbo].[User] ([User_id]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO

-- ----------------------------
-- Foreign Key structure for table [dbo].[Announce]
-- ----------------------------
ALTER TABLE [dbo].[Announce] ADD FOREIGN KEY ([User_id]) REFERENCES [dbo].[User] ([User_id]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO

-- ----------------------------
-- Foreign Key structure for table [dbo].[Dynamic]
-- ----------------------------
ALTER TABLE [dbo].[Dynamic] ADD FOREIGN KEY ([Album_id]) REFERENCES [dbo].[Album] ([Album_id]) ON DELETE CASCADE ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[Dynamic] ADD FOREIGN KEY ([User_id]) REFERENCES [dbo].[User] ([User_id]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO

-- ----------------------------
-- Foreign Key structure for table [dbo].[Message]
-- ----------------------------
ALTER TABLE [dbo].[Message] ADD FOREIGN KEY ([Dynamic_id]) REFERENCES [dbo].[Dynamic] ([Dynamic_id]) ON DELETE CASCADE ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[Message] ADD FOREIGN KEY ([receive_id]) REFERENCES [dbo].[User] ([User_id]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO
ALTER TABLE [dbo].[Message] ADD FOREIGN KEY ([send_id]) REFERENCES [dbo].[User] ([User_id]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO

-- ----------------------------
-- Foreign Key structure for table [dbo].[Photo]
-- ----------------------------
ALTER TABLE [dbo].[Photo] ADD FOREIGN KEY ([Album_id]) REFERENCES [dbo].[Album] ([Album_id]) ON DELETE CASCADE ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[Photo] ADD FOREIGN KEY ([User_id]) REFERENCES [dbo].[User] ([User_id]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO
