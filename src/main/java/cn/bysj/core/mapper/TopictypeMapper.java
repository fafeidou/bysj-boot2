package cn.bysj.core.mapper;

import cn.bysj.core.pojo.Topictype;
import cn.bysj.core.pojo.TopictypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TopictypeMapper {
    int countByExample(TopictypeExample example);

    int deleteByExample(TopictypeExample example);

    int deleteByPrimaryKey(Integer topicTypeId);

    int insert(Topictype record);

    int insertSelective(Topictype record);

    List<Topictype> selectByExample(TopictypeExample example);

    Topictype selectByPrimaryKey(Integer topicTypeId);

    int updateByExampleSelective(@Param("record") Topictype record, @Param("example") TopictypeExample example);

    int updateByExample(@Param("record") Topictype record, @Param("example") TopictypeExample example);

    int updateByPrimaryKeySelective(Topictype record);

    int updateByPrimaryKey(Topictype record);
}