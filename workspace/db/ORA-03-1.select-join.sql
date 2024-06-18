-- JOIN

-- 주문 정보 조회
SELECT * FROM ORDERS;

-- 고객이름 + 주문정보 조회
SELECT O.ORDERID, O.CUSTID, C.NAME, O.BOOKID, O.SALEPRICE, O.ORDERDATE
FROM CUSTOMER C, ORDERS O
WHERE C.CUSTID = O.CUSTID;

SELECT O.ORDERID, O.CUSTID, C.NAME, O.BOOKID, O.SALEPRICE, O.ORDERDATE
FROM CUSTOMER C
INNER JOIN ORDERS O ON C.CUSTID = O.CUSTID;

-- 고객이름 + 도서제목 + 주문정보 조회
SELECT O.ORDERID, O.CUSTID, C.NAME, O.BOOKID, B.BOOKNAME, O.SALEPRICE, O.ORDERDATE
FROM CUSTOMER C, ORDERS O, BOOK B
WHERE C.CUSTID = O.CUSTID AND O.BOOKID = B.BOOKID;

SELECT O.ORDERID, O.CUSTID, C.NAME, O.BOOKID, B.BOOKNAME, O.SALEPRICE, O.ORDERDATE
FROM CUSTOMER C 
INNER JOIN ORDERS O ON C.CUSTID = O.CUSTID
INNER JOIN BOOK B ON O.BOOKID = B.BOOKID;

-- 모든 고객의 고객별 총주문액, 총주문건수 조회
SELECT C.CUSTID, C.NAME, SUM(O.SALEPRICE) 주문총액, COUNT(O.ORDERID) 주문건수
FROM CUSTOMER C, ORDERS O
WHERE C.CUSTID = O.CUSTID
GROUP BY C.CUSTID, C.NAME
ORDER BY C.CUSTID;

SELECT C.CUSTID, C.NAME, NVL(SUM(O.SALEPRICE), 0) 주문총액, COUNT(O.ORDERID) 주문건수
FROM CUSTOMER C, ORDERS O
WHERE C.CUSTID = O.CUSTID(+) -- LEFT OUTER JOIN ( ORACLE 고유 구문 )
GROUP BY C.CUSTID, C.NAME
ORDER BY C.CUSTID;

SELECT C.CUSTID, C.NAME, NVL(SUM(O.SALEPRICE), 0) 주문총액, COUNT(O.ORDERID) 주문건수
FROM CUSTOMER C
LEFT OUTER JOIN ORDERS O ON C.CUSTID = O.CUSTID 
GROUP BY C.CUSTID, C.NAME
ORDER BY C.CUSTID;






