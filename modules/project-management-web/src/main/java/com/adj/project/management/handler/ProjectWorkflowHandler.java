package com.adj.project.management.handler;

import com.adj.project.management.model.Project;
import com.adj.project.management.service.ProjectLocalService;
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
		 property = {"model.class.name=org.javasavvy.leave.model.Leave"},
		 service = WorkflowHandler.class
		)
public class ProjectWorkflowHandler extends BaseWorkflowHandler<Project> {

	@Override
	public String getClassName() {
		// TODO Auto-generated method stub
		return Project.class.getName();
	}

	@Override
	public String getType(Locale locale) {
		// TODO Auto-generated method stub
		return "project";
	}

	@Override
	public Project updateStatus(int status, Map<String, Serializable> workflowContext) throws PortalException {
		long userId = GetterUtil.getLong((String)workflowContext.get(WorkflowConstants.CONTEXT_USER_ID));
	    long leaveId = GetterUtil.getLong((String)workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));
	    ServiceContext serviceContext = (ServiceContext)workflowContext.get("serviceContext");
	    Project project = projectLocalService.updateStatus(userId, leaveId, status, serviceContext);
	    return project;
	}

	@Reference
	ProjectLocalService projectLocalService;

}
