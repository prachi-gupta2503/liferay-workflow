package com.adj.recruitment.portlet.render;

import com.adj.demo.model.Candidate;
import com.adj.demo.service.CandidateLocalService;
import com.adj.recruitment.constants.RecruitmentProcessPortletKeys;
import com.adj.recruitment.portlet.dto.CandidateDTO;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = { "javax.portlet.name=" + RecruitmentProcessPortletKeys.RECRUITMENTPROCESS,
		"mvc.command.name=/view/candidate" }, service = MVCRenderCommand.class)
public class ViewCandidateProfileMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		CandidateDTO candidateDto=new CandidateDTO();
		try {
		String[] transitionNames = ParamUtil.getStringValues(renderRequest, "transitionNames");
		long workflowTaskId = ParamUtil.getLong(renderRequest, "workflowTaskId");
		long candidateId = ParamUtil.getLong(renderRequest, "candidateId");
		Candidate candidate = candidateLocalService.getCandidate(candidateId);
		candidateDto=new CandidateDTO(candidate);
		
		logger.info("List.of(transitionName)  "+ List.of(transitionNames));
		
	    List<String>transitionList=new ArrayList<>();
		for(String transitionName: transitionNames) {
			transitionName=transitionName.replace("[", "");
			transitionName=transitionName.replace("]", "");
			
			transitionList.add(transitionName.trim());
		}
		logger.info("transitionName"+ transitionList);
		candidateDto.setWorkflowTaskId(workflowTaskId);	
		candidateDto.setTransitionNames(transitionList);
		
		}catch(Exception e) {
			logger.error(e);
		}
		renderRequest.setAttribute("candidate", candidateDto);
		List<String> roleNames = themeDisplay.getUser().getRoles().stream().map(Role::getName).collect(Collectors.toList());
		
		if(roleNames.contains(RoleConstants.POWER_USER) || roleNames.contains(RoleConstants.ADMINISTRATOR)) {
			renderRequest.setAttribute("isAdmin", true);
		}else {
			renderRequest.setAttribute("isAdmin", false);
		}
		return "/view-candidate-profile.jsp";
	}


	@Reference
	CandidateLocalService candidateLocalService;
	private static Log logger = LogFactoryUtil.getLog(ViewCandidateProfileMVCRenderCommand.class);
}
