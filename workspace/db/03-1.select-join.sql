-- market_db 데이터베이스로 전환
USE market_db;

select * from buy;

-- 1. 걸그룹 정보와 구매 정보를 결합해서 조회

-- SELECT *
SELECT 
	-- mem_id, -- mem_id가 member.mem_id인지 buy.mem_id 인지 구분 불가능
    member.mem_id,
    mem_name, prod_name, price, amount
FROM member
INNER JOIN buy
ON member.mem_id = buy.mem_id;

SELECT 
    m.mem_id,
    m.mem_name, b.prod_name, b.price, b.amount
FROM member m -- m은 member 테이블 이름의 별칭
INNER JOIN buy b -- b는 buy 테이블 이름의 별칭
ON m.mem_id = b.mem_id
ORDER BY m.mem_id;

-- 2. 모든 걸그룹의 구매 내역 조회 ( 구매 내역이 없는 걸그룹도 조회에 포함 )
SELECT 
    m.mem_id 아이디,
    m.mem_name 이름, 
    IFNULL(b.prod_name, '') 제품이름, -- NULL일 경우 대체값 사용하도록 지정
    IFNULL(b.price, 0) 가격, 
    IFNULL(b.amount, 0) 수량
FROM member m -- m은 member 테이블 이름의 별칭
LEFT OUTER JOIN buy b -- b는 buy 테이블 이름의 별칭
ON m.mem_id = b.mem_id
ORDER BY m.mem_id;

-- 3. FULL OUTER JOIN : LEFT OUTER JOIN + RIGHT OUTER JOIN

-- 4. CROSS JOIN
SELECT m.mem_id, b.num
FROM member m
CROSS JOIN buy b;

-- 5. 


