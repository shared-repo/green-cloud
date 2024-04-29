-- employees db에서 아래와 같은 SQL 문 작성
USE employees;

-- 1. 사원 정보 조회 (employees 테이블의 모든 정보, 현재 부서이름, 현재 직급)
select * from titles;

SELECT E.*, D.DEPT_NAME, T.TITLE
FROM EMPLOYEES E
INNER JOIN DEPT_EMP DE
ON E.EMP_NO = DE.EMP_NO
INNER JOIN DEPARTMENTS D
ON D.DEPT_NO = DE.DEPT_NO
INNER JOIN TITLES T
ON E.EMP_NO = T.EMP_NO
WHERE T.TO_DATE = '9999-01-01' AND DE.TO_DATE = '9999-01-01'
ORDER BY E.EMP_NO;

SELECT E.*, D.DEPT_NAME, T.TITLE
FROM EMPLOYEES E, DEPARTMENTS D, DEPT_EMP DE, TITLES T
WHERE E.EMP_NO = DE.EMP_NO AND D.DEPT_NO = DE.DEPT_NO AND E.EMP_NO = T.EMP_NO
	  AND
      T.TO_DATE = '9999-01-01' AND DE.TO_DATE = '9999-01-01'
ORDER BY E.EMP_NO;

-- 2. 현재 개발부서(Development)에 근무하는 직원 정보 조회
SELECT E.*
FROM EMPLOYEES E, DEPARTMENTS D, DEPT_EMP DE
WHERE E.EMP_NO = DE.EMP_NO AND D.DEPT_NO = DE.DEPT_NO
	  AND DE.TO_DATE = '9999-01-01'
      AND D.DEPT_NAME = 'Development';
      
-- 3. 현재 급여가 50000 ~ 60000인 직원 조회
SELECT E.*, S.SALARY
FROM EMPLOYEES E, SALARIES S
WHERE E.EMP_NO = S.EMP_NO AND 
	  S.TO_DATE = '9999-01-01' AND
      -- S.SALARY BETWEEN 50000 AND 60000;
      S.SALARY >= 50000 AND S.SALARY < 60000;

-- 4. 부서별 직원의 평균 급여 조회 
SELECT DE.DEPT_NO, D.DEPT_NAME, AVG(S.SALARY) 평균급여
FROM DEPARTMENTS D, DEPT_EMP DE, SALARIES S
WHERE D.DEPT_NO = DE.DEPT_NO AND DE.EMP_NO = S.EMP_NO AND
	  S.TO_DATE = '9999-01-01'
GROUP BY DE.DEPT_NO, D.DEPT_NAME
ORDER BY AVG(S.SALARY);

-- 5. 직급별 직원의 평균 급여
SELECT T.TITLE, AVG(S.SALARY) 평균급여
FROM TITLES T, SALARIES S
WHERE T.EMP_NO = S.EMP_NO AND
	  T.TO_DATE = '9999-01-01' AND S.TO_DATE = '9999-01-01'
GROUP BY T.TITLE
ORDER BY AVG(S.SALARY);

-- 6. 성별 평균 급여 조회
SELECT E.GENDER, AVG(S.SALARY) 평균급여
FROM EMPLOYEES E, SALARIES S
WHERE E.EMP_NO = S.EMP_NO AND
	  S.TO_DATE = '9999-01-01'
GROUP BY E.GENDER;

-- 7. 근무했던 부서가 2 이상인 직원 조회 ( 부서 이동이 있었던 직원 조회 )
-- SELECT E.EMP_NO, E.FIRST_NAME, E.LAST_NAME, COUNT(*) 근무부서수
SELECT E.EMP_NO, CONCAT(E.FIRST_NAME, ' ', E.LAST_NAME) NAME, COUNT(*) 근무부서수
FROM EMPLOYEES E, DEPT_EMP DE
WHERE E.EMP_NO = DE.EMP_NO
GROUP BY E.EMP_NO
HAVING COUNT(*) >= 2;

-- ------------------------------------------------

-- madang_db에서 아래의 SQL문 작성 
use madang_db;

-- 9. 평균 주문금액 이하의 주문에 대해서 주문번호와 금액을 보이시오
select avg(saleprice) from orders; -- 평균 주문 금액

select *
from orders o
where o.saleprice <= ( select avg(saleprice) 
					   from orders );

-- 10. 각 고객의 평균 주문금액보다 큰 금액의 주문 내역에 대해서 
--      주문번호, 고객번호, 금액을 보이시오
select *
from orders o
-- where o.saleprice > 현재 처리중인 행의 고객 아이디로 주문한 구매가격의 평균
where o.saleprice > ( select avg(o2.saleprice)
                      from orders o2 
                      where o.custid = o2.custid );
                      
-- 11. 대한민국에 거주하는 고객에게 판매한 도서의 총판매액을 구하시오
select * from customer where address like '대한민국%';

select sum(saleprice) 대한민국_총판매액
from orders o
where o.custid in ( select c.custid 
                    from customer c 
                    where c.address like '대한민국%' );

-- 12. 3번 고객이 주문한 도서의 최고 금액보다 
--     더 비싼 도서를 구입한 주문의 주문번호와 금액을 보이시오
select max(saleprice) from orders where custid = 3;

select *
from orders o
where o.saleprice > ( select max(o2.saleprice) 
                      from orders o2 
                      where o2.custid = 3 ); 

-- 13. EXISTS 연산자로 대한민국에 거주하는 고객에게 판매한 도서의 총 판매액을 구하시오
--     EXISTS 연산자는 값을 비교하는 연산자가 아님 --> 존재 여부만 확인 ( 결과가 있으면 TRUE, 없으면 FALSE )

select sum(saleprice) 대한민국_총판매액
from orders o
-- where exists ( select c.custid 
where exists ( select 1 -- 1을 사용해서 값의 내용이 아니라 존재 여부만 확인하는 것을 강조
               from customer c 
               where c.custid = o.custid and c.address like '대한민국%' );
               
select sum(saleprice) 대한민국_총판매액
from orders o
where o.custid in ( select c.custid 
					from customer c 
					where c.address like '대한민국%' );
               
-- 14. 마당서점의 고객별 판매액을 보이시오(결과는 고객이름과 고객별 판매액을 출력).
select c.name, sum(o.saleprice)
from customer c, orders o
where c.custid = o.custid
group by c.name;

select 
    ( select c.name
      from customer c
      where c.custid = o.custid ) name,
    sum(o.saleprice)
from orders o
group by o.custid;

-- 15. Orders 테이블에 각 주문에 맞는 도서이름을 조회하시오
select o.*, (select b.bookname from book b where o.bookid = b.bookid) bookname
from orders o;

select o.*, b.bookname
from orders o, book b
where o.bookid = b.bookid;

-- 17. 도서의 판매액 평균보다 자신의 구매액 평균이 더 높은 고객의 이름
select distinct c.name
from customer c, orders o
where c.custid = o.custid
      and 
      (select avg(o3.saleprice) from orders o3 where o3.custid = c.custid) > 
      (select avg(o2.saleprice) from orders o2);

















