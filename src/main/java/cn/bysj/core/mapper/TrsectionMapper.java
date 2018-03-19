package cn.bysj.core.mapper;

import cn.bysj.core.pojo.Trsection;
import cn.bysj.core.pojo.TrsectionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TrsectionMapper {
    int countByExample(TrsectionExample example);

    int deleteByExample(TrsectionExample example);

    int deleteByPrimaryKey(Integer trsectionId);

    int insert(Trsection record);

    int insertSelective(Trsection record);

    List<Trsection> selectByExample(TrsectionExample example);

    Trsection selectByPrimaryKey(Integer trsectionId);

    int updateByExampleSelective(@Param("record") Trsection record, @Param("example") TrsectionExample example);

    int updateByExample(@Param("record") Trsection record, @Param("example") TrsectionExample example);

    int updateByPrimaryKeySelective(Trsection record);

    int updateByPrimaryKey(Trsection record);
}