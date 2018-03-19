package cn.bysj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@MapperScan("cn.bysj.core")
@ImportResource(locations = {"classpath:config/captcha.xml"})
@PropertySource("classpath:config/init.properties")
public class BysjBoot2Application {

	public static void main(String[] args) {
		SpringApplication.run(BysjBoot2Application.class, args);
	}
}
