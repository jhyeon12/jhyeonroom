package ver07;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

public class SmartPhoneUsedSet {

	// ① Contact 클래스의 인스턴스 10개를 저장 할 수 있는 배열을 정의합시다.
		// ② 배열에 인스턴스를 저장하고, 수정하고, 삭제, 저장된 데이터의 리스트를 출력하는 메소드를 정의합니다.

		private Set<Contact> contacts;

		public static Scanner sc = new Scanner(System.in);

		// 생성자 : ArrayList() 생성
		public SmartPhoneUsedSet(int size) {
			contacts = new HashSet<Contact>(size);
		}

		// 배열에 요소를 추가하는 메소드 : 참조값을 전달 받아 배열에 추가 하는 기능
		public void addContact(Contact contact) {
			contacts.add(contact);
			//System.out.println("데이터가 저장되었습니다.");
		}

		// 사용자로 부터 데이터를 받아 Contact 객체를 생성하고 배열에 참조값을 저장 메소드 호출
		public void insertContact(int select) {
			System.out.println("연락처 등록을 시작합니다.....");
			System.out.println("이름 >>");
			// String name = getString();
			String name = getName();

			System.out.println("전화번호 >>");
			// String phoneNumber = getString();
			String phoneNumber = checkPhoneNumber();

//			if(checkPhoneNumber(phoneNumber)) {
//				System.out.println("입력하신 전화번호가 이미 존재 합니다.");
//				System.out.println("메뉴로 돌아갑니다.");
//				return;
//			}

			System.out.println("이메일 >>");
			String email = getString();

			System.out.println("주소 >>");
			String address = getString();

			System.out.println("생일 >>");
			String birthday = getString();

			System.out.println("그룹 >>");
			String group = getString();

			if (select == 1) {

				System.out.println("회사이름을 입력하세요. >>");
				String companyName = getString();
				System.out.println("부서이름을 입력하세요. >>");
				String divName = getString();
				System.out.println("직급을 입력하세요. >>");
				String job = getString();

				addContact(
						new CompanyContact(name, phoneNumber, email, address, birthday, group, companyName, divName, job));

			} else { // select == 2

				System.out.println("거래처 회사이름을 입력하세요. >>");
				String companyName = getString();
				System.out.println("거래품목을 입력하세요. >>");
				String product = getString();
				System.out.println("담당자의 직급을 입력하세요. >>");
				String job = getString();

				addContact(
						new CustomerContact(name, phoneNumber, email, address, birthday, group, companyName, product, job));

			}
		}

		// 2. 연락처 이름 이력 시에 공백에 대한 예외처리와 영문자와 한글만 허용하는 예외 처리를 해봅시다.
		// 이름 입렵식 공백 허용 하지 않고, 영문자와 한글만 가능하도록 처리
		private String getName() {

			String result = null;

			// 사용자에 문자열 받고 -> 예외 발생 여부 확인 -> 예외 발생 -> 예외처리
			while (true) {
				// 데이터 받기
				result = sc.nextLine().trim();

				// 잘못된 입력 판단 조건식
				if (result.length() > 0) {

					// 문저열에 영문자와 한글만 포함고 있는지 체크하는 변수
					boolean check = false;

					for (int i = 0; i < result.length(); i++) {

						char c = result.charAt(i);
						boolean c1 = c >= 'a' && c <= 'z'; // 입력문자가 소문자
						boolean c2 = c >= 'A' && c <= 'Z'; // 입력문자가 대문자
						boolean c3 = c >= '가' && c <= '힣'; // 입력문자가 한글

						if (!(c1 || c2 || c3)) {

							BadNameInputException bne = new BadNameInputException(
									"영문자 또는 한글이 아닌 문자가 입력되었습니다.\n확인 후 다시 입력해주세요.");
							try {
								throw bne;
							} catch (BadNameInputException e) {
								System.out.println(e.getMessage());
								check = true;
								break;
							}
						}
					}

					// 예외 없이 문자열 체크
					// 정상적으로 영문자와 한글이 입력된경우
					// check => true : 다시입력!, false : while(true) 블록을 탈출!
					// result 문자열을 현재 보관
					if (!check) {
						break; // while(true) 블록을 탈출
					}

				} else {
					// 예외 발생
					BadNameInputException bne = new BadNameInputException("공백 입력!!");
					try {
						throw bne;
					} catch (BadNameInputException e) {
						// 예외 처리 : 예외의 로그 등록
						System.out.println(e.getMessage());
						System.out.println("이름을 다시 입력해주세요");
					}
				}

			}

			return result;
		}

