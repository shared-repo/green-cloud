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

-- 6. 설정 데이터 저장 테이블 만들기
CREATE TABLE app_settings
(
	setting_name varchar(100) primary key,
    setting_value varchar(100) not null
);

INSERT INTO app_settings VALUES ("total_counter", "0");

-- 7. board 테이블 만들기
CREATE TABLE board
(
    boardno INT PRIMARY KEY AUTO_INCREMENT
    , title VARCHAR(100) NOT NULL
    , writer VARCHAR (20) NOT NULL
    , content VARCHAR (4000) NOT NULL
    , writedate DATE DEFAULT (NOW())
    , modifydate DATE DEFAULT (NOW())
    , readcount INT DEFAULT (0)
    , deleted BOOLEAN DEFAULT (FALSE)

    , CONSTRAINT fk_board_to_member FOREIGN KEY (writer) REFERENCES member (memberid)
);

-- 8. boardattach(첨부파일) 테이블 만들기
CREATE TABLE boardattach
(
    attachno INT PRIMARY KEY AUTO_INCREMENT
    , boardno INT NOT NULL
    , userfilename VARCHAR (100) NOT NULL
    , savedfilename VARCHAR (100) NOT NULL
    , downloadcount INT DEFAULT (0)

    , CONSTRAINT fk_boardattach_to_board FOREIGN KEY (boardno) REFERENCES board (boardno)
);

-- 9. boardcomment(댓글) 테이블 만들기
CREATE TABLE boardcomment
(
    commentno INT PRIMARY KEY AUTO_INCREMENT
    , boardno INT NOT NULL
    , writer VARCHAR (20) NOT NULL
    , content VARCHAR (1000) NOT NULL
    , writedate DATE DEFAULT (NOW())
    , modifydate DATE DEFAULT (NOW())
    , deleted BOOLEAN DEFAULT (FALSE)

    , groupno INT NOT NULL
    , step INT NOT NULL
    , depth INT NOT NULL

    , CONSTRAINT fk_boardcomment_to_board FOREIGN KEY (boardno) REFERENCES board (boardno)
    , CONSTRAINT fk_boardcomment_to_member FOREIGN KEY (writer) REFERENCES member (memberid)
);