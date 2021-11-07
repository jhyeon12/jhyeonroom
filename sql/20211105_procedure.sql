-- 2021.11.05
-- procedure
-- 테이터를 받아서 데이터 변경처리를 프로그램 요소를 이용해서 실행


-- create or replace {프로시저 이름} (매개변수)
-- as -- 선언부
-- begin -- 프로그램 코드 시작
-- ......
-- end
-- ;

-- 프로시저 호출 : 실행 요청
-- exec 프로시저이름(매개변수 값,....);
exec insertbook(13, '스포츠과학', '마당과학서적', 25000);
select * from book;

-- 같은 이름의 책을 등록해보고, 다른 이름의 책을 입력
-- 같은 이름의 책 등록
exec bookinsertorupdate(15, '스포츠과학', '마당과학서적', 10000);
exec bookinsertorupdate(15, '스포츠 즐거움', '마당과학서적', 40000);
select * from book;

----------------------------------------------------
set serveroutput on;
declare
    avgVal number;
begin
    -- 프로시저 호출
    avgPrice(avgVal);
    dbms_output.put_line('책값 평균 : '|| avgVal);
end;

----------------------------------------------------
set serveroutput on;
exec interest;