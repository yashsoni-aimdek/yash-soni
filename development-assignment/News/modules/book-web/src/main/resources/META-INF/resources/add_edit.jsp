<%@page import="com.liferay.portal.kernel.service.LayoutLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="com.aimdek.assignment.model.Book"%>
<%@page import="com.liferay.portal.kernel.theme.ThemeDisplay"%>

<%@ include file="/init.jsp" %>

<portlet:actionURL name="/book/add/edit" var="addBookURL"/>
<portlet:resourceURL id="captcha" var="captchaResourceURL"/>

<portlet:renderURL var="viewBooksURL">
	<portlet:param name="mvcRenderCommandName" value="/" />
</portlet:renderURL>

<liferay-ui:error key="book-code-required" message="book-code-required" />
<liferay-ui:error key="book-name-required" message="book-name-required" />

<aui:form action="${addBookURL}" method="post">
	<aui:model-context bean="${book}" model="<%=Book.class%>"/>
	<aui:input type="hidden" name="bookId"/>
	<aui:input type="text" value="" name="bookCode" label="Book-code-Label"/>
	<aui:input type="text" value="" name="bookName" label="Book-Name"/>
	<aui:input type="submit" name="Submit" value="Submit"/>
	<aui:button name="Cancel" href="${viewBooksURL}" value="Cancel"/>
	
	
</aui:form>