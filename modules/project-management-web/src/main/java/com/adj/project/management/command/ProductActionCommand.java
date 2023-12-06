package com.adj.project.management.command;

import com.adj.project.management.constants.ProjectMamagementWebPortletKeys;
import com.adj.project.management.model.Project;
import com.adj.project.management.service.ProductLocalServiceUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;


@Component(
		 property = {
		 "javax.portlet.name=" + ProjectMamagementWebPortletKeys.PROJECTMAMAGEMENTWEB,
		 "mvc.command.name=saveProduct"
		 }, service =  MVCActionCommand.class
		 )
public class ProductActionCommand  extends BaseMVCActionCommand{

	@Override
	protected void doProcessAction(ActionRequest request, ActionResponse response) throws Exception {
	
		System.out.println("DoProcessAction**********");
     ServiceContext serviceContext = ServiceContextFactory.getInstance(Project.class.getName(), request);
		
		String name=ParamUtil.getString(request, "name");
		long workflowId=ParamUtil.getLong(request, "workflowId");
	
		ProductLocalServiceUtil.addProduct(serviceContext, name, workflowId);
		response.setRenderParameter("jspPage", "/product_list.jsp");
	}

	
}
