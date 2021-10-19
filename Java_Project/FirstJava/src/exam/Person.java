package exam;

public class Person {

	String name;
	String juminNum;
	public Person(String name, String juminNum) {
		
		this.name = name;
		this.juminNum = juminNum;
	}
	
	public Person() {}
	
	public  void introduce(String name, String age){
		System.out.println("안녕하세요. 저는 "+ name + "이고" + age +"살 입니다");
	}
	

	
}
