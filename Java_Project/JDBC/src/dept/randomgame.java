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
}
