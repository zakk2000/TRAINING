-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        10.0.21-MariaDB - mariadb.org binary distribution
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  9.3.0.5046
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 테이블 authorities 구조 내보내기
CREATE TABLE IF NOT EXISTS `AUTHORITIES` (
  `AUTHORITY` varchar(50) NOT NULL COMMENT '권한',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '등록자 ID',
  `CREATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '등록일자',
  `UPDATED_BY` varchar(50) DEFAULT NULL COMMENT '수정자 ID',
  `UPDATED_DATE` timestamp NULL DEFAULT NULL COMMENT '수정일자',
  PRIMARY KEY (`AUTHORITY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 authorities:~3 rows (대략적) 내보내기
/*!40000 ALTER TABLE `AUTHORITIES` DISABLE KEYS */;
INSERT INTO `AUTHORITIES` (`AUTHORITY`, `CREATED_BY`) VALUES
	('ADMIN', 'admin'),
	('ANONYMOUS', 'admin'),
	('USER', 'admin');
/*!40000 ALTER TABLE `AUTHORITIES` ENABLE KEYS */;

-- 테이블 secured_resources 구조 내보내기
CREATE TABLE IF NOT EXISTS `SECURED_RESOURCES` (
  `RESOURCE_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '자원관리 순번',
  `RESOURCE_NAME` varchar(100) NOT NULL COMMENT '자원명',
  `RESOURCE_PATTERN` varchar(250) NOT NULL COMMENT '자원 URL(ANT PATTERN)',
  `SORT_ORDER` int(11) NOT NULL COMMENT '정렬 기준값',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '등록자 ID',
  `CREATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '등록일자',
  `UPDATED_BY` varchar(50) DEFAULT NULL COMMENT '수정자 ID',
  `UPDATED_DATE` timestamp NULL DEFAULT NULL COMMENT '수정일자',
  PRIMARY KEY (`RESOURCE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- 테이블 데이터 secured_resources:~6 rows (대략적) 내보내기
/*!40000 ALTER TABLE `SECURED_RESOURCES` DISABLE KEYS */;
INSERT INTO `SECURED_RESOURCES` (`RESOURCE_ID`, `RESOURCE_NAME`, `RESOURCE_PATTERN`, `SORT_ORDER`, `CREATED_BY`) VALUES
	(1, '정적자원', '/resources/**', 1, 'admin'),
	(2, '로그인화면', '/loginForm.app', 2, 'admin'),
	(3, '백오피스화면', '/mng/*.app', 3, 'admin'),
	(4, '측위결과조회화면', '/pos/*.app', 4, 'admin'),
	(5, 'RESTful자원', '/rest/**', 5, 'admin'),
	(6, '사용자화면', '/**/*.app', 6, 'admin');
/*!40000 ALTER TABLE `SECURED_RESOURCES` ENABLE KEYS */;

-- 테이블 secured_resources_authorities 구조 내보내기
CREATE TABLE IF NOT EXISTS `SECURED_RESOURCES_AUTHORITIES` (
  `RESOURCE_ID` int(11) NOT NULL COMMENT '자원관리 순번',
  `AUTHORITY` varchar(50) NOT NULL COMMENT '권한',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '등록자 ID',
  `CREATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '등록일자',
  `UPDATED_BY` varchar(50) DEFAULT NULL COMMENT '수정자 ID',
  `UPDATED_DATE` timestamp NULL DEFAULT NULL COMMENT '수정일자',
  PRIMARY KEY (`RESOURCE_ID`,`AUTHORITY`),
  KEY `FK_AUTHORITY_RESOURCE_AUTH` (`AUTHORITY`),
  CONSTRAINT `FK_AUTHORITY_RESOURCE` FOREIGN KEY (`RESOURCE_ID`) REFERENCES `SECURED_RESOURCES` (`RESOURCE_ID`) ON DELETE CASCADE,
  CONSTRAINT `FK_AUTHORITY_RESOURCE_AUTH` FOREIGN KEY (`AUTHORITY`) REFERENCES `AUTHORITIES` (`AUTHORITY`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 secured_resources_authorities:~9 rows (대략적) 내보내기
/*!40000 ALTER TABLE `SECURED_RESOURCES_AUTHORITIES` DISABLE KEYS */;
INSERT INTO `SECURED_RESOURCES_AUTHORITIES` (`RESOURCE_ID`, `AUTHORITY`, `CREATED_BY`) VALUES
	(1, 'ANONYMOUS', 'admin'),
	(2, 'ANONYMOUS', 'admin'),
	(3, 'ADMIN', 'admin'),
	(4, 'ADMIN', 'admin'),
	(4, 'USER', 'admin'),
	(5, 'ADMIN', 'admin'),
	(5, 'USER', 'admin'),
	(6, 'ADMIN', 'admin'),
	(6, 'USER', 'admin');
/*!40000 ALTER TABLE `SECURED_RESOURCES_AUTHORITIES` ENABLE KEYS */;

-- 테이블 users 구조 내보내기
CREATE TABLE IF NOT EXISTS `USERS` (
  `USER_ID` varchar(50) NOT NULL COMMENT '사용자 ID',
  `USER_PW` varchar(250) NOT NULL COMMENT '사용자 비밀번호',
  `USER_NAME` varchar(50) NOT NULL COMMENT '사용자 이름',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '등록자 ID',
  `CREATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '등록일자',
  `UPDATED_BY` varchar(50) DEFAULT NULL COMMENT '수정자 ID',
  `UPDATED_DATE` timestamp NULL DEFAULT NULL COMMENT '수정일자',
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 users:~4 rows (대략적) 내보내기
/*!40000 ALTER TABLE `USERS` DISABLE KEYS */;
INSERT INTO `USERS` (`USER_ID`, `USER_PW`, `USER_NAME`, `CREATED_BY`) VALUES
	('appuser01', '$2a$10$ynyjWaq9a0qalIWNabsal.a85BysNq0AqphIvVSURh/EfsUj0dXAu', '관리자', 'admin'),
	('appuser02', '$2a$10$ynyjWaq9a0qalIWNabsal.a85BysNq0AqphIvVSURh/EfsUj0dXAu', '관리자', 'admin'),
	('appuser03', '$2a$10$ynyjWaq9a0qalIWNabsal.a85BysNq0AqphIvVSURh/EfsUj0dXAu', '관리자', 'admin'),
	('appuser04', '$2a$10$ynyjWaq9a0qalIWNabsal.a85BysNq0AqphIvVSURh/EfsUj0dXAu', '관리자', 'admin'),
	('appuser05', '$2a$10$ynyjWaq9a0qalIWNabsal.a85BysNq0AqphIvVSURh/EfsUj0dXAu', '관리자', 'admin'),
	('appuser06', '$2a$10$ynyjWaq9a0qalIWNabsal.a85BysNq0AqphIvVSURh/EfsUj0dXAu', '관리자', 'admin'),
	('appuser07', '$2a$10$ynyjWaq9a0qalIWNabsal.a85BysNq0AqphIvVSURh/EfsUj0dXAu', '관리자', 'admin'),
	('appuser08', '$2a$10$ynyjWaq9a0qalIWNabsal.a85BysNq0AqphIvVSURh/EfsUj0dXAu', '관리자', 'admin'),
	('appuser09', '$2a$10$ynyjWaq9a0qalIWNabsal.a85BysNq0AqphIvVSURh/EfsUj0dXAu', '관리자', 'admin'),
	('appuser10', '$2a$10$ynyjWaq9a0qalIWNabsal.a85BysNq0AqphIvVSURh/EfsUj0dXAu', '관리자', 'admin');
/*!40000 ALTER TABLE `USERS` ENABLE KEYS */;

-- 테이블 user_authorities 구조 내보내기
CREATE TABLE IF NOT EXISTS `USER_AUTHORITIES` (
  `USER_ID` varchar(50) NOT NULL COMMENT '사용자 ID',
  `AUTHORITY` varchar(50) NOT NULL COMMENT '권한',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '등록자 ID',
  `CREATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '등록일자',
  `UPDATED_BY` varchar(50) DEFAULT NULL COMMENT '수정자 ID',
  `UPDATED_DATE` timestamp NULL DEFAULT NULL COMMENT '수정일자',
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `IX_USER_AUTH` (`USER_ID`,`AUTHORITY`),
  KEY `FK_AUTHORITIES_USER_AUTH` (`AUTHORITY`),
  CONSTRAINT `FK_AUTHORITIES_USER` FOREIGN KEY (`USER_ID`) REFERENCES `USERS` (`USER_ID`) ON DELETE CASCADE,
  CONSTRAINT `FK_AUTHORITIES_USER_AUTH` FOREIGN KEY (`AUTHORITY`) REFERENCES `AUTHORITIES` (`AUTHORITY`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 user_authorities:~4 rows (대략적) 내보내기
/*!40000 ALTER TABLE `USER_AUTHORITIES` DISABLE KEYS */;
INSERT INTO `USER_AUTHORITIES` (`USER_ID`, `AUTHORITY`, `CREATED_BY`) VALUES
	('appuser01', 'ADMIN', 'admin'),
	('appuser02', 'ADMIN', 'admin'),
	('appuser03', 'ADMIN', 'admin'),
	('appuser04', 'ADMIN', 'admin'),
	('appuser05', 'ADMIN', 'admin'),
	('appuser06', 'ADMIN', 'admin'),
	('appuser07', 'ADMIN', 'admin'),
	('appuser08', 'ADMIN', 'admin'),
	('appuser09', 'ADMIN', 'admin'),
	('appuser10', 'ADMIN', 'admin');
/*!40000 ALTER TABLE `USER_AUTHORITIES` ENABLE KEYS */;

-- 테이블 product_items 구조 내보내기
CREATE TABLE IF NOT EXISTS `PRODUCT_ITEMS` (
  `item_id` int(11) NOT NULL AUTO_INCREMENT,
  `item_name` varchar(250) NOT NULL,
  `item_price` int(11) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `created_by` varchar(50) NOT NULL,
  `updated_date` timestamp NULL DEFAULT NULL,
  `updated_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- 테이블 데이터 product_items:~34 rows (대략적) 내보내기
/*!40000 ALTER TABLE `PRODUCT_ITEMS` DISABLE KEYS */;
INSERT INTO `PRODUCT_ITEMS` (`item_id`, `item_name`, `item_price`, `created_by`) VALUES
	(1, 'Backbone.js 프로그래밍', 29000, 'zakk'),
	(2, 'jQuery Mobile 프로그래밍', 24000, 'zakk'),
	(3, 'Effective Java', 25400, 'zakk'),
	(4, 'Bootstrap', 21000, 'zakk'),
	(5, 'AngularJS', 17000, 'zakk'),
	(6, 'Spring Boot', 25000, 'zakk'),
	(7, 'Effective Java', 28000, 'zakk');
/*!40000 ALTER TABLE `PRODUCT_ITEMS` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
