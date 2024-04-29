-- 작업 데이터베이스 선택
USE madang_db;

-- 가장 비싼 도서의 이름과 가격 조회
SELECT MAX(price)
FROM book;

SELECT bookname, price
FROM book
WHERE price = 35000; -- 위에서 조회한 가장 비싼 가격

SELECT bookname, price
FROM book
WHERE price = ( 
				SELECT MAX(price)
			    FROM book 
			  );
              
-- 도서 구매 실적이 있는 고객 조회
SELECT DISTINCT c.*
FROM customer c
INNER JOIN orders o
ON c.custid = o.custid;

SELECT *
FROM customer
WHERE custid IN (
					SELECT DISTINCT custid
                    FROM orders
				);
                
-- 대한미디어에서 출간한 도서를 구매한 고객 정보 조회
SELECT c.*
FROM customer c
INNER JOIN orders o
ON c.custid = o.custid
INNER JOIN book b
ON o.bookid = b.bookid
WHERE b.publisher = '대한미디어';

SELECT *
FROM customer c
WHERE c.custid = (
					SELECT o.custid
                    FROM orders o
                    WHERE o.bookid IN (
										SELECT b.bookid
                                        FROM book b
                                        WHERE b.publisher = '대한미디어'
									  )
				 );
                 
-- 출판사별로 출간한 도서의 평균가격보다 비싼 도서 조회
SELECT * FROM book;

SELECT *
FROM book b
WHERE b.price > (
					SELECT AVG(b2.price)
                    FROM book b2
                    WHERE b2.publisher = b.publisher -- main query의 데이터를 sub query에서 사용
				);
                
-- 고객별 판매액 조회
SELECT c.custid, c.name, SUM(saleprice) 판매액
FROM customer c
INNER JOIN orders o
ON c.custid = o.custid
GROUP BY c.custid, c.name;

SELECT 
	o.custid 아이디, 
	( SELECT c.name FROM customer c WHERE c.custid = o.custid ) 이름,
	SUM(o.saleprice) 판매액
FROM orders o
GROUP BY o.custid;

-- 고객번호가 2 이하인 고객별 판매액 조회
SELECT c2.custid, c2.name, SUM(o.saleprice)
FROM (
		SELECT c.custid, c.name 
        FROM customer c
        WHERE c.custid <= 2
	 ) c2
INNER JOIN orders o
ON c2.custid = o.custid
GROUP BY c2.custid, c2.name;


 
                
                
                
                
                