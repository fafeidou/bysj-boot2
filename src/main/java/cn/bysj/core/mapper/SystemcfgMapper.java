package cn.bysj.core.mapper;

import cn.bysj.core.pojo.Systemcfg;
import cn.bysj.core.pojo.SystemcfgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SystemcfgMapper {
    int countByExample(SystemcfgExample example);

    int deleteByExample(SystemcfgExample example);

    int deleteByPrimaryKey(Integer systemCfgId);

    int insert(Systemcfg record);

    int insertSelective(Systemcfg record);

    List<Systemcfg> selectByExample(SystemcfgExample example);

    Systemcfg selectByPrimaryKey(Integer systemCfgId);

    int updateByExampleSelective(@Param("record") Systemcfg record, @Param("example") SystemcfgExample example);

    int updateByExample(@Param("record") Systemcfg record, @Param("example") SystemcfgExample example);

    int updateByPrimaryKeySelective(Systemcfg record);

    int updateByPrimaryKey(Systemcfg record);
}