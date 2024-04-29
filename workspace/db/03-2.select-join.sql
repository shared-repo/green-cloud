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

SELECT c.custid, c.name, b.bookname, o.*
FROM customer c, orders o, book b
WHERE o.bookid = b.bookid and c.custid = o.custid
ORDER BY c.custid;

-- 3.  박지성 고객이 구매한 도서의 출판사 수 ( customer, orders, book )
SELECT COUNT(publisher) "출판사수"
FROM customer c
INNER JOIN orders o
ON c.custid = o.custid
INNER JOIN book b
ON o.bookid = b.bookid
WHERE c.name = '박지성';

SELECT COUNT(publisher) "출판사수"
FROM customer c, orders o, book b
WHERE c.name = '박지성' AND c.custid = o.custid AND o.bookid = b.bookid;

-- 4.  박지성 고객이 구매한 도서의 이름, 가격, 정가와 판매가격의 차이 ( customer, orders, book )
SELECT b.bookname, b.price, b.price - o.saleprice "할인액"
FROM customer c
INNER JOIN orders o
ON c.custid = o.custid
INNER JOIN book b
ON o.bookid = b.bookid
WHERE c.name = '박지성';

SELECT b.bookname, b.price, b.price - o.saleprice "할인액"
FROM customer c, orders o, book b
WHERE c.name = '박지성' AND c.custid = o.custid AND o.bookid = b.bookid;

-- 5. 고객의 이름과 고객이 구매한 도서 목록 ( customer, orders, book )
SELECT c.name, b.*
FROM customer c
INNER JOIN orders o
ON c.custid = o.custid
INNER JOIN book b
ON o.bookid = b.bookid
ORDER BY c.name, b.bookid;

SELECT c.name, b.*
FROM customer c, orders o, book b
WHERE c.custid = o.custid AND o.bookid = b.bookid
ORDER BY c.name, b.bookid;









