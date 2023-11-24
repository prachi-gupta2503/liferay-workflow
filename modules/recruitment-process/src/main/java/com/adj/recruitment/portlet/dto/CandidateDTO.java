package com.adj.recruitment.portlet.dto;

import com.adj.demo.model.Candidate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CandidateDTO {

	private long CandidateId;
	private String firstName;
	private String lastName;
	private String phoneNo;
	private String email;
	private String education;
	private String skills;
	private String status;
	private String level;
	private long workflowTaskId;
	private List<String> transitionNames;
	private Map<String,Boolean> levelStatus;
	
	
	public CandidateDTO() {
		super();
		
	}
	public CandidateDTO(Candidate candidate) {
		
		this.setCandidateId(candidate.getCandidateId());
		this.setFirstName(candidate.getFirstName());
		this.setLastName(candidate.getLastName());
		this.setPhoneNo(candidate.getPhoneNo());
		this.setEmail(candidate.getEmail());
		this.setEducation(candidate.getEducation());
		this.setSkills(candidate.getSkills());
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getSkills() {
		return skills;
	}
	public void setSkills(String skills) {
		this.skills = skills;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public List<String> getTransitionNames() {
		return transitionNames;
	}
	public void setTransitionNames(List<String> transitionNames) {
		this.transitionNames = transitionNames;
	}
	public long getCandidateId() {
		return CandidateId;
	}
	public void setCandidateId(long candidateId) {
		CandidateId = candidateId;
	}
	public long getWorkflowTaskId() {
		return workflowTaskId;
	}
	public void setWorkflowTaskId(long workflowTaskId) {
		this.workflowTaskId = workflowTaskId;
	}
	public Map<String, Boolean> getLevelStatus() {
		return levelStatus;
	}
	public void setLevelStatus(Map<String, Boolean> levelStatus) {
		this.levelStatus = levelStatus;
	}
	
	
}
