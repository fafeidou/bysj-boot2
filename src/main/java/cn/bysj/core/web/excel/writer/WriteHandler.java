package cn.bysj.core.web.excel.writer;

import java.util.List;

import cn.bysj.core.web.excel.vo.CellKV;
import cn.bysj.core.web.excel.vo.ListLine;

/**
 * 目前的三个实现不是线程安全的
 * @author shizhongtao
 *
 */
public interface WriteHandler {

	/**
	 * 
	 */
	public abstract void writeLine(ListLine line);
	public abstract void writeHeader(List<CellKV<String>> listStr);
	public abstract void createSheet(String name);

	/**
	 * 
	 */
	public abstract void flush();

}