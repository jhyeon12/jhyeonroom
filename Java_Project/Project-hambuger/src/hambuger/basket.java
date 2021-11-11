package hambuger;

import java.util.LinkedList;
import java.util.List;
public class basket {

	public List<Sales> list;
	
	
	public basket(){
		list = new LinkedList<Sales>();
	}
	
// add(): sales 객체 매개변수로 받아서 리스트에 추가
	public void add(Sales s)  {
		list.add(s);
	}
	
	
// update(): sales객체 index 정수를 매개변수로 받아 리스트의 index번 인덱스에 있는 객체를 sales로 덮어씌운다.
	public void update(Sales s, int index) {
		list.set(index, s);
	}
	
// delete(): sales 객체를 매개변수로 받아서 해당객체 삭제 (삭제하는 기준 : 상품명이 같으면 삭제)
	public void delete(Sales s) {
	
		list.remove(s);
	}
	
	
}
