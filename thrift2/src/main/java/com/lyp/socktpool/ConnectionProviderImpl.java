package com.lyp.socktpool;

import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * 连接池实现
 */
public class ConnectionProviderImpl implements ConnectionProvider, InitializingBean, DisposableBean {
	/** 服务的IP地址 */
    private String serviceIP;
    /** 服务的端口 */
    private int servicePort;
    /** 连接超时配置 */
    private int conTimeOut = 5000;
    /** 可以从缓存池中分配对象的最大数量 */
    private int maxActive = 100;
    /** 缓存池中最大空闲对象数量 */
    private int maxIdle = 20;
    /** 缓存池中最小空闲对象数量 */
    private int minIdle = GenericObjectPool.DEFAULT_MIN_IDLE;
    /** 阻塞的最大数量 */
    private long maxWait = GenericObjectPool.DEFAULT_MAX_WAIT;

    /** 从缓存池中分配对象，是否执行PoolableObjectFactory.validateObject方法 */
    private boolean testOnBorrow = GenericObjectPool.DEFAULT_TEST_ON_BORROW;
    private boolean testOnReturn = GenericObjectPool.DEFAULT_TEST_ON_RETURN;
    private boolean testWhileIdle = GenericObjectPool.DEFAULT_TEST_WHILE_IDLE;

    public ConnectionProviderImpl() {
    	this.serviceIP = "127.0.0.1";
    	this.servicePort = 7911;
    }
    
    public ConnectionProviderImpl(ConnectionProperties properties) {
    	this.serviceIP = properties.getServiceIP();
    	this.servicePort = properties.getServicePort();
    	this.conTimeOut = properties.getConTimeOut();
    	this.maxActive = properties.getMaxActive();
    	this.maxIdle = properties.getMaxIdle();
    	this.minIdle = properties.getMinIdle();
    	this.maxWait = properties.getMaxWait();
    	this.testOnBorrow = properties.isTestOnBorrow();
    	this.testOnReturn = properties.isTestOnReturn();
    	this.testWhileIdle = properties.isTestWhileIdle();
    	
    	try {
			afterPropertiesSet();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /** 对象缓存池 */
    private ObjectPool<TTransport> objectPool = null;
    public TSocket getConnection() {
        try {
            // 从对象池取对象
            TSocket socket = (TSocket) objectPool.borrowObject();
            return socket;
        } catch (Exception e) {
            throw new RuntimeException("error getConnection()", e);
        }
    }

    public void returnCon(TSocket socket) {
        try {
            // 将对象放回对象池
            objectPool.returnObject(socket);
        } catch (Exception e) {
            throw new RuntimeException("error returnCon()", e);
        }
    }

    public void destroy() throws Exception {
        try {
            objectPool.close();
        } catch (Exception e) {
            throw new RuntimeException("erorr destroy()", e);
        }
    }

    public void afterPropertiesSet() throws Exception {
// 对象池
        objectPool = new GenericObjectPool<TTransport>();
        //
        ((GenericObjectPool<TTransport>) objectPool).setMaxActive(maxActive);
        ((GenericObjectPool<TTransport>) objectPool).setMaxIdle(maxIdle);
        ((GenericObjectPool<TTransport>) objectPool).setMinIdle(minIdle);
        ((GenericObjectPool<TTransport>) objectPool).setMaxWait(maxWait);
        ((GenericObjectPool<TTransport>) objectPool).setTestOnBorrow(testOnBorrow);
        ((GenericObjectPool<TTransport>) objectPool).setTestOnReturn(testOnReturn);
        ((GenericObjectPool<TTransport>) objectPool).setTestWhileIdle(testWhileIdle);
        ((GenericObjectPool<TTransport>) objectPool).setWhenExhaustedAction(GenericObjectPool.WHEN_EXHAUSTED_BLOCK);
        // 设置factory
        ThriftPoolableObjectFactory thriftPoolableObjectFactory = new ThriftPoolableObjectFactory(serviceIP, servicePort, conTimeOut);
        objectPool.setFactory(thriftPoolableObjectFactory);
    }
    public String getServiceIP() {
        return serviceIP;
    }

    public void setServiceIP(String serviceIP) {
        this.serviceIP = serviceIP;
    }

    public int getServicePort() {
        return servicePort;
    }

    public void setServicePort(int servicePort) {
        this.servicePort = servicePort;
    }

    public int getConTimeOut() {
        return conTimeOut;
    }

    public void setConTimeOut(int conTimeOut) {
        this.conTimeOut = conTimeOut;
    }

    public int getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public long getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(long maxWait) {
        this.maxWait = maxWait;
    }

    public boolean isTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public boolean isTestOnReturn() {
        return testOnReturn;
    }

    public void setTestOnReturn(boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
    }

    public boolean isTestWhileIdle() {
        return testWhileIdle;
    }

    public void setTestWhileIdle(boolean testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
    }

    public ObjectPool<TTransport> getObjectPool() {
        return objectPool;
    }

    public void setObjectPool(ObjectPool<TTransport> objectPool) {
        this.objectPool = objectPool;
    }
}
