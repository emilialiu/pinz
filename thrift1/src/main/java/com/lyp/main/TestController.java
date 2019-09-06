/**
 * Created on 2019年5月27日 by liuyipin
 */
package com.lyp.main;

import java.util.Hashtable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.thrift.TProcessor;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;
import org.apache.thrift.transport.TServerSocket;

import com.lyp.impl.HelloServiceImpl;
import com.lyp.thrift1.HelloWorldService;
import com.lyp.thrift1.IGroupMemberQueryService;

/**
 * @Title
 * @Description
 * @Copyright
 *            <p>
 *            Copyright (c) 2015
 *            </p>
 * @Company
 *          <p>
 *          迪曼森信息科技有限公司 Co., Ltd.
 *          </p>
 * @author liuyipin
 * @version 1.0
 * @修改记录
 * @修改序号，修改日期，修改人，修改内容
 */
public class TestController{

	 public  static  final int  SERVER_PORT = 8000;
	    public void startServer() {
	        try {
	            System.out.println("HelloWorld TSimpleServer start .....");

	            //在这里调用了 HelloWorldImpl 规定了接受的方法和返回的参数
	            TProcessor tprocessor = new HelloWorldService.Processor<HelloWorldService.Iface>( new HelloServiceImpl());

	            TServerSocket serverTransport = new TServerSocket(SERVER_PORT);
	            TServer.Args tArgs = new TServer.Args(serverTransport);
	            tArgs.processor(tprocessor);
	            tArgs.protocolFactory(new TBinaryProtocol.Factory());

	            TServer server = new TSimpleServer(tArgs);
	            server.serve();
	            
	            
//	            // 非阻塞式的，配合TFramedTransport使用
//		    	System.out.println("thrift server open port 8000");
//	            TNonblockingServerTransport serverTransport = new TNonblockingServerSocket(8000);
//	            // 关联处理器与Service服务的实现
//	            TProcessor processor = new HelloWorldService.Processor<HelloWorldService.Iface>(new HelloServiceImpl());
//	            // 目前Thrift提供的最高级的模式，可并发处理客户端请求
//	            TThreadedSelectorServer.Args tArgs = new TThreadedSelectorServer.Args(serverTransport);
//	            tArgs.processor(processor);
//	            // 设置协议工厂，高效率的、密集的二进制编码格式进行数据传输协议
//	            tArgs.protocolFactory(new TCompactProtocol.Factory());
//	            // 设置传输工厂，使用非阻塞方式，按块的大小进行传输，类似于Java中的NIO
//	            tArgs.transportFactory(new TFramedTransport.Factory());
//	            // 设置处理器工厂,只返回一个单例实例
//	            tArgs.processorFactory(new TProcessorFactory(processor));
//	            // 多个线程，主要负责客户端的IO处理
//	            tArgs.selectorThreads(1);
//	            //工作线程池
//	            ExecutorService pool = Executors.newFixedThreadPool(20);
//	            tArgs.executorService(pool);
//	            TThreadedSelectorServer server = new TThreadedSelectorServer(tArgs);
//	            System.out.println("Starting server ........... ");
//	            server.serve();

	        } catch (Exception e) {
	            System.out.println("Server start error!!!");
	            e.printStackTrace();
	        }
	    }

	    /**
	     * @param args
	     */
	    public static void main(String[] args) {
	    	TestController server = new TestController();
	        server.startServer();
	    } 
	}
