package com.news.author.portlet.actions;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.counter.kernel.service.CounterLocalService;
import com.liferay.portal.kernel.exception.PortalException;
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
import com.news.author.constants.AuthorWebPortletKeys;
import com.aimdek.assignment.exception.AuthorException;
import com.aimdek.assignment.model.Author;
import com.aimdek.assignment.model.Book;
import com.aimdek.assignment.service.AuthorLocalService;
import com.aimdek.assignment.service.BookLocalService;
import com.aimdek.assignment.service.BookLocalServiceUtil;

@Component(
		immediate = true,
		property = {
				"javax.portlet.name=" + AuthorWebPortletKeys.AUTHORWEB,
				"mvc.command.name=/author/add/edit"
		},
		service = MVCActionCommand.class
)
public class AuthorAddEditActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		ServiceContext serviceContext = ServiceContextFactory.getInstance(Author.class.getName(), actionRequest);
		long authorId = ParamUtil.getLong(actionRequest, "authorId", -1);
		String[] bookIds = actionRequest.getParameterValues("bookId");
		
		List<Long> bookIdsList = Arrays.asList(bookIds).stream().map(n->Long.parseLong(n)).collect(Collectors.toList());
		
		try {
			if (authorId > 0) {
				Author author = authorLocalService.getAuthor(authorId);

				preparedAuthorDetails(actionRequest, author);
			
				authorLocalService.updateAuthor(author, serviceContext);

			} else {

				Author author = authorLocalService.createAuthor(counterLocalService.increment(Author.class.getName()));
				preparedAuthorDetails(actionRequest, author);
				authorLocalService.addAuthorBooks(author.getAuthorId(), bookIdsList);
				authorLocalService.addAuthor(author, serviceContext);
				
			}
		} catch (AuthorException e) {

			SessionErrors.add(actionRequest, e.getMessage());
			PortalUtil.copyRequestParameters(actionRequest, actionResponse);
			actionResponse.getRenderParameters().setValue("mvcRenderCommandName", "/author/add/edit");
			actionResponse.getRenderParameters().setValue("authorId", String.valueOf(authorId));
			LOG.error(e.getMessage(), e);
		}

	}

	private void preparedAuthorDetails(ActionRequest actionRequest, Author author)
			throws NumberFormatException, PortalException {

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		String authorCode = ParamUtil.getString(actionRequest, "authorCode", "");
		String authorName = ParamUtil.getString(actionRequest, "authorName", "");
		
		author.setGroupId(themeDisplay.getScopeGroupId());
		author.setUserId(themeDisplay.getUserId());
		author.setUserName(themeDisplay.getUser().getFullName());
		author.setCompanyId(themeDisplay.getCompanyId());
		author.setAuthorCode(authorCode);
		author.setAuthorName(authorName);
		author.setAuthorRegisterDate(new Date());

		LOG.info("Author Code: " + authorCode);
		LOG.info("Author Name: " + authorName);

	}

	@Reference
	private AuthorLocalService authorLocalService;

	@Reference
	private BookLocalService bookLocalService;

	@Reference
	private CounterLocalService counterLocalService;

	private static final Log LOG = LogFactoryUtil.getLog(AuthorAddEditActionCommand.class.getName());

}
