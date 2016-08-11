/**

 爬虫文章表结构-SQL MySQL

 */

CREATE TABLE `tbGovArticle` (

  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT  COMMENT '主键,自动增加',

  `domain` varchar(255) CHARACTER SET utf8 DEFAULT NULL   COMMENT '域名',

  `topicMap` varchar(1024) CHARACTER SET utf8 DEFAULT NULL COMMENT '文章栏目地图描述',

  `articleTitle` varchar(1024) CHARACTER SET utf8 DEFAULT NULL COMMENT '文章标题',

  `publishTime` varchar(128) CHARACTER SET utf8 DEFAULT NULL COMMENT '文章发布时间',

  `author` varchar(512) CHARACTER SET utf8 DEFAULT NULL COMMENT '文章来源，文章作者',

  `articleContents` longtext CHARACTER SET utf8 DEFAULT NULL COMMENT '文章内容',

  `articleRawUrl` varchar(1024) CHARACTER SET utf8 DEFAULT NULL COMMENT '文章链接',

  `crawlTime` datetime DEFAULT NULL COMMENT '爬取时间',

  PRIMARY KEY (`id`)

);



CREATE TABLE `tbGovArticleImg` (

  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键，自动增加',

  `domain` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '域名',

  `articleRawUrl` varchar(1024) CHARACTER SET utf8 DEFAULT NULL COMMENT '文章链接',

  `articleTitle` varchar(1024) CHARACTER SET utf8 DEFAULT NULL COMMENT '文章标题',

  `imgSrcUrl` varchar(1024) CHARACTER SET utf8 DEFAULT NULL COMMENT '文章中图片链接',

  `isDownload` boolean DEFAULT FALSE COMMENT '图片是否下载，false 图片没有下载，true 图片已经下载',

  `imgName` varchar(1024) CHARACTER SET utf8 DEFAULT NULL COMMENT '图片文件名称',

  `savePath` varchar(2048) CHARACTER SET utf8 DEFAULT NULL COMMENT '图片的存储路径，在文件服务器上的路径或在图片服务器上的路径',

  `crawlTime` datetime DEFAULT NULL COMMENT '爬取时间',

  PRIMARY KEY (`id`)

);



CREATE TABLE `tbDoorNewsArticle` (

  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键，自动增加',

  `domain` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '域名',

  `topicMap` varchar(1024) CHARACTER SET utf8 DEFAULT NULL COMMENT '文章栏目地图描述',

  `articleTitle` varchar(1024) CHARACTER SET utf8 DEFAULT NULL COMMENT '文章标题',

  `rawTitle` varchar(1024) CHARACTER SET utf8 DEFAULT NULL COMMENT '文章原标题',

  `publishTime` varchar(128) CHARACTER SET utf8 DEFAULT NULL COMMENT '文章发布时间',

  `author` varchar(512) CHARACTER SET utf8 DEFAULT NULL COMMENT '文章来源，作者',

  `articleContents` longtext CHARACTER SET utf8 DEFAULT NULL COMMENT '文章内容',

  `commentCount` int DEFAULT 0 COMMENT '评论数',

  `articleRawUrl` varchar(1024) CHARACTER SET utf8 DEFAULT NULL COMMENT '文章链接',

  `crawlTime` datetime DEFAULT NULL COMMENT '爬取时间',

  PRIMARY KEY (`id`)

);



CREATE TABLE `tbDoorNewsComment` (

  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键，自动增加',

  `domain` varchar(255) CHARACTER SET utf8  DEFAULT NULL COMMENT '域名',

  `articleRawUrl` varchar(1024) CHARACTER SET utf8 DEFAULT NULL COMMENT '文章链接',

  `imgSrcUrl` varchar(1024) CHARACTER SET utf8  DEFAULT NULL COMMENT '评论者头像链接',

  `author` varchar(128) CHARACTER SET utf8 DEFAULT NULL COMMENT '评论者昵称',

  `local` varchar(128) CHARACTER SET utf8 DEFAULT NULL COMMENT '评论者评论时所在的地域',

  `time` varchar(128) CHARACTER SET utf8 DEFAULT NULL COMMENT '评论时间',

  `contents` text CHARACTER SET utf8 DEFAULT NULL COMMENT '评论内容',

  `likesCount` int  DEFAULT 0 COMMENT '点赞数',

  `isImgDownload` boolean  DEFAULT FALSE COMMENT '头像图片是否下载，false 未下载，true 已经下载',

  `imgName` varchar(1024) CHARACTER SET utf8  DEFAULT NULL COMMENT '头像图片名称',

  `savePath` varchar(2048) CHARACTER SET utf8  DEFAULT NULL COMMENT '头像图片保存在文件服务器或图片服务器上的路径',

  `crawlTime` datetime DEFAULT NULL COMMENT '爬取时间',

  PRIMARY KEY (`id`)

);



