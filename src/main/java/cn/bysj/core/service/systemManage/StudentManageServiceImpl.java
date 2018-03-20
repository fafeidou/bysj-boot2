package cn.bysj.core.service.systemManage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bysj.core.mapper.StudentMapper;
import cn.bysj.core.pojo.Student;
import cn.bysj.core.pojo.StudentExample;

/**
 * 学生管理 ClassName: StudentManageService
 * 
 * @Description: TODO
 * @author it小祥
 * @date 2016年11月8日
 */
@Service
public class StudentManageServiceImpl implements StudentManageService {
	@Autowired
	private StudentMapper studentMapper;

	@Override
	public List<Student> getAllStudent() {
		StudentExample example = new StudentExample();
		return studentMapper.selectByExample(example);
	}

}
