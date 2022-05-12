<%@page import="java.util.Map"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="com.liferay.portal.kernel.log.LogFactoryUtil"%>
<%@page import="com.liferay.portal.kernel.log.Log"%>
<%@page import="com.aimdek.assignment.model.Book"%>
<%@page import="java.util.List"%>
<%@page import="com.aimdek.assignment.service.AuthorLocalServiceUtil"%>
<%@page import="com.aimdek.assignment.model.Author"%>
<%@ include file="/init.jsp" %>

<portlet:renderURL var="addBookURL">
	<portlet:param name="mvcRenderCommandName" value="/book/add/edit" />
	<portlet:param name="bookId" value="-1" />
</portlet:renderURL>

<portlet:renderURL var="viewBookURL">
	<portlet:param name="mvcRenderCommandName" value="/" />
</portlet:renderURL>

<aui:button name="addBook" href="${addBookURL}" value="Add Book"/>

<br/>

<table>
	<tr>
		<th><liferay-ui:message key="book.id" /></th>
		<th><liferay-ui:message key="book.code" /></th>
		<th><liferay-ui:message key="book.name" /></th>
		<th><liferay-ui:message key="author.name" /></th>
		<th><liferay-ui:message key="actions" /></th>
	</tr>
	<c:forEach items="${books}" var="book">
		<tr>
			<td>${book.bookId}</td>
			<td>${book.bookCode}</td>
			<td>${book.bookName}</td>
			<%
				//				String[] authors = ((Book)pageContext.getAttribute("book")).get().split(",");
				
				
					Map<Long, List<Author>> authorsOfBooks = (Map<Long, List<Author>>)renderRequest.getAttribute("bookAuthors");
					Log log = LogFactoryUtil.getLog(this.getClass());
					log.info("authorsOfBooks : " + authorsOfBooks);
					List<Author> authors = authorsOfBooks.get(((Book)pageContext.getAttribute("book")).getBookId());
					StringBuilder authorNames = new StringBuilder();
					log.info("Author names fetched : " + authorNames);
					if(authors!=null) {
						for (int i = 0; i < authors.size(); i++) {
							Author author = authors.get(i);
							if (author != null) {
								authorNames.append(author.getAuthorName());
								authorNames.append(",");
							}
						}
					}
					if (authorNames.toString().length() > 0) {
						int lastIndexOfComma = authorNames.lastIndexOf(",");
						log.info("Author Names : " + authorNames);
						log.info("LastIndexOfComma : " + lastIndexOfComma);
						authorNames = authorNames.replace(lastIndexOfComma, lastIndexOfComma + 1, "");
					} else {
						authorNames.append("Unknown Author");
					}
			%>
			<td><%= authorNames.toString()%></td>
			<td>
			
				
				<liferay-ui:icon-menu direction="Left-side" icon="" markupView="lexicon" message="actions" showWhenSingleIcon="<%= true %>">
					<portlet:renderURL var="editBookURL">
						<portlet:param name="mvcRenderCommandName" value="/book/add/edit" />
						<portlet:param name="bookId" value="${book.bookId}" />
					</portlet:renderURL>
					<liferay-ui:icon message="edit" url="<%= editBookURL%>" />
					
					<portlet:actionURL name="/book/delete" var="deleteBookURL" >
						<portlet:param name="redirect" value="${viewBooksURL}" />
						<portlet:param name="bookId" value="${book.bookId}" />
					</portlet:actionURL>
					<liferay-ui:icon message="delete" url="<%= deleteBookURL%>" />	
				</liferay-ui:icon-menu>
			</td>
		</tr>
	</c:forEach>
</table>