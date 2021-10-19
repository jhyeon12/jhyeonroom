package chapter09;

import java.util.ArrayList;
import java.util.List;

public class WrapperTest {

	public static void main(String[] args) {
		
		Integer iValue1 = new Integer(11);
		Integer iValue2 = 11; // AutoBoxing : 기본형 데이터를 인스턴스로 만드는것
		
		System.out.println(iValue1);
		System.out.println(iValue2);
		
		System.out.println("참조변수의 비교"+(iValue1 == iValue2));
		System.out.println("iValue1.equals(iValue2)"+ iValue1.equals(iValue2));
		
		int num = iValue1; // Auto UnBoxing : 래퍼 클래스의 인스턴스에서 값을 꺼내는것.
		int num2 = Integer.parseInt("11");
		
		// Collection 클래스들은 객체를 저장하는 목적으로 만드는 클래스
		List<Integer> list = new ArrayList<Integer>();
		list.add(11); // new Integer(11)

	}

}
