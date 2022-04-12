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

package com.aimdek.assignment.service.impl;

import com.aimdek.assignment.model.Author;
import com.aimdek.assignment.service.base.AuthorServiceBaseImpl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = {
		"json.web.service.context.name=news",
		"json.web.service.context.path=Author"
	},
	service = AopService.class
)
public class AuthorServiceImpl extends AuthorServiceBaseImpl {
	
	public Author getAuthor(long authorId) throws PortalException{
		
		return authorLocalService.getAuthor(authorId);
		
	}
	
}