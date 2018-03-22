package cn.bysj;

import cn.bysj.core.mapper.ApplicationMapper;
import cn.bysj.core.pojo.Application;
import cn.bysj.core.pojo.ApplicationExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	}

}
