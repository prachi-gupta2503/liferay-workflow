package com.adj.recruitment.portlet.render;

import com.adj.recruitment.constants.RecruitmentProcessPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = { "javax.portlet.name=" + RecruitmentProcessPortletKeys.RECRUITMENTPROCESS,
"mvc.command.name=/saveApplicantant" }, service = MVCRenderCommand.class)

public class TestRender implements MVCRenderCommand{

	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		System.out.println("RenderCommand");
		return null;
	}

}
