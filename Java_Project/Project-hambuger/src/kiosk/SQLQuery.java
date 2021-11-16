package kiosk;

public class SQLQuery {
	
	// DAO 에 쓰이는 SQL 문을 모아놓은 메모장
	
    public static final String CREATE_TICKET = "select Ticket_sequence.nextVal from dual";
    public static final String SELECT_SALES_BY_NAME ="select * from sales where s_name = ? ";
    public static final String SELECT_SALES_BY_TYPE = "select * from sales where s_type = ?";
    public static final String SHOW_BURGER_FOR_THE_OLD = "select * from Sales where (s_name = 'BulgogiBurger' or s_name = 'ChickenBurger'  or s_name = 'ShrimpBurger' or s_name ='CheeseBurger')"; 
    public static final String INSERT_ORDER_INFO = "insert into HamOrder values (?,?,?,?,?,?)"; // 번호표, 판매일자, 연령대 , 매장식사, 테이블서비스, 상품번호
    public static final String SHOW_SIDE = "select * from sales where s_type = 'Side' ";
    public static final String SHOW_RECOMMENDED ="select * from sales where s_type = 'Signature'"; // 추천메뉴는 시그니처 버거로 합니다.
    public static final String OUT_OF_STOCK = "select ingre_stock-ingre_need from (select * from sales s1 natural join ingredient where s1.s_name = ?)";
    public static final String RECIEVE_ORDER ="update ingredient i1 set ingre_stock = ingre_stock - ingre_need*? where i1.s_id = (select s_id from sales s1 where s1.s_name = ?)";
}