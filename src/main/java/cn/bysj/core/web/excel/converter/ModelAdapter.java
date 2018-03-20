package cn.bysj.core.web.excel.converter;


import cn.bysj.core.web.excel.mapper.ExcelConverterMapperHandler;
import cn.bysj.core.web.excel.vo.ListLine;
import cn.bysj.core.web.excel.vo.ListRow;



/**  
 * 创建时间：2015-12-15下午2:12:56  
 * 项目名称：excel  
 * @author shizhongtao  
 * @version 1.0   
 * 文件名称：Convertor.java  
 */
public interface ModelAdapter  {
	ListLine marshal(Object source,ExcelConverterMapperHandler handler);
	Object unmarshal(ListRow source, ExcelConverterMapperHandler handler);
}
 