package cn.bysj;

import cn.bysj.core.web.captcha.JcaptchaServlet;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@MapperScan("cn.bysj.core.mapper")
@ImportResource(locations = {"classpath:config/captcha.xml","classpath:config/util.xml"})
@PropertySource("classpath:config/init.properties")
@Controller
public class BysjBoot2Application extends SpringBootServletInitializer{




	public static void main(String[] args) {
		SpringApplication.run(BysjBoot2Application.class, args);
	}

	@RequestMapping("/")
	public  String  index(){
		return "redirect:/system/login.do";
	}


	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BysjBoot2Application.class);
	}


	@Bean
	public ServletRegistrationBean<JcaptchaServlet> testServletRegistration() {
		ServletRegistrationBean<JcaptchaServlet> registration = new ServletRegistrationBean<>(new JcaptchaServlet());
		registration.addUrlMappings("/captcha.svl");
		return registration;
	}
//	@Bean
//	public FilterRegistrationBean testFilterRegistration() {
//		FilterRegistrationBean registration = new FilterRegistrationBean();
//		registration.setFilter(new Filter() {
//			@Override
//			public void init(FilterConfig filterConfig) throws ServletException {
//			}
//			@Override
//			public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//					chain.doFilter(request,response);
//			}
//
//			@Override
//			public void destroy() {
//
//			}
//		});
//		registration.addUrlPatterns("*.do");
//		registration.addInitParameter("paramName", "paramValue");
//		registration.setName("testFilter");
//		registration.setOrder(1);
//		return registration;
//	}
}
