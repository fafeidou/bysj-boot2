package cn.bysj.core.web.excel.converter.base;

import java.lang.reflect.Type;

import cn.bysj.core.web.excel.converter.AbstractFieldConvertor;
import cn.bysj.core.web.excel.core.handler.ConverterHandler;
import cn.bysj.core.web.excel.vo.OutValue;

import com.google.common.base.Strings;

public final class LongFieldConverter extends AbstractFieldConvertor {

	@Override
	public boolean canConvert(Class<?> clz) {
		 return clz.equals(long.class) || clz.equals(Long.class);
	}

	@Override
	public Object fromString(String cell,ConverterHandler converterHandler,Type targetType) {
		if (Strings.isNullOrEmpty(cell)) {
            return null;
        }
		  char c1 = cell.charAt(1);
	        if (c1 == 'x' || c1 == 'X') {
	            return Long.decode(cell);
	        }
		return Long.parseLong(cell);
	}

	@Override
	public OutValue toObject(Object source,ConverterHandler converterHandler) {
		if(source==null){
			return null;
		}
		return OutValue.longValue(source);
	}

}
