package chapter06.score;

public class ScoreMain2 {

	public static void main(String[] args) {
		
		
		ScoreManager manager = new ScoreManager();
		// 사용자가 Student 데이터를 입력해야한다.
		// 사용자가 데이터입력/ 검색/ 출력/ 삭제/ 프로그램 종료 선택한다.
		
		while(true) {
			showMenu();
			
			int select = Integer.parseInt(ScoreManager.sc.nextLine());
			
			switch(select) {
			case 1:
				System.out.println("점수 데이터를 입력합니다.");
				manager.insertStudent();
				break;
			case 2:
				System.out.println("전체 데이터를 출력합니다.");
				manager.showAllData();
			break;
			case 3:
				System.out.println("학생 정보를 검색합니다.");
				manager.searcData();
				break;
			case 4:
				System.out.println("학생 정보를 삭제합니다.");
				manager.deleteScore();
			break;
			case 5: System.out.println("프로그램을 종료 합니다.");
			return;
			}
			
		}
		
		

	}
	
	public static void showMenu() {
		System.out.println("시험점수 관리 프로그램");
		System.out.println("----------------------");
		System.out.println("1. 학생 점수 데이터 입력");
		System.out.println("2. 전체 데이터 출력");
		System.out.println("3. 학생 점수 검색");
		System.out.println("4. 학생 점수 삭제");
		System.out.println("5. 프로그램 종료");
		System.out.println("----------------------");
		System.out.println("메뉴 번호를 입력해주세요");
		System.out.println(">");
		
	}

}
