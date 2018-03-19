package cn.bysj;

import cn.bysj.core.mapper.ApplicationMapper;
import cn.bysj.core.pojo.Application;
import cn.bysj.core.pojo.ApplicationExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BysjBoot2ApplicationTests {

	@Autowired
	private ApplicationMapper applicationMapper;

	@Test
	public void contextLoads() {
		List<Application> applications = applicationMapper.selectByExample(new ApplicationExample());
		System.out.println(applications.size() + "------------------------------");
	}

}
