package cch.importTool.init;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.SleepingWaitStrategy;
import com.lmax.disruptor.WaitStrategy;
import com.lmax.disruptor.WorkerPool;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.ProducerType;

import cch.importTool.disruptor.authication.AuthEvent;
import cch.importTool.disruptor.authication.AuthEventExceptionHandler;
import cch.importTool.disruptor.authication.AuthEventFactory;
import cch.importTool.disruptor.authication.AuthEventProducer;
import cch.importTool.disruptor.authication.AuthEventWorkHandler;
import cch.importTool.disruptor.file.FileEvent;
import cch.importTool.disruptor.file.FileEventExceptionHandler;
import cch.importTool.disruptor.file.FileEventFactory;
import cch.importTool.disruptor.file.FileEventProducer;
import cch.importTool.disruptor.file.FileEventWorkHandler;

public class InitDisruptor {
	
	public static RingBuffer<FileEvent> fileRingBuffer;
	public static FileEventProducer fileEventProducer;
	
	public static RingBuffer<AuthEvent> authRingBuffer;
	public static AuthEventProducer authEventProducer;
	public void init(){
		System.out.println("InitDisruptor init ------------------------------------------------------------------");
		System.out.println("File disruptor init ------------------------------------------------------------------");
		FileEventFactory eventFactory = new FileEventFactory();
        int bufferSize = 8;
        WaitStrategy YIELDING_WAIT = new YieldingWaitStrategy();
        WaitStrategy sleep = new SleepingWaitStrategy();
        fileRingBuffer = RingBuffer.create(ProducerType.SINGLE, eventFactory, bufferSize, sleep);
        SequenceBarrier barriers = fileRingBuffer.newBarrier();
        FileEventExceptionHandler exceptionHandler = new FileEventExceptionHandler();
        FileEventWorkHandler[] eventHandler = new FileEventWorkHandler[4];
        eventHandler[0] = new FileEventWorkHandler("thread 1");
        eventHandler[1] = new FileEventWorkHandler("thread 2");
        eventHandler[2] = new FileEventWorkHandler("thread 3");
        eventHandler[3] = new FileEventWorkHandler("thread 4");
        WorkerPool<FileEvent> workerPool = new WorkerPool<FileEvent>(fileRingBuffer,barriers,exceptionHandler,eventHandler);
        ExecutorService executor = Executors.newFixedThreadPool(4);
        fileRingBuffer = workerPool.start(executor);
        fileRingBuffer.addGatingSequences(workerPool.getWorkerSequences());
        
        fileEventProducer = new FileEventProducer(fileRingBuffer);
        System.out.println("File disruptor end ------------------------------------------------------------------");
        System.out.println("Auth disruptor init ------------------------------------------------------------------");
		AuthEventFactory authEventFactory = new AuthEventFactory();
        int authBufferSize = 1024;
        authRingBuffer = RingBuffer.create(ProducerType.SINGLE, authEventFactory, authBufferSize, sleep);
        SequenceBarrier authBarriers = authRingBuffer.newBarrier();
        AuthEventExceptionHandler authExceptionHandler = new AuthEventExceptionHandler();
        AuthEventWorkHandler[] authEventHandler = new AuthEventWorkHandler[4];
        authEventHandler[0] = new AuthEventWorkHandler("thread 1");
        authEventHandler[1] = new AuthEventWorkHandler("thread 2");
        authEventHandler[2] = new AuthEventWorkHandler("thread 3");
        authEventHandler[3] = new AuthEventWorkHandler("thread 4");
        WorkerPool<AuthEvent> authworkerPool = new WorkerPool<AuthEvent>(authRingBuffer,authBarriers,authExceptionHandler,authEventHandler);
        ExecutorService authExecutor = Executors.newFixedThreadPool(4);
        authRingBuffer = authworkerPool.start(authExecutor);
        authRingBuffer.addGatingSequences(authworkerPool.getWorkerSequences());
        
        authEventProducer = new AuthEventProducer(authRingBuffer);
        System.out.println("Auth disruptor end ------------------------------------------------------------------");
        System.out.println("InitDisruptor end ------------------------------------------------------------------");
	}

}
