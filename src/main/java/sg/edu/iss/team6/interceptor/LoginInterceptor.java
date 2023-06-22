package sg.edu.iss.team6.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {


	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	throws IOException{
		HttpSession session = request.getSession();
		if(session.getAttribute("username")!= null)
			return true;

		String[] splitURI = request.getRequestURI().split("/");
		if(splitURI[splitURI.length-1].equals("login"))
			return true;

		response.sendRedirect("/login");
		return false;
	}
}
