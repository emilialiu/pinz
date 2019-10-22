package com.dimine.activity.listener;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.delegate.event.ActivitiVariableEvent;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.dimine.activity.entity.FeeborrowEntity;
import com.dimine.activity.entity.LeaveEntity;
import com.dimine.activity.service.FeeborrowService;

@Controller("feborrowEndprocessor")
public class FeborrowEndprocessor implements ExecutionListener {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 @Autowired
	 RuntimeService runtimeService;
	
	FeeborrowService<FeeborrowEntity> feeborrowService;
	public FeeborrowService<FeeborrowEntity> getFeeborrowService() {
		return feeborrowService;
	}
	@Autowired
	public void setFeeborrowService(
			FeeborrowService<FeeborrowEntity> feeborrowService) {
		this.feeborrowService = feeborrowService;
	}
	
	@Override
	public void notify(DelegateExecution arg0) throws Exception {
		
	}
	 
}
