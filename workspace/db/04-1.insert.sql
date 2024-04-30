-- 작업 데이터베이스 선택
USE market_db;

SHOW TABLES;

DESC member;

-- 테이블에 데이터 삽입
-- 형식 : INSERT INTO 테이블이름 ( 컬럼 목록 ) VALUES ( 값 목록 )
--       INSERT INTO 테이블이름 VALUES ( 값 목록 ) 
--       INSERT INTO 테이블이름 ( 컬럼 목록 ) VALUES ( 값 목록 ), ( 값 목록 ), ( 값 목록 )

-- member 테이블에 데이터 삽입 1
select * from member limit 1;  
INSERT INTO member (mem_id, mem_name, mem_number, addr, phone1, phone2, height, debut_date)
VALUES ('NJS', '뉴진스', 5, '서울', '02', '12345678', 165, '2022-07-22');

SELECT * FROM member;

-- member 테이블에 데이터 삽입 2 : 모든 컬럼을 순서대로 사용하는 경우 컬럼 목록 생략 가능
INSERT INTO member
VALUES ('ASP', '에스파', 4, '부산', '051', '15649237', 170, '2020-11-17');

SELECT * FROM member;

-- member 테이블에 데이터 삽입 3 : 일부 컬럼의 데이터만 삽입 ( NULL 허용된 컬럼만 생략 가능 )
INSERT INTO member (mem_id, mem_name, mem_number)
VALUES ('LSF', '르세라핌', 5); -- 오류 : NULL을 허용하지 않는 컬럼(addr)이 누락

INSERT INTO member (mem_id, mem_name, mem_number, addr)
VALUES ('LSF', '르세라핌', 5, '대전');

SELECT * FROM member;

-- buy 테이블에 데이터 삽입
desc buy;

