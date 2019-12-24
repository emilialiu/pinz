package com.ahdms.config;

import java.net.SocketException;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.ahdms.tinyradius.util.RadiusClient;

@Configuration
public class RadiusConfig {

//    private String server = "192.168.4.24";
	private String server = "172.16.1.105";
    private int authport = 1812;
    private int acctport = 1813;
    private int timeout = 5000;
    private int retry = 3;
    private String authProtocol = "pap";
    private String userfile = "jrusers.txt";
    private String secret = "123456";
    private String nasid = "test001";
    private String nasip = "192.168.4.10";

    public RadiusClient getClient() throws SocketException {
        RadiusClient cli = new RadiusClient(getServer(),getSecret());
        cli.setAcctPort(getAcctport());
        cli.setAuthPort(getAuthport());
        cli.setRetryCount(retry);
        cli.setSocketTimeout(timeout);
        cli.setAuthProtocol(getAuthProtocol());
        return cli;
    }

    public ThreadPoolTaskExecutor getExecutor(int queueSize,int poolSize){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(poolSize);
        taskExecutor.setQueueCapacity(queueSize);
        taskExecutor.setKeepAliveSeconds(60);
        taskExecutor.setThreadNamePrefix("TASK_EXECUTOR");
        taskExecutor.setMaxPoolSize(poolSize);
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        taskExecutor.initialize();
        return taskExecutor;
    }


    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public int getAuthport() {
        return authport;
    }

    public void setAuthport(int authport) {
        this.authport = authport;
    }

    public int getAcctport() {
        return acctport;
    }

    public void setAcctport(int acctport) {
        this.acctport = acctport;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getRetry() {
        return retry;
    }

    public void setRetry(int retry) {
        this.retry = retry;
    }

    public String getUserfile() {
        return userfile;
    }

    public void setUserfile(String userfile) {
        this.userfile = userfile;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getNasid() {
        return nasid;
    }

    public void setNasid(String nasid) {
        this.nasid = nasid;
    }

    public String getAuthProtocol() {
        return authProtocol;
    }

    public void setAuthProtocol(String authProtocol) {
        this.authProtocol = authProtocol;
    }

    public String getNasip() {
        return nasip;
    }

    public void setNasip(String nasip) {
        this.nasip = nasip;
    }

    @Override
    public String toString() {
        return "RadiusConfig{" +
                "server='" + server + '\'' +
                ", authport=" + authport +
                ", acctport=" + acctport +
                ", timeout=" + timeout +
                ", retry=" + retry +
                ", authProtocol='" + authProtocol + '\'' +
                ", userfile='" + userfile + '\'' +
                ", secret='" + secret + '\'' +
                ", nasid='" + nasid + '\'' +
                ", nasip='" + nasip + '\'' +
                '}';
    }

}