		// 사용자로부터 문자열을 입력 받고 -> 공백 여부를 확인 -> 공백이면 다시 입력 -> 입력받은 데이터를 반환
		private String getString() {
			String result = null;

			while (true) {
				result = sc.nextLine();
				result = result.trim(); // " 손흥민 " => "손흥민", " " -> ""
				if (result.length() == 0) {
					System.out.println("공백입력은 허용하지 않습니다. 다시 입력해주세요....");
				} else {
					break;
				}
			}
			return result;
		}

		// 전화번호를 받고 배열에 저장된 데이터와 비교 후 결과 를 반환
		private boolean checkPhoneNumber(String number) {
			boolean chk = false;
			// 배열 반복하면서 전화번호 비교 -> 같은 번호가 있으면 -> chk=true
			
			Iterator<Contact> itr = contacts.iterator();
			while(itr.hasNext()) {
				if (itr.next().getPhoneNumber().equals(number)) {
					chk = true;
					break;
				}
			}
			
			return chk;
		}

		
		// 3.전화번호 형식에 맞지 않을 때 예외처리를 하고 중복될 때 예외 상황이 발생하도록 하고 예외 처리를 합시다.
		private String checkPhoneNumber() {

			String number = null;

			while (true) {
				
				number = sc.nextLine().trim();
				// 전화번호 존재 체크
				boolean chk = false;
				if (number.length() > 0) {

					try {
						
						// 전화번호 형식 체크
						String pattern = "^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}$";
						// 정규식으로 문자열 패턴 체크
						boolean regex = Pattern.matches(pattern, number);
						
						// 패턴과 맞지 않을 때 예외 처리!
						if(!regex) {
							throw new PhoneNumberPatternException();
						}
						
						Iterator<Contact> itr = contacts.iterator();
						while(itr.hasNext()) {
							if (itr.next().getPhoneNumber().equals(number)) {
								// 예외 처리
								throw new DuplicatePhoneNumberException();
							}
						}
						
					} catch (DuplicatePhoneNumberException | PhoneNumberPatternException e) {
						chk = true;
						System.out.println(e.getMessage()+" 다시 입력하세요.");
						//break;
					} 

					// 같은 번호가 존재하면 while문을 벋어나지 않는다
					if (!chk) {
						break;
					}
				} else {
					System.out.println("공백문자를 허용하지 않습니다. 다시입력하세요.");
				}

			}

			return number;

		}

		// 전체 데이터 출력 : 배열은 반복문
		public void printAllData() {

			System.out.println("리스트를 출력합니다.(" + contacts.size() + "명)");
			System.out.println("============================");

//			for (int i = 0; i < cnt; i++) {
//				contacts[i].printContact();
//				System.out.println("------------------");
//			}
			// for-each
			for(Contact c : contacts) {
				c.printContact();
				System.out.println("------------------");
			}

		}

		// 간단한 정보 출력 메소드
		public void printAllSimpleData() {
			System.out.println("리스트를 출력합니다.(" + contacts.size() + "명)");
			System.out.println("============================");

//			for (int i = 0; i < cnt; i++) {
//				contacts[i].printSimpleData();
//				System.out.println("------------------");
//			}
			for(Contact c : contacts) {
				c.printSimpleData();
				System.out.println("-------------------");
			}
		}

