package com.dimine.pub.service;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dimine.base.service.BaseService;
import com.dimine.base.util.KeyUtils;
import com.dimine.pub.dao.AttachmentDao;
import com.dimine.pub.entity.AttachmentEntity;
import com.dimine.util.ConstantUtil;
import com.dimine.util.DocConverter;
import com.dimine.util.FileUploadUntil;

/**
 * 附件信息管理事务处理
 * 
 * @author dimine 2015-03-10 15:52:56
 * 
 */
@Service("attachmentService")
public class AttachmentService<T> extends BaseService<T> {

	@Autowired
	private AttachmentDao<T> dao;
	/**
	 * pdf转swf的运行软件的路径，从spring的service的配置获取
	 */
	private String pdfexestr;

	public AttachmentDao<T> getDao() {
		return dao;
	}

	/**
	 * 添加新的附件信息
	 * 
	 * @param bean
	 * @param actiontype
	 * @param user
	 */
	public void insert(AttachmentEntity bean) throws Exception {
		// 插入数据
		getDao().insert((T) bean);
	}

	/**
	 * 修改附件信息信息
	 * 
	 * @param bean
	 * @param actiontype
	 */
	public void update(AttachmentEntity bean) throws Exception {
		// 执行修改操作
		getDao().update((T) bean);
	}

	/**
	 * 删除附件信息信息
	 * 
	 * @param bean
	 * 
	 */
	public void delete(AttachmentEntity bean) {
		// 删除附件信息
		getDao().delete((T) bean);
	}

	/**
	 * 根据业务id查询附件信息
	 * 
	 * @param bean
	 * @return
	 */
	public List<AttachmentEntity> selectByBusinessid(AttachmentEntity bean) {
		return getDao().selectByBusinessid(bean);
	}

	public String getBiztypename() {
		return "附件信息信息管理";
	}

	/**
	 * 课件查询
	 * 
	 * @param bean
	 * @return
	 */
	public List<AttachmentEntity> selectCwList(AttachmentEntity bean) {
		Integer rowCount = dao.selectCwCount(bean);
		bean.getPager().setRowCount(rowCount);
		return (List<AttachmentEntity>) dao.selectCwList(bean);
	}

	/**
	 * 根据文件路径删除文件
	 * 
	 * @param bean
	 */
	public void deleteByUrl(AttachmentEntity bean) {
		getDao().deleteByUrl(bean);
	}

	/**
	 * 修改文件路径
	 * 
	 * @param bean
	 */
	public void updateUrl(AttachmentEntity bean) {
		getDao().updateUrl(bean);
	}
	
	/**
	 * SSM
	 * 
	 * @param attchmentid
	 *            已有附件的attachmentid
	 * @param attchmentname
	 *            附件的名称
	 * @param attchmenturl
	 *            附件上传的url
	 * @param userid
	 *            用户id
	 * @param deptid
	 *            部门id
	 * @param biztype
	 *            业务类型
	 * @param bizid
	 *            业务id
	 */
	public String uploadSwf(String attchmentid, String attchmentname,
			String attchmenturl, String userid, String deptid, String biztype,
			String bizid) {
		// 首先删除已有的附件
		AttachmentEntity att1 = (AttachmentEntity) getDao().selectById(
				attchmentid);
		// 如果附件不为空则先删除原来的附件
		if (att1 != null) {
			// 上传文件时候，删除原有的文件
			String dstPath = ServletActionContext.getServletContext()
					.getRealPath("upfile" + "/" + att1.getAttachmenturl());
			// 删除附件
			FileUtils.deleteQuietly(new File(dstPath));
			// 删除附件衍生出来的.swf文件
			if (att1.getAttachmenttype().equals(ConstantUtil.FJLX_DOC)
					|| att1.getAttachmenttype().equals(ConstantUtil.FJLX_PDF)
					|| att1.getAttachmenttype().equals(ConstantUtil.FJLX_XLS)) {
				FileUtils.deleteQuietly(new File(dstPath.substring(0,
						dstPath.lastIndexOf("."))
						+ ".swf"));
			}
			getDao().delete((T) att1);
		}
		AttachmentEntity att = new AttachmentEntity();
		String keyID = KeyUtils.createKeyID();
		att.setAttachmentid(keyID);
		att.setCreateid(userid);
		att.setAttachmentname(attchmentname);
		att.setAttachmenturl(attchmenturl);
		att.setBzstype(biztype);
		att.setBusinessid(bizid);
		// 附件类型
		att.setAttachmenttype(FileUploadUntil.getAttchmenttype(attchmentname));
		getDao().insert((T) att);
		// 如果附件类型为xls，doc将数据转换为swf 可以进行在线预览
		/*if (att.getAttachmenttype() != null
				&& (att.getAttachmenttype().equals(ConstantUtil.FJLX_DOC) || att
						.getAttachmenttype().equals(ConstantUtil.FJLX_XLS))) {
			DocConverter doc = new DocConverter(ServletActionContext
					.getServletContext().getRealPath(
							"upfile" + "/" + att.getAttachmenturl()));
			doc.setPdfexestr(pdfexestr);
			doc.conver();
		}*/
		return keyID;

	}
	/**
	 * 将附件转换为swf
	 * @param attachmenturl
	 */
	public void converswf(String attachmenturl){
		DocConverter doc = new DocConverter(ServletActionContext.getServletContext()
				.getRealPath("upfile"+attachmenturl));
		doc.setPdfexestr(pdfexestr);
		doc.conver();
	}
	public String getPdfexestr() {
		return pdfexestr;
	}

	public void setPdfexestr(String pdfexestr) {
		this.pdfexestr = pdfexestr;
	}
	
	
}
