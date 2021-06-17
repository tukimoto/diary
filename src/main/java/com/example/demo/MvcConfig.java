package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/mypage").setViewName("mypage");
		registry.addViewController("/").setViewName("index");
		registry.addViewController("/log").setViewName("log");
		registry.addViewController("/login").setViewName("login");
	}

}