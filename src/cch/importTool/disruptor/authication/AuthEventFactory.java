package cch.importTool.disruptor.authication;

import com.lmax.disruptor.EventFactory;

public class AuthEventFactory implements EventFactory<AuthEvent> {

	@Override
	public AuthEvent newInstance() {
		// TODO Auto-generated method stub
		return new AuthEvent();
	}

}
