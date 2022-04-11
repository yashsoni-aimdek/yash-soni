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

import com.aimdek.assignment.exception.AuthorException;
import com.aimdek.assignment.model.Author;
import com.aimdek.assignment.model.Book;
import com.aimdek.assignment.service.BookLocalService;
import com.aimdek.assignment.service.base.AuthorLocalServiceBaseImpl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = "model.class.name=com.aimdek.assignment.model.Author",
	service = AopService.class
)
public class AuthorLocalServiceImpl extends AuthorLocalServiceBaseImpl {
	
	public Author addAuthor(Author author,ServiceContext serviceContext) throws PortalException{
		
		validateAuthor(author);
		return addAuthor(author);
		
	}
	
	public Author updateAuthor(Author author,ServiceContext serviceContext) throws PortalException{
		
		validateAuthor(author);
		System.out.println("author" +author);
		return updateAuthor(author);
		
	}
	
	public Author deleteAuthor(long	authorId) throws PortalException{
			
		return  super.deleteAuthor(authorId);
	}
	
	private void validateAuthor(Author author) throws AuthorException {
		
		if(Validator.isNull(author.getAuthorCode())){
			
			throw new AuthorException("author-code-required");
			
		}
		if(Validator.isNull(author.getAuthorName())) {
			
			throw new AuthorException("author-name-required");
			
		}
		
	}
	
	
}