package cn.bysj.core.web.excel.exception;

public class IllegalEntityException extends RuntimeException {
	@Override
	public String getMessage() {
		return super.getMessage();
	}

	@Override
	public String toString() {
		return super.toString();
	}

	public IllegalEntityException(Class<?> clz,String message) {
		super("The model entity ["+clz.getName()+"]："+message);
		
	}

	public IllegalEntityException(String message, Throwable cause) {
		super(message, cause);
	}
	public IllegalEntityException(Class clazz,String message, Throwable cause) {
		super("The model entity ["+clazz.getName()+"]："+message,cause);
	}
	
}
