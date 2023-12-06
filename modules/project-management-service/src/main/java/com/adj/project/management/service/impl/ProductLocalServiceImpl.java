/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.adj.project.management.service.impl;

import com.adj.project.management.model.Product;
import com.adj.project.management.service.ProductLocalServiceUtil;
import com.adj.project.management.service.base.ProductLocalServiceBaseImpl;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.WorkflowDefinitionLink;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;
import com.liferay.portal.workflow.kaleo.model.KaleoDefinition;
import com.liferay.portal.workflow.kaleo.service.KaleoDefinitionLocalServiceUtil;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

/**
 * The implementation of the product local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.adj.product.management.service.ProductLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProductLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=com.adj.product.management.model.Product",
	service = AopService.class
)
public class ProductLocalServiceImpl extends ProductLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>com.adj.product.management.service.ProductLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.adj.product.management.service.ProductLocalServiceUtil</code>.
	 */
	
	

	public Product addProduct(ServiceContext serviceContext, String name, long workflowId) {
		Product product = ProductLocalServiceUtil.createProduct(CounterLocalServiceUtil.increment());
		try {
			User user = userLocalService.getUser(serviceContext.getUserId());
			product.setName(name);
			product.setWorkflowId(workflowId);
			product.setUserId(serviceContext.getUserId());
			product.setUserName(user.getFullName());
			product.setGroupId(user.getGroupId());

			product.setStatus(WorkflowConstants.STATUS_DRAFT);
			product.setStatusByUserId(user.getUserId());
			product.setStatusDate(new Date());
			product.setStatusByUserName(user.getFullName());
			product.setStatusByUserUuid(user.getUserUuid());

			product = ProductLocalServiceUtil.addProduct(product);

			
			

			AssetEntry assetEntry = assetEntryLocalService.updateEntry(user.getUserId(),
					serviceContext.getScopeGroupId(), new Date(), new Date(), Product.class.getName(),
					product.getProductId(), product.getUuid(), 0, null, null, true, false, new Date(), null, new Date(),
					null, ContentTypes.TEXT_HTML, product.getName(), product.getName(), null, null, null, 0, 0, null);

			//startWorkflowInstance(user.getUserId(), product, serviceContext, workflowId);

			
			  WorkflowHandlerRegistryUtil.startWorkflowInstance(product.getCompanyId(),
			  product.getGroupId(), product.getUserId(), Product.class.getName(),
			  product.getPrimaryKey(), product, serviceContext);
			 

		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}

	public Product updateStatus(long userId, long productId, int status, ServiceContext serviceContext) {
		Product product = null;
		try {
			product = ProductLocalServiceUtil.getProduct(productId);
			product.setStatus(status);
			product.setStatusByUserId(userId);
			product.setStatusDate(new Date());
			User user = null;

			user = userLocalService.getUser(userId);
			product.setStatusByUserName(user.getFullName());
			product.setStatusByUserUuid(user.getUserUuid());
		} catch (PortalException e) {
			e.printStackTrace();
		}
		product = ProductLocalServiceUtil.updateProduct(product);
		try {
			if (status == WorkflowConstants.STATUS_APPROVED) {
				// update the asset status to visibile
				assetEntryLocalService.updateEntry(Product.class.getName(), productId, new Date(), null, true, true);
			} else {
			
				assetEntryLocalService.updateVisible(Product.class.getName(), productId, false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}

	protected void startWorkflowInstance(long userId, Product baseEntity, ServiceContext serviceContext,
			long workflowId) throws PortalException {

	
		KaleoDefinition kaleoDefinition = KaleoDefinitionLocalServiceUtil.getKaleoDefinition(workflowId);
		
		Map<String, Serializable> workflowContext = new HashMap<>();
		workflowContext.put("utilClass", "com.adj.project.management.service.ProductLocalServiceUtil");
		try {
			WorkflowDefinitionLink defnLink = WorkflowDefinitionLinkLocalServiceUtil
					.getWorkflowDefinitionLink(serviceContext.getCompanyId(), baseEntity.getGroupId(), Product.class.getName(), 0, 0);
			System.out.println("*********Name try******"+defnLink.getWorkflowDefinitionName());
			
		
		} catch (Exception e) {
			WorkflowDefinitionLink addWorkflowDefinitionLink = WorkflowDefinitionLinkLocalServiceUtil.addWorkflowDefinitionLink(serviceContext.getUserId(),
					serviceContext.getCompanyId(), baseEntity.getGroupId(), Product.class.getName(), 0, 0, kaleoDefinition.getName(), kaleoDefinition.getVersion());
			System.out.println("*********Name catch******"+addWorkflowDefinitionLink.getWorkflowDefinitionName());
			
		}

		Product product = WorkflowHandlerRegistryUtil.startWorkflowInstance(baseEntity.getCompanyId(), baseEntity.getGroupId(), userId,
				Product.class.getName(), baseEntity.getProductId(), baseEntity, serviceContext, workflowContext);

		System.err.println("********************after startup********"+product.getStatus());
		
		  
		 

	}

}