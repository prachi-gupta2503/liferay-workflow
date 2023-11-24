package com.adj.recruitment.handler;

import com.adj.demo.model.Candidate;
import com.adj.demo.service.CandidateLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.workflow.BaseWorkflowHandler;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowHandler;

import java.io.Serializable;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	    property = {"model.class.name=com.adj.demo.model.Candidate"},
	    service = WorkflowHandler.class
	)
public class RecruitmentWorkflowHandler  extends BaseWorkflowHandler<Candidate> {

	@Override
	public String getClassName() {
		// TODO Auto-generated method stub
		return Candidate.class.getName();
	}

	@Override
	public String getType(Locale locale) {
		
		return "Candidate";
	}

	@Override
	public Candidate updateStatus(int status, Map<String, Serializable> workflowContext) throws PortalException {
		long userId = GetterUtil.getLong((String) workflowContext.get(WorkflowConstants.CONTEXT_USER_ID));
		long classPK = GetterUtil.getLong((String) workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));
		ServiceContext serviceContext = (ServiceContext) workflowContext.get("serviceContext");
		return candidateLocalService.updateStatus(userId, classPK, status, serviceContext, workflowContext);
		
	}

	@Reference
	private CandidateLocalService candidateLocalService;
}
