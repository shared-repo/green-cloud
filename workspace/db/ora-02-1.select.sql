-- hr �������� ����

-- 1. ���̺� ��� ��ȸ 
select * from tab;

-- green_cloud �������� ����
-- connect green_cloud/oracle;

-- 2. ���̺� ��� ��ȸ
SELECT * FROM tab; -- mysql : show tables;

-- 3. ���̺� ���� ��ȸ
DESC BOOK;
DESC CUSTOMER;

-- 4. ��� ������ �̸��� ���� ��ȸ
SELECT BOOKNAME, PRICE
FROM BOOK;

-- 5. ������ ���ǻ� ���� ��ȸ
SELECT PUBLISHER
FROM BOOK;

SELECT DISTINCT PUBLISHER
FROM BOOK;

-- 6. ������ 20000�� �̻��� ���� ��ȸ
SELECT * 
FROM BOOK
WHERE PRICE >= 20000;

-- 6. ������ 10000�� �̻� 20000�� ������ ���� ��ȸ
SELECT * 
FROM BOOK
-- WHERE PRICE >= 10000 AND PRICE <= 20000;
WHERE PRICE BETWEEN 10000 AND 20000;

-- 7. '������'�� '�Ｚ��' ���ǻ翡�� �Ⱓ�� ����
SELECT * 
FROM BOOK
-- WHERE PUBLISHER = '������' OR PUBLISHER = '�Ｚ��';
-- WHERE PUBLISHER IN ('������', '�Ｚ��');
WHERE PUBLISHER NOT IN ('������', '�Ｚ��'); -- ���� (�ݴ� ���� ��ȸ)

-- 8. �౸ ���� ���� ��ȸ
SELECT *
FROM BOOK
-- WHERE BOOKNAME LIKE '%�౸%';
WHERE BOOKNAME NOT LIKE '%�౸%'; -- ���� (�ݴ� ���� ��ȸ)

-- 9. ��� ���ݼ����� ���� ��ȸ
SELECT *
FROM BOOK
-- ORDER BY PRICE DESC;
ORDER BY PRICE DESC, PUBLISHER ASC;

-- 10. ������ ��� ����, �ְ� ����, ���� ���� ��ȸ
SELECT AVG(PRICE) "��հ���", MAX(PRICE) �ְ���, MIN(PRICE) ��������
FROM BOOK;

-- 11. ������ �� �Ǹž�
SELECT SUM(SALEPRICE) ���Ǹž�
FROM ORDERS;

-- 12. ���� ���� ���� ����
SELECT O.CUSTID, SUM(SALEPRICE) �ѱ��ž�, COUNT(*) �ѱ��ŰǼ�
FROM ORDERS O
GROUP BY O.CUSTID;

-- 13. ���ǻ纰 ���� ��� ����
SELECT PUBLISHER, AVG(PRICE) ��հ���
FROM BOOK
GROUP BY PUBLISHER;

-- 14. ��� ���ž��� 10000�� �̻��� ���� ���� ��� ���ž� ��ȸ
--SELECT CUSTID, AVG(SALEPRICE) ��ձ��ž�
--FROM ORDERS
--WHERE AVG(SALEPRICE) >= 10000
--GROUP BY CUSTID;

SELECT  CUSTID, 
        ROUND( AVG(SALEPRICE)) ��ձ��ž�1,
        ROUND( AVG(SALEPRICE), 2) ��ձ��ž�2,
        ROUND( AVG(SALEPRICE), -2) ��ձ��ž�3
FROM ORDERS
GROUP BY CUSTID
HAVING AVG(SALEPRICE) >= 10000;





