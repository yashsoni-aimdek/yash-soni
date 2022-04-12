package com.news.author.portlet.actions;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.aimdek.assignment.model.Author;
import com.aimdek.assignment.service.AuthorLocalService;
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
		Author author = null;	
		try {
			if(authorId > 0) {
				author = authorLocalService.getAuthor(authorId);
			}
		}catch(PortalException e) {
			LOG.error(e.getMessage(), e);
		}
		renderRequest.setAttribute("author", author);
		return "/add_edit.jsp";
	}
	
	@Reference
	private AuthorLocalService authorLocalService;
	
	private static final Log LOG = LogFactoryUtil.getLog(AuthorAddEditActionCommand.class);
}
