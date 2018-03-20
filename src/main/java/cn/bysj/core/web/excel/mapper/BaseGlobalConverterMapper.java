package cn.bysj.core.web.excel.mapper;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import cn.bysj.core.web.excel.converter.FieldValueConverter;
import cn.bysj.core.web.excel.converter.base.BooleanFieldConverter;
import cn.bysj.core.web.excel.converter.base.ByteFieldConverter;
import cn.bysj.core.web.excel.converter.base.CharacterFieldConverter;
import cn.bysj.core.web.excel.converter.base.DateFieldConverter;
import cn.bysj.core.web.excel.converter.base.DoubleFieldConverter;
import cn.bysj.core.web.excel.converter.base.FloatFieldConverter;
import cn.bysj.core.web.excel.converter.base.IntegerFieldConverter;
import cn.bysj.core.web.excel.converter.base.LongFieldConverter;
import cn.bysj.core.web.excel.converter.base.ShortFieldConverter;
import cn.bysj.core.web.excel.converter.base.StringFieldConverter;
import cn.bysj.core.web.excel.converter.collections.ArrayConverter;
import cn.bysj.core.web.excel.converter.collections.CollectionConverter;
import cn.bysj.core.web.excel.converter.enums.EnumConVerter;

import com.google.common.collect.ImmutableMap;

/**
 * 默认的全局转换类，先静态吧，容我想想
 * @author shizhongtao
 *
 * @date 2016-3-19
 * Description:  
 */
public class BaseGlobalConverterMapper {
	static ImmutableMap.Builder<Class<?>, FieldValueConverter>   builder;
	static{
		builder=ImmutableMap.builder();
		builder.put(String.class,new StringFieldConverter());
		builder.put(Date.class,new DateFieldConverter());
		builder.put(Enum.class,new EnumConVerter());
		builder.put(Array.class,new ArrayConverter());
		//builder.put(Collections.class,new ArrayConverter());
		
		
		builder.put(Integer.class,new IntegerFieldConverter());
		builder.put(Long.class,new LongFieldConverter());
		builder.put(Boolean.class,new BooleanFieldConverter());
		builder.put(Byte.class,new ByteFieldConverter());
		builder.put(Character.class,new CharacterFieldConverter());
		builder.put(Double.class,new DoubleFieldConverter());
		builder.put(Float.class,new FloatFieldConverter());
		builder.put(Short.class,new ShortFieldConverter());
		builder.put(Collection.class,new CollectionConverter());
	}
	public final static ImmutableMap<Class<?>, FieldValueConverter> globalFieldConverterMapper=builder.build();
	
}
