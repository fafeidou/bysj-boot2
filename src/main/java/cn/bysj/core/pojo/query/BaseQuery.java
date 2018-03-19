package cn.bysj.core.pojo.query;

import java.io.Serializable;

/**
 * 条件对象公用对象
 * @author lx
 */
public class BaseQuery implements Serializable{

	private static final long serialVersionUID = 1L;
	//定义常量 每页数
	public final static int DEFAULT_SIZE = 10;
	//每页数
	protected int pageSize = DEFAULT_SIZE;
	//起始行
	protected int startRow;//起始行
	//结束行
	protected int endRow; 
	//页码
	protected int pageNo = 1;
	//Sql查询字段
	protected String fields;
	//是否使用limit 0 ： 不用    1 ：用
	protected int isLimit;
	
	public int getIsLimit() {
		return isLimit;
	}
	public void setIsLimit(int isLimit) {
		this.isLimit = isLimit;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
		this.startRow = (pageNo-1)*this.pageSize;
	}
	public int getPageSize() {
		return pageSize;
	}
	public BaseQuery setPageSize(int pageSize) {  
		this.pageSize = pageSize;  
		this.startRow =  (pageNo-1)*this.pageSize; 
		this.endRow = this.startRow + this.pageSize;     
		return this;
	}
	public int getStartRow() {
		return startRow;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	
	public int getEndRow() {
		return endRow;
	}
	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}
	public String getFields() {
		return fields;
	}
	public void setFields(String fields) {
		this.fields = fields;
	}
	
}
