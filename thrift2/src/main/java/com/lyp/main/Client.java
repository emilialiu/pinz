/**
 * Created on 2019年5月27日 by liuyipin
 */
package com.lyp.main;
/**
 * @Title 
 * @Description 
 * @Copyright <p>Copyright (c) 2015</p>
 * @Company <p>迪曼森信息科技有限公司 Co., Ltd.</p>
 * @author liuyipin
 * @version 1.0
 * @修改记录
 * @修改序号，修改日期，修改人，修改内容
 */

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.lyp.socktpool.ConnectionManager;
import com.lyp.socktpool.ConnectionProperties;
import com.lyp.socktpool.ConnectionProvider;
import com.lyp.socktpool.ConnectionProviderImpl;
import com.lyp.thrift2.GroupMemberListResult;
import com.lyp.thrift2.HelloWorldService;
import com.lyp.thrift2.IGroupMemberQueryService;

/**
 * Created by Administrator on 2017/4/1.
 */
public class Client {
	public static final int SERVER_PORT = 8000;
	public static final String SERVER_IP = "172.16.0.123";
	private static ConnectionManager connectionManager;

	public void startClient() {
		TTransport tTransport = null;
		try {
			// 协议要和服务端一致
			 tTransport = new TSocket(SERVER_IP, SERVER_PORT);
//			tTransport = new TFramedTransport(new TSocket(SERVER_IP, SERVER_PORT, 5000));

			TProtocol protocol = new TBinaryProtocol(tTransport);
			HelloWorldService.Client client = new HelloWorldService.Client(protocol);
			tTransport.open();

			String result = client.sayHello("ll") ;

			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws TException {
		Client client = new Client();
		client.startClient();
		
//		TSocket socket = new TSocket("127.0.0.1", 8000);
//        socket.setTimeout(3000);
//        TTransport transport = new TFramedTransport(socket);
//        TProtocol protocol = new TCompactProtocol(transport);
//        transport.open();
//        System.out.println("Connected to Thrfit Server"); 
//        HelloWorldService.Client client = new HelloWorldService.Client.Factory()
//              .getClient(protocol);
//        System.out.println(client.sayHello("lili"));
// 
//        IGroupMemberQueryService.Client client = new IGroupMemberQueryService.Client.Factory()
//                .getClient(protocol);
//        GroupMemberListResult result = client.getAllGroupMembers(1, 1);
//        System.out.println("Thrift client result=" + result.getGroupMemeberList().get(0).getGroupId()); 
		
//		ConnectionProperties connectionProperties = new ConnectionProperties();
//		connectionProperties.setServiceIP("127.0.0.1");
//		connectionProperties.setServicePort(8000);
//		ConnectionProvider connectionProvider = new ConnectionProviderImpl(connectionProperties);
//		connectionManager = new ConnectionManager(connectionProvider);
//		
//		/*try {
////			 IGroupMemberQueryService.Client client = new IGroupMemberQueryService.Client.Factory().getClient(protocol);
//			 IGroupMemberQueryService.Iface client = new IGroupMemberQueryService.Client(getTProtocol());
//			//调用thrift连接测试接口
//			 GroupMemberListResult result = client.getAllGroupMembers(1, 1);
//			 System.out.println("Thrift client result=" + result.getGroupMemeberList().get(0).getGroupId()); 
//			connectionManager.returnBack(); 
//		} catch (Exception e) {
//			 
//		}*/
//		
//		try {
////			 IGroupMemberQueryService.Client client = new IGroupMemberQueryService.Client.Factory().getClient(protocol);
//			 HelloWorldService.Iface client = new HelloWorldService.Client(getTProtocol());
//			//调用thrift连接测试接口
//			 String result = client.sayHello("PINZ");
//			 System.out.println(result); 
//			connectionManager.returnBack(); 
//		} catch (Exception e) {
//			 
//		}
		 
	}
	
	private static TProtocol getTProtocol(){
		TProtocol protocol = new TCompactProtocol(new TFramedTransport(connectionManager.getSocket()));
		return protocol;
	}
}
