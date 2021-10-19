package chapter06.score;

public class Student {
//  2.Student 클래스를 정의해봅시다.
	
	// 데이터 저장 목적
	
//	①학생이름, 국어점수, 영어점수, 수학점수를 저장하는 변수를 정의 합니다.
  private String name;
  private int korScore;
  private int engScore;
  private int mathScore;
//	②변수는 캡슐화를 합니다. getter/setter 메소드를 정의합니다.
  
 
  
public Student(String name, int korScore, int engScore, int mathScore) {
	super();
	this.name = name;
	this.korScore = korScore;
	this.engScore = engScore;
	this.mathScore = mathScore;
	
}
 Student() {}

public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getKorScore() {
	return korScore;
}
public void setKorScore(int korScore) {
	this.korScore = korScore;
}
public int getEngScore() {
	return engScore;
}
public void setEngScore(int engScore) {
	this.engScore = engScore;
}
public int getMatScore() {
	return mathScore;
}
public void setMatScore(int matScore) {
	this.mathScore = matScore;
}
//③총점과 평균을 구해 결과를 반환하는 메소드를 정의합니다.
public int getSum() {
	return this.korScore+this.engScore+this.mathScore;
	
}

public float getAvg() {
	return getSum()/3.0f;
}
@Override
public String toString() {
	return name+"\t" + korScore+"\t"+engScore+"\t"+mathScore+"\t"+getSum()+"\t"+getAvg();
}
}
   

