-- madang_db로 작업 데이터베이스 변경
USE madang_db;

-- (1) 도서번호가 1인 도서의 이름 ( book )
SELECT *
FROM book
WHERE bookid = 1;

-- (2) 가격이 20,000원 이상인 도서의 이름 ( book )
SELECT *
FROM book
WHERE price >= 20000;

-- (3) 박지성의 총 구매액(박지성의 고객번호는 1번으로 놓고 작성) ( orders )
SELECT *
FROM customer
WHERE name = '박지성';

SELECT SUM(saleprice) 도서구매액
FROM orders
WHERE custid = 1;

-- (4) 박지성이 구매한 도서의 수(박지성의 고객번호는 1번으로 놓고 작성) ( orders )
SELECT COUNT(*) 구매도서수
FROM orders
WHERE custid = 1;

-- (5) 도서의 총 개수 ( book )
SELECT COUNT(*)
FROM book;

-- (6) 도서를 출고하는 출판사의 총 개수 ( book )
-- SELECT COUNT(publisher) -- 중복 데이터도 포함한 개수
SELECT COUNT(DISTINCT publisher) -- 중복 데이터 제외한 개수
FROM book;

-- (7) 모든 고객의 이름, 주소 ( customer )
SELECT name, address
FROM customer;

-- (8) 2014년 7월 4일~7월 7일 사이에 주문 받은 도서의 주문번호 ( orders )
SELECT *
FROM orders
-- WHERE orderdate BETWEEN '2014-07-04' AND '2014-07-07';
WHERE orderdate >= '2014-07-04' AND orderdate <= '2014-07-07';

-- (9) 2014년 7월 4일~7월 7일 사이에 주문 받은 도서를 제외한 도서의 주문번호 ( orders )
SELECT *
FROM orders
WHERE orderdate NOT BETWEEN '2014-07-04' AND '2014-07-07';
-- WHERE orderdate < '2014-07-04' OR orderdate > '2014-07-07';

-- (10) 성이 ‘김’ 씨인 고객의 이름과 주소 ( customer )
SELECT *
FROM customer
WHERE name LIKE '김%';

-- (11) 성이 ‘김’ 씨이고 이름이 ‘아’로 끝나는 고객의 이름과 주소 ( customer )
SELECT *
FROM customer
WHERE name LIKE '김%아';