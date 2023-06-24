package sg.edu.iss.team6.interceptor;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import sg.edu.iss.team6.exception.UnauthorizedException;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor{


    @Override
    public boolean preHandle(HttpServletRequest request,
    HttpServletResponse response, Object handler) throws IOException, UnauthorizedException {

        System.out.println("Intercepting: " + request.getRequestURI());
        HttpSession session = request.getSession();

        String uri = request.getRequestURI();

        if (uri.startsWith("/login")) {
            return true;
        }

        String username = null;
        // check if the user already has set attribute userLogin
        if (session.getAttribute("username") != null){
            username = (String) session.getAttribute("username");
        }
        else if (request.getHeader("X-Username") != null) {
            username = request.getHeader("X-Username");
        }

        if (username == null){
            response.sendRedirect("/login");
            return false;
        }


        if (uri.startsWith("/admin") && username.charAt(0)!='a') {
            response.sendRedirect("/login");
        }

        if (uri.startsWith("/student") && username.charAt(0)!='s') {
            response.sendRedirect("/login");
        }

        if (uri.startsWith("/lecturer") && username.charAt(0)!='l') {
            response.sendRedirect("/login");
        }



        return true;

    }


}
