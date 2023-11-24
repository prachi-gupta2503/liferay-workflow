package com.adj.recruitment.portlet.render;

import com.adj.demo.model.Candidate;
import com.adj.demo.service.CandidateLocalService;
import com.adj.recruitment.constants.RecruitmentProcessPortletKeys;
import com.adj.recruitment.portlet.dto.CandidateDTO;
import com.adj.recruitment.portlet.util.CustomWorkflowTaskUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.kernel.workflow.WorkflowInstance;
import com.liferay.portal.kernel.workflow.WorkflowLog;
import com.liferay.portal.kernel.workflow.WorkflowNode;
import com.liferay.portal.kernel.workflow.WorkflowNode.Type;
import com.liferay.portal.kernel.workflow.WorkflowTask;
import com.liferay.portal.kernel.workflow.WorkflowTaskManagerUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = { "javax.portlet.name=" + RecruitmentProcessPortletKeys.RECRUITMENTPROCESS,
"mvc.command.name=/" }, service = MVCRenderCommand.class)

public class CandidateListMVCRenderCommand implements MVCRenderCommand{

	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		List<String> roleNames = themeDisplay.getUser().getRoles().stream().map(Role::getName).collect(Collectors.toList());
		List<CandidateDTO> candidateDtoList=new ArrayList<>();
		List<Candidate> candidates = candidateLocalService.getCandidates(-1, -1);
		List<WorkflowNode> workflowNodes = CustomWorkflowTaskUtil.getWorkflowNodes("02f93705-164d-3cd9-f5bf-c88e9a17f1be", themeDisplay.getCompanyId());
		workflowNodes = workflowNodes.stream().filter(e->e.getType().equals(Type.TASK)).collect(Collectors.toList()); 
		
		for(Candidate candidate :candidates ) {
			try {
				CandidateDTO candidateDto = new CandidateDTO(candidate);
				WorkflowInstance workflowInstace = CustomWorkflowTaskUtil.getWorkflowInstace(Candidate.class.getName(),
						themeDisplay, candidate.getCandidateId());
				List<WorkflowLog> workflowLogs = CustomWorkflowTaskUtil.getWorkflowLogs(themeDisplay.getCompanyId(),
						workflowInstace);
				/*
				 * workflowLogs.stream().forEach(e -> logger.info("node level" +
				 * e.getCurrentWorkflowNodeName()));
				 */
				
				long workflowTaskId = CustomWorkflowTaskUtil.getWorkflowTaskIdByLogs(workflowLogs);
				Map<String, Boolean> levelStatus = new HashMap<String, Boolean>();
				updateLevelstatus(workflowLogs, levelStatus);
				/* levelStatus.forEach((k,v)->logger.info("current stage"+ k+"  status"+ v)); */
				WorkflowTask workflowTask = WorkflowTaskManagerUtil.getWorkflowTask(themeDisplay.getCompanyId(),
						workflowTaskId);
				List<String> transitionNames = CustomWorkflowTaskUtil.getTransitionNames(themeDisplay, workflowTaskId);
				String name = workflowTask.getName();

				candidateDto.setStatus(WorkflowConstants.getStatusLabel(candidate.getStatus()));
				candidateDto.setLevel(name);
				candidateDto.setTransitionNames(transitionNames);
				candidateDto.setWorkflowTaskId(workflowTask.getWorkflowTaskId());
				candidateDto.setLevelStatus(levelStatus);
				long assigneeUserId = workflowTask.getAssigneeUserId();
				if (!roleNames.contains(RoleConstants.POWER_USER) || !roleNames.contains(RoleConstants.ADMINISTRATOR)) {
					if (themeDisplay.getUserId() == assigneeUserId) {
						candidateDtoList.add(candidateDto);
					}
				} else {
					candidateDtoList.add(candidateDto);
				}

			} catch (WorkflowException e) {

				e.printStackTrace();
			}
			
			
		}
		boolean isAdmin=false;
		if (roleNames.contains(RoleConstants.POWER_USER) || roleNames.contains(RoleConstants.ADMINISTRATOR)) {
			
			isAdmin=true;
		}
		renderRequest.setAttribute("isAdmin", isAdmin);	
		renderRequest.setAttribute("candidateDtoList", candidateDtoList);
		renderRequest.setAttribute("recruitmentStep", workflowNodes);
		
		return "/applicant-list-pivot.jsp";
			
	
	}

	public Map<String, Boolean>  updateLevelstatus(List<WorkflowLog> workflowLogs,Map<String, Boolean> levelStatus){
		
		
		for(WorkflowLog workflowLog:workflowLogs) {
			long workflowTaskId = workflowLog.getWorkflowTaskId();
			
			boolean taskCompleted = CustomWorkflowTaskUtil.isTaskCompleted(workflowTaskId);
			levelStatus.put(workflowLog.getCurrentWorkflowNodeName(), taskCompleted);
		}
		return levelStatus;
				
	}
	
	private static  Log logger=LogFactoryUtil.getLog(CandidateListMVCRenderCommand.class);

	@Reference
	CandidateLocalService candidateLocalService;
}
