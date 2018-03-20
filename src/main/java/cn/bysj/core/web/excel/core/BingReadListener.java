package cn.bysj.core.web.excel.core;

import cn.bysj.core.web.excel.core.impl.BingExcelEventImpl.ModelInfo;

public interface BingReadListener {

	void readModel(Object object, ModelInfo modelInfo);

}
