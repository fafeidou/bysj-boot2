package cn.bysj.core.web.common;

import cn.bysj.core.web.excel.converter.FieldValueConverter;
import cn.bysj.core.web.excel.core.BingExcel;

/**
 * @author shizhongtao
 *
 * @date 2015-12-17
 * Description:  
 */
public interface Builder<T> {
	
	T builder();

	Builder<T> registerFieldConverter(Class<?> clazz,
			FieldValueConverter converter);
}
