package chapter11;

import java.util.HashSet;
import java.util.Iterator;
import java.util.HashSet;
public class HashSetTest2 {

public static void main(String[] args) {
		
		HashSet<SimpleNumber> set = new HashSet<SimpleNumber>();
		
		set.add(new SimpleNumber(10));
		set.add(new SimpleNumber(20));
		set.add(new SimpleNumber(10));
		
		System.out.println("요소의 개수 : " + set.size());
		
		Iterator<SimpleNumber> itr = set.iterator();
		while(itr.hasNext()) {
			System.out.println(itr.next());
		}
		
		
		
		
		
		
		
		
		

	}
}
