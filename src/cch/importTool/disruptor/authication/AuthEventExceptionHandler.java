package cch.importTool.disruptor.authication;

import com.lmax.disruptor.ExceptionHandler;

public class AuthEventExceptionHandler implements ExceptionHandler{

	@Override
	public void handleEventException(Throwable arg0, long arg1, Object arg2) {
		// TODO Auto-generated method stub
		arg0.printStackTrace();
	}

	@Override
	public void handleOnShutdownException(Throwable arg0) {
		// TODO Auto-generated method stub
		arg0.printStackTrace();
	}

	@Override
	public void handleOnStartException(Throwable arg0) {
		// TODO Auto-generated method stub
		arg0.printStackTrace();
	}

}
