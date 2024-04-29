-- 작업 데이터베이스 변경
use world;

-- 
SELECT * FROM country;
SELECT * FROM country WHERE name LIKE '%orea%';

SELECT * FROM countrylanguage;
SELECT * FROM countrylanguage WHERE CountryCode = 'KOR';

SELECT * FROM city;
SELECT * FROM city WHERE CountryCode = 'KOR';

-- 국가명과 사용언어 조회
SELECT c.name, cl.language, cl.isofficial
FROM country c
INNER JOIN countrylanguage cl
ON c.code = cl.countrycode
WHERE c.name = 'India';

SELECT c.name, cl.language, cl.isofficial
FROM country c, countrylanguage cl
WHERE c.code = cl.countrycode AND c.name = 'India';

-- 국가명, 주요 도시 이름, 도시별 인구 조회
SELECT co.name, ci.name, ci.population
FROM country co
INNER JOIN city ci
ON co.code = ci.countrycode
WHERE co.name = 'South Korea';

-- 작업 데이터베이스 변경
USE employees;

SELECT * FROM employees LIMIT 10;
SELECT * FROM departments LIMIT 10;
SELECT * FROM dept_emp LIMIT 20;
SELECT * FROM dept_manager LIMIT 20;
SELECT * FROM titles LIMIT 10;
SELECT * FROM salaries LIMIT 10;

-- 1990년에 'Development' 부서에 근무한 직원 정보 조회
SELECT e.*, de.from_date, de.to_date
FROM employees e
INNER JOIN dept_emp de
ON e.emp_no = de.emp_no
INNER JOIN departments d
ON d.dept_no = de.dept_no
WHERE d.dept_name = 'Development' AND from_date BETWEEN '1990-01-01' AND '1990-12-31'
								  OR to_date BETWEEN '1990-01-01' AND '1990-12-31';
                
select * from employees;
                
-- 'Mary Sluis' 직원의 연봉 계약 이력 조회
SELECT e.first_name, e.last_name, s.salary, s.from_date, s.to_date
FROM employees e
INNER JOIN salaries s
ON e.emp_no = s.emp_no
WHERE e.first_name = 'Mary' AND e.last_name = 'Sluis';

-- 'Georgi Facello' 직원의 직급 이력 조회
SELECT e.first_name, e.last_name, t.title, t.from_date, t.to_date
FROM employees e
INNER JOIN titles t
ON e.emp_no = t.emp_no
WHERE e.first_name = 'Georgi' AND e.last_name = 'Facello';

-- 사용 데이터베이스 변경
USE sakila;

SELECT * FROM film_text LIMIT 10;
SELECT * FROM film LIMIT 10;
SELECT * FROM store LIMIT 10;

-- Action 카테고리의 영화 조회
SELECT * FROM category;

SELECT f.*
FROM category c
INNER JOIN film_category fc
ON c.category_id = fc.category_id
INNER JOIN film f
ON fc.film_id = f.film_id
WHERE c.name = 'Action';

-- 'MARIA MILLER' 고객의 대여 이력 조회
SELECT f.title, r.rental_date
FROM customer c
INNER JOIN rental r
ON c.customer_id = r.customer_id
INNER JOIN inventory i
ON r.inventory_id = i.inventory_id
INNER JOIN film f
ON f.film_id = i.film_id
WHERE c.first_name = 'MARIA' AND last_name = 'MILLER'
ORDER BY r.rental_date;




















