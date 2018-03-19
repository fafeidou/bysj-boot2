package cn.bysj.core.mapper;

import cn.bysj.core.pojo.Application;
import cn.bysj.core.pojo.ApplicationExample;
import cn.bysj.core.pojo.ApplicationKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ApplicationMapper {
    int countByExample(ApplicationExample example);

    int deleteByExample(ApplicationExample example);

    int deleteByPrimaryKey(ApplicationKey key);

    int insert(Application record);

    int insertSelective(Application record);

    List<Application> selectByExample(ApplicationExample example);

    Application selectByPrimaryKey(ApplicationKey key);

    int updateByExampleSelective(@Param("record") Application record, @Param("example") ApplicationExample example);

    int updateByExample(@Param("record") Application record, @Param("example") ApplicationExample example);

    int updateByPrimaryKeySelective(Application record);

    int updateByPrimaryKey(Application record);
}