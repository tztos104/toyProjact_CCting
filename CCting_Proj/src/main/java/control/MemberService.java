package control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MemberService {
	// 선언만 한다고 했었나?
	void execute(HttpServletRequest request, HttpServletResponse response);
}
