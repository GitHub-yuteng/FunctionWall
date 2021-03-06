CREATE TABLE `user`
(
  `id`           BIGINT(20) NOT NULL AUTO_INCREMENT,
  `username`     VARCHAR(16) NOT NULL,
  `account`      VARCHAR(16) NOT NULL,
  `password`     VARCHAR(64) NOT NULL,
  `realname`     VARCHAR(10) NOT NULL,
  `level`        VARCHAR(10) NOT NULL,
  `sex`          CHAR        NOT NULL,
  `created_date` DATETIME DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;


CREATE TABLE `topic_lovewall`
(
  `id`            BIGINT (20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `title`         VARCHAR(32)   NOT NULL,
  `username` VARCHAR(16)   NOT NULL,
  `link`          VARCHAR(16)  DEFAULT NULL,
  `content`       VARCHAR(1024) NOT NULL,
  `imageUrl`      VARCHAR(256) DEFAULT NULL,
  `created_date`  DATETIME      NOT NULL,
  `id_user`       BIGINT (20) NOT NULL,
  FOREIGN KEY (`id_user`) REFERENCES `user` (`id`)
) ENGINE = INNODB AUTO_INCREMENT = 30 DEFAULT CHARSET = utf8;

CREATE TABLE `topic_complaintwall`
(
  `id`            BIGINT (20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `title`         VARCHAR(32)   NOT NULL,
  `username` VARCHAR(16)   NOT NULL,
  `link`          VARCHAR(16)  DEFAULT NULL,
  `content`       VARCHAR(1024) NOT NULL,
  `imageUrl`      VARCHAR(256) DEFAULT NULL,
  `created_date`  DATETIME      NOT NULL,
  `id_user`       BIGINT (20) NOT NULL,
  FOREIGN KEY (`id_user`) REFERENCES `user` (`id`)
) ENGINE = INNODB AUTO_INCREMENT = 30 DEFAULT CHARSET = utf8;

CREATE TABLE `item`
(
  `id`            bigint(20) NOT NULL AUTO_INCREMENT,
  `realname_user` varchar(16)   NOT NULL,
  `link`          tinyint(4) DEFAULT NULL,
  `type`          varchar(16)  DEFAULT NULL,
  `category`      varchar(16)  DEFAULT NULL,
  `content`       varchar(1024) NOT NULL,
  `image_url`     varchar(256) DEFAULT NULL,
  `created_date`  datetime      NOT NULL,
  `id_user`       bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY             `id_user` (`id_user`),
  CONSTRAINT `item_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8



CREATE TABLE `logs`
(
  `id`       INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `action`   VARCHAR(100)  DEFAULT NULL COMMENT '事件',
  `data`     VARCHAR(2000) DEFAULT NULL COMMENT '数据',
  `authorId` INT(10) DEFAULT NULL COMMENT '作者编号',
  `ip`       VARCHAR(20)   DEFAULT NULL COMMENT 'ip地址',
  `created`  INT(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;