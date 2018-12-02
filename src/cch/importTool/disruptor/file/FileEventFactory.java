package cch.importTool.disruptor.file;

import com.lmax.disruptor.EventFactory;

public class FileEventFactory implements EventFactory<FileEvent> {

	@Override
	public FileEvent newInstance() {
		// TODO Auto-generated method stub
		return new FileEvent();
	}

}
