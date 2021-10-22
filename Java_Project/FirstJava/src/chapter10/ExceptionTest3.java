package chapter10;

import java.util.Scanner;

public class ExceptionTest3 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.println("피제수를 입력해주세요.");
		int num1 = sc.nextInt();
		System.out.println("제수를 입력해주세요");
		int num2 = sc.nextInt();

		try {
			System.out.println(num1 / num2);
		} catch (ArithmeticException e) {
			System.out.println("0으로 나누기를 할 수 없습니다.");
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println("오류 발생");
			
		}
		
		System.out.println("프로그램을 종료 합니다.");
		

	}
}
