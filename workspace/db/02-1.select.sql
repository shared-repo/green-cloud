
SHOW DATABASES; -- 현재 서버에 포함된 데이터베이스 목록 조회

USE market_db; -- 작업 db 선택 : 이후에 실행되는 SQL은 market_db를 대상으로 합니다.

SHOW TABLES; -- 현재 작업데이터베이스 포함된 테이블 목록 조회

DESC member; -- 지정된 테이블 정보 조회

-- 1. 데이터 조회 기본 구문 : select 컬럼목록 from 테이블이름
-- member 테이블의 모든 데이터 조회
SELECT mem_id, mem_name, mem_number, addr, phone1, phone2, height, debut_date
FROM member;

-- member 테이블의 모든 데이터 조회 ( 컬럼 순서 변경 )
SELECT mem_number, mem_id, mem_name, phone1, phone2, addr, debut_date, height
FROM member;

-- member 테이블의 모든 데이터 조회 ( wild card 사용 )
SELECT *
FROM member;

-- member 테이블에서 아이디, 이름, 키 정보 조회 ( 일부 컬럼 조회 )
SELECT mem_id, mem_name, height
FROM member;

SELECT mem_id, mem_id, mem_name, height
FROM member;

-- 조회된 데이터의 컬럼 이름 변경 ( ALIAS, AS는 선택적 )
SELECT mem_id AS 아이디, mem_name 이름, height 키
FROM member;

-- buy 테이블의 모든 컬럼 모든 데이터 조회
SELECT *
FROM buy;

-- 연산 결과 조회 : buy 테이블의 제품이름, 분류, 가격, 수량, 구매총액 조회
SELECT prod_name, group_name, price, amount, price * amount 구매총액
FROM buy;

-- 회원의 거주지역 중복 제거하고 조회
SELECT addr FROM member; -- 전체 조회
SELECT DISTINCT addr FROM member; -- 중복 제거 조회

-- 회원의 아이디, 이름, 멤버수를 죄회하되 모든 행의 끝에 '걸그룹'을 추가해서 조회
-- ( 조회 결과에 스칼라 값을 추가 )
SELECT mem_id, mem_name, mem_number, '걸그룹' 그룹구분
FROM member;

-- 조회되는 데이터가 명확하다면 (테이블에서 조회되는 것이 아니라면 ) FROM 생략 가능
SELECT NOW(); -- NOW()는 현재 시간을 반환하는 함수

-- 2. 조건이 있는 데이터 조회 구문 ( 필터링, 검색 )
--    형식 : SELECT 컬럼목록 FROM 테이블이름 WHERE 조건식

-- 키가 165 이상인 회원 조회
SELECT *
FROM member
WHERE height >= 165;

-- 멤버 수가 5인 이상인 회원 조회
SELECT *
FROM member
WHERE mem_number >= 5;

-- 이름이 트와이스인 회원 조회
SELECT *
FROM member
WHERE mem_name = '트와이스'; 	-- 문자열 데이터는 작은 따옴표로 표현
							-- 동일성 비교는 = 연산자 사용 ( == 아님 )
                            
-- 지역이 서울인 회원 조회
SELECT *
FROM member
WHERE addr = '서울'; 

-- 지역이 서울이면서 평균신장이 165 이하인 멤버 조회
SELECT *
FROM member
WHERE addr = '서울' AND height <= 165; -- 두 조건 결합할 때 AND, OR 사용

-- 평균 신장이 163 이상 167 이하인 회원 조회
SELECT *
FROM member
-- WHERE height >= 163 AND height <= 167;
WHERE height BETWEEN 163 AND 167;

-- 주소가 '경기' 또는 '전남' 또는 '경남' 인 회원 조회
SELECT *
FROM member
-- WHERE addr = '경기' OR addr = '전남' OR addr = '경남';
WHERE addr IN ('경기', '전남', '경남');

-- 이름에 '이'가 포함된 회원 조회
SELECT *
FROM member
WHERE mem_name LIKE '%이%'; -- % : 0개 이상의 모든 문자

-- 이름의 마지막에서 두 번째 문자가 '이'인 회원 조회
SELECT *
FROM member
WHERE mem_name LIKE '%이_'; -- _ : 1개의 모든 문자

-- 이름이 '크'로 끝나는 회원 조회
SELECT *
FROM member
WHERE mem_name LIKE '%크'; 

