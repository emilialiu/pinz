package com.dimine.sys.util;

/**
 * @author dominic
 */
public class PublicUtil
{
    /**
     * 验证码
     */
    public static final String SESSION_KEY_VALIDATE_CODE = "session_key_validate_code";
    
    /**
     * 超级管理员用户ID
     */
    public final static String ADMIN_USER_ID = "2000";
    
    /**
     * 超级管理员角色
     */
    public final static String ADMIN_ROLE_ID = "3000";
    
    // **************************数据库***************************
    //
    public final static String KEY_SEQ_SYS = "SEQ_SYS";
    
    // **************************树的根结点***************************
    /**
     * 平台机构根结点编号
     */
    public final static String DEPT_ROOT_NUMBER = "1000";
    
    /**
     * 平台机构根结点名称
     */
    public final static String DEPT_ROOT_NAME = "管理";
    
    /**
     * 资源库根结点编号
     */
    public final static String SOURCE_ROOT_NUMBER = "1000";
    
    /**
     * 资源库根结点名称
     */
    public final static String SOURCE_ROOT_NAME = "目录";
    
    /**
     * 功能根结点编号
     */
    public final static String FUNC_ROOT_NUMBER = "100000";
    
    /**
     * 功能根结点名称
     */
    public final static String FUNC_ROOT_NAME = "功能维护";
    
    
    /**
     * 登陆类型
     */
    public static final String USER_TYPE_CAOZUOYUAN = "1";
    
    /**
     * 查看是否是部门机构的顶点，即大平台结构
     * 这里的根结点不包括平台机构根结点，这里规定大平台即为各自下属机构的根结点
     * @return 是则返回true,否则返回false
     * @see [类、类#方法、类#成员]
     */
    public static boolean isRootDept(String deptID)
    {
        return false;
    }
}
