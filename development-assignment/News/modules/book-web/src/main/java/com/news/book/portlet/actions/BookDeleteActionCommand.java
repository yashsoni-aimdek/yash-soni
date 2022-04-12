package com.news.book.portlet.actions;

import java.awt.print.Book;
import java.util.Date;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

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
import com.news.book.portlet.actions.BookDeleteActionCommand;
import com.news.book.constants.BookWebPortletKeys;

@Component(
		immediate = true,
		property = {
				"javax.portlet.name=" + BookWebPortletKeys.BOOKWEB,
				"mvc.command.name=/book/delete"
		},
		service = MVCActionCommand.class
)
public class BookDeleteActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		
		long bookId = ParamUtil.getLong(actionRequest, "bookId", -1);
		bookLocalService.deleteBook(bookId);
		
	}
	
	@Reference
	private BookLocalService bookLocalService;
	
	private static final Log LOG = LogFactoryUtil.getLog(BookDeleteActionCommand.class);
}
