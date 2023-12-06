package com.adj.project.management.portlet;

import com.adj.project.management.constants.ProjectMamagementWebPortletKeys;
import com.adj.project.management.model.Project;
import com.adj.project.management.service.ProductLocalServiceUtil;
import com.adj.project.management.service.ProjectLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author adj_2
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=ProjectMamagementWeb",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + ProjectMamagementWebPortletKeys.PROJECTMAMAGEMENTWEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class ProjectManagementWebPortlet extends MVCPortlet {
	
	
	public void addProject(ActionRequest request,ActionResponse response) throws PortalException {
		
		ServiceContext serviceContext = ServiceContextFactory.getInstance(Project.class.getName(), request);
		String name=(String)request.getParameter("name");
		String workflowId=request.getParameter("workflowName");
	
		//project.setWorkflowName(Integer.parseInt(workflowName));
		ProjectLocalServiceUtil.addProject(serviceContext, name, Long.parseLong(workflowId));
		
	}
	
	public void addProduct(ActionRequest request,ActionResponse response) throws PortalException {
		
		ServiceContext serviceContext = ServiceContextFactory.getInstance(Project.class.getName(), request);
		
		String name=ParamUtil.getString(request, "name");
		long workflowId=ParamUtil.getLong(request, "workflowId");
	
		ProductLocalServiceUtil.addProduct(serviceContext, name, workflowId);
		response.setRenderParameter("jspPage", "/product_list.jsp");
	}
}