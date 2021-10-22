package chapter10;

public class ExceptionTest6 {

	public static void main(String[] args)  {

		try {
			// 1. 예외 객체 생성
			Exception e = new Exception("고의로 발생시킨 예외");
			
			// 2. 예외 발생
			throw e;
			
			
		} catch (Exception e) {
			//System.out.println("예외 메시지 : " + e.getMessage());
			e.printStackTrace();
		}
		
		System.out.println("프로그램이 종료됩니다.");

	}
}
