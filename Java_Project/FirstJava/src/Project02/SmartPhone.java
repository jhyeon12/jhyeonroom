package Project02;
import java.util.Scanner;
public class SmartPhone {
  //아래의 데이터를 저장 이름, 전화번호, 이메일, 주소, 생일, 그룹
   private String name;
   private String phoneNum;
   private String mail;
   private String address;
   private int birth;
   private String group;
   Scanner scanner = new Scanner(System.in);
	

// getter() 와 setter() 정의
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getPhoneNum() {
	return phoneNum;
}
public void setPhoneNum(String phoneNum) {
	this.phoneNum = phoneNum;
}
public String getMail() {
	return mail;
}
public void setMail(String mail) {
	this.mail = mail;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public int getBirth() {
	return birth;
}
public void setBirth(int birth) {
	this.birth = birth;
}
public String getGroup() {
	return group;
}
public void setGroup(String group) {
	this.group = group;
}
   
public SmartPhone() {}
// 생성자
public SmartPhone(String name, String phoneNum, String mail, String address, int birth, String group) {
	this.name = name;
	this.phoneNum = phoneNum;
	this.mail = mail;
	this.address = address;
	this.birth = birth;
	this.group = group;
	
}
// 정보 입력 메소드
public void printData(SmartPhone user) {

	System.out.println("이름을 입력해주세요.");
	System.out.print("> ");
	user.setName(scanner.nextLine());

	System.out.println("전화번호를 입력해주세요.");
	System.out.print("> ");
	user.setPhoneNum(scanner.nextLine());

	System.out.println("이메일을 입력해주세요.");
	System.out.print("> ");
	user.setMail(scanner.nextLine());

	System.out.println("주소를 입력해주세요.");
	System.out.print("> ");
	user.setAddress(scanner.nextLine());

	System.out.println("생일을 입력해주세요.");
	System.out.print("> ");
	user.setBirth(scanner.nextInt());
	scanner.nextLine();

	System.out.println("그룹을 입력해주세요.");
	System.out.print("> ");
	user.setGroup(scanner.nextLine());
}
// user에 저장된 연락처 정보를 getter로 가져와 출력
public void getData(SmartPhone user) {
	System.out.println("\n-------------------------------------------------------\n");
	System.out.println("이름: " + user.getName());
	System.out.println("전화번호: " + user.getPhoneNum());
	System.out.println("이메일: " + user.getMail());
	System.out.println("주소: " + user.getAddress());
	System.out.println("생일: " + user.getBirth());
	System.out.println("그룹: " + user.getGroup());
	System.out.println("\n-------------------------------------------------------\n");
	
}
// 저장된 연락처 정보를 수정할 수 있도록 하는 메소드.
public void updateData(SmartPhone user) {
	while(true) {
		System.out.println("정보를 수정하시겠습니까? Y/N");
		System.out.print("> ");
		String yesNo = scanner.nextLine();
		if (yesNo.equalsIgnoreCase("y")) {
			// update 메소드 실행
			user.printData(user);
			user.getData(user);
		} else {
			scanner.close();
			break;
		}
		
	}
}
public void deleteData(SmartPhone user) {
	
}

}
