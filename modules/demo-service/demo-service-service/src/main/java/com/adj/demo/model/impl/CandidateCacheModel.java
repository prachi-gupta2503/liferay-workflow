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

package com.adj.demo.model.impl;

import com.adj.demo.model.Candidate;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Candidate in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class CandidateCacheModel
	implements CacheModel<Candidate>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof CandidateCacheModel)) {
			return false;
		}

		CandidateCacheModel candidateCacheModel = (CandidateCacheModel)object;

		if (candidateId == candidateCacheModel.candidateId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, candidateId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(37);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", candidateId=");
		sb.append(candidateId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", firstName=");
		sb.append(firstName);
		sb.append(", lastName=");
		sb.append(lastName);
		sb.append(", phoneNo=");
		sb.append(phoneNo);
		sb.append(", email=");
		sb.append(email);
		sb.append(", Skills=");
		sb.append(Skills);
		sb.append(", education=");
		sb.append(education);
		sb.append(", status=");
		sb.append(status);
		sb.append(", statusByUserId=");
		sb.append(statusByUserId);
		sb.append(", statusByUserName=");
		sb.append(statusByUserName);
		sb.append(", statusDate=");
		sb.append(statusDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Candidate toEntityModel() {
		CandidateImpl candidateImpl = new CandidateImpl();

		if (uuid == null) {
			candidateImpl.setUuid("");
		}
		else {
			candidateImpl.setUuid(uuid);
		}

		candidateImpl.setCandidateId(candidateId);
		candidateImpl.setGroupId(groupId);
		candidateImpl.setCompanyId(companyId);
		candidateImpl.setUserId(userId);

		if (userName == null) {
			candidateImpl.setUserName("");
		}
		else {
			candidateImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			candidateImpl.setCreateDate(null);
		}
		else {
			candidateImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			candidateImpl.setModifiedDate(null);
		}
		else {
			candidateImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (firstName == null) {
			candidateImpl.setFirstName("");
		}
		else {
			candidateImpl.setFirstName(firstName);
		}

		if (lastName == null) {
			candidateImpl.setLastName("");
		}
		else {
			candidateImpl.setLastName(lastName);
		}

		if (phoneNo == null) {
			candidateImpl.setPhoneNo("");
		}
		else {
			candidateImpl.setPhoneNo(phoneNo);
		}

		if (email == null) {
			candidateImpl.setEmail("");
		}
		else {
			candidateImpl.setEmail(email);
		}

		if (Skills == null) {
			candidateImpl.setSkills("");
		}
		else {
			candidateImpl.setSkills(Skills);
		}

		if (education == null) {
			candidateImpl.setEducation("");
		}
		else {
			candidateImpl.setEducation(education);
		}

		candidateImpl.setStatus(status);
		candidateImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			candidateImpl.setStatusByUserName("");
		}
		else {
			candidateImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			candidateImpl.setStatusDate(null);
		}
		else {
			candidateImpl.setStatusDate(new Date(statusDate));
		}

		candidateImpl.resetOriginalValues();

		return candidateImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		candidateId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		firstName = objectInput.readUTF();
		lastName = objectInput.readUTF();
		phoneNo = objectInput.readUTF();
		email = objectInput.readUTF();
		Skills = objectInput.readUTF();
		education = objectInput.readUTF();

		status = objectInput.readInt();

		statusByUserId = objectInput.readLong();
		statusByUserName = objectInput.readUTF();
		statusDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(candidateId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (firstName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(firstName);
		}

		if (lastName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(lastName);
		}

		if (phoneNo == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(phoneNo);
		}

		if (email == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(email);
		}

		if (Skills == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(Skills);
		}

		if (education == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(education);
		}

		objectOutput.writeInt(status);

		objectOutput.writeLong(statusByUserId);

		if (statusByUserName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(statusByUserName);
		}

		objectOutput.writeLong(statusDate);
	}

	public String uuid;
	public long candidateId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String firstName;
	public String lastName;
	public String phoneNo;
	public String email;
	public String Skills;
	public String education;
	public int status;
	public long statusByUserId;
	public String statusByUserName;
	public long statusDate;

}