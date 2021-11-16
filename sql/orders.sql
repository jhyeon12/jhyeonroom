create table SALES (
s_id number(6),
s_name VarChar2(40),
s_price number(6),
s_cal number(4),
s_type Varchar2(40),
-- s_type
-- 1. Hamburger, 2. Beverage 3. Sidedish

constraint PK_Sales primary key(s_id));

create index idx_Sales_type on Sales(s_id);
drop index idx_Sales_type;
select*from sales;
create table Ingredient(
ingre_id number(6),
ingre_name varchar2(40),
ingre_type varchar2(40),
ingre_stock number(5),
ingre_need number(2),
s_id number(6),


constraint PK_Ingre_Id primary key(ingre_id),
constraint FK_Ingre_s_id foreign key (s_id) references SALES(s_id)
);

create table BurgerType(
bt_type varchar2(40),
bt_burgertype varchar2(40)
);

create table HamOwner(
ho_name varchar2(40),
ho_profit number(9) default 0 not null
);


create table HamOrder(
TicketNumber number(4),
ageType Varchar2(20),
saleDate Varchar2(40),
eatHere varchar2(5),
tableService varchar2(1),
s_id number(6),

constraint FK_o_s_id foreign key (s_id) references SALES(s_id)
);

select*from HamOrder;
drop table HamOrder;
drop table sales;
drop table ingredient;
drop table HamOwner;
drop table HamOrder;

insert into HamOwner(ho_name) values('Jones');
select* from sales;
select* from ingredient where s_id = (select s_id from sales where s_name = 'BigMac');
select ingre_stock-ingre_need from (select * from sales s1 natural join ingredient where s1.s_name = 'BigMac');
update ingredient set ingre_stock =5 where ingre_name ='비법소스';
commit;
-- 상품의 타입은 다음과 같다
-- 1. 햄버거 : Beef, Shrimp, Signature, Chicken, Bulgogi, Etc
-- 2. 음료 : Coffee, Ade, Smoothie, Beverage
-- 3. 사이드 메뉴의 타입은 Side 로 통일
insert into sales values(1,'cheeseburger', 4600, 650, 'Etc');
insert into sales values(2, 'tomatoburger' , 3900, 400, 'Etc');
insert into sales values(3, 'BulgogiBurger', 3500, 400, 'Bulgogi');
insert into sales values(4, 'DoubleBulgogiBurger', 4000, 500, 'Bulgogi');
insert into sales values(5, 'EggBulgogiBurger', 3800, 420, 'Bulgogi');
insert into sales values(6, 'ShrimpBurger' , 3600, 400, 'Shrimp');
insert into sales values(7, 'ShrimpCheeseBurger' , 4000, 400, 'Shrimp');
insert into sales values(8, 'BigMac' , 5500, 650, 'Beef');
insert into sales values(9, 'BaconCheeseBurger' , 3600, 400, 'Etc');
insert into sales values(10, 'ChickenBurger' , 4900, 600, 'Chicken');
insert into sales values(11, 'HotChickenBurger' , 5100, 610, 'Chicken');
insert into sales values(12, 'ShrimpBurger' , 3600, 400, 'Shrimp');
insert into sales values(13, 'TripleOnionBurger' , 7000, 700, 'Signature');
insert into sales values(14, 'GoldenEggCheeseBurger' , 6800, 640, 'Signature');
insert into sales values(15, 'HotAllOverBurger' ,6700, 650, 'Signature');
insert into sales values(16, 'TripleBigMac', 8000, 800, 'Beef');
insert into sales values(27, 'KoreanBeefBurger', 8500, 900, 'Beef');
----------------------------------------------------------------------
insert into sales values(17, 'CornSalad', 1800, 146, 'Side');
insert into sales values(18, 'FrenchFries(Regular)', 2300, 377, 'Side');
insert into sales values(19, 'FrenchFries(Large)', 2900, 630, 'Side');
insert into sales values(20, 'ChickenNugget(6piece)', 3700, 450, 'Side');
insert into sales values(21, 'ChickenNugget(10piece)', 6000, 500, 'Side');
insert into sales values(22, 'CheeseStick', 1500, 130, 'Side');

