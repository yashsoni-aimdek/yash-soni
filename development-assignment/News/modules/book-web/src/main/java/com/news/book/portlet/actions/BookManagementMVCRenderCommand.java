package com.news.book.portlet.actions;

import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.aimdek.assignment.model.Book;
import com.aimdek.assignment.service.BookLocalService;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.news.book.constants.BookWebPortletKeys;

@Component(
		immediate = true,
		property = {
				"javax.portlet.name=" + BookWebPortletKeys.BOOKWEB,
				"mvc.command.name=/"
		},
		service = MVCRenderCommand.class
)
public class BookManagementMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		
		List<Book> book = bookLocalService.getBooks(-1, -1);
		renderRequest.setAttribute("books", book);
		
		return "/view.jsp";
	}
	@Reference
	private BookLocalService bookLocalService;
}
