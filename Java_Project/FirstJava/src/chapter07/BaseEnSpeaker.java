package chapter07;

public class BaseEnSpeaker extends Speaker {

	private int baseRate;
	
	public void setBaseRate(int base) {
		baseRate = base;
	}
	
	// 오버라이딩
	// 상위 클래스의 메소드의 선언부를 일치시키고
	
	public void showCurretState() {
		super.showCurrentState();
		System.out.println("베이스크기"+baseRate );
	}
	
	public static void main(String[] args) {
		
		BaseEnSpeaker bSpeaker = new BaseEnSpeaker();
		bSpeaker.showCurrentState();
		
		System.out.println("----------------------------------");
		
		// 다형성
		// 상위 타입의 참조변수 = 하위 타입의 인스턴스
		// 참조변수의타입에 정의된 멤버만 사용이 가능
		Speaker sp = new BaseEnSpeaker();
		sp.showCurrentState();
		//sp.setBaseRate(100);
		
		
	}
}
