package exam;

public class Ex08 {
   // 1.국어, 영어, 수학 점수 10개씩을 저장하는 배열을 정의하고 점수를 모두 출력하고, 평균 점수를 출력하는 프로그램을 작성해봅시다.
   //   각 점수는 랜덤한 값을 생성해서 입력합시다.
	public static void main(String[] args) {
	int[][] score = new int [10][3];
	
	
	for(int i =0; i<score.length; i++) {
		for(int j = 0; j<score[i].length; j++) {
			int num = (int)(Math.random()*101);
			
			score[i][j] = num;
			
			
		}
	}
	
	
		System.out.println("국어\t영어\t수학\t");
		for (int i = 0; i < score.length; i++) {
			for (int j = 0; j < score[i].length; j++) {
				System.out.print(score[i][j] + "\t");
			}
			System.out.println();
		}
		
	System.out.println("========================================");
	
	for (int i = 0; i<score.length; i++) {
		
		
		int sum = score[i][0] + score[i][1] + score[i][2];
		int average = (int) sum/3;	
		
		System.out.printf("%2d\n", average);
	}
	
	}
	
}
