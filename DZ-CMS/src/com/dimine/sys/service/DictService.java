package com.dimine.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimine.base.service.BaseService;
import com.dimine.sys.dao.DictDao;
import com.dimine.sys.dao.DictTypeDao;
import com.dimine.sys.entity.DictEntity;
import com.dimine.sys.entity.DictTypeEntity;

/**
 * 
 * <资源属性逻辑关系处理类> <功能详细描述>
 * 
 * @author LCF
 * @version [版本号, 2014-7-25]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service("dictService")
public class DictService<T> extends BaseService<T> {

	@Autowired
	private DictDao<T> dao;

	public DictDao<T> getDao() {
		return dao;
	}

	@Autowired
	private DictTypeDao<T> dictTypeDao;

	public DictTypeDao<T> getDictTypeDao() {
		return dictTypeDao;
	}

	/**
	 * 添加新的属性信息 <功能详细描述>
	 * 
	 * @param bean
	 * @see [类、类#方法、类#成员]
	 */
	public void insert(DictEntity bean) {
		int id = 0;

		String maxdictid = dao.maxDictid(bean.getTypeid());
		DictTypeEntity type = (DictTypeEntity) dictTypeDao.selectById(bean
				.getTypeid());
		String typeCode = type.getCode();
		int typeCodeLength = typeCode.length();
		// 如果该父节点不存在属性
		if (maxdictid != null) {
			id = Integer.parseInt(maxdictid.substring(typeCodeLength));
		}
		id++;
		StringBuffer _code = new StringBuffer();
		String _id = Integer.toString(id).toString();
		int idLength = _id.length();
		_code.append(_id);
		for (int i = idLength; i < 3; i++) {
			_code.insert(0, "0");
		}
		_code.insert(0, typeCode);
		bean.setCode(_code.toString());
		dao.insert((T) bean);
	}

	/**
	 * <修改资源属性> <功能详细描述>
	 * 
	 * @see [类、类#方法、类#成员]
	 */
	public void update(DictEntity bean) {
		dao.update((T) bean);
	}

	/**
	 * <删除资源属性> <功能详细描述>
	 * 
	 * @param codes
	 * @see [类、类#方法、类#成员]
	 */
	public void delete(String code) {
		dao.delete((T) code);
	}

	/**
	 * 根据资源编号获得资源名称
	 * 
	 * @param dictid
	 * @return
	 */
	public String dictNameByid(String dictid) {
		return dao.dictNameByid(dictid);
	}

	/**
	 * 根据资源名称获得资源编号
	 * 
	 * @param dictid
	 * @return
	 */
	public String dictCodeByName(String name) {
		return dao.dictCodeByName(name);
	}

	/**
	 * 根据名字取字典值
	 * 
	 * @param name
	 * @return
	 */
	public DictEntity dictidByname(DictEntity bean) {
		return dao.dictidByname(bean);
	}

	/**
	 * 根据parentid查询paramname
	 * 
	 * @param name
	 * @return
	 */
	public List<DictEntity> getSelectByParent(DictEntity bean) {
		return getDao().getSelectByParent(bean);
	}

	/**
	 * 根据dicttypeid取字典信息
	 * 
	 * @return
	 */
	public List<DictEntity> dictBydicttypid(String dicttypeid) {
		return dao.dictBydicttypid(dicttypeid);
	}

	public List<String> dictNameBytypeid(String dicttypeid) {
		return dao.dictNameBytypeid(dicttypeid);
	}
	
	public List<DictEntity> dictBySql(String sql) {
		return dao.dictBySql(sql);
	}

	public String getBiztypename() {
		// TODO Auto-generated method stub
		return "资源属性信息管理";
	}

}
