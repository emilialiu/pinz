package com.dimine.sys.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dimine.base.dao.BaseDao;
import com.dimine.base.service.BaseService;
import com.dimine.sys.dao.LogDispatchDao;

@Service("logDispatchService")
public class LogDispatchService<T> extends BaseService<T> {
	@Autowired
	private LogDispatchDao<T> dao;
	@Override
	public BaseDao<T> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	
	/**批量删除操作日志*/
	public void deltebatch(String ids){
		String []idarry = ids.split(",");
		for(int i=0;i<idarry.length;i++){
			dao.delete((T) idarry[i]);
		}
	}
	
	/**删除所有的日志*/
	public void deleteall(){
		dao.deleteAll();
	}
}
