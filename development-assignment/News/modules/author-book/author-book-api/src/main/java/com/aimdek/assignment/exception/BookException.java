package com.aimdek.assignment.exception;

import com.liferay.portal.kernel.exception.PortalException;

public class BookException extends PortalException {

	public BookException(){
		
	}
	public BookException(String msg) {
		super(msg);
	}
	public BookException(String msg, Throwable throwable) {
		super(msg, throwable);
	}
	public BookException (Throwable throwable) {
		super(throwable);
	}
	
}
