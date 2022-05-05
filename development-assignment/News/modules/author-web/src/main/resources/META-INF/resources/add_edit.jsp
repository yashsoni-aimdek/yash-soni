<%@page import="com.aimdek.assignment.model.Author"%>
<%@ include file="/init.jsp" %>

<link rel="stylesheet" href="css/multi-select.min.css">

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
	
 	<aui:select id="bookNameId" name="bookId" multiple="true">

	</aui:select>

<aui:input type="submit" name="Submit" value="Submit"/>
	<aui:button name="Cancel" href="${viewAuthorsURL}" value="Cancel"/>
	
</aui:form>

 <script type="text/javascript" >	


$(document).ready(function() {
	Liferay.Service(
			  '/news.book/get-all-book',
			  function(obj) {
			    console.log(obj);
			    
			    var list = $("#<portlet:namespace/>bookNameId");
			    for (let key of obj) {
			    list.append(new Option(key.bookName, key.bookId));
			    }
			    
			  }
			);
});
</script>

