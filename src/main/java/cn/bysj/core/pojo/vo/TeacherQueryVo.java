package cn.bysj.core.pojo.vo;

/**
 * 
 * <p>Title: TeacherQueryVo</p>
 * <p>Description:包装类，用于页面向action传递参数，将数据传到mybatis </p>
 * @author	it小祥
 * @version 1.0
 */
public class TeacherQueryVo {
	
	//分页参数
	private PageQuery pageQuery;
	//用户的查询条件
	private TeacherCustom teacherCustom;
	//可以扩展多个属性
	//....
	public PageQuery getPageQuery() {
		return pageQuery;
	}
	public void setPageQuery(PageQuery pageQuery) {
		this.pageQuery = pageQuery;
	}
	public TeacherCustom getTeacherCustom() {
		return teacherCustom;
	}
	public void setTeacherCustom(TeacherCustom teacherCustom) {
		this.teacherCustom = teacherCustom;
	}

	
	
	

}
