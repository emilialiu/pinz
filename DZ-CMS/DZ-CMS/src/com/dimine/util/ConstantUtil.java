package com.dimine.util;

/**
 * 字典常量定义类
 * 
 * @author Luqizhi 2015/10/12
 * 
 */

public class ConstantUtil {
	// 是否上报
	public static final String SFSB_Y = "SFSB002"; // 已上报
	public static final String SFSB_N = "SFSB001"; // 未上报

	// 组织机构类型
	public static final String ZZJGLX_GS = "ZZJGLX001"; // 公司
	public static final String ZZJGLX_FGS = "ZZJGLX002"; // 分公司
	public static final String ZZJGLX_XMB = "ZZJGLX003"; // 项目部
	public static final String ZZJGLX_BM = "ZZJGLX004"; // 部门
	public static final String ZZJGLX_GQ = "ZZJGLX005"; // 工区

	// 附件类型
	public static final String FJLX_PDF = "FJLX001";
	public static final String FJLX_DOC = "FJLX002";
	public static final String FJLX_XLS = "FJLX003";
	public static final String FJLX_SWF = "FJLX004";
	public static final String FJLX_RN = "FJLX005";

	// 附件业务类型
	public static final String FJYWLX_NJH = "FJYWLX001"; // 年计划
	public static final String FJYWLX_YJH = "FJYWLX002"; // 月计划
	public static final String FJYWLX_ZJH = "FJYWLX003"; // 周计划

	// 审批状态
	public static final String SPZT_WSP = "SPZT001"; // 未审批
	public static final String SPZT_SPZ = "SPZT002"; // 审批中
	public static final String SPZT_TG = "SPZT003"; // 审批通过
	public static final String SPZT_WTG = "SPZT004"; // 审批未通过
	/**
	 * 穿孔台帐、爆破台帐审批待办角色--穿爆工段长
	 */
	public static final String CB_TZDB_ROLE = "8caa5cd81e4c40e384fa4e5e5d15d877";
	/**
	 * 破碎台帐审批待办角色--破碎工段长
	 */
	public static final String PS_TZDB_ROLE = "bec8ec96b52e41f0bce2c55ee6b4b7d7";
	/**
	 * 矿石转运台帐、辅助产量台帐、卡车运输台账审批待办角色--调度主管
	 */
	public static final String DDZG_TZDB_ROLE = "b0d22f08302f48eebcce663fba8267b0";
	/**
	 * 调度管理员角色
	 */
	public static final String DDY_ROLE = "4790254e37e343789dab9072ea6c1c3f";
	/**
	 * 调度主管角色
	 */
	public static final String DDZG_ROLE = "b0d22f08302f48eebcce663fba8267b0";
	/**
	 * 采矿工程师角色
	 */
	public static final String CKGCS_ROLE = "ad2fef21fbb94e418dd7d3feca471703";
	/**
	 * 采矿主任角色
	 */
	public static final String CKZR_ROLE = "ff2d25b05fc4454a82156173448c89e9";
	/**
	 * 矿山分厂厂长角色
	 */
	public static final String KSFCCZ_ROLE = "7725cda59b39483481800657869415c3";
	/**
	 * 质控技术员角色
	 */
	public static final String ZKJSY_ROLE = "36599906d62a4237a5cc3246ef43ce9e";
	/**
	 * 质控主任角色
	 */
	public static final String ZKZR_ROLE = "5acc36bfa2b0482d805eb2fae93c3f4f";
	/**
	 * 机械主管角色
	 */
	public static final String JXZG_ROLE = "83d8581ed0154424a940e150516ca5bc";
	/**
	 * 机械技术员角色
	 */
	public static final String JXJSY_ROLE = "293c7f4b1c4a489299f6cb81d8bfcbd1";

}
