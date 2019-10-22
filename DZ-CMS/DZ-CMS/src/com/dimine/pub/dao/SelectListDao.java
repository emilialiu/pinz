package com.dimine.pub.dao;

import java.util.List;

import com.dimine.base.dao.BaseDao;
import com.dimine.base.dao.BizDao;
import com.dimine.pub.entity.SelectListEntity;

/**
 * SelectList Mapper
 * 
 * @author LCF
 * 
 */
@BizDao
public interface SelectListDao<T> extends BaseDao<T> {

	// 公共
	public List<SelectListEntity> selectList(String value);

	// 公共 +条件
	public List<SelectListEntity> selectlimitList(SelectListEntity entity);
	
	// 查询月计划 班组下拉列表值
	public List<SelectListEntity> selectBZList(SelectListEntity entity);

	// 查询班组下拉列表值
	public List<SelectListEntity> selectClassList(SelectListEntity entity);
	
	// 查询月计划 设备下拉列表值
	public List<SelectListEntity> selectSBList(SelectListEntity entity);

	// 查询生产台账 设备下拉列表值
	public List<SelectListEntity> selectPROSBList(SelectListEntity entity);

	// 查询月计划 通过deptid查询对应业务
	public List<SelectListEntity> selectBusinessByDeptid(String value);

	// 查询月计划 通过deptid查询对应组合支护
	public List<SelectListEntity> selectGroupsupportByDeptid(String value);

	// 查询月计划 通过 通过Detailid查询未添加的组合支护
	public List<SelectListEntity> selectGroupsupportByDetailid(
			SelectListEntity entity);

	// 查询周计划 通过 通过Detailid查询未添加的组合支护
	public List<SelectListEntity> selectGroupsupportByDetailidWeek(
			SelectListEntity entity);

	// 查询月计划 通过deptid查询对应组合支护 单位
	public List<SelectListEntity> selectsupportdwByDeptid(String value);

	// 查询月计划 通过deptid查询对应组合支护 单位
	public List<SelectListEntity> selectsupportdwnameByDeptid(String value);

	// 查询月计划 通过deptid查询对应组合支护 支护形式
	public List<SelectListEntity> selectzhtypeByDeptid(String value);

	// 查询月计划 中、分段 下拉列表值
	public List<SelectListEntity> selectzhongduanList(SelectListEntity entity);

	public List<SelectListEntity> selectfenduanList(SelectListEntity entity);

	// 查询月计划 合同编号 下拉列表值
	public List<SelectListEntity> selectcontractcodeList(SelectListEntity entity);

	// 查询月计划 根据作业地点查合同
	public SelectListEntity selectcontractByProject(SelectListEntity entity);

	// 查询月计划 查询月计划 根据分断查合同
	public SelectListEntity selectcontractByfenduan(SelectListEntity entity);

	// 查询月计划 查询月计划 根据中断查合同
	public SelectListEntity selectcontractByzhongduan(SelectListEntity entity);

	// 查询工程签证下面所有的指标信息
	public List<SelectListEntity> selectTarget();

	// 所有设备
	public List<SelectListEntity> selectallSB(String busstype);

	// 设备台账 查询所有设备
	public List<SelectListEntity> selectSBList2(SelectListEntity bean);

	/**
	 * 根据合同单价类型查询单位列表
	 * 
	 * @param dicttypeid
	 *            ,businesstype
	 * @return
	 */
	public List<SelectListEntity> selectdwlist(SelectListEntity bean);

	/**
	 * 根据工序业务查询工序名称列表(这里用来查询竖井的列表)
	 * 
	 * @param userid
	 * @return
	 */
	public List<SelectListEntity> selectsjgxlist(SelectListEntity bean);

	public List<SelectListEntity> selectclasseslist(String deptid);

	// 根据所属项目部查询对应业务是否配置了反井工程
	public List<SelectListEntity> querybusinessbydept(SelectListEntity bean);

	// 根据所属项目部查询对应业务配置
	public List<SelectListEntity> getBusinessConf(SelectListEntity bean);
	/**
	 * 根据工序id，查询相应的设备信息
	 * @param bean
	 * @return
	 */
	public List<SelectListEntity> selectdevlist(SelectListEntity bean);
	public List<SelectListEntity> selectDevByProTea(SelectListEntity bean);

}
