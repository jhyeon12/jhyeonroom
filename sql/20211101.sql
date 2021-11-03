-- 2021-11-01

-- JOIN
-- 스키마의 결합, 행은 집합의 합 연산
-- 컬럼의 개수가 늘어난다! 행은 행X행 의 결과가 생성
-- 의미 있는 데이터를 만들기 위해 컬럼의 비교 연산!!!!!

-- Cross Join : 단순히 테이블 붙이는 조인
-- 컬럼을 붙이고, 행(데이터)는 집합의 합 연산(카티즌 곱) -> 전체 행은 ==> R1의 행의 개수 곱하기 R2의 행의 개수
-- emp 테이블과 dept 테이블의 join -> 14 * 4 = 56개 행

-- Cross Join 문
-- select * from 테이블 명, 테이블 명,...
select *
from emp, dept
order by dept.deptno
;


-- Equi JOIN
-- 특정 컬럼의 값들을 비교해서 같은 결과 행만 축출

select *
from emp, dept
where emp.deptno = dept.deptno
;

-- SCOTT 사원의 부서이름을 출력
select ename, emp.deptno, dname
from emp, dept
where emp.deptno=dept.deptno
      and ename='SCOTT'
;

-- 테이블의 별칭 이용
select *
from emp e, dept d
where e.deptno=d.deptno
;

select *
from salgrade
;


select e.ename, e.sal, s.grade--, s.losal, s.hisal
from emp e, salgrade s
where e.sal>=s.losal and e.sal<=s.hisal
order by e.empno
;


-- Self Join
-- 나 자신을 참조해서 붙이는 조인
select e.ename, e.mgr, m.empno, m.ename
from emp e, emp m
where e.mgr=m.empno
order by e.mgr
;
-- 결과의 행의 개수가 13 이다!!!! --> mgr -> null => 무조건 false


-- Outter JOIN
select e.ename as eName, nvl(m.ename, '관리자 없음') as Manager
from emp e, emp m
where e.mgr=m.empno(+)
order by e.mgr
;


-- ANSI JOIN

-- Cross Join
select *
from emp, dept
;

select *
from emp cross join dept
;


-- Inner JOin
-- select * 
-- from t1 inner join t2
-- on 컬럼의 비교 조건(Join의 비교)

-- SCOTT 사원의 사원 정보와 부서정보를 inner join을 이용해서 출력
select *
from emp inner join dept
on emp.deptno=dept.deptno
where emp.ename='SCOTT'
;
select *
from emp join dept
on emp.deptno=dept.deptno
where emp.ename='SCOTT'
;

select *
from emp e join dept d
--on e.deptno=d.deptno
using (deptno)
;

select *
from emp NATURAL join dept
;


-- ANSI outer Join
-- from R1 [LEFT | RIGHT | FULL] outer join R2
-- 사원 이름과 상관의 이름을 출력
select e.ename, m.ename
--from emp e left OUTER JOIN emp m
--from emp e left outer JOIN emp m
from emp m right outer JOIN emp e
on e.mgr=m.empno
;

-- 테이블 세개 조인 : 주문 내역( 고객 정보, 책의 정보, 구매 정보 )
-- 주문 고객의 이름과 책의 이름 구매 날짜와 구매 금액을 출력
select c.name, b.bookname, o.orderdate, o.saleprice
from book b, customer c, orders o
where o.custid=c.custid and  o.bookid=b.bookid
;
