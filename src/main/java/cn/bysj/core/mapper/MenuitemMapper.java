package cn.bysj.core.mapper;

import cn.bysj.core.pojo.Menuitem;
import cn.bysj.core.pojo.MenuitemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MenuitemMapper {
    int countByExample(MenuitemExample example);

    int deleteByExample(MenuitemExample example);

    int deleteByPrimaryKey(Integer menuitemId);

    int insert(Menuitem record);

    int insertSelective(Menuitem record);

    List<Menuitem> selectByExample(MenuitemExample example);

    Menuitem selectByPrimaryKey(Integer menuitemId);

    int updateByExampleSelective(@Param("record") Menuitem record, @Param("example") MenuitemExample example);

    int updateByExample(@Param("record") Menuitem record, @Param("example") MenuitemExample example);

    int updateByPrimaryKeySelective(Menuitem record);

    int updateByPrimaryKey(Menuitem record);
}