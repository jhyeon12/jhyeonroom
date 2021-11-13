-- 1. 아래 조건에 맞도록 기본 SQL 문을 작성하시오
-- ======전화번호 부(테이블 이름 : Contact)
---------기본정보(not null)
----대리키 : 일련번호 -> pIdx 기본키로 설정
----이름, 전화번호, 주소, 이메일
----주소값과 이메일은 입력이 없을때 기본값 입력
----친구의 타입(카테고리) : univ, com, cafe 세가지 값만 저장가능
---------선택정보
----전공, 학년
----회사이름, 부서이름, 직급
----모임이름, 닉네임
create table phoneInfo_basic (
    pIdx number(6) constraint b_pIdx_PK primary key,
    b_name varchar2(20) not null,
    b_phonenumber varchar2(20) not null,
    b_address varchar2(20) default 'KOREA' not null,
    b_email varchar2(20) default 'not reg' not null,
    b_group varchar2(4) check (b_group in('univ', 'com', 'cafe'))
);

create table phoneInfo_univ(
   pIdx number(6),
   univ_major varchar2(20) not null,
   univ_grade varchar2(4) not null,
   constraint b_univ_idx_PK primary key (pIdx),
   constraint b_univ_ref_FK FOREIGN KEY (b_ref) REFERENCES phoneInfo_basic (pIdx)
);

create table phoneInfo_com(
   pIdx number(6),
   com_name varchar2(20) not null,
   com_dept varchar2(20) not null,
   com_job varchar2(10) not null,
   constraint b_univ_idx_PK primary key (pIdx),
   constraint b_univ_ref_FK FOREIGN KEY (b_ref) REFERENCES phoneInfo_basic (pIdx)
);

create table phoneInfo_cafe(
   pIdx number(6),
   cafe_cName varchar2(10),
   cafe_nickname varchar2(10),
   constraint b_univ_idx_PK primary key (pIdx),
   constraint b_univ_ref_FK FOREIGN KEY (b_ref) REFERENCES phoneInfo_basic (pIdx)
);

-- 2. DEPT 테이블에 데이터를 삽입하는 SQL을 작성하시오. 입력 데이터는 임의로 작성하시오.
insert into dept values(60, 'PRODUCING', 'WASINGTON');
-- 3. DEPT 테이블에 위에서 삽입한 데이터의 dname, loc 데이터를 변경하는 SQL을 작성하시오.
--    입력데이터는 모두 임의로 작성하시오.
update dept
set dname = 'SALES', loc = 'TOKYO'
where deptno = 60
;
-- select * from dept;

-- 4. DEPT 테이블에 위에서 삽입한 데이터를 deptno 컬럼의 값을 기반으로 삭제하는  SQL을 작성하시오.
delete from dept
where deptno = 60
;
-- select * from dept
-- 5. 사용자가 보유한 테이블 목록을 확인하는 SQL을 작성하시오.
select * from tab;
-- 6. EMP 테이블의 구조를 확인하는 SQL을 작성하시오.
select * from emp;
-- 7. 사용자가 정의한 제약조건들을 확인하는 SQL문을 작성하시오.
select * from all_constraints;
-- #2. 아래 요구사항에 맞도록 고급 SQL문을 작성하시오
-- 1. EMP 테이블의 ename 컬럼에 인덱스를 생성하는 SQL을 작성하시오. 인덱스의 이름은 emp_index
create index emp_index
on emp(ename)
;

-- 2. EMP 테이블과 DEPT 테이블을 조인하는 SQL을 기반으로 view 객체를 생성하는 SQL을 작성하시오.
--    view의 이름은 emp_view
create or replace view emp_view
as
select hiredate from emp em, dept de 
where em.deptno = de.deptno
;

-- 3. EMP 테이블에서 모든 사원의 부서번호를 이름이 'SCOTT'인 사원의 부서번호로 변경하는 SQL을 작성 하시오.
update emp
set deptno = (select deptno from emp where ename = 'SCOTT')
;




