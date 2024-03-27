-- 계정 생성 (관리자 계정으로 접속)
ALTER SESSION SET "_ORACLE_SCRIPT" = TRUE;

CREATE USER SPRING_KAR IDENTIFIED BY SPRING1234;

GRANT CONNECT, RESOURCE TO SPRING_KAR;

ALTER USER SPRING_KAR
DEFAULT TABLESPACE USERS
QUOTA 20M ON USERS;

--> 계정 생성 후 접속방법(새 데이터베이스) 추가

---------------------------------------------------------------------------------------------------------------
/*SPRING 계정 접속*/

--"" : 내부에 작성된 글(모양) 그대로 인식 -> 대/소문자 구분
--> "" (쌍따옴표) 쓰는 것을 권장함

--CHAR(10) : 고정 길이 문자열 10바이트 (최대 2000바이트)
--VARCHAR2(10) : 가변 길이 문자열 10바이트(최대 4000바이트)