insert into sales values(24, 'FriedSquidRings', 2200, 200, 'Side');
insert into sales values(25, 'Cheeseball(2piece)', 2000, 220, 'Side');
insert into sales values(26, 'CheeseBall(4piece)', 3500, 440, 'Side');
insert into sales values(100, 'FrenchFries', 2300, 377, 'Side');


select* from sales;
---------------------------------------------------------------------
insert into sales values(28,'AmeriCano',1000,30,'Coffee');
insert into sales values(29,'CafeLatte',2000,70,'Coffee');
insert into sales values(30,'CafeMocha',2300,80,'Coffee');
insert into sales values(31,'CaramelMacchiato',2500,100,'Coffee');

insert into sales values(32,'LemonAde',2000,50,'Ade');
insert into sales values(33,'GrapefruitAde',2500,70,'Ade');
insert into sales values(34,'GreengrapeAde',2500,80,'Ade');

insert into sales values(35,'Coke',1000,100,'Beverage');
insert into sales values(36,'LargeCoke',1500,130,'Beverage');
insert into sales values(37,'Sprite',800,90,'Beverage');
insert into sales values(38,'GrapeFanta',1100,100,'Beverage');
insert into sales values(39,'OrangeFanta',1100,100,'Beverage');
insert into sales values(40,'Water',500,0,'Beverage');

insert into sales values(41,'OrangeSmoothie',2000,200,'Smoothie');
insert into sales values(42,'StrawberrySmoothie',2100,210,'Smoothie');
insert into sales values(43,'Mangosmoothie',2100,220,'Smoothie');
insert into sales values(44,'AppleSmoothie',2100,220,'Smoothie');
insert into sales values(45,'KiwiSmoothie',2100,220,'Smoothie');


select * from sales;
select * from sales where s_name = 'FrenchFries(Regular)';

select * from sales;
insert into ingredient values(1, '양파' , '채소',1000, 1,1);
insert into ingredient values(2, '치즈', '유제품',1000,2,1);
insert into ingredient values(3, '소고기패티', '육류',150,1,1);
insert into ingredient values(4, '토마토', '채소',200,1,2);
insert into ingredient values(5, '양상추', '채소',900,1,1);
insert into ingredient values(6, '빵', '가공',1000,1,1);
insert into ingredient values(7, '치즈', '유제품',1000,1,1);
insert into ingredient values(8, '소고기패티', '육류',1000,1,1);
-- 토마토버거
insert into ingredient values(9, '빵', '가공',1000,1,2);
insert into ingredient values(10, '소고기패티', '육류',1000,1,2);
insert into ingredient values(11, '양상추', '채소',1000,1,2);
insert into ingredient values(12, '치즈', '유제품',1000,1,2);
insert into ingredient values(13, '피클', '가공',1000,1,2);

-- 불고기버거
insert into ingredient values(14,'빵','가공',1000,1,3);
insert into ingredient values(15,'고기패티','육류',1000,1,3);
insert into ingredient values(16,'양상추','채소',1000,1,3);
insert into ingredient values(17,'피클','가공',1000,1,3);

-- 더블 불고기 버거
insert into ingredient values(18,'빵','가공',1000,1,4);
insert into ingredient values(19,'고기패티','육류',1000,2,4);
insert into ingredient values(20,'양상추','채소',1000,1,4);
insert into ingredient values(21,'피클','가공',1000,1,4);

-- 계란 불고기 버거
insert into ingredient values(22,'빵','가공',1000,1,5);
insert into ingredient values(23,'고기패티','육류',1000,1,5);
insert into ingredient values(24,'양상추','채소',1000,1,5);
insert into ingredient values(25,'피클','가공',1000,1,5);
insert into ingredient values(26,'계란','식재료',1000,1,5);

-- 새우버거
insert into ingredient values(27,'빵','가공',1000,1,6);
insert into ingredient values(28,'새우패티','가공',1000,1,6);
insert into ingredient values(29,'양상추','채소',1000,1,6);
insert into ingredient values(30,'피클','가공',1000,1,6);

