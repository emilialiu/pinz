package com.lyp.socktpool;

import org.apache.thrift.transport.TSocket;

public interface ConnectionProvider {
	/**
     * 取链接池中的一个链接
     * @return TSocket
     */
    TSocket getConnection();

    /**
     * 返回链接
     * @param socket
     */
    void returnCon(TSocket socket);
    
    public void destroy() throws Exception;
}
