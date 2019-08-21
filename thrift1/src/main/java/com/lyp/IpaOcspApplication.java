/**
 * Created on 2016年12月12日 by caiming
 */
package com.lyp;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.thrift.TProcessor;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;
import org.apache.thrift.transport.TTransportException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ApplicationContext;

import com.lyp.impl.IGroupMemberQueryServiceImpl;
import com.lyp.thrift1.IGroupMemberQueryService;


//@SpringBootApplication
@SpringBootApplication(exclude=
{DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
public class IpaOcspApplication {
	
	public static void main(String[] args) { 
 
		ApplicationContext applicationContext  = SpringApplication.run(IpaOcspApplication.class, args);
		
		IGroupMemberQueryService.Iface bean = applicationContext.getBean(IGroupMemberQueryServiceImpl.class);
		startServer(bean);
	}
	
	/** 
     * 启动Thrift服务器 
     */  
    private static void startServer(IGroupMemberQueryService.Iface bean) {  
        try {  
	    	System.out.println("thrift server init");
	        
/*	    	//普通thrift模式
        	System.out.println("thrift server open port 8000");
            TServerSocket serverTransport = new TServerSocket(8000);  
            OCSPIface.Processor process = new Processor(bean);  
            Factory portFactory = new TBinaryProtocol.Factory(true, true);  

            Args a = new Args(serverTransport);  
            a.processor(process);  
            a.protocolFactory(portFactory); 
            TServer server = new TThreadPoolServer(a);  
            System.out.println("Server start....");
            server.serve();  */
	    

            

            // 非阻塞式的，配合TFramedTransport使用
	    	System.out.println("thrift server open port 8000");
            TNonblockingServerTransport serverTransport = new TNonblockingServerSocket(8000);
            // 关联处理器与Service服务的实现
            TProcessor processor = new IGroupMemberQueryService.Processor<IGroupMemberQueryService.Iface>(bean);
            // 目前Thrift提供的最高级的模式，可并发处理客户端请求
            TThreadedSelectorServer.Args tArgs = new TThreadedSelectorServer.Args(serverTransport);
            tArgs.processor(processor);
            // 设置协议工厂，高效率的、密集的二进制编码格式进行数据传输协议
            tArgs.protocolFactory(new TCompactProtocol.Factory());
            // 设置传输工厂，使用非阻塞方式，按块的大小进行传输，类似于Java中的NIO
            tArgs.transportFactory(new TFramedTransport.Factory());
            // 设置处理器工厂,只返回一个单例实例
            tArgs.processorFactory(new TProcessorFactory(processor));
            // 多个线程，主要负责客户端的IO处理
            tArgs.selectorThreads(1);
            //工作线程池
            ExecutorService pool = Executors.newFixedThreadPool(20);
            tArgs.executorService(pool);
            TThreadedSelectorServer server = new TThreadedSelectorServer(tArgs);
            System.out.println("Starting server ........... ");
            server.serve();
             
        } catch (TTransportException e) {  
            e.printStackTrace();  
        }  
    }  
}
