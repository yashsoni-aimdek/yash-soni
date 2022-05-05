package com.news.author.portlet.actions;

import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.aimdek.assignment.model.Author;
import com.aimdek.assignment.model.Book;
import com.aimdek.assignment.service.AuthorLocalService;
import com.aimdek.assignment.service.BookLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.news.author.constants.AuthorWebPortletKeys;


@Component(
		immediate = true,
		property = {
				"javax.portlet.name=" + AuthorWebPortletKeys.AUTHORWEB,
				"mvc.command.name=/author/add/edit"
		},
		service = MVCRenderCommand.class
)
public class AuthorAddEditRenderCommand implements MVCRenderCommand{

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		
		long authorId = ParamUtil.getLong(renderRequest, "authorId", -1);
		long bookId = ParamUtil.getLong(renderRequest,"bookId", -1);
		Author author = null;	
		Book book = null;
		try {
			if(authorId > 0) {
				author = authorLocalService.getAuthor(authorId);
				book = bookLocalService.getBook(bookId);
			}
			
		}catch(PortalException e) {
			LOG.error(e.getMessage(), e);
		}
		renderRequest.setAttribute("author", author);
		renderRequest.setAttribute("book", book);
		return "/add_edit.jsp";
	}
	
	@Reference
	private AuthorLocalService authorLocalService;
	
	@Reference
	private BookLocalService bookLocalService;
	
	private static final Log LOG = LogFactoryUtil.getLog(AuthorAddEditActionCommand.class);
}
 