package exam;

public class Ex02 {

	
	//	문제 1.
//
//	int형 1차원 배열을 매개변수로 전달 받아서 배열에 저장된 최대값,
//
//	그리고 최소값을 구해서 반환하는 메소드를 다음의 형태로 각각 정의.
//	public static int miniValue(int[] arr) { . . . . } // 최소값 반환
     public static int miniValue(int[] arr) {
		int minValue=arr[0];
		
		for(int i= 1; i < arr.length; i++) {
			if(arr[i] < minValue) {
				minValue = arr[i];
			}
		}
		
		return minValue;
	}
//	public static int maxValue(int[] arr) { . . . . } // 최대값 반환

	public static int maxValue(int[] arr) {
		int maxValue=arr[0];
		
		for(int i= 1; i < arr.length; i++) {
			if(arr[i] > maxValue) {
				maxValue = arr[i];
			}
		}
		
		return maxValue;
	}
	
//	위의 두 매소드는 인자로 전달되는 배열의 길이에 상관없이 동작하도록 정의.
//
//	두 매소드 실행을 확인인하기 위한 main 메소드 정의.
//
//	int형 배열에 채워질 정수는 프로그램 사용자로부터 입력 받고, 배열의 길이는
//
//	임의로 결정
	public static void main(String[] args) {
		
		
		
	  	 int[] arr = {2, 5, 314, 53, 21, 67, 233};
	  	 
	  	 System.out.println("최대값 :" + maxValue(arr));
	  	 System.out.println("최소값 : " + miniValue(arr));
	  	 
	  	 
	  	 
	  	 // 2차원 배열의 선언
	  	 int[][] arr3 = {
	  			 {1,2,3},
	  			 {4, 5, 6, 7},
	  			 {8, 9, 10, 11, 12, 13}
	  	 };
	  	 
	  	 System.out.println("2차원 배열 출력");
	  	 addOneDArr(arr3,100);
	  	 System.out.println("======================================================================");
	  	 // for -each
	  	 for(int i=0; i<arr.length; i++) {
	  		 System.out.println(arr[i]);
	  	 }
	  	System.out.println("======================================================================");
	  	 for(int num : arr) {
	  		 System.out.println(num);
	  	 }
	  	 
	  	int[][] arr1 = {
				{1,2,3},
				{4,5,6},
				{7,8,9},
				{10,11,12}
		
		};
		
		//출력
		for(int i=0; i<arr1.length; i++) {
			for(int j = 0; j < arr1[i].length; j++) {
				System.out.println(arr1[i][j] + "\t");
			}
			System.out.println();
		}
		////////////////////////////////////////////////////////////////////////////
		// 행의 시프트
		
		// 임시배열 선언
		int [] temp = arr1[arr1.length-1];
		
		for(int i = arr1.length-1; i > 0; i--) {
			arr1[i] = arr1[i-1];
			
		}
		
		arr1[0] = temp;
		///////////////////////////////////////////////////////////////////////////////
		
		for(int i=0; i<arr1.length; i++) {
			for(int j = 0; j < arr1[i].length; j++) {
				System.out.println(arr1[i][j] + "\t");
			}
			System.out.println();
		}
		/////////////////////////////////////////////////////////////////////////////////
}
	   
	
	public static void addOneDArr(int[][] arr, int add) {
		
		// 출력
		// 행
		for(int i = 0; i< arr.length; i++) {
			//열
			for(int j=0; j<arr[i].length; j++) {
				System.out.print(arr[i][j] + "\t");
			}
			System.out.println();
		}
		
		// 매개변수로 전달 받은 숫자를 각 요소에 더해준다.
		for(int i = 0; i< arr.length; i++) {
			//열
			for(int j=0; j<arr[i].length; j++) {
				arr[i][j] += add;
			}
			System.out.println();
		}
	}
	

	
}
