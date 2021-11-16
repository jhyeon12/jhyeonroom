package kiosk;




@SuppressWarnings("serial")
public class Input_BadInput extends Exception {
	
	// 잘못된 값을 입력했는지 체크하는 예외 처리 담당 클래스

	private final String isEmpty = "다시 입력하세요";
	private final String wrongMenu = "해당 상품은 없는 상품입니다.";
	private final String wrongMenuInput = "올바른 정보를 입력하세요";
	private final String wrongInput = "잘못 입력하셨습니다.";

	public Input_BadInput(String message) {
		System.out.println(message);
	}

	public Input_BadInput() {

	}

	// 입력이 공백인지 확인 : boolean 반환
	private boolean isEmpty(String info) {
		boolean chk = false;
		if (info.trim().isEmpty()) {
			chk = true;
		}
		return chk;
	}
	
	

	// 입력이 공백인지 확인하고 공백이 아닐때만 info 반환
	public String chkInfo(String info) throws Input_BadInput {
		if (isEmpty(info)) {
			throw new Input_BadInput(isEmpty);
		} else {
			return info;
		}
	}

	// 올바를 메뉴를 선택했는지 확인
	public String chkMenuNum(String info, int listSize) throws Input_BadInput, NumberFormatException {
		int choice = 0;
		if(isEmpty(info)) {
			throw new Input_BadInput(isEmpty);
		}else {
			
			try {
				choice = Integer.parseInt(info);
				
				if(!(choice > 0 && choice <= listSize)) {
					throw new Input_BadInput(wrongMenu);
				}
				
			} catch (NumberFormatException e) {
				throw new Input_BadInput(wrongMenuInput);
			}
			
		}
		
		return String.valueOf(choice);
	}
	
	public int isVailedSelectOne(int info, int listSize) throws Input_BadInput {
	     if(!((info > 0 && info <=listSize)||info == 32-48 || info == 13-48)) {
	    	 throw new Input_BadInput(wrongInput);
	     }else {
	    	 return info;
	     }
	}
	
	public int isVailedSelectOne2(int info, int listSize) throws Input_BadInput{
		if(!((info > 0 && info <=listSize)||info == 32-48 || info == 13-48 ||info == 44-48 || info ==46-48 || info == 47-48)) {
			throw new Input_BadInput(wrongInput);
		}else {
			return info;
		}
	}
	
	public int isVailedSelectOne3(int info, int listSize) throws Input_BadInput{
		if(!((info > 0 && info <=listSize)|| info == 32-48)) {
			throw new Input_BadInput(wrongInput);
		}else {
			return info;
		}
	}
	
	public int isVailedSelectOne4(int info) throws Input_BadInput {
		if(!(info ==32-48 || info == 13-48 || info == 43-48 || info ==45-48 )) {
			throw new Input_BadInput(wrongInput);
		}else {
			return info;
		}
	}
	
	public int lastCheck(int info) throws Input_BadInput {
	     if(!(info == 32-48 || info == 13-48)) {
	    	 throw new Input_BadInput(wrongInput);
	     }else {
	    	 return info;
	     }
	}
	
	
	
	
	
	

}