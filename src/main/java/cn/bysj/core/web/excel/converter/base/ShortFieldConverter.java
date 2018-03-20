package cn.bysj.core.web.excel.converter.base;

import java.lang.reflect.Type;

import cn.bysj.core.web.excel.converter.AbstractFieldConvertor;
import cn.bysj.core.web.excel.core.handler.ConverterHandler;
import cn.bysj.core.web.excel.vo.OutValue;

import com.google.common.base.Strings;

public final class ShortFieldConverter extends AbstractFieldConvertor {

	@Override
	public boolean canConvert(Class<?> clz) {
		 return clz.equals(short.class) || clz.equals(Short.class);
	}

	@Override
	public Object fromString(String cell,ConverterHandler converterHandler,Type targetType) {
		if(Strings.isNullOrEmpty(cell)){
			return null;
		}
		int value = Integer.valueOf(cell).intValue();
    	if(value < Short.MIN_VALUE || value > Short.MAX_VALUE) {
    		throw new NumberFormatException("For input string: \"" + cell + '"');
    	}
        return new Short((short)value);
	}

	@Override
	public OutValue toObject(Object source,ConverterHandler converterHandler) {
		if(source==null){
			return null;
		}
		return OutValue.intValue(((Short)source).intValue());
	}

}
