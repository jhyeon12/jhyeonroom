package mm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import mm.dao.MemberDao;

@Configuration
public class JavaConfig {
	
	// MemberDao
	@Bean(name = "dao")
	@Scope(value = "Singleton")
	public MemberDao memberDao() {
		return new MemberDao();
	}
	
	// MemberRegService : dao  주입
	// ChangePasswordService : dao 주입

}
