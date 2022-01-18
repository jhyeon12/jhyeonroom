package mm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import mm.dao.Dao;
import mm.domain.Member;
import mm.domain.RegRequest;
import mm.exception.DuplicateMemberException;

@Component("regService")
public class MemberRegService2 {

	
	@Autowired
	//@Qualifier("kkd")
	private Dao guestDao;  // Dao 타입의 bean을 자동 주입
	
	public MemberRegService2() {}
	
	
	public void regMember(RegRequest request) throws DuplicateMemberException {
		
		// 이메일 중복 체크
		Member member = guestDao.selectByEmail(request.getEmail());
		
		if(member != null) {
			throw new DuplicateMemberException("이미 존재하는 이메일");
		}
		
		guestDao.insert(request.toMember());
		System.out.println("[msg : 등록되었습니다.]");
	}
	
}
