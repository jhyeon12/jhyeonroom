-- 2021.11.03
-- DML : 입력, 수정, 삭제


-- 테스트 테이블 생성
drop table dept01;

create table dept01
as
select * from dept where 1=0
;


-- 데이터의 입력 
-- insert into {테이블 명} (컬러명, ...) values (data, ...);
-- 입력하고자 하는 컬럼의 개수와 입력데이터의 개수는 일치해야 한다.
-- 컬럼의 도메인과 입력데이터의 타입이 일치해야 한다!!!
-- 컬럼의 제약중에 not null 인 컬럼을 생략하면 안된다!!!

desc dept01;

insert into dept (dname, loc) values ('개발팀', '판교');

insert into dept01 (deptno, dname) values (20, 'DEV', '서울');

-- 모든 컬럼의 데이터를 입력할 경우 컬럼을 기술하는것을 생략 가능
insert into dept01 values (30, 'ACCOUNTING', null);
insert into dept01 values (30, 'ACCOUNTING', '');
insert into dept01 (deptno, dname) values (20, 'DEV');

select * from dept01;

--------------------------------------------------------------
-- 서브 쿼리를 이용한 데이터 입력

drop table dept02;

create table dept02
as
select * from dept where 1=2;

select * from dept02;

-- 서브 쿼리를 이용해서 데이터 삽입
insert into dept02 select * from dept;
insert into dept02 select * from dept02;
select * from dept02;
