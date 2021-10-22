package chapter10;

public class ExceptionTest5 {

	public static void main(String[] args) {

		try {
			System.out.println(10/2);
			return;
		} catch (Exception e) {
			System.out.println("예외 발생 = 예외처리");
		} finally {
			System.out.println("예외상황과 상관 없이 실행!");
		}
		
		try {
			System.out.println("try");
		} finally {
			System.out.println("finally");
		}
		

	}
}
