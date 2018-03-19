package cn.bysj.core.mapper;

import cn.bysj.core.pojo.Thesistopic;
import cn.bysj.core.pojo.ThesistopicExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ThesistopicMapper {
    int countByExample(ThesistopicExample example);

    int deleteByExample(ThesistopicExample example);

    int deleteByPrimaryKey(Integer thesisTopicId);

    int insert(Thesistopic record);

    int insertSelective(Thesistopic record);

    List<Thesistopic> selectByExample(ThesistopicExample example);

    Thesistopic selectByPrimaryKey(Integer thesisTopicId);

    int updateByExampleSelective(@Param("record") Thesistopic record, @Param("example") ThesistopicExample example);

    int updateByExample(@Param("record") Thesistopic record, @Param("example") ThesistopicExample example);

    int updateByPrimaryKeySelective(Thesistopic record);

    int updateByPrimaryKey(Thesistopic record);
}