package exam;

public class CountDownThr extends Thread {

	
	@Override
	public void run() {
		
		
		for(int i =30; i>0; i--) {
			
			if(HighLowGame.inputCheck) {
				System.out.println("정답을 맞추어 끝냅니다.");
				return;
			}
			
			System.out.println(i);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
		
		System.out.println("30초동안 입력이 없어 프로그램을 종료합니다.");
		System.exit(0);
	}
	
}
