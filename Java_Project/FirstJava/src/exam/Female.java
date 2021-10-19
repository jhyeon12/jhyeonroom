package exam;

public class Female extends Person {
    String age;
	String job;
	
	public Female(String name, String juminNum, String job, String age) {
		super(name, juminNum);
		this.job = job;
		this.age = age;
	}
	public Female() {
		
	}

	@Override
	public void introduce(String name, String age) {
		super.introduce(name, age);
	}
	
	public void introduceFemale(String job) {
		System.out.println("저는 여자이고, 직업은" + job + "입니다.");
	}

}
