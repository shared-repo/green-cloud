-- SUBQUERY

-- 도서 구매 이력이 있는 고객 조회
SELECT DISTINCT C.*
FROM CUSTOMER C
INNER JOIN ORDERS O ON C.CUSTID = O.CUSTID;

SELECT *
FROM CUSTOMER C
WHERE C.CUSTID IN ( SELECT DISTINCT CUSTID FROM ORDERS );

-- 대한미디어에서 출간한 도서를 구매한 고객 조회
SELECT C.*
FROM CUSTOMER C
INNER JOIN ORDERS O ON C.CUSTID = O.CUSTID
INNER JOIN BOOK B ON O.BOOKID = B.BOOKID
WHERE B.PUBLISHER = '대한미디어';

SELECT C.*
FROM CUSTOMER C
WHERE C.CUSTID IN ( SELECT O.CUSTID
                    FROM ORDERS O
                    WHERE O.BOOKID IN ( SELECT B.BOOKID
                                        FROM BOOK B
                                        WHERE B.PUBLISHER = '대한미디어' ) );
                                        
-- 도서 정보 조회하되 
-- 도서 가격이 그 도서를 출간한 출판사의 모든 도서의 평균 가격보다 높은 도서만 조회
SELECT *
FROM BOOK B
WHERE B.PRICE > ( SELECT AVG(B2.PRICE)
                  FROM BOOK B2 
                  WHERE B2.PUBLISHER = B.PUBLISHER );








