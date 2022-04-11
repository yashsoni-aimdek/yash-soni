<%@page import="com.aimdek.assignment.model.Author"%>
<%@ include file="/init.jsp" %>

<portlet:actionURL name="/author/add/edit" var="addAuthorURL"/>

<portlet:renderURL var="viewAuthorsURL">
	<portlet:param name="mvcRenderCommandName" value="/" />
</portlet:renderURL>

<liferay-ui:error key="author-code-required" message="author-code-required" />
<liferay-ui:error key="author-name-required" message="author-name-required" />

<aui:form action="${addAuthorURL}" method="post">
	<aui:model-context bean="${author}" model="<%=Author.class%>"/>
	<aui:input type="hidden" name="authorId"/>
	<aui:input type="text" name="authorCode" label="Author-code-Label"/>
	<aui:input type="text" name="authorName" label="Author-Name"/>
	<aui:input type="submit" name="Submit" value="Submit"/>
	<aui:button name="Cancel" href="${viewAuthorsURL}" value="Cancel"/>
	
</aui:form>