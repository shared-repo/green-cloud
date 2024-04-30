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

desc buy; -- 자동 증가 컬럼 확인 auto_increment, 외래키(Foreign Key) 확인
select * from buy;

-- buy 테이블에 데이터 삽입 1 : 자동 증가 컬럼은 insert 컬럼 목록에서 제외
INSERT INTO buy (mem_id, prod_name, group_name, price, amount)
VALUES ( 'LSF', '아이패드', '디지털', 70, 5 );

SELECT * FROM buy;

-- buy 테이블에 데이터 삽입 2 : 외래키 컬럼의 데이터는 관계를 맺고 있는 테이블에 존재하는 데이터만 삽입 가능
INSERT INTO buy (mem_id, prod_name, group_name, price, amount)
-- VALUES ( 'MJX', '아이패드프로', '디지털', 150, 5 ); -- 오류 : 관계있는 테이블에 없는 데이터 사용
VALUES ( 'NJS', '아이패드프로', '디지털', 150, 5 );

SELECT * FROM buy;

-- buy 테이블에 데이터 삽입 3 : 여러 행의 데이터를 일괄 삽입 
INSERT INTO buy (mem_id, prod_name, group_name, price, amount)
VALUES ( 'ASP', '혼공자바', '도서', 3, 4 ),
	   ( 'ASP', '혼공SQL', '도서', 3, 4 ),
       ( 'ASP', '웹프론트엔드', '도서', 3, 4 );

SELECT * FROM buy;

-- 테이블 수정 형식 : UPDATE 테이블이름 
-- 	    		   SET 컬럼1 = 값1, 컬럼2 = 값2, ... 
-- 				   WHERE 조건식 ( 선택적 ... 필수라고 생각하고 작성 )
-- 

-- member 테이블 수정
UPDATE member
SET phone1 = '042', phone2 = '56479532'
WHERE mem_id = 'LSF';

SELECT * FROM member;

-- 데이터 삭제 형식 : DELETE FROM 테이블이름
--                 WHERE 조건식 ( 선택적 ... 필수라고 생각하고 작성 )

-- member 테이블 데이터 삭제 1
DELETE FROM member
WHERE mem_id = 'LSF'; -- 오류 : 이 데이터를 참조하는 관계있는 테이블의 데이터가 있으면 삭제 불가

-- member 테이블 데이터 삭제 2
DELETE FROM buy
WHERE mem_id = 'LSF'; -- 참조하고 있는 자식데이터 먼저 삭제하고

DELETE FROM member
WHERE mem_id = 'LSF'; -- 부모 데이터 삭제 가능

SELECT * FROM member;

-- 참고 : 
INSERT INTO buy (mem_id, prod_name, group_name, price, amount)
VALUES ( 'ASP', '아이패드프로', '디지털', 150, 5 );

select * from buy;







