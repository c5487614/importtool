package cch.importTool.disruptor.file;

import java.nio.ByteBuffer;

import com.lmax.disruptor.RingBuffer;

public class FileEventProducer {
	private final  RingBuffer<FileEvent> ringBuffer;

    public FileEventProducer(RingBuffer<FileEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }
    
    public void onData(FileEvent fileEvent) {
        long sequence = ringBuffer.next();
        try {
            // Get the entry in the Disruptor
        	System.out.println(fileEvent.getFileName());
        	FileEvent event = ringBuffer.get(sequence);
        	event.setFileFullPath(fileEvent.getFileFullPath());
        	event.setFileName(fileEvent.getFileName());
            // for the sequence   // Fill with data
            //event.setId(id);
        } finally {
            ringBuffer.publish(sequence);
        }
    }
}
