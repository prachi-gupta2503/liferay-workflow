package com.adj.recruitment.portlet.action;

import com.adj.demo.model.Candidate;
import com.adj.demo.service.CandidateLocalService;
import com.adj.recruitment.constants.RecruitmentProcessPortletKeys;
import com.adj.recruitment.portlet.render.CandidateListMVCRenderCommand;
import com.adj.recruitment.portlet.util.CustomWorkflowTaskUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowInstance;
import com.liferay.portal.kernel.workflow.WorkflowLog;
import com.liferay.portal.kernel.workflow.WorkflowTaskManagerUtil;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = { "javax.portlet.name=" + RecruitmentProcessPortletKeys.RECRUITMENTPROCESS,
		"mvc.command.name=/application/form" }, service = MVCActionCommand.class)
public class SaveApplicationForm  extends BaseMVCActionCommand{

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();
		Map<String,Serializable> candidateMap=new HashMap<>();
		candidateMap.put("firstName", ParamUtil.getString(actionRequest, "firstName"));
		candidateMap.put("lastName", ParamUtil.getString(actionRequest, "lastName"));
		candidateMap.put("email", ParamUtil.getString(actionRequest, "email"));
		candidateMap.put("phoneNo", ParamUtil.getString(actionRequest, "phoneNo"));
		candidateMap.put("education", ParamUtil.getString(actionRequest, "education"));
		candidateMap.put("skills", ParamUtil.getString(actionRequest, "skills"));
		Candidate saveCandidate = candidateLocalService.saveCandidate(candidateMap, serviceContext);
		
		
		

	}

	private static  Log logger=LogFactoryUtil.getLog(CandidateListMVCRenderCommand.class);

	@Reference
	CandidateLocalService candidateLocalService;
}
