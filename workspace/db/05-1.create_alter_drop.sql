-- 작업 데이터베이스 선택
USE market_db;

-- 테이블 만들기
-- 형식 : CREATE TABLE 테이블이름 (
-- 			컬럼이름1 자료형 [ 속성/제약조건 ],
-- 			컬럼이름2 자료형 [ 속성/제약조건 ],
-- 			컬럼이름3 자료형 [ 속성/제약조건 ],
-- 			...
--       )

-- member2 테이블 만들기 1 : 컬럼명, 자료형만 사용
DESC member;
select * from member;

DROP TABLE IF EXISTS member2;

CREATE TABLE member2 (
	mem_id char(3),
    mem_name varchar(10),
    mem_number int,
    addr varchar(10),
    phone1 varchar(3),
    phone2 varchar(8),
    height smallint,
    debut_date date
);

DESC member2;

-- member2 테이블 만들기 2 : 컬럼명, 자료형, NULL 허용 속성 사용
DESC member;

DROP TABLE IF EXISTS member2;

CREATE TABLE member2 (
	mem_id char(3) 			NOT NULL,
    mem_name varchar(10) 	NOT NULL,
    mem_number int 			NOT NULL,
    addr varchar(10)		NOT NULL,
    phone1 varchar(3)		NULL,
    phone2 varchar(8)		, -- DEFAULT NULL
    height smallint			,
    debut_date date
);

DESC member2;

-- member2 테이블 만들기 3-1 : 컬럼명, 자료형, NULL 허용 속성, PK 사용
DESC member;

DROP TABLE IF EXISTS member2;

CREATE TABLE member2 (
	mem_id char(3) 			NOT NULL PRIMARY KEY,
    mem_name varchar(10) 	NOT NULL,
    mem_number int 			NOT NULL,
    addr varchar(10)		NOT NULL,
    phone1 varchar(3)		NULL,
    phone2 varchar(8)		, -- DEFAULT NULL
    height smallint			,
    debut_date date
);

DESC member2;

-- member2 테이블 만들기 3-2 : 컬럼명, 자료형, NULL 허용 속성, PK 사용
DESC member;

DROP TABLE IF EXISTS member2;

CREATE TABLE member2 (
	mem_id char(3) 			NOT NULL,
    mem_name varchar(10) 	NOT NULL,
    mem_number int 			NOT NULL,
    addr varchar(10)		NOT NULL,
    phone1 varchar(3)		NULL,
    phone2 varchar(8)		, -- DEFAULT NULL
    height smallint			,
    debut_date date			,
    -- PRIMARY KEY (mem_id)	-- 기본키 지정 ( 자동으로 생성되는 PK 이름 사용 )
    CONSTRAINT pk_member2 PRIMARY KEY (mem_id)	-- 기본키 지정 ( 사용자 지정 PK 이름 )
);

DESC member2;

-- member2 테이블 만들기 4 : 컬럼명, 자료형, NULL 허용 속성, PK, DEFAULT 사용
DESC member;

DROP TABLE IF EXISTS member2;

CREATE TABLE member2 (
	mem_id char(3) 			NOT NULL,
    mem_name varchar(10) 	NOT NULL,
    mem_number int 			NOT NULL,
    addr varchar(10)		NOT NULL,
    phone1 varchar(3)		NULL,
    phone2 varchar(8)		, -- DEFAULT NULL
    height smallint			,
    debut_date date			DEFAULT ( NOW() ),
    -- PRIMARY KEY (mem_id)	-- 기본키 지정 ( 자동으로 생성되는 PK 이름 사용 )
    CONSTRAINT pk_member2 PRIMARY KEY (mem_id)	-- 기본키 지정 ( 사용자 지정 PK 이름 )
);

DESC member2;

INSERT INTO member2 (mem_id, mem_name, mem_number, addr)
VALUES ('ABC', '새걸그룹', 5, '서울');
SELECT * FROM member2;

SELECT NOW();



