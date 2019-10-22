package com.dimine.activity.entity;

import org.activiti.engine.impl.persistence.entity.DeploymentEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.repository.Deployment;

import com.dimine.activity.modules.servlet.PropertiesServlet;

/**
 * 流程实体咧
 * @author Administrator
 *
 */
public class WorkflowEntity extends ProcessDefinitionEntity {
		// 参数
		private String param = "";

		public String getParam() {
			return param;
		}

		public void setParam(String param) {
			this.param = param;
		}
		
}
