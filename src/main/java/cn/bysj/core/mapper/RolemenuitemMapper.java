package cn.bysj.core.mapper;

import cn.bysj.core.pojo.RolemenuitemExample;
import cn.bysj.core.pojo.RolemenuitemKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RolemenuitemMapper {
    int countByExample(RolemenuitemExample example);

    int deleteByExample(RolemenuitemExample example);

    int deleteByPrimaryKey(RolemenuitemKey key);

    int insert(RolemenuitemKey record);

    int insertSelective(RolemenuitemKey record);

    List<RolemenuitemKey> selectByExample(RolemenuitemExample example);

    int updateByExampleSelective(@Param("record") RolemenuitemKey record, @Param("example") RolemenuitemExample example);

    int updateByExample(@Param("record") RolemenuitemKey record, @Param("example") RolemenuitemExample example);
}