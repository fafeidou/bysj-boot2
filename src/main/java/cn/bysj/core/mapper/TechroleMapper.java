package cn.bysj.core.mapper;

import cn.bysj.core.pojo.TechroleExample;
import cn.bysj.core.pojo.TechroleKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TechroleMapper {
    int countByExample(TechroleExample example);

    int deleteByExample(TechroleExample example);

    int deleteByPrimaryKey(TechroleKey key);

    int insert(TechroleKey record);

    int insertSelective(TechroleKey record);

    List<TechroleKey> selectByExample(TechroleExample example);

    int updateByExampleSelective(@Param("record") TechroleKey record, @Param("example") TechroleExample example);

    int updateByExample(@Param("record") TechroleKey record, @Param("example") TechroleExample example);
}