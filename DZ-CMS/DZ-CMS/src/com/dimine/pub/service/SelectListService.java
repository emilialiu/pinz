package com.dimine.pub.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimine.base.service.BaseService;
import com.dimine.pub.dao.SelectListDao;
import com.dimine.pub.entity.SelectListEntity;

/**
 * 
 * 下拉列表service
 * 
 * @author LCF
 * @version [版本号, 2014-12-12]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service("selectListService")
public class SelectListService<T> extends BaseService<T> {
	@Autowired
	private SelectListDao<T> dao;

	public SelectListDao<T> getDao() {
		return dao;
	}

	/**
	 * 根据字典值类型得到下拉列表
	 * 
	 * @param userid
	 * @return
	 */
	public List<SelectListEntity> selectList(String value) {
		return getDao().selectList(value);
	}

	public List<SelectListEntity> selectlimitList(SelectListEntity entity) {
		return getDao().selectlimitList(entity);
	}

	// 查询工程签证下面所有的指标信息
	public List<SelectListEntity> selectTarget() {
		return getDao().selectTarget();
	}
	
	// 查询月计划 班组下拉列表值
	public List<SelectListEntity> selectBZList(SelectListEntity entity) {
		return getDao().selectBZList(entity);
	}
	
	// 查询班组下拉列表值
	public List<SelectListEntity> selectClassList(SelectListEntity entity) {
		return getDao().selectClassList(entity);
	}

	// 查询月计划 设备下拉列表值
	public List<SelectListEntity> selectSBList(SelectListEntity entity) {
		return getDao().selectSBList(entity);
	}

	// 查询生产台账 设备下拉列表值
	public List<SelectListEntity> selectPROSBList(SelectListEntity entity) {
		return getDao().selectPROSBList(entity);
	}

	// 查询月计划 通过deptid查询对应业务
	public List<SelectListEntity> selectBusinessByDeptid(String value) {
		return getDao().selectBusinessByDeptid(value);
	}

	// 查询月计划 通过deptid查询对应组合支护
	public List<SelectListEntity> selectGroupsupportByDeptid(String value) {
		return getDao().selectGroupsupportByDeptid(value);
	}

	// 查询月计划 通过Detailid查询未添加的组合支护
	public List<SelectListEntity> selectGroupsupportByDetailid(
			SelectListEntity entity) {
		return getDao().selectGroupsupportByDetailid(entity);
	}

	// 查询月计划 通过deptid查询对应组合支护
	public List<SelectListEntity> selectGroupsupportByDetailidWeek(
			SelectListEntity entity) {
		return getDao().selectGroupsupportByDetailidWeek(entity);
	}

	// 查询月计划 通过deptid查询对应组合支护 单位
	public List<SelectListEntity> selectsupportdwByDeptid(String value) {
		return getDao().selectsupportdwByDeptid(value);
	}

	// 查询月计划 通过deptid查询对应组合支护 单位
	public List<SelectListEntity> selectsupportdwnameByDeptid(String value) {
		return getDao().selectsupportdwnameByDeptid(value);
	}

	// 查询月计划 通过deptid查询对应组合支护 支护形式
	public List<SelectListEntity> selectzhtypeByDeptid(String value) {
		return getDao().selectzhtypeByDeptid(value);
	}

	public List<SelectListEntity> selectSBList2(SelectListEntity bean) {
		return getDao().selectSBList2(bean);
	}

	// 查询月计划 中、分段 下拉列表值
	public List<SelectListEntity> selectzhongduanList(SelectListEntity bean) {
		return getDao().selectzhongduanList(bean);
	}

	public List<SelectListEntity> selectfenduanList(SelectListEntity bean) {
		return getDao().selectfenduanList(bean);
	}

	// 查询月计划 合同编号 下拉列表值
	public List<SelectListEntity> selectcontractcodeList(SelectListEntity bean) {
		return getDao().selectcontractcodeList(bean);
	}

	// 查询月计划 根据作业地点查合同
	public SelectListEntity selectcontractByProject(SelectListEntity bean) {
		return getDao().selectcontractByProject(bean);
	}

	// 查询月计划 查询月计划 根据分断查合同
	public SelectListEntity selectcontractByfenduan(SelectListEntity bean) {
		return getDao().selectcontractByfenduan(bean);
	}

	// 查询月计划 查询月计划 根据中断查合同
	public SelectListEntity selectcontractByzhongduan(SelectListEntity bean) {
		return getDao().selectcontractByzhongduan(bean);
	}

	// 根据所属项目部查询对应业务是否配置了反井工程
	public List<SelectListEntity> querybusinessbydept(SelectListEntity bean) {
		return getDao().querybusinessbydept(bean);
	}

	// 根据所属项目部查询对应业务配置
	public List<SelectListEntity> getBusinessConf(SelectListEntity bean) {
		return getDao().getBusinessConf(bean);
	}

	/**
	 * 根据合同单价类型查询单位列表
	 * 
	 * @param userid
	 * @return
	 */
	public List<SelectListEntity> selectdwlist(SelectListEntity bean) {
		return getDao().selectdwlist(bean);
	}

	/**
	 * 根据登录人所在部门查询班次列表
	 * 
	 * @param userid
	 * @return
	 */
	public List<SelectListEntity> selectclasseslist(String deptid) {
		return getDao().selectclasseslist(deptid);
	}

	/**
	 * 根据工序业务查询工序名称列表(这里用来查询竖井的列表)
	 * 
	 * @param userid
	 * @return
	 */
	public List<SelectListEntity> selectsjgxlist(SelectListEntity bean) {
		return getDao().selectsjgxlist(bean);
	}

	/**
	 * 
	 * 查询 月计划 合同单价
	 * 
	 * @param userid
	 * @return
	 */
	public List<SelectListEntity> selectallSB(String busstype) {
		return getDao().selectallSB(busstype);
	}
	/**
	 * 跟据工序id 查询出所有的设备信息
	 * @param bean
	 * @return
	 */
	public List<SelectListEntity> selectdevlist(SelectListEntity bean) {
		// TODO Auto-generated method stub
		return  getDao().selectdevlist(bean);
	}
	/**
	 * 跟据工序id，班组ID 查询出所有的设备信息
	 * @param bean
	 * @return
	 */
	public List<SelectListEntity> selectDevByProTea(SelectListEntity bean) {
		// TODO Auto-generated method stub
		return  getDao().selectDevByProTea(bean);
	}

}
