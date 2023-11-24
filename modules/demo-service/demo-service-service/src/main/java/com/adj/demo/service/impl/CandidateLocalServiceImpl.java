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

package com.adj.demo.service.impl;

import com.adj.demo.model.Candidate;
import com.adj.demo.model.Employee;
import com.adj.demo.service.EmployeeLocalServiceUtil;
import com.adj.demo.service.base.CandidateLocalServiceBaseImpl;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.adj.demo.model.Candidate",
	service = AopService.class
)
public class CandidateLocalServiceImpl extends CandidateLocalServiceBaseImpl {
	
	public Candidate saveCandidate(Map<String,Serializable> candidateMap,ServiceContext serviceContext) {
		Candidate candidate=null;
		try {
		User user=UserLocalServiceUtil.getUser(serviceContext.getUserId());
		candidate = createCandidate(CounterLocalServiceUtil.increment());
		candidate.setFirstName((String)candidateMap.get("firstName"));
		candidate.setLastName((String)candidateMap.get("lastName"));
		candidate.setEmail((String)candidateMap.get("email"));
		candidate.setPhoneNo((String)candidateMap.get("phoneNo"));
		candidate.setEducation((String)candidateMap.get("education"));
		candidate.setSkills((String)candidateMap.get("skills"));
		
		candidate.setUserId(serviceContext.getUserId());
		candidate.setUserName(user.getFirstName());
		candidate.setGroupId(serviceContext.getScopeGroupId());
		candidate.setStatus(WorkflowConstants.STATUS_PENDING);
	    candidate.setStatusByUserId(serviceContext.getUserId());
        candidate.setStatusDate(new Date());
        candidate.setStatusByUserName(user.getFullName());
        candidate.setStatusByUserUuid(user.getUserUuid());
        
		candidate = candidateLocalService.addCandidate(candidate);
		
		AssetEntry assetEntry = assetEntryLocalService.updateEntry(serviceContext.getUserId(), serviceContext.getScopeGroupId(), new Date(),
	            new Date(), Candidate.class.getName(),candidate.getCandidateId(), candidate.getUuid(), 0, null, null, true, false, new Date(), null,
	            new Date(), null, ContentTypes.TEXT_HTML, candidate.getFirstName(), candidate.getFirstName(), null, null, null, 0, 0, null);

		
		WorkflowHandlerRegistryUtil.startWorkflowInstance(serviceContext.getCompanyId() ,serviceContext.getUserId(), Candidate.class.getName(),candidate.getCandidateId(), candidate, serviceContext);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return candidate;
	}
	
	public Candidate updateStatus(long userId, long classPK ,int status,ServiceContext serviceContext, Map<String, Serializable> workflowContext) {
		Candidate Candidate = candidatePersistence.fetchByPrimaryKey(classPK);
		logger.info("status"+status);
		Candidate.setStatus(status);
		 Candidate.setStatusByUserId(userId);
		 Candidate.setStatusDate(new Date());
		 User user = null;
		 try {
		      user = userLocalService.getUser(userId);
		      Candidate.setStatusByUserName(user.getFullName());
		      Candidate.setStatusByUserUuid(user.getUserUuid());
		 } catch (PortalException e) {
		     e.printStackTrace();
		 }
		 
		  Candidate = candidatePersistence.update(Candidate);
		if(status==WorkflowConstants.STATUS_APPROVED) {
			logger.info("status Approved");
			Employee employee = EmployeeLocalServiceUtil.createEmployee(CounterLocalServiceUtil.increment());
			employee.setFirstName(Candidate.getFirstName());
			employee.setLastName(Candidate.getLastName());
			employee.setUserId(userId);
			employee.setUserName(user.getFullName());
			employee.setGroupId(user.getGroupId());
			employee.setEmail(Candidate.getEmail());
			employee.setPhoneNo(Candidate.getPhoneNo());
			employee.setPosition("Software Engineer");
			EmployeeLocalServiceUtil.addEmployee(employee);
			logger.info("employee"+ employee.toString());
		}
		  
		return Candidate;
	}
	
	private static  Log logger=LogFactoryUtil.getLog(CandidateLocalServiceImpl.class);

}