--DROP TABLE BOARDCOMMENT;
--DROP TABLE BOARDATTACH;
--DROP TABLE BOARD;
--DROP TABLE MEMBER;
--DROP TABLE APP_SETTINGS;
--DROP SEQUENCE BOARD_SEQUENCE;
--DROP SEQUENCE BOARDATTACH_SEQUENCE;
--DROP SEQUENCE BOARDCOMMENT_SEQUENCE;

-- 4. MEMBER 테이블 만들기
CREATE TABLE member 
(
    memberid VARCHAR2 (20) PRIMARY KEY
    , passwd VARCHAR2 (100) NOT NULL
    , email VARCHAR2 (50) NOT NULL
    , usertype VARCHAR2 (50) DEFAULT ('user') CHECK ( usertype IN ('user', 'admin'))
    , regdate DATE DEFAULT (SYSDATE)
    , active CHAR(1) DEFAULT ('1') -- CHAR(1) : boolean type과 호환되는 자료형
);

-- 5. 설정 데이터 저장 테이블 만들기
CREATE TABLE app_settings
(
	setting_name varchar2(100) primary key,
    setting_value varchar2(100) not null
);

INSERT INTO app_settings VALUES ('total_counter', '0');
COMMIT;

-- 6. board 테이블 만들기
CREATE SEQUENCE BOARD_SEQUENCE NOCACHE;

CREATE TABLE board
(
    boardno NUMBER PRIMARY KEY
    , title VARCHAR2(100) NOT NULL
    , writer VARCHAR2 (20) NOT NULL
    , content VARCHAR2 (4000) NOT NULL
    , writedate DATE DEFAULT (SYSDATE)
    , modifydate DATE DEFAULT (SYSDATE)
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
    , regdate DATE DEFAULT (SYSDATE)
    , deleted CHAR(1) DEFAULT ('0')

    , groupno NUMBER NOT NULL
    , step NUMBER NOT NULL
    , depth NUMBER NOT NULL

    , CONSTRAINT fk_boardcomment_to_board FOREIGN KEY (boardno) REFERENCES board (boardno)
    , CONSTRAINT fk_boardcomment_to_member FOREIGN KEY (writer) REFERENCES member (memberid)
);