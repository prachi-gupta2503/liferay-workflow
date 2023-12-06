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

import com.adj.project.management.model.Project;
import com.adj.project.management.service.ProjectLocalServiceUtil;
import com.adj.project.management.service.base.ProjectLocalServiceBaseImpl;
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
import com.liferay.portal.workflow.kaleo.service.KaleoDefinitionLocalServiceUtil;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

/**
 * The implementation of the project local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * <code>com.adj.project.management.service.ProjectLocalService</code>
 * interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProjectLocalServiceBaseImpl
 */
@Component(property = "model.class.name=com.adj.project.management.model.Project", service = AopService.class)
public class ProjectLocalServiceImpl extends ProjectLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use
	 * <code>com.adj.project.management.service.ProjectLocalService</code> via
	 * injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use
	 * <code>com.adj.project.management.service.ProjectLocalServiceUtil</code>.
	 */

	public Project addProject(ServiceContext serviceContext, String name, long workflowId) {
		Project project = ProjectLocalServiceUtil.createProject(CounterLocalServiceUtil.increment());
		try {
			User user = userLocalService.getUser(serviceContext.getUserId());
			project.setName(name);
			project.setWorkflowId(workflowId);
			project.setUserId(serviceContext.getUserId());
			project.setUserName(user.getFullName());
			project.setGroupId(user.getGroupId());

			project.setStatus(WorkflowConstants.STATUS_DRAFT);
			project.setStatusByUserId(user.getUserId());
			project.setStatusDate(new Date());
			project.setStatusByUserName(user.getFullName());
			project.setStatusByUserUuid(user.getUserUuid());

			project = ProjectLocalServiceUtil.addProject(project);

			String workflowName = KaleoDefinitionLocalServiceUtil.getKaleoDefinition(workflowId)
					.getTitle(LocaleUtil.getDefault());
			System.out.println("workflowName" + workflowName);

			AssetEntry assetEntry = assetEntryLocalService.updateEntry(user.getUserId(),
					serviceContext.getScopeGroupId(), new Date(), new Date(), Project.class.getName(),
					project.getProjectId(), project.getUuid(), 0, null, null, true, false, new Date(), null, new Date(),
					null, ContentTypes.TEXT_HTML, project.getName(), project.getName(), null, null, null, 0, 0, null);

			startWorkflowInstance(user.getUserId(), project, serviceContext, workflowName);

			/*
			 * WorkflowHandlerRegistryUtil.startWorkflowInstance(project.getCompanyId(),
			 * project.getGroupId(), project.getUserId(), Project.class.getName(),
			 * project.getPrimaryKey(), project, serviceContext);
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
		return project;
	}

	public Project updateStatus(long userId, long projectId, int status, ServiceContext serviceContext) {
		Project project = null;
		try {
			project = ProjectLocalServiceUtil.getProject(projectId);
			project.setStatus(status);
			project.setStatusByUserId(userId);
			project.setStatusDate(new Date());
			User user = null;

			user = userLocalService.getUser(userId);
			project.setStatusByUserName(user.getFullName());
			project.setStatusByUserUuid(user.getUserUuid());
		} catch (PortalException e) {
			e.printStackTrace();
		}
		project = ProjectLocalServiceUtil.updateProject(project);
		try {
			if (status == WorkflowConstants.STATUS_APPROVED) {
				// update the asset status to visibile
				assetEntryLocalService.updateEntry(Project.class.getName(), projectId, new Date(), null, true, true);
			} else {
				// set leave entity status to false
				assetEntryLocalService.updateVisible(Project.class.getName(), projectId, false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return project;
	}

	protected void startWorkflowInstance(long userId, Project baseEntity, ServiceContext serviceContext,
			String workflowName) throws PortalException {

		//workflowName = "Single Approver";
		
		Map<String, Serializable> workflowContext = new HashMap<>();
		workflowContext.put("utilClass", "com.adj.project.management.service.ProductLocalServiceUtil;");
		try {
			WorkflowDefinitionLink defnLink = WorkflowDefinitionLinkLocalServiceUtil
					.getWorkflowDefinitionLink(serviceContext.getCompanyId(), baseEntity.getGroupId(), Project.class.getName(), baseEntity.getProjectId(), 0);
			System.out.println("*********Name try******"+defnLink.getWorkflowDefinitionName());
			
		
		} catch (Exception e) {
			WorkflowDefinitionLink addWorkflowDefinitionLink = WorkflowDefinitionLinkLocalServiceUtil.addWorkflowDefinitionLink(serviceContext.getUserId(),
					serviceContext.getCompanyId(), baseEntity.getGroupId(), Project.class.getName(), baseEntity.getProjectId(), 0, workflowName, 1);
			System.out.println("*********Name catch******"+addWorkflowDefinitionLink.getWorkflowDefinitionName());
			
		}

		WorkflowHandlerRegistryUtil.startWorkflowInstance(baseEntity.getCompanyId(), baseEntity.getGroupId(), userId,
				Project.class.getName(), baseEntity.getProjectId(), baseEntity, serviceContext, workflowContext);

		
		  
		 

	}
}