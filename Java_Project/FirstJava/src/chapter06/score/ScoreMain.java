package chapter06.score;

public class ScoreMain {
   public static void main(String[] args) {
	
	   
	   
	   ScoreManager manager = new ScoreManager();// 10개 저장공간
	   
	   // 1. 데이터 저장
	   manager.insertScore(new Student("손흥민", 100, 100, 100));
	   manager.insertScore(new Student("손흥", 100, 20, 30));
	   manager.insertScore(new Student("손", 100, 11, 50));
	   
	  
	   // 2. 데이터 전체 출력
	   manager.showAllData();
	   // 3. 데이터 검색
	   //manager.searcData("손흥민");
	   //manager.searcData("이강인");
	   
	   // 4. 데이터 삭제
	   //manager.deleteScore("SCOTT");
	   //manager.deleteScore("이강인");
	   // 5. 데이터 검색 또는 전체 데이터 출력
	   manager.showAllData();
}
}
