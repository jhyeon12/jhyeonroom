package exam;

import java.util.Scanner;

import javax.swing.JOptionPane;

public class HighLowGame extends Thread {

	static boolean inputCheck = false;

	public static void main(String[] args) {

		int ranNum = (int) (Math.random() * 101);

		CountDownThr t = new CountDownThr();
		t.start();

		while (true) {

			String input = JOptionPane.showInputDialog("1~100 까지의 숫자 중 하나를 입력하세요.");

			System.out.println("입력하신 숫자는 " + input + "입니다.");

			int num = Integer.parseInt(input);

			if (num < ranNum) {
				System.out.println("UP");
			} else if (num > ranNum) {
				System.out.println("DOWN");
			} else if (num == ranNum) {
				System.out.println("미션 성공!!");
				inputCheck = true;
				
				
			}
			
		}
		
	}

}
