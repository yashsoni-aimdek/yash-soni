package com.news.book.portlet.actions;

import java.util.Date;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.aimdek.assignment.exception.BookException;
import com.aimdek.assignment.model.Book;
import com.aimdek.assignment.service.BookLocalService;
import com.liferay.counter.kernel.service.CounterLocalService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.news.book.portlet.actions.BookAddEditActionCommand;
import com.news.book.constants.BookWebPortletKeys;

@Component(
		immediate = true,
		property = {
				"javax.portlet.name=" + BookWebPortletKeys.BOOKWEB,
				"mvc.command.name=/book/add/edit"
		},
		service = MVCActionCommand.class
)
public class BookAddEditActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		
		ServiceContext serviceContext = ServiceContextFactory.getInstance(Book.class.getName(),actionRequest);

		long bookId = ParamUtil.getLong(actionRequest, "bookId", -1);
		
		try{
			if(bookId > 0) {
				
				
				Book book = bookLocalService.getBook(bookId);
				
			
				preparedBookDetails(actionRequest, book);
				bookLocalService.updateBook(book, serviceContext);
				
			}else {
				
				Book book = bookLocalService.createBook(counterLocalService.increment(Book.class.getName()));
				preparedBookDetails(actionRequest, book);
				bookLocalService.addBook(book, serviceContext);
			}
		}catch(BookException e) {
			
			SessionErrors.add(actionRequest, e.getMessage());
			PortalUtil.copyRequestParameters(actionRequest, actionResponse);
			actionResponse.getRenderParameters().setValue("mvcRenderCommandName", "/book/add/edit");
			actionResponse.getRenderParameters().setValue("bookId", String.valueOf(bookId));
			LOG.error(e.getMessage(),e);
		}
		
	}
	
	protected void preparedBookDetails(ActionRequest actionRequest, Book book) {
		
		ThemeDisplay themeDisplay =(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		
		String bookCode = ParamUtil.getString(actionRequest,"bookCode", "");
		String bookName = ParamUtil.getString(actionRequest, "bookName", "");
	
		
		book.setGroupId(themeDisplay.getScopeGroupId());
		book.setCompanyId(themeDisplay.getCompanyId());
		book.setUserId(themeDisplay.getUserId());
		book.setUserName(themeDisplay.getUser().getFullName());
		book.setBookCode(bookCode);
		book.setBookName(bookName);
		book.setBookPublishDate(new Date());
		
		LOG.info("Book Code: " +bookCode);
		LOG.info("Book Name: " +bookName);
		
	}
	
	@Reference
	private BookLocalService bookLocalService;
	
	@Reference
	private CounterLocalService counterLocalService;
	
	private static final Log LOG = LogFactoryUtil.getLog(BookAddEditActionCommand.class.getName());
	
}
