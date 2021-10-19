package chapter06.score;

public class StudentMain {
//	   3.main()메소드에 아래 내용을 정의해봅시다.
	public static void main(String[] args) {
       
//	   ①Student 타입의 배열을 선언하고, 요소 10개를 저장할 수 있는 배열 인스턴스를 생성해 봅시다.
       Student[] score = new Student [10]; // null null null null
       
       int numOfStudent = 0; // 입력된 학생의 수, 배열에 입력할때 index로 사용
       
//	   ②Student 타입의 인스턴스를 생성하고 배열에 저장하는 코드를 정의해봅시다.
       Student s1 = new Student("손흥민", 100, 100, 100);
       // score[0] = s1;
       score[numOfStudent] = s1;
       numOfStudent++; // 추가된 학생의 수를 +1증가
       
       Student s2 = new Student("이강인", 90, 80, 70);
       score[numOfStudent] = s2;
       numOfStudent++;
//	   ③배열에 저장된 Student 타입의 인스턴스의 메소드를 이용해서 모든 데이터를 출력해봅시다.
       
       for(int i=0; i<numOfStudent; i++) {
//    	   System.out.println(score[i].getKorScore()+"\t"+score[i].getEngScore()+"\t"+score[i].getMatScore()+"\t"+score[i].getSum()+"\t"+score[i].getAvg());
      System.out.println(score[i]);
       }
	   
	  //score[5].getAvg(); //오류
       
       // 데이터입력 -> 배열에 저장
       // 데이터 검색 -> 배열의 요소(객체)에서 검색
       // 데이터 삭제 -> 배열의 요소를 삭제
}
}
