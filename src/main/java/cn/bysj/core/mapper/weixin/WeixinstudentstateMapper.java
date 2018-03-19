package cn.bysj.core.mapper.weixin;

import cn.bysj.core.pojo.weixin.Weixinstudentstate;
import cn.bysj.core.pojo.weixin.WeixinstudentstateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WeixinstudentstateMapper {
    int countByExample(WeixinstudentstateExample example);

    int deleteByExample(WeixinstudentstateExample example);

    int deleteByPrimaryKey(String fromUserName);

    int insert(Weixinstudentstate record);

    int insertSelective(Weixinstudentstate record);

    List<Weixinstudentstate> selectByExample(WeixinstudentstateExample example);

    Weixinstudentstate selectByPrimaryKey(String fromUserName);

    int updateByExampleSelective(@Param("record") Weixinstudentstate record, @Param("example") WeixinstudentstateExample example);

    int updateByExample(@Param("record") Weixinstudentstate record, @Param("example") WeixinstudentstateExample example);

    int updateByPrimaryKeySelective(Weixinstudentstate record);

    int updateByPrimaryKey(Weixinstudentstate record);
}