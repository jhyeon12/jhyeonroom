package day1116;
import java.util.Scanner;
public class exam04 {

	public static void main(String[] args) {
		System.out.println("숫자하나를 입력하세요");
		

		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		
		if(num <= 0) {
			System.out.println("선택하신 숫자는 정수가 아닙니다.");
		}else {
			System.out.println("선택하신 숫자는 정수 입니다.");
		}
	}


}