-- 새우치즈버거
insert into ingredient values(31,'빵','가공',1000,1,7);
insert into ingredient values(32,'새우패티','가공',1000,1,7);
insert into ingredient values(33,'양상추','채소',1000,1,7);
insert into ingredient values(34,'치즈','유제품',1000,1,7);
insert into ingredient values(35,'피클','가공',1000,1,7);

-- 빅맥
insert into ingredient values(36,'빵','가공',1000,2,8);
insert into ingredient values(37,'고기패티','육류',1000,2,8);
insert into ingredient values(38,'양상추','채소',1000,1,8);
insert into ingredient values(39,'치즈','유제품',1000,2,8);
insert into ingredient values(30,'피클','가공',1000,1,8);
insert into ingredient values(100,'비법소스', '소스' ,4, 3,8);


insert into ingredient values(31, '빵', '가공',1000,1,9);
insert into ingredient values(32, '치즈', '유제품',1000,1,9);
insert into ingredient values(33, '소고기패티', '육류',1000,1,9);
insert into ingredient values(34, '베이컨','가공',1000,2,9);

-- 치킨버거 
insert into ingredient values(35, '빵', '가공',1000,1,10);
insert into ingredient values(36, '치킨패티', '가공',1000,1,10);
insert into ingredient values(37,'양상추','채소',1000,1,10);
insert into ingredient values(38,'피클','가공',1000,1,10);

-- 핫치킨버거
insert into ingredient values(39, '빵', '가공',1000,1,11);
insert into ingredient values(40, '치킨패티', '가공',1000,1,11);
insert into ingredient values(41,'양상추','채소',1000,1,11);
insert into ingredient values(42,'피클','가공',1000,1,11);

-- 트리플 양파 버거
insert into ingredient values(43, '빵', '가공',1000,1,12);
insert into ingredient values(44, '소고기패티', '육류',1000,1,12);
insert into ingredient values(45,'양파','채소',1000,3,12);
insert into ingredient values(46,'양상추','채소',1000,1,12);
insert into ingredient values(47,'피클','가공',1000,1,12);

-- 골든에그 치즈버거
insert into ingredient values(48,'빵','가공',1000,1,13);
insert into ingredient values(49,'고기패티','육류',1000,1,13);
insert into ingredient values(50,'양상추','채소',1000,1,13);
insert into ingredient values(51,'피클','가공',1000,1,13);
insert into ingredient values(52,'계란','식재료',1000,1,13);
insert into ingredient values(53, '치즈', '유제품',1000,1,13);
insert into ingredient values(54, '베이컨','가공',1000,2,13);
insert into ingredient values(55,'양파','채소',1000,1,13);

-- 핫올오버버거
insert into ingredient values(56,'빵','가공',1000,1,14);
insert into ingredient values(57,'고기패티','육류',1000,1,14);
insert into ingredient values(58,'치킨패티','가공',1000,1,14);
insert into ingredient values(59,'양상추','채소',1000,1,14);
insert into ingredient values(60,'피클','가공',1000,1,14);
insert into ingredient values(61,'계란','식재료',1000,1,14);
insert into ingredient values(62, '치즈', '유제품',1000,1,14);
insert into ingredient values(63, '베이컨','가공',1000,2,14);
insert into ingredient values(64,'양파','채소',1000,1,14);


update ingredient i1
set ingre_stock = ingre_stock - ingre_need
where i1.s_id =
(select s_id from ingredient i2,
sales s1 where i2.s_id = s1.s_id);


-- JAVA 의 ResultSet 타입 변수에 넣어줄 SQL문
-- 고객이 주문한 '상품명' 을 가진 상품의 필요한 재료 갯수들을 재고에서 뺀 결과를 보내준다.
-- 그 ResultSet rs 에 저장된 값을 하나씩 체크하면서 
-- 만약 0보다 작은 수가 나왔다면, 재고가 부족하다는 의미이므로, 주문을 취소시킨다.
select ingre_stock-ingre_need from
(select *from sales s1 natural join ingredient where s1.s_name = 'ShrimpBurger');
select*from sales;

update ingredient i1 set ingre_stock = ingre_stock - ingre_need*2
where i1.s_id = (select s_id from sales s1 where s1.s_name = 'ShrimpBurger');

