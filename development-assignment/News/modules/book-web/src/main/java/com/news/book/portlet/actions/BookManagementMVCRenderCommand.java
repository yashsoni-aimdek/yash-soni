package com.news.book.portlet.actions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.aimdek.assignment.model.Author;
import com.aimdek.assignment.model.Book;
import com.aimdek.assignment.service.AuthorLocalService;
import com.aimdek.assignment.service.BookLocalService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
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

		List<Book> books = bookLocalService.getBooks(-1, -1);

		Map<Long, List<Author>> bookAuthors = new HashMap<Long, List<Author>>();
		for (Book book : books) {
			LOG.info("bookId : " + book.getBookId() + " Book Name : " + book.getBookName());
			try {
				List<Author> authors = authorLocalService.getBookAuthors(book.getBookId());
				bookAuthors.put(book.getBookId(), authors);
			} catch (Exception e) {
				LOG.error(e.getMessage());
			}
		}

		renderRequest.setAttribute("books", books);
		renderRequest.setAttribute("bookAuthors", bookAuthors);
		return "/view.jsp";
	}
	@Reference
	private BookLocalService bookLocalService;
	
	@Reference
	private AuthorLocalService authorLocalService;
	
	private static final Log LOG = LogFactoryUtil.getLog(BookManagementMVCRenderCommand.class.getName());	
}
