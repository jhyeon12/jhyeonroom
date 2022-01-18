package practice;

import java.util.Scanner;

public class practice {

	public static void main(String[] args) {
		
		char ch;  // 문자열에서 뽑아낸 문자를 저장 ( String > char )
	      boolean output=true;
	      
	      // Character.isLetter(ch) 문자
	      // 
	      Scanner sc=new Scanner(System.in);
	   
	      System.out.println("문자열을 입력하세요");
	      System.out.println(">");
	      String input=sc.nextLine();

	      while(output) {
	      for(int i=0;i<input.length();i++) {
	         ch=input.charAt(i);
	      
	         if(Character.isLowerCase(ch)) // 입력받은 문자열이 소문자라면
	            System.out.print(Character.toUpperCase(ch)); // 대문자로 바꿔서 출력해라
	         
	         else // 대문자라면
	            System.out.print(Character.toLowerCase(ch)); // 소문자로 바꿔서 출력해라

	         
	   }
	      break;
	      }   // System.out.println("는 입력 불가능. 영문자만 입력 가능합니다"); // 안된다      

	}

}
