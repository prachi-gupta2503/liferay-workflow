package com.adj.recruitment.portlet;

import com.adj.demo.model.Candidate;
import com.adj.demo.service.CandidateLocalService;
import com.adj.recruitment.constants.RecruitmentProcessPortletKeys;
import com.adj.recruitment.portlet.render.CandidateListMVCRenderCommand;
import com.adj.recruitment.portlet.util.CustomWorkflowTaskUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.kernel.workflow.WorkflowInstance;
import com.liferay.portal.kernel.workflow.WorkflowLog;
import com.liferay.portal.kernel.workflow.WorkflowTask;
import com.liferay.portal.kernel.workflow.WorkflowTaskManagerUtil;

import java.io.IOException;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author AdjLT3
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=RecruitmentProcess",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/application-form.jsp",
		"javax.portlet.name=" + RecruitmentProcessPortletKeys.RECRUITMENTPROCESS,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class RecruitmentProcessPortlet extends MVCPortlet {
	
	
}