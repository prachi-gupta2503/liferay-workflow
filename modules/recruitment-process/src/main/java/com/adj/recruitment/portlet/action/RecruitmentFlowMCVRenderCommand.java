package com.adj.recruitment.portlet.action;

import com.adj.demo.model.Candidate;
import com.adj.recruitment.constants.RecruitmentProcessPortletKeys;
import com.adj.recruitment.portlet.render.CandidateListMVCRenderCommand;
import com.adj.recruitment.portlet.util.CustomWorkflowTaskUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowInstance;
import com.liferay.portal.kernel.workflow.WorkflowLog;
import com.liferay.portal.kernel.workflow.WorkflowTaskManagerUtil;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = { "javax.portlet.name=" + RecruitmentProcessPortletKeys.RECRUITMENTPROCESS,
"mvc.command.name=/recruitment/flow" }, service = MVCActionCommand.class)

public class RecruitmentFlowMCVRenderCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		try {
		logger.info("RecruitmentFlowMCVRenderCommand started ");
		String transitionName = ParamUtil.getString(actionRequest, "transitionName");
		long workflowTaskId = ParamUtil.getLong(actionRequest, "workflowTaskId");
		long candidateId = ParamUtil.getLong(actionRequest, "candidateId");
		String comments = ParamUtil.getString(actionRequest, "comments");
		WorkflowInstance workflowInstace = CustomWorkflowTaskUtil.getWorkflowInstace(Candidate.class.getName(),
				themeDisplay, candidateId);
		logger.info("transitionName"+ transitionName);
		
		CustomWorkflowTaskUtil.completeWorkflowTask(themeDisplay, workflowInstace, workflowTaskId, comments, transitionName);

		List<User> users = UserLocalServiceUtil.getRoleUsers(46407l, -1, -1);
		  logger.info("User size ::"+ users.size());
		for (User user : users) {
		    long userId = user.getUserId();

		    // Define task title, description, and other necessary information.
		    // ...
		    logger.info("User ::"+ user.getFullName());
		    // Create and assign a task to the user.
		    WorkflowTaskManagerUtil.assignWorkflowTaskToUser(themeDisplay.getCompanyId(), userId, workflowTaskId, userId, "", null, workflowInstace.getWorkflowContext());
		    
		}
		
		//CustomWorkflowTaskUtil.completeWorkflowTask(themeDisplay, workflowInstace, workflowTaskId, comments, transitionName);
	}catch(Exception e) {
		logger.error(e);
	}
	}
	
	private static  Log logger=LogFactoryUtil.getLog(RecruitmentFlowMCVRenderCommand.class);

}
