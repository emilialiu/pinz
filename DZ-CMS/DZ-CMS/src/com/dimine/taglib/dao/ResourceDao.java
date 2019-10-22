package com.dimine.taglib.dao;

import java.util.List;
import java.util.Map;

import com.dimine.base.dao.BaseDao;
import com.dimine.base.dao.BizDao;
import com.dimine.sys.entity.DictEntity;
import com.dimine.taglib.entity.ResourceBean;

@BizDao
public interface ResourceDao<T> extends BaseDao<T> {
	//一级下拉列表
	public List<ResourceBean> selectdictlist(DictEntity bean);
	//赛选某阶段和某业务类型没有选择的工序
	public List<ResourceBean> selectgxlist(DictEntity bean);
	//二级下拉列表
	public List<ResourceBean> selectdictsecondlist(DictEntity bean);
	/**
	 * 获取元素表信息
	 * @param bean
	 * @return
	 */
	public List<ResourceBean> findElement(DictEntity bean);
}
