-- madang_db로 작업 데이터베이스 변경
USE madang_db;

-- 1. 고객별 (고객이름 같이 조회) 구매액 합계 ( customer, orders )
SELECT c.custid, c.name, SUM(o.saleprice)
FROM customer c
LEFT OUTER JOIN orders o
ON c.custid = o.custid
GROUP BY c.custid, c.name;

-- 2. 고객아이디, 고객이름, 도서명, 주문 정보 ( customer, book, orders )
SELECT c.custid, c.name, b.bookname, o.*
FROM customer c
INNER JOIN orders o
ON c.custid = o.custid
INNER JOIN book b
ON o.bookid = b.bookid
ORDER BY c.custid;

SELECT c.custid, c.name, b.bookname, o.*
FROM customer c
INNER JOIN orders o
INNER JOIN book b
ON o.bookid = b.bookid and c.custid = o.custid
ORDER BY c.custid;

-- 3.  박지성 고객이 구매한 도서의 출판사 수 ( customer, orders, book )

-- 4.  박지성 고객이 구매한 도서의 이름, 가격, 정가와 판매가격의 차이 ( customer, orders, book )

-- 5. 고객의 이름과 고객이 구매한 도서 목록 ( customer, orders, book )