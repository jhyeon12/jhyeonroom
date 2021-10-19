package exam;

public class Male extends Person {
    String age;
	String hobby;
	
	
	public Male(String name, String juminNum, String hobby, String age) {
		super(name, juminNum);
		this.hobby = hobby;
		this.age = age;
	}

	public Male() {}

	@Override
	public void introduce(String name, String age) {
		super.introduce(name, age);
	}
	
	public void introduceMale(String hobby) {
		System.out.println("저는 남자이고, 취미는" + hobby + "입니다.");
	}

	
}
