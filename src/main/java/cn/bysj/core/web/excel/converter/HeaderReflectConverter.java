package cn.bysj.core.web.excel.converter;

import java.util.List;

import cn.bysj.core.web.excel.mapper.ExcelConverterMapperHandler;
import cn.bysj.core.web.excel.vo.CellKV;


public interface HeaderReflectConverter {
   List<CellKV<String>> getHeader(ExcelConverterMapperHandler handler);
}
