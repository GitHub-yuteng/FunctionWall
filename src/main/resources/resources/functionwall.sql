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
) ENGINE=INNODB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8


CREATE TABLE `topic_lovewall`
(
  `id`       BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `title`    VARCHAR(32)   NOT NULL,
  `category` VARCHAR(16)   NOT NULL,
  `content`  VARCHAR(1024) NOT NULL,
  `image`    MEDIUMBLOB,
  `created_date` DATETIME DEFAULT NULL,
  `id_user`  BIGINT(20) DEFAULT NULL,
  FOREIGN KEY (`id_user`)  REFERENCES `user` (`id`)
) ENGINE=INNODB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8

CREATE TABLE `topic_complaintwall`
(
  `id`       BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `title`    VARCHAR(32)   NOT NULL,
  `category` VARCHAR(16)   NOT NULL,
  `content`  VARCHAR(1024) NOT NULL,
  `image`    MEDIUMBLOB,
  `created_date` DATETIME DEFAULT NULL,
  `id_user`  BIGINT(20) DEFAULT NULL,
  FOREIGN KEY (`id_user`)  REFERENCES `user` (`id`)
) ENGINE=INNODB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8