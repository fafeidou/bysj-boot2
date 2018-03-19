package cn.bysj.core.mapper;

import cn.bysj.core.pojo.Systemmanager;
import cn.bysj.core.pojo.SystemmanagerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SystemmanagerMapper {
    int countByExample(SystemmanagerExample example);

    int deleteByExample(SystemmanagerExample example);

    int deleteByPrimaryKey(Integer systemManagerId);

    int insert(Systemmanager record);

    int insertSelective(Systemmanager record);

    List<Systemmanager> selectByExample(SystemmanagerExample example);

    Systemmanager selectByPrimaryKey(Integer systemManagerId);

    int updateByExampleSelective(@Param("record") Systemmanager record, @Param("example") SystemmanagerExample example);

    int updateByExample(@Param("record") Systemmanager record, @Param("example") SystemmanagerExample example);

    int updateByPrimaryKeySelective(Systemmanager record);

    int updateByPrimaryKey(Systemmanager record);
}