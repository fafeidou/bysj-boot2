package cn.bysj.core.controller.systemManage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统管理详情页 ClassName: DepartmentManageController
 * 
 * @Description: TODO
 * @author it小祥
 * @date 2016年11月5日
 */
@Controller
@RequestMapping("/manage/")
public class IndexManageController {
	@Autowired
	/*
	 * 跳转到管理员页面
	 */
	@RequestMapping("index.do")
	public String index(){
		return "/manager/index";
	}


}