		// 이름으로 검색(index 찾기) -> 해당 데이터 출력
		public void searchPrint() {

			System.out.println("검색을 시작합니다.....");
			System.out.println("찾고자 하는 이름을 입력하세요. >>");
			String name = getString();
			
			boolean searchChk = false;
			
			Iterator<Contact> itr = contacts.iterator();
			while(itr.hasNext()) {
				 Contact c = itr.next();
				 if(c.getName().equals(name)) {
					 c.printContact();
					 searchChk = true;
					 break;
				 }
			}
			
			if(!searchChk) {
				System.out.println("검색하신 이름 " + name + " 의 데이터가 존재하지 않습니다.");
			}

			
		}

		// 이름으로 검색 -> 해당 데이터 삭제
		public void deleteContact() {

			System.out.println("데이터 삭제를 시작합니다.....");
			System.out.println("삭제하고자하는 데이터의 이름을 입력하세요. >>");
			String name = getString();

			Iterator<Contact> itr = contacts.iterator();
			
			boolean searchCheck = false;
			
			while(itr.hasNext()) {
				if(itr.next().getName().equals(name)) {
					 itr.remove();
					 System.out.println(name + " 의 데이터가 삭제되었습니다.");
					 searchCheck = true;
					 break;
				}
			}

			if(!searchCheck) {
				System.out.println("검색하신 이름 " + name + " 의 데이터가 존재하지 않습니다.");
			}
			
		}

		// 이름으로 검색 -> 데이터 수정 : 수정할 데이터를 받아서 처리
		public void editContact() {

			System.out.println("데이터 수정을 시작합니다.....");
			System.out.println("변경을 원하는 정보의 이름을 입력하세요. >>");
			String name = getString();
			
			Contact contact = null;

			// 반복을 통해 이름이 같은 Contact 객체를 찾고 -> contact 변수에 참조값을 저장
			Iterator<Contact> itr = contacts.iterator();
			while(itr.hasNext()) {
				Contact c = itr.next();
				if(c.getName().equals(name)) {
					contact = c;
					break;
				}
			}

			if (contact == null) {
				System.out.println("찾으시는 이름 " + name + " 의 정보가 존재하지 않습니다.");
			} else {
				System.out.println("데이터 수정을 위해 각가의 데이터를 입력하세요.....");

				System.out.println("이름 >>");
				String ename = getString();

				System.out.println("전화번호 >>");
				String phoneNumber = getString();

				System.out.println("이메일 >>");
				String email = getString();

				System.out.println("주소 >>");
				String address = getString();

				System.out.println("생일 >>");
				String birthday = getString();

				System.out.println("그룹 >>");
				String group = getString();

				// 회사 동료인지 고객인지 분기
				if (contact instanceof CompanyContact) {

					CompanyContact companyContact = (CompanyContact) contact;

					System.out.println("회사이름을 입력하세요. >>");
					String companyName = getString();
					System.out.println("부서이름을 입력하세요. >>");
					String divName = getString();
					System.out.println("직급을 입력하세요. >>");
					String job = getString();

					companyContact.setName(ename);
					companyContact.setPhoneNumber(phoneNumber);
					companyContact.setEmail(email);
					companyContact.setAddress(address);
					companyContact.setBirthday(birthday);
					companyContact.setGroup(group);
					companyContact.setCompanyName(companyName);
					companyContact.setDivName(divName);
					companyContact.setJob(job);

				} else { // CustomerContact

					CustomerContact customerContact = (CustomerContact) contact;

					System.out.println("거래처 회사이름을 입력하세요. >>");
					String companyName = getString();
					System.out.println("거래품목을 입력하세요. >>");
					String product = getString();
					System.out.println("담당자의 직급을 입력하세요. >>");
					String job = getString();

					customerContact.setName(ename);
					customerContact.setPhoneNumber(phoneNumber);
					customerContact.setEmail(email);
					customerContact.setAddress(address);
					customerContact.setBirthday(birthday);
					customerContact.setGroup(group);
					customerContact.setCompanyName(companyName);
					customerContact.setProduct(product);
					customerContact.setJob(job);
				}

				System.out.println(name + " 의 정보가 수정되었습니다.");

			}

		}

}
