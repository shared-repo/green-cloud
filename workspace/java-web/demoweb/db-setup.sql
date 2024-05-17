-- root 계정으로 실행

-- 1. 사용자 계정 만들기
-- CREATE USER green_cloud@localhost IDENTIFIED BY "mysql";
-- CREATE USER green_cloud@"%" IDENTIFIED BY "mysql";

-- 2. 데이터베이스 만들기
CREATE DATABASE demoweb;

-- 3. 권한 부여
GRANT ALL PRIVILEGES ON demoweb.* TO green_cloud@localhost;
GRANT ALL PRIVILEGES ON demoweb.* TO green_cloud@"%";

FLUSH PRIVILEGES;

-- green_cloud 계정으로 실행

-- 4. 데이터베이스 선택
USE demoweb;

-- 5. MEMBER 테이블 만들기

CREATE TABLE member 
(
    memberid VARCHAR (20) PRIMARY KEY
    , passwd VARCHAR (100) NOT NULL
    , email VARCHAR (50) NOT NULL
    , usertype VARCHAR (50) DEFAULT ('user') CHECK ( usertype IN ('user', 'admin'))
    , regdate DATETIME DEFAULT (NOW())
    , active BOOLEAN DEFAULT (TRUE)
);