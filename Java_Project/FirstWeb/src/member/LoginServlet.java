package member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 어노테이션: 자바 문법아니고, 주석아니다. 약속된 추가적인 처리를 해주는 약속 
//@Override
@WebServlet({ "/Login", "/login" })
public class LoginServlet extends HttpServlet {
    //사용자가 요청할때 Get 방식으로 요청했을때 처리되는 메소드  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	// 사용자가 요청할때 Post 방식으로 요청 했을 때 처리되는 메소드
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Post 방식으로 요청 처리!!!");
		doGet(request, response);
	}

}
