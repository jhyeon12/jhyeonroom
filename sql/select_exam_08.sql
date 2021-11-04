-- 앞에서 생성한 전화번호부 테이블을 기준으로 DML 을 작성해봅시다.
-- 1. phoneInfo_basic 테이블의 SELECT, UPDATE, DELETE, INSERT 하는 SQL
select * from phoneInfo_basic;

insert into phoneInfo_basic
values (1, '이름1', '연락처1', '이메일1', '주소1', sysdate);
insert into phoneInfo_basic
values (2, '이름2', '연락처2', '이메일2', '주소2', sysdate);

update phoneInfo_basic
set fr_name = '이순신', fr_phonenumber = '010-0000-0000', fr_email = '2순신@gmail.com', fr_address = '대한민국'
where idx = 2;


delete from phoneInfo_basic;

-- 2. phoneinfo_univ 테이블의 SELECT, UPDATE, DELETE, INSERT 하는 SQL
select * from phoneInfo_univ;

insert into phoneInfo_univ
values (1, '국문학과', 1, 1);

update phoneInfo_univ
set fr_u_major = '영문학과'
where idx = 1;

delete from phoneInfo_univ;

-- 3. phoneinfo_com 테이블의 SELECT, UPDATE, DELETE, INSERT 하는 SQL
select * from phoneInfo_com;

insert into phoneInfo_com
values (1, '쿠팡', 2);

update phoneInfo_com
set fr_c_company = '구글'
where idx = 1;

delete from phoneInfo_com;

