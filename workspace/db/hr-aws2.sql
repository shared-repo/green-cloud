select * from employees;
select * from departments;
select * 
from employees e, departments d
where e.department_id = d.department_id;

select * 
from employees e, departments d
where e.department_id > d.department_id;

select distinct(e.department_id)
from employees e
order by e.department_id;

select distinct(d.department_id)
from departments d
order by d.department_id;

select d.department_name, count(e.employee_id)
from employees e, departments d
-- where e.department_id (+)= d.department_id
where d.department_id = e.department_id(+)
group by d.department_name
order by d.department_name;

select d.department_name, count(e.employee_id)
from employees e
right outer join departments d
on d.department_id = e.department_id
group by d.department_name
order by d.department_name;

select d.department_name, e.employee_id
from employees e
full outer join departments d
on d.department_id = e.department_id
order by d.department_name;

create table departments2
as select * from departments;

update departments2 set manager_id = 200
where department_id > 50;
commit;

select * from departments;

select * 
from departments d1 natural join departments2 d2;

select count(*)
from employees, departments;

select count(*) from employees;
select count(*) from departments;

select 107 * 27 from dual;

select * from departments;

create or replace view dept_emp 
as
    select d.department_id, d.department_name, e.first_name, e.last_name
    from departments d, employees e
    where d.department_id(+) = e.department_id;
    
select * from dept_emp;

create or replace view small_emp
as
    select e.employee_id, e.first_name, e.last_name, e.email, d.department_name
    from employees e
    left outer join departments d
    on e.department_id = d.department_id;
    
select * from small_emp;

select * from departments where department_id between 10 and 60;
select * from departments where department_id between 40 and 100;

select * from departments where department_id between 10 and 60
union
select * from departments where department_id between 40 and 100;

select * from departments where department_id between 10 and 60
union all
select * from departments where department_id between 40 and 100;

select * from departments where department_id between 10 and 60
intersect
select * from departments where department_id between 40 and 100;

select * from departments where department_id between 10 and 60
minus
select * from departments where department_id between 40 and 100;

select * from departments where department_id between 10 and 60
union
select department_id from departments where department_id between 40 and 100;

select department_id from employees where employee_id between 101 and 105
union all
select department_id from departments where department_id between 40 and 100;

--------------------------------------------------------------------

SELECT E.DEPARTMENT_ID, ROUND(AVG(E.SALARY)) 평균급여
FROM EMPLOYEES E
GROUP BY E.DEPARTMENT_ID
ORDER BY E.DEPARTMENT_ID;

-- SELECT E.DEPARTMENT_ID, ROUND(AVG(E.SALARY)) 평균급여
SELECT E.DEPARTMENT_ID, COUNT(E.EMPLOYEE_ID) 사원수
FROM EMPLOYEES E
WHERE E.DEPARTMENT_ID IS NOT NULL
GROUP BY ROLLUP(E.DEPARTMENT_ID)
ORDER BY E.DEPARTMENT_ID;

SELECT E.DEPARTMENT_ID, E.JOB_ID, COUNT(*) 사원수
FROM EMPLOYEES E
WHERE E.DEPARTMENT_ID IS NOT NULL
-- GROUP BY ROLLUP(E.DEPARTMENT_ID, E.JOB_ID)
GROUP BY ROLLUP( (E.DEPARTMENT_ID, E.JOB_ID) )
ORDER BY E.DEPARTMENT_ID;

SELECT E.DEPARTMENT_ID, E.JOB_ID, COUNT(*) 사원수
FROM EMPLOYEES E
WHERE E.DEPARTMENT_ID IS NOT NULL
-- GROUP BY CUBE(E.DEPARTMENT_ID, E.JOB_ID)
GROUP BY CUBE( (E.DEPARTMENT_ID, E.JOB_ID) )
ORDER BY E.DEPARTMENT_ID;


SELECT E.DEPARTMENT_ID, E.JOB_ID, COUNT(*) 사원수
FROM EMPLOYEES E
WHERE E.DEPARTMENT_ID IS NOT NULL
-- GROUP BY GROUPING SETS( E.DEPARTMENT_ID, E.JOB_ID )
GROUP BY GROUPING SETS( E.DEPARTMENT_ID, E.JOB_ID, () )
ORDER BY E.DEPARTMENT_ID;

SELECT E.DEPARTMENT_ID, E.JOB_ID, COUNT(*) 사원수,
       GROUPING(E.DEPARTMENT_ID),
       GROUPING(E.JOB_ID)
FROM EMPLOYEES E
WHERE E.DEPARTMENT_ID IS NOT NULL
GROUP BY ROLLUP( E.DEPARTMENT_ID, E.JOB_ID )
ORDER BY E.DEPARTMENT_ID;

SELECT CASE GROUPING(E.DEPARTMENT_ID)
        WHEN 1 THEN 'TOTAL'
        ELSE TO_CHAR(E.DEPARTMENT_ID)
       END DEPARTMENT_ID,
       CASE GROUPING(E.JOB_ID)
        WHEN 1 THEN 'TOTAL'
        ELSE TO_CHAR(E.JOB_ID)
       END JOB_ID,
       COUNT(*) 사원수
FROM EMPLOYEES E
WHERE E.DEPARTMENT_ID IS NOT NULL
GROUP BY ROLLUP( E.DEPARTMENT_ID, E.JOB_ID )
ORDER BY E.DEPARTMENT_ID;

---------------------------------

SELECT EMPLOYEE_ID, EMAIL,
       SALARY,
       RANK() OVER(ORDER BY SALARY DESC) AS RANK1,
       DENSE_RANK() OVER(ORDER BY SALARY DESC) AS RANK2,
       ROW_NUMBER() OVER(ORDER BY SALARY DESC) AS RN    
