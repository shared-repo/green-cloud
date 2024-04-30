[ 테이블 만들기 ]

1. tbl_member

   memberid 문자열 PK
   passwd 문자열 NOT NULL
   email 문자열 NOT NULL UNIQUE
   usertype 문자열 NULL
   regdate datetime
   active BOOLEAN 

2. tbl_board

   boardno int PK 자동증가
   writer 문자열 NOT NULL
   title 문자열 NOT NULL
   content 문자열 not null
   writedate DATETIME
   modifydate DATETIME
   readcount int 
   외래키 writer -> tbl_member(memberid)   

3. tbl_comment

   commentno int pk 자동증가
   writer 문자열 NOT NULL
   boardno INT not null
   content 문자열 not null
   writedate DATETIME
   modifydate DATETIME
   외래키 boardno -> tbl_board(boardno)
   외래키 writer -> tbl_member(memberid)
   
[ 테이블 수정 ]

1. tbl_member 

   usergrade int 컬럼 추가
   regdate 컬럼 -> joindate 컬럼으로 변경

2. tbl_board

   category 문자열 not null 컬럼 추가


[ 데이터 추가 ]

1. tbl_member 테이블 데이터 추가

   'iamuserone', 'iamuserone', 'iamuserone@example.com', 'user', now(), true
   'iamusertwo', 'iamusertwo', 'iamusertwo@example.com', 'user', now(), true
   'iamuserthree', 'iamuserthree', 'iamuserthree@example.com', 'user', now(), true

   'iamadminone', 'iamadminone', 'iamadminone@example.com', 'admin', now(), true


2. tbl_board 테이블 데이터 추가

   'iamuserone', '게시글 연습 1', '게시글 작성 연습입니다.', now(), now(), 0, 1 
   'iamuserone', '게시글 연습 2', '게시글 작성 연습입니다.', now(), now(), 0, 1
   'iamuserone', '게시글 연습 3', '게시글 작성 연습입니다.', now(), now(), 0, 1

 
3. tbl_comment 테이블 데이터 추가

   - 아래 1, 2, 3은 글번호인데 2번에서 삽입된 글번호 사용

   'iamusertwo', 1, '게시글 1에 대한 댓글', now(), now()
   'iamusertwo', 2, '게시글 2에 대한 댓글', now(), now()
   'iamusertwo', 3, '게시글 3에 대한 댓글', now(), now()

[ 데이터 수정 ]

iamuserone 사용자의 password는 'Pa$$word'로 이메일은 'iamuserone@naver.com'으로 변경

1번 게시글의 제목은 '수정된 게시글 1'로 modifydate는 now() 로 변경

[ 데이터 삭제 ]

iamuserthree 사용자 삭제

   