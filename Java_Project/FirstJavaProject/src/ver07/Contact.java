package ver07;

public abstract class Contact implements ShowData {

//	저장 정보
//	이름, 전화번호, 이메일, 주소, 생일, 그룹
	// 캡슐화 : 외부(다른 인스턴스)에서 변수를 직접 참조하지 못하도록 하는것.
	private String name; // 이름
	private String phoneNumber; // 전화번호 : 000-0000-0000
	private String email; // 이메일
	private String address; // 주소
	private String birthday; // 생일: 20111022
	private String group; // 그룹

	// 생성자 : 변수들을 초기화 할때 사용
	public Contact(String name, String phoneNumber, String email, String address, String birthday, String group) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.address = address;
		this.birthday = birthday;
		this.group = group;

	}

	public Contact(String name, String phoneNumber) {
		// this.name = name;
		// this.phoneNumber = phoneNumber;
		this(name, phoneNumber, null, null, null, null);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

//	기능: 위 데이터를 출력하는 기능
	public void printContact() {
		System.out.println("이름:" + name);
		System.out.println("전화번호:" + phoneNumber);
		System.out.println("이메일:" + email);
		System.out.println("주소:" + address);
		System.out.println("생일" + birthday);
		System.out.println("그룹:" + group);
	}

	@Override
	public String toString() {
		return "Contact [name=" + name + ", phoneNumber=" + phoneNumber + ", email=" + email + ", address=" + address
				+ ", birthday=" + birthday + ", group=" + group + "]";
	}

	// HashSet<E> : 중복의 조건은 개발자가 정의 할 수 있다.
	// hashCode(), equals() 메소드를 오버라이딩 해준다.

	@Override
	public boolean equals(Object obj) {

		boolean result = false;

		// name와 전달받은 객체가 가지는 문자열 비교
		if (obj != null && obj instanceof Contact) {
			Contact c = (Contact) obj;
			if (this.name.contentEquals(c.getName())) {
				result = true;
				System.out.println("이름이 중복된 데이터는 저장되지 않습니다.");
			} else {
				System.out.println("데이터가 저장되었습니다.");
			}
		}
		return result;
	}

	@Override
	public int hashCode() {
		return this.name.charAt(0) % 10;
	}

}
