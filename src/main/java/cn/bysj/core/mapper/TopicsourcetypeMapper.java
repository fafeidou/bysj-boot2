package cn.bysj.core.mapper;

import cn.bysj.core.pojo.Topicsourcetype;
import cn.bysj.core.pojo.TopicsourcetypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TopicsourcetypeMapper {
    int countByExample(TopicsourcetypeExample example);

    int deleteByExample(TopicsourcetypeExample example);

    int deleteByPrimaryKey(Integer topicSourceTypeId);

    int insert(Topicsourcetype record);

    int insertSelective(Topicsourcetype record);

    List<Topicsourcetype> selectByExample(TopicsourcetypeExample example);

    Topicsourcetype selectByPrimaryKey(Integer topicSourceTypeId);

    int updateByExampleSelective(@Param("record") Topicsourcetype record, @Param("example") TopicsourcetypeExample example);

    int updateByExample(@Param("record") Topicsourcetype record, @Param("example") TopicsourcetypeExample example);

    int updateByPrimaryKeySelective(Topicsourcetype record);

    int updateByPrimaryKey(Topicsourcetype record);
}