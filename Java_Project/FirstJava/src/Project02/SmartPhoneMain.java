package Project02;

public class SmartPhoneMain {
	private static SmartPhone user;

	public static void main(String[] args) {
		SmartPhone user = new SmartPhone();
		showMenu();
		
	

}

	  static void showMenu() {
		// TODO Auto-generated method stub
		user.printData(user);
		user.getData(user);
		user.updateData(user);
	}
}

