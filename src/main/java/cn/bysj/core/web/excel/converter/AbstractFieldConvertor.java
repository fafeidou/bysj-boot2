package cn.bysj.core.web.excel.converter;

import java.lang.reflect.Type;

import cn.bysj.core.web.excel.core.handler.ConverterHandler;
import cn.bysj.core.web.excel.vo.OutValue;
import cn.bysj.core.web.excel.vo.OutValue.OutType;

public class AbstractFieldConvertor implements FieldValueConverter {

	@Override
	public boolean canConvert(Class<?> clz) {

		return false;
	}

	@Override
	public OutValue toObject(Object source,ConverterHandler converterHandler) {
		if(source==null){
			return null;
		}
		return new OutValue(OutType.STRING, source.toString());
	}

	@Override
	public Object fromString(String cell, ConverterHandler converterHandler,
			Type targetType) {
		if (cell == null) {
			return null;
		}
		return cell;
	}

}
