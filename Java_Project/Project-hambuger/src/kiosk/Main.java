package kiosk;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		// 테스트용 메인 클래스
		Kiosk_Screen screen = new Kiosk_Screen();		
		screen.showMain();
		
		
		
		
		
		
		
		
		
//	System.out.println(SortPrinting.ROOF.length());
	//	System.out.println((int) Math.ceil((double) (3) / 3));

//		String width = new String("┏━━━━━━1━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
//		System.out.println(width.length());
//		

	//	 정렬 테스트
//		List<Integer> test = new LinkedList<Integer>();
//		for (int i = 0; i < 10; i++) {
//			test.add(i + 1);
//		}
//		for(Integer n : test) {
//			System.out.print(n);
//		}

		// list 에 담긴 정보들을 n개 의 열로 표현하고 싶을 때
		// >> 2차원 배열로 만들어서?
//		int n = 3;
//		Integer[][] arr = new Integer[(int) Math.ceil((double) (test.size()) / n)][];
//		int arrCnt1 = 0;
//		for (int i = 0; i < test.size(); i += n) {
//			
//			if(arrCnt1 != arr.length-1) {
//				arr[arrCnt1] = new Integer[n];
//			}else {
//				arr[arrCnt1] = new Integer[i%n+1];
//			}		
//			
//			for (int j = i; j < i + n; j++) {
//				if (j < test.size()) {
//					
//					arr[arrCnt1][j % n] = test.get(j);
//				}
//			}
//			arrCnt1++;
//		}
//        
//		for(int i=0; i<arr.length; i++) {
//			System.out.println(Arrays.toString(arr[i]));
//		}

		// 성공!

		// 텍스트 자동정렬 테스트

//		String width = new String(/"┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
//		// width의 양쪽을 뺀 나머지 길이는
//		System.out.println(width.length() - 2); // 48
//		// 따라서 Line의 길이를 W라고 하면 1번 인덱스부터 W-2 인덱스까지가 글자 출력할 수 있는 길이이다
//		// 그 길이를 N등분하면 (3등분)
//		System.out.println((int) (width.length() - 2) / 3); // N등분했을때 한 텍스트가 써야하는 최대 길이 =15
//		// 출력해야 하는 문자열의 길이를 7이라 하자.
//		String[] testArr = new String[3];
//		testArr[0] = "abc";
//		testArr[1] = "abcdefg";
//		testArr[2] = "a";
//		String testString = new String("abcdefg");
//		// 15 에서 7을 뺀 값 = 8이 N등분했을때 abcdefg가 써지고 남는 공백 칸수이다.
//		// 이 칸수를 2로 나눠 abcdefg 양 옆에 똑같이 분배해서 정렬하는 것이므로
//		// abcdefg가 써지는 곳은 8을 2로 나눈 값 = 4, 즉 " "를 4번 입력하고 abcdefg를 써주면 된다.
//		
//		int N=3;
//		System.out.println(width);
//		System.out.print("┃");
//		for (int i = 0; i < testArr.length; i++) {
//			for (int j = 0; j < (((int) (width.length() - 2) / N) - testArr[i].length()) / 2; j++) {
//				System.out.print(" ");
//				
//			}
//			System.out.print(testArr[i]);
//			for (int j = 0; j < (((int) (width.length() - 2) / N) - testArr[i].length()) / 2; j++) {
//				System.out.print(" ");
//				
//			}
//		}
//		// 홀수개로 N등분시 나누는 과정에서 차이가 생기므로 " " 을 한번 더해준다.
//        if(N%2 ==1) {
//        	System.out.print(" ");
//        }
//		System.out.print("┃");
		
		

	}
}

