package chapter12;

import java.io.Serializable;

public class Circle implements Serializable {

	int xPos;
	int yPos;
	transient double rad;

	public Circle(int xPos, int yPos, double rad) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.rad = rad;
	}
	
	public void showCircleInfo() {
		System.out.printf("[%d, %d]\n", xPos, yPos);
		System.out.println("rad : " + rad);
	}
	

}
