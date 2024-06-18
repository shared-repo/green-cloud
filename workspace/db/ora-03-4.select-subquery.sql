-- SUBQUERY

-- ���� ���� �̷��� �ִ� �� ��ȸ
SELECT DISTINCT C.*
FROM CUSTOMER C
INNER JOIN ORDERS O ON C.CUSTID = O.CUSTID;

SELECT *
FROM CUSTOMER C
WHERE C.CUSTID IN ( SELECT DISTINCT CUSTID FROM ORDERS );

-- ���ѹ̵��� �Ⱓ�� ������ ������ �� ��ȸ
SELECT C.*
FROM CUSTOMER C
INNER JOIN ORDERS O ON C.CUSTID = O.CUSTID
INNER JOIN BOOK B ON O.BOOKID = B.BOOKID
WHERE B.PUBLISHER = '���ѹ̵��';

SELECT C.*
FROM CUSTOMER C
WHERE C.CUSTID IN ( SELECT O.CUSTID
                    FROM ORDERS O
                    WHERE O.BOOKID IN ( SELECT B.BOOKID
                                        FROM BOOK B
                                        WHERE B.PUBLISHER = '���ѹ̵��' ) );
                                        
-- ���� ���� ��ȸ�ϵ� 
-- ���� ������ �� ������ �Ⱓ�� ���ǻ��� ��� ������ ��� ���ݺ��� ���� ������ ��ȸ
SELECT *
FROM BOOK B
WHERE B.PRICE > ( SELECT AVG(B2.PRICE)
                  FROM BOOK B2 
                  WHERE B2.PUBLISHER = B.PUBLISHER );








