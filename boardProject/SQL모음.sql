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

-- CHAR(10) : 고정 길이 문자열 10바이트 (최대 2000바이트)
-- VARCHAR2(10) : 가변 길이 문자열 10바이트(최대 4000바이트)

-- NVARCHAR2(10) : 가변 길이 문자열 10글자(유니코드, 최대 4000바이트)
-- CROB : 가변 길이 문자열 (최대 4GB)

/*MEMBER 테이블 생성*/
CREATE TABLE "MEMBER"(
	"MEMBER_NO" NUMBER CONSTRAINT "MEMBER_PK" PRIMARY KEY,
	"MEMBER_EMAIL" NVARCHAR2(50) NOT NULL,
	"MEMBER_PW" NVARCHAR2(100) NOT NULL,
	"MEMBER_NICKNAME" NVARCHAR2(10) NOT NULL,
	"MEMBER_TEL" CHAR(11) NOT NULL,
	"MEMBER_ADDRESS" NVARCHAR2(150),
	"PROFILE_IMG" VARCHAR2(300),
	"ENROLL_DATE" DATE DEFAULT SYSDATE NOT NULL,
	"MEMBER_DEL_FL" CHAR(1) DEFAULT 'N' CHECK("MEMBER_DEL_FL" IN ('Y','N')),
	"AUTHORITY" NUMBER DEFAULT 1 CHECK("AUTHORITY" IN (1,2))
);

-- 회원번호 시퀀스 만들기
CREATE SEQUENCE SEQ_MEMBER_NO NOCACHE;

-- 샘플 회원 데이터 삽입
INSERT INTO "MEMBER"
VALUES(SEQ_MEMBER_NO.NEXTVAL,'member01@kh.or.kr',
			'$2a$10$tnbUHEc0ZOejLjA6zG4QMeLs7L5pVxRSbpi5wGrLw1M7ZjIkxq39u',
			'샘플01',
			'01012341234',
			NULL,
			NULL,
			DEFAULT,
			DEFAULT,
			DEFAULT
			
);

COMMIT;

SELECT * FROM "MEMBER";

-- 로그인
-- -> Bcrypt 암호화 사용중
-- -> DB에서 비밀번호 비교 불가능!
-- -> 그래서 비밀번호 (MEMBER_PW)를 조회
-- -> 이메일이 일치하는 회원 + 탈퇴 안한 회원 조건 추가
SELECT MEMBER_NO, MEMBER_EMAIL, MEMBER_NICNAME, MEMBER_PW, MEMBER_TEL, MEMBER_ADDRESS,PROFILE_IMG, AUTHORITY,
			 TO_CHAR(ENROLL_DATE,'YYYY"년" MM"월" DD"일" HH24"시" MI"분" SS"초"')
FROM "MEMBER"
WHERE MEMBER_EMAIL = ?
AND MEMBER_DEL_FL ='N'
























