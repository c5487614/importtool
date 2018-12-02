package cch.importTool.disruptor.authication;

import java.nio.ByteBuffer;

import com.lmax.disruptor.RingBuffer;

public class AuthEventProducer {
	private final  RingBuffer<AuthEvent> ringBuffer;

    public AuthEventProducer(RingBuffer<AuthEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }
    
    public void onData(AuthEvent authEvent) {
        long sequence = ringBuffer.next();
        try {
            // Get the entry in the Disruptor
        	
        	AuthEvent event = ringBuffer.get(sequence);
        	event.setId(authEvent.getId());
        	event.setLegalPersonIdCardNum(authEvent.getLegalPersonIdCardNum());
        	event.setLegalPersonName(authEvent.getLegalPersonName());
        	event.setMobile(authEvent.getMobile());
        	event.setPersonIdCardNum(authEvent.getPersonIdCardNum());
        	event.setPersonName(authEvent.getPersonName());
        	event.setType(authEvent.getType());
            // for the sequence   // Fill with data
            //event.setId(id);
        } finally {
            ringBuffer.publish(sequence);
        }
    }
}
