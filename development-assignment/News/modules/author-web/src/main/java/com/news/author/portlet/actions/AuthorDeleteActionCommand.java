package com.news.author.portlet.actions;

import java.util.Date;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.counter.kernel.service.CounterLocalService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.news.author.constants.AuthorWebPortletKeys;
import com.aimdek.assignment.model.Author;
import com.aimdek.assignment.service.AuthorLocalService;

@Component(
		immediate = true,
		property = {
				"javax.portlet.name=" + AuthorWebPortletKeys.AUTHORWEB,
				"mvc.command.name=/author/delete"
		},
		service = MVCActionCommand.class
)
public class AuthorDeleteActionCommand extends BaseMVCActionCommand{
	
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		
		long authorId = ParamUtil.getLong(actionRequest, "authorId", -1);
		authorLocalService.deleteAuthor(authorId);
		
		 
	}
	
	@Reference
	private AuthorLocalService authorLocalService;
	
	
	private static final Log LOG = LogFactoryUtil.getLog(AuthorDeleteActionCommand.class.getName());

}
