package chapter10;

import java.util.Scanner;

public class ExceptionTest4 {
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		try {
			System.out.println(10/2);
			return;
		} catch (Exception e) {
			System.out.println("예외 발생 = 예외처리");
			System.out.println(e.getMessage());
		} finally {
			System.out.println("예외상황과 상관 없이 실행");
		}
		
		try {
			System.out.println("try");
		} finally {
			System.out.println("finally");
		}
		
	}
}
