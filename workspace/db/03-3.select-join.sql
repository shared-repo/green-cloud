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








