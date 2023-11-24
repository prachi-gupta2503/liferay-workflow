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

package com.adj.demo.service.persistence;

import com.adj.demo.exception.NoSuchCandidateException;
import com.adj.demo.model.Candidate;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the candidate service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CandidateUtil
 * @generated
 */
@ProviderType
public interface CandidatePersistence extends BasePersistence<Candidate> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CandidateUtil} to access the candidate persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the candidates where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching candidates
	 */
	public java.util.List<Candidate> findByUuid(String uuid);

	/**
	 * Returns a range of all the candidates where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CandidateModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of candidates
	 * @param end the upper bound of the range of candidates (not inclusive)
	 * @return the range of matching candidates
	 */
	public java.util.List<Candidate> findByUuid(
		String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the candidates where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CandidateModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of candidates
	 * @param end the upper bound of the range of candidates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching candidates
	 */
	public java.util.List<Candidate> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Candidate>
			orderByComparator);

	/**
	 * Returns an ordered range of all the candidates where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CandidateModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of candidates
	 * @param end the upper bound of the range of candidates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching candidates
	 */
	public java.util.List<Candidate> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Candidate>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first candidate in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching candidate
	 * @throws NoSuchCandidateException if a matching candidate could not be found
	 */
	public Candidate findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Candidate>
				orderByComparator)
		throws NoSuchCandidateException;

	/**
	 * Returns the first candidate in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching candidate, or <code>null</code> if a matching candidate could not be found
	 */
	public Candidate fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Candidate>
			orderByComparator);

	/**
	 * Returns the last candidate in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching candidate
	 * @throws NoSuchCandidateException if a matching candidate could not be found
	 */
	public Candidate findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Candidate>
				orderByComparator)
		throws NoSuchCandidateException;

	/**
	 * Returns the last candidate in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching candidate, or <code>null</code> if a matching candidate could not be found
	 */
	public Candidate fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Candidate>
			orderByComparator);

	/**
	 * Returns the candidates before and after the current candidate in the ordered set where uuid = &#63;.
	 *
	 * @param candidateId the primary key of the current candidate
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next candidate
	 * @throws NoSuchCandidateException if a candidate with the primary key could not be found
	 */
	public Candidate[] findByUuid_PrevAndNext(
			long candidateId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Candidate>
				orderByComparator)
		throws NoSuchCandidateException;

	/**
	 * Removes all the candidates where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of candidates where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching candidates
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the candidate where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchCandidateException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching candidate
	 * @throws NoSuchCandidateException if a matching candidate could not be found
	 */
	public Candidate findByUUID_G(String uuid, long groupId)
		throws NoSuchCandidateException;

	/**
	 * Returns the candidate where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching candidate, or <code>null</code> if a matching candidate could not be found
	 */
	public Candidate fetchByUUID_G(String uuid, long groupId);

	/**
	 * Returns the candidate where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching candidate, or <code>null</code> if a matching candidate could not be found
	 */
	public Candidate fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache);

	/**
	 * Removes the candidate where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the candidate that was removed
	 */
	public Candidate removeByUUID_G(String uuid, long groupId)
		throws NoSuchCandidateException;

	/**
	 * Returns the number of candidates where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching candidates
	 */
	public int countByUUID_G(String uuid, long groupId);

	/**
	 * Returns all the candidates where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching candidates
	 */
	public java.util.List<Candidate> findByUuid_C(String uuid, long companyId);

	/**
	 * Returns a range of all the candidates where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CandidateModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of candidates
	 * @param end the upper bound of the range of candidates (not inclusive)
	 * @return the range of matching candidates
	 */
	public java.util.List<Candidate> findByUuid_C(
		String uuid, long companyId, int start, int end);

	/**
	 * Returns an ordered range of all the candidates where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CandidateModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of candidates
	 * @param end the upper bound of the range of candidates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching candidates
	 */
	public java.util.List<Candidate> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Candidate>
			orderByComparator);

	/**
	 * Returns an ordered range of all the candidates where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CandidateModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of candidates
	 * @param end the upper bound of the range of candidates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching candidates
	 */
	public java.util.List<Candidate> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Candidate>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first candidate in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching candidate
	 * @throws NoSuchCandidateException if a matching candidate could not be found
	 */
	public Candidate findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Candidate>
				orderByComparator)
		throws NoSuchCandidateException;

	/**
	 * Returns the first candidate in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching candidate, or <code>null</code> if a matching candidate could not be found
	 */
	public Candidate fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Candidate>
			orderByComparator);

	/**
	 * Returns the last candidate in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching candidate
	 * @throws NoSuchCandidateException if a matching candidate could not be found
	 */
	public Candidate findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Candidate>
				orderByComparator)
		throws NoSuchCandidateException;

	/**
	 * Returns the last candidate in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching candidate, or <code>null</code> if a matching candidate could not be found
	 */
	public Candidate fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Candidate>
			orderByComparator);

	/**
	 * Returns the candidates before and after the current candidate in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param candidateId the primary key of the current candidate
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next candidate
	 * @throws NoSuchCandidateException if a candidate with the primary key could not be found
	 */
	public Candidate[] findByUuid_C_PrevAndNext(
			long candidateId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Candidate>
				orderByComparator)
		throws NoSuchCandidateException;

	/**
	 * Removes all the candidates where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of candidates where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching candidates
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns all the candidates where firstName = &#63;.
	 *
	 * @param firstName the first name
	 * @return the matching candidates
	 */
	public java.util.List<Candidate> findByFirstName(String firstName);

	/**
	 * Returns a range of all the candidates where firstName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CandidateModelImpl</code>.
	 * </p>
	 *
	 * @param firstName the first name
	 * @param start the lower bound of the range of candidates
	 * @param end the upper bound of the range of candidates (not inclusive)
	 * @return the range of matching candidates
	 */
	public java.util.List<Candidate> findByFirstName(
		String firstName, int start, int end);

	/**
	 * Returns an ordered range of all the candidates where firstName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CandidateModelImpl</code>.
	 * </p>
	 *
	 * @param firstName the first name
	 * @param start the lower bound of the range of candidates
	 * @param end the upper bound of the range of candidates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching candidates
	 */
	public java.util.List<Candidate> findByFirstName(
		String firstName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Candidate>
			orderByComparator);

	/**
	 * Returns an ordered range of all the candidates where firstName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CandidateModelImpl</code>.
	 * </p>
	 *
	 * @param firstName the first name
	 * @param start the lower bound of the range of candidates
	 * @param end the upper bound of the range of candidates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching candidates
	 */
	public java.util.List<Candidate> findByFirstName(
		String firstName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Candidate>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first candidate in the ordered set where firstName = &#63;.
	 *
	 * @param firstName the first name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching candidate
	 * @throws NoSuchCandidateException if a matching candidate could not be found
	 */
	public Candidate findByFirstName_First(
			String firstName,
			com.liferay.portal.kernel.util.OrderByComparator<Candidate>
				orderByComparator)
		throws NoSuchCandidateException;

	/**
	 * Returns the first candidate in the ordered set where firstName = &#63;.
	 *
	 * @param firstName the first name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching candidate, or <code>null</code> if a matching candidate could not be found
	 */
	public Candidate fetchByFirstName_First(
		String firstName,
		com.liferay.portal.kernel.util.OrderByComparator<Candidate>
			orderByComparator);

	/**
	 * Returns the last candidate in the ordered set where firstName = &#63;.
	 *
	 * @param firstName the first name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching candidate
	 * @throws NoSuchCandidateException if a matching candidate could not be found
	 */
	public Candidate findByFirstName_Last(
			String firstName,
			com.liferay.portal.kernel.util.OrderByComparator<Candidate>
				orderByComparator)
		throws NoSuchCandidateException;

	/**
	 * Returns the last candidate in the ordered set where firstName = &#63;.
	 *
	 * @param firstName the first name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching candidate, or <code>null</code> if a matching candidate could not be found
	 */
	public Candidate fetchByFirstName_Last(
		String firstName,
		com.liferay.portal.kernel.util.OrderByComparator<Candidate>
			orderByComparator);

	/**
	 * Returns the candidates before and after the current candidate in the ordered set where firstName = &#63;.
	 *
	 * @param candidateId the primary key of the current candidate
	 * @param firstName the first name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next candidate
	 * @throws NoSuchCandidateException if a candidate with the primary key could not be found
	 */
	public Candidate[] findByFirstName_PrevAndNext(
			long candidateId, String firstName,
			com.liferay.portal.kernel.util.OrderByComparator<Candidate>
				orderByComparator)
		throws NoSuchCandidateException;

	/**
	 * Removes all the candidates where firstName = &#63; from the database.
	 *
	 * @param firstName the first name
	 */
	public void removeByFirstName(String firstName);

	/**
	 * Returns the number of candidates where firstName = &#63;.
	 *
	 * @param firstName the first name
	 * @return the number of matching candidates
	 */
	public int countByFirstName(String firstName);

	/**
	 * Caches the candidate in the entity cache if it is enabled.
	 *
	 * @param candidate the candidate
	 */
	public void cacheResult(Candidate candidate);

	/**
	 * Caches the candidates in the entity cache if it is enabled.
	 *
	 * @param candidates the candidates
	 */
	public void cacheResult(java.util.List<Candidate> candidates);

	/**
	 * Creates a new candidate with the primary key. Does not add the candidate to the database.
	 *
	 * @param candidateId the primary key for the new candidate
	 * @return the new candidate
	 */
	public Candidate create(long candidateId);

	/**
	 * Removes the candidate with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param candidateId the primary key of the candidate
	 * @return the candidate that was removed
	 * @throws NoSuchCandidateException if a candidate with the primary key could not be found
	 */
	public Candidate remove(long candidateId) throws NoSuchCandidateException;

	public Candidate updateImpl(Candidate candidate);

	/**
	 * Returns the candidate with the primary key or throws a <code>NoSuchCandidateException</code> if it could not be found.
	 *
	 * @param candidateId the primary key of the candidate
	 * @return the candidate
	 * @throws NoSuchCandidateException if a candidate with the primary key could not be found
	 */
	public Candidate findByPrimaryKey(long candidateId)
		throws NoSuchCandidateException;

	/**
	 * Returns the candidate with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param candidateId the primary key of the candidate
	 * @return the candidate, or <code>null</code> if a candidate with the primary key could not be found
	 */
	public Candidate fetchByPrimaryKey(long candidateId);

	/**
	 * Returns all the candidates.
	 *
	 * @return the candidates
	 */
	public java.util.List<Candidate> findAll();

	/**
	 * Returns a range of all the candidates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CandidateModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of candidates
	 * @param end the upper bound of the range of candidates (not inclusive)
	 * @return the range of candidates
	 */
	public java.util.List<Candidate> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the candidates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CandidateModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of candidates
	 * @param end the upper bound of the range of candidates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of candidates
	 */
	public java.util.List<Candidate> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Candidate>
			orderByComparator);

	/**
	 * Returns an ordered range of all the candidates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CandidateModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of candidates
	 * @param end the upper bound of the range of candidates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of candidates
	 */
	public java.util.List<Candidate> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Candidate>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the candidates from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of candidates.
	 *
	 * @return the number of candidates
	 */
	public int countAll();

}