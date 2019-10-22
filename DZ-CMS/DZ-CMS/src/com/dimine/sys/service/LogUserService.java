package com.dimine.sys.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dimine.base.dao.BaseDao;
import com.dimine.base.service.BaseService;
import com.dimine.sys.dao.LogUserDao;
@Service("logUserService")
public class LogUserService<T> extends BaseService<T> {
	@Autowired
	private LogUserDao<T> dao;
	@Override
	public BaseDao<T> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	/**
	 * 批量删除操作日志
	 * @param ids
	 */
	public void deltebatch(String ids){
		String []idarry = ids.split(",");
		for(int i=0;i<idarry.length;i++){
			dao.delete((T) idarry[i]);
		}
	}
	/**
	 * 删除所有的日志
	 */
	public void deleteall(){
		dao.deleteAll();
	}
}
