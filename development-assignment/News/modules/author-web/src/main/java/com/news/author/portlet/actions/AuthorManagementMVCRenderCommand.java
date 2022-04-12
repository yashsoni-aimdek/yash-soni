package com.news.author.portlet.actions;

import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.aimdek.assignment.model.Author;
import com.aimdek.assignment.service.AuthorLocalService;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.news.author.constants.AuthorWebPortletKeys;

@Component(
		immediate = true,
		property = {
				"javax.portlet.name=" + AuthorWebPortletKeys.AUTHORWEB,
				"mvc.command.name=/"
		},
		service = MVCRenderCommand.class
)
public class AuthorManagementMVCRenderCommand implements MVCRenderCommand{
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		
		List<Author> authors = authorLocalService.getAuthors(-1, -1);
		renderRequest.setAttribute("authors", authors);
		
		return "/view.jsp";
	}
	
	@Reference
	private AuthorLocalService authorLocalService;

}
