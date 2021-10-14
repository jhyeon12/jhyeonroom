package chapter05;

public class Member {
	String name;
	String phoneNum;
	String major;
	int grade;
	String email;
	int birthday;
	String address;
	

	Member(String name, String phoneNum, String major, int grade, String email, int birthday, String address) {
		this.name = name;
		this.phoneNum = phoneNum;
		this.major = major;
		this.grade = grade;
		this.email = email;
		this.birthday = birthday;
		this.address = address;
	}

	Member(String name, String phoneNum, String major, int studentClass, String email) {
		this.name = name;
		this.phoneNum = phoneNum;
		this.major = major;
		this.grade = studentClass;
		this.email = email;
	}

	public void print() {
		System.out.println("이름: " + name);
		System.out.println("전화번호: " + phoneNum);
		System.out.println("전공: " + major);
		System.out.println("학년: " + grade + "학년");
		System.out.println("이메일: " + email);
		if (birthday != 0 && address != null) {

			System.out.println("생년월일: " + birthday);
			System.out.println("주소: " + address);
		}
	}

	public static void main(String[] args) {
		Member m1 = new Member("aaa\n", "010-7777-7777\n", "동시통역\n", 3, "aaa@gmail.com\n", 930101, "서울\n");
		m1.print();
		System.out.println("\n=========================================\n");
		Member m2 = new Member("bbb", "010-7777-7777", "동시통역", 3, "aaa@gmail.com");
		m2.print();
	}
}
