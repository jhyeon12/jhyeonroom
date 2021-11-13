-- 아래 DDL은 책 정보를 저장하는 테이블입니다.
-- 이 테이블을 기준으로 아래 문제를 해결하시오.
CREATE TABLE Book(bookid NUMBER(2) PRIMARY KEY,
                  bookname VARCHAR2(40),
                  publisher VARCHAR2(40),
                  price NUMBER(8)
);

CREATE TABLE Book_log(bookid_1 NUMBER,
                      bookname_1 VARCHAR2(40),
                      publisher_1 VARCHAR2(40),
                      price_1 NUMBER
);

-- 1. 동일한 도서가 있는지 점검한 후 삽입하는 프로시저(BookInsertOrUpdate)를 작성하시오
create or replace procedure BookInsertOrUpdate(
myBookId number,
myBookname VARCHAR2,
myPublisher VARCHAR2,
myPrice number
)
as
mycount number;
begin
select count(*) into mycount from book where bookname like myBookname;

if mycount != 0 then

update book set price = myPrice where bookname like myBookname;

else
insert into book values(myBookId, myBookname, myPublisher, myPrice);
end if;
end;




-- 2. 판매된 도서에 대한 이익을 계산하는 함수(fnc_Icom)를 작성하시오.
--    (이익금 계산조건 : 가격이 30,000원 이상이면 10%, 30,000원 미만이면 5%)
create or replace procedure interest
as
myInterest NUMERIC;
price NUMERIC;
cursor interestCirsor is select saleprice from orders;
begin
myInterest := 0.0;
open interestCursor;
loop
fetch interestCursor into price;
exit when interestCursor%notfound;
if price >= 30000 then
myInterest := myInterest + price*0.1;
else
myInterest := myInterest + price*0.05;
end if;

end loop;

close interestCursor;

dbms_output.put_line('총 이익금액: '|| myInterest);

end;




-- 3. 새로운 도서를 삽입한 후 자동으로 Book_log 테이블에 삽입한 내용을 기록하는 트리거를 작성하시오.
create or replace trigger InsertNewBook
after insert on book
for each row

begin
insert into book_log
values(:new.bookId, :new.bookname, :new.publisher, :new.price);
end;
