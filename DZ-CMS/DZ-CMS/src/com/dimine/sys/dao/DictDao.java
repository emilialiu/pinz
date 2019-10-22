package com.dimine.sys.dao;

import java.util.List;

import com.dimine.base.dao.BaseDao;
import com.dimine.base.dao.BizDao;
import com.dimine.sys.entity.DictEntity;

/**
 * Dict Mapper
 * 
 * @author LCF
 * 
 */
@BizDao
public interface DictDao<T> extends BaseDao<T> {
	/**
	 * 根据资源类型编号删除资源
	 * 
	 * @param typeCode
	 * @return
	 */
	public void deleteByTypeCode(String typeCode);

	/**
	 * 取得某资源类型下最大的资源属性编号
	 * 
	 * @param typeCode
	 * @return
	 */
	public String maxDictid(String typeCode);

	/**
	 * 根据资源编号获得资源名称
	 * 
	 * @param dictid
	 * @return
	 */
	public String dictNameByid(String dictid);

	/**
	 * 根据资源名称获得资源编号
	 * 
	 * @param dictid
	 * @return
	 */
	public String dictCodeByName(String name);

	/**
	 * 根据dicttypeid取list集合
	 * 
	 * @param dicttypeid
	 * @return
	 */
	public List<DictEntity> dictBydicttypid(String dicttypeid);

	public List<String> dictNameBytypeid(String dicttypeid);

	public DictEntity dictidByname(DictEntity bean);

	/**
	 * 根据parentid查询paramname
	 * 
	 * @param name
	 * @return
	 */
	public List<DictEntity> getSelectByParent(DictEntity bean);
	public List<DictEntity> dictBySql(String sql);
}
