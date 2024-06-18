-- 관리자 계정으로 실행

-- 1. HR 계정 복원 (활성화)

ALTER USER HR ACCOUNT UNLOCK;
ALTER USER HR IDENTIFIED BY "oracle";
