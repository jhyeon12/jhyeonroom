-- PhoneBook DDL : 테이블 정의서 를 참고 해서 DDL 작성한다.
drop table phoneInfo_univ;
drop table phoneInfo_com;
drop table phoneInfo_basic;

CREATE table phoneInfo_basic (
    idx number(6) constraint pi_basic_idx_PK primary key,
    fr_name VARCHAR2(20) not null,
    fr_phonenumber varchar2(20) not null,
    fr_email varchar2(20) ,
    fr_address varchar2(20),
    fr_regdate date default sysdate
);

-- phoneinfo_basic 테이블 PK -> idx 에 입력할 일련번호 
-- 시퀀스 생성
create sequence pib_idx_seq
start with 1
minvalue 1
increment by 1
maxvalue 999999
;

-- insert : CREATE

desc phoneinfo_basic;

insert into phoneinfo_basic --()
                values (pib_idx_seq.nextval, '손흥민', '010-0000-0000', 'son@gmail.com', 'LONDON', sysdate);
insert into phoneinfo_basic --()
                values (pib_idx_seq.nextval, '이강인', '010-9999-9999', 'lee@gmail.com', 'LONDON', sysdate);
    



-- select
select * from phoneinfo_basic;

-- 이름으로 검색
select * from phoneinfo_basic where fr_name like '%손%';

-- 전화번호 검색
select * from phoneinfo_basic where fr_phonenumber like '%999%';

-- 시스템 내부에서 관리자가 검색 
-- 예) 리스트 -> 상세보기
select * from phoneinfo_basic where idx=1;


-- update
-- 시스템 배부에서 변경 페이지를 이용한 데이터 수정
update phoneinfo_basic
set fr_name='SON', fr_phonenumber='010-7777-5555', fr_email='son@naver.com', fr_address='서울'
where  idx = 1
;


-- delete
delete from phoneinfo_basic where idx=1;



create table phoneinfo_univ (
    idx number(6),
    fr_u_major VARCHAR2(20) default 'N' not null,
    fr_u_year number(1) default 1  not null, --check (fr_u_year between 1 and 4),
    fr_ref number(6) not null,
    constraint pi_univ_idx_PK primary key (idx),
    constraint chk check (fr_u_year between 1 and 4), -- 테이블 레벨에서 check  제약 설정 
    constraint pi_univ_ref_FK FOREIGN KEY (fr_ref) REFERENCES phoneInfo_basic (idx)
);

-- sequence
create sequence piu_idx_seq
maxvalue 999999;



-- insert

-- 친구 정보를 입력 : 기본 정보 + 학교 정보

-- 1. 기본 정보 입력
insert into phoneinfo_basic --()
                values (pib_idx_seq.nextval, '황희찬', '010-3333-1111', 'hh@gmail.com', 'LONDON', sysdate);
select pib_idx_seq.currval from dual;
-- 2. 학교 정보 입력
insert into phoneinfo_univ
                values(piu_idx_seq.nextval, '축구', 1, PIB_IDX_SEQ.currval)
;

-- select

select * from phoneinfo_univ; -- 이 테이블의 데이터만으로는 의미가 없다!!!

select *
from phoneinfo_basic pb, phoneinfo_univ pu
where pb.idx = pu.fr_ref
;

select pb.fr_name, pb.fr_phonenumber, pu.fr_u_major, pu.fr_u_year
from phoneinfo_basic pb, phoneinfo_univ pu
where pb.idx = pu.fr_ref
;

-- 기본 정보만 입력된 데이터도 출력 : outer JOIN
select pb.fr_name, 
        pb.fr_phonenumber, 
        nvl(pu.fr_u_major, '입력정보없음') major, 
        nvl(pu.fr_u_year, 0) year, 
        to_char(pb.fr_regdate, 'YYYY.MM.DD HH24:mm') regdate
from phoneinfo_basic pb, phoneinfo_univ pu
where pb.idx = pu.fr_ref(+)
;

-- update
update phoneinfo_univ
set fr_u_major='야구', fr_u_year=4
where idx=1
;


-- delete
-- 자식 테이블의 데이터를 먼저 삭제
delete from phoneinfo_univ where idx=1;
-- 부모 테이블의 데이터를 삭제
delete from phoneinfo_basic where idx=3;









create table phoneinfo_com (
    idx number(6) constraint pi_com_idx_PK primary key,
    fr_c_company VARCHAR2(20) default 'N' not null,
    fr_ref number(6) not null constraint pi_com_ref_FK REFERENCES phoneinfo_basic (idx)
);

-- sequence
create sequence pic_idx_seq maxvalue 999999;

-- insert
select idx from phoneinfo_basic;
insert into phoneinfo_basic
                values (pib_idx_seq.nextval, '황의조', '010-2222-1111', 'h@gmail.com', 'SEOUL', sysdate);
desc phoneinfo_com;
insert into phoneinfo_com
                values (pic_idx_seq.nextval, 'NAVER', pib_idx_seq.currval);
        
-- 회사 친구 입력 : 기본 정복 입력 -> 회사정보 입력


-- select
 select pb.fr_name, pb.fr_phonenumber, pb.fr_email, 
        nvl(pc.fr_c_company, '입력 데이터 없음') company, 
        to_char(pb.fr_regdate, 'yyyy.MM.DD HH24:mi') regdate
 from phoneinfo_basic pb, phoneinfo_com pc
 where pb.idx=pc.fr_ref(+)
 ;


-- update
update phoneinfo_com
set fr_c_company='쿠팡'
where idx=1
;


-- delete
-- 자식 테이블 행 먼저 삭제
delete from phoneinfo_com where idx=1;
-- 부모 테이블 행 삭제
delete from phoneinfo_basic where idx=4;


---------------------------------------------------
-- 전체 데이터 리스트 출력 : 테이블 세개 JOIN
select pb.fr_name, pb.fr_phonenumber, pu.fr_u_major, pc.fr_c_company
from phoneinfo_basic pb, phoneinfo_com pc, phoneinfo_univ pu
where pb.idx=pc.fr_ref(+) and pb.idx=pu.fr_ref(+) -- and pb.fr_name='손흥민'
;

select * from phoneinfo_view where fr_name='손흥민'  ;

-- view 생성
create or replace view phoneinfo_view
as
select pb.fr_name, pb.fr_phonenumber, pu.fr_u_major, pc.fr_c_company
from phoneinfo_basic pb, phoneinfo_com pc, phoneinfo_univ pu
where pb.idx=pc.fr_ref(+) and pb.idx=pu.fr_ref(+)
;


