package cn.bysj.core.web.excel.reader;


import cn.bysj.core.web.excel.vo.ListRow;


/**
 * @author shizhongtao
 *
 * @date 2016-3-1
 * Description:  
 */
public abstract class AbstractExcelReadListener implements ExcelReadListener {

	@Override
	public void optRow(int curRow, ListRow rowList) {
		
	}

	@Override
	public void startSheet(int sheetIndex, String name) {
		
	}

	@Override
	public void endSheet(int sheetIndex, String name) {
		
	}

	@Override
	public void endWorkBook() {
		
	}

}
