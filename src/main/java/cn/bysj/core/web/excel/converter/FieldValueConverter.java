package cn.bysj.core.web.excel.converter;

import java.lang.reflect.Type;

import cn.bysj.core.web.excel.core.handler.ConverterHandler;
import cn.bysj.core.web.excel.vo.OutValue;



/**  
 * 创建时间：2015-12-15下午2:12:56  
 * 项目名称：excel  
 * @author shizhongtao  
 * @version 1.0   
 * 文件名称：FieldValueConverter.java  
 * 类说明：  这里面convertor是针对实体类的filed。主要用于扩展转换,目前版本中，convertor中必须有无参的构造方法。
 */
public interface FieldValueConverter extends ConverterMatcher {
	  OutValue toObject(Object source,ConverterHandler converterHandler);
	  Object fromString(String cell,ConverterHandler converterHandler,Type targetType);
}
