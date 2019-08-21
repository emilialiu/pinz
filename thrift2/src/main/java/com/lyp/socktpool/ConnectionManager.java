package com.lyp.socktpool;

import org.apache.thrift.transport.TSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 连接池管理
 */

public class ConnectionManager {
	/** 日志记录器 */
    private final Logger logger = LoggerFactory.getLogger(getClass());
    /** 保存local对象 */
    ThreadLocal<TSocket> socketThreadSafe = new ThreadLocal<TSocket>();

    /** 连接提供池 */
    private ConnectionProvider connectionProvider;
    
    public ConnectionManager(ConnectionProvider connectionProvider) {
    	this.connectionProvider = connectionProvider;
    }

    public ConnectionProvider getConnectionProvider() {
        return connectionProvider;
    }

    public void setConnectionProvider(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    public TSocket getSocket() {
        TSocket socket = null;
        try {
            socket = connectionProvider.getConnection();
            socketThreadSafe.set(socket);
            return socketThreadSafe.get();
        } catch (Exception e) {
            logger.error("error ConnectionManager.invoke()", e);
        } 
//        finally {
//            connectionProvider.returnCon(socket);
//            socketThreadSafe.remove();
//        }
        return socket;
    }
    
    public void returnBack() {
    	if (null != socketThreadSafe.get()) {
    		connectionProvider.returnCon(socketThreadSafe.get());
            socketThreadSafe.remove();
    	}
    }
    
}
