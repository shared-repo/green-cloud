-- root 계정으로 실행

-- 1. 사용자 계정 만들기
-- CREATE USER green_cloud IDENTIFIED BY "oracle";

-- 2. 데이터베이스 만들기
CREATE DATABASE demoweb;

-- 3. 권한 부여
-- GRANT CONNECT, RESOURCE TO green_cloud;

-- green_cloud 계정으로 실행

-- 4. MEMBER 테이블 만들기

CREATE TABLE member 
(
    memberid VARCHAR2 (20) PRIMARY KEY
    , passwd VARCHAR2 (100) NOT NULL
    , email VARCHAR2 (50) NOT NULL
    , usertype VARCHAR2 (50) DEFAULT ('user') CHECK ( usertype IN ('user', 'admin'))
    , regdate DATE DEFAULT (NOW())
    , active CHAR(1) DEFAULT ('1')
);

-- 5. 설정 데이터 저장 테이블 만들기
CREATE TABLE app_settings
(
	setting_name varchar2(100) primary key,
    setting_value varchar2(100) not null
);

INSERT INTO app_settings VALUES ("total_counter", "0");

-- 6. board 테이블 만들기
CREATE SEQUENCE BOARD_SEQUENCE NOCACHE;

CREATE TABLE board
(
    boardno NUMBER PRIMARY KEY
    , title VARCHAR2(100) NOT NULL
    , writer VARCHAR2 (20) NOT NULL
    , content VARCHAR2 (4000) NOT NULL
    , writedate DATE DEFAULT (NOW())
    , modifydate DATE DEFAULT (NOW())
    , readcount NUMBER DEFAULT (0)
    , deleted CHAR(1) DEFAULT ('0')

    , CONSTRAINT fk_board_to_member FOREIGN KEY (writer) REFERENCES member (memberid)
);

-- 7. boardattach(첨부파일) 테이블 만들기
CREATE SEQUENCE BOARDATTACH_SEQUENCE NOCACHE;

CREATE TABLE boardattach
(
    attachno NUMBER PRIMARY KEY
    , boardno NUMBER NOT NULL
    , userfilename VARCHAR2 (100) NOT NULL
    , savedfilename VARCHAR2 (100) NOT NULL
    , downloadcount NUMBER DEFAULT (0)

    , CONSTRAINT fk_boardattach_to_board FOREIGN KEY (boardno) REFERENCES board (boardno)
);

-- 8. boardcomment(댓글) 테이블 만들기
CREATE SEQUENCE BOARDCOMMENT_SEQUENCE NOCACHE;
CREATE TABLE boardcomment
(
    commentno NUMBER PRIMARY KEY
    , boardno NUMBER NOT NULL
    , writer VARCHAR2 (20) NOT NULL
    , content VARCHAR2 (1000) NOT NULL
    , regdate DATE DEFAULT (NOW())
    , deleted CHAR(1) DEFAULT ('0')

    , groupno NUMBER NOT NULL
    , step NUMBER NOT NULL
    , depth NUMBER NOT NULL

    , CONSTRAINT fk_boardcomment_to_board FOREIGN KEY (boardno) REFERENCES board (boardno)
    , CONSTRAINT fk_boardcomment_to_member FOREIGN KEY (writer) REFERENCES member (memberid)
);