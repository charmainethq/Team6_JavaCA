package sg.edu.iss.team6.configuration;

import javax.annotation.processing.SupportedAnnotationTypes;
import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import sg.edu.iss.team6.interceptor.AuthenticationInterceptor;
import sg.edu.iss.team6.interceptor.LoginInterceptor;

@Component
public class WebAppConfig implements WebMvcConfigurer {
	/*
	@Autowired
	LoginInterceptor loginInterceptor;
	*/

	@Autowired
	AuthenticationInterceptor authenticationInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authenticationInterceptor)
		                .addPathPatterns("/admin/**")
				.addPathPatterns("/student/**")
				.addPathPatterns("/lecturer/**")
				.excludePathPatterns("/home");
	/**
	@Autowired
	LoginInterceptor loginInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/admin/**")
				.addPathPatterns("/student/**")
				.addPathPatterns("/lecturer/**")
				.excludePathPatterns("/home");
	**/
	}
}
