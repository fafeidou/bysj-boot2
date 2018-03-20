package cn.bysj.core.web.utils.xlsx;

import java.util.List;

/**
 * 药品目录 导入接口
 * @author Thinkpad
 *
 */
public class HxlsOptRowsInterfaceImpl implements HxlsOptRowsInterface {

	@Override
	public String optRows(int sheetIndex, int curRow, List<String> rowlist)
			throws Exception {
		
		System.out.println("===================================");
		//获取解析到的一行数据
		System.out.println(rowlist.get(0));
		
		//校验数据合法性
		//.......
		
		System.out.println(rowlist.get(1));
		System.out.println(rowlist.get(2));
		
		//封装到po类
		
		//调用mapper插入数据库
		
		//插入数据库
		//System.out.println("sheetIndex="+sheetIndex+"curRow="+curRow+rowlist);
		return null;
	}
	
}
