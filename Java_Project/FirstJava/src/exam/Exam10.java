package exam;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Exam10 {

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		
		String id = null;
		int year = 0;
		
		try {
			id = getId();
			System.out.println("아이디: " + id );
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		try {
			year = getYear();
			System.out.println("태어난 해 : " + year);
		} catch (InputMismatchException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		

	}
	
	public static String getId() throws BadinputException {
		
		String name = null;
		
		System.out.println("아이디(영문자와 숫자만 조합) >> ");
		name = sc.nextLine();
		
		//boolean checkStr = !(name.matches("^[a-zA-Z0-9]*$"));
		
		for(int i=0 ; i<name.length(); i++) {
			
			char c = name.charAt(i);
		
			if(!('a'<=c && c<='z' || 'A'<=c && c<='Z' || '0'<=c && c<='9')) {
				//Exception e = new Exception("영문자 또는 숫자 이외의 문자가 포함됨.");
				// 사용자 정의 예외 클래스
				BadinputException be = new BadinputException(name);
				throw be;
			}
		}
		
		return name;
	}

	public static int getYear() {
		System.out.println("태어난 년도를 입력하세요 >>");
		int year = sc.nextInt(); 
		return year;
	}
}
