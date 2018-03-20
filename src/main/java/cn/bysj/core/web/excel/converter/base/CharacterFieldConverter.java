package cn.bysj.core.web.excel.converter.base;

import java.lang.reflect.Type;

import cn.bysj.core.web.excel.converter.AbstractFieldConvertor;
import cn.bysj.core.web.excel.core.handler.ConverterHandler;
import cn.bysj.core.web.excel.vo.OutValue;

import com.google.common.base.Strings;

/**
 * @author shizhongtao
 *
 * @date 2016-3-21
 * Description:  
 */
public final class CharacterFieldConverter extends AbstractFieldConvertor {

	@Override
	public boolean canConvert(Class<?> clz) {
		 return clz.equals(char.class) || clz.equals(Character.class);
	}

	@Override
	public Object fromString(String cell,ConverterHandler converterHandler,Type targetType) {
		if (cell==null) {
			return null;
		}
		 if (cell.length() == 0) {
	            return new Character('\0');
	        } else {
	            return new Character(cell.charAt(0));
	        }
	}

	@Override
	public OutValue toObject(Object source,ConverterHandler converterHandler) {
		if (source==null) {
			return null;
		}
		return OutValue.stringValue(source.toString());
	}

}
