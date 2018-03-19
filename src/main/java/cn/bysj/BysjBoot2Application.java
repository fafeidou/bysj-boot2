package cn.bysj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.bysj.core")
public class BysjBoot2Application {

	public static void main(String[] args) {
		SpringApplication.run(BysjBoot2Application.class, args);
	}
}
