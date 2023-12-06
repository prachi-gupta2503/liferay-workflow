package com.adj.project.management.command;

import com.adj.project.management.constants.ProjectMamagementWebPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;


@Component(
		 property = {
		 "javax.portlet.name=" + ProjectMamagementWebPortletKeys.PROJECTMAMAGEMENTWEB,
		 "mvc.command.name=productList"
		 }, service =  MVCRenderCommand.class
		 )
public class ProductRenderCommand implements MVCRenderCommand{

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		
		String page=renderRequest.getParameter("page");
		if(page.equals("view")) {
			return "/product_list.jsp";
		}else if(page.equalsIgnoreCase("product_list")) {
			
			return "/view.jsp";
		}
		return "/view.jsp";
		
	}

}
