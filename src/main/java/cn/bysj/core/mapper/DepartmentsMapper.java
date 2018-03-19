package cn.bysj.core.mapper;

import cn.bysj.core.pojo.Departments;
import cn.bysj.core.pojo.DepartmentsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DepartmentsMapper {
    int countByExample(DepartmentsExample example);

    int deleteByExample(DepartmentsExample example);

    int deleteByPrimaryKey(Integer departmentId);

    int insert(Departments record);

    int insertSelective(Departments record);

    List<Departments> selectByExample(DepartmentsExample example);

    Departments selectByPrimaryKey(Integer departmentId);

    int updateByExampleSelective(@Param("record") Departments record, @Param("example") DepartmentsExample example);

    int updateByExample(@Param("record") Departments record, @Param("example") DepartmentsExample example);

    int updateByPrimaryKeySelective(Departments record);

    int updateByPrimaryKey(Departments record);
}