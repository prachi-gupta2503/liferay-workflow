package com.adj.recruitment.portlet.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.WorkflowInstanceLink;
import com.liferay.portal.kernel.service.WorkflowInstanceLinkLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowDefinition;
import com.liferay.portal.kernel.workflow.WorkflowDefinitionManagerUtil;
import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.kernel.workflow.WorkflowInstance;
import com.liferay.portal.kernel.workflow.WorkflowInstanceManagerUtil;
import com.liferay.portal.kernel.workflow.WorkflowLog;
import com.liferay.portal.kernel.workflow.WorkflowLogManagerUtil;
import com.liferay.portal.kernel.workflow.WorkflowNode;
import com.liferay.portal.kernel.workflow.WorkflowTaskManagerUtil;
import com.liferay.portal.kernel.workflow.comparator.WorkflowComparatorFactoryUtil;
import com.liferay.portal.workflow.metrics.service.persistence.WorkflowMetricsSLADefinitionVersionUtil;

import java.util.ArrayList;
import java.util.List;

public class CustomWorkflowTaskUtil {

	private CustomWorkflowTaskUtil() {}
	
	public static  WorkflowInstance getWorkflowInstace(String className, ThemeDisplay themeDisplay, long classPK){
		try {
			WorkflowInstanceLink instanceLink = WorkflowInstanceLinkLocalServiceUtil.getWorkflowInstanceLink(
					themeDisplay.getCompanyId(), 0, className, classPK);
		
			if (Validator.isNotNull(instanceLink)) {
				return WorkflowInstanceManagerUtil.getWorkflowInstance(themeDisplay.getCompanyId(),
						instanceLink.getWorkflowInstanceId());
			}
		} catch (PortalException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	public static  List<WorkflowLog> getWorkflowLogs(long companyId, WorkflowInstance instance) {
		List<Integer> assignLogTypes = new ArrayList<>();
		assignLogTypes.add(WorkflowLog.TASK_ASSIGN);
		try {
			
			return WorkflowLogManagerUtil.getWorkflowLogsByWorkflowInstance(companyId, instance.getWorkflowInstanceId(),
					assignLogTypes, -1, -1, WorkflowComparatorFactoryUtil.getLogCreateDateComparator(true));
		} catch (WorkflowException e) {
			logger.error(e.getMessage(), e);
		}
		return new ArrayList<>();
	}
	
	public static  long getWorkflowTaskIdByLogs(List<WorkflowLog> wfAssignLogs) {
		try {
			
		return wfAssignLogs.get(wfAssignLogs.size() - 1).getWorkflowTaskId();
		}catch(Exception e) {
			logger.info(e.getMessage());
		}
		return 0;
		
	}

	
	public static  List<String> getTransitionNames(ThemeDisplay themeDisplay, long workflowTaskId) {
		try {
			
			return WorkflowTaskManagerUtil.getNextTransitionNames(themeDisplay.getCompanyId(), themeDisplay.getUserId(),
					workflowTaskId);
		} catch (WorkflowException e) {
			logger.error(e.getMessage(), e);
		}
		return new ArrayList<>();
	}
	
	public static  void completeWorkflowTask(ThemeDisplay themeDisplay, WorkflowInstance instance, long workflowTaskId,
			String comments, String transitionName) {
		try {
			
			
			WorkflowTaskManagerUtil.completeWorkflowTask(themeDisplay.getCompanyId(), themeDisplay.getUserId(),
					workflowTaskId, transitionName, comments, instance.getWorkflowContext());
		} catch (PortalException e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	public static List<WorkflowNode> getWorkflowNodes(String workflowDefinitionName,long companyId){
		
		WorkflowDefinition workflowDefinition;
		try {
			
			workflowDefinition=WorkflowDefinitionManagerUtil.getLatestWorkflowDefinition(companyId, workflowDefinitionName);
			workflowDefinition.getWorkflowTransitions().stream().forEach(e->logger.info("source ::"+e.getSourceNodeName()+" target:: " + e.getTargetNodeName()));
			
			return workflowDefinition.getWorkflowNodes();
		} catch (WorkflowException e) {
			logger.info(e);
		}
		return new ArrayList<>();
	}
	
	public static  boolean isTaskCompleted(long workflowTaskId) {
		try {
			
			return WorkflowTaskManagerUtil.fetchWorkflowTask(workflowTaskId).isCompleted();
		} catch (WorkflowException e) {
			logger.error(e.getMessage(), e);
		}
		return false;
	}
	
	
	private static  Log logger=LogFactoryUtil.getLog(CustomWorkflowTaskUtil.class);
}
