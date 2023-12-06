package com.adj.project.management.handler;

import com.adj.project.management.model.Product;
import com.adj.project.management.service.ProductLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.workflow.BaseWorkflowHandler;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowHandler;

import java.io.Serializable;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
@Component(
		 property = {"model.class.name=com.adj.project.management.model.Product"},
		 service = WorkflowHandler.class
		)
public class ProductWorkflowHandler extends BaseWorkflowHandler<Product> {

	@Override
	public String getClassName() {
		// TODO Auto-generated method stub
		return Product.class.getName();
	}

	@Override
	public String getType(Locale locale) {
		// TODO Auto-generated method stub
		return "product";
	}

	@Override
	public Product updateStatus(int status, Map<String, Serializable> workflowContext) throws PortalException {
		long userId = GetterUtil.getLong((String)workflowContext.get(WorkflowConstants.CONTEXT_USER_ID));
	    long productId = GetterUtil.getLong((String)workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));
	    ServiceContext serviceContext = (ServiceContext)workflowContext.get("serviceContext");
	    Product product = productLocalService.updateStatus(userId, productId , status, serviceContext);
	    return product;
	}

	@Reference
	ProductLocalService productLocalService;

}
