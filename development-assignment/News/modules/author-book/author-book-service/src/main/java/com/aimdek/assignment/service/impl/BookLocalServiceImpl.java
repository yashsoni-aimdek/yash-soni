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

import com.aimdek.assignment.exception.BookException;
import com.aimdek.assignment.model.Book;
import com.aimdek.assignment.service.BookLocalService;
import com.aimdek.assignment.service.base.BookLocalServiceBaseImpl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.aimdek.assignment.model.Book",
	service = AopService.class
)
public class BookLocalServiceImpl extends BookLocalServiceBaseImpl {
	
	@Indexable(type = IndexableType.REINDEX)
	public Book addBook(Book book,ServiceContext serviceContext) throws PortalException{
		
		validateBook(book);
		return addBook(book);
		
	}
	@Indexable(type = IndexableType.REINDEX)
	public Book updateBook(Book book,ServiceContext serviceContext) throws PortalException{
		
		validateBook(book);
		System.out.println("book" +book);
		return updateBook(book);
		
	}
	@Indexable(type = IndexableType.DELETE)
	public Book deleteBook(long	bookId) throws PortalException{
			
		return super.deleteBook(bookId);
	}
	
	private void validateBook(Book book) throws BookException{
		
		if(Validator.isNull(book.getBookCode())) {
			throw new BookException("book-code-required");
			
		}
		if(Validator.isNull(book.getBookName())) {
			throw new BookException("book-name-required");
			
		}
		
	}


}