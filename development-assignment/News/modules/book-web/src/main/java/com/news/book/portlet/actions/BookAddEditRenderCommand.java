package com.news.book.portlet.actions;



import java.io.IOException;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.aimdek.assignment.model.Book;
import com.aimdek.assignment.service.BookLocalService;
import com.liferay.captcha.util.CaptchaUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.news.book.constants.BookWebPortletKeys;

@Component(
		immediate = true,
		property = {
				"javax.portlet.name=" + BookWebPortletKeys.BOOKWEB,
				"mvc.command.name=/book/add/edit"
		},
		service = MVCRenderCommand.class
)
public class BookAddEditRenderCommand implements MVCRenderCommand{
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		
		long bookId = ParamUtil.getLong(renderRequest, "bookId", 0);
		Book book = null;	
		try {
			if(bookId > 0) {
				book = bookLocalService.getBook(bookId);
			}
		}catch(PortalException e) {
			LOG.error(e.getMessage(), e);
		}
		renderRequest.setAttribute("book", book);
		
		return "/add_edit.jsp";
	}
	
	@Reference
	private BookLocalService bookLocalService;
	
	private static final Log LOG = LogFactoryUtil.getLog(BookAddEditActionCommand.class);
	
}
