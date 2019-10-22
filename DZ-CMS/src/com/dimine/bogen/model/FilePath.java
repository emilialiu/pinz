package com.dimine.bogen.model;
/**
 * 
 * <p> FilePath </p>
 * 
 * @author  Aaron
 * @version 2014-12-12
 */
public class FilePath {
	
	private String actionPath;//Action文件输出路径
	private String entityPath;//Entity文件输出路径
	private String servicePath;//Service文件输出路径
	private String daoPath;//Dao文件输出路径
	private String actionConfPath;//action_config 文件输出路径
	private String ibatisPath;//ibatis_config文件输出路径
	private String springPath;//spring_config文件输出路径
	private String messagePath;//message_config国际化资源文件输出路径
	private String pagePath;//JSP页面文件输出路径
	private String previewPath;//预览JSP文件输出路径
	
	public String getActionPath(String str,String modelPath) {
		StringBuffer sb = new StringBuffer("");
		actionPath = sb.append("tmp/").append(str).append("/src/com/dimine/").append(modelPath).append("/action").toString();
		return actionPath;
	}
	public String getEntityPath(String str,String modelPath) {
		StringBuffer sb = new StringBuffer("");
		entityPath = sb.append("tmp/").append(str).append("/src/com/dimine/").append(modelPath).append("/entity").toString();
		return entityPath;
	}
	public String getServicePath(String str,String modelPath) {
		StringBuffer sb = new StringBuffer("");
		servicePath = sb.append("tmp/").append(str).append("/src/com/dimine/").append(modelPath).append("/service").toString();
		return servicePath;
	}
	public String getDaoPath(String str,String modelPath) {
		StringBuffer sb = new StringBuffer("");
		daoPath = sb.append("tmp/").append(str).append("/src/com/dimine/").append(modelPath).append("/dao").toString();
		return daoPath;
	}
	public String getActionConfPath(String str,String modelPath) {
		System.out.println("modelPath"+modelPath);
		StringBuffer sb = new StringBuffer("");
		actionConfPath = sb.append("tmp/").append(str).append("/src/conf/action/").append(modelPath).toString();
		return actionConfPath;
	}
	public String getIbatisPath(String str,String modelPath) {
		StringBuffer sb = new StringBuffer("");
		ibatisPath = sb.append("tmp/").append(str).append("/src/conf/mybatis/mysql/").append(modelPath).toString();
		return ibatisPath;
	}
	public String getSpringPath(String str) {
		StringBuffer sb = new StringBuffer("");
		springPath = sb.append("tmp/").append(str).append("/src/conf/spring").toString();
		return springPath;
	}
	public String getGlobalMessagesPath(String str) {
		StringBuffer sb = new StringBuffer("");
		messagePath = sb.append("tmp/").append(str).append("/src/conf/properties").toString();
		return messagePath;
	}
	public String getPagePath(String str,String modelPath,String filePath) {
		StringBuffer sb = new StringBuffer("");
		pagePath = sb.append("tmp/").append(str).append("/WebRoot/").append(filePath).toString();
		return pagePath;
	}
	public String getPreviewPath(String str,String seq,String filePath) {
		StringBuffer sb = new StringBuffer("");
		previewPath = sb.append("tmp/preview/").append(str+"/").append(seq+"/").append(filePath).toString();
		return previewPath;
	}
}