select *  from ingredient i1
where i1.s_id = (select s_id from sales s1 where s1.s_name = 'ShrimpBurger');

select * from sales where s_name = 'ShrimpBurger';


-- 만약 0이 아니라면 주문을 접수하고 재료의 재고에서 상품이 필요하는 재료의 갯수를 뺀다 (Update)
-- = 주문을 받고 조리 시작.
update ingredient i1
set ingre_stock = ingre_stock - ingre_need
where i1.s_id =  (
select s_id from sales s1 where s1.s_name = 'cheeseburger' );

-- 번호표 시퀀스 생성
-- 시퀀스가 생성한 번호는 HAMORDER 테이블의 NUMTICKET 으로 들어간다.
-- 사용자가 결제까지 완료한 다음 DB에 ORDERS 데이터를 추가한다.
-- 결제 완료후 데이터에 추가할때에 JAVA ORDERS 클래스에 변수 int ticketNum 에 SQL 문을 사용하여 초기화시킨다.
create sequence Ticket_sequence
start with 1
maxValue 6000
cycle
increment by 1;
-- Orders 테이블의 PK를 생성하는 시퀀스
create sequence SEQ_order_sequence
start with 1
increment by 1;
----------------------------------------------------------
select Ticket_sequence.nextVal from dual;
-- 사용자의 주문한 상품을 Orders 테이블에 데이터로 추가시킨다.
-- 시퀀스를 이용하여 Orders 테이블의 PK를 생성한다.
-- 한 고객이 주문한 상품들에 대해 번호표는 같은 번호여야 하므로
-- JAVA 에서 int ticketNum = (SEQ_sequence 가 생성하는 nextVal) 으로 초기화 시키고
-- String OrderDate 에는 (sysdate를 to_char로 변환한 문자열) 으로 초기화시키고
-- int age는 최초 메뉴화면에서 간편선택 메뉴를 선택시 0으로(고령자임을 명시) 선택안할시 1로(일반 주문) 으로 초기화시킨다.
-- 첫번째 물음표에 ticketNum, 두번째 물음표에는 OrderDate를, 세번째 물음표에는 age를, 네번째 물음표에는 
-- basket 에 모은 상품들의 고유번호를 저장한다.
insert into HamOrder values(SEQ_order_sequence, '?' , '?' ,'?' ,'?');

-- 연령대가 60세 이상이 구매했던 상품들만 출력해주는 SQL문
-- JAVA의 resultSet 타입 객체변수로 받아 출력할 수 있다.
-- 타입별로 정렬한다.
select s_name, s_Price from HamOrder natural join Sales where Age =0
order by s_type;

-- 전체 상품을 보여주는 뷰어 
create or replace view VW_ALL
as select s_name, s_Price, s_Cal, s_type, s_id from Sales;
-- 고령자들을 위한 추천 상품을 보여주는 뷰어 BulgogiBurger, ChickenBurger, ShrimpBurger, CheeseBurger 4가지를 보여준다
select *from Sales where (s_name = 'BulgogiBurger' or s_name = 'ChickenBurger'  or s_name = 'ShrimpBurger' or s_name ='CheeseBurger');
select s_name, s_price, s_cal from vw_all;

-- 상품을 보여주는 SQL문
-- JAVA 에서 ? 문에 상품의 타입을 설정해서 SQL 문 실행
-- 타입 : Hamberger, Beverage, SideMenu 
-- 설정한 타입에 따라 해당 타입의 상품의 정보들을 ResultSet 에 넘길
select s_name, s_price, s_Cal from VW_ALL where s_type = '?';
-- 고령자들을 위한 간편메뉴
select s_name, s_price from VW_All natural join HamOrder where age = 0 and s_type = '?';

select *from ingredient;

select SEQ_sequence.nextVal from dual;
select SEQ_sequence.currVal from dual;


update ingredient 
set ingre_stock =0 where ingre_name = '양파';
select * from ingredient;

update ingredient set ingre_stock = ingre_stock + 100 where ingre_name = '양파';


select *from sales s1 natural join ingredient where s1.s_name = 'cheeseburger';
select s_name from hamorder natural join sales where ageType = 'TheOld';