CREATE TABLE `tbDoorNewsImg` (

  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键，自动增加',

  `domain` varchar(255) CHARACTER SET utf8  DEFAULT NULL COMMENT '域名',

  `articleRawUrl` varchar(1024) CHARACTER SET utf8 DEFAULT NULL COMMENT '文章链接',

  `articleTitle` varchar(1024) CHARACTER SET utf8 DEFAULT NULL COMMENT '文章标题',

  `imgSrcUrl` varchar(1024) CHARACTER SET utf8  DEFAULT NULL COMMENT '文章中图片链接',

  `isDownload` boolean  DEFAULT FALSE COMMENT '图片是否下载，false 图片没有下载，true 图片已经下载',

  `imgName` varchar(1024) CHARACTER SET utf8 NULL DEFAULT NULL COMMENT '图片文件名称',

  `savePath` varchar(2048) CHARACTER SET utf8  DEFAULT NULL COMMENT '图片的存储路径，在文件服务器上的路径或在图片服务器上的路径',

  `crawlTime` datetime DEFAULT NULL COMMENT '爬取时间',

  PRIMARY KEY (`id`)

);



CREATE TABLE `tbSogouWeChatArticle` (

  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键，自动增加',

  `weChatNo` varchar(255) CHARACTER SET utf8  DEFAULT NULL COMMENT '微信号',

  `weChatName` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '微信名称',

  `mainGroup` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '账号主体',

  `essential` varchar(4096) CHARACTER SET utf8  DEFAULT NULL COMMENT '微信号功能介绍',

  `articleTitle` varchar(1024) CHARACTER SET utf8  DEFAULT NULL COMMENT '文章标题',

  `publishTime` varchar(255) CHARACTER SET utf8  DEFAULT NULL COMMENT '文章发布时间',

  `articleStyleContents` longtext CHARACTER SET utf8 DEFAULT NULL COMMENT '带HTML格式的文章内容',

  `readCount` int  DEFAULT 0 COMMENT '阅读数',

  `likesCount` int  DEFAULT 0 COMMENT '点赞数',

  `crawlTime` datetime DEFAULT NULL COMMENT '爬取时间',

  PRIMARY KEY (`id`)

);



CREATE TABLE `tbTopBaidu` (

  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',

  `topNo` int DEFAULT NULL COMMENT '热榜排名',

  `keyWords` varchar(128) CHARACTER SET utf8 DEFAULT NULL COMMENT '关键词',

  `searchIndex` int DEFAULT NULL COMMENT '搜索指数',

  `searchUrl` varchar(1024) CHARACTER SET utf8 DEFAULT NULL COMMENT '关键词搜索链接',

  `crawlTime` datetime DEFAULT NULL COMMENT '爬取时间',

  PRIMARY KEY (`id`)

);



CREATE TABLE `tbTopBaiduArticleInfo` (

  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键，自动增加',

  `articleTitle` varchar(1024) CHARACTER SET utf8  DEFAULT NULL COMMENT '文章标题',

  `essential` varchar(4096) CHARACTER SET utf8  DEFAULT NULL COMMENT '文章概要',

  `publishTime` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '文章发布时间',

  `keyWords` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '关键词',

  `articleRawUrl` varchar(1024) CHARACTER SET utf8 DEFAULT NULL COMMENT '文章链接',

  `crawlTime` datetime DEFAULT NULL COMMENT '爬取时间',

  PRIMARY KEY (`id`)

);



