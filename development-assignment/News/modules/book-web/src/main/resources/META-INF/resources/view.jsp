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
		<th><liferay-ui:message key="actions" /></th>
	</tr>
	<c:forEach items="${books}" var="book">
		<tr>
			<td>${book.bookId}</td>
			<td>${book.bookCode}</td>
			<td>${book.bookName}</td>
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