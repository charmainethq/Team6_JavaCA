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
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("username") != null) {
            String username = (String) session.getAttribute("username");
            String role = username.substring(0, 3); // Extract the role from the username

            if (role.equals("adm") && request.getRequestURI().startsWith("/admin")) {
                return true; // Allow access for admin role to /admin URLs
            } else if (role.equals("stu") && request.getRequestURI().startsWith("/student")) {
                return true; // Allow access for student role to /student URLs
            } else if (role.equals("lec") && request.getRequestURI().startsWith("/lecturer")) {
                return true; // Allow access for lecturer role to /lecturer URLs
            }
        }

        response.sendRedirect("/login"); // Redirect unauthorized users to the login page
        return false;
    }
}


