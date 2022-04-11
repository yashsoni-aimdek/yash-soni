<%@ include file="/init.jsp" %>

<portlet:renderURL var="addAuthorURL">
	<portlet:param name="mvcRenderCommandName" value="/author/add/edit" />
	<portlet:param name="authorId" value="-1" />
</portlet:renderURL>

<portlet:renderURL var="viewAuthorsURL">
	<portlet:param name="mvcRenderCommandName" value="/" />
</portlet:renderURL>

<aui:button name="addAuthor" href="${addAuthorURL}" value="Add Author"/>

<br/>

<table>
	<tr>
		<th><liferay-ui:message key="author.id" /></th>
		<th><liferay-ui:message key="author.code" /></th>
		<th><liferay-ui:message key="author.name" /></th>
		<th><liferay-ui:message key="actions" /></th>
	</tr>
	<c:forEach items="${authors}" var="author">
		<tr>
			<td>${author.authorId}</td>
			<td>${author.authorCode}</td>
			<td>${author.authorName}</td>
			<td>
			
				
				<liferay-ui:icon-menu direction="Left-side" icon="" markupView="lexicon" message="actions" showWhenSingleIcon="<%= true %>">
					<portlet:renderURL var="editAuthorURL">
						<portlet:param name="mvcRenderCommandName" value="/author/add/edit" />
						<portlet:param name="authorId" value="${author.authorId}" />
					</portlet:renderURL>
					<liferay-ui:icon message="edit" url="<%= editAuthorURL%>" />
					
					<portlet:actionURL name="/author/delete" var="deleteAuthorURL" >
						<portlet:param name="redirect" value="${viewAuthorsURL}" />
						<portlet:param name="authorId" value="${author.authorId}" />
					</portlet:actionURL>
					<liferay-ui:icon message="delete" url="<%= deleteAuthorURL%>" />	
				</liferay-ui:icon-menu>
			</td>
		</tr>
	</c:forEach>
</table>