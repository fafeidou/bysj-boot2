package cn.bysj.core.service.weixin;

import cn.bysj.core.pojo.weixin.Weixinstudentstate;

public interface WeixinstudentstateService {
	/**
	 * 
	 * @Description: 通过主键查询用户状态
	 * @param @param
	 *            fromUserName
	 * @param @return
	 * @return Weixinstudentstate
	 * @throws @author
	 *             it小祥
	 * @date 2017年2月5日
	 */
	Weixinstudentstate getStateByFromUserName(String fromUserName);

	/**
	 * 
	 * @Description: 添加一条信息
	 * @param @param
	 *            weixinstudentstate
	 * @return void
	 * @throws @author
	 *             it小祥
	 * @date 2017年2月5日
	 */
	void AddWeiXinStudentState(Weixinstudentstate weixinstudentstate);

	/**
	 * 
	 * @Description: 删除用户信息
	 * @param @param
	 *            fromUserName
	 * @return void
	 * @throws @author
	 *             it小祥
	 * @date 2017年2月5日
	 */
	void deleteWeiXinStudentStateByFromUserName(String fromUserName);
	
	Weixinstudentstate getStateByStudentId(Integer studentId);

}
