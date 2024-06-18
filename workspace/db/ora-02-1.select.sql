-- hr 계정으로 실행

-- 1. 테이블 목록 조회 
select * from tab;

-- green_cloud 계정으로 실행
-- connect green_cloud/oracle;

-- 2. 테이블 목록 조회
SELECT * FROM tab; -- mysql : show tables;

-- 3. 테이블 정보 조회
DESC BOOK;
DESC CUSTOMER;

-- 4. 모든 도서의 이름과 가격 조회
SELECT BOOKNAME, PRICE
FROM BOOK;

-- 5. 도서의 출판사 정보 조회
SELECT PUBLISHER
FROM BOOK;

SELECT DISTINCT PUBLISHER
FROM BOOK;

-- 6. 가격이 20000원 이상인 도서 조회
SELECT * 
FROM BOOK
WHERE PRICE >= 20000;

-- 6. 가격이 10000원 이상 20000원 이하인 도서 조회
SELECT * 
FROM BOOK
-- WHERE PRICE >= 10000 AND PRICE <= 20000;
WHERE PRICE BETWEEN 10000 AND 20000;

-- 7. '나무수'와 '삼성당' 출판사에서 출간한 도서
SELECT * 
FROM BOOK
-- WHERE PUBLISHER = '나무수' OR PUBLISHER = '삼성당';
-- WHERE PUBLISHER IN ('나무수', '삼성당');
WHERE PUBLISHER NOT IN ('나무수', '삼성당'); -- 참고 (반대 조건 조회)

-- 8. 축구 관련 도서 조회
SELECT *
FROM BOOK
-- WHERE BOOKNAME LIKE '%축구%';
WHERE BOOKNAME NOT LIKE '%축구%'; -- 참고 (반대 조건 조회)

-- 9. 비싼 가격순으로 도서 조회
SELECT *
FROM BOOK
-- ORDER BY PRICE DESC;
ORDER BY PRICE DESC, PUBLISHER ASC;

-- 10. 도서의 평균 가격, 최고 가격, 최저 가격 조회
SELECT AVG(PRICE) "평균가격", MAX(PRICE) 최고가격, MIN(PRICE) 최저가격
FROM BOOK;

-- 11. 도서의 총 판매액
SELECT SUM(SALEPRICE) 총판매액
FROM ORDERS;

-- 12. 고객별 도서 구매 정보
SELECT O.CUSTID, SUM(SALEPRICE) 총구매액, COUNT(*) 총구매건수
FROM ORDERS O
GROUP BY O.CUSTID;

-- 13. 출판사별 도서 평균 가격
SELECT PUBLISHER, AVG(PRICE) 평균가격
FROM BOOK
GROUP BY PUBLISHER;

-- 14. 평균 구매액이 10000원 이상인 고객의 고객별 평균 구매액 조회
--SELECT CUSTID, AVG(SALEPRICE) 평균구매액
--FROM ORDERS
--WHERE AVG(SALEPRICE) >= 10000
--GROUP BY CUSTID;

SELECT  CUSTID, 
        ROUND( AVG(SALEPRICE)) 평균구매액1,
        ROUND( AVG(SALEPRICE), 2) 평균구매액2,
        ROUND( AVG(SALEPRICE), -2) 평균구매액3
FROM ORDERS
GROUP BY CUSTID
HAVING AVG(SALEPRICE) >= 10000;





