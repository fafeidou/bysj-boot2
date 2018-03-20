package cn.bysj.core.web.excel.converter.base;

import java.lang.reflect.Type;

import org.apache.commons.lang3.StringUtils;

import cn.bysj.core.web.excel.converter.AbstractFieldConvertor;
import cn.bysj.core.web.excel.core.handler.ConverterHandler;
import cn.bysj.core.web.excel.vo.OutValue;

import com.google.common.base.Strings;

public final class DoubleFieldConverter extends AbstractFieldConvertor {

	@Override
	 public boolean canConvert(Class type) {
	        return type.equals(double.class) || type.equals(Double.class);
	    }

	@Override
	public Object fromString(String cell,ConverterHandler converterHandler,Type targetType) {
		if(StringUtils.isBlank(cell)){
			return null;
		}
		return Double.valueOf( cell);
	}

	@Override
	public OutValue toObject(Object source,ConverterHandler converterHandler) {
		if(source==null){
			return null;
		}
		return OutValue.doubleValue(source);
	}

}