FROM EMPLOYEES;

SELECT DEPARTMENT_ID, EMPLOYEE_ID, EMAIL,
       SALARY,
       RANK() OVER(PARTITION BY DEPARTMENT_ID ORDER BY SALARY DESC) AS RANK1,
       DENSE_RANK() OVER(PARTITION BY DEPARTMENT_ID ORDER BY SALARY DESC) AS RANK2,
       ROW_NUMBER() OVER(PARTITION BY DEPARTMENT_ID ORDER BY SALARY DESC) AS RN    
FROM EMPLOYEES
ORDER BY DEPARTMENT_ID;

SELECT EMPLOYEE_ID, EMAIL, DEPARTMENT_ID, JOB_ID,
       SUM(SALARY) OVER(PARTITION BY DEPARTMENT_ID) AS SUM,
       SUM(SALARY) OVER(PARTITION BY JOB_ID) AS SUM2,
       MIN(SALARY) OVER(PARTITION BY DEPARTMENT_ID) AS MIN,
       MAX(SALARY) OVER(PARTITION BY DEPARTMENT_ID) AS MAX,
       AVG(SALARY) OVER(PARTITION BY DEPARTMENT_ID) AS AVG,
       COUNT(SALARY) OVER(PARTITION BY DEPARTMENT_ID) AS CNT    
FROM EMPLOYEES
ORDER BY DEPARTMENT_ID;

SELECT EMPLOYEE_ID, EMAIL, DEPARTMENT_ID,
       SALARY,
       FIRST_VALUE(SALARY) OVER(ORDER BY SALARY DESC) FV,
       LAST_VALUE(SALARY) OVER(ORDER BY SALARY DESC) LV
FROM EMPLOYEES
ORDER BY DEPARTMENT_ID;

SELECT EMPLOYEE_ID, EMAIL, DEPARTMENT_ID,
       SALARY,
       LAG(SALARY, 3) OVER(ORDER BY SALARY DESC) LAGV,
       LEAD(SALARY, 3) OVER(ORDER BY SALARY DESC) LEADV
FROM EMPLOYEES
ORDER BY SALARY DESC;

SELECT EMPLOYEE_ID, EMAIL, DEPARTMENT_ID,
       SALARY,
       LAG(SALARY, 1) OVER(PARTITION BY DEPARTMENT_ID ORDER BY SALARY DESC) LAGV,
       LEAD(SALARY, 1) OVER(PARTITION BY DEPARTMENT_ID ORDER BY SALARY DESC) LEADV
FROM EMPLOYEES
ORDER BY DEPARTMENT_ID, SALARY DESC;

SELECT EMPLOYEE_ID, EMAIL, DEPARTMENT_ID,
       SALARY,
       ROUND(RATIO_TO_REPORT(SALARY) OVER(), 5) RTR,
       ROUND(PERCENT_RANK() OVER(ORDER BY SALARY), 5) PR,
       ROUND(CUME_DIST() OVER(ORDER BY SALARY), 5) CD,
       NTILE(3) OVER(ORDER BY SALARY) NT
FROM EMPLOYEES
ORDER BY SALARY;

select 2100 / sum(salary), 1 / count(salary) from employees;

SELECT EMPLOYEE_ID, EMAIL, DEPARTMENT_ID,
       SALARY,
       FIRST_VALUE(SALARY) 
       OVER(ORDER BY SALARY DESC RANGE BETWEEN CURRENT ROW AND UNBOUNDED FOLLOWING) FV,
       LAST_VALUE(SALARY) 
       OVER(ORDER BY SALARY DESC RANGE BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW) LV
FROM EMPLOYEES
ORDER BY DEPARTMENT_ID;

SELECT EMPLOYEE_ID, EMAIL, DEPARTMENT_ID,
       SALARY,
       FIRST_VALUE(SALARY) 
       OVER(ORDER BY SALARY RANGE BETWEEN 100 PRECEDING AND UNBOUNDED FOLLOWING) FV,
       LAST_VALUE(SALARY) 
       OVER(ORDER BY SALARY RANGE BETWEEN UNBOUNDED PRECEDING AND 100 FOLLOWING) LV
FROM EMPLOYEES
ORDER BY SALARY;

SELECT ROWNUM,
       EMPLOYEE_ID,
       EMAIL
FROM EMPLOYEES;

SELECT ROWNUM,
       EMPLOYEE_ID,
       EMAIL
FROM EMPLOYEES
ORDER BY EMAIL;

SELECT ROWNUM,
       EMPLOYEE_ID,
       EMAIL
FROM ( SELECT * FROM EMPLOYEES ORDER BY EMAIL );

SELECT ROWNUM,
       EMPLOYEE_ID,
       EMAIL
FROM EMPLOYEES
ORDER BY EMPLOYEE_ID;

SELECT E1.EMAIL, E2.EMAIL MANAGER_EMAIL
FROM EMPLOYEES E1, EMPLOYEES E2 -- E1 : 직원테이블, E2 : 관리자테이블
WHERE E2.EMPLOYEE_ID = E1.MANAGER_ID;

SELECT LEVEL, -- 계층구조에서 순위 번호
       EMPLOYEE_ID, EMAIL, MANAGER_ID,
       SYS_CONNECT_BY_PATH('[' || EMPLOYEE_ID || '/' || EMAIL || ']', '-') PATH
FROM EMPLOYEES E
START WITH MANAGER_ID IS NULL -- 최상위 요소 ( 여기서는 CEO )
CONNECT BY PRIOR EMPLOYEE_ID = MANAGER_ID;










