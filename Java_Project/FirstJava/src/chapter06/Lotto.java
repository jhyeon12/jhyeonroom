package chapter06;

public class Lotto {

	public static void main(String[] args) {
		
		// 1~45 까지의 숫자를 저장할 수 있는 배열을 생성
		int[] ball = new int[45];
		
		// 각 배열의 요소에 1~45까지의 숫자를 저장
		for(int i =0; i < ball.length; i++) {
			// 각 요소에 값을 저장
			ball[i]= i+1;
		}
		
		for(int i=0; i<ball.length; i++) {
			System.out.println(ball[i]);
		}
		
		System.out.println("숫자섞기================================================");
       
		// 임시변수: 0번지의 값을 저장
		int temp=ball[0];
		
		for(int i = 0; i<10000; i++) {
			// 랜덤한 index(정수) -> 1~44
			int index = (int)(Math.random()*44)+1; // 0.0 <= r < 45.0 => 44
			
			temp = ball[0];
			ball[0] = ball[index];
			ball[index] = temp;
		}
		
		for(int i=0; i< 6; i++) {
			System.out.println(ball[i]);
		}
		
		
		
	}

}
