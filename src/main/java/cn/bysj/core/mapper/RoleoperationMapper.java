package cn.bysj.core.mapper;

import cn.bysj.core.pojo.Roleoperation;
import cn.bysj.core.pojo.RoleoperationExample;
import cn.bysj.core.pojo.RoleoperationKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleoperationMapper {
    int countByExample(RoleoperationExample example);

    int deleteByExample(RoleoperationExample example);

    int deleteByPrimaryKey(RoleoperationKey key);

    int insert(Roleoperation record);

    int insertSelective(Roleoperation record);

    List<Roleoperation> selectByExample(RoleoperationExample example);

    Roleoperation selectByPrimaryKey(RoleoperationKey key);

    int updateByExampleSelective(@Param("record") Roleoperation record, @Param("example") RoleoperationExample example);

    int updateByExample(@Param("record") Roleoperation record, @Param("example") RoleoperationExample example);

    int updateByPrimaryKeySelective(Roleoperation record);

    int updateByPrimaryKey(Roleoperation record);
}