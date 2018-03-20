package cn.bysj.core.web.excel.converter.base;

import java.lang.reflect.Type;

import cn.bysj.core.web.excel.converter.AbstractFieldConvertor;
import cn.bysj.core.web.excel.core.handler.ConverterHandler;

public final class StringFieldConverter extends AbstractFieldConvertor {

	@Override
	public boolean canConvert(Class<?> clz) {
		 return clz.equals(String.class);
	}

	@Override
	public Object fromString(String cell,ConverterHandler converterHandler,Type targetType) {
		return cell;
	}

	

}
