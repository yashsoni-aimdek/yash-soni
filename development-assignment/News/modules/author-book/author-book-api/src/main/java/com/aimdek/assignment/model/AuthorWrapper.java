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

package com.aimdek.assignment.model;

import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Author}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Author
 * @generated
 */
public class AuthorWrapper
	extends BaseModelWrapper<Author> implements Author, ModelWrapper<Author> {

	public AuthorWrapper(Author author) {
		super(author);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("authorId", getAuthorId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("authorCode", getAuthorCode());
		attributes.put("authorName", getAuthorName());
		attributes.put("authorRegisterDate", getAuthorRegisterDate());
		attributes.put("bookId", getBookId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long authorId = (Long)attributes.get("authorId");

		if (authorId != null) {
			setAuthorId(authorId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String authorCode = (String)attributes.get("authorCode");

		if (authorCode != null) {
			setAuthorCode(authorCode);
		}

		String authorName = (String)attributes.get("authorName");

		if (authorName != null) {
			setAuthorName(authorName);
		}

		Date authorRegisterDate = (Date)attributes.get("authorRegisterDate");

		if (authorRegisterDate != null) {
			setAuthorRegisterDate(authorRegisterDate);
		}

		Long bookId = (Long)attributes.get("bookId");

		if (bookId != null) {
			setBookId(bookId);
		}
	}

	@Override
	public Author cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the author birth day of this author.
	 *
	 * @return the author birth day of this author
	 */
	@Override
	public Date getAuthorRegisterDate() {
		return model.getAuthorRegisterDate();
	}

	/**
	 * Returns the author code of this author.
	 *
	 * @return the author code of this author
	 */
	@Override
	public String getAuthorCode() {
		return model.getAuthorCode();
	}

	/**
	 * Returns the author ID of this author.
	 *
	 * @return the author ID of this author
	 */
	@Override
	public long getAuthorId() {
		return model.getAuthorId();
	}

	/**
	 * Returns the author name of this author.
	 *
	 * @return the author name of this author
	 */
	@Override
	public String getAuthorName() {
		return model.getAuthorName();
	}

	/**
	 * Returns the book ID of this author.
	 *
	 * @return the book ID of this author
	 */
	@Override
	public long getBookId() {
		return model.getBookId();
	}

	/**
	 * Returns the company ID of this author.
	 *
	 * @return the company ID of this author
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this author.
	 *
	 * @return the create date of this author
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the group ID of this author.
	 *
	 * @return the group ID of this author
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the modified date of this author.
	 *
	 * @return the modified date of this author
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the primary key of this author.
	 *
	 * @return the primary key of this author
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the user ID of this author.
	 *
	 * @return the user ID of this author
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this author.
	 *
	 * @return the user name of this author
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this author.
	 *
	 * @return the user uuid of this author
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the uuid of this author.
	 *
	 * @return the uuid of this author
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the author birth day of this author.
	 *
	 * @param authorRegisterDate the author birth day of this author
	 */
	@Override
	public void setAuthorRegisterDate(Date authorRegisterDate) {
		model.setAuthorRegisterDate(authorRegisterDate);
	}

	/**
	 * Sets the author code of this author.
	 *
	 * @param authorCode the author code of this author
	 */
	@Override
	public void setAuthorCode(String authorCode) {
		model.setAuthorCode(authorCode);
	}

	/**
	 * Sets the author ID of this author.
	 *
	 * @param authorId the author ID of this author
	 */
	@Override
	public void setAuthorId(long authorId) {
		model.setAuthorId(authorId);
	}

	/**
	 * Sets the author name of this author.
	 *
	 * @param authorName the author name of this author
	 */
	@Override
	public void setAuthorName(String authorName) {
		model.setAuthorName(authorName);
	}

	/**
	 * Sets the book ID of this author.
	 *
	 * @param bookId the book ID of this author
	 */
	@Override
	public void setBookId(long bookId) {
		model.setBookId(bookId);
	}

	/**
	 * Sets the company ID of this author.
	 *
	 * @param companyId the company ID of this author
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this author.
	 *
	 * @param createDate the create date of this author
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the group ID of this author.
	 *
	 * @param groupId the group ID of this author
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the modified date of this author.
	 *
	 * @param modifiedDate the modified date of this author
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the primary key of this author.
	 *
	 * @param primaryKey the primary key of this author
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the user ID of this author.
	 *
	 * @param userId the user ID of this author
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this author.
	 *
	 * @param userName the user name of this author
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this author.
	 *
	 * @param userUuid the user uuid of this author
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this author.
	 *
	 * @param uuid the uuid of this author
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	@Override
	public StagedModelType getStagedModelType() {
		return model.getStagedModelType();
	}

	@Override
	protected AuthorWrapper wrap(Author author) {
		return new AuthorWrapper(author);
	}

}