-- 2015년 이후에 데뷰한 회원 조회 ( 시간/날짜 처리에 대한 연습 )
SELECT *
FROM member
WHERE debut_date >= '2015-01-01'; -- 날짜 데이터는 작은 따옴표로 표현 

-- 2014년 ~ 2016년 사이에 데뷰한 회원 조회 ( 시간/날짜 처리에 대한 연습 )
SELECT *
FROM member
-- WHERE debut_date >= '2014-01-01' AND debut_date < '2017-01-01'; -- 날짜 데이터는 작은 따옴표로 표현 
WHERE debut_date BETWEEN '2014-01-01' AND '2016-12-31'; -- 날짜 데이터는 작은 따옴표로 표현 

-- 3. 조회 결과 정렬
--    형식 : SELECT 컬럼 목록
--          FROM 테이블 이름
--          [ WHERE 조건식 ]
--          [ ORDER BY 정렬 기준 ]

-- 회원을 데뷰일자 순으로 정렬해서 조회
SELECT *
FROM member
-- ORDER BY debut_date; -- ASC 생략 (기본값)
-- ORDER BY debut_date ASC; -- ASC : 오름차순 정렬
ORDER BY debut_date DESC; -- DESC : 내림차순 정렬

-- 회원을 지역 순으로 정렬하고 지역이 같다면 데뷰일자 순으로 정렬해서 조회
SELECT *
FROM member
-- ORDER BY addr, debut_date;
-- ORDER BY addr ASC, debut_date DESC;
ORDER BY addr DESC, debut_date ASC;

-- 4. 조회 결과에서 일부만 추출 : LIMIT
-- 회원을 데뷰일자 순으로 정렬해서 데뷰일자가 빠른 5개의 그룹 조회
SELECT *
FROM member
ORDER BY debut_date
LIMIT 5; -- 처음부터 5개의 조회 결과 가져오기

-- 회원을 데뷰일자 순으로 정렬해서 데뷰일자가 빠른 순으로 3번째에서 7번째 그룹 조회
SELECT *
FROM member
ORDER BY debut_date
LIMIT 3, 5; -- 3번째에서 7번째 그룹 조회 ( 3번째부터 5개 조회 )

-- 5-1. 집계 함수
 
-- 전체 회원수 조회
SELECT COUNT(*)
FROM member;

-- member 테이블에서 phone1의 갯수 조회
SELECT COUNT(phone1) -- null은 빼고 계산
FROM member;

-- 가장 큰 평균 신장, 가장 작은 평균 신장, 전체 평균 신장 조회
SELECT AVG(height), MIN(height), MAX(height)
FROM member;

-- 각 그룹에 포함된 전체 인원 조회
SELECT SUM(mem_number)
FROM member;

-- 5-2. 그룹별 조회 (GROUP BY)
--      형식 : SELECT 컬럼 목록
--            FROM 테이블 이름
--            [ WHERE 조건식 ]
--			  [ GROUP BY 기준 컬럼 ]
--			  [ HAVING 조건식 ]
--            [ ORDER BY 정렬 기준 ]

SELECT * FROM buy;

-- 구매 총액 조회
SELECT SUM(price * amount) "구매 총액" -- 이름에 공백이 포함되면 "" 사용
FROM buy;

-- 걸그룹별 구매 총액, 구매액 평균, 구매 건수 조회
SELECT 
	-- num, -- 오류 : group by에 지정된 컬럼만 select에 사용 가능
    mem_id, 
    SUM(price * amount) "구매 총액", -- 이름에 공백이 포함되면 "" 사용
    AVG(price * amount) "구매 평균",
    COUNT(price * amount) "구매 건수"
FROM buy
GROUP BY mem_id;

-- 제품별 판매 총액, 판매액 평균, 판매 건수 조회
SELECT 
	prod_name, 
    SUM(price*amount) 총판매액, 
    AVG(price*amount) 평균판매액, 
    COUNT(price*amount) 판매건수
FROM buy
GROUP BY prod_name;

-- 구매액합계가 1000이상인 걸그룹 조회
SELECT 
	mem_id, 
    SUM(price * amount) "구매 총액"
FROM buy
-- WHERE SUM(price * amount) >= 1000 -- 오류 : 집계함수는 GROUP BY와 함께 처리되기 때문에 WHERE 절에서 사용할 수 없습니다.
GROUP BY mem_id
HAVING SUM(price * amount) >= 1000
ORDER BY SUM(price * amount);



















