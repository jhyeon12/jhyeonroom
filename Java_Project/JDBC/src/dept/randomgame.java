package dept;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class randomgame {
	public static void main(String[] args) {

		// 배열 생성 : new String[18]
		// 배열 : 인스턴스다
		String[] members = { 
				"김성준", "남현수", "문가란", "박태현", "서선영", 
				"신승민", "오종현", "정지원", "조윤재", "지명근", 
				"구성모", "김지현", "김현우", "유태우", "이종현", 
				"이주현", "이호재", "이효은", "차상준", "최종혁" };

		// array to List
		ArrayList<String> memberList = new ArrayList<String>(Arrays.asList(members));

		// shuffle
		for (int i = 0; i < 100000; i++) {
			Collections.shuffle(memberList);
		}

		displayPicker(memberList, true, 25);
	}

	public static void displayPicker(ArrayList<String> memberList, boolean chk, int delay) {

		String[][] teams = new String[4][5];	

		System.out.println("=============================================");

		int index = 0;

		for (int i = 0; i < teams.length; i++) {
			for (int j = 0; j < teams[i].length; j++) {
				teams[i][j] = memberList.get(index++);
			}
		}
		
		int cnt = 1;
		
		for (String[] team : teams) {
			System.out.print(cnt++ + "팀\t");
			for (String name : team) {
				if (chk) {
					;
				}
				try {
					Thread.sleep(delay);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.print(name + "\t");
			}
			System.out.println();
		}
	}

	create table SALES (
	s_id number(6),
	s_name VarChar2(40),
	s_price number(6),
	s_cal number(4),
	s_type Varchar2(40),
	
	constraint PK_Sales_Id primary key(s_id)
	);
	
	create table Ingredient(
	ingre_id number(6),
	inger_name varchar2(40),
	ingre_type varchar2(40),
	s_id number(6),
	
	constraint PK_Ingre_Id primary key(ingre_id),
	constraint FK_Ingre_s_id foreign key (s_id) references SALES(s_id)
	);
	
	drop table sales;
	drop table ingredient;
	insert into sales values(1,'cheeseburger', 6700, 650, '햄버거');
	insert into sales values(2, 'tomatoburger' , 5000, 400, '햄버거');
	
	insert into ingredient values(1, '양파' , '채소', 1);
	insert into ingredient values(2, '치즈', '유제품',1);
	insert into ingredient values(3, '소고기패티', '육류',1);
	insert into ingredient values(4, '토마토', '채소',2);
	insert into ingredient values(5, '양상추', '채소',1);
	
	
	select * from SALES natural join Ingredient
	order by s_id;
}
